package us.scriptwith.core.script.generic;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Identifiable;
import org.powerbot.script.wrappers.Interactive;
import org.powerbot.script.wrappers.Locatable;
import org.powerbot.script.wrappers.Nameable;
import us.scriptwith.core.job.Job;
import us.scriptwith.core.script.Script;

import java.util.concurrent.Callable;

/**
 * Date: 9/29/13
 * Time: 9:52 PM
 */

public abstract class Interaction
        <T extends Script, N extends Interactive & Locatable & Identifiable & Nameable>
        extends Job<T>
        implements Callable<Boolean> {
    public int[] ids;
    private String action;
    private BasicNamedQuery<N> query;

    public Interaction(T script, BasicNamedQuery<N> query, int[] ids, String action) {
        super(script);
        this.ids = ids;
        this.action = action;
        this.query = query;
    }

    @Override
    public void execute() {
        final N entity = query.select().id(ids).nearest().poll();
        if (!entity.isOnScreen()) {
            ctx.movement.stepTowards(entity);
            ctx.camera.turnTo(entity);
            sleep(350, 500);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return entity.isOnScreen() || !ctx.players.local().isInMotion();
                }
            });

        }

        if (entity.isValid() && entity.interact(action)) {
            sleep(350, 500);
            Condition.wait(this, 500, 10);
        }
    }

    @Override
    public Boolean call() throws Exception {
        return true;
    }
}
