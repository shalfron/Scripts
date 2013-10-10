package us.scriptwith.scripts.fishing.jobs.interactive;

import org.powerbot.script.wrappers.Actor;
import org.powerbot.script.wrappers.Npc;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 4:46 PM
 */

/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
