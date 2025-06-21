package umu.tds.apps.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import tds.BubbleText;

public class BurbujaCellRenderer extends JPanel implements ListCellRenderer<BubbleText> {

	private static final long serialVersionUID = 1L;

	public BurbujaCellRenderer() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends BubbleText> list, BubbleText value, int index,
			boolean isSelected, boolean cellHasFocus) {
		add(value);
		return this;
	}

}