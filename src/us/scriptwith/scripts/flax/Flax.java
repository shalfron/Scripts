package us.scriptwith.scripts.flax;

import org.powerbot.script.Manifest;
import us.scriptwith.core.script.Script;
import us.scriptwith.scripts.flax.jobs.Bank;
import us.scriptwith.scripts.flax.jobs.PickFlax;
import us.scriptwith.scripts.flax.jobs.TraverseToBank;
import us.scriptwith.scripts.flax.jobs.TraverseToFlax;
import us.scriptwith.util.Methods;

/**
 * Date: 10/17/13
 * Time: 12:01 AM
 */

@Manifest(name = "Auto Flaxer", authors = "Coma", description = "Pick flax",
        version = 1.0, topic = 1105723)
public class Flax extends Script {
    public Methods methods;

    @Override
    public void start() {
        this.methods = new Methods(getContext());
        container().submit(
                new PickFlax(this),
                new TraverseToFlax(this),
                new TraverseToBank(this),
                new Bank(this)
        );
    }
}
