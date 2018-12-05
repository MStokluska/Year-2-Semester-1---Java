package registrationSystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.*;

public class EditUser extends JFrame implements ActionListener {
	private JLabel name, surname, nameFound, surnameFound, randomAttribute1, randomAttribute2, randomAttribute3,
			randomAttribute4, age;
	private JTextField nameField, surnameField, nameFieldFound, surnameFieldFound, randomAttribute1Field,
			randomAttribute2Field, randomAttribute3Field, randomAttribute4Field, ageField;
	private JButton back, findUser, change;
	private JLabel error;
	private User userToFind;
	private LinkedList<User> registeredUsersSaved;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	private boolean exists = false;
	private JPanel nameFoundPanel = new JPanel();
	private JPanel surnameFoundPanel = new JPanel();
	private JPanel random1Panel = new JPanel();
	private JPanel random2Panel = new JPanel();
	private JPanel random3Panel = new JPanel();
	private JPanel random4Panel = new JPanel();
	private JPanel agePanel = new JPanel();
	private JPanel changePanel = new JPanel();

	private static CurrentSoftwareUser currentUser = new CurrentSoftwareUser();

	public EditUser() {

		super("Edit / Find ");
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

		name = new JLabel("Name");
		nameFound = new JLabel("New Name");
		nameField = new JTextField("", 23);
		nameFieldFound = new JTextField("", 23);

		surname = new JLabel("Surname");
		surnameFound = new JLabel("New Surname ");
		surnameField = new JTextField("", 23);
		surnameFieldFound = new JTextField("", 23);

		randomAttribute1 = new JLabel(currentUser.getRandomAttribute1());
		randomAttribute1Field = new JTextField("", 23);
		randomAttribute2 = new JLabel(currentUser.getRandomAttribute2());
		randomAttribute2Field = new JTextField("", 23);
		randomAttribute3 = new JLabel(currentUser.getRandomAttribute3());
		randomAttribute3Field = new JTextField("", 23);
		randomAttribute4 = new JLabel(currentUser.getRandomAttribute4());
		randomAttribute4Field = new JTextField("", 23);
		age = new JLabel("Age                              ");
		ageField = new JTextField("", 23);
		registeredUsersSaved = new LinkedList<User>();
		back = new JButton("Back");
		if (currentUser.getOrganisationType() == 1) {
			findUser = new JButton("Find Player");
		}
		if (currentUser.getOrganisationType() == 2) {
			findUser = new JButton("Find Fan");
		}
		if (currentUser.getOrganisationType() == 3) {
			findUser = new JButton("Find Subscriber");
		}

		if (currentUser.getOrganisationType() == 2) {
			name.setForeground(Color.WHITE);
			surname.setForeground(Color.WHITE);
			nameFound.setForeground(Color.WHITE);
			surnameFound.setForeground(Color.WHITE);
			randomAttribute1.setForeground(Color.WHITE);
			randomAttribute2.setForeground(Color.WHITE);
			randomAttribute3.setForeground(Color.WHITE);
			randomAttribute4.setForeground(Color.WHITE);
			age.setForeground(Color.WHITE);
		}
		error = new JLabel("");
		change = new JButton("Update Record!");

		JPanel mainBackground = new JPanel();
		mainBackground.setBackground(new Color(255, 255, 255));
		mainBackground.setMaximumSize(new Dimension(400, 600));

		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(400, 50));
		emptyPanel.setBackground(new Color(255, 255, 255));

		JPanel namePanel = new JPanel();
		namePanel.setMaximumSize(new Dimension(400, 30));
		namePanel.setPreferredSize(new Dimension(400, 30));
		JPanel nameInnerPanel = new JPanel();
		JPanel nameFieldInnerPanel = new JPanel();
		nameInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		nameInnerPanel.add(name);
		nameInnerPanel.setPreferredSize(new Dimension(120, 30));
		nameFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		nameFieldInnerPanel.setPreferredSize(new Dimension(270, 30));
		nameFieldInnerPanel.add(nameField);
		nameInnerPanel.setOpaque(false);
		nameFieldInnerPanel.setOpaque(false);
		namePanel.add(nameInnerPanel);
		namePanel.add(nameFieldInnerPanel);

