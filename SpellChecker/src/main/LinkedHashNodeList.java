package main;

/**
* This class models the state and behavior of a Linked List of
* Hash Entries
* @author Matthew F. Leader
*/
public class LinkedHashNodeList<K, V> {

	/** the first element in the collection */
    private HashNode front;
    /** size of the list */
    private int size;
    /** the number of word comparisons made in this bucket */
    private int numProbes;

    /**
     * Construct a Linked List
     */
    public LinkedHashNodeList() {
    	front = null;
        size = 0;
        numProbes = 0;
    }

    /**
     * Validates whether or not the list is empty
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Accessor for the list's size 
     * @return the current value of the list's size
     */
    public int size() {
    	return size;
    }

    /**
     * Add an item to the front, or if it is already on the list, then move
     * element the containing value equal to the parameter to the front.
     * @param key
     * 				the key associated with hash node to add
     * @param value
     *              the value to add to the list
     */
    public void add(K key, V value) {
    	if (front == null) {
    		front = new HashNode(key, value, null);
    	} else {
    		front = new HashNode(key, value, front);
    	}
        size++;
    }

    /**
     * Find a value on the list
     * @param key
     *  				the value to look for on the list
     * @return if the value is on the list, then return the value,
     * otherwise return null 
     */
    public V find(K key) {    	
    	for (HashNode k = front; k != null; k = k.next) {
    		numProbes++;
    		if (k.key.equals(key)) {
    			return k.value;
    		}
    	}
    	return null;
    }
    
    /**
     * Resets number of probes
     */
    public void deProbe() {
    	numProbes = 0;
    }
    
    /**
     * 
     * @return number of probes in this bucket
     */
    public int getProbed() {
    	return numProbes;
    }
    
    /**
     * Remove a Node with the given value, if it exists
     * @param key
     * 			the value to remove from the list
     * @return the Node removed from the list
     */
    public V remove(K key) {
    	HashNode current = front;
    	HashNode previous = null;
    	while (current != null && !current.key.equals(key)) {
    		previous = current;
    		current = current.next;
    	}
    	if (current != null) {
    		if (current == front) {
    			front = front.next;
    		} else {
    			previous.next = current.next;
    		}
    		size--;
    		return current.value;
        }
    	return null;
    }

        /**
         * Convert the Linked List to an array
         * @return an array of the objects on the List
         */
        @SuppressWarnings("unchecked")
		public V[] toArray() {
            V[] array = null;
            if (!isEmpty()) {
                array = (V[]) new Object[size];
                int k = 0;
                HashNode current = front;
                while (k < size && current != null) {
                    array[k] = current.value;
                    k++;
                    current = current.next;
                }
            }
            return array;
        }

        /**
         * Access the value for a Node at a given index on the list
         * @param index
         * 					the position on the list
         * @return the Node at the index on the list
         */
        public V get(int index) {
            HashNode current = front;
            for (int k = 0; k < index; k++) {
                current = current.next;
            }
            if (current != null) {
            	return current.value;
            }
            return null;
        }


        /**
         * This class models the state and behavior of a Node in the
         * LinkedList.
         */
        private class HashNode {

            /** the value within the element */
    		private V value;
    		/** the key for this node */
    		private K key;
    		/** a reference to the next element in the list */
    		private HashNode next;

            /**
             * Constructs a Node given value and a pointer to the next element.
             * @param value
             *              the value in this element
             * @param next
             *              the pointer to the next element
             */
            public HashNode(K key, V value, HashNode next) {
            	this.key = key;
                this.value = value;
                this.next = next;
            }

            /**
             * Constructs a Node with null pointers
             * @param value
             *              the value in this node
             * @param key
             * 				the associated key for this node's value
             */
            public HashNode(K key, V value) {
                this(key, value, null);
            }
            
            /**
             * 
             * @return this HashNode's value
             */
            public V value() {
            	return value;
            }
            
            /**
             * 
             * @return this HashNode's key
             */
            public K key() {
            	return key;
            }
        }
    

}
