package us.scriptwith.core.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Date: 6/17/13
 * Time: 3:59 PM
 */

public class JobContainer implements Comparator<Job> {
    private final List<Job> jobList = new ArrayList<>();

    public JobContainer(Job[] jobs) {
        submit(jobs);
    }

    public JobContainer() {
        this(new Job[0]);
    }

    public void submit(final Job... jobs) {
        synchronized (jobList) {
            for (Job j : jobs) {
                if (!jobList.contains(j)) {
                    jobList.add(j);
                }
            }
            Collections.sort(jobList, this);
        }
    }

    @Override
    public int compare(Job o1, Job o2) {
        return o2.priority() - o1.priority();
    }

    public void clear() {
        jobList.clear();
    }

    public Job get() {
        synchronized (jobList) {
            for (Job j : jobList) {
                //System.out.println("Checking " + j.getClass().getSimpleName());
                if (j.activate()) {
                    return j;
                }
            }
        }
        return null;
    }
}
