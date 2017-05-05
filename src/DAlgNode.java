import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * One of the tasks will require a node for the abstracted Dijkstra's Alg task 
 * Thus we add the extra field to store the dist
 */
public class DAlgNode extends Node{

	public String dist;
	
	public DAlgNode(String distance, int xPos, int yPos, String theName, int location){
		super(xPos, yPos, theName, location);
		dist=distance;
	}
	
	public String getDistance(){
		return dist;
	}
	
	public String toString(){
		String s = "Node "+name;
		s += ". Value is "+dist;
		s += ". "+inEdges.size() + " edges.";
		return s;
	}
	
	
	public void paintComponent(Graphics g){
//		System.out.println("DAlg");
//		System.out.println(scaleFactor);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(s);
		if(sel){
			g2.setColor(Color.RED);
		}
		else{
			g2.setColor(Color.BLACK);
		}
		
		g2.drawOval((int)(scaleFactor*x), (int)(scaleFactor*y), (int)(scaleFactor*75), (int)(scaleFactor*75));
		g2.setFont(new Font("Arial",Font.PLAIN, (int)(scaleFactor*24)));
		g2.drawString(name, (int)(scaleFactor*(x+12)), (int)(scaleFactor*(y+32)));
		String s = "" + getDistance();
		g2.drawString(s,(int)(scaleFactor*(x+12)), (int)(scaleFactor*(y+52)));
	}
	
}
