package us.scriptwith.scripts.fishing.jobs.sets;

import us.scriptwith.core.job.JobSet;
import us.scriptwith.scripts.fishing.Fishing;
import us.scriptwith.scripts.fishing.jobs.interactive.summoning.DrinkPotion;

/**
 * Date: 9/23/13
 * Time: 9:20 PM
 */

public class SummoningSet extends JobSet<Fishing> {
    public SummoningSet(Fishing script) {
        super(script, new RenewFamiliarSet(script), new DrinkPotion(script));
    }

    @Override
    public boolean activate() {
        return script.methods.hasSummoningMaterials();
    }
}
