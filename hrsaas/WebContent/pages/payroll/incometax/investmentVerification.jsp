<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<div id="progressBar" style="position: absolute;z-index: 3; width: 480px; height: 120px; top: 100px; left: 160px; padding: 10px;">
<table width="100%" border="0" align="center" >
	<tr>
		<td align="center"><img src="../pages/images/ajax-loader.gif">
		</td>
	</tr>
	<tr>
		<td align="center"><span style="color: red; font-size: 16px; font-weight: bold;">Processing...</span>
		</td>
	</tr>
	<tr>
		<td align="center"><span style="color: red; font-size: 16px; font-weight: bold;">
			Please do not close the browser and do not click anywhere.</span></td>
	</tr>
</table>
</div>
<s:form action="InvestmentVerification" validate="true" id="paraFrm" theme="simple">
<div id="confirmationDiv" style='position: absolute; z-index: 3; width:100 px; height: 150px; visibility: hidden; top: 200px; left: 150px;'></div>
<s:hidden name="empId"/>
<s:hidden name="navigateTo"/>
<s:hidden name="dataPath" />
<s:hidden name="show" />
<s:hidden name="hiddenMypage" />
<s:hidden name="myPage" id="myPage"/>
<s:hidden name="nodata" />
<s:hidden name="saveType"/>
<s:hidden name="settCode"/>
<s:hidden name="empCode"/>
<s:hidden name="divId"/>
<s:hidden name="branchId"/>
<s:hidden name="deptId"/>
<s:hidden name="dupDiv"/>
<s:hidden name="dupbranch"/>
<s:hidden name="dupDept"/>
<s:hidden name="dupStatus"/>
<s:hidden name="settlementFlag"/>
<s:hidden name="settlementApplnStatus"/>
<s:hidden name="status"/>
<s:hidden name="exemptionsListViewFlag"/>
<s:hidden name="otherListViewFlag"/>
<s:hidden name="deductionsVIAListViewFlag"/>
<s:hidden name="deductionsVIListViewFlag"/>
<s:hidden name="donationListViewFlag"/>
<s:hidden name="monthlyFlag"/>
<s:hidden name="empSaveFlag"/>
<s:hidden name="proofAttachedFlag"/>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Investment Verification</strong></td>
					<td width="3%" valign="top">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
					<td>
						<s:if test="settlementFlag">
							<s:if test='%{settlementApplnStatus == "F"}'>
							</s:if><s:else>
								<input type="button" cssClass="token" value="Save Verified Amount"	onclick="return saveVerificationList();" />
							</s:else>
							<input type="button" class="token" onclick="backToSettlement();" value="Back" />	
						</s:if>
						<s:else>
							<input type="button" cssClass="token" value="Save Verified Amount"	onclick="return saveVerificationList();" />
							<input type="button" class="token" onclick="investmentListFun();" value="Employee Investment List" />
							<input type="button" class="token" onclick="reportFun();" value="Report" />
							<input type="button" class="token" onclick="resetFun();" value="Reset" />
						</s:else>
					</td>
					<td width="30%" align="right">
						<font color="red">*</font> Indicates Required
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead">Select any/all search criteria for Employee Investment </strong>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="4">
							<strong class="formhead">
								<label id=selectPeriod name="selectPeriod" ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="20%" >
							<label  class = "set"  id="fromyear" name="fromyear" 
							ondblclick="callShowDiv(this);"><%=label.get("fromyear")%></label>:<font color="red">*</font></td>
							<td width="30%"> <s:textfield
								name="fromYear" theme="simple" readonly="false"  size="25"
								maxlength="4" onkeypress="return numbersOnly();" onblur="add()" />
							</td>
							<td width="20%" >
							<label  class = "set"  id="toyear" name="toyear" ondblclick="callShowDiv(this);">
							<%=label.get("toyear")%></label>:<font color="red">*</font></td>
							<td width="30%">
								<s:textfield name="toYear" theme="simple" readonly="true" maxlength="4" size="25" /> 
							</td>
						</tr>
						<s:if test="settlementFlag">
						</s:if>
						<s:else>
						<tr>
							<td width="20%">
								<strong class="formhead"><label
									id=selectReportFilter name="selectReportFilter"
									ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
								</strong>
							</td>
							<td colspan="3">
								<a href="#" style="color:blue;" title="Expand Filter" onclick="expandFilter();">Advanced Filter</a>
							</td>
						</tr>
						</s:else>
						<s:if test="settlementFlag">	
							<tr>
								<td width="20%">
									<label class="set" id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font color="red">*</font>
								</td>
								<td colspan="3" width="100%" nowrap="nowrap">
							 		<s:textfield name="empToken" size="25" readonly="true"/>
									<s:textfield name="empName" size="70" readonly="true"/>
								</td>
							</tr>
						</s:if>
						<s:else>
						<tr id="employeeBlock">
							<td width="20%"><label class="set" id="employee" name="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font color="red">*</font></td>
							<td colspan="3" width="100%" nowrap="nowrap">
							 	<s:textfield name="empToken" size="25" readonly="true"/>
								<s:textfield name="empName" size="70" readonly="true"/>
								<s:if test="settlementFlag">
									&nbsp;
								</s:if><s:else>
									<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle" width="16"
									onclick="clearFilterOnEmployeeSelect();javascript:callsF9(500,325,'InvestmentVerification_f9employee.action');">
								</s:else>
							</td>
						</tr>
						<!--<tr>
							<td colspan="4" align="center" >
								<input type="button" id="expandRef" value="Show Filter" onclick="expandFilter();" class="token">
							</td>
						</tr>-->
						<tr id="selectFilterBlock" style="display:none;">
							<td colspan="4">
								<table width="100%" border="0" cellpadding="1" cellspacing="1">
									<tr>
										<td width="20%"><label  class = "set"  id="division" name="division" 
										ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
										<td> <s:textfield name="divName" theme="simple" readonly="true" maxlength="50" size="25" /> 
											<s:if test="filterflag"></s:if><s:else></s:else>
											<img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="15" align="absmiddle" width="16"
											onclick="javascript:callsF9(500,325,'InvestmentVerification_f9div.action');">
										</td>
										<td width="20%">
											<label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
										</td>
										<td>
										 	<s:textfield name="branchName" theme="simple" readonly="true" maxlength="50" size="25" />
											<s:if test="filterflag"></s:if><s:else></s:else>
											 <img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="15" align="absmiddle" width="16"
											onclick="javascript:callsF9(500,325,'InvestmentVerification_f9brn.action');">
										</td>
									</tr>
									<tr>
										<td>
											<label class ="set" id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
										</td>
										<td>
											<s:textfield name="deptName" theme="simple" readonly="true" maxlength="50" size="25"/>
											<s:if test="filterflag"></s:if><s:else></s:else>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle" width="16"
											onclick="javascript:callsF9(500,325,'InvestmentVerification_f9dept.action');">
											
										</td>
										<td>
											<label class="set" id="paybill1" name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:
										</td>
										<td><s:hidden name="paybillId"/>
											<s:textfield theme="simple" name="paybillName" readonly="true" size="25"/>
											<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
											onclick="javascript:callsF9(800,525,'InvestmentVerification_f9paybill.action');">
										</td>
									</tr>
									<tr>
										<td>
											<label  name="dupstatus" id="dupstatus" ondblclick="callShowDiv(this);"><%=label.get("dupstatus")%></label>:
										</td>
										<td> 
											<s:select theme="simple" name="Estatus" cssStyle="width:140" list="#{'A':'All','S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}"/>
										</td>
										<td>
											<label  name="invStatus" id="invStatus" ondblclick="callShowDiv(this);"><%=label.get("invStatus")%></label>:
										</td>
										<td> 
											<s:select theme="simple" name="listType" cssStyle="width:140" list="#{'A':'All','N':'Non-Verified','V':'Verified'}"/>
										</td>
									</tr>
									<tr>
										<td ><label  name="invSection" id="invSection" ondblclick="callShowDiv(this);"><%=label.get("invSection")%></label>:<font color="red">*</font></td>
										<td  colspan="3"><s:hidden name="investmentCode"/>
											<s:textfield name="investmentName" readonly="true" size="25"/>
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle" width="16"
												onclick="javascript:callsF9(500,325,'InvestmentVerification_f9section.action');">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<!--<tr>
							<td colspan="4" align="center"  class="formhead" width="30%"><b>(OR)</b></td>
						</tr>-->
						</s:else>
						<!-- 
						<tr>
							<td colspan="4">
								<strong>Select Investment Proofs</strong>
							</td>
						</tr>
						<tr>
							<td ><label  name="invProof" id="invProof" ondblclick="callShowDiv(this);"><%=label.get("invProof")%></label> :</td>
							<td  colspan="3"> <s:select name="investmentGroup" cssStyle="width:270" headerKey="" headerValue="-- Select --"
								list="#{'E':'Exemption under 10-17','D':'Deductions under chapter VI-A','S':'Deductions under chapter VI(Sec 80C)','O':'Other Income','M':'Monthly Accommodations & Conveyance Declaration'}" />
							</td>
						</tr>
						 -->
						<!--<tr>
							<td colspan="4" align="center">
								<input type="button" class="token" onclick="investmentListFun()" value="Employee Investment List" />
								<input type="button" class="token" onclick="reportFun()" value="Report" />
								<s:if test="settlementFlag">
									&nbsp;
								</s:if><s:else>
									<input type="button" class="token" onclick="resetFun()" value="Reset" />
								</s:else>
							</td>
						</tr>-->
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	<!-- ///////////////////////////////////////////////////////////////  -->	
	<%
		int totalPage = 0;
		int pageNo = 0;
	%>
	<%	
		int exmpCount = 0;
		int monthlySr = 0;	
		int otherCount = 0;
		int deductionsVIACount = 0;
		int deductionsVICount = 0;
		int donationCount = 0; 
	%>
	<s:if test="empListFlag">
		<tr>
			<td colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="formbg">
						<tr>
							<td colspan="5" width="60%" class="formtxt">
								<strong>Employee Investment List for Verification :</strong>
							</td>
							<td colspan="4" align="right" nowrap="nowrap">
							<s:if test="paginationFlag">
								&nbsp;
							</s:if><s:else>
								<s:if test="settlementFlag">
								</s:if>
								<s:else>
									<img src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" title="Previous Employee" onclick="previousEmployeeFun()"/>
									<img src="../pages/common/img/next.gif" width="10" height="10" class="iconImage" title="Next Employee" onclick="nextEmployeeFun()"/>
								</s:else>
							</s:else>
							</td>
						</tr>
					
						<s:if test="exemptionsListViewFlag">
							<tr style="background:#E2E6EF;">
								<td colspan="4" nowrap="nowrap">
									<strong>Exemptions under section 10 & 17</strong>
								</td>
								<s:if test="paginationFlag">
									<td colspan="5" width="100%" align="right" ><b>Page:</b>
										<%
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										%> 
										<a href="#" onclick="callConfirmPage('1', 'F', '<%=totalPage%>', 'pageNoField');" >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" /></a>&nbsp; 
										<a href="#" onclick="callConfirmPage('P', 'P', '<%=totalPage%>', 'pageNoField');">
										<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" /></a>
										<input type="text"	name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
											maxlength="10" onkeypress="callPageText(event, '<%=totalPage%>', 'InvestmentVerification_submit.action');return numbersOnly();" />
										of <%=totalPage%> 
										<a href="#" onclick="callConfirmPage('N', 'N', '<%=totalPage%>', 'pageNoField');">
										<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; 
										<a href="#" onclick="callConfirmPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'pageNoField');">
										<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /></a>
									</td>
								</s:if><s:else>
									<td colspan="5" width="100%" align="right" >
										&nbsp;
									</td>
								</s:else>
							</tr>
							<tr>
								<td width="5%" align="center" class="formth">
									<label class="set" id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LempName" name="LempName" ondblclick="callShowDiv(this);"><%=label.get("LempName")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LinvName" name="LinvName" ondblclick="callShowDiv(this);"><%=label.get("LinvName")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LSection" name="LSection" ondblclick="callShowDiv(this);"><%=label.get("LSection")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LChapter" name="LChapter" ondblclick="callShowDiv(this);"><%=label.get("LChapter")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LInvAmt" name="LInvAmt" ondblclick="callShowDiv(this);"><%=label.get("LInvAmt")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LviewDoc" name="LviewDoc" ondblclick="callShowDiv(this);"><%=label.get("LviewDoc")%></label>
								</td>
								<td width="8%" align="center" class="formth">
									<label class="set" id="LverifiedLable1" name="LverifiedLable" ondblclick="callShowDiv(this);"><%=label.get("LverifiedLable")%></label></br>
									<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" onclick="checkAllRecords(this, 'exemptionsChk', 'exemptionsInvAmountItt', 'exemptionsVerifiedAmountItt', 'exemptLengthVar','exemptionsVerifiedItt');" />
								</td>
								<td width="5%" align="center" class="formth">
									<label class="set" id="LverifiedAmt" name="LverifiedAmt" ondblclick="callShowDiv(this);"><%=label.get("LverifiedAmt")%></label>
								</td>
							</tr>
							<s:iterator value="exemptionsInvestmentList">
								<tr>
									<td  class="sortabletd" align="center"><%=++exmpCount%></td>
									<td  class="sortabletd"><s:property value="exemptionsEmpNameItt"/></td>
									<td  class="sortabletd"><s:property value="exemptionsInvestNameItt"/></td>
									<td  class="sortabletd"><s:property value="exemptionsInvSectionNameItt"/></td>
									<td  class="sortabletd"><s:property value="exemptionsChapterNameItt"/></td>
									<td  class="sortabletd" align="right">
										<input type="hidden" name="exemptionsEmpIdItt" id="exemptionsEmpIdItt<%=exmpCount%>" value="<s:property value="exemptionsEmpIdItt"/>"/>
										<input type="hidden" name="exemptionsInvestmentIdItt" id="exemptionsInvestmentIdItt<%=exmpCount%>" value="<s:property value="exemptionsInvestmentIdItt"/>"/>
										<input type="hidden" name="exemptionsInvAmountItt" id="exemptionsInvAmountItt<%=exmpCount%>" value="<s:property value="exemptionsInvAmountItt"/>"/>
										<s:property value="exemptionsInvAmountItt"/>
									</td>
									<td  class="sortabletd">
										<s:if test='%{exemptionsProofAttachedItt =="Y"}'>
											<table>
												<s:iterator value="exemptionsAttachmentList">
												<tr>
													<td>
														<a href="#" style="color:blue;" title="View attachment" onclick="viewAttachedProof('<s:property value="exemptionsViewDocItt"/>');" ><s:property value="exemptionsViewDocItt"/></a>
													</td>
												</tr>
												</s:iterator>
											</table>
										</s:if>
										<s:else>
										&nbsp;
										</s:else>
									</td>
									<td  class="sortabletd" align="center">
										<s:if test='%{exemptionsVerifiedItt =="Y"}'>
											<input type="checkbox" name="exemptionsChk"	id="exemptionsChk<%=exmpCount%>" checked="checked"  onclick="return callChecked('exemptionsChk<%=exmpCount%>', '<s:property value="exemptionsInvAmountItt"/>','exemptionsVerifiedAmountItt<%=exmpCount%>', 'exemptionsVerifiedItt<%=exmpCount%>');"/>
										</s:if>
										<s:else>
											<input type="checkbox" name="exemptionsChk" id="exemptionsChk<%=exmpCount%>" onclick="return callChecked('exemptionsChk<%=exmpCount%>', '<s:property value="exemptionsInvAmountItt"/>', 'exemptionsVerifiedAmountItt<%=exmpCount%>', 'exemptionsVerifiedItt<%=exmpCount%>');"/>
										</s:else>
										<input type="hidden" name="exemptionsVerifiedItt" id="exemptionsVerifiedItt<%=exmpCount%>" value="<s:property value="exemptionsVerifiedItt" />"/>
									</td>
									<td  class="sortabletd" align="right">
										<input	type="text" style="text-align: right" name="exemptionsVerifiedAmountItt" value="<s:property value="exemptionsVerifiedAmountItt" />"
										id="exemptionsVerifiedAmountItt<%=exmpCount%>" maxlength="15" size="8" onkeypress="return numbersWithDot();"/>
									</td>
								</tr>
							</s:iterator>
							<input type="hidden" name="exemptLengthVar" id="exemptLengthVar" value="<%=exmpCount%>"/>
						</s:if>
						<s:if test="otherListViewFlag">
							<tr style="background:#E2E6EF;">
								<td colspan="4">
									<strong>Other income</strong>
								</td>
								<s:if test="paginationFlag">
									<td colspan="5" width="100%" align="right" ><b>Page:</b>
										<%
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										%> 
										<a href="#" onclick="callConfirmPage('1', 'F', '<%=totalPage%>', 'pageNoField');" >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" /></a>&nbsp; 
										<a href="#" onclick="callConfirmPage('P', 'P', '<%=totalPage%>', 'pageNoField');">
										<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" /></a>
										<input type="text"	name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
											maxlength="10" onkeypress="callPageText(event, '<%=totalPage%>', 'InvestmentVerification_submit.action');return numbersOnly();" />
										of <%=totalPage%> 
										<a href="#" onclick="callConfirmPage('N', 'N', '<%=totalPage%>', 'pageNoField');">
										<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; 
										<a href="#" onclick="callConfirmPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'pageNoField');">
										<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /></a>
									</td>
								</s:if><s:else>
									<td colspan="5" width="100%" align="right" >
										&nbsp;
									</td>
								</s:else>
							</tr>
							<tr>
								<td width="5%" align="center" class="formth">
									<label class="set" id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LempName" name="LempName" ondblclick="callShowDiv(this);"><%=label.get("LempName")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LinvName" name="LinvName" ondblclick="callShowDiv(this);"><%=label.get("LinvName")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LSection" name="LSection" ondblclick="callShowDiv(this);"><%=label.get("LSection")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LChapter" name="LChapter" ondblclick="callShowDiv(this);"><%=label.get("LChapter")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LInvAmt" name="LInvAmt" ondblclick="callShowDiv(this);"><%=label.get("LInvAmt")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LviewDoc" name="LviewDoc" ondblclick="callShowDiv(this);"><%=label.get("LviewDoc")%></label>
								</td>
								<td width="8%" align="center" class="formth">
									<label class="set" id="LverifiedLable1" name="LverifiedLable" ondblclick="callShowDiv(this);"><%=label.get("LverifiedLable")%></label></br>
									<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" onclick="checkAllRecords(this, 'otherChk', 'otherInvAmountItt', 'otherVerifiedAmountItt', 'otherLengthVar', 'otherVerifiedItt');" />
								</td>
								<td width="5%" align="center" class="formth">
									<label class="set" id="LverifiedAmt" name="LverifiedAmt" ondblclick="callShowDiv(this);"><%=label.get("LverifiedAmt")%></label>
								</td>
							</tr>
							
							<s:iterator value="otherInvestmentList">
								<tr>
									<td  class="sortabletd" align="center"><%=++otherCount%></td>
									<td  class="sortabletd"><s:property value="otherEmpNameItt"/></td>
									<td  class="sortabletd"><s:property value="otherInvestNameItt"/></td>
									<td  class="sortabletd"><s:property value="otherInvSectionNameItt"/></td>
									<td  class="sortabletd"><s:property value="otherChapterNameItt"/></td>
									<td  class="sortabletd" align="right">
										<input type="hidden" name="otherEmpIdItt" id="otherEmpIdItt<%=otherCount%>" value="<s:property value="otherEmpIdItt"/>"/>
										<input type="hidden" name="otherInvestmentIdItt" id="otherInvestmentIdItt<%=otherCount%>" value="<s:property value="otherInvestmentIdItt"/>"/>
										<input type="hidden" name="otherInvAmountItt" id="otherInvAmountItt<%=otherCount%>" value="<s:property value="otherInvAmountItt"/>"/>
										<s:property value="otherInvAmountItt"/>
									</td>
									<td  class="sortabletd">
										<s:if test='%{otherProofAttachedItt =="Y"}'>
											<table>
												<s:iterator value="otherAttachmentList">
												<tr>
													<td>
														<a href="#" style="color:blue;" title="View attachment" onclick="viewAttachedProof('<s:property value="otherViewDocItt"/>');" ><s:property value="otherViewDocItt"/></a>
													</td>
												</tr>
												</s:iterator>
											</table>
										</s:if>
										<s:else>
										&nbsp;
										</s:else>
									</td>
									<td  class="sortabletd" align="center">
										<s:if test='%{otherVerifiedItt =="Y"}'>
											<input type="checkbox" name="otherChk" id="otherChk<%=otherCount%>" checked="checked" onclick="return callChecked('otherChk<%=otherCount%>', '<s:property value="otherInvAmountItt"/>','otherVerifiedAmountItt<%=otherCount%>', 'otherVerifiedItt<%=otherCount%>');"/>
										</s:if>
										<s:else>
											<input type="checkbox" name="otherChk" id="otherChk<%=otherCount%>" onclick="return callChecked('otherChk<%=otherCount%>', '<s:property value="otherInvAmountItt"/>','otherVerifiedAmountItt<%=otherCount%>', 'otherVerifiedItt<%=otherCount%>');"/>
										</s:else>
										<input type="hidden" name="otherVerifiedItt" id="otherVerifiedItt<%=otherCount%>" value="<s:property value="otherVerifiedItt" />"/>
									</td>
									<td  class="sortabletd" align="right">
										<input	type="text" style="text-align: right" name="otherVerifiedAmountItt" value="<s:property value="otherVerifiedAmountItt" />"
										id="otherVerifiedAmountItt<%=otherCount%>" maxlength="15" size="8" onkeypress="return numbersWithDot();"/>
									</td>
								</tr>
							</s:iterator>
							<input type="hidden" name="otherLengthVar" id="otherLengthVar" value="<%=otherCount%>"/>
						</s:if>
						<s:if test="deductionsVIAListViewFlag">
							<tr style="background:#E2E6EF;">
								<td colspan="4">
									<strong>Deductions under Chapter VI-A</strong>
								</td>
								<s:if test="paginationFlag">
									<td colspan="5" width="100%" align="right" ><b>Page:</b>
										<%
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										%> 
										<a href="#" onclick="callConfirmPage('1', 'F', '<%=totalPage%>', 'pageNoField');" >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" /></a>&nbsp; 
										<a href="#" onclick="callConfirmPage('P', 'P', '<%=totalPage%>', 'pageNoField');">
										<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" /></a>
										<input type="text"	name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
											maxlength="10" onkeypress="callPageText(event, '<%=totalPage%>', 'InvestmentVerification_submit.action');return numbersOnly();" />
										of <%=totalPage%> 
										<a href="#" onclick="callConfirmPage('N', 'N', '<%=totalPage%>', 'pageNoField');">
										<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; 
										<a href="#" onclick="callConfirmPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'pageNoField');">
										<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /></a>
									</td>
								</s:if><s:else>
									<td colspan="5" width="100%" align="right" >
										&nbsp;
									</td>
								</s:else>
							</tr>
							<tr>
								<td width="5%" align="center" class="formth">
									<label class="set" id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LempName" name="LempName" ondblclick="callShowDiv(this);"><%=label.get("LempName")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LinvName" name="LinvName" ondblclick="callShowDiv(this);"><%=label.get("LinvName")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LSection" name="LSection" ondblclick="callShowDiv(this);"><%=label.get("LSection")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LChapter" name="LChapter" ondblclick="callShowDiv(this);"><%=label.get("LChapter")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LInvAmt" name="LInvAmt" ondblclick="callShowDiv(this);"><%=label.get("LInvAmt")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LviewDoc" name="LviewDoc" ondblclick="callShowDiv(this);"><%=label.get("LviewDoc")%></label>
								</td>
								<td width="8%" align="center" class="formth">
									<label class="set" id="LverifiedLable1" name="LverifiedLable" ondblclick="callShowDiv(this);"><%=label.get("LverifiedLable")%></label></br>
									<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" onclick="checkAllRecords(this, 'deductionsVIAChk', 'deductionsVIAInvAmountItt', 'deductionsVIAVerifiedAmountItt', 'deductionsVIALengthVar', 'deductionsVIAVerifiedItt');" />
								</td>
								<td width="5%" align="center" class="formth">
									<label class="set" id="LverifiedAmt" name="LverifiedAmt" ondblclick="callShowDiv(this);"><%=label.get("LverifiedAmt")%></label>
								</td>
							</tr>
							
							<s:iterator value="deductionsVIAInvestmentList">
								<tr>
									<td  class="sortabletd" align="center"><%=++deductionsVIACount%></td>
									<td  class="sortabletd"><s:property value="deductionsVIAEmpNameItt"/></td>
									<td  class="sortabletd"><s:property value="deductionsVIAInvestNameItt"/></td>
									<td  class="sortabletd"><s:property value="deductionsVIAInvSectionNameItt"/></td>
									<td  class="sortabletd"><s:property value="deductionsVIAChapterNameItt"/></td>
									<td  class="sortabletd" align="right">
										<input type="hidden" name="deductionsVIAEmpIdItt" id="deductionsVIAEmpIdItt<%=deductionsVIACount%>" value="<s:property value="deductionsVIAEmpIdItt"/>"/>
										<input type="hidden" name="deductionsVIAInvestmentIdItt" id="deductionsVIAInvestmentIdItt<%=deductionsVIACount%>" value="<s:property value="deductionsVIAInvestmentIdItt"/>"/>
										<input type="hidden" name="deductionsVIAInvAmountItt" id="deductionsVIAInvAmountItt<%=deductionsVIACount%>" value="<s:property value="deductionsVIAInvAmountItt"/>"/>
										<s:property value="deductionsVIAInvAmountItt"/>
									</td>
									<td  class="sortabletd">
										<s:if test='%{deductionsVIAProofAttachedItt =="Y"}'>
											<table>
												<s:iterator value="deductionsVIAAttachmentList">
												<tr>
													<td>
														<a href="#" style="color:blue;" title="View attachment" onclick="viewAttachedProof('<s:property value="deductionsVIAViewDocItt"/>');" ><s:property value="deductionsVIAViewDocItt"/></a>
													</td>
												</tr>
												</s:iterator>
											</table>
										</s:if>
										<s:else>
										&nbsp;
										</s:else>
									</td>
									<td  class="sortabletd" align="center">
										<s:if test='%{deductionsVIAVerifiedItt =="Y"}'>
											<input type="checkbox" name="deductionsVIAChk" id="deductionsVIAChk<%=deductionsVIACount%>" checked="checked" onclick="return callChecked('deductionsVIAChk<%=deductionsVIACount%>', '<s:property value="deductionsVIAInvAmountItt"/>','deductionsVIAVerifiedAmountItt<%=deductionsVIACount%>', 'deductionsVIAVerifiedItt<%=deductionsVIACount%>');"/>
										</s:if>
										<s:else>
											<input type="checkbox" name="deductionsVIAChk" id="deductionsVIAChk<%=deductionsVIACount%>" onclick="return callChecked('deductionsVIAChk<%=deductionsVIACount%>', '<s:property value="deductionsVIAInvAmountItt"/>','deductionsVIAVerifiedAmountItt<%=deductionsVIACount%>', 'deductionsVIAVerifiedItt<%=deductionsVIACount%>');"/>
										</s:else>
										<input type="hidden" name="deductionsVIAVerifiedItt" id="deductionsVIAVerifiedItt<%=deductionsVIACount%>" value="<s:property value="deductionsVIAVerifiedItt" />"/>
									</td>
									<td  class="sortabletd" align="right">
										<input	type="text" style="text-align: right" name="deductionsVIAVerifiedAmountItt" value="<s:property value="deductionsVIAVerifiedAmountItt"/>"
										id="deductionsVIAVerifiedAmountItt<%=deductionsVIACount%>" maxlength="15" size="8" onkeypress="return numbersWithDot();"/>
									</td>
								</tr>
							</s:iterator>
							<input type="hidden" name="deductionsVIALengthVar" id="deductionsVIALengthVar" value="<%=deductionsVIACount%>"/>
						</s:if>
						<s:if test="deductionsVIListViewFlag">
							<tr style="background:#E2E6EF;">
								<td colspan="4" >
									<strong>Deductions under Chapter VI (sec 80C)</strong>
								</td>
								<s:if test="paginationFlag">
									<td colspan="5" width="100%" align="right" ><b>Page:</b>
										<%
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										%> 
										<a href="#" onclick="callConfirmPage('1', 'F', '<%=totalPage%>', 'pageNoField');" >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" /></a>&nbsp; 
										<a href="#" onclick="callConfirmPage('P', 'P', '<%=totalPage%>', 'pageNoField');">
										<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" /></a>
										<input type="text"	name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
											maxlength="10" onkeypress="callPageText(event, '<%=totalPage%>', 'InvestmentVerification_submit.action');return numbersOnly();" />
										of <%=totalPage%> 
										<a href="#" onclick="callConfirmPage('N', 'N', '<%=totalPage%>', 'pageNoField');">
										<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; 
										<a href="#" onclick="callConfirmPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'pageNoField');">
										<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /></a>
									</td>
								</s:if><s:else>
									<td colspan="5" width="100%" align="right" >
										&nbsp;
									</td>
								</s:else>
							</tr>
							<tr>
								<td width="5%" align="center" class="formth">
									<label class="set" id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LempName" name="LempName" ondblclick="callShowDiv(this);"><%=label.get("LempName")%></label>
								</td>
								<td width="20%" align="center" class="formth">
									<label class="set" id="LinvName" name="LinvName" ondblclick="callShowDiv(this);"><%=label.get("LinvName")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LSection" name="LSection" ondblclick="callShowDiv(this);"><%=label.get("LSection")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LChapter" name="LChapter" ondblclick="callShowDiv(this);"><%=label.get("LChapter")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LInvAmt" name="LInvAmt" ondblclick="callShowDiv(this);"><%=label.get("LInvAmt")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LviewDoc" name="LviewDoc" ondblclick="callShowDiv(this);"><%=label.get("LviewDoc")%></label>
								</td>
								<td width="8%" align="center" class="formth">
									<label class="set" id="LverifiedLable1" name="LverifiedLable" ondblclick="callShowDiv(this);"><%=label.get("LverifiedLable")%></label></br>
									<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" onclick="checkAllRecords(this, 'deductionsVIChk', 'deductionsVIInvAmountItt', 'deductionsVIVerifiedAmountItt', 'deductionsVILengthVar', 'deductionsVIVerifiedItt');" />
								</td>
								<td width="5%" align="center" class="formth">
									<label class="set" id="LverifiedAmt" name="LverifiedAmt" ondblclick="callShowDiv(this);"><%=label.get("LverifiedAmt")%></label>
								</td>
							</tr>
							<s:iterator value="deductionsVIInvestmentList">
								<tr>
									<td  class="sortabletd" align="center"><%=++deductionsVICount%></td>
									<td  class="sortabletd"><s:property value="deductionsVIEmpNameItt"/></td>
									<td  class="sortabletd"><s:property value="deductionsVIInvestNameItt"/></td>
									<td  class="sortabletd"><s:property value="deductionsVIInvSectionNameItt"/></td>
									<td  class="sortabletd"><s:property value="deductionsVIChapterNameItt"/></td>
									<td  class="sortabletd" align="right">
										<input type="hidden" name="deductionsVIEmpIdItt" id="deductionsVIEmpIdItt<%=deductionsVICount%>" value="<s:property value="deductionsVIEmpIdItt"/>"/>
										<input type="hidden" name="deductionsVIInvestmentIdItt" id="deductionsVIInvestmentIdItt<%=deductionsVICount%>" value="<s:property value="deductionsVIInvestmentIdItt"/>"/>
										<input type="hidden" name="deductionsVIInvAmountItt" id="deductionsVIInvAmountItt<%=deductionsVICount%>" value="<s:property value="deductionsVIInvAmountItt"/>"/>
										<s:property value="deductionsVIInvAmountItt"/>
									</td>
									<td  class="sortabletd">
										<s:if test='%{deductionsVIProofAttachedItt =="Y"}'>
											<table>
												<s:iterator value="deductionsVIAttachmentList">
												<tr>
													<td>
														<a href="#" style="color:blue;" title="View attachment" onclick="viewAttachedProof('<s:property value="deductionsVIViewDocItt"/>');" ><s:property value="deductionsVIViewDocItt"/></a>
													</td>
												</tr>
												</s:iterator>
											</table>
										</s:if>
										<s:else>
										&nbsp;
										</s:else>
									</td>
									<td  class="sortabletd" align="center">
										<s:if test='%{deductionsVIVerifiedItt =="Y"}'>
											<input type="checkbox" name="deductionsVIChk" id="deductionsVIChk<%=deductionsVICount%>" checked="checked" onclick="return callChecked('deductionsVIChk<%=deductionsVICount%>', '<s:property value="deductionsVIInvAmountItt"/>','deductionsVIVerifiedAmountItt<%=deductionsVICount%>', 'deductionsVIVerifiedItt<%=deductionsVICount%>');"/>
										</s:if>
										<s:else>
											<input type="checkbox" name="deductionsVIChk" id="deductionsVIChk<%=deductionsVICount%>" onclick="return callChecked('deductionsVIChk<%=deductionsVICount%>', '<s:property value="deductionsVIInvAmountItt"/>','deductionsVIVerifiedAmountItt<%=deductionsVICount%>', 'deductionsVIVerifiedItt<%=deductionsVICount%>');"/>
										</s:else>
										<input type="hidden" name="deductionsVIVerifiedItt" id="deductionsVIVerifiedItt<%=deductionsVICount%>" value="<s:property value="deductionsVIVerifiedItt" />"/>
									</td>
									<td  class="sortabletd" align="right">
										<input	type="text" style="text-align: right" name="deductionsVIVerifiedAmountItt" value="<s:property value="deductionsVIVerifiedAmountItt"/>"
										id="deductionsVIVerifiedAmountItt<%=deductionsVICount%>" maxlength="15" size="8" onkeypress="return numbersWithDot();"/>
									</td>
								</tr>
							</s:iterator>
							<input type="hidden" name="deductionsVILengthVar" id="deductionsVILengthVar" value="<%=deductionsVICount%>"/>
						</s:if>
					</table>
				</td>
			</tr>
			<!--------- EMPLOYEE DONATION LIST FOR VERIFICATIONS ------->
			<s:if test="donationListViewFlag">
				<tr>
					<td colspan="3" align="center">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td colspan="8" align="left" style="background:#E2E6EF;">
									<strong>Employee Donation List for Verifications</strong>	
								</td>
							</tr>
							<tr>
								<td width="5%" align="center" class="formth">
									<label class="set" id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
								</td>
								<td width="15%" align="center" class="formth">
									<label class="set" id="LempName" name="LempName" ondblclick="callShowDiv(this);"><%=label.get("LempName")%></label>
								</td>
								<td width="15%" align="center" class="formth">
									<label class="set" id="donatnName" name="donatnName" ondblclick="callShowDiv(this);"><%=label.get("donatnName")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="exemptionPer" name="exemptionPer" ondblclick="callShowDiv(this);"><%=label.get("exemptionPer")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LInvAmt" name="LInvAmt" ondblclick="callShowDiv(this);"><%=label.get("LInvAmt")%></label>
								</td>
								<td width="10%" align="center" class="formth">
									<label class="set" id="LviewDoc" name="LviewDoc" ondblclick="callShowDiv(this);"><%=label.get("LviewDoc")%></label>
								</td>
								<td width="8%" align="center" class="formth">
									<label class="set" id="LverifiedLable1" name="LverifiedLable" ondblclick="callShowDiv(this);"><%=label.get("LverifiedLable")%></label></br>
									<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" onclick="checkAllRecords(this, 'donationChk', 'donationInvAmountItt', 'donationVerifiedAmountItt', 'donationLengthVar', 'donationVerifiedItt');" />
								</td>
								<td width="5%" align="center" class="formth">
									<label class="set" id="LverifiedAmt" name="LverifiedAmt" ondblclick="callShowDiv(this);"><%=label.get("LverifiedAmt")%></label>
								</td>
							</tr>
							
							<s:iterator value="donationInvestmentList">	
								<tr>
									<td  class="sortabletd"><%=++donationCount%></td>
									<td  class="sortabletd" align="left"><s:property value="donationEmpNameItt"/></td>
									<td  class="sortabletd" align="left"><s:property value="donationNameItt"/></td>
									<td  class="sortabletd" align="left"><s:property value="donationExemptionPercentageItt"/></td>
									<td  class="sortabletd" align="right">
										<input type="hidden" name="donationEmpIdItt" id="donationEmpIdItt<%=donationCount%>" value="<s:property value="donationEmpIdItt"/>"/>
										<input type="hidden" name="donationIdItt" id="donationIdItt<%=donationCount%>" value="<s:property value="donationIdItt"/>"/>
										<input type="hidden" name="donationInvAmountItt" id="donationInvAmountItt<%=donationCount%>" value="<s:property value="donationInvAmountItt"/>"/>
										<s:property value="donationInvAmountItt"/>
									</td>
									<td  class="sortabletd">
										<s:if test='%{donationProofAttachedItt =="Y"}'>
											<table>
												<s:iterator value="donationAttachmentList">
												<tr>
													<td>
														<a href="#" style="color:blue;" title="View attachment" onclick="viewAttachedProof('<s:property value="donationViewDocItt"/>');" ><s:property value="donationViewDocItt"/></a>
													</td>
												</tr>
												</s:iterator>
											</table>
										</s:if>
										<s:else>
										&nbsp;
										</s:else>
									</td>
									<td  class="sortabletd" align="center">
										<s:if test='%{donationVerifiedItt =="Y"}'>
											<input type="checkbox" name="donationChk" id="donationChk<%=donationCount%>" checked="checked" onclick="return callChecked('donationChk<%=donationCount%>', '<s:property value="donationInvAmountItt"/>','donationVerifiedAmountItt<%=donationCount%>', 'donationVerifiedItt<%=donationCount%>');"/>
										</s:if>
										<s:else>
											<input type="checkbox" name="donationChk" id="donationChk<%=donationCount%>" onclick="return callChecked('donationChk<%=donationCount%>', '<s:property value="donationInvAmountItt"/>','donationVerifiedAmountItt<%=donationCount%>', 'donationVerifiedItt<%=donationCount%>');"/>
										</s:else>
										<input type="hidden" name="donationVerifiedItt" id="donationVerifiedItt<%=donationCount%>" value="<s:property value="donationVerifiedItt" />"/>
									</td>
									<td  class="sortabletd" align="right">
										<input	type="text" style="text-align: right" name="donationVerifiedAmountItt" value="<s:property value="donationVerifiedAmountItt"/>"
										id="donationVerifiedAmountItt<%=donationCount%>" maxlength="15" size="8" onkeypress="return numbersWithDot();"/>
									</td>
								</tr>
							</s:iterator>
							<input type="hidden" name="donationLengthVar" id="donationLengthVar" value="<%=donationCount%>"/>
						</table>
					</td>
				</tr>
			</s:if>
			<% if(exmpCount == 0 && otherCount == 0 && deductionsVIACount == 0 && deductionsVICount  == 0 && donationCount == 0){ %>
			<tr>
				<td colspan="3" width="100%">
				<table width="100%" border="0"  cellpadding="1"	cellspacing="1" class="formbg">
					<tr>
						<td colspan="3" align="center">
							<font color="red">Investment details not available</font>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<% }%>
		</s:if>
		<!--------- MONTHLY INVESTMENTS DISPLAYED WHEN MONTHLY ACCOMMODATIONS SELECTED ------->
		
		<s:if test="monthlyFlag">
		<tr>
			<td colspan="3">
			<div style="width:798px;height:380px;overflow:auto;" align="center">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" >
				<tr>
					<td colspan="19" align="left" style="background:#E2E6EF;">
						<strong>Monthly Accommodation & Conveyance Declaration </strong>	
					</td>
				</tr>
				<tr>
					<!--<td align="center" class="formth">
						<label class="set" id="sno2" name="sno" ondblclick="callShowDiv(this);">
						<%=label.get("sno")%></label>
					</td>
					<td align="center" class="formth">
						<label class="set" id="LempName" name="LempName2" ondblclick="callShowDiv(this);">
						<%=label.get("LempName")%></label>
					</td>-->
					<td align="center" class="formth">
						<label class="set" id="imonth" name="imonth" ondblclick="callShowDiv(this);">
						<%=label.get("imonth")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="rent" name="rent" ondblclick="callShowDiv(this);">
						<%=label.get("rent")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="india" name="india" ondblclick="callShowDiv(this);">
						<%=label.get("india")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="metro" name="metro" ondblclick="callShowDiv(this);">
						<%=label.get("metro")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="ownedHouse" name="ownedHouse" ondblclick="callShowDiv(this);">
						<%=label.get("ownedHouse")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id=citypopulation name="citypopulation" ondblclick="callShowDiv(this);">
						<%=label.get("citypopulation")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="carUsed" name="carUsed" ondblclick="callShowDiv(this);">
						<%=label.get("carUsed")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="capacity" name="capacity" ondblclick="callShowDiv(this);">
						<%=label.get("capacity")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="driver" name="driver" ondblclick="callShowDiv(this);">
						<%=label.get("driver")%></label>
					</td>
					<td align="center" class="formth" colspan="2">
						<label class="set" id="rentByComp" name="rentByComp" ondblclick="callShowDiv(this);">
						<%=label.get("rentByComp")%></label>
					</td>
					<!--<td align="center" class="formth">
						<label class="set" id="proof" name="proof" ondblclick="callShowDiv(this);">
						<%=label.get("proof")%></label>
					</td>-->
				</tr>
				<tr>
					<!--<td align="center" class="formth">
						&nbsp;
					</td>
					<td align="center" class="formth">
						&nbsp;
					</td>-->
					<td align="center" class="formth">
						&nbsp;
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth">
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth" >
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
								onclick="selectAllRecords(this, 'monthlyInIndiaVerifiedItt', 'checkSeven', 'monthlyLengthVar');" />
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth" >
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
									onclick="selectAllRecords(this, 'monthlyIsMetroVerifiedItt', 'checkOne', 'monthlyLengthVar');" />
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth">
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
									onclick="selectAllRecords(this, 'monthlyCompanyOwnedHouseVerifiedItt', 'checkTwo', 'monthlyLengthVar');" />
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth">
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
									onclick="selectAllRecords(this, 'monthlyCityPopulationVerifiedItt', 'checkEight', 'monthlyLengthVar');" />
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth">
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
									onclick="selectAllRecords(this, 'monthlyCarUsedVerifiedItt', 'checkThree', 'monthlyLengthVar');" />
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth">
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
									onclick="selectAllRecords(this, 'monthlyCubicCapacityVerifiedItt', 'checkFour', 'monthlyLengthVar');" />
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth">
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
								onclick="selectAllRecords(this, 'monthlyDriverUsedVerifiedItt', 'checkFive', 'monthlyLengthVar');" />
					</td>
					<td align="center" class="formth">
						Declared
					</td>
					<td align="center" class="formth">
						<label class="set" id="actualLabel" name="actualLabel" ondblclick="callShowDiv(this);"><%=label.get("actualLabel")%></label>
						&nbsp;<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all" 
								onclick="selectAllRecords(this, 'monthlyHouseRentPaidByCompanyVerifiedItt', 'checkSix', 'monthlyLengthVar');" />
					</td>
					<!--<td align="center" class="formth">
						&nbsp;
					</td>-->
				</tr>
				<s:iterator value="monthlyInvestmentList">
					<tr><%++monthlySr;%>
					<!--	
						<td class="sortableTD"><%=monthlySr%>
							
						</td>
						<td class="sortabletd" nowrap="nowrap">
							<s:property value="monthlyEmployeeNameItt" />
						</td>-->
						<td  class="sortabletd" nowrap="nowrap">
							<s:hidden name="monthlyRentCodeItt" id='<%="monthlyRentCodeItt" +monthlySr%>'/>
							<s:hidden name="monthlyEmployeeIdItt"/>
							<s:hidden name="monthlyAttachmentFileItt"/>
							<s:hidden name="investmentMonthItt"/>
							<s:property value="investmentMonthNameItt" />
						</td>
						<td  class="sortabletd" align="right">
							<s:property value="monthlyRentPaidDeclaredItt" />
						</td>
						<td  class="sortabletd">
							<s:textfield name="monthlyRentPaidVerifiedItt" size="7" cssStyle="text-align: right"/>
						</td>
						<td  class="sortabletd" align="center">
								<s:checkbox disabled="true" name="monthlyInIndiaDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
								<s:if test='%{monthlyInIndiaVerifiedItt =="Y"}'>
									<input type="checkbox" class="checkbox" name="checkSeven" id="checkSeven<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkSeven<%=monthlySr%>', 'monthlyInIndiaVerifiedItt<%=monthlySr%>');"/>
								</s:if>
								<s:else>
									<input type="checkbox" class="checkbox" name="checkSeven" id="checkSeven<%=monthlySr%>" onclick="callMonthlyCheckedList('checkSeven<%=monthlySr%>', 'monthlyInIndiaVerifiedItt<%=monthlySr%>');"/>
								</s:else>
								<input type="hidden" name="monthlyInIndiaVerifiedItt" value='<s:property value="monthlyInIndiaVerifiedItt"/>'	id="monthlyInIndiaVerifiedItt<%=monthlySr%>" />
						</td>
						<td  class="sortabletd" align="center">
							<s:checkbox disabled="true" name="monthlyIsMetroDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
							<s:if test='%{monthlyIsMetroVerifiedItt =="Y"}'>
								<input type="checkbox" class="checkbox" name="checkOne" id="checkOne<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkOne<%=monthlySr%>', 'monthlyIsMetroVerifiedItt<%=monthlySr%>');"/>
							</s:if>	
							<s:else>
								<input type="checkbox" class="checkbox" name="checkOne" id="checkOne<%=monthlySr%>" onclick="callMonthlyCheckedList('checkOne<%=monthlySr%>', 'monthlyIsMetroVerifiedItt<%=monthlySr%>');"/>
							</s:else>
							<input type="hidden" name="monthlyIsMetroVerifiedItt"	value='<s:property value="monthlyIsMetroVerifiedItt"/>' id="monthlyIsMetroVerifiedItt<%=monthlySr%>" />
						</td>
						<td  class="sortabletd" align="center">
							<s:checkbox disabled="true" name="monthlyCompanyOwnedHouseDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
							<s:if test='%{monthlyCompanyOwnedHouseVerifiedItt =="Y"}'>
								<input type="checkbox" class="checkbox" name="checkTwo" id="checkTwo<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkTwo<%=monthlySr%>', 'monthlyCompanyOwnedHouseVerifiedItt<%=monthlySr%>');"/>
							</s:if>	
							<s:else>
								<input type="checkbox" class="checkbox" name="checkTwo" id="checkTwo<%=monthlySr%>" onclick="callMonthlyCheckedList('checkTwo<%=monthlySr%>', 'monthlyCompanyOwnedHouseVerifiedItt<%=monthlySr%>');"/>
							</s:else>	
							<input type="hidden" name="monthlyCompanyOwnedHouseVerifiedItt" value='<s:property value="monthlyCompanyOwnedHouseVerifiedItt"/>'	id="monthlyCompanyOwnedHouseVerifiedItt<%=monthlySr%>" />
						</td>
						<td  class="sortabletd" align="center">
							<s:checkbox disabled="true" name="monthlyCityPopulationDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
							<s:if test='%{monthlyCityPopulationVerifiedItt =="Y"}'>
								<input type="checkbox" class="checkbox" name="checkEight" id="checkEight<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkEight<%=monthlySr%>', 'monthlyCityPopulationVerifiedItt<%=monthlySr%>');"/>
							</s:if>	
							<s:else>
								<input type="checkbox" class="checkbox" name="checkEight" id="checkEight<%=monthlySr%>" onclick="callMonthlyCheckedList('checkEight<%=monthlySr%>', 'monthlyCityPopulationVerifiedItt<%=monthlySr%>');"/>
							</s:else>	
							<input type="hidden" name="monthlyCityPopulationVerifiedItt" value='<s:property value="monthlyCityPopulationVerifiedItt"/>'	id="monthlyCityPopulationVerifiedItt<%=monthlySr%>" />
						</td>
						<td  class="sortabletd" align="center">
							<s:checkbox disabled="true" name="monthlyCarUsedDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
							<s:if test='%{monthlyCarUsedVerifiedItt =="Y"}'>
								<input type="checkbox" class="checkbox" name="checkThree" id="checkThree<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkThree<%=monthlySr%>', 'monthlyCarUsedVerifiedItt<%=monthlySr%>');"/>
							</s:if>
							<s:else>
								<input type="checkbox" class="checkbox" name="checkThree" id="checkThree<%=monthlySr%>" onclick="callMonthlyCheckedList('checkThree<%=monthlySr%>', 'monthlyCarUsedVerifiedItt<%=monthlySr%>');"/>
							</s:else>
							<input type="hidden" name="monthlyCarUsedVerifiedItt"	value='<s:property value="monthlyCarUsedVerifiedItt"/>' id="monthlyCarUsedVerifiedItt<%=monthlySr%>" />
						</td>
						<td  class="sortabletd" align="center">
							<s:checkbox disabled="true" name="monthlyCubicCapacityDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
							<s:if test='%{monthlyCubicCapacityVerifiedItt =="Y"}'>
								<input type="checkbox" class="checkbox" name="checkFour" id="checkFour<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkFour<%=monthlySr%>', 'monthlyCubicCapacityVerifiedItt<%=monthlySr%>');"/>
							</s:if>
							<s:else>
								<input type="checkbox" class="checkbox" name="checkFour" id="checkFour<%=monthlySr%>" onclick="callMonthlyCheckedList('checkFour<%=monthlySr%>', 'monthlyCubicCapacityVerifiedItt<%=monthlySr%>');"/>
							</s:else>
							<input type="hidden" name="monthlyCubicCapacityVerifiedItt" value='<s:property value="monthlyCubicCapacityVerifiedItt"/>'	id="monthlyCubicCapacityVerifiedItt<%=monthlySr%>" />
						</td>
						<td  class="sortabletd" align="center">
							<s:checkbox disabled="true" name="monthlyDriverUsedDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
							<s:if test='%{monthlyDriverUsedVerifiedItt =="Y"}'>
								<input type="checkbox" class="checkbox" name="checkFive" id="checkFive<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkFive<%=monthlySr%>', 'monthlyDriverUsedVerifiedItt<%=monthlySr%>');"/>
							</s:if>
							<s:else>
								<input type="checkbox" class="checkbox" name="checkFive" id="checkFive<%=monthlySr%>" onclick="callMonthlyCheckedList('checkFive<%=monthlySr%>', 'monthlyDriverUsedVerifiedItt<%=monthlySr%>');"/>
							</s:else>
							<input type="hidden" name="monthlyDriverUsedVerifiedItt"	value='<s:property value="monthlyDriverUsedVerifiedItt"/>' id="monthlyDriverUsedVerifiedItt<%=monthlySr%>" />
						</td>
						<td  class="sortabletd" align="center">
							<s:checkbox disabled="true" name="monthlyHouseRentPaidByCompanyDeclaredItt" />
						</td>
						<td  class="sortabletd" align="center">
							<s:if test='%{monthlyHouseRentPaidByCompanyVerifiedItt =="Y"}'>
								<input type="checkbox" class="checkbox" name="checkSix" id="checkSix<%=monthlySr%>" checked="checked" onclick="callMonthlyCheckedList('checkSix<%=monthlySr%>', 'monthlyHouseRentPaidByCompanyVerifiedItt<%=monthlySr%>');"/>
							</s:if>
								<s:else>
									<input type="checkbox" class="checkbox" name="checkSix" id="checkSix<%=monthlySr%>" onclick="callMonthlyCheckedList('checkSix<%=monthlySr%>', 'monthlyHouseRentPaidByCompanyVerifiedItt<%=monthlySr%>');"/>
								</s:else>
								<input type="hidden" name="monthlyHouseRentPaidByCompanyVerifiedItt" value='<s:property value="monthlyHouseRentPaidByCompanyVerifiedItt"/>'	id="monthlyHouseRentPaidByCompanyVerifiedItt<%=monthlySr%>" />
							</td>
						<!--<td  class="sortabletd" align="center">
							<table>
								<s:iterator value="monthlyAttachmentList">
									<tr>
										<td>
											<a href="#" style="color:blue;" title="View attachment" onclick="viewAttachedProof('<s:property value="monthlyAttachmentFileItt"/>');" ><s:property value="monthlyAttachmentFileItt"/></a>
										</td>
									</tr>
								</s:iterator>
							</table>
						</td>-->
					</tr>
					</s:iterator>
					<input type="hidden" name="monthlyLengthVar" id="monthlyLengthVar" value="<%=monthlySr%>"/>
				</table>
				</div>
			</td>
		</tr>
		</s:if>
		<s:if test="proofAttachedFlag">
			<tr>
				<td colspan="3">
					<table width="100%" border="0"  cellpadding="1"	cellspacing="1" class="formbg">
						<tr>
							<td>
								<strong>View Proofs</strong>
							</td>
						</tr>
						<% 	int proofCount = 0; %>
						<s:iterator value="monthlyAttachmentList" >
							<tr>
								<td width="100%" nowrap="nowrap">
									<%++proofCount;%><%=proofCount%>&nbsp;)&nbsp;
									<a href="#" style="color:blue;" title="View attachment" onclick="viewAttachedProof('<s:property value="monthlyAttachmentFileItt"/>');" ><s:property value="monthlyAttachmentFileItt"/></a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
		</s:if>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
					<td>
						<s:if test="settlementFlag">
							<s:if test='%{settlementApplnStatus == "F"}'>
							</s:if><s:else>
								<input type="button" cssClass="token" value="Save Verified Amount"	onclick="return saveVerificationList();" />
							</s:else>
							<input type="button" class="token" onclick="backToSettlement();" value="Back" />	
						</s:if>
						<s:else>
							<input type="button" cssClass="token" value="Save Verified Amount"	onclick="return saveVerificationList();" />
							<input type="button" class="token" onclick="investmentListFun();" value="Employee Investment List" />
							<input type="button" class="token" onclick="reportFun();" value="Report" />
							<input type="button" class="token" onclick="resetFun();" value="Reset" />
						</s:else>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
