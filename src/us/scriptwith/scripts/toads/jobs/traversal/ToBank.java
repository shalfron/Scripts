package us.scriptwith.scripts.toads.jobs.traversal;

import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.core.script.generic.Traversal;
import us.scriptwith.scripts.toads.SwampToads;

/**
 * Date: 10/17/13
 * Time: 5:03 PM
 */

public class ToBank extends Traversal<SwampToads> {
    public ToBank(SwampToads script) {
        super(script, new TilePath(script.getContext(), new Tile[]{
                new Tile(2419, 3510, 0),
                new Tile(2422, 3483, 0),
                new Tile(2422, 3471, 0),
                new Tile(2437, 3451, 0),
                new Tile(2444, 3433, 0)
        }));
    }

    @Override
    public boolean activate() {
        final GameObject stairsUp = ctx.objects.select().id(69505).at(new Tile(2446, 3434, 0)).poll();
        return ctx.backpack.select().count() == 28 && ctx.players.local().getLocation().getPlane() == 0
                && (!stairsUp.isValid() || !stairsUp.isOnScreen());
    }
}
