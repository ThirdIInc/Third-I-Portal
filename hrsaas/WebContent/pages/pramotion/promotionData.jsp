<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include
	file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="PromotionMaster" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion 
					</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>


			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="3" class="formhead"><strong
								class="forminnerhead">&nbsp;<label class="set"
								name="promotion" id="promotion" ondblclick="callShowDiv(this);"><%=label.get("promotion")%></label>
							</strong></td>
						</tr>
						<s:hidden name="promCode" />
						<s:hidden name="lockFlag" />
						<s:hidden name="updateFlag" />
						<s:hidden name="lockStatus" />
						<tr>
							<td width="20%" colspan="1">&nbsp;<label class="set"
								name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							<font color="red">*</font>:</td>
							<td height="27" colspan="3"><s:hidden name="empCode"
								value="%{empCode}" /> <s:textfield name="empToken" size="15"
								value="%{empToken}" theme="simple" readonly="true" /> <s:textfield
								readonly="true" name="empName" value="%{empName}" size="77" />

							<s:if test="%{selectFlag}">
								<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="15" 
									onclick="javascript:callsF9(500,325,'PromotionMaster_f9empaction.action');" id='ctrlHide'/>
							</s:if> <s:else></s:else></td>
						</tr>

						<tr>
							<td width="20%">&nbsp;<label class="set" name="doj"
								id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
							<td width="25%"><s:textfield size="15" maxlength="30"
								theme="simple" name="joinDate" value="%{joinDate}"
								readonly="true" /></td>

						</tr>

						<tr>
							<td width="20%">&nbsp;<label class="set"
								name="promotion.date" id="promotion.date"
								ondblclick="callShowDiv(this);"><%=label.get("promotion.date")%></label><font
								color="red">*</font>:</td>
							<td width="25%"><s:textfield size="15" maxlength="30"
								theme="simple" name="promDate" value="%{promDate}" /><s:a
								href="javascript:NewCal('paraFrm_promDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="15" id='ctrlHide'>
							</s:a></td>
							<td width="20%">&nbsp;<label class="set"
								name="effective.date" id="effective.date"
								ondblclick="callShowDiv(this);"><%=label.get("effective.date")%></label><font
								color="red">*</font>:</td>
							<td width="35%">&nbsp;&nbsp;<s:textfield size="15"
								maxlength="30" theme="simple" name="effDate" value="%{effDate}" /><s:a
								href="javascript:NewCal('paraFrm_effDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="15" id='ctrlHide'>
							</s:a></td>

						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td width="27%" colspan="1"><s:textfield size="27"
								theme="simple" name="div" value="%{div}" readonly="true" /><s:hidden
								name="divCode" value="%{divCode}" /></td>
							<td width="22%" colspan="1"><label class="set"
								name="proposed.division" id="proposed.division"
								ondblclick="callShowDiv(this);"><%=label.get("proposed.division")%></label>
							<font color="red">*</font>:</td>
							<td width="35%" colspan="3"><s:textfield size="27"
								theme="simple" name="proDiv" readonly="true" value="%{proDiv}" />
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9proDiv.action');" id='ctrlHide'>
							<s:hidden name="prDivCode" value="%{prDivCode}" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="27%" colspan="1"><s:textfield size="27"
								readonly="true" theme="simple" name="branch" value="%{branch}" /><s:hidden
								name="branchCode" value="%{branchCode}" /></td>
							<td width="22%" colspan="1"><label class="set"
								name="proposed.branch" id="proposed.branch"
								ondblclick="callShowDiv(this);"><%=label.get("proposed.branch")%></label>
							<font color="red">*</font> :</td>
							<td width="35%" colspan="3"><s:textfield size="27"
								theme="simple" name="proBranch" value="%{proBranch}"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9proBrnach.action');" id='ctrlHide'>
							<s:hidden name="prBranCode" value="%{prBranCode}" /></td>

						</tr>

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td width="27%" colspan="1"><s:textfield size="27"
								readonly="true" theme="simple" name="dept" value="%{dept}" /><s:hidden
								name="deptCode" value="%{deptCode}" /></td>

							<td width="22%" colspan="1"><label class="set"
								name="proposed.department" id="proposed.department"
								ondblclick="callShowDiv(this);"><%=label.get("proposed.department")%></label>
							<font color="red">*</font>:</td>
							<td width="35%" colspan="3"><s:textfield size="27"
								theme="simple" name="proDept" value="%{proDept}" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9proDept.action');" id='ctrlHide'>
							<s:hidden name="prDeptCode" value="%{prDeptCode}" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="27%" colspan="1"><s:textfield size="27"
								readonly="true" theme="simple" name="desg" value="%{desg}" /><s:hidden
								name="desgCode" value="%{desgCode}" /></td>

							<td width="22%" colspan="1"><label class="set"
								name="proposed.designation" id="proposed.designation"
								ondblclick="callShowDiv(this);"><%=label.get("proposed.designation")%></label><font
								color="red">*</font> :</td>
							<td width="35%" colspan="3"><s:textfield readonly="true"
								size="27" theme="simple" name="proDesg" value="%{proDesg}" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9proDesg.action');" id='ctrlHide'>
							<s:hidden name="prDesgCode" value="%{prDesgCode}" /></td>

						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="role" id="role"
								ondblclick="callShowDiv(this);"><%=label.get("role")%></label>
							:</td>
							<td width="27%" colspan="1"><s:textfield size="27"
								readonly="true" theme="simple" name="currentRole"/></td>

							<td width="22%" colspan="1"><label class="set"
								name="proposed.role" id="proposed.role"
								ondblclick="callShowDiv(this);"><%=label.get("proposed.role")%></label>
							:</td>
							<td width="35%" colspan="3"><s:textfield 
								size="27" theme="simple" name="proRole" /> 
							</td>

						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set" name="grade"
								id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td width="27%" colspan="1"><s:textfield size="27"
								theme="simple" name="grade" readonly="true" value="%{grade}" /><s:hidden
								name="gradeCode" value="%{gradeCode}" /></td>

							<td width="22%" colspan="1"><label class="set"
								name="proposed.grade" id="proposed.grade"
								ondblclick="callShowDiv(this);"><%=label.get("proposed.grade")%></label>:</td>
							<td width="35%" colspan="3"><s:textfield size="27"
								theme="simple" name="proGrade" value="%{proGrade}"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9proGrd.action');" id='ctrlHide'>
							<s:hidden name="prGrdCode" value="%{prGrdCode}" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="reporting.to" id="reporting.to"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>
							:</td>
							<td width="27%" colspan="1"><s:textfield name="repToName"
								readonly="true" size="27" value="%{repToName}" theme="simple" /><s:hidden
								name="repToCode" value="%{repToCode}" theme="simple" /></td>
							<td width="22%" colspan="1"><label class="set"
								name="proposed.reporting.to" id="proposed.reporting.to"
								ondblclick="callShowDiv(this);"><%=label.get("proposed.reporting.to")%></label><font
								color="red">*</font>:</td>
							<td width="35%" colspan="3"><s:textfield size="27"
								theme="simple" name="prRepToNm" value="%{prRepToNm}"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9repTo.action');" id='ctrlHide'>
							<s:hidden name="prRepCode" value="%{prRepCode}" /> <s:hidden
								name="empTokenRp" /></td>

						</tr>

						<tr>
							<td width="20%"><label class="set" name="Proposed.by"
								id="Proposed.by" ondblclick="callShowDiv(this);"><%=label.get("Proposed.by")%></label>:</td>
							<td width="27%"><s:textfield size="27" theme="simple"
								name="proByNm" value="%{proByNm}" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"  id='ctrlHide'
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9proBy.action');">
							<s:hidden name="proByCode" value="%{proByCode}" /> <s:hidden
								name="empTokenPro" /></td>
							<td width="22%"><label class="set" name="approved.by"
								id="approved.by" ondblclick="callShowDiv(this);"><%=label.get("approved.by")%></label>:</td>
							<td width="35%"><s:textfield size="27" theme="simple"
								name="apprByName" value="%{apprByName}" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9apprBy.action');" id='ctrlHide'>
							<s:hidden name="apprByCode" value="%{apprByCode}" /> <s:hidden
								name="empTokenAppr" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="appraisal.rating" id="appraisal.rating"
								ondblclick="callShowDiv(this);"><%=label.get("appraisal.rating")%></label>:</td>
							<td width="27%" colspan="1"><s:textfield size="27"
								theme="simple" name="rating" value="%{rating}"
								onkeypress="return numbersWithDot();" /></td>
								<!-- UPDATED BY REEBA BEGINS -->
							<td width="22%"><label class="set" name="old.ctc"
								id="old.ctc" ondblclick="callShowDiv(this);"><%=label.get("old.ctc")%></label>:</td>
							<td width="35%"><s:textfield size="27"
								theme="simple" name="oldCTC" value="%{oldCTC}"
								onkeypress="return numbersWithDot();" /></td>
							<!-- UPDATED BY REEBA ENDS -->
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="promotion.flag" id="promotion.flag"
								ondblclick="callShowDiv(this);"><%=label.get("promotion.flag")%></label></td>
							<td width="27%" colspan="1"><s:checkbox name="promFlag"
								value="%{promFlag}" /></td>
								
							<td width="22%"><label class="set" name="letter_temp"
								id="letter_temp" ondblclick="callShowDiv(this);"><%=label.get("letter_temp")%></label>:</td>
							<td width="35%"><s:textfield size="27" theme="simple"
								name="ltrTemp" value="%{ltrTemp}" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PromotionMaster_f9ltrTemp.action');" id='ctrlHide'>
							<s:hidden name="ltrTempCode" value="%{ltrTempCode}" /> <s:hidden
								name="empTokenLtr" /></td>  
									
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="strengths" id="strengths" ondblclick="callShowDiv(this);"><%=label.get("strengths")%></label>:</td>
							<td width="79%" colspan="3"><s:textarea cols="93" rows="3"
								name="strength" value="%{strength}" /></td>
							<td></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="weakness" id="weakness" ondblclick="callShowDiv(this);"><%=label.get("weakness")%></label>:</td>
							<td width="79%" colspan="3"><s:textarea cols="93" rows="3"
								theme="simple" name="weakness" value="%{weakness}" /></td>
						</tr>


