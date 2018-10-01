package guiframework.widgets;

import guiframework.elements.clickables.Clickable;
import guiframework.elements.clickables.CompositeClickable;
import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;

import java.awt.*;

public class ButtonGroup implements Clickable, Displayable
{
    private final CompositeClickable clickable;
    private final CompositeDisplayable displayable;

    public ButtonGroup(int x, int y)
    {
        this.clickable = new CompositeClickable(x, y);
        this.displayable = new CompositeDisplayable(x, y);
    }

    public ButtonGroup(int x, int y, Button... buttons)
    {
        this(x, y);
        for(Button button : buttons)
            this.add(button);
    }

    public void add(Button button)
    {
        clickable.add(button);
        displayable.add(button);
    }

    public void remove(Button button)
    {
        clickable.add(button);
        displayable.add(button);
    }

    public int getX() { return clickable.getX(); }
    public int getY() { return clickable.getY(); }
    public int getWidth() { return clickable.getWidth(); }
    public int getHeight() { return clickable.getHeight(); }

    public void setX(int x) { clickable.setX(x); displayable.setX(x); }
    public void setY(int y) { clickable.setY(y); displayable.setY(y); }

    public void update() { displayable.update(); }
    public void display(Graphics2D g2d) { displayable.display(g2d); }

    public void enter(int x, int y) { clickable.enter(x, y); }
    public void exit(int x, int y) { clickable.exit(x, y); }
    public void press(int x, int y) { clickable.press(x, y); }
    public void release(int x, int y) { clickable.release(x, y); }

    public boolean pointIsOn(int x, int y) { return clickable.pointIsOn(x, y); }
}
