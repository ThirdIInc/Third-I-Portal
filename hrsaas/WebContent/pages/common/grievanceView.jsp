<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="Suggestion" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3">
			<table>
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">View
					Suggestion </strong></td>
					<td width="5%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
				<tr>
					<td>
					<table width="100%" colspan="4" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="22%" height="22" class="formtext">Employee Name:</td>
							<td height="27%" height="22"><b> <s:property
								value="emplName"/></b></td>
							<td width="4%" height="22">&nbsp;</td>
							<td width="22%" class="formtext">Department :</td>
							<td width="28%"><b><s:property  value="empdept"
								/></b></td>
						</tr>
						<tr>
							<td width="22%" height="22" class="formtext">Branch :</td>
							<td width="27%" height="22"><b><s:property 
								value="empbranch"/></b></td>
							<td width="4%" height="22">&nbsp;</td>
							<td width="22%" class="formtext">Designation :</td>
							<td width="28%"><b><s:property  value="empdesg"
								/></b></td>
						</tr>

					</table>
					</td>
				</tr>


				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
								<tr>
									<td colspan="2" class="formhead" width="100%"><strong
										class="forminnerhead">Suggestion Particulars </strong></td>
								</tr>
								
								<tr>
									<td class="formtext" width="20%" colspan="1">Date :</td>
									<td width="80%" colspan="1"><s:property value="suggDate" /></td>
	
								</tr>
								<tr>
									<td colspan="2" width="100%" >&nbsp;</td>
								</tr>
								<tr>

									<td height="22" class="formtext" >Subject :</td>
									<td ><s:property value="suggestion" /></td>
								</tr>
								<tr>
									<td colspan="2" width="100%" >&nbsp;</td>
								</tr>
								<tr>
									<td  height="22" class="formtext" >Implement :</td>
									<td ><s:property value="suggimple" /></td>
									</tr>
									<tr>
									<td colspan="2" width="100%" >&nbsp;</td>
								</tr>
							
                                    <tr>
									<td height="22" class="formtext" >Improvement :</td>
									<td ><s:property value="suggimprove" /></td>
								</tr>
								
																
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>





</s:form>