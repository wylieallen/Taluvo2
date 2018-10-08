package guiframework.elements.displayables;

import guiframework.resources.TestDisplayable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AlignedDisplayableTest extends AbstractDisplayableTest
{
    private static final int SPAN = 100, BUFFER = 10;

    @Override
    protected Displayable makeDisplayable(int x, int y)
    {
        return new AlignedDisplayable(x, y, SPAN, BUFFER, AlignedDisplayable.Orientation.HORIZONTAL, AlignedDisplayable.Alignment.NEUTRAL);
    }

    private AlignedDisplayable displayable;
    private TestDisplayable left, center, right;
    private Graphics2D g2d;

    @BeforeEach
    void initializeDisplayables()
    {
        displayable = new AlignedDisplayable(0, 0, SPAN, BUFFER, AlignedDisplayable.Orientation.HORIZONTAL, AlignedDisplayable.Alignment.NEUTRAL);
        left = new TestDisplayable(-100, 1000);
        center = new TestDisplayable(1000, 5);
        right = new TestDisplayable(0, 0);
        displayable.add(left);
        displayable.add(center);
        displayable.add(right);

        g2d = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB).createGraphics();
    }

    @Test
    @DisplayName("Tests that calling display() on parent calls display() on its children")
    void testDisplay()
    {
        Assertions.assertEquals(0, left.getDisplays());
        Assertions.assertEquals(0, center.getDisplays());
        Assertions.assertEquals(0, right.getDisplays());

        displayable.display(g2d);

        Assertions.assertEquals(1, left.getDisplays());
        Assertions.assertEquals(1, center.getDisplays());
        Assertions.assertEquals(1, right.getDisplays());
    }

    @Test
    @DisplayName("Tests that calling update() on parent calls display() on its children")
    void testUpdate()
    {
        Assertions.assertEquals(0, left.getUpdates());
        Assertions.assertEquals(0, center.getUpdates());
        Assertions.assertEquals(0, right.getUpdates());

        displayable.update();

        Assertions.assertEquals(1, left.getUpdates());
        Assertions.assertEquals(1, center.getUpdates());
        Assertions.assertEquals(1, right.getUpdates());
    }

    @Test
    @DisplayName("Tests that left, right, and center Displayables have been properly aligned")
    void testAlignment()
    {
        int leftX, leftY, middleX, middleY, rightX, rightY;

        leftX = displayable.getX() + BUFFER;
        leftY = displayable.getY();

        Assertions.assertEquals(leftX, left.getX());
        Assertions.assertEquals(leftY, left.getY());

        // todo
    }
}
