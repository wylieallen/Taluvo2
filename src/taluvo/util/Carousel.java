package taluvo.util;

public class Carousel<T>
{
    private Node head, tail, current;
    private int size;


    public Carousel()
    {
        head = tail = current = null;
        size = 0;
    }

    public Carousel(T... values)
    {
        super();
        for(T value : values)
            this.add(value);
    }

    public void add(T value)
    {
        if(size == 0)
        {
            head = tail = current = new Node(value, null);
        }
        else
        {
            tail.next = new Node(value, head);
            tail = tail.next;
        }
        size++;
    }

    public void remove(T value)
    {
        if(size <= 0) return;


        if(size == 1 && head.value.equals(value))
        {
            head = tail = current = null;
            --size;
            return;
        }

        Node preceding = getPreceding(value);

        if(preceding == null) return;

        Node toRemove = preceding.next;

        if(toRemove == current)
        {
            current = toRemove.next;
        }

        if(toRemove == tail)
        {
            tail = tail.next;
        }

        if(toRemove == head)
        {
            head = head.next;
        }

        --size;
    }

    public T next()
    {
        T value = current.value;
        current = current.next;
        return value;
    }

    private Node getPreceding(T value)
    {
        Node prev = tail, cur = head;

        do {
            if(cur.value.equals(value))
            {
                return prev;
            }
            prev = cur;
            cur = cur.next;
        } while (cur != head);

        return null;
    }

    private Node getNode(T value)
    {
        Node cur = head;

        do
        {
            if(cur.value.equals(value))
            {
                return cur;
            }
            cur = cur.next;
        } while (cur != head);

        return null;
    }

    private class Node
    {
        Node(T value, Node next)
        {
            this.value = value;

            if(next == null)
                this.next = this;
            else
                this.next = next;
        }

        final T value;
        Node next;
    }
}
