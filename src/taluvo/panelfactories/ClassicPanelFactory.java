package taluvo.panelfactories;

import guiframework.DisplayPanel;
import guiframework.elements.clickables.CompositeClickable;
import guiframework.ClickHandler;
import guiframework.elements.displayables.CompositeDisplayable;
import guiframework.elements.displayables.Displayable;
import guiframework.widgets.*;
import guiframework.widgets.Button;
import taluvo.displayablefactories.DisplayableFactory;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ClassicPanelFactory implements PanelFactory
{
    private static final int WIDTH = 1280, HEIGHT = 720;
    private final DisplayableFactory displayableFactory;

    public ClassicPanelFactory(DisplayableFactory displayableFactory)
    {
        this.displayableFactory = displayableFactory;
    }

    @Override
    public DisplayPanel makeTestPanel()
    {
        CompositeDisplayable scene = new CompositeDisplayable(0, 0);
        DisplayPanel panel = new DisplayPanel(scene);
        panel.setSize(WIDTH, HEIGHT);

        CompositeClickable root = new CompositeClickable(0, 0);
        ClickHandler clickHandler = new ClickHandler(root);
        panel.addMouseListener(clickHandler);
        panel.addMouseMotionListener(clickHandler);
        clickHandler.setRoot(root);

        Displayable[] buttonDisplayables = displayableFactory.makeMenuButton();
        Button button = new Button(300, 300, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2]);
        button.setEnter((x, y) -> System.out.println("Entry at " + x + "," + y));
        Button button2 = new Button(0, 0, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2]);
        button2.setEnter((x, y) -> System.out.println("Button2 entry at " + x + "," + y));
        Button button3 = new Button(0, 100, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2]);
        Button button4 = new Button(200, 100, buttonDisplayables[0], buttonDisplayables[1], buttonDisplayables[2]);
        ButtonGroup group = new ButtonGroup(500, 500, button2, button3, button4);

        HeadsUpDisplay hud = new HeadsUpDisplay(0, 0, WIDTH, HEIGHT, 20);

        hud.addLeft(displayableFactory.makeBox(0, 0, 100, 100, Color.RED));
        hud.addLeft(displayableFactory.makeBox(0, 0, 100, 100, Color.BLUE));
        hud.addLeft(displayableFactory.makeBox(0, 0, 100, 100, Color.GREEN));
        hud.addLeft(displayableFactory.makeBox(0, 0, 100, 100, Color.RED));

        hud.addCenter(displayableFactory.makeBox(0, 0, 100, 100, Color.RED));
        hud.addCenter(displayableFactory.makeBox(0, 0, 100, 100, Color.ORANGE));
        hud.addCenter(group);

        hud.addRight(displayableFactory.makeBox(0, 0, 100, 100, Color.YELLOW));
        hud.addRight(displayableFactory.makeBox(0, 0, 100, 100, Color.CYAN));
        hud.addRight(displayableFactory.makeBox(0, 0, 100, 100, Color.WHITE));

        DraggableCamera camera = new DraggableCamera(0, 0, WIDTH, HEIGHT);

        camera.add(displayableFactory.makeBox(0, 0, WIDTH, HEIGHT, Color.GRAY));
        camera.add(displayableFactory.makeBox(0, 0, 20, 20, Color.PINK));
        camera.add(displayableFactory.makeBox(0, HEIGHT - 20, 20, 20, Color.PINK));
        camera.add(displayableFactory.makeBox(WIDTH - 20, 0, 20, 20, Color.PINK));
        camera.add(displayableFactory.makeBox(WIDTH - 20, HEIGHT - 20, 20, 20, Color.PINK));
        camera.add(button);

        scene.add(camera);
        scene.add(hud);

        root.add(hud);
        root.add(camera);

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

        return panel;
    }
}
