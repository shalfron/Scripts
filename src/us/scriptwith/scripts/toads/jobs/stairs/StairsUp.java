package us.scriptwith.scripts.toads.jobs.stairs;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import us.scriptwith.core.script.generic.Interaction;
import us.scriptwith.scripts.toads.SwampToads;

/**
 * Date: 10/17/13
 * Time: 5:11 PM
 */

public class StairsUp extends Interaction<SwampToads, GameObject, BasicNamedQuery<GameObject>> {
    public StairsUp(SwampToads script) {
        super(script, script.getContext().objects, "Climb-up", 69505);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28
                && !ctx.objects.select().id(ids).select(onScreen).at(new Tile(2446, 3434, 0)).isEmpty();
    }

    @Override
    public Boolean call() throws Exception {
        return ctx.players.local().getLocation().getPlane() == 1;
    }
}
