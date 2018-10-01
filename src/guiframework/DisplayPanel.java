package guiframework;

import guiframework.elements.displayables.Displayable;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedHashSet;

public class DisplayPanel extends JPanel
{
    private Displayable scene;

    public DisplayPanel(Displayable scene)
    {
        super();
        this.scene = scene;
    }

    public void update()
    {
        scene.update();
    }

    public void setScene(Displayable scene)
    {
        this.scene = scene;
    }

    /*
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform t = g2d.getTransform();

        // g2d.translate(camera.getX() ...);
        underlays.forEach(d -> d.display(g2d));

        g2d.setTransform(t);
        overlays.forEach(d -> d.display(g2d));
    }
    */

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        scene.display((Graphics2D) g);
    }
}
