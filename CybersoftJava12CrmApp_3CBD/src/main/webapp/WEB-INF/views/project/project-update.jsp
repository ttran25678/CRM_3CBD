<%@page import="cybersoft.java12.crmapp.util.DBConst"%>
<%@page import="cybersoft.java12.crmapp.model.Project"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="cybersoft.java12.crmapp.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
  <head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 4 DatePicker</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
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
    button{
    	display: initial !important;
    }
    
    #format_a{
	   	position: absolute;
	    margin-top: 30px;
	    height: calc(1.5em + 0.75rem + 2px);
	    width: 72%;
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
                	<form action="" method="post">
                		<div class="d-flex justify-content-between align-items-center mb-3">
	                        <h4 class="text-right">Project Update</h4>
	                    </div>
	                    <div class="row mt-3">
	                        <div class="col-md-12">
	                        	<label class="labels">Id</label>
	                        	<input type="text" class="form-control" placeholder="Id project" name="id" readonly value="${project.id }">
	                        </div>
	                        <div class="col-md-12">
	                        	<label class="labels">Name</label>
	                        	<input type="text" class="form-control" placeholder="Name project" name="name" value="${project.name }">
	                        </div>
	                        <div class="col-md-12">
	                        	<label class="labels">Description</label>
	                        	<input type="text" class="form-control" placeholder="description project" name="description" value="${project.description }">
	                        	
	                        </div>
	                    </div>
	                    <div class="row mt-3">
	                        <div class="col-md-6">
	                        	<label class="labels">Start date</label>
	                        	<input type="text" class="datepicker_start" placeholder="yyyy-mm-dd" name="start_date" value="${project.start_date }" readonly>
	                        	
	                        </div>
	                        <div class="col-md-6">
	                        	<label class="labels">End date</label>
	                        	<input type="text" class="datepicker_end" placeholder="yyyy-mm-dd" name="end_date" value="${project.end_date }" readonly>
	                        </div>
	                    </div>
	                    <div class="row mt-3">
	                    	<div class="col-md-12">
	                    		<select class="custom-select" id="inputGroupSelect01" name="owner">
	                    		<option value="${project.owner.id }" selected>${project.owner.id }</option>
	                    		<%
	                    		try{
	                    			String query = "select * from user";
	                    			Class.forName(DBConst.DRIVER);
	                    			Connection conn = DriverManager.getConnection(DBConst.URL, DBConst.USERNAME, DBConst.PASSWORD);
	                    			Statement stm = conn.createStatement();
	                    			ResultSet rs = stm.executeQuery(query);
	                    			Project pro = new Project();
	                    			
	                    			while(rs.next()){
	                    				%>
	                    				<option value="<%=rs.getString("id") %>"><%=rs.getString("id")%> </option>
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
    
    <script>
	    $('.datepicker_start').datepicker({
	        format: 'yyyy-mm-dd',
	        startDate: '-3d'
	    });
	    
	    $('.datepicker_end').datepicker({
	        format: 'yyyy-mm-dd',
	        startDate: '-3d'
	    });
	</script>
	
  </body>
</html>