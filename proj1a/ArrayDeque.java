public class ArrayDeque <T> {
    private T[] items;
    private int front;
    private int last;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        last = 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    private boolean isFull(){
        return size == items.length;
    }
    private boolean isSparse(){
        return items.length >= 16 && size < (items.length)/4;
    }
    private int plusOne(int index){
        return (index + 1) % items.length;
    }
    private int minusOne(int index){
        return Math.floorMod(index, items.length);
    }
    private void resize(int capacity){
        T[] newDeque = (T[]) new Object[capacity];
        int oldindex = plusOne(front);
        for (int newindex = 0;newindex < size;newindex++){
            newDeque[newindex] = items[oldindex];
            oldindex = plusOne(oldindex);
        }
        items = newDeque;
        front = capacity - 1;
        last = size;
    }
    private void upsize (){
       resize(2*size);
    }
    private void downsize (){
        resize(items.length/2);
    }
    public void printDeque(){
        for (int i = plusOne(front);i != last;i = plusOne(i)){
            System.out.println(items[i] + " ");
        }
        System.out.println();
    }
    public void addFirst(T item){
        if (isFull()){
            upsize();
        }
        items[front] = item;
        front = minusOne(front);
        size++;
    }
    public void addLast(T item){
        if(isFull()){
            upsize();
        }
        items[last] = item;
        last = plusOne(last);
        size--;
    }
    public T removeFirst(){
        if(isSparse()){
            downsize();
        }
        int i = plusOne(front);
        T res = items[i];
        items[i] = null;
        front = i;
        if(!isEmpty()){
            size -= 1;
        }
        return res;
    }
    public T removeLast() {
        if(isSparse()){
            downsize();
        }
        last = minusOne(last);
        T res = items[last];
        items[last] = null;
        if(!isEmpty()){
            size--;
        }
        return res;
    }
    public T get(int index){
        if(index >= size){
            return null;
        }
        int starter = plusOne(front);
        return items[Math.floorMod(starter+index,items.length)];
    }
}
