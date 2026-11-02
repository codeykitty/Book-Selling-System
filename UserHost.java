package Textbook_selling_system;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class UserHost extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UserHost frame = new UserHost();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(
						null,
						"Unable to open dashboard:\n" + e.getMessage(),
						"Error",
						JOptionPane.ERROR_MESSAGE
				);
			}
		});
	}

	public UserHost() {
		setTitle("Book Hub - User Dashboard");

		String iconPath =
				"C:\\Users\\Soumyadeep\\Desktop\\Book Hub\\0 (Custom).jpg";

		File iconFile = new File(iconPath);

		if (iconFile.exists()) {
			setIconImage(
					Toolkit.getDefaultToolkit().getImage(iconPath)
			);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// MENU BAR
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1008, 26);
		contentPane.add(menuBar);

		JMenu mainMenu = new JMenu("Main Menu");
		menuBar.add(mainMenu);

		JMenuItem buyBooksMenuItem = new JMenuItem("Buy Books");
		buyBooksMenuItem.addActionListener(e -> openBuyBooks());
		mainMenu.add(buyBooksMenuItem);

		JMenuItem recycleBooksMenuItem =
				new JMenuItem("Recycle your books");
		recycleBooksMenuItem.addActionListener(e -> openRecycleBooks());
		mainMenu.add(recycleBooksMenuItem);

		// MANAGE MENU
		JMenu manageMenu = new JMenu("Manage");
		menuBar.add(manageMenu);

		JMenu ordersMenu = new JMenu("Orders");
		manageMenu.add(ordersMenu);

		JMenuItem showOrdersMenuItem = new JMenuItem("Show Orders");
		showOrdersMenuItem.addActionListener(e -> openOrderHistory());
		ordersMenu.add(showOrdersMenuItem);

		JMenuItem manageOrdersMenuItem = new JMenuItem("Manage Orders");
		manageOrdersMenuItem.addActionListener(e ->
				JOptionPane.showMessageDialog(
						this,
						"Manage Orders functionality is not implemented yet."
				)
		);
		ordersMenu.add(manageOrdersMenuItem);

		JMenu userAdminMenu = new JMenu("User/Admin");
		manageMenu.add(userAdminMenu);

		JMenuItem manageUsersMenuItem = new JMenuItem("Manage Users");
		manageUsersMenuItem.addActionListener(e ->
				JOptionPane.showMessageDialog(
						this,
						"Manage Users functionality is not implemented yet."
				)
		);
		userAdminMenu.add(manageUsersMenuItem);

		JMenuItem manageAdminsMenuItem = new JMenuItem("Manage Admins");
		manageAdminsMenuItem.addActionListener(e ->
				JOptionPane.showMessageDialog(
						this,
						"Manage Admins functionality is not implemented yet."
				)
		);
		userAdminMenu.add(manageAdminsMenuItem);

		// LOG OUT BUTTON
		JButton logoutButton = new JButton("Log Out");
		logoutButton.setBounds(899, 39, 97, 25);

		logoutButton.addActionListener(e -> {
			int answer = JOptionPane.showConfirmDialog(
					this,
					"Are you sure you want to log out?",
					"Confirm Logout",
					JOptionPane.YES_NO_OPTION
			);

			if (answer == JOptionPane.YES_OPTION) {
				dispose();

				Main_screen mainScreen = new Main_screen();
				mainScreen.setTitle("Book Hub");
				mainScreen.setVisible(true);
			}
		});

		contentPane.add(logoutButton);

		// BUY BOOKS BUTTON
		JButton buyBooksButton = new JButton("Buy Books");
		buyBooksButton.setFont(new Font("Tahoma", Font.PLAIN, 29));
		buyBooksButton.setBounds(46, 197, 264, 118);
		buyBooksButton.addActionListener(e -> openBuyBooks());
		contentPane.add(buyBooksButton);

		// RECYCLE BOOKS BUTTON
		JButton recycleBooksButton = new JButton("Recycle Books");
		recycleBooksButton.setFont(new Font("Tahoma", Font.PLAIN, 29));
		recycleBooksButton.setBounds(374, 197, 264, 118);
		recycleBooksButton.addActionListener(e -> openRecycleBooks());
		contentPane.add(recycleBooksButton);

		// HISTORY BUTTON
		JButton historyButton = new JButton("History");
		historyButton.setFont(new Font("Tahoma", Font.PLAIN, 29));
		historyButton.setBounds(699, 197, 264, 118);

		// Opens order history, not the available-books screen.
		historyButton.addActionListener(e -> openOrderHistory());

		contentPane.add(historyButton);

		// REQUEST BUTTON
		JButton requestButton = new JButton("Request");
		requestButton.setFont(new Font("Tahoma", Font.PLAIN, 29));
		requestButton.setBounds(237, 366, 264, 118);

		requestButton.addActionListener(e -> {
			Request request = new Request();
			request.setVisible(true);
		});

		contentPane.add(requestButton);

		// CONNECT US BUTTON
		JButton connectButton = new JButton("Connect us");
		connectButton.setFont(new Font("Tahoma", Font.PLAIN, 29));
		connectButton.setBounds(550, 366, 264, 118);

		connectButton.addActionListener(e ->
				JOptionPane.showMessageDialog(
						this,
						"Thank you for using Book Hub!"
				)
		);

		contentPane.add(connectButton);

		// HEADING
		JLabel headingLabel = new JLabel("Book Hub");
		headingLabel.setForeground(new Color(255, 255, 0));
		headingLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		headingLabel.setBounds(24, 25, 300, 98);
		contentPane.add(headingLabel);

		// TAGLINE
		JLabel taglineLabel = new JLabel("Buy Sell Recycle");
		taglineLabel.setForeground(new Color(255, 255, 204));
		taglineLabel.setFont(new Font("Sylfaen", Font.PLAIN, 28));
		taglineLabel.setBounds(99, 84, 431, 86);
		contentPane.add(taglineLabel);

		// BACKGROUND IMAGE
		String backgroundPath =
				"C:\\Users\\Soumyadeep\\Desktop\\Book Hub\\why-you-should-read-and-re-read-these-high-school-books-as-an-adult (Custom) (Custom).jpeg";

		File backgroundFile = new File(backgroundPath);

		if (backgroundFile.exists()) {
			JLabel backgroundLabel =
					new JLabel(new ImageIcon(backgroundPath));

			backgroundLabel.setBounds(0, 25, 1008, 537);
			contentPane.add(backgroundLabel);

			// Place background behind the other controls.
			contentPane.setComponentZOrder(
					backgroundLabel,
					contentPane.getComponentCount() - 1
			);
		}
	}

	// OPEN AVAILABLE BOOKS
	private void openBuyBooks() {
		BuyBooks window = new BuyBooks();
		window.setVisible(true);
	}

	// OPEN RECYCLE BOOKS
	private void openRecycleBooks() {
		Add_old_books window = new Add_old_books();
		window.setVisible(true);
	}

	// OPEN ORDER HISTORY
	private void openOrderHistory() {
		Show_bookings window = new Show_bookings();
		window.setVisible(true);
	}
}