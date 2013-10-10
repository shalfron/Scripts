package us.scriptwith.scripts.fishing.jobs.sets;

import us.scriptwith.core.job.JobSet;
import us.scriptwith.scripts.fishing.Fishing;
import us.scriptwith.scripts.fishing.jobs.interactive.summoning.RenewFamiliar;
import us.scriptwith.scripts.fishing.jobs.interactive.summoning.SummonFamiliar;

/**
 * Date: 9/23/13
 * Time: 9:54 PM
 */

public class RenewFamiliarSet extends JobSet<Fishing> {
    public RenewFamiliarSet(Fishing script) {
        super(script, new SummonFamiliar(script), new RenewFamiliar(script));
    }

    @Override
    public boolean activate() {
        return ctx.summoning.getSummoningPoints() >= script.familiar.getRequiredPoints();
    }
}
