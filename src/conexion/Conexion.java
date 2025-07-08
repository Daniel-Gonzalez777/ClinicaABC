package conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Conexion {
	private static Conexion instancia;
	
	/*Base de Datos 
	 * 
	 * create database clinicaabc;
	use clinicaabc;

	create table personas (
    	documento varchar(20) primary key,
    	nombre varchar(100) not null,
    	telefono varchar(20)
	);

	create table mascotas (
    	nombre varchar(100) not null,
    	raza varchar(50),
    	sexo varchar(10) not null,
    	id_dueño varchar(20),
    	foreign key (id_dueño) references personas(documento) on delete cascade
	);
*/

	Properties propiedades = new Properties();
	
	private String nombreBd="";
	private String usuario="";
	private String password="";
	private String url="";
	
	
	private Connection conn = null;
	private boolean primeraConexion = true;
	
	  private Conexion() {
		  cargarCredenciales();
	        conectar();
	  }

	private void conectar() {
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
	            conn = DriverManager.getConnection(url, usuario, password);
	            
	            if (conn != null) {
	                if (primeraConexion) {
	                    JOptionPane.showMessageDialog(null, "Conexion exitosa con la BD: " + nombreBd);
	                    primeraConexion = false;
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "No se pudo conectar con la BD " + nombreBd);
	            }
	            
	        } catch (ClassNotFoundException e) {
	            JOptionPane.showMessageDialog(null, "Ocurre una ClassNotFoundException: " + e.getMessage());
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Ocurre una SQLException: " + e.getMessage() + 
	                "\nVerifique que MySQL esta prendido");
	        }
		
	}

	private void cargarCredenciales() {
	    try {
            propiedades.load(new FileInputStream("src/properties/credenciales.properties"));

            nombreBd = propiedades.getProperty("db.name");
            usuario = propiedades.getProperty("db.user");
            password = propiedades.getProperty("db.password");

            String host = propiedades.getProperty("db.host");
            String port = propiedades.getProperty("db.port");
            String suffix = propiedades.getProperty("db.url_suffix");

            url = "jdbc:mysql://" + host + ":" + port + "/" + nombreBd + suffix;

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de propiedades", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo de propiedades", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
		
	}	
	
	public static Conexion getInstance() {
        if (instancia == null) {
            synchronized (Conexion.class) {
                if (instancia == null) {
                    instancia = new Conexion();
                }
            }
        }
        return instancia;
    }
	
	public Connection getConnection() {
		 try {
	            if (conn == null || conn.isClosed()) {
	                conectar();
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error verificando conexión: " + e.getMessage());
	            conectar();
	        }
	        return conn;
	}
	
	public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
        conn = null;
    }
	
	public void reconectar() {
        desconectar();
        conectar();
    }


}
