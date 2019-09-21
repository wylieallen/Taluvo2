package taluvo.util;

public class Point extends Object
{
    public final int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Point)
        {
            Point point = (Point) obj;
            return point.x == this.x && point.y == this.y;
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return x * 961 + y;
    }
}
