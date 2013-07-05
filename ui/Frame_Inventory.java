/*
 * Copyright (c) 2013 LimeCodes Media
 * See the file license.txt for copying permission.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableRow;

import main.SQLite_helper;

public class Frame_Inventory extends JPanel {
	private DefaultTableModel modelInventory = new DefaultTableModel();
	private JTable table_inventory;

	/**
	 * Create the panel.
	 */
	public Frame_Inventory() throws Throwable {
		setLayout(new BorderLayout(0, 0));
		setBounds(0, 0, 900, 800);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table_inventory = new JTable();
		table_inventory.setShowVerticalLines(false);
		table_inventory.setModel(modelInventory);
		init();
		scrollPane.setViewportView(table_inventory);
				
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton button_Add = new JButton("Add New Item");
		button_Add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new AddNewItemInventory();
			}
		});
		panel.add(button_Add);
		
		JButton button_Edit = new JButton("Edit Entry");
		button_Edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int rowIndex = table_inventory.getSelectedRow();
				String name = (String) table_inventory.getValueAt(rowIndex, 1).toString();
				String code = (String) table_inventory.getValueAt(rowIndex, 2).toString();
				
				String priceRaw = (String) table_inventory.getValueAt(rowIndex, 3).toString();
				double price = Double.parseDouble(priceRaw);

				String stockRaw = (String) table_inventory.getValueAt(rowIndex, 4).toString();
				int stock = Integer.parseInt(stockRaw);
				
				String idRaw = (String) table_inventory.getValueAt(rowIndex, 0).toString();
				int id = Integer.parseInt(idRaw);
								
				new EditItemInventory(name, code, price, stock, id);
			}
		});
		panel.add(button_Edit);
		
		JButton button_delete = new JButton("Delete Entry");
		button_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SQLite_helper sql;
				int rowIndex = table_inventory.getSelectedRow();
				String rowCode = (String) table_inventory.getValueAt(rowIndex, 0).toString();
//				System.out.println(rowCode);
				try {
					sql = new SQLite_helper();
					sql.delete("items", (int) Integer.parseInt(rowCode));
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
		modelInventory.addColumn("Product ID");
		modelInventory.addColumn("Product Name");
		modelInventory.addColumn("Product Code");
		modelInventory.addColumn("Product Price");
		modelInventory.addColumn("in Stock");
		modelInventory.addColumn("Sold");
		populateTable();
	}
	
	private void refreshTable() {
		if (modelInventory.getRowCount() > 0) {
		    for (int i = modelInventory.getRowCount() - 1; i > -1; i--) {
		    	modelInventory.removeRow(i);
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
		ResultSet set = sql.getBulk("items");
		while(set.next()) {
//			System.out.println(set.getInt("id"));
			modelInventory.addRow(new Object[]{
				set.getInt("id"), 
				set.getString("product_name"), 
				set.getString("serial_code"), 
				set.getDouble("price"), 
				set.getInt("stock"), 
				set.getInt("sold")});
		}
		set.close();
		sql.destruct();
	}

}
