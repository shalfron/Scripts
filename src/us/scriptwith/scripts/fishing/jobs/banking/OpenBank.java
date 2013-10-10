package us.scriptwith.scripts.fishing.jobs.banking;

import org.powerbot.script.wrappers.Interactive;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 9:37 PM
 */

public class OpenBank extends Job<Fishing> {
    public OpenBank(Fishing script) {
        super(script);
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean activate() {
        final Interactive banker = (Interactive) ctx.bank.getNearest();
        return !ctx.bank.isOpen()
                && banker != null && banker.isOnScreen()
                && (!script.methods.hasBait() || ctx.backpack.select().count() == 28);
    }

    @Override
    public void execute() {
        ctx.camera.turnTo(ctx.bank.getNearest());
        ctx.bank.open();
    }
}
