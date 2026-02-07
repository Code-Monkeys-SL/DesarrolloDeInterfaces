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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import modeloBD_DAO.CategoriaDAO;
import modeloBD_DTO.CategoriaDTO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class Categoria extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CategoriaDAO Opcat = new CategoriaDAO();
	private ArrayList<CategoriaDTO> categorias = new ArrayList<CategoriaDTO>();  
	private JTextField tfNombre;
	private JTextField tfDescripcion;
	private JButton actionButton;
	private JComboBox cbAction;
	private JComboBox cbCategoria;

	/**
	 * Create the dialog.
	 */
	public Categoria() {
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 270);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(247, 244, 238));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblActionCategoria = new JLabel("Visualizar Categoria");
		lblActionCategoria.setOpaque(true);
		lblActionCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblActionCategoria.setForeground(Color.WHITE);
		lblActionCategoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblActionCategoria.setBackground(new Color(29, 46, 74));
		lblActionCategoria.setBounds(43, 10, 350, 40);
		contentPanel.add(lblActionCategoria);
		
		JLabel lblIdDeCategoria = new JLabel("Id de Categoria:");
		lblIdDeCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdDeCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdDeCategoria.setBounds(127, 94, 91, 13);
		contentPanel.add(lblIdDeCategoria);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(149, 120, 69, 13);
		contentPanel.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNombre.setColumns(10);
		tfNombre.setBounds(221, 117, 120, 19);
		contentPanel.add(tfNombre);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcin.setBounds(149, 143, 69, 13);
		contentPanel.add(lblDescripcin);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(43, 158, 350, 19);
		contentPanel.add(tfDescripcion);
		
		cbCategoria = new JComboBox();
		cbCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = (Integer) cbCategoria.getSelectedItem();
				if (id == null)
					return;
				
				for (CategoriaDTO categoria : categorias) {
		            if (categoria.getIdCategoria() == id) {
		                tfNombre.setText(categoria.getNombre());
		                tfDescripcion.setText(categoria.getDescripcion());
		                break;
		            }
		        }
			}
		});
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbCategoria.setBounds(221, 91, 120, 19);
		contentPanel.add(cbCategoria);
		cargarCategorias();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(247, 244, 238));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				actionButton = new JButton("Action");
				actionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nombre = tfNombre.getText().trim();
					    String descripcion = tfDescripcion.getText().trim();
					    if (nombre.isEmpty() || descripcion.isEmpty()) {
					    	JOptionPane.showMessageDialog(null, "El nombre y la descripción no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
					        return;
					    }
					    
					    Integer id = (Integer) cbCategoria.getSelectedItem();
					    CategoriaDTO categoria;
					    
						String accion = (String) cbAction.getSelectedItem();
				        switch (accion) {
				            case "Añadir":
				            	categoria = new CategoriaDTO(0, nombre, descripcion);
				            	if (Opcat.create(categoria)) {
				            		cargarCategorias();
				                    limpiarTF();
				                    JOptionPane.showMessageDialog(null, "Categoría añadida exitosamente!");
				                } else {
				                    JOptionPane.showMessageDialog(null, "Error al añadir la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
				                }
				                break;
				            case "Modificar":
				            	if (id == null) {
				            		JOptionPane.showMessageDialog(null, "No se puede modificar un campo sin id.", "Error", JOptionPane.ERROR_MESSAGE);
									return;
				            	}
				            	
				            	categoria = new CategoriaDTO(id, nombre, descripcion);
				            	if (Opcat.update(categoria)) {
				                    JOptionPane.showMessageDialog(null, "Categoría modificada exitosamente!", "Error", JOptionPane.ERROR_MESSAGE);
				                } else {
				                    JOptionPane.showMessageDialog(null, "Error al modificar la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
				                }
				                break;
				            case "Eliminar":
				                if (id == null) {
				                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una categoría para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
				                    return;
				                }
				                
				                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar la categoría?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
				                if (confirmacion != JOptionPane.YES_OPTION)
				                	return;
				                
				                if (Opcat.delete(id)) {
				                	cbCategoria.setSelectedIndex(-1);
				                	cargarCategorias();
				                    limpiarTF();
				                    JOptionPane.showMessageDialog(null, "Categoría eliminada exitosamente!");
				                } else {
				                    JOptionPane.showMessageDialog(null, "Error al eliminar la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
				                }
				                break;
				            default:
				                break;
				        }
					}
				});
				actionButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		
		cbAction = new JComboBox();
		cbAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarSeleccion(lblActionCategoria);
			}
		});
		cbAction.setModel(new DefaultComboBoxModel(new String[] {"Visualizar", "Añadir", "Modificar", "Eliminar"}));
		cbAction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbAction.setBounds(117, 60, 233, 21);
		contentPanel.add(cbAction);
		cbAction.setSelectedIndex(0);
		cambiarSeleccion(lblActionCategoria);
	}
	
	private void cambiarSeleccion(JLabel lblActionCategoria) {
		String seleccion = (String) cbAction.getSelectedItem();
		lblActionCategoria.setText(seleccion + " Categoria");
		
		switch (seleccion) {
			case "Visualizar":
				actionButton.setVisible(false);
				tfNombre.setEditable(false);
				tfDescripcion.setEditable(false);
				cbCategoria.setEnabled(true);
				break;
				
			case "Añadir":
				actionButton.setText("Añadir");
				actionButton.setVisible(true);
				tfNombre.setEditable(true);
				tfDescripcion.setEditable(true);
				cbCategoria.setSelectedIndex(-1);
				cbCategoria.setEnabled(false);
				limpiarTF();
				break;
				
			case "Modificar":
				actionButton.setText("Modificar");
				actionButton.setVisible(true);
				tfNombre.setEditable(true);
				tfDescripcion.setEditable(true);
				cbCategoria.setEnabled(true);
				break;
				
			case "Eliminar":
				actionButton.setText("Eliminar");
				actionButton.setVisible(true);
				tfNombre.setEditable(false);
				tfDescripcion.setEditable(false);
				cbCategoria.setEnabled(true);
				break;
		}
	}
	
	private void limpiarTF() {
		tfNombre.setText("");
		tfDescripcion.setText("");
	}
	
	private void cargarCategorias() {
        categorias = Opcat.readAll();
        cbCategoria.removeAllItems();
        for (CategoriaDTO categoria : categorias) {
            cbCategoria.addItem(categoria.getIdCategoria());
        }
    }
}
