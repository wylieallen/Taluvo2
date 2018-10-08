package guiframework.elements.clickables;

import guiframework.elements.clickables.composites.ExclusiveClickable;

public class ExclusiveClickableTest extends AbstractClickableTest
{
    @Override
    protected Clickable makeClickable(int x, int y, int width, int height)
    {
        SimpleClickable component = new SimpleClickable(0, 0, width, height);
        component.setEnter((a, b) -> enter());
        component.setExit((a, b) -> exit());
        component.setPress((a, b) -> press());
        component.setRelease((a, b) -> release());

        ExclusiveClickable composite = new ExclusiveClickable(x, y);
        composite.add(component);
        return composite;
    }
}
