<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Users management application example</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/users/add" var="urlAddUser" />

<script type="text/javascript">
	function OnSubmitSearchForm() {
		document.searchForm.action = "/users/find/"+document.getElementById('search-user').value;
		return true;
	}
</script>

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">User management</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active">
					</li>
				<li class="active"><a href="${urlAddUser}">Add User</a></li>
			</ul>
		</div>
		<div class="col-sm-3 col-md-3 pull-right">
			<form name="searchForm" class="navbar-form" role="search" onsubmit="return OnSubmitSearchForm();">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search user" id="search-user">
				</div>
			</form>
		</div>
	</div>
</nav>