package us.scriptwith.scripts.toads.jobs.traversal;

import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.core.job.impl.Traversal;
import us.scriptwith.scripts.toads.SwampToads;

/**
 * Date: 10/17/13
 * Time: 5:08 PM
 */

public class ToToads extends Traversal<SwampToads> {
    public ToToads(SwampToads script) {
        super(script, new TilePath(script.getContext(), new Tile[]{
                new Tile(2444, 3433, 0),
                new Tile(2437, 3451, 0),
                new Tile(2422, 3471, 0),
                new Tile(2422, 3483, 0),
                new Tile(2419, 3510, 0)
        }));
    }

    @Override
    public String status() {
        return "Walking to toads";
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() < 28 && ctx.players.local().getLocation().getPlane() == 0
                && ctx.groundItems.select().id(2150).isEmpty();
    }
}
