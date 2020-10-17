import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	
	//adjacency list
	ArrayList<ArrayList<Integer>> matrix;
	ArrayList<String> names = new ArrayList<String>();
	
	/*
	 * Default no-argument constructor
	 */ 
	public Graph() {   //don't I need the amount of vertices??? So I can create a list that long?
						//right now you are creating a arraylist of arraylist with
	}

	/**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void addVertex(String vertex) {
		
		if(vertex.equals(null)) {  //checks if null
			return;
		}
		
		for(int i = 0; i < names.size(); i++) {  //adds name. 
				if(names.get(i).equals(vertex)){ 
				return;
		        }
		}
		
		names.add(vertex); //adds name/vertex to a specific index. 
		
	}

	/**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void removeVertex(String vertex) {
		
		if(vertex.equals(null)) {
			return; 
		}
		
		if(!names.contains(vertex)) {
			return;
		}

		int j = names.indexOf(vertex);
		names.remove(j);  //removes the name of the vertex from the name arraylist
		matrix.remove(j);  //removes the vertex and its edges from the graph
	}

	/**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * add vertex, and add edge, no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {

		if( vertex1.equals(null) || vertex2.equals(null)) { //checks if null
			return;
		}
		
		if(!(names.contains(vertex1)) || !(names.contains(vertex2))) {
			return;   //checks if contains 
		}
		
		int i = names.indexOf(vertex1);  //makes the strings integers 
		int j = names.indexOf(vertex2);
		
		if(matrix.get(j).get(i) == 1) { //checks if already edge. 
			return;
		}
		
		else {  //sets edge.
		matrix.get(j).set(i, 1);
		}

	}
	
	/**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     */
	public void removeEdge(String vertex1, String vertex2) {

		if( vertex1.equals(null) || vertex2.equals(null)) { //checks if null
			return;
		}
		
		if(!(names.contains(vertex1)) || !(names.contains(vertex2))) {
			return;   //checks if contains 
		}
		
		int i = names.indexOf(vertex1);  //makes the strings integers 
		int j = names.indexOf(vertex2);
		
		if(matrix.get(j).get(i) != 1) { //checks if already edge. 
			return;
		}
		
		else {  //sets edge.
		matrix.get(j).set(i, 0);
		}
	}	

	/**
     * Returns a Set that contains all the vertices
     * 
	 */
	public Set<String> getAllVertices() {
		
		Set<String> nameSet = new HashSet<>();  //set of names of all 
		 										//vertices in the graph
		for(int i = 0; i < names.size(); i++) {
			nameSet.add(names.get(i));
		}
		
		return nameSet;
	}

	/**
     * Get all the neighbor (adjacent-dependencies) of a vertex
     * 
     * For the example graph, A->[B, C], D->[A, B] 
     *     getAdjacentVerticesOf(A) should return [B, C]. 
     * 
     * In terms of packages, this list contains the immediate 
     * dependencies of A and depending on your graph structure, 
     * this could be either the predecessors or successors of A.
     * 
     * @param vertex the specified vertex
     * @return an List<String> of all the adjacent vertices for specified vertex
     */
	public List<String> getAdjacentVerticesOf(String vertex) {
		
		List<String> adjVert = new ArrayList<String>();  //list to return
		
		int i = names.indexOf(vertex); //index of the vertex to find all 
									 //things the vertex is a dependency of
		
		for(int j = 0; j < matrix.size(); j++) {
			if(matrix.get(j).get(i) == 1) {
				adjVert.add(names.get(j));
			}
		}

		return adjVert;
	}
	
	/**
     * Returns the number of edges in this graph.
     */
    public int size() {
    	
    	int s = 0;
    	
    	for(int i = 0; i < matrix.size(); i++) {
    		for(int j = 0; j < matrix.get(j).size(); j++) {
    			if(matrix.get(i).get(j) == 1) {
    				s++;
    			}
    		}
    	}
        return s;
    }

	/**
     * Returns the number of vertices in this graph.
     */
	public int order() {
        return names.size();
    }
}
