package us.scriptwith.scripts.fishing.ui;

import org.powerbot.script.wrappers.TilePath;
import us.scriptwith.scripts.fishing.Fishing;
import us.scriptwith.scripts.fishing.jobs.Stop;
import us.scriptwith.scripts.fishing.jobs.banking.CloseBank;
import us.scriptwith.scripts.fishing.jobs.banking.DepositInventory;
import us.scriptwith.scripts.fishing.jobs.banking.OpenBank;
import us.scriptwith.scripts.fishing.jobs.interactive.CatchFish;
import us.scriptwith.scripts.fishing.jobs.interactive.DropItems;
import us.scriptwith.scripts.fishing.jobs.interactive.stiles.ExchangeStiles;
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
 * Date: 10/12/13
 * Time: 2:10 AM
 */

public class FishingGUI extends JFrame {
    public FishingGUI(final Fishing script) {
        super("Auto Fisher");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        final JComboBox<Fish> fish = new JComboBox<Fish>(Location.values()[0].getFish()) {{
            setBounds(45, 40, 150, 20);
        }};
        final JComboBox<Location> locations = new JComboBox<Location>(Location.values()) {{
            setBounds(45, 10, 150, 20);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final Location location = (Location) getSelectedItem();
                    fish.setModel(new JComboBox<>(location.getFish()).getModel());
                }
            });
        }};
        final JCheckBox bank = new JCheckBox("Bank") {{
            setBounds(45, 70, 75, 20);
            setPreferredSize(new Dimension(50, 20));
        }};
        final JCheckBox stiles = new JCheckBox("Stiles") {{
            setBounds(130, 70, 75, 20);
            setPreferredSize(new Dimension(50, 20));
        }};
        final JButton start = new JButton("Start") {{
            setBounds(80, 105, 75, 20);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    script.location = (Location) locations.getSelectedItem();
                    script.fish = (Fish) fish.getSelectedItem();

                    if (bank.isSelected()) {
                        script.container().submit(
                                new OpenBank(script),
                                new DepositInventory(script), new CloseBank(script), new DepositInventory(script),
                                new ToBank(script, new TilePath(script.getContext(), script.location.getPathToBank()))
                        );
                    } else if (stiles.isSelected()) {
                        script.container().submit(
                                new ToBank(script, new TilePath(script.getContext(), script.location.getPathToBank())),
                                new ExchangeStiles(script)
                        );

                    } else {
                        script.container().submit(new DropItems(script, Fish.allFishIds()));
                    }

                    script.container().submit(new CatchFish(script),
                            new ToSpot(script, new TilePath(script.getContext(), script.location.getPathToSpot())),
                            new RandomCameraMovement<>(script),
                            new Stop(script)
                    );
                    dispose();
                }
            });
        }};

        final JPanel content = new JPanel(null) {{
            add(locations);
            add(fish);
            add(bank);
            add(stiles);
            add(start);
        }};

        this.setSize(250, 175);
        this.setContentPane(content);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
