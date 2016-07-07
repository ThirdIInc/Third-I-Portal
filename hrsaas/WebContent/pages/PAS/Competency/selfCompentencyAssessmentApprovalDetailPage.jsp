<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<%@page import="org.paradyne.lib.Utility"%>
<head>
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
<s:form action="SelfCompentencyAssessmentApproval" validate="true" id="paraFrm"
	theme="simple">
	<%
	Object[][] headers = (Object[][])request.getAttribute("headerDataObj");
	Object[][] detail = (Object[][])request.getAttribute("detailObj");
	Object[][] approverDataObj = (Object[][]) request.getAttribute("approverDataObj");
	Object[][] titleObj = (Object[][])request.getAttribute("dropDownTitle"); 
	%>
	<table width="100%" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<s:hidden name="approverCompetencyKey" /> 
			<s:hidden name="approverCompetencyID"/> 
		 	<s:hidden name="approverCompetencyAssesmentCode"/> 
			<s:hidden name="approverCompetencyEmployeeCode"/>
			<s:hidden name="competencyAssesmentLevel" />
			<s:hidden name="competencyMasterCode" />
			<s:hidden name="listType" />
			<s:hidden name="source" id="source" />
			<s:hidden name="competencyTitleDropDown" />
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Competency Assessment Approval</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
      		<td>
      		  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
         	 	<tr>
            		<td><b>Employee Details</b>
            			<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">
             			<tr>
              				<td width="15%" height="20" class="formtext"><label  name="employee.id" class = "set"  id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label> :</td>
			  				<td width="30%" height="20"><s:hidden name="empId"></s:hidden><s:hidden name="empToken"></s:hidden><s:property value="empToken"  /></td>
			  				<td width="8%" height="20"></td>
              				<td width="15%" height="20" class="formtext"><label  name="employee" class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>   
              				<td width="30%" height="20"><s:property value="empName" /><s:hidden name="empName"></s:hidden></td> 
            			</tr>
            
            			<tr>
              				<td width="15%" height="20" class="formtext"><label  name="branch" class = "set"  id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
			  				<td width="30%" height="20"><s:property value="empBranch"  /><s:hidden name="empBranch"></s:hidden></td>
              				<td width="8%" height="20"></td>
              				<td width="15%" height="20"><label name="department"  class = "set"  id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>   
              				<td width="30%" height="20"><s:property value="empDepartment" /><s:hidden name="empDepartment"></s:hidden></td> 
            			</tr>
            
             			<tr>
              				<td width="15%" height="20" class="formtext"><label  name="designation" class = "set"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
			  				<td width="30%" height="20"><s:property value="empDesignation"  /><s:hidden name="empDesignation"></s:hidden></td>
              				<td width="8%" height="20"></td>
              				<td width="15%" height="20"><label name="reporting.to"  class = "set"  id="reporting.to" ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label> :</td>   
              				<td width="30%" height="20"><s:property value="empReportingTo" /><s:hidden name="empReportingTo"></s:hidden></td> 
            			</tr>
            			</table>
            		</td>
          		</tr>
       		  </table>
       		</td>
    	</tr>
 
 <s:if test="detailPageFlag">
	<!-- Detail Page BEGINS -->
	
		<tr>
			<td>
			   <table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
				   <tr>
				   	 <td width="10%">
						<b><label name="category" id="category" ondblclick="callShowDiv(this);"><%=label.get("category")%></label></b>  :
					 </td>
				   	 <td width="30%">
						<s:property value="mappedCategory"/>
					 </td>		
					 <td align="right" width="30%">
						<b>Select Competency Title : </b>
					 </td>
				<s:if test="afterFinalizedFlag">	
					 <td width="30%" align="right">
						<select disabled="disabled" name="competencyTitleDrop" id="competencyTitleDrop" onchange="callCompetencyTitleData();">
							<% 
								if (titleObj != null) {
							%>
								<option value="-1">---------- Select Competency Title---------</option>
							<%		
									for(int i = 0; i < titleObj.length; i++) {
							%>
									<option value="<%=String.valueOf(titleObj[i][0])%>"><%=String.valueOf(titleObj[i][1])%></option>
							<% 	
									}
								} 
							%>
						</select>
					 </td>
				</s:if>
				<s:else>
					<td width="30%" align="right">
						<select name="competencyTitleDrop" id="competencyTitleDrop" onchange="callCompetencyTitleData();">
							<% 
								if (titleObj != null) {
							%>
								<option value="-1">---------- Select Competency Title---------</option>
							<%		
									for(int i = 0; i < titleObj.length; i++) {
							%>
									<option value="<%=String.valueOf(titleObj[i][0])%>"><%=String.valueOf(titleObj[i][1])%></option>
							<% 	
									}
								} 
							%>
						</select>
					 </td>
				</s:else>
				  </tr>
			  </table>
			</td>		
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="3">
						<b><s:property value="approverCompetencyTitle"/></b>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
						<s:property value="approverCompetencyDescription"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="3"><b>
						<label name="indicator" id="indicator" ondblclick="callShowDiv(this);"><%=label.get("indicator")%></label>  : 
					</b></td>
				</tr>
				
				<tr>
					<td colspan="3">
						<s:property value="approverCompetencyIndicator"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
				<%
				 Object[][] detailCompetencyDataObj = (Object[][])request.getAttribute("detailCompetencyDataObj");	
					if(detailCompetencyDataObj != null && detailCompetencyDataObj.length > 0) {
						for(int i = 0; i<detailCompetencyDataObj.length; i++) {				
				%>
					<td  class="formth" nowrap="nowrap" colspan="3">
						<b><%= Utility.checkNull(String.valueOf(detailCompetencyDataObj[i][1])) %></b>
					</td>
				<%
					 	 
					}
				%>	
				</tr>
				
				<tr>
				<%
				
						for(int j = 0; j<detailCompetencyDataObj.length; j++) {			
							
				%>
					<td colspan="3" class="sortableTD" <% if(String.valueOf(detailCompetencyDataObj[j][4]).equals("Y")){
					%>
					bgcolor="orange"
					<%
						}
					%>
					> 
						<b><%= Utility.checkNull(String.valueOf(detailCompetencyDataObj[j][2])) %></b>
					</td>
				<%
						}
					}
				%>	
				</tr>
			</table>
			</td>
		</tr>
		
	<!-- Employee Self Rating And Comments BEGINS -->	
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr><td colspan="3"><b>Employee Self Rating & Comments</b></td></tr>
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="20%">
					 <label name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>  : 
					</td>
					<td width="75%">
						<s:textarea name="employeeSelfComments"  cols="70" rows="3" disabled="true"/>
						<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_employeeSelfComments','comments','readonly','','2000');" >
					</td>
				</tr>
				
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="20%">
					 	<label name="rating" id="rating" ondblclick="callShowDiv(this);"><%=label.get("rating")%></label>  : 
					</td>
					<td width="75%">
						<s:select  name="employeeSelfRating" size="" list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" disabled="true"></s:select>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	<!-- Employee Self Rating And Comments ENDS -->	
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="20%">
					 <label name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>  : 
					</td>
					<td width="75%">
						<s:textarea theme="simple" cols="70" rows="3" name="approverSelfComments" />
						<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_approverSelfComments','comments','','','2000');" >
					</td>
				</tr>
				
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="20%">
					 	<label name="rating" id="rating" ondblclick="callShowDiv(this);"><%=label.get("rating")%></label>  : 
					</td>
					<td width="75%">
						<s:select name="approverSelfRating" size="" list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}"></s:select>
					</td>
				</tr>
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="20%">
					<label name="development.plan.required" id="development.plan.required" ondblclick="callShowDiv(this);"><%=label.get("development.plan.required")%></label>
					 	 
					</td>
					<td width="75%">
						<s:select name="devRequired" size="" list="#{'N':'No','Y':'Yes'}" onchange="checkDevPlan()"></s:select>
					</td>
				</tr>
				
				
				
				<tr id="devRequired1">
					<td width="5%">&nbsp;</td>
					<td width="20%">
					<label name="development.plan" id="development.plan" ondblclick="callShowDiv(this);"><%=label.get("development.plan")%></label>
					 	  :<font	color="red"> *</font> 
					</td>
					<td width="75%">
					<s:hidden name="devPlanCode"/>
						<s:textfield name="devPlanName" size="40" readonly="true"/> <img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'SelfCompentencyAssessmentApproval_f9DevPlan.action');">
					</td>
				</tr>
				<tr id="devRequired2">
					<td width="5%">&nbsp;</td>
					<td width="20%">
					<label name="target.date" id="target.date" ondblclick="callShowDiv(this);"><%=label.get("target.date")%></label>
					 	 :<font	color="red"> *</font>
					</td>
					<td width="75%">
						<s:textfield name="devTargetDate" onkeypress="return numbersWithHiphen();" maxlength="10"/> 
						<s:a
								href="javascript:NewCal('paraFrm_devTargetDate','DDMMYYYY');">
								<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a>
					</td>
				</tr>
				<tr id="devRequired3">
					<td width="5%">&nbsp;</td>
					<td width="20%">
					 <label name="devcomments" id="devcomments" ondblclick="callShowDiv(this);"><%=label.get("devcomments")%></label>  : 
					</td>
					<td width="75%">
						<s:textarea theme="simple" cols="70" rows="3" name="approverdevPlanComment" />
						<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_approverdevPlanComment','comments','','','2000');" >
					</td>
				</tr>
				<tr >
					<td width="5%">&nbsp;</td>
					
				</tr>
				<tr>
					<td colspan="3" align="center">
						<s:if test="finalPageFlag">
							<input type="button" value="Finalize" class="save" theme="simple" onclick="callFinalize();">
							<input type="button" name="saveAndPrevious" value="Save & Previous" class="back" theme="simple" onclick="saveAndPreviousFunction();">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">						
						</s:if>
						<s:elseif test="finalPageSignOffFlag">
							<input type="button" value="Sign Off" class="save" theme="simple" onclick="callSignOff();">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">						
						</s:elseif>
						<s:else>
							<input type="button" name="saveAndNext" value="Save & Next" class="next" theme="simple" onclick="saveAndNextFunction();">
							<input type="button" name="saveAndPrevious" value="Save & Previous" class="back" theme="simple" onclick="saveAndPreviousFunction();">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">						
						</s:else>	
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Detail Page ENDS -->
</s:if>	

