package umu.tds.apps;


import javax.swing.*;

public class VentanaNueva {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ventana ventana = new Ventana();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para cerrar el programa cuando le demos a la X
		//ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //para ocultar la venta cuando tengamos mas de 1 ventana

	}

}

