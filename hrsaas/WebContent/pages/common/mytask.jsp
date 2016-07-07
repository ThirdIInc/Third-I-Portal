<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/ddaccordion.js"></script>

<script type="text/javascript">
ddaccordion.init({
	headerclass: "technology", //Shared CSS class name of headers group
	contentclass: "thelanguage", //Shared CSS class name of contents group
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: ["closedlanguage", "openlanguage"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "<img src='../pages/common/css/default/images/plus.gif' align='left' vspace='3'  hspace='3' /> ", "<img src='../pages/common/css/default/images/minus.gif'align='left' vspace='3' hspace='3'  /> "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "slow" //speed of animation: "fast", "normal", or "slow"
})

</script>


<s:form action="WeekPlanner"  id="paraFrm"	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
		<tr>
			  <td colspan="3">
			    <div class="technology">&nbsp;My Tzdfask</div>
			    <div class="thelanguage">
			        <table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				     <tr>
					   <td>
					        	<tr class="sortable">
									   <td>
									      <table width="100%" colspan="4" border="0" cellpadding="0" cellspacing="0">
										   <tr>
											<td width="20%"  colspan="1" class="formtext">Task Type :</td>
											<td width="80%" colspan="3" height="18"><s:hidden name="hiddentaskId" id="hiddentaskId"/> <s:select
												list=" #{'O':'Other','S':'Self'}" name="plannerBean.taskType"
												 onchange="taskType();"/> </td>
											</tr>
										    <tr><td colspan="4">
										   	<div id="divadv"  >
										   	
										 <table width="100%" colspan="4" border="0" cellpadding="0" cellspacing="0">
										    <tr>
										   
											<td width="20%" height="18"  class="formtext">Assigned To :</td>
											<td width="80%" height="18"  ><s:textfield size="30" maxlength="30"
												name="plannerBean.empName" readonly="true" /><s:hidden name="plannerBean.empToken" /> <s:hidden name="plannerBean.empCode" /><img
												src="../pages/common/css/default/images/search2.gif"
												width="16" class="iconImage" height="15"
												onclick="javascript:callsF9(500,325,'WeekPlanner_f9actionAssignTo.action');" /></td>
											
											</tr>
											</table>
										</div>
										</td>
										</tr>
											<tr>
											<td width="20%" height="18" colspan="1" class="formtext">Task Title</td>
											<td width="30%" height="18" colspan="1">
												<s:textfield size="30" 	name="plannerBean.taskTitleNew"  />
												
												</td>
											<td wiidth="20%" colspan="1"></td>
											<td wiidth="30%" colspan="1">hrs &nbsp;&nbsp;&nbsp; mns</td>
											</tr>
											<tr>
											<td width="20%" colspan="1" height="18" class="formtext">Start Date :</td>
											<td width="30%" colspan="1" ><s:textfield name="plannerBean.newStartDate"
												size="25" readonly="true" /> <s:a
												href="javascript:NewCal('paraFrm_plannerBean_newStartDate','DDMMYYYY');">

												<img src="../pages/images/recruitment/Date.gif"
													class="iconImage" height="16" align="absmiddle" width="16">
											</s:a></td>
											<td width="20%" height="8" colspan="1" class="formtext">Start Time :</td> 
																			<td width="30%" colspan="1">
																			
														<s:select
												list=" #{'8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}" name="plannerBean.startTimeHr"
												  />					
																			:
												<s:select
												list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}" name="plannerBean.StartTimeMin"
												  />								
											</td>
																		
										   </tr>
											<tr>
											<td width="20%" colspan="1" height="18" class="formtext">End Date   :</td>
											<td width="30%" colspan="1" ><s:textfield name="plannerBean.newEndDate"
												size="25" readonly="true"/> <s:a
												href="javascript:NewCal('paraFrm_plannerBean_newEndDate','DDMMYYYY');">

												<img src="../pages/images/recruitment/Date.gif"
													class="iconImage" height="16" align="absmiddle" width="16">
											</s:a></td>
                                          	<td width="20%" height="8" colspan="1" class="formtext">End Time :</td>
                                          	<td width="30%" colspan="1">
                                          	<s:select
												list=" #{'8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}" name="plannerBean.endTimeHr"
												  />	
								                                          	 :
								           <s:select
												list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}" name="plannerBean.endTimeMin"
												  />                              	 
											
											</td>
																					
										   </tr>
											<tr>
										   <td width="20%" colspan="1" height="30" class="formtext">Task Description :</td>
										   <td width="60%" colspan="2"><s:textarea rows="6" cols="60"
												name="plannerBean.taskDesc"  />
												
										   </td>
										   <td width="20%" colspan="1">Status &nbsp;<s:select
												list=" #{'O':'Open','C':'Close'}" name="plannerBean.status"
												  /></td>
										   </tr>
										   
										   
										   <tr>
										   <td width="20%" colspan="1" height="30" class="formtext">&nbsp;</td>
										   <td width="60%" colspan="2">
												<s:submit cssClass="pagebutton"  value=" Add Task " action="WeekPlanner_addTask" onclick="return addValidation();"/>
										   </td>
										   <td width="20%" colspan="1">&nbsp;</td>
										   </tr>
										   
										   
										</table>
										</td>
									</tr>
								
								
								
							
								
										
							
						
					</table>
				</div>
				</td>
				</tr>
				</table>
			
			
			
			
			
			
			<table width="100%" border="0" align="right" cellpadding="0"n cellspacing="0">
		<tr>
			  <td colspan="3">
			    <div class="formth"><b>&nbsp;Search Criteria</b></div>
			    <div class="thelanguage">
			        <table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				     <tr>
					   <td>
					        	<tr class="sortable">
									   <td>
									      <table width="100%" colspan="4" border="0" cellpadding="0" cellspacing="0">
										   <tr>
											<td width="30%"  colspan="1" class="formtext">Date :</td>
											<td width="70%" colspan="3" height="18">  <s:textfield name="searchDate"
												size="25" /> <s:a
												href="javascript:NewCal('paraFrm_searchDate','DDMMYYYY');">

												<img src="../pages/images/recruitment/Date.gif"
													class="iconImage" height="16" align="absmiddle" width="16">
											</s:a></td>
											</tr>
										    <tr>
											<td width="20%" height="18" colspan="1" class="formtext">Status :</td>
											<td width="80%" height="18" colspan="3" > </td>
											</tr>
											
											<tr>
										   <td width="20%" colspan="1" height="30" class="formtext">Task Title :</td>
										   <td width="60%" colspan="2"><s:textfield size="30"
												name="taskTitle"  />
												
										   </td>
										   
										   </tr>
										   
											<tr>
										   <td width="20%" colspan="1" height="30" class="formtext">&nbsp;</td>
										   <td width="60%" colspan="2">&nbsp;
												
										   </td>
										   
										   </tr>

										    
											</table>
										</td>
									</tr>
								
								
								<tr>
									<td colspan="2" class="formtext"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>
										
							
							<tr>
										   <td width="20%" colspan="1" height="30" class="formtext">&nbsp;</td>
										   <td width="60%" colspan="2"><s:submit value="Search" > </s:submit>
												
										   </td>
										   
										   </tr>
						
					</table>
				</div>
				</td>
				</tr>
				
				</table>
			
			
			<table width="100%" border="0" align="right" cellpadding="0"n cellspacing="0">
		<tr>
			  <td colspan="3">
			    <div class="formth"><b>&nbsp;LIST</b></div>
			    <div class="thelanguage">
			        <table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				     <tr>
					   <td>
					        	<tr class="sortable">
									   <td>
									      <table width="100%" colspan="4" border="0" cellpadding="0" cellspacing="0">
										   <tr>
											<td width="10%"  colspan="1" class="formtext">Sr No :</td>
											<td width="30%" colspan="1" height="18">Title </td>
											<td width="30%" colspan="1" height="18" >Start Date </td>
											<td width="30%" colspan="1" height="18">Assigned </td>
											<td width="30%" colspan="1" height="18">View </td>
											</tr>
										   <%int i=0; %>
											<s:iterator id="plannerBean.showList" value="plannerBean.showList">
											<tr class="sortable">
									   <td>
									      
										   <tr>
											<td width="10%"  colspan="1" class="formtext"><%=++i %><s:hidden name="taskId" /></td>
											<td width="30%" colspan="1" height="18"><a href="#" onclick="viewTitle('<s:property value="taskId" />')" ><s:property value="title" /></a><input type="hidden" name="title" value="%{'<s:property value="title" />'}" id="title<%=i %>"/> </td>
											<td width="30%" colspan="1" height="18"><s:property value="startDate" /><input type="hidden" name="startDate" id="startDate<%=i %>"/> </td>
											<td width="30%" colspan="1" height="18"><s:property value="assigned" /><input type="hidden" name="assigned" id="assigned<%=i %>"/>  </td>
											<td width="30%" colspan="1" height="18"><a href="#" onclick="viewFunction('<s:property value="taskId" />')" >view </a></td>
											</tr>
											
											</s:iterator>

										    
											</table>
										</td>
									</tr>
								
								
						
					</table>
				</div>
				</td>
				</tr>
				</table>
			
			
			
			
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
taskType();
function viewTitle(id)
{alert('id--'+id);//hiddentaskId
document.getElementById('hiddentaskId').value=id;
document.getElementById('paraFrm').target='_blank';
document.getElementById('paraFrm').action="WeekPlanner_showOnTitle.action";
document.getElementById('paraFrm').submit();
document.getElementById('paraFrm').target='main';
}



function viewFunction(id)
{document.getElementById('hiddentaskId').value=id;
document.getElementById('paraFrm').action="WeekPlanner_showOnView.action";
document.getElementById('paraFrm').submit();
}
function addValidation()
{
if(document.getElementById('paraFrm_plannerBean_taskType').value=="O")
{if(document.getElementById('paraFrm_plannerBean_empCode').value=="")
{
alert('Please Select Assigned To');
return false;
}
}

if(document.getElementById('paraFrm_plannerBean_taskTitleNew').value=="")
{alert('Please Enter Task Title');
return false
}


if(document.getElementById('paraFrm_plannerBean_newStartDate').value=="")
{alert('Please Enter Start Date');
return false
}
if(document.getElementById('paraFrm_plannerBean_newEndDate').value=="")
{alert('Please Enter End Date');
return false
}
return true;
}
function taskType()
{
var val=document.getElementById('paraFrm_plannerBean_taskType').value;
alert(val);
if(val=="S")
 {
 document.getElementById('divadv').style.display ='none';
  document.getElementById('paraFrm_plannerBean_empName').value ='';
   document.getElementById('paraFrm_plannerBean_empCode').value ='';
    }
  else
     { 
     document.getElementById('divadv').style.display='';
     }

}
</script>