<s:hidden name="empListFlag" id="empListFlag"/>
<s:hidden name="filterflag" id="filterflag"/>
</s:form>
<script>

callOnload();
function callOnload(){
	if(document.getElementById('paraFrm_settlementFlag').value == 'false'){
		if(document.getElementById('paraFrm_divId').value==""){
			document.getElementById('selectFilterBlock').style.display='none';
			document.getElementById('employeeBlock').style.display='';
		}else{
			document.getElementById('selectFilterBlock').style.display='';
			document.getElementById('employeeBlock').style.display='none';
		}
	}
	disableBlockDiv();
}
function add() {
	var from = document.getElementById('paraFrm_fromYear').value;
    
	if(from==""){
		document.getElementById('paraFrm_toYear').value="";
	}else{
		var x=eval(from) +1;
		document.getElementById('paraFrm_toYear').value=x;
	}
}

function investmentListFun(){
	if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="InvestmentVerification_submit.action";
			document.getElementById('paraFrm').submit();
	}
}

function validateFields(){
	var div = document.getElementById('paraFrm_divId').value;
	var emp = document.getElementById('paraFrm_empName').value;
	if(document.getElementById('paraFrm_settlementFlag').value == 'true'){
		return true;
	}else{
		if(document.getElementById('selectFilterBlock').style.display==''){
			if(div == ""){
				alert("Please select division");
				return false;
			}
			if(document.getElementById('paraFrm_investmentName').value == ""){
				alert("Please select investment name");
				document.getElementById('paraFrm_investmentName').focus();
				return false;
			}
		}else{
			if(emp == ""){
				alert("Please select employee");
				return false;
			}
		}
	}
	return true;
}

function reportFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="InvestmentVerification_genReport.action";
	document.getElementById('paraFrm').submit();
}

function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="InvestmentVerification_reset.action";
	document.getElementById('paraFrm').submit();
}

function viewDetails(attachmentFile){
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action="InvestmentVerification_viewAttachmentReceipt.action?attachmentFile="+attachmentFile;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
}

function callChecked(checkBox, invAmount, verifiedAmtField, setCheckedVar){
	if(document.getElementById(checkBox).checked == true){	  
		document.getElementById(verifiedAmtField).value=invAmount;
		document.getElementById(setCheckedVar).value="Y";
  	} else {
  		document.getElementById(verifiedAmtField).value="0.00";
  		document.getElementById(setCheckedVar).value="N";
  	}
}

function checkAllRecords(object, checkBox, invAmount, verifiedAmtField, totalRecordsField, setCheckedVar) {
	var totalRecords = document.getElementById(totalRecordsField).value;
	var initialVal = 1;
	
	if(totalRecords > 19){
		initialVal = totalRecords - 19;
	}
	
	if(object.checked) {
		for(var i = initialVal; i <= totalRecords; i++) {
			document.getElementById(checkBox + i).checked = true;
			document.getElementById(verifiedAmtField + i).value=document.getElementById(invAmount + i).value;
			document.getElementById(setCheckedVar+i).value="Y";
		}
	} else {
		for(var i = initialVal; i <= totalRecords; i++) {
			document.getElementById(checkBox + i).checked = false;
			document.getElementById(verifiedAmtField + i).value="0.00";
			document.getElementById(setCheckedVar+i).value="N";
		}
	}
}

