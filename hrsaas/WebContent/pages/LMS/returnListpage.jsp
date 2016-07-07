<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table align="center" width="100%" class="formbg" border="0"
		cellspacing="0" cellpadding="1">

		<tr>
			<td valign="bottom" class="txt" colspan="2">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Annual
					Returns </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="pageToShow" value="general_workforce" />
		<s:hidden name="frequency" />
		<s:hidden name="fromPeriod" />
		<s:hidden name="toPeriod" />
		<tr>
			<td class="formtext" colspan="2">
			<table width="100%">
				<tr>
					<td width="78%" colspan="1"></td>
					<td width="22%" colspan="1">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext" colspan="2">
			<table width="100%">
				<tr>
					<td width="22%" height="22" class="formtext">Select Company :<font
						color="red">*</font></td>
					<td width="17%" height="22"><s:textfield name="orgName"
						theme="simple" size="35" readonly="true" /></td>
					<td width="27%" height="22" class="formtext"><img
						id='ctrlHide' align="absmiddle"
						src="../pages/common/css/default/images/search2.gif"
						onclick="javascript:callsF9(500,325,'ReturnAct_f9division.action');"></td>
					<td width="18%" height="22"><s:hidden name="orgId" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="orgFlag">

			<tr>
				<td>
				<table class="" width="100%">
					<tr>
						<td><a href="#" onclick="setAction('P')"> Pending Returns</a>
						| <a href="#" onclick="setAction('C')"> Returns Filed</a></td>
					</tr>
				</table>
				</td>
			</tr>

			<!-- PENDING RETURNS BEGIN -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<!-- PAGING TR COMES HERE -->
					<tr>
						<td colspan="2">
						<table width="100%" class="sorttable">
							<tr>
								<s:hidden name="myPage" id="myPage" />
								<td class="formth" width="5%"><b><label class="set"
									name="ser.no" id="ser.no" ondblclick="callShowDiv(this);"><%=label.get("ser.no")%></label></b>
								</td>

								<td class="formth" width="10%"><b><label class="set"
									name="frequency" id="frequency" ondblclick="callShowDiv(this);"><%=label.get("frequency")%></label></b>
								</td>

								<td class="formth" width="40%"><b><label class="set"
									name="return.name" id="return.name"
									ondblclick="callShowDiv(this);"><%=label.get("return.name")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="return_to_be_filed_for" id="return_to_be_filed_for"
									ondblclick="callShowDiv(this);"><%=label.get("return_to_be_filed_for")%></label></b>
								</td>
								<td class="formth" width="10%"><b><label class="set"
									name="return.status" id="return.status"
									ondblclick="callShowDiv(this);"><%=label.get("return.status")%></label></b>
								</td>
								<td class="formth" width="15%">Apply Return</td>
								<td class="formth" width="15%">Finalize</td>
								<td class="formth" width="15%">Payment Gateway</td>
							</tr>

							<%
								int count = 0;
							%>
							<s:iterator value="pendingReturnsList">
								<tr>

									<td class="sortableTD">
									<%
										++count;
									%><%=count%><s:hidden name="srNo" /></td>
									<s:hidden name="returnID" />
									<s:hidden name="returnCode" />
									<s:hidden name="returnMasterId" />
									<td class="sortableTD" align="left">&nbsp;<s:property
										value="returnFrequency" />&nbsp;</td>
									<td class="sortableTD" align="left">
									<table>
										<%
											int srNo = 1;
										%>
										<s:iterator value="returnActNames">
											<tr>
												<td><%=srNo++%>)<s:property value="returnName" /></td>
											</tr>
										</s:iterator>
									</table>

									</td>
									<td class="sortableTD">&nbsp;<s:property
										value="financialYear" />&nbsp;</td>
									<td class="sortableTD" align="left">&nbsp;<s:property
										value="returnStatus" />&nbsp;</td>
									<td align="center" class="sortableTD"><input type="button"
										value="Generate Return" class="token"
										onclick="generateReturn('<s:property value="returnID" />','<s:property value="returnFrequency" />',
											'<s:property value="financialYear" />')" /></td>
									<td align="center" class="sortableTD"><input type="button"
										value="Finalize Returns" class="token"/></td>
									<td align="center" class="sortableTD"><input type="button"
										value="Payment Gateway" class="token"/></td>
								</tr>

							</s:iterator>

							<%
								if (count == 0) {
							%>
							<tr align="center">
								<td colspan="7" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
								} else {

										}
							%>

							<tr>
								<td align="right" colspan="5"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>
	</table>
</s:form>

<script>
	
function setAction(listType){
    if(listType=="C"){
      	document.getElementById('paraFrm').action='ReturnAct_callReturnList.action?status='+listType; 
      	document.getElementById('paraFrm').submit();
     }if(listType=="P"){
      	document.getElementById('paraFrm').action='ReturnAct_callReturnList.action?status='+listType;
      	document.getElementById('paraFrm').submit();
     }
}

function generateReturn(returnID,returnFrequency, financialYear){
	var period = "";
	if(returnFrequency == "Annual"){
		returnFrequency = "A";
		period = financialYear.split("-");
		document.getElementById('paraFrm_fromPeriod').value = period[0];
		document.getElementById('paraFrm_toPeriod').value = period[1];
	}
	else if(returnFrequency == "Monthly"){
		returnFrequency = "M";
		document.getElementById('paraFrm_fromPeriod').value = financialYear;
	}
	document.getElementById('paraFrm_frequency').value = returnFrequency;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'ReturnAct_generateReturn.action?returnID='+returnID;
	document.getElementById('paraFrm').submit();
}
	
	
</script>