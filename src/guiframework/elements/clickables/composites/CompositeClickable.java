package guiframework.elements.clickables.composites;

import guiframework.elements.clickables.AbstractClickable;
import guiframework.elements.clickables.Clickable;

public abstract class CompositeClickable extends AbstractClickable
{
    protected CompositeClickable(int x, int y)
    {
        super(x, y, 0, 0);
    }

    public abstract void add(Clickable c);
    public abstract void remove(Clickable c);
    public abstract void resize();
}
