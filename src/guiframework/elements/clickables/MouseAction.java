package guiframework.elements.clickables;

public interface MouseAction
{
    void execute(int x, int y);

    MouseAction NULL = (x, y) -> {};
}
