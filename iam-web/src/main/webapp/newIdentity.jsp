<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Identity</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>
    $(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
        minDate:'-01/01/1900',
        startDate:'-/1900/01/01',
      };
      date_input.datepicker(options);
    })
</script>
</head>
<body>

<div xmlns="http://www.w3.org/1999/xhtml" class="bs-example">

    <div class="container">
        <h2 class="text-info">New Identity Creation </h2>
        <a href="welcome.jsp">&lt;&lt; back</a>
    </div>
    <form class="form-horizontal" role="form" action="creation" method="post">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">User name</label>

            <div class="col-sm-10">
                <input required="true" type="text" class="form-control" id="username" placeholder="User name" name="username" />
            </div>
        </div>
        
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>

            <div class="col-sm-10">
                <input required="true" type="password" class="form-control" id="password" placeholder="Password" name="password" />
            </div>
        </div>
       
        <div class="form-group">
            <label for="lastName" class="col-sm-2 control-label">Email</label>

            <div class="col-sm-10">
                <input required="true" type="email" class="form-control" id="lastName" placeholder="Email" name="email"/>
            </div>
        </div>

        <div class="form-group">
            <label for="date" class="col-sm-2 control-label">Birth Date</label>

            <div class="col-sm-10">
                <input required="true" type="date" class="form-control" id="date" placeholder="Birth Date" name="birthdate" />
            </div>
        </div>

        <div class="form-group">
        	<label for="isAdmin" class="col-sm-2 control-label">Admin user type</label>
        
         	<div class="col-sm-10">
				<input class="checkbox" type="checkbox" value="admin" class="form-control", id="isAdmin" name="isAdmin">
			</div>
        </div>
        
       	<div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>

</div>


</body>
</html>