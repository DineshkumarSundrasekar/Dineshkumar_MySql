package com.gl.graded.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class MysqlJdbcGradedProject {
    
    public static void insert(Statement st, int id, String name, String email, String phone) throws SQLException {
        String query = "INSERT INTO employee VALUES(" + id + ",'" + name + "','" + email + "','" + phone + "')";
       
        int count = st.executeUpdate(query);
        if (count != 0)
            System.out.println(count + " Records were inserted into the employee table");
        else
            System.out.println("Could not insert the employee record");
    }
    
    public static void alter(Statement st,String query) throws SQLException {
    	st.executeUpdate(query);
    	return;
    }
    
    public static void update(Statement st,String query) throws SQLException {
    	int rowsUpdated = st.executeUpdate(query);
        if (rowsUpdated > 0) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("No records were updated.");
        }
    }
    
    public static void delete(Statement st, String query) throws SQLException {
    	int rowsAffected = st.executeUpdate(query);

        if (rowsAffected > 0) {
            System.out.println(rowsAffected + " record deleted successfully.");
        } else {
            System.out.println("No records deleted.");
        }
    }
    
    public static void remove(Statement st, String query) throws SQLException {
    	int rowsAffected = st.executeUpdate(query);
        System.out.println("Deleted " + rowsAffected + " records from the employee table.");     
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gl", "root", "DKthiru@3535");
        Statement st = con.createStatement();
        
        // a. Insert 5 records
        
        insert(st, 1, "Alan", "alan@gmail.com", "33534353");
        insert(st, 2, "Deekshith", "deekshith@gmail.com", "346432253");
        insert(st, 3, "Dinesh", "dinesh@gmail.com", "12345353");
        insert(st, 4, "Gokul", "gokul.com", "32532221");
        insert(st, 5, "Roop", "roop@gmail.com", "93543223");

        // b. Modify Email_Id column to varchar(30) NOT NULL
        
        String sql1 = "ALTER TABLE employee MODIFY email_id varchar(30) not null";
        alter(st,sql1);
        
        // c. Insert 2 records and check.
        
        insert(st,6,"Charan","charn123@gmail.com","9874563210");
        
        // d. Update the name of employee Id 3 to Karthik and phone number to 1234567890.
        
        String Sql2 = "UPDATE Employee SET name = 'Karthik', phone_number=1234567890 WHERE id = 3";
        update(st,Sql2);
        
        // e. Delete employee records 3 and 4.
        String sql3 = "DELETE FROM Employee WHERE id IN (3, 4)";
        delete(st,sql3);
        
        // f. Remove all records from the table employee.
        String sql4 = "DELETE FROM employee";
        remove(st,sql4);
        
        st.close();
        con.close();
    }
}
