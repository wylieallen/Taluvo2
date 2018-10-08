package guiframework.widgets;

import guiframework.elements.clickables.*;
import guiframework.elements.clickables.composites.ExclusiveClickable;
import guiframework.elements.clickables.composites.InclusiveClickable;
import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.TranslatedDisplayable;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class DraggableCamera implements Clickable, Displayable
{
    private final InclusiveClickable clickRoot;
    private final SimpleClickable cameraController;
    private final TranslatedClickable translatedClickable;
    private final ExclusiveClickable clickEdge;

    private final TranslatedDisplayable displayRoot;
    private final CompositeDisplayable displayEdge;

    public DraggableCamera(int x, int y, int width, int height)
    {
        this.cameraController = new SimpleClickable(0, 0, width, height);
        this.clickEdge = new ExclusiveClickable(0, 0);
        this.translatedClickable = new TranslatedClickable(0, 0, width, height, clickEdge);

        this.clickRoot = new InclusiveClickable(x, y);
        clickRoot.add(cameraController);
        clickRoot.add(translatedClickable);

        this.displayEdge = new CompositeDisplayable(0, 0);
        this.displayRoot = new TranslatedDisplayable(x, y, displayEdge);

        Point cameraPoint = new Point(0, 0);
        cameraController.setEnter((nextX, nextY) -> {
            cameraPoint.x = nextX;
            cameraPoint.y = nextY;
        });

        cameraController.setTraverse((nextX, nextY) -> {
            if(cameraController.isDepressed())
            {
                translate(nextX - cameraPoint.x, nextY - cameraPoint.y);
            }

            cameraPoint.x = nextX;
            cameraPoint.y = nextY;
        });
    }

    @Override
    public int getX() { return clickRoot.getX(); }

    @Override
    public int getY() { return clickRoot.getY(); }

    @Override
    public int getWidth() { return clickRoot.getWidth(); }

    @Override
    public int getHeight() { return clickRoot.getHeight(); }

    public int getDx() { return displayRoot.getDx(); }
    public int getDy() { return displayRoot.getDy(); }

    @Override
    public void setX(int x)
    {
        clickRoot.setX(x);
        displayRoot.setX(x);
    }

    @Override
    public void setY(int y)
    {
        displayRoot.setY(y);
    }

    @Override
    public boolean pointIsOn(int x, int y)
    {
        return clickRoot.pointIsOn(x, y);
    }

    @Override
    public void enter(int x, int y)
    {
        clickRoot.enter(x, y);
    }

    @Override
    public void exit(int x, int y)
    {
        clickRoot.exit(x, y);
    }

    @Override
    public void press(int x, int y)
    {
        clickRoot.press(x, y);
    }

    @Override
    public void release(int x, int y)
    {
        clickRoot.release(x, y);
    }

    @Override
    public void traverse(int x, int y)
    {
        clickRoot.traverse(x, y);
    }

    @Override
    public void display(Graphics2D g2d)
    {
        displayRoot.display(g2d);
    }

    @Override
    public void update()
    {
        displayRoot.update();
    }

    public void resize(int newWidth, int newHeight)
    {
        int prevWidth = getWidth();
        int prevHeight = getHeight();

        cameraController.setWidth(newWidth);
        cameraController.setHeight(newHeight);

        displayRoot.translate((newWidth - prevWidth) / 2 , (newHeight - prevHeight) / 2);
//        displayRoot.setWidth(newWidth);
//        displayRoot.setHeight(newHeight);

        translatedClickable.translate((newWidth - prevWidth) / 2, (newHeight - prevHeight) / 2);
        translatedClickable.setWidth(newWidth);
        translatedClickable.setHeight(newHeight);

        clickRoot.resize();
    }

    public void translate(int dx, int dy)
    {
        translatedClickable.translate(dx, dy);
        displayRoot.translate(dx, dy);
    }

    public <T extends Clickable & Displayable> void add(T widget)
    {
        clickEdge.add(widget);
        displayEdge.add(widget);
    }

    public <T extends Clickable & Displayable> void remove(T widget)
    {
        clickEdge.remove(widget);
        displayEdge.remove(widget);
    }

    public void add(Displayable displayable)
    {
        displayEdge.add(displayable);
    }

    public void remove(Displayable displayable)
    {
        displayEdge.remove(displayable);
    }
}
