package guiframework.resources;

import guiframework.elements.displayables.AbstractDisplayable;

import java.awt.*;

public class TestDisplayable extends AbstractDisplayable
{
    private int displays, updates;

    public TestDisplayable(int x, int y)
    {
        super(x, y);
        displays = updates = 0;
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

    public int getWidth() { return 10; }
    public int getHeight() { return 10; }
}
