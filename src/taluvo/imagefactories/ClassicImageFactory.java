package taluvo.imagefactories;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ClassicImageFactory implements ImageFactory
{
    public BufferedImage[] makeMenuButton()
    {
        BufferedImage[] images = new BufferedImage[3];

        images[0] = makeBorderedRect(200, 80, Color.WHITE);
        images[1] = makeBorderedRect(200, 80, Color.GRAY);
        images[2] = makeBorderedRect(200, 80, Color.YELLOW);

        return images;
    }

    public BufferedImage makeBorderedRect(int width, int height, Color bodyColor)
    {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(bodyColor);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 0, width - 1, height - 1);
        return image;
    }
}
