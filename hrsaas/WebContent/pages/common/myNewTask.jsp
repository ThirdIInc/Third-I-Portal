
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/ddaccordion.js"></script>

<STYLE type=text/css>
	a:hover {
		COLOR: #FF0000;
		text-decoration: underline;
	}
	</STYLE>
	
<script type="text/javascript">
ddaccordion.init({
	headerclass: "technology", //Shared CSS class name of headers group
	contentclass: "thelanguage", //Shared CSS class name of contents group
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: [ "openlanguage","closedlanguage"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "<img src='../pages/common/css/default/images/plus.gif' align='left' vspace='3'  hspace='3' /> ", "<img src='../pages/common/css/default/images/minus.gif'align='left' vspace='3' hspace='3'  /> "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "slow" //speed of animation: "fast", "normal", or "slow"
})

</script>
<s:hidden name="plannerBean.taskType"></s:hidden>
<s:hidden name="show" value="%{show}" />
<s:hidden name="modeLength" />


<s:form action="WeekPlanner" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/task24.png"  
						  /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Task </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		 
		<tr>
			<td colspan="3">
			<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<input type="button" name="Add" class="add" value="Add New Task" onclick="return addNewTaskFun();"/><!--
						 <a 
						href="javascript:void(0);" class="contlink" id="hideTask" title="Hide Task"
						style="display: none;" theme="simple" onclick="return callHide();">
					<img src="../pages/common/css/default/images/update1.gif"
						align="absmiddle" border="0" /> Hide Task</a> 
						
						-->
					&nbsp;&nbsp;	
					
					
					<!--<a title="Search" href="javascript:void(0);" class="contlink"
						id="searchTaskBtnId" theme="simple"> <img align="absmiddle"
						src="../pages/common/css/default/images/search2.gif" border="0" />
					Search</a> -->
					
					<!--<a href="javascript:void(0);" class="contlink"
						id="hideSearchTaskBtnId" style="display: none;" theme="simple"
						onclick="return callHideSearch();"> <img align="absmiddle"  
						border="0" src="../pages/common/css/default/images/update1.gif" />
					Hide Search</a>
					
					
					--></td>
					<td >
					<div align="right"><span class="style2"> </span></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="hiddentaskId"
								id="hiddentaskId" />
		<tr>
			<td>
			<div id="showTask" style="display: none;">

			
			</div>
			</td>
		</tr>

		<tr>
			<td>
			<div id="searchTask" >
			<div class="formth" id="4"><b>&nbsp;Search Criteria</b></div>
			<div class="closedlanguage" id="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
				<tr class="sortable">
					<td colspan="2">
					<table width="100%"  border="0" cellpadding="0"
						cellspacing="0" >
						<s:hidden name="myPage" id="myPage" />
						<tr >
							<td class="formtext" width="15%">Start Date from</td>
							<td nowrap="nowrap" width="20%"><s:textfield name="toDate" size="25" onchange="return validateDate('paraFrm_toDate','Start Date');"/>
							<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">

								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							
							<td class="formtext" width="15%" align="center">&nbsp;&nbsp;to</td>
							<td nowrap="nowrap" width="20%"><s:textfield name="searchDate" size="25" onchange="return validateDate('paraFrm_searchDate','Start Date');"/>
							<s:a href="javascript:NewCal('paraFrm_searchDate','DDMMYYYY');">

								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>

							<td height="22" class="formtext" width="20%" nowrap="nowrap">&nbsp;&nbsp;Status :</td>
							<td width="20%" nowrap="nowrap"><s:select list=" #{'O':'Open','C':'Close'}"
								name="plannerBean.searchStatus" /></td>
						</tr>


						<tr>
						<td class="formtext" width="15%">Due Date from</td>
							<td nowrap="nowrap" width="20%"><s:textfield name="endSearchDateStart" size="25" onchange="return validateDate('paraFrm_endSearchDateStart','Due Date');"/>
							<s:a href="javascript:NewCal('paraFrm_endSearchDateStart','DDMMYYYY');">

								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							
							<td class="formtext" width="15%" align="center">&nbsp;&nbsp;to</td>
							<td nowrap="nowrap" width="20%"><s:textfield name="endSearchDateEnd" size="25" onchange="return validateDate('paraFrm_endSearchDateEnd','Due Date');"/>
							<s:a href="javascript:NewCal('paraFrm_endSearchDateEnd','DDMMYYYY');">

								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							
							<td class="formtext" width="15%">&nbsp;&nbsp;Task Project :<font color="red">&nbsp;</font></td>
							<td nowrap="nowrap" width="15%">
							
							
								<s:select name="searchProject"
								cssStyle="width:175" headerKey=""
								onclick="callOtherTaskProject(this.value)"
								headerValue="--- Select Project ---" size="1" list="tmap1" />
								
							
							
								
								</td>

						</tr>
						<tr>
						<td  colspan="" class="formtext" width="15%">Employee :</td>
							<td  colspan="" height="18" width="20%"><s:textfield size="25"
												name="plannerBean.empName" readonly="true" /><s:hidden name="plannerBean.searchEmpCode"></s:hidden><img
												align="absmiddle" style="cursor: pointer;"
												src="../pages/common/css/default/images/search2.gif"
												width="16" class="iconImage" height="15"
												onclick="javascript:callsF9(500,325,'WeekPlanner_f9actionAssignToSearchEmp.action');" /></td>
							<td  colspan="" class="formtext" width="15%">&nbsp;&nbsp;Task Title :</td>
							<td  colspan="" height="18" width="20%"><s:textfield
								size="25" name="searchTaskTitle" /></td>
						</tr>


					</table>
					</td>
				</tr>


				<tr>
					<td colspan="2" class="formtext"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="7" /></td>
				</tr>


				<tr align="center">
					
					<td width="60%" colspan="2"><s:submit value="Search"
						action="WeekPlanner_search" cssClass="token">
					</s:submit>
					<input type="button" value="Reset"
						onclick="resetFun();">
					</td>

				</tr>

			</table>
			</div>
			</div>


			<s:if test="%{islist}">
				<!--<div class="formth" id="2"><b>&nbsp;LIST</b></div>
				-->
				<div class="closedlanguage" id="1">
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
					
					<%
							int totalPage = 0;
							int pageNo = 0;
						%>
						
						<!--	Pagination code  -->
					<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
							  	totalPage = (Integer) request.getAttribute("totalPage");
								pageNo = (Integer) request.getAttribute("pageNo"); 
							%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'WeekPlanner_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'WeekPlanner_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'WeekPlanner_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'WeekPlanner_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'WeekPlanner_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
					
					</tr>
					<tr class="sortable">
						<td>
						<table width="100%" border="0" cellpadding="1"
							cellspacing="4">
							<tr>
								<td width="6%" colspan="1" class="mypageTh" align="center">Sr.No.</td>
								<td width="20%" colspan="1" class="mypageTh" align="center"> Project Name</td>
								<td width="37%" colspan="1" class="mypageTh" align="center">
								Task Title</td>
								<!--<td width="20%" colspan="1" class="mypageTh" align="left">Start
								Date</td>
								-->
								<td width="20%" colspan="1" class="mypageTh" align="center">Due
								Date</td>
								<td width="5%" colspan="1" class="mypageTh" align="center">Assigned</td>
								<td width="5%" colspan="1" class="mypageTh" align="center">Status</td>
								<td width="7%" colspan="1" class="mypageTh" align="center">Edit</td>
							</tr>
							
							<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
												int i = 0;
												int cn = pageNo * 20 - 20;
										%>
							<s:iterator id="plannerBean.showList"
								value="plannerBean.showList">

								<tr ondblclick="javascript:viewFunction('<s:property value="taskId" />',<%=i %>);">
								
									<td colspan="1" class="formtext" align="right"><%=++cn%><%++i;%><s:hidden
										name="taskId" /></td>
									<td  colspan="1"><s:property value="projectName" /> </td>
									<td colspan="1" height="18">
									<input type="hidden" name="taskId" id="taskId<%=i %>" />  <a href="#" class="link" onclick="viewFunction('<s:property value="taskId" />',<%=i %>)"><s:property
										value="title"  /></a><input type="hidden" name="title"
										value="%{'<s:property value="title" />'}" id="title<%=i %>" />
									<input type="hidden" name="startDate" id="startDate<%=i %>" />
									</td>


									<!--
									<td colspan="1" height="18" title="Double Click to view task"><s:property
										value="startDate" /><input type="hidden" name="startDate"
										id="startDate<%=i %>" /></td>
									-->
									<td colspan="1" height="18" ><s:property
										value="endDate" /></td>
									<td colspan="1" height="18" align="left" ><s:property
										value="assigned" /><input type="hidden" name="assigned"
										id="assigned<%=i %>" /></td>
									<td colspan="1" height="18" align="center" ><s:property
										value="iteratorstatus" /><input type="hidden"
										name="iteratorstatus" id="iteratorstatus<%=i %>"
										value="<s:property	value="iteratorstatus" />" /></td>


									<td colspan="1" height="18" align="center"
										style="cursor: pointer;"><img title="Edit Task"
										onclick="viewFunction('<s:property value="taskId" />',<%=i %>)"
										src="../pages/mypage/images/icons/edit.png" /></td>

								</tr>

							</s:iterator>
							<%
										d = i;
										%>
							<%if(i==0){ %>
							
							<tr>
								<td nowrap="nowrap" colspan="6" align="center">No task Defined.</td>
							</tr>
							<%} %>

						</table>
						</td>
					</tr>

				</table>
				</div>
			</s:if> <s:else>

			</s:else></td>
		</tr>
	</table>
	<s:hidden name="isEdit" id="isEdit" />

</s:form>

<script type="text/javascript">

function callPage(id, pageImg, totalPageHid, action) {		
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}

function resetFun(){
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'WeekPlanner_resetSearch.action';
		document.getElementById('paraFrm').submit();
	


}

function callReset()
{
	 
		document.getElementById('paraFrm_plannerBean_taskType').value="";					
		document.getElementById('paraFrm_plannerBean_empName').value="";					
		document.getElementById('paraFrm_plannerBean_empToken').value="";				
		document.getElementById('paraFrm_plannerBean_empCode').value="";				
	document.getElementById('paraFrm_plannerBean_project').value="";					
				document.getElementById('paraFrm_plannerBean_type').value="";			
		document.getElementById('paraFrm_plannerBean_otherTaskProject').value="";					
		document.getElementById('paraFrm_plannerBean_otherTaskType').value="";
		document.getElementById('paraFrm_plannerBean_taskTitleNew').value="";					
		document.getElementById('paraFrm_plannerBean_newStartDate').value="";				
		document.getElementById('paraFrm_plannerBean_startTimeHr').value="00";					
		document.getElementById('paraFrm_plannerBean_StartTimeMin').value="00";					
			document.getElementById('paraFrm_plannerBean_newEndDate').value="";				
			document.getElementById('paraFrm_plannerBean_endTimeHr').value="00";				
		document.getElementById('paraFrm_plannerBean_endTimeMin').value="00";					
			document.getElementById('paraFrm_plannerBean_taskDesc').value="";			
		document.getElementById('paraFrm_plannerBean_status').value="O";			

}


try{
$(document).ready(function(){
  $("#addTask").click(function() {
				$( "#showTask" ).slideToggle(500);
			});
});
$(document).ready(function(){
  $("#searchTaskBtnId").click(function() {
				$( "#searchTask").slideToggle(500);
			});
});

$(document).ready(function(){
  $("#showTask").click(function() {
				$( "#callReset").slideToggle(500);
			});
});

$(document).ready(function(){
	taskType();
});
}catch(e){alert(e);}
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"> 

function viewTitle(id)
{

document.getElementById('hiddentaskId').value=id;
var path='<%=request.getContextPath()%>'
window.open(path+'/common/WeekPlanner_showOnTitle.action?sss='+id,'','width=700,height=300,scrollbars=no,resizable=no,top=50,left=100');
//document.getElementById('paraFrm').action="WeekPlanner_showOnTitle.action";
//document.getElementById('paraFrm').submit();
//document.getElementById('paraFrm').target='main';
}



function viewFunction(id,sno)
{

document.getElementById('isEdit').value='Y';
document.getElementById('hiddentaskId').value=id;
document.getElementById('paraFrm').action="WeekPlanner_showOnView.action";
document.getElementById('paraFrm').submit();

}
function addValidation()
{
if(document.getElementById('paraFrm_plannerBean_taskType').value=="O")
{if(document.getElementById('paraFrm_plannerBean_empCode').value=="")
{
alert('Please Select Employee');
return false;
}
}

	
	var taskProject= document.getElementById('paraFrm_plannerBean_project').value ;
	
	
		if(taskProject=="")
		{
			alert("Please select task project.");
			return false;
		}
		
		
		
	var taskType= document.getElementById('paraFrm_plannerBean_type').value ;
	
	
		if(taskType=="")
		{
			alert("Please select task type.");
			return false;
		}

if(document.getElementById('paraFrm_plannerBean_taskTitleNew').value=="")
{alert('Please Enter Task Title');
return false
}


if(document.getElementById('paraFrm_plannerBean_newStartDate').value=="")
{alert('Please Enter Start Date');
return false
}




if(document.getElementById('paraFrm_plannerBean_startTimeHr').value=="00")
{
if(document.getElementById('paraFrm_plannerBean_StartTimeMin').value=="00"){
alert('Please Enter Start Time');
return false
}
}




if(document.getElementById('paraFrm_plannerBean_newEndDate').value=="")
{alert('Please Enter End Date');
return false
}
//if(document.getElementById('paraFrm_plannerBean_newEndDate').value !=document.getElementById('paraFrm_plannerBean_newStartDate').value)
//{alert('Please Enter End Date Equal To Start Date');
//return false
//}


if(document.getElementById('paraFrm_plannerBean_endTimeHr').value=="00" )
{
if(document.getElementById('paraFrm_plannerBean_endTimeMin').value=="00" )
{
alert('Please Enter End Time');
return false
}
}


if(eval(document.getElementById('paraFrm_plannerBean_endTimeHr').value)== eval(document.getElementById('paraFrm_plannerBean_startTimeHr').value) )
{
if(eval(document.getElementById('paraFrm_plannerBean_endTimeMin').value)< eval(document.getElementById('paraFrm_plannerBean_StartTimeMin').value) )
{
alert(' End Time must be greater than Start time ');
return false
}

}

 else if(eval(document.getElementById('paraFrm_plannerBean_endTimeHr').value)< eval(document.getElementById('paraFrm_plannerBean_startTimeHr').value) )
{

alert(' End Time must be greater than Start time ');
return false
}


return true;
}

function callOtherTaskProject(value){
	
	if(value==34){
		document.getElementById('otherTaskProjectId').style.display = '';
		document.getElementById('taskProjectId').style.display = '';
		document.getElementById('taskProjectId1').style.display = '';
		document.getElementById('taskTypeId').style.display = 'none';
		document.getElementById('taskTypeId1').style.display = 'none';

		if(document.getElementById('paraFrm_plannerBean_type').value==12){
			document.getElementById('taskTypeId').style.display = '';
			document.getElementById('taskTypeId1').style.display = '';
			document.getElementById('blankId').style.display = 'none';
			document.getElementById('blankId1').style.display = 'none';
		}else{
			document.getElementById('blankId').style.display = '';
			document.getElementById('blankId1').style.display = '';
			document.getElementById('taskTypeId').style.display = 'none';
			document.getElementById('taskTypeId1').style.display = 'none';
		}
		
		
	}else{
		document.getElementById('otherTaskProjectId').style.display = 'none';
		document.getElementById('taskProjectId').style.display = 'none';
		document.getElementById('taskProjectId1').style.display = 'none';
		document.getElementById('taskTypeId').style.display = 'none';
		document.getElementById('taskTypeId1').style.display = 'none';
		document.getElementById('blankId').style.display = 'none';
		document.getElementById('blankId1').style.display = 'none';
	}
}

function callOtherTaskType(value){
	if(value==12){
		document.getElementById('otherTaskProjectId').style.display = '';
		document.getElementById('taskProjectId').style.display = 'none';
		document.getElementById('taskProjectId1').style.display = 'none';
		document.getElementById('taskTypeId').style.display = '';
		document.getElementById('taskTypeId1').style.display = '';
		
		if(document.getElementById('paraFrm_plannerBean_project').value==34){
			document.getElementById('taskProjectId').style.display = '';
			document.getElementById('taskProjectId1').style.display = '';
			document.getElementById('blankId').style.display = 'none';
			document.getElementById('blankId1').style.display = 'none';
		}else{
			document.getElementById('blankId').style.display = '';
			document.getElementById('blankId1').style.display = '';
			document.getElementById('taskProjectId').style.display = 'none';
			document.getElementById('taskProjectId1').style.display = 'none';
		}
	}else{
		document.getElementById('otherTaskProjectId').style.display = 'none';
		document.getElementById('taskProjectId').style.display = 'none';
		document.getElementById('taskProjectId1').style.display = 'none';
		document.getElementById('taskTypeId').style.display = 'none';
		document.getElementById('taskTypeId1').style.display = 'none';
		document.getElementById('blankId').style.display = 'none';
		document.getElementById('blankId1').style.display = 'none';
	}
	
	
	
	
	
	
	
	
}
function taskType()
{
	if(document.getElementById('isEdit').value=='Y') {
		document.getElementById('showTask').style.display = '';
	}
	var val=document.getElementById('paraFrm_plannerBean_taskType').value;
	
	if(val=="S") {
	
	document.getElementById('showTask').style.display ='';
 		
  		document.getElementById('paraFrm_plannerBean_empName').value ='';
   		document.getElementById('paraFrm_plannerBean_empCode').value ='';
    } 
}

function changeDivStyle(val){
	if(val==""){
		document.getElementById('showTask').style.display ='none';
	}
}

window.onload()
{
callOtherTaskProject(document.getElementById('paraFrm_plannerBean_project').value)
callOtherTaskType(document.getElementById('paraFrm_plannerBean_type').value)
changeDivStyle(document.getElementById('paraFrm_plannerBean_taskType').value);
}

function addNewTaskFun()
{
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WeekPlanner_addNew.action';
		document.getElementById('paraFrm').submit();
}

function validateDate(fieldName, labName){
	//alert("hi");
	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+labName+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}


 </script>
