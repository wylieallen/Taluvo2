package guiframework.common;

public interface AbstractFunction
{
    void execute();

    AbstractFunction NULL = () -> {};
}
