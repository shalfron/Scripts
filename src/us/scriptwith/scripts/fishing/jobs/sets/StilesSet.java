package us.scriptwith.scripts.fishing.jobs.sets;

import us.scriptwith.core.job.JobSet;
import us.scriptwith.scripts.fishing.Fishing;
import us.scriptwith.scripts.fishing.jobs.interactive.stiles.ExchangeStiles;
import us.scriptwith.scripts.fishing.jobs.interactive.stiles.ToStiles;

/**
 * Date: 9/22/13
 * Time: 3:21 PM
 */

public class StilesSet extends JobSet<Fishing> {
    public StilesSet(Fishing script) {
        super(script, new ToStiles(script), new ExchangeStiles(script));
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28;
    }
}
