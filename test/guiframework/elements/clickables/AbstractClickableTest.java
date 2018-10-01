package guiframework.elements.clickables;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractClickableTest
{
    private int entries, exits, presses, releases;

    private Clickable clickable;
    private static final int X = 1, Y = 1, WIDTH = 100, HEIGHT = 50;

    protected void enter() { entries++; }
    protected void exit() { exits++; }
    protected void press() { presses++; }
    protected void release() { releases++; }

    protected void reset() { entries = exits = presses = releases = 0; }

    @BeforeEach
    void initializeClickable()
    {
        this.clickable = makeClickable(X, Y, WIDTH, HEIGHT);
        reset();
    }

    protected abstract Clickable makeClickable(int x, int y, int width, int height);

    @Test
    void testPointIsOn()
    {
        for(int i = clickable.getX(); i < clickable.getX() + clickable.getWidth(); i++)
        {
            for(int j = clickable.getY(); j < clickable.getY() + clickable.getHeight(); j++)
            {
                Assertions.assertTrue(clickable.pointIsOn(i, j));
            }
        }

        Assertions.assertTrue(clickable.pointIsOn(X + WIDTH - 1, Y + HEIGHT - 1));

        Assertions.assertFalse(clickable.pointIsOn(clickable.getX() - 1, clickable.getY() - 1));
        Assertions.assertFalse(clickable.pointIsOn(
                clickable.getX() + clickable.getWidth() + 1,
                clickable.getY() + clickable.getHeight() + 1)
        );
    }

    @Test
    void testEnter()
    {
        clickable.enter(10, 10);

        Assertions.assertEquals(1, entries);
        Assertions.assertEquals(0, exits);
        Assertions.assertEquals(0, presses);
        Assertions.assertEquals(0, releases);


        clickable.enter(10, 10);

        Assertions.assertEquals(2, entries);
        Assertions.assertEquals(0, exits);
        Assertions.assertEquals(0, presses);
        Assertions.assertEquals(0, releases);
    }

    @Test
    void testExit()
    {
        Assertions.assertThrows(IllegalStateException.class, () -> clickable.exit(10, 10));

        assertInteractions(0, 0, 0, 0);

        clickable.enter(10, 10);

        assertInteractions(1, 0, 0, 0);

        clickable.exit(10, 10);

        assertInteractions(1, 1, 0, 0);

        Assertions.assertThrows(IllegalStateException.class, () -> clickable.exit(10, 10));

        assertInteractions(1, 1, 0, 0);
    }

    @Test
    void testPress()
    {
        assertInteractions(0, 0, 0, 0);

        clickable.press(10, 10);

        assertInteractions(0, 0, 1, 0);
    }

    @Test
    void testRelease()
    {
        Assertions.assertThrows(IllegalStateException.class, () -> clickable.release(10, 10));

        assertInteractions(0, 0, 0, 0);

        clickable.press(10, 10);

        assertInteractions(0, 0, 1, 0);

        clickable.release(10, 10);

        assertInteractions(0, 0, 1, 1);

        Assertions.assertThrows(IllegalStateException.class, () -> clickable.exit(10, 10));

        assertInteractions(0, 0, 1, 1);
    }

    private void assertInteractions(int entries, int exits, int presses, int releases)
    {
        Assertions.assertEquals(entries, this.entries);
        Assertions.assertEquals(exits, this.exits);
        Assertions.assertEquals(presses, this.presses);
        Assertions.assertEquals(releases, this.releases);
    }
}
