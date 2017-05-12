import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import javax.swing.*;
/*
 * Controls the visual display, the self-voicing and keyboard interactions
 * with the graph
 */
public class Display extends JFrame implements  KeyListener{
	public ArrayList<Graph> graphs;
	public ArrayList<ArrayList<Edge>> currEdges;
	public ArrayList<ArrayList<Node>> currNodes;
	public ArrayList<Integer> currEdge;
	public int currGr;
	public boolean dynamic;
	public Graph currGraph;
	SynthesizerModeDesc desc;
	Synthesizer synthesizer;
	Voice voice;
	public boolean searchMode;
	public String search;
	public File file;
	public BufferedWriter writer;
	public double scaleFactor;
	public ArrayList<Boolean> out;
	public boolean voicing;
	public ArrayList<Node> from;

	public Display(){
		setTitle("Graph");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		if(width>height){
			scaleFactor = (height/1500.0)*.75;
		}
		else{
			scaleFactor = (width/1500.0)*.75;
		}
		currEdge = new ArrayList<Integer>();
		currEdges = new ArrayList<ArrayList<Edge>>();
		currEdges.add(new ArrayList<Edge>());
		currEdges.add(new ArrayList<Edge>());
		out=new ArrayList<Boolean>();
		out.add(false);
		out.add(false);
		from = new ArrayList<Node>();
		from.add(null);
		from.add(null);
		setSize((int)(1500*scaleFactor),(int)(1500*scaleFactor));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusTraversalKeysEnabled(false);
		voicing = false;
		graphs = new ArrayList<Graph>();
		search = "";
		file = new File("log.txt");
		currNodes = new ArrayList<ArrayList<Node>>();
		currNodes.add(new ArrayList<Node>());
		currNodes.add(new ArrayList<Node>());
		try {
			 writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws Exception{
		Display d = new Display();
		d.setUp();
		d.setVisible(true);
		d.init("kevin16");
		
		
	}
	public void setUp(){
		addKeyListener(this);
	}
	
	public void setUpGraphs(int taskNum){
		Graph g1 = new Graph(scaleFactor);
		Graph g2 = new Graph(scaleFactor);
		g1.clear();
		g2.clear();
		currNodes.get(0).clear();
		currNodes.get(1).clear();
		currEdges.set(0,null);
		currEdges.set(1, null);
		
		if(taskNum == 1){
			g1.load1a();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.load1b();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		else if(taskNum == 2){
			g1.load2a();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.load2b();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		else if(taskNum == 3){
			g1.load3a();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.load3b();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		else if(taskNum == 4){
			g1.load4a();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.load4b();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		else if(taskNum == 5){
			g1.load5a();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.load5b();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		else if(taskNum == 6){
			g1.load6a();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.load6b();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		else if(taskNum == 7){
			g1.loadGraph1();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.loadGraph2();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		else if(taskNum == 8){
			g1.load8a();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.load8b();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
			graphs.clear();
			graphs.add(g1);
			graphs.add(g2);
		}
		try {
			writer.write("Task "+taskNum);
			writer.newLine();
		} catch (IOException e1) {
			//  Auto-generated catch block
			e1.printStackTrace();
		}
		currEdge.add(0);
		currEdge.add(0);
		currGr = 0;
		currGraph = graphs.get(currGr);
		add(g1);
		graphs.get(currGr).repaint();
		requestFocusInWindow();
		try {
			String s = "Graph "+(currGr+1)+" of "+graphs.size() + ". ";
			s += graphs.get(currGr).nodes.size() +" Nodes and " + graphs.get(currGr).edges.size() + " Edges. ";
			s += " Current "+graphs.get(currGr).getCurrNode().toString();
			doSpeak(s);
		} catch (EngineException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (AudioException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		synthesizer.cancel();
		if(!searchMode){
			if((e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) ){
				if(!e.isShiftDown() && currNodes.get(currGr).size()>0){
					currEdge.set(currGr, (currEdge.get(currGr)-1+currEdges.get(currGr).size()) % currEdges.get(currGr).size());
					graphs.get(currGr).getCurrNode().setSel(false);
					graphs.get(currGr).setCurrNode(currNodes.get(currGr).get(currEdge.get(currGr)));
					graphs.get(currGr).getCurrNode().setSel(true);
					String s = nodeInfo();
					try {
						doSpeak(s);
					} catch (EngineException e1) {
						e1.printStackTrace();
					} catch (AudioException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.isShiftDown()){
					//new interaction which places on node in same loc in past
					if(dynamic){
						//Move to the node in the past graph
						if(currGr != 0 && graphs.get(currGr-1).map.get(graphs.get(currGr).getCurrNode().getLoc())!=null){
							//get the node in the same loc in the past graph
							graphs.get(currGr-1).getCurrNode().setSel(false);
							graphs.get(currGr-1).setCurrNode(graphs.get(currGr-1).map.get(graphs.get(currGr).getCurrNode().getLoc()));
							currGr--;
							currEdges.set(currGr,null);
							currNodes.get(currGr).clear();
							graphs.get(currGr).getCurrNode().setSel(true);
							currGraph = graphs.get(currGr);
							String s = "Graph "+(currGr+1)+" of "+graphs.size();
							s += " Current "+graphs.get(currGr).getCurrNode().toString();
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}

						}
						//There is no node in the past graph
						else if(currGr != 0){
							String s = "No node at current location in past graph. Cannot go to the past";
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						//There is no past graph
						else{
							String s = "No past graph";
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
					//old interaction which places at last visited in that graph
					else{
						if(currGr != 0 ){
							//current node is stored in graph so based on previous visit
							currGr--;
							currGraph = graphs.get(currGr);
							String s = "Graph "+(currGr+1)+" of "+graphs.size();
							s += " Current "+graphs.get(currGr).getCurrNode().toString();
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						//There is no past graph
						else{
							String s = "No past graph";
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
			if((e.getKeyCode() == KeyEvent.VK_KP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) ){
				if(!e.isShiftDown() && currNodes.get(currGr).size()>0){
					currEdge.set(currGr, (currEdge.get(currGr)+1) % currEdges.get(currGr).size());
					graphs.get(currGr).getCurrNode().setSel(false);
					graphs.get(currGr).setCurrNode(currNodes.get(currGr).get(currEdge.get(currGr)));
					graphs.get(currGr).getCurrNode().setSel(true);
					String s = nodeInfo();
					
					try {
						doSpeak(s);
					} catch (EngineException e1) {
						e1.printStackTrace();
					} catch (AudioException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.isShiftDown()){
					//new interaction which places on node in same loc in future
					if(dynamic){
						//Move to the node in the future graph
						if(currGr != graphs.size()-1 && graphs.get(currGr+1).map.get(graphs.get(currGr).getCurrNode().getLoc())!=null){
							//Get the node at the same loc in the future
							graphs.get(currGr+1).getCurrNode().setSel(false);
							graphs.get(currGr+1).setCurrNode(graphs.get(currGr+1).map.get(graphs.get(currGr).getCurrNode().getLoc()));
							currGr++;
							currEdges.set(currGr,null);
							currNodes.get(currGr).clear();
							graphs.get(currGr).getCurrNode().setSel(true);
							currGraph = graphs.get(currGr);
							String s = "Graph "+(currGr+1)+" of "+graphs.size();
							s += " Current "+graphs.get(currGr).getCurrNode().toString();
							add(currGraph);
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						//There is no node in the future graph
						else if(currGr != graphs.size()-1){
							String s = "No node at current location in future graph. Cannot go to the future";
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						//There is no past graph
						else{
							String s = "No future graph";
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
					//old interaction which places at last visited in graph
					else{
						if(currGr != graphs.size()-1 ){
							//current node is stored in graph based on previous visit or initialization
							currGr++;
							currGraph = graphs.get(currGr);

							add(currGraph);
							String s = "Graph "+(currGr+1)+" of "+graphs.size();
							s += " Current "+graphs.get(currGr).getCurrNode().toString();
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						//There is no past graph
						else{
							String s = "No future graph";
							try {
								doSpeak(s);
							} catch (EngineException e1) {
								e1.printStackTrace();
							} catch (AudioException e1) {
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_I){
				if(graphs.get(currGr).getCurrNode().getInEdges().size()>0){
					out.set(currGr, false);
					currNodes.get(currGr).clear();
					currEdges.set(currGr, graphs.get(currGr).getCurrNode().getInEdges());
					for(int i = 0; i< currEdges.get(currGr).size(); i++){
						currNodes.get(currGr).add(currEdges.get(currGr).get(i).start);
					}
					from.set(currGr, graphs.get(currGr).getCurrNode());
					graphs.get(currGr).getCurrNode().setSel(false);
					currEdge.set(currGr, 0);
					graphs.get(currGr).setCurrNode(currEdges.get(currGr).get(currEdge.get(currGr)).start);
					graphs.get(currGr).getCurrNode().setSel(true);
					String s = nodeInfo();
					try {
						doSpeak(s);
					} catch (EngineException e1) {
						e1.printStackTrace();
					} catch (AudioException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else{
					try {
						doSpeak("No in edges");
					} catch (EngineException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (AudioException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_O){
				if(graphs.get(currGr).getCurrNode().getOutEdges().size()>0){
					out.set(currGr, true);
					currNodes.get(currGr).clear();
					graphs.get(currGr).getCurrNode().setSel(false);
					currEdges.set(currGr, graphs.get(currGr).getCurrNode().getOutEdges());
					for(int i = 0; i< currEdges.get(currGr).size(); i++){
						ArrayList<Node> temp = currNodes.get(currGr);
						temp.add(currEdges.get(currGr).get(i).end);
					}
					currEdge.set(currGr,0);
					from.set(currGr, graphs.get(currGr).getCurrNode());
					graphs.get(currGr).setCurrNode(currEdges.get(currGr).get(currEdge.get(currGr)).end);
					graphs.get(currGr).getCurrNode().setSel(true);
					String s = nodeInfo();
					try {
						doSpeak(s);
					} catch (EngineException e1) {
						e1.printStackTrace();
					} catch (AudioException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else{
					try {
						doSpeak("No out edges");
					} catch (EngineException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (AudioException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_R){
				//Repeat current info
				String s;
				s = graphs.get(currGr).getCurrNode().toString();
				s += " Graph "+(currGr+1)+" of "+graphs.size();
				try {
					doSpeak(s);
				} catch (EngineException e1) {
					e1.printStackTrace();
				} catch (AudioException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				//Graph Summary
				String s = "Graph "+(currGr+1)+" of "+graphs.size() + ". ";
				s += graphs.get(currGr).nodes.size() +" Nodes and " + graphs.get(currGr).edges.size() + " Edges";
				s += " Current "+graphs.get(currGr).getCurrNode().toString();
				try {
					doSpeak(s);
				} catch (EngineException e1) {
					e1.printStackTrace();
				} catch (AudioException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_P){
				if(from.get(currGr)!=null){
					graphs.get(currGr).getCurrNode().setSel(false);
					graphs.get(currGr).setCurrNode(from.get(currGr));
					graphs.get(currGr).getCurrNode().setSel(true);
					currNodes.get(currGr).clear();
					currEdges.set(currGr,null);
					try {
						doSpeak(nodeInfo());
					} catch (EngineException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (AudioException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_M){
				dynamic = !dynamic;
				String s = "";
				if(dynamic){
					s = "Relative Location Mode";
				}
				else{
					s = "Previous Location Mode";
				}
				try {
					doSpeak(s);
				} catch (EngineException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (AudioException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1){
				setUpGraphs(1);
			}
			if(e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2){
				setUpGraphs(2);
			}
			if(e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3){
				setUpGraphs(3);
			}
			if(e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4){
				setUpGraphs(4);
			}
			if(e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_NUMPAD5){
				setUpGraphs(5);
			}
			if(e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6){
				setUpGraphs(6);
			}
			if(e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_NUMPAD7){
				setUpGraphs(7);
			}
			if(e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_NUMPAD8){
				setUpGraphs(8);
			}
			if(e.getKeyCode() == KeyEvent.VK_V){
				voicing = !voicing;
				String s = "";
				if(voicing){
					s += "Self voicing mode on";
				}
				else{
					s += "Self voicing mode off";
				}
				try {
					doSpeak(s);
				} catch (EngineException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (AudioException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()){
				searchMode = true;
				String s = "Type the name of the node you want to jump to and press enter";
				search="";
				try {
					doSpeak(s);
				} catch (EngineException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (AudioException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_TAB && !e.isShiftDown()){
				int index = graphs.get(currGr).nodes.indexOf(graphs.get(currGr).getCurrNode());
				graphs.get(currGr).getCurrNode().setSel(false);
				graphs.get(currGr).setCurrNode(graphs.get(currGr).nodes.get((index+1)%graphs.get(currGr).nodes.size()));
				graphs.get(currGr).getCurrNode().setSel(true);
				from.set(currGr, null);
				currNodes.get(currGr).clear();
				currEdges.set(currGr,null);
				try {
					doSpeak(graphs.get(currGr).getCurrNode().toString());
				} catch (EngineException e1) {
					e1.printStackTrace();
				} catch (AudioException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_TAB && e.isShiftDown()){
				int index = graphs.get(currGr).nodes.indexOf(graphs.get(currGr).getCurrNode());
				graphs.get(currGr).getCurrNode().setSel(false);
				graphs.get(currGr).setCurrNode(graphs.get(currGr).nodes.get((index-1+graphs.get(currGr).nodes.size())%graphs.get(currGr).nodes.size()));
				graphs.get(currGr).getCurrNode().setSel(true);
				from.set(currGr, null);
				currNodes.get(currGr).clear();
				currEdges.set(currGr,null);
				try {
					doSpeak(graphs.get(currGr).getCurrNode().toString());
				} catch (EngineException e1) {
					e1.printStackTrace();
				} catch (AudioException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		//In search mode
		else{
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				searchMode = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				searchMode = false;
				boolean found = false;
				for(int i = 0; i<graphs.get(currGr).nodes.size(); i++){
					if(graphs.get(currGr).nodes.get(i).getSearchName().contains(search)){
						found = true;
						graphs.get(currGr).getCurrNode().setSel(false);
						graphs.get(currGr).setCurrNode(graphs.get(currGr).nodes.get(i));
						graphs.get(currGr).getCurrNode().setSel(true);
						String s = graphs.get(currGr).getCurrNode().toString();
						try {
							doSpeak(s);
						} catch (EngineException e1) {
							// Auto-generated catch block
							e1.printStackTrace();
						} catch (AudioException e1) {
							// Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalArgumentException e1) {
							// Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
				if(!found){
					String s = "No node with name: "+search;
					try {
						doSpeak(s);
					} catch (EngineException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (AudioException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					}
				}
				search = "";
			}
		}
		if(currGraph != null){
			currGraph.repaint();
		}
	}
	/*
	 * Creates a string contextualizing the current node
	 * Which node, where connected from 
	 */
	public String nodeInfo(){
		String s = "";
		s = graphs.get(currGr).getCurrNode().toString();
		if(currNodes.get(currGr).size()>0){
			s += " Node " + (currEdge.get(currGr)+1) + " of " + currNodes.get(currGr).size() + ". ";

			if(graphs.get(currGr).directed){
				if(out.get(currGr)){
					s += "Connected via edge from " + currEdges.get(currGr).get(currEdge.get(currGr)).start.getName();
				}
				else{
					s += "Connected via edge to " + currEdges.get(currGr).get(currEdge.get(currGr)).end.getName();
				}
			}
			else{
				if(out.get(currGr)){
					s += "Connected to " + currEdges.get(currGr).get(currEdge.get(currGr)).start.getName();
				}
				else{
					s += "Connected to " + currEdges.get(currGr).get(currEdge.get(currGr)).end.getName();
				}
			}
			if(currEdges.get(currGr).get(currEdge.get(currGr)).getWeight() != 0){
				s += " with edge weight:  " + currEdges.get(currGr).get(currEdge.get(currGr)).getWeight();
			}
			s += ".";
		}
		return s;
	}
	/*
	 * Sets up Freetts
	 */
//	public String edgeInfo(){
//		String s = currEdges.get(currEdge.get(currGr)).toString() + " " + (currEdge.get(currGr)+1) + " of "+currEdges.size();
//		if(out.get(currGr)){
//			s += " out edges.";
//		}
//		else{
//			s += " in edges.";
//		}
//		return s;
//	}
	public void init(String voiceName) 
			throws EngineException, AudioException, EngineStateError, 
			PropertyVetoException {
		if (desc == null) {

			System.setProperty("freetts.voices", 
					"com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

			desc = new SynthesizerModeDesc(Locale.US);
			Central.registerEngineCentral
			("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			synthesizer = Central.createSynthesizer(desc);
			synthesizer.allocate();
			synthesizer.resume();
			SynthesizerModeDesc smd = 
					(SynthesizerModeDesc)synthesizer.getEngineModeDesc();
			Voice[] voices = smd.getVoices();
			Voice voice = null;
			for(int i = 0; i < voices.length; i++) {
				if(voices[i].getName().equals(voiceName)) {
					voice = voices[i];
					break;
				}
			}
			synthesizer.getSynthesizerProperties().setVoice(voice);
		}

	}

	public void terminate() throws EngineException, EngineStateError {
		synthesizer.deallocate();
	}
	/*
	 * Speaks the provided statement and logs in in log.txt
	 */
	public void doSpeak(String speakText) 
			throws EngineException, AudioException, IllegalArgumentException, 
			InterruptedException 
	{
		if(voicing){
			synthesizer.speakPlainText(speakText, null);
		}
		else{
			getAccessibleContext().setAccessibleDescription(speakText);
			requestFocusInWindow();
		}
		
		
		try {
			System.out.println(speakText);
			writer.write(speakText);
			writer.newLine();;
			writer.flush();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub
	}

	/*
	 * Handles keys typed in search mode 
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if(searchMode && (Character.isLetterOrDigit(e.getKeyChar()) || Character.isWhitespace(e.getKeyChar()))){
			search += e.getKeyChar();
		}
		else if(!searchMode){
			
		}
	}

	
}
