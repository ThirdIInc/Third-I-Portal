
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Edit event</title>
<style type="text/css">
body{
	margin:5px;
	padding:0px;
	font-family:arial;
	font-size:0.8em;	
	height:100%;
	width:100%;
	background-color:#E2EBED;
}

textarea,input,select{
	width:400px;
}
textarea{
	height:200px;
}

.colorDiv{
	width:15px;
	height:15px;
	margin:1px;
	float:left;
	border:1px solid #000;
	
}
.colorDivSelected{
	width:13px;
	height:13px;
	margin:1px;
	float:left;
	border:2px solid #000;

}

.aButton{
	width:75px;
}
fieldset{
	width:470px;
}
.buttonDiv{
	float:right;
	margin-top:5px;
	padding-right:15px;
}
</style>

<!--


// Saving event
if(isset($_POST['save'])){
	
	// Update your database with the values of the form
	// mysql_connect....
	// mysql_select_db...
	// mysql_query(".....
	
	// Closing window
	
	//?>
	//<script type="text/javascript">
	//self.close();
	//</script>
	//<?
	//exit;
}

-->
<%

// if(isset($_GET['id'])){
//	$db = mysql_connect("host","username","password") or die("Unable to connect to mysql database");
//	mysql_select_db("name_of_database",$db);
//	$res = mysql_query("select * from events where id='".$_GET['id']."'");
//	$inf = mysql_fetch_array($res);
//}else{
//	die("no id sent to this window");
//}

//*/

///* Values for this example only 

//Y/ou will typically pull these variables from a database(look above) instead of setting them fixed as I have done in this example.

//*/

//int inf = array();
//inf ["eventDescription"] = "This is just an example text. This text should be pulled from the database dynamically from the input variable \$_GET['id']";
//inf ["projectID"] = 2;
//$inf ["colorCode"] = "#FFFFFF"; 
//$inf ["contactID"] = 2; 

 // array of colors the user could choose from
 //$colors = array("#CCCCCC","#FFFFFF","#E2EBED");	// array of colors the user could choose from
String id=request.getParameter("id");
 String value=request.getParameter("desc");
 String eventStartDate=request.getParameter("eventStartDate");
 //out.println("id------"+id);
 //out.println("value------"+value);
 if(value==null){
	 value="";
 }
 
String[] colors = {"#CCCCCC","#FFFFFF","#E2EBED"};	
%>
<script type="text/javascript">

function getContents(value)
{
alert('value'+value);
var ta = document.getElementById('eventDescription').value=value;
}

function confirmSave()
{ 	

	if(confirm('Click OK to save')){
		if(window.opener){
			//var id = 500000000;
	    	var id1 = <%=id %>;
	 		var formObj = document.forms[0];	
	 		
			opener.setElement_txt(id1,formObj.eventDescription.value);	// Calling function in week planner - update content
			//if(formObj.color.value.length>0)opener.setElement_color(id,formObj.color.value);	// Calling function in week planner - updating color
		}
		self.close();
		return true;
	}	
	return false;
}

// This function doesn't do anything with the week planner - it only updates color on this page. The confirmSave() function sends the color value back
// to the week planner
var activeColorObj = false;

function selectColor(inputObj,color)
{	if(activeColorObj)activeColorObj.className='colorDiv';
	inputObj.className='colorDivSelected';
	activeColorObj = inputObj;
	document.forms[0].color.value = color; 
	
}

</script>
</head>
<body >



<!-- Example of a form --->
<form action="PHP_SELF" method="Get" >
<input type="hidden" name="id" ">

<input type="hidden" name="color" value="">
<fieldset>
	<legend>Edit event</legend>
	<table border="0" cellpadding="2" cellpadding="0">
		<tr>
			<td>
				<label for="eventDescription">Event:</label>
			</td>
			<td>
				<textarea id="eventDescription" name="eventDescription" ><%=value%>
				 </textarea>
			</td>
		</tr>	
		<tr>
			<td>Project:</td>
			
		</tr>
		<tr>
			<td>Contact:</td>
			
		</tr>
		<tr>
			<td>Color:</td>
			<td><%
			for(int no=0; no < 3;no++){
				%>
	<div class="colorDiv"  onclick="selectColor(this,'<%=colors[no]%>')"  style="background-color:<%=colors[no]%>"><span></span></div>
          <% 
			}
			%>
			</td>
		</tr>
		

	</table>	
</fieldset>
<div class="buttonDiv">
	<!--<input type="button" value="Save"  class="aButton" onclick="return confirmSave();">
	--><input type="button" value="Close" class="aButton" onclick="self.close()">
</div>
		
		
</form>
</body>
</html>