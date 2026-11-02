package Textbook_selling_system;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Userlogin extends JFrame {

	private static final long serialVersionUID = 1L;

	// MySQL database configuration
	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/book_selling_system";

	private static final String DB_USER = "root";

	// Replace this with your actual MySQL root password
	private static final String DB_PASSWORD = "N@veen2008";

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Userlogin frame = new Userlogin();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Userlogin() {

		setTitle("Book Hub - User Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Heading
		JLabel lblHeading = new JLabel("BOOK HUB");
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblHeading.setBounds(300, 25, 220, 40);
		contentPane.add(lblHeading);

		// Username label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(230, 125, 120, 30);
		contentPane.add(lblUsername);

		// Username field
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(350, 125, 200, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		// Password label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(230, 185, 120, 30);
		contentPane.add(lblPassword);

		// Password field
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(350, 185, 200, 30);
		contentPane.add(passwordField);

		// Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(350, 245, 95, 35);
		contentPane.add(btnLogin);

		// Admin Login button
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdminLogin.setBounds(350, 300, 125, 30);
		contentPane.add(btnAdminLogin);

		// Create Account button
		JButton btnCreateAccount = new JButton("Create New Account");
		btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateAccount.setBounds(310, 345, 200, 30);
		contentPane.add(btnCreateAccount);

		// Reset button
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(460, 245, 90, 35);
		contentPane.add(btnReset);

		// LOGIN BUTTON ACTION
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String username = textField.getText().trim();
				String password = new String(passwordField.getPassword());

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(
							Userlogin.this,
							"Please enter both username and password.",
							"Missing Details",
							JOptionPane.WARNING_MESSAGE
					);
					return;
				}

				String query =
						"SELECT user1 FROM account WHERE user1 = ? AND pass1 = ?";

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");

					try (Connection con = DriverManager.getConnection(
							DB_URL, DB_USER, DB_PASSWORD);
						 PreparedStatement ps = con.prepareStatement(query)) {

						ps.setString(1, username);
						ps.setString(2, password);

						try (ResultSet rs = ps.executeQuery()) {

							if (rs.next()) {

								JOptionPane.showMessageDialog(
										Userlogin.this,
										"Login successful!",
										"Success",
										JOptionPane.INFORMATION_MESSAGE
								);

								dispose();

								// Open the user dashboard
								UserHost userHost = new UserHost();
								userHost.setTitle("Book Hub");
								userHost.setLocationRelativeTo(null);
								userHost.setVisible(true);

							} else {

								JOptionPane.showMessageDialog(
										Userlogin.this,
										"Incorrect username or password.",
										"Login Failed",
										JOptionPane.ERROR_MESSAGE
								);
							}
						}
					}

				} catch (ClassNotFoundException ex) {

					JOptionPane.showMessageDialog(
							Userlogin.this,
							"MySQL JDBC driver was not found.\n"
									+ "Check that MySQL Connector/J is added to the project.",
							"Driver Error",
							JOptionPane.ERROR_MESSAGE
					);

					ex.printStackTrace();

				} catch (SQLException ex) {

					JOptionPane.showMessageDialog(
							Userlogin.this,
							"Could not connect to the database.\n"
									+ "Check your MySQL server, database name, "
									+ "username and password.\n\n"
									+ "Error: " + ex.getMessage(),
							"Database Error",
							JOptionPane.ERROR_MESSAGE
					);

					ex.printStackTrace();

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(
							Userlogin.this,
							"Could not open the user dashboard.\n"
									+ ex.getMessage(),
							"Application Error",
							JOptionPane.ERROR_MESSAGE
					);

					ex.printStackTrace();
				}
			}
		});

		// RESET BUTTON ACTION
		btnReset.addActionListener(e -> {
			textField.setText("");
			passwordField.setText("");
			textField.requestFocusInWindow();
		});

		// CREATE ACCOUNT BUTTON ACTION
		btnCreateAccount.addActionListener(e -> {
			Create_ac createAccount = new Create_ac();
			createAccount.setLocationRelativeTo(null);
			createAccount.setVisible(true);
		});

		// ADMIN LOGIN BUTTON ACTION
		btnAdminLogin.addActionListener(e -> {
			JOptionPane.showMessageDialog(
					Userlogin.this,
					"Connect this button to your existing Admin Login screen."
			);
		});
	}
}