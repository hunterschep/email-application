### What is this project?

Email Application takes input, generates a user's company email and password, then adds this to a mySQL database

#### Input:
* Full name
* Company
* Department

#### Output:
* Company email
* Password

#### Example:
![Program & SQL](C:\Users\h2cub\OneDrive\Documents\email-application\images\DEMONSTRATION.jpg)


### How do I use this project?

#### I. Download mySQL server & workbench
&nbsp;&nbsp;&nbsp; [mySQL setup](https://www.youtube.com/watch?v=OM4aZJW_Ojs)

#### II. Create a your own mySQL database with workbench query

&nbsp;&nbsp;&nbsp; Copy the code below
```kotlin
CREATE TABLE emailUser (
    primary key(user_id),
    user_id VARCHAR(20),
    first_name VARCHAR(36),
    last_name VARCHAR(36),
    company VARCHAR(36),
    department VARCHAR(36),
    email VARCHAR(36),
    user_password VARCHAR(18)
);
```


#### III. Edit the email.java code to connect your own database
&nbsp;&nbsp;&nbsp; CAPS fields should be edited with your database name, & password
```java
//ADD OWN USERNAME, PASSWORD, & DATABASE NAME HERE
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATABASENAME", "root", "PASSWORD");
```
