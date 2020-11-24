package juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;




public class Controlador implements WindowListener{
	static String nick;
	//vistas
	VistaInicio vInicio= null;
	VistaMejorJugador vMejorJugador = null;
	VistaPedirNombre vPedirNombre = null;
	VistaGameSolo vGameSolo = null;
	//modelo
	Modelo modelo = null;
	public Controlador(VistaInicio vInicio, VistaMejorJugador vMejorJugador,
			VistaPedirNombre vPedirNombre, VistaGameSolo vGameSolo, Modelo modelo) {
		this.vInicio = vInicio;
		this.vMejorJugador = vMejorJugador;
		this.vPedirNombre = vPedirNombre;
		this.vGameSolo = vGameSolo;
		this.modelo = modelo;
		vInicio.addWindowListener(this);
		vMejorJugador.addWindowListener(this);
		vPedirNombre.addWindowListener(this);
		vGameSolo.addWindowListener(this);

		//Inicio
		vInicio.ayuda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					Runtime.getRuntime().exec("hh.exe AyudaJuego.chm");
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}

			}
		});

		vInicio.BtnMJ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vMejorJugador.consulta.setText("");
				Connection con = Modelo.conectar();
				Modelo.rellenarTextArea(con, vMejorJugador.consulta);
				Modelo.desconectar(con);
				vMejorJugador.setVisible(true);


			}
		});
		vInicio.BtnNP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vPedirNombre.setVisible(true);

			}
		});
		//MejorJugador
		vMejorJugador.btVolver.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				vMejorJugador.setVisible(false);

			}
		});
		//PedirNombre
		vPedirNombre.btnComenzar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VistaPedirNombre.name = vPedirNombre.txpl1.getText();
				vGameSolo.setVisible(true);
				vPedirNombre.setVisible(false);
				vGameSolo.jugador = VistaPedirNombre.name;
				vGameSolo.nombre.setText(vGameSolo.jugador);
				vPedirNombre.txpl1.setText("");
			}
		});
		vPedirNombre.btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vPedirNombre.setVisible(false);

			}
		});

		//Juego	

		vGameSolo.btnStart.addActionListener(new ActionListener() {
			int puntos=0;
			int terminar =0;
			int numrondas = 5;
			int punt= puntos;


			@Override
			public void actionPerformed(ActionEvent e) {


				System.out.println();
				String elec1 = vGameSolo.chpj1.getSelectedItem();
				terminar= terminar+1;
				vGameSolo.jugador =  VistaPedirNombre.name;
				String jug =vGameSolo.jugador;
				//eleccion random


				Random rand = new Random();
				java.util.List<String> Lista = Arrays.asList("Piedra", "Papel", "Tijeras");
				int EleccionAle = rand.nextInt(Lista.size());
				String PcElec = Lista.get(EleccionAle);          

				//gana
				if (elec1 =="Seleccionar 1") {
					JOptionPane.showMessageDialog(null, "Seleccione una jugada menos esta "+jug);
				}
				else if (elec1=="Piedra" && PcElec == "Tijeras") {
					JOptionPane.showMessageDialog(null, "Usted Gano "+jug);
					punt=punt+1;
				}else if (elec1=="Papel" && PcElec == "Piedra") {
					JOptionPane.showMessageDialog(null, "Usted Gano "+jug);
					punt=punt+1;
				}else if (elec1=="Tijeras" && PcElec == "Papel") {
					JOptionPane.showMessageDialog(null, "Usted Gano "+jug);
					punt=punt+1;
				}//pierde
				else if (elec1=="Tijeras" && PcElec == "Piedra") {
					JOptionPane.showMessageDialog(null, "Usted Perdio "+jug);
					punt=punt-1;
				}else if (elec1=="Piedra" && PcElec == "Papel") {
					JOptionPane.showMessageDialog(null, "Usted Perdio "+jug);
					punt=punt-1;
				}else if (elec1=="Papel" && PcElec == "Tijeras") {
					JOptionPane.showMessageDialog(null, "Usted Perdio "+jug);
					punt=punt-1;
				}//empate
				else if (elec1=="Papel" && PcElec == "Papel") {
					JOptionPane.showMessageDialog(null, "Usted Empato "+jug);
				}else if (elec1=="Piedra" && PcElec == "Piedra") {
					JOptionPane.showMessageDialog(null, "Usted Empato "+jug);
				}else if (elec1=="Tijeras" && PcElec == "Tijeras") {
					JOptionPane.showMessageDialog(null, "Usted Empato "+jug);
				}

				System.out.println(punt);
				if (terminar == numrondas) {
					JOptionPane.showMessageDialog(null, "Se termino la partida y tiene "+punt+ " punto/s");
					vGameSolo.setVisible(false);
					Connection con = Modelo.conectar();
					modelo.insertar(con,jug,punt);
					punt= 0;
					terminar =0;
					numrondas = 5;
					Modelo.desconectar(con);

				}
			}
		});
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosing(WindowEvent e) {
		vInicio.setVisible(false);
		vGameSolo.setVisible(false);
		vMejorJugador.setVisible(false);
		vPedirNombre.setVisible(false);
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
