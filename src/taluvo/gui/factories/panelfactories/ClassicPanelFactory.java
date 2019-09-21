package taluvo.gui.factories.panelfactories;

import com.sun.javafx.css.Rule;
import guiframework.InterfacePanel;
import guiframework.elements.clickables.composites.ExclusiveClickable;
import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;
import guiframework.elements.displayables.primitives.ImageDisplayable;
import guiframework.widgets.*;
import guiframework.widgets.Button;
import taluvo.game.Game;
import taluvo.game.Player;
import taluvo.game.hex.Building;
import taluvo.game.hex.Terrain;
import taluvo.game.rules.RuleViolationException;
import taluvo.gui.factories.displayablefactories.ClassicDisplayableFactory;
import taluvo.gui.factories.displayablefactories.DisplayableFactory;
import taluvo.gui.factories.widgetfactories.ClassicWidgetFactory;
import taluvo.gui.factories.widgetfactories.WidgetFactory;
import taluvo.gui.widgets.HexButton;
import taluvo.gui.widgets.HexDetailWidget;
import taluvo.util.Reference;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

public class ClassicPanelFactory implements PanelFactory
{
    private static final int WIDTH = 1280, HEIGHT = 720, BUFFER = 20;
    private final DisplayableFactory displayableFactory;
    private final WidgetFactory widgetFactory;

    public ClassicPanelFactory(DisplayableFactory displayableFactory)
    {
        this.displayableFactory = displayableFactory;
        this.widgetFactory = new ClassicWidgetFactory(displayableFactory);
    }

    @Override
    public void configureGamePanel(InterfacePanel panel)
    {
        CompositeDisplayable scene = new CompositeDisplayable(0, 0);
        panel.setScene(scene);
        panel.setSize(WIDTH, HEIGHT);

        ExclusiveClickable root = new ExclusiveClickable(0, 0);
        panel.setRoot(root);

        DraggableCamera camera = new DraggableCamera(0, 0, panel.getWidth(), panel.getHeight());
        HeadsUpDisplay hud = new HeadsUpDisplay(0, 0,  panel.getWidth(), panel.getHeight(), BUFFER);

        scene.add(camera);
        scene.add(hud);

        root.add(hud);
        root.add(camera);

        hud.addLeft(widgetFactory.makeCameraController(camera));

        Map<Point, HexButton> buttonMap = new HashMap<>();

        Player player1 = new Player("Wylie"), player2 = new Player("Bob");
        Game game = new Game(player1, player2);

        Map<Player, Color> primaryColors = new HashMap<>();
        Map<Player, Color> secondaryColors = new HashMap<>();

        primaryColors.put(player1, Color.WHITE);
        secondaryColors.put(player1, Color.BLACK);

        primaryColors.put(player2, Color.BLACK);
        secondaryColors.put(player2, Color.WHITE);

        HexDetailWidget hexDetailWidget = widgetFactory.makeHexDetailWidget(game.getBoardView());

        hud.addRight(widgetFactory.makeDeckController(game.getDeck()));
        hud.addRight(hexDetailWidget);

        Reference<Building> activeBuilding = new Reference<>();
        Reference<Terrain> activeTerrain = new Reference<>();

        Widget buildingSelector = widgetFactory.makeBuildSelectorWidget(activeTerrain, activeBuilding);
        buildingSelector.press(34, 2);
        hud.addRight(buildingSelector);

        hud.addCenter(widgetFactory.makePlayerStatusWidget(player1, primaryColors.get(player1), secondaryColors.get(player1)));
        hud.addCenter(widgetFactory.makePlayerStatusWidget(player2, primaryColors.get(player2), secondaryColors.get(player2)));

        game.subscribe((x, y, height, terrain, tileId) ->
        {
            Point point = new Point(x, y);

            if(buttonMap.containsKey(point))
            {
                HexButton button = buttonMap.get(point);
                ImageDisplayable[] buttonDisplayables = displayableFactory.makeHexButton(terrain, Color.PINK, Color.RED, Color.GRAY);

                button.setTerrain(buttonDisplayables[0].getImage(), buttonDisplayables[1].getImage(), buttonDisplayables[2].getImage(), height);
            }
            else
            {
                HexButton button = widgetFactory.makeHexButton(x, y, terrain);

                button.setPress((a, b) ->
                {
                    try
                    {
                        if(!game.isTilePlaced())
                        {
                            game.placeTile(x, y);
                        }
                        else
                        {
                            game.placeBuilding(x, y, activeBuilding.get());
                        }
                    }
                    catch (RuleViolationException e)
                    {
                        System.out.println(e.getMessage());
                    }

                });
                button.setEnter((a, b) -> {hexDetailWidget.setTarget(x, y); hexDetailWidget.enable(); });
                button.setExit((a, b) -> hexDetailWidget.disable());

                buttonMap.put(point, button);
                camera.add(button);
            }
        });

        game.subscribe((x, y, building, player) ->
        {
                    Displayable buildingDisplayable = displayableFactory.getBuildingDisplayable(building, primaryColors.get(player), secondaryColors.get(player));
                    buildingDisplayable.setX(4);
                    buildingDisplayable.setY(13);
                    buttonMap.get(new Point(x, y)).setBuilding(buildingDisplayable);
        });

        panel.addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent e)
            {
                int width = e.getComponent().getWidth();
                int height = e.getComponent().getHeight();

                hud.resize(width, height);
                camera.resize(width, height);
            }
        });

        game.start();
    }
}
