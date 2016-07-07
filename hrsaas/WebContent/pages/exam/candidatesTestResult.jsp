<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CandidatesTestResult" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
			<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/common/css/default/images/lines.gif"
				class="txt"><img
				src="../pages/common/css/default/images/lines.gif" width="16"
				height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="5" /></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">CandidatesTestResult
			</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">



				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%" colspan="1"><input type="button" class="token"
								onclick="return callReportResult();" value="    Export"
								 /> <s:submit cssClass="reset"
								action="CandidatesTestResult_reset" theme="simple"
								value="    Reset"  /> 
								<s:submit cssClass="search"
								action="CandidatesTestResult_search" theme="simple"
								value="    Search"  />
								</td>
							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>
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
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<td width="20%" class="formtext">Test From Date<font
									color="red">*</font>:</td>
								<td width="20%"><s:textfield name="fromDate" size="15"
									onkeypress="return numbersWithHiphen();" theme="simple"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								<td width="20%" class="formtext">Test To Date<font
									color="red">*</font>:</td>
								<td width="20%"><s:textfield name="toDate" size="15"
									onkeypress="return numbersWithHiphen();" theme="simple"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								</tr>

								<tr>
									<td width="20%" colspan="1" class="formtext" nowrap="nowrap">Paper
									:<s:hidden name="ctres.paperCode" value="%{paperCode}" /></td>
									<td width="20%" colspan="1"><s:textfield
										name="ctres.paperName" size="30" value="%{paperName}"
										theme="simple" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'CandidatesTestResult_f9Paper.action');">
									</td>
								</tr>
								<tr>
									
									<td width="20%" class="formtext">Lower CutOff:</td>
									<td width="20%"><s:textfield name="lcutoff" size="15"
										onkeypress="return numbersOnly();" theme="simple"
										maxlength="5" /></td>
									<td width="20%" class="formtext">Upper CutOff:</td>
									<td width="20%"><s:textfield name="ucutoff" size="15"
										onkeypress="return numbersOnly();" theme="simple"
										maxlength="5" /></td>
								</tr>

							</table>
							</td>
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
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center">Sr No.</td>
									<td class="formth" align="center">Candidate Name</td>
									<td class="formth" align="center">Test Date</td>
									<td class="formth" align="center">Test Time</td>
									<td class="formth" align="center">Score</td>
									<td class="formth" align="center">Result</td>
									<%
							int count = 0;
							%>

									<%
							int i = 0;
							%>
									<s:iterator value="list">

										<tr <%if(count%2==0){
									%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">
											<td width="10%" align="left"><%=++i%></td>
											<s:hidden value="%{candidateCode}"></s:hidden>
											<td width="30%" align="left"><s:property
												value="candidateName" /> <input type="hidden"
												name="candidateName" id="candidateName<%=i%>" /></td>
											<td width="15%" align="left"><s:property
												value="testDate" /> <input type="hidden" name="testDate"
												id="testDate<%=i%>" /></td>
											<td width="15%" align="left"><s:property
												value="testTime" /> <input type="hidden" name="testTime"
												id="testTime<%=i%>" /></td>
											<td width="10%" align="left"><s:property
												value="totScore" /> <input type="hidden" name="totScore"
												id="totScore<%=i%>" /></td>
											<td width="10%" align="left"><s:property value="result" />
											<input type="hidden" name="result" id="result<%=i%>" /></td>

										</tr>
									</s:iterator>

								</tr>




							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>



				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
			</table>
			<br />


			<label></label></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

	function callReportResult(){
  	var frmDate = document.getElementById("paraFrm_fromDate").value;
  	var toDate  = document.getElementById("paraFrm_toDate").value;
  	if(frmDate!=""){
  			if(!validateDate("paraFrm_fromDate","From Date"))
				return false;
	}else{
	alert("Enter From Date");
	document.getElementById("paraFrm_fromDate").focus();
	return false;
	}
	 if(toDate!=""){
  			if(!validateDate("paraFrm_toDate","To Date"))
			return false;
	}else{
	alert("Enter To Date");
	document.getElementById("paraFrm_toDate").focus();
	return false;
	}
	  	 if ((frmDate!="") && (toDate!="")) {
  			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_fromDate', 'From Date', 'To Date'))
			return false;
	}
	
			callReport('CandidatesTestResult_report.action');
	}
	

</script>
