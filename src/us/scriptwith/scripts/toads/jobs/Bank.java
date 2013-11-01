package us.scriptwith.scripts.toads.jobs;

import org.powerbot.script.wrappers.Interactive;
import org.powerbot.script.wrappers.Locatable;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.toads.SwampToads;

/**
 * Date: 10/17/13
 * Time: 5:26 PM
 */

public class Bank extends Job<SwampToads> {
    public Bank(SwampToads script) {
        super(script);
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean activate() {
        final Interactive banker = script.methods.nearestBanker();
        return banker.isValid() && ctx.backpack.select().count() == 28
                && ctx.players.local().getLocation().getPlane() == 1;
    }

    @Override
    public void execute() {
        final Interactive banker = script.methods.nearestBanker();
        if (!banker.isOnScreen()) {
            ctx.movement.stepTowards((Locatable) banker);
            sleep(500, 800);
        } else if (!ctx.bank.isOpen()) {
            ctx.bank.open();
            sleep(500, 800);
        } else {
            ctx.bank.depositInventory();
            sleep(500, 800);
            ctx.bank.close();
        }
    }
}
