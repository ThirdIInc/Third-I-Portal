<%@ taglib prefix="s" uri="/struts-tags"%>




<link rel="stylesheet" type="text/css" href="../pages/common/calendar/assets/calendar.css" />
<script type="text/javascript" src="../pages/common/calendar/calendar/myAjax.js"></script>
<script type="text/javascript" src="../pages/common/calendar/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../pages/common/calendar/event/event.js"></script>
<script type="text/javascript" src="../pages/common/calendar/dom/dom.js"></script>
<script type="text/javascript" src="../pages/common/calendar/calendar/calendar-min.js"></script>

<s:form action="WeekPlanner" method="post" name="CalenderForm"  id="paraFrm" theme="simple" target="main">

<body>
	<table width="100%">
	
		<tr>
			<td>
			<table colspan="2" align="center">
				
				<tr>
					<td colspan="2" ><div id="cal1Container"></div></td> 
					
				</tr>
				
				<tr>
				<s:hidden   name="plannerBean.hiddentask"      />
					<td >Task<font color="red" >*</font>:</td>
					<td><s:textfield theme="simple" name="plannerBean.newtask" size="5"/></td>
				</tr>
				<tr>
					<td >Date<font color="red" >*</font>:</td>
					<td><s:textfield theme="simple" name="plannerBean.newtaskDate" size="5" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><s:submit cssClass="pagebutton"  theme="simple" 
						action="javascript:return false;" value="  Add Task  " onclick="retrieveURL('WeekPlanner_addTask.action?','CalenderForm','plannerBean.newtask','plannerBean.newtaskDate');" />
										
						</td>
				</tr>
				
				
			</table>
			</td>
		</tr>
	</table>
	</s:form>
<script type="text/javascript">
	YAHOO.namespace("example.calendar");
	YAHOO.example.calendar.init = function() {
		YAHOO.example.calendar.cal1 = new YAHOO.widget.Calendar("cal1","cal1Container");
		var mySelectHandler = function(type,args,obj) {
			var selected = args[0];
			alert(selected[0]);
			
			
			document.getElementById('plannerBean.hiddentask').value=selected;
			document.getElementById('plannerBean.newtaskDate').value= this._toDate(selected[0])
			
		};

		var myDeselectHandler = function(type,args,obj) {
			var deselected = args[0];
			
		};

		YAHOO.example.calendar.cal1.selectEvent.subscribe(mySelectHandler, YAHOO.example.calendar.cal1, true);
		YAHOO.example.calendar.cal1.deselectEvent.subscribe(myDeselectHandler, YAHOO.example.calendar.cal1, true);

		YAHOO.example.calendar.cal1.render();
	}

	YAHOO.util.Event.onDOMReady(YAHOO.example.calendar.init);
</script>



<script src="../pages/common/calendar/assets/dpSyntaxHighlighter.js"></script>
<script language="javascript"> 
dp.SyntaxHighlighter.HighlightAll('code'); 
</script>
<script src='../common/calendar/assets/YUIexamples.js'></script>
