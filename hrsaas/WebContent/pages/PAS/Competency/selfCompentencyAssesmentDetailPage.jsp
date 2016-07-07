<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<%@page import="org.paradyne.lib.Utility"%>
 
<s:form action="SelfCompentencyAssesment" validate="true" id="paraFrm"
	theme="simple">
	<%
	Object[][] headers = (Object[][])request.getAttribute("headerDataObj");
	Object[][] detail = (Object[][])request.getAttribute("detailObj");
	Object[][] approverDataObj = (Object[][]) request.getAttribute("approverDataObj");
	
	%>
	<table width="100%" class="formbg">
	<s:hidden name="hiddenCompetencyName"/>
	<s:hidden name="competencyID"/>
	<s:hidden name="competencyAssementID"/> 
	<s:hidden name="competencyKey"/>
	<s:hidden name="masterCompetencyCode"/>
	<s:hidden name="listType" />
	<s:hidden name="source" id="source" />
	<s:hidden name="competencyTitleDropDown" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Self Competency Assessment</strong></td>
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
 
 
 <s:if test="nonFinalPageFlag">
	<!-- Non-Final Page BEGINS -->
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
					 
					 <td width="30%" align="right">
						<select name="competencyTitleDrop" id="competencyTitleDrop" onchange="callCompetencyTitleData();">
							<% 
								Object[][] titleObj = (Object[][])request.getAttribute("dropDownTitle"); 
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
						<b><s:property value="competencyName"/></b>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
						<s:property value="competencyDescription"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="3"><b>
						<label name="indicator" id="indicator" ondblclick="callShowDiv(this);"><%=label.get("indicator")%></label>  : 
					</b></td>
				</tr>
				
				<tr>
					<td colspan="3">
						<s:property value="competencyIndicator"/>
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
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="20%">
					 <label name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label> <font color="red"> * </font> : 
					</td>
					<td width="75%">
						<s:textarea theme="simple" cols="70" rows="3" name="selfComments" />
						<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_selfComments','comments','','','2000');" >
					</td>
				</tr>
				
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="20%">
					 	<label name="rating" id="rating" ondblclick="callShowDiv(this);"><%=label.get("rating")%></label>  : 
					</td>
					<td width="75%">
						<s:select name="selfRating" size="" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}"></s:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="center">
							<s:if test="firstDetailPageFlag">
								<input type="button" name="saveAndNext" value="Save & Next" class="next" theme="simple" onclick="saveAndNextFunction();">
								<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">							
							</s:if>
							
							
							<s:if test="secondDetailPageFlag">
									<input type="button" name="saveAndNext" value="Save & Next" class="next" theme="simple" onclick="saveAndNextFunction();">
									<input type="button" name="saveAndPrevious" value="Save & Previous" class="back" theme="simple" onclick="saveAndPreviousFunction();">
									<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">																
							</s:if>			  
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</s:if>	
<!-- Non-Final Page ENDS -->		
		
