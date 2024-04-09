/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author amrka
 */
public class Connect {
    public static void main(String[] args) {
        try {
            //read from file
            FileReader fr=new FileReader("C:\\Users\\amrka\\OneDrive\\Documents\\NetBeansProjects\\files\\from.txt");
            BufferedReader br=new BufferedReader(fr);
            String line=br.readLine();
            // Load Driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Password123");
            String[] row = new String[3];
            while(line!=null){
                // Declaration of a String array named row with a size of no of cols 
                row=line.split(" ");
                String id=row[0];
                String name=row[1];
                String salary=row[2];
                // Insert a new row into the employee table
                PreparedStatement insertStmt ;
                insertStmt= conn.prepareStatement("INSERT INTO employee (name, id, salary) VALUES (?, ?, ?)");
                insertStmt.setString(1,name);
                insertStmt.setString(2,id);
                insertStmt.setString(3,salary);
                insertStmt.executeUpdate();
                
                line=br.readLine();
            }
            // Select all rows from the employee table
            PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM employee");
            ResultSet rs = selectStmt.executeQuery();
            
            // Iterate over the result set and print each row
            while (rs.next()) {
                String na = rs.getString("name");
                int id = rs.getInt("id");
                double salary = rs.getDouble("salary");
                System.out.println("Name: " + na + ", ID: " + id + ", Salary: " + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
