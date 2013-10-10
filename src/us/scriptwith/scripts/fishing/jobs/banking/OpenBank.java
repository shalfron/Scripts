package us.scriptwith.scripts.fishing.jobs.banking;

import org.powerbot.script.wrappers.Interactive;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 9:37 PM
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

public class OpenBank extends Job<Fishing> {
    public OpenBank(Fishing script) {
        super(script);
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean activate() {
        final Interactive banker = (Interactive) ctx.bank.getNearest();
        return !ctx.bank.isOpen()
                && banker != null && banker.isOnScreen()
                && (!script.methods.hasBait() || ctx.backpack.select().count() == 28);
    }

    @Override
    public void execute() {
        ctx.camera.turnTo(ctx.bank.getNearest());
        ctx.bank.open();
    }
}
