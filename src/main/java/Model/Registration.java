package Model;
import java.sql.*;
import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

public class Registration {

    private Connection con;
    HttpSession se;

    public Registration(HttpSession session) {
        try {

            Class.forName("com.mysql.jdbc.Driver"); // load the drivers
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HSTUDIO", "root", "Hemanth@162");
            // connection with data base
            se = session;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public String Registration(String name, String phone, String email, String pw) {
        PreparedStatement ps;
        String status = "";
        try {
            Statement st = null;
            ResultSet rs = null;
            st = con.createStatement();
            rs = st.executeQuery("select * from STUDIO1 where phone='" + phone + "' or email='" + email + "';");
            boolean b = rs.next();
            if (b) 
            {
                status = "existed";
            } 
            else {
                ps = (PreparedStatement) con.prepareStatement("insert into STUDIO1 values(0,?,?,?,?,now())");
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);
                ps.setString(4, pw);
                int a = ps.executeUpdate();
                if (a > 0) 
                {
                    status = "success";
                } 
                else 
                {
                    status = "failure";
                }
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return status;
    }
    public String login(String email, String pass) {
        String status1 = "", id = "";
        String name = "", emails = "";

        try {
            Statement st = null;
            ResultSet rs = null;
            st = con.createStatement();

            rs = st.executeQuery("select * from STUDIO1 where email='" + email + "' and password='" + pass + "';");
            boolean b = rs.next();
            if (b == true) 
            {
                id = rs.getString("id");
                name = rs.getString("name");
                emails = rs.getString("email");
                se.setAttribute("uname", name);
                se.setAttribute("email", emails);
                se.setAttribute("id", id);
                status1 = "success";
            } 
            else 
            {
                status1 = "failure";
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return status1;
    }
    
    // while editing the EXISTED details 
    public student getInfo() {
        Statement st = null;
        ResultSet rs = null;
        student s = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from STUDIO1 where id= '" + se.getAttribute("id") + "'");
            boolean b = rs.next();
            if (b == true) 
            {
                s = new student();
                s.setName(rs.getString("name"));
                s.setphone(rs.getString("phone"));
                s.setemail(rs.getString("email"));
            } 
            else 
            {
                s = null;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return s;
    }

    // WHILE UPDATING THE EXISTIED RECORDS IN  DATABASE
    public String update(String name, String pno, String email) {
        String status = "";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            st.executeUpdate("update STUDIO1 set name='" + name + "',phone='" + pno + "',email='" + email + "' where id= '" + se.getAttribute("id") + "' ");
            se.setAttribute("uname", name);
            status = "success";
        }
        catch (Exception e) 
        {
            status = "failure";
            e.printStackTrace();
        }

        return status;
    }
//    TO DELETE COLUMN NAME FROM THE DATA BASE (11/11/23)
    public String delete(int id)
    {
    	int count=0;
    	Statement st =null;
    	String status =" ";
    	try {
    		st=con.createStatement();
    		count=st.executeUpdate("delete from STUDIO1 where id='"+id+"'" );
    		if(count>0)
    		{
    			status="success";
    		}
    		else 
    		{
    			status="failure";
    			
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return status;
    }
    
    //FOR CHANGING THE COLUMN NAME (DELETE)
    public ArrayList<student> getUserDetails() {
    	 Statement st;
    	 ResultSet rs;
    	 ArrayList<student> al = new ArrayList<student>();
    	 try {
    	 st = con.createStatement();
    	 String qry ="select *,date_format(date,'%b %d, %Y') as date1 from STUDIO1 where ID not in(1);";
    	 
    	 rs = st.executeQuery(qry);
    	 while (rs.next()) {
    	 student p = new student();
    	 p.setId(rs.getString("id"));
    	 p.setName(rs.getString("name"));
    	 p.setemail(rs.getString("email"));
    	 p.setphone(rs.getString("phone"));
    	 p.setdate(rs.getString("date1"));
    	 al.add(p);
    	 }
    	 } catch (Exception e) {
    	 e.printStackTrace();
    	 }
    	 return al;
    }
    
   //TO SEARCH AN DETAILS FROM DATA BASE 
    public ArrayList<student>getUserinfo(String id)
    {
    	Statement st =null;
    	ResultSet rs = null;
    	ArrayList<student>al = new ArrayList<student>();
    	try
    	{
    		st=con.createStatement();
    		String qry= "select * from STUDIO1 where id = '" + id + "';";
//    		String qry="select * from STUDIO1 where id="+id+";";
    		rs=st.executeQuery(qry);
    		while(rs.next())
    		{
    			student p = new student();
    			p.setId(rs.getString("id"));
    			p.setName(rs.getString("name"));
    			p.setemail(rs.getString("email"));
    			p.setphone(rs.getString("phone"));
    			p.setdate(rs.getString("date"));
    			al.add(p);
    		}
    		
    	}

    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return al;
    }
    
    // FORGOT DETAILS
    public String forgot(String email)
    {
    	PreparedStatement ps=null;
    	String status="";
    	String Q1="SELECT * FROM STUDIO1 WHERE EMAIL=?";
    	try
    	{
    		ResultSet rs=null;
    		ps=con.prepareStatement(Q1);
    		ps.setString(1, email);
    		rs=ps.executeQuery();
    		if(rs.next()==true)
    		{
    			String mail=rs.getString("email");
    			se.setAttribute("email", mail);
    			status="success";
    		}
    		else
    		{
    			status="failure";
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return status;
    }
    public String change(String pwrd)
    {
    	PreparedStatement ps=null;
    	String status="";
    	String Q1="SELECT * FROM STUDIO1 WHERE EMAIL=?";
    	String Q2="UPDATE STUDIO1 SET PWD=? WHERE EMAIL=?";
    	try
    	{
    		ResultSet rs=null;
    		String em=(String)se.getAttribute("email");
    		ps=con.prepareStatement(Q1);
    		ps.setString(1, em);
    		rs=ps.executeQuery();
    		if(rs.next()==true)
    		{
    			String pd=rs.getString("pwd");
    			if(pd.equals(pwrd))
    			{
    				status="existed";
    			}
    		else
    		{
    			ps=con.prepareStatement(Q2);
    			ps.setString(1, pwrd);
    			ps.setString(2, em);
    			int up=ps.executeUpdate();
    			if(up>0)
    			{
    				status="success";
    			}
    			else
    			{
    				status="failure";
    			}
    		}
    	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return status;
    }

    	
    	
}
    
    
    

