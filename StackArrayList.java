import java.util.ArrayList;
class StackArrayList<T> implements IStack<T> {
    private ArrayList<T> stack = new ArrayList<>();
    
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