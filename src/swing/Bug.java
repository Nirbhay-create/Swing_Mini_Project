package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Bug extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bug frame = new Bug();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Bug() {

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("BUG ID:");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 45, 59, 14);
		contentPane.add(lblNewLabel);
		
		

		JLabel lblDescription = new JLabel("DESCRIPTION:");
		lblDescription.setForeground(Color.RED);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescription.setBounds(10, 179, 90, 14);
		contentPane.add(lblDescription);

		JLabel lblProduct = new JLabel("PRODUCT:");
		lblProduct.setForeground(Color.RED);
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduct.setBounds(10, 70, 77, 14);
		contentPane.add(lblProduct);

		JLabel lblType = new JLabel("TYPE:");
		lblType.setForeground(Color.RED);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblType.setBounds(10, 95, 46, 14);
		contentPane.add(lblType);

		final JLabel warning = new JLabel("");
		warning.setBounds(52, 278, 150, 14);
		contentPane.add(warning);

		JLabel lblEnviroment = new JLabel("ENVIROMENT:");
		lblEnviroment.setForeground(Color.RED);
		lblEnviroment.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnviroment.setBounds(10, 120, 90, 14);
		contentPane.add(lblEnviroment);

		JLabel lblStatus = new JLabel("STATUS:");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStatus.setBounds(10, 237, 90, 14);
		contentPane.add(lblStatus);

		textField = new JTextField();
		textField.setBounds(116, 43, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ECommerce", "Online Streaming", "Social Media"}));
		comboBox.setEditable(true);
		comboBox.setToolTipText("");
		comboBox.setBounds(116, 67, 86, 22);
		contentPane.add(comboBox);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Securety", "Spelling", "UI/UX", "Functional"}));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(116, 92, 86, 22);
		contentPane.add(comboBox_1);

		final JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"Windows", "Linux", "Mac OS"}));
		comboBox_1_1.setEditable(true);
		comboBox_1_1.setBounds(116, 117, 86, 22);
		contentPane.add(comboBox_1_1);

		final JTextArea txtrNewBug = new JTextArea();
		txtrNewBug.setText("NEW BUG");
		txtrNewBug.setBounds(116, 143, 126, 85);
		contentPane.add(txtrNewBug);

		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "NEW", "RESOLVED", "OPEN" }));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(116, 234, 90, 22);
		contentPane.add(comboBox_2);

		table1 = new JTable();
		table1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table1.setBounds(269, 45, 428, 260);
		contentPane.add(table1);
		
		tableData();
		JButton btnNewButton = new JButton("ADD RECORD\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					warning.setText("No data");
				} else {
					try {
						String querry = "insert into new_table(Bug_id,product,type,Enviroment,description,status)values(?,?,?,?,?,?)";
						Class.forName("com.mysql.cj.jdbc.Driver");//1
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrack", "root",
								"root");//2
						PreparedStatement statement = con.prepareStatement(querry);//3
						statement.setInt(1, Integer.parseInt(textField.getText()));
						statement.setString(2, "" + comboBox.getSelectedItem());
						statement.setString(3, "" + comboBox_1.getSelectedItem());
						statement.setString(4, "" + comboBox_1_1.getSelectedItem());
						statement.setString(5, "" + txtrNewBug.getText());
						statement.setString(6, "" + comboBox_2.getSelectedItem());
						statement.executeUpdate();
						warning.setText("Data inserted sucessfully");
						textField.setText("");
						txtrNewBug.setText("");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					tableData();
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(10, 316, 126, 36);
		contentPane.add(btnNewButton);

		JButton btnUpdateRecord = new JButton("UPDATE RECORD\r\n");
		btnUpdateRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					warning.setText("Please enter the id To update");
				} else {
					try {
						// Database Logic here
						String querry2 = "update new_table set product='" + comboBox.getSelectedItem() + "',type='"
								+ comboBox_1.getSelectedItem() + "',Enviroment='" + comboBox_1_1.getSelectedItem()
								+ "',description='" + txtrNewBug.getText() + "',status='" + comboBox_2.getSelectedItem()
								+ "' where Bug_id=" + Integer.parseInt(textField.getText());
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrack", "root",
								"root");
						PreparedStatement statement = con.prepareStatement(querry2);
						statement.executeUpdate();
						textField.setText("");
						txtrNewBug.setText("");
						warning.setText("Record Updated Sucessfully!!!!");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					tableData();
				}
			}
			
		});
		
		
		
		btnUpdateRecord.setForeground(new Color(255, 128, 0));
		btnUpdateRecord.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnUpdateRecord.setBounds(163, 316, 156, 36);
		contentPane.add(btnUpdateRecord);

		JLabel lblNewLabel_1 = new JLabel("BUG TRACKER SYSTEM");
		lblNewLabel_1.setFont(new Font("Traditional Arabic", Font.BOLD, 18));
		lblNewLabel_1.setForeground(new Color(255, 0, 128));
		lblNewLabel_1.setBounds(202, 0, 229, 32);
		contentPane.add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(477, 358, 77, 34);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Delete Record");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setForeground(new Color(255, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String querry = "DELETE FROM new_table WHERE Bug_id=?";
					Class.forName("com.mysql.cj.jdbc.Driver");//1
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrack", "root",
							"root");//2
					PreparedStatement statement = con.prepareStatement(querry);//3
					statement.setInt(1, Integer.parseInt(textField.getText()));
					statement.executeUpdate();
					warning.setText("Data deleted sucessfully");
					textField.setText("");
					txtrNewBug.setText("");
			}
				catch (Exception e2) {
					e2.printStackTrace();
				}
				tableData();}
		});
		btnNewButton_2.setBounds(348, 316, 136, 36);
		contentPane.add(btnNewButton_2);


	}

	private void tableData(){
		try{
		String a= "Select* from new_table";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrack","root","root");
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(a);
		table1.setModel(buildTableModel(rs));
		}catch (Exception ex1){
		ex1.printStackTrace();
		}
		}

	@SuppressWarnings("unchecked")
	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException {
			ResultSetMetaData metaData = rs.getMetaData();
			// names of columns
			@SuppressWarnings("rawtypes")
			Vector<String> columnNames = new Vector();
			int columnCount = metaData.getColumnCount();
			for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
			}
			// data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
			}
			return new DefaultTableModel(data, columnNames);
			}
}
