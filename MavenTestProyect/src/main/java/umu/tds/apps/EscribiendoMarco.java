package umu.tds.apps;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public class EscribiendoMarco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoConTexto ventana =  new MarcoConTexto();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para cerrar el programa cuando le demos a la X
		ventana.setResizable(true);
	}

}


class MarcoConTexto extends JFrame{
	
	//constructor
	public MarcoConTexto(){
		
		Toolkit mipantalla = Toolkit.getDefaultToolkit(); //para ver la resolucion de mi pantalla
		Dimension tamanoPantalla = mipantalla.getScreenSize(); //para tomar las medidas de la pnatlla y ajustar la ventana
		
		int altura = tamanoPantalla.height; //cuando haya calculado el alto de mi pantalla
		int ancho = tamanoPantalla.width; //cuando haya calculado el ancho de mi pantalla
		
		setSize(ancho/2, altura/2); //tamaño de la ventana
		setLocation(ancho/4, altura/4); //donde se va a craer la pantalla ( en el centro )
		
		setTitle("AppChatPorno ;)");
		Image icono = mipantalla.getImage("src/test/resources/foto.jpg");
		setIconImage(icono); //poner el icono de la aplicacion
		
		Lamina miLamina = new Lamina();
		add(miLamina); //para añadir la lamina a la ventana 
		miLamina.setBackground(new Color(247,14,208)); //para pintar el fondo de la lamina
		//miLamina.setForeground(Color.GREEN.brighter()); //para indicar que todo lo que se escriba y pinte sea de un color en concreto
	}
	
}


class Lamina extends JPanel{
	
	 public void paintComponent(Graphics g) {
		 		 
		 super.paintComponent(g);
		 
		 // para dibujar
		 
		// g.drawRect(270, 25, 185, 340); //dibujar un cuadrado/rectangulo		 
		 g.drawLine(270, 60, 455, 60); //para dibujar una linea
		 // otra forma de hacer un rectangulo
		 Graphics2D g2 = (Graphics2D) g;
		 Rectangle2D rect = new Rectangle2D.Double(270, 25, 185, 340);
		 g2.setPaint(Color.BLACK);
		 g2.draw(rect);
		 Rectangle2D rect2 = new Rectangle2D.Double(85, 60, 185, 305);
		 g2.setPaint(Color.BLACK);
		 g2.draw(rect2);	
		 Rectangle2D rect3 = new Rectangle2D.Double(455, 60, 185, 305);
		 g2.setPaint(Color.BLACK);
		 g2.draw(rect3);
		 
		 
		 
		 // para escribir un comentario en la ventana	
		 Font fuente = new Font("Courier",Font.BOLD,10);
		 g2.setFont(fuente);
		 g2.drawString("Bienvenidos a Vanilla Unicorn ;)", 280, 50);
		 
		 
 		 // para incluir fotos
		 
		 try {
			 imagen = ImageIO.read(new File("src/test/resources/trevor.jpg"));
		 }catch(IOException e){
			 System.out.println("la imagen no se encuentra");
		 }
		 int nuevoAncho = 185; 
         int nuevoAlto = 305;  
		 g.drawImage(imagen,85,60,nuevoAncho,nuevoAlto,this);
		 
		 try {
			 imagen2 = ImageIO.read(new File("src/test/resources/trevor2.jpg"));
		 }catch(IOException e){
			 System.out.println("la imagen no se encuentra");
		 }
		 int nuevoAncho2 = 185; 
         int nuevoAlto2 = 305;
		 g.drawImage(imagen2,455,60,nuevoAncho2,nuevoAlto2,this);
		 
		 try {
			 imagen3 = ImageIO.read(new File("src/test/resources/xxx.jpg"));
		 }catch(IOException e){
			 System.out.println("la imagen no se encuentra");
		 }
		 int nuevoAncho3 = 185; 
         int nuevoAlto3 = 305;  
		 g.drawImage(imagen3,270,60,nuevoAncho3,nuevoAlto3,this);
	 }
	 
	 private Image imagen;
	 private Image imagen2;
	 private Image imagen3;
}