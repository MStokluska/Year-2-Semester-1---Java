package registrationSystem;

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
import javax.swing.*;

public class ViewAllUsers extends JFrame implements ActionListener {
	private JTextArea list;
	private JButton generate, back;
	private LinkedList<User> registeredUsersSaved;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	private static CurrentSoftwareUser currentUser = new CurrentSoftwareUser();

	public ViewAllUsers() {

		super("View All Registered Members");
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
		list = new JTextArea(27, 35);
		JScrollPane sampleScrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		generate = new JButton("View All");
		back = new JButton("Back");
		JPanel mainBackground = new JPanel();
		mainBackground.setMaximumSize(new Dimension(400, 600));
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(400, 500));
		emptyPanel.add(sampleScrollPane);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(400, 55));
		buttonPanel.setPreferredSize(new Dimension(400, 80));
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(back);
		buttonPanel.add(generate);
		back.setPreferredSize(new Dimension(150, 50));
		generate.setPreferredSize(new Dimension(150, 50));
		mainBackground.setOpaque(false);
		buttonPanel.setOpaque(false);
		emptyPanel.setOpaque(false);
		back.addActionListener(this);
		generate.addActionListener(this);
		mainBackground.add(emptyPanel);
		mainBackground.add(buttonPanel);
		allIn.add(mainBackground);
		this.setContentPane(allIn);
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == generate) {
			File registeredUsersFile = new File(currentUser.getId() + "registeredUsers.txt");
			if (registeredUsersFile.exists()) {

				try {
					in = new ObjectInputStream(new FileInputStream(currentUser.getId() + "registeredUsers.txt"));
					registeredUsersSaved = (LinkedList) in.readObject();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Can not locate file", "Error", JOptionPane.WARNING_MESSAGE);
				}
				list.setText(registeredUsersSaved.toString());
			}
		}
		if (event.getSource() == back) {
			new MainMenu();
			this.setVisible(false);
		}
	}
}
