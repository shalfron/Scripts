package us.scriptwith.core.script;

import org.powerbot.script.PollingScript;
import us.scriptwith.core.job.Job;
import us.scriptwith.core.job.JobContainer;
import us.scriptwith.util.InventoryMonitor;
import us.scriptwith.util.PriceWrapper;

/**
 * Date: 8/22/13
 * Time: 10:24 PM
 */

public abstract class Script extends PollingScript {
    private JobContainer container;
    private InventoryMonitor monitor;
    private PriceWrapper prices;

    public Script() {
        this.container = new JobContainer();
        this.prices = new PriceWrapper();
    }

    @Override
    public int poll() {
        final Job j = container.get();
        if (j != null) {
            System.out.println("Executing " + j.getClass().getSimpleName());
            j.execute();
            return j.delay();
        }
        return 500;
    }

    public void setMonitor(InventoryMonitor monitor) {
        this.monitor = monitor;
    }

    public InventoryMonitor monitor() {
        return this.monitor;
    }

    public JobContainer container() {
        return this.container;
    }

    public PriceWrapper prices() {
        return this.prices;
    }
}
