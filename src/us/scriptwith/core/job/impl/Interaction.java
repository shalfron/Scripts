package us.scriptwith.core.job.impl;

import org.powerbot.script.lang.AbstractQuery;
import org.powerbot.script.lang.Filter;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Identifiable;
import org.powerbot.script.wrappers.Interactive;
import org.powerbot.script.wrappers.Locatable;
import org.powerbot.script.wrappers.Nameable;
import us.scriptwith.core.job.Job;
import us.scriptwith.core.script.Script;

import java.util.concurrent.Callable;

/**
 * Date: 10/17/13
 * Time: 4:16 PM
 */

public abstract class Interaction<T extends Script,
        N extends Interactive & Locatable & Identifiable & Nameable,
        K extends AbstractQuery<K, N> & Locatable.Query<K> & Identifiable.Query<K> & Nameable.Query<K>>
        extends Job<T>
        implements Callable<Boolean> {

    private K query;
    private String action;
    public int[] ids;

    public Interaction(T script, K query, String action, int... ids) {
        super(script);
        this.query = query;
        this.ids = ids;
        this.action = action;
    }

    @Override
    public void execute() {
        interact(query.select().id(ids).nearest().poll());
    }

    public void interact(final N entity) {
        ctx.antipatterns.setEnabled(false);
        if (!entity.isValid()) {
            return;
        }

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
        if (entity.interact(action)) {
            Condition.wait(this, 250, 15);
        }
        ctx.antipatterns.setEnabled(true);
    }

    @Override
    public Boolean call() throws Exception {
        return true;
    }

    public Filter<N> onScreen = new Filter<N>() {
        @Override
        public boolean accept(N n) {
            return n.isOnScreen();
        }
    };

    public Filter<N> reachable = new Filter<N>() {
        @Override
        public boolean accept(N n) {
            return n.getLocation().getMatrix(ctx).isReachable();
        }
    };
}
