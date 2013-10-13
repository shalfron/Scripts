package us.scriptwith.core.job;

import org.powerbot.script.methods.MethodProvider;
import us.scriptwith.core.script.Script;

/**
 * Author: Aadil Farouk
 * Date: 8/23/13
 * Time: 3:57 PM
 *
 * COPYRIGHT 2013, AADIL FAROUK
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

public abstract class Job<T extends Script> extends MethodProvider {
    protected T script;

    public Job(T t) {
        super(t.getContext());
        this.script = t;
    }

    public int delay() {
        return (int) ((Math.random() * 100) + 500);
    }

    public int priority() {
        return 0;
    }

    public String status() {
        return "";
    }

    public abstract boolean activate();

    public abstract void execute();
}
