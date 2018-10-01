package guiframework.elements.clickables;

import guiframework.common.Locatable;

public interface Clickable extends Locatable
{
    default boolean pointIsOn(int x, int y)
    {
        int thisX = getX(), thisY = getY();
        return (x >= thisX && y >= thisY && x < thisX + getWidth() && y < thisY + getHeight());
    }

    void enter(int x, int y);
    void exit(int x, int y);
    void press(int x, int y);
    void release(int x, int y);

    Clickable NULL = new Clickable()
    {
        public boolean pointIsOn(int x, int y) { return false; }

        public void enter(int x, int y) {}
        public void exit(int x, int y) {}
        public void press(int x, int y) {}
        public void release(int x, int y) {}

        public int getWidth() { return 0; }
        public int getHeight() { return 0; }
        public int getX() { return -9999; }
        public int getY() { return -9999; }

        public void setX(int x) { throw new UnsupportedOperationException("Tried to invoke setX on NULL Clickable"); }
        public void setY(int y) { throw new UnsupportedOperationException("Tried to invoke setY on NULL Clickable"); }
    };
}
