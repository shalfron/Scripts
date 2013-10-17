package us.scriptwith.scripts.toads.jobs;

import org.powerbot.script.lang.GroundItemQuery;
import org.powerbot.script.wrappers.GroundItem;
import us.scriptwith.core.script.generic.Interaction;
import us.scriptwith.scripts.toads.SwampToads;

/**
 * Date: 10/17/13
 * Time: 2:17 AM
 */

public class TakeToad extends Interaction<SwampToads, GroundItem, GroundItemQuery<GroundItem>> {
    public TakeToad(SwampToads script) {
        super(script, script.getContext().groundItems, "Take", 2150);
    }

    @Override
    public boolean activate() {
        return !ctx.groundItems.select().id(ids).isEmpty() && ctx.backpack.select().count() < 28;
    }
}
