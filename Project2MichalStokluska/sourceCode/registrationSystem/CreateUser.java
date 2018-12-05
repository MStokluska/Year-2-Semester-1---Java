package registrationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.awt.Color;

//adding registered user to the programm itself. 
public class CreateUser extends JFrame implements ActionListener {
	private JLabel userName, userPassword, errorMessage, organizationType, organizationName, infoLabel;
	private JTextField userNameTextField, userPasswordTextField, organizationNameField, randomAttributeOne,
			randomAttributeTwo, randomAttributeThree, randomAttributeFour;
	private JButton createUserButton, backButton;
	private SoftwareUser newUser;
	private LinkedList<SoftwareUser> registeredUsers, registeredUsersSaved;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	private ButtonGroup userType;
	private JRadioButton type1, type2, type3;

	public CreateUser() {

		super("Create User");
		this.setSize(1500, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Container content = this.getContentPane();
		FlowLayout layout = new FlowLayout();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		userName = new JLabel("Your ID: ");
		userPassword = new JLabel("Your Password: ");
		errorMessage = new JLabel("You must fill in all the fields");
		errorMessage.setForeground(Color.RED);
		userNameTextField = new JTextField("", 15);
		userPasswordTextField = new JTextField("", 15);
		createUserButton = new JButton("Create User");
		backButton = new JButton("Back");
		registeredUsers = new LinkedList<SoftwareUser>();
		registeredUsersSaved = new LinkedList<SoftwareUser>();
		userType = new ButtonGroup();
		type1 = new JRadioButton("Sport Club");
		type2 = new JRadioButton("Fan Club");
		type3 = new JRadioButton("Subscribers Data Base");
		type1.setSelected(true);
		userType.add(type1);
		userType.add(type2);
		userType.add(type3);
		organizationType = new JLabel("Your Organisation Type: ");
		organizationName = new JLabel("Your Organisation Name: ");
		organizationNameField = new JTextField("", 15);
		infoLabel = new JLabel(
				"All users that you will register will have Name, Surname and Age attribute,\n please choose additional two attributes of your liking");
		randomAttributeOne = new JTextField("", 15);
		randomAttributeTwo = new JTextField("", 15);
		randomAttributeThree = new JTextField("", 15);
		randomAttributeFour = new JTextField("", 15);
		content.setLayout(layout);
		content.add(userName);
		content.add(userNameTextField);
		content.add(userPassword);
		content.add(userPasswordTextField);
		content.add(organizationName);
		content.add(organizationNameField);
		content.add(infoLabel);
		content.add(randomAttributeOne);
		content.add(randomAttributeTwo);
		content.add(randomAttributeThree);
		content.add(randomAttributeFour);
		content.add(organizationType);
		content.add(type1);
		content.add(type2);
		content.add(type3);

		content.add(createUserButton);
		content.add(backButton);
		content.add(errorMessage);
		type1.addActionListener(this);
		type2.addActionListener(this);
		type3.addActionListener(this);
		errorMessage.setVisible(false);
		createUserButton.addActionListener(this);
		backButton.addActionListener(this);
		this.setContentPane(content);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == createUserButton) {
			if (userNameTextField.getText().equals("") || userPasswordTextField.getText().equals("")
					|| (organizationNameField.getText().equals("")) || (randomAttributeOne.getText().equals(""))
					|| (randomAttributeTwo.getText().equals("")) || (randomAttributeThree.getText().equals(""))
					|| (randomAttributeFour.getText().equals(""))) {
				errorMessage.setVisible(true);
			} else {
				newUser = new SoftwareUser(userNameTextField.getText(), userPasswordTextField.getText());
				if (type1.isSelected()) {
					newUser.setOrganisationType(1);
				}
				if (type2.isSelected()) {
					newUser.setOrganisationType(2);
				}
				if (type3.isSelected()) {
					newUser.setOrganisationType(3);
				}
				newUser.setOrganisationName(organizationNameField.getText());
				newUser.setRandomAttribute1(randomAttributeOne.getText());
				newUser.setRandomAttribute2(randomAttributeTwo.getText());
				newUser.setRandomAttribute3(randomAttributeThree.getText());
				newUser.setRandomAttribute4(randomAttributeFour.getText());
				// try catch block added to every input output to/from the list of users saved.
				File registeredUsersFile = new File("registeredUsers.txt");
				if (registeredUsersFile.exists()) {
					try {
						in = new ObjectInputStream(new FileInputStream("registeredUsers.txt"));
						registeredUsersSaved = (LinkedList) in.readObject();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Can not locate file", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
					registeredUsersSaved.add(newUser);
					JOptionPane.showMessageDialog(null, "User Added Successfuly", "User Added",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						out = new ObjectOutputStream(new FileOutputStream("registeredUsers.txt"));
						out.writeObject(registeredUsersSaved);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (!registeredUsersFile.exists()) {
					registeredUsers.add(newUser);
					JOptionPane.showMessageDialog(null, "User Added Successfuly", "User Added",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						out = new ObjectOutputStream(new FileOutputStream("registeredUsers.txt"));
						out.writeObject(registeredUsers);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Something went wrong, sorry!", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}

		if (event.getSource() == backButton) {
			this.setVisible(false);
			new LogInWindow();
		}
	}
}
