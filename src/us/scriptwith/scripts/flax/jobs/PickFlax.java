package us.scriptwith.scripts.flax.jobs;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.wrappers.GameObject;
import us.scriptwith.core.job.impl.Interaction;
import us.scriptwith.scripts.flax.Flax;

/**
 * Date: 10/17/13
 * Time: 12:02 AM
 */

public class PickFlax extends Interaction<Flax, GameObject, BasicNamedQuery<GameObject>> {
    public PickFlax(Flax script) {
        super(script, script.getContext().objects, "Pick", 2646);
    }

    @Override
    public String status() {
        return "Picking flax";
    }

    @Override
    public int delay() {
        return 500;
    }

    @Override
    public boolean activate() {
        return !ctx.objects.select().id(2646).select(onScreen).isEmpty()
                && ctx.backpack.select().count() < 28;
    }

    @Override
    public Boolean call() throws Exception {
        return !ctx.players.local().isInMotion();
    }
}
