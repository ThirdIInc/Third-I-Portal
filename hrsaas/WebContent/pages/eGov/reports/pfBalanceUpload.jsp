
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<!-- Nilesh Dhandare 24th June 2011 -->
<s:form action="PFBalance" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" colspan="1" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" colspan="1" class="txt"><strong
						class="text_head">PF Balance Upload</strong></td>
					<td width="4%" colspan="1" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
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
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="from.year" name="from.year" ondblclick="callShowDiv(this);"><%=label.get("from.year")%></label><font
								color="red">*</font>:</td>
							<td><s:textfield name="fromYear"
								onkeypress="return numbersOnly();" onblur="add()" theme="simple"
								maxlength="4" size="25" /></td>


							<td colspan="1" width="20%"><label id="to.year"
								name="to.year" ondblclick="callShowDiv(this);"><%=label.get("to.year")%></label><font
								color="red">*</font>:</td>
							<td><s:textfield name="toYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label class="set"
								id="division.name" name="division.name"
								ondblclick="callShowDiv(this);"><%=label.get("division.name")%></label>
							<font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="divId" /> <s:textfield
								name="divisionName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PFBalance_f9division.action');">

							</td>

							<td colspan="1" width="20%"><label class="set"
								id="paybill.name" name="paybill.name"
								ondblclick="callShowDiv(this);"><%=label.get("paybill.name")%></label>
							:</td>
							<td><s:hidden name="paybillId" /> <s:textfield
								name="payBill" theme="simple" readonly="true" maxlength="50"
								size="25" /><img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PFBalance_f9payBill.action');"></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td >
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td align="center"><s:submit type="button" cssClass="token"
						onclick="return check();" value=" Download Template " action="PFBalance_uploadReport" /></td>
				</tr>
			</table>
			<label></label></td>
		</tr>


	</table>
</s:form>
