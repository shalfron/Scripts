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

/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
