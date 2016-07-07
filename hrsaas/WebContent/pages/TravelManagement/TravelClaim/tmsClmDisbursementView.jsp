<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
var disbursement = new Array();
</script>
<s:form action="TravelExpDisbrsmnt" validate="true" id="paraFrm"
	theme="simple" method="post">

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<!-- zero flag is for  note in claiam page ,donot remove it -->
		<s:hidden name="zeroflag" />
		<s:hidden name="gradId" />
		<s:hidden name="status" />
		<!-- for navigation -->
		<s:hidden name="trvlAppId" />
		<s:hidden name="trvlAppCode" />
		<s:hidden name="appId" />
		<s:hidden name="appCode" />
		<s:hidden name="tmsClmAppId" />
		<s:hidden name="tmsClmStatus" />

		<!-- for Travel Policy view purpose -->
		<s:hidden name="trvlAppFor" />
		<s:hidden name="empGrade" />
		<s:hidden name="trvlAppDate" />
		<s:hidden name="trvlStartDate" />
		<s:hidden name="trvlEndDate" />
		<!-- for Travel Policy view purpose -->
		<s:hidden name="appDate" />
		<s:hidden name="startDate" />
		<s:hidden name="endDate" />

		<!-- to do function -->
		<s:hidden name="navStatus" />
		<s:hidden name="statusSave" />

		<!-- For setting params for showing booking details -->

		<s:hidden name="bDtlAppId" />
		<s:hidden name="bDtlAppCode" />
		<s:hidden name="bDtlInitrId" />
		<s:hidden name="bDtlEmpId" />
		<s:hidden name="bDtlAppDate" />

		<s:hidden name="claimtrvlId" />
		<s:hidden name="clmTrvlType" />
		<s:hidden name="empCode" />

		<s:hidden name="disburseFlag" />
		<s:hidden name="source" id="source" />


		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1" width="4%"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>


					<td width="93%" class="txt" colspan="1"><strong
						class="text_head"> Claim Disbursement</strong></td>

					<td width="3%" valign="top" class="txt" colspan="1">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td width="100%" colspan="2">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">



				<tr>
					<td width="78%" align="left"><s:if test="disburseFlag"></s:if><s:else>
						<input type="button" value="Save" class="token"
							onclick="callSave();" />
						<input type="button" value="Reset" class="token"
							onclick="callReset();" />
					</s:else> <input type="button" value="Return to List" class="token"
						onclick="callBack();" /></td>
					<td width="22%" align="right"><s:if test="disburseFlag"></s:if><s:else>
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
					</s:else></td>
				</tr>



				<!-- <tr>
					<td width="78%" align="left" colspan="1"><s:if
						test="disburseFlag">
						<s:submit action="TravelExpDisbrsmnt_saveData" value="Save "
							theme="simple" cssClass="token" />
						<input type="button" value="Reset" class="token"
							onclick="callReset();" />
					</s:if> <input type="button" value="Return to List" class="token"
						onclick="callBack();" /></td>
					<td width="22%" colspan="1"><s:if test="disburseFlag">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
					</s:if></td>
				</tr> -->



			</table>
			</td>
		</tr>

		<s:if test='%{showRevokeStatus}'>
			<tr>
				<td>
				<table class="formbg" width="100%">
					<tr>
						<td colspan="2"><font color="red"><b>Application
						has been revoked. So cannot be disbursed.</b></font></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>


		<s:if test="approverCommentsFlag">
			<tr>
				<td>
				<table class="formbg" width="100%">
					<tr>
						<td width="25%"><label class="set" name="trvlClm.apprCmts"
							id="trvlClm.apprCmts" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.apprCmts")%></label>
						:</td>
						<td><s:textarea theme="simple" cols="70" rows="3"
							name="clmApprCmts" /></textarea></td>
					</tr>

				</table>
				</td>
			</tr>

		</s:if>




		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->

		<s:if test="true">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								Approver Details :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top"
									ondblclick="callShowDiv(this);">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date</td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>
								</td>

							</tr>


							<%
							int m = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="10%" class="sortableTD"><%=m%><s:hidden
										name="appSrNo" value="%{<%=m%>}" /></td>
									<td width="15%" class="sortableTD"><s:property
										value="prevApproverID" /><s:hidden name="prevApproverID" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="prevApproverName" /><s:hidden name="prevApproverName" /></td>
									<td width="10%" class="sortableTD" align="center"><s:property
										value="prevApproverDate" /><s:hidden name="prevApproverDate" /></td>
									<td width="10%" class="sortableTD">&nbsp;<s:property
										value="prevApproverStatus" /><s:hidden
										name="prevApproverStatus" /></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property
										value="prevApproverComment" /><s:hidden
										name="prevApproverComment" /></td>
								</tr>
								<%
								m++;
								%>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>
		<tr>
			<td width="100%" colspan="4">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td colspan="4" width="30%"><strong>Employee Details</strong></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%">
						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="30%" colspan="1"><s:label name="empName"
								theme="simple" value="%{empName}" /></td>

							<td width="20%" class="formtext" colspan="1"><label
								class="set" name="travelstartdate" id="travelstartdate"
								ondblclick="callShowDiv(this);"><%=label.get("travelstartdate")%></label>
							:</td>
							<td width="30%" colspan="1"><s:label name="trvlStartDate"
								theme="simple" value="%{trvlStartDate}" /></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="30%" colspan="1"><s:label name="empBranch"
								theme="simple" value="%{empBranch}" /></td>
							<td width="20%" class="formtext" colspan="1"><label
								class="set" name="travelenddate" id="travelenddate"
								ondblclick="callShowDiv(this);"><%=label.get("travelenddate")%></label>
							:</td>
							<td width="30%" colspan="1"><s:label name="trvlEndDate"
								theme="simple" value="%{trvlEndDate}" /></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td width="30%" colspan="1"><s:label name="empDept"
								theme="simple" value="%{empDept}" /></td>
							<td width="20%" class="formtext" colspan="1"><label
								class="set" name="grade" id="grade"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td width="30%" colspan="1"><s:label name="empGrade"
								theme="simple" value="%{empGrade}" /></td>
						</tr>



						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="1"><s:label name="empDesgn"
								theme="simple" value="%{empDesgn}" /></td>

							<td width="20%" colspan="1"><label class="set" name="trvlid"
								id="trvlid" ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label>
							:</td>
							<td width="30%" class="formtext" colspan="1"><s:property
								value="claimtrvlId" /></td>
						</tr>


						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="appl.date" id="appl.date"
								ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>
							:</td>
							<td width="80%" colspan="3"><s:label name="clmApplDate"
								theme="simple" value="%{clmAppDate}" /></td>
							<!--<td width="50%" class="formtext" colspan="2"><input
								type="button" value="View Travel Policy" class="token"
								onclick="viewPolicy(document.getElementById('paraFrm_gradId').value);"
								align="top"></td>
							<td width="30%" colspan="1">
							<s:if test='%{clmTrvlType=="T"}'>

								<input type="button" value="View Booking Details" class="token"
									onclick="showBookingDtls(<s:property value="bDtlAppId" />,<s:property value="bDtlAppCode" />,<s:property value="bDtlEmpId" />,<s:property value="bDtlAppDate" />,<s:property value="bDtlInitrId" />)"
									align="top">
							</s:if></td>-->



							<script>
	         function showBookingDtls(appId,appCode,empId,appDate,initId){	          		
	           		win=window.open('','win','top=260,left=250,width=900,height=600,scrollbars=yes,status=no,resizable=no');
					document.getElementById("paraFrm").target="win";
					//document.getElementById("paraFrm").action="TravelApplication_bookingDtls.action?applicationId="+appId+"&empApplId="+appCode+"&status=FI&empId="+empId+"&applDate="+appDate+"&iniEmpId="+initId+"&dtlsType=SCH&userType=SCH";
					document.getElementById("paraFrm").action="TravelApplication_bookingDtls.action?applicationId="+appId+"&empApplId="+appCode+"&status=FI&empId="+empId+"&applDate="+appDate+"&iniEmpId="+initId+"&dtlsType=SCH&userType=SCH&dtlsFor=CLAIM";
					document.getElementById("paraFrm").submit();
					document.getElementById("paraFrm").target="main";
	          }
	         </script>


						</tr>
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>






		<tr>
			<td colspan="3" width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Payment Details</strong></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%">
						<tr>
							<td width="30%" class="formtext" height="22" colspan="1"><label
								class="set" name="apprvAmt" id="apprvAmt"
								ondblclick="callShowDiv(this);"><%=label.get("apprvAmt")%></label>
							:</td>
							<td width="25%" colspan="2" nowrap="nowrap"><s:textfield
								size="10" name="apprvdAmt" value="%{apprvdAmt}" readonly="true"
								theme="simple" /> <s:property value="totalCurrencyExpense" /></td>
						</tr>

						<tr>
							<td width="30%" class="formtext" height="22" colspan="1"><label
								class="set" name="lessAdvAmt" id="lessAdvAmt"
								ondblclick="callShowDiv(this);"><%=label.get("lessAdvAmt")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield size="10"
								readonly="true" name="advAmt" value="%{advAmt}" theme="simple"
								onkeyup="callMeOnload();" /> <s:property
								value="totalCurrencyExpense" /></td>

						</tr>
						<s:hidden name="otherAdv" value="%{otherAdv}" />
						<!--<tr>
							<td width="30%" class="formtext" height="22" colspan="1"><label
								class="set" name="lessAllPrev" id="lessAllPrev"
								ondblclick="callShowDiv(this);"><%=label.get("lessAllPrev")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield name="otherAdv"
								size="10" onkeypress="return numbersWithDot();" value="%{otherAdv}"
								onkeyup="callMeOnload();" maxlength="10" /></td>
						</tr>



						-->
						<tr>
							<td width="30%" class="formtext" height="22" colspan="1"><label
								class="set" name="totDisAmt" id="totDisAmt"
								ondblclick="callShowDiv(this);"><%=label.get("totDisAmt")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield size="10"
								name="totDisbrAmt" value="%{totDisbrAmt}" readonly="true"
								theme="simple" /> <s:property value="totalCurrencyExpense" /></td>
						</tr>
						<tr>
							<td width="30%" class="formtext" height="22" colspan="1"><label
								class="set" name="expDisburseDate" id="expDisburseDate"
								ondblclick="callShowDiv(this);"><%=label.get("expDisburseDate")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield size="10"
								name="expDisbDate" value="%{expDisbDate}" readonly="true"
								theme="simple" /></td>
						</tr>
						
					</table>
					</td>
				</tr>



			</table>
			</td>
		</tr>


		<tr id="hideTR">
			<td colspan="4" width="100%">

			<table width="100%" class="formbg" border="0" theme="simple"
				id="disbursement">


				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="pay.date" id="pay.date"
						ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label>
					:<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:textfield name="disburseDate"
						size="15" maxlength="10" onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_disburseDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_disburseDate','dd-mm-yyyy')" /> <s:a
						href="javascript:NewCal('paraFrm_disburseDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>
					<td width="20%" class="formtext" colspan="1"><label
						class="set" name="pay.mode" id="pay.mode"
						ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label><font
						color="red">*</font>:</td>
					<td width="30%" colspan="1"><!--<s:select name="disburseMode"
						cssStyle="width:100" theme="simple"
						list="#{'CA':'Cash','CH':'Cheque','TR':'Transfer','IS':'In Salary'}"
						onchange="return changePayMode();" />
						
						--><s:select name="disburseMode" list="payModeMap" size="1"
						headerKey="" headerValue="--Select--" cssStyle="width:100"
						theme="simple" onchange="return changePayMode();" /></td>
				</tr>
				<tr id="cheque">
					<td width="20%" class="formtext" height="22" colspan="1"><label
						class="set" name="chk.date" id="chk.date"
						ondblclick="callShowDiv(this);"><%=label.get("chk.date")%></label>
					:<font color="red">*</font></td>
					<td width="30%" nowrap="nowrap" colspan="1"><s:textfield
						name="disburseChqDate" size="15" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_disburseChqDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_disburseChqDate','dd-mm-yyyy')" /> <s:a
						href="javascript:NewCal('paraFrm_disburseChqDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>
					<td width="20%" class="formtext" colspan="1"><label
						class="set" name="chk.no" id="chk.no"
						ondblclick="callShowDiv(this);"><%=label.get("chk.no")%></label> :<font
						color="red">*</font></td>
					<td width="30%"><s:textfield name="disburseChqNo" size="25"
						maxlength="10" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr id="bank">
					<td width="20%" class="formtext" height="22" colspan="1"><label
						class="set" name="acco.no" id="acco.no"
						ondblclick="callShowDiv(this);"><%=label.get("acco.no")%></label>
					:<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:textfield
						name="disburseAccount" size="25" maxlength="10"
						onkeypress="return numbersOnly();" /></td>
					<td width="20%" class="formtext" colspan="1"><label
						class="set" name="bank.name" id="bank.name"
						ondblclick="callShowDiv(this);"><%=label.get("bank.name")%></label>
					:<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:hidden name="bankCode" /> <s:hidden
						name="bankIFSCCode" /> <s:hidden name="bankBranch" /> <s:hidden
						name="bankBSRCode" /> <s:textfield name="disburseBankName"
						size="25" maxlength="30" readonly="true" /> <a href="#"><img
						src="../pages/common/css/default/images/search2.gif" width="16"
						height="15" border="0"
						onclick="javascript:callsF9(500,325,'TravelExpDisbrsmnt_f9Bank.action');" /></a>

					</td>
				</tr>

				<tr id="salary">
					<td width="20%" colspan="1"><label class="set" name="month"
						id="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
						color="red">*</font></td>
					<td width="30%" colspan="1"><s:select theme="simple"
						name="monthClaim" cssStyle="width:155"
						list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td width="20%" colspan="1"><label class="set" name="year"
						id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font
						color="red">*</font></td>
					<td width="30%" colspan="1"><s:textfield name="yearClaim"
						size="25" maxlength="4" onkeypress="return numbersOnly();" /></td>
				</tr>

				<tr id="creditHeadShowId" style="display: none;">
					<td width="20%" colspan="1"><label class="set"
						name="salCredit" id="salCredit" ondblclick="callShowDiv(this);"><%=label.get("salCredit")%></label><font
						color="red">*</font>:</td>
					<td width="30%" colspan="1"><s:textfield name="creditName"
						size="25" readonly="true" /><s:hidden name="creditCode" /><img
						src="../pages/common/css/default/images/search2.gif" width="16"
						height="15" border="0"
						onclick="javascript:callsF9(500,325,'TravelExpDisbrsmnt_f9creditCode.action');" /></td>
					<td width="20%" colspan="1"></td>
					<td width="30%" colspan="1"></td>
				</tr>





				<tr>
					<td width="20%" class="formtext" height="22" colspan="1"><label
						class="set" name="amount" id="amount1"
						ondblclick="callShowDiv(this);"><%=label.get("amount")%></label> :<font
						color="red">*</font></td>

					<td width="30%" colspan="1"><s:textfield name="disburseAmount"
						size="15" maxlength="10" onkeypress="return numbersWithDot();"
						readonly="true" /> <s:property value="totalCurrencyExpense" /></td>
					<td width="20%" class="formtext" valign="middle" colspan="1"><label
						class="set" name="comments" id="comments1"
						ondblclick="callShowDiv(this);"><%=label.get("comments")%>
					:</label></td>
					<td width="30%" nowrap="nowrap" colspan="1"><s:hidden
						name="descCnt1" /> <s:textarea name="disbursementComment"
						rows="3" cols="23" readonly="false" /><img
						src="../pages/images/zoomin.gif" height="12" align="bottom"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_disbursementComment','comments','','paraFrm_descCnt1','2000');"></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="disburseFlag"></s:if>

		<s:else>

			<tr id="addremove">
				<td width="100%" colspan="4" align="center"><input
					type="button" class="add" value="Add" onclick="callAdd();" /> <input
					type="button" class="delete" value="Remove" onclick="callRemove();" />
				</td>


			</tr>
		</s:else>
		<tr id="recovery">
			<td colspan="8" width="100%">
			<table border="0">

				<tr>
					<td width="100%" colspan="8"><font color="red">* Note:
					Advance amount is greater than claim amount. So, there is a
					recovery.</font></td>

				</tr>

				
			</table>
			</td>
		</tr>
		<tr id="recovery1">
			<td colspan="8" width="100%">
			<table border="0"><!--

				<tr>
					<td width="100%" colspan="8"><font color="red">* Note:
					Advance amount is greater than claim amount. So, there is a
					recovery.</font></td>

				</tr>

				--><tr>
					<td width="15%" colspan="1"><label class="set"
						name="recovery.month" id="recovery.month"
						ondblclick="callShowDiv(this);"><%=label.get("recovery.month")%></label><font
						color="red">*</font>:</td>
					<td width="15%" colspan="1"><s:select theme="simple"
						name="recoveryMonth" cssStyle="width:130"
						list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td width="15%" colspan="1"><label class="set"
						name="recovery.year" id="recovery.year"
						ondblclick="callShowDiv(this);"><%=label.get("recovery.year")%></label><font
						color="red">*</font>:</td>
					<td width="15%" colspan="1"><s:textfield name="recoveryYear"
						size="10" maxlength="4" onkeypress="return numbersOnly();" /></td>
					<td width="15%" colspan="1"><label class="set"
						name="recovery.debit" id="recovery.debit"
						ondblclick="callShowDiv(this);"><%=label.get("recovery.debit")%></label><font
						color="red">*</font>:</td>
					<td width="15%" colspan="1"><s:textfield name="recoveryDebit"
						size="25" readonly="true" /><s:hidden name="recoveryDebitCode" /></td>
					<td width="15%" colspan="2"><img
						src="../pages/common/css/default/images/search2.gif" width="16"
						height="15" border="0"
						onclick="javascript:callsF9(500,325,'TravelExpDisbrsmnt_f9recoveryDebit.action');" /></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" class="formbg" border="0" theme="simple"
				id="claimDisbursement">
				<tr>
					<td class="formth" width="5%" colspan="1"><b><label
						class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b></td>
					<td class="formth" width="10%" colspan="1"><b><label
						class="set" name="pay.date" id="pay.date"
						ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label></b></td>
					<td class="formth" width="15%" colspan="1" align="left"><b><label
						class="set" name="pay.mode" id="pay.mode"
						ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label></b></td>
					<td class="formth" width="15%"><b><label class="set"
						name="payDet" id="payDet" ondblclick="callShowDiv(this);"><%=label.get("payDet")%></label></b></td>
					<td class="formth" width="10%"><b><label class="set"
						name="amount" id="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></b></td>
					<td class="formth" width="30%"><b><label class="set"
						name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></b></td>
					<s:if test="disburseFlag">
						<td class="formth" width="10%">&nbsp;</td>
						<td class="formth" width="5%">&nbsp;</td>
					</s:if>
					<s:else>
						<td class="formth" width="10%"><b>Delete</b></td>
						<td class="formth" width="5%"><input type="checkbox"
							name="checkAll" id="checkAll" onclick="callAll();" /></td>
					</s:else>
				</tr>
				
				
		
				<%
					int i = 1;
				%>
				<s:iterator value="paymentList">

					<tr id="editRow<%=i%>">
						<td class="sortableTD" width="5%" align="center"><s:hidden
							name="creditCodeItt" /> <s:hidden name="creditNameItt" /> <%=i%>
						<input type="hidden" name="editButton" id="editButton<%=i %>"
							value="<s:property value="editButton" />"><input
							type="hidden" name="disbBalId" id="disbBalId<%=i %>"
							value="<s:property value="disbBalId" />"></td>
						<td class="sortableTD" align="center" width="15%"><s:hidden
							name="disbBalDate" /><s:property value="disbBalDate" />&nbsp;</td>
						<td class="sortableTD" width="15%"><s:hidden
							name="disbBalPayMode" /><s:property value="disbBalPayMode" />&nbsp;</td>
						<td class="sortableTD" width="28%"><s:hidden name="bankId" /><s:hidden
							name="bankName" /><s:hidden name="accountNo" /><s:hidden
							name="chqDate" /><s:hidden name="chqNo" /><s:hidden
							name="month" /> <s:hidden name="year" /><s:property
							value="paymentDet" /><s:hidden name="paymentDet" />&nbsp;</td>
						<td class="sortableTD" width="12%" align="right"><s:hidden
							name="disbBalAmt" id='<%="disbBalAmt"+i%>' /> <s:property
							value="disbBalAmt" /> &nbsp;</td>
						<td class="sortableTD" width="20%" align="center"><s:property
							value="disbBalCmt" /><s:hidden name="disbBalCmt"
							id="<%="disbBalCmt"+i %>" /> <!--<img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							width="12" theme="simple"
							onclick="javascript:callWindow('<%="disbBalCmt"+i %>','comments','readonly','2000','2000');">
						--><s:hidden name="disbursementId" />&nbsp; <input type="hidden"
							name="payFlag" id="payFlag<%=i%>" value="N"></td>
						<script type="text/javascript">
							
							disbursement[<%=i%>] = document.getElementById('disbBalAmt'+<%=i%>).value;
							
						</script>



						<td class="sortableTD" width="5%"><s:if test="editButton"></s:if><s:else>
							<!--  <input type="button" class="token" value="Edit"
								onclick="callForEdit('<%=i %>','<s:property value="disbBalId"/>','<s:property value="disbBalDate"/>',
								'<s:property value="disbBalPayMode"/>','<s:property value="bankId"/>','<s:property value="accountNo"/>',
								'<s:property value="chqDate"/>','<s:property value="chqNo"/>','<s:property value="disbBalAmt"/>',
								'<s:property value="disbBalCmt"/>','<s:property value="bankName"/>','<s:property value="month"/>','<s:property value="year"/>');" />
							-->
						</s:else> &nbsp;</td>
						<td class="sortableTD" width="5%" valign="middle">&nbsp; <s:if
							test="editButton">

						</s:if><s:else>
							<input type="checkbox" name="payChk" id="payChk<%=i%>"
								onclick="callCheck('<%=i %>');">
						</s:else></td>


					</tr>

					<%
						i++;
					%>
				</s:iterator>



			</table>

			</td>
		</tr>
		<tr>
			<td colspan="8">
			<table border="0" width="100%">
				<tr>
					<td align="right" colspan="6"><label class="set" name="totAmt"
						id="totAmt" ondblclick="callShowDiv(this);"><%=label.get("totAmt")%></label>
					:</td>
					<td colspan="2" align="center"><s:textfield size="10"
						name="totalAmount" value="%{totalAmount}" readonly="true"
						theme="simple" /> <s:property value="totalCurrencyExpense" /></td>
				</tr>

				<tr>
					<td align="right" colspan="6"><label class="set" name="balAmt"
						id="balAmt" ondblclick="callShowDiv(this);"><%=label.get("balAmt")%></label>
					:</td>
					<td colspan="2" align="center"><s:textfield size="10"
						name="balanceAmount" value="%{balanceAmount}" readonly="true"
						theme="simple" /> <s:property value="totalCurrencyExpense" /></td>
				</tr>
			</table>
			</td>
		</tr>


		
		<!--<tr>
			<td colspan="8" width="100%">
			<table border="0" >
				<tr>
					<td width="15%" colspan="1"><label
						class="set" name="status" id="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label><font
						color="red">*</font> :</td>
					<td width="85%" colspan="7"><s:select name="disburseStatus"
						cssStyle="width:130" theme="simple"
						list="#{'S':'--Select--','P':'Partially Closed','C':'Closed'}" 
						onchange="return changeStatus();" />
						 X= Over Payment </td>

				</tr>
			</table>
			</td>
		</tr>
		
		
		-->
		<s:hidden name="disburseStatus" />
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:if test="disburseFlag"></s:if><s:else>
						<input type="button" value="Save" class="token"
							onclick="callSave();" />
						<input type="button" value="Reset" class="token"
							onclick="callReset();" />
					</s:else> <input type="button" value="Return to List" class="token"
						onclick="callBack();" /></td>
					<td width="22%" align="right"></td>
				</tr>



			</table>
			</td>
		</tr>


		<s:hidden name="disburseCode" />
		
		<s:hidden name="rowId" />
		<s:hidden name="paraId" />
		<s:hidden name="transferFlag" />
	</table>
