<!-- Bhushan Dasare --><!-- Feb 15, 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PersonalDataChangeApproval" name="PersonalDataChangeApproval" id="paraFrm" validate="true" target="main" theme="simple">
	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Personal Data Change Approval</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="20%">Approver Comments :</td>
						<td>
							<s:textarea theme="simple" cols="70" rows="3" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<jsp:include page="personalDataChangeData.jsp">
					<jsp:param value="true" name="isForApproval"/>
				</jsp:include>
			</td>
		</tr>
	</table>
</s:form>