package us.scriptwith.core.script;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Author: Aadil Farouk
 * Date: 8/22/13
 * Time: 10:41 PM
 * <p/>
 * COPYRIGHT 2013, AADIL FAROUK
 */

public class Painter {
    private String name, version;
    private PaintProperty[] properties = new PaintProperty[0];
    private final DecimalFormat format = new DecimalFormat("###,###,###");

    public Painter(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public Painter properties(PaintProperty... properties) {
        this.properties = properties;
        return this;
    }

    public String format(double i) {
        return this.format.format(i);
    }

    public int getExperienceToLevel(MethodContext ctx, int skill, int level) {
        return ctx.skills.getExperienceAt(level) - ctx.skills.getExperience(skill);
    }

    public double getHourlyRate(final int base, final long runTime) {
        if (runTime < 1) {
            return 0;
        }
        return (base * 3600000D) / (runTime);
    }

    public String getTimeToLevel(MethodContext ctx, final int skill, final int goal, final int xpPerHour) {
        if (xpPerHour < 1) {
            return Timer.format(0L);
        }
        return Timer.format((long)
                (getExperienceToLevel(ctx, skill, goal) * 3600000D / xpPerHour));
    }

    private final AlphaComposite background = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .75f);
    private final AlphaComposite foreground = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
    private final Font title = new Font("Tahoma", Font.PLAIN, 16);
    private final Font stats = new Font("Tahoma", Font.PLAIN, 12);

    public void draw(Graphics render) {
        draw(render, null);
    }

    public void draw(Graphics render, MethodContext ctx) {
        final Graphics2D g = (Graphics2D) render;
        final FontMetrics fm = g.getFontMetrics(title);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        g.setFont(title);

        g.setColor(Color.BLACK);
        g.setComposite(background);
        g.fillRect(5, 5, 180, 50 + (properties.length * 20));

        g.setComposite(foreground);
        g.setColor(Color.WHITE);
        g.drawRect(5, 5, 180, 50 + (properties.length * 20));

        if (ctx != null) {
            final int x = ctx.mouse.getLocation().x, y = ctx.mouse.getLocation().y;
            g.drawLine(x + 5, y, x - 5, y);
            g.drawLine(x, y + 5, x, y - 5);
        }

        final int w1 = fm.stringWidth(name);
        final int w2 = fm.stringWidth(version);

        g.drawString(name, (180 / 2) - (w1 / 2), 20);
        g.drawString(version, (180 / 2) - (w2 / 2), 40);
        g.drawLine(15, 45, 175, 45);

        g.setFont(stats);

        for (int i = 0; i < properties.length; i++) {
            final PaintProperty p = properties[i];
            g.drawString(p.getValue(), 15 + p.getOffset(), 70 + (i * 20));
        }
    }

    public static class PaintProperty {
        private String value;
        private int offset;

        public PaintProperty(String value, int offset) {
            this.value = value;
            this.offset = offset;
        }

        public PaintProperty(String value) {
            this(value, 0);
        }


        public PaintProperty value(String value) {
            this.value = value;
            return this;
        }

        public PaintProperty offset(int offset) {
            this.offset = offset;
            return this;
        }

        public String getValue() {
            return this.value;
        }

        public int getOffset() {
            return this.offset;
        }
    }
}
