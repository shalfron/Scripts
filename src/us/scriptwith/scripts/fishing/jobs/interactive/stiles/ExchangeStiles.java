package us.scriptwith.scripts.fishing.jobs.interactive.stiles;

import org.powerbot.script.lang.Filter;
import org.powerbot.script.wrappers.Npc;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/22/13
 * Time: 3:26 PM
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

public class ExchangeStiles extends Job<Fishing> {
    private Filter<Npc> onScreen = new Filter<Npc>() {
        @Override
        public boolean accept(Npc npc) {
            return npc.isOnScreen();
        }
    };

    public ExchangeStiles(Fishing script) {
        super(script);
    }

    @Override
    public boolean activate() {
        return !ctx.npcs.select().select(onScreen).id(11267).isEmpty();
    }

    @Override
    public void execute() {
        for (Npc stiles : ctx.npcs) {
            if (stiles.interact("Exchange")) {
                sleep(500, 750);
            }
        }
    }
}
