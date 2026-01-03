package modeloVista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexionBD.ConexionSGL;
import modeloBD_DAO.PersonalDAO;
import modeloBD_DTO.PersonalDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ConexionSGL conn = ConexionSGL.getInstancia();
	private static PersonalDAO Opper = new PersonalDAO();
	private PersonalDTO usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(247, 244, 238));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGF = new JLabel("Gestión de Fichajes");
		lblGF.setForeground(new Color(255, 255, 255));
		lblGF.setOpaque(true);
		lblGF.setBackground(new Color(29, 46, 74));
		lblGF.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGF.setHorizontalAlignment(SwingConstants.CENTER);
		lblGF.setBounds(62, 30, 350, 40);
		contentPane.add(lblGF);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(103, 101, 75, 15);
		contentPane.add(lblEmail);
		
		JTextField tfEmail = new JTextField();
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfEmail.setBounds(191, 100, 195, 19);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasenia.setBounds(103, 141, 75, 15);
		contentPane.add(lblContrasenia);
		
		JPasswordField pfContrasenia = new JPasswordField();
		pfContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pfContrasenia.setBounds(191, 140, 195, 19);
		contentPane.add(pfContrasenia);
		
		JButton btnLogIn = new JButton("Iniciar Sesión");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String email = tfEmail.getText().trim();
					String pass = String.valueOf(pfContrasenia.getPassword()).trim();
					if (email.isEmpty() || pass.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Error!, No se puede iniciar sesión sin rellenar el correo y la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
			        	return;
					}
					
					usuario = Opper.login(email, pass);
					if (usuario == null) {
						JOptionPane.showMessageDialog(null, "No se ha podido iniciar sesión, el correo o la contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						Empleado ventana = new Empleado(usuario, true);
						ventana.setVisible(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogIn.setBackground(new Color(29, 46, 74));
		btnLogIn.setForeground(new Color(255, 255, 255));
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogIn.setBounds(257, 192, 109, 21);
		contentPane.add(btnLogIn);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn.cerrarConexion();
				System.exit(0);
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalir.setBackground(new Color(128, 0, 32));
		btnSalir.setBounds(123, 192, 90, 21);
		contentPane.add(btnSalir);

	}
}
