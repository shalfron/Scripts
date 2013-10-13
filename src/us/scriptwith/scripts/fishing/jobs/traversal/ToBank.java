package us.scriptwith.scripts.fishing.jobs.traversal;

import org.powerbot.script.wrappers.Interactive;
import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.core.script.generic.Traversal;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/16/13
 * Time: 3:06 PM
 */

public class ToBank extends Traversal<Fishing> {
    public ToBank(Fishing script, TilePath path) {
        super(script, path);
    }

    @Override
    public String status() {
        return "Walking to bank";
    }

    @Override
    public boolean activate() {
        final Interactive bank = script.methods.nearestBanker();
        return (!script.methods.hasBait() || ctx.backpack.select().count() == 28)
                && (!bank.isValid() || !bank.isOnScreen());
    }
}