function callMonthlyCheckedList(checkBox, field){
	if(document.getElementById(checkBox).checked == true){	  
		document.getElementById(field).value="Y";
  	} else {
  		document.getElementById(field).value="N";
  	}
}

function selectAllRecords(object, field, checkBox, totalRecordsField) {
	var totalRecords = document.getElementById(totalRecordsField).value;
	
	if(object.checked) {
		for(var i = 1; i <= totalRecords; i++) {
			document.getElementById(checkBox + i).checked = true;
			document.getElementById(field + i).value = "Y";
		}
	} else {
		for(var i = 1; i <= totalRecords; i++) {
			document.getElementById(checkBox + i).checked = false;
			document.getElementById(field + i).value = "N";
		}
	}
}

function amountVerify(verifiedAmount, investmentAmount) {
	var amt=trim(document.getElementById(verifiedAmount).value);
	var mainAmount=eval(document.getElementById(investmentAmount).value);
	if(amt!=""){	
		if(isNaN(amt)) { 
		  document.getElementById(verifiedAmount).value="0";
		  document.getElementById(verifiedAmount).focus();
		  return false;
		 }	 
		 try{
		 	document.getElementById(verifiedAmount).value=formatNumber(eval(amt));
		 }catch(e){ }
		if(eval(amt)>mainAmount){
			alert(""+document.getElementById('LverifiedAmt').innerHTML.toLowerCase()+" should be less than or equal to the "+document.getElementById('LInvAmt').innerHTML.toLowerCase()+"");
   			document.getElementById(verifiedAmount).value="0";
   			return false;
  		}else{
  			return true;
  		}
	}
}

