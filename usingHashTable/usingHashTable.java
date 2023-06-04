package usingHashTable;

public class usingHashTable {
    public static void main(String[] args) {
        //релизовать вызов
    }
    
    public class HashTable<K,V> {
        private static final int INIT_BASKET_COUNT = 16;
                
        private Basket[] baskets;

        private int size = 0;   //счетчик узлов baskets

        private final double loadFactor = 0.75;
 
        private class Entity {
            private K key;
            private V value;

            public Entity(K key, V value){
                this.key = key;
                this.value = value;
            }
        }
    
        private class Basket {                        
            private class Node {
                private Entity entity;
                private Node next;    
                public Node(K key, V value){
                    entity = new Entity(key, value);
                }
            }
            private Node head;

            public V find(K key){
                Node currentNode = head;
                while(currentNode != null){
                    if(currentNode.entity.key.equals(key))
                        return currentNode.entity.value;
                    currentNode = currentNode.next;
                }
                return null;                
            }

            public boolean add(K key, V value){
                Node currentNode = head;
                Node node = new Node(key, value);
                while(currentNode != null){
                    if(currentNode.entity.key.equals(key)){
                        return false;
                    }else if(currentNode.next == null){
                        currentNode.next = node;
                        return true;
                    }                        
                    currentNode = currentNode.next;
                }   
                head = node;
                return true;                 
            }  
            public boolean remove(K key){
                Node currentNode = head;
                if(currentNode == null) 
                    return false;
                else if(currentNode.entity.key.equals(key)){ 
                    head = head.next;
                    return true;
                }
                while(currentNode.next!= null){
                    if(currentNode.next.entity.key.equals(key)){
                        currentNode.next = currentNode.next.next;    
                        return true;
                    }
                    currentNode = currentNode.next;
                }
                return false;
            }                   
        }

        // constructors 
        public HashTable(int initSize) {
            baskets = (Basket[]) new Object[initSize];
        }
        public HashTable() {
            this(INIT_BASKET_COUNT);
        }
        
        private int calculateBasketIndex(K key) {
            return Math.abs(key.hashCode()) % baskets.length;
        }

        //Поиск по ключу в хэш-таблице
        public V find(K key){
            Basket basket =   baskets[calculateBasketIndex(key)];        
            return (basket != null) ? basket.find(key) : null;
        }

        public boolean add(K key, V value){
            if(loadFactor*baskets.length > size)
                resize();
            int calculatedIndex = calculateBasketIndex(key);
            Basket basket =   baskets[calculatedIndex];        
            if(basket == null){
                baskets[calculatedIndex] = new Basket();
            } 
            boolean valueAdded = baskets[calculatedIndex].add(key, value);
            if(valueAdded) size++;
            return valueAdded;
        }

        public boolean remove(K key){
            int calculatedIndex = calculateBasketIndex(key);
            Basket basket =   baskets[calculatedIndex];   

            //return (basket != null) ? basket.remove(key) : false;    
            if(basket == null){
                return false;
            }
            boolean valueRemoved = basket.remove(key);
            if(valueRemoved) size--;
            return valueRemoved;
        }

        private void resize(){
            Basket[] old = baskets;
            baskets = (Basket[]) new Object[old.length *2];
            size = 0;
            for (Basket basket : old){
                if(basket != null){
                    Basket.Node node = basket.head; 
                    while(node != null){
                        add(node.entity.key, node.entity.value);
                        node = node.next;
                    }
                }
            } 
        }
    }
}
