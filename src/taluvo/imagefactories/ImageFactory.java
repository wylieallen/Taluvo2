package taluvo.imagefactories;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageFactory
{
    BufferedImage[] makeMenuButton();
    BufferedImage makeBorderedRect(int width, int height, Color bodyColor);
}
