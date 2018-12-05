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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.*;

public class AgeStatistics extends JFrame implements ActionListener {
	private JLabel youngestMember, youngestMemberResult, oldestMember, oldestMemberResult, averageAge, averageAgeResult,
			numberOfSenior, numberOfSeniorResult, numberOfJunior, numberOfJuniorResult;
	private JButton generate, back;
	private User userToRegister;
	private LinkedList<User> registeredUsers, registeredUsersSaved;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;

	private static CurrentSoftwareUser currentUser = new CurrentSoftwareUser();

	public AgeStatistics() {
		super("Age Statistics");
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

		youngestMember = new JLabel("Age of youngest member: ");
		youngestMemberResult = new JLabel("");
		oldestMember = new JLabel("Age of oldest member: ");
		oldestMemberResult = new JLabel("");
		averageAge = new JLabel("Average of age: ");
		averageAgeResult = new JLabel("");
		numberOfSenior = new JLabel("Number of senior (18+) members: ");
		numberOfSeniorResult = new JLabel("");
		numberOfJunior = new JLabel("Number of junior (up to 17) members: ");
		numberOfJuniorResult = new JLabel("");

		// color set for black background
		if (currentUser.getOrganisationType() == 2) {
			youngestMember.setForeground(Color.WHITE);
			youngestMemberResult.setForeground(Color.WHITE);
			oldestMember.setForeground(Color.WHITE);
			oldestMemberResult.setForeground(Color.WHITE);
			averageAge.setForeground(Color.WHITE);
			averageAgeResult.setForeground(Color.WHITE);
			numberOfSenior.setForeground(Color.WHITE);
			numberOfSeniorResult.setForeground(Color.WHITE);
			numberOfJunior.setForeground(Color.WHITE);
			numberOfJuniorResult.setForeground(Color.WHITE);
		}
		registeredUsers = new LinkedList<User>();
		registeredUsersSaved = new LinkedList<User>();
		back = new JButton("Back");
		generate = new JButton("Generate");

		JPanel mainBackground = new JPanel();
		mainBackground.setBackground(new Color(255, 255, 255));
		mainBackground.setMaximumSize(new Dimension(400, 600));

		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(400, 50));
		emptyPanel.setBackground(new Color(255, 255, 255));

		JPanel youngestPanel = new JPanel();
		youngestPanel.setMaximumSize(new Dimension(400, 55));
		youngestPanel.setPreferredSize(new Dimension(400, 55));
		JPanel youngestInnerPanel = new JPanel();
		JPanel youngestFieldInnerPanel = new JPanel();
		youngestInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		youngestInnerPanel.add(youngestMember);
		youngestInnerPanel.setPreferredSize(new Dimension(270, 55));
		youngestFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		youngestFieldInnerPanel.setPreferredSize(new Dimension(120, 55));
		youngestFieldInnerPanel.add(youngestMemberResult);
		youngestInnerPanel.setOpaque(false);
		youngestFieldInnerPanel.setOpaque(false);
		youngestPanel.add(youngestInnerPanel);
		youngestPanel.add(youngestFieldInnerPanel);

		JPanel oldestPanel = new JPanel();
		oldestPanel.setMaximumSize(new Dimension(400, 55));
		oldestPanel.setPreferredSize(new Dimension(400, 55));
		JPanel oldestInnerPanel = new JPanel();
		JPanel oldestFieldInnerPanel = new JPanel();
		oldestInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		oldestInnerPanel.add(oldestMember);
		oldestInnerPanel.setPreferredSize(new Dimension(270, 55));
		oldestFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		oldestFieldInnerPanel.setPreferredSize(new Dimension(120, 55));
		oldestFieldInnerPanel.add(oldestMemberResult);
		oldestInnerPanel.setOpaque(false);
		oldestFieldInnerPanel.setOpaque(false);
		oldestPanel.add(oldestInnerPanel);
		oldestPanel.add(oldestFieldInnerPanel);

		JPanel averagePanel = new JPanel();
		averagePanel.setMaximumSize(new Dimension(400, 55));
		averagePanel.setPreferredSize(new Dimension(400, 55));
		JPanel averageInnerPanel = new JPanel();
		JPanel averageFieldInnerPanel = new JPanel();
		averageInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		averageInnerPanel.add(averageAge);
		averageInnerPanel.setPreferredSize(new Dimension(270, 55));
		averageFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		averageFieldInnerPanel.setPreferredSize(new Dimension(120, 55));
		averageFieldInnerPanel.add(averageAgeResult);
		averageInnerPanel.setOpaque(false);
		averageFieldInnerPanel.setOpaque(false);
		averagePanel.add(averageInnerPanel);
		averagePanel.add(averageFieldInnerPanel);

		JPanel numberOfSeniorPanel = new JPanel();
		numberOfSeniorPanel.setMaximumSize(new Dimension(400, 55));
		numberOfSeniorPanel.setPreferredSize(new Dimension(400, 55));
		JPanel numberOfSeniorInnerPanel = new JPanel();
		JPanel numberOfSeniorFieldInnerPanel = new JPanel();
		numberOfSeniorInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		numberOfSeniorInnerPanel.add(numberOfSenior);
		numberOfSeniorInnerPanel.setPreferredSize(new Dimension(270, 55));
		numberOfSeniorFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		numberOfSeniorFieldInnerPanel.setPreferredSize(new Dimension(120, 55));
		numberOfSeniorFieldInnerPanel.add(numberOfSeniorResult);
		numberOfSeniorInnerPanel.setOpaque(false);
		numberOfSeniorFieldInnerPanel.setOpaque(false);
		numberOfSeniorPanel.add(numberOfSeniorInnerPanel);
		numberOfSeniorPanel.add(numberOfSeniorFieldInnerPanel);

