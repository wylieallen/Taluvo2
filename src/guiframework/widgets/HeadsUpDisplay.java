package guiframework.widgets;

import guiframework.elements.clickables.Clickable;
import guiframework.elements.displayables.AlignedDisplayable;
import guiframework.elements.displayables.Displayable;

import java.awt.*;

public class HeadsUpDisplay implements Clickable, Displayable
{
    private final AlignedWidget root, left, center, right;

    public HeadsUpDisplay(int x, int y, int width, int height, int endBuffer)
    {
        this.root = new AlignedWidget(x, y, width, endBuffer, AlignedDisplayable.Orientation.HORIZONTAL, AlignedDisplayable.Alignment.NEUTRAL);
        this.left = new AlignedWidget(0, 0, height, endBuffer, AlignedDisplayable.Orientation.VERTICAL, AlignedDisplayable.Alignment.NEGATIVE);
        this.center = new AlignedWidget(0, 0, height, endBuffer, AlignedDisplayable.Orientation.VERTICAL, AlignedDisplayable.Alignment.NEGATIVE);
        this.right = new AlignedWidget(0, 0, height, endBuffer, AlignedDisplayable.Orientation.VERTICAL, AlignedDisplayable.Alignment.NEGATIVE);
        root.add(left);
        root.add(center);
        root.add(right);
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        return root.pointIsOn(x, y);
    }

    @Override
    public void enter(int x, int y)
    {
        root.enter(x, y);
    }

    @Override
    public void exit(int x, int y)
    {
        root.exit(x, y);
    }

    @Override
    public void press(int x, int y)
    {
        root.press(x, y);
    }

    @Override
    public void release(int x, int y)
    {
        root.release(x, y);
    }

    @Override
    public void display(Graphics2D g2d)
    {
        root.display(g2d);
    }

    @Override
    public void update()
    {
        root.update();
    }

    @Override
    public int getX()
    {
        return root.getX();
    }

    @Override
    public int getY()
    {
        return root.getY();
    }

    @Override
    public int getWidth()
    {
        return root.getWidth();
    }

    @Override
    public int getHeight()
    {
        return center.getHeight();
    }

    @Override
    public void setX(int x)
    {
        root.setX(x);
    }

    @Override
    public void setY(int y)
    {
        root.setY(y);
    }

    public <T extends Clickable & Displayable> void addLeft(T widget)
    {
        left.add(widget);
        root.resize();
    }

    public <T extends Clickable & Displayable> void addCenter(T widget)
    {
        center.add(widget);
        root.resize();
    }

    public <T extends Clickable & Displayable> void addRight(T widget)
    {
        right.add(widget);
        root.resize();
    }

    public void addLeft(Displayable d)
    {
        left.add(d);
        root.resize();
    }

    public void addCenter(Displayable d)
    {
        center.add(d);
        root.resize();
    }

    public void addRight(Displayable d)
    {
        right.add(d);
        root.resize();
    }

    public void resize(int width, int height)
    {
        left.setSpan(height);
        center.setSpan(height);
        right.setSpan(height);

        root.setSpan(width);
    }
}
