
package contacts;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import contacts.DB_connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class contact_list {

	private JFrame frame;
	 
	//Search Pane
	private JTextField txtSearch;
	
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtMname;
	private JTextField txtHomeStreetAddress;
	private JTextField txtHomeCity;
	private JTextField txtHomeState;
	private JTextField txtHomeZip;
	private JTextField txtWorkStreetAddress;
	private JTextField txtWorkCity;
	private JTextField txtWorkState;
	private JTextField txtWorkZip;
	private JTextField txtHomeAreaCode;
	private JTextField txtHomeNumber;
	private JTextField txtWorkAreaCode;
	private JTextField txtWorkNumber;
	private JTextField txtFaxAreaCode;
	private JTextField txtFaxNumber;
	private JTextField txtDateType;
	private JTextField txtDate;
	private JTable table_1;
	
	private JTextField txtFname1;
	private JTextField txtLname1;
	private JTextField txtMname1;
	private JTextField txtHomeStreetAddress1;
	private JTextField txtHomeCity1;
	private JTextField txtHomeState1;
	private JTextField txtHomeZip1;
	private JTextField txtWorkStreetAddress1;
	private JTextField txtWorkCity1;
	private JTextField txtWorkState1;
	private JTextField txtWorkZip1;
	private JTextField txtHomeAreaCode1;
	private JTextField txtHomeNumber1;
	private JTextField txtWorkAreaCode1;
	private JTextField txtWorkNumber1;
	private JTextField txtFaxAreaCode1;
	private JTextField txtFaxNumber1;
	private JTextField txtDateType1;
	private JTextField txtDate1;	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					contact_list window = new contact_list();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public contact_list() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1331, 737);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);		
		
		//Search Panel		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search", null, panel, null);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setForeground(Color.BLACK);
		txtSearch.setBounds(42, 16, 617, 61);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					DefaultTableModel model3 = (DefaultTableModel) table_1.getModel();
					model3.setRowCount(0);
					String search = txtSearch.getText();
					DB_connect con = new DB_connect();
					 
					String query= null;
					 
					
					String Fname = null, Lname = null, Mname = null, Contact_id = null;
					
					
					if(!search.isEmpty())
					{
						query="select Distinct c.Contact_id, c.Fname, c.Mname, c.Lname  from phone as p, contact c, address a where a.Contact_id=c.Contact_id and c.Contact_id=p.Contact_id and (c.Fname like '%"+ search +"%' or c.Lname like '%"+ search +"%' or c.Mname  like '%"+ search +"%' or a.Address like '%"+ search +"%' or p.Number like '%"+ search +"%'  )";
						//query = "Select first_name, last_name, middle_name, contact_id from [dbo].[Contacts] c where c.first_name like '%"+ search +"%' or c.last_name like '%"+ search +"%' or c.middle_name  like '%"+ search +"%' or c.home_address like '%"+ search +"%' or c.home_phone like '%"+ search +"%';";
						if(!query.isEmpty()){  										  				              
				            
				            ResultSet r = con.Connection(query);  
				            
				            if(!r.next()){
	
				            	JOptionPane.showMessageDialog(null,"No Matches");
				            }
				            else{
				            	Object obj1[] = {Contact_id, Fname,Mname,Lname};
				                do {
				                	Contact_id = r.getString("Contact_id");
				                	Fname = r.getString("Fname");
				                	Lname = r.getString("Lname");
				                	Mname = r.getString("Mname");


				                		if(obj1[0]!=null)
				                			model3.addRow(obj1);
				                		obj1[0] = Contact_id;
			                			obj1[1] = Fname;
			                			obj1[2] = Lname;
			                			obj1[3] = Mname;

				                	
				                } while (r.next());
				            }
						}
					}
					else 
						{
							JOptionPane.showMessageDialog(null,"Enter any value to search");
						}
				}
				catch(SQLException e4)
				{
					e4.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(720, 15, 150, 62);
		panel.add(btnSearch);
		
		table_1 = new JTable();
		table_1.setBounds(42, 123, 1229, 483);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 115, 1229, 532);
		panel.add(scrollPane);
		
		table_1.setModel(new DefaultTableModel
				(
			new Object[][] {
			},
			new String[] {
				"Contact_id", "Fname", "Lname", "Mname"
			}
						)
				);
		
		scrollPane.setViewportView(table_1);
				
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int rowIndex=table_1.getSelectedRow();
				DB_connect con = new DB_connect();
				if(rowIndex == -1){
					
					JOptionPane.showMessageDialog(null,"select atleast a single row");
					
				}
				else{
					String C_id = (String) table_1.getValueAt(rowIndex, 0);
					int Cid = Integer.parseInt(C_id);
					String Fname1 = null, Mname1 = null, Lname1 = null;
			        try{
			        	
			        	String query_modify="select * from CONTACT where Contact_id = " + Cid;

				        ResultSet r1 = con.Connection(query_modify);  

	                	if(!r1.next()){
	                		
			            	JOptionPane.showMessageDialog(null,"No Matches");
			            }
			            else{
			                do {
			                	Fname1 = r1.getString("Fname");
			                	Lname1 = r1.getString("Lname");
			                	Mname1 = r1.getString("Mname");

			                } while (r1.next());
			            }
	                	
	                	
						String Address_type = null;
						String Home_Street_Address = null, Home_City = null, Home_State = null, Home_Zip = null;
						String Work_Street_Address = null, Work_City = null, Work_State = null, Work_Zip = null;

	                	String query_modify2 = "select * from Address where Contact_id = " + Cid;

				        ResultSet r2 = con.Connection(query_modify2);  

	                	if(!r2.next()){
	                		
			            	JOptionPane.showMessageDialog(null,"No Matches");
			            }
			            else{
			                do {
			                	Address_type = r2.getString("Address_type");
			                	if(Address_type.equals("Home")) {
			                		Home_Street_Address = r2.getString("Address");
			                		Home_City = r2.getString("City");
			                		Home_State = r2.getString("State");
			                		Home_Zip = r2.getString("Zip");
			                	}
			                	else if(Address_type.equals("Work")) {
			                		Work_Street_Address = r2.getString("Address");
			                		Work_City = r2.getString("City");
			                		Work_State = r2.getString("State");
			                		Work_Zip = r2.getString("Zip");
			                	}

			                } while (r2.next());
			            }
	                	
	                	String Phone_type = null;
						String Home_Area_Code = null, Home_Number = null;
						String Work_Area_Code = null, Work_Number = null;
						String Fax_Area_Code = null, Fax_Number = null;

	                	String query_modify3 = "select * from Phone where Contact_id = " + Cid;

				        ResultSet r3 = con.Connection(query_modify3);  

	                	if(!r3.next()){
	                		
			            	JOptionPane.showMessageDialog(null,"No Matches");
			            }
			            else{
			                do {
			       
			                	Phone_type = r3.getString(3);

			                	if(Phone_type.equals("Home")) {
			                		Home_Area_Code = r3.getString(4);
			                		Home_Number = r3.getString(5);
			                	}
			                	else if(Phone_type.equals("Work")) {
			                		Work_Area_Code = r3.getString(4);
			                		Work_Number = r3.getString(5);
			                	}
			                	else if(Phone_type.equals("Cell")) {
			                		Fax_Area_Code = r3.getString(4);
			                		Fax_Number = r3.getString(5);
			                	}
			                } while (r3.next());
			            }
			    
						String Date_Type = null, Date1 = null;

	                	String query_modify4 = "select * from Date where Contact_id = " + Cid;

				        ResultSet r4 = con.Connection(query_modify4); 

	                	if(!r4.next()){
	                		
			            	JOptionPane.showMessageDialog(null,"No Matches");
			            }
			            else{
			                do {
			                	Date_Type = r4.getString("Date_type");
			                	if(Date_Type.equals("Birth")) {
			                		Date1 = r4.getString("Date");
			                	}

			                } while (r4.next());
			            }
	                	
				        JPanel panel_3 = new JPanel();
						tabbedPane.addTab("Modify Contact", null, panel_3, null);
						panel_3.setLayout(null);
	
						
						JLabel Fname = new JLabel("First Name");
						Fname.setBounds(43, 55, 160, 20);
						panel_3.add(Fname);
						
						JLabel Lname = new JLabel("Last Name");
						Lname.setBounds(620, 55, 160, 20);
						panel_3.add(Lname);
	
						JLabel Mname = new JLabel("Middle Name");
						Mname.setBounds(43, 100, 160, 20);
						panel_3.add(Mname);
						
						txtFname = new JTextField(Fname1);
						txtFname.setBounds(181, 55, 355, 26);
						panel_3.add(txtFname);
						txtFname.setColumns(10);
						
						txtLname = new JTextField(Lname1);
						txtLname.setBounds(790, 55, 355, 26);
						panel_3.add(txtLname);
						txtLname.setColumns(10);
						
						txtMname = new JTextField(Mname1);
						txtMname.setBounds(181, 100, 355, 26);
						panel_3.add(txtMname);
						txtMname.setColumns(10);
						
						JLabel HomeStreetAddress = new JLabel("Home Street Address");
						HomeStreetAddress.setBounds(620, 100, 138, 20);
						panel_3.add(HomeStreetAddress);
						
						txtHomeStreetAddress = new JTextField(Home_Street_Address);
						txtHomeStreetAddress.setBounds(790, 100, 355, 26);
						panel_3.add(txtHomeStreetAddress);
						txtHomeStreetAddress.setColumns(10);
						
						JLabel HomeCity = new JLabel("Home City");
						HomeCity.setBounds(620, 150, 138, 20);
						panel_3.add(HomeCity);
						
						txtHomeCity = new JTextField(Home_City);
						txtHomeCity.setBounds(790, 150, 355, 26);
						panel_3.add(txtHomeCity);
						txtHomeCity.setColumns(10);
						
						JLabel HomeState = new JLabel("Home State");
						HomeState.setBounds(620, 200, 138, 20);
						panel_3.add(HomeState);
						
						txtHomeState = new JTextField(Home_State);
						txtHomeState.setBounds(790, 200, 355, 26);
						panel_3.add(txtHomeState);
						txtHomeState.setColumns(10);
						
						JLabel HomeZip = new JLabel("Home Zip");
						HomeZip.setBounds(620, 250, 138, 20);
						panel_3.add(HomeZip);
						
						txtHomeZip = new JTextField(Home_Zip);
						txtHomeZip.setBounds(790, 250, 355, 26);
						panel_3.add(txtHomeZip);
						txtHomeZip.setColumns(10);
						
						JLabel WorkStreetAddress = new JLabel("Work Street Address");
						WorkStreetAddress.setBounds(620, 300, 138, 20);
						panel_3.add(WorkStreetAddress);
						
						txtWorkStreetAddress = new JTextField(Work_Street_Address);
						txtWorkStreetAddress.setBounds(790, 300, 355, 26);
						panel_3.add(txtWorkStreetAddress);
						txtWorkStreetAddress.setColumns(10);
						
						JLabel WorkCity = new JLabel("Work City");
						WorkCity.setBounds(620, 350, 138, 20);
						panel_3.add(WorkCity);
						
						txtWorkCity = new JTextField(Work_City);
						txtWorkCity.setBounds(790, 350, 355, 26);
						panel_3.add(txtWorkCity);
						txtWorkCity.setColumns(10);
						
						JLabel WorkState = new JLabel("Work State");
						WorkState.setBounds(620, 400, 138, 20);
						panel_3.add(WorkState);
						
						txtWorkState = new JTextField(Work_State);
						txtWorkState.setBounds(790, 400, 355, 26);
						panel_3.add(txtWorkState);
						txtWorkState.setColumns(10);
						
						JLabel WorkZip = new JLabel("Work Zip");
						WorkZip.setBounds(620, 450, 138, 20);
						panel_3.add(WorkZip);
						
						txtWorkZip = new JTextField(Work_Zip);
						txtWorkZip.setBounds(790, 450, 355, 26);
						panel_3.add(txtWorkZip);
						txtWorkZip.setColumns(10);
						
						JLabel HomeAreaCode = new JLabel("Home Area Code");
						HomeAreaCode.setBounds(43, 150, 138, 20);
						panel_3.add(HomeAreaCode);
						
						txtHomeAreaCode = new JTextField(Home_Area_Code);
						txtHomeAreaCode.setBounds(181, 150, 355, 26);
						panel_3.add(txtHomeAreaCode);
						txtHomeAreaCode.setColumns(10);
	
						JLabel HomeNumber = new JLabel("Home Number");
						HomeNumber.setBounds(43, 200, 138, 20);
						panel_3.add(HomeNumber);
						
						txtHomeNumber = new JTextField(Home_Number);
						txtHomeNumber.setBounds(181, 200, 355, 26);
						panel_3.add(txtHomeNumber);
						txtHomeNumber.setColumns(10);
						
						JLabel WorkAreaCode = new JLabel("Work Area Code");
						WorkAreaCode.setBounds(43, 250, 138, 20);
						panel_3.add(WorkAreaCode);
						
						txtWorkAreaCode = new JTextField(Work_Area_Code);
						txtWorkAreaCode.setBounds(181, 250, 355, 26);
						panel_3.add(txtWorkAreaCode);
						txtWorkAreaCode.setColumns(10);
	
						JLabel WorkNumber = new JLabel("Work Number");
						WorkNumber.setBounds(43, 300, 138, 20);
						panel_3.add(WorkNumber);
						
						txtWorkNumber = new JTextField(Work_Number);
						txtWorkNumber.setBounds(181, 300, 355, 26);
						panel_3.add(txtWorkNumber);
						txtWorkNumber.setColumns(10);
						
						JLabel FaxAreaCode = new JLabel("Cell Area Code");
						FaxAreaCode.setBounds(43, 350, 138, 20);
						panel_3.add(FaxAreaCode);
						
						txtFaxAreaCode = new JTextField(Fax_Area_Code);
						txtFaxAreaCode.setBounds(181, 350, 355, 26);
						panel_3.add(txtFaxAreaCode);
						txtFaxAreaCode.setColumns(10);
	
						JLabel FaxNumber = new JLabel("Fax Number");
						FaxNumber.setBounds(43, 400, 138, 20);
						panel_3.add(FaxNumber);
						
						txtFaxNumber = new JTextField(Fax_Number);
						txtFaxNumber.setBounds(181, 400, 355, 26);
						panel_3.add(txtFaxNumber);
						txtFaxNumber.setColumns(10);
						
						JLabel DateType = new JLabel("Date Type");
						DateType.setBounds(43, 450, 138, 20);
						panel_3.add(DateType);
						
						txtDateType = new JTextField(Date_Type);
						txtDateType.setBounds(181, 450, 355, 26);
						panel_3.add(txtDateType);
						txtDateType.setColumns(10);
						
						JLabel Date = new JLabel("Date");
						Date.setBounds(43, 500, 138, 20);
						panel_3.add(Date);
						
						txtDate = new JTextField(Date1);
						txtDate.setBounds(181, 500, 355, 26);
						panel_3.add(txtDate);
						txtDate.setColumns(10);
						
						JButton btnModifyContact = new JButton("Modify Contact");
						btnModifyContact.addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent arg0)
							{
								String s1 = txtFname.getText();
								String s2 = txtLname.getText();
								String s3 = txtMname.getText();
								String HomeStreet = txtHomeStreetAddress.getText();
								String HomeAreaCode = txtHomeAreaCode.getText();
								String HomeNumber = txtHomeNumber.getText();
								String HomeCity = txtHomeCity.getText();
								String HomeState = txtHomeState.getText();
								String Date = txtDate.getText();
								String HomeZip = txtHomeZip.getText();
								String WorkStreet = txtWorkStreetAddress.getText();
								String WorkAreaCode = txtWorkAreaCode.getText();
								String WorkNumber = txtWorkNumber.getText();
								String WorkCity = txtWorkCity.getText();
								String WorkState = txtWorkState.getText();
								String WorkZip = txtWorkZip.getText();
								String FaxAreaCode = txtFaxAreaCode.getText();
								String FaxNumber = txtFaxNumber.getText();
	
								DB_connect con  = new DB_connect();
															
								String query2 = "Update Contact Set Fname = '"+s1+"', Mname = '"+s2+"', Lname = '"+s3+"' Where Contact_id = '"+Cid+"' ;";
								int r1 = con.Connection1(query2);
	
								String query3 = "Update Address Set Address = '"+HomeStreet+"', City = '"+HomeCity+"', State = '"+HomeState+"', Zip = '"+HomeZip+"' Where Contact_id = '"+Cid+"' and Address_type = 'Home' ;";
								con.Connection1(query3);
								
								String query4 = "Update Address Set Address = '"+WorkStreet+"', City = '"+WorkCity+"', State = '"+WorkState+"', Zip = '"+WorkZip+"' Where Contact_id = '"+Cid+"' and Address_type = 'Work' ;";
								con.Connection1(query4);
								
								String query5 = "Update Phone Set Area_code = '"+HomeAreaCode+"', Number = '"+HomeNumber+"' Where Contact_id = '"+Cid+"' and Phone_type = 'Home' ;";
								con.Connection1(query5);
								
								String query6 = "Update Phone Set Area_code = '"+WorkAreaCode+"', Number = '"+WorkNumber+"' Where Contact_id = '"+Cid+"' and Phone_type = 'Work' ;";												
								con.Connection1(query6);
								
								String query7 = "Update Phone Set Area_code = '"+FaxAreaCode+"', Number = '"+FaxNumber+"' Where Contact_id = '"+Cid+"' and Phone_type = 'Cell' ;";												
								con.Connection1(query7);
								
								String query8 = "Update Date Set Date = '"+Date+"' Where Contact_id = '"+Cid+"' and Date_type = 'Birth' ;";												
								con.Connection1(query8);
								
								/*String query9 = "Update Contacts Set first_name = '"+s1+"', middle_name = '"+s2+"', last_name = '"+s3+"', home_phone = '"+HomeNumber+"', cell_phone = '"+FaxNumber+"', work_phone = '"+WorkNumber+"', home_address = '"+HomeStreetAddress+"', home_city = '"+HomeCity+"', home_state = '"+HomeState+"', home_zip  = '"+HomeZip+"', work_address = '"+WorkStreetAddress+"', work_city = '"+WorkCity+"', work_state = '"+WorkState+"', work_zip  = '"+WorkZip+"', birth_date = '"+Date+"' Where Contact_id = '"+Cid+"' ;";
								//String query9 = "Update Contacts set middle_name = 'pppppp' where contact_id = 1;";
								int r9 = con.Connection1(query9);
								*/
								if(r1 != 0){
			                		
					            	JOptionPane.showMessageDialog(null,"Contact Details Updated");
					            }
							}
						});
						
						btnModifyContact.setBounds(345, 576, 236, 29);
						panel_3.add(btnModifyContact);
                	}
	        catch (Exception e9) {
	            e9.printStackTrace();
	        }  			        
				}
							
			}
		});
		btnModify.setBounds(890, 15, 150, 62);
		panel.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int rowIndex=table_1.getSelectedRow();
				DB_connect con = new DB_connect();
				if(rowIndex == -1){
					
					JOptionPane.showMessageDialog(null,"select atleast a single row");
					
				}
				else{
					String C_id = (String) table_1.getValueAt(rowIndex, 0);
					int Cid = Integer.parseInt(C_id);

			        try{
			        	
			        	String query_delete1="Delete from Phone where Contact_id = " + Cid;
				        con.Connection1(query_delete1); 
				        
				        String query_delete2="Delete from Address where Contact_id = " + Cid;
				        con.Connection1(query_delete2); 
				        
				        String query_delete3="Delete from Date where Contact_id = " + Cid;
				        con.Connection1(query_delete3); 
				        
				        String query_delete4="Delete from Contact where Contact_id = " + Cid;
				        int d4 = con.Connection1(query_delete4); 
				        
						if(d4!=0 )
						{
							JOptionPane.showMessageDialog(null,"Contact deleted");
						}

			        }	        catch (Exception e9) {
			            e9.printStackTrace();
			        } 
				}
			}
		});
				
				
		btnDelete.setBounds(1060, 15, 150, 62);
		panel.add(btnDelete);
		
		//Add Contacts
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Add New Contact", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel Fname1 = new JLabel("First Name");
		Fname1.setBounds(43, 55, 160, 20);
		panel_3.add(Fname1);
		
		JLabel Lname1 = new JLabel("Last Name");
		Lname1.setBounds(620, 55, 160, 20);
		panel_3.add(Lname1);

		JLabel Mname1 = new JLabel("Middle Name");
		Mname1.setBounds(43, 100, 160, 20);
		panel_3.add(Mname1);
		
		txtFname1 = new JTextField();
		txtFname1.setBounds(181, 55, 355, 26);
		panel_3.add(txtFname1);
		txtFname1.setColumns(10);
		
		txtLname1 = new JTextField();
		txtLname1.setBounds(790, 55, 355, 26);
		panel_3.add(txtLname1);
		txtLname1.setColumns(10);
		
		txtMname1 = new JTextField();
		txtMname1.setBounds(181, 100, 355, 26);
		panel_3.add(txtMname1);
		txtMname1.setColumns(10);
		
		JLabel HomeStreetAddress1 = new JLabel("Home Street Address");
		HomeStreetAddress1.setBounds(620, 100, 138, 20);
		panel_3.add(HomeStreetAddress1);
		
		txtHomeStreetAddress1 = new JTextField();
		txtHomeStreetAddress1.setBounds(790, 100, 355, 26);
		panel_3.add(txtHomeStreetAddress1);
		txtHomeStreetAddress1.setColumns(10);
		
		JLabel HomeCity1 = new JLabel("Home City");
		HomeCity1.setBounds(620, 150, 138, 20);
		panel_3.add(HomeCity1);
		
		txtHomeCity1 = new JTextField();
		txtHomeCity1.setBounds(790, 150, 355, 26);
		panel_3.add(txtHomeCity1);
		txtHomeCity1.setColumns(10);
		
		JLabel HomeState1 = new JLabel("Home State");
		HomeState1.setBounds(620, 200, 138, 20);
		panel_3.add(HomeState1);
		
		txtHomeState1 = new JTextField();
		txtHomeState1.setBounds(790, 200, 355, 26);
		panel_3.add(txtHomeState1);
		txtHomeState1.setColumns(10);
		
		JLabel HomeZip1 = new JLabel("Home Zip");
		HomeZip1.setBounds(620, 250, 138, 20);
		panel_3.add(HomeZip1);
		
		txtHomeZip1 = new JTextField();
		txtHomeZip1.setBounds(790, 250, 355, 26);
		panel_3.add(txtHomeZip1);
		txtHomeZip1.setColumns(10);
		
		JLabel WorkStreetAddress1 = new JLabel("Work Street Address");
		WorkStreetAddress1.setBounds(620, 300, 138, 20);
		panel_3.add(WorkStreetAddress1);
		
		txtWorkStreetAddress1 = new JTextField();
		txtWorkStreetAddress1.setBounds(790, 300, 355, 26);
		panel_3.add(txtWorkStreetAddress1);
		txtWorkStreetAddress1.setColumns(10);
		
		JLabel WorkCity1 = new JLabel("Work City");
		WorkCity1.setBounds(620, 350, 138, 20);
		panel_3.add(WorkCity1);
		
		txtWorkCity1 = new JTextField();
		txtWorkCity1.setBounds(790, 350, 355, 26);
		panel_3.add(txtWorkCity1);
		txtWorkCity1.setColumns(10);
		
		JLabel WorkState1 = new JLabel("Work State");
		WorkState1.setBounds(620, 400, 138, 20);
		panel_3.add(WorkState1);
		
		txtWorkState1 = new JTextField();
		txtWorkState1.setBounds(790, 400, 355, 26);
		panel_3.add(txtWorkState1);
		txtWorkState1.setColumns(10);
		
		JLabel WorkZip1 = new JLabel("Work Zip");
		WorkZip1.setBounds(620, 450, 138, 20);
		panel_3.add(WorkZip1);
		
		txtWorkZip1 = new JTextField();
		txtWorkZip1.setBounds(790, 450, 355, 26);
		panel_3.add(txtWorkZip1);
		txtWorkZip1.setColumns(10);
		
		JLabel HomeAreaCode1 = new JLabel("Home Area Code");
		HomeAreaCode1.setBounds(43, 150, 138, 20);
		panel_3.add(HomeAreaCode1);
		
		txtHomeAreaCode1 = new JTextField();
		txtHomeAreaCode1.setBounds(181, 150, 355, 26);
		panel_3.add(txtHomeAreaCode1);
		txtHomeAreaCode1.setColumns(10);

		JLabel HomeNumber1 = new JLabel("Home Number");
		HomeNumber1.setBounds(43, 200, 138, 20);
		panel_3.add(HomeNumber1);
		
		txtHomeNumber1 = new JTextField();
		txtHomeNumber1.setBounds(181, 200, 355, 26);
		panel_3.add(txtHomeNumber1);
		txtHomeNumber1.setColumns(10);
		
		JLabel WorkAreaCode1 = new JLabel("Work Area Code");
		WorkAreaCode1.setBounds(43, 250, 138, 20);
		panel_3.add(WorkAreaCode1);
		
		txtWorkAreaCode1 = new JTextField();
		txtWorkAreaCode1.setBounds(181, 250, 355, 26);
		panel_3.add(txtWorkAreaCode1);
		txtWorkAreaCode1.setColumns(10);

		JLabel WorkNumber1 = new JLabel("Work Number");
		WorkNumber1.setBounds(43, 300, 138, 20);
		panel_3.add(WorkNumber1);
		
		txtWorkNumber1 = new JTextField();
		txtWorkNumber1.setBounds(181, 300, 355, 26);
		panel_3.add(txtWorkNumber1);
		txtWorkNumber1.setColumns(10);
		
		JLabel FaxAreaCode1 = new JLabel("Cell Area Code");
		FaxAreaCode1.setBounds(43, 350, 138, 20);
		panel_3.add(FaxAreaCode1);
		
		txtFaxAreaCode1 = new JTextField();
		txtFaxAreaCode1.setBounds(181, 350, 355, 26);
		panel_3.add(txtFaxAreaCode1);
		txtFaxAreaCode1.setColumns(10);

		JLabel FaxNumber1 = new JLabel("Cell Number");
		FaxNumber1.setBounds(43, 400, 138, 20);
		panel_3.add(FaxNumber1);
		
		txtFaxNumber1 = new JTextField();
		txtFaxNumber1.setBounds(181, 400, 355, 26);
		panel_3.add(txtFaxNumber1);
		txtFaxNumber1.setColumns(10);
		
		JLabel DateType1 = new JLabel("DateType");
		DateType1.setBounds(43, 450, 138, 20);
		panel_3.add(DateType1);
		
		txtDateType1 = new JTextField();
		txtDateType1.setBounds(181, 450, 355, 26);
		panel_3.add(txtDateType1);
		txtDateType1.setColumns(10);
		
		JLabel Date1 = new JLabel("Date");
		Date1.setBounds(43, 500, 138, 20);
		panel_3.add(Date1);
		
		txtDate1 = new JTextField();
		txtDate1.setBounds(181, 500, 355, 26);
		panel_3.add(txtDate1);
		txtDate1.setColumns(10);
		
		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String af = txtFname1.getText();
				String al = txtLname1.getText();
				String am = txtMname1.getText();
				String aHomeStreet = txtHomeStreetAddress1.getText();
				String aHomeAreaCode = txtHomeAreaCode1.getText();
				String aHomeNumber = txtHomeNumber1.getText();
				String aHomeCity = txtHomeCity1.getText();
				//String aDateType = txtDateType1.getText();
				String aHomeState = txtHomeState1.getText();
				String aDate = txtDate1.getText();
				String aHomeZip = txtHomeZip1.getText();
				String aWorkStreet = txtWorkStreetAddress1.getText();
				String aWorkAreaCode = txtWorkAreaCode1.getText();
				String aWorkNumber = txtWorkNumber1.getText();
				String aWorkCity = txtWorkCity1.getText();
				String aWorkState = txtWorkState1.getText();
				String aWorkZip = txtWorkZip1.getText();
				String aFaxAreaCode = txtFaxAreaCode1.getText();
				String aFaxNumber = txtFaxNumber1.getText();
				
				DB_connect con  = new DB_connect();
							
				String query_add1 = "Insert into CONTACT(Fname, Lname, Mname) values ('"+af+"','"+al+"','"+am+"') ;";
				con.Connection1(query_add1);
				
				String qs = " Select MAX(Contact_id) from Contact;" ;
				
				ResultSet r_id = con.Connection(qs);
				int id = 0;
				try {
				if(!r_id.next()){
            		
	            	JOptionPane.showMessageDialog(null,"No Matches");
	            }
	            else{
	                
						do {
							id = r_id.getInt(1);
						} while (r_id.next());
					
	            }
								
				String query_add2 = "Insert into ADDRESS(Contact_id, Address_type, Address, City, State, Zip) values ('"+id+"','Home', '"+aHomeStreet+"','"+aHomeCity+"','"+aHomeState+"','"+aHomeZip+"');";
				con.Connection1(query_add2);
				
				String query_add3 = "Insert into ADDRESS(Contact_id, Address_type, Address, City, State, Zip) values ('"+id+"','Work', '"+aWorkStreet+"','"+aWorkCity+"','"+aWorkState+"','"+aWorkZip+"');";
				con.Connection1(query_add3);
				
				String query_add4 = "Insert into PHONE(Contact_id, Phone_type, Area_code, Number) values ('"+id+"', 'Home', '"+aHomeAreaCode+"', '"+aHomeNumber+"');";
				con.Connection1(query_add4);
				
				String query_add5 = "Insert into PHONE(Contact_id, Phone_type, Area_code, Number) values ('"+id+"', 'Work', '"+aWorkAreaCode+"', '"+aWorkNumber+"');";
				con.Connection1(query_add5);

				String query_add6 = "Insert into PHONE(Contact_id, Phone_type, Area_code, Number) values ('"+id+"', 'Cell', '"+aFaxAreaCode+"', '"+aFaxNumber+"');";
				con.Connection1(query_add6);
				
				String query_add7 = "Insert into DATE(Contact_id, Date_type,Date) values ('"+id+"', 'Birth', '"+aDate+"');";
				int a4 = con.Connection1(query_add7);
				
				if(a4!=0 )
				{
					JOptionPane.showMessageDialog(null,"Contact Added");
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnAddContact.setBounds(345, 576, 236, 29);
		panel_3.add(btnAddContact); 	 

	}
}

