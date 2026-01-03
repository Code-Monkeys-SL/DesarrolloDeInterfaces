package modeloVista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import modeloBD_DAO.CategoriaDAO;
import modeloBD_DAO.PersonalDAO;
import modeloBD_DTO.CategoriaDTO;
import modeloBD_DTO.PersonalDTO;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class Personal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static PersonalDAO Opper = new PersonalDAO();
	private PersonalDTO usuario;
	private static CategoriaDAO Opcat = new CategoriaDAO();
	private JTable table_Personal;

	/**
	 * Create the dialog.
	 */
	public Personal() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 425);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(247, 244, 238));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblControlDePersonal = new JLabel("Control de Personal");
		lblControlDePersonal.setOpaque(true);
		lblControlDePersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlDePersonal.setForeground(Color.WHITE);
		lblControlDePersonal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblControlDePersonal.setBackground(new Color(29, 46, 74));
		lblControlDePersonal.setBounds(182, 20, 350, 40);
		contentPanel.add(lblControlDePersonal);
		
		JScrollPane scrollPane_Table = new JScrollPane();
		scrollPane_Table.setBounds(21, 70, 639, 175);
		contentPanel.add(scrollPane_Table);
		
		table_Personal = new JTable();
		table_Personal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre", "Apellidos", "Telefono", "Correo", "Administrador", "Categoria"
			}
		));
		table_Personal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_Table.setViewportView(table_Personal);
		
		JPanel panel_user = new JPanel();
		panel_user.setBackground(new Color(247, 244, 238));
		panel_user.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Gesti\u00F3n de Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_user.setBounds(21, 261, 429, 74);
		TitledBorder border_user = (TitledBorder) panel_user.getBorder();
        border_user.setTitleFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.add(panel_user);
		panel_user.setLayout(null);
		
		JButton btnNuevoUsuario = new JButton("Nuevo");
		btnNuevoUsuario.setForeground(Color.WHITE);
		btnNuevoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNuevoUsuario.setBackground(new Color(29, 46, 74));
		btnNuevoUsuario.setBounds(10, 30, 94, 21);
		panel_user.add(btnNuevoUsuario);
		
		JButton btnModificarUsuario = new JButton("Modificar");
		btnModificarUsuario.setForeground(Color.WHITE);
		btnModificarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModificarUsuario.setBackground(new Color(29, 46, 74));
		btnModificarUsuario.setBounds(119, 30, 94, 21);
		panel_user.add(btnModificarUsuario);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminar.setBackground(new Color(29, 46, 74));
		btnEliminar.setBounds(223, 31, 94, 21);
		panel_user.add(btnEliminar);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = table_Personal.getSelectedRow();
				if (seleccion == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un usuario para visualizar", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					int id_personal = (int) table_Personal.getValueAt(seleccion, 0);
					usuario = Opper.read(id_personal);
					Empleado ventana = new Empleado(usuario, false);
					ventana.setVisible(true);
				}
			}
		});
		btnVisualizar.setForeground(Color.WHITE);
		btnVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVisualizar.setBackground(new Color(29, 46, 74));
		btnVisualizar.setBounds(326, 31, 94, 21);
		panel_user.add(btnVisualizar);
		
		JPanel panel_category = new JPanel();
		panel_category.setLayout(null);
		panel_category.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Gesti\u00F3n de Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_category.setBackground(new Color(247, 244, 238));
		panel_category.setBounds(466, 261, 194, 74);
		TitledBorder border_category = (TitledBorder) panel_category.getBorder();
        border_category.setTitleFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.add(panel_category);
		
		JButton btnControlDeCategorias = new JButton("Control de Categorias");
		btnControlDeCategorias.setForeground(Color.WHITE);
		btnControlDeCategorias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnControlDeCategorias.setBackground(new Color(29, 46, 74));
		btnControlDeCategorias.setBounds(10, 30, 174, 21);
		panel_category.add(btnControlDeCategorias);
		
		try {
			cargarPersonal();
		} catch (Exception e) {
			System.out.println("Error al cargar la listado de personal");
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(247, 244, 238));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	
	private void cargarPersonal() {
		ArrayList<PersonalDTO> listaPersonal = Opper.readAll();
	    ArrayList<CategoriaDTO> listaCategorias = Opcat.readAll();
	    
	    DefaultTableModel modelo = (DefaultTableModel)table_Personal.getModel();
		while (modelo.getRowCount()>0) modelo.removeRow(0);
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
		table_Personal.setRowSorter(sorter);
		
		int numCols = modelo.getColumnCount();
		
		for (PersonalDTO personal : listaPersonal) {
			String categoriaNombre = "";
	        for (CategoriaDTO categoria : listaCategorias) {
	            if (categoria.getIdCategoria() == personal.getIdCategoria()) {
	                categoriaNombre = categoria.getNombre();
	                break;
	            }
	        }
	        
	        Object [] fila = new Object[numCols];
	        fila[0] = personal.getIdPersonal();
	        fila[1] = personal.getNombre();
	        fila[2] = personal.getApellidos();
	        fila[3] = personal.getTelefono();
	        fila[4] = personal.getCorreo();
	        fila[5] = personal.isAdmin() ? "Sí" : "No";
	        fila[6] = categoriaNombre;
	        modelo.addRow(fila);
	    }
	}
}
