package us.scriptwith.scripts.toads;

import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.wrappers.Item;
import us.scriptwith.core.script.Painter;
import us.scriptwith.core.script.Script;
import us.scriptwith.scripts.toads.jobs.Bank;
import us.scriptwith.scripts.toads.jobs.TakeToad;
import us.scriptwith.scripts.toads.jobs.stairs.StairsDown;
import us.scriptwith.scripts.toads.jobs.stairs.StairsUp;
import us.scriptwith.scripts.toads.jobs.traversal.ToBank;
import us.scriptwith.scripts.toads.jobs.traversal.ToToads;
import us.scriptwith.util.InventoryMonitor;
import us.scriptwith.util.Methods;
import us.scriptwith.util.jobs.RandomCameraMovement;

import java.awt.*;

import static us.scriptwith.core.script.Painter.PaintProperty;


/**
 * Date: 10/17/13
 * Time: 2:16 AM
 */

@Manifest(name = "Auto Swamp Toads",
        authors = "Coma",
        description = "Picks up swamp toads in Gnome Stronghold for no-requirements money making",
        version = 1.0, topic = 1105942)
public class SwampToads extends Script implements PaintListener {
    public Methods methods;

    private int toadsPicked = 0;

    @Override
    public void start() {
        setMonitor(new InventoryMonitor(getContext()) {
            @Override
            public void onChange() {
                for (Item i : getChanges()) {
                    if (i.getId() == 2150) {
                        toadsPicked++;
                    }
                }
                update();
            }
        });
        prices().add(2150);
        this.methods = new Methods(getContext());
        container().submit(new StairsDown(this),
                new ToToads(this), new TakeToad(this),
                new ToBank(this), new StairsUp(this), new Bank(this),
                new RandomCameraMovement<>(this)
        );
    }

    @Override
    public int poll() {
        if (ctx.camera.getPitch() < 70) ctx.camera.setPitch(true);
        return super.poll();
    }

    private final Painter paint = new Painter(getName(), getVersion() + "");
    private final PaintProperty time = new PaintProperty();
    private final PaintProperty status = new PaintProperty();
    private final PaintProperty toads = new PaintProperty();
    private final PaintProperty profit = new PaintProperty();

    @Override
    public void repaint(Graphics graphics) {
        final long runtime = getRuntime();
        final int profit = toadsPicked * prices().getPrice(2150);

        final double profitHr = paint.getHourlyRate(profit, runtime);
        final double toadsHr = paint.getHourlyRate(toadsPicked, runtime);

        paint.properties(
                time.value("Time: " + paint.formatTime(runtime)),
                status.value("Status: " + status()),
                toads.value("Toads: " + toadsPicked + " (" + (int) toadsHr + ")"),
                this.profit.value("Profit: " + paint.format(profit) + " (" + paint.format((int) profitHr) + ")")
        ).draw(graphics, ctx);
    }
}
