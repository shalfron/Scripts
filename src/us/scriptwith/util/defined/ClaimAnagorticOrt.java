package us.scriptwith.util.defined;

import org.powerbot.script.wrappers.GroundItem;
import us.scriptwith.core.job.Job;
import us.scriptwith.core.script.Script;

/**
 * Date: 8/18/13
 * Time: 4:44 PM
 */

public class ClaimAnagorticOrt<T extends Script> extends Job<T> {
    public ClaimAnagorticOrt(T script) {
        super(script);
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String status() {
        return "Claiming Anagortic Ort";
    }

    @Override
    public boolean activate() {
        return !ctx.groundItems.select().id(24909).isEmpty();
    }

    @Override
    public void execute() {
        for (GroundItem i : ctx.groundItems.nearest().first()) {
            if (!i.isOnScreen()) {
                ctx.movement.stepTowards(i);
            } else {
                i.interact("Take");
            }
            sleep(500, 600);
        }
    }
}
