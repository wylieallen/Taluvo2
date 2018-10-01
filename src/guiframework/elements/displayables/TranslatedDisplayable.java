package guiframework.elements.displayables;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TranslatedDisplayable extends AbstractDisplayable
{
    private int dx, dy;
    private int width, height;
    private final Displayable target;

    public TranslatedDisplayable(int x, int y, int width, int height, Displayable target)
    {
        super(x, y);
        this.width = width;
        this.height = height;
        dx = dy = 0;
        this.target = target;
    }

    public void update() { target.update(); }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public int getDx() { return dx; }
    public int getDy() { return dy; }

    public void setTranslation(int x, int y) { dx = x; dy = y; }
    public void translate(int dx, int dy) { this.dx += dx; this.dy += dy; }

    public void do_display(Graphics2D g2d)
    {
        AffineTransform t = g2d.getTransform();
        g2d.translate(dx, dy);
        target.display(g2d);
        g2d.setTransform(t);
    }
}
