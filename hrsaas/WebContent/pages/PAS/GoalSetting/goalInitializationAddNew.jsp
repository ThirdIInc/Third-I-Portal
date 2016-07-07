<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<title>Goal Initialization</title>
</head>
<body>
<s:form action="GoalInitialization" id="paraFrm" theme="simple">
	<table width="100%" cellspacing="1" cellpadding="2" border="0"
		align="right" class="formbg">
		<s:hidden name="gStatus" />
		<s:hidden name="dupData" />
		<s:hidden name="editFlag" />
		<s:hidden name="eligibleEmployeeReportFlag" />
		<s:hidden name="goalId" id="paraFrm_goalId" />
		<tr>
			<td>
			<table width="100%" cellspacing="2" cellpadding="2" border="0"
				align="center" class="formbg">
				<tr>
					<s:if test="editFlag">
						<td valign="bottom" class="txt"><strong class="text_head"><img
							height="25" width="25"
							src="../pages/images/recruitment/review_shared.gif"></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Edit
						Goal</strong></td>

					</s:if>
					<s:else>

						<td valign="bottom" class="txt"><strong class="text_head"><img
							height="25" width="25"
							src="../pages/images/recruitment/review_shared.gif"></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Goal
						Initialization</strong></td>
					</s:else>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table>
				<tr id="trButton">
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="paraId" />
		<tr>
			<td colspan="3">
			<table width="100%" cellspacing="1" cellpadding="2" border="0"
				class="formbg">

				<tr>
					<td width="20%" class="formtext"><label class="set"
						id="goalPeriod" name="goalPeriod" onDblClick="callShowDiv(this);"><%=label.get("goalPeriod")%></label>
					:<font color="red">*</font></td>
					<td><s:textfield name="goalName" size="30"></s:textfield></td>
					<!-- <td><label name="appraisal.code" class="set"
						id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
					</td>
					<td><s:textfield name="appraisalName" readonly="true"
						size="30" theme="simple" /> <img
						src="../pages/images/recruitment/search2.gif" id="ctrlHide"
						height="16" align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'GoalInitialization_f9AppCal.action'); ">
					<s:hidden name="appraisalCode" /></td> -->
				</tr>
				<tr>
					<td><label class="set" id="goalFromDate" name="goalFromDate"
						onDblClick="callShowDiv(this);"><%=label.get("goalFromDate")%></label>
					:<font color="red">*</font></td>
					<td><s:textfield name="goalfromDate" maxlength="10" size="30"
						theme="simple" onkeypress="return numbersWithHiphen();"></s:textfield>
					<a href="javascript:NewCal('paraFrm_goalfromDate','DDMMYYYY');">
					<img id="ctrlHide" height="16" width="16" border="0"
						align="absmiddle" src="../pages/images/recruitment/Date.gif"
						class="iconImage"></a></td>
					<td><label class="set" id="goalToDate" name="goalToDate"
						onDblClick="callShowDiv(this);"><%=label.get("goalToDate")%></label>
					:<font color="red">*</font></td>
					<td><s:textfield name="goaltoDate" maxlength="10" size="30"
						theme="simple" onkeypress="return numbersWithHiphen();"></s:textfield>
					<a href="javascript:NewCal('paraFrm_goaltoDate','DDMMYYYY');"
						id="paraFrm_"> <img id="ctrlHide" height="16" width="16"
						border="0" align="absmiddle"
						src="../pages/images/recruitment/Date.gif" class="iconImage"></a></td>
				</tr>
				<tr>
					<td width="23%"><label class="set" id="goalStatus"
						name="goalStatus" onDblClick="callShowDiv(this);"><%=label.get("goalStatus")%></label>
					:</td>
					<td><s:select theme="simple" name="goalPublishStatus"
						disabled="false" cssStyle="width:165"
						list="#{'1':'Draft','2':'Published'}" /></td>
					<td width="23%"><label class="set" id="reporting.type"
						name="reporting.type" onDblClick="callShowDiv(this);"><%=label.get("reporting.type")%></label>
					:</td>
					<td><s:select theme="simple" name="reportingType"
						cssStyle="width:165"
						list="#{'reporting':'Common Reporting','goal':'Goal Reporting'}" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" cellspacing="2" cellpadding="2" border="0"
				class="formbg">

				<tr>
					<td width="5%" class="formth"><label class="set" id="dateSrno"
						name="dateSrno" onDblClick="callShowDiv(this);"><%=label.get("dateSrno")%></label></td>
					<td width="55%" align="left" class="formth"><label class="set"
						id="goalReviewDate" name="goalReviewDate"
						onDblClick="callShowDiv(this);"><%=label.get("goalReviewDate")%></label></td>
					<td width="30%" align="left" class="formth"><label class="set"
						id="reviewWeightage" name="reviewWeightage"
						onDblClick="callShowDiv(this);"><%=label.get("reviewWeightage")%></label></td>
					<td width="10%" align="left" class="formth"><input
						type="button" class="add" value="Add Row" onclick="addDateRow();">
					</td>
				</tr>
				<%
				int k = 1;
				%>
				<%
				int c = 0;
				%>
				<s:iterator value="reviewDateList">
					<tr>
						<td class=sortableTD width="5%"><%=k%></td>
						<td class="sortableTD" width="55%"><s:textfield
							name="reviewDate" id='<%="reviewDate"+c%>' size="30"
							onkeypress="return numbersWithHiphen();" maxlength="10" /> <a
							href="javascript:NewCal('<%="reviewDate"+c%>','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" id="ctrlHide"
							class="iconImage" height="16" align="absmiddle" width="16">
						</a></td>
						<td class="sortableTD" width="30%"><s:textfield
							name="reviewWeightage" id='<%="reviewWeightage"+c%>' size="30"
							onkeypress="return numbersOnly();"
							onchange="javascript:addreviewWeightage(this)" maxlength="3" /></td>
						<td align="center" width="10%" class="sortableTD" id="ctrlHide">
						<input type="button" class="rowDelete" title="Delete Record"
							onclick="callForDelete(<%=c%>)" /></td>

					</tr>
					<%
						k++;
						c++;
					%>
				</s:iterator>

				<tr>
					<td width="5%">&nbsp;</td>
					<!--<td>Total</td>
							<td><s:property value="totalreviewWeight" />&nbsp;
								<s:hidden name="totalreviewWeight" /></td>-->



					<td width="55%" align="right">Total</td>
					<td width="30%"><s:textfield cssStyle="border: none;"
						name="totalreviewWeight" id="totalreviewWeight" size="30"
						onkeypress="return numbersOnly();" maxlength="3" readonly="true" /></td>


					<td width="10%">&nbsp;</td>
				</tr>

				<input type="hidden" id="count" name="count" value="<%=c%>" />
			</table>
			</td>
		</tr>

		<!-- Updated By Anantha Lakshmi -->
		<tr>
			<td>
			<table width="100%" border="0" class="formbg">

				<tr width="25%" colspan="1">
					<td align="left" width="20%"><label class="set"
						id="isAchievedFlagReq" name="isAchievedFlagReq"
						onDblClick="callShowDiv(this);"><%=label.get("isAchievedFlagReq")%></label>
					</td>
					<td><s:checkbox name="isAchievedFlagReq" /></td>
				</tr>
				<tr width="25%" colspan="1">
					<td align="left" width="20%"><label class="set" id="isSignOff"
						name="isSignOff" onDblClick="callShowDiv(this);"><%=label.get("isSignOff")%></label>
					</td>
					<td><s:checkbox name="isSignOffRequired" /></td>
				</tr>
				<tr width="30%" colspan="1">
					<td align="left" width="20%"><label class="set"
						id="isEscalation" name="isEscalation"
						onDblClick="callShowDiv(this);"><%=label.get("isEscalation")%></label>
					</td>
					<td><s:checkbox name="isEscalation" id="isEscalationId"
						onclick="validateEscalationFlag()" /></td>
				</tr>


				<tr>
					<td colspan="3">
					<table id="showEltMailId">
						<tr>
							<td align="left" width="30%"><label class="set"
								id="escalationMailId1" name="escalationMailId1"
								onDblClick="callShowDiv(this);"><%=label.get("escalationMailId1")%></label>
							</td>
							<td><s:textarea cols="40" rows="2" name="escalationMailId"
								id="escalationMailId" /></td>
						</tr>
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>

		<tr>

			<td>
			<table width="100%" cellspacing="1" cellpadding="2" border="0"
				class="formbg">
				<tr>
					<td><strong>Goal Rating Details</strong></td>
				</tr>
				<!-- <tr>

					<td><label name="appraisal.code" class="set"
						id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
					</td>
					<td><s:textfield name="appraisalName" readonly="true"
						size="30" theme="simple" /> <img
						src="../pages/images/recruitment/search2.gif" id="ctrlHide"
						height="16" align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'GoalInitialization_f9AppCal.action'); ">
					<s:hidden name="appraisalCode" /></td>
					
					<td align="left"><label class="set" id="goalWt" name="goalWt"
						onDblClick="callShowDiv(this);"><%=label.get("goalWt")%> </label>:<font
						color="red">*</font></td>
					<td><s:textfield name="goalWeightage" id="goalWeightage"
						size="30" onchange="javascript:appraisalWeightage()" maxlength="3"
						onkeypress="return numbersOnly();" /></td>
						
						
						
						
					 <td align="left"><label class="set" id="appraisalWeightage"
						name="appraisalWeightage" onDblClick="callShowDiv(this);"><%=label.get("appraisalWeightage")%>
					:</label></td>
					<td><s:textfield name="appWeightage" id="appWeightage"
						size="30" readonly="true" cssStyle="background-color: #F2F2F2;" /></td> 
				</tr>-->
				<s:hidden name="appWeightage" id="appWeightage" />
				<s:hidden name="goalWeightage" id="goalWeightage" value="100" />

				<tr>
					<td align="left"><label class="set" id="selfAssWeightage"
						name="selfAssWeightage" onDblClick="callShowDiv(this);"><%=label.get("selfAssWeightage")%>
					</label>:<font color="red">*</font></td>
					<td><s:textfield name="selfAsstWeightage"
						id="selfAsstWeightage" size="30"
						onchange="javascript:selfAssestmentWeightage()" maxlength="2"
						onkeypress="return numbersOnly();" /></td>
					<td align="left"><label class="set" id="mgerAsstWeightage"
						name="mgerAsstWeightage" onDblClick="callShowDiv(this);"><%=label.get("mgerAsstWeightage")%>
					:</label></td>
					<td><s:textfield name="managerAsstWeightage"
						id="managerAsstWeightage" size="30" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>

				<tr>
					<td align="left"><label class="set" id="multiManagerRating"
						name="multiManagerRating" onDblClick="callShowDiv(this);"><%=label.get("multiManagerRating")%>
					:</label></td>
					<td><s:if test="multipleManagerRatingRadio">
						<input type="radio" id='final' value='F'
							name="multipleManagerRatingRadio" checked="checked" /> Final Rating
							<input type="radio" id='avg' value='A'
							name="multipleManagerRatingRadio" /> Average Rating
						</s:if> <s:else>
						<input type="radio" id='final' value='F'
							name="multipleManagerRatingRadio" /> Final Rating
							<input type="radio" id='avg' value='A'
							name="multipleManagerRatingRadio" checked="checked" /> Average Rating
						</s:else></td>
				</tr>
				<tr>

					<td align="left"><label class="set" id="ratingrangeupto"
						name="ratingrangeupto" onDblClick="callShowDiv(this);"><%=label.get("ratingrangeupto")%>
					</label>:<font color="red">*</font></td>
					<td><s:textfield name="ratingrangeupto" size="30" maxlength=""
						onkeypress="return numbersOnly();" /></td>
					<td align="left"><label class="set" id="ratingrangedescrit"
						name="ratingrangedescrit" onDblClick="callShowDiv(this);"><%=label.get("ratingrangedescrit")%>
					</label>:<font color="red">&nbsp;</font></td>
					<td><s:textarea cols="40" rows="2" name="ratingrangedescrp"
						id="ratingrangedescrp" /></td>
				</tr>
				<s:if test="calcRatingFlag">
					<tr>
						<td align="center" colspan="4" id="ctrlShow"><input
							type="button" value="Calculate Rating" class="token"
							onclick="calcRatings()"> <input type="button"
							value="     Report     " class="token" onclick="reportFun()"></td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellspacing="2" cellpadding="2" border="0"
				class="formbg">
				<tr width="25%" colspan="1">
					<td align="left" width="20%"><label class="set"
						id="isCategoryReq" name="isCategoryReq"
						onDblClick="callShowDiv(this);"><%=label.get("isCategoryReq")%></label>
					</td>
					<td><s:checkbox name="isCategoryReq" id="isCategoryReqID"
						onclick="isChk()" /></td>
				</tr>

						<tr>
							<td colspan="2">
							<table id="showCategory" width="100%">
								<tr>
									<td width="20%" id="ctrlHide"><label class="set"
										name="category" id="category" ondblclick="callShowDiv(this);"><%=label.get("category")%></label>:
									<font color="red">*</font></td>
									<td><s:textfield size="30" name="category1" id="category1" />
									<s:hidden name="hiddenEdit" /></td>

									<td id="ctrlHide"><label class="set" name="catWeightage"
										id="catWeightage" ondblclick="callShowDiv(this);"><%=label.get("catWeightage")%></label>:
									<font color="red">*</font></td>
									<td><s:textfield name="categoryWeightage" size="30"
										onkeypress="return numbersOnly();" maxlength="3" /></td>

								</tr>

								<tr>
									<!--  <td width="25%" colspan="1">&nbsp;</td>-->
									<td colspan="4" width="10%" class="sortableTD" id="ctrlHide"
										align="center"><s:submit cssClass="add" theme="simple"
										value="Add" action="GoalInitialization_addItem" 
										onclick="return callAdd()" /></td>
								</tr>

								<tr>
									<td width="100%" colspan="4">
									<table class="formbg" width="100%">
										<tr>
											<!--   srNo -->
											<td align="center" class="formth" width="10%"><label
												class="set" name="srNo" id="srNo"
												ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
											<!--   category  -->
											<td align="center" class="formth" width="25%"><label
												class="set" name="category" id="category"
												ondblclick="callShowDiv(this);"><%=label.get("categoryName")%></label>
											</td>
											<td align="center" class="formth" width="25%"><label
												class="set" name="categoryWeightage" id="categoryWeightage1"
												ondblclick="callShowDiv(this);"><%=label.get("categoryWeightage")%></label>
											</td>
											<td class="formth" class="formth" width="20%" id="ctrlHide">
											<label class="set" class="token" theme="simple"
												name="editRemove" id="editRemove"
												ondblclick="callShowDiv(this);"><%=label.get("editRemove")%></label>&nbsp;
											</td>
										</tr>

										<%
										int count1 = 0;
										%>
										<%!int d1 = 0;%>
										<%
										int ii = 0;
										%>
										<%!int val = 0;%>

										<s:iterator value="list">

											<tr <%if(count1%2==0){
															%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count1++; %>
												onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="category"/>',<s:property value="srNo"/>);">

												<td width="10%" align="center" class="sortableTD"><%=++ii%>
												<input type="hidden" name="srNo" value="<%=ii%>" /></td>
												<td class="sortableTD"><s:property value="category" />&nbsp;
												<s:hidden name="category" /></td>
												<td class="sortableTD"><s:property value="catWeightage" />&nbsp;
												<s:hidden name="catWeightage" /></td>
												<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
												<td width="10%" align="center" nowrap="nowrap"
													class="sortableTD" id="ctrlHide"><input type="button"
													class="rowEdit" title="Edit Record"
													onclick="callForEdit11('<s:property  value="category" />','<%=ii%>','<s:property value="catWeightage" />')" />
												<input type="button" class="rowDelete" title="Delete Record"
													id="confChkop<%=ii%>" name="confChkop"
													onclick="callForDelete2('<%=ii%>')" /></td>

											</tr>

										</s:iterator>
										<%
										d1 = ii;
										%>
										<tr>
											<td>&nbsp;</td>
											<td>Total</td>
											<td><s:property value="totalCatWeightage" />&nbsp; <s:hidden
												name="totalCatWeightage" /></td>
											<td>&nbsp;</td>
										</tr>

										<input type="hidden" id="categoryCnt" name="categoryCnt"
											value="<%=d1%>" />
										<s:hidden name="subcode" />
										<s:hidden name="hiddenCategoryCheck" />
										<s:hidden name="tableLength" />
									</table>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">

						<!--		              <tr>-->
						<!--		              	<td>-->
						<!---->
						<!--		              	 Applicable for whole organization : <s:checkbox name="eligibleForGoal" id="eligibleForGoal" onclick="isEligiblechk()"></s:checkbox>-->
						<!--  				 		</td>-->
						<!--		              </tr>-->


						<tr id="showEligibleEmployee">
							<td colspan="3" width="100%"><STRONG>Set
							Eligibility Criteria : <font color="red">*</font></STRONG></td>
						</tr>
						<tr id="showEligibleEmployee1">
							<td nowrap="nowrap" colspan="2" id="mapGoalComp"><s:hidden
								name="deleteKey" id="deleteKey" /> <a href="#"
								onclick="callFun();" style="cursor: pointer;"
								title="Click here to configure Eligible Employee"> <u>Click
							here to configure Eligible Employees</u></a> <input type="submit"
								value="     Report     " class="token"
								onclick="eligibleEmployeeReportFun()"></td>
						</tr>


					</table>
					<!--<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">

								              <tr>
								              	<td>
						
								              	 Applicable for whole organization : <s:checkbox name="eligibleForGoal" id="eligibleForGoal" onclick="isEligiblechk()"></s:checkbox>
						  				 		</td>
								              </tr>

						<tr id="showEligibleEmployee">
							<td colspan="3" width="100%"><STRONG>Set
							Eligibility Criteria : <font color="red">*</font></STRONG></td>
						</tr>
						<tr id="showEligibleEmployee1">
							<td nowrap="nowrap" colspan="2" id="mapGoalComp"><s:hidden
								name="deleteKey" id="deleteKey" /> <a href="#"
								onclick="callFun();" style="cursor: pointer;"
								title="Click here to configure Eligible Employee"> <u>Click
							here to configure Eligible Employees</u></a></td>
						</tr>

					</table>
					--></td>
				<tr>
					<td colspan="3">
					<table>
						<tr id="trButton">
							<td width="70%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</s:form>
</body>
</html>
<script type="text/javascript">
function addreviewWeightage(check)
{
	var totalRows=document.getElementById('count').value;
	//alert("totalRows : "+totalRows);
	var totalReviewWeightage=0
	var totalReviewWeightage = parseFloat(totalReviewWeightage);
	for(var i=0;i<totalRows;i++){
	//alert(""+document.getElementById('reviewWeightage'+i).value);
	var val1=document.getElementById('reviewWeightage'+i).value;
	var val1 = parseFloat(val1);
	if(val1=="")
	{
		val1=0;
	}
	totalReviewWeightage=eval(totalReviewWeightage)+eval(val1);
	var totalReviewWeightage = parseFloat(totalReviewWeightage);
	}
	//alert("totalReviewWeightage :: after  ::  "+totalReviewWeightage);
	document.getElementById('totalreviewWeight').value=totalReviewWeightage;
	
	//alert("check :: "+check.value);
	/*var totalReviewWeightage=document.getElementById('totalreviewWeight').value;
	if(totalReviewWeightage=="")
	totalReviewWeightage=0;
	//alert("totalReviewWeightage :: befor ::  "+totalReviewWeightage);
	totalReviewWeightage=eval(totalReviewWeightage)+eval(check.value);
	//alert("totalReviewWeightage :: after  ::  "+totalReviewWeightage);
	document.getElementById('totalreviewWeight').value=totalReviewWeightage;*/
	return true;
}



	function addDateRow(){
			document.getElementById("paraFrm").action='GoalInitialization_addDateRow.action';
			document.getElementById("paraFrm").submit();	
	}
	
	function draftFun()
	{
		try
	
		{
		try
		
			{	
				if(!validateSave()){
					return false;
				}
				}catch(e)
			{
				alert("Exception occured in draft function : "+e);
			}
			try
		
				{
					if(!checkReviewDate()){
						return false;
					}
					}catch(e)
				{
					alert("Exception occured in draft function : "+e);
				}
			var goalwgt=document.getElementById("goalWeightage").value;
			var apprwgt=document.getElementById("appWeightage").value;
			var selfAsstWt=document.getElementById("selfAsstWeightage").value;
			var managerAsstWt=document.getElementById("managerAsstWeightage").value;
			var totalWeightage=eval(goalwgt)+eval(apprwgt);
			var totalSelfManagerWt=eval(selfAsstWt)+eval(managerAsstWt);
			var ratingupto=document.getElementById("paraFrm_ratingrangeupto").value;
			var revwt=document.getElementById('totalreviewWeight').value;
			var catwt=document.getElementById('paraFrm_totalCatWeightage').value;
			
			if(document.getElementById('isEscalationId').checked){
				 document.getElementById('showEltMailId').style.display="";   		 
				 if(document.getElementById("paraFrm_reportingType").value!="goal"){
				 	alert("Please select reporting structure type is Goal Reporting ");
				 	return false;
				 }
			}	 
			/*if(goalwgt!="" && apprwgt!=""){
				if(totalWeightage != 100){
					alert("Total of goal weightage and appraisal weightage should be 100");
					return false;
					}
					
			}else*/ if(selfAsstWt!="" && managerAsstWt!=""){
			
				if(totalSelfManagerWt!=100){
					alert("Total of Self Assestment weightage and Manager Assestment weightage should be 100");
						return false;
					}
					
			}
			
		if(goalwgt=="")
		{
			alert("Please enter "+document.getElementById('goalWt').innerHTML.toLowerCase());
		  	document.getElementById('goalWeightage').focus();
		 	return false;		
		}
		
		if(revwt!="100")
		{
			alert("The total review weightage should not be greater than or less than 100");
		 	return false;		
		} 
		if(document.getElementById("isCategoryReqID").checked){	
		if(catwt!="100")
		{
			alert("The total category weightage should not be greater than or less than 100");
		 	return false;		
		} 
		}
		
		if(selfAsstWt=="")
		{
			alert("Please enter "+document.getElementById('selfAssWeightage').innerHTML.toLowerCase());
		  	document.getElementById('selfAssWeightage').focus();
		 	return false;		
		}
		if(ratingupto=="" || ratingupto=="0")
		{
			alert("Please enter "+document.getElementById('ratingrangeupto').innerHTML.toLowerCase()+" greater than zero");
		  	document.getElementById('paraFrm_ratingrangeupto').focus();
		 	return false
		}
					if(document.getElementById("isCategoryReqID").checked){		
						if(document.getElementById("categoryCnt").value=="" || document.getElementById("categoryCnt").value==0){ 
						    alert("Please add minimum one category in the list. ");
						}else{
							document.getElementById("paraFrm").action='GoalInitialization_save.action';
							document.getElementById("paraFrm").submit();
						}
					}else{
						document.getElementById("paraFrm").action='GoalInitialization_save.action';
						document.getElementById("paraFrm").submit();
					}
			
			
		}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
	}
	
	function publishFun()
	{	
		if(!validateSave()){
			return false;
		}
		if(!checkReviewDate()){
			return false;
		}
		
	  	var goalwgt=document.getElementById("goalWeightage").value;
			var apprwgt=document.getElementById("appWeightage").value;
			var selfAsstWt=document.getElementById("selfAsstWeightage").value;
			var managerAsstWt=document.getElementById("managerAsstWeightage").value;
			var totalWeightage=eval(goalwgt)+eval(apprwgt);
			var totalSelfManagerWt=eval(selfAsstWt)+eval(managerAsstWt);
			var ratingupto=document.getElementById("paraFrm_ratingrangeupto").value;
			var revwt=document.getElementById('totalreviewWeight').value;
			var catwt=document.getElementById('paraFrm_totalCatWeightage').value;
			
			if(document.getElementById('isEscalationId').checked){
				 document.getElementById('showEltMailId').style.display="";   		 
				 if(document.getElementById("paraFrm_reportingType").value!="goal"){
				 	alert("Please select reporting structure type is Goal Reporting ");
				 	return false;
				 }
			}	 
			
			
			/*if(goalwgt!="" && apprwgt!=""){
				if(totalWeightage != 100){
					alert("Total of goal weightage and appraisal weightage should be 100");
					return false;
					}
					
			}else*/if(selfAsstWt!="" && managerAsstWt!=""){
			
				if(totalSelfManagerWt!=100){
					alert("Total of Self Assestment weightage and Manager Assestment weightage should be 100");
						return false;
					}
					
			}
			
		if(goalwgt=="")
		{
			alert("Please enter "+document.getElementById('goalWt').innerHTML.toLowerCase());
		  	document.getElementById('goalWeightage').focus();
		 	return false;		
		}
		
		if(revwt!="100")
		{
			alert("The total review weightage should not be greater than or less than 100");
		 	return false;		
		} 
		if(document.getElementById("isCategoryReqID").checked){	
		if(catwt!="100")
		{
			alert("The total category weightage should not be greater than or less than 100");
		 	return false;		
		} 
		}
		 
		if(selfAsstWt=="")
		{
			alert("Please enter "+document.getElementById('selfAssWeightage').innerHTML.toLowerCase());
		  	document.getElementById('selfAssWeightage').focus();
		 	return false;		
		}
		if(ratingupto=="" || ratingupto=="0")
		{
			alert("Please enter "+document.getElementById('ratingrangeupto').innerHTML.toLowerCase()+" greater than zero");
		  	document.getElementById('paraFrm_ratingrangeupto').focus();
		 	return false
		}
					if(document.getElementById("isCategoryReqID").checked){		
						if(document.getElementById("categoryCnt").value=="" || document.getElementById("categoryCnt").value==0){ 
						    alert("Please add minimum one category in the list. ");
						}else{
						var conf=confirm("Do you really want to publish this goal?");
						  	if(!conf) 
						  	{
						  		return false;
						  	}
							document.getElementById("paraFrm").action='GoalInitialization_publishGoal.action';
							document.getElementById("paraFrm").submit();
						}
					}else{
						var conf=confirm("Do you really want to publish this goal?");
						  	if(!conf) 
						  	{
						  		return false;
						  	}
						document.getElementById("paraFrm").action='GoalInitialization_publishGoal.action';
						document.getElementById("paraFrm").submit();
					}
		
		
	}
	function editFun()
	{
		document.getElementById('paraFrm').action = "GoalInitialization_edit.action";
		document.getElementById('paraFrm').submit();
	}
		function unlockFun()
	{
	   var conf = confirm("Do You Want To Unlock?");
	   if(conf)
	    {
		document.getElementById('paraFrm').action = "GoalInitialization_editUnlock.action";
		document.getElementById('paraFrm').submit();
		}
	}
	function eligibleEmployee()
	{
		document.getElementById('paraFrm').action = "GoalInitialization_eligibleEmployee.action";
		document.getElementById('paraFrm').submit();
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'GoalInitialization_back.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
	
		try{
		document.getElementById('isEscalationId').checked=false;
		document.getElementById('paraFrm_isAchievedFlagReq').checked=false;
		document.getElementById('paraFrm_isSignOffRequired').checked=false;
		document.getElementById('isCategoryReqID').checked=false;
		
  	 	document.getElementById('paraFrm').action = "GoalInitialization_reset.action";
		document.getElementById('paraFrm').submit();
		}
		catch(e){
	     alert(e)	
	     }
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
	      	document.getElementById('paraFrm').action = 'GoalInitialization_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	function validateSave(){
		var fieldName  = [ "paraFrm_goalName","paraFrm_goalfromDate","paraFrm_goaltoDate"];
		var lableName  = ["goalPeriod","goalFromDate","goalToDate"];
		var types  = ["enter","enter","enter"];
		var fromDate = document.getElementById("paraFrm_goalfromDate").value;
		var toDate = document.getElementById("paraFrm_goaltoDate").value;
		var emailID=document.getElementById("escalationMailId").value;
		if(!validateBlank(fieldName, lableName,types)){
					return false;			  
				}
			if(document.getElementById("paraFrm_goalfromDate").value != ""){
				if(!validateDate("paraFrm_goalfromDate", "goalFromDate"))return false;
			}
			
			if(document.getElementById("paraFrm_goaltoDate").value != ""){
				if(!validateDate("paraFrm_goaltoDate", "goalFromDate"))return false;
			}
		if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_goalfromDate", "goalFromDate", "goalToDate")){
				return false;
		}
		if(emailID!="") {
			var mySplitResult = emailID.split(";");
			for(var i = 0; i < mySplitResult.length; i++){
				if(!validateEmailAddress(mySplitResult[i])){
						return false;
				}
			}
		} 
		
		return true;
	}
		
	function checkReviewDate(){
	try
	{
	
		var count =document.getElementById('count').value;
		var fromDate = document.getElementById("paraFrm_goalfromDate").value;
		
			if(count == 0 || count==""){
				alert("Please add minimum one review date in the list. ");
				return false;
			}
		for(var check=0;check <count; check++){
			if(document.getElementById('reviewDate'+check).value==""){
				alert("Please enter "+document.getElementById('goalReviewDate').innerHTML);
				document.getElementById('reviewDate'+check).focus();
				return false;
			}else{
				if(!validateDate("reviewDate"+check, "goalReviewDate"))return false;
				if(!dateDifferenceEqual(fromDate, document.getElementById('reviewDate'+check).value, "paraFrm_goalfromDate", "goalFromDate", "goalReviewDate")){
				return false;
		}
			}
		}
		return true;
		}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
	}
	 function callForDelete(id){
		try{
    	 document.getElementById('paraFrm_paraId').value = id;
		document.getElementById('paraFrm').action = "GoalInitialization_removeDate.action";
		document.getElementById('paraFrm').submit();
		}catch(e)
		 {
		 	alert("Exception occured in delete function : "+e);
		 } 
	}
  	isChk();
  	validateEscalationFlag();
	function isChk(){	
	  try{
		   if(document.getElementById('isCategoryReqID').checked){
		  		document.getElementById('showCategory').style.display="";   
		   }
		   else {
		   		document.getElementById('showCategory').style.display="none";
		   }		   
		 }catch(e){} 
		 
	}
	
	function callAdd(){
	    	var fields = ["category1"];
			var labels = ["category1"];
			var labels1 = ["categoryWeightage"];
			var selectFlag = ["enter","enter"];	
		  	var val=document.getElementById('category1').value;
		  	var val1=document.getElementById('paraFrm_categoryWeightage').value;
		  	
		  	if(val==""){
			  alert("Please enter category");
			  document.getElementById('category1').value='';
			  return false;
			}
		  	
		  	if(val1==""){ 
			  alert("Please enter category weightage");
			  document.getElementById('paraFrm_categoryWeightage').value=''; 
			  return false;  
			} 
			if(!f9specialchars(fields)){
		          return false;
		    }
		    document.getElementById('paraFrm').target = "main";
		    document.getElementById('isCategoryReqID').checked=true;
		    return true;
	}
	
    function callForDelete2(id){
    try{
    	 document.getElementById('paraFrm_paraId').value = id;
		 document.getElementById('paraFrm').action = "GoalInitialization_removeGoalsCategories.action";
		 document.getElementById('paraFrm').submit();
		 }catch(e)
		 {
		 	alert("Exception occured in delete function : "+e);
		 } 
		 
    }
    
	function callForEdit11(kvalue,rowNo,kval2){
	
	    document.getElementById("category1").value = kvalue;
	    document.getElementById("paraFrm_categoryWeightage").value = kval2;
	    document.getElementById('paraFrm_paraId').value=rowNo;  
	}  
	
	  function calcRatings(){	
		 document.getElementById('paraFrm').action = "GoalInitialization_calculateRating.action";
		 document.getElementById('paraFrm').submit();
    }
    function appraisalWeightage (){
                {
                        var val1 = parseFloat(document.getElementById("goalWeightage").value);
                      var ansD = document.getElementById("appWeightage");
                        ansD.value =(100 - val1 );
                }
            }
    function selfAssestmentWeightage (){
       {
       		var val2 =document.getElementById("selfAsstWeightage").value;
       		 if(val2=="")
             {
             	val2=0;
             }
               var val1 = parseFloat(val2);
               
             var ansD = document.getElementById("managerAsstWeightage");
            
               ansD.value =(100 - val1 );
       }
    }    
            
    function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'GoalInitialization_report.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}  
	
	function eligibleEmployeeReportFun()
	{
	document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'GoalInitialization_eligibleEmployeeReport.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function validateEscalationFlag(){
	  try{
			 if(document.getElementById('isEscalationId').checked){
			 document.getElementById('paraFrm_isSignOffRequired').checked =true;
				 document.getElementById('showEltMailId').style.display="";   		 
				 if(document.getElementById("paraFrm_reportingType").value!="goal"){
				 	alert("Please select reporting structure type is Goal Reporting ");
				 	document.getElementById('reportingType').focus();
				 }
		  	 }else{
		  	 	//document.getElementById('paraFrm_isSignOffRequired').checked ='';
		  	 	document.getElementById('showEltMailId').style.display="none";
		  	 }
		 
	  }catch(e){} 
	}  
	function validateEmailAddress (name) {
	var emailStr = name;
	if(emailStr=="")return true;
	var checkTLD        = 1;
	var knownDomsPat    = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	var emailPat		= /^(.+)@(.+)$/;
	var specialChars	= "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
	var validChars		= "\[^\\s" + specialChars + "\]";
	var quotedUser		= "(\"[^\"]*\")";
	var ipDomainPat		= /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	var atom			= validChars + '+';
	var word			= "(" + atom + "|" + quotedUser + ")";
	var userPat			= new RegExp("^" + word + "(\\." + word + ")*$");
	var domainPat		= new RegExp("^" + atom + "(\\." + atom +")*$");
	var matchArray		= emailStr.match(emailPat);
	if (matchArray==null) {
		alert("Email address seems incorrect (check @ and .'s)");
		return false;
	}
	var user			= matchArray[1];
	var domain			= matchArray[2];
	if(user.indexOf(".")!=-1 && user.split(".").length>2){
	 		alert("Only one dot is allowed in user name of email id");
			return false;
	}
	if(user.indexOf("_")!=-1 && user.split(".").length>1){
	 		alert("The username doesn't seem to be valid for email.");
			return false;
	}
	for (i=0; i<user.length; i++) {
		if (user.charCodeAt(i)>127 || user.indexOf("-")!=-1) {
			alert("The username contains invalid characters.");
			return false;
		}
	}

	if(domain.indexOf("-")!=-1 && domain.split("-").length>2){
	 		alert("The domain doesn't seem to be valid for email.");
			return false;
	}

	for (i=0; i<domain.length; i++) {
		if (domain.charCodeAt(i)>127 || domain.indexOf("_")!=-1) {
			alert("The domain name contains invalid characters.");
			return false;
   		}
	}


	if (user.match(userPat)==null) {
		alert("The username doesn't seem to be valid for email.");
		return false;
	}

	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
		for (var i=1;i<=4;i++) {
			if (IPArray[i]>255) {
				alert("Destination IP address is invalid!");
				return false;
		   }
		}
			return true;
	}

	var atomPat		= new RegExp("^" + atom + "$");
	var domArr		= domain.split(".");
	var len    		= domArr.length;
	
	var dot         = domain.indexOf(".");
	var hiphen		= domain.indexOf("-");
	
	if(dot>hiphen && (dot-hiphen)<2){
		alert("The domain name does not seem to be valid");
		return false;
	}if(dot<hiphen && (hiphen-dot)<2){
		alert("The domain name does not seem to be valid");
		return false;
	}if(len>3){
		alert("Maximum two dots are allowed in domain name");
		return false;
	}
	
	for (i=0;i<len;i++) {
		if (domArr[i].search(atomPat)==-1) {
			alert("The domain name does not seem to be valid");
			return false;
	   }
	}if(domain.indexOf("-")!=-1){
		if(domain.indexOf("-")<2){
			alert("The domain name does not seem to be valid");
			return false;
		}
	}

	if (len<2) {
		alert("This email address is missing a hostname!");
		return false;
	}
	
	return true;
}

function callFun()
{
	if(document.getElementById("paraFrm_goalId").value=="")
	{
		alert("Please Save Record");
		return false;
	}
	document.getElementById("paraFrm").action="GoalInitialization_eligibleEmployeeList.action";
	document.getElementById("paraFrm").submit();
}

/*isEligiblechk();
	function isEligiblechk(){	
	
	  try{
		 		    if(document.getElementById('eligibleForGoal').checked){
		  		document.getElementById("showEligibleEmployee").style.display='none';  
		  			document.getElementById("showEligibleEmployee1").style.display='none';   
		   }
		   else {
		   		document.getElementById("showEligibleEmployee").style.display='';
		   		document.getElementById("showEligibleEmployee1").style.display='';
		   } 
		 }catch(e){alert(e)} 
		 
	}*/
  </Script>
