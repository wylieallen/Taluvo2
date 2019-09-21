package taluvo.gui.factories.widgetfactories;

import guiframework.elements.clickables.SimpleClickable;
import guiframework.elements.clickables.composites.CompositeClickable;
import guiframework.elements.clickables.composites.ExclusiveClickable;
import guiframework.elements.clickables.composites.RadialClickable;
import guiframework.elements.displayables.*;
import guiframework.elements.displayables.primitives.ImageDisplayable;
import guiframework.elements.displayables.primitives.StringDisplayable;
import guiframework.widgets.Button;
import guiframework.widgets.CompositeWidget;
import guiframework.widgets.DraggableCamera;
import guiframework.widgets.Widget;
import taluvo.game.Board;
import taluvo.game.Player;
import taluvo.game.hex.Building;
import taluvo.game.hex.Terrain;
import taluvo.game.hex.Tile;
import taluvo.game.hex.deck.Deck;
import taluvo.gui.factories.displayablefactories.ClassicDisplayableFactory;
import taluvo.gui.factories.displayablefactories.DisplayableFactory;
import taluvo.gui.widgets.HexButton;
import taluvo.gui.widgets.HexDetailWidget;
import taluvo.util.Reference;

import java.awt.*;

public class ClassicWidgetFactory implements WidgetFactory
{
    private DisplayableFactory displayableFactory;

    public ClassicWidgetFactory(DisplayableFactory displayableFactory)
    {
        this.displayableFactory = displayableFactory;
    }

    @Override
    public Widget makeBuildSelectorWidget(Reference<Terrain> activeTerrain, Reference<Building> activeBuilding)
    {
        CompositeWidget buildingSelectorRadialGroup = this.makeRadial(0, 0);

        buildingSelectorRadialGroup.add(makeBuildingSelector(33, 0, Building.VILLAGE, activeBuilding));
        buildingSelectorRadialGroup.add(makeBuildingSelector(0, 34, Building.TEMPLE, activeBuilding));
        buildingSelectorRadialGroup.add(makeBuildingSelector(66, 34, Building.TOWER, activeBuilding));

        buildingSelectorRadialGroup.add(makeTerrainSelector(0, 76, Terrain.GRASSY, activeTerrain));
        buildingSelectorRadialGroup.add(makeTerrainSelector(66, 76, Terrain.JUNGLE, activeTerrain));
        buildingSelectorRadialGroup.add(makeTerrainSelector(0, 110, Terrain.LAKE, activeTerrain));
        buildingSelectorRadialGroup.add(makeTerrainSelector(66, 110, Terrain.ROCKY, activeTerrain));

        return buildingSelectorRadialGroup;
    }

    private Button makeTerrainSelector(int x, int y, Terrain terrain, Reference<Terrain> activeTerrain)
    {
        Color color = ClassicDisplayableFactory.getTerrainColor(terrain);
        Displayable[] displayables = displayableFactory.makeRectButton(64, 32, Color.WHITE, color, color, Color.BLACK, terrain.name());
        Button button = new Button(x, y, displayables[0], displayables[1], displayables[2]);
        button.setPress((a, b) -> activeTerrain.set(terrain));
        return button;
    }

    private Button makeBuildingSelector(int x, int y, Building building, Reference<Building> activeBuilding)
    {
        Displayable[] displayables = displayableFactory.makeRectButton(64, 32, Color.WHITE, Color.GRAY, Color.GRAY, Color.BLACK, building.name());
        Button button = new Button(x, y, displayables[0], displayables[1], displayables[2]);
        button.setPress((a, b) -> activeBuilding.set(building));
        return button;
    }

    @Override
    public Widget makePlayerStatusWidget(Player player, Color primary, Color secondary)
    {
        CompositeDisplayable displayables = new CompositeDisplayable(0, 0);
        displayables.add(displayableFactory.makeBox(0, 0, 128, 64, Color.WHITE));
        displayables.add(new StringDisplayable(4, 0, Color.BLACK, player::toString));
//        displayables.add(new StringDisplayable(96, 0, Color.BLACK, () -> "" + player.getScore()));

        AlignedDisplayable aligned = new AlignedDisplayable(4, 24, 128, 4, AlignedDisplayable.Orientation.HORIZONTAL, AlignedDisplayable.Alignment.NEUTRAL);

        aligned.add(displayableFactory.makeBuildingStatusDisplayable(player, Building.VILLAGE, primary, secondary));
        aligned.add(displayableFactory.makeBuildingStatusDisplayable(player, Building.TEMPLE, primary, secondary));
        aligned.add(displayableFactory.makeBuildingStatusDisplayable(player, Building.TOWER, primary, secondary));

        displayables.add(aligned);

        return new Widget(0, 0, new SimpleClickable(0, 0, 128, 48), displayables);
    }

    @Override
    public CompositeWidget makeRadial(int x, int y)
    {
        return new CompositeWidget(x, y, new RadialClickable(0, 0), new CompositeDisplayable(0, 0));
    }

