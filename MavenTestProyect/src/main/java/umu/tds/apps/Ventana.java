package umu.tds.apps;

import java.awt.Frame;

import javax.swing.*;

public class Ventana extends JFrame{
	
	//constructor
	public Ventana(){
	//	setSize(500,300); //para el tamaño de la ventana
	//	setLocation(700,350); // para la posicion dentro de la pantalla
		
		setBounds(580,280,500,300); //para marcar el tamaño y la posicion de la ventana a la vez
				// x, y, ancho,alto
		
		setResizable(false); //para poder redimensionar la pantalla, hacerla mas grande la venta(true) o no(false)
		
	//	setExtendedState(Frame.MAXIMIZED_BOTH); //para hacer la ventana en pantalla completa
		
		setTitle("AppChatPorno ;)");
	}
	
}
