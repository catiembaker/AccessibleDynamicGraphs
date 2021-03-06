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
	public double scaleFactor;
	public Node currNode;
	public ArrayList<Edge> currEdgeList;
	
	public Graph(){
		//Creates Empty Graph
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		map = new HashMap<Integer,Node>();
		rep = new HashMap<Edge,Edge>();
		setSize((int)(1500*scaleFactor),(int)(1500*scaleFactor));
	}
	public Graph(double sf){
		//Creates Empty Graph
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		map = new HashMap<Integer,Node>();
		rep = new HashMap<Edge,Edge>();
		scaleFactor = sf;
		setSize((int)(1500.0*scaleFactor),(int)(1500.0*scaleFactor));
	}
	
	public HashMap<Integer,Node> getMapping(){
		return map;
	}
	public void setScaleFactor(double sf){
		scaleFactor = sf;
	}
	public void paint(Graphics g){
//		System.out.println("Display");
		g.setColor(Color.WHITE);
		g.clearRect(0, 0, (int)(1500*scaleFactor), (int)(1500*scaleFactor));

		for(int i = 0; i<nodes.size(); i++){
			//				System.out.print("Drawing ");
			nodes.get(i).setScaleFactor(scaleFactor);
			nodes.get(i).paintComponent(g);
		}
		for(int i = 0; i<edges.size(); i++){
			//				System.out.print("Drawing ");
			edges.get(i).setScaleFactor(scaleFactor);
			edges.get(i).paintComponent(g);
		}


	}
	
	public Node getCurrNode(){
		return currNode;
	}
	public void setCurrNode(Node a){
		currNode = a;
	}
	public void clear(){
		nodes.clear();
		map.clear();
		edges.clear();
		rep.clear();
	}
	/*
	 * Practice Task 1
	 */
	public void loadGraph1(){
		directed = false;
		
		Node a = new Node(500,250,"A",1,"A");
		nodes.add(a);
		map.put(1, a);
		Node b = new Node(250,700,"B",2,"B");
		nodes.add(b);
		map.put(2, b);
		Node c = new Node(750,700,"C",3,"C");
		nodes.add(c);
		map.put(3, c);
		Node d = new Node(500,1150,"D",4,"D");
		map.put(4, d);
		nodes.add(d);
		
		
		Edge ab = new Edge(a,b,500,275,275,700);
		Edge ba = new Edge(b,a,false);
		Edge bc = new Edge(b,c,300,725,750,725);
		Edge cb = new Edge(c,b,false);
		Edge ac = new Edge(a,c,550,275,775,700);
		Edge ca = new Edge(c,a,false);
		Edge bd = new Edge(b,d,275,750,500,1175);
		Edge db = new Edge(d,b,false);
		edges.add(ab);
		edges.add(ac);
		edges.add(bc);
		edges.add(bd);
		rep.put(ab,ab);
		rep.put(ba,ab);
		rep.put(ac,ac);
		rep.put(ca,ac);
		rep.put(bc,bc);
		rep.put(cb,bc);
		rep.put(bd,bd);
		rep.put(db, bd);
		
		
		a.addInEdge(ba);
		a.addInEdge(ca);
		a.addOutEdge(ab);
		a.addOutEdge(ac);
		
		b.addInEdge(ab);
		b.addInEdge(cb);
		b.addInEdge(db);
		b.addOutEdge(ba);
		b.addOutEdge(bc);
		b.addOutEdge(bd);
		
		c.addInEdge(ac);
		c.addInEdge(bc);
		c.addOutEdge(ca);
		c.addOutEdge(cb);
		
		d.addInEdge(bd);
		d.addOutEdge(db);
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
		
	}
	public void loadGraph2(){
		directed = false;
		
		Node a = new Node(500,250,"A",1,"A");
		nodes.add(a);
		map.put(1, a);
		Node b = new Node(250,700,"B",2,"B");
		nodes.add(b);
		map.put(2, b);
		Node c = new Node(750,700,"C",3,"C");
		nodes.add(c);
		map.put(3, c);
		Node d = new Node(500,1150,"D",4,"D");
		map.put(4, d);
		nodes.add(d);
		
		
		Edge ab = new Edge(a,b,500,275,275,700);
		Edge ba = new Edge(b,a,false);
		Edge bc = new Edge(b,c,300,725,750,725);
		Edge cb = new Edge(c,b,false);
		Edge ac = new Edge(a,c,550,275,775,700);
		Edge ca = new Edge(c,a,false);
		Edge bd = new Edge(b,d,275,750,500,1175);
		Edge db = new Edge(d,b,false);
		Edge ad = new Edge(a,d,525,300,525,1150);
		Edge da = new Edge(d,a,false);
		edges.add(ab);
		edges.add(ac);
		edges.add(bc);
		edges.add(bd);
		edges.add(ad);
		rep.put(ab,ab);
		rep.put(ba,ab);
		rep.put(ac,ac);
		rep.put(ca,ac);
		rep.put(bc,bc);
		rep.put(cb,bc);
		rep.put(bd,bd);
		rep.put(db, bd);
		rep.put(ad, ad);
		rep.put(da, ad);
		
		
		a.addInEdge(ba);
		a.addInEdge(ca);
		a.addInEdge(da);
		a.addOutEdge(ab);
		a.addOutEdge(ac);
		a.addOutEdge(ad);
		
		b.addInEdge(ab);
		b.addInEdge(cb);
		b.addInEdge(db);
		b.addOutEdge(ba);
		b.addOutEdge(bc);
		b.addOutEdge(bd);
		
		c.addInEdge(ac);
		c.addInEdge(bc);
		c.addOutEdge(ca);
		c.addOutEdge(cb);
		
		d.addInEdge(bd);
		d.addInEdge(ad);
		d.addOutEdge(db);
		d.addOutEdge(da);
				
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
		
	}
	/*
	 * Practice Task 2
	 */
	public void load8a(){
		directed = false;
		
		Node a = new Node(500,250,"W",1);
		nodes.add(a);
		map.put(1, a);
		Node b = new Node(250,700,"X",2);
		nodes.add(b);
		map.put(2, b);
		Node c = new Node(750,700,"Y",3);
		nodes.add(c);
		map.put(3, c);
		Node d = new Node(500,1150,"Z",4);
		map.put(4, d);
		nodes.add(d);
		
		
		Edge ab = new Edge(a,b,500,275,275,700);
		Edge ba = new Edge(b,a,false);
		Edge ac = new Edge(a,c,550,275,775,700);
		Edge ca = new Edge(c,a,false);
		Edge bd = new Edge(b,d,275,750,500,1175);
		Edge db = new Edge(d,b,false);
		Edge cd = new Edge(c,d,775,750,550,1175);
		Edge dc = new Edge(d,c,false);
		edges.add(ab);
		edges.add(ac);
		edges.add(cd);
		edges.add(bd);
		rep.put(ab,ab);
		rep.put(ba,ab);
		rep.put(ac,ac);
		rep.put(ca,ac);
		rep.put(cd,cd);
		rep.put(dc,cd);
		rep.put(bd,bd);
		rep.put(db, bd);
		
		
		a.addInEdge(ba);
		a.addInEdge(ca);
		a.addOutEdge(ab);
		a.addOutEdge(ac);
		
		b.addInEdge(ab);
//		b.addInEdge(cb);
		b.addInEdge(db);
		b.addOutEdge(ba);
//		b.addOutEdge(bc);
		b.addOutEdge(bd);
		
		c.addInEdge(ac);
		c.addInEdge(dc);
		c.addOutEdge(ca);
		c.addOutEdge(cd);
		
		d.addInEdge(bd);
		d.addOutEdge(db);
		d.addInEdge(cd);
		d.addOutEdge(dc);
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(false);
		}
	}
	public void load8b(){
		directed = false;
		
		Node a = new Node(500,250,"W",1);
		nodes.add(a);
		map.put(1, a);
		Node b = new Node(250,700,"X",2);
		nodes.add(b);
		map.put(2, b);
		Node c = new Node(750,700,"Y",3);
		nodes.add(c);
		map.put(3, c);
		Node d = new Node(500,1150,"Z",4);
		map.put(4, d);
		nodes.add(d);
		
		
		Edge ab = new Edge(a,b,500,275,275,700);
		Edge ba = new Edge(b,a,false);
		Edge ac = new Edge(a,c,550,275,775,700);
		Edge ca = new Edge(c,a,false);
		Edge bd = new Edge(b,d,275,750,500,1175);
		Edge db = new Edge(d,b,false);
		Edge cd = new Edge(c,d,775,750,550,1175);
		Edge dc = new Edge(d,c,false);
		Edge bc = new Edge(b,c,300,725,750,725);
		Edge cb = new Edge(c,b,false);
		edges.add(ab);
		edges.add(ac);
		edges.add(cd);
		edges.add(bd);
		edges.add(bc);
		rep.put(ab,ab);
		rep.put(ba,ab);
		rep.put(ac,ac);
		rep.put(ca,ac);
		rep.put(cd,cd);
		rep.put(dc,cd);
		rep.put(bd,bd);
		rep.put(db, bd);
		rep.put(bc, bc);
		rep.put(cb, bc);
		
		a.addInEdge(ba);
		a.addInEdge(ca);
		a.addOutEdge(ab);
		a.addOutEdge(ac);
		
		b.addInEdge(ab);
		b.addInEdge(db);
		b.addInEdge(cb);
		b.addOutEdge(ba);
		b.addOutEdge(bd);
		b.addOutEdge(bc);
		
		c.addInEdge(ac);
		c.addInEdge(dc);
		c.addInEdge(bc);
		c.addOutEdge(ca);
		c.addOutEdge(cd);
		c.addOutEdge(cb);
		
		d.addInEdge(bd);
		d.addOutEdge(db);
		d.addInEdge(cd);
		d.addOutEdge(dc);
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(false);
		}
	}
	/*	
	 * Linked List Task 1
	 */
	public void load1a(){
		directed = true;
		Node n1 = new Node(50,600,"5",1);
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(200,600,"7",2);
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(350,600,"8",3);
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(500,600,"7",4);
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(650,600,"3",5);
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(800,600,"6",6);
		nodes.add(n6);
		map.put(6, n6);
		
		
		Edge n1n2 = new Edge(n1,n2,100,625,200,625);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
				
		Edge n2n3 = new Edge(n2,n3,250,625,350,625);
		edges.add(n2n3);
		n2.addOutEdge(n2n3);
		n3.addInEdge(n2n3);
		rep.put(n2n3, n2n3);
		
		Edge n3n4 = new Edge(n3,n4,400,625,500,625); 
		edges.add(n3n4);
		n3.addOutEdge(n3n4);
		n4.addInEdge(n3n4);
		rep.put(n3n4, n3n4);
		
		Edge n4n5 = new Edge(n4,n5,550,625,650,625);
		edges.add(n4n5);
		n4.addOutEdge(n4n5);
		n5.addInEdge(n4n5);
		rep.put(n4n5, n4n5);
		
		Edge n5n6 = new Edge(n5,n6,700,625,800,625);
		edges.add(n5n6);
		n5.addOutEdge(n5n6);
		n6.addInEdge(n5n6);
		rep.put(n5n6, n5n6);
		
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
		
	}
	public void load1b(){
		directed = true;
		Node n1 = new Node(50,600,"5",1);
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(200,600,"7",2);
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(350,600,"8",3);
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(500,600,"2",4);
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(650,600,"7",5);
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(800,600,"3",6);
		nodes.add(n6);
		map.put(6, n6);
		Node n7 = new Node(950,600,"6",7);
		nodes.add(n7);
		map.put(7, n7);
		
		
		Edge n1n2 = new Edge(n1,n2,100,625,200,625);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
				
		Edge n2n3 = new Edge(n2,n3,250,625,350,625);
		edges.add(n2n3);
		n2.addOutEdge(n2n3);
		n3.addInEdge(n2n3);
		rep.put(n2n3, n2n3);
		
		Edge n3n4 = new Edge(n3,n4,400,625,500,625); 
		edges.add(n3n4);
		n3.addOutEdge(n3n4);
		n4.addInEdge(n3n4);
		rep.put(n3n4, n3n4);
		
		Edge n4n5 = new Edge(n4,n5,550,625,650,625);
		edges.add(n4n5);
		n4.addOutEdge(n4n5);
		n5.addInEdge(n4n5);
		rep.put(n4n5, n4n5);
		
		Edge n5n6 = new Edge(n5,n6,700,625,800,625);
		edges.add(n5n6);
		n5.addOutEdge(n5n6);
		n6.addInEdge(n5n6);
		rep.put(n5n6, n5n6);
		
		Edge n6n7 = new Edge(n6,n7,850,625,950,625);
		edges.add(n6n7);
		n6.addOutEdge(n6n7);
		n7.addInEdge(n6n7);
		rep.put(n6n7, n6n7);
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
		
	}
	/*
	 * Linked List Task 2
	 */
	public void load2a(){
		directed = true;
		Node n1 = new Node(50,600,"3",1);
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(200,600,"2",2);
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(350,600,"3",3);
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(500,600,"6",4);
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(650,600,"5",5);
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(800,600,"1",6);
		nodes.add(n6);
		map.put(6, n6);
		
		Edge n1n2 = new Edge(n1,n2,100,625,200,625);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
				
		Edge n2n3 = new Edge(n2,n3,250,625,350,625);
		edges.add(n2n3);
		n2.addOutEdge(n2n3);
		n3.addInEdge(n2n3);
		rep.put(n2n3, n2n3);
		
		Edge n3n4 = new Edge(n3,n4,400,625,500,625); 
		edges.add(n3n4);
		n3.addOutEdge(n3n4);
		n4.addInEdge(n3n4);
		rep.put(n3n4, n3n4);
		
		Edge n4n5 = new Edge(n4,n5,550,625,650,625);
		edges.add(n4n5);
		n4.addOutEdge(n4n5);
		n5.addInEdge(n4n5);
		rep.put(n4n5, n4n5);
		
		Edge n5n6 = new Edge(n5,n6,700,625,800,625);
		edges.add(n5n6);
		n5.addOutEdge(n5n6);
		n6.addInEdge(n5n6);
		rep.put(n5n6, n5n6);
				
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	public void load2b(){
		directed = true;
		Node n1 = new Node(50,600,"3",1);
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(200,600,"2",2);
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(350,600,"3",3);
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(500,600,"6",4);
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(650,600,"8",5);
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(800,600,"5",6);
		nodes.add(n6);
		map.put(6, n6);
		Node n7 = new Node(950,600,"1",7);
		nodes.add(n7);
		map.put(7, n7);
		
		
		Edge n1n2 = new Edge(n1,n2,100,625,200,625);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
				
		Edge n2n3 = new Edge(n2,n3,250,625,350,625);
		edges.add(n2n3);
		n2.addOutEdge(n2n3);
		n3.addInEdge(n2n3);
		rep.put(n2n3, n2n3);
		
		Edge n3n4 = new Edge(n3,n4,400,625,500,625); 
		edges.add(n3n4);
		n3.addOutEdge(n3n4);
		n4.addInEdge(n3n4);
		rep.put(n3n4, n3n4);
		
		Edge n4n5 = new Edge(n4,n5,550,625,650,625);
		edges.add(n4n5);
		n4.addOutEdge(n4n5);
		n5.addInEdge(n4n5);
		rep.put(n4n5, n4n5);
		
		Edge n5n6 = new Edge(n5,n6,700,625,800,625);
		edges.add(n5n6);
		n5.addOutEdge(n5n6);
		n6.addInEdge(n5n6);
		rep.put(n5n6, n5n6);
		
		Edge n6n7 = new Edge(n6,n7,850,625,950,625);
		edges.add(n6n7);
		n6.addOutEdge(n6n7);
		n7.addInEdge(n6n7);
		rep.put(n6n7, n6n7);
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	/*
	 * Family Tree Task 1
	 */
	public void load3a(){
		directed = true;
		Node n1 = new Node(600,100,"T",1, "Tom");
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(100,400,"A",2, "Alex");
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(400,400,"B",3, "Brad");
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(700,400,"J",4, "Jill");
		nodes.add(n4);
		map.put(4, n4);
//		Node n5 = new Node(1000,400,"K",5, "Karl");
//		nodes.add(n5);
//		map.put(5, n5);
		Node n6 = new Node(25,700,"F",6, "Fred");
		nodes.add(n6);
		map.put(6, n6);
		Node n7 = new Node(100,700,"E",7, "Erin");
		nodes.add(n7);
		map.put(7, n7);
//		Node n8 = new Node(200,700,"P",8,"Pam");
//		nodes.add(n8);
//		map.put(8, n8);
		Node n9 = new Node(400,700,"C",9,"Chris");
		nodes.add(n9);
		map.put(9, n9);
		
		
		Edge n1n2 = new Edge(n1,n2,600,125,125,400);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
		
		Edge n1n3 = new Edge(n1,n3,617,150,425,400);
		edges.add(n1n3);
		n1.addOutEdge(n1n3);
		n3.addInEdge(n1n3);
		rep.put(n1n3, n1n3);
		
		Edge n1n4 = new Edge(n1,n4,635,150,725,400);
		edges.add(n1n4);
		n1.addOutEdge(n1n4);
		n4.addInEdge(n1n4);
		rep.put(n1n4, n1n4);
		
//		Edge n1n5 = new Edge(n1,n5,650,125,1025,400); 
//		edges.add(n1n5);
//		n1.addOutEdge(n1n5);
//		n5.addInEdge(n1n5);
//		rep.put(n1n5, n1n5);
		
		Edge n2n6 = new Edge(n2,n6,115,445,50,700);
		edges.add(n2n6);
		n2.addOutEdge(n2n6);
		n6.addInEdge(n2n6);
		rep.put(n2n6, n2n6);
		
		Edge n2n7 = new Edge(n2,n7,125,450,125,700);
		edges.add(n2n7);
		n2.addOutEdge(n2n7);
		n7.addInEdge(n2n7);
		rep.put(n2n7, n2n7);
		
//		Edge n2n8 = new Edge(n2,n8,135,445,200,700);
//		edges.add(n4n8);
//		n2.addOutEdge(n4n8);
//		n8.addInEdge(n4n8);
//		rep.put(n4n8, n4n8);
		
		Edge n3n9 = new Edge(n3,n9,425,450,425,700);
		edges.add(n3n9);
		n3.addOutEdge(n3n9);
		n9.addInEdge(n3n9);
		rep.put(n3n9, n3n9); 
				
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	public void load3b(){
		directed = true;
		Node n1 = new Node(600,100,"T",1, "Tom");
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(100,400,"A",2, "Alex");
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(400,400,"B",3, "Brad");
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(700,400,"J",4, "Jill");
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(1000,400,"K",5, "Karl");
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(25,700,"F",6, "Fred");
		nodes.add(n6);
		map.put(6, n6);
		Node n7 = new Node(100,700,"E",7, "Erin");
		nodes.add(n7);
		map.put(7, n7);
		Node n8 = new Node(175,700,"P",8,"Pam");
		nodes.add(n8);
		map.put(8, n8);
		Node n9 = new Node(400,700,"C",9,"Chris");
		nodes.add(n9);
		map.put(9, n9);
		
		
		Edge n1n2 = new Edge(n1,n2,600,125,125,400);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
		
		Edge n1n3 = new Edge(n1,n3,617,150,425,400);
		edges.add(n1n3);
		n1.addOutEdge(n1n3);
		n3.addInEdge(n1n3);
		rep.put(n1n3, n1n3);
		
		Edge n1n4 = new Edge(n1,n4,635,150,725,400);
		edges.add(n1n4);
		n1.addOutEdge(n1n4);
		n4.addInEdge(n1n4);
		rep.put(n1n4, n1n4);
		
		Edge n1n5 = new Edge(n1,n5,650,125,1025,400); 
		edges.add(n1n5);
		n1.addOutEdge(n1n5);
		n5.addInEdge(n1n5);
		rep.put(n1n5, n1n5);
		
		Edge n2n6 = new Edge(n2,n6,115,445,50,700);
		edges.add(n2n6);
		n2.addOutEdge(n2n6);
		n6.addInEdge(n2n6);
		rep.put(n2n6, n2n6);
		
		Edge n2n7 = new Edge(n2,n7,125,450,125,700);
		edges.add(n2n7);
		n2.addOutEdge(n2n7);
		n7.addInEdge(n2n7);
		rep.put(n2n7, n2n7);
		
		Edge n2n8 = new Edge(n2,n8,135,445,200,700);
		edges.add(n2n8);
		n2.addOutEdge(n2n8);
		n8.addInEdge(n2n8);
		rep.put(n2n8, n2n8);
		
		Edge n3n9 = new Edge(n3,n9,425,450,425,700);
		edges.add(n3n9);
		n3.addOutEdge(n3n9);
		n9.addInEdge(n3n9);
		rep.put(n3n9, n3n9); 
				
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	/*
	 * Family Tree Task 2
	 */
	public void load4a(){
		directed = true;
		Node n1 = new Node(600,100,"J",1, "Jane");
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(100,400,"T",2, "Teresa");
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(400,400,"P",3, "Paul");
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(700,400,"B",4, "Blaine");
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(1000,400,"M",5, "Mike");
		nodes.add(n5);
		map.put(5, n5);
//		Node n6 = new Node(100,700,"D",6, "Dan");
//		nodes.add(n6);
//		map.put(6, n6);
		Node n7 = new Node(300,700,"S",7, "Sara");
		nodes.add(n7);
		map.put(7, n7);
//		Node n8 = new Node(450,700,"W",8,"Wendy");
//		nodes.add(n8);
//		map.put(8, n8);
		Node n9 = new Node(700,700,"L",9,"Lily");
		nodes.add(n9);
		map.put(9, n9);
		
		
		Edge n1n2 = new Edge(n1,n2,600,125,125,400);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
		
		Edge n1n3 = new Edge(n1,n3,617,150,425,400);
		edges.add(n1n3);
		n1.addOutEdge(n1n3);
		n3.addInEdge(n1n3);
		rep.put(n1n3, n1n3);
		
		Edge n1n4 = new Edge(n1,n4,635,150,725,400);
		edges.add(n1n4);
		n1.addOutEdge(n1n4);
		n4.addInEdge(n1n4);
		rep.put(n1n4, n1n4);
		
		Edge n1n5 = new Edge(n1,n5,650,125,1025,400); 
		edges.add(n1n5);
		n1.addOutEdge(n1n5);
		n5.addInEdge(n1n5);
		rep.put(n1n5, n1n5);
		
	
//		Edge n2n6 = new Edge(n2,n6,125,450,125,700);
//		edges.add(n2n6);
//		n2.addOutEdge(n2n6);
//		n6.addInEdge(n2n6);
//		rep.put(n2n6, n2n6);
		
		Edge n3n7 = new Edge(n3,n7,415,445,325,700);
		edges.add(n3n7);
		n3.addOutEdge(n3n7);
		n7.addInEdge(n3n7);
		rep.put(n3n7, n3n7);
		
//		Edge n3n8 = new Edge(n3,n8,435,445,475,700);
//		edges.add(n3n8);
//		n3.addOutEdge(n3n8);
//		n8.addInEdge(n3n8);
//		rep.put(n3n8, n3n8);
//		
		Edge n4n9 = new Edge(n4,n9,725,450,725,700);
		edges.add(n4n9);
		n4.addOutEdge(n4n9);
		n9.addInEdge(n4n9);
		rep.put(n4n9, n4n9); 
				
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	public void load4b(){
		directed = true;
		Node n1 = new Node(600,100,"J",1, "Jane");
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(100,400,"T",2, "Teresa");
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(400,400,"P",3, "Paul");
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(700,400,"B",4, "Blaine");
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(1000,400,"M",5, "Mike");
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(100,700,"D",6, "Dan");
		nodes.add(n6);
		map.put(6, n6);
		Node n7 = new Node(300,700,"S",7, "Sara");
		nodes.add(n7);
		map.put(7, n7);
		Node n8 = new Node(450,700,"W",8,"Wendy");
		nodes.add(n8);
		map.put(8, n8);
		Node n9 = new Node(700,700,"L",9,"Lily");
		nodes.add(n9);
		map.put(9, n9);
		
		
		Edge n1n2 = new Edge(n1,n2,600,125,125,400);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
		
		Edge n1n3 = new Edge(n1,n3,617,150,425,400);
		edges.add(n1n3);
		n1.addOutEdge(n1n3);
		n3.addInEdge(n1n3);
		rep.put(n1n3, n1n3);
		
		Edge n1n4 = new Edge(n1,n4,635,150,725,400);
		edges.add(n1n4);
		n1.addOutEdge(n1n4);
		n4.addInEdge(n1n4);
		rep.put(n1n4, n1n4);
		
		Edge n1n5 = new Edge(n1,n5,650,125,1025,400); 
		edges.add(n1n5);
		n1.addOutEdge(n1n5);
		n5.addInEdge(n1n5);
		rep.put(n1n5, n1n5);
		
	
		Edge n2n6 = new Edge(n2,n6,125,450,125,700);
		edges.add(n2n6);
		n2.addOutEdge(n2n6);
		n6.addInEdge(n2n6);
		rep.put(n2n6, n2n6);
		
		Edge n3n7 = new Edge(n3,n7,415,445,325,700);
		edges.add(n3n7);
		n3.addOutEdge(n3n7);
		n7.addInEdge(n3n7);
		rep.put(n3n7, n3n7);
		
		Edge n3n8 = new Edge(n3,n8,435,445,475,700);
		edges.add(n3n8);
		n3.addOutEdge(n3n8);
		n8.addInEdge(n3n8);
		rep.put(n3n8, n3n8);
		
		Edge n4n9 = new Edge(n4,n9,725,450,725,700);
		edges.add(n4n9);
		n4.addOutEdge(n4n9);
		n9.addInEdge(n4n9);
		rep.put(n4n9, n4n9); 
				
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	/*
 	* Dijkstra Task 1	
 	*/
	public void load5a(){
		directed = false;
		DAlgNode nF = new DAlgNode("inf",600,200,"Fred",1);
		nodes.add(nF);
		map.put(1, nF);
		DAlgNode nC = new DAlgNode("inf",250,600,"Cate",2);
		nodes.add(nC);
		map.put(2, nC);
		DAlgNode nD = new DAlgNode("inf",600,600,"Dan",3);
		nodes.add(nD);
		map.put(3, nD);
		DAlgNode nE = new DAlgNode("3",900,600,"Edgar",4);
		nodes.add(nE);
		map.put(4, nE);
		DAlgNode nB = new DAlgNode("1",450,900,"Brian",5);
		nodes.add(nB);
		map.put(5, nB);
		DAlgNode nA = new DAlgNode("0",750,900,"Allen",6);
		nodes.add(nA);
		map.put(6, nA);
		
		Edge nAnB = new Edge(nA,nB,525,925,750,925);
		Edge nBnA = new Edge(nB,nA,false);
		edges.add(nAnB);
		nA.addOutEdge(nAnB);
		nB.addOutEdge(nBnA);
		nA.addInEdge(nBnA);
		nB.addInEdge(nAnB);
		rep.put(nAnB, nAnB);
		rep.put(nBnA, nAnB);
		
		Edge nAnE = new Edge(nA,nE,825,925,925,675);
		Edge nEnA = new Edge(nE,nA,false);
		edges.add(nAnE);
		nA.addOutEdge(nAnE);
		nE.addOutEdge(nEnA);
		nA.addInEdge(nEnA);
		nE.addInEdge(nAnE);
		rep.put(nAnE, nAnE);
		rep.put(nEnA, nAnE);
		
		Edge nBnE = new Edge(nB,nE,525,920,900,660);
		Edge nEnB = new Edge(nE,nB,false);
		edges.add(nBnE);
		nB.addOutEdge(nBnE);
		nE.addOutEdge(nEnB);
		nB.addInEdge(nEnB);
		nE.addInEdge(nBnE);
		rep.put(nEnB, nBnE);
		rep.put(nBnE, nBnE);
		
		Edge nBnD = new Edge(nB,nD,505,905,620,670);
		Edge nDnB = new Edge(nD,nB,false);
		edges.add(nBnD);
		nB.addOutEdge(nBnD);
		nD.addOutEdge(nDnB);
		nB.addInEdge(nDnB);
		nD.addInEdge(nBnD);
		rep.put(nDnB, nBnD);
		rep.put(nBnD, nBnD);
		
		Edge nBnF = new Edge(nB,nF,475,900,600,255);
		Edge nFnB = new Edge(nF,nB,false);
		edges.add(nBnF);
		nB.addOutEdge(nBnF);
		nF.addOutEdge(nFnB);
		nB.addInEdge(nFnB);
		nF.addInEdge(nBnF);
		rep.put(nFnB, nBnF);
		rep.put(nBnF, nBnF);
		
		Edge nBnC = new Edge(nB,nC,455,910,285,675);
		Edge nCnB = new Edge(nC,nB,false);
		edges.add(nBnC);
		nB.addOutEdge(nBnC);
		nC.addOutEdge(nCnB);
		nB.addInEdge(nCnB);
		nC.addInEdge(nBnC);
		rep.put(nCnB, nBnC);
		rep.put(nBnC, nBnC);
		
		Edge nDnC = new Edge(nD,nC,325,640,600,640);
		Edge nCnD = new Edge(nC,nD,false);
		edges.add(nDnC);
		nD.addOutEdge(nDnC);
		nC.addOutEdge(nCnD);
		nD.addInEdge(nCnD);
		nC.addInEdge(nDnC);
		rep.put(nCnD, nDnC);
		rep.put(nDnC, nDnC);
		
		Edge nDnE = new Edge(nD,nE,675,640,900,640);
		Edge nEnD = new Edge(nE,nD,false);
		edges.add(nDnE);
		nD.addOutEdge(nDnE);
		nE.addOutEdge(nEnD);
		nD.addInEdge(nEnD);
		nE.addInEdge(nDnE);
		rep.put(nEnD, nDnE);
		rep.put(nDnE, nDnE);
		
		Edge nFnE = new Edge(nF,nE,675,250,910,610);
		Edge nEnF = new Edge(nE,nF,false);
		edges.add(nFnE);
		nF.addOutEdge(nFnE);
		nE.addOutEdge(nEnF);
		nF.addInEdge(nEnF);
		nE.addInEdge(nFnE);
		rep.put(nEnF, nFnE);
		rep.put(nFnE, nFnE);
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	public void load5b(){
		directed = false;
		DAlgNode nF = new DAlgNode("inf",600,200,"Fred",1);
		nodes.add(nF);
		map.put(1, nF);
		DAlgNode nC = new DAlgNode("9",250,600,"Cate",2);
		nodes.add(nC);
		map.put(2, nC);
		DAlgNode nD = new DAlgNode("2",600,600,"Dan",3);
		nodes.add(nD);
		map.put(3, nD);
		DAlgNode nE = new DAlgNode("4",900,600,"Edgar",4);
		nodes.add(nE);
		map.put(4, nE);
		DAlgNode nB = new DAlgNode("1",450,900,"Brian",5);
		nodes.add(nB);
		map.put(5, nB);
		DAlgNode nA = new DAlgNode("0",750,900,"Allen",6);
		nodes.add(nA);
		map.put(6, nA);
		
		Edge nAnB = new Edge(nA,nB,525,925,750,925);
		Edge nBnA = new Edge(nB,nA,false);
		edges.add(nAnB);
		nA.addOutEdge(nAnB);
		nB.addOutEdge(nBnA);
		nA.addInEdge(nBnA);
		nB.addInEdge(nAnB);
		rep.put(nAnB, nAnB);
		rep.put(nBnA, nAnB);
		
		Edge nAnE = new Edge(nA,nE,825,925,925,675);
		Edge nEnA = new Edge(nE,nA,false);
		edges.add(nAnE);
		nA.addOutEdge(nAnE);
		nE.addOutEdge(nEnA);
		nA.addInEdge(nEnA);
		nE.addInEdge(nAnE);
		rep.put(nAnE, nAnE);
		rep.put(nEnA, nAnE);
		
		Edge nBnE = new Edge(nB,nE,525,920,900,660);
		Edge nEnB = new Edge(nE,nB,false);
		edges.add(nBnE);
		nB.addOutEdge(nBnE);
		nE.addOutEdge(nEnB);
		nB.addInEdge(nEnB);
		nE.addInEdge(nBnE);
		rep.put(nEnB, nBnE);
		rep.put(nBnE, nBnE);
		
		Edge nBnD = new Edge(nB,nD,505,905,620,670);
		Edge nDnB = new Edge(nD,nB,false);
		edges.add(nBnD);
		nB.addOutEdge(nBnD);
		nD.addOutEdge(nDnB);
		nB.addInEdge(nDnB);
		nD.addInEdge(nBnD);
		rep.put(nDnB, nBnD);
		rep.put(nBnD, nBnD);
		
		Edge nBnF = new Edge(nB,nF,475,900,600,255);
		Edge nFnB = new Edge(nF,nB,false);
		edges.add(nBnF);
		nB.addOutEdge(nBnF);
		nF.addOutEdge(nFnB);
		nB.addInEdge(nFnB);
		nF.addInEdge(nBnF);
		rep.put(nFnB, nBnF);
		rep.put(nBnF, nBnF);
		
		Edge nBnC = new Edge(nB,nC,455,910,285,675);
		Edge nCnB = new Edge(nC,nB,false);
		edges.add(nBnC);
		nB.addOutEdge(nBnC);
		nC.addOutEdge(nCnB);
		nB.addInEdge(nCnB);
		nC.addInEdge(nBnC);
		rep.put(nCnB, nBnC);
		rep.put(nBnC, nBnC);
		
		Edge nDnC = new Edge(nD,nC,325,640,600,640);
		Edge nCnD = new Edge(nC,nD,false);
		edges.add(nDnC);
		nD.addOutEdge(nDnC);
		nC.addOutEdge(nCnD);
		nD.addInEdge(nCnD);
		nC.addInEdge(nDnC);
		rep.put(nCnD, nDnC);
		rep.put(nDnC, nDnC);
		
		Edge nDnE = new Edge(nD,nE,675,640,900,640);
		Edge nEnD = new Edge(nE,nD,false);
		edges.add(nDnE);
		nD.addOutEdge(nDnE);
		nE.addOutEdge(nEnD);
		nD.addInEdge(nEnD);
		nE.addInEdge(nDnE);
		rep.put(nEnD, nDnE);
		rep.put(nDnE, nDnE);
		
		Edge nFnE = new Edge(nF,nE,675,250,910,610);
		Edge nEnF = new Edge(nE,nF,false);
		edges.add(nFnE);
		nF.addOutEdge(nFnE);
		nE.addOutEdge(nEnF);
		nF.addInEdge(nEnF);
		nE.addInEdge(nFnE);
		rep.put(nEnF, nFnE);
		rep.put(nFnE, nFnE);
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	/*
	 * Dijkstra Task 2
	 */	
	public void load6a(){
		directed = false;
		DAlgNode nA = new DAlgNode("inf",600,300,"ape",1);
		nodes.add(nA);
		map.put(1, nA);
		DAlgNode nB = new DAlgNode("inf",200,600,"bat",2);
		nodes.add(nB);
		map.put(2, nB);
		DAlgNode nC = new DAlgNode("1",600,600,"cat",3);
		nodes.add(nC);
		map.put(3, nC);
		DAlgNode nD = new DAlgNode("2",1000,600,"dog",4);
		nodes.add(nD);
		map.put(4, nD);
		DAlgNode nE = new DAlgNode("5",400,900,"eagle",5);
		nodes.add(nE);
		map.put(5, nE);
		DAlgNode nF = new DAlgNode("0",800,900,"frog",6);
		nodes.add(nF);
		map.put(6, nF);
		
		Edge nAnB = new Edge(nA,nB,600,350,250,610);
		Edge nBnA = new Edge(nB,nA,false);
		edges.add(nAnB);
		nA.addOutEdge(nAnB);
		nB.addOutEdge(nBnA);
		nA.addInEdge(nBnA);
		nB.addInEdge(nAnB);
		rep.put(nAnB, nAnB);
		rep.put(nBnA, nAnB);
		
		Edge nAnC = new Edge(nA,nC,638,375,638,600);
		Edge nCnA = new Edge(nC,nA,false);
		edges.add(nAnC);
		nA.addOutEdge(nAnC);
		nC.addOutEdge(nCnA);
		nA.addInEdge(nCnA);
		nC.addInEdge(nAnC);
		rep.put(nAnC, nAnC);
		rep.put(nCnA, nAnC);
		
		Edge nBnC = new Edge(nB,nC,275,638,600,638);
		Edge nCnB = new Edge(nC,nB,false);
		edges.add(nBnC);
		nB.addOutEdge(nBnC);
		nC.addOutEdge(nCnB);
		nB.addInEdge(nCnB);
		nC.addInEdge(nBnC);
		rep.put(nBnC, nBnC);
		rep.put(nCnB, nBnC);
		
		Edge nCnD = new Edge(nC,nD,675,638,1000,638);
		Edge nDnC = new Edge(nD,nC,false);
		edges.add(nCnD);
		nC.addOutEdge(nCnD);
		nD.addOutEdge(nDnC);
		nC.addInEdge(nDnC);
		nD.addInEdge(nCnD);
		rep.put(nDnC, nCnD);
		rep.put(nCnD, nCnD);
		
		Edge nCnF = new Edge(nC,nF,650,670,825,910);
		Edge nFnC = new Edge(nF,nC,false);
		edges.add(nCnF);
		nC.addOutEdge(nCnF);
		nF.addOutEdge(nFnC);
		nC.addInEdge(nFnC);
		nF.addInEdge(nCnF);
		rep.put(nFnC, nCnF);
		rep.put(nCnF, nCnF);
		
		Edge nEnC = new Edge(nE,nC,610,670,450,910);
		Edge nCnE = new Edge(nC,nE,false);
		edges.add(nEnC);
		nE.addOutEdge(nEnC);
		nC.addOutEdge(nCnE);
		nE.addInEdge(nCnE);
		nC.addInEdge(nEnC);
		rep.put(nCnE, nEnC);
		rep.put(nEnC, nEnC);
		
		Edge nDnF = new Edge(nD,nF,1010,660,860,910);
		Edge nFnD = new Edge(nF,nD,false);
		edges.add(nDnF);
		nD.addOutEdge(nDnF);
		nF.addOutEdge(nFnD);
		nD.addInEdge(nFnD);
		nF.addInEdge(nDnF);
		rep.put(nFnD, nDnF);
		rep.put(nDnF, nDnF);
		
		Edge nDnA = new Edge(nD,nA,680,328,1010,610);
		Edge nAnD = new Edge(nA,nD,false);
		edges.add(nDnA);
		nD.addOutEdge(nDnA);
		nA.addOutEdge(nAnD);
		nD.addInEdge(nAnD);
		nA.addInEdge(nDnA);
		rep.put(nAnD, nDnA);
		rep.put(nDnA, nDnA);
		
		Edge nFnE = new Edge(nF,nE,475,938,800,938);
		Edge nEnF = new Edge(nE,nF,false);
		edges.add(nFnE);
		nF.addOutEdge(nFnE);
		nE.addOutEdge(nEnF);
		nF.addInEdge(nEnF);
		nE.addInEdge(nFnE);
		rep.put(nEnF, nFnE);
		rep.put(nFnE, nFnE);
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	public void load6b(){
		directed = false;
		DAlgNode nA = new DAlgNode("inf",600,300,"ape",1);
		nodes.add(nA);
		map.put(1, nA);
		DAlgNode nB = new DAlgNode("6",200,600,"bat",2);
		nodes.add(nB);
		map.put(2, nB);
		DAlgNode nC = new DAlgNode("1",600,600,"cat",3);
		nodes.add(nC);
		map.put(3, nC);
		DAlgNode nD = new DAlgNode("6",1000,600,"dog",4);
		nodes.add(nD);
		map.put(4, nD);
		DAlgNode nE = new DAlgNode("5",400,900,"eagle",5);
		nodes.add(nE);
		map.put(5, nE);
		DAlgNode nF = new DAlgNode("0",800,900,"frog",6);
		nodes.add(nF);
		map.put(6, nF);
		
		Edge nAnB = new Edge(nA,nB,600,350,250,610);
		Edge nBnA = new Edge(nB,nA,false);
		edges.add(nAnB);
		nA.addOutEdge(nAnB);
		nB.addOutEdge(nBnA);
		nA.addInEdge(nBnA);
		nB.addInEdge(nAnB);
		rep.put(nAnB, nAnB);
		rep.put(nBnA, nAnB);
		
		Edge nAnC = new Edge(nA,nC,638,375,638,600);
		Edge nCnA = new Edge(nC,nA,false);
		edges.add(nAnC);
		nA.addOutEdge(nAnC);
		nC.addOutEdge(nCnA);
		nA.addInEdge(nCnA);
		nC.addInEdge(nAnC);
		rep.put(nAnC, nAnC);
		rep.put(nCnA, nAnC);
		
		Edge nBnC = new Edge(nB,nC,275,638,600,638);
		Edge nCnB = new Edge(nC,nB,false);
		edges.add(nBnC);
		nB.addOutEdge(nBnC);
		nC.addOutEdge(nCnB);
		nB.addInEdge(nCnB);
		nC.addInEdge(nBnC);
		rep.put(nBnC, nBnC);
		rep.put(nCnB, nBnC);
		
		Edge nCnD = new Edge(nC,nD,675,638,1000,638);
		Edge nDnC = new Edge(nD,nC,false);
		edges.add(nCnD);
		nC.addOutEdge(nCnD);
		nD.addOutEdge(nDnC);
		nC.addInEdge(nDnC);
		nD.addInEdge(nCnD);
		rep.put(nDnC, nCnD);
		rep.put(nCnD, nCnD);
		
		Edge nCnF = new Edge(nC,nF,650,670,825,910);
		Edge nFnC = new Edge(nF,nC,false);
		edges.add(nCnF);
		nC.addOutEdge(nCnF);
		nF.addOutEdge(nFnC);
		nC.addInEdge(nFnC);
		nF.addInEdge(nCnF);
		rep.put(nFnC, nCnF);
		rep.put(nCnF, nCnF);
		
		Edge nEnC = new Edge(nE,nC,610,670,450,910);
		Edge nCnE = new Edge(nC,nE,false);
		edges.add(nEnC);
		nE.addOutEdge(nEnC);
		nC.addOutEdge(nCnE);
		nE.addInEdge(nCnE);
		nC.addInEdge(nEnC);
		rep.put(nCnE, nEnC);
		rep.put(nEnC, nEnC);
		
		Edge nDnF = new Edge(nD,nF,1010,660,860,910);
		Edge nFnD = new Edge(nF,nD,false);
		edges.add(nDnF);
		nD.addOutEdge(nDnF);
		nF.addOutEdge(nFnD);
		nD.addInEdge(nFnD);
		nF.addInEdge(nDnF);
		rep.put(nFnD, nDnF);
		rep.put(nDnF, nDnF);
		
		Edge nDnA = new Edge(nD,nA,680,328,1010,610);
		Edge nAnD = new Edge(nA,nD,false);
		edges.add(nDnA);
		nD.addOutEdge(nDnA);
		nA.addOutEdge(nAnD);
		nD.addInEdge(nAnD);
		nA.addInEdge(nDnA);
		rep.put(nAnD, nDnA);
		rep.put(nDnA, nDnA);
		
		Edge nFnE = new Edge(nF,nE,475,938,800,938);
		Edge nEnF = new Edge(nE,nF,false);
		edges.add(nFnE);
		nF.addOutEdge(nFnE);
		nE.addOutEdge(nEnF);
		nF.addInEdge(nEnF);
		nE.addInEdge(nFnE);
		rep.put(nEnF, nFnE);
		rep.put(nFnE, nFnE);
		
		for(int i = 0; i<nodes.size(); i++){
			nodes.get(i).setDir(directed);
		}
	}
	
}
