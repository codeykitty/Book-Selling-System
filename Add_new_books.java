package Textbook_selling_system;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_new_books extends JFrame {

	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/book_selling_system";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "N@veen2008";

	private JTextField bookNameField;
	private JTextField writerField;
	private JTextField publisherField;
	private JTextField priceField;
	private JTextField sourceField;

	private JComboBox<String> subjectBox;
	private JComboBox<String> conditionBox;
	private JComboBox<String> dayBox;
	private JComboBox<String> monthBox;
	private JComboBox<String> yearBox;

	public Add_new_books() {
		setTitle("Add New Book");
		setSize(500, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		bookNameField = new JTextField();
		writerField = new JTextField();
		publisherField = new JTextField();
		priceField = new JTextField();
		sourceField = new JTextField();

		subjectBox = new JComboBox<>(new String[]{
				"English", "Hindi", "Bengali", "Maths",
				"Physics", "Chemistry", "Biology", "Computer",
				"History", "Geography", "Others"
		});

		conditionBox = new JComboBox<>(new String[]{"New", "Old"});

		String[] days = new String[31];
		for (int i = 0; i < 31; i++) {
			days[i] = String.valueOf(i + 1);
		}
		dayBox = new JComboBox<>(days);

		String[] months = {
				"Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
		};
		monthBox = new JComboBox<>(months);

		String[] years = new String[126];
		for (int i = 0; i < years.length; i++) {
			years[i] = String.valueOf(1901 + i);
		}
		yearBox = new JComboBox<>(years);
		yearBox.setSelectedItem("2026");

		panel.add(new JLabel("Book Name:"));
		panel.add(bookNameField);

		panel.add(new JLabel("Writer Name:"));
		panel.add(writerField);

		panel.add(new JLabel("Condition:"));
		panel.add(conditionBox);

		panel.add(new JLabel("Subject:"));
		panel.add(subjectBox);

		panel.add(new JLabel("Publisher:"));
		panel.add(publisherField);

		panel.add(new JLabel("Price:"));
		panel.add(priceField);

		panel.add(new JLabel("Source:"));
		panel.add(sourceField);

		panel.add(new JLabel("Published Day:"));
		panel.add(dayBox);

		panel.add(new JLabel("Published Month:"));
		panel.add(monthBox);

		panel.add(new JLabel("Published Year:"));
		panel.add(yearBox);

		JButton addButton = new JButton("Add Book");
		addButton.addActionListener(e -> addBook());

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(e -> dispose());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(closeButton);

		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void addBook() {
		String name = bookNameField.getText().trim();
		String writer = writerField.getText().trim();
		String publisher = publisherField.getText().trim();
		String source = sourceField.getText().trim();
		String priceText = priceField.getText().trim();

		String condition = (String) conditionBox.getSelectedItem();
		String subject = (String) subjectBox.getSelectedItem();

		String date = dayBox.getSelectedItem() + "-"
				+ monthBox.getSelectedItem() + "-"
				+ yearBox.getSelectedItem();

		if (name.isEmpty() || writer.isEmpty() || priceText.isEmpty()) {
			JOptionPane.showMessageDialog(
					this,
					"Enter the book name, writer name and price."
			);
			return;
		}

		double price;

		try {
			price = Double.parseDouble(priceText);

			if (!Double.isFinite(price) || price < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(
					this,
					"Enter a valid, non-negative price."
			);
			return;
		}

		String sql = "INSERT INTO books "
				+ "(book_name, writer, book_condition, source, "
				+ "publisher, subject, price, published_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(
				DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, name);
			statement.setString(2, writer);
			statement.setString(3, condition);
			statement.setString(4, source);
			statement.setString(5, publisher);
			statement.setString(6, subject);
			statement.setBigDecimal(7, java.math.BigDecimal.valueOf(price));
			statement.setString(8, date);

			statement.executeUpdate();

			JOptionPane.showMessageDialog(
					this,
					"Book added successfully!"
			);

			bookNameField.setText("");
			writerField.setText("");
			publisherField.setText("");
			priceField.setText("");
			sourceField.setText("");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(
					this,
					"Could not add book:\n" + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE
			);
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->
				new Add_new_books().setVisible(true)
		);
	}
}