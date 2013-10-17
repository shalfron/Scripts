package us.scriptwith.scripts.flax.jobs;

import org.powerbot.script.wrappers.Interactive;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.flax.Flax;

/**
 * Date: 10/17/13
 * Time: 12:19 AM
 */

public class Bank extends Job<Flax> {
    public Bank(Flax script) {
        super(script);
    }

    @Override
    public boolean activate() {
        final Interactive banker = script.methods.nearestBanker();
        return banker.isValid() && banker.isOnScreen() && ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
        if (!ctx.bank.isOpen()) {
            ctx.bank.open();
        } else {
            ctx.bank.depositInventory();
            sleep(350, 500);
            ctx.bank.close();
        }
    }
}
