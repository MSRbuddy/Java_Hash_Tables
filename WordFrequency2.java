import java.util.LinkedList;

class MyMapNode2<K, V> {
    private K key;
    private V value;

    public MyMapNode2(K key, V value) {
        this.key = key;
        this.value = value;
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
}

public class WordFrequency2 {
    private LinkedList<MyMapNode2<String, Integer>>[] hashTable;
    private int numBuckets;

    public WordFrequency2(int numBuckets) {
        this.numBuckets = numBuckets;
        this.hashTable = new LinkedList[numBuckets];

        // Initialize the hash table with empty linked lists
        for (int i = 0; i < numBuckets; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(String word) {
        int hashCode = Math.abs(word.hashCode());
        return hashCode % numBuckets;
    }

    public void addWord(String word) {
        int bucketIndex = getBucketIndex(word);
        LinkedList<MyMapNode2<String, Integer>> bucket = hashTable[bucketIndex];

        // Search for the word in the bucket
        MyMapNode2<String, Integer> node = searchWord(bucket, word);

        if (node == null) {
            // Word not found in the bucket, create a new node and add it to the bucket
            MyMapNode2<String, Integer> newNode = new MyMapNode2<>(word, 1);
            bucket.add(newNode);
        } else {
            // Word found in the bucket, increment its frequency
            int frequency = node.getValue();
            node.setValue(frequency + 1);
        }
    }

    private MyMapNode2<String, Integer> searchWord(LinkedList<MyMapNode2<String, Integer>> bucket, String word) {
        for (MyMapNode2<String, Integer> node : bucket) {
            if (node.getKey().equals(word)) {
                return node;
            }
        }
        return null;
    }

    public void printWordFrequency() {
        for (LinkedList<MyMapNode2<String, Integer>> bucket : hashTable) {
            if (!bucket.isEmpty()) {
                for (MyMapNode2<String, Integer> node : bucket) {
                    System.out.println(node.getKey() + ": " + node.getValue());
                }
            }
        }
    }

    public static void main(String[] args) {
        String paragraph = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
        String[] words = paragraph.toLowerCase().split("\\s+");

        WordFrequency2 wordFrequency = new WordFrequency2(words.length);

        for (String word : words) {
            wordFrequency.addWord(word);
        }

        System.out.println("Word frequency in the paragraph:");
        wordFrequency.printWordFrequency();
    }
}