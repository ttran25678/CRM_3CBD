<%@page import="cybersoft.java12.crmapp.util.UrlConst"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="cybersoft.java12.crmapp.util.DBConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <title>User update</title>
<style>
    body {
    background: rgb(99, 39, 120)
    }

    .form-control:focus {
        box-shadow: none;
        border-color: #BA68C8
    }

    .profile-button {
        background: rgb(99, 39, 120);
        box-shadow: none;
        border: none
    }

    .profile-button:hover {
        background: #682773
    }

    .profile-button:focus {
        background: #682773;
        box-shadow: none
    }

    .profile-button:active {
        background: #682773;
        box-shadow: none
    }

    .back:hover {
        color: #682773;
        cursor: pointer
    }

    .labels {
        font-size: 11px
    }

    .add-experience:hover {
        background: #BA68C8;
        color: #fff;
        cursor: pointer;
        border: solid 1px #BA68C8
    }
</style> 

</head>
  <body>
    <div class="container rounded bg-white mt-5 mb-5">
        <div class="row">
            <div class="col-md-3 border-right">
                
            </div>
            <div class="col-md-6 border-right">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">User Update</h4>
                    </div>
                    <form action="" method="post">
                    	<div class="row mt-3">
	                        <div class="col-md-12"><label class="labels">Id</label>
	                        	<input type="text" class="form-control" placeholder="Id project" name="id" value="${user.id }" readonly>
		                    </div>
		                    <div class="col-md-12"><label class="labels">Email</label>
		                        <input type="text" class="form-control" placeholder="Name project" name="email" value="${user.email }">
		                    </div>
		                    <div class="col-md-12"><label class="labels">Name</label>
		                        <input type="text" class="form-control" placeholder="Description project" name="name" value="${user.name }">
		                    </div>
		                    <div class="col-md-12"><label class="labels">Address</label>
		                        <input type="text" class="form-control" placeholder="Name project" name="address" value="${user.address }">
		                    </div>
		                    <div class="col-md-12"><label class="labels">Phone</label>
		                        <input type="text" class="form-control" placeholder="Description project" name="phone" value="${user.phone }">
		                    </div>
	                    </div>
	                   <div class="row mt-3">
		                    	<div class="col-md-12">
		                    		<label class="labels">Role</label>
		                    		<select class="custom-select" id="inputGroupSelect01" name="owner"> 
			                    		<option value="${user.role.id }" disabled selected>${user.role.name }</option>
	
		                    			<%
			                    		try{
			                    			String query = "select * from role";
			                    			Class.forName(DBConst.DRIVER);
			                    			Connection conn = DriverManager.getConnection(DBConst.URL, DBConst.USERNAME, DBConst.PASSWORD);
			                    			Statement stm = conn.createStatement();
			                    			ResultSet rs = stm.executeQuery(query);
			                    			while(rs.next()){
			                    				%>
			                    				<option value="<%=rs.getString("id") %>"><%=rs.getString("name")%> </option>
			                    				<%
			                    			}	
			                    		}catch(Exception e){
			                    			e.printStackTrace();
			                    		}
		                    			%>
		                    		
		                    		</select>
		                    	</div>
		                    </div>
	                    <div class="mt-5 text-center">
	                    	<button class="btn btn-primary profile-button" type="submit">Update</button>
	                    </div>
                    </form>
                    
                </div>
            </div>
            <div class="col-md-3">
                
            </div>
        </div>
    </div>
  </body>
</html>