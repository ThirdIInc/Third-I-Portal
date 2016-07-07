<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<style>
.button_open {
	font-family: Arial;
	font-size: 25;
	font-weight: bold;
	background-color: #A0FF00;
}
.button_close {
	font-family: Arial;
	font-size: 25;
	font-weight: bold;
	background-color: #FF9200;
}
</style>



<s:form action="OpenCloseOffice" validate="true" id="paraFrm" target="main"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="93%" class="txt"><strong
							class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong><strong class="text_head">Open/Close Office</strong></td>
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
				<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
					<tr><s:hidden name="message"></s:hidden>
						<td colspan="1"><font color="red" size="4"><s:property value="message"/></font>
						</td>
					</tr>
					<tr>
						<td colspan="1" align="center"><s:submit cssClass="button_open" action="OpenCloseOffice_open"
							theme="simple" value=" Open"/>
						&nbsp;<s:submit cssClass="button_close" action="OpenCloseOffice_close"
							theme="simple" value=" Close"/> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>	
</s:form>	