		JPanel surnamePanel = new JPanel();
		surnamePanel.setMaximumSize(new Dimension(400, 30));
		surnamePanel.setPreferredSize(new Dimension(400, 30));
		JPanel surnameInnerPanel = new JPanel();
		JPanel surnameFieldInnerPanel = new JPanel();
		surnameInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		surnameInnerPanel.add(surname);
		surnameInnerPanel.setPreferredSize(new Dimension(120, 30));
		surnameFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		surnameFieldInnerPanel.setPreferredSize(new Dimension(270, 30));
		surnameFieldInnerPanel.add(surnameField);
		surnameInnerPanel.setOpaque(false);
		surnameFieldInnerPanel.setOpaque(false);
		surnamePanel.add(surnameInnerPanel);
		surnamePanel.add(surnameFieldInnerPanel);

		nameFoundPanel.setMaximumSize(new Dimension(400, 55));
		nameFoundPanel.setPreferredSize(new Dimension(400, 40));
		JPanel nameFoundInnerPanel = new JPanel();
		JPanel nameFoundFieldInnerPanel = new JPanel();
		nameFoundInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		nameFoundInnerPanel.add(nameFound);
		nameFoundInnerPanel.setPreferredSize(new Dimension(120, 40));
		nameFoundFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		nameFoundFieldInnerPanel.setPreferredSize(new Dimension(270, 40));
		nameFoundFieldInnerPanel.add(nameFieldFound);
		nameFoundPanel.setOpaque(false);
		nameFoundInnerPanel.setOpaque(false);
		nameFoundFieldInnerPanel.setOpaque(false);
		nameFoundPanel.add(nameFoundInnerPanel);
		nameFoundPanel.add(nameFoundFieldInnerPanel);

		surnameFoundPanel.setMaximumSize(new Dimension(400, 55));
		surnameFoundPanel.setPreferredSize(new Dimension(400, 40));
		JPanel surnameFoundInnerPanel = new JPanel();
		JPanel surnameFoundFieldInnerPanel = new JPanel();
		surnameFoundInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		surnameFoundInnerPanel.add(surnameFound);
		surnameFoundInnerPanel.setPreferredSize(new Dimension(120, 40));
		surnameFoundFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		surnameFoundFieldInnerPanel.setPreferredSize(new Dimension(270, 40));
		surnameFoundFieldInnerPanel.add(surnameFieldFound);
		surnameFoundPanel.setOpaque(false);
		surnameFoundInnerPanel.setOpaque(false);
		surnameFoundFieldInnerPanel.setOpaque(false);
		surnameFoundPanel.add(surnameFoundInnerPanel);
		surnameFoundPanel.add(surnameFoundFieldInnerPanel);

		random1Panel.setMaximumSize(new Dimension(400, 55));
		random1Panel.setPreferredSize(new Dimension(400, 40));
		JPanel random1InnerPanel = new JPanel();
		JPanel random1FieldInnerPanel = new JPanel();
		random1InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random1InnerPanel.add(randomAttribute1);
		random1InnerPanel.setPreferredSize(new Dimension(120, 40));
		random1FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random1FieldInnerPanel.setPreferredSize(new Dimension(270, 40));
		random1FieldInnerPanel.add(randomAttribute1Field);
		random1InnerPanel.setOpaque(false);
		random1FieldInnerPanel.setOpaque(false);
		random1Panel.add(random1InnerPanel);
		random1Panel.add(random1FieldInnerPanel);

		random2Panel.setMaximumSize(new Dimension(400, 55));
		random2Panel.setPreferredSize(new Dimension(400, 40));
		JPanel random2InnerPanel = new JPanel();
		JPanel random2FieldInnerPanel = new JPanel();
		random2InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random2InnerPanel.add(randomAttribute2);
		random2InnerPanel.setPreferredSize(new Dimension(120, 40));
		random2FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random2FieldInnerPanel.setPreferredSize(new Dimension(270, 40));
		random2FieldInnerPanel.add(randomAttribute2Field);
		random2InnerPanel.setOpaque(false);
		random2FieldInnerPanel.setOpaque(false);
		random2Panel.add(random2InnerPanel);
		random2Panel.add(random2FieldInnerPanel);

