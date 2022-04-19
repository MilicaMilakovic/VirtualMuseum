<%@page import="net.etfbl.ip.vm.admin.dao.UserDAO"%>
<%@page import="net.etfbl.ip.vm.admin.dto.User"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="userService" class="net.etfbl.ip.vm.admin.dao.UserDAO" scope="session"></jsp:useBean>
<jsp:useBean id="admin" class="net.etfbl.ip.vm.admin.dto.User" scope="session"></jsp:useBean>

<%	

	if(admin.getToken()==null || admin.getId()==0) {
		if(request.getParameter("id") != null && request.getParameter("token") != null) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String token = request.getParameter("token");		
			
			if(!userService.authenticate(id, token))	
				response.sendRedirect("401.jsp");
			
			User user = UserDAO.getUserById(id);
			
		
			System.out.println("user -> " +  user);
			admin.setFirstName(user.getFirstName());
			admin.setLastName(user.getLastName());
			admin.setToken(token);
			admin.setId(id);
			//return;
		} else
			response.sendRedirect("401.jsp");
		//return;
	}

	if(request.getParameter("approve")!= null){
		int id = Integer.parseInt(request.getParameter("approve"));
		userService.approveRegistration(id);
		System.out.println("approve");
	}
	
	if(request.getParameter("reset")!= null){
		int id = Integer.parseInt(request.getParameter("reset"));
		System.out.println("reset");
		System.out.println(id);
		userService.resetPassword(id);
		
	}
	
	if(request.getParameter("deactivate")!= null){
		int id = Integer.parseInt(request.getParameter("deactivate"));
		System.out.println("deactivate");
		System.out.println(id);
		userService.deactivateAccount(id);
		
	}	



%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Users | Virtual Museum</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link rel="icon" href="./assets/logo.png">
   
    <link rel="stylesheet" href="./styles/style.css" />
  </head>
  <body>
    <nav class="navbar navbar-light bg-dark" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;">
        <div class="container" style="margin: 0;">
          <a class="navbar-brand" href="#">
            <img src="assets/logo.png" alt="" width="50" height="50">
          </a>
        </div>
      </nav>

    <div class="container-fluid" style="padding: 30px;">
     

        <h3 style="margin-bottom: 10vh;">
            Virtual Museums | Administrator
            <small class="text-muted">Users management</small>
        </h3>

        <p class="lead">Admin:  <%= admin.getFirstName() + " " + admin.getLastName() %></p>
        <small class="text-muted">TOKEN: <%= admin.getToken() %></small>
          <hr>
        <table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Firstname</th>
                <th scope="col">Lastname</th>
                <th scope="col">Username</th>
                <th scope="col">Password</th>
                <th scope="col">Email</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
                <th scope="col" >Action</th>                
                <!-- <th></th> -->
              </tr>
            </thead>
            <tbody>
            <% for(User user : userService.selectAll()) { %>
              <tr>
                <th scope="row"><%= user.getId() %></th>
                <td><%= user.getFirstName() %></td>
                <td><%= user.getLastName() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getEmail() %></td>                
                <td><%= user.getRole() %></td>
                <td><% if(user.getApproved()) {%>
                	Registration approved<% } else {%>
                	Pending approval<%} if(user.isActive()) {%>
                	, Active
                	<% } else {%>
                	, Deactivated
                	<% } %>
                </td>
                <td>
                  <div class="forms" style="display: flex;">
	                  <form action="users.jsp"  method="post"> <button type="submit" name="approve" value=<%= user.getId()%> class="btn btn-outline-success">Approve </button> </form> 
	                  <form action="users.jsp" method="post"> <button type="submit" name="reset" value=<%= user.getId()%>  class="btn btn-outline-primary">Reset </button> </form> 
	                  <form action="users.jsp" method="post"> <button type="submit" name="deactivate" value=<%= user.getId()%> class="btn btn-outline-danger"> <% if(user.isActive()
	                		  ) { %>
	                		  Deactivate 
	                		  <% } else { %>
	                		  Reactivate 
	                		  <% } %></button> </form> 
                 </div>
                  
                </td>             
              </tr>
              <% } %>              
            
            </tbody>
          </table>

    </div>

    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
