<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<div xmlns="http://www.w3.org/1999/xhtml" class="bs-example">
    <div class="jumbotron">
        <div class="container">
            <h1 class="text-info">Welcome to the IAM System </h1>
            <p>Logged in as: ${sessionScope.userName}</p> <a href="logout">disconnect</a>
            
            <p style="color:${message_color};">${message}</p>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <h4>Identity Creation</h4>

                <p>Here you can create a new user and add him into the database.</p>
                <form action="newIdentity.jsp">
                	<button class="btn" >Create new user</button>
                </form>
            </div>
            <div class="col-xs-6">
                <h4>Identity Search</h4>

                <p>Here you can access the IAM database of users and modify them.</p>
                <form action="searchIdentity.jsp">
                	<button class="btn" >Search the database</button>
                </form>
            </div>
        </div>
    </div>


</div>

</body>
</html>