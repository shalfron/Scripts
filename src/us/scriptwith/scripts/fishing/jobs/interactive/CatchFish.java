package us.scriptwith.scripts.fishing.jobs.interactive;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.wrappers.Actor;
import org.powerbot.script.wrappers.Npc;
import us.scriptwith.core.script.generic.Interaction;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 4:46 PM
 */

public class CatchFish extends Interaction<Fishing, Npc, BasicNamedQuery<Npc>> {
    public CatchFish(Fishing script) {
        super(script, script.getContext().npcs, script.fish.getAction(), script.fish.getSpotIds());
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
}