<!-- PENDING PAGE DETAILS - APPLICANT'S & ALL THE APPROVER'S RATING, COMMENTS BEGINS -->
<s:if test="pendingEmpRatingCommentsPreviousApproverRatingCommentsFlag">
		<tr>
			<td> 
			<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg" id="allRatingCommentsTable">
				<tr>
					<td colspan="5"><b>Competency Details </b></td>
				</tr>
				<%
						try{
						if(headers != null && headers.length > 0) {	
							System.out.println("approverDataObj length************" + headers.length);
							%>
						<tr>
							<%for(int z = 0; z<headers[0].length; z++) {	
					%>
							<td class="formth"  class="sortableTD">
								<%= Utility.checkNull(String.valueOf(headers[0][z])) %>
							</td>
					<% 
				   		}%></tr><%
							}
						}catch(Exception e){
							e.printStackTrace();
						};
					%>		
					
					
				
				<%
					try{
					 if(approverDataObj != null && approverDataObj.length > 0) {
						for(int i = 0; i<approverDataObj.length; i++) {	%>
						<tr>
							<%
								for(int z = 0; z<approverDataObj[0].length; z++) {	
							%>
							
							<%
								if(z==3 || z==8 || (z>8 && z%2==0)) {
							%>
							<td class="sortableTD" align="center">
								<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" value="<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>" disabled="true"/> 
							</td>
							<%		
								} else if(z== 4) {
							%>
							<td class="sortableTD">
								<table>
									<tr>
										<td>
											<s:textarea  name="commentsItr" id = "<%="commentsItr"+i %>" cols="25" rows="2" disabled="true" value="<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>"/>
										</td>
										<td>
											<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
											onclick="javascript:callPopupWindow('<%="commentsItr"+i %>','Comments','readonly','','2000');" >  
										</td>
									</tr>
								</table>
							</td>
							<%		
								} else if(z== 5) {
							%>
							<td class="sortableTD" width="40%" align="center">
								<table width="40%">
									<tr>
										<td>
											<s:textarea name="allRatingCommentsSelfApproverCommentsItr" id = "<%="allRatingCommentsSelfApproverCommentsItr"+i %>" cols="35" rows="2" 
											value="<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>"/>										
										</td>
										<td>
											<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
												onclick="javascript:callPopupWindow('<%="allRatingCommentsSelfApproverCommentsItr"+i %>','Approver Comments','','','2000');" >
										</td>
									</tr>
								</table>
							</td>
							<%		
								} else if(z== 6) {
							%>
							<td class="sortableTD" width="5%" align="center">
								<s:select name="allRatingCommentsSelfApproverRatingItr" list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" value="<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>"/>
							</td>	
							<%		
								} else if(z==(approverDataObj[0].length-1)) {
							%>
								<s:hidden name="allRatingCommentsCompetencyIDItr" value="<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>"/>
							<%		
								} else {
							%>
							<td class="sortableTD">
								<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>
							</td>
							<%		
								}
								}
							%>
				   		</tr>
				   	<%
						}
					 }	
					}catch(Exception e){
						e.printStackTrace();
					};
					%>		
			</table>
			</td>
		</tr>
		 
	    <tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="2" align="center">
						<s:if test="processedListPageFlag">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">
						</s:if>
												  
						
						<s:if test="nonProcessListPageFlag">
							<input type="button" name="saveAndNext" value="Save & Next" class="next" theme="simple" onclick="saveAndNextFromAllRatingCommets();">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">					
						</s:if>	
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</s:if>
<!-- PENDING PAGE DETAILS - APPLICANT'S & ALL THE APPROVER'S RATING, COMMENTS BEGINS -->

