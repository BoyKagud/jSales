package ui;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reciept extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public Reciept(final DefaultTableModel model, double total, double ap, double change) throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox, BorderLayout.NORTH);
		
		JLabel lblIbusinessMate = new JLabel("iBusiness Mate");
		verticalBox.add(lblIbusinessMate);
		
		JLabel lbl_date = new JLabel("");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime =formatter.parse(formatter.format(today));
		lbl_date.setText(todayWithZeroTime.toString());
		verticalBox.add(lbl_date);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Discard");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			    for (int i = model.getRowCount() - 1; i > -1; i--) {
			    	model.removeRow(i);
			    }
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.CENTER);
		table.setModel(model);
		
		model.addRow(new Object[]{"", "", ""});
		model.addRow(new Object[]{"", "Total: ", total});
		model.addRow(new Object[]{"", "Paid: ", ap});
		model.addRow(new Object[]{"", "Change: ", change});
		
		setVisible(true);
		setUndecorated(true);
		pack();
	}
}
