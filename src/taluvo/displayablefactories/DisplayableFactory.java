package taluvo.displayablefactories;

import guiframework.elements.displayables.Displayable;

import java.awt.*;

public interface DisplayableFactory
{
    Displayable[] makeMenuButton();

    Displayable makeBox(int x, int y, int width, int height, Color color);
}
