package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Frame_About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_About frame = new Frame_About();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame_About() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		double scrHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		double scrWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		setBounds((int)Math.floor(scrWidth*0.36), (int)Math.floor(scrHeight*0.36), 350, 250);
		setAlwaysOnTop(true);
		this.setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblAuthor = new JLabel("Author: ");
		contentPane.add(lblAuthor, "4, 4");
		
		JLabel lblRicardomongskieBenitez = new JLabel("Ricardo \"Mongskie\" Benitez");
		contentPane.add(lblRicardomongskieBenitez, "8, 4");
		
		JLabel lblAbout = new JLabel("About: ");
		contentPane.add(lblAbout, "4, 8");
		
		JTextArea txtrJsalesIsAn = new JTextArea();
		txtrJsalesIsAn.setWrapStyleWord(true);
		txtrJsalesIsAn.setEditable(false);
		txtrJsalesIsAn.setLineWrap(true);
		txtrJsalesIsAn.setColumns(100);
		txtrJsalesIsAn.setText("jSales is an Open Source Point-of-Sales Application aimed to assist B.A. Students on sales and fast report generation");
		contentPane.add(txtrJsalesIsAn, "8, 10, fill, fill");
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnNewButton, "8, 12");
	}

}
