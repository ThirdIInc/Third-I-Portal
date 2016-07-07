<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="InterviewSchedule" validate="true" id="paraFrm" theme="simple">
<s:hidden name="fromSchdIntListFlag"/>	
<s:hidden name="interviewCode"/>
<s:hidden name="reqDate"/>
<s:hidden name="rowId" />
<s:hidden name="RefFlag" />
<s:hidden name="refInterviewFlag" />
<s:hidden name="interviewReschFlag" />
<s:hidden name="hidReschFlag"/> 
<s:hidden name="testFlag" />
<s:hidden name="hidTestResultFlag" />
<s:hidden name="dummyField"/>

<s:hidden name="testType"/>
<s:hidden name="fromDate"/>
<s:hidden name="toDate"/>
<s:hidden name="searchCriteria"/>
<s:hidden name="marksObtained"/>
<s:hidden name="resultType"/>
<s:hidden name="hidTestTypeForSchTest"/>


	<table width="100%"  class="formbg"><!-- Final Table -->
		 <tr><!--Schedule Test-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" ><!--Table 1-->
								
					<tr>
	       				 <td colspan="3" width="100%">
	        				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
							     <tr>
								     <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
								     <td width="93%" class="txt"><strong class="text_head">Schedule Interview</strong></td>
								     <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
							     </tr>
							</table>
						</td>
					</tr>
					
				</table><!--Table 1-->
				</td>
		</tr><!--Schedule header-->
		
		<tr>
			<td>
				<table width="100%" align="center" cellpadding="0" cellspacing="2"><!--button panel-->
					<tr>
         				 <td width="78%" colspan="2">
         				 <input type="button" class="token"  theme="simple" 
								value="Schedule Interview" onclick="return scheduleValidation();" />
							<s:submit cssClass="token" action="InterviewSchedule_toPostReumeForm" theme="simple"
								value="Add New Candidate" onclick="return addNewvalidation();" />		
							
								<!-- <input type="button" class="token"  theme="simple"
								value="Print Test Schedule"  />
							<input type="button" class="token"  theme="simple"
								value="Export Schedule Interview  Report"  onclick="return myreport();" />	 -->
								  <s:if test="RefFlag">
								 		 <s:if test="refInterviewFlag">
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_callForTestInterview"/>
								 		 </s:if>
								 		 <s:else>
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_formInterview"/>
								 		 </s:else> 
								</s:if>
								<s:if test="hidReschFlag">
									  <s:submit cssClass="cancel" value="Cancel" action="SchdInterviewList_input"/>
								</s:if>
								<s:if test="hidTestResultFlag">
									  <s:submit cssClass="cancel" value="Cancel" action="TestResult_showTestResult"/>
								</s:if>
												
						</td>
						 <td width="22%"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr>			
		<tr><!--Schedule interview-->
			<td colspan="5">
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"><strong>Schedule Interview</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					
					<tr>
						<td>
							
							<table width="100%" border="0"   cellpadding="0" cellspacing="2"><!--Table header-->
							<s:hidden name="headerView"/>
							<s:if test="headerView">
							<tr>
						       <td width="100%">
							
							     <table width="740" border="0"   cellpadding="2" cellspacing="0">
								<tr>
									<td width="18%"><label  class = "set" name="reqs.code" id="reqs.code" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> :<font color="red">*</font></td>
									<td width="32%"><s:hidden name="requisitionCode" /><s:textfield name="requisitionName" size="25"
										readonly="true" />
										<s:hidden name="f9Flag"/>
										<s:if test="f9Flag">
										<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
										onclick="javascript:callsF9(500,325,'InterviewSchedule_f9Requisition.action');"  >
										</s:if>
										</td>
									<td width="10%"><label  class = "set" name="position" id="Position" 
			            		ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
									<td width="30%"><s:textfield name="position" size="25"
										readonly="true"  onmouseover="return tooltipDisplay(this)"/><s:hidden name="positionCode" /></td>
								</tr>
								
								<tr>
									<td width="18%"><label  class = "set" name="hiring.mgr" id="Hiring.Manager" 
			            		ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> :<font color="red">*</font></td>
									<td width="82%" colspan="3"><s:hidden name="hiringManagerCode" /><s:textfield name="hiringManager" size="25"
										readonly="true" /></td>
									 	
								</tr></table></td></tr>
								<tr>
						<td width="100%" colspan="3" >
							
							<table width="740" border="0"   cellpadding="0" cellspacing="2">
								<tr>
										<td width="20%" colspan="1" ><label  class = "set" name="Contact.Person" id="Contact.Person" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Contact.Person")%></label> :<font color="red">*</font></td>
									<td width="80%" colspan="3"><s:hidden name="cntPersonId" /><s:hidden name="cntPersonToken"/><s:textarea name="contactPerson" rows="3" cols="70"
										readonly="false" /><img src="../pages/images/search2.gif" height="16" align="bottom" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'InterviewSchedule_f9CntPerson.action');" ></td>	
										
								</tr>
							
							
							
								<tr>
										<td width="20%" colspan="1" ><label  class = "set" name="Conveyance.Details" id="Conveyance.Details" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Conveyance.Details")%></label> :</td>
									<td width="80%" colspan="3"><s:textarea cols="70" rows="3" name="conveyanceDetail" />&nbsp;<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_conveyanceDetail','Conveyance.Details','','250','250');" ></td>	
										
								</tr>
								
								<tr>
									<td width="20%" colspan="1"><label  class = "set" name="Interview.Requirements" id="Interview.Requirements" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Interview.Requirements")%></label> :</td>
									<td width="80%" colspan="3"><s:textarea label="%{getText('Select Interview Requirements')}"
										theme="simple" cols="70" rows="3" name="testRequirements" />
										<s:hidden name="testReqCode"/>
										<img src="../pages/images/search2.gif" height="16" align="bottom" width="16" theme="simple" 
										onclick="call();"  >
									</td>
									<td width="40%">
									
									</td>
								</tr>
								</table></td></tr>
							</s:if>
								<s:else>
								<tr>
									<td width="20%"><label  class = "set" name="reqs.code" id="reqcode" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> :<font color="red">*</font></td>
									<td width="25%"><s:hidden name="requisitionCode" /><s:property value="requisitionName"/>
									<s:hidden name="requisitionName"/>
										</td>
									<td width="25%"><label  class = "set" name="position" id="Position" 
			            		ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
									<td width="25%"><s:property value="position"/><s:hidden name="positionCode" />
									<s:hidden name="position"/></td>
								</tr>
								<tr>
									<td width="20%"><label  class = "set" name="hiring.mgr" id="Hiring.Manager" 
			            		ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> :<font color="red">*</font></td>
									<td width="25%"><s:hidden name="hiringManagerCode" /><s:property value="hiringManager"/>
									<s:hidden name="hiringManager"/></td>
									<td width="20%"></td>
									<td width="25%"></td>
								</tr>
								
								<tr>
								<td width="20%"><label  class = "set" name="Contact.Person" id="Contact.Person" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Contact.Person")%></label> :</td>
									<td width="25%"><s:hidden name="cntPersonId" /><s:hidden name="cntPersonToken"/>
										<s:property value="contactPerson"/><s:hidden name="contactPerson"/></td>	
										<td width="25%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
								</tr>
								
								
								<tr>
								<td width="20%"><label  class = "set" name="Conveyance.Details" id="Conveyance.Details" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Conveyance.Details")%></label> :</td>
									<td width="25%"><s:property value="conveyanceDetail"/><s:hidden name="conveyanceDetail"/></td>	
										<td width="25%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
								</tr>
								<tr>
									<td width="19%" colspan="1"><label  class = "set" name="Interview.Requirements" id="Interview.Requirements" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Interview.Requirements")%></label> :</td>
									<td width="81%" colspan="3"><s:property value="testRequirements" />
										<s:hidden name="testReqCode"/><s:hidden name="testRequirements" />
										
									</td>
									<td width="40%">
									
									</td>
								</tr>
							</s:else>
							</table><!--Table header-->
						</td>
					</tr>		
					
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule interview-->		
		<tr>
			<td>
				<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2" class="formbg"><!--candidateList-->
					<tr>
						<td>
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Candidate List header-->
								<tr>
									<td><strong class="formhead">Candidate List : </strong></td>
									 <td align="right"> <input type="button" class="delete" value="   Remove"  onclick="return deleteList();"/></td>
								 </tr>
							</table><!--Candidate List header-->	
						</td>
					</tr>
					<tr>
									<td>
										<table width="100%" border="0"  cellpadding="1" cellspacing="1"
											class="sortable" id="tblCandidateList"><!-- table 6 -->
											<tr>
												<td width="5%" valign="top" class="formth" nowrap="nowrap"><b><label  class = "set" name="serial.no" id="serial.no" 
			            		ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
												<td width="20%" valign="top" class="formth" align="center"><b><label  class = "set" name="cand.name" id="cntname" 
			            		ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
												<td width="10%" valign="top" class="formth" align="center"><b><label  class = "set" name="Interview.Round.Type" id="Interview.Round.Type" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Interview.Round.Type")%></label></b><font color="red">*</font></td>
												<td width="20%" valign="top" class="formth" align="center"><b><label  class = "set" name="intDate" id="intDate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("intDate")%></label><font color="red">*</font></td>
												<td width="2%" valign="top" class="formth" align="center">&nbsp;</td>
												<td width="22%" valign="top" class="formth" align="center" ><b><label  class = "set" name="Interview.Time" id="Interview.Time" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Interview.Time")%></label><font color="red">*</font></td>
												<td width="25%" valign="top" class="formth" align="center"><b><label  class = "set" name="Venue" id="Venue" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Venue")%></label></b><font color="red">*</font></td><td  width="2%" valign="top" class="formth" align="center">&nbsp;</td>
												<td width="25%" valign="top" class="formth" align="center"><b><label  class = "set" name="Interviewer" id="Interviewer" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Interviewer")%></label></b></td>
												<td width="5%" valign="top" class="formth" align="center"><b><label  class = "set" name="Previous.Interview.Details" id="Previous.Interview.Details" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Previous.Interview.Details")%></label></b></td>
												<td width="5%" valign="top" class="formth" align="center"><b><label  class = "set" name="View.Test.Result" id="View.Test.Result" 
			            		ondblclick="callShowDiv(this);"><%=label.get("View.Test.Result")%></label></b></td>
												<td width="27%" valign="top" class="formth" align="center"><b><label  class = "set" name="rec.comments" id="rec.comments" 
			            		ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td><td  width="2%" valign="top" class="formth" align="center">&nbsp;</td>
												<td width="5%" valign="top" class="formth" abbr="right">
												<input class="classcheck" 
													style="text-align: center; border: none; margin: 1px"
													type="checkbox" size="2" name="chkAll" id="chkAll" onclick="return callChkAll();"">							
												</td>
											</tr>
											
											
											<%! int c=0; %>
							
							  				<%int j=1; int k=0;%>
											<s:iterator status="stat" value="candidateList">
			                        	<tr>
			                        		<td width="5%" class="sortableTD" class="border2"><%=j %></td>
			                        		
			                        		 <%
		      	   		                                String si = (String)request.getAttribute("paraFrm_interviewerCode"+j);   
			   											String sn=(String)request.getAttribute("paraFrm_interviewerName"+j);
		      	   		                               %>
			                        		
			                        		<td width="15%" nowrap="nowrap" class="sortableTD">&nbsp;<s:property value="candidateName"/>
			                        			<s:hidden name="candidateName" id='<%="candidateName"+j%>'/><s:hidden name="candidateCode" id='<%="candidateCode"+j%>'/></td>
			                        		
			                        		
			                        		<!--<td width="10%" nowrap="nowrap" class="sortableTD"><input type="text" name="intRoundType" maxlength="20" size="7" id="<%="intRoundType"+j%>"  value='<s:property
													value="intRoundType" />'/>&nbsp;</td>
											-->
											<td width="10%" nowrap="nowrap" class="sortableTD"> 
												<s:select headerKey="-1" headerValue="-- Select --"	
												id='<%="intRoundType"+j%>'
												name="intRoundType" cssStyle="width:100" list="interviewRoundTypeMap" />&nbsp;
											</td> 		
													
			                        		<td width="25%" nowrap="nowrap" class="sortableTD"><input type="text" size="8" name="intDate" id='<%="intDate"+j%>' maxlength="10"
			                        			onkeypress="return numbersWithHiphen();"  value='<s:property value="intDate"/>'/>&nbsp;</td>
												<td width="2%" class="sortableTD"><a
												href="javascript:NewCal('<%="intDate"+j%>','DDMMYYYY');"> <img
												src="../pages/images/recruitment/Date.gif" class="iconImage" height="16"
												align="absmiddle" width="16"> </a></td>
												
												
			                        		<td width="25%" class="sortableTD" nowrap="nowrap" align="center"><input type="text" name="intTime" size="5" id='<%="intTime"+j%>'  maxlength="5"
			                        			value='<s:property value="intTime" />' onkeypress="return numbersWithColon();" />&nbsp;</td>
											<td width="25%" class="sortableTD" ><s:textarea label="%{getText('Venue')}"
														theme="simple" cols="15" rows="2" name="venue" id='<%="venue"+j%>' />&nbsp;</td><td width="2%" class="sortableTD"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('<%="venue"+j%>','Venue','','250','250');" ></td>
											<td nowrap="nowrap" width="25%" class="sortableTD" ><s:textfield name ='<%="paraFrm_interviewerName"+j%>'    value="<%=sn%>" readonly="true"  size="20" />
											<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick = "callInterviewer('<s:property value='<%=""+j%>'/>')">&nbsp; </td>
			   								<td class="sortableTD" nowrap="nowrap" width="5%"><input type="button" name="intDetail" class="token"  value="View" onclick="viewInterviewDetails('<s:property value="candidateCode"/>')"/></td>
			   								<td class="sortableTD" nowrap="nowrap" width="5%"><input type="button" name="testDetail" class="token" value="View" onclick="viewTestDetails('<s:property value="candidateCode"/>')"  /></td>								
			                        		<td width="27%" class="sortableTD">&nbsp;<s:textarea label="%{getText('Comments')}"
														theme="simple" cols="15" rows="2" name="comments" id='<%="comments"+j%>' /></td><td width="2%" class="sortableTD"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('<%="comments"+j%>','rec.comments','aa',250,250);" ></td>
			                        		<td width="5%" nowrap="nowrap" class="sortableTD">
			                        			<input type="checkbox" name="chk" id='<%="chk"+j %>' value="N" onclick="callChk(<%=j %>)"/>
			                        			<input type="hidden" name="checkBox" id="<%=j %>" value="N"/>
			                        			<s:hidden name ='<%="paraFrm_interviewerCode"+j%>'  value="<%=si%>" />
			                        			<s:hidden name="recruiterId" id='<%="recruiterId"+j%>'/>
			                        			<s:hidden name="interviewDtlCode"/>
			                        		</td>
			                        	</tr>
			                        <%j++;k++;%>
								</s:iterator>
									  <%c=j;%>
										</table><!-- table 6 -->
										<input type="hidden" name="count" id="count" value="<%=k%>"/> 
									</td>
								</tr>	
				</table><!--candidateList-->
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" align="center" cellpadding="0" cellspacing="2"><!--button panel-->
					<tr>
         				 <td><input type="button" class="token"  theme="simple" 
								value="Schedule Interview" onclick="return scheduleValidation();" />
							<s:submit cssClass="token" action="InterviewSchedule_toPostReumeForm" theme="simple"
								value="Add New Candidate" onclick="return addNewvalidation();" />		
							
								<!--<input type="button" class="token"  theme="simple"
								value="Print Test Schedule"  />
							<input type="button" class="token"  theme="simple"
								value="Export Schedule Interview  Report"  onclick="return myreport();" />-->
											
			   <s:if test="RefFlag">
								 		 <s:if test="refInterviewFlag">
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_callForTestInterview"/>
								 		 </s:if>
								 		 <s:else>
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_formInterview"/>
								 		 </s:else>
										
           </s:if>
                   <s:if test="hidReschFlag">
									  <s:submit cssClass="cancel" value="Cancel" action="SchdInterviewList_input"/>
				   </s:if>
				   <s:if test="hidTestResultFlag">
									  <s:submit cssClass="cancel" value="Cancel" action="TestResult_showTestResult"/>
				   </s:if>
									
			    <!--  	<s:if test="testFlag">
					<s:submit cssClass="cancel" value="Cancel" action="TestResult_toScheduleInterview"/>
				</s:if>-->
						</td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr>			
	</table><!-- Final Table -->
