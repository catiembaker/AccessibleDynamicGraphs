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
	public ArrayList<Edge> currEdges;
	public int currEdge;
	public boolean onNode;
	public int currGr;
	public boolean dynamic;
//	public JPanel buttons;
	public Graph currGraph;
	SynthesizerModeDesc desc;
	Synthesizer synthesizer;
	Voice voice;
	public boolean searchMode;
	public String search;
	public File file;
	public BufferedWriter writer;
	public double scaleFactor;
	public boolean out;

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
//		System.out.println(scaleFactor);
		setSize((int)(1500*scaleFactor),(int)(1500*scaleFactor));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		graphs = new ArrayList<Graph>();
		search = "";
		file = new File("log.txt");
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
		//d.setLayout(new GridLayout(2,1));
//		d.add(d.buttons);
		d.setVisible(true);
//		d.add(d.currGraph);
		//d.repaint();
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
		//String s = "Task "+taskNum;
		try {
			writer.write("Task "+taskNum);
			writer.newLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		currEdge = 0;
		onNode = true;
		currGr = 0;
		currGraph = graphs.get(currGr);
		add(g1);
		graphs.get(currGr).repaint();
//		currGraph.repaint();
//		onNode = true;
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
//		if(synthesizer.getEngineState()==synthesizer.QUEUE_NOT_EMPTY){
		synthesizer.cancel();
		if(!searchMode){
			if(e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT){
				if(!onNode && !e.isShiftDown()){
					graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
					currEdge = (currEdge-1+currEdges.size())%currEdges.size();
					graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(true);
					String s = edgeInfo();
//					System.out.println(s);
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
				else if(onNode && e.isShiftDown()){
//					System.out.println("Go to past!");
					//TODO Implement this 
					//new interaction which places on node in same loc in past
					if(dynamic){
						//Move to the node in the past graph
//						System.out.println("Dynamic");
						if(currGr != 0 && graphs.get(currGr-1).map.get(graphs.get(currGr).getCurrNode().getLoc())!=null){
							//get the node in the same loc in the past graph
//							System.out.println("To Past in dynamic");
							graphs.get(currGr-1).getCurrNode().setSel(false);
							graphs.get(currGr-1).setCurrNode(graphs.get(currGr-1).map.get(graphs.get(currGr).getCurrNode().getLoc()));
							currGr--;
							graphs.get(currGr).getCurrNode().setSel(true);
							currGraph = graphs.get(currGr);
							String s = "Graph "+(currGr+1)+" of "+graphs.size();
							s += " Current node "+graphs.get(currGr).getCurrNode().toString();
//							System.out.println(s);
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
							s += " Current node "+graphs.get(currGr).getCurrNode().toString();
//							System.out.println(s);
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
			if(e.getKeyCode() == KeyEvent.VK_KP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(!onNode && !e.isShiftDown()){
					graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
					currEdge = (currEdge+1)%currEdges.size();
					graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(true);
					String s = edgeInfo();
//					System.out.println(s);
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
				else if(onNode && e.isShiftDown()){
//					System.out.println("Go to future");
					//new interaction which places on node in same loc in future
					if(dynamic){
						//					System.out.println("Dynmamic");
						//Move to the node in the future graph

						if(currGr != graphs.size()-1 && graphs.get(currGr+1).map.get(graphs.get(currGr).getCurrNode().getLoc())!=null){
							//Get the node at the same loc in the future
//							System.out.println("Dynamic in future");
//							graphs.get(currGr).getCurrNode().setSel(false);
							graphs.get(currGr+1).getCurrNode().setSel(false);
							graphs.get(currGr+1).setCurrNode(graphs.get(currGr+1).map.get(graphs.get(currGr).getCurrNode().getLoc()));
							currGr++;
							graphs.get(currGr).getCurrNode().setSel(true);
							currGraph = graphs.get(currGr);
							String s = "Graph "+(currGr+1)+" of "+graphs.size();
							s += " Current node "+graphs.get(currGr).getCurrNode().toString();
//							System.out.println(s);
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
//							System.out.println("No node in future graph");
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
//							System.out.println(s);
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
							//						remove(currGraph);
							currGraph = graphs.get(currGr);

							add(currGraph);
							//						requestFocussInWindow();
							String s = "Graph "+(currGr+1)+" of "+graphs.size();
							s += " Current node "+graphs.get(currGr).getCurrNode().toString();
//							System.out.println(s);
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
			if(e.getKeyCode() == KeyEvent.VK_KP_UP || e.getKeyCode() == KeyEvent.VK_UP){
				if(onNode){
					if(graphs.get(currGr).getCurrNode().getInEdges().size()>0){
						out = false;
						currEdges = graphs.get(currGr).getCurrNode().getInEdges();
						graphs.get(currGr).getCurrNode().setSel(false);
						currEdge = 0;
						graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(true);
						String s = edgeInfo();
//						System.out.println(s);
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
						onNode = false;
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
				else{
					onNode =  true;
					graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
					graphs.get(currGr).setCurrNode(currEdges.get(currEdge).start);
					graphs.get(currGr).getCurrNode().setSel(true);
//					System.out.println(graphs.get(currGr).getCurrNode());
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
			if(e.getKeyCode() == KeyEvent.VK_KP_DOWN || e.getKeyCode() == KeyEvent.VK_DOWN){
				if(onNode){
					if(graphs.get(currGr).getCurrNode().getOutEdges().size()>0){
						out = true;
						graphs.get(currGr).getCurrNode().setSel(false);
						currEdges = graphs.get(currGr).getCurrNode().getOutEdges();
						currEdge = 0;
						graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(true);
						String s = edgeInfo();
//						System.out.println(s);
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
						onNode = false;
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
				else{
					onNode =  true;
					graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
					graphs.get(currGr).setCurrNode(currEdges.get(currEdge).end);
					graphs.get(currGr).getCurrNode().setSel(true);
//					System.out.println(graphs.get(currGr).getCurrNode());
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
			if(e.getKeyCode() == KeyEvent.VK_R){
				//Repeat current info
				String s;
				if(onNode){
					s = graphs.get(currGr).getCurrNode().toString();
				}
				else{
					s = edgeInfo();
				}
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
				String s = "Graph "+(currGr+1)+" of "+graphs.size();
				s += graphs.get(currGr).nodes.size() +" Nodes and " + graphs.get(currGr).edges.size() + " Edges";
				if(onNode){
					s += " Current "+graphs.get(currGr).getCurrNode().toString();
				}
				else{
					s += " Current "+edgeInfo();
				}
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
			if(e.getKeyCode() == KeyEvent.VK_D){
				dynamic = !dynamic;
				String s = "";
				if(dynamic){
					s = "Spatial Location Mode";
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
			if(e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()){
				searchMode = true;
				String s = "Type the name of the node you want to jump to and press enter";
				search="";
//				System.out.println(s);
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
		else{
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				searchMode = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				searchMode = false;
				boolean found = false;
				for(int i = 0; i<graphs.get(currGr).nodes.size(); i++){
					if(graphs.get(currGr).nodes.get(i).getName().contains(search)){
						found = true;
						if(onNode){
							graphs.get(currGr).getCurrNode().setSel(false);
						}
						else{
							graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
							onNode = true;
						}
						graphs.get(currGr).setCurrNode(graphs.get(currGr).nodes.get(i));
						graphs.get(currGr).getCurrNode().setSel(true);
						String s = graphs.get(currGr).getCurrNode().toString();
//						System.out.println(s);
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
//					System.out.println(s);
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
	 * Returns string w/ edge name and location in edge list (index start at 1)
	 */
	public String edgeInfo(){
		String s = currEdges.get(currEdge).toString() + " " + (currEdge+1) + " of "+currEdges.size();
		if(out){
			s += " out edges.";
		}
		else{
			s += " in edges.";
		}
		return s;
	}
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
		synthesizer.speakPlainText(speakText, null);
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

	@Override
	public void keyTyped(KeyEvent e) {
		if(searchMode && (Character.isLetterOrDigit(e.getKeyChar()) || Character.isWhitespace(e.getKeyChar()))){
//			System.out.println(e.getKeyChar());
			search += e.getKeyChar();
		}
		else if(!searchMode){
			
		}
	}

	
}
