<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

    <title>Royal Arms Residents Portal</title>

    <!-- Bootstrap core CSS -->
    <link href="PaymentGateway.css" rel="stylesheet" />
	  <link href="../HomePage/HomePage/bootstrap.min.css" rel="stylesheet" />
	  <script src="LoginRegister.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div class="container">    
        <div id="paymentbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
         <div class="panel panel-info">
         <%session = request.getSession();
         String selectedDate  = (String)session.getAttribute("selectedDate");%>
         <p><%= selectedDate %></p>
                        <div class="panel-heading">
                            <div class="panel-title">Payment Confirmation</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px"></div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" method = "post" action="<%=request.getContextPath()%>/BookAptController" class="form-horizontal" role="form">
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <%--
                                 <%
                                 session=request.getSession();
                                 String username  = (String)session.getAttribute("USER");
                                 %>
                                 <%=username %>
                                 --%>
                                 
                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label">Name On Card</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="Name" placeholder="Name On Card">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="firstname" class="col-md-3 control-label">Card Number</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="Card Number" placeholder="Card Number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastname" class="col-md-3 control-label">CVV Code</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="CVV Code" placeholder="CVV Code">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Zip Code</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="Zip Code" placeholder="Zip Code">
                                    </div>
                                </div>
													 
								<div>
									   <label for="password">Expiration month</label>                  
									  <select class="selectbox" name="expir-date" id="expire-month" placeholder="Expiration month"
											style="border-radius: 4px;">
											<option value="1">January</option>
											<option value="2">February</option>
											<option value="3">March</option>
											<option value="4">April</option>
											<option value="5">May</option>
											<option value="6">June</option>
											<option value="7">July</option>
											<option value="8">August</option>
											<option value="9">September</option>
											<option value="10">October</option>
											<option value="11">November</option>
											<option value="12">December</option>
										</select>
										
								</div>
								   
								  
								<div >
								<label for="password" align="left">Expiration Year</label> 
										<select class="selectbox" name="expire-date" id="expire-year" placeholder="Expiration year"
											style="border-radius: 4px;">
											<option value="2012">2012</option>
											<option value="2013">2013</option>
											<option value="2014">2014</option>
											<option value="2015">2015</option>
											<option value="2016">2016</option>
											<option value="2017">2017</option>
								</div>
								
									
							<!-- Button -->                                        
								<div>
									<input  id="Pay" type="submit" class="btn btn-info" value="Make Payment"/>
								   
								</div>
                     
                            </form>
                         </div>
                    </div>  
        </div>  
        </div>
        
    
</body>
</html>