<!-- FINAL PAGE BEGINS -->	
<s:if test="finalPageFlag">
		<!-- All Rating And Comments BEGINS-->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg" id="allRatingCommentsTable">
				<tr>
					<td class="formth" nowrap="nowrap" width="5%">
						<label name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label>
					</td>
					<td class="formth" nowrap="nowrap" >
						<label name="competency" id="competency" ondblclick="callShowDiv(this);"><%=label.get("competency")%></label> 
					</td>
					<td class="formth" width="5%">
						<label name="proficiencyLevel" id="proficiencyLevel" ondblclick="callShowDiv(this);"><%=label.get("proficiencyLevel")%></label>
					</td>
					<td class="formth" nowrap="nowrap" >
						<label name="rating" id="rating" ondblclick="callShowDiv(this);"><%=label.get("rating")%></label>
					</td>
					<td class="formth" nowrap="nowrap" >
						<label name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
					</td>
				</tr>
				 
				<% int j = 0;%>
					<s:iterator value="allRatingAndCommentsIterator">
						<tr>
							<td class="sortableTD" width="5%" align="center"><%=++j%></td>
							
							<td class="sortableTD" width="45%">
							<s:hidden name="competencyIDFinalizeItr"/>
							<s:hidden name="competencyNameFinalizeItr" id="<%="competencyNameFinalizeItr"+j %>" />
								<s:property value="competencyNameFinalizeItr" />
							</td>
							<td class="sortableTD" width="5%" align="center">
								<s:property	value="proficiencyLevelFinalizeItr" />
							</td>
							
							<s:if test="finalizedFlag"> 
							<td class="sortableTD" width="5%" align="left">
								<s:select name="ratingFinalizeItr" disabled="true" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" />
							</td>
							<td class="sortableTD" width="40%" align="left">
								<table width="40%">
									<tr>
										<td>
											<s:textarea theme="simple" cols="35" rows="2" name="commentsFinalizeItr" id="<%="commentsFinalizeItr"+j%>" readonly="true"/>
										</td>
										<td>
											<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
												onclick="javascript:callWindow('<%="commentsFinalizeItr"+j %>','comments','readonly','','2000');" >		
										</td>	
									</tr>
								</table>	
							</td>
							</s:if>
							
							<s:else> 
								<td class="sortableTD" width="5%" align="left">
								<s:select name="ratingFinalizeItr" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" />
							</td>
							<td class="sortableTD" width="40%" align="center">
								<table width="40%">
									<tr>
										<td>
											<s:textarea name="commentsFinalizeItr" id="<%="commentsFinalizeItr"+j %>" cols="35" rows="2" />
										</td>
										<td>
											<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
												onclick="javascript:callWindow('<%="commentsFinalizeItr"+j %>','comments','','','2000');" >
										</td>										
									</tr>
								</table>
							</td>
							</s:else>
							
						</tr>
					</s:iterator>
			</table>
			</td>
		</tr>
		<!-- All Rating And Comments ENDS-->
	 
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="2" align="center">
						<s:if test="finalizeSaveAndPreviousPageFlag">
							<input type="button" value="Finalize" class="save" theme="simple" onclick="callFinalize();">
							<input type="button" name="previousPage" value="Save & Previous" class="back" theme="simple" onclick="callPreviousFromFinalPage();">						
						</s:if>
							
						<s:if test="backToListPageFlag">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">					
						</s:if>	
					</td>
				</tr>
			</table>
			</td>
		</tr>
</s:if>
<!-- FINAL PAGE ENDS --> 
	
