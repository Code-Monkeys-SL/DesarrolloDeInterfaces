package modeloVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloBD_DAO.CategoriaDAO;
import modeloBD_DAO.PersonalDAO;
import modeloBD_DTO.CategoriaDTO;
import modeloBD_DTO.PersonalDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class Usuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static CategoriaDAO Opcat = new CategoriaDAO();
	private static PersonalDAO Opper = new PersonalDAO();
	private JTextField tfId;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfTelefono;
	private JTextField tfCorreo;
	private JPasswordField pfContrasenia;
	private JPasswordField pfConfirmar;
	private JCheckBox ckAdmin;
	private JComboBox cbCategoria;
	private JButton actionButton;

	/**
	 * Create the dialog.
	 */
	public Usuario() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 490, 290);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(247, 244, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAccionUser = new JLabel("Action User");
			lblAccionUser.setOpaque(true);
			lblAccionUser.setHorizontalAlignment(SwingConstants.CENTER);
			lblAccionUser.setForeground(Color.WHITE);
			lblAccionUser.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblAccionUser.setBackground(new Color(29, 46, 74));
			lblAccionUser.setBounds(69, 10, 350, 40);
			contentPanel.add(lblAccionUser);
		}
		
		JLabel lblId = new JLabel("Id del Usuario:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(148, 62, 91, 13);
		contentPanel.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfId.setBounds(249, 60, 100, 19);
		contentPanel.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(33, 96, 69, 13);
		contentPanel.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNombre.setColumns(10);
		tfNombre.setBounds(105, 93, 120, 19);
		contentPanel.add(tfNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(245, 96, 69, 13);
		contentPanel.add(lblApellidos);
		
		tfApellidos = new JTextField();
		tfApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfApellidos.setColumns(10);
		tfApellidos.setBounds(320, 93, 120, 19);
		contentPanel.add(tfApellidos);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefono.setBounds(33, 125, 69, 13);
		contentPanel.add(lblTelefono);
		
		tfTelefono = new JTextField();
		tfTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(105, 123, 120, 19);
		contentPanel.add(tfTelefono);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorreo.setBounds(245, 125, 69, 13);
		contentPanel.add(lblCorreo);
		
		tfCorreo = new JTextField();
		tfCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCorreo.setColumns(10);
		tfCorreo.setBounds(320, 123, 120, 19);
		contentPanel.add(tfCorreo);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasea.setBounds(33, 154, 69, 13);
		contentPanel.add(lblContrasea);
		
		pfContrasenia = new JPasswordField();
		pfContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pfContrasenia.setBounds(105, 151, 120, 19);
		contentPanel.add(pfContrasenia);
		
		JLabel lblConfirmar = new JLabel("Confirmar:");
		lblConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConfirmar.setBounds(245, 154, 69, 13);
		contentPanel.add(lblConfirmar);
		
		pfConfirmar = new JPasswordField();
		pfConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pfConfirmar.setBounds(320, 151, 120, 19);
		contentPanel.add(pfConfirmar);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCategoria.setBounds(33, 183, 69, 13);
		contentPanel.add(lblCategoria);
		
		cbCategoria = new JComboBox();
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbCategoria.setBounds(105, 180, 120, 19);
		contentPanel.add(cbCategoria);
		
		cargarCategorias(0);
		
		JLabel lblAdministrador = new JLabel("Permisos:");
		lblAdministrador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdministrador.setBounds(245, 183, 69, 13);
		contentPanel.add(lblAdministrador);
		
		ckAdmin = new JCheckBox("Administrador");
		ckAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ckAdmin.setBounds(320, 180, 110, 21);
		contentPanel.add(ckAdmin);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(247, 244, 238));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				actionButton = new JButton("Añadir Usuario");
				actionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tfNombre.getText().isEmpty() || tfApellidos.getText().isEmpty() ||
			                tfTelefono.getText().isEmpty() || tfCorreo.getText().isEmpty() ||
			                pfContrasenia.getPassword().length == 0 || 
			                pfConfirmar.getPassword().length == 0) {
			                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
			                return;
			            }
						String password = new String(pfContrasenia.getPassword());
			            String confirmPassword = new String(pfConfirmar.getPassword());
			            if (!password.equals(confirmPassword)) {
			                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
			                return;
			            }
			            
			            String nombre_categoria = (String) cbCategoria.getSelectedItem();
			            CategoriaDTO categoria = Opcat.readByName(nombre_categoria);
			            if (categoria == null) {
			                JOptionPane.showMessageDialog(null, "Categoría no encontrada.");
			                return;
			            }
			            int id_categoria = categoria.getIdCategoria(); 
			            
			            PersonalDTO actionUsuario;
			            if (tfId.getText().isEmpty()) {
			                actionUsuario = new PersonalDTO(0, tfNombre.getText(), tfApellidos.getText(), tfTelefono.getText(), tfCorreo.getText(), password,ckAdmin.isSelected(), id_categoria);
			                if (Opper.create(actionUsuario)) {
			                    JOptionPane.showMessageDialog(null, "Usuario añadido exitosamente.");
			                    dispose();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Error al añadir el usuario.");
			                }
			                
			            } else {
			            	int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea modificar este usuario?", 
                                    "Confirmar modificación", JOptionPane.YES_NO_OPTION);
			            	if (confirmacion == JOptionPane.YES_OPTION) {
				                actionUsuario = new PersonalDTO(Integer.parseInt(tfId.getText()), tfNombre.getText(), tfApellidos.getText(), tfTelefono.getText(), tfCorreo.getText(), password, ckAdmin.isSelected(), id_categoria);
				                if (Opper.update(actionUsuario)) {
				                    JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente.");
				                    dispose();
				                } else {
				                    JOptionPane.showMessageDialog(null, "Error al modificar el usuario.");
				                }
			            	}
			            }
					}
				});
				actionButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
				actionButton.setActionCommand("");
				actionButton.setBackground(new Color(29, 46, 74));
				actionButton.setForeground(new Color(255, 255, 255));
				buttonPane.add(actionButton);
				getRootPane().setDefaultButton(actionButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setBackground(new Color(128, 0, 32));
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Usuario(PersonalDTO usuario) {
	    this();
	    actionButton.setText("Modificar Usuario");

	    tfId.setText(usuario.getIdPersonal()+"");
	    tfNombre.setText(usuario.getNombre());
	    tfApellidos.setText(usuario.getApellidos());
	    tfTelefono.setText(usuario.getTelefono());
	    tfCorreo.setText(usuario.getCorreo());
	    pfContrasenia.setText(usuario.getContrasenia());
	    pfConfirmar.setText(usuario.getContrasenia());
	    ckAdmin.setSelected(usuario.isAdmin());
	    cargarCategorias(usuario.getIdCategoria());
	}
	
	private void cargarCategorias(int idSeleccion) {
	    ArrayList<CategoriaDTO> listaCategorias = Opcat.readAll();
	    
	    cbCategoria.removeAllItems();
	    for (CategoriaDTO categoria : listaCategorias) {
	    	cbCategoria.addItem(categoria.getNombre());
	        if (categoria.getIdCategoria() == idSeleccion) {
	        	cbCategoria.setSelectedItem(categoria.getNombre());
	        }
	    }
	}
}
