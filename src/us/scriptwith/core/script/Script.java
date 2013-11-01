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
    private String status = "";

    public Script() {
        this.container = new JobContainer();
        this.prices = new PriceWrapper();
    }

    @Override
    public int poll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (monitor != null && !ctx.bank.isOpen() && monitor.hasChanged()) {
                    monitor.onChange();
                }
            }
        }).start();

        final Job j = container.get();
        if (j != null) {
            //System.out.println("Executing " + j.getClass().getSimpleName());
            status = j.status();
            j.execute();
            return j.delay();
        }
        return 500;
    }

    public void setMonitor(InventoryMonitor monitor) {
        this.monitor = monitor;
        monitor.update();
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

    public String status() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
