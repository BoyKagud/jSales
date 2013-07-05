/*
 * Copyright (c) 2013 LimeCodes Media
 * See the file license.txt for copying permission.
 */
package ui;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class Auth_frame extends JOptionPane{

	public static void auth_prompt(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Auth_frame frame = new Auth_frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Auth_frame() {
		int selection = JOptionPane.showConfirmDialog(
		                null
		        , "Authentication Failed!"
		        , "Input Error"
		        , JOptionPane.OK_CANCEL_OPTION
		        , JOptionPane.INFORMATION_MESSAGE);   
		if (selection == JOptionPane.OK_OPTION)
		{
			new Login();
		}
		else if (selection == JOptionPane.CANCEL_OPTION)
		{
		    System.exit(ABORT);
		}
	}
}