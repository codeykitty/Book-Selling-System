package Textbook_selling_system;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Create_ac extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField email;
	private JTextField username;
	private JTextField mob;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel label;

	// MySQL connection settings
	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/book_selling_system";
	private static final String DB_USER = "root";

	// Replace this with your actual MySQL root password.
	// If your root account has no password, use "".
	private static final String DB_PASSWORD = "N@veen2008";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Create_ac frame = new Create_ac();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Create_ac() {

		setTitle("Book Hub - Registration");

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Soumyadeep\\Desktop\\Book Hub\\0 (Custom).jpg"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewUserRegister = new JLabel("New User Register");
		lblNewUserRegister.setForeground(new Color(102, 255, 0));
		lblNewUserRegister.setFont(
				new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);

		JLabel lblName = new JLabel("First name");
		lblName.setForeground(new Color(255, 204, 51));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 151, 120, 43);
		contentPane.add(lblName);

		JLabel lblNewLabel = new JLabel("Last name");
		lblNewLabel.setForeground(new Color(255, 204, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 243, 110, 29);
		contentPane.add(lblNewLabel);

		JLabel lblEmailAddress = new JLabel("Email address");
		lblEmailAddress.setForeground(new Color(255, 204, 0));
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailAddress.setBounds(58, 324, 145, 36);
		contentPane.add(lblEmailAddress);

		firstname = new JTextField();
		firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
		firstname.setBounds(214, 151, 228, 50);
		contentPane.add(firstname);
		firstname.setColumns(10);

		lastname = new JTextField();
		lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lastname.setBounds(214, 235, 228, 50);
		contentPane.add(lastname);
		lastname.setColumns(10);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 32));
		email.setBounds(214, 320, 228, 50);
		contentPane.add(email);
		email.setColumns(10);

		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 32));
		username.setBounds(707, 151, 228, 50);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 204, 0));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(542, 159, 120, 29);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 204, 0));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(542, 245, 110, 24);
		contentPane.add(lblPassword);

		JLabel lblMobileNumber = new JLabel("Mobile number");
		lblMobileNumber.setForeground(new Color(255, 204, 0));
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMobileNumber.setBounds(542, 329, 155, 26);
		contentPane.add(lblMobileNumber);

		mob = new JTextField();
		mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
		mob.setBounds(707, 320, 228, 50);
		contentPane.add(mob);
		mob.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		passwordField.setBounds(707, 235, 228, 50);
		contentPane.add(passwordField);

		// Register button
		btnNewButton = new JButton("Register");

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String first = firstname.getText().trim();
				String last = lastname.getText().trim();
				String emailadd = email.getText().trim();
				String user1 = username.getText().trim();
				String mobile = mob.getText().trim();
				String pass1 = new String(passwordField.getPassword());

				// Validate empty fields
				if (first.isEmpty() || last.isEmpty()
						|| emailadd.isEmpty() || user1.isEmpty()
						|| mobile.isEmpty() || pass1.isEmpty()) {

					JOptionPane.showMessageDialog(
							Create_ac.this,
							"Please fill in all fields.",
							"Missing Information",
							JOptionPane.WARNING_MESSAGE
					);
					return;
				}

				// Validate mobile number
				if (!mobile.matches("\\d{10}")) {
					JOptionPane.showMessageDialog(
							Create_ac.this,
							"Enter a valid 10-digit mobile number.",
							"Invalid Mobile Number",
							JOptionPane.WARNING_MESSAGE
					);
					return;
				}

				String query =
						"INSERT INTO account "
								+ "(first, last, user1, pass1, emailadd, mb) "
								+ "VALUES (?, ?, ?, ?, ?, ?)";

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");

					try (Connection con = DriverManager.getConnection(
							DB_URL, DB_USER, DB_PASSWORD);
						 PreparedStatement sta =
								 con.prepareStatement(query)) {

						sta.setString(1, first);
						sta.setString(2, last);
						sta.setString(3, user1);
						sta.setString(4, pass1);
						sta.setString(5, emailadd);
						sta.setString(6, mobile);

						int result = sta.executeUpdate();

						if (result > 0) {
							JOptionPane.showMessageDialog(
									Create_ac.this,
									"Welcome, " + first
											+ "! Your account was created successfully.",
									"Registration Successful",
									JOptionPane.INFORMATION_MESSAGE
							);

							// Clear the form after successful registration
							firstname.setText("");
							lastname.setText("");
							email.setText("");
							username.setText("");
							mob.setText("");
							passwordField.setText("");
						}
					}

				} catch (SQLException ex) {

					if (ex.getErrorCode() == 1062) {
						JOptionPane.showMessageDialog(
								Create_ac.this,
								"This username already exists. "
										+ "Please choose another username.",
								"Duplicate Username",
								JOptionPane.WARNING_MESSAGE
						);
					} else {
						ex.printStackTrace();

						JOptionPane.showMessageDialog(
								Create_ac.this,
								"Database error: " + ex.getMessage(),
								"Registration Failed",
								JOptionPane.ERROR_MESSAGE
						);
					}

				} catch (ClassNotFoundException ex) {

					JOptionPane.showMessageDialog(
							Create_ac.this,
							"MySQL JDBC driver not found. "
									+ "Please check the project libraries.",
							"Driver Error",
							JOptionPane.ERROR_MESSAGE
					);

					ex.printStackTrace();
				}
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(345, 397, 139, 50);
		contentPane.add(btnNewButton);

		// Sign-in button
		btnNewButton_1 =
				new JButton("Already Have Account ? Sign in");

		btnNewButton_1.addActionListener(e -> {
			dispose();

			Userlogin us = new Userlogin();
			us.setTitle("Book Hub");
			us.setVisible(true);
		});

		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton_1.setBounds(297, 474, 435, 50);
		contentPane.add(btnNewButton_1);

		// Reset button
		btnNewButton_2 = new JButton("Reset");

		btnNewButton_2.addActionListener(e -> {
			firstname.setText("");
			lastname.setText("");
			email.setText("");
			username.setText("");
			mob.setText("");
			passwordField.setText("");
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton_2.setBounds(542, 397, 139, 50);
		contentPane.add(btnNewButton_2);

		// Background image
		label = new JLabel("");
		label.setForeground(new Color(255, 204, 0));
		label.setIcon(new ImageIcon(
				"C:\\Users\\Soumyadeep\\Desktop\\Book Hub\\3 (Custom).jpg"));
		label.setBounds(0, 0, 1008, 562);
		contentPane.add(label);
	}
}