package us.scriptwith.core.job;

import us.scriptwith.core.script.Script;

/**
 * Author: Saleh Jafar
 * Date: 7/15/13
 * Time: 10:45 PM
 *
 * COPYRIGHT 2013, Saleh Jafar
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

public abstract class JobSet<T extends Script> extends Job<T> {
    private JobContainer container;
    private String status = "";

    public JobSet(T script, Job... jobs) {
        super(script);
        this.container = new JobContainer(jobs);
    }

    @Override
    public final void execute() {
        final Job job = container.get();
        if (job != null) {
            status = job.status();
            job.execute();
        }
    }

    @Override
    public String status() {
        return status;
    }
}
