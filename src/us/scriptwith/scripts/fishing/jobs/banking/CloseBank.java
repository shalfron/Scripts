package us.scriptwith.scripts.fishing.jobs.banking;

import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 10:10 PM
 */

public class CloseBank extends Job<Fishing> {
    public CloseBank(Fishing script) {
        super(script);
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean activate() {
        return ctx.bank.isOpen() && script.methods.hasBait();//TODO urns
    }

    @Override
    public void execute() {
        ctx.bank.close();
    }
}
