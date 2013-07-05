/*
 * Copyright (c) 2013 LimeCodes Media
 * See the file license.txt for copying permission.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class Checkout_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField cashField;
	private JButton btnDone;
	private double change;
	private double cash;
	private JLabel lblCha;

	/**
	 * Create the frame.
	 */
	public Checkout_Frame(final DefaultTableModel md, final double total) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 24));
		
		JLabel lblPhp = new JLabel("");
		lblPhp.setText(total+" Php");
		lblPhp.setFont(new Font("Dialog", Font.BOLD, 24));
		
		JLabel lblCash = new JLabel("Cash: ");
		lblCash.setFont(new Font("Dialog", Font.BOLD, 24));		
		
		JLabel lblChange = new JLabel("Change: ");
		lblChange.setFont(new Font("Dialog", Font.BOLD, 24));
		
		lblCha = new JLabel("");
		lblCha.setFont(new Font("Dialog", Font.BOLD, 24));
		
		cashField = new JTextField();
		cashField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					cash = Double.parseDouble(cashField.getText());
					change = cash-total;
					lblCha.setText(change+" Php");
					btnDone.setEnabled(true);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
					try {
						new Reciept(md, total, cash, change);
					} catch (ParseException l) {
						// TODO Auto-generated catch block
						l.printStackTrace();
					}
				}
			}
		});
		cashField.setColumns(10);
		
		btnDone = new JButton("Done");
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
				try {
					new Reciept(md, total, cash, change);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDone.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblChange)
									.addGap(18)
									.addComponent(lblCha))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblCash)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cashField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblTotal)
										.addGap(18)
										.addComponent(lblPhp)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(148)
							.addComponent(btnDone)))
					.addContainerGap(143, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(lblPhp))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCash)
						.addComponent(cashField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChange)
						.addComponent(lblCha))
					.addGap(18)
					.addComponent(btnDone)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setUndecorated(true);
		setVisible(true);
	}
}
