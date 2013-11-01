package us.scriptwith.scripts.flax;

import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import us.scriptwith.core.script.Painter;
import us.scriptwith.core.script.Script;
import us.scriptwith.scripts.flax.jobs.Bank;
import us.scriptwith.scripts.flax.jobs.PickFlax;
import us.scriptwith.scripts.flax.jobs.TraverseToBank;
import us.scriptwith.scripts.flax.jobs.TraverseToFlax;
import us.scriptwith.util.Methods;
import us.scriptwith.util.jobs.RandomCameraMovement;

import java.awt.*;

import static us.scriptwith.core.script.Painter.PaintProperty;

/**
 * Date: 10/17/13
 * Time: 12:01 AM
 */

@Manifest(name = "Auto Flax", authors = "Coma",
        description = "Picks flax in Seer's Village for no-requirements money making",
        version = 1.0, topic = 1105723)
public class Flax extends Script implements PaintListener, MessageListener {
    public Methods methods;

    private int flaxPicked = 0;

    @Override
    public void start() {
        prices().add(1779);
        this.methods = new Methods(getContext());
        container().submit(
                new PickFlax(this),
                new TraverseToFlax(this),
                new TraverseToBank(this),
                new Bank(this),
                new RandomCameraMovement<>(this)
        );
    }

    @Override
    public int poll() {
        if (ctx.camera.getPitch() < 70) ctx.camera.setPitch(true);
        return super.poll();
    }

    @Override
    public void messaged(MessageEvent messageEvent) {
        final String msg = messageEvent.getMessage().toLowerCase();
        if (msg.contains("you pick")) {
            flaxPicked++;
        }
    }

    private final Painter paint = new Painter(getName(), getVersion() + "");
    private final PaintProperty time = new PaintProperty();
    private final PaintProperty status = new PaintProperty();
    private final PaintProperty flax = new PaintProperty();
    private final PaintProperty profit = new PaintProperty();

    @Override
    public void repaint(Graphics graphics) {
        final long runtime = getRuntime();
        final double flaxHr = paint.getHourlyRate(flaxPicked, runtime);
        final int profit = flaxPicked * prices().getPrice(1779);
        final double profitHr = paint.getHourlyRate(profit, runtime);

        paint.properties(
                time.value("Time: " + paint.formatTime(runtime)),
                status.value("Status: " + status()),
                flax.value("Flax: " + flaxPicked + " (" + (int) flaxHr + ")"),
                this.profit.value("Profit: " + paint.format(profit) + " (" + paint.format((int) profitHr) + ")")
        ).draw(graphics, ctx);
    }
}
