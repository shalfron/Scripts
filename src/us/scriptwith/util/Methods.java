package us.scriptwith.util;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Interactive;

/**
 * Date: 9/29/13
 * Time: 8:32 PM
 */

public class Methods extends MethodProvider {
    private final int[] stanceCombatIds = {17970, 17971, 17975, 17891, 17982, 17990, 17993, 18011, 18012};

    public Methods(MethodContext ctx) {
        super(ctx);
    }

    public double healthPercentage() {
        return 100 * (currentHealth() / maxHealth());
    }

    public double maxHealth() {
        final String s = healthString();
        return Double.parseDouble(s.substring(s.indexOf("/") + 1));
    }

    public double currentHealth() {
        final String s = healthString();
        return Double.parseDouble(s.substring(0, s.indexOf("/")));
    }

    public String healthString() {
        return healthWidget().getText();
    }

    public Component healthWidget() {
        return ctx.widgets.get(1430, 83).getChild(7);
    }

    public boolean inCombat() {
        final int stance = ctx.players.local().getStance();
        for (int i : stanceCombatIds) {
            if (i == stance) {
                return true;
            }
        }
        return false;
    }

    public Interactive nearestBanker() {
        return (Interactive) ctx.bank.getNearest();
    }
}
