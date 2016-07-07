<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ include file="../common/commonValidations.jsp"%>
<s:form action="Suggestion" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="0"
		class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Suggestion</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<s:if test="isApprove">

							</s:if>
							<s:else>
								<td width="78%"><s:if test="%{insertFlag}">
									<s:submit cssClass="add" action="Suggestion_addSugg"
										theme="simple" value="  Add New"
										onclick="return validate('add');" />
								</s:if> <s:if test="%{updateFlag}">
									<s:submit action="Suggestion_addSugg" cssClass="edit"
										value="   Update" onclick="return validate('update');" />
								</s:if><s:if test="%{viewFlag}">
									<input type="button" class="search" value="    Search"
										onclick="javascript:callsF9(600,500,'Suggestion_f9search.action');">
								</s:if><s:submit cssClass="reset" action="Suggestion_reset"
									theme="simple" value="    Reset" /> <s:if test="%{deleteFlag}">
									<s:submit cssClass="delete"
										action="Suggestion_deleteSuggestion" theme="simple"
										value="    Delete " onclick="return deletefun();" />
								</s:if> <s:if test="%{viewFlag}">
									<input type="button" class="token"
										onclick="return callReportforSelected('Suggestion_report.action','paraFrm_suggcode');"
										value="  Report " />
								</s:if>
								
								<input type="button" value="Back" onclick="callBack();">
								
								
								</td>
							</s:else>


							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>

						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">
								<tr>
									<s:hidden name="dupstatus"></s:hidden>
									<td>



									<table width="98%" border="0" align="center" cellpadding="2"
										cellspacing="2" >
										<tr>
											<td colspan="5" class="formhead"><strong
												class="forminnerhead"><label class="set"
												name="suggestion.application.form"
												id="suggestion.application.form"
												ondblclick="callShowDiv(this);"><%=label.get("suggestion.application.form")%></label>
											:</strong></td>
										</tr>
										<s:hidden name="suggcode" />
										<tr><td>
										<table width="98%" border="0" cellpadding="2"
													cellspacing="2" >
													
													<tr>

												<td colspan="1" width="25%"><label class="set"
													name="employee" id="employee"
													ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
												<font color="red">*</font>:</td>
												<td colspan="1" nowrap="nowrap"><s:textfield readonly="true"
													name="eToken" size="12" /><s:textfield theme="simple"
													name="empName" readonly="true" size="38" />													
													 <s:if
													test="isApprove">
												</s:if> <s:else>
													<s:if test="%{generalFlag}">
													</s:if>
													<s:else>
														<img src="../pages/images/search2.gif" class="iconImage"
															height="18" align="absmiddle" width="18"
															onclick="javascript:callsF9(500,325,'Suggestion_f9empaction.action');">
													</s:else>
												</s:else></td>

												<s:hidden name="ecode" />

												<s:hidden name="empAppr" />
												<s:hidden name="empdept" />
												<s:hidden name="empbranch" />



											</tr>
											
											
											
											
											
											<tr>
												<td colspan="1" width="25%"><label class="set"
													name="date" id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label>
												<font color="red">*</font>:</td>
												<td colspan="1"><s:if test="isApprove">

													<s:textfield size="20" name="suggDate" readonly="true"
														onkeypress="return numbersWithHiphen();" />
												</s:if><s:else>
													<s:textfield size="20" name="suggDate"
														onkeypress="return numbersWithHiphen();" />
													<s:a
														href="javascript:NewCal('paraFrm_suggDate','DDMMYYYY');">
														<img src="../pages/images/recruitment/Date.gif"
															class="iconImage" height="16" align="absmiddle"
															width="16">
													</s:a>
												</s:else></td>
												<td width="25%"><label class="set" name="sts"
													id="sts" ondblclick="callShowDiv(this);"><%=label.get("sts")%></label>
												: <s:select theme="simple" name="suggestionFlag"
													list="#{'P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded'}"
													disabled="true"></s:select> <s:hidden name="hiddenStatus" />
												</td>


											</tr>
											
											<tr>

												<td colspan="1" width="25%"><label class="set"
													name="subject.of.suggestion" id="subject.of.suggestion"
													ondblclick="callShowDiv(this);"><%=label.get("subject.of.suggestion")%></label>
												<font color="red">*</font>:</td>
												<td colspan="1"><s:if test="isApprove">
													<s:textfield name="suggestion" size="55" maxlength="250"
														readonly="true" />
												</s:if> <s:else>
													<s:textfield name="suggestion" size="55" maxlength="250"
														onkeypress="return allCharacters();" />
												</s:else></td>
											</tr>
											
											
											<tr>
												<td colspan="4">What is your Suggestion ?</td>
												<td>
											</tr>
											
											
											<tr>
												<td colspan="4" width="100%">Be Specific -Describe the present method and your suggested improvement and how it can be implemented:</td>
											</tr>
											<tr><td colspan="4"><table width="100%" border="0" cellpadding="2"
													cellspacing="2" >
													
													<tr>

														<s:if test="isApprove">
															<td colspan="4" width="100%"><s:textarea
																name="suggimple" rows="4" cols="70" readonly="true" />
														</s:if>
														<s:else>
															<td colspan="4" width="100%"><s:textarea
																name="suggimple" rows="4" cols="70"
																onkeyup="return callLength('isappcount');" /> Remaining chars (Max:700)<s:textfield
																name="isappcount" readonly="true" size="5"></s:textfield></td>
														</s:else>

													</tr>
													<tr>
														<td colspan="4" width="100%">How will your suggestion improve the present or benefit the company ? Be Specific:

														</td>
													</tr>
													<tr>

														<s:if test="isApprove">
															<td colspan="4" width="100%"><s:textarea
																name="suggimprove" rows="4" cols="70" readonly="true" />
															</td>
														</s:if>
														<s:else>
															<td colspan="4" width="100%"><s:textarea
																name="suggimprove" rows="4" cols="70"
																onkeyup="return callLength1('improvecount');" /> Remaining chars (Max:700)<s:textfield
																name="improvecount" readonly="true" size="5"></s:textfield></td>
														</s:else>

													</tr>
													<tr>
														<td colspan="4" width="100%"><b> ELIGIBILITY REQUIREMENTS:
														</b></td>
													</tr>

													<tr>
														<td colspan="4" width="100%">1.Your suggestion must propose improvements in specific way. It must be timely,practical and state how improvements can be made and implemented.</td>
													</tr>
													<tr>
														<td colspan="4" width="100%">2.Suggestions are not eligible if they concern personal grievance or Complaints.</label>
														</td>
													</tr>
													<tr>
														<td colspan="4" width="100%">3.If the same suggestion is submitted by two or more person separately, the first one received is eligible, other or duplicate are not eligible.</td>
													</tr>
													<tr>
														<td colspan="4" width="100%">4.Suggestions will be evaluated and recommendations are made by the Management.</td>
													</tr>
													<tr>
														<td colspan="4" width="100%">5.The method used to evaluate a suggestion rests with the Management.</td>
													</tr>
											
											
											
											</table>
											</td></tr>										
											
											
											
													
													
													
													
													</table>
										</td></tr>
										
										
										
											
										<tr>
										<td colspan="5" class="formhead">
										<s:if test="listFlag">
										<strong class="forminnerhead">Suggestion Application Approval Details List:
												</strong>
										</s:if>
										<s:elseif test="isApprove">
										<strong class="forminnerhead">Suggestion Application Approval Details List:
												</strong>
										</s:elseif>
										</td>
										</tr>
										<%
															int j = 0;
															%>

										<tr>
											<td><s:if test="isApprove" >
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" >
													<tr>
														<td class="formtext">
														<table width="100%" border="0" cellpadding="2"
															cellspacing="2" class="sortable">
															<tr>
																<td width="5%" class="formth"><label class="set"
																	name="sr.no." id="sr.no."
																	ondblclick="callShowDiv(this);"><%=label.get("sr.no.")%></label></td>
																<td width="25%" class="formth"><label class="set"
																	name="approver.name" id="approver.name"
																	ondblclick="callShowDiv(this);"><%=label.get("approver.name")%></label></td>
																<td width="20%" class="formth"><label class="set"
																	name="approved.date" id="approved.date"
																	ondblclick="callShowDiv(this);"><%=label.get("approved.date")%></label></td>
																<td width="10%" class="formth"><label class="set"
																	name="sts" id="status1"
																	ondblclick="callShowDiv(this);"><%=label.get("sts")%></label></td>
																<td width="30%" class="formth"><label class="set"
																	name="comments" id="comments"
																	ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>



															</tr>
															

															<s:iterator value="approveList">


																<tr>

																	<td class="sortableTd" width="5%" align="center"><%=++j%></td>
																	<td class="sortableTd" width="25%"><s:property
																		value="apprName" /></td>
																	<td class="sortableTd" width="20%" align="left"><s:property
																		value="apprDate" /></td>
																	<td class="sortableTd" width="10%" align="left"><s:property
																		value="approvalStatus" /></td>
																	<td class="sortableTd" width="30%" align="left"><s:property
																		value="apprComments" />&nbsp;</td>


																</tr>


															</s:iterator>


														</table>

														</td>
													</tr>
												</table>
											</s:if>
											<s:elseif test="listFlag">
											<table width="100%" border="0" cellpadding="0"
													cellspacing="0" >
													<tr>
														<td class="formtext">
														<table width="100%" border="0" cellpadding="2"
															cellspacing="2" class="sortable">
															<tr>
																<td width="5%" class="formth"><label class="set"
																	name="sr.no." id="sr.no."
																	ondblclick="callShowDiv(this);"><%=label.get("sr.no.")%></label></td>
																<td width="25%" class="formth"><label class="set"
																	name="approver.name" id="approver.name"
																	ondblclick="callShowDiv(this);"><%=label.get("approver.name")%></label></td>
																<td width="20%" class="formth"><label class="set"
																	name="approved.date" id="approved.date"
																	ondblclick="callShowDiv(this);"><%=label.get("approved.date")%></label></td>
																<td width="10%" class="formth"><label class="set"
																	name="sts" id="status1"
																	ondblclick="callShowDiv(this);"><%=label.get("sts")%></label></td>
																<td width="30%" class="formth"><label class="set"
																	name="comments" id="comments"
																	ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>



															</tr>
															
															<s:iterator value="approveList">


																<tr>

																	<td class="sortableTd" width="5%" align="center"><%=++j%></td>
																	<td class="sortableTd" width="25%"><s:property
																		value="apprName" /></td>
																	<td class="sortableTd" width="20%" align="left"><s:property
																		value="apprDate" /></td>
																	<td class="sortableTd" width="10%" align="left"><s:property
																		value="approvalStatus" /></td>
																	<td class="sortableTd" width="30%" align="left">
																	
																	 <textarea name="comme" id="<%="comme"+(j-1)%>" 
																	 rows="2" readonly="true"
															cols="30"><s:property value="apprComments" />
															</textarea></td>
														<td>
																		<img src="../pages/images/zoomin.gif" height="12"
										align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('<%="comme"+(j-1)%>','comments','readonly','','900');">
										</td>						

																</tr>


															</s:iterator>


														</table>

														</td>
													</tr>
												</table>
											
											</s:elseif>
											
											
											
											
											
											</td>
										</tr>
									</table>

									</td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
		<s:hidden name="listFlag"  /> 
	
		<s:hidden name="source" id="source" />
		 
