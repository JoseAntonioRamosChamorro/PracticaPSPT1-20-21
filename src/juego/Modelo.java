package juego;

import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Modelo {
	//conexiones
		public static Connection conectar()
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/juego19_20?autoReconnect=true&useSSL=false";
			String user = "root2";
			String password = "12345678A";
			Connection con = null;
			try {
				// Cargar los controladores para el acceso a la BD
				Class.forName(driver);
				// Establecer la conexión con la BD empresa
				con = DriverManager.getConnection(url, user, password);
				if (con != null) {
					System.out.println("Conectado a la base de datos");
				}
			} catch (SQLException ex) {
				System.out.println("ERROR:La dirección no es válida o el usuario y clave");
				ex.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				System.out.println("Error 1-" + cnfe.getMessage());
			}
			return con;
		}
		public static void desconectar(Connection con)
		{
			try
			{
				con.close();
			}
			catch(Exception e) {}
		}
		//Game
		public void insertar(Connection con, String nombreJugador, int puntos) {
			try {

				Statement sta = con.createStatement();
				String cadenaSQL = "INSERT INTO ganador (`NombreGanador`, `PuntosGanador`)"
						+ "VALUES ('" + nombreJugador + "','"+ puntos +"')";
				System.out.println(cadenaSQL);
				sta.executeUpdate(cadenaSQL);
				sta.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al crear", "Atención", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}

		}
		//Mejor jugador
		public static void rellenarTextArea(Connection con, TextArea t) {
			String sqlSelect = "SELECT * FROM ganador ORDER BY PuntosGanador DESC LIMIT 10;";

			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sqlSelect);
				while (rs.next()) 
				{
					if(t.getText().length()==0)
					{
						t.setText(rs.getString("NombreGanador")+">Tiene:"
								+rs.getInt("PuntosGanador")+" puntos");
					}
					else
					{
						t.setText(t.getText() + "\n" +
								rs.getString("NombreGanador")+">Tiene:"
								+rs.getInt("PuntosGanador")+" puntos");
					}
				}
				rs.close();
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("ERROR:al consultar");
				ex.printStackTrace();
			}}
}
