import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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
public class Display extends JFrame implements KeyListener{
	public ArrayList<Graph> graphs;
	public ArrayList<Edge> currEdges;
	public int currEdge;
	public boolean onNode;
	public Node currNode;
	public int currGr;
	public boolean dynamic;

	SynthesizerModeDesc desc;
	Synthesizer synthesizer;
	Voice voice;


	public Display(){
		setTitle("Graph");
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		graphs = new ArrayList<Graph>();
	}

	public void paint(Graphics g){
//		System.out.println("Display");
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 1000, 1000);
		if(graphs.size()>0){
			for(int i = 0; i<graphs.get(currGr).nodes.size(); i++){
//				System.out.print("Drawing ");
				graphs.get(currGr).nodes.get(i).paintComponent(g);
			}
			for(int i = 0; i<graphs.get(currGr).edges.size(); i++){
//				System.out.print("Drawing ");
				graphs.get(currGr).edges.get(i).paintComponent(g);
			}
		}

	}


	public static void main(String[] args) throws Exception{
		Display d = new Display();
		d.setUp();
		d.repaint();
		d.init("kevin16");
	}
	public void setUp(){
		Graph g1 = new Graph();
		addKeyListener(this);
		g1.loadGraph1();
		add(g1);
		graphs.add(g1);
		currEdge = 0;
		onNode = true;
		currGr = 0;
		currNode = graphs.get(currGr).nodes.get(0);
		currNode.setSel(true);
		onNode = true;
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
					if(currGr != 0 && graphs.get(currGr-1).map.get(currNode.getLoc())!=null){
						currNode = graphs.get(currGr-1).map.get(currNode.getLoc());
						currGr--;
						String s = "Graph "+(currGr+1)+" of "+graphs.size();
						s += " Current node "+currNode.toString();
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
						String s = "No node at current location in past graph";
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
				//old interaction which places at top?
				else{
					
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
					//Move to the node in the future graph
					if(currGr != graphs.size()-1 && graphs.get(currGr+1).map.get(currNode.getLoc())!=null){
						currNode = graphs.get(currGr+1).map.get(currNode.getLoc());
						currGr++;
						String s = "Graph "+(currGr+1)+" of "+graphs.size();
						s += " Current node "+currNode.toString();
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
					else if(currGr != graphs.size()-1){
						String s = "No node at current location in future graph";
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
				// TODO Implement this
				//old interaction which places at top?
				else{
					
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_KP_UP || e.getKeyCode() == KeyEvent.VK_UP){
			if(onNode){
				currEdges = currNode.getInEdges();
				currNode.setSel(false);
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
				currNode = currEdges.get(currEdge).start;
				currNode.setSel(true);
				System.out.println(currNode);
				try {
					doSpeak(currNode.toString());
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
				currNode.setSel(false);
				currEdges = currNode.getOutEdges();
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
				currNode = currEdges.get(currEdge).end;
				currNode.setSel(true);
				System.out.println(currNode);
				try {
					doSpeak(currNode.toString());
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
				s = currNode.toString();
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
				s += " Current "+currNode.toString();
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
		repaint();
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
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
