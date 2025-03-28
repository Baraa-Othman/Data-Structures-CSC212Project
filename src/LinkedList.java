public class LinkedList<T> {
    public Node<T> head;
    public Node<T> current;
    public LinkedList() {
        head = current = null;
    }
    public boolean empty() {
        return head == null;
    }
    public boolean last(){
        return current.next == null;
    }
    public boolean full() {
        return false; // LinkedList is never full
    }
    public void findFirst() {
        current = head;
    }
    public void findNext() {
        current = current.next; 
    }
    public void update(T data) {
        current.data = data;
    }
    public void insert(T data) {
        Node<T> tmp;
        if (empty()) {
            head = current = new Node<T>(data);
        } else {
            tmp = current.next;
            current.next = new Node<T>(data);
            current = current.next;
            current.next = tmp;
        }
    }
    public void remove() {
        if (current == head) {
            head = head.next;
        } else {
            Node<T> tmp = head;
            while (tmp.next != current) {
                tmp = tmp.next;
            }
            tmp.next = current.next;
        }
        if (current.next == null) {
            current = head;
        } else {
            current = current.next;
        }
    }
    
    
}
