/*
 * Copyright (c) 2013 LimeCodes Media
 * See the file license.txt for copying permission.
 */
package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.Items;

public class AddNewItemInventory extends JFrame {
	private JTextField txt_pStock;
	private JTextField txt_pPrice;
	private JTextField txt_pName;
	private JTextField txt_pCode;

	/**
	 * Create the panel.
	 */

	public AddNewItemInventory() {
		setBounds(500, 500, 400, 150);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblProductName = new JLabel("Product Name:");
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductName.gridx = 1;
		gbc_lblProductName.gridy = 1;
		add(lblProductName, gbc_lblProductName);
		
		txt_pName = new JTextField();
		GridBagConstraints gbc_txt_pName = new GridBagConstraints();
		gbc_txt_pName.insets = new Insets(0, 0, 5, 0);
		gbc_txt_pName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_pName.gridx = 3;
		gbc_txt_pName.gridy = 1;
		add(txt_pName, gbc_txt_pName);
		txt_pName.setColumns(10);
		
		JLabel lblProductCode = new JLabel("Product Code:");
		GridBagConstraints gbc_lblProductCode = new GridBagConstraints();
		gbc_lblProductCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductCode.gridx = 1;
		gbc_lblProductCode.gridy = 3;
		add(lblProductCode, gbc_lblProductCode);
		
		txt_pCode = new JTextField();
		GridBagConstraints gbc_txt_pCode = new GridBagConstraints();
		gbc_txt_pCode.insets = new Insets(0, 0, 5, 0);
		gbc_txt_pCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_pCode.gridx = 3;
		gbc_txt_pCode.gridy = 3;
		add(txt_pCode, gbc_txt_pCode);
		txt_pCode.setColumns(10);
		
		JLabel lblProductPrice = new JLabel("Product Price:");
		GridBagConstraints gbc_lblProductPrice = new GridBagConstraints();
		gbc_lblProductPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductPrice.gridx = 1;
		gbc_lblProductPrice.gridy = 5;
		add(lblProductPrice, gbc_lblProductPrice);
		
		txt_pPrice = new JTextField();
		GridBagConstraints gbc_txt_pPrice = new GridBagConstraints();
		gbc_txt_pPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txt_pPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_pPrice.gridx = 3;
		gbc_txt_pPrice.gridy = 5;
		add(txt_pPrice, gbc_txt_pPrice);
		txt_pPrice.setColumns(10);
		
		JLabel lblProductStock = new JLabel("Product Stock:");
		GridBagConstraints gbc_lblProductStock = new GridBagConstraints();
		gbc_lblProductStock.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductStock.gridx = 1;
		gbc_lblProductStock.gridy = 7;
		add(lblProductStock, gbc_lblProductStock);
		
		txt_pStock = new JTextField();
		GridBagConstraints gbc_txt_pStock = new GridBagConstraints();
		gbc_txt_pStock.insets = new Insets(0, 0, 5, 0);
		gbc_txt_pStock.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_pStock.gridx = 3;
		gbc_txt_pStock.gridy = 7;
		add(txt_pStock, gbc_txt_pStock);
		txt_pStock.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try{
					String prodName = txt_pName.getText();
					String serial = txt_pCode.getText();
					double price = Double.parseDouble(txt_pPrice.getText());
					int stock = Integer.parseInt(txt_pStock.getText());
					
					Items k = new Items();
					boolean isReg = k.regItem(prodName, serial, price, stock);
					
					if(isReg) {
						txt_pStock.setText("");
						txt_pPrice.setText("");
						txt_pName.setText("");
						txt_pCode.setText("");
						dispose();
					}
					
				} catch(Exception e1) {
					e1.printStackTrace();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 9;
		add(btnSubmit, gbc_btnSubmit);
		
		pack();
		setVisible(true);
	}

}