		random3Panel.setMaximumSize(new Dimension(400, 55));
		random3Panel.setPreferredSize(new Dimension(400, 40));
		JPanel random3InnerPanel = new JPanel();
		JPanel random3FieldInnerPanel = new JPanel();
		random3InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random3InnerPanel.add(randomAttribute3);
		random3InnerPanel.setPreferredSize(new Dimension(120, 40));
		random3FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random3FieldInnerPanel.setPreferredSize(new Dimension(270, 40));
		random3FieldInnerPanel.add(randomAttribute3Field);
		random3InnerPanel.setOpaque(false);
		random3FieldInnerPanel.setOpaque(false);
		random3Panel.add(random3InnerPanel);
		random3Panel.add(random3FieldInnerPanel);

		random4Panel.setMaximumSize(new Dimension(400, 55));
		random4Panel.setPreferredSize(new Dimension(400, 40));
		JPanel random4InnerPanel = new JPanel();
		JPanel random4FieldInnerPanel = new JPanel();
		random4InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random4InnerPanel.add(randomAttribute4);
		random4InnerPanel.setPreferredSize(new Dimension(120, 40));
		random4FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random4FieldInnerPanel.setPreferredSize(new Dimension(270, 40));
		random4FieldInnerPanel.add(randomAttribute4Field);
		random4InnerPanel.setOpaque(false);
		random4FieldInnerPanel.setOpaque(false);
		random4Panel.add(random4InnerPanel);
		random4Panel.add(random4FieldInnerPanel);

		agePanel.setMaximumSize(new Dimension(400, 55));
		agePanel.setPreferredSize(new Dimension(400, 40));
		JPanel ageInnerPanel = new JPanel();
		JPanel ageFieldInnerPanel = new JPanel();
		ageInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ageInnerPanel.add(age);
		ageInnerPanel.setPreferredSize(new Dimension(120, 40));
		ageFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		ageFieldInnerPanel.setPreferredSize(new Dimension(270, 40));
		ageFieldInnerPanel.add(ageField);
		ageInnerPanel.setOpaque(false);
		ageFieldInnerPanel.setOpaque(false);
		agePanel.add(ageInnerPanel);
		agePanel.add(ageFieldInnerPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(400, 55));
		buttonPanel.setPreferredSize(new Dimension(400, 80));
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(back);
		buttonPanel.add(findUser);
		back.setPreferredSize(new Dimension(150, 50));
		findUser.setPreferredSize(new Dimension(150, 50));

		changePanel.setBackground(new Color(255, 255, 255));
		changePanel.setMaximumSize(new Dimension(400, 55));
		changePanel.setPreferredSize(new Dimension(400, 80));
		changePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		changePanel.add(change);
		change.setPreferredSize(new Dimension(150, 50));
		changePanel.setOpaque(false);

		nameFoundPanel.setVisible(false);
		surnameFoundPanel.setVisible(false);
		random1Panel.setVisible(false);
		random2Panel.setVisible(false);
		random3Panel.setVisible(false);
		random4Panel.setVisible(false);
		agePanel.setVisible(false);
		changePanel.setVisible(false);
		buttonPanel.setOpaque(false);
		mainBackground.setOpaque(false);
		emptyPanel.setOpaque(false);
		namePanel.setOpaque(false);
		surnamePanel.setOpaque(false);
		random1Panel.setOpaque(false);
		random2Panel.setOpaque(false);
		random3Panel.setOpaque(false);
		random4Panel.setOpaque(false);
		agePanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		change.addActionListener(this);
		back.addActionListener(this);
		findUser.addActionListener(this);
		mainBackground.add(emptyPanel);
		mainBackground.add(namePanel);
		mainBackground.add(surnamePanel);
		mainBackground.add(buttonPanel);
		mainBackground.add(nameFoundPanel);
		mainBackground.add(surnameFoundPanel);
		mainBackground.add(random1Panel);
		mainBackground.add(random2Panel);
		mainBackground.add(random3Panel);
		mainBackground.add(random4Panel);
		mainBackground.add(agePanel);
		mainBackground.add(changePanel);
		mainBackground.add(error);
		error.setVisible(false);

