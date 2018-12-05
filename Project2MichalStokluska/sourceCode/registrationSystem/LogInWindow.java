package registrationSystem;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.ListIterator;

public class LogInWindow extends JFrame implements ActionListener {

	private JLabel idLabel, passwordLabel, incorrectIdOrPassword;
	private JTextField idTextField;
	private JTextField passwordField;
	private JButton createButton, logInButton;
	private JPanel idPanel, passwordPanel, logInCreatePanel;
	private LinkedList<SoftwareUser> registeredUsers;
	private SoftwareUser possibleUser;

	public LogInWindow() {
		super("Log In");
		this.setSize(450, 170);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Container content = this.getContentPane();
		FlowLayout layout = new FlowLayout();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		idLabel = new JLabel("ID");
		passwordLabel = new JLabel("PASSWORD");
		incorrectIdOrPassword = new JLabel("");
		passwordField = new JTextField("", 30);
		idTextField = new JTextField("", 30);
		createButton = new JButton("Create User");
		logInButton = new JButton("Log In");
		idPanel = new JPanel();
		passwordPanel = new JPanel();
		logInCreatePanel = new JPanel();
		registeredUsers = new LinkedList<SoftwareUser>();

		idPanel.setLayout(new BorderLayout());
		idLabel.setBorder(new EmptyBorder(0, 0, 0, 65));
		idPanel.add("West", idLabel);
		idPanel.add("East", idTextField);

		passwordPanel.setLayout(new BorderLayout());
		passwordLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
		passwordPanel.add("West", passwordLabel);
		passwordPanel.add("East", passwordField);

		logInCreatePanel.setLayout(new BorderLayout());
		logInCreatePanel.add("West", logInButton);
		logInCreatePanel.add("East", createButton);

		idTextField.setBorder(new BevelBorder(BevelBorder.LOWERED));
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED));
		logInCreatePanel.setBorder(new BevelBorder(BevelBorder.RAISED));

		content.setLayout(layout);
		content.add(idPanel);
		content.add(passwordPanel);
		content.add(logInCreatePanel);
		content.add(incorrectIdOrPassword);
		createButton.addActionListener(this);
		logInButton.addActionListener(this);
		this.setContentPane(content);
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == logInButton) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new FileInputStream("registeredUsers.txt"));
				registeredUsers = (LinkedList) in.readObject();
				possibleUser = new SoftwareUser(idTextField.getText(), passwordField.getText());
				ListIterator<SoftwareUser> iterator = registeredUsers.listIterator();
				while (iterator.hasNext()) {
					if (iterator.next().getId().equals(possibleUser.getId())) {
						iterator.previous();
						if (iterator.next().getPassword().equals(possibleUser.getPassword())) {
							iterator.previous();
							CurrentSoftwareUser newCurrent = new CurrentSoftwareUser();
							newCurrent.setOrganisationName(iterator.next().getOrganisationName());
							newCurrent.setId(idTextField.getText());
							iterator.previous();
							newCurrent.setTypeOfOrganisation(iterator.next().getOrgType());
							iterator.previous();
							newCurrent.setRandomAttribute1(iterator.next().getRandomAttribute1());
							iterator.previous();
							newCurrent.setRandomAttribute2(iterator.next().getRandomAttribute2());
							iterator.previous();
							newCurrent.setRandomAttribute3(iterator.next().getRandomAttribute3());
							iterator.previous();
							newCurrent.setRandomAttribute4(iterator.next().getRandomAttribute4());
							new MainMenu();
							this.setVisible(false);
						}
					} else {
						incorrectIdOrPassword.setForeground(Color.RED);
						incorrectIdOrPassword.setText("Id or Password you have entered are incorrect");
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No Software Registered Users can be found", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (event.getSource() == createButton) {
			new CreateUser();
			this.setVisible(false);
		}
	}
}