</s:form>


<script>
function changeStatus(){

	if(document.getElementById('paraFrm_disburseStatus').value=="X"){
		document.getElementById('recovery').style.display='';
		document.getElementById('recovery').style.display='';
		  var mode=document.getElementById('paraFrm_disburseMode').value;
		alert(mode);
		  if(mode=='IS'){
		  	document.getElementById('recovery1').style.display='';
		  }
	}else{
		document.getElementById('recovery').style.display='none';
		document.getElementById('recovery1').style.display='none';
		document.getElementById('paraFrm_recoveryMonth').value="";
		document.getElementById('paraFrm_recoveryYear').value="";
		document.getElementById('paraFrm_recoveryDebit').value="";
		document.getElementById('paraFrm_recoveryDebitCode').value="";
	}
}


function callSave(){
try{
		
	var tbl = document.getElementById('claimDisbursement');
	var disburseStatus=document.getElementById('paraFrm_disburseStatus').value;
	var disbAmt = document.getElementById('paraFrm_totDisbrAmt').value;
	
	var rowLen = tbl.rows.length;
	if(rowLen==1&& disbAmt>0)
	{
		alert('Please add payment details. ');
		return false;
	}
	var sttamt=document.getElementById('paraFrm_disburseAmount').value;
	
	/*if(sttamt!='')
	{
	   alert('Please add payment details.');
	   return false;
	}*/
	
	var amt=eval(document.getElementById('paraFrm_balanceAmount').value)
	/*if(disburseStatus=='S')
	{ alert('Please select status');
	  return false;
	}
	
	if(amt==0.00 && disburseStatus=='P')
	{
		alert('You cannot keep status as partially closed ');
		return false;
	
	}
	else if(amt>0 && disburseStatus=='C')
	{
		alert('you cannot keep status as  Closed ');
		return false;
	
	}
	else if(amt>=0 && disburseStatus=='X')
	{
		alert('Status should not be over Payment');
		return false;
	
	}*/
	if(disbAmt<0){
	var month = document.getElementById('paraFrm_recoveryMonth').value;
	var year = document.getElementById('paraFrm_recoveryYear').value;
	var currentTime = new Date();
	var currentMonth = currentTime.getMonth() + 1;
	var currentYear = currentTime.getFullYear();
	if(month=="0"){
			alert("Please select"+document.getElementById("recovery.month").innerHTML.toLowerCase());
			return false;
	}
	if(year==""){
			alert("Please enter "+document.getElementById("recovery.year").innerHTML.toLowerCase());
			return false;
	}
	if(document.getElementById('paraFrm_recoveryDebitCode').value==""){
			alert("Please select "+document.getElementById("recovery.debit").innerHTML.toLowerCase());
			return false;
	}		 
	
	if(year > currentYear){
	}
	else if(currentMonth < month && currentYear <= year) { 		

	}
	else{
	         alert("Entered Salary month should be greater than Current Month and Year");
		     return false;
	    }
	}
	
	if(rowLen==1 && eval(document.getElementById('paraFrm_totDisbrAmt').value>0)){
	//alert("In ifffffffffff");
		alert("There are no records to save.");
		return false;
	}	
	else{
	//alert('disburseStatus'+document.getElementById('paraFrm_disburseStatus').value);
	document.getElementById('paraFrm').action ="TravelExpDisbrsmnt_saveData.action";  
	document.getElementById('paraFrm').submit();
	}
	}catch(e){
		alert(e);
		return false;
	}
}

