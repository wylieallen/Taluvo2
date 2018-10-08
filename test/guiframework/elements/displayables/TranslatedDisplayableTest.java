package guiframework.elements.displayables;

import guiframework.resources.TestDisplayable;
import guiframework.resources.TestImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TranslatedDisplayableTest extends AbstractDisplayableTest
{
    private TranslatedDisplayable displayable;
    private TestDisplayable target;
    private Graphics2D g2d;

    @Override
    protected Displayable makeDisplayable(int x, int y)
    {
        return new TranslatedDisplayable(x, y, new TestDisplayable(0, 0));
    }

    @BeforeEach
    void initializeDisplayables()
    {
        target = new TestDisplayable(0, 0);
        displayable = new TranslatedDisplayable(0, 0, target);
        g2d = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB).createGraphics();
    }

    @Test
    @DisplayName("Test that calling update() on parent calls update() on target")
    void testUpdate()
    {
        Assertions.assertEquals(0, target.getUpdates());
        displayable.update();
        Assertions.assertEquals(1, target.getUpdates());
    }

    @Test
    @DisplayName("Test that calling display() on parent calls display() on target")
    void testDisplayCalled()
    {
        Assertions.assertEquals(0, target.getDisplays());
        displayable.display(g2d);
        Assertions.assertEquals(1, target.getDisplays());
    }

    @Test
    @DisplayName("Test that calling parent's translation transforms target's display")
    void testDisplayTranslates()
    {
        Assertions.assertEquals(0, displayable.getDx());
        Assertions.assertEquals(0, displayable.getDy());

        TestImage translatedCanvas = new TestImage(100, 100);
        TestImage untranslatedCanvas = new TestImage(100, 100);

        BufferedImage redSquare = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D redg2d = redSquare.createGraphics();
        redg2d.setColor(Color.RED);
        redg2d.fillRect(0, 0, redSquare.getWidth(), redSquare.getHeight());
        Displayable target = new ImageDisplayable(redSquare, 0, 0);
        displayable.setTarget(target);

        Assertions.assertEquals(translatedCanvas, untranslatedCanvas);

        displayable.translate(50, 50);
        displayable.display(translatedCanvas.createGraphics());

        Assertions.assertNotEquals(translatedCanvas, untranslatedCanvas);

        target.setX(50);
        target.setY(50);

        target.display(untranslatedCanvas.createGraphics());

        Assertions.assertEquals(translatedCanvas, untranslatedCanvas);

        translatedCanvas = new TestImage(100, 100);
        untranslatedCanvas = new TestImage(100, 100);

        displayable.setTranslation(0, 0);
        target.setX(0);
        target.setY(0);

        Assertions.assertEquals(translatedCanvas, untranslatedCanvas);

        target.display(untranslatedCanvas.createGraphics());

        Assertions.assertNotEquals(translatedCanvas, untranslatedCanvas);

        displayable.display(translatedCanvas.createGraphics());

        Assertions.assertEquals(translatedCanvas, untranslatedCanvas);
    }

    @Test
    @DisplayName("Test that parent's getWidth() and getHeight() return target's width and height")
    void testGetWidthAndHeight()
    {
        Assertions.assertEquals(target.getWidth(), displayable.getWidth());
        Assertions.assertEquals(target.getHeight(), displayable.getHeight());
    }
}
