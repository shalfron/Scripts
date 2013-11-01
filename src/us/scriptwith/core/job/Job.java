package us.scriptwith.core.job;

import org.powerbot.script.methods.MethodProvider;
import us.scriptwith.core.script.Script;

/**
 * Date: 8/23/13
 * Time: 3:57 PM
 */

public abstract class Job<T extends Script> extends MethodProvider {
    protected T script;

    public Job(T t) {
        super(t.getContext());
        this.script = t;
    }

    public int delay() {
        return 250;
    }

    public int priority() {
        return 0;
    }

    public String status() {
        return "";
    }

    public abstract boolean activate();

    public abstract void execute();
}
