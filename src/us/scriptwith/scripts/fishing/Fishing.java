package us.scriptwith.scripts.fishing;

import org.powerbot.script.Manifest;
import org.powerbot.script.methods.Summoning;
import us.scriptwith.core.script.Script;
import us.scriptwith.scripts.fishing.resources.Fish;
import us.scriptwith.scripts.fishing.resources.FishingMethods;
import us.scriptwith.scripts.fishing.resources.Location;
import us.scriptwith.scripts.fishing.ui.FishingGUI;

import java.awt.*;

/**
 * Date: 9/15/13
 * Time: 4:36 PM
 */

@Manifest(authors = "Coma",
        description = "Catches fish",
        name = "Auto Fisher",
        version = 1.02,
        topic = 1103810)
public class Fishing extends Script {
    public Fish fish;
    public Location location;
    public FishingMethods methods;
    public Summoning.Familiar familiar;

    public Fishing() {
        super();
    }

    @Override
    public void start() {
        this.methods = new FishingMethods(this);
        final Fishing script = this;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FishingGUI(script);
            }
        });
    }

    @Override
    public int poll() {
        ctx.camera.setPitch(true);
        return super.poll();
    }
}
