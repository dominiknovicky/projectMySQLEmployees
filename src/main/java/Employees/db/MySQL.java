package Employees.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQL {

    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3307/employees";
    private String username="root";
    private String password="";

    public List<String> getEmployees(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "select distinct last_name from employees";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("last_name");
                list.add(name);
            }

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }

    public String getSalary(String name){
        String result = "";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, password);
                String query = "select max(salaries.salary) as sal from salaries inner join employees " +
                        "on salaries.emp_no = employees.emp_no where employees.last_name ='" +name+ "'";
            PreparedStatement p = conn.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            while(rs.next())
                result = rs.getString("sal");
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());

        }
        return result;
    }
}
