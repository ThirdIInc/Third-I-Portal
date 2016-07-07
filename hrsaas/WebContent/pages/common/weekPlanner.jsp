<%!int rowHeight = 58;  %> 
<%@ page import="java.util.*,java.text.SimpleDateFormat;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>week Planner</title>
<style type="text/css">
html{
	margin:0px;
	padding:0px;
	height:100%;
	width:100%;
}
body{
	margin:0px;
	padding:0px;
	font-family:arial;
	font-size:0.8em;	
	height:100%;
	width:100%;
}

p,h2{
	margin:2px;
}

h1{
	font-size:1.4em;
	margin:2px;
}
h2{
	font-size:1.3em;
}
.weekButton{
	width:80px;
	font-size:0.8em;
	font-family:arial;
}
#weekScheduler_container{
	border:1px solid #D7E8FD;
	width:986px;	
}

.weekScheduler_appointments_day{	/* Column for each day */
	width:130px;
	float:left;
	background-color: #D7E8FD;
	border-right:1px solid #D7E8FD;	
	position:relative;
}
#weekScheduler_top{
	background-image: url(../pages/common/themes/sugar/images/grad1.gif);
	height:20px;
	border-bottom:1px solid #C5DDFC;
}
.calendarContentTime,.spacer{
	background-color:#C5DDFC;
	text-align:center;
	font-family:arial;
	font-size:28px;
	line-height:59px;
	height:59px;	/* Height of hour rows */
	
	border-right:1px solid #D7E8FD;
	width:50px;
}

.weekScheduler_appointmentHour{	/* Small squares for each hour inside the appointment div */
	height:59px; /* Height of hour rows */
	border-bottom:1px solid #ffffff;	
}

.spacer{
	height:20px;
	float:left;
}	

#weekScheduler_hours{
	width:50px;
	float:left;
}
.calendarContentTime{
	border-bottom:1px solid #D7E8FD;

}

#weekScheduler_appointments{	/* Big div for appointments */
	width:917px;
	float:left;
}
.calendarContentTime .content_hour{
	font-size:10px;
	text-decoration:superscript;	
	vertical-align:top;
	line-height:52px;
}
	
#weekScheduler_top{
	position:relative;
	clear:both;
}
#weekScheduler_content{
	clear:both;
	height:540px;
	position:relative;
	overflow:auto;
}
.days div{
	width:130px;
	float:left;
	background-image: url(../pages/common/themes/sugar/images/grad1.gif);
	text-align:center;
	font-family:arial;
	height:20px;
	font-size:0.9em;
	line-height:20px;
	border-right:1px solid #D7E8FD;	
}

.weekScheduler_anAppointment{	/* A new appointment */
	position:absolute;
	background-color:#FFF;
	border:1px solid #000;
	z-index:1000;
	overflow:hidden;


}

.weekScheduler_appointment_header{	/* Appointment header row */
	height:4px;
	background-color:#D7E8FD;
}
.weekScheduler_appointment_headerActive{ /* Appointment header row  - when active*/
	height:4px;
	background-color:#C5DDFC;
}

.weekScheduler_appointment_textarea{	/* Textarea where you edit appointments */
	font-size:0.7em;
	font-family:arial;
}

.weekScheduler_appointment_txt{
	font-size:0.7em;
	font-family:arial;
	padding:2px;
	padding-top:5px;
	overflow:hidden;

}

.weekScheduler_appointment_footer{
	position:absolute;
	bottom:-1px;
	border-top:1px solid #C5DDFC;
	height:4px;
	width:100%;
	font-size:0.8em;
	background-color:#C5DDFC;
}

