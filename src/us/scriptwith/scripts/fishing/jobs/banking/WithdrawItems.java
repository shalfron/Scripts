package us.scriptwith.scripts.fishing.jobs.banking;

import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 10:01 PM
 */

public class WithdrawItems extends Job<Fishing> {
    public WithdrawItems(Fishing script) {
        super(script);
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean activate() {
        return ctx.bank.isOpen(); //TODO urns
    }

    @Override
    public void execute() {
        //TODO urn
    }
}
