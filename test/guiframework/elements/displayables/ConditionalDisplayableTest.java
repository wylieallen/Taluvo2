package guiframework.elements.displayables;

import guiframework.resources.TestDisplayable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConditionalDisplayableTest extends AbstractDisplayableTest
{
    private ConditionalDisplayable displayable;
    private TestDisplayable component1, component2, defaultComponent;
    private Graphics2D g2d;

    private boolean condition1, condition2;

    private boolean isCondition1() { return condition1; }
    private boolean isCondition2() { return condition2; }
    private void setCondition1(boolean condition1) { this.condition1 = condition1; }
    private void setCondition2(boolean condition2) { this.condition2 = condition2; }

    @Override
    protected Displayable makeDisplayable(int x, int y) { return new ConditionalDisplayable(x, y, new TestDisplayable(0, 0)); }

    @BeforeEach
    void initializeDisplayables()
    {
        condition1 = condition2 = false;

        this.component1 = new TestDisplayable(0, 0);
        this.component2 = new TestDisplayable(0, 0, 15, 15);
        this.defaultComponent = new TestDisplayable(0, 0, 25, 25);

        this.displayable = new ConditionalDisplayable(50, 50, defaultComponent);
        displayable.add(component1, this::isCondition1);
        displayable.add(component2, this::isCondition2);

        this.g2d = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB).createGraphics();

        assertDisplayCounts(0, 0, 0 );
        assertUpdateCounts(0, 0, 0);
    }

    @Test
    @DisplayName("Test that parent's display() invoke's defaultComponent's display when neither condition is true")
    void testDisplayDefault()
    {
        displayable.update();
        assertUpdateCounts(1, 0, 0 );
        displayable.display(g2d);
        assertDisplayCounts(1, 0, 0);
    }

    @Test
    @DisplayName("Test that parent's display() invokes component1's display when condition1 is true and condition2 is false")
    void testDisplay1()
    {
        setCondition1(true);
        displayable.update();
        assertUpdateCounts(0, 1, 0 );
        displayable.display(g2d);
        assertDisplayCounts(0, 1, 0);
    }

    @Test
    @DisplayName("Test that parent's display() invokes component2's display when condition2 is true and condition1 is false")
    void testDisplay2()
    {
        setCondition2(true);
        displayable.update();
        assertUpdateCounts(0, 0, 1 );
        displayable.display(g2d);
        assertDisplayCounts(0, 0, 1);
    }

    @Test
    @DisplayName("Test that parent's display() invokes most recently added component's display when condition1 both conditions are true")
    void testDisplayPriority()
    {
        setCondition1(true);
        setCondition2(true);
        displayable.update();
        assertUpdateCounts(0, 0, 1);
        displayable.display(g2d);
        assertDisplayCounts(0, 0, 1);

        Assertions.assertEquals(displayable.getWidth(), component2.getWidth());
        Assertions.assertEquals(displayable.getHeight(), component2.getHeight());

        displayable.remove(component2);

        displayable.update();
        assertUpdateCounts(0, 1, 1);
        displayable.display(g2d);
        assertDisplayCounts(0, 1, 1);

        Assertions.assertEquals(displayable.getWidth(), component1.getWidth());
        Assertions.assertEquals(displayable.getHeight(), component1.getHeight());

        // Remove twice as a smoke test:
        displayable.remove(component1);
        displayable.remove(component1);

        displayable.update();
        assertUpdateCounts(1, 1, 1);
        displayable.display(g2d);
        assertDisplayCounts(1, 1, 1);

        Assertions.assertEquals(displayable.getWidth(), defaultComponent.getWidth());
        Assertions.assertEquals(displayable.getHeight(), defaultComponent.getHeight());
    }

    private void assertUpdateCounts(int updatesDefault, int updates1, int updates2)
    {
        Assertions.assertEquals(updatesDefault, defaultComponent.getUpdates());
        Assertions.assertEquals(updates1, component1.getUpdates());
        Assertions.assertEquals(updates2, component2.getUpdates());
    }

    private void assertDisplayCounts(int displaysDefault, int displays1, int displays2)
    {
        Assertions.assertEquals(displaysDefault, defaultComponent.getDisplays());
        Assertions.assertEquals(displays1, component1.getDisplays());
        Assertions.assertEquals(displays2, component2.getDisplays());
    }

}
