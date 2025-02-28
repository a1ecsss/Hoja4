class StackList<T> implements IStack<T> {
    private IList<T> list;
    
    public StackList(boolean isDoubly) {
        this.list = isDoubly ? new DoublyLinkedList<>() : new SinglyLinkedList<>();
    }
    
    @Override
    public void push(T data) {
        list.push(data);
    }
    
    @Override
    public T pop() {
        return list.pop();
    }
    
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}