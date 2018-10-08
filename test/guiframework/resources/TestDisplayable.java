package guiframework.resources;

import guiframework.elements.displayables.AbstractDisplayable;

import java.awt.*;

public class TestDisplayable extends AbstractDisplayable
{
    private int width = 10, height = 10;
    private int displays, updates;

    public TestDisplayable(int x, int y)
    {
        super(x, y);
        displays = updates = 0;
    }

    public TestDisplayable(int x, int y, int width, int height)
    {
        this(x, y);
        this.width = width;
        this.height = height;
    }

    protected void do_display(Graphics2D g2d)
    {
        ++displays;
    }

    public void update()
    {
        ++updates;
    }

    public int getDisplays() { return displays; }
    public int getUpdates() { return updates; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
