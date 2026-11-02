package Textbook_selling_system;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	// Admin credentials
	private final String[] user = {"Rohan"};
	private final String[] pass = {"N@veen2009"};

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AdminLogin frame = new AdminLogin();
				frame.setTitle("Book Hub");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(
						null,
						"Unable to open Admin Login: " + e.getMessage()
				);
			}
		});
	}

	public AdminLogin() {

		setTitle("Book Hub");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setLocationRelativeTo(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Soumyadeep\\Desktop\\Book Hub\\0 (Custom).jpg"
		));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel heading = new JLabel("Admin Login");
		heading.setForeground(new Color(255, 255, 102));
		heading.setFont(new Font("Times New Roman", Font.PLAIN, 46));
		heading.setBounds(364, 60, 300, 93);
		contentPane.add(heading);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		usernameLabel.setBounds(250, 166, 193, 52);
		contentPane.add(usernameLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		textField.setBounds(481, 170, 281, 68);
		contentPane.add(textField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		passwordLabel.setBounds(250, 286, 193, 52);
		contentPane.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		passwordField.setBounds(481, 286, 281, 68);
		contentPane.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		loginButton.setBounds(545, 392, 162, 73);

		loginButton.addActionListener(e -> {

			String username = textField.getText().trim();
			String password = new String(passwordField.getPassword());

			boolean validLogin = false;

			for (int i = 0; i < user.length; i++) {
				if (username.equals(user[i])
						&& password.equals(pass[i])) {
					validLogin = true;
					break;
				}
			}

			if (validLogin) {

				try {
					AdminHost adminHost = new AdminHost();
					adminHost.setTitle("Book Hub");
					adminHost.setVisible(true);
					dispose();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(
							this,
							"Unable to open Admin Dashboard: "
									+ ex.getMessage(),
							"Error",
							JOptionPane.ERROR_MESSAGE
					);
				}

			} else {

				JOptionPane.showMessageDialog(
						this,
						"Wrong Username & Password",
						"Login Failed",
						JOptionPane.ERROR_MESSAGE
				);

				passwordField.setText("");
				passwordField.requestFocusInWindow();
			}
		});

		contentPane.add(loginButton);

		JButton userLoginButton = new JButton("User Login");
		userLoginButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		userLoginButton.setBounds(250, 387, 193, 83);

		userLoginButton.addActionListener(e -> {
			dispose();
			Userlogin userLogin = new Userlogin();
			userLogin.setTitle("Book Hub");
			userLogin.setVisible(true);
		});

		contentPane.add(userLoginButton);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(
				"C:\\Users\\Soumyadeep\\Desktop\\Book Hub\\books-wallpaper-49796-51475-hd-wallpapers (Custom).jpg"
		));
		background.setBounds(0, 0, 1008, 562);
		contentPane.add(background);

		contentPane.setComponentZOrder(
				background, contentPane.getComponentCount() - 1
		);
	}
}