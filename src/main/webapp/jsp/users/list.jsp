<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Users</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Age</th>
					<th>Is Admin</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${users}">
				<tr>
					<td>
						${user.id}
					</td>
					<td>${user.name}</td>
					<td>${user.age}</td>
					<td><c:if test="${user.admin}">Admin</c:if>
						</td>
					<td>
						<spring:url value="/users/${user.id}" var="userUrl" />
						<spring:url value="/users/${user.id}/delete" var="deleteUrl" /> 
						<spring:url value="/users/${user.id}/update" var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	<div class="pagination">
		<!-- paging -->
		<c:if test="${currentPage gt 1}">
			<a href="?page=${currentPage - 1}">Previous</a>
		</c:if>
		<c:forEach begin="1" end="${maxPages}" var="i">
			<c:choose>
				<c:when test="${i!=currentPage}">
					&nbsp;<a href="?page=<c:out value="${i}"/>"><c:out value="${i}"/></a>
				</c:when>
				<c:otherwise>
					<c:out value="${i}"/>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage lt maxPages}">
			<a href="?page=${currentPage + 1}">Next</a>
		</c:if>
	</div>
	</div>


	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>