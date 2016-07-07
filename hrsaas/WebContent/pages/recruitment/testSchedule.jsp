<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TestSchedule" validate="true" id="paraFrm" theme="simple">
<s:hidden name="reqDate"/>
<s:hidden name="testTemplateCode"/>
<s:hidden name="testDuration"/>

<s:hidden name="totalNoQue"/>
<s:hidden name="testTotalMarks"/>
<s:hidden name="passingMark"/>
<s:hidden name="testName"/>
<s:hidden name="RefFlag" />
<s:hidden name="testType1" />
<s:hidden name="RefCanTestFlag" /> 
<s:hidden name="fromTestRescheduleFlag"/>
<s:hidden name="fromTestResultFlag"></s:hidden>
<s:hidden name="testCode"></s:hidden>
<s:hidden name="testDtlCode"></s:hidden>
<s:hidden name="isFrmTestReschedule" />
<s:hidden name="dummyField"></s:hidden>

<s:hidden name="hidTestTypeForSchTest"/>
<s:hidden name="fromDate"/>
<s:hidden name="toDate"/>
<s:hidden name="searchCriteria"/>
<s:hidden name="marksObtained"/>
<s:hidden name="resultType"/>
<s:hidden name="formName"/>

	<table width="100%"  class="formbg"><!-- Final Table -->
		 <tr><!--Schedule Test-->
			<td colspan="5">
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" ><!--Table 1-->
					<tr>
	       				 <td colspan="3" width="100%">
	        				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
							     <tr>
								     <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
								     <td width="93%" class="txt"><strong class="text_head">Schedule Test</strong></td>
								     <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
							     </tr>
							</table>
						</td>
					</tr>
					 
				</table><!--Table 1-->
				</td>
		</tr><!--Schedule Test-->
		<tr><!--Buttons-->
			<td>
				<table width="100%" align="center" cellpadding="0" cellspacing="2"><!--button panel-->
					<tr>
         				 <td width="78%" colspan="2">
         				 <input type="button" class="token" 
								value="Schedule Test" onclick="return scheduleValidation();" />
							<s:submit cssClass="token" action="TestSchedule_toPostReumeForm" theme="simple"
								value="Add New Candidate" onclick="return addNewvalidation();" />		
							
								<!--<input type="button" class="token"  theme="simple"
								value="Print Test Schedule"  /> 
							<input type="button" class="token"  theme="simple"
								value="Export Test Schedule Report"  />	-->	
				
				  		  <s:if test="RefFlag">  
					       <s:if test="RefCanTestFlag">
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_callForTestInterview"/>
								 		 </s:if>
								 		 <s:else>
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_formTest"/>
								 		 </s:else>
					
					     </s:if>
					     <s:elseif test="fromTestRescheduleFlag">
					     	<s:submit cssClass="cancel" value="Cancel" action="TestReschedule_input"/>
					     </s:elseif>
					     <s:elseif test="fromTestResultFlag">
					     	<s:submit cssClass="cancel" value="Cancel" action="TestResult_showTestResult"/>
					     </s:elseif>      		
						</td>
						
						 <td width="22%"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr><!--buttons-->
		<tr><!--Schedule Test-->
			<td colspan="1" width="100%">
				<table width="100%" border="0"   cellpadding="2" cellspacing="2" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"><strong>Schedule Test</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td >
							<table width="100%" border="0"  align="center" cellpadding="2" cellspacing="2"><!--Table header-->
								<tr><td width="100%">
								<table width="740" border="0"  cellpadding="2" cellspacing="2" align="left">
								<tr>
									<td width="20%"><label  class = "set" name="reqs.code" id="reqs.code" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> :<font color="red">*</font></td>
									<td width="30%" nowrap="nowrap"><s:hidden name="requisitionCode" /><s:textfield name="requisitionName" size="25"
										readonly="true" /><s:hidden name="hiringManager"/><s:hidden name="f9Flag"/>
										<s:if test="f9Flag">
										<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
										onclick="javascript:callsF9(500,325,'TestSchedule_f9Requisition.action');">
										</s:if>
										</td>
									<td width="22%"><label  class = "set" name="position" id="position" 
			            		     ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
									<td width="28%"><s:textfield name="position" size="25" onmouseover="return tooltipDisplay(this)" readonly="true" /><s:hidden name="positionCode" /></td>
								</tr>
								
								<tr>
									<td width="20%"><label  class = "set" name="test.type" id="test.type" 
			            		ondblclick="callShowDiv(this);"><%=label.get("test.type")%></label> :<font color="red">*</font></td>
									<td width="30%"><s:select  name="testType" list="#{'':'Select','W':'Written Test','O':'On Line Test'}"   
											  cssStyle="width:100" theme="simple"/></td>
									<td width="22%"><label  class = "set" name="select.test.template" id="select.test.template" 
			            		  ondblclick="callShowDiv(this);"><%=label.get("select.test.template")%></label> :<font color="red">*</font></td>
									<td width="28%"><s:hidden name="templateId"/><s:textfield name="testTemplate" size="25"
										readonly="true" /><img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple"  onclick="javascript:callsF9(500,325,'TestSchedule_f9testtemplate.action');"></td>	
								</tr> 
								
								</table></td></tr>
								
								
								<tr><td width="100%">
								<table width="100%" border="0"  cellpadding="0" cellspacing="0">
								
								<tr>
									<td width="20%" valign="top"><label  class = "set" name="contact.person" id="contact.person" 
			            		ondblclick="callShowDiv(this);"><%=label.get("contact.person")%></label> :<font color="red">*</font></td>
									<td width="80%"><s:hidden name="cntPersonId" /><s:hidden name="cntPersonToken"/><s:textarea name="contactPerson" cols="70" rows="3"
										readonly="false" /><img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'TestSchedule_f9CntPerson.action');" ></td>
										
								</tr>
								<tr>
									<td width="20%" valign="top"><label  class = "set" name="venue" id="venue1" 
			            		ondblclick="callShowDiv(this);"><%=label.get("venue")%></label> :<font color="red">*</font></td>
									<td width="80%" colspan="3"><s:textarea cols="70" rows="3" name="venue" />&nbsp;<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_venue','venue1','','250','250');" ></td>
									</tr>
								<tr>	
									<td width="20%" valign="top"><label  class = "set" name="conveyance.details" id="conveyance.details" 
			            		ondblclick="callShowDiv(this);"><%=label.get("conveyance.details")%></label> :</td>
									<td width="80%" colspan="3"><s:textarea cols="70" rows="3" name="conveyanceDetail" />&nbsp;<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_conveyanceDetail','conveyance.details','','250','250');" ></td>										
								</tr>
								<tr>
									<td width="20%" colspan="1" valign="top"><label  class = "set" name="select.interview.requirements" id="select.interview.requirements" 
			            		ondblclick="callShowDiv(this);"><%=label.get("select.interview.requirements")%></label> :</td>
									<td width="80%" colspan="3"><s:textarea label="%{getText('Select Test Requirements')}"
										theme="simple" cols="70" rows="3" name="testRequirements" />
										<s:hidden name="testReqCode"/>
										<img src="../pages/images/search2.gif" height="16" align="bottom" width="16" theme="simple" 
										onclick="call();"  >
									</td>
								</tr>
								</table></td></tr>
							</table><!--Table header-->
						</td>
					</tr>		
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule Test-->
		
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
							<table width="100%" border="0"  cellpadding="1" cellspacing="1"	class="sortable" id="tblCandidateList"><!-- table 6 -->
								<tr>
									<td width="5%" valign="top" class="formth" nowrap="nowrap"><b><label  class = "set" name="serial.no" id="serial.no" 
			            		ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
									<td width="15%" valign="top" class="formth" align="center"><b><label  class = "set" name="reqs.code" id="requistion.code1" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
									<td width="15%" valign="top" class="formth" align="center"><b><label  class = "set" name="cand.name" id="cand.name" 
			            		ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
									<td width="15%" valign="top" class="formth" align="center"><b><label  class = "set" name="email.id" id="e.id" 
			            		ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></b></td>
									<td width="10%" valign="top" nowrap="nowrap" class="formth" align="center"><b><label  class = "set" name="cntno1" id="cntno1" 
			            		ondblclick="callShowDiv(this);"><%=label.get("cntno1")%></label></b></td>
			            		<td width="10%" valign="top" class="formth" align="center"><b><label  class = "set" name="test.round" id="test.round" 
			            		ondblclick="callShowDiv(this);"><%=label.get("test.round")%></label></b><font color="red">*</font></td>
									<td width="10%" valign="top" class="formth" align="center" ><b><label  class = "set" name="testdate" id="testdate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("testdate")%></label><font color="red">*</font></td>
									<td width="10%" valign="top" class="formth" align="center"><b><label  class = "set" name="testtime" id="testtime" 
			            		ondblclick="callShowDiv(this);"><%=label.get("testtime")%></label><font color="red">*</font></td>
									<td width="30%" valign="top" class="formth" align="center" nowrap="nowrap"><b><label  class = "set" name="comments" id="comments" 
			            		ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></b></td>
									<td width="5%" valign="top" class="formth">
									<input class="classcheck" 
											style="border: none; margin: 1px"
											type="checkbox" size="2" name="chkAll" id="chkAll" onclick="return callChkAll();"">							
									</td>
								</tr>
								
								<%! int c=0; %>
							
							  				<%int j=1; int k=0;%>
											<s:iterator status="stat" value="candidateList">
			                        	<tr>
			                        		<td width="5%" align="center" class="sortableTD"><%=j %></td>
			                        		<td class="sortableTD" width="10%"><s:hidden name="requisitionCodeIterator" id='<%="requisitionCodeIterator"+j%>'/>&nbsp;
			                        		<s:property
											value="reqName" /></td>
			                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="candidateName"/>
			                        			<s:hidden name="candidateName" id='<%="candidateName"+j%>'/><s:hidden name="candidateCode" id='<%="candidateCode"+j%>'/></td>
			                        		<td width="10%" class="sortableTD"><s:property value="emailId"/>&nbsp;</td>
			                        		<td width="10%" nowrap="nowrap" class="sortableTD"><s:property value="contactNo"/>&nbsp;</td>
			                        		<td width="10%" nowrap="nowrap" class="sortableTD"><input type="text" name="testRoundType" maxlength="20" size="7" id='<%="testRoundType"+j%>'  value='<s:property
													value="testRoundType" />'/>&nbsp;</td>
			                        		<td width="20%" nowrap="nowrap"  class="sortableTD">
			                        			<input type="text" size="8" name="testDate" id='<%="testDate"+j%>' onkeypress="return numbersWithHiphen();"  value='<s:property
													value="testDate"/>' maxlength="10"/><a
												href="javascript:NewCal('<%="testDate"+j%>','DDMMYYYY');"> <img
												src="../pages/images/recruitment/Date.gif" class="iconImage" height="16"
												align="absmiddle" width="16"> </a>&nbsp;
												</td>
			                        		<td width="10%"  nowrap="nowrap"  class="sortableTD"><input type="text" name="testTime" size="7" id='<%="testTime"+j%>'  value='<s:property
													value="testTime" />' maxlength="5" onkeypress="return numbersWithColon();"/>&nbsp;</td>
			                        		<td width="30%" class="sortableTD" nowrap="nowrap"><s:textarea label="%{getText('comments')}"
														theme="simple" cols="15" rows="2" name="comments" id='<%="comments"+j%>' />&nbsp;&nbsp; <img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('<%="comments"+j%>','comments','aa',300,300);" ></td>
			                        		<td width="5%"  class="sortableTD" align="center">
			                        			<input type="checkbox" name="chk" id='<%="chk"+j %>' value="N" onclick="callChk(<%=j %>)"/>
			                        			<input type="hidden" name="checkBox" id="<%=j %>" value="N"/>
			                        			<s:hidden name="reqName" id='<%="reqName"+j%>'/>
			                        			<s:hidden name="hiringManagerIterator" id='<%="hiringManagerIterator"+j%>'/>
			                        			<s:hidden name="hiringManagerCode" id='<%="hiringManagerCode"+j%>'/>
			                        			<s:hidden name="recruiterName" id='<%="recruiterName"+j%>'/>
			                        			<s:hidden name="recruiterId" id='<%="recruiterId"+j%>'/>
			                        			<s:hidden name="emailId" id='<%="emailId"+j%>'/>
			                        			<s:hidden name="contactNo" id='<%="contactNo"+j%>'/>&nbsp;
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
         				 <td>
         				 <input type="button"  class="token" 
								value="Schedule Test" onclick="return scheduleValidation();" />
							<s:submit cssClass="token" action="TestSchedule_toPostReumeForm" theme="simple"
								value="Add New Candidate" onclick="return addNewvalidation();" />		
							
								<!--<input type="button" class="token"  theme="simple"
								value="Print Test Schedule"  /> 
							<input type="button" class="token"  theme="simple"
								value="Export Test Schedule Report"  />	-->		
								
						 <s:if test="RefFlag">  
					       <s:if test="RefCanTestFlag">
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_callForTestInterview"/>
								 		 </s:if>
								 		 <s:else>
								 		 <s:submit cssClass="cancel" value="Cancel" action="CandidateScreeningStatus_formTest"/>
								 		 </s:else>
					
					     </s:if>     	
					     <s:elseif test="fromTestRescheduleFlag">
					     	<s:submit cssClass="cancel" value="Cancel" action="TestReschedule_input"/>
					     </s:elseif>	
					     <s:elseif test="fromTestResultFlag">
					     	<s:submit cssClass="cancel" value="Cancel" action="TestResult_showTestResult"/>
					     </s:elseif> 	
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
  var tbl = document.getElementById('tblCandidateList');
		var rowLen = tbl.rows.length;
  	var count = 0;
  	var chkFlag = false;
  	
  	var fieldName = ["paraFrm_requisitionName","paraFrm_testType","paraFrm_testTemplate","paraFrm_contactPerson","paraFrm_venue"];
  	var labelName = ["reqs.code","test.type","select.test.template","contact.person","venue1"];
  	var flag      = ["select", "select","","select or enter","enter"];
  	
  	if(!validateBlank(fieldName, labelName, flag))return false;
  	
  	var venue1 = document.getElementById('paraFrm_venue').value;
  	var testReq = document.getElementById('paraFrm_testRequirements').value;
  	var covDetails = document.getElementById('paraFrm_conveyanceDetail').value;
  	var contact=trim(document.getElementById('paraFrm_contactPerson').value);
  	var venue=document.getElementById('venue1').innerHTML.toLowerCase();
  	var convdetail=document.getElementById('conveyance.details').innerHTML.toLowerCase();
  	var intvreq=document.getElementById('select.interview.requirements').innerHTML.toLowerCase();
  	var contactPers=document.getElementById('contact.person').innerHTML.toLowerCase();
  	
  	if(contact.length >500){
  		alert("Maximum length of "+contactPers+" is 500 characters.");
  		return false;
  	}
  	
  	
  	if(venue1 != "" && venue1.length > 250){
			alert("Maximum length of "+venue+" is 250 characters.");
			return false;
	}
	
	if(covDetails != "" && covDetails.length > 250){
			alert("Maximum length of "+convdetail+" is 250 characters.");
			return false;
	}
	
	if(testReq != "" && testReq.length > 250){
			alert("Maximum length of "+intvreq+" is 300 characters.");
			return false;
	}
  	
  	
  	if(rowLen == 0){
  		alert("There is no record in the list");
  		return false;
  	}
  	
  
	try{
	for(i = 1; i < rowLen; i++){
			if(document.getElementById('chk'+i).checked == true){
				count++;
				var testRound = trim(document.getElementById('testRoundType'+i).value);				
				var testDate = document.getElementById('testDate'+i).value;
				var testTime = document.getElementById('testTime'+i).value;
				var comments = document.getElementById('comments'+i).value;
				
				if(testRound == ""){
					alert("Please enter "+document.getElementById('test.round').innerHTML.toLowerCase());
					document.getElementById('testRoundType'+i).focus();
					return false;
				} //end of if
				
				if(testDate == ""){
					alert("Please select/enter "+document.getElementById('testdate').innerHTML.toLowerCase());
					document.getElementById('testDate'+i).focus();
					return false;
				}
				if(!validateDate("testDate"+i, 'testdate')) return false;
				if(!dateCheckWithToday("testDate"+i, 'testdate')) return false; 
				if(testTime == ""){
					alert("Please enter <%=label.get("testtime")%>");
					document.getElementById('testTime'+i).focus();
					return false;
				}
				if(!validateTime("testTime"+i, 'testtime')) return false;
				
				if(comments.length >300){
   
 				alert("Maximum length of 'Comments' is 300 characters.");
				return false;
   
  				 }   
			}
		}
		}catch(e){
		alert(e);
		}	
		if(count==0){
			alert('Please select at least one candidate');	
			return false;
		}
		
		var message = document.getElementById('paraFrm_fromTestRescheduleFlag').value;
		var con = "";
		if(message == "true"){
			con=confirm('Do you really want to reschedule the test ?');
		} //end of if
		else{
			con=confirm('Do you really want to schedule the test ?');
		} //end of else
 		
		 if(con){
		    document.getElementById('paraFrm').action="TestSchedule_scheduleTest.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return false;
		    }
  	
  	return true;
  }
  
    
  function callChecklist(){
		//document.getElementById('paraFrm_testReqCodeHid').value = setTestReqCode;
		//document.getElementById('paraFrm_testRequirementsHid').value = setTestReq;
		
		window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	 	document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action = 'TestSchedule_testChecklist.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	}
	
	function deleteList()
	{
	//alert('dgdfgfd');
		 if(chkSkill()){
		 var con=confirm('Do you want to remove the records?');
		 if(con){
		    document.getElementById('paraFrm').action="TestSchedule_deleteList.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please select atleast one record to remove');
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
	
	function addNewvalidation(){
		var reqCode = document.getElementById('paraFrm_requisitionName').value;
		var reqname=document.getElementById('reqs.code').innerHTML.toLowerCase();
		
		if(reqCode==""){
			alert('Please select '+reqname);
			return false;
		}
		return true;
	}
	
	function call(){
	 try{
	 var id = document.getElementById('paraFrm_testReqCode').value;
	  	   	 
	window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	 		document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action='TestSchedule_testChecklist.action?id='+id; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  		}catch(e){
	  			//alert(e);
	  		}
	  	 }
	

	
	function tooltipDisplay()
	{	 
	 document.getElementById("paraFrm_position").title =document.getElementById("paraFrm_position").value; 	 
	}
	
</script>