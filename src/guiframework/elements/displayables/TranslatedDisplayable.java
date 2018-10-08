package guiframework.elements.displayables;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TranslatedDisplayable extends AbstractDisplayable
{
    private int dx, dy;
    private Displayable target;

    public TranslatedDisplayable(int x, int y, Displayable target)
    {
        super(x, y);
        dx = dy = 0;
        this.target = target;
    }

    public void update() { target.update(); }

    public int getWidth() { return target.getWidth(); }
    public int getHeight() { return target.getHeight(); }

    public int getDx() { return dx; }
    public int getDy() { return dy; }

    public void setTranslation(int x, int y) { dx = x; dy = y; }
    public void translate(int dx, int dy) { this.dx += dx; this.dy += dy; }

    public void setTarget(Displayable target) { this.target = target; }

    public void do_display(Graphics2D g2d)
    {
        AffineTransform t = g2d.getTransform();
        g2d.translate(dx, dy);
        target.display(g2d);
        g2d.setTransform(t);
    }
}
