<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Project Staffs</title>

<style type="text/css">
	.format_label{
		font-size: 20px;
		padding: 0.75rem;
		font-weight: bold;
	}
	.format_center{
		text-align: center;
	}
</style>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	   <div class="format_label">Project Staffs</div>
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
	                     <th class="format_center">Project Info</th>
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
		                           
		                           <td class="format_center">
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_STAFF_DASHBOARD %>"/>?id=${project.id}" class="text-muted">
		                           		<i class="fas fa-id-card"></i>
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