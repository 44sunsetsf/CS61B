public class LinkedListDeque <T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    int size;
    Node sentinel;

    public LinkedListDeque() {
       this.size = 0;
       this.sentinel = new Node(null,(T) new Object(),null);
       sentinel.prev = sentinel;
       sentinel.next = sentinel;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int Size(){
        return size;
    }
    public void addFirst(T x){
        Node newNode = new Node(sentinel,x,sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }
    public void addLast (T x){
        Node newNode = new Node(sentinel.prev,x,sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }
    public void printDeque(){
        Node ptr = sentinel.next;
        while (ptr != sentinel){
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
        }
    public T removeFirst(){
        if(isEmpty()) return null;
        size --;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return res;
    }
    public T removeLast(){
        if(isEmpty()) return null;
        size--;
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return res;
    }
    public T get(int index){
        if(size <= index) return null;
        Node ptr = sentinel.next;
        for(int i = 0;i <= index;i++){
            ptr = ptr.next;
        }
        return ptr.item;
    }
    public T getRecursive(int index){
        return getRecursiveHelper(sentinel.next,index);
    }
    private T getRecursiveHelper(Node start,int index){
        if(index == 0){
            return start.item;
        }else {
            return getRecursiveHelper(start.next,index--);
        }
    }

    }
