public class LinkedListDeque<T> implements Deque<T> {
    private class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node() {

        }

        public Node(T item) {
            this.item = item;
        }
    }


    int size;
    private Node<T> first;
    private Node<T> last;

    public LinkedListDeque() {
        first = new Node<>();
        last = new Node<>();
        first.next = last;
        last.prev = first;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node<T> newNode = new Node<>(item);
        newNode.next = first.next;
        first.next.prev = newNode;
        newNode.prev = first;
        first.next = newNode;
    }

    @Override
    public void addLast(T item) {
        size++;
        Node<T> newNode = new Node<>(item);
        newNode.prev = last.prev;
        last.prev.next = newNode;
        newNode.next = last;
        last.prev = newNode;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> node = first.next;
        while (node != last) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        T item = first.next.item;
        first.next = first.next.next;
        first.next.prev = first;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        T item = last.prev.item;
        last.prev = last.prev.prev;
        last.prev.next = last;
        return item;
    }

    @Override
    public T get(int index) {
        Node<T> tempNode = first.next;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.item;
    }

    public T getRecursiveHelper(int index, Node<T> node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    public T getRecursive(int index) {
        Node tempNode = first;
        return (T) getRecursiveHelper(index, tempNode.next);
    }
}
