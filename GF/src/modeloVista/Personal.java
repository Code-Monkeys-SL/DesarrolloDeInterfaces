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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import conexionBD.ConexionSGL;
import modeloBD_DAO.CategoriaDAO;
import modeloBD_DAO.PersonalDAO;
import modeloBD_DTO.CategoriaDTO;
import modeloBD_DTO.FichajeDTO;
import modeloBD_DTO.PersonalDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class Personal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static PersonalDAO Opper = new PersonalDAO();
	private PersonalDTO usuario;
	private static CategoriaDAO Opcat = new CategoriaDAO();
	private JTable table_Personal;
	private JComboBox cbOrdenarTabla;
	private JComboBox cbOrdenarTipo;
	private JComboBox<Integer> cbEmp;

	/**
	 * Create the dialog.
	 */
	public Personal() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 460);
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
		panel_user.setBounds(21, 283, 429, 94);
		TitledBorder border_user = (TitledBorder) panel_user.getBorder();
        border_user.setTitleFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.add(panel_user);
		panel_user.setLayout(null);
		
		JButton btnNuevoUsuario = new JButton("Nuevo");
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario ventana = new Usuario();
				ventana.setVisible(true);
				cargarPersonal();
			}
		});
		btnNuevoUsuario.setForeground(Color.WHITE);
		btnNuevoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNuevoUsuario.setBackground(new Color(29, 46, 74));
		btnNuevoUsuario.setBounds(10, 30, 94, 21);
		panel_user.add(btnNuevoUsuario);
		
		cbEmp = new JComboBox();
		cbEmp.setBounds(374, 62, 45, 22);
		panel_user.add(cbEmp);
		cargarEmpleados();
		cbEmp.setSelectedIndex(0);
		
		JButton btnModificarUsuario = new JButton("Modificar");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = table_Personal.getSelectedRow();
		        if (seleccion == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona un usuario para modificar", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            int id_personal = (int) table_Personal.getValueAt(seleccion, 0);
		            PersonalDTO usuario = Opper.read(id_personal);
		            Usuario ventana = new Usuario(usuario);
		            ventana.setVisible(true);
		            cargarPersonal();
		        }
			}
		});
		btnModificarUsuario.setForeground(Color.WHITE);
		btnModificarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModificarUsuario.setBackground(new Color(29, 46, 74));
		btnModificarUsuario.setBounds(119, 30, 94, 21);
		panel_user.add(btnModificarUsuario);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = table_Personal.getSelectedRow();
				if (seleccion == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un usuario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					int id_personal = (int) table_Personal.getValueAt(seleccion, 0);
					String nombre_usuario = table_Personal.getValueAt(seleccion, 1) + " " + table_Personal.getValueAt(seleccion, 2);
		            int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar " + nombre_usuario.trim()  + "?", "Confirmar eliminación de usuario", JOptionPane.YES_NO_OPTION);
		            if (confirm == JOptionPane.YES_OPTION) {
		                Opper.delete(id_personal);
		                cargarPersonal();
		            }
				}
			}
		});
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
		
		JButton btnInformeGeneral = new JButton("Informe general");
		btnInformeGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    InputStream reportStream = getClass().getResourceAsStream("/informes/HorasTrabajadas.jasper");
				    
				    if (reportStream == null) {
				        JOptionPane.showMessageDialog(null, "No se encontró el archivo del reporte", "Error", JOptionPane.ERROR_MESSAGE);
				        return;
				    }
				    
				    JasperPrint jp = JasperFillManager.fillReport(reportStream, null, ConexionSGL.getInstancia().getCon());
				    JasperViewer.viewReport(jp, false);
				    
				} catch (JRException ex) {
				    JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				    ex.printStackTrace();
				}
			}
		});
		btnInformeGeneral.setForeground(Color.WHITE);
		btnInformeGeneral.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInformeGeneral.setBackground(new Color(29, 46, 74));
		btnInformeGeneral.setBounds(10, 62, 203, 21);
		panel_user.add(btnInformeGeneral);
		
		JButton btnInformeEmp = new JButton("Informe");
		btnInformeEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((int) cbEmp.getSelectedItem() == 0) {
				    JOptionPane.showMessageDialog(null, "Selecciona un empleado");
				    return;
				}
				
				Map <String, Object> parametro = new HashMap();
				parametro.put ("idPersonal", (int) cbEmp.getSelectedItem()); 

		        
				try {
				    InputStream reportStream = getClass().getResourceAsStream("/informes/InformeEmpleado.jasper");
				    
				    if (reportStream == null) {
				        JOptionPane.showMessageDialog(null, "No se encontró el archivo del reporte", "Error", JOptionPane.ERROR_MESSAGE);
				        return;
				    }
				    
				    JasperPrint jp = JasperFillManager.fillReport(reportStream, parametro, ConexionSGL.getInstancia().getCon());
				    JasperViewer.viewReport(jp, false);
				    
				} catch (JRException ex) {
				    JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				    ex.printStackTrace();
				}
			}
		});
		btnInformeEmp.setForeground(Color.WHITE);
		btnInformeEmp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInformeEmp.setBackground(new Color(29, 46, 74));
		btnInformeEmp.setBounds(223, 63, 94, 21);
		panel_user.add(btnInformeEmp);
		
		JLabel lblEmp = new JLabel("Empl.");
		lblEmp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmp.setBounds(336, 66, 38, 13);
		panel_user.add(lblEmp);
		
		JPanel panel_category = new JPanel();
		panel_category.setLayout(null);
		panel_category.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Gesti\u00F3n de Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_category.setBackground(new Color(247, 244, 238));
		panel_category.setBounds(466, 283, 194, 74);
		TitledBorder border_category = (TitledBorder) panel_category.getBorder();
        border_category.setTitleFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPanel.add(panel_category);
		
		JButton btnControlDeCategorias = new JButton("Control de Categorias");
		btnControlDeCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Categoria ventana = new Categoria();
				ventana.setVisible(true);			}
		});
		btnControlDeCategorias.setForeground(Color.WHITE);
		btnControlDeCategorias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnControlDeCategorias.setBackground(new Color(29, 46, 74));
		btnControlDeCategorias.setBounds(10, 30, 174, 21);
		panel_category.add(btnControlDeCategorias);
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrdenarPor.setBounds(21, 259, 90, 13);
		contentPanel.add(lblOrdenarPor);
		
		cbOrdenarTabla = new JComboBox();
		cbOrdenarTabla.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nombre", "Apellidos", "Categoria"}));
		cbOrdenarTabla.setSelectedIndex(0);
		cbOrdenarTabla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbOrdenarTabla.setBounds(105, 255, 161, 21);
		contentPanel.add(cbOrdenarTabla);
		
		cbOrdenarTipo = new JComboBox();
		cbOrdenarTipo.setModel(new DefaultComboBoxModel(new String[] {"Ascendente", "Descendente"}));
		cbOrdenarTipo.setSelectedIndex(0);
		cbOrdenarTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbOrdenarTipo.setBounds(273, 256, 120, 21);
		contentPanel.add(cbOrdenarTipo);
		
		cbOrdenarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarPersonal();
				} catch (Exception ex) {
					System.out.println("Error al cargar la listado de ordenado");
					ex.printStackTrace();
				}
			}
		});
		
		cbOrdenarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarPersonal();
				} catch (Exception ex) {
					System.out.println("Error al cargar la listado de ordenado");
					ex.printStackTrace();
				}
			}
		});
		
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
	    
	    ordenarTabla(listaPersonal);
	    
	    DefaultTableModel modelo = (DefaultTableModel)table_Personal.getModel();
		while (modelo.getRowCount()>0) modelo.removeRow(0);
		
