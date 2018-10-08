package taluvo.gui.factories.widgetfactories;

import guiframework.widgets.DraggableCamera;
import guiframework.widgets.Widget;
import taluvo.gui.widgets.HexButton;

public interface WidgetFactory
{
    Widget makeCameraController(DraggableCamera camera);
    HexButton makeHexButton(int x, int y);
}
