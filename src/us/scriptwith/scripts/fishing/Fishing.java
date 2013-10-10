package us.scriptwith.scripts.fishing;

import org.powerbot.event.PaintListener;
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

/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

@Manifest(authors = "Coma", description = "Fishes", name = "Auto Fisher", version = 1.00)
public class Fishing extends Script implements PaintListener {
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
    public void repaint(Graphics graphics) {
        //TODO
    }
}
