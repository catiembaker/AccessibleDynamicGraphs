/*
 * One of the tasks will require a node for the abstracted Dijkstra's Alg task 
 * Thus we add the extra field to store the dist
 */
public class DAlgNode extends Node {

	public int dist;
	
	public DAlgNode(int distance, int xPos, int yPos, String theName, int location){
		super(xPos, yPos, theName, location);
		dist=distance;
	}
	
	public int getDistance(){
		return dist;
	}
	
	public String toString(){
		String s = "Node "+name;
		s += inEdges.size() + " in edges and " + outEdges.size() + " out edges.";
		return s;
	}
	
}
