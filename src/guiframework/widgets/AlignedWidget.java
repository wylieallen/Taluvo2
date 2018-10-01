package guiframework.widgets;

import guiframework.elements.clickables.Clickable;
import guiframework.elements.clickables.CompositeClickable;
import guiframework.elements.displayables.AlignedDisplayable;
import guiframework.elements.displayables.Displayable;

import java.awt.*;

public class AlignedWidget implements Displayable, Clickable
{
    private final AlignedDisplayable displayable;
    private final CompositeClickable clickable;

    public AlignedWidget(int x, int y, int span, int endBuffer, AlignedDisplayable.Orientation orientation, AlignedDisplayable.Alignment alignment)
    {
        this.displayable = new AlignedDisplayable(x, y, span, endBuffer, orientation, alignment);
        this.clickable = new CompositeClickable(x, y);
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
        displayable.setX(x);
        clickable.setX(x);
    }

    @Override
    public void setY(int y)
    {
        displayable.setY(y);
        clickable.setY(y);
    }

    public void add(Displayable d)
    {
        displayable.add(d);
    }

    public <T extends Clickable & Displayable> void add(T widget)
    {
        displayable.add(widget);
        clickable.add(widget);
    }

    public void setSpan(int span)
    {
        displayable.setSpan(span);
        displayable.resize();
    }

    public void resize()
    {
        displayable.resize();
    }
}
