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
	public double scaleFactor;
	public boolean dir;
	public String spokenName;
	
	public Node(String theName, int location){
		x=0;
		y=0;
		sel=false;
		name = theName;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
		loc = location;
	}
	
	public Node(int xPos, int yPos, String theName, int location, String s){
		x=xPos;
		y=yPos;
		sel=false;
		name = theName;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
		loc = location;
		spokenName = s;
	}
	public Node(int xPos, int yPos, String theName, int location){
		x=xPos;
		y=yPos;
		sel=false;
		name = theName;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
		loc = location;
		spokenName = theName;
	}
	
	public Node(int xPos, int yPos, boolean selected, String theName, int location, String s){
		x=xPos;
		y=yPos;
		sel=selected;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
		name = theName;
		loc = location;
		spokenName = s;
	}
	
	public void paintComponent(Graphics g){
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
		
		g2.drawOval((int)(scaleFactor*x), (int)(scaleFactor*y), (int)(scaleFactor*50), (int)(scaleFactor*50));
		g2.setFont(new Font("Arial",Font.BOLD, (int)(scaleFactor*32)));
		g2.drawString(name, (int)(scaleFactor*(x+7)), (int)(scaleFactor*(y+35)));
	}
	public String getSearchName(){
		return  name;
	}
	public int getLoc(){
		return loc;
	}
	public void setScaleFactor(double sf){
		scaleFactor = sf;
	}
	public String toString(){
		String s = "Node "+getName();
		if(dir){
			s += ". " + inEdges.size() + " in edges and " + outEdges.size() + " out edges.";
		}
		else{
			s += ". " + inEdges.size() + " edges.";
		}
		return s;
	}
	
	public String getName(){
		
		return spokenName;
	}
	
	public void setDir(boolean b){
		dir = b;
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
