package guiframework.elements.displayables;

import guiframework.resources.TestDisplayable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

class CompositeDisplayableTest extends AbstractDisplayableTest
{
    private CompositeDisplayable displayable;
    private TestDisplayable component1, component2;
    private Graphics2D g2d;

    @Override
    protected Displayable makeDisplayable(int x, int y)
    {
        return new CompositeDisplayable(x, y);
    }

    @BeforeEach
    void initializeDisplayables()
    {
        this.displayable = new CompositeDisplayable(0, 0);
        this.component1 = new TestDisplayable(5, 5);
        this.component2 = new TestDisplayable(0, 0);
        displayable.add(component1);
        displayable.add(component2);

        g2d = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB).createGraphics();

        Assertions.assertEquals(0, component1.getDisplays());
        Assertions.assertEquals(0, component2.getDisplays());

        Assertions.assertEquals(0, component1.getUpdates());
        Assertions.assertEquals(0, component2.getUpdates());
    }

    @Test
    @DisplayName("Test that CompositeDisplayable's display() invokes display() on its children")
    void testDisplay()
    {
        displayable.display(g2d);

        Assertions.assertEquals(1, component1.getDisplays());
        Assertions.assertEquals(1, component2.getDisplays());

        displayable.remove(component1);

        displayable.display(g2d);

        Assertions.assertEquals(1, component1.getDisplays());
        Assertions.assertEquals(2, component2.getDisplays());
    }

    @Test
    @DisplayName("Test that CompositeDisplayable's update() invokes update() on its children")
    void testUpdate()
    {
        displayable.update();

        Assertions.assertEquals(1, component1.getUpdates());
        Assertions.assertEquals(1, component2.getUpdates());

        displayable.remove(component1);

        displayable.update();

        Assertions.assertEquals(1, component1.getUpdates());
        Assertions.assertEquals(2, component2.getUpdates());
    }
}