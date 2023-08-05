package DSA;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapCodeExtended {
    static class HashMapDesign<K, V1, V2> {
        private class Node {
            K key;
            V1 value1;
            V2 value2;

            public Node(K key, V1 value1, V2 value2) {
                this.key = key;
                this.value1 = value1;
                this.value2 = value2;
            }
        }

        private int n; // Number of nodes
        private int N; // Number of buckets
        private LinkedList<Node>[] buckets; // Buckets to store linked lists of nodes

        @SuppressWarnings("unchecked")
        public HashMapDesign() {
            this.N = 4;
            this.buckets = new LinkedList[4];
            for (int i = 0; i < 4; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        private int hashFunction(K key) {
            int bi = key.hashCode();
            return Math.abs(bi) % N;
        }

        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];
            for (int i = 0; i < ll.size(); i++) {
                if (ll.get(i).key.equals(key)) {
                    return i;
                }
            }
            return -1;
        }

        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<Node>[] oldBucket = buckets;
            buckets = new LinkedList[N * 2];
            for (int i = 0; i < N * 2; i++) {
                buckets[i] = new LinkedList<>();
            }
            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.get(j);
                    put(node.key, node.value1, node.value2);
                }
            }
        }

        public void put(K key, V1 value1, V2 value2) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                buckets[bi].add(new Node(key, value1, value2));
                n++;
            } else {
                Node node = buckets[bi].get(di);
                node.value1 = value1;
                node.value2 = value2;
            }
            double lambda = (double) n / N;
            if (lambda > 2.0) {
                rehash();
            }
        }

        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            return di != -1;
        }

        public V1 getValue1(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                return null;
            }
            return buckets[bi].get(di).value1;
        }

        public V2 getValue2(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                return null;
            }
            return buckets[bi].get(di).value2;
        }

        public void remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di != -1) {
                buckets[bi].remove(di);
                n--;
            }
        }

        public static void main(String args[]) {
            HashMapDesign<String, Integer, Double> map = new HashMapDesign<>();
            map.put("India", 190, 1.23);
            map.put("China", 200, 4.56);
            map.put("US", 50, 7.89);

            System.out.println("Value1 for India: " + map.getValue1("India")); // Output: Value1 for India: 190
            System.out.println("Value2 for China: " + map.getValue2("China")); // Output: Value2 for China: 4.56

            System.out.println("Contains key 'US': " + map.containsKey("US")); // Output: Contains key 'US': true
            System.out.println("Contains key 'Canada': " + map.containsKey("Canada")); // Output: Contains key 'Canada': false

            map.remove("India");
            System.out.println("Value1 for India after removal: " + map.getValue1("India")); // Output: Value1 for India after removal: null
        }
    }

    public static void main(String[] args) {
        HashMapDesign<String, Integer, Double> map = new HashMapDesign<>();
        map.put("India", 190, 1.23);
        map.put("China", 200, 4.56);
        map.put("US", 50, 7.89);

        System.out.println("Value1 for India: " + map.getValue1("India")); // Output: Value1 for India: 190
        System.out.println("Value2 for China: " + map.getValue2("China")); // Output: Value2 for China: 4.56

        System.out.println("Contains key 'US': " + map.containsKey("US")); // Output: Contains key 'US': true
        System.out.println("Contains key 'Canada': " + map.containsKey("Canada")); // Output: Contains key 'Canada': false

        map.remove("India");
        System.out.println("Value1 for India after removal: " + map.getValue1("India")); // Output: Value1 for India after removal: null
    }
}
