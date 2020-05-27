package br.com.mhbp.threads.phaser;

import java.util.*;

public class SynchronizedCollections {

    public static void main(String[] args) {
        Vector<Object> objects = new Vector<>();
        Stack stack = new Stack();
        Collection<Object> objects1 = Collections.synchronizedCollection(new ArrayList<>());
        Collection collection = Collections.synchronizedCollection(new LinkedList());

        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
    }


    public static <E> boolean addIfAbsent(Collection<E> collection, E e) {

        synchronized(collection) {

            if (!collection.contains(e)) {
                return collection.add(e);

            }

            return false;
        }
    }
}
