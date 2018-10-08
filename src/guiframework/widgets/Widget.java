package guiframework.widgets;

import guiframework.elements.clickables.Clickable;
import guiframework.elements.displayables.Displayable;

import java.awt.*;

public class Widget implements Clickable, Displayable
{
    private final Clickable clickable;
    private final Displayable displayable;

    public Widget(int x, int y, Clickable clickable, Displayable displayable)
    {
        this.clickable = clickable;
        clickable.setX(x);
        clickable.setY(y);
        this.displayable = displayable;
        displayable.setX(x);
        displayable.setY(y);
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        return clickable.pointIsOn(x, y);
    }

    @Override
    public void enter(int x, int y)
    {
        clickable.enter(x, y);
    }

    @Override
    public void exit(int x, int y)
    {
        clickable.exit(x, y);
    }

    @Override
    public void press(int x, int y)
    {
        clickable.press(x, y);
    }

    @Override
    public void release(int x, int y)
    {
        clickable.release(x, y);
    }

    @Override
    public void traverse(int x, int y)
    {
        clickable.traverse(x, y);
    }

    @Override
    public void display(Graphics2D g2d)
    {
        displayable.display(g2d);
    }

    @Override
    public void update()
    {
        displayable.update();
    }

    @Override
    public int getX()
    {
        return displayable.getX();
    }

    @Override
    public int getY()
    {
        return displayable.getY();
    }

    @Override
    public int getWidth()
    {
        return displayable.getWidth();
    }

    @Override
    public int getHeight()
    {
        return displayable.getHeight();
    }

    @Override
    public void setX(int x)
    {
        clickable.setX(x);
        displayable.setX(x);
    }

    @Override
    public void setY(int y)
    {
        clickable.setY(y);
        displayable.setY(y);
    }
}
