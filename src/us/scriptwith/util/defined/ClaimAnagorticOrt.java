package us.scriptwith.util.defined;

import org.powerbot.script.wrappers.GroundItem;
import us.scriptwith.core.job.Job;
import us.scriptwith.core.script.Script;

/**
 * Date: 8/18/13
 * Time: 4:44 PM
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

public class ClaimAnagorticOrt<T extends Script> extends Job<T> {
    public ClaimAnagorticOrt(T script) {
        super(script);
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String status() {
        return "Claiming Anagortic Ort";
    }

    @Override
    public boolean activate() {
        return !ctx.groundItems.select().id(24909).isEmpty();
    }

    @Override
    public void execute() {
        for (GroundItem i : ctx.groundItems.nearest().first()) {
            if (!i.isOnScreen()) {
                ctx.movement.stepTowards(i);
            } else {
                i.interact("Take");
            }
            sleep(500, 600);
        }
    }
}
