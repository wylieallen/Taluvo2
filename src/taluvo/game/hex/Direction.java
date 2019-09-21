package taluvo.game.hex;

public enum Direction
{
    NORTHWEST(-20, -30),
    NORTHEAST(20, -30),
    EAST(40, 0),
    SOUTHEAST(20, 30),
    SOUTHWEST(-20, 30),
    WEST(-40, 0);

    public final int x, y;

    Direction(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Direction rotateLeft()
    {
        return values()[wrapAround(ordinal() - 1)];
    }

    public Direction rotateRight()
    {
        return values()[wrapAround(ordinal() + 1)];
    }

    private static final int MIN = 0, MAX = values().length;

    private int wrapAround(int index)
    {
        if (index <  MIN) index = values().length - 1;
        if (index >= MAX) index = 0;
        return index;
    }
}
