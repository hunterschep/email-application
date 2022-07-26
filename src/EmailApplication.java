/* Hunter Scheppat | March-July 2022

   @Frameworks used: JDBC(Java Database Connector), Java Swing(GUI), &
   mySQL server to create a small GUI and save its information

   @Libraries: mySQL connectorJ from Oracle

   @Project use/function: the "application" takes a user's full name, company
   & department and generates an email, userID, and unique password which
   is added to a mySQL database

   @Things needed to run: JAVA, mySQL server, necessary libs

 */

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

//EmailApplication class contains the main method and the framework of the application
public class EmailApplication extends JFrame implements ActionListener
{
   //Global variables for GUI(user interface)
   //Text fields
   private static JTextField userText1;
   private static JTextField userText2;
   private static JTextField userText3;
   private static JTextField cEmail;
   private static JTextField cPassword;
   //Buttons
   private static JButton button;
   //Labels
   private static JLabel dataBaseSuccess;

   //Initialize email object
   public Email user;

   public static void main(String[] args)
   {
      //Create and set the main display panel
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      frame.setSize(1200,1200);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(panel);
      panel.setBackground(Color.GRAY);
      frame.setTitle("SchepSystems Email Generator");
      panel.setLayout(null);

      //Schep systems label
      JLabel schep = new JLabel("SchepSystems Email Generator");
      schep.setBounds(200, 40, 800, 50);
      schep.setFont(new Font("Calibri", Font.BOLD,48));
      panel.add(schep);

      //Full name label
      JLabel name = new JLabel("Full Name: ");
      name.setBounds(250, 150, 200, 40);
      name.setFont(new Font("Calibri", Font.BOLD,34));
      panel.add(name);

      //Text field to take user's input
      userText1 = new JTextField();
      userText1.setBounds(500, 140, 250, 50);
      userText1.setFont(new Font("Calibri", Font.PLAIN,28));
      panel.add(userText1);

      //Company label
      JLabel company = new JLabel("Company: ");
      company.setBounds(250, 220, 200, 40);
      company.setFont(new Font("Calibri", Font.BOLD,34));
      panel.add(company);

      //Text field to take user's input
      userText2 = new JTextField();
      userText2.setBounds(500, 210, 250, 50);
      userText2.setFont(new Font("Calibri", Font.PLAIN,28));
      panel.add(userText2);

      //Department panel with textField
      JLabel department = new JLabel("Department: ");
      department.setBounds(250, 290, 200, 40);
      department.setFont(new Font("Calibri", Font.BOLD,34));
      panel.add(department);

      //Text field to take user's input
      userText3 = new JTextField();
      userText3.setBounds(500, 280, 250, 50);
      userText3.setFont(new Font("Calibri", Font.PLAIN,28));
      panel.add(userText3);

      //Submit button to submit text entered in fields
      button = new JButton("Submit");
      button.setBounds(275, 350, 150, 40);
      button.addActionListener(new EmailApplication());
      panel.add(button);

      //Output fields for the email & password generated
      cEmail = new JTextField("Company Email: ");
      cEmail.setBounds(250, 410, 850, 75);
      cEmail.setFont(new Font("Oxygen", Font.BOLD,24));
      cEmail.setBackground(Color.PINK);
      panel.add(cEmail);

      cPassword = new JTextField("Company Password: ");
      cPassword.setBounds(250, 470, 850, 75);
      cPassword.setFont(new Font("Oxygen", Font.BOLD,24));
      cPassword.setBackground(Color.PINK);
      panel.add(cPassword);

      //Tell user if database upload was successful
      dataBaseSuccess = new JLabel("");
      dataBaseSuccess.setBounds(470,350,400,40);
      dataBaseSuccess.setFont(new Font("Oxygen", Font.BOLD,24));
      panel.add(dataBaseSuccess);
      frame.setVisible(true);

   }

   //Method to be implemented on button press
   @Override
   public void actionPerformed(ActionEvent e)
   {
      //if the button is pressed initialize temporary variables before passing them to the constructor
      if (e.getSource()==button)
      {
         String tempUser;
         String tempFirst;
         String tempLast;
         String tempCompany;
         String tempDepartment;

         //Input validation(cannot enter empty text field)
         if (userText1.getText().length() >= 1)
         {
            tempUser = userText1.getText();
            tempFirst = getFirst(tempUser);
            tempLast = getLast(tempUser);

            //Input validation(cannot enter empty text field)
            if (userText2.getText().length() >= 1)
            {
               tempCompany = userText2.getText();

               //Input validation(cannot enter empty text field)
               if (userText3.getText().length() >= 1)
               {
                  tempDepartment = userText3.getText();
                  String address;
                  String password;
                  try
                  {
                     //Pass the data gathered from the submitted fields to a new instance of the email class
                     user = new Email(tempFirst, tempLast, tempCompany, tempDepartment);
                     dataBaseSuccess.setText("Successfully Added to Database");

                     //Generate the email and password
                     address = user.getAddress();
                     password = user.getPassword();

                     //Set the text fields to display this
                     cEmail.setText("Company Email: " + address);
                     cPassword.setText("Company Password: " + password);
                  }
                  //Catch and print SQL exceptions
                  catch (SQLException ex)
                  {
                     System.out.println("Error Code = " + ex.getErrorCode());
                     System.out.println("SQL state = " + ex.getSQLState());
                     System.out.println("Message = " + ex.getMessage());
                     System.out.println("printTrace /n");
                     ex.printStackTrace();
                  }

               }
            }
         }
         //If text field is empty, display error
         else
         {
            cEmail.setText("ERROR: name, department, or company not entered");
         }
      }

   }

   //Methods to parse the first and last names when a full name is submitted
   public String getFirst(@NotNull String full)
   {
      int space = full.indexOf(" ");
      return full.substring(0,space);
   }

   public String getLast(@NotNull String full)
   {
      int len = full.length();
      int space = full.indexOf(" ");
      return full.substring(space + 1,len);
   }

}