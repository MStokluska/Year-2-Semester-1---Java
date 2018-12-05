package registrationSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

//this is a window that shows when Add user button is pressed. Main purpose is to create users with 4 different random attributes and 3 preassigned attributes
//random attributes are pulled from static class corresponing to logged in user

public class AddUser extends JFrame implements ActionListener {
	private JLabel name, surname, randomAttribute1, randomAttribute2, randomAttribute3, randomAttribute4, age;
	private JTextField nameField, surnameField, randomAttribute1Field, randomAttribute2Field, randomAttribute3Field,
			randomAttribute4Field, ageField;
	private JButton back, create;
	private JLabel error;
	private User userToRegister;
	private LinkedList<User> registeredUsers, registeredUsersSaved;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private boolean exists = false;
	private static CurrentSoftwareUser currentUser = new CurrentSoftwareUser();

	public AddUser() {
		super("Add User");

		// background selection based on club type
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
		surname = new JLabel("Surname");
		randomAttribute1 = new JLabel(currentUser.getRandomAttribute1());
		randomAttribute2 = new JLabel(currentUser.getRandomAttribute2());
		randomAttribute3 = new JLabel(currentUser.getRandomAttribute3());
		randomAttribute4 = new JLabel(currentUser.getRandomAttribute4());
		age = new JLabel("Age");
		// if the background is black - club fan - fonts have been changed to white
		// color to be visible a little bit better
		if (currentUser.getOrganisationType() == 2) {
			name.setForeground(Color.WHITE);
			surname.setForeground(Color.WHITE);
			randomAttribute1.setForeground(Color.WHITE);
			randomAttribute2.setForeground(Color.WHITE);
			randomAttribute3.setForeground(Color.WHITE);
			randomAttribute4.setForeground(Color.WHITE);
			age.setForeground(Color.WHITE);
		}
		nameField = new JTextField("", 23);
		surnameField = new JTextField("", 23);
		randomAttribute1Field = new JTextField("", 23);
		randomAttribute2Field = new JTextField("", 23);
		randomAttribute3Field = new JTextField("", 23);
		randomAttribute4Field = new JTextField("", 23);
		ageField = new JTextField("", 23);
		registeredUsers = new LinkedList<User>();
		registeredUsersSaved = new LinkedList<User>();
		back = new JButton("Back");
		// depending on type of background ( or club type ) we can add player, fan or
		// subscriber
		if (currentUser.getOrganisationType() == 1) {
			create = new JButton("Add Player");
		}
		if (currentUser.getOrganisationType() == 2) {
			create = new JButton("Add Fan");
		}
		if (currentUser.getOrganisationType() == 3) {
			create = new JButton("Add Subscriber");
		}
		error = new JLabel("");

		JPanel mainBackground = new JPanel();
		mainBackground.setBackground(new Color(255, 255, 255));
		mainBackground.setMaximumSize(new Dimension(400, 600));
		// empty panel serves as a gap from top to next field
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(400, 50));
		emptyPanel.setBackground(new Color(255, 255, 255));

		// each panel consists of inner panel which consists of label and inner panel
		// which consists of particular label text field.
		// im sure there's a way of making it easier...
		JPanel namePanel = new JPanel();
		namePanel.setMaximumSize(new Dimension(400, 55));
		namePanel.setPreferredSize(new Dimension(400, 55));
		JPanel nameInnerPanel = new JPanel();
		JPanel nameFieldInnerPanel = new JPanel();
		nameInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		nameInnerPanel.add(name);
		nameInnerPanel.setPreferredSize(new Dimension(120, 55));
		nameFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		nameFieldInnerPanel.setPreferredSize(new Dimension(270, 55));
		nameFieldInnerPanel.add(nameField);
		nameInnerPanel.setOpaque(false);
		nameFieldInnerPanel.setOpaque(false);
		namePanel.add(nameInnerPanel);
		namePanel.add(nameFieldInnerPanel);

