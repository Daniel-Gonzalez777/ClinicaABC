package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexion.Conexion;
import dto.MascotaDto;
import dto.PersonaDto;

public class MascotaDao {
	
	public String registrarMascota(MascotaDto mascota) {
	    Conexion conexion = Conexion.getInstance();

	    String verificar = "SELECT 1 FROM personas WHERE documento = ?";
	    String insertar = "INSERT INTO mascotas (nombre, raza, sexo, id_dueño) VALUES (?, ?, ?, ?)";

	    try (
	        Connection conn = conexion.getConnection();
	        PreparedStatement stmtVerificar = conn.prepareStatement(verificar);
	    ) {
	        stmtVerificar.setString(1, mascota.getId_dueño());
	        ResultSet rs = stmtVerificar.executeQuery();

	        if (!rs.next()) {
	            return "El dueño no está registrado.";
	        }

	        try (PreparedStatement stmt = conn.prepareStatement(insertar)) {
	            stmt.setString(1, mascota.getNombre());
	            stmt.setString(2, mascota.getRaza());
	            stmt.setString(3, mascota.getSexo());
	            stmt.setString(4, mascota.getId_dueño());
	           

	            stmt.executeUpdate();
	            return "ok";
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	public ArrayList<MascotaDto> consultarMascota(String idDueño, String nombre) {
	    ArrayList<MascotaDto> lista = new ArrayList<>();
	    Conexion conexion = Conexion.getInstance();
	    String consulta;

	    if (!idDueño.isEmpty() && !nombre.isEmpty()) {
	        consulta = "SELECT * FROM mascotas WHERE id_dueño = ? AND nombre = ?";
	    } else if (!idDueño.isEmpty()) {
	        consulta = "SELECT * FROM mascotas WHERE id_dueño = ?";
	    } else {
	        return lista; 
	    }

	    try (Connection connection = conexion.getConnection();
	         PreparedStatement statement = connection.prepareStatement(consulta)) {

	        if (!idDueño.isEmpty() && !nombre.isEmpty()) {
	            statement.setString(1, idDueño);
	            statement.setString(2, nombre);
	        } else {
	            statement.setString(1, idDueño);
	        }

	        ResultSet result = statement.executeQuery();

	        while (result.next()) {
	            MascotaDto mascota = new MascotaDto();
	            mascota.setNombre(result.getString("nombre"));
	            mascota.setRaza(result.getString("raza"));
	            mascota.setSexo(result.getString("sexo"));
	            mascota.setId_dueño(result.getString("id_dueño"));
	            lista.add(mascota);
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al buscar sus mascotas: " + e.getMessage());
	    }

	    return lista;
	}



	
	
	public String actualizarMascota(MascotaDto mascota) {
	    Conexion conexion = Conexion.getInstance();
	    String consulta = "UPDATE mascotas SET raza = ?, sexo = ? WHERE id_dueño = ? AND nombre = ?";
	    String respuesta;

	    try (Connection connection = conexion.getConnection();
	         PreparedStatement statement = connection.prepareStatement(consulta)) {

	    	statement.setString(1, mascota.getRaza());
	        statement.setString(2, mascota.getSexo());
	        statement.setString(3, mascota.getId_dueño());
	        statement.setString(4, mascota.getNombre());
	        statement.execute();

	        respuesta = "ok";
	    } catch (Exception e) {
	        e.printStackTrace();
	        respuesta = "error";
	    }

	    return respuesta;
	}
	
	
	
	public String eliminarMascota(MascotaDto mascota) {
	    Conexion conexion = Conexion.getInstance();
	    String consulta = "DELETE FROM mascotas WHERE id_dueño = ? AND nombre = ?";
	    String respuesta;

	    try (Connection connection = conexion.getConnection();
	         PreparedStatement statement = connection.prepareStatement(consulta)) {

	        statement.setString(1, mascota.getId_dueño());
	        statement.setString(2, mascota.getNombre());
	        statement.execute();

	        respuesta = "ok";
	    } catch (SQLException e) {
	        e.printStackTrace();
	        respuesta = "error";
	    }

	    return respuesta;
	}

	
	
	public ArrayList<MascotaDto> consultarListaMascotas() {
	    ArrayList<MascotaDto> listaMascotas = new ArrayList<>();
	    Conexion conexion = Conexion.getInstance();
	    String consulta = "SELECT * FROM mascotas";
	    
	    try (
	        Connection connection = conexion.getConnection();
	        PreparedStatement statement = connection.prepareStatement(consulta);
	        ResultSet resultSet = statement.executeQuery()
	    ) {
	        while (resultSet.next()) {
	            MascotaDto mascota = new MascotaDto();
	            mascota.setNombre(resultSet.getString("nombre"));
	            mascota.setRaza(resultSet.getString("raza"));
	            mascota.setSexo(resultSet.getString("sexo"));
	            mascota.setId_dueño(resultSet.getString("id_dueño"));
	            listaMascotas.add(mascota);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al consultar la lista de mascotas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    return listaMascotas;
	}



}
