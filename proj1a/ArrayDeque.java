public class ArrayDeque<T> {

    private T[] deque;
    private int size;
    private int nextFront;

    private int nextBack;

    public ArrayDeque() {
        deque = (T[]) new Object[8];
        size = 0;
        nextFront = deque.length - 1;
        nextBack = 0;
    }

    private void resize() {
        T[] newQueue;

        // enlarge size
        if (size == deque.length) {
            newQueue = (T[]) new Object[2 * deque.length];

        }
        // reduce size
        else if (deque.length >= 16 && size / (double) deque.length <= 0.25) {
            newQueue = (T[]) new Object[deque.length / 2];
        } else {
            return;
        }
        int frontIndicater = nextFront + 1;
        for (int i = 0; i < size; i++) {
            if (frontIndicater >= deque.length) {
                frontIndicater = 0;
            }
            newQueue[i] = deque[frontIndicater];
            frontIndicater++;

            deque = newQueue;
            nextFront = deque.length - 1;
            nextBack = size;
        }
    }


    public void addFirst(T item) {
        deque[nextFront] = item;
        nextFront--;
        size++;
        resize();
    }

    public void addLast(T item) {
        deque[nextBack] = item;
        nextBack++;
        size++;
        resize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int frontIndicater = nextFront + 1;
        for (int i = 0; i < size; i++) {
            if (frontIndicater >= deque.length) {
                frontIndicater = 0;
            }
            System.out.print(deque[frontIndicater] + " ");
            frontIndicater++;
        }
        System.out.println();
    }

    public T removeFirst() {
        int frontIndicater = nextFront + 1;
        frontIndicater = frontIndicater >= deque.length ? 0 : frontIndicater;
        size--;
        T item = deque[frontIndicater];
        nextFront = frontIndicater;
        resize();
        return item;
    }

    public T removeLast() {
        int backIndicater = nextBack - 1;
        backIndicater = backIndicater < 0 ? deque.length - 1 : backIndicater;
        size--;
        T item = deque[backIndicater];
        nextBack = backIndicater;
        resize();
        return item;
    }

    public T get(int index) {
        if (deque.length - nextFront - 1 > index)
            return deque[nextFront + 1 + index];
        else {
            index = index - (deque.length - nextFront - 1);
            return deque[index];
        }
    }
}
