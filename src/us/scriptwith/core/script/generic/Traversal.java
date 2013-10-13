package us.scriptwith.core.script.generic;

import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Locatable;
import org.powerbot.script.wrappers.Path;
import org.powerbot.script.wrappers.Player;
import us.scriptwith.core.job.Job;
import us.scriptwith.core.script.Script;

import java.util.concurrent.Callable;

/**
 * Date: 9/29/13
 * Time: 8:23 PM
 */

public abstract class Traversal<T extends Script> extends Job<T> implements Callable<Boolean> {
    private final Path path;

    public Traversal(T script, Path path) {
        super(script);
        this.path = path;
    }

    public Traversal(T script, Locatable dest) {
        this(script, script.getContext().movement.findPath(dest));
    }

    @Override
    public void execute() {
        if (path.traverse() || ctx.movement.stepTowards(path.getEnd())) {
            Condition.wait(this, 350, 10);
        }
    }

    @Override
    public Boolean call() throws Exception {
        final Player local = ctx.players.local();
        return !local.isInMotion() || ctx.movement.getDestination().distanceTo(local) < 10;
    }
}
