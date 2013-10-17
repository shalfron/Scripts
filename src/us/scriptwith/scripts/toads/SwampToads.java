package us.scriptwith.scripts.toads;

import org.powerbot.script.Manifest;
import us.scriptwith.core.script.Script;
import us.scriptwith.scripts.toads.jobs.Bank;
import us.scriptwith.scripts.toads.jobs.TakeToad;
import us.scriptwith.scripts.toads.jobs.stairs.StairsDown;
import us.scriptwith.scripts.toads.jobs.stairs.StairsUp;
import us.scriptwith.scripts.toads.jobs.traversal.ToBank;
import us.scriptwith.scripts.toads.jobs.traversal.ToToads;
import us.scriptwith.util.Methods;
import us.scriptwith.util.defined.RandomCameraMovement;

/**
 * Date: 10/17/13
 * Time: 2:16 AM
 */

@Manifest(name = "Auto Swamp Toads",
        authors = "Coma",
        description = "Picks up swamp toads in Gnome Stronghold for gold",
        version = 1.0, topic = 1105942)
public class SwampToads extends Script {
    public Methods methods;

    public SwampToads() {
        super();
    }

    @Override
    public void start() {
        this.methods = new Methods(getContext());
        container().submit(new StairsDown(this),
                new ToToads(this), new TakeToad(this),
                new ToBank(this), new StairsUp(this), new Bank(this),
                new RandomCameraMovement<>(this)
        );
    }

    @Override
    public int poll() {
        ctx.camera.setPitch(true);
        return super.poll();
    }
}
