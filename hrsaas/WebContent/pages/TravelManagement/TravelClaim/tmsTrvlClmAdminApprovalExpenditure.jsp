<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form name="" action="" validate="" id="paraFrm" theme="simple">

<s:hidden name="tmsClmAppId" />
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
					<td width="93%" class="txt"><strong class="text_head">Travel Claim
					Expenditure</strong></td>
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
					<td colspan="4" width="100%">Expenditure Details</td>
				</tr>
				<tr>
					<td width="30%"><label class="set" name="lodgeExpend" id="lodgeExpend"
						ondblclick="callShowDiv(this);"><%=label.get("lodgeExpend")%></label>
					:</td>
					<td colspan="3"><s:property value="lodgeExpenditureAmount" /></td>
				</tr>
				<tr>
					<td width="30%"><label class="set" name="travelExpend" id="travelExpend"
						ondblclick="callShowDiv(this);"><%=label.get("travelExpend")%></label>
					:</td>
					<td colspan="3"><s:property value="travelExpenditureAmount" /></td>
				</tr>
				<tr>
					<td width="30%"><label class="set" name="claimExpend" id="claimExpend"
						ondblclick="callShowDiv(this);"><%=label.get("claimExpend")%></label>
					:</td>
					<td colspan="3"><s:property value="claimExpenditureAmount" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
