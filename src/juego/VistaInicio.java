package juego;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;

public class VistaInicio extends Frame{
	private static final long serialVersionUID = 1L;
	Button BtnNP = new Button("Nueva Partida");
	Button BtnMJ = new Button("Top 10");
	Button ayuda = new Button("Ayuda");
	Panel pn1 = new Panel();
	public VistaInicio() {
		setTitle("Inicio"); 
		setLocation(550, 300); 
		setLocationRelativeTo(null);
		setSize(100, 150); 
		setResizable(false);
		add(pn1);
		pn1.add(BtnNP);
		pn1.add(BtnMJ);
		pn1.add(ayuda);
		setVisible(true);
	}
}