		JPanel surnamePanel = new JPanel();
		surnamePanel.setMaximumSize(new Dimension(400, 55));
		surnamePanel.setPreferredSize(new Dimension(400, 55));
		JPanel surnameInnerPanel = new JPanel();
		JPanel surnameFieldInnerPanel = new JPanel();
		surnameInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		surnameInnerPanel.add(surname);
		surnameInnerPanel.setPreferredSize(new Dimension(120, 55));
		surnameFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		surnameFieldInnerPanel.setPreferredSize(new Dimension(270, 55));
		surnameFieldInnerPanel.add(surnameField);
		surnameInnerPanel.setOpaque(false);
		surnameFieldInnerPanel.setOpaque(false);
		surnamePanel.add(surnameInnerPanel);
		surnamePanel.add(surnameFieldInnerPanel);

		JPanel random1Panel = new JPanel();
		random1Panel.setMaximumSize(new Dimension(400, 55));
		random1Panel.setPreferredSize(new Dimension(400, 55));
		JPanel random1InnerPanel = new JPanel();
		JPanel random1FieldInnerPanel = new JPanel();
		random1InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random1InnerPanel.add(randomAttribute1);
		random1InnerPanel.setPreferredSize(new Dimension(120, 55));
		random1FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random1FieldInnerPanel.setPreferredSize(new Dimension(270, 55));
		random1FieldInnerPanel.add(randomAttribute1Field);
		random1InnerPanel.setOpaque(false);
		random1FieldInnerPanel.setOpaque(false);
		random1Panel.add(random1InnerPanel);
		random1Panel.add(random1FieldInnerPanel);

		JPanel random2Panel = new JPanel();
		random2Panel.setMaximumSize(new Dimension(400, 55));
		random2Panel.setPreferredSize(new Dimension(400, 55));
		JPanel random2InnerPanel = new JPanel();
		JPanel random2FieldInnerPanel = new JPanel();
		random2InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random2InnerPanel.add(randomAttribute2);
		random2InnerPanel.setPreferredSize(new Dimension(120, 55));
		random2FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random2FieldInnerPanel.setPreferredSize(new Dimension(270, 55));
		random2FieldInnerPanel.add(randomAttribute2Field);
		random2InnerPanel.setOpaque(false);
		random2FieldInnerPanel.setOpaque(false);
		random2Panel.add(random2InnerPanel);
		random2Panel.add(random2FieldInnerPanel);

		JPanel random3Panel = new JPanel();
		random3Panel.setMaximumSize(new Dimension(400, 55));
		random3Panel.setPreferredSize(new Dimension(400, 55));
		JPanel random3InnerPanel = new JPanel();
		JPanel random3FieldInnerPanel = new JPanel();
		random3InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random3InnerPanel.add(randomAttribute3);
		random3InnerPanel.setPreferredSize(new Dimension(120, 55));
		random3FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random3FieldInnerPanel.setPreferredSize(new Dimension(270, 55));
		random3FieldInnerPanel.add(randomAttribute3Field);
		random3InnerPanel.setOpaque(false);
		random3FieldInnerPanel.setOpaque(false);
		random3Panel.add(random3InnerPanel);
		random3Panel.add(random3FieldInnerPanel);

		JPanel random4Panel = new JPanel();
		random4Panel.setMaximumSize(new Dimension(400, 55));
		random4Panel.setPreferredSize(new Dimension(400, 55));
		JPanel random4InnerPanel = new JPanel();
		JPanel random4FieldInnerPanel = new JPanel();
		random4InnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		random4InnerPanel.add(randomAttribute4);
		random4InnerPanel.setPreferredSize(new Dimension(120, 55));
		random4FieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		random4FieldInnerPanel.setPreferredSize(new Dimension(270, 55));
		random4FieldInnerPanel.add(randomAttribute4Field);
		random4InnerPanel.setOpaque(false);
		random4FieldInnerPanel.setOpaque(false);
		random4Panel.add(random4InnerPanel);
		random4Panel.add(random4FieldInnerPanel);

