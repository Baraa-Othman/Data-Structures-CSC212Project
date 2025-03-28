public class BSTNode <T> {
    public int key;
    public T data;
    public BSTNode<T> left, right;

    public BSTNode(int k,T data) {
        key =k;
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public BSTNode(int k, T data, BSTNode<T> left, BSTNode<T> right) {
        key = k;
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
}
