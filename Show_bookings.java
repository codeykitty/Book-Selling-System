package Textbook_selling_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Show_bookings extends JFrame {

	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/book_selling_system";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "N@veen2008";

	private final DefaultTableModel model;

	public Show_bookings() {
		setTitle("Order History");
		setSize(1014, 460);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		JLabel heading = new JLabel(
				"Order History", SwingConstants.CENTER
		);
		heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
		add(heading, BorderLayout.NORTH);

		String[] columns = {
				"Order ID",
				"Name",
				"Address",
				"Book Name",
				"Price",
				"Condition"
		};

		model = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		add(new JScrollPane(table), BorderLayout.CENTER);

		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(e -> loadOrders());

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(e -> dispose());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(refreshButton);
		buttonPanel.add(closeButton);

		add(buttonPanel, BorderLayout.SOUTH);

		loadOrders();
	}

	private void loadOrders() {
		model.setRowCount(0);

		String sql = "SELECT o.order_id, o.customer_name, "
				+ "o.delivery_address, o.book_name, o.price, "
				+ "COALESCE(b.book_condition, 'Unavailable') AS book_condition "
				+ "FROM orders o "
				+ "LEFT JOIN books b ON o.book_id = b.id "
				+ "ORDER BY o.order_date DESC";

		try (Connection connection = DriverManager.getConnection(
				DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement statement =
					 connection.prepareStatement(sql);
			 ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				model.addRow(new Object[]{
						result.getString("order_id"),
						result.getString("customer_name"),
						result.getString("delivery_address"),
						result.getString("book_name"),
						result.getBigDecimal("price"),
						result.getString("book_condition")
				});
			}

			if (model.getRowCount() == 0) {
				JOptionPane.showMessageDialog(
						this,
						"No orders have been placed yet."
				);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(
					this,
					"Could not load order history:\n" + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE
			);
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->
				new Show_bookings().setVisible(true)
		);
	}
}