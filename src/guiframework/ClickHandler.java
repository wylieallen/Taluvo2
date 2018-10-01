package guiframework;

import guiframework.elements.clickables.Clickable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClickHandler implements MouseListener, MouseMotionListener
{
    private Clickable root;

    public ClickHandler(Clickable root) { this.root = root; }

    public Clickable getRoot() { return root; }
    public void setRoot(Clickable root) { this.root = root; }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e)
    {
        root.press(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        root.release(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseMoved(e);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        root.enter(e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        root.enter(e.getX(), e.getY());
    }
}
