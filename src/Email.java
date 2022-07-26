/* Hunter Scheppat March-July 2022
   @Email class for EmailApplication
 */
import java.sql.*;

//Email class contains the variables and methods of the Email object
//Connects to the database and adds the email's attributes
public class Email
{
    //Class Variables
    private final String firstName;
    private final String lastName;
    private String company;
    private String department;
    private String user_id;
    private String password;
    private String address;

    //initialize the connection
    Connection connection;


    //Email constructor
    Email(String fName, String lName, String comp, String dep) throws SQLException
    {

        this.firstName = fName;
        this.lastName = lName;
        this.company = comp;
        this.department = dep;
        //Create the necessary data to send to the database
        this.setAddress();
        this.setPassword();
        this.generateUID();

        //Create SQL statement & connection
        createConnection();
        Statement statement = connection.createStatement();
        //Create a string Query to SQL
        String dbInsert = "INSERT INTO EMAILUSER VALUES('" + this.user_id + "'" + ',' + "'" + this.firstName + "'" + ',';
        dbInsert += "'" + this.lastName + "'" + ',' + "'" + this.company +"'" + ',' +"'" + this.department +"'" + ',' +"'";
        dbInsert += this.address +"'" + ',' +"'" + this.password +"'" + ")";
        //Add the data using the Query
        statement.execute(dbInsert);
        statement.close();



    }

    //Set the address
    public void setAddress()
    {
        //Remove spaces
        company = company.replaceAll("\\s","");
        department = department.replaceAll("\\s","");

        address = firstName.toLowerCase() + "." + lastName.toLowerCase();
        address += "@" + department.toLowerCase() + "." + company.toLowerCase() + ".com";
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getPassword()
    {
        return this.password;
    }

    //Set the password
    public void setPassword()
    {
        String characters = "abcdefghijklmnopqrstuvwxyz123456789!@#$%";
        password = "";

        for(int i = 0; i < 18; i++)
        {
            int n = characters.length();
            int r = (int) (n * Math.random());
            password = password + characters.charAt(r);
        }
    }

    //Generate a UserID
    public void generateUID()
    {
        String characters = "123456789";
        user_id = "";

        for (int i = 0; i < 20; i++)
        {
            int n = characters.length();
            int r = (int) (n * Math.random());
            user_id = user_id + characters.charAt(r);
        }

    }

    //Connect to the database
    public void createConnection()
    {
        try
        {
            //ADD OWN USERNAME, PASSWORD, & DATABASE NAME HERE
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATABASENAME", "root", "PASSWORD");
        }
        catch (SQLException e)
        {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
            System.out.println("printTrace /n");
            e.printStackTrace();
        }
    }
}