//		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
//		table_Personal.setRowSorter(sorter);
		
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
	
	private void ordenarTabla(ArrayList<PersonalDTO> listaPersonal) {
	    String criterio = (String) cbOrdenarTabla.getSelectedItem();
	    String tipoOrden = (String) cbOrdenarTipo.getSelectedItem();
	    Comparator<PersonalDTO> comparador = null;

	    switch (criterio) {
	        case "Id":
	            comparador = tipoOrden.equals("Ascendente") ? PersonalDTO.Comparadores.ID_PERSONAL_ASC : PersonalDTO.Comparadores.ID_PERSONAL_DESC;
	            break;
	        case "Nombre":
	            comparador = tipoOrden.equals("Ascendente") ? PersonalDTO.Comparadores.NOMBRE_ASC : PersonalDTO.Comparadores.NOMBRE_DESC;
	            break;
	        case "Apellidos":
	            comparador = tipoOrden.equals("Ascendente") ? PersonalDTO.Comparadores.APELLIDOS_ASC : PersonalDTO.Comparadores.APELLIDOS_DESC;
	            break;
	        case "Categoria":
	        	comparador = tipoOrden.equals("Ascendente") ? PersonalDTO.Comparadores.CATEGORIA_ASC : PersonalDTO.Comparadores.CATEGORIA_DESC;
	            break;
	    }

	    listaPersonal.sort(comparador);
	}
	
	private void cargarEmpleados() {
		ArrayList<PersonalDTO> listaPersonal = null;
		listaPersonal = Opper.readAll();
		
		cbEmp.addItem(0);
		for (PersonalDTO pers : listaPersonal ) {
			cbEmp.addItem(pers.getIdPersonal());
		}
		
	}
}
