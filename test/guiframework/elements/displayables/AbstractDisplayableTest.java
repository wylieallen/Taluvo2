package guiframework.elements.displayables;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

abstract class AbstractDisplayableTest
{
    @Test
    @DisplayName("Test that Displayable's x and y can be initialized and set")
    void testSetLocation()
    {
        int x = 0, y = 0;

        Displayable displayable = makeDisplayable(x, y);

        Assertions.assertEquals(x, displayable.getX());
        Assertions.assertEquals(y, displayable.getY());

        x = 40;
        y = 15;

        displayable.setX(x);
        displayable.setY(y);

        Assertions.assertEquals(x, displayable.getX());
        Assertions.assertEquals(y, displayable.getY());
    }

    @Test
    @DisplayName("Test that Displayable's update() doesn't explode")
    void testUpdateForSmoke()
    {
        makeDisplayable(0, 0).update();
    }

    protected abstract Displayable makeDisplayable(int x, int y);
}