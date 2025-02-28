import java.util.Vector;
class StackVector<T> implements IStack<T> {
    private Vector<T> stack = new Vector<>();
    
    @Override
    public void push(T data) {
        stack.add(data);
    }
    
    @Override
    public T pop() {
        if (stack.isEmpty()) return null;
        return stack.remove(stack.size() - 1);
    }
    
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
