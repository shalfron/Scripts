package us.scriptwith.scripts.fishing.ui;

import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.scripts.fishing.Fishing;
import us.scriptwith.scripts.fishing.jobs.banking.CloseBank;
import us.scriptwith.scripts.fishing.jobs.banking.DepositInventory;
import us.scriptwith.scripts.fishing.jobs.banking.OpenBank;
import us.scriptwith.scripts.fishing.jobs.interactive.CatchFish;
import us.scriptwith.scripts.fishing.jobs.interactive.DropItems;
import us.scriptwith.scripts.fishing.jobs.traversal.ToBank;
import us.scriptwith.scripts.fishing.jobs.traversal.ToSpot;
import us.scriptwith.scripts.fishing.resources.Fish;
import us.scriptwith.scripts.fishing.resources.Location;
import us.scriptwith.util.defined.RandomCameraMovement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Date: 9/16/13
 * Time: 9:07 PM
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

public class FishingGUI extends JPanel {
    public FishingGUI(final Fishing script) {
        super(new GridLayout(4, 0));

        final JFrame frame = new JFrame("Fisher");
        final JComboBox<Location> locations = new JComboBox<>(Location.values());
        final JComboBox<Fish> fish = new JComboBox<>(Fish.values());
        final JCheckBox bank = new JCheckBox("Bank");
        final JButton start = new JButton("Start");

        locations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Location location = (Location) locations.getSelectedItem();
                fish.setModel(new JComboBox<>(location.getFish()).getModel());
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                script.location = (Location) locations.getSelectedItem();
                script.fish = (Fish) fish.getSelectedItem();

                if (bank.isSelected()) {
                    script.container().submit(
                            new OpenBank(script),
                            new DepositInventory(script), new CloseBank(script),
                            new ToBank(script, new TilePath(script.getContext(), script.location.getPathToBank()))
                    );
                } else {
                    script.container().submit(new DropItems(script, Fish.allFishIds()));
                }

                script.container().submit(new CatchFish(script),
                        new ToSpot(script, new TilePath(script.getContext(), script.location.getPathToSpot())),
                        new RandomCameraMovement<>(script)
                );
                frame.dispose();
            }
        });

        this.add(locations);
        this.add(fish);
        this.add(bank);
        this.add(start);

        frame.setSize(300, 150);
        frame.setContentPane(this);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
