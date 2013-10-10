package us.scriptwith.scripts.fishing.jobs.interactive.stiles;

import org.powerbot.script.lang.Filter;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/22/13
 * Time: 3:23 PM
 */

public class ToStiles extends Job<Fishing> {
    private TilePath path;
    private Filter<Npc> onScreen = new Filter<Npc>() {
        @Override
        public boolean accept(Npc npc) {
            return npc.isOnScreen();
        }
    };

    public ToStiles(Fishing script) {
        super(script);
        this.path = new TilePath(ctx, new Tile[]{
                new Tile(2924, 3177, 0),
                new Tile(2900, 3162, 0),
                new Tile(2882, 3145, 0),
                new Tile(2867, 3148, 0),
                new Tile(2852, 3144, 0)
        });
    }

    @Override
    public boolean activate() {
        return ctx.npcs.select().select(onScreen).id(11267).isEmpty();
    }

    @Override
    public void execute() {
        if (path.traverse() || ctx.movement.stepTowards(path.getEnd())) {
            sleep(350, 500);
        }
    }
}
