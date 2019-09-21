package taluvo.gui.widgets;

import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.primitives.ImageDisplayable;
import guiframework.elements.displayables.primitives.StringDisplayable;
import guiframework.widgets.Button;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HexButton extends Button
{
    private final Shape hexagon;
    private final ImageDisplayable baseImage, hoverImage, pressImage;
    private final CompositeDisplayable baseRoot, hoverRoot, pressRoot;
    private Displayable building = Displayable.NULL, heightDisplayable = Displayable.NULL;

    public HexButton(int x, int y, ImageDisplayable baseImage, ImageDisplayable hoverImage, ImageDisplayable pressImage, Shape hexagon,
                     CompositeDisplayable baseRoot, CompositeDisplayable hoverRoot, CompositeDisplayable pressRoot)
    {
        super(x, y, baseRoot, hoverRoot, pressRoot);
        this.baseImage = baseImage;
        this.hoverImage = hoverImage;
        this.pressImage = pressImage;
        this.hexagon = hexagon;
        this.baseRoot = baseRoot;
        this.hoverRoot = hoverRoot;
        this.pressRoot = pressRoot;
        baseRoot.add(baseImage);
        hoverRoot.add(hoverImage);
        pressRoot.add(pressImage);
    }

    public void setBuilding(Displayable building)
    {
        removeFromRoots(this.building);
        addToRoots(building);
        this.building = building;
    }

    public void setTerrain(BufferedImage newBase, BufferedImage newHover, BufferedImage newPress, int height)
    {
        baseImage.setImage(newBase);
        hoverImage.setImage(newHover);
        pressImage.setImage(newPress);
        setBuilding(Displayable.NULL);
        removeFromRoots(heightDisplayable);
        heightDisplayable = new StringDisplayable(24, 5, Color.BLACK, () -> "" + height);
        addToRoots(heightDisplayable);
    }

    private void removeFromRoots(Displayable removeMe)
    {
        baseRoot.remove(removeMe);
        hoverRoot.remove(removeMe);
        pressRoot.remove(removeMe);
    }

    private void addToRoots(Displayable addMe)
    {
        baseRoot.add(addMe);
        hoverRoot.add(addMe);
        pressRoot.add(addMe);
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        return hexagon.contains(x - getX(), y - getY());
    }
}
