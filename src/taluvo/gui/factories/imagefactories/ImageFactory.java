package taluvo.gui.factories.imagefactories;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageFactory
{
    BufferedImage[] makeMenuButton();
    BufferedImage makeBorderedRect(int width, int height, Color bodyColor);
    BufferedImage[] makeHexButton(Color base, Color hover, Color press, Color border);
    BufferedImage makeBorderedHex(Color bodyColor, Color borderColor);

    Shape getHexShape();
}
