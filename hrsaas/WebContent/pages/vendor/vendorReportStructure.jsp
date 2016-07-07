<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="VendorReportStructure" id="paraFrm" theme="simple"
	validate="true">
	<s:hidden name="reportingID" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td valign="middle">
			<fieldset><legend class="legend"><img
				src="../pages/mypage/images/icons/earnings16.png" height="16"
				width="16"> &nbsp;Vendor Reporting Structure</legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>
					<table border="0" cellpadding="2" cellspacing="2" align="right">
						<tr>
							<td width="70%"></td>
							<td width="2%"><img
								src="../pages/mypage/images/icons/draft.png" width="10"
								height="10" /></td>
							<td width="5%"><s:a href="#" onclick=" saveFun();">Save</s:a></td>
							<td width="1%">|</td>
							<td width="2%"><img
								src="../pages/mypage/images/icons/sendforapproval.png"
								width="10" height="10" border="0" /></td>
							<td width="5%"><s:a href="#" onclick="resetFun();">Reset</s:a></td>
							<td width="1%">|</td>
							<td width="2%"><img
								src="../pages/mypage/images/icons/back.png" width="10"
								height="10" /></td>
							<td width="5%"><s:a href="#" onclick=" backFun();">Back</s:a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="1" bgcolor="#cccccc" class="style1"></td>
				</tr>
				<tr>
					<td>
					<fieldset><legend class="legend1">Reporting
					Structure </legend>
					<table cellpadding="2" cellspacing="1" border="0" align="left">
						<tr>
							<td width="18%"><label class="set" name="partnerName"
								id="partnerName1" ondblclick="callShowDiv(this);"><%=label.get("partnerName")%></label></td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>
							<td><label><span class="text1" /><s:hidden
								name="partnerCode" /> <s:textfield name="partnerName" size="20"
								readonly="true" /></label><img
								src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'VendorReportStructure_f9partner.action');"></td>
						</tr>
						<tr>
							<td width="18%"><label class="set" name="approver"
								id="approver1" ondblclick="callShowDiv(this);"><%=label.get("approver")%></label></td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>
							<td><label><span class="text1" /><s:select
								name="approverType" headerKey="-1" headerValue="--Select--"
								list="#{'Mgr':'Manager','Acc':'Accountant','Ack':'Acknowledged'}"></s:select></label></td>

						</tr>
						<tr>
							<td width="18%"><label class="set" name="lblLevel"
								id="lblLevel1" ondblclick="callShowDiv(this);"><%=label.get("lblLevel")%></label></td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>
							<td><label><span class="text1" /><s:textfield
								name="level" size="20" maxlength="1" onkeypress="return numbersOnly()" /></label></td>

						</tr>
						<tr>
							<td width="15%" colspan="1"><label class="set"
								name="approverNm" id="approverNm1"
								ondblclick="callShowDiv(this);"><%=label.get("approverNm")%></label></td>
							<td width="1%">*</td>
							<td width="1%">:</td>
							<td colspan="2" width="55%"><s:textfield theme="simple"
								name="approverToken" readonly="true" size="20"></s:textfield> <s:textfield
								theme="simple" name="approverName" readonly="true" size="47" />
							<s:hidden name="approverCode" /><img
								src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'VendorReportStructure_f9action.action');"></td>
						</tr>
						</fieldset>
					</table>
					</td>
				</tr>

			</table>
			</fieldset>
			</td>
		</tr>
	</table>
</s:form>

<script> 
function saveFun(){
		//var paCode= document.getElementById('paraFrm_partnerCode').value;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'VendorReportStructure_save.action';
		document.getElementById('paraFrm').submit();
}

function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'VendorReportStructure_reset.action';
		document.getElementById('paraFrm').submit();
}

function backFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'VendorReportStructure_input.action';
		document.getElementById('paraFrm').submit();
	}
</script>