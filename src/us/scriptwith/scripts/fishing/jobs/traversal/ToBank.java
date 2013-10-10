package us.scriptwith.scripts.fishing.jobs.traversal;

import org.powerbot.script.wrappers.Interactive;
import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/16/13
 * Time: 3:06 PM
 */

public class ToBank extends Job<Fishing> {
    private final TilePath path;

    public ToBank(Fishing script, TilePath path) {
        super(script);
        this.path = path;
    }

    @Override
    public String status() {
        return "Walking to bank";
    }

    @Override
    public boolean activate() {
        final Interactive bank = (Interactive) ctx.bank.getNearest();
        return (!script.methods.hasBait() || ctx.backpack.select().count() == 28)
                && (!bank.isValid() || !bank.isOnScreen());
    }

    @Override
    public void execute() {
        if (path.traverse() || ctx.movement.stepTowards(path.getEnd())) {
            sleep(350, 500);
        }
    }
}
