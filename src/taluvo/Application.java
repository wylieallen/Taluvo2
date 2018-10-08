package taluvo;

import guiframework.InterfacePanel;
import guiframework.elements.clickables.Clickable;
import guiframework.elements.displayables.Displayable;
import taluvo.gui.factories.displayablefactories.ClassicDisplayableFactory;
import taluvo.gui.factories.imagefactories.ClassicImageFactory;
import taluvo.gui.factories.panelfactories.ClassicPanelFactory;
import taluvo.gui.factories.panelfactories.PanelFactory;

import javax.swing.*;
import java.awt.*;

public class Application
{
    private static Timer timer;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Application::createAndShowGui);
    }

    private static void createAndShowGui()
    {
        PanelFactory panelFactory = new ClassicPanelFactory(new ClassicDisplayableFactory(new ClassicImageFactory()));

        JFrame frame = new JFrame();
        frame.setSize(1280 + 16, 720 + 39);
        frame.setTitle("Taluvo2 v0.0");

        InterfacePanel panel = new InterfacePanel(Displayable.NULL, Clickable.NULL);
        panelFactory.configureGamePanel(panel);
        frame.setContentPane(panel);
        panel.setBackground(Color.BLUE);

        frame.validate();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        timer = new Timer(16, e -> {
            timer.stop();

            panel.update();
            panel.repaint();

            timer.restart();
        });

        timer.start();
    }
}