function valCTC(fieldName,labelName)
	{
	var amount=document.getElementById(fieldName).value;
	var amountLabel=document.getElementById(labelName).innerHTML.toLowerCase();
	if(trim(amount)!=""){
		 if(isNaN(amount)){ 
		    alert("Only one dot is allowed in "+amountLabel+" field.");
		    document.getElementById(fieldName).focus();
		    return false;
		    }	
		  }
		return true;
  }
function dateCompare(fromDate, toDate, fieldName,toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater or equal to system date.");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}


var currentTime = new Date();
var month = currentTime.getMonth() + 1;
var day = currentTime.getDate();
var year = currentTime.getFullYear();
var currentDate=day+"-"+month+"-"+year ;
changePayMode();


	function callReset(){
		
		/*if(document.getElementById("paraFrm_disburseFlag").value != 'true') {
			return false;
		}*/
        document.getElementById("paraFrm_otherAdv").value="";
		document.getElementById("paraFrm_disburseDate").value="";
		document.getElementById("paraFrm_disburseMode").value="";
		document.getElementById("paraFrm_disburseChqDate").value="";
		document.getElementById("paraFrm_disburseChqNo").value="";
		document.getElementById("paraFrm_disburseAccount").value="";
		document.getElementById("paraFrm_disburseBankName").value="";
		document.getElementById("paraFrm_bankCode").value="";
		document.getElementById("paraFrm_disburseAmount").value="";
		document.getElementById("paraFrm_disbursementComment").value="";
		document.getElementById("paraFrm_disburseChqNo").value="";
	    document.getElementById("paraFrm_disburseChqDate").value="";	
	    //document.getElementById("paraFrm_disburseStatus").value="S";  
	    document.getElementById('cheque').style.display='none';
    	document.getElementById('bank').style.display='none';
    	//document.getElementById('paraFrm_disburseStatus').disabled=false;
    	document.getElementById("paraFrm_otherAdv").readOnly=false;
     callMeOnload();
     
     }

	function callCheck(id){
		if(document.getElementById('payChk'+id).checked ==true)
			document.getElementById('payFlag'+id).value="Y";
		else
			document.getElementById('payFlag'+id).value="N";
		}//End of function

	function callRemove(){
	//alert('in remove');
			var result=false;
			var tbl = document.getElementById('claimDisbursement');
			//alert('tbl.rows.length'+tbl.rows.length);
			var rowLen = tbl.rows.length-1;
			
		//alert('in rowLen'+rowLen);
			
			
			
			
			for (var idx = 1; idx <= rowLen; idx++) {
			//
			 if(document.getElementById('disbBalId'+idx).value=='')
	    {
			
	    		if(document.getElementById('payChk'+idx).checked)
	    		 result=true;	
	    }
	    		 else{
	    		//alert('in else');
			 continue;
			}
	    		  
	        }	
	        if(!result){
	        	alert("There is no record to remove.");
	        	return false;
	        }else{
			    var conf=confirm("Do you really want to remove the records?");
			    if(conf){
				document.getElementById('paraFrm').action ="TravelExpDisbrsmnt_removePayDtls.action";  
				document.getElementById('paraFrm').submit();
				}
			}	
		}//End of function


		function callAdd(){
		
					if(document.getElementById('paraFrm_disburseMode').value==""){						
							       alert("Please select the "+document.getElementById('pay.mode').innerHTML.toLowerCase());
							    	return false;
								
						}
						if(trim(document.getElementById('paraFrm_disburseDate').value)==""){
							    alert("Please select or enter the "+document.getElementById('pay.date').innerHTML.toLowerCase());
							     return false;
			
			          		}
			try{
					     if(!validateDate("paraFrm_disburseDate","pay.date")){
				               document.getElementById('paraFrm_disburseDate').focus();
				               return false;
				
				             }
				             
				           var pay=document.getElementById('paraFrm_disburseDate').value 
				          if(!dateCompare(currentDate,pay,'paraFrm_disburseDate','pay.date'))
				               return false;
			
						  if(document.getElementById('paraFrm_disburseMode').value=="TR"){						
							    if(document.getElementById('paraFrm_disburseAccount').value==""){							       
							       alert("Please enter the "+document.getElementById('acco.no').innerHTML.toLowerCase());
							    	return false;
							    }
							    if(document.getElementById('paraFrm_bankCode').value==""){							    
							     alert("Please select the "+document.getElementById('bank.name').innerHTML.toLowerCase());
							    	return false;
							    
							    }
						
								
						}//end of transfer
						
						if(document.getElementById('paraFrm_disburseMode').value=="IS"){
						    var currentTime = new Date();
						    var currentMonth = currentTime.getMonth() + 1;
						    var currentYear = currentTime.getFullYear();
						    var month =document.getElementById('paraFrm_monthClaim').value;
						    var year =document.getElementById('paraFrm_yearClaim').value;
						    var creditCode =document.getElementById('paraFrm_creditCode').value;						
								 if(month== "0" ||month==""){							       
								       alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
								    	return false;
								  }
								  if(year==""){							    
								     alert("Please enter the "+document.getElementById('year').innerHTML.toLowerCase());
								    return false;
								    
								 }				       
								 if(creditCode=="") {
								    alert("Please select credit head.");
								    return false;
								 }
								    						
						  if(year>currentYear){
						  }
						  else if(currentMonth < month && currentYear <= year) { 		
				          }
						  else{
			        		 alert("Entered Salary month should be greater than Current Month and Year");
				     		 return false;
			    		  }	
			    		  
			    		  if(disbAmt<0){
							var month = document.getElementById('paraFrm_recoveryMonth').value;
							var year = document.getElementById('paraFrm_recoveryYear').value;
							var currentTime = new Date();
							var currentMonth = currentTime.getMonth() + 1;
							var currentYear = currentTime.getFullYear();
							if(month=="0"){
									alert("Please select"+document.getElementById("recovery.month").innerHTML.toLowerCase());
									return false;
							}
							if(year==""){
									alert("Please enter "+document.getElementById("recovery.year").innerHTML.toLowerCase());
									return false;
							}
							if(document.getElementById('paraFrm_recoveryDebitCode').value==""){
									alert("Please select "+document.getElementById("recovery.debit").innerHTML.toLowerCase());
									return false;
							}		 
							
							if(year > currentYear){
							}
							else if(currentMonth < month && currentYear <= year) { 		
						
							}
							else{
							         alert("Entered Salary month should be greater than Current Month and Year");
								     return false;
							    }
							}
								    
					}
					  if(document.getElementById('paraFrm_disburseMode').value=="CH"){
						  if(trim(document.getElementById('paraFrm_disburseChqDate').value)==""){
						   		alert("Please select or enter the "+document.getElementById('chk.date').innerHTML.toLowerCase());
						   		return false;
						   }
						   if(!validateDate("paraFrm_disburseChqDate","chk.date")){
				               document.getElementById('paraFrm_disburseChqDate').focus();
				               return false;
				
				           }
			             	var chq=document.getElementById('paraFrm_disburseChqDate').value;
							var pay=document.getElementById('paraFrm_disburseDate').value;
							if(!dateDifferenceEqual(pay, chq, 'paraFrm_disburseChqDate', 'pay.date','chk.date'))
		  							return false;
						    if(document.getElementById('paraFrm_disburseChqNo').value==""){
					    		alert("Please enter the "+document.getElementById('chk.no').innerHTML.toLowerCase());
					    	   	return false;
						    }
					  }//end of cheque
					  if(trim(document.getElementById('paraFrm_disburseAmount').value)==""){
								alert("Please enter the "+document.getElementById('amount').innerHTML.toLowerCase());
								return false;
						}
						
					
						
						
						if(!valCTC('paraFrm_disburseAmount','amount'))
							return false;
							
							
						var deduction=document.getElementById('paraFrm_disburseAmount').value;	
						var deduct=document.getElementById('paraFrm_otherAdv').value;	
						
						if(document.getElementById('paraFrm_otherAdv').value=="")
						{
						deduct=" ";
						}	
						
						if(deduction.indexOf(".")!=-1){
						 var dot=deduction.split('.');
				         if(dot[1].length >2){
					      alert("Only two digits are allowed after the dot.");
					      document.getElementById('paraFrm_disburseAmount').focus();
					      return false;
     			          }
						}
					}catch(e){
						alert(e);
					}
					
					try
							{	
						if(!valCTC('paraFrm_otherAdv','lessAllPrev'))
							return false;
							
						 if(deduct.indexOf(".")!=-1){
						  var dot1=deduct.split('.');
						 if(dot1[1].length >2){
					      alert("Only two digits are allowed after the dot.");
					      document.getElementById('paraFrm_otherAdv').focus();
					      return false;
     			         }
     			        }
     			        }
     			        catch(e)
     			        {
     			     	  // alert('2: ='+e);
     			        }
						var disbursedAmt=document.getElementById('paraFrm_totDisbrAmt').value;
						var amount=0;
						var disburse=document.getElementById('paraFrm_disburseAmount').value
						for(var i=1;i<disbursement.length;i++){
						
							amount+=parseFloat(disbursement[i]);
							
							
						 }
		

 if(parseFloat(amount)==0){ 	  
			if(parseFloat(disburse) > parseFloat(disbursedAmt)){
				
					alert(document.getElementById('amount').innerHTML.toLowerCase()+ " should not exceed the "+document.getElementById('totDisAmt').innerHTML.toLowerCase()+".");
					return false;
		
			}
			else
			{
			 // alert("else");
			}
	}else{
			if(document.getElementById('paraFrm_paraId').value==""){	
						if((parseFloat(amount)+parseFloat(disburse)) > parseFloat(disbursedAmt)){						
							alert(document.getElementById('amount').innerHTML.toLowerCase()+ " should not exceed the "+document.getElementById('totDisAmt').innerHTML.toLowerCase()+".");
							return false;
						
						}
				}else{		
						
						  var id=document.getElementById('paraFrm_paraId').value;
						  
						  var editAmt=document.getElementById('disbBalAmt'+id).value;	
						  
						  amount=eval(amount)-eval(editAmt);
						 
						  if(eval(amount)+eval(disburse) > eval(disbursedAmt)){
						    alert(document.getElementById('amount').innerHTML.toLowerCase()+ " should not exceed the "+document.getElementById('totDisAmt').innerHTML.toLowerCase()+".");
							return false;
						
				  }	
	
	          }		
	
	
	}	
			var comment=trim(document.getElementById('paraFrm_disbursementComment').value);
			if(comment.length >2000){
			
				alert("Maximum length of "+document.getElementById('comments').innerHTML.toLowerCase()+" is 2000 characters");
				return false;
			}
			
			
				
				
		
	if(parseFloat(amount)==0){
			document.getElementById('paraFrm_totalAmount').value=parseFloat(disburse);
			var total=document.getElementById('paraFrm_totalAmount').value;
			document.getElementById('paraFrm_balanceAmount').value=parseFloat(disbursedAmt)-parseFloat(total);
		
	   }else{
	
				if(document.getElementById('paraFrm_paraId').value==""){
					document.getElementById('paraFrm_totalAmount').value=parseFloat(amount)+parseFloat(disburse);
					var total=document.getElementById('paraFrm_totalAmount').value;
					document.getElementById('paraFrm_balanceAmount').value=parseFloat(disbursedAmt)-parseFloat(total);
				}else{
						  var id=document.getElementById('paraFrm_paraId').value;
						  var editAmt=document.getElementById('disbBalAmt'+id).value;	
						  document.getElementById('paraFrm_totalAmount').value=parseFloat(amount)+parseFloat(disburse);
					      var total=document.getElementById('paraFrm_totalAmount').value;
					      document.getElementById('paraFrm_balanceAmount').value=parseFloat(disbursedAmt)-parseFloat(total);	
				}
        }		
        
        
			
		if(document.getElementById('paraFrm_paraId').value==""){
		     		document.getElementById('paraFrm').action ="TravelExpDisbrsmnt_addPayDtls.action";  
			        document.getElementById('paraFrm').submit();  
		        }else{
			        document.getElementById('paraFrm').action ="TravelExpDisbrsmnt_editPayDtls.action";  
			        document.getElementById('paraFrm').submit(); 
		        }
		
    }
		
		


function callForEdit(srNo,disbId,disDate,disMode,bankId,accountNo,chqDate,chqNo,amount,comment,bankName,month,year ){

	 
	 
		document.getElementById('paraFrm_paraId').value=srNo;
		
		document.getElementById('paraFrm_disburseCode').value=disbId;
		document.getElementById('paraFrm_disburseDate').value=disDate;
		if(disMode=="Transfer"){
		document.getElementById('cheque').style.display='none';
		document.getElementById('bank').style.display='';
		document.getElementById('salary').style.display='';
		
		document.getElementById('paraFrm_disburseMode').value="TR";
		document.getElementById('recovery1').style.display='none';
		}
		else if(disMode=="Cheque"){
		document.getElementById('cheque').style.display='';
		document.getElementById('bank').style.display='none';
		document.getElementById('salary').style.display='';
		document.getElementById('paraFrm_disburseMode').value="CH";
		document.getElementById('recovery1').style.display='none';
		}
		//ADDED BY REEBA
		else if(disMode=="In Salary"){
		document.getElementById('cheque').style.display='none';
		document.getElementById('bank').style.display='none';
		document.getElementById('salary').style.display='';
		document.getElementById('paraFrm_disburseMode').value="IS";
		document.getElementById('creditHeadShowId').style.display='';
		document.getElementById('recovery1').style.display='';
		
		}
		else{
		document.getElementById('cheque').style.display='none';
		document.getElementById('bank').style.display='none';
		document.getElementById('paraFrm_disburseMode').value="CA";
		document.getElementById('recovery1').style.display='none';
		}
		document.getElementById('paraFrm_bankCode').value=bankId;
		document.getElementById('paraFrm_disburseAccount').value=accountNo;
		document.getElementById('paraFrm_disburseChqDate').value=chqDate;
		document.getElementById('paraFrm_disburseChqNo').value=chqNo;
		document.getElementById('paraFrm_disburseAmount').value=amount;
		document.getElementById('paraFrm_disbursementComment').value=comment;
		document.getElementById('paraFrm_disburseBankName').value=bankName;
		document.getElementById('paraFrm_monthClaim').value=month;
		 
		document.getElementById('paraFrm_yearClaim').value=year;
	 
}




function changePayMode(){
    
    var mode=document.getElementById('paraFrm_disburseMode').value;
   	document.getElementById('recovery1').style.display='none';
    if(mode=="TR"){
    	
    		document.getElementById('cheque').style.display='none';
    		document.getElementById('bank').style.display='';
    		
    		
    		document.getElementById('paraFrm_disburseChqDate').value="";
    		document.getElementById('paraFrm_disburseChqNo').value="";
    		document.getElementById('salary').style.display='none';
    		document.getElementById('paraFrm_monthClaim').style.display='none';
    		document.getElementById('paraFrm_yearClaim').style.display='none';
    		document.getElementById('paraFrm_monthClaim').style.display='none';
    		document.getElementById('paraFrm_yearClaim').style.display='none';
    			document.getElementById('creditHeadShowId').style.display='none';
    		document.getElementById('recovery1').style.display='none';
            
    }else if(mode=="CH"){
            //document.getElementById('paraFrm_bankCode').value="";
    		//document.getElementById('paraFrm_disburseBankName').value="";
            //document.getElementById('paraFrm_disburseAccount').value="";
            document.getElementById('cheque').style.display='';
    		document.getElementById('bank').style.display='none';
    		document.getElementById('salary').style.display='none';
    		document.getElementById('paraFrm_monthClaim').value="";
    		document.getElementById('paraFrm_yearClaim').value="";
    		document.getElementById('creditHeadShowId').style.display='none';
    		document.getElementById('recovery1').style.display='none';
        } else if(mode=="IS"){
            //document.getElementById('paraFrm_bankCode').value="";
    		//document.getElementById('paraFrm_disburseBankName').value="";
            //document.getElementById('paraFrm_disburseAccount').value="";
            document.getElementById('paraFrm_disburseChqDate').value="";
    		document.getElementById('paraFrm_disburseChqNo').value="";
            document.getElementById('cheque').style.display='none';
    		document.getElementById('bank').style.display='none';
    		document.getElementById('salary').style.display='';
    		document.getElementById('paraFrm_monthClaim').style.display='';
    		document.getElementById('paraFrm_yearClaim').style.display='';
    		document.getElementById('creditHeadShowId').style.display='';
    		document.getElementById('recovery1').style.display='';
        }else{
        
            //document.getElementById('paraFrm_bankCode').value="";
    		//document.getElementById('paraFrm_disburseBankName').value="";
            //document.getElementById('paraFrm_disburseAccount').value="";
            document.getElementById('cheque').style.display='none';
    		document.getElementById('bank').style.display='none';
    		document.getElementById('salary').style.display='none';
    		document.getElementById('paraFrm_disburseChqDate').value="";
    		document.getElementById('paraFrm_disburseChqNo').value="";
    		document.getElementById('paraFrm_monthClaim').style.display='none';
    		document.getElementById('paraFrm_yearClaim').style.display='none';
    		document.getElementById('creditHeadShowId').style.display='none';
        	document.getElementById('recovery1').style.display='none';
        }
        
        } 
function callAll(){
	var tbl = document.getElementById('claimDisbursement');
		var rowLen = tbl.rows.length;
		//alert('rowLen'+rowLen);
	if (document.getElementById('checkAll').checked == true){
	
	for (var idx = 1; idx <= rowLen; idx++) {
	
	
	    if(document.getElementById('disbBalId'+idx).value=='')
	    {
				document.getElementById('payChk'+idx).checked = true;
				 document.getElementById('payFlag'+idx).value="Y";
			}
			else{
			continue;
			}
	    }

 }else{
 
	for (var idx =1; idx <= rowLen; idx++) {
	 if(document.getElementById('disbBalId'+idx).value=='')
	    {
			
	document.getElementById('payChk'+idx).checked = false;
	document.getElementById('payFlag'+idx).value="N";
	}
	else{
			continue;
			}
	
     }
  }	 


}
callMeOnload();
		function callMeOnload()
				{
			alert("Hi");
			try{
				document.getElementById('recovery1').style.display='none';
				var apprvdAmt=document.getElementById('paraFrm_apprvdAmt').value; 
				var advAmount=document.getElementById('paraFrm_advAmt').value; 
				var otherAmt=document.getElementById('paraFrm_otherAdv').value; 
				var balance=document.getElementById('paraFrm_balanceAmount').value;
				var tmsClmStatus=document.getElementById('paraFrm_tmsClmStatus').value;
				//var disburseStatus=document.getElementById('paraFrm_disburseStatus').value;
				//alert("--disburseStatus--"+disburseStatus);
				if(otherAmt=="")
				{
				otherAmt=0.0;
				}
				if(advAmount=="")
				{
				advAmount=0.0;
				}
				
				var total=eval(apprvdAmt)-(eval(advAmount)+eval(otherAmt));
				//alert("total"+total);
				if(total<0){
				//document.getElementById('paraFrm_disburseStatus').value="C";
				document.getElementById('recovery').style.display='';
				var mode=document.getElementById('paraFrm_disburseMode').value;
				alert(mode);
				if(mode=='IS'){
				 document.getElementById('recovery1').style.display='';
				 }
				}
					//alert('2');
				else if(total==0)
				{
				//alert('1');
					//document.getElementById('paraFrm_disburseStatus').value="C";
					//document.getElementById('paraFrm_disburseStatus').disabled=true;
					document.getElementById('recovery').style.display='none';
					document.getElementById('recovery1').style.display='none';
				}
				//	alert('3');
				else{
					document.getElementById('recovery').style.display='none';
					document.getElementById('recovery1').style.display='none';
					/*if(disburseStatus=="P"){
						document.getElementById('paraFrm_disburseStatus').value="P";
						document.getElementById('recovery').style.display='none';
					}
					else if(disburseStatus=="C"){
					//alert('else if');
					document.getElementById('paraFrm_disburseStatus').value="C";
					document.getElementById('paraFrm_disburseStatus').disabled=true;
					document.getElementById('recovery').style.display='none';
					}
					else{
						document.getElementById('paraFrm_disburseStatus').value="S";
						document.getElementById('recovery').style.display='none';
					}*/
				}
				
				document.getElementById('paraFrm_totDisbrAmt').value=total;
				document.getElementById('paraFrm_disburseAmount').value=total;
				//for chking -ve in total  disbursement
				
			/*	if(document.getElementById('paraFrm_totDisbrAmt').value<='0')
				{
					try{
					
					document.getElementById('addremove').style.display='none';
					document.getElementById('hideTR').style.display='none';
					}catch(e){}
					}
				else
				{
					
					document.getElementById('addremove').style.display='';
					document.getElementById('hideTR').style.display='';
				}
				*/
				var totAmt=document.getElementById('paraFrm_totalAmount').value;
				if(totAmt!=''){
				
				var val=eval(total)-eval(totAmt);
				document.getElementById('paraFrm_balanceAmount').value=val.toFixed(2);
				}
				
				if(document.getElementById('paraFrm_disburseStatus').value=="C") {
					document.getElementById('paraFrm_otherAdv').readOnly = true;
				
				}
				
				}catch(e){ }//alert("value of e     ---"+e);
						//alert("status - "+document.getElementById('paraFrm_disburseStatus').value);	
		}
				
	function callFun(stat)
					{
					 document.getElementById('paraFrm_statusSave').value=stat;
						var disMsg="";
						if(stat=="A")
						disMsg="Approve";
						else if(stat=="B")
						disMsg="send back";
						else 
						disMsg="submit"		
						
						
						               if(stat=="A")
						               {
						               //check monadatory fields
						                   
						               if(!document.getElementById('elgblAmtRadio').checked && !document.getElementById('expAmtRadio').checked  && !document.getElementById('otherRadio').checked)
						               {
						               alert("Select radio button or Enter the approved amount.");
						               return false;
						               }
						               
						               if(document.getElementById('otherRadio').checked)
						               {
						                  //check for other amount
						                  
						                     if(document.getElementById('paraFrm_otherAmt').value=="")
						                     {
						                     alert("Plese enter the approved amount.");
						                     return false;
						                     }
						                  }
						               
						               }
										
						var confr=confirm('Do you want to '+disMsg+' the application?');
						if(confr)
						{
						return true;
						}
						
						else
						{
						return false;
						
						}
					
					
					
					
					
					   
					}//end of callFun

 
 		function callBack()
		{    	
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "TravelExpDisbrsmnt_input.action";
		document.getElementById('paraFrm').submit();  
		}
	
					
		function viewPolicy(gradId){		   
			//alert(gradId);  
			win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
			document.getElementById("paraFrm").target="win";
			document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradId;
			document.getElementById("paraFrm").submit();	
			document.getElementById("paraFrm").target="main"; 
		}
		
	    function chkCmtsLength()
	    {  
	      if(eval(document.getElementById('paraFrm_descCnt').value)<0)
		  {
	      alert('Maximum length of '+document.getElementById("trvl.apprCmts2").innerHTML+' is 500');
	      document.getElementById("paraFrm_clmApprCmts").focus();
		  return false;
		  }
		  else
		  {
		  return true;
		  }
	   
	    }
	    
	    
 function validateDate(fieldName, labName){

	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}  

function dateDifferenceEqual(fromDate, toDate, fieldName, fromLabName, toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater or equal to "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}
	    
    
</script>
