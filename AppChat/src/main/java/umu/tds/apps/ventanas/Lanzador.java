package umu.tds.apps.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Lanzador {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//invoca a la ventana de login para abrir la aplicacion
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