.weekScheduler_appointment_time{
	position:absolute;
	border:1px solid #000;
	right:1px;
	top:5px;
	width:50px;
	height:12px;
	z-index:100000;
	font-size:0.7em;
	padding:1px;
	background-color:#D7E8FD;
}
.eventIndicator{
	background-color:#D7E8FD;
	z-index:50;
	display:none;
	position:absolute;
}
</style>
<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
<script type="text/javascript">



	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
	
	if ( tMonth < 10) tMonth = "0"+tMonth;
	if ( tDate < 10) tDate = "0"+tDate;
	
	
	
	//var abc = tDate+"-"+tMonth+"-"+tDay.getFullYear();
	var initDateToShow=tDay.getFullYear()+"-"+tMonth+"-"+tDate;   //system date 
	


// It's important that this JS section is above the line below wher dhtmlgoodies-week-planner.js is included
var itemRowHeight= <%=rowHeight %>;//100;
//var initDateToShow = '2008-04-30';//'2008-01-07';	// Initial date to show

</script>
<script src="../pages/common/js/dhtmlgoodies-week-planner.js?random=20060214" type="text/javascript"></script>
</head>
<body>
<h1>Week Planner</h1>

<form id="paraFrm" action="WeekPlanner">
<input type="hidden" name="hidden" id="hidden" value="<%=rowHeight %>"/>
<input type="button" class="weekButton" value="<" onclick="displayPreviousWeek();return false">
<input type="button" class="weekButton" value=">" onclick="displayNextWeek();return false">
<div id="weekScheduler_container">
	<div id="weekScheduler_top">
		<div class="spacer"><span></span></div>
		<div class="days" id="weekScheduler_dayRow">
			<div>Monday <span></span></div>
			<div>Tuesday <span></span></div>
			<div>Wednesday <span></span></div>
			<div>Thursday <span></span></div>
			<div>Friday <span></span></div>
			<div>Saturday <span></span></div>
			<div>Sunday <span></span></div>		
		</div>	
	</div>
	<div id="weekScheduler_content">
		<div id="weekScheduler_hours">
		<%
		
		Date date = new Date();
	 	SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd hh:mm aaa");
	 	String sysDate = formater.format(date);
	 	System.out.println("date"+sysDate);
	 //	out.println("date"+sysDate);
		int startHourOfWeekPlanner = 0;	// Start hour of week planner
		 int endHourOfWeekPlanner = 23;	// End hour of weekplanner.
		 
		 %>
		<!--
	   //	mktime(startHourOfWeekPlanner,0,0,5,5,2006);
		
		--><%
		for(int no=startHourOfWeekPlanner;no<=endHourOfWeekPlanner;no++){
			
			//int hour = no;
			
			// Remove these two lines in case you want to show hours like 08:00 - 23:00
		//	int suffix = date("a", int date);
			String suffix=sysDate.substring(sysDate.length()-2,sysDate.length());
			//out.println("suffix"+suffix);
			String hour=sysDate.substring(sysDate.length()-7,sysDate.length()-6);
			
			
			//out.println("hour"+hour);
			//$hour = date("g",$date);
			
			 // int suffix = 00; // Enable this line in case you want to show hours like 08:00 - 23:00 
			
			  //time = hour
			  
			  
			  if(no>=0&&no<=12)
				  suffix="AM";
			  else
				  suffix="PM";
			  %>
			  
			  <%
			//int time =hour;
			%>
			
			<%
			//date =date + 3600;		
			%>
			<div class="calendarContentTime"><%=no %><span class="content_hour"><%=suffix%></span></div>
			<%			
		}
		%>
		</div>	
		
		<div id="weekScheduler_appointments">
			<%
			for(int no=0;no<7;no++){	// Looping through the days of a week
				%>
				<div class="weekScheduler_appointments_day">
				<%
				for(int no2=startHourOfWeekPlanner;no2<=endHourOfWeekPlanner;no2++){
					%>
					
				<input type="hidden" id="abcd" value="4" name="abcd"/> 
					<div id="weekScheduler_appointment_hour"<%=no %>"_"<%=no2 %>" class="weekScheduler_appointmentHour" ></div>
					<%					
				}				
				%>				
				</div>
				<%
			}
			%>		
		</div>
	</div>
</div>

<!--	DEMO -->

</form>
</body>
</html>