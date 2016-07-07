<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="EvaluatorPanel" validate="true" id="paraFrm"
	theme="simple">
<s:hidden name="myPage" id="myPage" />		
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
    
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Evaluator Panel</strong></td>
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
					Appraisals</a> | <a href="#" onclick="setAction('P2')">Processed
					Appraisals</a></td>
					<script type="text/javascript">
							function setAction(listType)
							{
								if(listType == "P1") {
									document.getElementById('paraFrm').action = 'EvaluatorPanel_input.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "P2") {
									document.getElementById('paraFrm').action = 'EvaluatorPanel_getProcessedList.action';
									document.getElementById('paraFrm').submit();
								}
							}
						</script>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Tab Options Ends -->
	<!-- Pending Appraisals Starts here -->
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
						Pending for Appraiser
					</td>
				</tr>

				<tr>

					<td colspan="4" class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td class="formth" align="center"  width="5%"><label name="evaluatorpanel.srno" class = "set"  id="evaluatorpanel.srno" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.srno")%></label></td>
							<td class="formth" align="center"  width="20%"><label name="evaluatorpanel.appr.code" class = "set"  id="evaluatorpanel.appr.code" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.appr.code")%></label></td>
							<td class="formth" align="center"  width="25%"><label name="employee" class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="department"  class = "set"  id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="designation"  class = "set"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
							<td class="formth" align="center"  width="10%"><label name="evaluatorpanel.phase"  class = "set"  id="evaluatorpanel.phase" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.phase")%></label></td>
							<td class="formth" align="center"  width="10%"><label name="evaluatorpanel.appr.process"  class = "set"  id="evaluatorpanel.appr.process" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.appr.process")%></label></td>
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
									<td width="20%" align="left" class="sortableTD"><s:property value="apprCode" /><s:hidden name="apprId" ></s:hidden></td>
									<td width="25%" align="left" class="sortableTD"><s:hidden name="empId" /><s:property value="empName" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="empDeptName" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="empDesgName" /></td>
									<td width="10%" align="center" class="sortableTD"><s:hidden name="phaseCode" ></s:hidden><s:property value="phaseName" /></td>
									<td width="10%" align="center" class="sortableTD">
									 <input type="button"  value=" Process"  class="token" 
									onclick = "callHidden('<s:property value="apprId"/>',
									'<s:property value="empId"/>','<s:property value="phaseCode"/>')"/>
									<!--<a href="###" onclick="callHidden('<s:property value="apprId"/>',
									'<s:property value="empId"/>','<s:property value="phaseCode"/>')" >Process</a> -->
									<s:hidden name="happrId"/><s:hidden name="hempId"/><s:hidden name="hphaseCode"/>
								</td>
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
		<!-- Eligible list -->
				 <tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				
				<tr>
					<td colspan="4">
						Pending for Appraisee
					</td>
				</tr>
				<% int j=0; %>
		<tr>
							
							<td class="formth" align="center"  width="5%"><label name="evaluatorpanel.srno" class = "set"  id="evaluatorpanel.srno" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.srno")%></label></td>
							<td class="formth" align="center"  width="20%"><label name="evaluatorpanel.appr.code" class = "set"  id="evaluatorpanel.appr.code" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.appr.code")%></label></td>
							<td class="formth" align="center"  width="25%"><label name="employee" class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="department"  class = "set"  id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="designation"  class = "set"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
						</tr>
						<s:if test="noData1">
							<tr>
								<td width="100%" colspan="8" align="center"><font color="red">No
								Data To Display</font></td>
							</tr>
						</s:if>
								<s:iterator value="eligibleList">
								<tr>
									<td width="5%" align="center" class="sortableTD"><%=++j %></td>
									<td width="20%" align="left" class="sortableTD"><s:property value="apprCode" /><s:hidden name="apprId" ></s:hidden></td>
									<td width="25%" align="left" class="sortableTD"><s:hidden name="empId" /><s:property value="empName" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="empDeptName" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="empDesgName" /></td>
								</tr>

							</s:iterator>
												<%
				} catch (Exception e) {
				}
			%>
				</table></td></tr>
				

    </s:if>
 	<!-- Pending Appraisals Ends here -->
 	<!-- Processed Appraisals Ends here -->
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
						Processed Appraisal List
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
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage1%>', 'EvaluatorPanel_callPage1.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage1%>', 'EvaluatorPanel_callPage1.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo1%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage1%>', 'EvaluatorPanel_callPage1.action');return numbersOnly();" /> of <%=totalPage1%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage1%>', 'EvaluatorPanel_callPage1.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage1%>', 'L', '<%=totalPage1%>', 'EvaluatorPanel_callPage1.action');" >
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
							<td class="formth" align="center"  width="5%"><label name="evaluatorpanel.srno" class = "set"  id="evaluatorpanel.srno" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.srno")%></label></td>
							<td class="formth" align="center"  width="20%"><label name="evaluatorpanel.appr.code" class = "set"  id="evaluatorpanel.appr.code" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.appr.code")%></label></td>
							<td class="formth" align="center"  width="25%"><label name="employee" class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="appr.start.date"  class = "set"  id="appr.start.date" ondblclick="callShowDiv(this);"><%=label.get("appr.start.date")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="appr.end.date"  class = "set"  id="appr.end.date" ondblclick="callShowDiv(this);"><%=label.get("appr.end.date")%></label></td>
							<td class="formth" align="center"  width="10%"><label name="evaluatorpanel.phase"  class = "set"  id="evaluatorpanel.phase" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.phase")%></label></td>
							<td class="formth" align="center"  width="10%"><label name="evaluatorpanel.appr.view"  class = "set"  id="evaluatorpanel.appr.view" ondblclick="callShowDiv(this);"><%=label.get("evaluatorpanel.appr.view")%></label></td> 
							
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
									<td width="20%" align="left" class="sortableTD"><s:property value="apprCode" /><s:hidden name="apprId" ></s:hidden></td>
									<td width="25%" align="left" class="sortableTD"><s:hidden name="empId" /><s:property value="empName" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="apprStartDate" /></td>
									<td width="15%" align="left" class="sortableTD"><s:property value="apprEndDate" /></td>
									<td width="10%" align="center" class="sortableTD"><s:hidden name="phaseCode" ></s:hidden><s:property value="phaseName" /></td>
								 <td width="10%" align="center" class="sortableTD">
									<!--  <a href="###"
									onclick = "callView('<s:property value="apprId"/>',
									'<s:property value="empId"/>','<s:property value="phaseCode"/>')">View</a>-->
									 <input type="button"  value=" View"  class="token" 
									onclick = "callView('<s:property value="apprId"/>',
									'<s:property value="empId"/>','<s:property value="phaseCode"/>')"/>
									
									<s:hidden name="happrId"/><s:hidden name="hempId"/><s:hidden name="hphaseCode"/>
								</td> 
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
 	<!-- Processed Appraisals Ends here -->
 </table>

	
	</s:form>


<script type="text/javascript">

function callHidden(apprCode,empCode,phaseCode){

	document.getElementById('paraFrm_happrId').value=apprCode;
	document.getElementById('paraFrm_hempId').value=empCode;
	document.getElementById('paraFrm_hphaseCode').value=phaseCode;
	
	var apprId = apprCode;
	var empId = empCode;
	var phaseCode =phaseCode;
	
	document.getElementById('paraFrm').target = 'main';
	document.getElementById('paraFrm').action = "ApprFormGeneralInfo_retrieveAppraisalDetails.action?apprId="+apprId+
							"&empId="+empId+"&phaseCode="+phaseCode;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
function callView(apprCode,empCode,phaseCode){

	document.getElementById('paraFrm_happrId').value=apprCode;
	document.getElementById('paraFrm_hempId').value=empCode;
	document.getElementById('paraFrm_hphaseCode').value=phaseCode;
	
	var apprId = apprCode;
	var empId = empCode;
	var phaseCode =phaseCode;
	
	document.getElementById('paraFrm').target = 'main';
	document.getElementById('paraFrm').action = "EvaluatorPanel_callView.action?apprId="+apprId+
							"&empId="+empId+"&phaseCode="+phaseCode;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}

   
   pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
   
</script>
