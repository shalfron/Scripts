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
