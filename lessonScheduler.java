import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class lessonScheduler extends JFrame implements ActionListener {

	private JPanel northPanel, eastPanel, eastPanel2, eastPanel3, westPanel, westPanel2, southPanel;
	private JLabel banner, baseballImage, helmetImage, camperFirstNameLabel, camperLastNameLabel, parentFirstNameLabel, parentLastNameLabel, space1, space2, space3, space4, 
					emailAddressLabel, phoneNumberLabel, homeAddressLabel, cityStateLabel, zipCodeLabel, ageLabel, positionLabel, eastPanelSpace1, 
					westPanelTitle, westPanelSpace, afterCareLabel, westPanelSpace2, referralLabel, 
					commentConcernLabel, baseballBatImage, gloveImage;
	private JTextField camperFirstNameText, camperLastNameText, parentFirstNameText, parentLastNameText, 
						emailAddressText, phoneNumberText, homeAddressText, cityStateText, zipCodeText, ageText, positionText;
	private JButton submitButton, cancelButton, exportButton;
	private JCheckBox weekOneBox, weekTwoBox, weekThreeBox, weekFourBox, weekFiveBox, weekSixBox;
	private JRadioButton afterCareYes, afterCareNo;
	private ButtonGroup afterCare;
	private JTextArea commentsConcernsArea;
	private JComboBox referralBox;
	private ImageIcon baseball, baseballBat, glove, helmet;
	private Socket socket;
	private ObjectOutputStream outputStream;
	
	
	public lessonScheduler() {
		super("Kramp Baseball Scheduler");
		
		banner = new JLabel("Kramp Baseball Camp Scheduler");
		baseball = new ImageIcon("C:\\Users\\joecu\\eclipse-workspace\\Kramp Baseball Lessons\\src\\Baseball Picture3.png");
		baseballImage = new JLabel(baseball);
		helmet = new ImageIcon("C:\\Users\\joecu\\eclipse-workspace\\Kramp Baseball Lessons\\src\\BaseballHelmet.png");
		helmetImage = new JLabel(helmet);
		northPanel = new JPanel();
		northPanel.add(helmetImage);
		northPanel.add(banner);
		northPanel.add(baseballImage);
		add(northPanel, BorderLayout.NORTH);
		

		camperFirstNameLabel = new JLabel("Camper First Name: ");
		camperFirstNameText = new JTextField(15);
		camperLastNameLabel = new JLabel("Camper Last Name: ");
		camperLastNameText = new JTextField(15);
		ageLabel = new JLabel("Age: ");
		ageText = new JTextField(15);
		positionLabel = new JLabel("Position(s): ");
		positionText = new JTextField(15);
		space1 = new JLabel("");
		space2 = new JLabel("");
		parentFirstNameLabel = new JLabel("Parent First Name: ");
		parentFirstNameText = new JTextField(15);
		parentLastNameLabel = new JLabel("Parent Last Name: ");
		parentLastNameText = new JTextField(15);
		emailAddressLabel = new JLabel("Email Address:  ");
		emailAddressText = new JTextField(15);
		phoneNumberLabel = new JLabel("Phone Number: ");
		phoneNumberText = new JTextField(15);
		homeAddressLabel = new JLabel("Home Address: ");
		homeAddressText = new JTextField(15);
		cityStateLabel = new JLabel("City/State: ");
		cityStateText = new JTextField(15);
		zipCodeLabel = new JLabel("Zip Code: ");
		zipCodeText = new JTextField(15);
		eastPanelSpace1 = new JLabel(" ");
		commentConcernLabel = new JLabel("Enter any comments or concerns below: ");
		commentsConcernsArea = new JTextArea(5,4);
		space3 = new JLabel(" ");
		space4 = new JLabel(" ");
		eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(13,2));
		eastPanel.add(camperFirstNameLabel);
		eastPanel.add(camperFirstNameText);
		eastPanel.add(camperLastNameLabel);
		eastPanel.add(camperLastNameText);
		eastPanel.add(ageLabel);
		eastPanel.add(ageText);
		eastPanel.add(positionLabel);
		eastPanel.add(positionText);
		eastPanel.add(space1);
		eastPanel.add(space2);
		eastPanel.add(parentFirstNameLabel);
		eastPanel.add(parentFirstNameText);
		eastPanel.add(parentLastNameLabel);
		eastPanel.add(parentLastNameText);
		eastPanel.add(emailAddressLabel);
		eastPanel.add(emailAddressText);
		eastPanel.add(phoneNumberLabel);
		eastPanel.add(phoneNumberText);
		eastPanel.add(homeAddressLabel);
		eastPanel.add(homeAddressText);
		eastPanel.add(cityStateLabel);
		eastPanel.add(cityStateText);
		eastPanel.add(zipCodeLabel);
		eastPanel.add(zipCodeText);
		eastPanel.add(space3);
		eastPanel.add(space4);
		eastPanel2 = new JPanel();
		eastPanel2.setLayout(new BorderLayout());
		eastPanel2.add(eastPanel, BorderLayout.NORTH);
		eastPanel2.add(commentConcernLabel, BorderLayout.CENTER);
		eastPanel2.add(commentsConcernsArea, BorderLayout.SOUTH);
		eastPanel3 = new JPanel();
		eastPanel3.add(eastPanel2);
		add(eastPanel3, BorderLayout.EAST);
		
		
		westPanelTitle = new JLabel("Kramp Baseball Summer 2024 Camps");
		weekOneBox = new JCheckBox("Week 1");
		weekTwoBox = new JCheckBox("Week 2");
		weekThreeBox = new JCheckBox("Week 3");
		weekFourBox = new JCheckBox("Week 4");
		weekFiveBox = new JCheckBox("Week 5");
		weekSixBox = new JCheckBox("Week 6");
		westPanelSpace = new JLabel(" ");
		afterCareLabel = new JLabel("Would you like After Care? (Extra Charge)");
		afterCareYes = new JRadioButton("Yes");
		afterCareNo = new JRadioButton("No");
		afterCare = new ButtonGroup();
		afterCare.add(afterCareYes);
		afterCare.add(afterCareNo);
		westPanelSpace2 = new JLabel(" ");
		referralLabel = new JLabel("How did you hear about us? (Optional): ");
		String referralList [] = {"Select one of the following", "Through a friend", "Advertisment", "Through a coach", "KBL player"};
		referralBox = new JComboBox(referralList);
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(14,1));
		westPanel.add(westPanelTitle);
		westPanel.add(weekOneBox);
		westPanel.add(weekTwoBox);
		westPanel.add(weekThreeBox);
		westPanel.add(weekFourBox);
		westPanel.add(weekFiveBox);
		westPanel.add(weekSixBox);
		westPanel.add(westPanelSpace);
		westPanel.add(afterCareLabel);
		westPanel.add(afterCareYes);
		westPanel.add(afterCareNo);
		westPanel.add(westPanelSpace2);
		westPanel.add(referralLabel);
		westPanel.add(referralBox);
		westPanel2 = new JPanel();
		westPanel2.add(westPanel);
		add(westPanel2, BorderLayout.WEST);
		
		
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		exportButton = new JButton("Export Output");
		exportButton.addActionListener(this);
		baseballBat = new ImageIcon("C:\\Users\\joecu\\eclipse-workspace\\Kramp Baseball Lessons\\src\\BaseballBat1.png");
		baseballBatImage = new JLabel(baseballBat);
		glove = new ImageIcon("C:\\Users\\joecu\\eclipse-workspace\\Kramp Baseball Lessons\\src\\BaseballGlove1.png");
		gloveImage = new JLabel(glove);
		southPanel = new JPanel();
		southPanel.add(baseballBatImage);
		southPanel.add(submitButton);
		southPanel.add(cancelButton);
		southPanel.add(exportButton);
		southPanel.add(gloveImage);
		add(southPanel, BorderLayout.SOUTH);
		
		
		setVisible(true);
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == submitButton) {
			String camperFirstName = camperFirstNameText.getText();
			String camperLastName = camperLastNameText.getText();
			String age = ageText.getText();
			String positions = positionText.getText();
			
			String parentFirstName = parentFirstNameText.getText();
			String parentLastName = parentLastNameText.getText();
			String emailAddress = emailAddressText.getText();
			String phoneNumber = phoneNumberText.getText();
			String homeAddress = homeAddressText.getText();
			String cityState = cityStateText.getText();
			String zipCode = zipCodeText.getText();
			String referral = referralBox.getSelectedItem().toString();
			String commentsAndConcerns = commentsConcernsArea.getText();
			
			boolean week1 = weekOneBox.isSelected();
			String week1Output = "No";
			if (week1 == true) {
				week1Output = "Yes";
			}
			boolean week2 = weekTwoBox.isSelected();
			String week2Output = "No";
			if (week2 == true) {
				week2Output = "Yes";
			}
			boolean week3 = weekThreeBox.isSelected();
			String week3Output = "No";
			if (week3 == true) {
				week3Output = "Yes";
			}
			boolean week4 = weekFourBox.isSelected();
			String week4Output = "No";
			if (week4 == true) {
				week4Output = "Yes";
			}
			boolean week5 = weekFiveBox.isSelected();
			String week5Output = "No";
			if (week5 == true) {
				week5Output = "Yes";
			}
			boolean week6 = weekSixBox.isSelected();
			String week6Output = "No";
			if (week6 == true) {
				week6Output = "Yes";
			}
			
			String afterCare = "Did not answer.  Will not be charged.";
			if (afterCareYes.isSelected()) {
				afterCare = "Yes";
			}
			
			else if (afterCareNo.isSelected()) {
				afterCare = "No";
			}
			
			
			
			String output = 

					"Receipt: \n"
					+ "Camper Name: " + camperFirstName + " " + camperLastName +"\n"
					+ "Age: " + age + "\n" 
					+ "Positions: " + positions + "\n"
					+ "\n"
					+ "Parent/Guardian Name: " + parentFirstName + " " + parentLastName + "\n"
					+ "Email Address: " + emailAddress + "\n"
					+ "Phone Number: " + phoneNumber + "\n"
					+ "Home Address: " + homeAddress + " " + "\n" 
					+ "City/State, Zip Code: " + cityState + ", " + zipCode + "\n"
					+ "\n"
					+ "Weeks Selected: " + "\n"
					+ "Week 1: " + week1Output + "\n"
					+ "Week 2: " + week2Output + "\n"
					+ "Week 3: " + week3Output + "\n"
					+ "Week 4: " + week4Output + "\n"
					+ "Week 5: " + week5Output + "\n"
					+ "Week 6: " + week6Output + "\n"
					+ "\n"
					+ "After Care: " + afterCare + "\n"
					+ "\n"
					+ "Referral: " + referral + "\n"
					+ "\n"
					+ "Comments and Concerns: " + "\n"
					+ commentsAndConcerns;
			JOptionPane.showMessageDialog(null, output);
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBC is connected successfully");
				String URL = "jdbc:mysql://localhost/lesson_scheduler?user=root&password=";
				Connection conn = DriverManager.getConnection(URL);
				System.out.println("Login Successful.");
				String insert = "insert into profile values('"+camperFirstName+"','"+camperLastName+"', '"+age+"', '"+positions+"',"
						+ "'"+parentFirstName+"', '"+parentLastName+"', '"+emailAddress+"', '"+phoneNumber+"', '"+homeAddress+"', '"+cityState+"',"
						+ " '"+zipCode+"', '"+commentsAndConcerns+"', '"+week1Output+"', '"+week2Output+"', '"+week3Output+"',"
						+ " '"+week4Output+"', '"+week5Output+"', '"+week6Output+"', '"+afterCare+"', '"+referral+"');";
				String commitStatement = "commit;";
				Statement stmt = conn.createStatement();
				stmt.execute(insert);
				Statement commit = conn.createStatement();
				commit.execute(commitStatement);
				System.out.println("Command ran successfully.");
			}
		
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if (event.getSource() == cancelButton) {
			
			camperFirstNameText.setText("");
			camperLastNameText.setText("");
			ageText.setText("");
			positionText.setText("");
			
			parentFirstNameText.setText("");
			parentLastNameText.setText("");
			emailAddressText.setText("");
			phoneNumberText.setText("");
			homeAddressText.setText("");
			cityStateText.setText("");
			zipCodeText.setText("");
			commentsConcernsArea.setText("");
			
			afterCare.clearSelection();
			
			weekOneBox.setSelected(false);
			weekTwoBox.setSelected(false);
			weekThreeBox.setSelected(false);
			weekFourBox.setSelected(false);
			weekFiveBox.setSelected(false);
			weekSixBox.setSelected(false);
			
			referralBox.setSelectedIndex(0);

		}
		
		else if (event.getSource() == exportButton) {
			
			String camperFirstName = camperFirstNameText.getText();
			String camperLastName = camperLastNameText.getText();
			String age = ageText.getText();
			String positions = positionText.getText();
			
			String parentFirstName = parentFirstNameText.getText();
			String parentLastName = parentLastNameText.getText();
			String emailAddress = emailAddressText.getText();
			String phoneNumber = phoneNumberText.getText();
			String homeAddress = homeAddressText.getText();
			String cityState = cityStateText.getText();
			String zipCode = zipCodeText.getText();
			String referral = referralBox.getSelectedItem().toString();
			String commentsAndConcerns = commentsConcernsArea.getText();
			
			boolean week1 = weekOneBox.isSelected();
			String week1Output = "No";
			if (week1 == true) {
				week1Output = "Yes";
			}
			boolean week2 = weekTwoBox.isSelected();
			String week2Output = "No";
			if (week2 == true) {
				week2Output = "Yes";
			}
			boolean week3 = weekThreeBox.isSelected();
			String week3Output = "No";
			if (week3 == true) {
				week3Output = "Yes";
			}
			boolean week4 = weekFourBox.isSelected();
			String week4Output = "No";
			if (week4 == true) {
				week4Output = "Yes";
			}
			boolean week5 = weekFiveBox.isSelected();
			String week5Output = "No";
			if (week5 == true) {
				week5Output = "Yes";
			}
			boolean week6 = weekSixBox.isSelected();
			String week6Output = "No";
			if (week6 == true) {
				week6Output = "Yes";
			}
			
			String afterCare = "Did not answer.  Will not be charged.";
			if (afterCareYes.isSelected()) {
				afterCare = "Yes";
			}
			
			else if (afterCareNo.isSelected()) {
				afterCare = "No";
			}
			
			
			
			String output = 

					"Receipt: \n"
					+ "Camper Name: " + camperFirstName + " " + camperLastName +"\n"
					+ "Age: " + age + "\n" 
					+ "Positions: " + positions + "\n"
					+ "\n"
					+ "Parent/Guardian Name: " + parentFirstName + " " + parentLastName + "\n"
					+ "Email Address: " + emailAddress + "\n"
					+ "Phone Number: " + phoneNumber + "\n"
					+ "Home Address: " + homeAddress + " " + "\n" 
					+ "City/State, Zip Code: " + cityState + " " + zipCode + "\n"
					+ "\n"
					+ "Weeks Selected: " + "\n"
					+ "Week 1: " + week1Output + "\n"
					+ "Week 2: " + week2Output + "\n"
					+ "Week 3: " + week3Output + "\n"
					+ "Week 4: " + week4Output + "\n"
					+ "Week 5: " + week5Output + "\n"
					+ "Week 6: " + week6Output + "\n"
					+ "\n"
					+ "After Care: " + afterCare + "\n"
					+ "\n"
					+ "Referral: " + referral + "\n"
					+ "\n"
					+ "Comments and Concerns: " + "\n"
					+ commentsAndConcerns;
			
			System.out.println("Client is executed.");
			
			try {
				socket = new Socket(InetAddress.getByName("localhost"), 1098);
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeObject(output);
				outputStream.flush();
			}
			
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		lessonScheduler app = new lessonScheduler();

	}

}
