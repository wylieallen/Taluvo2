package taluvo.gui.factories.widgetfactories;

import guiframework.widgets.CompositeWidget;
import guiframework.widgets.DraggableCamera;
import guiframework.widgets.Widget;
import taluvo.game.Board;
import taluvo.game.Player;
import taluvo.game.hex.Building;
import taluvo.game.hex.Terrain;
import taluvo.game.hex.deck.Deck;
import taluvo.gui.widgets.HexButton;
import taluvo.gui.widgets.HexDetailWidget;
import taluvo.util.Reference;

import java.awt.*;

public interface WidgetFactory
{
    HexDetailWidget makeHexDetailWidget(Board.View boardView);
    Widget makeCameraController(DraggableCamera camera);
    HexButton makeHexButton(int x, int y, Terrain terrain);
    Widget makeDeckController(Deck deck);
    CompositeWidget makeRadial(int x, int y);
    Widget makeBuildSelectorWidget(Reference<Terrain> activeTerrain, Reference<Building> activeBuilding);
    Widget makePlayerStatusWidget(Player player, Color primary, Color secondary);
}
