<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Task Dashboard</title>
    <meta charset="utf-8">
	<style type="text/css">
		.format_label{
			font-size: 20px;
			padding: 0.75rem;
			font-weight: bold;
		}
		.format_center{
			text-align: center !important;
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
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.TASK_DASHBOARD %>" />">Task</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Task Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Task Dashboard</h1>
	            </div>
	            <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.TASK_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add Task
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
						<th class="format_center">No</th>
						<th class="format_center">Id</th>
						<th>Name</th>
	                    <th>Description</th>
	                    <th class="format_center">Start date</th>
	                    <th class="format_center">End date</th>
	                    <th class="format_center">Project Id</th>
	                    <th class="format_center">User Id</th>
	                    <th class="format_center">Status Id</th>
	                    <th class="format_center">Function</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${tasks == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="task" varStatus="loopStatus" items="${tasks}" >
	                 			<tr>
		                           <td class="format_center">${loopStatus.count }</td>
		                           <td class="format_center">${task.id }</td>
		                           <td>${task.name }</td>
		                           <td>${task.description }</td>
		                           <td class="format_center">${task.start_date }</td>
		                           <td class="format_center">${task.end_date }</td>
		                           <td class="format_center">${task.project.id }</td>
		                           <td class="format_center">${task.user.id }</td>
		                           <td class="format_center">
		                           	 <c:if test="${task.status.id == 1}">
		                           	 	<span class="badge badge-primary">${task.status.name }</span>
		                           	 </c:if>
		                           	 <c:if test="${task.status.id == 2}">
		                           	 	<span class="badge badge-danger">${task.status.name }</span>
		                           	 </c:if>
		                           	 <c:if test="${task.status.id == 3}">
		                           	 	<span class="badge badge-secondary">${task.status.name }</span>
		                           	 </c:if>
									</td>
		                           <td class="format_center">
			                           	<a href="<c:url value="<%=UrlConst.TASK_UPDATE %>"/>?id=${task.id}" class="text-muted">
			                           		<i class="material-icons">settings</i>
			                           	</a>
			                           	<a href="<c:url value="<%=UrlConst.TASK_DELETE %>" />?id=${task.id}" class="text-muted" 
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
	
</body>