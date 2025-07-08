package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import dao.MascotaDao;
import dto.MascotaDto;
import principal.Coordinador;

public class VentanaGestionMascotas extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    private Coordinador miCoordinador;  

    private JTextField textIdDueño;
    private JTextField textRaza;
    private JTextField textNombre;
    private JTextField textSexo;
    private JTextArea textArea;

    JButton btnResgistrar;
    JButton btnConsultar;
    JButton btnEliminar;
    JButton btnActualizar;
    JButton btnConsultarLista;

    public VentanaGestionMascotas(VentanaPrincipal parent) {
        super(parent, true);
        setBounds(100, 100, 493, 517);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JLabel lblGestionM = new JLabel("GESTIONAR MASCOTAS");
        lblGestionM.setHorizontalAlignment(SwingConstants.CENTER);
        lblGestionM.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblGestionM.setBounds(0, 10, 476, 30);
        getContentPane().add(lblGestionM);

        JLabel lblIdDueño = new JLabel("ID Dueño:");
        lblIdDueño.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblIdDueño.setBounds(20, 74, 82, 21);
        getContentPane().add(lblIdDueño);

        textIdDueño = new JTextField();
        textIdDueño.setBounds(112, 77, 161, 19);
        getContentPane().add(textIdDueño);

        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRaza.setBounds(290, 74, 66, 21);
        getContentPane().add(lblRaza);

        textRaza = new JTextField();
        textRaza.setBounds(332, 77, 137, 19);
        getContentPane().add(textRaza);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNombre.setBounds(42, 121, 66, 21);
        getContentPane().add(lblNombre);

        textNombre = new JTextField();
        textNombre.setBounds(112, 124, 161, 19);
        getContentPane().add(textNombre);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSexo.setBounds(290, 121, 66, 21);
        getContentPane().add(lblSexo);

        textSexo = new JTextField();
        textSexo.setBounds(332, 124, 137, 19);
        getContentPane().add(textSexo);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(64, 128, 128));
        separator.setBounds(42, 193, 427, 2);
        getContentPane().add(separator);

        btnResgistrar = new JButton("Registrar");
        btnResgistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnResgistrar.setBounds(42, 199, 204, 21);
        btnResgistrar.addActionListener(this);
        getContentPane().add(btnResgistrar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnConsultar.setBounds(262, 199, 204, 21);
        btnConsultar.addActionListener(this);
        getContentPane().add(btnConsultar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnActualizar.setBounds(42, 230, 204, 21);
        btnActualizar.addActionListener(this);
        getContentPane().add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEliminar.setBounds(262, 230, 204, 21);
        btnEliminar.addActionListener(this);
        getContentPane().add(btnEliminar);

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
        
        JLabel lblNewLabel = new JLabel("Para buscar todas las mascotas de un dueño busque solo con el id del dueño, ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(38, 152, 438, 35);
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("y una mascota en especifico el id del duelo y el nombre de la mascota");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(38, 174, 438, 21);
        getContentPane().add(lblNewLabel_1);
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnResgistrar) {
            registrarMascota();
        } else if( e.getSource()== btnConsultarLista) {
        	consultarListaMascotas();
        }else if (e.getSource()==btnEliminar) {
        	eliminarMascota();
        }else if (e.getSource()==btnActualizar) {
        	actualizarMascota();
        }else if(e.getSource()==btnConsultar) {
        	consultarMascota();
        }
    }

    private void registrarMascota() {
        if (miCoordinador == null) {
            JOptionPane.showMessageDialog(this, "ERROR: Coordinador no asignado.");
            return;
        }

        MascotaDto mascota = new MascotaDto();
        mascota.setNombre(textNombre.getText());
        mascota.setRaza(textRaza.getText());
        mascota.setSexo(textSexo.getText().toLowerCase());
        mascota.setId_dueño(textIdDueño.getText());

        if (mascota.getNombre().trim().isEmpty() ||
            mascota.getRaza().trim().isEmpty() ||
            mascota.getSexo().trim().isEmpty() ||
            mascota.getId_dueño().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Long.parseLong(mascota.getId_dueño());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del dueño debe contener solo números", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sexo = mascota.getSexo();
        if (!sexo.equals("masculino") && !sexo.equals("femenino")) {
            JOptionPane.showMessageDialog(null, "El sexo debe ser 'masculino' o 'femenino'", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String resultado = miCoordinador.registrarMascota(mascota);

        if (resultado.equals("ok")) {
            JOptionPane.showMessageDialog(null, "Mascota registrada exitosamente");

            textNombre.setText("");
            textRaza.setText("");
            textSexo.setText("");
            textIdDueño.setText("");

            textArea.setText("Nombre: " + mascota.getNombre() + "\n");
            textArea.append("Raza: " + mascota.getRaza() + "\n");
            textArea.append("Sexo: " + mascota.getSexo() + "\n");
            textArea.append("Dueño (Documento): " + mascota.getId_dueño() + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Error: " + resultado, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    private void consultarMascota() {
        String idDueño = textIdDueño.getText().trim();
        String nombre = textNombre.getText().trim();

        if (idDueño.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes ingresar el ID del dueño.");
            return;
        }

        ArrayList<MascotaDto> lista = miCoordinador.consultarMascota(idDueño, nombre);
        textArea.setText("");

        if (lista.isEmpty()) {
            textArea.setText("No se encontraron mascotas con esos datos.");
        } else {
            if (!nombre.isEmpty() && lista.size() == 1) {
                MascotaDto mascota = lista.get(0);
                textNombre.setText(mascota.getNombre());
                textRaza.setText(mascota.getRaza());
                textSexo.setText(mascota.getSexo());
                textIdDueño.setText(mascota.getId_dueño());
            } else {
                textNombre.setText("");
                textRaza.setText("");
                textSexo.setText("");
            }

            for (MascotaDto mascota : lista) {
                textArea.append("Nombre: " + mascota.getNombre() + "\n");
                textArea.append("Raza: " + mascota.getRaza() + "\n");
                textArea.append("Sexo: " + mascota.getSexo() + "\n");
                textArea.append("Dueño (Documento): " + mascota.getId_dueño() + "\n");
                textArea.append("//////////////////////////////////////////////\n");
            }
        }
    }




    
    private void actualizarMascota() {
        MascotaDto mascota = new MascotaDto();
        mascota.setNombre(textNombre.getText());
        mascota.setRaza(textRaza.getText());
        mascota.setSexo(textSexo.getText());
        mascota.setId_dueño(textIdDueño.getText());

        if (textNombre.getText().trim().isEmpty() ||
            textRaza.getText().trim().isEmpty() ||
            textSexo.getText().trim().isEmpty() ||
            textIdDueño.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Ningún campo puede estar vacío");
        } else {
            String resultado = miCoordinador.actualizarMascota(mascota);

            if ("ok".equals(resultado)) {
                JOptionPane.showMessageDialog(null, "La mascota se actualizó correctamente");
                textArea.setText("Nombre: " + mascota.getNombre() + "\n");
                textArea.append("Raza: " + mascota.getRaza() + "\n");
                textArea.append("Sexo: " + mascota.getSexo() + "\n");
                textArea.append("Dueño (Documento): " + mascota.getId_dueño() + "\n");
            }
        }
    }
    
    
    private void eliminarMascota() {
        String idDueño = textIdDueño.getText().trim();
        String nombre = textNombre.getText().trim();

        if (idDueño.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el ID del dueño y el nombre de la mascota", "ERROR", JOptionPane.WARNING_MESSAGE);
        } else {
            MascotaDto mascota = new MascotaDto();
            mascota.setId_dueño(idDueño);
            mascota.setNombre(nombre);

            String resultado = miCoordinador.eliminarMascota(mascota);

            if ("ok".equals(resultado)) {
                JOptionPane.showMessageDialog(null, "Mascota eliminada correctamente.");

                textNombre.setText("");
                textRaza.setText("");
                textSexo.setText("");
                textIdDueño.setText("");
                textArea.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la mascota", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    
    private void consultarListaMascotas() {
        MascotaDao dao = new MascotaDao();
        ArrayList<MascotaDto> lista = dao.consultarListaMascotas();

        textArea.setText(""); 

        if (lista.isEmpty()) {
            textArea.setText("No existen mascotas registradas.");
        } else {
            for (MascotaDto mascota : lista) {
                textArea.append("Nombre: " + mascota.getNombre() + "\n");
                textArea.append("Raza: " + mascota.getRaza() + "\n");
                textArea.append("Sexo: " + mascota.getSexo() + "\n");
                textArea.append("Dueño (Documento): " + mascota.getId_dueño() + "\n");
                textArea.append("//////////////////////////////////////////////\n");
            }
        }
    }
}
