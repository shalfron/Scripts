package us.scriptwith.scripts.fishing.jobs.interactive.summoning;

import org.powerbot.script.wrappers.Item;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/23/13
 * Time: 9:09 PM
 */

public class SummonFamiliar extends Job<Fishing> {
    public SummonFamiliar(Fishing script) {
        super(script);
    }

    @Override
    public boolean activate() {
        return !ctx.summoning.isFamiliarSummoned();
    }

    @Override
    public void execute() {
        for (Item i : ctx.backpack.select().id(script.familiar.getPouchId()).first()) {
            if (i.interact("Summon")) {
                sleep(1500);
            }
        }
    }
}
