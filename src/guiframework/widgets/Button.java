package guiframework.widgets;

import guiframework.elements.clickables.SimpleClickable;
import guiframework.elements.displayables.ConditionalDisplayable;
import guiframework.elements.displayables.Displayable;

import java.awt.*;

public class Button extends SimpleClickable implements Displayable
{
    private final Displayable displayable;

    public Button(int x, int y, Displayable defaultDisplayable, Displayable enteredDisplayable, Displayable pressedDisplayable)
    {
        super(x, y, defaultDisplayable.getWidth(), defaultDisplayable.getHeight());
        ConditionalDisplayable displayable = new ConditionalDisplayable(x, y, defaultDisplayable);
        displayable.add(enteredDisplayable, this::isFocused);
        displayable.add(pressedDisplayable, this::isDepressed);
        this.displayable = displayable;
    }

    public void update() { displayable.update(); }

    public void display(Graphics2D g2d) { displayable.display(g2d); }

    public int getWidth() { return displayable.getWidth(); }
    public int getHeight() { return displayable.getHeight(); }

    public void setX(int x) { super.setX(x); displayable.setX(x); }
    public void setY(int y) { super.setY(y); displayable.setY(y); }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        return super.pointIsOn(x, y);
    }
}
