import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {
    
    private Graph graph;  //what is this used for? 
    private Package[] vertexs; 
    private ArrayList<Boolean> visited; //used to keep track of visited, when doing topological order
    private ArrayList<Integer> stack; //used to keep track of the list packages 
    private boolean allVisited; 
    
    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
        
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
     	
    	Object obj = new JSONParser().parse(new FileReader(jsonFilepath)); //opens file and sets to object
    	JSONObject jo = (JSONObject) obj;    //cast it to JSONObject
    	JSONArray packages = (JSONArray) jo.get("packages");  //stores whats in package array in JSON Array.
    	
    	Package[] packs = new Package[packages.size()];  //makes new package array with right size
    	for(int i = 0; i < packages.size(); i++) {       
    	    JSONObject jsonPackage = (JSONObject) packages.get(i);
    	    String packageName = (String) jsonPackage.get("name");
    	    JSONArray packageDep = (JSONArray) jsonPackage.get("dependencies");
    	    
    	    //finish parsing
    	    String[] arr = new String[packageDep.size()];
    	    
    	    for(int j = 0; j < arr.length; j++) {
    	    	 JSONObject dependencies = (JSONObject) packageDep.get(j);
//                 String packageNam = (String)dependencies.get("name");
//                 JSONArray dependencyArray = (JSONArray) dependencies.get("depdendencies");
                 String depend = ""; //USED TO BE String depend = " ";
//                 for (int k = 0; k < dependencyArray.size(); k++) {    //double check this maybe this isnt right. You dont need to add onto depend
                 depend += dependencies + ""; //USED TO BE depend += dependencies + ", ";
//                 }
                 arr[j] = depend ;  //stores a list of dependencies in a array for each name
    	    }
    	    
    	    //create a graph, however that works
    	    Package currPackage = new Package(packageName, arr); //makes a new package. 
            packs[i] = currPackage;  //adds the package to the package array "pack" 
    	} 
    	
    	vertexs = packs; //saves the packages. So for the 'getallpackages' method, I can list them. 
    	    //------------parsing done------------
            //at this point, you should have a array of packages, 'packs', with dependencies
            //and names
            
            for(int k = 0; k <packs.length; k++) {  //adds all of the names to the graph. 
            	graph.addVertex(packs[k].getName());				//as vertex's
            }
            
            //Have to also add the dependencies as verteces as well. Runs through dependencies
            for(int n = 0; n < packs.length; n++) {						//and those as vertex's 
            	for(int p = 0; p < packs[n].getDependencies().length; p++) {
            		graph.addVertex(packs[n].getDependencies()[p]);
            	}
            }
           
            
            for(int l = 0; l < packs.length; l++) {  //sets edges for each of the names of the graph.
            	for(int m = 0; m < packs[m].getDependencies().length; m++) {
            	    graph.addEdge(packs[l].getDependencies()[m], packs[l].getName());
            	}
            }
            
            //at this point, graph created

    }
    
    /**
     * Helper method to get all packages in the graph.
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
    	
    	 //wait does this method 
    	//just return the name of all the packages? 
    	
    	Set<String> nameSet = new HashSet<>();
    	
    	for(int i = 0; i < vertexs.length; i++) {
    		if(vertexs[i].getName() != null) {
    			if(!(nameSet.contains(vertexs[i].getName()))) {
    			nameSet.add(vertexs[i].getName());
    			}
    		}
    	}
    	
    	return nameSet;  //this is the name of all the packages. 
        //return graph.getAllVertices(); not anymore, since some vertex's are also from dependencies.
    												//and not from their own package.
    } 
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
    
    	//if there isn't a package, thrown exception.
    	if(!graph.names.contains(pkg)) {
    		throw new PackageNotFoundException();
    	}
    	
    	//check if cycle exception, idk. 
    	
    	//get order
    	
    	//sets up where to store visited, and where to add to get order
    	int num = graph.names.size();  //num of names
    	stack = new ArrayList<Integer>();  //makes a new stack
    	visited = new ArrayList<Boolean>(); //makes a new array of booleans based on the number of names
    	ArrayList<String> tempString = new ArrayList<String>();  //used to conver the stack arraylist to string.
    	ArrayList<Integer> tempInt = new ArrayList<Integer>();
    	int packIndex = graph.names.indexOf(pkg);  //get the index of the specific package.
    	
    	stack.add(packIndex);
    	
    	tempInt = topoSort(pkg);  //saves the arraylist of still mostly incorrect order 
    	stack = null;  //clears the stack for the next time
    	
    	//convert the mostly incorrect array to the right one. 
    	//runs through the .getadjacecny of each of the elements. If one of the 
    	//elements adjacencies is in the list. Then their lengths are compared, and if 
    	//it is not in the right spot, then it is deleted from the wrong spot, and added to the 
    	//new spot. 
    	
//    	Deque<Integer> myStack = new ArrayDeque();
//    	myStack.offerLast(num);
    	
        return null;
    }
    
    private ArrayList<Integer> topoSort(String pkg){

    	for(int i =0; i<graph.matrix.get(graph.names.indexOf(pkg)).size(); i++ ) {
    		if(visited.get(graph.names.indexOf(pkg)) == true) { //if the index at visited, continue;
    			continue;
    		}
    		
    		if(graph.matrix.get(graph.names.indexOf(pkg)).get(i) == 1) { //it means that it is a dependency in the matrix
    		visited.set(i, true); //adds index to visited
    		stack.add(i); //adds name to arraylist called stack
    		
    		//call this method again. 
    		topoSort(graph.names.get(i));
    	    }
    	}
    	
    	
    	
    	return stack;
		}
    
    
    
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
       
    	//get installation order for B. 
    	//get installation order for A. 
    	
    	//remove all of the names that are in both A and B. 
    	
    	//if A.contains for loop through B. Then remove it. 
    	
    	return null;
    }
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
    	
    	//gets the name with the max dependency. Then gets the installation order for that? NO. 
    	
    	//run through all the installation orders for the names of the graph. install the 
    	//ones with the least # of dependencies first. then work your way up.
        return null;
    }
    
    /**
     * Find and return the name of the package with the maximum number of dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
    	
    	//get a bunch of orders for all of the names. 
    	//keeps track of the  max. as it goes through the names array. 
    	
    	
    	
        return "";
    }
    
    
    public static void main (String [] args) {
        System.out.println("PackageManager.main()");
    }
    
}
