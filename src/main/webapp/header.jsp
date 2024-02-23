
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <style>
        ul {
            list-style-type: none;
            /*width:100%;*/
            margin: 0;
            padding: 10px;
            overflow: hidden;
            background-color: #123969;
        }

        li {
            float: right;
        }
        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color:green;
        }
        .uname{
            margin-top: 14px;
            color: white;
            margin-right: 10px;
        }
        /* FOR LOGO IN THE HOME PAGE (comment)  */
        .headerimg{
        width:80px;
        height:80px;
        
        
        }
    </style>
    <body style="margin: 0;">
        <ul>
            <% if (session.getAttribute("uname") != null) {%>
            <% if (request.getAttribute("image") != null) {%>

            
        			
            <li class="uname"><img src="images/Hemanth.JPG" style="margin-right:5px; height:50px; width=50px;border-radius:50%"><%=session.getAttribute("uname") %>
            <%}else{%>
            <li class="uname"><i class="fa fa-user-circle" aria-hidden="true" style="margin-right: 5px;"></i><%=session.getAttribute("uname")%></li>
             <%}%>
            
            <li><a href="register?logout=yes">Logout</a></li>
            <li><a href="EditForm.jsp">Edit</a></li>
                <% if (session.getAttribute("id").equals("1")) {%>
            <li><a href="DeleteUser.jsp">Delete</a></li>
            <li><a href="search.jsp">Search</a></li>
                <%}%>
                <%} else {%>
            <li><a href="Registration.jsp">Register</a></li>
            <li><a href="login.jsp">Login</a></li>
                <%}%>
            <li><a class="active" href="index.jsp">Home</a></li>
            <li style="float:left"><img class="headerimg" src="images/HSTUDIO.jpeg"></li>
            
            
        </ul>
    </body>
</html>
