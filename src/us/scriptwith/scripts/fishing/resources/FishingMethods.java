package us.scriptwith.scripts.fishing.resources;

import org.powerbot.script.lang.Filter;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Item;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 5:38 PM
 */

public class FishingMethods extends MethodProvider {
    public final int[] potionIds = {12146, 12144, 12142, 12140};
    private Fishing script;

    public FishingMethods(Fishing script) {
        super(script.getContext());
        this.script = script;
    }

    public boolean hasBait() {
        return script.fish.getBaitId() == -1 || !ctx.backpack.select().id(script.fish.getBaitId()).isEmpty();
    }

    public boolean hasSummoningMaterials() {
        return hasPouch() && hasSummoningPotion();
    }

    public boolean hasPouch() {
        return !ctx.backpack.select().id(script.familiar.getPouchId()).isEmpty();
    }

    public boolean hasSummoningPotion() {
        return !ctx.backpack.select().id(potionIds).isEmpty();
    }

    public Filter<Item> erroneousItems = new Filter<Item>() {
        @Override
        public boolean accept(Item item) {
            for (int i : potionIds) {
                if (item.getId() == i) {
                    return false;
                }
            }
            //TODO urn ids
            return item.getId() != script.fish.getBaitId();
        }
    };
}
