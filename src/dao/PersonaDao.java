package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexion.Conexion;
import dto.PersonaDto;

public class PersonaDao {
	
	public String registrarUsuario(PersonaDto miPersonaDto) {
		String resultado="";
		
		Connection connection = null;
		Conexion conexion = Conexion.getInstance();
		PreparedStatement preStatement = null;
		
		connection=conexion.getConnection();
		
	
		String consulta = "INSERT INTO personas (documento, nombre, telefono) VALUES (?, ?, ?)";
		
		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miPersonaDto.getDocumento());
			preStatement.setString(2, miPersonaDto.getNombre());
			preStatement.setString(3, miPersonaDto.getTelefono());
			preStatement.execute();
			
			resultado="ok";
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo registrar el dato: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			resultado="No se pudo registrar el dato "+e.getMessage();
		} finally {
			try {
				if (preStatement != null) {
					preStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return resultado;
	}
	
	public PersonaDto consultarPersona(String documento) {
	    Connection connection = null;
	    Conexion miConexion = Conexion.getInstance();
	    PreparedStatement statement = null;
	    ResultSet result = null;
	    PersonaDto miPersona = null;

	    try {
	        connection = miConexion.getConnection();
	        String consulta = "SELECT * FROM personas WHERE documento = ?";
	        statement = connection.prepareStatement(consulta);
	        statement.setString(1, documento);
	        result = statement.executeQuery();

	        if (result.next()) {
	            miPersona = new PersonaDto();
	            miPersona.setDocumento(result.getString("documento"));
	            miPersona.setNombre(result.getString("nombre"));
	            miPersona.setTelefono(result.getString("telefono"));
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al consultar persona: " + e.getMessage());
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return miPersona;
	}
	
	
	
	public String actualizarPersona(PersonaDto miPersonaDto) {
		Conexion conexion = Conexion.getInstance();
		String consulta = "UPDATE personas SET nombre = ?, telefono = ? WHERE documento = ?";
		String respuesta;
		
		try (Connection connection = conexion.getConnection();
			PreparedStatement statement = connection.prepareStatement(consulta)) {
			
			statement.setString(1, miPersonaDto.getNombre());
			statement.setString(2, miPersonaDto.getTelefono());
			statement.setString(3, miPersonaDto.getDocumento());
			statement.execute();
			
			respuesta = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = "error";
		}
		
		
		return respuesta;
	}
	
	
	public String eliminarPersona(PersonaDto miPersonaDto) {
		Conexion conexion = Conexion.getInstance();
		String consulta = "DELETE FROM personas WHERE documento = ?";
		String respuesta;
		
		try (Connection connection = conexion.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(consulta)){
				
			statement.setString(1, miPersonaDto.getDocumento());
			statement.execute();
				
				respuesta = "ok";
		} catch (SQLException e) {
				e.printStackTrace();
				respuesta= "error";
		}
		return  respuesta;
			
	}
	
	public ArrayList<PersonaDto> consultarListaPersona() {
	    ArrayList<PersonaDto> listaPersonas = new ArrayList<>();
	    Conexion conexion = Conexion.getInstance();
	    String consulta = "SELECT * FROM personas";
	    
	    try (
	        Connection connection = conexion.getConnection();
	        PreparedStatement statement = connection.prepareStatement(consulta);
	        ResultSet resultSet = statement.executeQuery()
	    ) {
	        while (resultSet.next()) {
	            PersonaDto persona = new PersonaDto();
	            persona.setDocumento(resultSet.getString("documento"));
	            persona.setNombre(resultSet.getString("nombre"));
	            persona.setTelefono(resultSet.getString("telefono"));
	            listaPersonas.add(persona);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al consultar la lista de personas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    return listaPersonas;
	}




}
