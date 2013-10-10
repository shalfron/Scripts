package us.scriptwith.util.defined;

import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Npc;
import us.scriptwith.core.job.Job;
import us.scriptwith.core.script.Script;

/**
 * Date: 8/18/13
 * Time: 3:33 PM
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

public class RandomCameraMovement<T extends Script> extends Job<T> {
    public RandomCameraMovement(T ctx) {
        super(ctx);
    }

    @Override
    public int priority() {
        return -1;
    }

    @Override
    public boolean activate() {
        return Math.random() > .95;
    }

    @Override
    public void execute() {
        if (Math.random() > .5) {
            for (Npc n : ctx.npcs.select().shuffle().first()) {
                ctx.camera.turnTo(n);
            }
        } else {
            for (GameObject n : ctx.objects.select().shuffle().first()) {
                ctx.camera.turnTo(n);
            }
        }
    }

    @Override
    public String status() {
        return "Camera Antipattern";
    }
}
