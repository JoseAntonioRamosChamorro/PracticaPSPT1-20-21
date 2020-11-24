package Main;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Gestion.ClienteAlta;
import Gestion.ClienteBaja;
import Gestion.ClienteConsulta;
import Gestion.ClienteModificacion;
import Gestion.FacturaAlta;
import Gestion.FacturaBaja;
import Gestion.FacturaConsulta;
import Gestion.FacturaModificacion;
import Gestion.MainGestion;
import Gestion.PanAlta;
import Gestion.PanBaja;
import Gestion.PanConsulta;
import Gestion.PanModificacion;
import Gestion.Principal;
import Gestion.Principalestandar;
import juego.Controlador;
import juego.Modelo;
import juego.VistaGameSolo;
import juego.VistaInicio;
import juego.VistaMejorJugador;
import juego.VistaPedirNombre;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Choice;
import javax.swing.JScrollPane;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCmd;
	private JButton btnTerminar;
	private JLabel lblproces;
	private JButton btnJuego;
	private JButton btngestion;
	private JButton btnpaint;
	private JButton btnblocnotas;
	private JTextArea textAreaCMD= new JTextArea(20, 30);
	private Choice choice;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


	}

	/**
	 * Create the frame.
	 */

	public Main() {
		setBackground(Color.RED);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 601);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		txtCmd = new JTextField();
		txtCmd.setText("cmd");
		GridBagConstraints gbc_txtCmd = new GridBagConstraints();
		gbc_txtCmd.insets = new Insets(0, 0, 5, 5);
		gbc_txtCmd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCmd.gridx = 0;
		gbc_txtCmd.gridy = 0;
		contentPane.add(txtCmd, gbc_txtCmd);
		txtCmd.setColumns(10);

		JButton btnEjecutarcmd = new JButton("Ejecutar");
		btnEjecutarcmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		GridBagConstraints gbc_btnEjecutarcmd = new GridBagConstraints();
		gbc_btnEjecutarcmd.insets = new Insets(0, 0, 5, 5);
		gbc_btnEjecutarcmd.gridx = 1;
		gbc_btnEjecutarcmd.gridy = 0;
		contentPane.add(btnEjecutarcmd, gbc_btnEjecutarcmd);

		btnEjecutarcmd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String CMD = txtCmd.getText() ;
				try {
					// Run “netsh” Windows command
					Process process = Runtime.getRuntime().exec(CMD);
					String temp = "";

					// Get input streams
					BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
					BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

					// Read command standard output
					String s;
					while ((s = stdInput.readLine()) != null) {
						temp +=s + "\n";
					}
					textAreaCMD.setText(temp);				 
					while ((s = stdError.readLine()) != null) {
						textAreaCMD.setText(s);
						temp +=s + "\n";
					}
					textAreaCMD.append(temp);
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}

			}
		});

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		textAreaCMD = new JTextArea();
		scrollPane.setViewportView(textAreaCMD);
		textAreaCMD.setEditable(false);

		btnblocnotas = new JButton("Bloc de Notas");
		GridBagConstraints gbc_btnblocnotas = new GridBagConstraints();
		gbc_btnblocnotas.insets = new Insets(0, 0, 5, 0);
		gbc_btnblocnotas.gridx = 3;
		gbc_btnblocnotas.gridy = 2;
		contentPane.add(btnblocnotas, gbc_btnblocnotas);

		//eventos
		//block notas
		btnblocnotas.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				//sustituir esto
				String ejecutar="C:\\Windows\\notepad.exe";
				String nombre="NotePad";
				try
				{
					Process	p=Runtime.getRuntime().exec(ejecutar);
					btnTerminar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							p.destroy();
							choice.remove(nombre);
						}
					});

					choice.add(nombre);

					System.out.println("Finalizando ejecucion");
				}
				catch (IOException ex)
				{

					System.exit(-1);
				}

			}});


		btnpaint = new JButton("Paint");
		GridBagConstraints gbc_btnpaint = new GridBagConstraints();
		gbc_btnpaint.insets = new Insets(0, 0, 5, 0);
		gbc_btnpaint.gridx = 3;
		gbc_btnpaint.gridy = 3;
		contentPane.add(btnpaint, gbc_btnpaint);
		//paint
		btnpaint.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				//sustituir esto
				String ejecutar="mspaint.exe";
				String nombre="Paint";
				try
				{
					Process	p=Runtime.getRuntime().exec(ejecutar);
					btnTerminar.addActionListener(new ActionListener() {

					
						public void actionPerformed(ActionEvent arg0) {
							p.destroy();
							choice.remove(nombre);
						}
					});

					choice.add(nombre);

					System.out.println("Finalizando ejecucion");
				}
				catch (IOException ex)
				{

					System.exit(-1);
				}


			}
		});

		btngestion = new JButton("Gesti\u00F3n");
		GridBagConstraints gbc_btngestion = new GridBagConstraints();
		gbc_btngestion.insets = new Insets(0, 0, 5, 0);
		gbc_btngestion.gridx = 3;
		gbc_btngestion.gridy = 4;
		contentPane.add(btngestion, gbc_btngestion);
		//gestion
		btngestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				MainGestion mg = new MainGestion();
				PanAlta PA=	new PanAlta();
				PanBaja PB=	new PanBaja();
				PanConsulta PC=	new PanConsulta();
				PanModificacion PM=	new PanModificacion();
				ClienteAlta CA=	new ClienteAlta();
				ClienteConsulta CC=	new	ClienteConsulta();
				ClienteBaja	CB =new ClienteBaja();
				ClienteModificacion CM=	new ClienteModificacion();
				Principal p = new Principal();
				Principalestandar pe = new Principalestandar();
				try {
					FacturaAlta	FA =new FacturaAlta();
					FA.setVisible(false);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				FacturaBaja	FB= new FacturaBaja();
				FacturaConsulta	FC =new FacturaConsulta();
				FacturaModificacion FM =new FacturaModificacion();
				choice.add("Gestion");
				PA.setVisible(false);
				PB.setVisible(false);
				PC.setVisible(false);
				PM.setVisible(false);
				CA.setVisible(false);
				CC.setVisible(false);
				CM.setVisible(false);				
				FB.setVisible(false);
				FC.setVisible(false);
				FM.setVisible(false);
				CB.setVisible(false);
				p.setVisible(false);
				pe.setVisible(false);
				btnTerminar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
					if (choice.getSelectedItem() == "Gestion") {
						choice.remove("Gestion");
						//Cerrar
		
						mg.setVisible(false);
						PA.setVisible(false);
						PB.setVisible(false);
						PC.setVisible(false);
						PM.setVisible(false);
						CA.setVisible(false);
						CC.setVisible(false);
						CM.setVisible(false);				
						FB.setVisible(false);
						FC.setVisible(false);
						FM.setVisible(false);
						CB.setVisible(false);
						p.setVisible(false);
						pe.setVisible(false);
					}else {
						System.out.println("No se selecciono nada");
					}
						

					}
				});

			}
		});

		btnJuego = new JButton("Juego");
		GridBagConstraints gbc_btnJuego = new GridBagConstraints();
		gbc_btnJuego.anchor = GridBagConstraints.SOUTH;
		gbc_btnJuego.insets = new Insets(0, 0, 5, 0);
		gbc_btnJuego.gridx = 3;
		gbc_btnJuego.gridy = 5;
		contentPane.add(btnJuego, gbc_btnJuego);
		//juego
		btnJuego.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VistaInicio VInicio = new VistaInicio();
				VistaMejorJugador VMejorJugador = new VistaMejorJugador();
				VistaPedirNombre VPedirNombre = new VistaPedirNombre();
				VistaGameSolo VGameSolo = new VistaGameSolo();
				//Modelo
				Modelo modelo = new Modelo();
				//Controlador
				new Controlador(VInicio,VMejorJugador,VPedirNombre,VGameSolo,modelo);
				
				choice.add("Juego");
				btnTerminar.addActionListener(new ActionListener() {
					
				
					public void actionPerformed(ActionEvent arg0) {
						if (choice.getSelectedItem() == "Juego") {
							choice.remove("Juego");
							VInicio.setVisible(false);
							VGameSolo.setVisible(false);
							VMejorJugador.setVisible(false);
							VPedirNombre.setVisible(false);
						
						}else {
							System.out.println("No se selecciono nada");
						}
						
						
					}
				});

			}
		});

		lblproces = new JLabel("Procesos Activos");
		lblproces.setForeground(Color.WHITE);
		lblproces.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblproces = new GridBagConstraints();
		gbc_lblproces.insets = new Insets(0, 0, 5, 5);
		gbc_lblproces.gridx = 2;
		gbc_lblproces.gridy = 7;
		contentPane.add(lblproces, gbc_lblproces);

		choice = new Choice();
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.insets = new Insets(0, 0, 5, 5);
		gbc_choice.gridx = 2;
		gbc_choice.gridy = 8;
		contentPane.add(choice, gbc_choice);

		choice.add("Seleccionar uno");

		btnTerminar = new JButton("Terminar");
		GridBagConstraints gbc_btnTerminar = new GridBagConstraints();
		gbc_btnTerminar.insets = new Insets(0, 0, 5, 0);
		gbc_btnTerminar.gridx = 3;
		gbc_btnTerminar.gridy = 8;
		contentPane.add(btnTerminar, gbc_btnTerminar);



	}

}
