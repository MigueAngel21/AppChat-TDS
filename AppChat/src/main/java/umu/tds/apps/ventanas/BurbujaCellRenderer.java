package umu.tds.apps.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import tds.BubbleText;
//Clase usada para renderizar las burbujas de chat en la lista de mensajes
public class BurbujaCellRenderer extends JPanel implements ListCellRenderer<BubbleText> {

	private static final long serialVersionUID = 1L;
	
	//Constructor de la clase
	public BurbujaCellRenderer() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
	}

	//Método que devuelve el componente que se va a usar para renderizar cada celda del JList
	@Override
	public Component getListCellRendererComponent(JList<? extends BubbleText> list, BubbleText value, int index,
			boolean isSelected, boolean cellHasFocus) {
		add(value);
		return this;
	}

}