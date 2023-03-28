import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class HashTableOpenAddressing<K, V> extends Dictionary<K,V>{

    private int capacity;  // size of the table
    private int size;  // number of elements in the table
    private int previousPrime; //store prev prime so that it is not calculated again and again in double hashing.
    private int mode;

    public static int LINEARPROBING = 1;
    public static int QUADRATICPROBING = 2;
    public static int DOUBLEHASHING = 3;
    private double loadFactor;
    private Entry<K, V>[] table;

    public HashTableOpenAddressing() {
        this(DOUBLEHASHING, 11, 0.75);  // default initial capacity of 11
    }

    public HashTableOpenAddressing(int mode) {
        this( mode, 11, 0.75);
    }

    public HashTableOpenAddressing(int capacity, double loadFactor) {
        this(DOUBLEHASHING, capacity, loadFactor);
    }

    /*
    TODO:
    This constructor takes a mode, capacity, loadFactor, and sets those variables + relevant variables
    according to such. Additionally, it will set up the table according to the capacity.
    If the mode is DOUBLEHASHING, please calculate the previousPrime and set it.
     */
    public HashTableOpenAddressing(int mode, int capacity, double loadFactor) {
        this.mode = mode;
        if (mode == 3){
            this.previousPrime = previousPrime(capacity - 1);
        }
        this.capacity = capacity;
        this.loadFactor = loadFactor;

        table = new Entry[capacity];
        for (int i = 0; i < capacity; i++){
            table[i] = null;
        }

    }

    private int previousPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number--;
        }
    }


    // TODO:
    //  second hash should be prevPrime - (key % prevPrime)...shouldn't be negative
    private int hash2(K key) {
        //System.out.println(previousPrime + " - (" + key.hashCode() + " % " + previousPrime + ") = " + abs(previousPrime - (key.hashCode() % previousPrime)));
        return abs(previousPrime - (key.hashCode() % previousPrime));}


    // TODO: gets the next index given the index and the offset. Please take into account the mode.
    // Remake this, do not make recursive, just get the next index.
    private int getNextIndex(K key, int offset) {
        int index = 0;

        if (mode == 1){
            index = abs(hash(key) + offset);
        } else if (mode == 2){
            index = abs(hash(key) + (offset * offset));
        } else if (mode == 3){
            index = hash(key) + (offset * hash2(key));
        }

        return index % capacity;
    }

    // TODO:
    //  Put a key, value pair into the table.
    //  If the key already exists/inactive, override it. Else, put it into the table.
    //  Throw a RuntimeException if there is an infinite loops.
    public void put(K key, V value) {

        int index = hash(key);
        int firstIndex = index;
        int offset = 1;

        while (table[index] != null){
            if (!table[index].isActive){
                break;
            } else if (table[index].key.equals(key)){
                table[index].setValue(value);
                return;
            } else {
                index = getNextIndex(key, offset);
                offset++;

                if (firstIndex == index){ // check for infinite loop
                    throw new RuntimeException();
                }
            }
        }

        // insert value
        size++;
        table[index] = new Entry(key, value);

        // resize if needed
        if (size > loadFactor * capacity){ // check for resize
            resize();
        }
    }

    // TODO:
    //  Retrieves the value of a key in the table.
    //  If there is an infinite loop, throw a RuntimeException.
    //  Return null if not there.
    public V get(K key) {
        if (!containsKey(key)){
            return null;
        } else {
            int index = hash(key);
            int firstIndex = index;
            int offset = 0;
            while (table[index] != null){

                if (table[index].key.equals(key)){
                    if (table[index].isActive){
                        return table[index].value;
                    } else {
                        return null;
                    }
                } else {
                    offset++;
                    index = getNextIndex(key, offset);

                    if (firstIndex == index){
                        throw new RuntimeException();
                    }
                }
            }
        }
        return null;
    }

    // TODO: Searches the table to see if the key exists or not.
    public boolean containsKey(K key) {
        int index = hash(key);
        int firstIndex = index;
        int offset = 0;
        while (table[index] != null){
            if (table[index].key.equals(key)){
                if (table[index].isActive){
                    return true;
                } else {
                    return false;
                }
            } else {
                offset++;
                index = getNextIndex(key, offset);

                if (firstIndex == index){
                    return false;
                }
            }
        }
        return false;
    }

    // TODO:
    //  Set the key as inactive if it exists in the table. Return true.
    //  If there is no key, return false.
    //  If there's an infinite loop, throw a RuntimeException.
    public boolean remove(K key) {
        if (!containsKey(key)){
            return false;
        } else {
            int index = hash(key);
            int firstIndex = index;
            int offset = 0;

            while (table[index] != null){
                if (table[index].key.equals(key)){ // if keys match

                    if (table[index].isActive){ // if not already deleted, then delete
                        table[index].setActive(false);
                        return true;
                    } else { // if already deleted, do nothing and return false
                        return false;
                    }
                } else { // get new index and check for infinite loop.
                    offset++;
                    index = getNextIndex(key, offset);

                    if (firstIndex == index){
                        throw new RuntimeException();
                    }
                }
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // TODO:
    //  Calculate the absolute hash of the key. Do not overthink this.
    private int hash(K key) {
        return abs(key.hashCode()) % capacity;
    }


    private boolean isPrime(int number) {
        for( int i = 2; i <= number / 2; i++ ) {
            if( number % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number++;
        }
    }

    // TODO:
    //  Set the capacity to the nextPrime of the capacity doubled.
    //  Calculate the previousPrime and set up the new table with the old tables'
    //  contents now hashed to the new.
    private void resize() {
        this.size = 0;
        int tempCapacity = capacity;
        Entry<K, V>[] temp = table.clone();
        this.capacity = nextPrime(capacity * 2);
        this.previousPrime = previousPrime(capacity - 1);
        table = new Entry[capacity];

        for (int i = 0; i < capacity; i++){
            table[i] = null;
        }

        for (int i = 0; i < tempCapacity; i++){
            if (temp[i] != null) {
                K key = temp[i].key;
                V value = temp[i].value;
                put(key, value);
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;

        for (Entry<K, V> entry : table) {
            sb.append(index + ": ");
            index++;
            if (entry != null) {
                sb.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            sb.append(";");
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    private class Entry<K, V> {
        private K key;
        private V value;

        private boolean isActive;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            isActive = true;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean getActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
    }

    public static void main(String []args ) {
        HashTableOpenAddressing<Integer, Integer> hashTable = new HashTableOpenAddressing<>(QUADRATICPROBING, 11, 0.75);

        hashTable.put(2,2);
        for (int i = 0; i < 280; i += 10) {
            hashTable.put(i, i);
            //System.out.println(hashTable);
        }
        hashTable.remove(0);
        System.out.println(hashTable);

    }

}
