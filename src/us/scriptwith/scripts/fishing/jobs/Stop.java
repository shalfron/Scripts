package us.scriptwith.scripts.fishing.jobs;

import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 9:35 PM
 */

public class Stop extends Job<Fishing> {
    public Stop(Fishing script) {
        super(script);
    }

    @Override
    public boolean activate() {
        return !script.methods.hasBait();
    }

    @Override
    public void execute() {
        script.getController().stop();
    }
}
