package guiframework.elements.clickables;

import java.util.*;

public class InclusiveClickable extends AbstractClickable
{
    private final Set<Clickable> components;
    private final Set<Clickable> focuseds, presseds;

    public InclusiveClickable(int x, int y)
    {
        super(x, y, 0, 0);
        this.components = new HashSet<>();
        this.focuseds = new HashSet<>();
        this.presseds = new HashSet<>();
    }

    public void add(Clickable clickable)
    {
        components.add(clickable);
        resize();
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        //
        if(super.pointIsOn(x, y))
        {
            int relativeX = x - getX();
            int relativeY = y - getY();

            for(Clickable clickable : components)
                if(clickable.pointIsOn(relativeX, relativeY))
                    return true;
        }

        return false;
    }

    @Override
    public void do_enter(int x, int y)
    {
        Collection<Clickable> removeFromFocuseds = new HashSet<>();

        for(Clickable clickable : focuseds)
        {
            if(!clickable.pointIsOn(x, y))
            {
                removeFromFocuseds.add(clickable);
                clickable.exit(x, y);
            }
        }

        focuseds.removeAll(removeFromFocuseds);

        Collection<Clickable> clickables = getComponents(x, y);

        clickables.forEach(clickable ->
        {
                focuseds.add(clickable);
                clickable.enter(x, y);
        });
    }

    @Override
    public void do_exit(int x, int y)
    {
        focuseds.forEach(c -> c.exit(x, y));
        focuseds.clear();
    }

    @Override
    public void do_press(int x, int y)
    {
        Collection<Clickable> clickables = getComponents(x, y);

        clickables.forEach(clickable -> { presseds.add(clickable); clickable.press(x, y); });
    }

    @Override
    public void do_release(int x, int y)
    {
        presseds.forEach(c -> c.release(x, y));
        presseds.clear();
    }

    public void resize()
    {
        int minX = getX(), minY = getY();
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for(Clickable clickable : components)
        {
            int x = clickable.getX(), y = clickable.getY();

            minX = x < minX ? x : minX;
            minY = y < minY ? y : minY;

            maxX = x + clickable.getWidth() > maxX ? x + clickable.getWidth() : maxX;
            maxY = y + clickable.getHeight() > maxY ? y + clickable.getHeight() : maxY;
        }

        setWidth(maxX - minX);
        setHeight(maxY - minY);
    }

    private Collection<Clickable> getComponents(int x, int y)
    {
        Collection<Clickable> clickables = new LinkedHashSet<>();

        x -= getX();
        y -= getY();

        for(Clickable clickable : components)
        {
            if(clickable.pointIsOn(x, y))
            {
                clickables.add(clickable);
            }
        }

        return clickables;
    }
}
