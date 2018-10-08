package guiframework.widgets;

import guiframework.elements.clickables.Clickable;
import guiframework.elements.clickables.composites.CompositeClickable;
import guiframework.elements.clickables.composites.ExclusiveClickable;
import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;

import java.awt.*;

public class CompositeWidget extends Widget
{
    private final CompositeClickable clickable;
    private final CompositeDisplayable displayable;

    public CompositeWidget(int x, int y, CompositeClickable clickable, CompositeDisplayable displayable)
    {
        super(x, y, clickable, displayable);
        this.clickable = clickable;
        this.displayable = displayable;
    }

    public <T extends Clickable & Displayable> void add(T widget)
    {
        clickable.add(widget);
        displayable.add(widget);
    }

    public <T extends Clickable & Displayable> void remove(T widget)
    {
        clickable.add(widget);
        displayable.add(widget);
    }
}
