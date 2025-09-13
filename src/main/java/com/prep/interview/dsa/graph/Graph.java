package com.prep.interview.dsa.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph implements using Map<T,List<T>>
 */
public class Graph<T> {
    private final Map<T, List<T>> mapList = new HashMap<>();

    public void addVertex(T vertex) {
        mapList.putIfAbsent(vertex, new ArrayList<>()); //A,B
    }

    public void addEdge(T vertex1, T vertex2) {
        if (mapList.get(vertex1) != null && mapList.get(vertex2) != null) {
            mapList.get(vertex1).add(vertex2);//A,[B]
            mapList.get(vertex2).add(vertex1);//B,[A]
        }
    }

    public void removeEdge(T vertex1, T vertex2) {
        if (mapList.get(vertex1) != null && mapList.get(vertex2) != null) {
            mapList.get(vertex1).remove(vertex2);//A,[B] => A,[]
            mapList.get(vertex2).remove(vertex1);//B,[A] => B,[]
        }
    }

    public void removeVertex(T vertex) {
        if (mapList.get(vertex) == null) return;
        for (T oldVertex : mapList.get(vertex)) {
            mapList.get(oldVertex).remove(vertex); //remove from list
        }
        mapList.remove(vertex); //remove from map
    }

    public void printGraph() {
        System.out.println(mapList);
    }

}
