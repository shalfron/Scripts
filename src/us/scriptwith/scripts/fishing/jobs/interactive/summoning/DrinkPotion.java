package us.scriptwith.scripts.fishing.jobs.interactive.summoning;

import org.powerbot.script.wrappers.Item;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/23/13
 * Time: 9:43 PM
 */

public class DrinkPotion extends Job<Fishing> {
    public DrinkPotion(Fishing script) {
        super(script);
    }

    @Override
    public boolean activate() {
        return ctx.summoning.getSummoningPoints() < script.familiar.getRequiredPoints();
    }

    @Override
    public void execute() {
        for (Item i : ctx.backpack.select().id(script.methods.potionIds).first()) {
            if (i.interact("Drink")) {
                sleep(750, 1000);
            }
        }
    }
}
