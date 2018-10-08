package guiframework.elements.displayables;

import guiframework.resources.TestImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

class ImageDisplayableTest extends AbstractDisplayableTest
{
    private TestImage canvas1, canvas2;
    private TestImage image;
    private ImageDisplayable displayable;

    @Override
    protected Displayable makeDisplayable(int x, int y)
    {
        return new ImageDisplayable(null, x, y);
    }

    @BeforeEach
    void initializeImages()
    {
        canvas1 = new TestImage(100, 100);
        canvas2 = new TestImage(100, 100);
        image = new TestImage(20, 20);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 10, 10);
        displayable = new ImageDisplayable(image, 0, 0);
    }

    @Test
    @DisplayName("Test that ImageDisplayable's display() draws its BufferedImage")
    void testDisplay()
    {
        Assertions.assertEquals(canvas1, canvas2);

        displayable.display(canvas1.createGraphics());

        Assertions.assertNotEquals(canvas1, canvas2);

        canvas2.createGraphics().drawImage(image, displayable.getX(), displayable.getY(), null);

        Assertions.assertEquals(canvas1, canvas2);
    }

    @Test
    @DisplayName("Test that ImageDisplayable's image can be accessed and set")
    void testGetSetImage()
    {
        Assertions.assertNotEquals(image, canvas1);

        Assertions.assertEquals(image, displayable.getImage());

        displayable.setImage(canvas1);

        Assertions.assertEquals(canvas1, displayable.getImage());
    }

    @Test
    @DisplayName("Test that ImageDisplayable has the same dimensions as its image")
    void testGetDimensions()
    {
        Assertions.assertEquals(displayable.getWidth(), image.getWidth());
        Assertions.assertEquals(displayable.getHeight(), image.getHeight());
    }
}