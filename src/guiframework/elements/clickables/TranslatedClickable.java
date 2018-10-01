package guiframework.elements.clickables;

public class TranslatedClickable extends AbstractClickable
{
    private int dx, dy;
    private final Clickable target;

    public TranslatedClickable(int x, int y, int width, int height, Clickable target)
    {
        super(x, y, width, height);
        this.target = target;
        dx = dy = 0;
    }

    public void setTranslation(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public void translate(int dx, int dy)
    {
        this.dx -= dx;
        this.dy -= dy;
    }

    @Override
    protected void do_enter(int x, int y)
    {
        target.enter(x + dx, y + dy);
    }

    @Override
    protected void do_exit(int x, int y)
    {
        target.exit(x + dx, y + dy);
    }

    @Override
    protected void do_press(int x, int y)
    {
        target.press(x + dx, y + dy);
    }

    @Override
    protected void do_release(int x, int y)
    {
        target.release(x + dx, y + dy);
    }

    @Override
    protected void do_traverse(int x, int y) { target.traverse(x + dx, y + dy); }
    
    @Override
    public boolean pointIsOn(int x, int y)
    {
        return target.pointIsOn((x - getX()) + dx, (y - getY()) + dy);
    }
}
