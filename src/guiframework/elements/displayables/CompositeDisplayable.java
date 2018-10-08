package guiframework.elements.displayables;

import java.awt.*;
import java.util.LinkedHashSet;

public class CompositeDisplayable extends AbstractDisplayable
{
    private final LinkedHashSet<Displayable> displayables;
    private int width, height;

    public CompositeDisplayable(int x, int y)
    {
        super(x, y);
        this.displayables = new LinkedHashSet<>();
        width = height = 0;
    }

    public void update() { displayables.forEach(Displayable::update); }

    protected void do_display(Graphics2D g2d) { displayables.forEach(d -> d.display(g2d)); }

    @Override
    public int getWidth() { return width; }

    @Override
    public int getHeight() { return height; }

    public void add(Displayable d)
    {
        displayables.add(d);
        resize();
    }

    public void remove(Displayable d)
    {
        displayables.remove(d);
        resize();
    }

    private void resize()
    {
        if(displayables.isEmpty())
        {
            width = 0;
            height = 0;
            return;
        }

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for(Displayable displayable : displayables)
        {
            int x = displayable.getX(), y = displayable.getY();

            minX = x < minX ? x : minX;
            minY = y < minY ? y : minY;

            maxX = x + displayable.getWidth() > maxX ? x + displayable.getWidth() : maxX;
            maxY = y + displayable.getHeight() > maxY ? y + displayable.getHeight() : maxY;
        }

        width = maxX - minX;
        height = maxY - minY;
    }
}