<!-- PROCESSED PAGE DETAILS- APPLICANT'S & ALL THE APPROVER'S RATING, COMMENTS BEGINS -->
<s:if test="processedPageApproverRatingCommentsFlag">
		<tr>
			<td>
			<table cellpadding="0" cellspacing="0" width="100%" class="formbg">
			<tr>
					<td colspan="5"><b>Competency Details</b></td>
				</tr>
			</table>
			<table width="100%" border="1" cellpadding="2" cellspacing="1" class="formbg">
				
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
								<%= Utility.checkNull(String.valueOf(approverDataObj[i][z])) %>&nbsp;
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
		 
	   <!-- Review Purpose Table - BEGINS --> 
	   <s:if test="signOffEscalationWorkFlowONFLAG"> 
	    <tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg" id="approverDetailTable">
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
						<label name="reviewComments" id="reviewComments" ondblclick="callShowDiv(this);"><%=label.get("reviewComments")%></label>
					</td>
				</tr>
				 
				<% int r = 0;%>
				<s:iterator value="reviewEmployeeIterator">
				<tr>
					<td class="sortableTD" width="5%" align="center"><%=++r%></td>
					<td class="sortableTD" width="25%">
						<s:hidden name="reviewApproverIDItr"/>
						<s:property value="reviewApproverTokenItr" />
					</td>
					<td class="sortableTD" width="45%" align="center">
						<s:property	value="reviewApproverNameItr" />
					</td>
					
				<s:if test="allReadyProcessedAgreeDisAgreeFlag">
					<td class="sortableTD" width="5%" align="center">
						<input type="hidden" name="reviewAgreeDisAgreeFlag" id="reviewAgreeDisAgreeFlag<%=r %>" 
				  			value="<s:property value="reviewAgreeDisAgreeFlag"/>" />
						
						<input type="checkbox" name="reviewAgreeItr" id="reviewAgreeItr<%=r %>" 
							value="<s:property value="reviewAgreeItr" />" disabled="disabled" />
					</td>
					 
					<td class="sortableTD" width="5%" align="center">
						<input type="checkbox" name="reviewDisAgreeItr" id="reviewDisAgreeItr<%=r %>" 
							value="<s:property value="reviewDisAgreeItr" />" disabled="disabled" />
					</td>
						 
					<td class="sortableTD" width="40%" align="left">
						<table width="40%">
							<tr>
								<td>
									<s:textarea theme="simple" cols="35" rows="2" name="reviewCommentsItr" id="<%="reviewCommentsItr"+r%>" disabled="true" />
								</td>
								<td>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('<%="reviewCommentsItr"+r %>','reviewComments','readonly','','2000');" >		
								</td>	
							</tr>
						</table>	
					</td>
				</s:if>
				<s:else>
					<td class="sortableTD" width="5%" align="center">
						<input type="hidden" name="reviewAgreeDisAgreeFlag" id="reviewAgreeDisAgreeFlag<%=r %>" 
				  			value="<s:property value="reviewAgreeDisAgreeFlag"/>" />
						
						<input type="checkbox" name="reviewAgreeItr" id="reviewAgreeItr<%=r %>" 
							value="<s:property value="reviewAgreeItr" />"
							onclick="setAgreeFlag('reviewAgreeItr<%=r %>','reviewDisAgreeItr<%=r %>','reviewAgreeDisAgreeFlag<%=r %>');" />
					</td>
					 
					<td class="sortableTD" width="5%" align="center">
						<input type="checkbox" name="reviewDisAgreeItr" id="reviewDisAgreeItr<%=r %>" 
							value="<s:property value="reviewDisAgreeItr" />" 
							onclick="setDisAgreeFlag('reviewDisAgreeItr<%=r %>','reviewAgreeItr<%=r %>','reviewAgreeDisAgreeFlag<%=r %>');"/>
					</td>
						 
					<td class="sortableTD" width="40%" align="left">
						<table width="40%">
							<tr>
								<td>
									<s:textarea theme="simple" cols="35" rows="2" name="reviewCommentsItr" id="<%="reviewCommentsItr"+r%>"/>
								</td>
								<td>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('<%="reviewCommentsItr"+r %>','reviewComments','','','2000');" >		
								</td>	
							</tr>
						</table>	
					</td>
				</s:else>	
					
					
					<script>
				 		if(document.getElementById('reviewAgreeDisAgreeFlag<%=r%>').value=='Y') {
							document.getElementById('reviewAgreeItr<%=r%>').checked =true;
						}
						if(document.getElementById('reviewAgreeDisAgreeFlag<%=r%>').value=='N') {
							document.getElementById('reviewDisAgreeItr<%=r%>').checked =true;
						}
					</script> 
					
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
	  </s:if>	
	 <!-- Review Purpose Table - ENDS -->
	    
	    <tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="2" align="center">
						<s:if test="signOffWorkflowFlag">
							<input type="button" name="acceptSignOff" value="Accept Sign Off" class="save" theme="simple" onclick="callAcceptRejectSignOff('Accept');">
							<input type="button" name="rejectSignOff" value="Reject Sign Off" class="save" theme="simple" onclick="callAcceptRejectSignOff('Reject');">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">
						</s:if>						
						<s:elseif test="signOffAndEscalationWorkflowFlag">
							<input type="button" name="submitRecords" value="Submit Record" class="save" theme="simple" onclick="callSubmitForReviewer('submit');">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">
						</s:elseif>
						<s:elseif test="afterReAssesedFlag">
							<input type="button" name="reSubmitRecords" value="Re-Submit Record" class="save" theme="simple" onclick="callSubmitForReviewer('reSubmit');">
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">
						</s:elseif>
						<s:else>
							<input type="button" name="backToList" value="Back To List" class="back" theme="simple" onclick="backToListPage();">
						</s:else>	
						
											
					</td>
				</tr>
			</table>
			</td>
		</tr>	
</s:if>
<!-- PROCESSED PAGE DETAILS- APPLICANT'S & ALL THE APPROVER'S RATING, COMMENTS ENDS -->
	</table>
</s:form>

<script type="text/javascript">
function saveAndNextFunction() {
		var selfComments = trim(document.getElementById('paraFrm_selfComments').value);
		var selfCommentsSize = eval(selfComments.length);
		if(selfCommentsSize>2000) {
			alert("Only 2000 characters are allowed");
			return false;
		} else {
			if (selfComments=="") {
				alert("Please enter your comments");
				document.getElementById('paraFrm_selfComments').focus();
				return false;
			} else {
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').action ='SelfCompentencyAssesment_saveAndNext.action';
				document.getElementById('paraFrm').submit();
			}
	  }
}

