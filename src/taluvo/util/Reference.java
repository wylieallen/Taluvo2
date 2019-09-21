package taluvo.util;

public class Reference<T>
{
    private T value;

    public T get() { return value; }
    public void set(T value) { this.value = value; }
}
