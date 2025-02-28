class DoublyLinkedList<T> implements IList<T> {
    private class Node {
        T data;
        Node next, prev;
        Node(T data) { this.data = data; this.next = null; this.prev = null; }
    }
    private Node head;
    
    @Override
    public void push(T data) {
        Node newNode = new Node(data);
        if (head != null) {
            head.prev = newNode;
        }
        newNode.next = head;
        head = newNode;
    }
    
    @Override
    public T pop() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        head = head.next;
        if (head != null){
            head.prev = null;
        } 
        return data;
    }
    
    @Override
    public boolean isEmpty() {
        return head == null;
    }
}