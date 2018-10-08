package taluvo.gui.factories.widgetfactories;

import guiframework.elements.clickables.SimpleClickable;
import guiframework.elements.clickables.composites.CompositeClickable;
import guiframework.elements.clickables.composites.ExclusiveClickable;
import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.StringDisplayable;
import guiframework.widgets.Button;
import guiframework.widgets.DraggableCamera;
import guiframework.widgets.Widget;
import taluvo.gui.factories.displayablefactories.DisplayableFactory;
import taluvo.gui.widgets.HexButton;

import java.awt.*;

public class ClassicWidgetFactory implements WidgetFactory
{
    private DisplayableFactory displayableFactory;

    public ClassicWidgetFactory(DisplayableFactory displayableFactory)
    {
        this.displayableFactory = displayableFactory;
    }

    @Override
    public HexButton makeHexButton(int x, int y)
    {
        Displayable[] buttonDisplayables = displayableFactory.makeHexButton(Color.WHITE, Color.PINK, Color.RED, Color.GRAY);
        return new HexButton(x, y, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2], displayableFactory.getHexShape());
    }

    @Override
    public Widget makeCameraController(DraggableCamera camera)
    {
        CompositeClickable clickRoot = new ExclusiveClickable(0, 0);
        CompositeDisplayable displayRoot = new CompositeDisplayable(0, 0);
        Widget widget = new Widget(0, 0, clickRoot, displayRoot);

        displayRoot.add(displayableFactory.makeBox(0, 0, 128, 48, Color.WHITE));
        displayRoot.add(new StringDisplayable(2, 0, Color.BLACK, () ->
                "Cam Pos: " + camera.getDx() + "," + camera.getDy() + "\nCam Dim: " + camera.getWidth() + "," + camera.getHeight()));

        Displayable[] buttonDisplayables = displayableFactory.makeRectButton(64, 32, Color.WHITE, Color.GRAY, Color.RED, Color.BLACK, "Reset");
        Button reset = new Button(32, 48, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2]);
        reset.setPress((x, y) -> camera.translate(-camera.getDx(), -camera.getDy()));
        clickRoot.add(reset);
        clickRoot.add(new SimpleClickable(0, 0, 128, 48));
        displayRoot.add(reset);

        return widget;
    }
}
