package guiframework.elements.clickables;

import java.util.LinkedHashSet;
import java.util.Set;


public class CompositeClickable extends AbstractClickable
{
    private final Set<Clickable> components;
    private Clickable focused, pressed;

    public CompositeClickable(int x, int y)
    {
        super(x, y, 0, 0);
        components = new LinkedHashSet<>();
        focused = Clickable.NULL;
        pressed = Clickable.NULL;
    }

    public void add(Clickable c)
    {
        components.add(c);
        resize();
    }

    public void remove(Clickable c)
    {
        components.remove(c);
        resize();

        if(focused == c)
            focused = Clickable.NULL;

        if(pressed == c)
            pressed = Clickable.NULL;
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        //
//        if(super.pointIsOn(x, y))
//        {
            int relativeX = x - getX();
            int relativeY = y - getY();

            for(Clickable clickable : components)
                if(clickable.pointIsOn(relativeX, relativeY))
                    return true;
//        }

        return false;
    }

    @Override
    public void do_enter(int x, int y)
    {
        Clickable c = getComponent(x, y);

        x -= getX();
        y -= getY();

        if(c != focused)
        {
            focused.exit(x, y);
            focused = c;
        }

        focused.enter(x, y);
    }

    @Override
    public void do_exit(int x, int y)
    {
        focused.exit(x, y);

        focused = Clickable.NULL;
    }

    @Override
    public void do_press(int x, int y)
    {
        pressed = getComponent(x, y);

        x -= getX();
        y -= getY();

        pressed.press(x, y);
    }

    @Override
    public void do_release(int x, int y)
    {
        pressed.release(x, y);

        pressed = Clickable.NULL;
    }

    private void resize()
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

    private Clickable getComponent(int x, int y)
    {
        x -= getX();
        y -= getY();

        for(Clickable clickable : components)
        {
            if(clickable.pointIsOn(x, y))
            {
                return clickable;
            }
        }

        return Clickable.NULL;
    }
}