<!-- #################################################################  -->
						<tr>
							<td width="20%" colspan="1">&nbsp;</td>
							<td width="27%" colspan="1"><s:checkbox name="grdFlag"
								id="paraFrm_grdFlag" onclick="chkflag(1);" /> <label class="set"
								name="grade" id="grade1" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							</td>
							<td width="35%" colspan="3"><s:checkbox name="frmFlag"
								id="paraFrm_frmFlag" onclick="chkflag(2);" /><label
								class="set" name="formula" id="formula"
								ondblclick="callShowDiv(this);"><%=label.get("formula")%></label>
							&nbsp;</td>
						</tr>


						<tr>
							<td width="50%" colspan="6">
							<div id="grd_Flag">
							<table width="100%">
								<tr>

									<td width="22%" colspan="1"><label class="set"
										name="salary.grade" id="salary.grade"
										ondblclick="callShowDiv(this);"><%=label.get("salary.grade")%></label>
									:</td>
									<td width="35%" colspan="1"><s:textfield theme="simple"
										readonly="true" name="salgrdName" size="27"
										onclick="salgrd();" /><img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="15"
										onclick="salgrd();" id='ctrlHide'/> <s:hidden name="salgrdId" /></td>
									<td width="20%" colspan="1">&nbsp;</td>
									<td width="17%" colspan="1"><label class="set"
										name="new.CTC" id="new.CTC"
										ondblclick="callShowDiv(this);"><%=label.get("new.CTC")%></label>:</td>
									<td width="27%" colspan="4"><s:textfield
										cssStyle="width:130" size="27" theme="simple" name="salPromAmount"
										value="%{salPromAmount}" maxlength="7"
										onkeypress="return numbersWithDot();" />
								</tr>
							</table>
							</div>
							</td>
							<td width="25%" colspan="1">&nbsp;</td>
							<td width="25%" colspan="1">&nbsp;</td>
						</tr>

						<tr>
							<td width="50%" colspan="6">
							<div id="frm_Flag">
							<table width="100%">
								<tr>

									<td width="16%" colspan="1"><label class="set"
										name="formula" id="formula1" ondblclick="callShowDiv(this);"><%=label.get("formula")%></label>
									:</td>
									<td width="22%" colspan="1"><s:textfield size="27"
										theme="simple" name="frmName" readonly="true"
										value="%{frmName}" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="15"
										onclick="javascript:callsF9(500,325,'PromotionMaster_f9frmaction.action');" id='ctrlHide'>
									<s:hidden name="frmId" value="%{frmId}" /> <s:hidden
										name="sCode" value="%{sCode}" /> <s:hidden name="sType"
										value="%{sType}" /> <s:hidden name="sFrm" value="%{sFrm}" /></td>
									<td width="17%" colspan="1"><label class="set"
										name="gross.amount" id="gross.amount"
										ondblclick="callShowDiv(this);"><%=label.get("gross.amount")%></label>:</td>
									<td width="27%" colspan="4"><s:textfield
										cssStyle="width:130" size="27" theme="simple" name="grsAmt"
										value="%{grsAmt}" maxlength="7"
										onkeypress="return numbersWithDot();" /> <s:submit
										cssClass="pagebutton" name="calculate"
										action="PromotionMaster_calculate" value="Calculate"
										onclick="return calcFormula();" id='ctrlHide'/></td>
								</tr>
							</table>
							</div>
							</td>

							<td width="25%" colspan="2">&nbsp;</td>
							<td width="35%" colspan="3">&nbsp;</td>

						</tr>

						<tr>
							<td colspan="6">&nbsp;</td>

						</tr>
					</table>

					<table width="100%" class="formbg">
						<tr>
							<td colspan="5"><strong class="text_head"><label
								class="set" name="salary.details" id="salary.details"
								ondblclick="callShowDiv(this);"><%=label.get("salary.details")%></label></strong></td>
						</tr>
						<tr>

							<td class="formth" width="30%"><label class="set"
								name="salary.head" id="salary.head"
								ondblclick="callShowDiv(this);"><%=label.get("salary.head")%></label></td>
							<td class="formth" width="20%"><label class="set"
								name="periodicity" id="periodicity"
								ondblclick="callShowDiv(this);"><%=label.get("periodicity")%></label></td>
							<td class="formth" width="25%" align="center"><label
								class="set" name="current.amount" id="current.amount"
								ondblclick="callShowDiv(this);"><%=label.get("current.amount")%></label></td>
							<td class="formth" width="25%" align="center"><label
								class="set" name="new.amount" id="new.amount"
								ondblclick="callShowDiv(this);"><%=label.get("new.amount")%></label></td>
						</tr>

						<%
						int id = 0;
						%>

						<s:iterator value="salList">
							<tr>

								<td class="td_bottom_border" width="30%"><s:hidden
									name="salCode" /><s:property value="salHead" /><s:hidden
									name="salHead" /></td>
								<td class="td_bottom_border" width="20%"><s:hidden
									name="salPerdiocity" /><s:property value="salPerdiocity" /></td>
								<td class="td_bottom_border" width="25%"><input type="text"
									name="curAmt" value='<s:property value="curAmt"/>'
									readonly="readonly" size="32%" Style="text-align: right" /></td>
								<td class="td_bottom_border" width="25%"><input type="text"
									name="newAmt" value='<s:property value="newAmt" />' size="32%"
									Style="text-align: right" id="newAmt<%=id%>"
									onkeypress="return numbersWithDot()" /> <s:hidden
									name="proSalCode" /></td>
							</tr>

							<%
							id++;
							%>
						</s:iterator>

						<%!int totRows = 0;%>
						<%
						totRows = id;
						%>
					</table>
			</table>

			</td>
		</tr>
		<tr>
		<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>




	function saveFun() {
	
		if(!callValidate()){
			return false;
		}
		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'PromotionMaster_save.action';
			document.getElementById('paraFrm').submit();
	}
	
	function editFun() {
	
		document.getElementById('paraFrm').action = "PromotionMaster_edit.action";
		document.getElementById('paraFrm').submit();
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PromotionMaster_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function resetFun() {
	
  	 	document.getElementById('paraFrm').action = "PromotionMaster_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	
  	function lockFun() {
  	
    	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PromotionMaster_lock.action';
		document.getElementById('paraFrm').submit();
 	}
 
 	
	
	function unlockFun() {
	var con = confirm("Do you really want to unlock this record?");
	if (con) {
		doAuthorisation('2', 'Promotion', 'U');
		
	} else {
		return false;
	}
	}
	
	
	function doUnlock() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PromotionMaster_unLock.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	
	function deleteFun() {
	
		var conf=confirm("Are you sure you want to delete this record ?");
  			if(conf) {
		
		document.getElementById("paraFrm").target = '_self';
		document.getElementById("paraFrm").action="PromotionMaster_delete.action";
		document.getElementById("paraFrm").submit();
		}
	
	}
	
	/* NEW FUNCTION ADDED BY PRASHANT*/
	function chkflag(val){
		if(val == 1){
			document.getElementById('grd_Flag').style.display = '';
			document.getElementById('frm_Flag').style.display = 'none';
			document.getElementById('paraFrm_frmFlag').checked = false;
		}else {
		
			document.getElementById('frm_Flag').style.display = '';
			document.getElementById('grd_Flag').style.display = 'none';
			document.getElementById('paraFrm_grdFlag').checked = false;
		
		}
	}
	
	function onLoadChkflag(){
		if(document.getElementById('paraFrm_grdFlag').checked){
			document.getElementById('grd_Flag').style.display = '';
			document.getElementById('frm_Flag').style.display = 'none';
			document.getElementById('paraFrm_frmFlag').checked = false;
		}else {		
			document.getElementById('frm_Flag').style.display = '';
			document.getElementById('grd_Flag').style.display = 'none';
			document.getElementById('paraFrm_grdFlag').checked = false;	
			document.getElementById('paraFrm_frmFlag').checked = true;
		}	
	}
	onLoadChkflag();

	/*function chkflag(val) {
	
		if(val == 1)
		{
 
			if(document.getElementById('paraFrm_grdFlag').checked == true)
			{
			
				document.getElementById('grd_Flag').style.display = '';
				document.getElementById('frm_Flag').style.display = 'none';
				document.getElementById('paraFrm_frmFlag').checked = false;
			
			}
			else 
			{
				
				document.getElementById('frm_Flag').style.display = '';
				document.getElementById('grd_Flag').style.display = 'none';
				document.getElementById('paraFrm_frmFlag').checked = true;
		
			}
		}
	else if(val == 2)
	{
		if(document.getElementById('paraFrm_frmFlag').checked == true)
		{
			
			document.getElementById('frm_Flag').style.display = '';
			document.getElementById('grd_Flag').style.display = 'none';
			document.getElementById('paraFrm_grdFlag').checked = false;
			
		} else {
			
			document.getElementById('grd_Flag').style.display = '';
			document.getElementById('frm_Flag').style.display = 'none';
			document.getElementById('paraFrm_grdFlag').checked = true;
			
		}
	
 		} else {
 			document.getElementById('paraFrm_grdFlag').checked = false;
 			document.getElementById('paraFrm_frmFlag').checked = false;
 			document.getElementById('frm_Flag').style.display = 'none';
 			document.getElementById('grd_Flag').style.display = 'none';
 		}
	}*/
	 
	function callSalary() {
  
  	//win=window.open('','win','top=260,left=150,width=700,height=350,scrollbars=no,status=yes,resizable=yes');
  		var code=document.getElementById('paraFrm_empCode').value;
  		var eDate=document.getElementById('paraFrm_effDate').value;
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").action='PromotionMaster_salary.action'; //?code='+code+'eDate='+eDate;
		document.getElementById("paraFrm").submit();	
		document.getElementById('paraFrm').target="main";  
  	}
  	
  	function callNewTotal() {
        
  		var totalrow =<%=totRows%> ;
 		
 		var count=0;
		var values=0;
		for(var row = 0;row < totalrow ;row++) {
	 			values=document.getElementById("newAmt"+row).value;
	 			count=eval(count)+eval(values*(100/100));
		}
		document.getElementById('paraFrm_totNewAmt').value=count;
 	 }

  	function callValidate() {
  		//alert("callValidate");
  		var ecode=document.getElementById('paraFrm_empCode').value; 
  		var pdate=document.getElementById('paraFrm_promDate').value; 
  		var edate=document.getElementById('paraFrm_effDate').value;
    	var pbranch=document.getElementById('paraFrm_proBranch').value ;
  		var pdept=document.getElementById('paraFrm_proDept').value  ;
 		var pdesg=document.getElementById('paraFrm_proDesg').value ; 
 		var pdiv=document.getElementById('paraFrm_proDiv').value  ;
		//var pgrd=document.getElementById('paraFrm_proGrade').value  ;
		var prepnm=document.getElementById('paraFrm_prRepToNm').value  ;
    	var pByNm=document.getElementById('paraFrm_proByNm').value  ;
    	var appByNm=document.getElementById('paraFrm_apprByName').value  ;
    	// var calc = document.getElementById('calculate').value  
    	var frname= document.getElementById('paraFrm_frmName').value;
    	var grdId= document.getElementById('paraFrm_salgrdId').value;
 		var grdNm= document.getElementById('paraFrm_salgrdName').value; 
 		//var strg= document.getElementById('paraFrm_strength').value; 
 		//var weak= document.getElementById('paraFrm_weakness').value; 
 		var rate= document.getElementById('paraFrm_rating').value; 
		var joinDate=document.getElementById('paraFrm_joinDate').value;	
		//var proRole=document.getElementById('paraFrm_proRole').value;
		
		//for labels
		var empName=document.getElementById('employee').innerHTML.toLowerCase();   
 	  	var prDt=document.getElementById('promotion.date').innerHTML.toLowerCase(); 
 	  	var efDt=document.getElementById('effective.date').innerHTML.toLowerCase(); 
 	  	var prBh=document.getElementById('proposed.branch').innerHTML.toLowerCase(); 
  	  	var prDep=document.getElementById('proposed.department').innerHTML.toLowerCase(); 
      	var prDes=document.getElementById('proposed.designation').innerHTML.toLowerCase(); 
      	var prDiv=document.getElementById('proposed.division').innerHTML.toLowerCase(); 
     	// var prGrd=document.getElementById('proposed.grade').innerHTML.toLowerCase(); 
      	var doj=document.getElementById('doj').innerHTML.toLowerCase(); 
     	var prRepTo=document.getElementById('proposed.reporting.to').innerHTML.toLowerCase();
      	var appRat=document.getElementById('appraisal.rating').innerHTML.toLowerCase();
     	// var strengths=document.getElementById('strengths').innerHTML.toLowerCase();
    	// var weakness=document.getElementById('weakness').innerHTML.toLowerCase();
      	//var formula=document.getElementById('formula').innerHTML.toLowerCase();
      	//var grAmt=document.getElementById('gross.amount').innerHTML.toLowerCase();
      	//var proRoleLbl=document.getElementById('proposed.role').innerHTML.toLowerCase();
      	
 		var totalrow =<%=totRows%>;
   
  		if(ecode=="") {
  			alert("Please select "+empName);
  			return false;
  		}
  		if(pdate=="") {
  			//alert("pdate");
  			alert("Please enter "+prDt);
  			return false;
  		}
 		if(edate=="") {
  			//alert("edate");
  			alert("Please enter "+efDt);
  			return false;
 		}
 	 	if(!validateDate('paraFrm_promDate','promotion.date'))
             return false;	  
  
  		if(!validateDate('paraFrm_effDate','effective.date'))
             return false;	 
  
 		if(!dateDifference(joinDate, pdate, 'paraFrm_promDate','doj','promotion.date')){
			return false;
	 	}
		if(!dateDifference(joinDate, edate, 'paraFrm_effDate','doj','effective.date')){
			return false;
	  	}
  		if(pbranch=="") {
  			alert("Please enter "+prBh);
  			return false;
  		}
  		if(pdept=="") {
 		 	alert("Please enter "+prDep);
 		 	return false;
 		}
  		if(pdesg=="") {
 		 	alert("Please enter  "+prDes);
  		 	return false;
    	}
   		if(pdiv=="") {
   			alert("Please enter  "+prDiv);
  			return false;
   		}
   		/*
   		if(proRole.trim() == "") {
   			alert("Please enter  "+proRoleLbl);
  			return false;
   		}
   		*/
   
   		if(prepnm=="") {
 		 	alert("Please enter "+prRepTo);
  		 	return false;
   		}
  
		if(rate>100) {
  			alert(appRat+" should  be less than 100");
  			return false;
  		}
  		if(rate<0) {
  			alert(appRat+" should not be less than 0");
  		}
   		if(totalrow<=0) {
  			alert('There are no salary details so promotion can not be saved');
  			return false;
  		}

  		var totalrow =<%=totRows%> ; 		
 		var count=0;
		var values=0;
		for(var row = 0;row < totalrow ;row++) {
	 			values=document.getElementById("newAmt"+row).value;
	 			if(isNaN(values)){
	 				alert("Please enter valid Amount.");
	 				document.getElementById("newAmt"+row).value = "0.00";
	 				document.getElementById("newAmt"+row).focus();
	 				return false;
	 			}	 			
		}
  		
  		var pCode= document.getElementById('paraFrm_promCode').value;
  		if(pCode!="") {
  		  	var lockFlag = document.getElementById('paraFrm_lockFlag').value;
		  	if(lockFlag=='Y') {  
		  		alert('Promotion has been already locked so you can not update !');
		  		return false;
		  	}
		}
		   	return true;
 	}

  	function chk() {
  
 		var pCode= document.getElementById('paraFrm_promCode').value;
 	 	if(pCode=="") {
 		 	alert('Please select record to generate promotion letter');
 		 	return false;
 	 	}
  	 	document.getElementById('paraFrm').target="_blank";
     	document.getElementById('paraFrm').action="PromotionMaster_report.action";
     	document.getElementById('paraFrm').submit();
 	 	document.getElementById('paraFrm').target="main";  
  	}
 
	function salgrd() {
	
 		var ecode=document.getElementById('paraFrm_empName').value; 
		var pCode= document.getElementById('paraFrm_promCode').value;
		var empName=document.getElementById('employee').innerHTML.toLowerCase();   
 	
 	if(pCode=="") {
		if(ecode=="") {
  			alert("Please select "+empName);
  			return false;
  		} else {
  			callsF9(800,525,'PromotionMaster_f9salgradection.action');
  		}
  	} else {
  		  callsF9(800,525,'PromotionMaster_f9salgradection.action');
  		  return true;
		}
	}
	
	function calcFormula() {
 		var totalrow =<%=totRows%> ;
      	var gAmt= document.getElementById('paraFrm_grsAmt').value;
 	  	var frname= document.getElementById('paraFrm_frmId').value;
 	  	var frnm= document.getElementById('paraFrm_frmName').value;
 	  	var grdId= document.getElementById('paraFrm_salgrdId').value;
 	  	var grdNm= document.getElementById('paraFrm_salgrdName').value; 
		var pCode= document.getElementById('paraFrm_promCode').value;
		var ecode=document.getElementById('paraFrm_empName').value; 
	//for label
		var empName=document.getElementById('employee').innerHTML.toLowerCase();   
		var formula=document.getElementById('formula').innerHTML.toLowerCase();
    	var grAmt=document.getElementById('gross.amount').innerHTML.toLowerCase();
		if(ecode =="") {
	  		alert("please select "+empName);
	  		return false;
		}
		if(frnm=="") {
	  		alert("please select "+formula);
	  		return false;
	  	}
	  if(gAmt=="") {
		alert("Please enter "+grAmt);
	  	return false;
	  }
  	  if(totalrow<=0) {
		alert('There are no salary details so salary can not be calculate');
  		return false;
		}
		document.getElementById('paraFrm').target='_self';
		return true;
	}
 
</script>
