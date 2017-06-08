<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
	<script type="text/javascript">
    function reloadPage(){
        window.location = "searchIdentity.jsp";
    }
</script>
</head>
<body>

<div xmlns="http://www.w3.org/1999/xhtml" class="bs-example">
    <div class="container">
        <h2 class="text-info">Identity Search </h2>
        <a href="welcome.jsp">&lt;&lt; back</a>

        <h3 class="text-info">Search Criteria</h3>
    </div>

	<form class="form-horizontal" role="form" action="search" method="post">
        <div class="form-group">
        <div class="container">
            <div class="col-sm-6 input-group">
            <span class="input-group-btn">
                    <button class="btn btn-primary" type="submit">Search</button>
            </span>
                <input class="form-control" id="searchString" name="searchString" type="text" placeholder="Type here what you want to search for." />
            </div>
            </div>
        </div>
    </form>


	<!-- SEARCH RESULTS -->
<c:set var="visibility" value="${(empty visibility) ? 'none' : visibility}" />

    <div class="container" style="display:${visibility}">
        <h3 class="text-info">Search Results</h3>

        <form class="form-horizontal" method="post">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Selection</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Birth date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${identities}" var="identity">
					    <tr>      
					        <td> <input name="selection" type="radio" value="${identity.id}" required/></td>
					        <td>${identity.displayname}</td>
					        <td>${identity.email}</td>
					        <td>${identity.birthDate}</td>  
					    </tr>
					</c:forEach>
                   	
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class="col-sm-10 text-left">
                    <button class="btn btn-primary" type="submit" name="action" value="update" onclick="form.action='update';" >Modify</button>
                    <button class="btn btn-danger" data-toggle="confirmation" type="submit" name="action" value="delete" onclick="form.action='delete';">Delete</button>
                    <button class="btn btn-default" type="button" id="cancelButton" onclick="javascript:reloadPage()" >Cancel</button>        
                </div>
            </div>
        </form>
    </div>
</div>



</body>
</html>