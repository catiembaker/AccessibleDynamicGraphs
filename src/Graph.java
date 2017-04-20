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
		Node d = new Node(250,700,"X",2);
		nodes.add(d);
		map.put(2, d);
		Node c = new Node(750,700,"C",3);
		nodes.add(c);
		map.put(3, c);
		
		Edge ad = new Edge(a,d,500,275,275,700);
		Edge da = new Edge(d,a,false);
		Edge dc = new Edge(d,c,300,725,750,725);
		Edge cd = new Edge(c,d,false);
		Edge ac = new Edge(a,c,550,275,775,700);
		Edge ca = new Edge(c,a,false);
		edges.add(ad);
		edges.add(ac);
		edges.add(dc);
		rep.put(ad,ad);
		rep.put(da,ad);
		rep.put(ac,ac);
		rep.put(ca,ac);
		rep.put(dc,dc);
		rep.put(cd,dc);
		
		
		a.addInEdge(da);
		a.addInEdge(ca);
		a.addOutEdge(ad);
		a.addOutEdge(ac);
		
		d.addInEdge(ad);
		d.addInEdge(cd);
		d.addOutEdge(da);
		d.addOutEdge(dc);
		
		c.addInEdge(ac);
		c.addInEdge(dc);
		c.addOutEdge(ca);
		c.addOutEdge(cd);
		
		
