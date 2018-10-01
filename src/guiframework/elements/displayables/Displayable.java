package guiframework.elements.displayables;

import guiframework.common.Locatable;

import java.awt.*;

public interface Displayable extends Locatable
{
    void display(Graphics2D g2d);

    void update();
}
