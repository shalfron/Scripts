package us.scriptwith.scripts.flax.jobs;

import org.powerbot.script.wrappers.GameObject;
import us.scriptwith.core.script.generic.Interaction;
import us.scriptwith.scripts.flax.Flax;

/**
 * Date: 10/17/13
 * Time: 12:02 AM
 */

public class PickFlax extends Interaction<Flax, GameObject> {
    public PickFlax(Flax script) {
        super(script, script.getContext().objects, new int[]{2646}, "Pick");
    }

    @Override
    public boolean activate() {
        return !ctx.objects.select().id(2646).select(onScreen).isEmpty()
                && ctx.backpack.select().count() < 28;
    }
}
