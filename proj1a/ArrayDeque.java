

public class ArrayDeque<T> {
    
    private int initArraySize = 8;
    private int size;
    private int totalSize     = initArraySize;
    private int listFirst     = 0;
    private int listLast      = 0;
    private T[] arrayList;

    public ArrayDeque() {
        arrayList = (T[]) new Object[initArraySize];
        this.size = 0;
    }
    
    private int modeMins(int index) {
        if (index - 1 < 0) {
            return totalSize - 1;
        }
        return index - 1;
    }

    private int modeAdd(int index) {
        if (index + 1 == totalSize) {
            return 0;
        }
        return index + 1;
    }

    private void resizeArrayListBig() {
        totalSize = totalSize * 2;
        T[] tempArray = (T[]) new Object[totalSize];
        if (listFirst < listLast) {
            System.arraycopy(arrayList, listFirst, tempArray, listFirst, totalSize / 2);
        } else {
            System.arraycopy(arrayList, 0, tempArray, 0, listLast + 1);
            System.arraycopy(arrayList, listFirst, tempArray, listFirst + totalSize / 2,
                             totalSize / 2 - listFirst);
            listFirst += totalSize / 2;
        }
        arrayList = tempArray;
    }

    private void resizeArrayListSmall() {
        totalSize = totalSize / 2;
        T[] tempArray = (T[]) new Object[totalSize];
        if (listFirst < listLast) {
            System.arraycopy(arrayList, listFirst, tempArray, 0, listLast - listFirst + 1);
            listLast  = listLast - listFirst;
            listFirst = 0;
        } else {
            System.arraycopy(arrayList, 0, tempArray, 0, listLast + 1);
            System.arraycopy(arrayList, listFirst, tempArray, listFirst - totalSize,
                             totalSize * 2 - listFirst);
            listFirst -= totalSize;
        }
        arrayList = tempArray;
    }

    public void addFirst(T num) {
        if (isEmpty()) {
            arrayList[this.listFirst] = num;
            this.size += 1;
            return;
        }
        if (size == totalSize) {
            resizeArrayListBig();
        }
        this.listFirst = modeMins(this.listFirst);
        arrayList[this.listFirst] = num;
        this.size += 1;
    }

    public void addLast(T num) {
        if (isEmpty()) {
            arrayList[this.listLast] = num;
            this.size += 1;
            return;
        }
        if (size == totalSize) {
            resizeArrayListBig();
        }
        this.listLast = modeAdd(this.listLast);
        arrayList[this.listLast] = num;
        this.size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i++) {   
            System.out.print(this.arrayList[i]);
            System.out.print(' ');
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size < totalSize / 2 && totalSize > 16) {
            resizeArrayListSmall();
        }
        T temp = arrayList[listFirst];
        if (size > 1) {
            listFirst = modeAdd(listFirst);
        }
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size < totalSize / 2 && totalSize > 16) {
            resizeArrayListSmall();
        }
        T temp = arrayList[listLast];
        if (size > 1) {
            listLast = modeMins(listLast);
        }
        this.size -= 1;
        return temp;
    }

    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        if (listFirst + index >= totalSize) {
            index = listFirst + index - totalSize;
        } else {
            index = index + listFirst;
        }
        return arrayList[index];
    }
}
