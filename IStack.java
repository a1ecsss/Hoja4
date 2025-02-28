public interface IStack<T> {
    void push(T data);
    T pop();
    boolean isEmpty();
}