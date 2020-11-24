package juego;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;



public class VistaGameSolo extends Frame{
	private static final long serialVersionUID = 1L;
	Choice cho = new Choice();
	Panel pntop = new Panel();
	Panel pnmid = new Panel();
	Panel pnbot = new Panel();
	Label lbElegir = new Label("Elige Jugada:");
	Choice chpj1 = new Choice();
	Button btnStart = new Button("Comparar");
	
	Label nombre = new Label();

	String jugador;
	public Object btnVolver;
	
	public VistaGameSolo() {
		
		
		setLayout(new FlowLayout());
		setSize(200,200);
		setResizable(true);
		setLocationRelativeTo(null);
		setTitle("En partida");
		setLayout(getLayout());
		chpj1.add("Seleccionar 1");
		chpj1.add("Piedra");
		chpj1.add("Papel");
		chpj1.add("Tijeras");		
		add(lbElegir);
		add(nombre);
		add(chpj1);
		add(btnStart);
	}
}
