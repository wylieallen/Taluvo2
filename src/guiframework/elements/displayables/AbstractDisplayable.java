package guiframework.elements.displayables;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class AbstractDisplayable implements Displayable
{
    private int x, y;

    protected AbstractDisplayable(int x, int y)
    {
        setX(x);
        setY(y);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public void display(Graphics2D g2d)
    {
        AffineTransform t = g2d.getTransform();
        g2d.translate(getX(), getY());
        do_display(g2d);
        g2d.setTransform(t);
    }

    abstract protected void do_display(Graphics2D g2d);
}