</s:form>

<script type="text/javascript">

function deletefun()

{

  		if(document.getElementById('paraFrm_suggcode').value=="")
	   {
		   alert("Please select a record to delete");
		   return false;
	   } 
	    if(!(document.getElementById('paraFrm_suggestionFlag').value=="P"||document.getElementById('paraFrm_suggestionFlag').value==""))
			 {
	  			alert("You can not delete suggestions application.");
				 return false;
			 }
      var conf=confirm("Do you really want to delete this record?");
  			if(conf) 
  			{
  			 
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
  		
  		return true;

}

function validate(type)
 {
 if(type=="update"){
	
	if(document.getElementById('paraFrm_suggcode').value==""){
  			alert("Please select a record to update  !");
  			return false;
	
  		}
  		
  		if(!(document.getElementById('paraFrm_suggestionFlag').value=="P"||document.getElementById('paraFrm_suggestionFlag').value==""))
			 {
	  			alert("You can modify only pending applications.");
				 return false;
			 }
		
	}
	 var empName=document.getElementById('employee').innerHTML.toLowerCase();
     var empId = document.getElementById('paraFrm_ecode').value;
  	 if(empId=="")
	 {
	   alert("Please select "+empName+".");
	   return false;
	 }
	  var suggimprove = document.getElementById('paraFrm_suggimprove').value;
	   var suggimple = document.getElementById('paraFrm_suggimple').value;
	   var empName=document.getElementById('employee').innerHTML.toLowerCase();	 
  	 if(suggimprove!="")
	 {
	   if(suggimprove.length>700)
	   {
	   alert("Please Enter Suggestion improvement Description less than 700 characters..!");
	   return false;
	   }
	  
	 }
	  if(suggimple!="")
	 {
	
	   if(suggimple.length>700)
	   {
	   alert("Please Enter Suggestion implementation Description less than 700 characters..!");
	   return false;
	   }
	 
	 }
 
    var fieldName = ["paraFrm_suggDate","paraFrm_suggestion"];
	var lableName = ["approved.date","subject.of.suggestion"];
	var badflags=["select","enter"];
	 var fieldName1 = ["paraFrm_suggestion"];

     if(!validateBlank(fieldName,lableName,badflags))
      {  return false; }
     if(!f9specialchars(fieldName1))
      {
              return false;
       }
      if(!validateDate('paraFrm_suggDate', "approved.date"))
		 {   return false; }
		     
	
		if(type=="add")
	{
		if(document.getElementById('paraFrm_suggcode').value==""){
		     return true;  		}
		 else
		 {
		  alert("Please click on 'Update' button");
		  return false;
		 }
		  		
    }
		
	
 }
 
  
 autoDate();
 function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
		if ( tMonth < 10) tMonth = "0"+tMonth;
		if ( tDate < 10) tDate = "0"+tDate;
		if(document.getElementById('paraFrm_suggcode').value=="")
	document.getElementById("paraFrm_suggDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
}
 function callLength(type){ 

     // alert('sai');
 
 if(type=='isappcount'){
	
			var cmt =document.getElementById('paraFrm_suggimple').value;
			var remain = 700 - eval(cmt.length);
			document.getElementById('paraFrm_isappcount').value = remain;
			
				if(eval(remain)< 0){
			document.getElementById('paraFrm_suggimple').style.background = '#FFFF99';
			
			}else document.getElementById('paraFrm_suggimple').style.background = '#FFFFFF';
		
		}
 
 
 }
 function callLength1(type1){
       var suggimprove = document.getElementById('paraFrm_suggimprove').value;
	   var suggimple = document.getElementById('paraFrm_suggimple').value;
 
 if(type1=='improvecount'){
	
			var cmt = document.getElementById('paraFrm_suggimprove').value;
			var remain = 700 - eval(cmt.length);
			document.getElementById('paraFrm_improvecount').value = remain;
			
			if(eval(remain)< 0){
			document.getElementById('paraFrm_suggimprove').style.background = '#FFFF99';
			
			}else document.getElementById('paraFrm_suggimprove').style.background = '#FFFFFF';
			
	
		}
 
 
 }
 
 function callBack()
 {
 		document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		document.getElementById('paraFrm').submit();
 }
 
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>



