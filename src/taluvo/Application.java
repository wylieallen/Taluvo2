package taluvo;

import guiframework.DisplayPanel;
import taluvo.displayablefactories.ClassicDisplayableFactory;
import taluvo.imagefactories.ClassicImageFactory;
import taluvo.panelfactories.ClassicPanelFactory;
import taluvo.panelfactories.PanelFactory;

import javax.swing.*;
import java.awt.*;

public class Application
{
    private static Timer timer;
    private static DisplayPanel panel;

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

        panel = panelFactory.makeTestPanel();
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
