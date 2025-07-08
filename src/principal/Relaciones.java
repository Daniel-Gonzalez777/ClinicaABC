package principal;

import vista.VentanaPrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexion.Conexion;
import dao.MascotaDao;
import dao.PersonaDao;
import dto.PersonaDto;
import vista.VentanaGestionMascotas;
import vista.VentanaGestionarPersonas;

public class Relaciones {
	
	public void Iniciar() {
		VentanaPrincipal miVentana = new VentanaPrincipal();
		Coordinador miCoordinador = new Coordinador();
		Conexion miConexion = Conexion.getInstance();
		PersonaDao miPersonaDao = new PersonaDao();
		MascotaDao miMacotaDao = new MascotaDao();
		
		miVentana.setCoordinador(miCoordinador);
		miCoordinador.setVentana(miVentana);

		VentanaGestionarPersonas ventanaGestionP = new VentanaGestionarPersonas(miVentana);
		ventanaGestionP.setCoordinador(miCoordinador);
		miCoordinador.setVentanaGestionarPersonas(ventanaGestionP);
		
		VentanaGestionMascotas ventanaGestionM = new VentanaGestionMascotas(miVentana);
		ventanaGestionM.setCoordinador(miCoordinador);
		miCoordinador.setVentanaGestionarMascotas(ventanaGestionM);
		
		miCoordinador.setPersonaDao(miPersonaDao);
		miCoordinador.setMascotaDao(miMacotaDao);
		
		miCoordinador.mostrarVentanaPrincipal();
		
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
        	System.out.println("Desconectando");
        	miConexion.desconectar();
        	}));    
		
	}
	
	
		
}





