/*
non-directional, non-weighted, cyclic graph
 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

    private Map<T, Set<T>> adjacentList = new HashMap<>();
    private int noOfNodes;

    public void addNode(T t) {
        if(!adjacentList.containsKey(t)) {
            adjacentList.put(t, null);
            noOfNodes++;
        }
    }

    public void addEdge(T t1, T t2) {
        if(t1 == t2)
            return;
        adjacentList.computeIfAbsent(t1, s -> new HashSet<>());
        adjacentList.get(t1).add(t2);
        adjacentList.computeIfAbsent(t2, s -> new HashSet<>());
        adjacentList.get(t2).add(t1);
    }

    public void print() {
        for(Map.Entry<T, Set<T>> entry : adjacentList.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public int size() {
        return noOfNodes;
    }
}