		JPanel agePanel = new JPanel();
		agePanel.setMaximumSize(new Dimension(400, 55));
		agePanel.setPreferredSize(new Dimension(400, 55));
		JPanel ageInnerPanel = new JPanel();
		JPanel ageFieldInnerPanel = new JPanel();
		ageInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ageInnerPanel.add(age);
		ageInnerPanel.setPreferredSize(new Dimension(120, 55));
		ageFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		ageFieldInnerPanel.setPreferredSize(new Dimension(270, 55));
		ageFieldInnerPanel.add(ageField);
		ageInnerPanel.setOpaque(false);
		ageFieldInnerPanel.setOpaque(false);
		agePanel.add(ageInnerPanel);
		agePanel.add(ageFieldInnerPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(400, 55));
		buttonPanel.setPreferredSize(new Dimension(400, 55));
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(back);
		buttonPanel.add(create);
		back.setPreferredSize(new Dimension(150, 50));
		create.setPreferredSize(new Dimension(150, 50));

		// all main panel backgrounds are opacity is set to null, all inner panels
		// backgrounds are set to null in each individual main panel
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
		back.addActionListener(this);
		create.addActionListener(this);
		mainBackground.add(emptyPanel);
		mainBackground.add(namePanel);
		mainBackground.add(surnamePanel);
		mainBackground.add(random1Panel);
		mainBackground.add(random2Panel);
		mainBackground.add(random3Panel);
		mainBackground.add(random4Panel);
		mainBackground.add(agePanel);
		mainBackground.add(buttonPanel);
		mainBackground.add(error);
		error.setVisible(false);

		allIn.add(mainBackground);
		this.setContentPane(allIn);
	}

	public void actionPerformed(ActionEvent event) {
		ListIterator<User> iterator = registeredUsersSaved.listIterator();
		if (event.getSource() == create) {
			String empty = "";

			if (nameField.getText().equals(empty) || surnameField.getText().equals(empty)) {
				error.setForeground(Color.RED);
				error.setText("Name and Surname required to add user");
				error.setVisible(true);
			} else {
				userToRegister = new User(nameField.getText(), surnameField.getText());
				userToRegister.setRandomAttribute1(randomAttribute1Field.getText());
				userToRegister.setRandomAttribute2(randomAttribute2Field.getText());
				userToRegister.setRandomAttribute3(randomAttribute3Field.getText());
				userToRegister.setRandomAttribute4(randomAttribute4Field.getText());
			}
			try {
				if (ageField.getText().equals(empty)) {
					userToRegister.setAge(0);
				} else {
					userToRegister.setAge(Integer.parseInt(ageField.getText()));
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Age must be numeric values", "Error", JOptionPane.WARNING_MESSAGE);
				new AddUser();
				this.setVisible(false);
			}
			// following lines show what must be done if required fields are not null and if
			// file already exists.
			File registeredUsersFile = new File(currentUser.getId() + "registeredUsers.txt");
			if (registeredUsersFile.exists()) {
				try {
					in = new ObjectInputStream(new FileInputStream(currentUser.getId() + "registeredUsers.txt"));
					registeredUsersSaved = (LinkedList) in.readObject();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Can not locate file", "Error", JOptionPane.WARNING_MESSAGE);
				}

				while (iterator.hasNext()) {
					if (iterator.next().getName().equals(userToRegister.getName())) {
						iterator.previous();
						if (iterator.next().getSurname().equals(userToRegister.getSurname())) {
							JOptionPane.showMessageDialog(null, "User Already Exists", "Error",
									JOptionPane.WARNING_MESSAGE);
							this.setVisible(false);
							new AddUser();
							exists = true;

						}
					}
				}
				// if user doesn't exists do the following:
				if (exists == false) {
					registeredUsersSaved.add(userToRegister);
					error.setForeground(Color.GREEN);
					error.setText("User Added Successfully");
					error.setVisible(true);
					try {
						out = new ObjectOutputStream(new FileOutputStream(currentUser.getId() + "registeredUsers.txt"));
						out.writeObject(registeredUsersSaved);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// if file doesnt exists creates new users file
			if (!registeredUsersFile.exists()) {
				registeredUsers.add(userToRegister);
				error.setForeground(Color.GREEN);
				error.setText("User Added Successfully");
				error.setVisible(true);
				this.setVisible(false);
				new MainMenu();
				try {
					out = new ObjectOutputStream(new FileOutputStream(currentUser.getId() + "registeredUsers.txt"));
					out.writeObject(registeredUsers);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Something went wrong, sorry!", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		if (event.getSource() == back) {
			new MainMenu();
			this.setVisible(false);
		}

	}

}
