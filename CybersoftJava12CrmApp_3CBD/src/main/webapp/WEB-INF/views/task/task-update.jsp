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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add New Task</title>
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
<script type="text/javascript">
	function validateForm(){
		let tname = document.forms["addForm"]["tname"].value;
		let start = document.forms["addForm"]["start_date"].value;
		let end = document.forms["addForm"]["end_date"].value;
		let pid = document.forms["addForm"]["pid"].value;
		let uid = document.forms["addForm"]["uid"].value;
		let sname = document.forms["addForm"]["sname"].value;
		
		if(tname == ""){
			alert("Name not null");
		    return false;
		}else if(start == ""){
			alert("start_date not null");
		    return false;
		}else if(end == ""){
			alert("end_date not null");
		    return false;
		}else if(pid == -1){
			alert("Project Id not null");
		    return false;
		}else if(uid == -1){
			alert("User Id not null");
		    return false;
		}else if(sname == -1){
			alert("Status not null");
		    return false;
		}
	}
</script>
</head>
  <body>
  
    <div class="container rounded bg-white mt-5 mb-5">
        <div class="row">
            <div class="col-md-3 border-right">
                
            </div>
            <div class="col-md-6 border-right">
                <div class="p-3 py-5">
                	<form name="addForm" action="" onsubmit="return validateForm()" method="post">
                		<div class="d-flex justify-content-between align-items-center mb-3">
	                        <h4 class="text-right">Add New Task</h4>
	                    </div>
	                    <div class="row mt-3">
	                        <div class="col-md-12">
	                        	<label class="labels">Id</label>
	                        	<input type="text" class="form-control" placeholder="Auto generate" name="id" value="${task.id }" readonly>
	                        </div>
	                        <div class="col-md-12">
	                        	<label class="labels">Name<span style="color:red;">*</span></label>
	                        	<input type="text" class="form-control" placeholder="Name project" name="tname" value="${task.name }">
	                        </div>
	                        <div class="col-md-12">
	                        	<label class="labels">Description</label>
	                        	<input type="text" class="form-control" placeholder="description project" name="description" value="${task.description }">
	                        	
	                        </div>
	                    </div>
	                    <div class="row mt-3">
	                        <div class="col-md-6">
	                        	<label class="labels">Start date<span style="color:red;">*</span></label>
	                        	<input type="text" class="datepicker_start" placeholder="yyyy-mm-dd" name="start_date" id="start_date" value="${task.start_date }" readonly>
	                        	
	                        </div>
	                        <div class="col-md-6">
	                        	<label class="labels">End date<span style="color:red;">*</span></label>
	                        	<input type="text" class="datepicker_end" placeholder="yyyy-mm-dd" name="end_date" id="end_date" value="${task.end_date }" readonly>
	                        </div>
	                    </div>
	                    <div class="row mt-3">
	                    	<div class="col-md-12">
	                    		<label class="labels">Project Id<span style="color:red;">*</span></label>
	                    		<select class="custom-select" id="inputGroupSelect01" name="pid">
	                    		<option value="${task.project.id }" disabled selected>${task.project.id }</option>
		                    		<%
		                    		try{
		                    			String query = "select * from project";
		                    			Class.forName(DBConst.DRIVER);
		                    			Connection conn = DriverManager.getConnection(DBConst.URL, DBConst.USERNAME, DBConst.PASSWORD);
		                    			Statement stm = conn.createStatement();
		                    			ResultSet rs = stm.executeQuery(query);
		  
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
	                    	
	                    	<div class="col-md-12">
	                    		<label class="labels">User Id<span style="color:red;">*</span></label>
	                    		<select class="custom-select" id="inputGroupSelect01" name="uid">
	                    		<option value="${task.user.id }" disabled selected>${task.user.id }</option>
		                    		<%
		                    		try{
		                    			String query = "select * from user";
		                    			Class.forName(DBConst.DRIVER);
		                    			Connection conn = DriverManager.getConnection(DBConst.URL, DBConst.USERNAME, DBConst.PASSWORD);
		                    			Statement stm = conn.createStatement();
		                    			ResultSet rs = stm.executeQuery(query);
		                   
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
	                    	
	                    	<div class="col-md-12">
	                    		<label class="labels">Status Name<span style="color:red;">*</span></label>
	                    		<select class="custom-select" id="inputGroupSelect01" name="sname">
	                    		<option value="${task.status.id }" disabled selected>${task.status.name }</option>
		                    		<%
		                    		try{
		                    			String query = "select * from status";
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
	                    	<button class="btn btn-primary profile-button" type="submit">Add</button>
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
	    

	    $("#start_date, #end_date").datepicker();
	    $("#end_date").change(function () {
	        var start_date = document.getElementById("start_date").value;
	        var end_date = document.getElementById("end_date").value;
	     
	        if ((Date.parse(end_date) <= Date.parse(start_date))) {
	            alert("End date should be greater than Start date !");
	            document.getElementById("end_date").value = "";
	        }
	    });
	
	</script>
	
  </body>
</html>