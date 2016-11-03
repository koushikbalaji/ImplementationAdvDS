<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Royal Arms Residents Portal</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap.min.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div>
		<h1>Royal Arms Apartment</h1>

    <!-- NAVBAR
    ================================================== -->
    <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top" role="navigation" style="margin-bottom: 0px;">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href=../HomePage/HomePage/HomePage.html>Home</a></li>
                        <li><a href="../LoginRegister/LoginRegister.jsp">Residents</a></li>
                        <li><a href="../FloorPlan/FloorPlan/FloorPlan.jsp">Floor Plan</a></li>
                        <li><a href="../ContactUs/ContactUs.jsp">Contact</a></li>
                    </ul>
                </div>
            </div>
        </nav>
	<%
		session = request.getSession();
		String username  = (String)session.getAttribute("USER");
		String aptNo  = (String)session.getAttribute("APT");
		String selectedDate  = (String)session.getAttribute("selectedDate");
	%>
    </div>
	<h1 align="center">Your payment was successful! Have fun at Royals arms</h1>
	<p align="center">Details:<br/>
	<p><b>Name :</b> <%=username %></p>
	<p><b>Room No:</b><%=aptNo %></p><br/>
	<p><b>Move InDate:</b><%=selectedDate %></p><br/>
    <!-- <a align="center" href="success.html">Save booking details as PDF</a> -->
</body>
</html>