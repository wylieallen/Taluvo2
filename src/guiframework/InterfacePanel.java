package guiframework;

import guiframework.elements.clickables.Clickable;
import guiframework.elements.displayables.Displayable;

public class InterfacePanel extends DisplayPanel
{
    private final ClickHandler clickHandler;

    public InterfacePanel(Displayable scene, Clickable root)
    {
        super(scene);
        clickHandler = new ClickHandler(root);
        this.addMouseListener(clickHandler);
        this.addMouseMotionListener(clickHandler);
    }

    public void setRoot(Clickable c) { clickHandler.setRoot(c); }
}
