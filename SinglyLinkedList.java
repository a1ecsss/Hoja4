class SinglyLinkedList<T> implements IList<T> {
    private class Node {
        T data;
        Node next;
        Node(T data) { this.data = data; this.next = null; }
    }
    private Node head;
    
    @Override
    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    
    @Override
    public T pop() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        return data;
    }
    
    @Override
    public boolean isEmpty() {
        return head == null;
    }
}