<!-- PROCESSED PAGE DETAILS - APPLICANT'S & ALL THE APPROVER'S RATING, COMMENTS BEGINS -->
<s:if test="processedEmpRatingCommentsPreviousApproverRatingCommentsFlag">
		<tr>
			<td> 
			<table width="100%" border="1" cellpadding="1" cellspacing="0" class="formbg" id="allRatingCommentsTable">
				
					<%
						try{
						if(headers != null && headers.length > 0) {		%>
						<tr>
							<%for(int z = 0; z<headers[0].length; z++) {	
								
									
									if(z==0 || z==1 || z==2){		
					%><td class="formth" class="sortableTD"  rowspan="2">
								<%= Utility.checkNull(String.valueOf(headers[0][z]))%>
							</td>
							<% } if(z==4){ %>
							
					<td class="formth" class="sortableTD"  colspan="2" align="center">
								<%= Utility.checkNull(String.valueOf(headers[0][z]))%>
							</td>
					
				   		<%}%>
				   		<% if(z==7 || z==11 ||z==15 ||z==19){ %>
							
					<td class="formth" class="sortableTD"  colspan="4" align="center">
								<%= Utility.checkNull(String.valueOf(headers[0][z])) %>
							</td>
					
				   		<%}%>
				   		
				   		<%
							} %></tr><%}
						}catch(Exception e){
							e.printStackTrace();
						};
					%>	
					
					<%
						try{
						if(detail != null && detail.length > 0) {		%>
						<tr>
							<%for(int z = 0; z<detail[0].length; z++) {	
					%>
					<% if(z==0 || z==1 || z==2) {%>
						
					<%}else{ %>
							<td class="formth"  class="sortableTD">
								<%= Utility.checkNull(String.valueOf(detail[0][z])) %>
							</td>
							<%} %>
					<% 
				   		}%></tr><%
							}
						}catch(Exception e){
							e.printStackTrace();
						};
					%>		
					
						
				
				<%
					try{
					 if(approverDataObj != null && approverDataObj.length > 0) {
						for(int i = 0; i<approverDataObj.length; i++) {	%>
						<tr>
							<%
								for(int z = 0; z<approverDataObj[0].length; z++) {	
							%>
							
							
							<td class="sortableTD">
								<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>
							</td>
							<%		
								}
								
							%>
							<s:hidden name="allRatingCommentsCompetencyIDItr" value="<%= Utility.checkNull(String.valueOf(approverDataObj[i][0])) %>"/>
				   		</tr>
				   	<%}
						} 
					 
					}catch(Exception e){
						e.printStackTrace();
					};
					%>		
			</table>
			</td>
		</tr>
		 
	    <tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="2" align="center">
						<s:if test="processedListPageFlag">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">
						</s:if>
												  
						<s:if test="nonProcessListPageFlag">
							<input type="button" name="saveAndNext" value="Save & Next" class="next" theme="simple" onclick="saveAndNextFromAllRatingCommets();">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">					
						</s:if>	
						
						<s:if test="signOffForFinalizedRecordFlag">
							<input type="button" value="Sign Off" class="save" theme="simple" onclick="callSignOff();">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">					
						</s:if>	
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</s:if>
<!-- PROCESSED PAGE DETAILS - APPLICANT'S & ALL THE APPROVER'S RATING, COMMENTS BEGINS -->




