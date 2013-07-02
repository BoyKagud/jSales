package ui;

/*
 * Author: Ricardo Benitez
 * Contributors: Cyril Yaranon, Justin Dela Cruz
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import ui.JPanelWithBgImage;

public class MainFrame {

	private int admin;
	private double scrHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private double scrWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private Rectangle bounds = new Rectangle(0, 0, (int)scrWidth, (int)scrHeight);
	private ImageIcon ii = new ImageIcon("img/logo.png");
	private final JPanel logoOnFrame = new JPanelWithBgImage(ii);
	private int logoWidth = 50;
	private int logoHeight = (int) Math.ceil(((double)logoWidth / ii.getIconWidth()) * (ii.getIconHeight()));
	JFrame mainFrame;
	JPanel inventoryPane;
	JPanel transactionPane;
	JPanel HRPane;
	JPanel ICPane;
	final JPanel menuPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics grphcs) {
            Graphics2D g2d = (Graphics2D) grphcs;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gp = new GradientPaint(0, 0,
            		Color.LIGHT_GRAY.darker(), 0, getHeight(),
                    Color.LIGHT_GRAY);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            super.paintComponent(grphcs);
        }
    };

	/**
	 * Create the application.
	 * @throws Throwable 
	 */
	public MainFrame() throws Throwable {
		initialize();
		mainFrame.setVisible(true);
	}
	
	public MainFrame(int adm) throws Throwable {
		admin = adm;
		initialize();
		mainFrame.setVisible(true);
	}

	@SuppressWarnings("null")
	private void initialize() throws Throwable{
		inventoryPane = new Frame_Inventory();
		transactionPane = new Frame_Transaction();
		HRPane = new Frame_HR();
		ICPane = new Frame_IC();
		
	    Image icon = Toolkit.getDefaultToolkit().getImage("img/favicon.jpg");
		mainFrame = new JFrame("jSales v1.0");
		mainFrame.setIconImage(icon);
		mainFrame.setBounds(bounds);
		mainFrame.setMinimumSize(new Dimension((int) Math.floor(scrWidth)-200, (int) Math.floor(scrHeight)-200));
		mainFrame.setUndecorated(false);
//		mainFrame.setAlwaysOnTop(true); //comment out while in dev't
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		// initialize logo
		logoOnFrame.setOpaque(false);
		logoOnFrame.setBounds(0, 0, logoWidth, logoHeight);
		logoOnFrame.setPreferredSize(new Dimension(logoWidth, logoHeight));
		
		// instance of our standard menu bar plus add it on the menupanel
		MenuBar mBar = new MenuBar();
		mBar.setVisible(true);
		
		// initialize menuPanel
		final JPanel menuPane = new JPanel();
//		menuPane.setBackground(Color.BLACK); //for debugging
		menuPane.add(logoOnFrame);
		menuPane.add(mBar);
		
//		patientUI.setBackground(Color.BLACK);
		
		//add menuPanel to mainframe
		mainFrame.getContentPane().add(menuPane, BorderLayout.NORTH);
		
		mainFrame.getContentPane().add(getDefaultScreen(), BorderLayout.CENTER);
		
		// create wrapper for body
			
		mainFrame.pack();
		
	}
	
	private JPanel getDefaultScreen() {
		
		JPanel defScreen = new JPanel();
//		defScreen.setBackground(Color.black);
		defScreen.setVisible(true);

		// default view
		
		return defScreen;
	}
	
	@SuppressWarnings("serial")
	class MenuBar extends JMenuBar {
		MenuBar() {
			super();
			this.setOpaque(false);
			this.setBorderPainted(true);
			this.setPreferredSize(new Dimension(mainFrame.getWidth()-logoWidth-80, 50) );
							
			JMenuItem Inventory = new buttonHoverEffect(menuPanel, "Inventory");
			
			JMenuItem Transaction = new buttonHoverEffect(menuPanel, "Transaction");
			
			JMenuItem hr = new buttonHoverEffect(menuPanel, "Human Resource");
			
			JMenuItem Miscellaneous = new buttonHoverEffect(menuPanel, "Miscellaneous");
			Miscellaneous.setBorder(new EmptyBorder(0, 0, 0, 10));
													
			JMenuItem logout = new buttonHoverEffect(menuPanel, "LogOut");
			logout.setHorizontalAlignment(SwingConstants.CENTER);
			logout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					logout();
				}
			});
				
			if(admin == 1) {
				this.add(Inventory);
			}
			this.add(Transaction);
			if(admin == 1){
				this.add(hr);
			}
			this.add(Miscellaneous);
			this.add(Box.createGlue());
			this.add(logout);
			
//			this.setOpaque(true);
		}
				
		private void logout() {
			int selection = JOptionPane.showConfirmDialog(
	                null
	        , "This will exit the program"
	        , "LogOut iBusiness Mate"
	        , JOptionPane.OK_CANCEL_OPTION
	        , JOptionPane.INFORMATION_MESSAGE);   
			if (selection == JOptionPane.OK_OPTION)
			{
				System.exit(0);
			}
		}
	}
	
	@SuppressWarnings("serial")
	class buttonHoverEffect extends JMenuItem {
		
		buttonHoverEffect(final JPanel k, final String name) {
			super();
			this.setText(name);
			this.setName(name);
			final Color z = this.getForeground();
			this.setMaximumSize(new Dimension(200, 100));
			this.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
			this.setOpaque(false);
		    this.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					if(!name.equals("LogOut")) {
						BorderLayout k = (BorderLayout) mainFrame.getContentPane().getLayout();
						k.getLayoutComponent(BorderLayout.CENTER).setVisible(false);
						mainFrame.remove(k.getLayoutComponent(BorderLayout.CENTER));
						
						if(name.equals("Inventory")) {
							inventoryPane.setVisible(true);
							mainFrame.getContentPane().add(inventoryPane, BorderLayout.CENTER);
							k.getLayoutComponent(BorderLayout.CENTER).setVisible(true);
						} else if(name.equals("Transaction")) {
							transactionPane.setVisible(true);
							mainFrame.getContentPane().add(transactionPane, BorderLayout.CENTER);
							k.getLayoutComponent(BorderLayout.CENTER).setVisible(true);
						} else if(name.equals("Human Resource")) {
							HRPane.setVisible(true);
							mainFrame.getContentPane().add(HRPane, BorderLayout.CENTER);
							k.getLayoutComponent(BorderLayout.CENTER).setVisible(true);
						} else if(name.equals("Miscellaneous")) {
							ICPane.setVisible(true);
							mainFrame.getContentPane().add(ICPane, BorderLayout.CENTER);
							k.getLayoutComponent(BorderLayout.CENTER).setVisible(true);
						}
					
					}				
				}
		        public void mouseEntered(java.awt.event.MouseEvent evt) {
		        	setForeground(Color.WHITE);
		        }
				public void mouseExited(java.awt.event.MouseEvent evt) {
					setForeground(z);
		        }
		        });
		}
		
	}
	
}






