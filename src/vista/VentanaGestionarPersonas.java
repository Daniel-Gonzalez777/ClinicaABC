package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;
import dao.PersonaDao;
import dto.PersonaDto;
import principal.Coordinador;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextArea;

public class VentanaGestionarPersonas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	Coordinador miCoordinador;
	private JTextField textDocumento;
	private JTextField textTelefono;
	private JTextField textNombre;
	JButton btnResgistrar;
	JButton btnConsultar;
	JButton btnEliminar;
	JButton btnActualizar;
	JButton btnConsultarLista;
	JTextArea textArea;

	
	/**
	 * Create the dialog.
	 */
	public VentanaGestionarPersonas(VentanaPrincipal parent) {
		super(parent, true); 
		setBounds(100, 100, 493, 517);
		setLocationRelativeTo(parent); 
		getContentPane().setLayout(null);
		
	
		
		
		iniciarComponentes();
	}


	private void iniciarComponentes() {
		JLabel lblGestionP = new JLabel("GESTIONAR PERSONAS");
		lblGestionP.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionP.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestionP.setBounds(0, 10, 476, 30);
		getContentPane().add(lblGestionP);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDocumento.setBounds(20, 74, 82, 21);
		getContentPane().add(lblDocumento);
		
		textDocumento = new JTextField();
		textDocumento.setBounds(112, 77, 115, 19);
		getContentPane().add(textDocumento);
		textDocumento.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefono.setBounds(262, 74, 66, 21);
		getContentPane().add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(338, 77, 131, 19);
		getContentPane().add(textTelefono);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(42, 121, 66, 21);
		getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(112, 124, 357, 19);
		getContentPane().add(textNombre);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(64, 128, 128));
		separator.setBounds(42, 165, 427, 2);
		getContentPane().add(separator);
		
		btnResgistrar = new JButton("Resgistrar");
		btnResgistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnResgistrar.setBounds(38, 177, 204, 21);
		btnResgistrar.addActionListener(this);
		getContentPane().add(btnResgistrar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConsultar.setBounds(262, 177, 204, 21);
		btnConsultar.addActionListener(this);
		getContentPane().add(btnConsultar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminar.setBounds(262, 213, 204, 21);
		btnEliminar.addActionListener(this);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnActualizar.setBounds(38, 215, 204, 21);
		btnActualizar.addActionListener(this);
		getContentPane().add(btnActualizar);
		
		btnConsultarLista = new JButton("Consultar Lista");
		btnConsultarLista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConsultarLista.setBounds(38, 261, 431, 21);
		btnConsultarLista.addActionListener(this);
		getContentPane().add(btnConsultarLista);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(42, 292, 427, 175);
		getContentPane().add(scrollPane);
		}
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnResgistrar) {
			registrarPersona();
	        //cargarResgistros();
		}else if(e.getSource()==btnConsultar) {
			consultaPersonas();
		}else if(e.getSource()==btnActualizar) {
			actualiarPersona();
		}else if(e.getSource()==btnEliminar) {
			eliminarPersona();
		}else if(e.getSource()==btnConsultarLista) {
			consultarListaPersona();
		}
		
	}

	private void consultarListaPersona() {
	    PersonaDao dao = new PersonaDao();
	    ArrayList<PersonaDto> lista = dao.consultarListaPersona();

	    textArea.setText(""); 

	    if (lista.isEmpty()) {
	        textArea.setText("No existen personas registradas.");
	    } else {
	        for (PersonaDto persona : lista) {
	        	textArea.append("Documento: " + persona.getDocumento() + "\n");
	            textArea.append("Nombre: " + persona.getNombre() + "\n");
	            textArea.append("Teléfono: " + persona.getTelefono() + "\n");
	            textArea.append("//////////////////////////////////////////////"+"\n");
	        }
	    }
	}

		


	private void eliminarPersona() {
	    String documento = textDocumento.getText().trim();
	    if (textDocumento.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Seleciione a alguien","ERROR", JOptionPane.WARNING_MESSAGE);
	    }else {

	    PersonaDto persona = new PersonaDto();
	    persona.setDocumento(documento);
	    
	    String resultado = miCoordinador.eliminarPersona(persona);

	    if ("ok".equals(resultado)) {
	        JOptionPane.showMessageDialog(null, "Persona eliminada correctamente.");

	        textDocumento.setText("");
	        textNombre.setText("");
	        textTelefono.setText("");
            textArea.setText("");

	    } else {
	        JOptionPane.showMessageDialog(null, "No se pudo eliminar la persona", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}


	private void actualiarPersona() {
		PersonaDto persona = new PersonaDto();
		persona.setDocumento(textDocumento.getText());
		persona.setNombre(textNombre.getText());
		persona.setTelefono(textTelefono.getText());
		
		if(textDocumento.getText().trim().isEmpty() ||
		textNombre.getText().trim().isEmpty() ||
		textTelefono.getText().trim().isEmpty()){
			
			JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio");
		}else {
			
			String resultado = miCoordinador.actualizarPersona(persona);
			
			if("ok".equals(resultado)) {
				JOptionPane.showMessageDialog(null, "La persona se actualizo correctamente");
				textArea.setText("Documento: " + persona.getDocumento() + "\n");
	            textArea.append("Nombre: " + persona.getNombre() + "\n");
	            textArea.append("Teléfono: " + persona.getTelefono() + "\n");
			}
		}
	}


	

	private void consultaPersonas() {
	    String documento = textDocumento.getText().trim();

	    if (!documento.isEmpty()) {
	        PersonaDto persona = miCoordinador.consultarPersona(documento);

	        if (persona != null) {
	        	textDocumento.setText(persona.getDocumento());
		        textNombre.setText(persona.getNombre());
		        textTelefono.setText(persona.getTelefono());
	            textArea.setText("Documento: " + persona.getDocumento() + "\n");
	            textArea.append("Nombre: " + persona.getNombre() + "\n");
	            textArea.append("Teléfono: " + persona.getTelefono() + "\n");
	        } else {
	            textArea.setText("No se encontró una persona con ese documento.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Por favor ingresa un documento válido.");
	    }
	}


	private void registrarPersona() {
	    PersonaDto persona = new PersonaDto();
	    persona.setDocumento(textDocumento.getText());
	    persona.setNombre(textNombre.getText());
	    persona.setTelefono(textTelefono.getText());

	    if (textDocumento.getText().trim().isEmpty() || 
	        textNombre.getText().trim().isEmpty() || 
	        textTelefono.getText().trim().isEmpty()) {
	        
	        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "ERROR", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    try {
	        Long.parseLong(persona.getDocumento());
	        Long.parseLong(persona.getTelefono());
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Documento y teléfono deben ser solo números", "ERROR", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    String resultado = miCoordinador.resgistrarPersona(persona);

	    if (resultado.equals("ok")) {
	        JOptionPane.showMessageDialog(null, "Persona registrada exitosamente");

	        textDocumento.setText("");
	        textNombre.setText("");
	        textTelefono.setText("");

	        textArea.setText("Documento: " + persona.getDocumento() + "\n");
	        textArea.append("Nombre: " + persona.getNombre() + "\n");
	        textArea.append("Teléfono: " + persona.getTelefono() + "\n");
	    } else {
	        JOptionPane.showMessageDialog(null, "Error: " + resultado);
	    }
	}
}
	