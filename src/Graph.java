import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class Graph extends JPanel{
	public ArrayList<Edge> edges;
	public ArrayList<Node> nodes;
	public boolean directed;
	public HashMap<Integer,Node> map;
	//in the case of undirected graphs there are two versions 
	//of a single edge so this is used to make sure only a single
	//edge is drawn/used
	public HashMap<Edge,Edge> rep;
	
	public Node currNode;
	public ArrayList<Edge> currEdgeList;
	
	public Graph(){
		//Creates Empty Graph
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		map = new HashMap<Integer,Node>();
		rep = new HashMap<Edge,Edge>();
		
	}
	public Graph(int num){
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		map = new HashMap<Integer,Node>();
		rep = new HashMap<Edge,Edge>();
		if(num==1){
			loadGraph1();
		}
	}
	
	public HashMap<Integer,Node> getMapping(){
		return map;
	}
		
	public void paint(Graphics g){
		//		System.out.println("Display");
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 1000, 1000);

		for(int i = 0; i<nodes.size(); i++){
			//				System.out.print("Drawing ");
			nodes.get(i).paintComponent(g);
		}
		for(int i = 0; i<edges.size(); i++){
			//				System.out.print("Drawing ");
			edges.get(i).paintComponent(g);
		}


	}
	
	public Node getCurrNode(){
		return currNode;
	}
	public void setCurrNode(Node a){
		currNode = a;
	}
	
	public void loadGraph1(){
		directed = false;
		
		Node a = new Node(500,250,"A",1);
		nodes.add(a);
		map.put(1, a);
		Node b = new Node(250,700,"B",2);
		nodes.add(b);
		map.put(2, b);
		Node c = new Node(750,700,"C",3);
		nodes.add(c);
		map.put(3, c);
		
		Edge ab = new Edge(a,b,500,275,275,700);
		System.out.println(ab.visible);
		Edge ba = new Edge(b,a,false);
		Edge bc = new Edge(b,c,300,725,750,725);
		Edge cb = new Edge(c,b,false);
		Edge ac = new Edge(a,c,550,275,775,700);
		Edge ca = new Edge(c,a,false);
		edges.add(ab);
		edges.add(ac);
		edges.add(bc);
		rep.put(ab,ab);
		rep.put(ba,ab);
		rep.put(ac,ac);
		rep.put(ca,ac);
		rep.put(bc,bc);
		rep.put(cb,bc);
		
		
		a.addInEdge(ba);
		a.addInEdge(ca);
		a.addOutEdge(ab);
		a.addOutEdge(ac);
		
		b.addInEdge(ab);
		b.addInEdge(cb);
		b.addOutEdge(ba);
		b.addOutEdge(bc);
		
		c.addInEdge(ac);
		c.addInEdge(bc);
		c.addOutEdge(ca);
		c.addOutEdge(cb);
		
	}
	public void loadGraph2(){
		directed = false;
		
		Node a = new Node(500,250,"A",1);
		nodes.add(a);
		map.put(1, a);
		Node b = new Node(250,700,"B",2);
		nodes.add(b);
		map.put(2, b);
		Node c = new Node(750,700,"C",3);
		nodes.add(c);
		map.put(3, c);
		
		Edge ab = new Edge(a,b,500,275,275,700);
		System.out.println(ab.visible);
		Edge ba = new Edge(b,a,false);
		Edge bc = new Edge(b,c,300,725,750,725);
		Edge cb = new Edge(c,b,false);
		Edge ac = new Edge(a,c,550,275,775,700);
		Edge ca = new Edge(c,a,false);
		edges.add(ab);
		edges.add(ac);
		edges.add(bc);
		rep.put(ab,ab);
		rep.put(ba,ab);
		rep.put(ac,ac);
		rep.put(ca,ac);
		rep.put(bc,bc);
		rep.put(cb,bc);
		
		
		a.addInEdge(ba);
		a.addInEdge(ca);
		a.addOutEdge(ab);
		a.addOutEdge(ac);
		
		b.addInEdge(ab);
		b.addInEdge(cb);
		b.addOutEdge(ba);
		b.addOutEdge(bc);
		
		c.addInEdge(ac);
		c.addInEdge(bc);
		c.addOutEdge(ca);
		c.addOutEdge(cb);
		
		
		add(a);
		add(b);
		add(c);
		
	}
	
	
}
