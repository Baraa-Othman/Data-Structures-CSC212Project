public class Node<T> {
    public T data;
    public Node<T> next;

    public Node() {
        this.data = null;
        this.next = null;
    }
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return this.data;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }
    public Node<T> getNext() {
        return this.next;
    }
    
}
