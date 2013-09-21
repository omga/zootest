<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel='stylesheet' type='text/css' href='css/main.css'/>
  <script src="http://code.jquery.com/jquery-latest.js">
  </script>
<head>
<jsp:useBean id="ZooMain" scope="application" class="com.test.zoo.ZooMain"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
<header id="header">
  <br/>not ready yet, sorry, ${sessionScope.username}
 </header>
 <br/>
 <br/>

 
 	<section id="middle">

		<div id="container">
	
			<div id="content" class="test">
			
				<strong>Description:</strong>
				<c:forEach items="${ZooMain.animals}" var = "animal">
				       
      			<ul class = "hidden" id= ${animal.name}>
      				<li> Name: ${animal.name}</li>
      				<li> Species: ${animal.species}</li>
        			<li> Age: ${animal.age}</li>
            		<li> Cage: ${animal.cage} </li>
            		<li> Keeper: ${animal.keeper} </li>
        		</ul>
        		</c:forEach>
			</div><!-- #content-->
		</div><!-- #container-->

 		<aside id="sideLeft">
 		<strong>List of zoo animals:</strong><br/>
		<c:forEach items="${ZooMain.animals}" var = "animal">
   			 <div id = "animals-block"	>
     			 <ul> 
     			 	<li >${animal.species}</li>   <a href="#">${animal.name}</a>
     			 	   
     			 	</ul>     

  			</div>
		</c:forEach> 		
 		</aside>
		
	</section>
</div>


	<footer id="footer">
	
		<p>Get xml <a href="XMLServletController">file</a> with all animals </p>
		<p><a href="uploadXML.html">Upload</a> the xml file to add animals to DB</p> 
	</footer><!-- #footer -->
	
	

<script>
	$(document).ready(function(){
		 	$(".hidden").hide();
		   // $(".animal-description").show();
		    $('a').on('click',function(){
		    	var txt = $(this).text();
		    	var prefix="#";
		    	var res=prefix.concat(txt);
		    	console.log(txt);
		    	$(".hidden").hide();
			    $(res).show();
			    });	 
	});



</script>
</body>
</html>