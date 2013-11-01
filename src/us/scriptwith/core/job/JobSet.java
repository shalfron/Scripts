package us.scriptwith.core.job;

import us.scriptwith.core.script.Script;

/**
 * Date: 7/15/13
 * Time: 10:45 PM
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
            //System.out.println("Executing " + job.getClass().getSimpleName());
            status = job.status();
            job.execute();
        }
    }

    @Override
    public String status() {
        return status;
    }
}
