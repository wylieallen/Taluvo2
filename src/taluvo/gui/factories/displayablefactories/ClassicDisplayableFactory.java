package taluvo.gui.factories.displayablefactories;

import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.ImageDisplayable;
import guiframework.elements.displayables.StringDisplayable;
import taluvo.gui.factories.imagefactories.ImageFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ClassicDisplayableFactory implements DisplayableFactory
{
    private ImageFactory imageFactory;

    public ClassicDisplayableFactory(ImageFactory imageFactory)
    {
        this.imageFactory = imageFactory;
    }

    @Override
    public Displayable[] makeRectButton(int width, int height, Color defaultColor, Color hoverColor, Color pressColor, Color textColor, String text)
    {
        Displayable[] displayables = new Displayable[3];
        displayables[0] = makeLabeledBox(0, 0, width, height, defaultColor, textColor, text);
        displayables[1] = makeLabeledBox(0, 0, width, height, hoverColor, textColor, text);
        displayables[2] = makeLabeledBox(0, 0, width, height, pressColor, textColor, text);
        return displayables;
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

    public Displayable[] makeHexButton(Color base, Color press, Color hover, Color border)
    {
        BufferedImage[] images = imageFactory.makeHexButton(base, press, hover, border);
        Displayable[] displayables = new Displayable[3];
        displayables[0] = new ImageDisplayable(images[0], 0, 0);
        displayables[1] = new ImageDisplayable(images[1], 0, 0);
        displayables[2] = new ImageDisplayable(images[2], 0, 0);
        return displayables;
    }

    public Displayable makeLabeledBox(int x, int y, int width, int height, Color bodyColor, Color textColor, String text)
    {
        BufferedImage box = imageFactory.makeBorderedRect(width, height, bodyColor);
        Graphics2D g2d = box.createGraphics();
        StringDisplayable textDisplayable = new StringDisplayable(0, 0, textColor, () -> text);
        textDisplayable.setX((width / 2) - (textDisplayable.getWidth() / 2) - 2);
        textDisplayable.setY((height / 2) - (textDisplayable.getHeight() / 2) - 4);
        System.out.println("TextDisplayable location: " + textDisplayable.getX() + "," + textDisplayable.getY());
        System.out.println("TextDisplayable dimension: " + textDisplayable.getWidth() + "," + textDisplayable.getHeight());
        textDisplayable.display(g2d);
        return new ImageDisplayable(box, x, y);
    }

    @Override
    public Displayable makeBox(int x, int y, int width, int height, Color color)
    {
        return new ImageDisplayable(imageFactory.makeBorderedRect(width, height, color), x, y);
    }

    public Shape getHexShape() { return imageFactory.getHexShape(); }
}