</table>
</s:form>
</body>
<script type="text/javascript">
checkDevPlan();
function checkDevPlan(){

//if(document.getElementById('paraFrm_detailPageFlag').value=='true'){
document.getElementById('devRequired1').style.display='none';
document.getElementById('devRequired2').style.display='none';
document.getElementById('devRequired3').style.display='none';
if(document.getElementById('paraFrm_devRequired').value=='Y'){
document.getElementById('devRequired1').style.display='';
document.getElementById('devRequired2').style.display='';
document.getElementById('devRequired3').style.display='';
}
//}
}

function backToListPage() {
	var listTypeVar = document.getElementById('paraFrm_listType').value;
		if(listTypeVar=="pending"){
			document.getElementById('paraFrm').target = '_self';
			if(document.getElementById('source').value=='mymessages') {
				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
			} else if(document.getElementById('source').value=='myservices') {
				document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
			} else {
				document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_input.action';
			}	
			document.getElementById('paraFrm').submit();
		}
		
		if(listTypeVar=="processed"){
			document.getElementById('paraFrm').target = '_self';
			if(document.getElementById('source').value=='mymessages') {
				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
			} else if(document.getElementById('source').value=='myservices') {
				document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
			} else {
				document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_getProcessedList.action';
			}	
				document.getElementById('paraFrm').submit();
		}
		
		if(listTypeVar=="reAssessed"){
			document.getElementById('paraFrm').target = '_self';
			if(document.getElementById('source').value=='mymessages') {
				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
			} else if(document.getElementById('source').value=='myservices') {
				document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
			} else {
				document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_getReassessedRecords.action';
			}	
			document.getElementById('paraFrm').submit();
		}
} 

