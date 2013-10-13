package us.scriptwith.scripts.fishing.jobs.traversal;

import org.powerbot.script.wrappers.Actor;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.core.script.generic.Traversal;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/16/13
 * Time: 3:12 PM
 */

public class ToSpot extends Traversal<Fishing> {
    public ToSpot(Fishing script, TilePath path) {
        super(script, path);
    }

    @Override
    public String status() {
        return "Walking to spot";
    }

    @Override
    public boolean activate() {
        final Npc fish = ctx.npcs.select().id(script.fish.getSpotIds()).nearest().first().poll();
        final Actor interacting = ctx.players.local().getInteracting();
        return script.methods.hasBait()
                && ctx.backpack.select().count() < 28
                && (!fish.isValid() || !fish.isOnScreen())
                && (interacting == null || !interacting.isValid());
    }
}
