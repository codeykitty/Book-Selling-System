package Textbook_selling_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyBooks extends JFrame {

	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/book_selling_system";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "N@veen2008";

	private JTable table;
	private DefaultTableModel model;

	public BuyBooks() {
		setTitle("Buy Books");
		setSize(700, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		JLabel heading = new JLabel(
				"Available Books", SwingConstants.CENTER
		);
		heading.setFont(new Font("Arial", Font.BOLD, 22));
		add(heading, BorderLayout.NORTH);

		model = new DefaultTableModel(
				new String[]{"Book ID", "Book Name", "Price", "Writer"}, 0
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(new JScrollPane(table), BorderLayout.CENTER);

		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(e -> loadBooks());

		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(e -> openAddress());

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(e -> dispose());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(refreshButton);
		buttonPanel.add(buyButton);
		buttonPanel.add(closeButton);

		add(buttonPanel, BorderLayout.SOUTH);

		loadBooks();
	}

	private void loadBooks() {
		model.setRowCount(0);

		String sql = "SELECT id, book_name, price, writer "
				+ "FROM books ORDER BY id DESC";

		try (Connection connection = DriverManager.getConnection(
				DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				model.addRow(new Object[]{
						result.getInt("id"),
						result.getString("book_name"),
						result.getBigDecimal("price"),
						result.getString("writer")
				});
			}

			if (model.getRowCount() == 0) {
				JOptionPane.showMessageDialog(
						this,
						"No books available. Add a book first."
				);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(
					this,
					"Could not load books:\n" + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE
			);
			ex.printStackTrace();
		}
	}

	private void openAddress() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(
					this,
					"Please select a book first."
			);
			return;
		}

		int bookId = Integer.parseInt(
				model.getValueAt(selectedRow, 0).toString()
		);

		String bookName =
				model.getValueAt(selectedRow, 1).toString();

		BigDecimal price = new BigDecimal(
				model.getValueAt(selectedRow, 2).toString()
		);

		int choice = JOptionPane.showConfirmDialog(
				this,
				"Book: " + bookName
						+ "\nPrice: ₹" + price
						+ "\n\nContinue to delivery address?",
				"Confirm Selection",
				JOptionPane.YES_NO_OPTION
		);

		if (choice == JOptionPane.YES_OPTION) {
			Address addressWindow =
					new Address(bookId, bookName, price);
			addressWindow.setVisible(true);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->
				new BuyBooks().setVisible(true)
		);
	}
}