package taluvo.displayablefactories;

import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.ImageDisplayable;
import taluvo.imagefactories.ImageFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ClassicDisplayableFactory implements DisplayableFactory
{
    private ImageFactory imageFactory;

    public ClassicDisplayableFactory(ImageFactory imageFactory)
    {
        this.imageFactory = imageFactory;
    }

    public Displayable[] makeMenuButton()
    {
        BufferedImage[] images = imageFactory.makeMenuButton();

        Displayable[] displayables = new Displayable[3];

        for(int i = 0; i < 3; i++)
        {
            displayables[i] = new ImageDisplayable(images[i], 0, 0);
        }

        return displayables;
    }

    @Override
    public Displayable makeBox(int x, int y, int width, int height, Color color)
    {
        return new ImageDisplayable(imageFactory.makeBorderedRect(width, height, color), x, y);
    }
}
