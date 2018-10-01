package guiframework.elements.clickables;

class SimpleClickableTest extends AbstractClickableTest
{
    @Override
    protected Clickable makeClickable(int x, int y, int width, int height)
    {
        SimpleClickable clickable = new SimpleClickable(x, y, width, height);

        clickable.setEnter((a, b) -> enter());
        clickable.setExit((a, b) -> exit());
        clickable.setPress((a, b) -> press());
        clickable.setRelease((a, b) -> release());

        return clickable;
    }
}