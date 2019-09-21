package taluvo.gui.factories.imagefactories;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ClassicImageFactory implements ImageFactory
{
    private final Shape hexagon = makeHexShape(40);

    private Shape makeHexShape(int size)
    {
        final int HALF = (size / 2), ONE_QUARTER = (size / 4), THREE_QUARTERS = (3 * ONE_QUARTER);
        int hexX[] = {0, HALF, size, size, HALF, 0};
        int hexY[] = {ONE_QUARTER, 0, ONE_QUARTER, THREE_QUARTERS, size, THREE_QUARTERS};
        return new Polygon(hexX, hexY, 6);
    }

    @Override
    public BufferedImage makeBorderedHex(Color bodyColor, Color borderColor)
    {
        BufferedImage image = new BufferedImage(hexagon.getBounds().width + 1, hexagon.getBounds().height + 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(bodyColor);
        g2d.fill(hexagon);
        g2d.setColor(borderColor);
        g2d.draw(hexagon);
        return image;
    }

    @Override
    public BufferedImage[] makeHexButton(Color base, Color hover, Color press, Color border)
    {
        BufferedImage[] images = new BufferedImage[3];
        images[0] = makeBorderedHex(base, border);
        images[1] = makeBorderedHex(hover, border);
        images[2] = makeBorderedHex(press, border);
        return images;
    }

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

    @Override
    public Shape getHexShape()
    {
        return hexagon;
    }
}
