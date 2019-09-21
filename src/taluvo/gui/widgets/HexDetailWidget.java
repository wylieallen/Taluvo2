package taluvo.gui.widgets;

import guiframework.elements.clickables.SimpleClickable;
import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.primitives.StringDisplayable;
import guiframework.widgets.Widget;
import taluvo.game.Board;
import taluvo.gui.factories.displayablefactories.DisplayableFactory;

import java.awt.*;

public class HexDetailWidget extends Widget
{
    private static final int WIDTH = 128, HEIGHT = 128;

    private int targetX, targetY;
    private boolean enabled;

    public HexDetailWidget(Board.View board, int x, int y, CompositeDisplayable displayRoot, DisplayableFactory displayableFactory)
    {
        super(x, y,  new SimpleClickable(0, 0, WIDTH, HEIGHT), displayRoot);
        targetX = targetY = 0;
        enabled = false;
        displayRoot.add(displayableFactory.makeBox(0, 0, WIDTH, HEIGHT, Color.WHITE));
        displayRoot.add(new StringDisplayable(2, 0, Color.BLACK, () -> enabled ? "Coords: " + getTargetX() + ", " + getTargetY() : ""));
        displayRoot.add(new StringDisplayable(2, 16, Color.BLACK, () -> enabled ? "TileId: " + board.getTileId(getTargetX(), getTargetY()) : ""));
        displayRoot.add(new StringDisplayable(2, 32, Color.BLACK, () -> enabled ? "Height: " + board.getHeight(getTargetX(), getTargetY()) : ""));
        displayRoot.add(new StringDisplayable(2, 48, Color.BLACK, () -> enabled ? "Terrain: " + board.getTerrain(getTargetX(), getTargetY()) : ""));
        displayRoot.add(new StringDisplayable(2, 64, Color.BLACK, () -> enabled ? "Building: " + board.getBuilding(getTargetX(), getTargetY()) : ""));
        displayRoot.add(new StringDisplayable(2, 80, Color.BLACK, () -> enabled ? "Owner: " + board.getOwner(getTargetX(), getTargetY()) : ""));
    }

    public int getTargetX() { return targetX; }
    public int getTargetY() { return targetY; }

    public void setTarget(int x, int y) { this.targetX = x; this.targetY = y; }
    public void enable() { this.enabled = true; }
    public void disable() { this.enabled = false; }
}
