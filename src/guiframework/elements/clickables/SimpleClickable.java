package guiframework.elements.clickables;

public class SimpleClickable extends AbstractClickable
{
    private MouseAction enter, exit, press, release;

    public SimpleClickable(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        enter = exit = press = release = MouseAction.NULL;
    }

    @Override
    public void do_enter(int x, int y) { enter.execute(x, y); }

    @Override
    public void do_exit(int x, int y) { exit.execute(x, y); }

    @Override
    public void do_press(int x, int y) { press.execute(x, y); }

    @Override
    public void do_release(int x, int y) { release.execute(x, y); }

    public void setEnter(MouseAction action) { this.enter = action; }
    public void setExit(MouseAction action) { this.exit = action; }
    public void setPress(MouseAction action) { this.press = action; }
    public void setRelease(MouseAction action) { this.release = action; }
}
