import java.util.*;

class MyMapNode1<K, V> {
    private K key;
    private V value;

    public MyMapNode1(K key, V value) {
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

public class WordFrequency1 {
    private LinkedList<MyMapNode1<String, Integer>> hashTable;
    private int numBuckets;

    public WordFrequency1(int numBuckets) {
        this.numBuckets = numBuckets;
        this.hashTable = new LinkedList<>();

        // Initialize the hash table with empty linked lists
        for (int i = 0; i < numBuckets; i++) {
            hashTable.add(null);
        }
    }

    private int getBucketIndex(String word) {
        int hashCode = Math.abs(word.hashCode());
        return hashCode % numBuckets;
    }

    public void addWord(String word) {
        int bucketIndex = getBucketIndex(word);
        LinkedList<MyMapNode1<String, Integer>> bucket = hashTable.get[bucketIndex];

        if (bucket == null) {
            // No words in the bucket yet, create a new node and add it to the bucket
            MyMapNode1<String, Integer> newNode = new MyMapNode1<>(word, 1);
            bucket = new LinkedList<>();
            bucket.add(newNode);
            hashTable.set(bucketIndex, bucket);
        } else {
            // Search for the word in the bucket
            MyMapNode1<String, Integer> node = searchWord(bucket, word);

            if (node == null) {
                // Word not found in the bucket, create a new node and add it to the bucket
                MyMapNode1<String, Integer> newNode = new MyMapNode1<>(word, 1);
                bucket.add(newNode);
            } else {
                // Word found in the bucket, increment its frequency
                int frequency = node.getValue();
                node.setValue(frequency + 1);
            }
        }
    }

    private MyMapNode1<String, Integer> searchWord(LinkedList<MyMapNode1<String, Integer>> bucket, String word) {
        for (MyMapNode1<String, Integer> node : bucket) {
            if (node.getKey().equals(word)) {
                return node;
            }
        }
        return null;
    }

    public void printWordFrequency() {
        for (LinkedList<MyMapNode1<String, Integer>> bucket : hashTable) {
            if (bucket != null) {
                for (MyMapNode1<String, Integer> node : bucket) {
                    System.out.println(node.getKey() + ": " + node.getValue());
                }
            }
        }
    }

    public static void main(String[] args) {
        String sentence = "To be or not to be";
        String[] words = sentence.toLowerCase().split("\\s+");

        WordFrequency1 wordFrequency = new WordFrequency1(words.length);

        for (String word : words) {
            wordFrequency.addWord(word);
        }

        System.out.println("Word frequency in the sentence:");
        wordFrequency.printWordFrequency();
    }
}