package us.scriptwith.scripts.fishing.jobs.interactive.summoning;

import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/23/13
 * Time: 9:22 PM
 */

public class RenewFamiliar extends Job<Fishing> {
    public RenewFamiliar(Fishing script) {
        super(script);
    }

    @Override
    public boolean activate() {
        return ctx.summoning.isFamiliarSummoned();
    }

    @Override
    public void execute() {
        if (ctx.summoning.renewFamiliar()) {
            sleep(1500);
        }
    }
}
