import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class Edge extends JComponent{
	public Node start;
	public Node end;
	public int weight;
	public Boolean sel;
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	BasicStroke s = new BasicStroke(3);
	public boolean visible;
	public boolean dir;
	public double scaleFactor;
	
	public Edge(Node a, Node b, int sX, int sY, int eX, int eY){
		start = a;
		end = b;
		weight = 0;
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
		visible = true;
		sel=false;
	}
	public Edge(Node a, Node b, int theWeight, int sX, int sY, int eX, int eY){
		start = a;
		end = b;
		weight = theWeight;
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
		visible = true;
		sel=false;
	}
	//To be used to mark visible false in the case of an undirected graph 
	//where the edge will be in twice but only needs to show once
	public Edge(Node a, Node b, Boolean vis){
		start = a;
		end = b;
		weight = 0;
		visible = vis;
		sel=false;
	}
	public Edge(Node a, Node b, int theWeight, Boolean vis){
		start = a;
		end = b;
		weight = theWeight;
		visible = vis;
		sel=false;
	}
	
	public void setSel(Boolean b){
		sel=b;
	}
	
	public void setScaleFactor(double sf){
		scaleFactor = sf;
	}
	
	public String toString(){
		String s = "Edge "+start.name + " " + end.name;
		if(weight != 0){
			s += ". weight: " + weight + ".";
		}
		return s;
	}
	
	
	public void paintComponent(Graphics g){
//		System.out.println("Painting");
		if(visible){
//			System.out.println("Drawing Visible");
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(s);
//			System.out.println(scaleFactor);
			if(sel){
				g2.setColor(Color.RED);
			}
			else{
				g2.setColor(Color.BLACK);
			}
			g2.drawLine((int)(scaleFactor*startX), (int)(scaleFactor*startY), (int)(scaleFactor*endX), (int)(scaleFactor*endY));
			if(weight != 0){
				if(sel){
					g2.setColor(Color.RED);
				}
				else{
					g2.setColor(Color.BLUE);
				}
				g2.setFont(new Font("Arial",Font.PLAIN, (int)(scaleFactor*28)));
				String s = ""+weight;
				g2.drawString(s, (int)(scaleFactor*((startX+endX)/2+15)), (int)(scaleFactor*((startY+endY)/2+25)));
			}	
		}
	}
}
