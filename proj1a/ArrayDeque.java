import java.lang.reflect.Array;

public class ArrayDeque<T> {
    
    private int initArraySize = 8;
    private T   initvalue     = null;
    private int size          = 0;
    private int totalSize     = initArraySize;
    private int ListFirst     = 0;
    private int ListLast      = 0;
    private T[] ArrayList;

    public ArrayDeque(T num)
    {
        ArrayList = (T[]) new Object[initArraySize];
        ArrayList[size] = num;
        size += 1; 
    }

    public ArrayDeque()
    {
        ArrayList = (T[]) new Object[initArraySize];
        this.size = 0;
    }
    
    private int modeMins(int index)
    {
        if (index - 1 < 0) {
            return totalSize - 1;
        }
        return index - 1;
    }

    private int modeAdd(int index)
    {
        if (index + 1 == totalSize) {
            return 0;
        }
        return index + 1;
    }

    private void resizeArrayList()
    {
        initArraySize = initArraySize * 2;
        T[] tempArray = (T[])new Object[initArraySize];
        for (int i = 0; i < totalSize; i++) {
            tempArray[i] = ArrayList[i];
        }
        ListFirst = 0;
        ListLast  = totalSize - 1;
        ArrayList = tempArray;
    }

    public void addFirst(T num)
    {
        if (isEmpty()) {
            ArrayList[this.ListFirst] = num;
            this.size += 1;
            return;
        }
        if (size == totalSize) {
            resizeArrayList();
        }
        this.ListFirst = modeMins(this.ListFirst);
        ArrayList[this.ListFirst] = num;
        this.size += 1;
    }

    public void addLast(T num)
    {
        if (isEmpty()) {
            ArrayList[this.ListLast] = num;
            this.size += 1;
            return;
        }
        if (size == totalSize) {
            resizeArrayList();
        }
        this.ListLast = modeAdd(this.ListLast);
        ArrayList[this.ListLast] = num;
        this.size += 1;
    }

    public boolean isEmpty()
    {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size()
    {
        return this.size;
    }

    public void printDeque()
    {
        for (int i = 0; i < this.size; i++) {   
            System.out.print(this.ArrayList[i]);
            System.out.print(' ');
        }
    }

    public T removeFirst()
    {
        if (isEmpty()) {
            return null;
        }
        T temp = ArrayList[ListFirst];
        if (size > 1) {
            ListFirst = modeAdd(ListFirst);
        }
        size -= 1;
        return temp;
    }

    public T removeLast()
    {
        if (isEmpty()) {
            return null;
        }
        T temp = ArrayList[ListLast];
        if (size > 1) {
            ListLast = modeMins(ListLast);
        }
        this.size -= 1;
        return temp;
    }

    public T get(int index)
    {
        if (index > this.size) {
            return null;
        }
        if (ListFirst + index >= totalSize) {
            index = ListFirst + index - totalSize;
        } else {
            index = index + ListFirst;
        }
        return ArrayList[index];
    }
}