    @Override
    public HexButton makeHexButton(int x, int y, Terrain terrain)
    {
        ImageDisplayable[] buttonDisplayables = displayableFactory.makeHexButton(terrain, Color.PINK, Color.RED, Color.GRAY);
        return new HexButton(x, y, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2], displayableFactory.getHexShape(),
                new CompositeDisplayable(0, 0), new CompositeDisplayable(0, 0), new CompositeDisplayable(0, 0));
    }

    @Override
    public HexDetailWidget makeHexDetailWidget(Board.View boardView)
    {
        return new HexDetailWidget(boardView, 0, 0, new CompositeDisplayable(0, 0), displayableFactory);
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

    @Override
    public Widget makeDeckController(Deck deck)
    {
        CompositeClickable clickRoot = new ExclusiveClickable(0, 0);
        CompositeDisplayable displayRoot = new CompositeDisplayable(0, 0);
        Widget widget = new Widget(0, 0, clickRoot, displayRoot);

        displayRoot.add(displayableFactory.makeBox(0, 0, 128, 128, Color.WHITE));

        final int VOLCANO_POS = 44;

        displayRoot.add(displayableFactory.makeHex(VOLCANO_POS, VOLCANO_POS, Color.RED, Color.GRAY));

        // Todo: all this is awful. Refactor so a HexButton can just fiddle with a DeckController when a Tile is popped
        Displayable nullHex = displayableFactory.makeHex(0, 0, ClassicDisplayableFactory.getTerrainColor(null), Color.GRAY);
        Displayable emptyHex = displayableFactory.makeHex(0, 0, ClassicDisplayableFactory.getTerrainColor(Terrain.NONE), Color.GRAY);
        Displayable grassyHex = displayableFactory.makeHex(0, 0, ClassicDisplayableFactory.getTerrainColor(Terrain.GRASSY), Color.GRAY);
        Displayable rockyHex = displayableFactory.makeHex(0, 0, ClassicDisplayableFactory.getTerrainColor(Terrain.ROCKY), Color.GRAY);
        Displayable lakeHex = displayableFactory.makeHex(0, 0, ClassicDisplayableFactory.getTerrainColor(Terrain.LAKE), Color.GRAY);
        Displayable jungleHex = displayableFactory.makeHex(0, 0, ClassicDisplayableFactory.getTerrainColor(Terrain.JUNGLE), Color.GRAY);
        Displayable volcanoHex = displayableFactory.makeHex(0, 0, ClassicDisplayableFactory.getTerrainColor(Terrain.VOLCANO), Color.GRAY);

        ConditionalDisplayable hexA = new ConditionalDisplayable(deck.peek()[1].x + VOLCANO_POS, deck.peek()[1].y + VOLCANO_POS, nullHex);
        hexA.add(emptyHex, () -> deck.peek()[1].terrain == Terrain.NONE);
        hexA.add(grassyHex, () -> deck.peek()[1].terrain == Terrain.GRASSY);
        hexA.add(rockyHex, () -> deck.peek()[1].terrain == Terrain.ROCKY);
        hexA.add(lakeHex, () -> deck.peek()[1].terrain == Terrain.LAKE);
        hexA.add(jungleHex, () -> deck.peek()[1].terrain == Terrain.JUNGLE);
        hexA.add(volcanoHex, () -> deck.peek()[1].terrain == Terrain.VOLCANO);

        ConditionalDisplayable hexB = new ConditionalDisplayable(deck.peek()[2].x + VOLCANO_POS, deck.peek()[2].y + VOLCANO_POS, nullHex);
        hexB.add(emptyHex, () -> deck.peek()[2].terrain == Terrain.NONE);
        hexB.add(grassyHex, () -> deck.peek()[2].terrain == Terrain.GRASSY);
        hexB.add(rockyHex, () -> deck.peek()[2].terrain == Terrain.ROCKY);
        hexB.add(lakeHex, () -> deck.peek()[2].terrain == Terrain.LAKE);
        hexB.add(jungleHex, () -> deck.peek()[2].terrain == Terrain.JUNGLE);
        hexB.add(volcanoHex, () -> deck.peek()[2].terrain == Terrain.VOLCANO);

        displayRoot.add(hexA);
        displayRoot.add(hexB);


        Displayable[] buttonDisplayables = displayableFactory.makeRectButton(64, 32, Color.WHITE, Color.GRAY, Color.RED, Color.BLACK, "ROT. L");
        Button rotLeft = new Button(0, 0, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2]);
        rotLeft.setPress((a, b) ->
        {
            deck.rotateLeft();
            Tile.Entry entryA = deck.peek()[1];
            Tile.Entry entryB = deck.peek()[2];
            hexA.setX(entryA.x + VOLCANO_POS);
            hexA.setY(entryA.y + VOLCANO_POS);
            hexB.setX(entryB.x + VOLCANO_POS);
            hexB.setY(entryB.y + VOLCANO_POS);
        });

        buttonDisplayables = displayableFactory.makeRectButton(64, 32, Color.WHITE, Color.GRAY, Color.RED, Color.BLACK, "ROT. R");
        Button rotRight = new Button(64, 0, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2]);
        rotRight.setPress((a, b) ->
        {
            deck.rotateRight();
            Tile.Entry entryA = deck.peek()[1];
            Tile.Entry entryB = deck.peek()[2];
            hexA.setX(entryA.x + VOLCANO_POS);
            hexA.setY(entryA.y + VOLCANO_POS);
            hexB.setX(entryB.x + VOLCANO_POS);
            hexB.setY(entryB.y + VOLCANO_POS);
        });

        CompositeWidget rots = new CompositeWidget(0, 128, new ExclusiveClickable(0, 0), new CompositeDisplayable(0, 0));
        rots.add(rotLeft);
        rots.add(rotRight);

        displayRoot.add(rots);
        clickRoot.add(rots);

        return widget;
    }
}