function saveAndNextFromAllRatingCommets() {
	try{
		var tbl = document.getElementById('allRatingCommentsTable');
 		var lastRow = tbl.rows.length;
		var iteration = lastRow-2;
		for(var i=0;i<iteration;i++){
			var commentsItrVar = trim(document.getElementById('allRatingCommentsSelfApproverCommentsItr'+i).value);
			var commentsItrVarSize = eval(commentsItrVar.length);
			if(commentsItrVarSize>2000) {
				alert("Only 2000 characters are allowed");
				document.getElementById('allRatingCommentsSelfApproverCommentsItr'+i).focus();
				return false;
			}  
		}
	}catch(e){
		alert("Unable to save records >>>"+e);
		return false;
	}
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_saveAndNextFromAllRatingCommets.action';
		document.getElementById('paraFrm').submit();
}

function saveAndNextFunction() {
		var approverSelfCommentsVar = trim(document.getElementById('paraFrm_approverSelfComments').value);
		var approverSelfCommentsSize = eval(approverSelfCommentsVar.length);
		if(approverSelfCommentsSize>2000) {
				alert("Only 2000 characters are allowed");
				document.getElementById('paraFrm_approverSelfComments').focus();
				return false;
		} else {
		if(document.getElementById('paraFrm_devRequired').value=='Y'){
				if(document.getElementById('paraFrm_devPlanCode').value==''){
				alert('Please select development plan ');
				return false;
				}
				
				if(document.getElementById('paraFrm_devTargetDate').value==''){
				alert('Please enter target date to achieve  ');
				return false;
				}else{
				  if(!validateDate("paraFrm_devTargetDate", "target.date"))
				  return false;
				}
				}
		
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_saveAndNext.action';
			document.getElementById('paraFrm').submit();
		}  
}

