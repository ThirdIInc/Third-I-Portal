<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<title>Competency Initialization</title>
</head>
<body>

<s:form action="CompetencyInitialization" id="paraFrm" theme="simple">
	<table width="100%" cellspacing="1" cellpadding="2" border="0"
		align="right" class="formbg">
		<tr>
			<td>
			<table width="100%" cellspacing="2" cellpadding="2" border="0"
				align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img	height="25" width="25"
						src="../pages/images/recruitment/review_shared.gif"></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Competency Initialization</strong></td>
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
				<s:hidden name="compId" id="paraFrm_compId" />
				<tr>
					<td width="20%" class="formtext">
						<label class="set" id="compPeriod" name="compPeriod" onDblClick="callShowDiv(this);"><%=label.get("compPeriod")%></label>:<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="compName" size="30"></s:textfield>
					</td>
					<!-- <td>
						<label name="appraisal.code" class="set" id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
					</td>
					<td>
						<s:textfield name="appraisalName" readonly="true" size="30" theme="simple" /> 
							<img src="../pages/images/recruitment/search2.gif" id="ctrlHide"
							height="16" align="absmiddle" width="17"
							onclick="javascript:callsF9(500,325,'CompetencyInitialization_f9AppCal.action'); ">
						
					</td>
					 -->
					 <s:hidden name="appraisalCode" />
					 <s:hidden name="appraisalName" />
				</tr>
				<tr>
					<td><label class="set" id="compFromDate" name="compFromDate" onDblClick="callShowDiv(this);"><%=label.get("compFromDate")%></label>:<font color="red">*</font></td>
					<td>
						<s:textfield name="compfromDate" maxlength="10" size="30" theme="simple" onkeypress="return numbersWithHiphen();"></s:textfield>
						<a href="javascript:NewCal('paraFrm_compfromDate','DDMMYYYY');">
							<img id="ctrlHide" height="16" width="16" border="0"
								align="absmiddle" src="../pages/images/recruitment/Date.gif"
								class="iconImage">
						</a>
					</td>
					<td><label class="set" id="compToDate" name="compToDate" onDblClick="callShowDiv(this);"><%=label.get("compToDate")%></label>:<font color="red">*</font></td>
					<td>
						<s:textfield name="comptoDate" maxlength="10" size="30" theme="simple" onkeypress="return numbersWithHiphen();"></s:textfield> 
						<a href="javascript:NewCal('paraFrm_comptoDate','DDMMYYYY');"
						id="paraFrm_"> <img id="ctrlHide" height="16" width="16"
						border="0" align="absmiddle"
						src="../pages/images/recruitment/Date.gif" class="iconImage"></a></td>
				</tr>
				<tr>
					<td width="23%"><label class="set" id="compStatus" name="compStatus" onDblClick="callShowDiv(this);"><%=label.get("compStatus")%></label>:</td>
					<td>
						<s:select theme="simple" name="compPublishStatus" disabled="true" cssStyle="width:165" list="#{'1':'Draft','2':'Published'}" /></td>
					<td width="23%"><label class="set" id="reporting.type" name="reporting.type" onDblClick="callShowDiv(this);"><%=label.get("reporting.type")%></label>:</td>
					<td><s:select theme="simple" name="reportingType" cssStyle="width:165" list="#{'reporting':'Common Reporting','goal':'Goal Reporting'}" /></td>
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
						id="compReviewDate" name="compReviewDate"
						onDblClick="callShowDiv(this);"><%=label.get("compReviewDate")%></label></td>
						<td width="30%" align="left" class="formth"><label class="set"
						id="reviewWeightage" name="reviewWeightage"
						onDblClick="callShowDiv(this);"><%=label.get("reviewWeightage")%></label></td>
					<td width="10%" align="left" class="formth"><input
						type="button" class="add" value="Add Row" onclick="addDateRow();">
					</td>
				</tr>
				<%
					int k = 1;
						int c = 0;
				%>
				<s:iterator value="reviewDateList">
					<tr>
						<td class=sortableTD width="5%"><%=k%></td>
						<td class="sortableTD" width="55%">
							<s:textfield name="reviewDate" id="<%="reviewDate"+c%>" size="30" onkeypress="return numbersWithHiphen();" maxlength="10" /> 
							<a  href="javascript:NewCal('<%="reviewDate"+c%>','DDMMYYYY');">
							    <img src="../pages/images/recruitment/Date.gif" id="ctrlHide"
								class="iconImage" height="16" align="absmiddle" width="16">
							</a>
						</td>
						<td class="sortableTD" width="30%"><s:textfield
							name="reviewWeightage" id="<%="reviewWeightage"+c%>" size="30"
							onkeypress="return numbersOnly();" onchange="javascript:addreviewWeightage(this)" maxlength="3" /></td>
						
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
					<td class=sortableTD width="5%">&nbsp;</td>
					 <td class=sortableTD width="55%" align="right">Total</td>
					<td class=sortableTD width="30%"><s:textfield
							cssStyle="border: none;"
							name="totalreviewWeight" id="totalreviewWeight" size="30"
							onkeypress="return numbersOnly();" maxlength="3"  readonly="true" /></td>
					
					
					<td class=sortableTD width="10%">&nbsp;</td>
					<!-- <td class=sortableTD width="55%" align="right">Total</td>
					<td class=sortableTD width="30%">
					<s:property value="totalreviewWeight"/><s:hidden
							name="totalreviewWeight"/></td>  
					<td class=sortableTD width="10%">&nbsp;</td> -->
				</tr>

				<input type="hidden" id="count" name="count" value="<%=c%>" />
			</table>
			</td>
		</tr>

		
		<tr>
			<td>
			<table width="100%" border="0" class="formbg">
				
				<!-- Catagory  -->
				 
				<tr width="25%" colspan="1">
					<td align="left" width="20%"><label class="set"
						id="isCategoryReq" name="isCategoryReq"
						onDblClick="callShowDiv(this);"><%=label.get("isCategoryReq")%></label>
					</td>
					<td><s:checkbox name="isCategoryReq" id="isCategoryReqID"
						onclick="isChk()" /></td>
				</tr>
				 
				
				<tr width="25%" colspan="1">
					<td align="left" width="20%"><label class="set" id="isSignOff"
						name="isSignOff" onDblClick="callShowDiv(this);"><%=label.get("isSignOff")%></label>
					</td>
					<td><s:checkbox name="isSignOffRequired" /></td>
				</tr>
				<tr width="30%" colspan="1">
					<td align="left" width="20%"><label class="set" id="isEscalation"
						name="isEscalation" onDblClick="callShowDiv(this);"><%=label.get("isEscalation")%></label>
					</td>
					<td><s:checkbox name="isEscalation" id="isEscalationId" onclick="validateEscalationFlag()" /></td>
				</tr>
				
				
				<tr>
					<td colspan="3">
						<table id="showEltMailId" >
							<tr>
								<td align="left" width="30%"><label class="set" id="escalationMailId1"
									name="escalationMailId1" onDblClick="callShowDiv(this);"><%=label.get("escalationMailId1")%></label>
								</td>
								<td>
									<s:textarea cols="40" rows="2" name="escalationMailId" id="escalationMailId" />
								</td>
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
					<td colspan="2"><strong>Competency Rating Details</strong></td>
				</tr>
			<!--  	<tr>

					<td align="left"><label class="set" id="compWt" name="compWt"
						onDblClick="callShowDiv(this);"><%=label.get("compWt")%> </label>:<font
						color="red">*</font></td>
					<td>
						<s:textfield name="compWeightage" id="compWeightage"
							size="30" onchange="javascript:appraisalWeightage()" maxlength="3"
							onkeypress="return numbersOnly();" />
					</td>
					<td align="left"><label class="set" id="appraisalWeightage"
						name="appraisalWeightage" onDblClick="callShowDiv(this);"><%=label.get("appraisalWeightage")%>
					:</label></td>
					<td><s:textfield name="appWeightage" id="appWeightage"
						size="30" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
			-->
				<s:hidden name="compWeightage" id="compWeightage" value="100"/>
				<s:hidden name="appWeightage" id="appWeightage" />
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
					<td>
						<s:if test="multipleManagerRatingRadio">
							<input type="radio" id='final' value='F'
								name="multipleManagerRatingRadio" checked="checked" /> Final Rating
							<input type="radio" id='avg' value='A'
								name="multipleManagerRatingRadio" /> Average Rating
						</s:if> 
						<s:else>
							<input type="radio" id='final' value='F'
								name="multipleManagerRatingRadio" /> Final Rating
							<input type="radio" id='avg' value='A'
								name="multipleManagerRatingRadio" checked="checked" /> Average Rating
						</s:else>
					</td>
				</tr>
				<!--
				<tr>
					<td align="left"><label class="set" id="ratingrangedescrit" name="ratingrangedescrit"
						onDblClick="callShowDiv(this);"><%=label.get("ratingrangedescrit")%> </label>:<font
						color="red">&nbsp;</font></td>
					<td><s:textarea cols="40" rows="2" name="ratingrangedescrp" id="ratingrangedescrp" /></td>
					
				</tr> -->
				
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
			<table id="showCategory" width="100%" cellspacing="2" cellpadding="2"
				border="0" class="formbg">
				<tr>
					<td width="25%" colspan="1" id="ctrlHide">
						<label class="set" name="category" id="category" ondblclick="callShowDiv(this);"><%=label.get("category")%></label>:
					<font color="red">*</font></td>
					<td width="75%" colspan="1">
						<s:textarea  name="category1" cols="40" rows="2"  /> 
						<s:hidden name="hiddenEdit" />
					</td>
					
					<!--  <td width="25%" colspan="1" id="ctrlHide"><label class="set"
						name="catWeightage" id="catWeightage" ondblclick="callShowDiv(this);"><%=label.get("catWeightage")%></label>:
					<font color="red">*</font></td>
					<td width="25%" colspan="1"><s:textfield name="categoryWeightage" size="30" onkeypress="return numbersOnly();" maxlength="3" /></td>
					-->

				</tr>

				<tr>
					
					<td colspan="4" class="sortableTD" id="ctrlHide" align="center">
					<input type="button" name="add"	onclick="return callAdd();" value="Add" class="add">
					
				    </td>
				</tr>

				<tr>
					<td width="100%" colspan="4">
					<table class="formbg" width="100%">
						<tr>
							
							<td align="center" class="formth" width="10%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							
							<td align="center" class="formth" width="40%"><label
								class="set" name="categoryName" id="categoryName"
								ondblclick="callShowDiv(this);"><%=label.get("categoryName")%></label>
							</td>
							<!--  <td align="center" class="formth" width="20%"><label
								class="set" name="categoryWeightage" id="categoryWeightage"
								ondblclick="callShowDiv(this);"><%=label.get("categoryWeightage")%></label>
							</td>-->
							
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<label class="set" class="token" theme="simple" name="editRemove"
								id="editRemove" ondblclick="callShowDiv(this);"><%=label.get("editRemove")%></label>&nbsp;
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
								ondblclick="javascript:callForEdit('<s:property value="categoryIt"/>',<s:property value="srNo"/>);">

								<td width="10%" align="center" class="sortableTD"><%=++ii%>
									<input type="hidden" name="srNo" value="<%=ii%>" />
								</td>
								<td class="sortableTD">
									<s:property value="categoryIt" />&nbsp;
									<s:hidden name="categoryIt" />
								</td>
								<!--  <td class="sortableTD"><s:property value="catWeightage" />&nbsp;
								<s:hidden name="catWeightage" /></td>-->
								<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
								<td width="10%" align="center" nowrap="nowrap" class="sortableTD" id="ctrlHide">
									<!--  <input type="button" class="rowEdit" title="Edit Record" onclick="callForEdit11('<s:property  value="categoryIt" />','<%=ii%>','<s:property value="catWeightage" />')" />
									<input type="button" class="rowDelete" title="Delete Record" id="confChkop<%=ii%>" name="confChkop"
										   onclick="callForDelete2('<%=ii%>')" />-->
										   <input type="button" class="rowEdit" title="Edit Record" onclick="callForEdit11('<s:property  value="categoryIt" />','<%=ii%>')" />
									<input type="button" class="rowDelete" title="Delete Record" id="confChkop<%=ii%>" name="confChkop"
										   onclick="callForDelete2('<%=ii%>')" />
								</td>

							</tr>

						</s:iterator>
						<%
							d1 = ii;
						%>
						<!--  <tr>
							<td>&nbsp;</td>
							<td>Total</td>
							<td><s:property value="totalCatWeightage" />&nbsp;
								<s:hidden name="totalCatWeightage" /></td>
							<td>&nbsp;</td>
						</tr>-->
						
					</table>
				</tr>
			</table>
			</td>
		</tr>
		<tr>

			<td>
			<table width="100%" cellspacing="1" cellpadding="2" border="0"
				class="formbg">
				
				<tr>
					<td align="left"><label class="set" id="confRating"
						name="confRating" onDblClick="callShowDiv(this);"><%=label.get("confRating")%>
					:</label><font color="red">*</font></td>
					<td>
						
							<input type="radio" id='actualRating' value='N'
								name="isConfRating" onclick="setCalculateRating(this);" /> Actual Rating
							<input type="radio" id='confgRating' value='Y'
								name="isConfRating" onclick="setCalculateRating(this);"  /> Configure Rating
						
					</td>
				</tr>
				
			</table>
			</td>
		</tr>
		
		
		<input type="hidden" id="categoryCnt" name="categoryCnt" value="<%=d1%>" />
						<s:hidden name="subcode" />
						<s:hidden name="hiddenCategoryCheck" />
						<s:hidden name="tableLength" />
						<s:hidden name="pushlishFlag"/>
						<s:hidden name="hiddenConfRating" id="hiddenConfRating"/>
		
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
function setCalculateRating(obj)
{

	if(document.getElementById('actualRating').checked)
	{
		document.getElementById('hiddenConfRating').value="N";
	}else
	{
		document.getElementById('hiddenConfRating').value="Y";
	}
}
function addreviewWeightage(check)
{
	var totalRows=document.getElementById('count').value;
	//alert("totalRows : "+totalRows);
	var totalReviewWeightage=0
	for(var i=0;i<totalRows;i++){
	//alert(""+document.getElementById('reviewWeightage'+i).value);
	var val1=document.getElementById('reviewWeightage'+i).value;
	if(val1=="")
	{
		val1=0;
	}
	totalReviewWeightage=eval(totalReviewWeightage)+eval(val1);
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
</script>
<script type="text/javascript"><!--
	function addDateRow(){
		
		document.getElementById("paraFrm").action='CompetencyInitialization_addDateRow.action';
		document.getElementById("paraFrm").submit();	
	}
	
	function draftFun()
	{
		try
		{
		var revwt=document.getElementById('totalreviewWeight').value;
		 
		try
		
			{	
				if(!validateSave()){
					return false;
				}
				}catch(e)
				{
					alert("Exception occured in draft function : "+e);
				}
				try{
					if(!checkReviewDate()){
						return false;
					}
				}catch(e)
				{
					alert("Exception occured in draft function : "+e);
				}
				
				try{
					if(!validateReviewdate()){
						return false;
					}
				}catch(e)
				{
					alert("Exception occured in draft function --------------: "+e);
				}
			  
				if(revwt!="100")
		{
			alert("The total review weightage should not be greater than or less than 100");
		 	return false;		
		} 
				
				
			var goalwgt=document.getElementById("compWeightage").value;
			var apprwgt=document.getElementById("appWeightage").value;
			var selfAsstWt=document.getElementById("selfAsstWeightage").value;
			var managerAsstWt=document.getElementById("managerAsstWeightage").value;
			var totalWeightage=eval(goalwgt)+eval(apprwgt);
			var totalSelfManagerWt=eval(selfAsstWt)+eval(managerAsstWt);
			
			
			if(document.getElementById('isEscalationId').checked){
				 document.getElementById('showEltMailId').style.display="";   		 
				 if(document.getElementById("paraFrm_reportingType").value!="goal"){
				 	alert("Please select Reporting Structure Type as Goal Reporting Structure");
				 	return false;
				 }
			}	 
			/*if(goalwgt!="" && apprwgt!=""){
				if(totalWeightage != 100){
					alert("Total of Competency weightage and appraisal weightage should be 100");
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
			alert("Please enter "+document.getElementById('compWt').innerHTML.toLowerCase());
		  	document.getElementById('compWeightage').focus();
		 	return false;		
		}
		if(selfAsstWt=="")
		{
			alert("Please enter "+document.getElementById('selfAssWeightage').innerHTML.toLowerCase());
		  	document.getElementById('selfAssWeightage').focus();
		 	return false;		
		}
		/*deepak*/
		if((document.getElementById("actualRating").checked == false && document.getElementById("confgRating").checked==false)){
			alert("Please select calculate rating");
			return false;
		}
		/*if(document.getElementById("isCategoryReqID").checked){		
			if(document.getElementById("categoryCnt").value=="" || document.getElementById("categoryCnt").value==0){ 
			    alert("Please add atleast one Category. ");
			}else{
				document.getElementById("paraFrm").action='CompetencyInitialization_save.action';
				document.getElementById("paraFrm").submit();
			}
		}else{*/
			document.getElementById("paraFrm").action='CompetencyInitialization_save.action';
			document.getElementById("paraFrm").submit();
		//}
			
		}catch(e)
		{
			alert("Exception occured in draft function : "+e);
		}
	}
	
	function publishFun()
	{	
	var revwt=document.getElementById('totalreviewWeight').value;
		if(!validateSave()){
			return false;
		}
		if(!checkReviewDate()){
			return false;
		}
		if(!validateReviewdate()){
			return false;
		}
		if(revwt!="100")
		{
			alert("The total review weightage should not be greater than or less than 100");
		 	return false;		
		}  
		
	  	var goalwgt=document.getElementById("compWeightage").value;
			var apprwgt=document.getElementById("appWeightage").value;
			var selfAsstWt=document.getElementById("selfAsstWeightage").value;
			var managerAsstWt=document.getElementById("managerAsstWeightage").value;
			var totalWeightage=eval(goalwgt)+eval(apprwgt);
			var totalSelfManagerWt=eval(selfAsstWt)+eval(managerAsstWt);
		
			
			
			if(document.getElementById('isEscalationId').checked){
				 document.getElementById('showEltMailId').style.display="";   		 
				 if(document.getElementById("paraFrm_reportingType").value!="goal"){
				 	alert("Please select Reporting Structure Type as Goal Reporting Structure ");
				 	return false;
				 }
			}	 
			
			
			/*if(goalwgt!="" && apprwgt!=""){
				if(totalWeightage != 100){
					alert("Total of competency weightage and appraisal weightage should be 100");
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
			alert("Please enter "+document.getElementById('compWt').innerHTML.toLowerCase());
		  	document.getElementById('compWeightage').focus();
		 	return false;		
		}
		if(selfAsstWt=="")
		{
			alert("Please enter "+document.getElementById('selfAssWeightage').innerHTML.toLowerCase());
		  	document.getElementById('selfAssWeightage').focus();
		 	return false;		
		}
		
		if((document.getElementById("actualRating").checked == false && document.getElementById("confgRating").checked==false)){
			alert("Please select calculate rating");
			return false;
		}

		/*if(document.getElementById("isCategoryReqID").checked){		
			if(document.getElementById("categoryCnt").value=="" || document.getElementById("categoryCnt").value==0){ 
			    alert("Please add atleast one Category. ");
			}else{
			var conf=confirm("Do you really want to publish this goal?");
			  	if(!conf) 
			  	{
			  		return false;
			  	}else{
					document.getElementById("paraFrm_pushlishFlag").value="1";
					document.getElementById("paraFrm").action='CompetencyInitialization_publishGoal.action';
					document.getElementById("paraFrm").submit();
				}
				
			}
		}else{*/
			var conf=confirm("Do you really want to publish this Competency?");
			if(!conf){
			  		return false;
			}else{
			document.getElementById("paraFrm_pushlishFlag").value="1";
			document.getElementById("paraFrm").action='CompetencyInitialization_publishGoal.action';
			document.getElementById("paraFrm").submit();
			}
		//}


	}
	function editFun()
	{
		document.getElementById('paraFrm').action = "CompetencyInitialization_edit.action";
		document.getElementById('paraFrm').submit();
	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CompetencyInitialization_back.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
  	 	document.getElementById('paraFrm').action = "CompetencyInitialization_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
	      	document.getElementById('paraFrm').action = 'CompetencyInitialization_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	function validateSave(){
		var fieldName  = [ "paraFrm_compName","paraFrm_compfromDate","paraFrm_comptoDate"];
		var lableName  = ["compPeriod","compFromDate","compToDate"];
		var types  = ["enter","enter/select","enter/select"];
		var fromDate = document.getElementById("paraFrm_compfromDate").value;
		var toDate = document.getElementById("paraFrm_comptoDate").value;
		var emailID=document.getElementById("escalationMailId").value;
		if(!validateBlank(fieldName, lableName,types)){
					return false;			  
		}
		if(document.getElementById("paraFrm_compfromDate").value != ""){
			if(!validateDate("paraFrm_compfromDate", "compFromDate"))return false;
		}
		if(document.getElementById("paraFrm_comptoDate").value != ""){
			if(!validateDate("paraFrm_comptoDate", "compToDate"))return false;
		}
		if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_compfromDate", "compFromDate", "compToDate")){
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
function validateReviewdate(){
	var count =document.getElementById('count').value;
	var fromDate = document.getElementById("paraFrm_compfromDate").value;
	var toDate = document.getElementById("paraFrm_comptoDate").value;
	
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var toDate1 = toDate.split("-"); 
	var toTime1 = new Date(toDate1[2],toDate1[1]-1,toDate1[0]); 	
	
	for(var check=0;check <count; check++){
		var reDate=document.getElementById('reviewDate'+check).value;
		var strDate2 = reDate.split("-"); 
		var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		if((endtime  < starttime) || (endtime  > toTime1) ) 
		{ 
			alert("Review Date should be in between fromdate and todate");
			return false;
		}	
	}
	return true;
}
	function checkReviewDate(){
		try
		{
			var count =document.getElementById('count').value;
			if(count == 0 || count==""){
					alert("Please add minimum one review date in the list. ");
					return false;
			}
			for(var check=0;check <count; check++){
				if(document.getElementById('reviewDate'+check).value==""){
					alert("Please enter / select "+document.getElementById('compReviewDate').innerHTML);
					document.getElementById('reviewDate'+check).focus();
					return false;
				}else{
					if(!validateDate("reviewDate"+check, "compReviewDate"))return false;
				}
			}
			return true;
		}catch(e)
		{
			alert("Exception occured in draft function : "+e);
		}
	}
	 function callForDelete(id){
	   	var conf=confirm("Do you really want to delete review date?");
		if(conf){
		 document.getElementById('paraFrm_paraId').value = id;
		 document.getElementById('paraFrm').action = "CompetencyInitialization_removeDate.action";
		 document.getElementById('paraFrm').submit();
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
		   
		   if(document.getElementById('hiddenConfRating').value=='N')	
		   {
		   		document.getElementById('actualRating').checked=true;
		   		document.getElementById('confgRating').checked=false;
		   		
		   }else if(document.getElementById('hiddenConfRating').value=='Y')
		   {
		   		document.getElementById('actualRating').checked=false;
		   		document.getElementById('confgRating').checked=true;
		   }
		 }catch(e){} 
		 
	}
	
	function callAdd(){
		try{
	    		var fieldName = ["paraFrm_category1"];
				var lableName = ["category"];
				var flag = ["enter"];	
			  	if(!validateBlank(fieldName, lableName,flag)){
	            	 return false;
	      		} else{ 
		      		document.getElementById('paraFrm').action="CompetencyInitialization_addItem.action";
					document.getElementById('paraFrm').submit();
				}
			   /* document.getElementById('paraFrm').target = "main";
			    document.getElementById('isCategoryReqID').checked=true;
			    */
			    return true;
		 }catch(e)
		 {
		 	alert("Exception occured in callAdd : "+e);
		 } 
	}
	
    function callForDelete2(id){
          try{
	    	var conf = confirm("Do you really want to delete this record?");
	  		if(conf) {
		    	 document.getElementById('paraFrm_paraId').value = id;
				 document.getElementById('paraFrm').action = "CompetencyInitialization_removeGoalsCategories.action";
				 document.getElementById('paraFrm').submit();
			 }
		 }catch(e)
		 {
		 	alert("Exception occured in delete function : "+e);
		 } 
		 
    }
    
	function callForEdit11(kvalue,rowNo){
	    document.getElementById('paraFrm_category1').value = kvalue;
	    //document.getElementById("categoryWeightage").value = kval2;
	    document.getElementById('paraFrm_paraId').value=rowNo;
	}
	
	  function calcRatings(){	
		 document.getElementById('paraFrm').action = "CompetencyInitialization_calculateRating.action";
		 document.getElementById('paraFrm').submit();
    }
    function appraisalWeightage (){
                {
                        var val1 = parseFloat(document.getElementById("compWeightage").value);
                      var ansD = document.getElementById("appWeightage");
                        ansD.value =(100 - val1 );
                }
            }
    function selfAssestmentWeightage (){
       {
               var val1 = parseFloat(document.getElementById("selfAsstWeightage").value);
             var ansD = document.getElementById("managerAsstWeightage");
             if(val1=="")
             {
             	val1=0;
             }
               ansD.value =(100 - val1 );
       }
    }    
            
    function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'CompetencyInitialization_report.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}   
	function validateEscalationFlag(){
	  try{
			 if(document.getElementById('isEscalationId').checked){
			 	 document.getElementById('paraFrm_isSignOffRequired').checked =true;
				 document.getElementById('showEltMailId').style.display="";   		 
				 if(document.getElementById("paraFrm_reportingType").value!="goal"){
				 	alert("Please select Reporting Structure Type as Goal Reporting Structure");
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
  </Script>
