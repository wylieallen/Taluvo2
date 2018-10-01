package guiframework.elements.displayables;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConditionalDisplayable extends AbstractDisplayable
{
    private Displayable activeDisplayable;
    private final List<Tuple> tuples = new ArrayList<>();

    public ConditionalDisplayable(int x, int y, Displayable defaultDisplayable)
    {
        super(x, y);
        activeDisplayable = defaultDisplayable;
        tuples.add(new Tuple(defaultDisplayable, () -> true));
    }

    public void add(Displayable displayable, Condition condition)
    {
        tuples.add(0, new Tuple(displayable, condition));
    }

    public void remove(Displayable displayable)
    {
        Tuple removeMe = null;

        for(Tuple tuple : tuples)
        {
            if(tuple.displayable == displayable)
            {
                removeMe = tuple;
                break;
            }
        }

        tuples.remove(removeMe);
    }

    public int getHeight() { return activeDisplayable.getHeight(); }
    public int getWidth() { return activeDisplayable.getWidth(); }

    public void do_display(Graphics2D g2d)
    {
        activeDisplayable.display(g2d);
    }

    @Override
    public void update()
    {
        for(Tuple tuple : tuples)
        {
            if(tuple.condition.is())
            {
                activeDisplayable = tuple.displayable;
                break;
            }
        }

        activeDisplayable.update();
    }

    private class Tuple
    {
        final Displayable displayable;
        final Condition condition;

        Tuple(Displayable displayable, Condition condition)
        {
            this.displayable = displayable;
            this.condition = condition;
        }
    }

    public interface Condition
    {
        boolean is();
    }
}
