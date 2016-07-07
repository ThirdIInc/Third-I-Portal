<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<%@page import="org.paradyne.lib.Utility"%>
 
<s:form action="ReviewerCompetencyAssessment" validate="true" id="paraFrm"
	theme="simple">
	<%
	Object[][] headers = (Object[][])request.getAttribute("headerDataObj");
	Object[][] approverDataObj = (Object[][]) request.getAttribute("approverDataObj");
	
	%>
	<table width="100%" class="formbg">
	<s:hidden name="competencyID"/>
	<s:hidden name="competencyAssementID"/> 
	<s:hidden name="competencyKey"/>
	<s:hidden name="competencyEmployeeCode"/>
	<s:hidden name="competencyAssesmentLevel" />
	<s:hidden name="masterCompetencyCode"/>
	<s:hidden name="listType" />
	<s:hidden name="source" id="source" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reviewer Competency Assessment</strong></td>
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
  
	
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
				<tr>
					<td colspan="5"><b>Competency Details</b></td>
				</tr>
				<%
						try{
						if(headers != null && headers.length > 0) {		%>
						<tr>
							<%for(int z = 0; z<headers[0].length; z++) {	
					%>
							<td class="formth" class="sortableTD">
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
								if(z==3 || z==6 || (z>6 && z%2==0)) {
							%>
							<td class="sortableTD">
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
											onclick="javascript:callWindow('<%="commentsItr"+i %>','commentsItr','readonly','','2000');" >  
										</td>
									</tr>
								</table>
							</td>
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
		 
	   <!-- Review Purpose Table - BEGINS --> 
	    <tr>
			
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg" id="allRatingCommentsTable">
				<tr>
					<td colspan="6"><b>Approver Details </b></td>
				</tr>
				<tr>
					<td class="formth" nowrap="nowrap" width="5%">
						<label name="reviewSrNo" id="reviewSrNo" ondblclick="callShowDiv(this);"><%=label.get("reviewSrNo")%></label>
					</td>
					<td class="formth" nowrap="nowrap" >
						<label name="reviewApproverToken" id="reviewApproverToken" ondblclick="callShowDiv(this);"><%=label.get("reviewApproverToken")%></label> 
					</td>
					<td class="formth" width="5%">
						<label name="reviewApproverName" id="reviewApproverName" ondblclick="callShowDiv(this);"><%=label.get("reviewApproverName")%></label>
					</td>
					<td class="formth" nowrap="nowrap" >
						<label name="reviewAgree" id="reviewAgree" ondblclick="callShowDiv(this);"><%=label.get("reviewAgree")%></label>
					</td>
					<td class="formth" nowrap="nowrap" >
						<label name="reviewDisAgree" id="reviewDisAgree" ondblclick="callShowDiv(this);"><%=label.get("reviewDisAgree")%></label>
					</td>
					<td class="formth">
						<label name="reviewEmployeeComments" id="reviewEmployeeComments" ondblclick="callShowDiv(this);"><%=label.get("reviewEmployeeComments")%></label>
					</td>
					<td class="formth">
						<label name="reviewReviewerComments" id="reviewReviewerComments" ondblclick="callShowDiv(this);"><%=label.get("reviewReviewerComments")%></label>
					</td>
				</tr>
				 
				<% int r = 0;%>
				<s:iterator value="reviewEmployeeIterator">
				<tr>
					<td class="sortableTD" width="5%" align="center"><%=++r%></td>
					<input type="hidden" name="reviewAgreeDisAgreeFlag" id="reviewAgreeDisAgreeFlag<%=r %>" 
				  			value="<s:property value="reviewAgreeDisAgreeFlag"/>" />
					<td class="sortableTD" width="25%">
						<s:hidden name="reviewApproverIDItr"/>
						<s:property value="reviewApproverTokenItr" />
					</td>
					<td class="sortableTD" width="45%">
						<s:property	value="reviewApproverNameItr" />
					</td>
					
					<td class="sortableTD" width="5%" align="center">
						<input type="checkbox" name="reviewAgreeItr" id="reviewAgreeItr<%=r %>" 
							value="<s:property value="reviewAgreeItr" />" disabled="disabled" />
					</td>
					
					<td class="sortableTD" width="5%" align="center">
						<input type="checkbox" name="reviewDisAgreeItr" id="reviewDisAgreeItr<%=r %>" 
							value="<s:property value="reviewDisAgreeItr" />"  disabled="disabled" />
					</td>
						 
					 <script>
				 		try {
				 			if(document.getElementById('reviewAgreeDisAgreeFlag<%=r%>').value=='Y') {
								document.getElementById('reviewAgreeItr<%=r%>').checked =true;
							}
							if(document.getElementById('reviewAgreeDisAgreeFlag<%=r%>').value=='N') {
								document.getElementById('reviewDisAgreeItr<%=r%>').checked =true;
							}
						}catch(e) {
							//alert("Error >>"+e);
						}
					</script> 	 
					
					<td class="sortableTD" width="40%" align="left">
						<table width="40%">
							<tr>
								<td>
									<s:textarea theme="simple" cols="30" rows="2" name="reviewEmployeeCommentsItr" id="<%="reviewEmployeeCommentsItr"+r%>" disabled="true"/>
								</td>
								<td>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('<%="reviewEmployeeCommentsItr"+r %>','reviewEmployeeComments','readonly','','2000');" >		
								</td>	
							</tr>
						</table>	
					</td>
					
				<s:if test="%{listType == 'pending'}">
					<td class="sortableTD" width="40%" align="left">
						<table width="40%">
							<tr>
								<td>
									<s:textarea theme="simple" cols="30" rows="2" name="reviewReviewerCommentsItr" id="<%="reviewReviewerCommentsItr"+r%>" />
								</td>
								<td>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('<%="reviewReviewerCommentsItr"+r %>','reviewReviewerComments','','','2000');" >		
								</td>	
							</tr>
						</table>	
					</td>
				</s:if>
				<s:else>
					<td class="sortableTD" width="40%" align="left">
						<table width="40%">
							<tr>
								<td>
									<s:textarea theme="simple" cols="30" rows="2" name="reviewReviewerCommentsItr" id="<%="reviewReviewerCommentsItr"+r%>" disabled="true"/>
								</td>
								<td>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('<%="reviewReviewerCommentsItr"+r %>','reviewReviewerComments','readonly','','2000');" >		
								</td>	
							</tr>
						</table>	
					</td>
				</s:else>	
				 </tr>
				</s:iterator>
				
			<%if(r==0){ %>	
				<tr>
					<td colspan="6" align="center"><font color="red">There is no data to display</font> </td>
				</tr>
			<%} %>		
			</table>
			</td>
		</tr>
	 <!-- Review Purpose Table - ENDS -->
	    
	    <tr>
			<td>
			  <table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
				<tr>
					<s:if test="%{listType == 'pending'}">
						<td colspan="2" align="center">
							<input type="button" name="submitRecord" value="Submit Record" class="save" theme="simple" onclick="callSubmitRecord();">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">					
						</td>
					</s:if>
					<s:else>
						<td colspan="2" align="center">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">					
						</td>
					</s:else>
				</tr>
			  </table>
			</td>
		</tr>	
 
	</table>
</s:form>

<script type="text/javascript">
function backToListPage() {
var listTypeVar = document.getElementById('paraFrm_listType').value;
document.getElementById('paraFrm').target = '_self';
	if(document.getElementById('source').value=='mymessages') {
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
	} else if(document.getElementById('source').value=='myservices') {
			document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
	} else {	
		if(listTypeVar=="pending"){
			document.getElementById('paraFrm').action ='ReviewerCompetencyAssessment_input.action';
		}
	
		if(listTypeVar=="processed"){
			document.getElementById('paraFrm').action ='ReviewerCompetencyAssessment_getProcessedList.action';
		}
	}	
	document.getElementById('paraFrm').submit();
}

function callSubmitRecord() {
	var con = confirm("Do you really want to submit this record?");
	if(con) {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='ReviewerCompetencyAssessment_submitRecord.action';
		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
} 
</script>
