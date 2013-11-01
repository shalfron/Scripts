package us.scriptwith.util;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 6/29/13
 * Time: 11:41 PM
 */

public abstract class InventoryMonitor extends MethodProvider {
    private Item[] cache = new Item[0];

    public InventoryMonitor(MethodContext ctx) {
        super(ctx);
        update();
    }

    public abstract void onChange();

    public boolean hasChanged() {
        return getChanges().length > 0;
    }

    public void update() {
        System.out.println("Updating item cache");
        cache = new Item[ctx.backpack.select().count()];
        int i = 0;
        for (Item item : ctx.backpack) {
            cache[i++] = new Item(ctx, item.getId(), item.getStackSize(), null);
        }
    }

    public Item[] getChanges() {
        int count = ctx.backpack.select().count();
        List<Item> items = new ArrayList<>(count);
        Item[] ci = new Item[count];
        int changes = 0;
        ctx.backpack.addTo(items);
        for (Item item : items) {
            int id = item.getId();
            int c1 = ctx.backpack.select(items).id(id).count(true), c2 = cached(id);
            if (c1 != c2 && !contains(ci, id)) {
                ci[changes++] = new Item(ctx, id, c1 - c2, null);
            }
        }
        return Arrays.copyOf(ci, changes);
    }

    private boolean contains(Item[] list, int id) {
        for (Item i : list) {
            if (i == null) continue;
            if (i.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private int cached(int id) {
        int count = 0;
        for (Item i : cache) {
            if (i.getId() == id) {
                count += i.getStackSize();
            }
        }
        return count;
    }
}
