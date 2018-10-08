package guiframework.elements.clickables.composites;

import guiframework.elements.clickables.Clickable;

import java.util.LinkedHashSet;

public class RadialClickable extends CompositeClickable
{
    private final LinkedHashSet<Clickable> clickables;
    private Clickable focused, pressed;

    public RadialClickable(int x, int y)
    {
        super(x, y);
        this.clickables = new LinkedHashSet<>();
        focused = NULL;
        pressed = NULL;
    }

    public void add(Clickable c)
    {
        clickables.add(c);
        resize();
    }

    public void remove(Clickable c)
    {
        clickables.remove(c);
        resize();

        if(focused == c)
            focused = NULL;

        if(pressed == c)
            pressed = NULL;
    }

    public void resize()
    {
        int minX = getX(), minY = getY();
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for(Clickable clickable : clickables)
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
        for(Clickable clickable : clickables)
        {
            if(clickable.pointIsOn(x, y))
            {
                return clickable;
            }
        }

        return Clickable.NULL;
    }

    @Override
    protected void do_enter(int x, int y)
    {
        Clickable c = getComponent(x, y);

        if(c != focused)
        {
            focused.exit(x, y);
            focused = c;
        }

        focused.enter(x, y);
    }

    @Override
    protected void do_exit(int x, int y)
    {
        focused.exit(x, y);
        focused = NULL;
    }

    @Override
    protected void do_press(int x, int y)
    {
        Clickable c = getComponent(x, y);

        if(c != pressed)
        {
            pressed.release(x, y);
            pressed = c;
            c.press(x, y);
        }
    }

    @Override
    protected void do_release(int x, int y)
    {

    }

    @Override
    protected void do_traverse(int x, int y)
    {
        Clickable c = getComponent(x, y);

        if(c != focused)
        {
            focused.exit(x, y);
            focused = c;
            focused.enter(x, y);
        }
        else
        {
            focused.traverse(x, y);
        }
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        for(Clickable clickable : clickables)
            if(clickable.pointIsOn(x - getX(), y - getY()))
                return true;

        return false;
    }
}
