package guiframework.elements.clickables;

public class CompositeClickableTest extends AbstractClickableTest
{
    @Override
    protected Clickable makeClickable(int x, int y, int width, int height)
    {
        SimpleClickable component = new SimpleClickable(0, 0, width, height);
        component.setEnter((a, b) -> enter());
        component.setExit((a, b) -> exit());
        component.setPress((a, b) -> press());
        component.setRelease((a, b) -> release());

        CompositeClickable composite = new CompositeClickable(x, y);
        composite.add(component);
        return composite;
    }
}
