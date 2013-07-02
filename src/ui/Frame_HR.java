package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.SQLite_helper;

public class Frame_HR extends JPanel {
	private DefaultTableModel modelHR = new DefaultTableModel();
	private JTable table_hr;

	/**
	 * Create the panel.
	 */
	
	public Frame_HR() throws Throwable {
		setLayout(new BorderLayout(0, 0));
		setBounds(0, 0, 900, 800);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table_hr = new JTable();
		table_hr.setShowVerticalLines(false);
		table_hr.setModel(modelHR);
		init();
		scrollPane.setViewportView(table_hr);
				
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton button_Add = new JButton("Add New User");
		button_Add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new AddNewUser();
			}
		});
		panel.add(button_Add);
				
		JButton button_delete = new JButton("Delete User");
		button_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SQLite_helper sql;
				int rowIndex = table_hr.getSelectedRow();
				String rowCode = (String) table_hr.getValueAt(rowIndex, 0).toString();
//				System.out.println(rowCode);
				try {
					sql = new SQLite_helper();
					sql.delete("users", (int) Integer.parseInt(rowCode));
					sql.destruct();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshTable();
			}
		});
		panel.add(button_delete);
		
		JButton button_Refresh = new JButton("Refresh");
		button_Refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				refreshTable();
			}
		});
		panel.add(button_Refresh);
		setVisible(true);
	}
	
	private void init() throws Throwable {
		modelHR.addColumn("user ID");
		modelHR.addColumn("First Name");
		modelHR.addColumn("Last Name");
		modelHR.addColumn("Middle Initial");
		modelHR.addColumn("Sex");
		modelHR.addColumn("Administrator");
		populateTable();
	}
	
	private void refreshTable() {
		if (modelHR.getRowCount() > 0) {
		    for (int i = modelHR.getRowCount() - 1; i > -1; i--) {
		    	modelHR.removeRow(i);
		    }
		}
		try {
			populateTable();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void populateTable() throws Throwable {
		SQLite_helper sql = new SQLite_helper();
		ResultSet set = sql.getBulk("users");
		while(set.next()) {
			System.out.println(set.getInt("id"));
			modelHR.addRow(new Object[]{
				set.getInt("id"), 
				set.getString("first_name"), 
				set.getString("last_name"), 
				set.getString("middle_initial"), 
				set.getString("sex"), 
				set.getString("isAdmin")});
		}
		set.close();
		sql.destruct();
	}

}
