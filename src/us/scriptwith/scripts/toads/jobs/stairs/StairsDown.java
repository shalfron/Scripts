package us.scriptwith.scripts.toads.jobs.stairs;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.wrappers.GameObject;
import us.scriptwith.core.job.impl.Interaction;
import us.scriptwith.scripts.toads.SwampToads;

/**
 * Date: 10/17/13
 * Time: 5:17 PM
 */

public class StairsDown extends Interaction<SwampToads, GameObject, BasicNamedQuery<GameObject>> {
    public StairsDown(SwampToads script) {
        super(script, script.getContext().objects, "Climb-down", 69404);
    }

    @Override
    public String status() {
        return "Climbing stairs";
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() < 28 && ctx.players.local().getLocation().getPlane() == 1
                && !ctx.objects.select().id(ids).isEmpty();
    }

    @Override
    public void execute() {
        ctx.camera.turnTo(ctx.objects.select().id(ids).nearest().poll());
        super.execute();
    }

    @Override
    public Boolean call() throws Exception {
        return ctx.players.local().getLocation().getPlane() == 0;
    }
}