function saveVerificationList(){
	if(!validateFields()){
			return false;
	} else {
		document.getElementById('paraFrm').target = "main";
		document.getElementById('paraFrm').action="InvestmentVerification_saveVerifiedInvestments.action";
		document.getElementById('paraFrm').submit();
	}
}

function showRecord(flag, fileName){
	if(flag=='N'|| fileName==" "){
		alert("Investment proof document not uploaded.");
		return false;
	}else{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "InvestmentVerification_viewDoc.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
}	

function formatNumber(num){
	return num.toFixed(2).toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1').split('').reverse().join('').replace(/^[\,]/,'');
}

function backToSettlement(){
	document.getElementById('paraFrm_empCode').value = document.getElementById('paraFrm_empId').value;
	document.getElementById('paraFrm_status').value = document.getElementById('paraFrm_settlementApplnStatus').value;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="<%=request.getContextPath()%>/settlement/SettlmentDetails_previousFourth.action";
	document.getElementById('paraFrm').submit();
}

function expandFilter(){
	if(document.getElementById('selectFilterBlock').style.display=='none'){
		document.getElementById('selectFilterBlock').style.display='';
		document.getElementById('employeeBlock').style.display='none';
		clearFilterOnAdvancedSelect();
	}else{
		document.getElementById('selectFilterBlock').style.display='none';
		document.getElementById('employeeBlock').style.display='';
		clearFilterOnEmployeeSelect()
	}
}

function clearFilterOnAdvancedSelect(){
	document.getElementById('paraFrm_empId').value ="";
	document.getElementById('paraFrm_empToken').value ="";
	document.getElementById('paraFrm_empName').value ="";
	document.getElementById('paraFrm_exemptionsListViewFlag').value=false;
	document.getElementById('paraFrm_otherListViewFlag').value=false;
	document.getElementById('paraFrm_deductionsVIAListViewFlag').value=false;
	document.getElementById('paraFrm_deductionsVIListViewFlag').value=false;
	document.getElementById('paraFrm_donationListViewFlag').value=false;
	document.getElementById('paraFrm_monthlyFlag').value=false;
	document.getElementById('paraFrm_empSaveFlag').value=false;
	document.getElementById('paraFrm_proofAttachedFlag').value=false;
}

function clearFilterOnEmployeeSelect(){
	document.getElementById('paraFrm_divId').value ="";
	document.getElementById('paraFrm_divName').value ="";
	document.getElementById('paraFrm_branchId').value ="";
	document.getElementById('paraFrm_branchName').value ="";
	document.getElementById('paraFrm_deptId').value ="";
	document.getElementById('paraFrm_deptName').value ="";
	document.getElementById('paraFrm_paybillId').value ="";
	document.getElementById('paraFrm_paybillName').value ="";
	document.getElementById('paraFrm_investmentCode').value ="";
	document.getElementById('paraFrm_investmentName').value ="";
	document.getElementById('paraFrm_Estatus').value ="A";
	document.getElementById('paraFrm_exemptionsListViewFlag').value=false;
	document.getElementById('paraFrm_otherListViewFlag').value=false;
	document.getElementById('paraFrm_deductionsVIAListViewFlag').value=false;
	document.getElementById('paraFrm_deductionsVIListViewFlag').value=false;
	document.getElementById('paraFrm_donationListViewFlag').value=false;
	document.getElementById('paraFrm_monthlyFlag').value=false;
	document.getElementById('paraFrm_empSaveFlag').value=false;
	document.getElementById('paraFrm_proofAttachedFlag').value=false;
}

function viewAttachedProof(attachment){
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action='InvestmentVerification_viewAttachmentReceipt.action?attachmentFile='+attachment;
	document.getElementById('paraFrm').submit();
}

function nextEmployeeFun(){
	document.getElementById('paraFrm_navigateTo').value="N";
	displayConfirmDiv();
}

function previousEmployeeFun(){
	document.getElementById('paraFrm_navigateTo').value="P";
	displayConfirmDiv();
}

function displayConfirmDiv(){
	document.getElementById("confirmationDiv").style.visibility = 'visible';
	document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class="formbg" style="border:1px solid #999;">'
		 			+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 			+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 			+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 			+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
}

function proceedWithSave(){
	if(document.getElementById('selectFilterBlock').style.display==''){
		saveVerificationList();
	}else{
		document.getElementById('paraFrm_empSaveFlag').value=true;	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="InvestmentVerification_fetchNextOrPreviousEmployee.action";
		document.getElementById('paraFrm').submit();
	}
}

function proceedWithoutSave(){
	if(document.getElementById('selectFilterBlock').style.display==''){
		investmentListFun();
	}else{
		document.getElementById('paraFrm_empSaveFlag').value=false;	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="InvestmentVerification_fetchNextOrPreviousEmployee.action";
		document.getElementById('paraFrm').submit();
	}
}

function cancel(){
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
}
	  
function disableBlockDiv(){
	try{
		document.getElementById("progressBar").style.visibility = "hidden";
		document.getElementById("progressBar").style.display = "none";   
	}catch(e){}
}

function callConfirmPage(id, pageImg, totalPage, pageNoFld){
	var pageNo =document.getElementById(pageNoFld).value;	
	 	  
	if(pageNo==""){
		alert("Please Enter Page Number.");
		document.getElementById(pageNoFld).focus();
		return false;
	}
	if(Number(pageNo)<=0){
		alert("Page number should be greater than zero.");
		document.getElementById(pageNoFld).focus();
		return false;
	}
	if(Number(totalPage)<Number(pageNo)){
		alert("Page number should not be greater than "+totalPage+".");
		document.getElementById(pageNoFld).focus();
		return false;
	}
	if(pageImg=="F"){
		if(pageNo=="1"){
			alert("This is first page.");
			document.getElementById(pageNoFld).focus();
			return false;
		} 
	}  
	if(pageImg=="L"){
		if(eval(pageNo)==eval(totalPage)){
			alert("This is last page.");
			document.getElementById(pageNoFld).focus();
			return false;
		} 
	} 
	if(pageImg=="P"){
		if(pageNo=="1"){
			alert("There is no previous page.");
			document.getElementById(pageNoFld).focus();
			return false;
		}  
	}  
	if(pageImg=="N"){
		if(Number(pageNo)==Number(totalPage)){
			alert("There is no next page.");
			document.getElementById(pageNoFld).focus();
			return false;
		}  
	}  
	var p=document.getElementById(pageNoFld).value;
	        
	if(id=='P'){
		id=eval(p)-1;
	}
	if(id=='N'){
		id=eval(p)+1;
	} 
	document.getElementById('myPage').value=id;
	displayConfirmDiv();
	document.getElementById('confirmationDiv').style.display='';
}
</script>