		allIn.add(mainBackground);
		this.setContentPane(allIn);
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == findUser) {
			boolean nameNotFound = true;
			boolean surnameNotFound = true;
			String empty = "";
			if (nameField.getText().equals(empty) || surnameField.getText().equals(empty)) {
				error.setForeground(Color.RED);
				error.setText("Name and Surname required to view/edit user");
				error.setVisible(true);
			} else {
				userToFind = new User(nameField.getText(), surnameField.getText());
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
						if (iterator.next().getName().equals(userToFind.getName())) {
							nameNotFound = false;
							iterator.previous();
							if (iterator.next().getSurname().equals(userToFind.getSurname())) {
								nameField.setText("");
								surnameField.setText("");
								nameFoundPanel.setVisible(true);
								surnameFoundPanel.setVisible(true);
								random1Panel.setVisible(true);
								random2Panel.setVisible(true);
								random3Panel.setVisible(true);
								random4Panel.setVisible(true);
								agePanel.setVisible(true);
								changePanel.setVisible(true);
								surnameNotFound = false;
								iterator.previous();
								userToFind.setName(iterator.next().getName());
								iterator.previous();
								userToFind.setSurname(iterator.next().getSurname());
								iterator.previous();
								userToFind.setRandomAttribute1((iterator.next().getRandomAttribute1()));
								iterator.previous();
								userToFind.setRandomAttribute2((iterator.next().getRandomAttribute2()));
								iterator.previous();
								userToFind.setRandomAttribute3((iterator.next().getRandomAttribute3()));
								iterator.previous();
								userToFind.setRandomAttribute4((iterator.next().getRandomAttribute4()));
								iterator.previous();
								userToFind.setAge(iterator.next().getAge());
								iterator.previous();
								iterator.remove();
								nameFieldFound.setText(userToFind.getName());
								surnameFieldFound.setText(userToFind.getSurname());
								randomAttribute1Field.setText(userToFind.getRandomAttribute1());
								randomAttribute2Field.setText(userToFind.getRandomAttribute2());
								randomAttribute3Field.setText(userToFind.getRandomAttribute3());
								randomAttribute4Field.setText(userToFind.getRandomAttribute4());
								ageField.setText(Integer.toString(userToFind.getAge()));

							}
						}
					}

				}
			}
			if ((nameNotFound == true) && (surnameNotFound == true)) {
				JOptionPane.showMessageDialog(null, "No such user sorry!", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (event.getSource() == change) {
			System.out.println("In");
			ListIterator<User> iterator = registeredUsersSaved.listIterator();
			while (iterator.hasNext()) {
				if (iterator.next().getName().equals(userToFind.getName())) {
					iterator.previous();
					if (iterator.next().getSurname().equals(userToFind.getSurname())) {
						iterator.previous();
						iterator.remove();
					}
				}
			}
			User updatedUser = new User(nameFieldFound.getText(), surnameFieldFound.getText());
			updatedUser.setRandomAttribute1(randomAttribute1Field.getText());
			updatedUser.setRandomAttribute2(randomAttribute2Field.getText());
			updatedUser.setRandomAttribute3(randomAttribute3Field.getText());
			updatedUser.setRandomAttribute4(randomAttribute4Field.getText());
			updatedUser.setAge(Integer.parseInt(ageField.getText()));
			registeredUsersSaved.add(updatedUser);
			try {
				out = new ObjectOutputStream(new FileOutputStream(currentUser.getId() + "registeredUsers.txt"));
				out.writeObject(registeredUsersSaved);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong, sorry!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "User Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			new MainMenu();
		}
		if (event.getSource() == back) {
			new MainMenu();
			this.setVisible(false);
		}
	}
}
