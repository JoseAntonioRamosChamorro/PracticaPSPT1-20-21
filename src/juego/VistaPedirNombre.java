package juego;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class VistaPedirNombre extends Frame {
	Button btnComenzar = new Button("Comenzar");
	Button btnVolver = new Button("Volver");
	Label ply1 = new Label("Jugador1");
	 TextField txpl1 = new TextField(5);
	 static String name;
	private static final long serialVersionUID = 1L;
	public VistaPedirNombre() {
		setLayout(new FlowLayout());
		setSize(200,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Nueva Partida");
		add(ply1);
		add(txpl1);
		add(btnComenzar);
		add(btnVolver);
	}
}