//		add(a);
//		add(d);
//		add(c);
		
	}
	
	public void load1a(){
		Node n1 = new Node(600,150,"15",1);
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(300,450,"10",2);
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(900,450,"20",3);
		nodes.add(n3);
		map.put(3, n3);
		Node n4 = new Node(150,750,"5",4);
		nodes.add(n4);
		map.put(4, n4);
		Node n5 = new Node(450,750,"13",5);
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(750,750,"18",6);
		nodes.add(n6);
		map.put(6, n6);
		Node n7 = new Node(1050,750,"23",7);
		nodes.add(n7);
		map.put(7, n7);
		Node n8 = new Node(75,1050,"1",8);
		nodes.add(n8);
		map.put(8, n8);
		Node n10 = new Node(375,1050,"12",10);
		nodes.add(n10);
		map.put(10, n10);
		Node n11 = new Node(525,1050,"14",11);
		nodes.add(n11);
		map.put(11, n11);
		Node n12 = new Node(675,1050,"17",12);
		nodes.add(n12);
		map.put(12, n12);
		Node n13 = new Node(825,1050,"19",13);
		nodes.add(n13);
		map.put(13, n13);
		Node n14 = new Node(975,1050,"21",14);
		nodes.add(n14);
		map.put(14, n14);
		Node n15 = new Node(1125,1050,"25",15);
		nodes.add(n15);
		map.put(15, n15);
		
		Edge n1n2 = new Edge(n1,n2,600,187,325,450);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
		
		Edge n1n3 = new Edge(n1,n3,650,187,925,450);
		edges.add(n1n3);
		n1.addOutEdge(n1n3);
		n3.addInEdge(n1n3);
		rep.put(n1n3, n1n3);
		
		Edge n2n4 = new Edge(n2,n4,300,487,175,750);
		edges.add(n2n4);
		n2.addOutEdge(n2n4);
		n4.addInEdge(n2n4);
		rep.put(n2n4, n2n4);
		
		Edge n2n5 = new Edge(n2,n5,350,487,475,750); 
		edges.add(n2n5);
		n2.addOutEdge(n2n5);
		n5.addInEdge(n2n5);
		rep.put(n2n5, n2n5);
		
		Edge n3n6 = new Edge(n3,n6,900,487,775,750);
		edges.add(n3n6);
		n3.addOutEdge(n3n6);
		n6.addInEdge(n3n6);
		rep.put(n3n6, n3n6);
		
		Edge n3n7 = new Edge(n3,n7,950,487,1075,750);
		edges.add(n3n7);
		n3.addOutEdge(n3n7);
		n7.addInEdge(n3n7);
		rep.put(n3n7, n3n7);
		
		Edge n4n8 = new Edge(n4,n8,150,787,100,1050);
		edges.add(n4n8);
		n4.addOutEdge(n4n8);
		n8.addInEdge(n4n8);
		rep.put(n4n8, n4n8);
		
		Edge n5n10 = new Edge(n5,n10,450,787,400,1050);
		edges.add(n5n10);
		n5.addOutEdge(n5n10);
		n10.addInEdge(n5n10);
		rep.put(n5n10, n5n10);
		
		Edge n5n11 = new Edge(n5,n11,500,787,550,1050);
		edges.add(n5n11);
		n5.addOutEdge(n5n11);
		n11.addInEdge(n5n11);
		rep.put(n5n11, n5n11);
		
		Edge n6n12 = new Edge(n6,n12,750,787,700,1050);
		edges.add(n6n12);
		n6.addOutEdge(n6n12);
		n12.addInEdge(n6n12);
		rep.put(n6n12, n6n12);
		
		Edge n6n13 = new Edge(n6,n13,800,787,850,1050);
		edges.add(n6n13);
		n6.addOutEdge(n6n13);
		n13.addInEdge(n6n13);
		rep.put(n6n13, n6n13);
		
		Edge n7n14 = new Edge(n7,n14,1050,787,1000,1050);
		edges.add(n7n14);
		n7.addOutEdge(n7n14);
		n14.addInEdge(n7n14);
		rep.put(n7n14, n7n14);
		
		Edge n7n15 = new Edge(n7,n15,1100,787,1150,1050);
		edges.add(n7n15);
		n7.addOutEdge(n7n15);
		n15.addInEdge(n7n15);
		rep.put(n7n15, n7n15);
				
		
	}
	
	public void load1b(){
		Node n1 = new Node(600,150,"15",1);
		nodes.add(n1);
		map.put(1, n1);
		Node n2 = new Node(300,450,"5",2);
		nodes.add(n2);
		map.put(2, n2);
		Node n3 = new Node(900,450,"20",3);
		nodes.add(n3);
		map.put(3, n3);
		Node n5 = new Node(450,750,"13",5);
		nodes.add(n5);
		map.put(5, n5);
		Node n6 = new Node(750,750,"18",6);
		nodes.add(n6);
		map.put(6, n6);
		Node n7 = new Node(1050,750,"23",7);
		nodes.add(n7);
		map.put(7, n7);
		Node n10 = new Node(375,1050,"12",10);
		nodes.add(n10);
		map.put(10, n10);
		Node n11 = new Node(525,1050,"14",11);
		nodes.add(n11);
		map.put(11, n11);
		Node n12 = new Node(675,1050,"17",12);
		nodes.add(n12);
		map.put(12, n12);
		Node n13 = new Node(825,1050,"19",13);
		nodes.add(n13);
		map.put(13, n13);
		Node n14 = new Node(975,1050,"21",14);
		nodes.add(n14);
		map.put(14, n14);
		Node n15 = new Node(1125,1050,"25",15);
		nodes.add(n15);
		map.put(15, n15);
		
		Edge n1n2 = new Edge(n1,n2,600,187,325,450);
		edges.add(n1n2);
		n1.addOutEdge(n1n2);
		n2.addInEdge(n1n2);
		rep.put(n1n2, n1n2);
		
		Edge n1n3 = new Edge(n1,n3,650,187,925,450);
		edges.add(n1n3);
		n1.addOutEdge(n1n3);
		n3.addInEdge(n1n3);
		rep.put(n1n3, n1n3);
				
		Edge n2n5 = new Edge(n2,n5,350,487,475,750); 
		edges.add(n2n5);
		n2.addOutEdge(n2n5);
		n5.addInEdge(n2n5);
		rep.put(n2n5, n2n5);
		
		Edge n3n6 = new Edge(n3,n6,900,487,775,750);
		edges.add(n3n6);
		n3.addOutEdge(n3n6);
		n6.addInEdge(n3n6);
		rep.put(n3n6, n3n6);
		
		Edge n3n7 = new Edge(n3,n7,950,487,1075,750);
		edges.add(n3n7);
		n3.addOutEdge(n3n7);
		n7.addInEdge(n3n7);
		rep.put(n3n7, n3n7);
		
		
		Edge n5n10 = new Edge(n5,n10,450,787,400,1050);
		edges.add(n5n10);
		n5.addOutEdge(n5n10);
		n10.addInEdge(n5n10);
		rep.put(n5n10, n5n10);
		
		Edge n5n11 = new Edge(n5,n11,500,787,550,1050);
		edges.add(n5n11);
		n5.addOutEdge(n5n11);
		n11.addInEdge(n5n11);
		rep.put(n5n11, n5n11);
		
		Edge n6n12 = new Edge(n6,n12,750,787,700,1050);
		edges.add(n6n12);
		n6.addOutEdge(n6n12);
		n12.addInEdge(n6n12);
		rep.put(n6n12, n6n12);
		
		Edge n6n13 = new Edge(n6,n13,800,787,850,1050);
		edges.add(n6n13);
		n6.addOutEdge(n6n13);
		n13.addInEdge(n6n13);
		rep.put(n6n13, n6n13);
		
		Edge n7n14 = new Edge(n7,n14,1050,787,1000,1050);
		edges.add(n7n14);
		n7.addOutEdge(n7n14);
		n14.addInEdge(n7n14);
		rep.put(n7n14, n7n14);
		
		Edge n7n15 = new Edge(n7,n15,1100,787,1150,1050);
		edges.add(n7n15);
		n7.addOutEdge(n7n15);
		n15.addInEdge(n7n15);
		rep.put(n7n15, n7n15);
				
		
	}
	
	public void load2a(){
		
	}
	public void load2b(){
		
	}
	public void load3a(){
		
	}
	public void load3b(){
		
	}
	public void load4a(){
		
	}
	public void load4b(){
		
	}
	public void load5a(){
		
	}
	public void load5b(){
		
	}
	public void load6a(){
		
	}
	public void load6b(){
		
	}
}
