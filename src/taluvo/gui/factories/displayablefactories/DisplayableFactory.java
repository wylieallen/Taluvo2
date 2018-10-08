package taluvo.gui.factories.displayablefactories;

import guiframework.elements.displayables.Displayable;

import java.awt.*;

public interface DisplayableFactory
{
    Displayable[] makeRectButton(int width, int height, Color defaultColor, Color hoverColor, Color pressColor, Color textColor, String text);
    Displayable[] makeMenuButton();
    Displayable[] makeHexButton(Color defaultColor, Color hoverColor, Color pressColor, Color borderColor);
    Shape getHexShape();

    Displayable makeBox(int x, int y, int width, int height, Color color);
}
