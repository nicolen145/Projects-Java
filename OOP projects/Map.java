package assignment9;

/**
 * A map implementation using a binary search tree.
 * @param <T> the type of keys maintained by this map, which must be comparable
 * @param <E> the type of mapped values
 */
public class Map<T extends Comparable<T>, E> {

    // Constructor to initialize the BinarySearchTree for storing key-value pairs
    public Map() {
        this.bst = new BinarySearchTree<>();
    }

    // Method to add a key-value pair to the map
    public boolean add(T key, E value) {
        Tuple<T, E> tuple = new Tuple<>(key, value); // Create a new tuple with the key-value pair
        if (tuple.key != null && tuple.value != null) // Check if valid tuple
            return bst.add(tuple); // Add the tuple to the BinarySearchTree
        return false; //if not valid
    }

    // Method to get the value associated with a key
    public E get(T key) {
        Tuple<T, E> tuple = new Tuple<>(key, null); // Create a tuple with the key and null value
        Tuple<T, E> result = bst.get(tuple); // Get the corresponding tuple from the BinarySearchTree
        return result == null ? null : result.value; // Return the value if the tuple is found, otherwise return null
    }

    // Method to get the number of key-value pairs in the map (number of nodes in bst member)
    public int size() {
        return bst.size(); // Return the size of the BinarySearchTree
    }

    // Method to check if a key exists in the map
    public boolean contains(T key) {
        Tuple<T, E> tuple = new Tuple<>(key, null); // Create a tuple with the key and null value
        return bst.contains(tuple); // Check if the tuple exists in the BinarySearchTree
    }

    // BinarySearchTree to store key-value pairs
    private BinarySearchTree<Tuple<T, E>> bst;

    /**
     * A key-value tuple used to store entries in the binary search tree.
     *
     * @param <T> the type of the key, which must be comparable
     * @param <E> the type of the value
     */
    private static class Tuple<T extends Comparable<T>, E> implements Comparable<Tuple<T, E>> {
        T key; // Key of the tuple
        E value; // Value of the tuple

        // Constructor to initialize the tuple with a key and value
        Tuple(T key, E value) {
            this.key = key;
            this.value = value;
        }

        // Method to compare two tuples based on their keys
        @Override
        public int compareTo(Tuple<T, E> other) {
            return this.key.compareTo(other.key);
        }
    }
}
