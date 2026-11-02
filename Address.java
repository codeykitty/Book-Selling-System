package Textbook_selling_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Address extends JFrame {

	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/book_selling_system";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "N@veen2008";

	private final int bookId;
	private final String bookName;
	private final BigDecimal price;

	private JTextField nameField;
	private JTextField pincodeField;
	private JTextArea addressArea;
	private JTextField landmarkField;

	public Address() {
		this(-1, "", BigDecimal.ZERO);
	}

	public Address(int bookId, String bookName, BigDecimal price) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.price = price;

		setTitle("Delivery Address");
		setSize(514, 460);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel nameLabel = new JLabel("Full Name:");
		nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nameLabel.setBounds(26, 13, 125, 41);
		contentPane.add(nameLabel);

		nameField = new JTextField();
		nameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nameField.setBounds(155, 15, 325, 39);
		contentPane.add(nameField);

		JLabel pincodeLabel = new JLabel("Pincode:");
		pincodeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		pincodeLabel.setBounds(26, 74, 117, 41);
		contentPane.add(pincodeLabel);

		pincodeField = new JTextField();
		pincodeField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		pincodeField.setBounds(155, 79, 165, 41);
		contentPane.add(pincodeField);

		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		addressLabel.setBounds(26, 150, 117, 41);
		contentPane.add(addressLabel);

		addressArea = new JTextArea();
		addressArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		addressArea.setLineWrap(true);
		addressArea.setWrapStyleWord(true);

		JScrollPane addressScroll = new JScrollPane(addressArea);
		addressScroll.setBounds(155, 133, 325, 124);
		contentPane.add(addressScroll);

		JLabel landmarkLabel = new JLabel("Landmark:");
		landmarkLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		landmarkLabel.setBounds(26, 282, 117, 41);
		contentPane.add(landmarkLabel);

		landmarkField = new JTextField();
		landmarkField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		landmarkField.setBounds(155, 287, 325, 41);
		contentPane.add(landmarkField);

		JButton placeOrderButton = new JButton("Place Order");
		placeOrderButton.setBounds(148, 345, 172, 55);
		contentPane.add(placeOrderButton);

		placeOrderButton.addActionListener(e -> placeOrder());
	}

	private void placeOrder() {
		String name = nameField.getText().trim();
		String pincode = pincodeField.getText().trim();
		String address = addressArea.getText().trim();
		String landmark = landmarkField.getText().trim();

		if (bookId <= 0) {
			JOptionPane.showMessageDialog(
					this,
					"Please select a book from Buy Books first."
			);
			return;
		}

		if (name.isEmpty() || pincode.isEmpty() || address.isEmpty()) {
			JOptionPane.showMessageDialog(
					this,
					"Please enter your name, pincode and address."
			);
			return;
		}

		if (!pincode.matches("\\d{6}")) {
			JOptionPane.showMessageDialog(
					this,
					"Enter a valid 6-digit Indian pincode."
			);
			return;
		}

		int choice = JOptionPane.showConfirmDialog(
				this,
				"Book: " + bookName
						+ "\nPrice: ₹" + price
						+ "\n\nPlace this order?",
				"Confirm Order",
				JOptionPane.YES_NO_OPTION
		);

		if (choice != JOptionPane.YES_OPTION) {
			return;
		}

		String orderId = UUID.randomUUID()
				.toString()
				.replace("-", "")
				.substring(0, 12)
				.toUpperCase();

		String sql = "INSERT INTO orders "
				+ "(order_id, book_id, book_name, price, customer_name, "
				+ "pincode, delivery_address, landmark) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(
				DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement statement =
					 connection.prepareStatement(sql)) {

			statement.setString(1, orderId);
			statement.setInt(2, bookId);
			statement.setString(3, bookName);
			statement.setBigDecimal(4, price);
			statement.setString(5, name);
			statement.setString(6, pincode);
			statement.setString(7, address);
			statement.setString(8, landmark);

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(
						this,
						"Congratulations, " + name + "!\n\n"
								+ "Your order was placed successfully."
								+ "\nOrder ID: " + orderId
								+ "\nBook: " + bookName
								+ "\nPrice: ₹" + price
								+ "\n\nThank you for shopping with us!"
				);

				dispose();
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(
					this,
					"Could not place your order:\n" + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE
			);
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->
				new Address().setVisible(true)
		);
	}
}