function saveAndPreviousFunction() {
		var approverSelfCommentsVar = trim(document.getElementById('paraFrm_approverSelfComments').value);
		var approverSelfCommentsSize = eval(approverSelfCommentsVar.length);
		if(approverSelfCommentsSize>2000) {
				alert("Only 2000 characters are allowed");
				document.getElementById('paraFrm_approverSelfComments').focus();
				return false;
		} else {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_saveAndPrevious.action';
			document.getElementById('paraFrm').submit();
		}
}

function callFinalize(){
	var approverSelfCommentsVar = trim(document.getElementById('paraFrm_approverSelfComments').value);
		var approverSelfCommentsSize = eval(approverSelfCommentsVar.length);
		if(approverSelfCommentsSize>2000) {
				alert("Only 2000 characters are allowed");
				document.getElementById('paraFrm_approverSelfComments').focus();
				return false;
		} else {
			var con = confirm("Do you really want to finalize this application?");	
			if (con) {
					document.getElementById('paraFrm').target = '_self';
					document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_finalizeRecord.action';
					document.getElementById('paraFrm').submit();
			} else {
					return false;
			}
	   }	
}

function callCompetencyTitleData() {
	document.getElementById("paraFrm_competencyTitleDropDown").value = document.getElementById("competencyTitleDrop").value;
	
	var selectedCompetency = document.getElementById("paraFrm_competencyTitleDropDown").value
	if(selectedCompetency == '0-0') {
		return false;
	} else {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='SelfCompentencyAssessmentApproval_setRespectiveCompetencyTileData.action';
		document.getElementById('paraFrm').submit();
	}
}

function callPopupWindow(fieldName,windowName,readFlag,charCount,maxLength) {
	try{
	   	if (window.event){
			window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+windowName+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
	 	}else{
	 		window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+windowName+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=650,height=450,scrollbars=no,resizable=no,top=250,left=350');
	 	}
		document.getElementById('paraFrm').target ="";	
	}catch(e){
		alert("Error >>"+e);
	} 
}

function callSignOff() {
	 var con = confirm("Do you really want to sign-off this application?");
	 if (con) {
	 	document.getElementById('paraFrm').target = '_self';
	 	document.getElementById('paraFrm').action = 'SelfCompentencyAssessmentApproval_signOff.action';
	 	document.getElementById('paraFrm').submit();
	 } else {
	 	return false; 
	 }
}
</script>
