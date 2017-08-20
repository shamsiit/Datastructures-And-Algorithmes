package DS;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;
	
	private class Node{
		private Key key;
		private Value value;
		private Node left,right;
		private int size;
		
		public Node(Key key,Value value,int size){
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}
	
	public BST() {
    }
	
	public boolean isEmpty() {
        return size() == 0;
    }
	
	public int size() {
        return size(root);
    }
	
	private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }
	
	public Value get(Key key){
		return get(root,key);
	}
	
	private Value get(Node x,Key key){
		if(key == null) throw new IllegalArgumentException("called get() with a null key");
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0 ) return get(x.left,key);
		else if(cmp > 0) return get(x.right,key);
		else return x.value;
	}
	
	public void put(Key key,Value value){
		if(key == null) throw new IllegalArgumentException("calledput() with a null key");
		if(value == null){
			delete(key);
			return;
		}
		root = put(root,key,value);
	}
	
	private Node put(Node x,Key key,Value value){
		
		if(x == null) return new Node(key,value,1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(x.left,key,value);
		else if(cmp > 0) x.right = put(x.right,key,value);
		else x.value = value;
		
		x.size = 1 + size(x.left) + size(x.right);
		
		return x;
		
	}
	
	public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
	
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with a null key");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    
	public Key min(){
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}
	
	private Node min(Node x){
		if(x.left == null) return x;
		else return min(x.left);
	}
	
	public Key max(){
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return max(root).key;
	}
	
	private Node max(Node x){
		if(x.right == null) return x;
		else return max(x.right);
	}
	
	public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    } 

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    }
	
	public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    }
    
    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     *
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    } 

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    }
    
    /**
     * Return the kth smallest key in the symbol table.
     *
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k. 
    private Node select(Node x, int k) {
        if (x == null) return null; 
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    }
    
    public Iterable<Key> keys(){
    	Queue<Key> q = new Queue<Key>();
    	inorder(root,q);
    	return q;
    }
    
    private void inorder(Node x,Queue<Key> q){
    	if(x == null) return;
    	inorder(x.left,q);
    	q.enqueue(x.key);
    	inorder(x.right,q);
    }
	
    public static void main(String args[]){
    	BST<String, Integer> st = new BST<String, Integer>();
    	st.put("S", 1);
    	st.put("E", 2);
    	st.put("L", 3);
    	st.put("B", 4);
    	st.put("N", 5);
    	st.put("C", 6);
    	st.put("A", 17);
    	for (String s : st.keys()){
            System.out.println(s+"   "+st.get(s));
    	}
    }
	
}
