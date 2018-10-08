package taluvo.gui.widgets;

import guiframework.elements.displayables.Displayable;
import guiframework.widgets.Button;

import java.awt.*;

public class HexButton extends Button
{
    private final Shape hexagon;

    public HexButton(int x, int y, Displayable base, Displayable hover, Displayable press, Shape hexagon)
    {
        super(x, y, base, hover, press);
        this.hexagon = hexagon;
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        return hexagon.contains(x - getX(), y - getY());
    }
}
