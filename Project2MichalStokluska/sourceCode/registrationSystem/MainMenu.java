package registrationSystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
	private JButton addUser, deleteUser, editUser, ageCalculations, viewAllUsers, logOut, exit;
	private JLabel orgTitle;

	private static CurrentSoftwareUser currentUser = new CurrentSoftwareUser();

	public MainMenu() {
		super("Welcome " + currentUser.getId());
		if (currentUser.getOrganisationType() == 1) {
			setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("Images/green.jpg"))));
		}
		if (currentUser.getOrganisationType() == 2) {
			setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("Images/red.jpg"))));
		}
		if (currentUser.getOrganisationType() == 3) {
			setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("Images/blue.jpg"))));
		}
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Container allIn = getContentPane();
		setLayout(new FlowLayout());
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		if (currentUser.getOrganisationType() == 1) {
			addUser = new JButton("Add Player");
			deleteUser = new JButton("Delete Player");
			editUser = new JButton("Edit / View Player");
			ageCalculations = new JButton("Age Statistics");
			viewAllUsers = new JButton("View All Players");
			orgTitle = new JLabel(currentUser.getOrganisationName());
			orgTitle.setFont(new Font("Serif", Font.BOLD, 36));
			orgTitle.setForeground(new Color(34, 139, 34));
		}
		if (currentUser.getOrganisationType() == 3) {
			addUser = new JButton("Add Subscriber");
			deleteUser = new JButton("Delete Subcriber");
			editUser = new JButton("Edit / View Subscriber");
			ageCalculations = new JButton("Age Statistics");
			viewAllUsers = new JButton("View All Subscribers");
			orgTitle = new JLabel(currentUser.getOrganisationName());
			orgTitle.setFont(new Font("Serif", Font.BOLD, 36));
			orgTitle.setForeground(Color.BLUE);
		}
		if (currentUser.getOrganisationType() == 2) {
			addUser = new JButton("Add New Fan");
			deleteUser = new JButton("Delete Fan");
			editUser = new JButton("Edit / View Fan Details");
			ageCalculations = new JButton("Age Statistics");
			viewAllUsers = new JButton("View All Fans");
			orgTitle = new JLabel(currentUser.getOrganisationName());
			orgTitle.setFont(new Font("Serif", Font.BOLD, 36));
			orgTitle.setForeground(Color.RED);
		}

		logOut = new JButton("Log Out");
		exit = new JButton("Exit");

		JPanel mainBackground = new JPanel();
		mainBackground.setMaximumSize(new Dimension(400, 550));

		JPanel titlePanel = new JPanel();
		titlePanel.setMaximumSize(new Dimension(400, 80));
		titlePanel.setPreferredSize(new Dimension(400, 80));
		titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(orgTitle);

		JPanel createUserPanel = new JPanel();
		createUserPanel.setMaximumSize(new Dimension(400, 55));
		createUserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		createUserPanel.add(addUser);
		addUser.setPreferredSize(new Dimension(400, 50));

		JPanel deleteUserPanel = new JPanel();
		deleteUserPanel.setMaximumSize(new Dimension(400, 55));
		deleteUserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteUserPanel.add(deleteUser);
		deleteUser.setPreferredSize(new Dimension(400, 50));

		JPanel editUserPanel = new JPanel();
		editUserPanel.setMaximumSize(new Dimension(400, 55));
		editUserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		editUserPanel.add(editUser);
		editUser.setPreferredSize(new Dimension(400, 50));

		JPanel ageCalculationsPanel = new JPanel();
		ageCalculationsPanel.setMaximumSize(new Dimension(400, 55));
		ageCalculationsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ageCalculationsPanel.add(ageCalculations);
		ageCalculations.setPreferredSize(new Dimension(400, 50));

		JPanel viewAllUsersPanel = new JPanel();
		viewAllUsersPanel.setMaximumSize(new Dimension(400, 55));
		viewAllUsersPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewAllUsersPanel.add(viewAllUsers);
		viewAllUsers.setPreferredSize(new Dimension(400, 50));

		JPanel logOutPanel = new JPanel();
		logOutPanel.setMaximumSize(new Dimension(400, 55));
		logOutPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		logOutPanel.add(logOut);
		logOut.setPreferredSize(new Dimension(400, 50));

		JPanel exitPanel = new JPanel();
		exitPanel.setMaximumSize(new Dimension(400, 55));
		exitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitPanel.add(exit);
		exit.setPreferredSize(new Dimension(400, 50));

		addUser.addActionListener(this);
		deleteUser.addActionListener(this);
		viewAllUsers.addActionListener(this);
		ageCalculations.addActionListener(this);
		logOut.addActionListener(this);
		editUser.addActionListener(this);
		exit.addActionListener(this);
		titlePanel.setOpaque(false);
		createUserPanel.setOpaque(false);
		deleteUserPanel.setOpaque(false);
		editUserPanel.setOpaque(false);
		ageCalculationsPanel.setOpaque(false);
		viewAllUsersPanel.setOpaque(false);
		logOutPanel.setOpaque(false);
		exitPanel.setOpaque(false);
		mainBackground.add(titlePanel);
		mainBackground.add(createUserPanel);
		mainBackground.add(deleteUserPanel);
		mainBackground.add(editUserPanel);
		mainBackground.add(ageCalculationsPanel);
		mainBackground.add(viewAllUsersPanel);
		mainBackground.add(logOutPanel);
		mainBackground.add(exitPanel);

		mainBackground.setOpaque(false);

		allIn.add(mainBackground);

		this.setContentPane(allIn);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == addUser) {
			new AddUser();
		}
		if (event.getSource() == deleteUser) {
			new DeleteUser();
		}
		if (event.getSource() == editUser) {
			new EditUser();
		}
		if (event.getSource() == viewAllUsers) {
			new ViewAllUsers();
		}
		if (event.getSource() == ageCalculations) {
			new AgeStatistics();
		}
		if (event.getSource() == logOut) {
			this.setVisible(false);
			new LogInWindow();
		}
		if (event.getSource() == exit) {
			System.exit(0);
		}

	}

}
