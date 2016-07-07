<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SelfGoalAssessment" validate="true" id="paraFrm"
	theme="simple">
<s:hidden name="myPage" id="myPage" />		
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
    
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Self Goal Assessment</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	   
		<!-- Tab Options Stars -->
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><a href="#" onclick="setAction('P1')">Pending
					Self Goal Assessment</a> | <a href="#" onclick="setAction('P2')">Processed
					Self Goal Assessment</a></td>
					<script type="text/javascript">
							function setAction(listType)
							{
								if(listType == "P1") {
									document.getElementById('paraFrm').action = 'SelfGoalAssessment_input.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "P2") {
									document.getElementById('paraFrm').action = 'SelfGoalAssessment_getProcessedList.action';
									document.getElementById('paraFrm').submit();
								}
								
								
							}
						</script>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Tab Options Ends -->
	<!-- Pending Assessment Starts here -->
	<s:if test="%{listType=='pending'}">
		 <tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4">
						Pending for Self Assessment
					</td>
				</tr>
				<tr>
					<td colspan="4" class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td class="formth" align="center"  width="5%"><label name="goalassessment.srno" class = "set"  id="goalassessment.srno" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.srno")%></label></td>
							<td class="formth" align="center"  width="25%"><label name="goalassessment.goalname" class = "set"  id="goalassessment.goalname" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalname")%></label></td>
							<td class="formth" align="center"  width="25%"><label name="employee" class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="department"  class = "set"  id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="designation"  class = "set"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
							<td class="formth" align="center"  width="25%"><label name="sch.assessment.date" class = "set"  id="sch.assessment.date" ondblclick="callShowDiv(this);"><%=label.get("sch.assessment.date")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="goalassessment.process"  class = "set"  id="goalassessment.process" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.process")%></label></td>
					</tr>	
						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font color="red">No
								Data To Display</font></td>
							</tr>
						</s:if>

							<%int count=0; %>
							<%! int d=0; %>
							<%

							int i = 0;
								%>

							<s:iterator value="dataList">

								<tr>
									<td width="5%" align="center" class="sortableTD"><%=++i%></td>
									<td width="25%" align="left" class="sortableTD"><s:property value="goalName" /><s:hidden name="goalId" /><s:hidden name="confgoalId" /><s:hidden name="goalAssessmentId" /></td>
									<td width="25%" align="left" class="sortableTD"><s:hidden name="empId" /><s:property value="empName" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="empDept" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="empDesg" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="scheduledAssessmentDateList" /></td>
									<td width="15%" align="center" class="sortableTD">
									<a href="###" onclick="callHidden('<s:property value="goalId"/>',
									'<s:property value="empId"/>','<s:property value="goalAssessmentId"/>','<s:property value="scheduledAssessmentDateList" />','<s:property value="confgoalId"/>')" ><font  color="blue">Process</font></a>
									<s:hidden name="hgoalId"/><s:hidden name="hempId"/><s:hidden name="goalAssessmentId"/></td>
								</tr>

							</s:iterator>
							<%d=i; %>
				</table>
					<%
				} catch (Exception e) {
				}
			%>
					</td>
				</tr>
				</table></td></tr>
    </s:if>
 	<!-- Pending Assessment Ends here -->
 	<!-- Processed Assessment starts here -->
	<s:if test="%{listType=='processed'}">
		 <tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				
				<tr>
					<td colspan="1">
						Processed Self Assessment List
					</td>
					<td colspan="1" align="right">
					<% int totalPage1 = 0; int pageNo1 = 0; %>
					  	<s:if test="noData">
					  	</s:if>
					  	<s:else>
						<td id="ctrlShow" width="30%" align="right" class="">
							
							<b>Page:</b>
							<%	 totalPage1 = (Integer) request.getAttribute("totalPage1");
								 pageNo1 = (Integer) request.getAttribute("pageNo1");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage1%>', 'SelfGoalAssessment_getProcessedList.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage1%>', 'SelfGoalAssessment_getProcessedList.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo1%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage1%>', 'SelfGoalAssessment_getProcessedList.action');return numbersOnly();" /> of <%=totalPage1%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage1%>', 'SelfGoalAssessment_getProcessedList.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage1%>', 'L', '<%=totalPage1%>', 'SelfGoalAssessment_getProcessedList.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>						
						</td>						
						</s:else>											
					</td>
				</tr>
				<tr>
					<td colspan="4" class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td class="formth" align="center"  width="5%"><label name="goalassessment.srno" class = "set"  id="goalassessment.srno" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.srno")%></label></td>
							<td class="formth" align="center"  width="25%"><label name="goalassessment.goalname" class = "set"  id="goalassessment.goalname" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalname")%></label></td>
							<td class="formth" align="center"  width="30%"><label name="employee" class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						 	<td class="formth" align="center"  width="25%"><label name="sch.assessment.date" class = "set"  id="sch.assessment.date" ondblclick="callShowDiv(this);"><%=label.get("sch.assessment.date")%></label></td>
							<td class="formth" align="center"  width="10%"><label name="goalassessment.view"  class = "set"  id="goalassessment.view" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.view")%></label></td>
						</tr>	
						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font color="red">No
								Data To Display</font></td>
							</tr>
						</s:if>

							<%int count1=0; %>
							<%! int d1=0; %>
							<%
							int cnt1= pageNo1*20-20;	
							int i1 = 0;
								%>

							<s:iterator value="processedList">

								<tr>
									<td width="5%" align="center" class="sortableTD"><%=++cnt1%><%++i1;%></td>
									<td width="25%" align="left" class="sortableTD"><s:property value="goalName" /><s:hidden name="goalId" ></s:hidden></td>
									<td width="30%" align="left" class="sortableTD"><s:hidden name="empId" /><s:property value="empName" /></td>
								  	<td width="15%" align="left" class="sortableTD"><s:property value="scheduledAssessmentDateList" /></td>
									<td width="10%" align="center" class="sortableTD">
									<a href="###"
									onclick="callView('<s:property value="goalId"/>',
									'<s:property value="empId"/>','<s:property value="goalAssessmentId"/>','<s:property value="scheduledAssessmentDateList"/>','<s:property value="confgoalId"/>')" >View</a>
									<s:hidden name="hgoalId"/><s:hidden name="hempId"/><s:hidden name="goalAssessmentId"/></td>
								</tr>

							</s:iterator>
							<%d1=i1; %>
				</table>

					<%
				} catch (Exception e) {
				}
			%>
					</td>
				</tr>
				
				</table></td></tr>
    </s:if>
   
    <s:hidden name="scheduledAssessmentDate"></s:hidden>
    <s:hidden name="listType"/>
    <s:hidden name="reportingType"/>
	<s:hidden name="assessLevel"/>   
  
 	<!-- Processed Assessment Ends here -->
 </table>
   <s:hidden name="confgoalId"/> 
