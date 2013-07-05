/*
 * Copyright (c) 2013 LimeCodes Media
 * See the file license.txt for copying permission.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import main.Items;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame_Transaction extends JPanel {

	private JPanel panel_ProductEntry;
	private static JTextField txt_Code;
	private JLabel label_ProductCode;
	private JLabel lblUnits;
	private JTextField txt_Units;
	private JPanel panel_Sum;
	private JLabel label_TotalPrice;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table_ProdList;
	private DefaultTableModel model = new DefaultTableModel();
	
	public double TotalPrice = 0.00;
	private JButton button_Checkout;
	private Component horizontalStrut;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Frame_Transaction() {
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		
		init();
		table_ProdList = new JTable();
		table_ProdList.setShowHorizontalLines(false);
		table_ProdList.setFillsViewportHeight(true);
		table_ProdList.setModel(model);
		table_ProdList.setVisible(true);
		scrollPane.setViewportView(table_ProdList);
		
		panel_ProductEntry = new JPanel();
		add(panel_ProductEntry, BorderLayout.SOUTH);
		
		label_ProductCode = new JLabel("Product Code");
		panel_ProductEntry.add(label_ProductCode);
		
		txt_Code = new JTextField();
		txt_Code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						addItem();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_ProductEntry.add(txt_Code);
		txt_Code.setColumns(10);
		txt_Code.requestFocus();
		
		lblUnits = new JLabel("Units");
		panel_ProductEntry.add(lblUnits);
		
		txt_Units = new JTextField();
		txt_Units.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						addItem();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_ProductEntry.add(txt_Units);
		txt_Units.setColumns(10);
		
		button_Checkout = new JButton("Checkout");
		button_Checkout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try{
					double tp = TotalPrice;
					TotalPrice = 0;
				    label_TotalPrice.setText("0.00 Php");
					new Checkout_Frame(model, tp);
				} catch(Exception l) {
					l.printStackTrace();
				}
			}
		});
		
		horizontalStrut = Box.createHorizontalStrut(300);
		panel_ProductEntry.add(horizontalStrut);
		panel_ProductEntry.add(button_Checkout);
		
		panel_Sum = new JPanel();
		add(panel_Sum, BorderLayout.EAST);
		
		label_TotalPrice = new JLabel("0.00 Php");
		label_TotalPrice.setFont(new Font("Dialog", Font.BOLD, 40));
		panel_Sum.add(label_TotalPrice);
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	public static void deffaultFocus() {
		txt_Code.requestFocus();
	}
	
	private void addItem() throws Throwable {
		int units;
		if(!txt_Code.getText().equals("")) {
			String code = txt_Code.getText();
			Items k = new Items(code);
			int id = k.getID();
			String prodName = k.getName();
			double denom = k.getPrice();
			if(txt_Units.getText().equals("")) {
				units = 1;
			} else {
				units = Integer.parseInt(txt_Units.getText());
			}
			
			if(k.validate(code)) {
				TotalPrice += denom*units;
				model.addRow(new Object[] {units, prodName, denom*units});
				label_TotalPrice.setText(TotalPrice+" Php");
				txt_Code.requestFocus();
				
				updateDB(id, units);
			}
			
			txt_Code.setText("");
			txt_Units.setText("");
		}
		
	}
	
	private void updateDB(int id, int units) throws Throwable{
		Items p = new Items();
		p.updateItem(id, units);
	}
	
	private void init() {
		model.addColumn("Units");
		model.addColumn("Product Name");
		model.addColumn("Total");
	}

}
