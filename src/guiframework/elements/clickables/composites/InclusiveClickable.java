package guiframework.elements.clickables.composites;

import guiframework.elements.clickables.Clickable;

import java.util.*;

public class InclusiveClickable extends CompositeClickable
{
    private final Set<Clickable> components;
    private final Set<Clickable> focuseds, presseds;

    public InclusiveClickable(int x, int y)
    {
        super(x, y);
        this.components = new HashSet<>();
        this.focuseds = new HashSet<>();
        this.presseds = new HashSet<>();
    }

    public void add(Clickable clickable)
    {
        components.add(clickable);
        resize();
    }

    public void remove(Clickable clickable)
    {
        components.remove(clickable);
        resize();
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        for(Clickable clickable : components)
            if(clickable.pointIsOn(x, y))
                return true;

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

    @Override
    public void do_traverse(int x, int y)
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
            if(focuseds.contains(clickable))
            {
                clickable.traverse(x, y);
            }
            else
            {
                focuseds.add(clickable);
                clickable.enter(x, y);
            }
        });
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
