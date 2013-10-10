package us.scriptwith.scripts.fishing.jobs.interactive;

import org.powerbot.script.wrappers.Item;
import us.scriptwith.core.job.Job;
import us.scriptwith.scripts.fishing.Fishing;

/**
 * Date: 9/15/13
 * Time: 9:50 PM
 */

public class DropItems extends Job<Fishing> {
    private final int[] junkIds;

    public DropItems(Fishing script, int... junkIds) {
        super(script);
        this.junkIds = junkIds;
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String status() {
        return "Dropping";
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28 && ctx.backpack.id(junkIds).count() > 0;
    }

    @Override
    public void execute() {
        for (Item i : ctx.backpack) {
            ctx.backpack.scroll(i);
            i.interact("Drop");
            sleep(250, 300);
        }
    }
}
