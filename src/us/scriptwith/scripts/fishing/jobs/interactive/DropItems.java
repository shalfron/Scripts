package us.scriptwith.scripts.fishing.jobs.interactive;

import org.powerbot.script.wrappers.Item;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 9:50 PM
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

public class DropItems extends Job<Fishing> {
    private final int[] junkIds;

    public DropItems(Fishing script, int... junkIds) {
        super(script);
        this.junkIds = junkIds;
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String status() {
        return "Dropping";
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28 && ctx.backpack.id(junkIds).count() > 0;
    }

    @Override
    public void execute() {
        for (Item i : ctx.backpack) {
            ctx.backpack.scroll(i);
            i.interact("Drop");
            sleep(250, 300);
        }
    }
}
