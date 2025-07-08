package principal;

import java.util.ArrayList;

import dao.MascotaDao;
import dao.PersonaDao;
import dto.MascotaDto;
import dto.PersonaDto;
import vista.VentanaGestionMascotas;
import vista.VentanaGestionarPersonas;
import vista.VentanaPrincipal;


public class Coordinador {
	
	private VentanaPrincipal miVentana;
	private VentanaGestionarPersonas ventanaGestionP;
	private VentanaGestionMascotas ventanaGestionM;
    private PersonaDao miPersonaDao;
    private MascotaDao miMascotaDao;
	
	public void setVentana(VentanaPrincipal miVentana) {
	this.miVentana = miVentana;
	}
	
	public void mostrarVentanaPrincipal() {
	    miVentana.setVisible(true); 
	}
	
	public void setVentanaGestionarPersonas(VentanaGestionarPersonas ventanaGestionP) {
	    this.ventanaGestionP = ventanaGestionP;
	}
	
	public void mostrarVentanaGestionarPersonas() {
		ventanaGestionP.setVisible(true);
	}
	
	public void setVentanaGestionarMascotas(VentanaGestionMascotas ventanaGestionM) {
		this.ventanaGestionM = ventanaGestionM;
	}
	
	public void mostarVentanaGestionMascotas() {
	    ventanaGestionM.setVisible(true);
	}
	
	public VentanaPrincipal getVentanaPrincipal() {
		return miVentana;
	}
	
	public void setPersonaDao(PersonaDao personaDao) {
	    this.miPersonaDao = personaDao;
	}
	
	public void setMascotaDao(MascotaDao mascotaDao) {
	    this.miMascotaDao = mascotaDao;
	}

	
	public String resgistrarPersona(PersonaDto persona) {
		return miPersonaDao.registrarUsuario(persona);
	}
	
	public PersonaDto consultarPersona(String documento) {
	    return miPersonaDao.consultarPersona(documento);
	}
	
	public String actualizarPersona(PersonaDto persona) {
		return miPersonaDao.actualizarPersona(persona);
	}
	
	public String eliminarPersona(PersonaDto persona) {
		return miPersonaDao.eliminarPersona(persona);
	}
	
	public ArrayList<PersonaDto> consultarListaPersona() {
		return miPersonaDao.consultarListaPersona();
	}
	
	public String registrarMascota(MascotaDto mascota) {
		return miMascotaDao.registrarMascota(mascota);
	}
	
	public ArrayList<MascotaDto> consultarMascota(String idDueño, String nombre) {
	    return miMascotaDao.consultarMascota(idDueño, nombre);
	}
	
	public String actualizarMascota(MascotaDto mascota) {
	    return miMascotaDao.actualizarMascota(mascota);
	}
	
	public String eliminarMascota(MascotaDto mascota) {
	    return miMascotaDao.eliminarMascota(mascota);
	}
	
	public ArrayList<MascotaDto> consultarListaMascots(){
		return miMascotaDao.consultarListaMascotas();
	}
	
	


}


