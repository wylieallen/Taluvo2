package guiframework;

import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.resources.TestImage;
import org.junit.jupiter.api.*;

import javax.swing.*;

class DisplayPanelTest
{
    private DisplayPanel panel;

    @BeforeEach
    public void initializePanel()
    {
        panel = new DisplayPanel(new CompositeDisplayable(0, 0));
        panel.setSize(100, 100);
    }

    @Test
    @DisplayName("Test that DisplayPanel's paintComponent invokes JPanel's paint")
    public void testPaintComponent()
    {
        TestImage image = new TestImage(panel.getSize());
        panel.paintComponent(image.createGraphics());

        JPanel jpanel = new JPanel();
        jpanel.setSize(panel.getSize());

        TestImage image2 = new TestImage(panel.getSize());

        Assertions.assertNotEquals(image, image2);

        jpanel.paint(image2.createGraphics());

        Assertions.assertEquals(image, image2);
    }

}