function saveAndPreviousFunction() {
	var selfComments = trim(document.getElementById('paraFrm_selfComments').value);
		var selfCommentsSize = eval(selfComments.length);
		if(selfCommentsSize>2000) {
			alert("Only 2000 characters are allowed");
			return false;
		} else {
			if (selfComments=="") {
				alert("Please enter your comments");
				document.getElementById('paraFrm_selfComments').focus();
				return false;
			} else {
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').action ='SelfCompentencyAssesment_saveAndPrevious.action';
				document.getElementById('paraFrm').submit();
			}
		}	
}

 
function callFinalize(){
		try{
			var tbl = document.getElementById('allRatingCommentsTable');
 			var lastRow = tbl.rows.length;
			var iteration = lastRow-1;
		 	for(var i=1;i<=iteration;i++){
		 		var competencyNameFinalizeItrVar = trim(document.getElementById('competencyNameFinalizeItr'+i).value);
		 		var commentsFinalizeItrVar = trim(document.getElementById('commentsFinalizeItr'+i).value);
				var commentsFinalizeItrVarSize = eval(commentsFinalizeItrVar.length);
				if(commentsFinalizeItrVarSize>2000) {
					alert("Only 2000 characters are allowed");
					document.getElementById('commentsFinalizeItr'+i).focus();
					return false;
				} else {
					if(commentsFinalizeItrVar == ""){
		 				alert("Please enter your comments for "+ competencyNameFinalizeItrVar);
		 				document.getElementById('commentsFinalizeItr'+i).focus();
		 				return false;
		 			}
			    }
		 	}
		}catch(e){
			alert("Unable to finalize Competency Assessment >>>"+e);
		}


		var con = confirm("Do you really want to finalize this application?");	
		if (con) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='SelfCompentencyAssesment_finalizeRecord.action';
			document.getElementById('paraFrm').submit();
		} else {
			return false;
		}
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
				document.getElementById('paraFrm').action ='SelfCompentencyAssesment_input.action';
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
				document.getElementById('paraFrm').action ='SelfCompentencyAssesment_getProcessedList.action';
			}	
			document.getElementById('paraFrm').submit();
		}
}

function callPreviousFromFinalPage() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='SelfCompentencyAssesment_previousFromFinalPage.action';
		document.getElementById('paraFrm').submit();
}

function callCompetencyTitleData() {
	document.getElementById("paraFrm_competencyTitleDropDown").value = document.getElementById("competencyTitleDrop").value;
	
	var selectedCompetency = document.getElementById("paraFrm_competencyTitleDropDown").value
	if(selectedCompetency == '0-0') {
		return false;
	} else {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='SelfCompentencyAssesment_setRespectiveCompetencyTileData.action';
		document.getElementById('paraFrm').submit();
	}
}

function setAgreeFlag(agreeID, disAgreeID, agreeDisAgreeFlag) {
	document.getElementById(disAgreeID).checked="";
	if(document.getElementById(agreeID).checked) {
		document.getElementById(agreeDisAgreeFlag).value="Y";
	} else {
		document.getElementById(agreeDisAgreeFlag).value="";
	}
}

function setDisAgreeFlag(disAgreeID, agreeID, agreeDisAgreeFlag)
{
	document.getElementById(agreeID).checked="";
	if(document.getElementById(disAgreeID).checked) {
		document.getElementById(agreeDisAgreeFlag).value="N";
	} else {
		document.getElementById(agreeDisAgreeFlag).value="";
	}
}

function callAcceptRejectSignOff(status) {
	var con = "";
	if (status == 'Accept') {
		con = confirm("Do you really want to accept signoff?");
	} else {
		con = confirm("Do you really want to reject signoff?"); 
	}
	
	if (con) {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'SelfCompentencyAssesment_acceptRejectSignOff.action?status='+status;
		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
}


function callSubmitForReviewer(buttonName) {
	try{
		var tbl = document.getElementById('approverDetailTable');
 		var lastRow = tbl.rows.length;
		var iteration = lastRow-2;
		var flag=true;
		for(var i=1;i<=iteration;i++){
			var disAgree = document.getElementById('reviewDisAgreeItr'+i).checked;
			if(disAgree) {
				var comments = trim(document.getElementById('reviewCommentsItr'+i).value);
				if (comments == "") {
					alert("Please enter your comments");
					document.getElementById('reviewCommentsItr'+i).focus();
					return false;
				}			
			}
			
			if(trim(document.getElementById('reviewAgreeDisAgreeFlag'+i).value)==""){
				flag=false;
			} 
		}  
	 
		if(!flag) {
			alert("Please select either Agree or Disagree");
			return false;
		}
	
		var con = "";
		if (buttonName == 'submit') {
			con = confirm("Do you really want to submit this application?");	
		} 
		
		if (buttonName == 'reSubmit') {
			con = confirm("Do you really want to re-submit this application?");	
		}
		
		if (con) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action = 'SelfCompentencyAssesment_updateRecordForReviewer.action';
			document.getElementById('paraFrm').submit();
		} else {
			return false;
		}
	} catch(e) {
		alert("Error >>"+e);
	}
}
</script>
