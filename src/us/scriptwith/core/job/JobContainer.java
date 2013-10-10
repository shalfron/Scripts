package us.scriptwith.core.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Aadil Farouk
 * Date: 6/17/13
 * Time: 3:59 PM
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

public class JobContainer implements Comparator<Job> {
    private List<Job> jobList = new ArrayList<>();

    public JobContainer(Job[] jobs) {
        submit(jobs);
    }

    public JobContainer() {
        this(new Job[0]);
    }

    public void submit(final Job... jobs) {
        for (Job j : jobs) {
            if (!jobList.contains(j)) {
                jobList.add(j);
            }
        }
        Collections.sort(jobList, this);
    }

    @Override
    public int compare(Job o1, Job o2) {
        return o2.priority() - o1.priority();
    }

    public void clear() {
        jobList.clear();
    }

    public Job get() {
        for (Job j : jobList) {
            if (j.activate()) {
                return j;
            }
        }
        return null;
    }
}
