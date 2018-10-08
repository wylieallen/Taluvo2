package guiframework.elements.displayables;

import guiframework.common.Locatable;

import java.awt.*;

public interface Displayable extends Locatable
{
    void display(Graphics2D g2d);

    void update();

    Displayable NULL = new Displayable() {
        public int getX() { return 0; }
        public int getY() { return 0; }
        public int getHeight() { return 0; }
        public int getWidth() { return 0; }
        public void setX(int x) {}
        public void setY(int y) {}
        public void display(Graphics2D g2d) {}
        public void update() {}
    };
}
