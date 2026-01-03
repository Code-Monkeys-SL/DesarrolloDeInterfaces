package modeloVista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloBD_DAO.CategoriaDAO;
import modeloBD_DAO.FichajeDAO;
import modeloBD_DAO.PersonalDAO;
import modeloBD_DTO.CategoriaDTO;
import modeloBD_DTO.FichajeDTO;
import modeloBD_DTO.PersonalDTO;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Empleado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static PersonalDAO Opper = new PersonalDAO();
	private static CategoriaDAO Opcat = new CategoriaDAO();
	private CategoriaDTO categoria;
	private static FichajeDAO Opfich = new FichajeDAO();
	private ArrayList<FichajeDTO> fichaje;
	private JTable table_Fichaje;

	/**
	 * Create the dialog.
	 */
	public Empleado(PersonalDTO usuario, boolean login) {
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 545, 460);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(247, 244, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		boolean isAdmin = false;
		try {
			isAdmin = Opper.isAdmin(usuario.getCorreo());			
		} catch (Exception e) {
			System.out.println("Error al comprobar privilegios");
		}
		
		JLabel lblTitulo = new JLabel("Titulo User");
		lblTitulo.setOpaque(true);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBackground(new Color(29, 46, 74));
		lblTitulo.setBounds(92, 20, 350, 40);
		contentPanel.add(lblTitulo);
		
		if (login)
			lblTitulo.setText("Bienvenido " + usuario.getNombre());
		else
			lblTitulo.setText("Ficha del empleado " + usuario.getIdPersonal());
		
		JScrollPane scrollPane_User = new JScrollPane();
		scrollPane_User.setBounds(29, 70, 475, 140);
		contentPanel.add(scrollPane_User);
		
		JLabel lblUser = new JLabel();
		lblUser.setVerticalAlignment(SwingConstants.TOP);
		scrollPane_User.setViewportView(lblUser);
		lblUser.setBackground(new Color(247, 244, 238));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		String htmlContent = "<html><body>"
			    + "<table style='border-collapse: collapse; width: 99%; margin: 0 auto;'>"
			    + "<tr><td colspan='4' style='border: 1px solid black; text-align: center;'><b>Datos del empleado:</b></td></tr>"
			    + "<tr>"
			    + String.format("<td style='border: 1px solid black; width: 25%%;'>Nombre:</td><td style='border: 1px solid black; width: 25%%;'>%s</td>"
			    + "<td style='border: 1px solid black; width: 25%%;'>Apellidos:</td><td style='border: 1px solid black; width: 25%%;'>%s</td></tr>",
			          usuario.getNombre(), usuario.getApellidos())
			    + "<tr>"
			    + String.format("<td style='border: 1px solid black;'>Telefono:</td><td style='border: 1px solid black;'>%s</td>"
			    + "<td style='border: 1px solid black;'>Correo:</td><td style='border: 1px solid black;'>%s</td></tr>",
			          usuario.getTelefono(), usuario.getCorreo());

		try {
		    categoria = Opcat.read(usuario.getIdCategoria());
		    htmlContent += String.format("<tr>"
		        + "<td style='border: 1px solid black;'>Categoria profesional:</td>"
		        + "<td style='border: 1px solid black;' colspan='3'>%s</td></tr>"
		        + "<tr>"
		        + "<td style='border: 1px solid black;'>Descripción:</td>"
		        + "<td style='border: 1px solid black;' colspan='3'>%s</td></tr>",
		        categoria.getNombre(), categoria.getDescripcion());
		} catch (Exception ex) {
		    htmlContent += "<tr><td colspan='4' style='border: 1px solid black;'>No se ha podido leer los datos de la categoría profesional correctamente.</td></tr>";
		}
		
		htmlContent += "</table></body></html>";	
		lblUser.setText(htmlContent);
		
		JScrollPane scrollPane_Table = new JScrollPane();
		scrollPane_Table.setBounds(29, 229, 475, 140);
		contentPanel.add(scrollPane_Table);
		
		table_Fichaje = new JTable();
		table_Fichaje.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Acci\u00F3n", "Fecha Incial", "Fecha Final", "Duraci\u00F3n"
			}
		));
		table_Fichaje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_Table.setViewportView(table_Fichaje);
		
		try {
			cargarFichaje(usuario.getIdPersonal());
		} catch (Exception e) {
			System.out.println("Error al cargar la listado de fichaje");
		}

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(247, 244, 238));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnControlDePersonal = new JButton("Control de Personal");
				btnControlDePersonal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Personal ventana = new Personal();
						ventana.setVisible(true);
					}
				});
				btnControlDePersonal.setForeground(Color.WHITE);
				btnControlDePersonal.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnControlDePersonal.setBackground(new Color(29, 46, 74));
				btnControlDePersonal.setActionCommand("OK");
				if (isAdmin && login)
					buttonPane.add(btnControlDePersonal);
				
				JButton btnFichar = new JButton("Fichar");
				btnFichar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Fichaje ventana = new Fichaje(usuario.getIdPersonal());
						ventana.setVisible(true);
						try {
							cargarFichaje(usuario.getIdPersonal());
						} catch (Exception e2) {
							System.out.println("Error al cargar la listado de fichaje");
						}
					}
				});
				btnFichar.setBackground(new Color(29, 46, 74));
				btnFichar.setForeground(new Color(255, 255, 255));
				btnFichar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnFichar.setActionCommand("OK");
				buttonPane.add(btnFichar);
				getRootPane().setDefaultButton(btnFichar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setBackground(new Color(29, 46, 74));
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
	
	private void cargarFichaje(int idPersonal) throws SQLException {
		fichaje = Opfich.readPer(idPersonal);
		
		DefaultTableModel modelo = (DefaultTableModel)table_Fichaje.getModel();
		while (modelo.getRowCount()>0) modelo.removeRow(0);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
		table_Fichaje.setRowSorter(sorter);
		int numCols = modelo.getColumnCount();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		for (FichajeDTO ficha : fichaje) {
			Object [] fila = new Object[numCols];
			
			fila[0] = ficha.getAccion();
			Timestamp fechaInicial = ficha.getFechaInicial();
			Timestamp fechaFinal = ficha.getFechaFinal();
	        fila[1] = sdf.format(fechaInicial);
	        if (fechaFinal == null) {
	            fila[2] = "";
	            fila[3] = "";
	        } else {
	            fila[2] = sdf.format(fechaFinal);
	            
	            long horas = calcularHoras(fechaInicial, fechaFinal);
	            
	            if (horas < 24) {
	                fila[3] = horas + " h";
	            } else {
	                long dias = horas / 24;
	                fila[3] = dias + " d";
	            }
	        }
			
			modelo.addRow(fila);
		}
	}
	
	private long redondearHoras(double horasTotales) {
		long parteEntera = (long) horasTotales;
	    double parteDecimal = horasTotales - parteEntera;

	    if (parteDecimal >= 0.50) {
	        return parteEntera + 1;
	    } else {
	        return parteEntera;
	    }
	}
	
	private long calcularHoras(Timestamp fechaInicial, Timestamp fechaFinal) {
	    long diferenciaMilisegundos = fechaFinal.getTime() - fechaInicial.getTime();

	    long horas = diferenciaMilisegundos / (1000 * 60 * 60);
	    long minutos = (diferenciaMilisegundos / (1000 * 60)) % 60;

	    double horasTotales = horas + (minutos / 60.0);

	    return redondearHoras(horasTotales);
	}
}
