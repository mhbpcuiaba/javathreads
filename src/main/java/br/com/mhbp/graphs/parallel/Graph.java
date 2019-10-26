package br.com.mhbp.graphs.parallel;

import java.util.List;
import java.util.Stack;

public class Graph {

    private Integer nNodes;
    private int[][] adjacencyMatrix;
    private Stack<Integer> globalStack;
    private List<Stack<Integer>> localStacks;
    private boolean[] visited;
    private boolean isDone;
    private int counter;

}