</s:form>


<script type="text/javascript">

function callHidden(goalId,empCode,goalAsmtId,assessmentDate,confgoalId){
	document.getElementById('paraFrm_hgoalId').value=goalId;
	document.getElementById('paraFrm_hempId').value=empCode;
	document.getElementById('paraFrm_scheduledAssessmentDate').value=assessmentDate;
	document.getElementById('paraFrm_goalAssessmentId').value=goalAsmtId;
	document.getElementById('paraFrm').target = 'main';
	document.getElementById('paraFrm').action = "SelfGoalAssessment_retrieveGoalDetails.action?goalId="+goalId+"&empId="+empCode+"&asmtId="+goalAsmtId+"&confGoalId="+confgoalId;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
function callView(goalId,empCode,goalAsmtId,assessmentDate,confgoalId){
 
	try
	{
	
	document.getElementById('paraFrm_hgoalId').value=goalId;
	document.getElementById('paraFrm_hempId').value=empCode;
	document.getElementById('paraFrm_goalAssessmentId').value=goalAsmtId;
	document.getElementById('paraFrm_scheduledAssessmentDate').value=assessmentDate;
	document.getElementById('paraFrm').target = 'main';
	document.getElementById('paraFrm').action = "SelfGoalAssessment_retrieveGoalDetails.action?goalId="+goalId+"&empId="+empCode+"&asmtId="+goalAsmtId+"&confGoalId="+confgoalId;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
	}catch(e){  }
	
}
   pgshow();
function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
   
</script>
