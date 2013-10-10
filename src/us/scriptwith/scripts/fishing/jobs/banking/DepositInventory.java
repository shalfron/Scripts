package us.scriptwith.scripts.fishing.jobs.banking;

import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 9:57 PM
 */

public class DepositInventory extends Job<Fishing> {
    public DepositInventory(Fishing script) {
        super(script);
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean activate() {
        return ctx.bank.isOpen() && ctx.backpack.select().select(script.methods.erroneousItems).count() > 0;
    }

    @Override
    public void execute() {
        ctx.bank.depositInventory();
    }
}
