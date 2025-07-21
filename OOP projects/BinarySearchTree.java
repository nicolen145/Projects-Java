package assignment9;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A binary search tree implementation.
 * @param <T> the type of elements maintained by this tree, which must be comparable
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root; // Root node of the binary search tree
    private int size; // New parameter of number of nodes in the tree

    // Constructor to initialize an empty tree
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    // Method to return the number of nodes in the tree
    public int size() {
        return size;
    }

    // Method to get the root node of the tree
    public Node<T> getRoot() {
        return this.root;
    }

    // Method to check if a value exists in the tree
    public boolean contains(T value) {
        return get(value) != null;
    }

    // Method to retrieve a value from the tree
    public T get(T value) {
        if (value != null) {
            Node<T> current = root;
            while (current != null) {
                int cmp = value.compareTo(current.getValue());
                if (cmp < 0) { //if value < current value go left
                    current = current.getLeft();
                } else if (cmp > 0) {
                    current = current.getRight(); //if value > current value go right
                } else {
                    return current.getValue(); // value found
                }
            }
        }
        return null; // value not found
    }

    // Method to return an in-order iterator
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    // Method to add a value to the tree
    public boolean add(T value) {
        if (value != null) {
            if (root == null) { //if tree is empty
                root = new Node<>(value); // Add value as root if tree is empty
                size++;
                return true;
            }
            //if not empty
            Node<T> parent = null;
            Node<T> current = root;
            while (current != null) { //while we didn't reach a leaf
                int cmp = value.compareTo(current.getValue());
                if (cmp < 0) { //if value < current value go left
                    parent = current;
                    current = current.getLeft();
                } else if (cmp > 0) { //if value > current value go right
                    parent = current;
                    current = current.getRight();
                } else {
                    return false; // Duplicate value, not added
                }
            }
            //we reach a leaf:
            if (value.compareTo(parent.getValue()) < 0) {
                parent.left = new Node<>(value); // Add value as left child
            } else {
                parent.right = new Node<>(value); // Add value as right child
            }
            size++;
            return true;
        }
        else
            return false;
    }

    /**
     * A node in the binary search tree.
     * @param <T> the type of the value held by this node
     */
    public static class Node<T> {

        private Node<T> right; // Right child
        private Node<T> left; // Left child
        private T value; // Value of the node

        // Constructor to initialize a node with a value
        Node(T value) {
            this.value = value;
            this.right = null;
            this.left = null;
        }

        // Method to get the right child
        public Node<T> getRight() {
            return right;
        }

        // Method to get the left child
        public Node<T> getLeft() {
            return left;
        }

        // Method to get the value of the node
        public T getValue() {
            return value;
        }
    }

    /**
     * An iterator that provides in-order traversal of the binary search tree.
     * @param <T> the type of elements returned by this iterator
     */
    public static class InOrderIterator<T> implements Iterator<T> {
        private Stack<Node<T>> stack = new Stack<>(); // Stack to store nodes

        // Constructor to initialize the iterator with the root node
        InOrderIterator(Node<T> root) {
            pushLeft(root);
        }

        // Helper method to push all left children to the stack
        private void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        // Method to check if there are more nodes to visit
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Method to return the next node in the traversal
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> node = stack.pop();
            T result = node.getValue();
            if (node.getRight() != null) {
                pushLeft(node.getRight());
            }
            return result;
        }
    }
}
