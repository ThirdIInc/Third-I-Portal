<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form name="" action="" validate="" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Traveller
					Details</strong></td>
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
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="4" width="100%">Traveller Information</td>
				</tr>
				<s:hidden name="empTravellerGradeId"
					id="paraFrm_empTravellerGradeId" />
				<s:if test='%{listType =="other_employee"}'>
					<tr>
						<td width="25%"><label class="set" name="Guestname" id="travellerName1"
							ondblclick="callShowDiv(this);"><%=label.get("travellerName")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3"><s:hidden name="travellerId"
							id="paraFrm_travellerId" /> <s:textfield name="travellerToken"
							id="paraFrm_travellerToken" theme="simple" size="15"
							maxlength="100" readonly="true" /> <s:textfield
							name="travellerName" id="paraFrm_travellerName" theme="simple"
							size="30" maxlength="100" readonly="true" /> <s:hidden
							name="listType" id="paraFrm_listType" value="%{listType}"></s:hidden>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'TravelApplication_addEmployeeToTravellerList.action');">
						</td>

					</tr>
				</s:if>
				<s:else>
					<tr>
						<td><label class="set" name="Guestname" id="travellerName1"
							ondblclick="callShowDiv(this);"><%=label.get("travellerName")%></label>
						<font color="red">*</font> :</td>
						<td width="25%"><s:hidden name="travellerToken"
							id="paraFrm_travellerToken" /> <s:hidden name="travellerId"
							id="paraFrm_travellerId" /> <s:textfield name="travellerName"
							id="paraFrm_travellerName" theme="simple" size="40"
							maxlength="100" readonly="false" /></td>
						<td><s:hidden name="listType" value="%{listType}"></s:hidden>&nbsp;
						</td>
					</tr>

				</s:else>
				<tr>
					<td><label class="set" name=ContactNO id="travellerContactNO1"
						ondblclick="callShowDiv(this);"><%=label.get("ContactNO")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield name="travellerContact"
						id="paraFrm_travellerContact" theme="simple" size="15"
						maxlength="10" onkeypress="return numbersOnly();" /></td>
					<td><s:hidden name="employeeDateOfBirth" id="paraFrm_employeeDateOfBirth" />
					<label class="set" name="EmpAge" id="travellerAge1"
						ondblclick="callShowDiv(this);"><%=label.get("EmpAge")%></label> <font
						color="red">*</font>: <s:textfield name="travellerAge"
						id="paraFrm_travellerAge" theme="simple" size="5" maxLength="2"
						onkeypress="return numbersOnly();" /></td>
				</tr>
				<s:if test='%{listType =="other_employee"}'>
					<tr>
						<td><label class="set" name="advanceDetails"
							id="advanceDetails1" ondblclick="callShowDiv(this);"><%=label.get("advanceDetails")%></label>
						:</td>
						<td width="25%"><s:textfield name="advDetails"
							id="paraFrm_advDetails" theme="simple" size="20" maxLength="10"
							value="0" onkeypress="return numbersOnly();" /></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
					<td>&nbsp</td>
						<td><s:hidden name="advDetails" id="paraFrm_advDetails"
							value="0" /></td>
					</tr>
				</s:else>
				<tr>
					<td colspan="6" align="center"><input type="button"
						Class="save" theme="simple" value="  Add   "
						onclick="javascript:return chkAdd();" /></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
	 <s:hidden name="addedEmployee" /> 
</s:form>

<script>

getEmployeeId();

 function getEmployeeId()
 {
 try{
 document.getElementById("paraFrm_addedEmployee").value = opener.document.getElementById('paraFrm_checkempids').value;
 }
 catch(e)
 {
 	//alert("e-------"+e);
 }
 
 
 }

	function chkAdd(){
	try{
		var gradeId = document.getElementById("paraFrm_empTravellerGradeId").value;
		var token = document.getElementById("paraFrm_travellerToken").value;
		var travellersName = document.getElementById("paraFrm_travellerName").value;
		var travellersContact = document.getElementById("paraFrm_travellerContact").value;
		var travellersDOB= document.getElementById("paraFrm_employeeDateOfBirth").value;
		var travellersAge= document.getElementById("paraFrm_travellerAge").value;
		var advanceDtls= document.getElementById("paraFrm_advDetails").value;
		var travellerId= document.getElementById("paraFrm_travellerId").value;
		var	empType="";
		
		if(document.getElementById("paraFrm_listType").value == "guest"){
			empType="G";
			travellerId="0";
		}else{
			empType="O";
		}
		
		if( travellersName ==""){
			if(document.getElementById("paraFrm_listType").value == "guest"){
				alert("Please enter guest name");
				return false;
			}else{
				alert("Please select employee ");
				return false;
			}
		}
		if( travellersContact =="" ){
			alert("Please enter contact details");
			return false;
		}
		if( travellersContact.length < 10 ){
			alert("Please enter min 10 digit contact no");
			return false;
		}
		if( travellersAge ==""){
			alert("Please enter traveller's age");
			return false;
		}
			opener.addRowToRef(token,travellersName,travellersAge,travellersContact,advanceDtls, empType,travellerId, gradeId, travellersDOB);
			 
			window.close();
			
			}catch(e){alert(e);}
	}

</script>