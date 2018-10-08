package taluvo.gui.factories.panelfactories;

import guiframework.DisplayPanel;
import guiframework.InterfacePanel;

public interface PanelFactory
{
    DisplayPanel makeTestPanel();
    void configureGamePanel(InterfacePanel panel);
}
