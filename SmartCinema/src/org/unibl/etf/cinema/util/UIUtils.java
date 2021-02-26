package org.unibl.etf.cinema.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UIUtils {
	public static final Color DEFAULT_MENU_BG_COLOR = new Color(33, 20, 47);
	public static final Font DEFAULT_MENU_FONT = new Font("Arial", Font.PLAIN, 16);
	public static final Color HOVER_MENU_BG_COLOR = new Color(28, 15, 42);
	public static final Font HOVER_MENU_FONT = new Font("Arial", Font.BOLD, 16);
	public static final Color BUTTON_COLOR = new Color(220, 20, 60);
	public static final Color COMBO_BOX_COLOR = new Color(214, 217, 223);
	
	public static final Color ERROR_COLOR = Color.RED;
	public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;

	public static final int MAX_DUZINA = 45;

	public static void dodajCrvenuIvicu(JTextField polje) {
		polje.setBorder(new CompoundBorder(new LineBorder(ERROR_COLOR), new EmptyBorder(0, 5, 0, 0)));
	}

	public static void dodajPodrazumijevanuIvicu(JTextField polje) {
		polje.setBorder(new CompoundBorder(new LineBorder(DEFAULT_BORDER_COLOR), new EmptyBorder(0, 5, 0, 0)));
	}

	public static void dodajCrvenuIvicu(JPasswordField polje) {
		polje.setBorder(new CompoundBorder(new LineBorder(ERROR_COLOR), new EmptyBorder(0, 5, 0, 0)));
	}

	public static void dodajPodrazumijevanuIvicu(JPasswordField polje) {
		polje.setBorder(new CompoundBorder(new LineBorder(DEFAULT_BORDER_COLOR), new EmptyBorder(0, 5, 0, 0)));
	}

	public static List<JTextField> praznaPolja(List<JTextField> polja) {
		List<JTextField> praznaPolja = new ArrayList<>();
		for (JTextField polje : polja) {
			if (polje.getText().isBlank()) {
				praznaPolja.add(polje);
			}
		}
		return praznaPolja;
	}

	public static void postaviIvicePolja(List<JTextField> nevalidnaPolja, List<JTextField> svaPolja) {
		for (JTextField npolje : nevalidnaPolja) {
			UIUtils.dodajCrvenuIvicu(npolje);
		}

		for (JTextField polje : svaPolja) {
			if (!nevalidnaPolja.contains(polje)) {
				UIUtils.dodajPodrazumijevanuIvicu(polje);
			}
		}
	}

	public static void ograniciDuzinuUnosa(KeyEvent e, JTextField polje) {
		UIUtils.ograniciDuzinuUnosa(e, polje, MAX_DUZINA);
	}

	public static void ograniciDuzinuUnosa(KeyEvent e, JTextField polje, int duzina) {
		if (polje.getText().length() > duzina - 1) {
			polje.getToolkit().beep();
			e.consume();
		}
	}

	public static void onemoguciUnosNonDigitKaraktera(KeyEvent e, JTextField polje, boolean prvaNulaDozvoljena) {
		if (!Character.isDigit(e.getKeyChar())
				|| prvaNulaDozvoljena && e.getKeyChar() == '0' && polje.getText().length() == 0) {
			polje.getToolkit().beep();
			e.consume();
		}
	}

	public static void onemoguciUnosNonDigitKarakteraOsimTacke(KeyEvent e, JTextField polje) {
		char keyChar = e.getKeyChar();
		if (!Character.isDigit(keyChar) && keyChar != '.' || keyChar == '.' && polje.getText().contains(".")
				|| (keyChar == '0' || keyChar == '.') && polje.getText().length() == 0) {
			polje.getToolkit().beep();
			e.consume();
		}
	}
}
