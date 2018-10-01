package guiframework.elements.clickables;

public abstract class AbstractClickable implements Clickable
{
    private int x, y, width, height;

    private boolean focused, depressed;

    protected AbstractClickable(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        focused = depressed = false;
    }

    @Override
    public void enter(int x, int y)
    {
        focused = true;
        do_enter(x, y);
    }

    @Override
    public void exit(int x, int y)
    {
        if(!focused) throw new IllegalStateException();
        else do_exit(x, y);
        focused = false;
    }

    @Override
    public void press(int x, int y)
    {
        depressed = true;
        do_press(x, y);
    }

    @Override
    public void release(int x, int y)
    {
        if(!depressed) throw new IllegalStateException();
        else do_release(x, y);
        depressed = false;
    }

    protected abstract void do_enter(int x, int y);
    protected abstract void do_exit(int x, int y);
    protected abstract void do_press(int x, int y);
    protected abstract void do_release(int x, int y);

    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }

    @Override
    public int getWidth() { return width; }

    @Override
    public int getHeight() { return height; }

    @Override
    public void setX(int x) { this.x = x; }

    @Override
    public void setY(int y) { this.y = y; }

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public boolean isDepressed() { return depressed; }
    public boolean isFocused() { return focused; }
}
