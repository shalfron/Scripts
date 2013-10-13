package us.scriptwith.scripts.fishing.jobs.interactive.stiles;

import org.powerbot.script.lang.Filter;
import org.powerbot.script.wrappers.Npc;
import us.scriptwith.core.script.generic.Interaction;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/22/13
 * Time: 3:26 PM
 */

public class ExchangeStiles extends Interaction<Fishing, Npc> {
    private Filter<Npc> onScreen = new Filter<Npc>() {
        @Override
        public boolean accept(Npc npc) {
            return npc.isOnScreen();
        }
    };

    public ExchangeStiles(Fishing script) {
        super(script, script.getContext().npcs, new int[]{11267}, "Exchange");
    }

    @Override
    public boolean activate() {
        return !ctx.npcs.select().select(onScreen).id(11267).isEmpty() && ctx.backpack.select().count() == 28;
    }
}
