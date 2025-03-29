
public class BST<T>{
    private BSTNode<T> root,current;


    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public T retrieve() {
            return current.data;     
    }
// modified to add the key
    public boolean update(int key, T val) {
        removeKey(current.key);
        return insert(key, val);
    }
// modified to add the key
    public boolean insert( int k, T val){
       BSTNode<T> p, q = current;
       if (findkey(k)){
        current = q;
        return false;
       }
       p = new BSTNode<>(k, val);
       if (empty()) {
           root = current = p;
           return true;
       } else {
           if (k < current.key) {
               current.left = p;
           } else {
               current.right = p;
           }
           current = p;
           return true;
       }
    }

    public void deleteSubtree() {
        if (current == root)
             {current=root=null;}
        else{
            BSTNode<T> p = current;
            find(Relative.Parent);
            if (current.left == p) {
                current.left = null;
            } else {
                current.right = null;
            }
            current = root;
        }
    }

    public boolean find(Relative rel) {
        switch (rel) {
            case Root:
                current = root;
                return true;
            case Parent:
                if (current == root) {
                    return false;
                }
                current = findParent(current, root);
                return true;
            case LeftChild:
                if (current.left == null) {
                    return false;
                }
                current = current.left;
                return true;
            case RightChild:
                if (current.right == null) {
                    return false;
                }
                current = current.right;
                return true;
            default:
               return false; 
        }
    
    }




    private BSTNode<T> findParent(BSTNode<T> p, BSTNode<T> t) {
        if (t == null) {
            return null;
        }
        if (t.left == null && t.right == null) {
            return null;
        }
        else if (t.left == p || t.right == p) {
            return t;
        }
        else {
            BSTNode<T> q = findParent(p, t.left);
            if (q != null) {
                return q;
            }
            else return findParent(p, t.right);
        }
    }
    public boolean findkey(int tkey){
        BSTNode<T> p = root, q=root;
        if (empty()) {
            return false;
        }
        while (p != null) {
            q=p;
            if (tkey == p.key) {
                current = p;
                return true;
            } else if (tkey < p.key) {
                p = p.left;
            } else {
                p = p.right;
            }
            
            
        }
        current = q;
         return false;

    }

    public boolean removeKey (int k){
        int k1 = k;
        BSTNode<T> p = root, q = null;
        while (p != null) {
            if (k1 < p.key) {
                q = p;
                p = p.left;
            } else if( k1 > p.key) {
                q = p;
                p = p.right;
            }
            else {
                if ((p.left == null) && (p.right == null)) {
                    BSTNode<T> min = p.right;
                    q=p;
                    while (min.left != null) {
                        q = min;
                        min = min.left;
                    }
                    p.key = min.key;
                    p.data = min.data;
                    k1 = min.key;
                    p = min;
                }
                if (p.left != null){
                    p=p.left;
                } else {
                    p = p.right;
                }
                if (q == null) {
                    root = p;
                } else {
                    if (k1 < q.key) {
                        q.left = p;
                    } else {
                        q.right = p;
                    }
                }
                current = root;
                return true;
            }
        }
        return false;
		
	}
    
    public void Traverse(Order ord) {
        if (root == null) {
            return; 
        }
    
        switch (ord) {
            case preOrder:
                preOrder(root);
                break;
            case inOrder:
                inOrder(root);
                break;
            case postOrder:
                postOrder(root);
                break;
            default:
                break;
        }
    }
    
    private void preOrder(BSTNode<T> node) {
        if (node != null) {
            System.out.println(node.data); 
            preOrder(node.left); 
            preOrder(node.right); 
        }
    }
    
    private void inOrder(BSTNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.data); 
            inOrder(node.right); 
        }
    }
    
    private void postOrder(BSTNode<T> node) {
        if (node != null) {
            postOrder(node.left); 
            postOrder(node.right); 
            System.out.println(node.data); 
        }
    }





}



    
