package modeloVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modeloBD_DAO.FichajeDAO;
import modeloBD_DTO.FichajeDTO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class Fichaje extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static FichajeDAO Opfich = new FichajeDAO();
	private JSpinner spinner_inicio;
	private JSpinner spinner_fin;
	private JLabel lblFechaInicial;
	private JLabel lblFechaFinal;
	private JLabel lblJornada;
	private JButton btnGuardar = new JButton("Guardar");

	/**
	 * Create the dialog.
	 */
	public Fichaje(int idPersonal) {
		setModal(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 440, 265);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(247, 244, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Partes Laborales");
		lblTitulo.setOpaque(true);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBackground(new Color(29, 46, 74));
		lblTitulo.setBounds(34, 20, 350, 40);
		contentPanel.add(lblTitulo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) comboBox.getSelectedItem();
	            if ("Baja".equals(selectedOption) || "Vacaciones".equals(selectedOption)) {
	            	lblFechaInicial.setVisible(true);
	            	spinner_inicio.setVisible(true);
	            	lblFechaFinal.setVisible(true);
	            	spinner_fin.setVisible(true);
	            	lblJornada.setVisible(false);
	            	if ("Baja".equals(selectedOption)) {
	            		btnGuardar.setText("Registrar Baja");
	            	} else {
	            		btnGuardar.setText("Registrar Vacaciones");
	            	}
	            } else {
	            	lblFechaInicial.setVisible(false);
	            	spinner_inicio.setVisible(false);
	            	lblFechaFinal.setVisible(false);
	            	spinner_fin.setVisible(false);
	            	lblJornada.setVisible(true);
	            	btnGuardar.setText(btnTextoJornada(idPersonal));
	            }
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Jornada laboral", "Vacaciones", "Baja"}));
		comboBox.setBounds(129, 84, 184, 21);
		contentPanel.add(comboBox);
		btnGuardar.setText(btnTextoJornada(idPersonal));
		
		lblFechaInicial = new JLabel("Fecha Incial");
		lblFechaInicial.setVisible(false);
		lblFechaInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaInicial.setBounds(72, 114, 85, 15);
		contentPanel.add(lblFechaInicial);
		
		spinner_inicio = new JSpinner(new SpinnerDateModel());
		spinner_inicio.setVisible(false);
		spinner_inicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner.DateEditor de_spinner_inicio = new JSpinner.DateEditor(spinner_inicio, "dd/MM/yyyy");
		spinner_inicio.setEditor(de_spinner_inicio);
		spinner_inicio.setBounds(72, 139, 112, 20);
		contentPanel.add(spinner_inicio);
		
		lblFechaFinal = new JLabel("Fecha Final");
		lblFechaFinal.setVisible(false);
		lblFechaFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaFinal.setBounds(248, 115, 85, 15);
		contentPanel.add(lblFechaFinal);
		
		spinner_fin = new JSpinner(new SpinnerDateModel());
		spinner_fin.setVisible(false);
		
		spinner_fin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JSpinner.DateEditor de_spinner_fin = new JSpinner.DateEditor(spinner_fin, "dd/MM/yyyy");
		spinner_fin.setEditor(de_spinner_fin);
		spinner_fin.setBounds(248, 139, 112, 20);
		contentPanel.add(spinner_fin);
		
		lblJornada = new JLabel("*Al dar a guardar se va a registrar inicio/fin de la jornada laboral");
		lblJornada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblJornada.setBounds(38, 132, 343, 21);
		contentPanel.add(lblJornada);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(247, 244, 238));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
//				btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String selectedOption = (String) comboBox.getSelectedItem();
				        Timestamp now = new Timestamp(System.currentTimeMillis());
				        Date startDateUtil = (Date) spinner_inicio.getValue();
				        Date endDateUtil = (Date) spinner_fin.getValue();
				        Timestamp startDate = new Timestamp(startDateUtil.getTime());
				        Timestamp endDate = new Timestamp(endDateUtil.getTime());
				        Timestamp startOfDay = Timestamp.valueOf(startDate.toLocalDateTime().toLocalDate() + " 00:00:00");
			            Timestamp endOfDay = Timestamp.valueOf(endDate.toLocalDateTime().toLocalDate() + " 23:59:59");
				        
				        if ("Jornada laboral".equals(selectedOption)) {
				            ArrayList<FichajeDTO> previousRecords = Opfich.readPastRecords(idPersonal);
				            FichajeDTO yesterdayRecord = null;

				            for (FichajeDTO record : previousRecords) {
				                long timeDifference = now.getTime() - record.getFechaInicial().getTime();
				                long tenHoursInMillis = 10 * 60 * 60 * 1000; // 10 horas en milisegundos

				                if (record.getFechaFinal() == null && timeDifference <= tenHoursInMillis) {
				                    yesterdayRecord = record;
				                    break;
				                }
				            }

				            if (yesterdayRecord != null) {
				                yesterdayRecord.setFechaFinal(now);
				                Opfich.update(yesterdayRecord);
				                JOptionPane.showMessageDialog(null, "Se ha registrado el fin de la jornada. ¡Que tengas un buen día!", "Fin de Jornada", JOptionPane.INFORMATION_MESSAGE);
				            } else {
				                FichajeDTO existingRecord = Opfich.readPerToday(idPersonal);
				                if (existingRecord == null) {
				                    FichajeDTO newEntry = new FichajeDTO(0, "Trabajo", now, null, idPersonal);
				                    Opfich.create(newEntry);
				                    JOptionPane.showMessageDialog(Fichaje.this, "Se ha registrado el inicio de la jornada, no te olvides registrar el final de la misma.", "Inicio de Jornada", JOptionPane.INFORMATION_MESSAGE);
				                } else {
				                    if (existingRecord.getFechaFinal() == null) {
				                        existingRecord.setFechaFinal(now);
				                        Opfich.update(existingRecord);
				                        JOptionPane.showMessageDialog(null, "Se ha registrado el fin de la jornada. ¡Que tengas un buen día!", "Fin de Jornada", JOptionPane.INFORMATION_MESSAGE);
				                    } else {
				                    	FichajeDTO newEntry = new FichajeDTO(0, "Trabajo", now, null, idPersonal);
					                    Opfich.create(newEntry);
					                    JOptionPane.showMessageDialog(Fichaje.this, "Se ha registrado el inicio de la jornada, no te olvides registrar el final de la misma.", "Inicio de Jornada", JOptionPane.INFORMATION_MESSAGE);
				                    }
				                }
				            }

				            for (FichajeDTO record : previousRecords) {
				                if (record.getFechaFinal() == null) {
				                    Timestamp eightHoursLater = new Timestamp(record.getFechaInicial().getTime() + (8 * 60 * 60 * 1000)); // 8 horas en milisegundos
				                    record.setFechaFinal(eightHoursLater);
				                    Opfich.update(record);
				                }
				            }

				        } else if ("Vacaciones".equals(selectedOption)) {
				        	if (endDate.before(startDate)) {
				                JOptionPane.showMessageDialog(null, "La fecha final no puede ser anterior a la fecha inicial.", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
				                return;
				            }
				        	
				            FichajeDTO vacationOrLeaveEntry = new FichajeDTO(0, selectedOption, startOfDay, endOfDay, idPersonal);
				            Opfich.create(vacationOrLeaveEntry);
				            JOptionPane.showMessageDialog(null, "Se ha registrado las vacaciones. ¡Disfrútalas!", "Vacaciones", JOptionPane.INFORMATION_MESSAGE);
				        
				        } else if ("Baja".equals(selectedOption)) {
				        	if (endDate.before(startDate)) {
				                JOptionPane.showMessageDialog(null, "La fecha final no puede ser anterior a la fecha inicial.", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
				                return;
				            }
				        	
				            FichajeDTO leaveEntry = new FichajeDTO(0, selectedOption, startOfDay, endOfDay, idPersonal);
				            Opfich.create(leaveEntry);
				            JOptionPane.showMessageDialog(null, "Se ha registrado la baja. ¡Cuídate!", "Baja", JOptionPane.INFORMATION_MESSAGE);
				        }
				        dispose();
					}
				});
				btnGuardar.setBackground(new Color(29, 46, 74));
				btnGuardar.setForeground(new Color(255, 255, 255));
				btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
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
	
	private String btnTextoJornada(int idPersonal) {
	    ArrayList<FichajeDTO> previousRecords = Opfich.readPastRecords(idPersonal);
	    FichajeDTO existingRecordToday = Opfich.readPerToday(idPersonal);
	    
	    boolean hasActiveToday = existingRecordToday != null && existingRecordToday.getFechaFinal() == null;
	    boolean hasActiveYesterday = false;

	    for (FichajeDTO record : previousRecords) {
	        long timeDifference = System.currentTimeMillis() - record.getFechaInicial().getTime();
	        long tenHoursInMillis = 10 * 60 * 60 * 1000;

	        if (record.getFechaFinal() == null && timeDifference <= tenHoursInMillis) {
	            hasActiveYesterday = true;
	            break;
	        }
	    }

	    if (hasActiveToday || hasActiveYesterday) {
	        return "Finalizar Jornada";
	    } else {
	        return "Iniciar Jornada";
	    }
	}
}
