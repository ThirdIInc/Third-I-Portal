<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="AppraisalBellCurve" validate="true" id="paraFrm" theme="simple">
	
	<s:hidden name="sAppId"></s:hidden>
	<s:hidden name="sAppStartDate"></s:hidden>
	<s:hidden name="sAppEndDate"></s:hidden>
	
	<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td valign="bottom" class="txt"><strong class="formhead"><img
								src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Appraisal
							Bell Curve </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/help.gif"
								width="16" height="16" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Button Panel -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%">
					<input type="button" class="reset" theme="simple" value=" Reset"
						onclick="return resetScreen();" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Button Panel -->
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="15%" colspan="1" height="20" class="formtext"
								nowrap="nowrap"><label	name="appraisal.code" class="set" id="appraisal.code"
								ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red"> *
							</font> :</td>
							<td width="30%" colspan="1" height="20" class="formtext"><s:textfield
								name="sAppCode" size="30" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15" theme="simple"
								onclick="javascript:callsF9(500,325,'AppraisalBellCurve_f9SelectAppraisal.action'); "></td>
							<td width="55%" colspan="2" height="20" class="formtext"><label
								name="bell.from.date" class="set"
								id="bell.from.date" ondblclick="callShowDiv(this);"><%=label.get("bell.from.date")%></label>
							: <s:property value="sAppStartDate"></s:property>&nbsp; <label
								name="bell.to.date" class="set"
								id="bell.to.date" ondblclick="callShowDiv(this);"><%=label.get("bell.to.date")%></label>
							: <s:property value="sAppEndDate"></s:property></td>
						</tr>
						<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden
								name="divCode" /> <s:textfield
								name="divName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:validateF9('f9Div');">
							</td>

							<td colspan="1" width="15%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td width="40%" colspan="1"><s:hidden name="branchCode" /> <s:textfield
								name="branchName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:validateF9('f9Branch');">
							</td>
						</tr>
						<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden name="deptCode" /> <s:textfield
								name="deptNameUp" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:validateF9('f9Dept');">
							</td>
							<td colspan="1" width="15%"><label  class = "set"  id="appraiser" name="appraiser" ondblclick="callShowDiv(this);"><%=label.get("appraiser")%></label> :</td>
							<td colspan="1" width="40%"><s:hidden name="apprEmpCode" /> <s:textfield
								name="apprEmpName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:validateF9('f9Employee');">
							</td>
						</tr>
						<tr>
							
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<s:if test="%{sAppCode != null && sAppCode != ''}">
				<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" height="410">
					
							<s:if test="flgData">
								<tr>
									<td class="formtext" align="center"><img
										src="../pages/images/areachart.png" WIDTH="500" HEIGHT="400"
										BORDER="0"></td>
										
									<td class="formtext" align="center">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="sortable">
    										<thead> 
											<tr>
												<td align="center" colspan="4" height="50">
													Bell Curve Data
												</td>	
											</tr>
											</thead>
											<tr>
												<td class="formth">Sr. No.</td>
												<td class="formth">Category</td>
												<td class="formth">Range </td>
												<td class="formth">No of Employee</td>
											</tr>
											<% int count = 0; %>
											<s:iterator value="lstBellCurveData">	
											<tr>
												<td class="sortableTD" align="center"><%=++count%></td>
												<td class="sortableTD" align="center"><s:property value="sApprScoreValue" /></td>
												<td class="sortableTD" align="center"><s:property value="sApprScoreFrom" />-<s:property value="sApprScoreTo" /></td>
												<td class="sortableTD" align="center"><s:property value="sNoOfEmployess" /></td>
											</tr>
											</s:iterator>	
										</table>
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td width="100%" align="center" class="formtext" colspan="2"><font
										color="red">There is no data available to plot bell curve for this appraisal calendar.</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
			</s:if>
			</td>
		</tr>
		
		<!-- Button Panel -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<input type="button" class="reset" theme="simple" value=" Reset"
						onclick="return resetScreen();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Button Panel -->
	</table>
	
</s:form>
<script type="text/javascript">
	function validateF9(action)
	{
		if(document.getElementById("paraFrm_sAppCode").value=="")
		{
			alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
			document.getElementById("paraFrm_apprCode").focus();
			return false;
		}
				
		javascript:callsF9(500,325,'AppraisalBellCurve_'+action+'.action'); 
	}
	
	function resetScreen()
	{
		document.getElementById('paraFrm_sAppId').value='';
		document.getElementById('paraFrm_sAppCode').value='';
		document.getElementById('paraFrm_sAppStartDate').value='';
		document.getElementById('paraFrm_sAppEndDate').value='';
		document.getElementById('paraFrm_divName').value='';
		document.getElementById('paraFrm_divCode').value='';
		document.getElementById('paraFrm_branchName').value='';
		document.getElementById('paraFrm_branchCode').value='';
		document.getElementById('paraFrm_deptNameUp').value='';
		document.getElementById('paraFrm_deptCode').value='';
		document.getElementById('paraFrm_apprEmpCode').value='';
		document.getElementById('paraFrm_apprEmpName').value='';
		
		
		document.getElementById("paraFrm").action="AppraisalBellCurve_input.action";
		document.getElementById("paraFrm").submit();
	}
</script>
