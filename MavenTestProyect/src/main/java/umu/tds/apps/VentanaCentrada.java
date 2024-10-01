package umu.tds.apps;

import javax.swing.JFrame;
import java.awt.*;

public class VentanaCentrada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoCentrado ventana = new MarcoCentrado();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para cerrar el programa cuando le demos a la X
	}

}

 class MarcoCentrado extends JFrame{
	
	//constructor
	public MarcoCentrado(){
		
		Toolkit mipantalla = Toolkit.getDefaultToolkit(); //para ver la resolucion de mi pantalla
		Dimension tamanoPantalla = mipantalla.getScreenSize(); //para tomar las medidas de la pnatlla y ajustar la ventana
		
		int altura = tamanoPantalla.height; //cuando haya calculado el alto de mi pantalla
		int ancho = tamanoPantalla.width; //cuando haya calculado el ancho de mi pantalla
		
		setSize(ancho/2, altura/2); //tamaño de la ventana
		setLocation(ancho/4, altura/4); //donde se va a craer la pantalla ( en el centro )
		
		setTitle("AppChatPorno ;)");
		Image icono = mipantalla.getImage("src/test/resources/foto.jpg");
		setIconImage(icono); //poner el icono de la aplicacion
	}
	
}