package us.scriptwith.scripts.fishing.jobs.traversal;

import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/16/13
 * Time: 3:12 PM
 */

public class ToSpot extends Job<Fishing> {
    private final TilePath path;

    public ToSpot(Fishing script, TilePath path) {
        super(script);
        this.path = path;
    }

    @Override
    public String status() {
        return "Walking to spot";
    }

    @Override
    public boolean activate() {
        final Npc fish = ctx.npcs.select().id(script.fish.getSpotIds()).first().poll();
        return script.methods.hasBait()
                && ctx.backpack.select().count() < 28
                && (!fish.isValid() || !fish.isOnScreen());
    }

    @Override
    public void execute() {
        final Npc fish = ctx.npcs.select().id(script.fish.getSpotIds()).first().poll();
        if (fish.isValid()) {
            ctx.movement.stepTowards(fish);
            ctx.camera.turnTo(fish);
            sleep(350, 500);
        } else if (path.traverse() || ctx.movement.stepTowards(path.getEnd())) {
            sleep(350, 500);
        }
    }
}
