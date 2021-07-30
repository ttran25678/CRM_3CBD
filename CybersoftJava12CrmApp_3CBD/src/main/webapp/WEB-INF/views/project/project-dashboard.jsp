<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Project Dashboard</title>
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
	   <div class="format_label">Manage Project</div>
	</div>
	<!-- End Breadcrumb -->
	
	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
            <table class="table mb-0 thead-border-top-0">
                <thead>
                    <tr>
						<th>No</th>
						<th>Id</th>
						<th>Name</th>
	                    <th>Description</th>
	                    <th>Start date</th>
	                    <th>End date</th>
	                    <th>Owner</th>
	                    <th>#</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${projects == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="project" varStatus="loopStatus" items="${projects}" >
	                 			<tr>
		                           <td>${loopStatus.count }</td>
		                           <td>${project.id }</td>
		                           <td>${project.name }</td>
		                           <td>${project.description }</td>
		                           <td>${project.start_date }</td>
		                           <td>${project.end_date }</td>
		                           
		                           <c:choose>
		                           	<c:when test="${project.owner.id == 0 }"><td></td></c:when>
		                           	<c:otherwise><td>${project.owner.id }</td></c:otherwise>
		                           </c:choose>
		                           
		                           <td>
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_UPDATE %>"/>?id=${project.id}" class="text-muted">
		                           		<i class="material-icons">settings</i>
		                           	</a>
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_DELETE%>" />?id=${project.id}" class="text-muted" 
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