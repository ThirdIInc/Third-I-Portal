<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.util.Vector"%>
<%
	Object[][] reqObj = (Object[][]) request.getAttribute("reqObj");
	Object[][] intDtlObj = null;
	Vector vect = null;
	try {
		intDtlObj = (Object[][]) request.getAttribute("intDtlObj");
		try {
			vect = (Vector) request.getAttribute("evalDtlObj");
		} catch (Exception e) {
			vect = null;
		}
	} catch (Exception e) {
		intDtlObj = null;
	}
%>
<%@page import="java.util.Vector;"%>
<s:form action="InterviewDetails" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="rowId" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<!-- Final Table -->

		<tr>
			<td width="100%" colspan="4" class="formtext"><input
				type="button" value="Close" class="token" onclick="window.close();"></td>
		</tr>

		<%
		if (intDtlObj != null && intDtlObj.length > 0) {
		%>
		<tr>
			<td width="93%" class="txt"><strong class="text_head">Previous
			Interview Details</strong></td>
		</tr>

		<tr>
			<td colspan="5" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<!--Schedule Test-->
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="5%" valign="top" nowrap="nowrap"><strong>Requisition
					Details</strong></td>
				</tr>
				<%
				if (reqObj != null) {
				%>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable" class="formbg" id="tblCandidateList">
						<!-- table 6 -->
						<tr>
							<td width="25%">Requisition :</td>
							<td><%=reqObj[0][0]%></td>
						</tr>
						<tr>
							<td>Candidate Name :</td>
							<td><%=intDtlObj[0][3]%></td>
						</tr>
						<tr>
							<td>Position :</td>
							<td><%=reqObj[0][1]%></td>
						</tr>
						<tr>
							<td>Division :</td>
							<td><%=reqObj[0][2]%></td>
						</tr>
						<tr>
							<td>Branch :</td>
							<td><%=reqObj[0][3]%></td>
						</tr>
						<tr>
							<td>Department :</td>
							<td><%=reqObj[0][4]%></td>
						</tr>
						<tr>
							<td>Hiring Manager :</td>
							<td><%=reqObj[0][5]%></td>
						</tr>

					</table>
					</td>
				</tr>
				<%
				}
				%>
			</table>
			</td>
		</tr>
		<%
		} else {
		%>
		<tr>
			<td width="93%" class="formtext"><strong class="formhead">There
			is no interview conducted for selected candidate</strong></td>
		</tr>
		<%
		}
		%>
		<%
				if (intDtlObj != null) {
				for (int i = 0; i < intDtlObj.length; i++) {
					if (vect != null && vect.get(i) != null) {
				Object evalDtlObj[][] = (Object[][]) vect.get(i);

				if (evalDtlObj != null && evalDtlObj.length > 0) {
					int k = 0;
		%>
		<tr>
			<td colspan="5" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<!--Schedule Test-->
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" valign="top"><strong><%=intDtlObj[i][1]%></strong></td>
					<td width="50%" valign="top"><strong>Interviewer Name:-<%=intDtlObj[i][2]%></strong></td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable" id="tblCandidateList" class="formbg">
						<!-- table 6 -->

						<tr>
							<td class="formth" width="25%">Parameters</td>
							<td class="formth" width="25%">Description</td>
							<td class="formth" width="25%">Rating</td>
							<td class="formth" width="25%">Remarks</td>

						</tr>

						<%
										try {

										for (int m = 0; m < evalDtlObj.length; m++) {
						%>


						<tr>

							<td width="25%"><%=String.valueOf(evalDtlObj[m][6])%></td>
							<td width="25%"><%=String.valueOf(evalDtlObj[m][7])%></td>
							<td width="25%" align="center">
								<%
									if(String.valueOf(evalDtlObj[m][1]).equals("0")) {
							 	%> NA
								
								<%
									} else { 
								%>
								<%=String.valueOf(evalDtlObj[m][1])%>
								
								<%	
									}
								%>
							</td>
							<td width="25%"><%=String.valueOf(evalDtlObj[m][5])%></td>
						</tr>


						<%
									}

									} catch (Exception e) {
										//e.printStackTrace();

									}
						%>

						<%
						if (evalDtlObj != null && evalDtlObj.length > 0) {
						%>

						<tr>
							<td width="25%">Interview Status :-</td>
							<td width="25%"><%=evalDtlObj[0][2]%></td>
						</tr>
						<%
						}
						%>
					</table>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable" id="tblCandidateList" class="formbg">
						<tr>
							<td width="25%" colspan="1">Constraints :-</td>
							<td width="55%" colspan="2"><%=evalDtlObj[0][4]%></td>
							<td width="20%" colspan="2">&nbsp;</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable" id="tblCandidateList" class="formbg">
						<tr>
							<td width="25%" colspan="1">Comments :-</td>
							<td width="55%" colspan="2"><%=evalDtlObj[0][3]%></td>
							<td width="20%" colspan="2">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%
					}
					}
				}
			}
		%>
		<tr>
			<td width="100%" colspan="4" class="formtext"><input
				type="button" value="Close" class="token" onclick="window.close();"></td>
		</tr>


	</table>
</s:form>