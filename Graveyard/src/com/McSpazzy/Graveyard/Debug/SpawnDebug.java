package com.McSpazzy.Graveyard.Debug;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SpawnDebug {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public SpawnDebug() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList lstPoints = new JList();
		lstPoints.setBounds(304, 32, 120, 219);
		frame.getContentPane().add(lstPoints);
		
		JLabel lblSpawnPoints = new JLabel("Spawn Points");
		lblSpawnPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpawnPoints.setBounds(304, 11, 120, 20);
		frame.getContentPane().add(lblSpawnPoints);
	}
}
