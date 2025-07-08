package vista;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Coordinador;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Coordinador miCoordinador;
	private FondoPanel fondo = new FondoPanel();
	JButton btnGestionarP;
	JButton btnGestionarM;


	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 489);
		setLocationRelativeTo(null);
		setContentPane(fondo);
		getContentPane().setLayout(null);
		

		
		iniciarComponentes();
	
	}
	
	
	
	
	private void iniciarComponentes() {
		JLabel lblTitulo = new JLabel("SISTEMA VETERINARIA ABC");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 10, 680, 30);
		getContentPane().add(lblTitulo);
		
		btnGestionarP = new JButton("Gestionar Personas");
		btnGestionarP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGestionarP.setBounds(51, 358, 196, 40);
		btnGestionarP.addActionListener(this);
		getContentPane().add(btnGestionarP);
		
		btnGestionarM = new JButton("Gestionar Mascotas");
		btnGestionarM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGestionarM.setBounds(445, 358, 196, 40);
		getContentPane().add(btnGestionarM);
		btnGestionarM.addActionListener(this);
		fondo.setLayout(null);
		
	}
	
	class FondoPanel extends JPanel{
		private Image imagen;
		
		@Override
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/imagenes/imgfondo.jpg")).getImage();
		
			int margenS = 50;
			int margenL = 20;
			int margenInferior = 20;

			int ancho = getWidth() - 2 * margenL;
			int alto = getHeight() - margenS - margenInferior;

			g.drawImage(imagen, margenL, margenS, ancho, alto, this);
			setOpaque(false);
			super.paint(g);
			
		}
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnGestionarP) {
			miCoordinador.mostrarVentanaGestionarPersonas();
		} else if (e.getSource()==btnGestionarM) {
			miCoordinador.mostarVentanaGestionMascotas();
		}
		
	}
}
