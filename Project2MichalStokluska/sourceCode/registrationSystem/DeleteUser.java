package registrationSystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.*;

public class DeleteUser extends JFrame implements ActionListener {
	private JLabel name, surname;
	private JTextField nameField, surnameField;
	private JButton back, delete;
	private JLabel error;
	private User userToDelete;
	private LinkedList<User> registeredUsers, registeredUsersSaved;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	private boolean exists = false;

	private static CurrentSoftwareUser currentUser = new CurrentSoftwareUser();

	public DeleteUser() {
		super("Delete User");
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

		name = new JLabel("Name                          ");
		nameField = new JTextField("", 23);
		surname = new JLabel("Surname                    ");
		surnameField = new JTextField("", 23);
		registeredUsers = new LinkedList<User>();
		registeredUsersSaved = new LinkedList<User>();
		back = new JButton("Back");
		delete = new JButton("Delete User");
		error = new JLabel("");
		if (currentUser.getOrganisationType() == 2) {
			name.setForeground(Color.WHITE);
			surname.setForeground(Color.WHITE);
		}
		JPanel mainBackground = new JPanel();
		mainBackground.setMaximumSize(new Dimension(400, 600));

		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(400, 50));

		JPanel namePanel = new JPanel();
		namePanel.setMaximumSize(new Dimension(400, 55));
		namePanel.setPreferredSize(new Dimension(400, 80));
		namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(name);
		namePanel.add(nameField);

		JPanel surnamePanel = new JPanel();
		surnamePanel.setMaximumSize(new Dimension(400, 55));
		surnamePanel.setPreferredSize(new Dimension(400, 80));
		surnamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		surnamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		surnamePanel.add(surname);
		surnamePanel.add(surnameField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(400, 55));
		buttonPanel.setPreferredSize(new Dimension(400, 80));
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(back);
		buttonPanel.add(delete);
		back.setPreferredSize(new Dimension(150, 50));
		delete.setPreferredSize(new Dimension(150, 50));

		emptyPanel.setOpaque(false);
		namePanel.setOpaque(false);
		surnamePanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		mainBackground.setOpaque(false);
		back.addActionListener(this);
		delete.addActionListener(this);
		mainBackground.add(emptyPanel);
		mainBackground.add(namePanel);
		mainBackground.add(surnamePanel);
		mainBackground.add(buttonPanel);
		mainBackground.add(error);
		error.setVisible(false);

		allIn.add(mainBackground);
		this.setContentPane(allIn);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == delete) {
			String empty = "";

			if (nameField.getText().equals(empty) || surnameField.getText().equals(empty)) {
				error.setForeground(Color.RED);
				error.setText("Name and Surname required to delete user");
				error.setVisible(true);
			} else {
				userToDelete = new User(nameField.getText(), surnameField.getText());

				File registeredUsersFile = new File(currentUser.getId() + "registeredUsers.txt");
				if (registeredUsersFile.exists()) {
					try {
						in = new ObjectInputStream(new FileInputStream(currentUser.getId() + "registeredUsers.txt"));
						registeredUsersSaved = (LinkedList) in.readObject();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Can not locate file", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
					ListIterator<User> iterator = registeredUsersSaved.listIterator();
					while (iterator.hasNext()) {
						if (iterator.next().getName().equals(userToDelete.getName())) {
							iterator.previous();
							if (iterator.next().getSurname().equals(userToDelete.getSurname())) {
								exists = true;
								iterator.previous();
								iterator.remove();
								JOptionPane.showMessageDialog(null, "User Removed", "Error",
										JOptionPane.WARNING_MESSAGE);
								this.setVisible(false);
								new DeleteUser();
								try {
									out = new ObjectOutputStream(
											new FileOutputStream(currentUser.getId() + "registeredUsers.txt"));
									out.writeObject(registeredUsersSaved);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
					if (exists == false) {
						JOptionPane.showMessageDialog(null, "No such user exists", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
		if (event.getSource() == back) {
			new MainMenu();
			this.setVisible(false);
		}

	}

}
