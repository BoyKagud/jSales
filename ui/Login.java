package ui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Users;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class Login extends JFrame implements KeyListener{
	private JPanel contentPane;
	private static JTextField textField;
	private static JPasswordField passwordField;

	public Login() {
		super();
		init();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setTitle("Login");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		double scrHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		double scrWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		setBounds((int)Math.floor(scrWidth*0.36), (int)Math.floor(scrHeight*0.36), 350, 170);
		this.setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblUsername = new JLabel("Username");
		contentPane.add(lblUsername, "6, 4");
		
		textField = new JTextField();
		contentPane.add(textField, "6, 6, left, default");
		textField.setColumns(25);
		textField.addKeyListener(this);
		
		JLabel lblPassword = new JLabel("Password");
		contentPane.add(lblPassword, "6, 8, left, default");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(25);
		contentPane.add(passwordField, "6, 10, left, default");
		passwordField.addKeyListener(this);
		
		JButton btnLogin = new JButton("Login");
		contentPane.addKeyListener(this);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					login();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnLogin, "6, 12, right, default");
		this.setVisible(true);
	}
		
	public void login() throws Throwable {
		char[] pword_onChar = passwordField.getPassword();
		String pword = "";
		for (char k : pword_onChar) {
			pword += k;
		}
    	Users user = new Users();
    	Integer auth = new Integer(user.login(textField.getText(), pword));
    	System.out.println(auth);
        if (textField.getText().equals("") || auth.intValue()==0){
			Auth_frame.auth_prompt(null);
			dispose();
        } else {
            MainFrame m = new MainFrame(auth);
            m.defaultView();
            dispose();
        }
	}
	
	@Override
	public void keyReleased(KeyEvent e) {		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				login();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
//				login();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
//				login();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		} 
	}

}
