 <%@ taglib prefix="s" uri="/struts-tags"%>
<body class="main">

<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/commonCSS.jsp" />

<script type="text/javascript" src="../pages/common/dtree/dtree.js"></script>
<script type="text/javascript"
	src="../pages/common/calendar/calendar/myAjax.js"></script>
<script type="text/javascript"
	src="../pages/common/calendar/yahoo/yahoo.js"></script>
<script type="text/javascript"
	src="../pages/common/calendar/event/event.js"></script>
<script type="text/javascript" src="../pages/common/calendar/dom/dom.js"></script>
<script type="text/javascript"
	src="../pages/common/calendar/calendar/calendar-min.js"></script>

<s:form name="paraFrm" id="paraFrm">

<%
		String[][] twoDimObjArr = null;
		try {
			twoDimObjArr = (String[][]) request
			.getAttribute("twoDimObjArr");
			//System.out.println("twoDimObjArr=======" + twoDimObjArr.length);
	%>
 
	<table border="0" width="150" height="100%">
		<tr>
			<td valign="top">


			<table border="0" width="100%">
				
				<tr>
					<td valign="top">
					<%
					if (twoDimObjArr != null && twoDimObjArr.length > 1) {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="header">Menu List</td>
						</tr>




						<tr>
							<td>
							<div class="layoutcurve" style="width: 100%;">


							<div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="layoutcolor">
								<tr>
									<td align="center" class="layoutcolor" width="100%">
									<table width="97%" border="0" cellpadding="0" cellspacing="0"
										class="borderLR">
										<tr>
											<td class="bg_grad">


											<div>
											<div class='left_menu_bg' align="left">
											<table border="0" width="100%" height="100%">
												<tr>
													<td valign="top">

													<table>
														<%
														if (twoDimObjArr != null && twoDimObjArr.length > 0) {
														%> 
														<%
														}
														%>
													</table>

													<script type="text/javascript">
											d = new dTree('d');
											</script> <%
 if (twoDimObjArr != null && twoDimObjArr.length > 0) {
 %> <script>
											d.add('<%=twoDimObjArr[0][0]%>','-1','<%=twoDimObjArr[0][2]%>');
										</script> <%
 		int i = 0;
 		for (i = 1; i < twoDimObjArr.length; i++) {
 			Object test = twoDimObjArr[i][3];
 			if (twoDimObjArr[i][3].equals("")
 			|| twoDimObjArr[i][3].equals("null")) {
 %> <script type="text/javascript">
												d.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>','','','','../pages/common/dtree/img/folder.png','../pages/common/dtree/img/folder_open.png');
											</script> <%
 } else {
 %> <script type="text/javascript">
											d.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>','<%=twoDimObjArr[i][3] %>','<%=twoDimObjArr[i][4] %>','<%=String.valueOf(twoDimObjArr[i][5]) %>');
										</script> <%
 		}
 		}
 			}
 %> <script type="text/javascript">
											document.write(d);
										</script></td>
												</tr>
											</table>
											</div>
											</div>
											</td>
										</tr>

									</table>

									</td>
								</tr>
							</table>
							</div>

							<div class="layoutcolor" style="width: 100%">&nbsp;</div>

						<div class="r4"></div>
						<div class="r3"></div>
						<div class="r2"></div>
						<div class="r1"></div></div>

							</td>
						</tr>
					</table>


					<%
					} 
					 } catch (Exception e) {
							e.printStackTrace();
						}
					%>
					</td>
				</tr>
			</table>



			</td>
		</tr>
	</table>










</s:form>
<script>
 YAHOO.namespace("example.calendar");
	YAHOO.example.calendar.init = function() {
	YAHOO.example.calendar.cal1 = new YAHOO.widget.Calendar("cal1","cal1Container");
	var mySelectHandler = function(type,args,obj) {
			var selected = args[0];
			var dd = this._toDate(selected[0]);
			
			var dt=selected.toString();
			var sp=selected[0]
			var todate=dt.split(",");
			if(todate[2].length==1){
			todate[2]='0'+todate[2];
			}
			if(todate[1].length==1){
			todate[1]='0'+todate[1];
			}
			var d1=todate[2]+'-'+todate[1]+'-'+todate[0];
			document.getElementById('paraFrm_plannerBean_newtaskDate').value=d1;
			var actionPara='WeekPlanner_getTaskWin.action?returnDateTo='+d1;
	
		};

		var myDeselectHandler = function(type,args,obj) {
			var deselected = args[0];
						
		};

		YAHOO.example.calendar.cal1.selectEvent.subscribe(mySelectHandler, YAHOO.example.calendar.cal1, true);
		YAHOO.example.calendar.cal1.deselectEvent.subscribe(myDeselectHandler, YAHOO.example.calendar.cal1, true);

		YAHOO.example.calendar.cal1.render();
	}

	YAHOO.util.Event.onDOMReady(YAHOO.example.calendar.init);
function callAction(){
	var link=document.getElementById('quickLink').value;
	document.getElementById("paraFrm").target="main";
	document.getElementById("paraFrm").action=link;
	document.getElementById("paraFrm").submit();
	}

function viewTaskList()
{
document.getElementById('paraFrm').target='blank';
document.getElementById('paraFrm').action="WeekPlanner_input.action";
document.getElementById('paraFrm').submit();

}

function newTask()
{
document.getElementById('paraFrm').target='main';
document.getElementById('paraFrm').action="WeekPlanner_newTask.action";
document.getElementById('paraFrm').submit();

}

function calender1(url,formname)
{
var task=document.getElementById('paraFrm_plannerBean_newtask').value;
var date=document.getElementById('paraFrm_plannerBean_newtaskDate').value;
var strtime=document.getElementById('paraFrm_plannerBean_taskStartTime').value;
var endtime=document.getElementById('paraFrm_plannerBean_taskEndTime').value;
if(task=="")
    {
    alert('Please Enter Task');
    return false;
    }
    if(date=="")
    {
    alert('Please Select Date');
    return false;
    }
   retrieveURL(url,formname);
    
  }  
  
 
</script>

<script src="../pages/common/calendar/assets/dpSyntaxHighlighter.js"></script>
<script language="javascript"> 
dp.SyntaxHighlighter.HighlightAll('code'); 

/**********for getting new Window to add new Task* By Lucky***************/

function call_Window(width,height,action) {

	win=window.open('','win','top=260,left=150,width=700,height=350,scrollbars=no,status=yes,resizable=yes');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action=action;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
}
/****************/
</script>