<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Manage Staffs</title>

<style type="text/css">
	.format_label{
		font-size: 20px;
		padding: 0.75rem;
		font-weight: bold;
	}

</style>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">Project</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Manage staffs
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Manage staffs</h1>
	            </div>
	            <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add staff to project 
	    			</a>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
		
	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
            <table class="table mb-0 thead-border-top-0">
                <thead>
                    <tr>
						<th>No</th>
						<th>User Id</th>
						<th>User Name</th>
						<th>Join Date</th>
						<th>Description</th>
						<th>Project Id</th>
						<th>Project Name</th>
	                    <th>#</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${projectUsers == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="projectUser" varStatus="loopStatus" items="${projectUsers}" >
	                 			<tr>
		                           <td>${loopStatus.count }</td>
		                           <td>${projectUser.user.id }</td>
		                           <td>${projectUser.user.name }</td>
		                           <td>${projectUser.join_date }</td>
		                           <td>${projectUser.join_description }</td>
		                           <td>${projectUser.project.id }</td>
		                           <td>${projectUser.project.name }</td>
		                           <td>
		                           		<a href="<c:url value="<%=UrlConst.PROJECT_STAFF_UPDATE %>"/>?uid=${projectUser.user.id}&pid=${projectUser.project.id }" class="text-muted">
		                           			<i class="material-icons">settings</i>
			                           	</a>
			                            <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_DELETE %>" />?uid=${projectUser.user.id}&pid=${projectUser.project.id }" class="text-muted" 
			                           		onclick="return confirm('Are you sure you want to delete this item?');">
			                           		<i class="material-icons">delete</i>
			                            </a>
		                           </td>
	                    		</tr>
                 			</c:forEach>
                 		</c:otherwise>
                 	</c:choose>
               	</tbody>
            </table>
		</div>
	</div>
	<!-- END BODY -->
</body>