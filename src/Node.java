import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.accessibility.*;
import javax.swing.*;

public class Node extends JComponent{
//public class Node extends JTextArea{

	public int x;
	public int y;
	public int loc;
	public boolean sel; 
	public String name;
	public ArrayList<Edge> inEdges;
	public ArrayList<Edge> outEdges;
	BasicStroke s = new BasicStroke(3);
	
	public Node(String theName, int location){
		x=0;
		y=0;
		sel=false;
		name = theName;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
	}
	
	public Node(int xPos, int yPos, String theName, int location){
		x=xPos;
		y=yPos;
		sel=false;
		name = theName;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
	}
	
	public Node(int xPos, int yPos, boolean selected, String theName, int location){
		x=xPos;
		y=yPos;
		sel=selected;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
		name = theName;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(s);
//		System.out.println("Node "+name);
//		System.out.println(isFocusOwner());
		if(sel){
			g2.setColor(Color.RED);
		}
		else{
			g2.setColor(Color.BLACK);
		}
		
		g2.drawOval(x, y, 50, 50);
		g2.setFont(new Font("Arial",Font.BOLD, 32));
		g2.drawString(name, x+15, y+35);
	}
	

	public String toString(){
		String s = "Node "+name;
		s += " " + inEdges.size() + " in edges and " + outEdges.size() + " out edges.";
		return s;
	}
	
	public void addInEdge(Edge a){
		inEdges.add(a);
	}
	
	public void addOutEdge(Edge a){
		outEdges.add(a);
	}
	
	public ArrayList<Edge> getInEdges(){
		return inEdges;
	}
	
	public ArrayList<Edge> getOutEdges(){
		return outEdges;
	}
	public void setSel(Boolean b){
		sel = b;
	}
	
}
