package taluvo.gui.factories.displayablefactories;

import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.primitives.ImageDisplayable;
import taluvo.game.Player;
import taluvo.game.hex.Building;
import taluvo.game.hex.Terrain;

import java.awt.*;

public interface DisplayableFactory
{
    Displayable[] makeRectButton(int width, int height, Color defaultColor, Color hoverColor, Color pressColor, Color textColor, String text);
    Displayable[] makeMenuButton();
    ImageDisplayable[] makeHexButton(Terrain terrain, Color hoverColor, Color pressColor, Color borderColor);
    Shape getHexShape();

    Displayable makeHex(int x, int y, Color bodyColor, Color hoverColor);
    Displayable makeBox(int x, int y, int width, int height, Color color);

    Displayable getBuildingDisplayable(Building building, Color primary, Color secondary);
    Displayable makeBuildingDisplayable(Building building, Color primary, Color secondary);
    Displayable makeBuildingStatusDisplayable(Player player, Building building, Color primary, Color secondary);
}
