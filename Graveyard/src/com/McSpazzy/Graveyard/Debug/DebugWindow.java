package com.McSpazzy.Graveyard.Debug;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.McSpazzy.Graveyard.Graveyard;
import javax.swing.ListSelectionModel;

public class DebugWindow {

	private JFrame frame;
	//private Graveyard plugin;
	JList lstPoints = new JList();
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public DebugWindow(Graveyard instance) {
		initialize();
		//plugin = instance;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void loadWindow(){
		//frame.setVisible(true);
	}
	
	public void addSpawnPoint(){
	   String[] ColorList = new String [9];
	    ColorList [0] = "Red";
	    ColorList [1] = "Magenta";
	    ColorList [2] = "Blue";
	    ColorList [3] = "Cyan";
	    ColorList [4] = "Green";
	    ColorList [5] = "Yellow";
	    ColorList [6] = "White";
	    ColorList [7] = "Gray";
	    ColorList [8] = "Black";
	    lstPoints.setListData(ColorList);
	    
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lstPoints.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//JList lstPoints = new JList();
		lstPoints.setBounds(304, 32, 120, 190);
		frame.getContentPane().add(lstPoints);
		
		JLabel lblSpawnPoints = new JLabel("Spawn Points");
		lblSpawnPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpawnPoints.setBounds(304, 11, 120, 20);
		frame.getContentPane().add(lblSpawnPoints);
	}
}
