package us.scriptwith.scripts.fishing.jobs.interactive;

import org.powerbot.script.wrappers.Actor;
import org.powerbot.script.wrappers.Npc;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 4:46 PM
 */

public class CatchFish extends Job<Fishing> {
    public CatchFish(Fishing script) {
        super(script);
    }

    @Override
    public String status() {
        return "Fishing";
    }

    @Override
    public boolean activate() {
        final Actor interacting = ctx.players.local().getInteracting();
        return script.methods.hasBait()
                && !ctx.bank.isOpen()
                && ctx.backpack.select().count() < 28
                && !ctx.npcs.select().id(script.fish.getSpotIds()).isEmpty()
                && (interacting == null || !interacting.isValid());
    }

    @Override
    public void execute() {
        for (Npc fishingSpot : ctx.npcs.nearest().first()) {
            if (!fishingSpot.isOnScreen()) {
                ctx.movement.stepTowards(fishingSpot);
                ctx.camera.turnTo(fishingSpot);
            } else {
                fishingSpot.interact(script.fish.getAction());
            }
            sleep(500, 750);
        }
    }
}