</s:form>	

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>
<script>
	function callChkAll()  {
	 
		var tbl = document.getElementById('tblCandidateList');
		var rowLen = tbl.rows.length;
		if(document.getElementById('chkAll').checked){
		
			 for(i = 1; i < rowLen; i++){
			 
			 	  document.getElementById('chk'+i).checked = true;
				  document.getElementById(i).value="Y";
			  }
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			   document.getElementById('chk'+i).checked =false;
			   document.getElementById(i).value="";
		 }
	  }	
	
  }
  
  function scheduleValidation(){
  try{
  	var count = document.getElementById("count").value;
  	var chkFlag = false;
  	
  	var fieldName = ["paraFrm_requisitionName", "paraFrm_contactPerson"];
  	var labelName = ["reqs.code", "Contact.Person"];
  	var flag      = ["select", "select"];
  	
  	if(!validateBlank(fieldName, labelName, flag))return false;
  	
  	var testReq = document.getElementById('paraFrm_testRequirements').value;
  	var covDetails = document.getElementById('paraFrm_conveyanceDetail').value;
  	
  	var convdetail=document.getElementById('Conveyance.Details').innerHTML.toLowerCase();  
  	var testReq1=document.getElementById('Interview.Requirements').innerHTML.toLowerCase();
  	var contact=document.getElementById('paraFrm_contactPerson').value;
  	
  	if(contact.length >500){
  	    alert("Maximum length of "+document.getElementById('Contact.Person').innerHTML.toLowerCase()+" is 500 characters");
 		return false;
  	}
  	
	if(covDetails != "" && covDetails.length > 250){
		alert("Maximum length of "+convdetail+" is 250 characters.");
		return false;
	}
		
	if(testReq != "" && testReq.length > 250){
		alert("Maximum length of "+testReq1+" is 250 characters.");
		return false;
	}
		
	if(count == 0){
  		alert("There is no record in the list");
  		return false;
  	}
  	
  	for(var i=1; i<=count; i++){
		if(document.getElementById('chk'+i).checked){
			chkFlag = true;
		}
	}
	
	if(!chkFlag){
		alert("Please select atleast one candidate");
		return false;
	}
	
	for(var j=1; j<=count; j++){
		if(document.getElementById('chk'+j).checked){
			var intRoundType = trim(document.getElementById('intRoundType'+j).value);
			var intDate = trim(document.getElementById('intDate'+j).value);
			var intTime = trim(document.getElementById('intTime'+j).value);
			var venue = document.getElementById('venue'+j).value;
			var comments = document.getElementById('comments'+j).value;
		
		
			
			if(intRoundType =="-1"){
				alert("Please select "+document.getElementById('Interview.Round.Type').innerHTML.toLowerCase()+" for "+document.getElementById('candidateName'+j).value); 
				document.getElementById('intRoundType'+j).focus();
				return false;
			}
			
			if(trim(document.getElementById('intDate'+j).value)==""){
			
				alert("Please select/enter "+document.getElementById('intDate').innerHTML.toLowerCase()); 
				document.getElementById('intDate'+j).value="";
				document.getElementById('intDate'+j).focus();
				return false;
			}
			if(!validateDate("intDate"+j, "intDate"))return false;
			
			if(!dateCheckWithToday("intDate"+j,"intDate"))return false;
			
			
			
			if(trim(document.getElementById('intTime'+j).value)==""){
				alert("Please enter "+document.getElementById('Interview.Time').innerHTML.toLowerCase()); 
				document.getElementById('intTime'+j).value="";
				document.getElementById('intTime'+j).focus();
				return false;
			}
			
			if(!validateTime("intTime"+j,"Interview.Time"))
			 return false;
			
			if(trim(document.getElementById('venue'+j).value)==""){
				alert("Please enter "+document.getElementById('Venue').innerHTML.toLowerCase()); 
				document.getElementById('venue'+j).value="";
				document.getElementById('venue'+j).focus();
				return false;
			}
			if(venue != "" && venue.length > 250){
				alert("Maximum length of "+document.getElementById('Venue').innerHTML.toLowerCase()+" is 250 characters.");
				return false;
			}
			
			if(comments != "" && comments.length > 250){
				alert("Maximum length of "+document.getElementById('rec.comments').innerHTML.toLowerCase()+" is 250 characters.");
				return false;
			}
		}
		//============ new Added===========
		
		  //added new for  comments length   
		 // if(!chkSize()){
		//	return false;// new added
		//}
		    
	}
  	}catch(e)
  	{
  		
  	}
  	
  	var message = document.getElementById('paraFrm_fromSchdIntListFlag').value;
  	
  	if(message == "true"){
  		var con=confirm('Do you really want to reschedule the interview ?');
		 if(con){
		    document.getElementById('paraFrm').action="InterviewSchedule_scheduleInt.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return false;
		    }
  	}
  	else{
  		var con=confirm('Do you really want to schedule the interview ?');
		 if(con){
		    document.getElementById('paraFrm').action="InterviewSchedule_scheduleInt.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return false;
		    }
  	}
  	
  	
  
  	return true;
  }
  
  /*function scheduleValidation(){
  		var tbl = document.getElementById('tblCandidateList');
		var rowLen = tbl.rows.length;
		var cntPerson = document.getElementById('paraFrm_cntPersonId').value;
		var reqCode = document.getElementById('paraFrm_requisitionCode').value;
		var count=0;
		
		if(reqCode==""){
			alert('Please select requisition');
			return false;
		}
		if(cntPerson==""){
			alert('Please select contact person');
			return false;
		}
		
		 var comment = document.getElementById('paraFrm_testRequirements').value;
 		 
 		 if(comment.length >300){
   
 			alert("Maximum length of 'Interview Requirements' is 300 characters.");
			return false;
   
  		 }    
		try{
		for(i = 1; i < rowLen; i++){
			if(document.getElementById('chk'+i).checked == true){
				count++;
				var intDate = document.getElementById('intDate'+i).value;
				var intTime = document.getElementById('intTime'+i).value;
				var intRoundType = document.getElementById('intRoundType'+i).value;
				var venue = document.getElementById('venue'+i).value;
				var commentList = document.getElementById('comments'+i).value;
				if(intRoundType == ""){
					alert('Please enter interview round type');
					return false;
				}
				
				if(intDate == ""){
					alert('Please enter interview date');
					return false;
				}
				if(!validateDate("intDate"+i, "Interview Date")) return false;
				if(intTime == ""){
					alert('Please enter interview time');
					return false;
				}
				if(!validateTime("intTime"+i, "Interview Time")) return false;
				
				if(venue == ""){
					alert('Please enter venue');
					return false;
				}
				if(venue.length >300){
   
 					alert("Maximum length of 'Venue' is 300 characters.");
					return false;
   
  				 }
  				 if(commentList.length >300){
 					alert("Maximum length of 'Comments' is 300 characters.");
					return false;
  				 }    
			}
		}
		}catch(e){
			alert(e);
		}
		
		if(count==0){
			alert('Please select at least one checkbox');	
			return false;
		}
		return true;
		//return false;
  }*/
  
  function callChecklist(){
		
		window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	 	document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action = 'InterviewSchedule_intChecklist.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	}
	
	function deleteList()
	{
	//alert('dgdfgfd');
		 if(chkSkill()){
		 var con=confirm('Do you want to remove the records?');
		 if(con){
		    document.getElementById('paraFrm').action="InterviewSchedule_deleteList.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please Select Atleast one Record To Remove');
		 	 return false;
		 }
	 
	}
	
	function chkSkill(){
	var tbl = document.getElementById('tblCandidateList');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('chk'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
	
	 function callInterviewer(id)
    {
	    
	      document.getElementById('paraFrm_rowId').value=id; 
	      callsF9(500,325,'InterviewSchedule_f9Interviewer.action'); 
     
     
   }
   
  
	
	function addNewvalidation(){
		var reqCode = document.getElementById('paraFrm_requisitionCode').value;
		var reqname=document.getElementById('reqs.code').innerHTML.toLowerCase();
		
		if(reqCode==""){
			alert('Please select '+reqname);
			return false;
		}
		return true;
	}
	
	function viewInterviewDetails(candCode)
	{
		var reqCode = document.getElementById('paraFrm_requisitionCode').value;
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}
	
	  function viewTestDetails(candCode)
	{
		 var reqCode = document.getElementById('paraFrm_requisitionCode').value;
		window.open('InterviewSchedule_viewTestDetails.action?requisitionCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=750,height=400');
	}
	
	function myreport()
	{
	 var Requiscode=document.getElementById('paraFrm_requisitionCode').value;
	 var reqcode=document.getElementById('reqs.code').innerHTML.toLowerCase();
		 if(Requiscode=="")
		 {
			alert('Please select '+reqcode);
			 return false;
		 }
	 document.getElementById("paraFrm").action = 'InterviewSchedule_generateReport.action'; 
     document.getElementById("paraFrm").target = "main";
	 document.getElementById("paraFrm").submit();
	}
	
	 function call(){
	 try{
	 var id = document.getElementById('paraFrm_testReqCode').value;
	  	   	 
	window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	 		document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action='InterviewSchedule_intChecklist.action?id='+id; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  		}catch(e){
	  			//alert(e);
	  		}
	  	 }
	
	// New Added For comments length
 
 function tooltipDisplay()
	{	 
	 document.getElementById("paraFrm_position").title =document.getElementById("paraFrm_position").value; 	 
	}
	
</script>




