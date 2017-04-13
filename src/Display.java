import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
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


	public Display(){
		setTitle("Graph");
		setSize(1200,1200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		graphs = new ArrayList<Graph>();
		
	}

//	public void paint(Graphics g){
//		if(graphs.size()>0){
//			graphs.get(currGr).paint(g);
//		}	
//	}


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
//		buttons = new JPanel();
//		buttons.setLayout(new GridLayout(3,2));
//		buttons.setSize(1200, 200);
//		JButton t1 = new JButton("Task 1");
//		t1.setActionCommand("Task1");
//		JButton t2 = new JButton("Task 2");
//		t2.setActionCommand("Task2");
//		JButton t3 = new JButton("Task 3");
//		t3.setActionCommand("Task3");
//		JButton t4 = new JButton("Task 4");
//		t4.setActionCommand("Task4");
//		JButton t5 = new JButton("Task 5");
//		t5.setActionCommand("Task5");
//		JButton t6 = new JButton("Task 6");
//		t6.setActionCommand("Task6");
//		t1.addActionListener(this);
//		t2.addActionListener(this);
//		t3.addActionListener(this);
//		t4.addActionListener(this);
//		t5.addActionListener(this);
//		t6.addActionListener(this);
//		t1.getAccessibleContext().setAccessibleName("Task 1");
//		buttons.add(t1);
//		buttons.add(t2);
//		buttons.add(t3);
//		buttons.add(t4);
//		buttons.add(t5);
//		buttons.add(t6);
		currGraph = new Graph();
//		pack();
	}
	
	public void setUpGraphs(int taskNum){
		Graph g1 = new Graph();
		Graph g2 = new Graph();
		if(taskNum == 1){
			g1.loadGraph1();
			g1.setCurrNode(g1.nodes.get(0));
			g1.getCurrNode().setSel(true);
			g2.loadGraph2();
			g2.setCurrNode(g2.nodes.get(0));
			g2.getCurrNode().setSel(true);
		}
		
		graphs.add(g1);
		graphs.add(g2);
		currEdge = 0;
		onNode = true;
		currGr = 0;
		//graphs.get(currGr).setCurrNode(graphs.get(currGr).nodes.get(0));
		//graphs.get(currGr).getCurrNode().setSel(true);
		currGraph = graphs.get(currGr);
		add(currGraph);
		currGraph.repaint();
		onNode = true;
		requestFocusInWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		synthesizer.cancel();
		if(e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT){
			if(!onNode && !e.isShiftDown()){
				graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
				currEdge = (currEdge-1+currEdges.size())%currEdges.size();
				graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(true);
				String s = edgeInfo();
				System.out.println(s);
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
				System.out.println("Go to past!");
				//TODO Implement this 
				//new interaction which places on node in same loc in past
				if(dynamic){
					//Move to the node in the past graph
					System.out.println("Dynamic");
					if(currGr != 0 && graphs.get(currGr-1).map.get(graphs.get(currGr).getCurrNode().getLoc())!=null){
						//get the node in the same loc in the past graph
						System.out.println("To Past in dynamic");
						graphs.get(currGr).getCurrNode().setSel(false);
						graphs.get(currGr-1).setCurrNode(graphs.get(currGr-1).map.get(graphs.get(currGr).getCurrNode().getLoc()));
						currGr--;
						graphs.get(currGr).getCurrNode().setSel(true);
						currGraph = graphs.get(currGr);
						String s = "Graph "+(currGr+1)+" of "+graphs.size();
						s += " Current node "+graphs.get(currGr).getCurrNode().toString();
						System.out.println(s);
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
						System.out.println(s);
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
				System.out.println(s);
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
				System.out.println("Go to future");
				//new interaction which places on node in same loc in future
				if(dynamic){
//					System.out.println("Dynmamic");
					//Move to the node in the future graph
					
					if(currGr != graphs.size()-1 && graphs.get(currGr+1).map.get(graphs.get(currGr).getCurrNode().getLoc())!=null){
						//Get the node at the same loc in the future
						System.out.println("Dynamic in future");
						graphs.get(currGr).getCurrNode().setSel(false);
						graphs.get(currGr+1).setCurrNode(graphs.get(currGr+1).map.get(graphs.get(currGr).getCurrNode().getLoc()));
						currGr++;
						graphs.get(currGr).getCurrNode().setSel(true);
						currGraph = graphs.get(currGr);
						String s = "Graph "+(currGr+1)+" of "+graphs.size();
						s += " Current node "+graphs.get(currGr).getCurrNode().toString();
						System.out.println(s);
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
						System.out.println("No node in future graph");
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
						System.out.println(s);
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
						System.out.println(s);
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
				currEdges = graphs.get(currGr).getCurrNode().getInEdges();
				graphs.get(currGr).getCurrNode().setSel(false);
				currEdge = 0;
				graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(true);
				String s = edgeInfo();
				System.out.println(s);
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
				onNode =  true;
				graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
				graphs.get(currGr).setCurrNode(currEdges.get(currEdge).start);
				graphs.get(currGr).getCurrNode().setSel(true);
				System.out.println(graphs.get(currGr).getCurrNode());
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
				graphs.get(currGr).getCurrNode().setSel(false);
				currEdges = graphs.get(currGr).getCurrNode().getOutEdges();
				currEdge = 0;
				graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(true);
				String s = edgeInfo();
				System.out.println(s);
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
				onNode =  true;
				graphs.get(currGr).rep.get(currEdges.get(currEdge)).setSel(false);
				graphs.get(currGr).setCurrNode(currEdges.get(currEdge).end);
				graphs.get(currGr).getCurrNode().setSel(true);
				System.out.println(graphs.get(currGr).getCurrNode());
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
		}
		if(e.getKeyCode() == KeyEvent.VK_1){
			setUpGraphs(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_2){
			setUpGraphs(2);
		}
		if(e.getKeyCode() == KeyEvent.VK_3){
			setUpGraphs(3);
		}
		if(e.getKeyCode() == KeyEvent.VK_4){
			setUpGraphs(4);
		}
		if(e.getKeyCode() == KeyEvent.VK_5){
			setUpGraphs(5);
		}
		if(e.getKeyCode() == KeyEvent.VK_6){
			setUpGraphs(6);
		}
		currGraph.repaint();
	}
	/*
	 * Returns string w/ edge name and location in edge list (index start at 1)
	 */
	public String edgeInfo(){
		String s = currEdges.get(currEdge).toString() + " " + (currEdge+1) + " of "+currEdges.size();
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

	public void doSpeak(String speakText) 
			throws EngineException, AudioException, IllegalArgumentException, 
			InterruptedException 
	{
		synthesizer.speakPlainText(speakText, null);

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub

	}

	
}
