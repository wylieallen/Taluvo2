package guiframework.elements.displayables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageDisplayable extends AbstractDisplayable
{
    private BufferedImage image;

    public ImageDisplayable(BufferedImage image, int x, int y)
    {
        super(x, y);
        this.image = image;
    }

    @Override
    protected void do_display(Graphics2D g2d)
    {
        g2d.drawImage(image, 0, 0, null);
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }

    @Override
    public int getWidth() { return image.getWidth(); }

    @Override
    public int getHeight() { return image.getHeight(); }

    public void update() {}
}