		JPanel numberOfJuniorPanel = new JPanel();
		numberOfJuniorPanel.setMaximumSize(new Dimension(400, 55));
		numberOfJuniorPanel.setPreferredSize(new Dimension(400, 55));
		JPanel numberOfJuniorInnerPanel = new JPanel();
		JPanel numberOfJuniorFieldInnerPanel = new JPanel();
		numberOfJuniorInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		numberOfJuniorInnerPanel.add(numberOfJunior);
		numberOfJuniorInnerPanel.setPreferredSize(new Dimension(270, 55));
		numberOfJuniorFieldInnerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		numberOfJuniorFieldInnerPanel.setPreferredSize(new Dimension(120, 55));
		numberOfJuniorFieldInnerPanel.add(numberOfJuniorResult);
		numberOfJuniorInnerPanel.setOpaque(false);
		numberOfJuniorFieldInnerPanel.setOpaque(false);
		numberOfJuniorPanel.add(numberOfJuniorInnerPanel);
		numberOfJuniorPanel.add(numberOfJuniorFieldInnerPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.setMaximumSize(new Dimension(400, 55));
		buttonPanel.setPreferredSize(new Dimension(400, 55));
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(back);
		buttonPanel.add(generate);

		back.setPreferredSize(new Dimension(150, 50));
		generate.setPreferredSize(new Dimension(150, 50));

		mainBackground.setOpaque(false);
		emptyPanel.setOpaque(false);
		youngestPanel.setOpaque(false);
		oldestPanel.setOpaque(false);
		averagePanel.setOpaque(false);
		numberOfSeniorPanel.setOpaque(false);
		numberOfJuniorPanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		back.addActionListener(this);
		generate.addActionListener(this);
		mainBackground.add(emptyPanel);
		mainBackground.add(youngestPanel);
		mainBackground.add(oldestPanel);
		mainBackground.add(averagePanel);
		mainBackground.add(numberOfSeniorPanel);
		mainBackground.add(numberOfJuniorPanel);
		mainBackground.add(buttonPanel);

		allIn.add(mainBackground);
		this.setContentPane(allIn);
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == generate) {
			int youngestInt = 0;
			// not really a necessary check to see if file exists as if it doesnt, no checks
			// are done.
			File registeredUsersFile = new File(currentUser.getId() + "registeredUsers.txt");
			if (registeredUsersFile.exists()) {
				// checking for exceptions. this could be very useful if paths to file were on
				// non existing HDD, however im using relative path to locate files.
				try {
					in = new ObjectInputStream(new FileInputStream(currentUser.getId() + "registeredUsers.txt"));
					registeredUsersSaved = (LinkedList) in.readObject();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Can not locate file", "Error", JOptionPane.WARNING_MESSAGE);
				}
				ListIterator<User> iterator = registeredUsersSaved.listIterator();
				youngestInt = iterator.next().getAge();
				iterator.previous();
				while (iterator.hasNext()) {

					// System.out.println(youngestInt);
					if (iterator.next().getAge() < youngestInt) {
						iterator.previous();
						youngestInt = iterator.next().getAge();
						youngestMemberResult.setText(Integer.toString(youngestInt));
					} else {
						youngestMemberResult.setText(Integer.toString(youngestInt));
					}
				}
				iterator = registeredUsersSaved.listIterator();
				int oldestInt = iterator.next().getAge();
				iterator.previous();
				while (iterator.hasNext()) {
					if (iterator.next().getAge() > youngestInt) {
						iterator.previous();
						oldestInt = iterator.next().getAge();
						oldestMemberResult.setText(Integer.toString(oldestInt));
					} else {
						oldestMemberResult.setText(Integer.toString(oldestInt));
					}
				}
				iterator = registeredUsersSaved.listIterator();
				int averageInt = 0;
				int totalAmountOfUsers = 0;
				while (iterator.hasNext()) {
					totalAmountOfUsers = totalAmountOfUsers + 1;
					averageInt = averageInt + iterator.next().getAge();
				}
				averageAgeResult.setText(Integer.toString(averageInt / totalAmountOfUsers));
				iterator = registeredUsersSaved.listIterator();
				int noOfSeniorMembers = 0;
				while (iterator.hasNext()) {
					if (iterator.next().getAge() >= 18) {
						noOfSeniorMembers++;
					}
				}
				numberOfSeniorResult.setText(Integer.toString(noOfSeniorMembers));
				iterator = registeredUsersSaved.listIterator();
				int noOfJuniorMembers = 0;
				while (iterator.hasNext()) {
					if (iterator.next().getAge() < 18) {
						noOfJuniorMembers++;
					}
				}
				numberOfJuniorResult.setText(Integer.toString(noOfJuniorMembers));

			}
		}
		if (event.getSource() == back) {
			new MainMenu();
			this.setVisible(false);
		}

	}

}
