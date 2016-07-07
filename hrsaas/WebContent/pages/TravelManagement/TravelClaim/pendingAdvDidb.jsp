<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="AdvClmDisbursement" validate="true" id="paraFrm"
	theme="simple">
	<!-- Added by krishna -->
	<s:hidden name="hiddengustflag" />

	<table width="100%" border="0" align="right" cellpadding="2"
		class="formbg" cellspacing="1">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head"> Advance Disbursement </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="rowId" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<s:hidden name="checkEdit" />
		<s:hidden name="categoryListlength" />
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

		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START -->

		<s:if test="showApproverComments">
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
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%" colspan="5"><strong>Employee Details</strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="30%" colspan="1"><s:property value="pendingEmpName" /><s:hidden
						name="pendingEmpId" /></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="travelstartdate" id="travelstartdate"
						ondblclick="callShowDiv(this);"><%=label.get("travelstartdate")%></label></td>
					<td width="30%" colspan="1"><s:property value="trvlStrtDate" />
					<s:hidden name="trvlStrtDate" />
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="30%" colspan="1"><s:property value="branchName" /></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="travelenddate" id="travelenddate"
						ondblclick="callShowDiv(this);"><%=label.get("travelenddate")%></label>:</td>
					<td width="30%" colspan="1"><s:property value="travelEnddate" />
					<s:hidden name="travelEnddate" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						class="set" name="department" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label :</td>
					<td width="30%" colspan="1"><s:property value="departmentName" /></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="grade" id="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
					<td width="30%" colspan="1"><s:property value="gradeName" /><s:hidden
						name="gradeID" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value="desig" /></td>
					<td width="20%" colspan="1" class="formtext"></td>
					<td width="30%" colspan="1"></td>

				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="appl.date" name="appl.date"
						ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="pendingApplicationDate" /></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="trvlid" name="trvlid"
						ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label> :</td>
					<td width="30%" colspan="1"><s:property value="pendingtrvlId" /></td>
					<s:hidden name="pendingapplId" />
					<s:hidden name="pendingtrvlId" />
					<s:hidden name="pendingapplCode" />
				</tr>
				<!--<tr>
					<td width="100%" colspan="4" class="formtext"><input
								type="button" value="View Travel Policy" class="token"
								onclick="viewPolicy(document.getElementById('paraFrm_gradeID').value);"
								align="top"></td>	
				</tr>-->

				</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- payment details  block-->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="2"
						class="formbg">
						<tr>
							<td width="20%" colspan="4"><strong>Payment Details</strong></td>
						</tr>

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="pay.date" id="pay.dated" ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label>
							<font color="red">*</font></td>
							<td width="30%" colspan="1"><s:textfield name="paymentDate"
								theme="simple" size="25" maxLength="10"
								onkeypress="return numbersWithHiphen();" /> <a
								href="javascript:NewCal('paraFrm_paymentDate','DDMMYYYY');">
							<img src="../pages/images/Date.gif" class="iconImage" height="16"
								align="absmiddle" width="16"> </a></td>
							<td width="20%" colspan="1"><label class="set"
								name="pay.mode" id="pay.mode" ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label><font
								color="red">*</font>:</td>

							<td width="30%" colspan="1"><!--<s:select name="paymentmode"
								cssStyle="width:150" theme="simple"
								list="#{'CA':'Cash','CH':'Cheque','TR':'Transfer','IS':'In Salary'}"
								onchange="return changeCombo();" />
								--><s:select name="paymentmode" list="payModeMap" size="1"
								headerKey="" headerValue="--Select--" cssStyle="width:150"
								theme="simple" onchange="return changeCombo();" /></td>
						</tr>

						<tr id="check">
							<td width="20%" colspan="1"><label class="set" name="chk.no"
								id="chk.no" ondblclick="callShowDiv(this);"><%=label.get("chk.no")%></label><font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:textfield name="checkNumber"
								theme="simple" size="25" maxLength="10"
								onkeypress="return numbersOnly();" /></td>
							<td width="20%" colspan="1"><label class="set"
								name="chk.date" id="chk.date" ondblclick="callShowDiv(this);"><%=label.get("chk.date")%></label><font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:textfield name="chkDate"
								theme="simple" size="25" maxLength="10" /> <a
								href="javascript:NewCal('paraFrm_chkDate','DDMMYYYY');"> <img
								src="../pages/images/Date.gif" class="iconImage" height="16"
								align="absmiddle" width="16"> </a></td>
						</tr>

						<tr id="trans">
							<td width="20%" colspan="1"><label class="set"
								name="acco.no" id="acco.no" ondblclick="callShowDiv(this);"><%=label.get("acco.no")%></label>
							<font color="red">*</font></td>
							<td width="30%" colspan="1"><s:textfield name="account"
								theme="simple" size="25" maxLength="10"
								onkeypress="return numbersOnly();" /></td>
							<td width="20%" colspan="1"><label class="set"
								name="bank.name" id="bank.name" ondblclick="callShowDiv(this);"><%=label.get("bank.name")%></label>
							<font color="red">*</font></td>
							<td width="30%" colspan="1"><s:hidden name="bankid" /> <s:hidden
								name="bankIFSCCode" /> <s:hidden name="bankBranch" /> <s:hidden
								name="bankBSRCode" /> <s:textfield name="bank" size="25"  readonly="true" />
							<img src="../pages/images/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'AdvClmDisbursement_f9Bank.action');">
							</td>
						</tr>

						<tr id="salary">
							<td width="20%" colspan="1"><label class="set" name="month"
								id="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:select theme="simple"
								name="monthAdv" cssStyle="width:155"
								list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
							<td width="20%" colspan="1"><label class="set" name="year"
								id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:textfield name="yearAdv"
								size="25" maxlength="4" onkeypress="return numbersOnly();" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" class="formtext" height="22"><label
								class="set" id="requiredadvamount" name="requiredadvamount"
								ondblclick="callShowDiv(this);"><%=label.get("requiredadvamount")%></label>
							:</td>
							<td width="30%" nowrap="nowrap"><s:property
								value="pendingadvAmount" /><s:hidden name="pendingadvAmount" />
							<s:property value="applApprvdAdvAmtCurrency" /></td>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="amount" id="amount1"
								ondblclick="callShowDiv(this);"><%=label.get("amount")%></label>
							<font color="red">*</font>:</td>
							<td width="30%" nowrap="nowrap"><s:textfield name="amount"
								theme="simple" size="15" maxLength="10"
								onkeypress="return numbersWithDot();"
								value="%{pendingadvAmount}" readonly="true" /> <s:property
								value="applApprvdAdvAmtCurrency" /></td>
						</tr>

						<tr>
							<td width="10%" colspan="1"><label class="set"
								name="comments" id="comments1" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
							</td>
							<td width="90%" colspan="3"><s:hidden name="descCnt1" /><s:textarea
								name="comment" rows="3" cols="70" readonly="false" /><img
								src="../pages/images/zoomin.gif" height="12" align="bottom"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_comment','comments','','paraFrm_descCnt1','2000');">
							</td>
						</tr>
						<s:if test='%{showRevokeStatus}'></s:if>
						<s:else>
							<tr>
								<td colspan="4" align="center" width="100%"><input
									type="button" value="Add " class="add"
									onclick="return addrow();" /></td>
							</tr>
						</s:else>
						</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- end of payment details -->
				<tr>
					<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<!-- Payment Details   -->
						<tr>
							<td>
							<table width="100%" class="formbg">
								<tr>
									<td width="70%" colspan="5"><b>Payment Details</b></td>
									<td width="30%" colspan="1" align="right"><s:submit
										value="Remove " cssClass="delete"
										onclick="return deleteRow();"
										action="AdvClmDisbursement_removeRow" />
								</tr>
								<tr>
									<td colspan="6">
									<table width="100%" class="sorttable" id="disbursement">
										<tr>
											<td class="formth" width="5%"><b><label class="set"
												name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
											</td>
											<td class="formth" width="10%"><b><label class="set"
												name="pay.date" id="pay.date"
												ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label></b>
											</td>
											<td class="formth" width="15%"><b><label class="set"
												name="pay.mode" id="pay.mode"
												ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label></b>
											</td>
											<td class="formth" width="20%"><b>Payment Details</b></td>
											<td class="formth" width="15%"><b><label class="set"
												name="amount" id="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></b>
											</td>
											<td class="formth" width="25%"><b><label class="set"
												name="comments" id="comments"
												ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></b>
											</td>
											<td class="formth" width="10%"><b>Edit</b></td>
											</td>
											<td class="formth" width="5%"><input type="checkbox"
												class="checkbox" value="N" name="confChk" id="confChk"
												onclick="callForAll();" /></td>
										</tr>
										<%!int count = 0;%>
										<%
										int i = 1;
										%>
										<s:iterator value="payList">

											<tr>
												<td class="sortableTD"><%=i%> <input type="hidden"
													name="settingflag" id="settingflag<%=i %>"
													value="<s:property value="settingflag" />"> <input
													type="hidden" name="SrNo" id="SrNo<%=i %>" value="<%=i%>">&nbsp;
												<input type="hidden" name="rownum" size="10"
													id="rownum<%=i %>" value="<s:property value="rownum" />"></td>
												<td class="sortableTD" align="center"><input
													type="hidden" name="itpaymentdate" size="15"
													id="itpaymentdate<%=i %>"
													value="<s:property value="itpaymentdate" />"><s:property
													value="itpaymentdate" />&nbsp;</td>
												<td class="sortableTD" align="center"><input
													type="hidden" name="itpaymentmode"
													id="<%="itpaymentmode"+i%>"
													value="<s:property value="itpaymentmode" />"><s:property
													value="itpaymentmode" />&nbsp;</td>

												<td class="sortableTD" align="center"><input
													type="hidden" name="month" id="month<%=i %>"
													value="<s:property value="month" />"><s:property
													value="monthName" /> <BR />
												<input type="hidden" name="year" id="year<%=i %>"
													value="<s:property value="year" />"><s:property
													value="year" /> <input type="hidden" name="chkno"
													id="chkno<%=i %>" value="<s:property value="chkno" />"><s:property
													value="chkno" /> <input type="hidden" name="checkdate"
													id="checkdate<%=i %>"
													value="<s:property value="checkdate" />"><BR />
												<s:property value="checkdate" /> <input type="hidden"
													name="accountno" id="accountno<%=i %>"
													value="<s:property value="accountno" />"><s:property
													value="accountno" /> <BR />
												<input type="hidden" name="bankname" id="bankname<%=i %>"
													value="<s:property value="bankname" />"><s:property
													value="bankname" /> <input type="hidden" name="itbankid"
													id="itbankid<%=i %>"
													value="<s:property value="itbankid" />">&nbsp;</td>


												<td class="sortableTD" align="center"><input
													type="hidden" name="itamount" id="itamount<%=i %>"
													value="<s:property value="itamount" />"
													onkeyup="return amountvalue();"> <s:property
													value="itamount" />&nbsp;</td>
												<td align="center" class="sortableTD"><s:property
													value="itcomment" /><s:hidden name="itcomment"
													id="<%="itcomment"+i %>" /> <!--<img
													src="../pages/images/zoomin.gif" height="12"
													align="absmiddle" width="12" theme="simple"
													onclick="javascript:callWindow('<%="itcomment"+i %>','comments','readonly','2000','2000');">
												--></td>
												<td class="sortableTD"><s:if test="settingflag">
													<input type="button" class="edit"
														onclick="calForedit('<s:property value="itpaymentdate"/>',
														'<s:property value="itpaymentmode"/>','<s:property value="chkno"/>',
														'<s:property value="checkdate"/>','<s:property value="accountno"/>',
														'<s:property value="bankname"/>','<s:property value="itamount"/>',
														'<s:property value="itcomment"/>','<s:property value="itbankid"/>',
														'<s:property value="SrNo"/>','<s:property value="month"/>','<s:property value="year"/>')"
														value=" Edit" />
												</s:if>&nbsp;</td>
												<td class="sortableTD" align="center"><s:if
													test="settingflag">
													<input type="checkbox" value="N" name="confChkSkill"
														id="confChkSkill<%=i%>"
														onclick="callForConsultant('<%=i%>')">

												</s:if>&nbsp;</td>
												<input type="hidden" name="hdeleteSkill"
													id="hdeleteSkill<%=i %>" value="N" />
											</tr>
											<script>
								
									 var tbl = document.getElementById('disbursement');
											var rowLen = tbl.rows.length;
                                          
                                         //  alert('ranu'+rowLen);
											for(var p=1;p<rowLen;p++)
												{
											//alert(''+document.getElementById("itpaymentmode"+p).value)
												staus=document.getElementById("itpaymentmode"+p).value;
												//alert('staus --'+staus);
												if(staus=='Cash'){
												//alert('in if'+p);
												//alert('value'+document.getElementById("chkno"+p).value);
												document.getElementById("chkno"+p).style.display='none';
												
												document.getElementById("checkdate"+p).style.display='none';
											//alert('after cash');
												document.getElementById("accountno"+p).style.display='none';
												//document.getElementById("dateimg"+p).style.display='none';
												document.getElementById("bankname"+p).style.display='none';
													document.getElementById("itbankid"+p).style.display='none';
												//document.getElementById("srchimg"+p).style.display='none';
												 }
												 
												  else if(staus=='Transfer'){
												document.getElementById("chkno"+p).style.display='none';
												//alert('in if of T');
												document.getElementById("checkdate"+p).style.display='none';
												//document.getElementById("dateimg"+p).style.display='none';
												document.getElementById("accountno"+p).disabled=false;
												document.getElementById("bankname"+p).disabled=false;
												
													document.getElementById("itbankid"+p).disabled=false;;
												//document.getElementById("srchimg"+p).style.display='';
												 }
												 else if(staus=='In Salary'){
												document.getElementById("month"+p).style.display='none';
												//alert('in if of T');
												document.getElementById("year"+p).style.display='none';
												//document.getElementById("dateimg"+p).style.display='none';
												
													document.getElementById("itbankid"+p).disabled=false;;
												//document.getElementById("srchimg"+p).style.display='';
												 }
												  else{
												// alert('in 3rd if');
												document.getElementById("chkno"+p).disabled=false;
												//alert('in if else ');
												document.getElementById("checkdate"+p).disabled=false;
												//alert('in case of ch')
												document.getElementById("accountno"+p).style.display='none';
												document.getElementById("bankname"+p).style.display='none';
												//document.getElementById("srchimg"+p).style.display='none';
												//alert('after of ch')
												document.getElementById("itbankid"+p).style.display='none';
												 }
												}                        
                                          </script>
											<%
											i++;
											%>
										</s:iterator>
										<%
										count = i;
										%>
									</table>
								<tr>
									<td colspan="6">
									<table width="100%">
										<tr>

											<td width="30%" colspan="2"></td>
											<td width="30%" colspan="1"></td>
											<td align="center" colspan="1" width=20%"><label
												class="set" name="advpaid" id="advpaid"
												ondblclick="callShowDiv(this);"><%=label.get("advpaid")%></label>

											</td>
											<td colspan="2" width="50%" nowrap="nowrap"><input
												type="text" readonly="readonly" style="border: none;"
												size="10" name="hiddenAdvPaid" id="hiddenAdvPaid"
												value="<s:property value="hiddenAdvPaid" />" /> <s:property
												value="applApprvdAdvAmtCurrency" /></td>

										</tr>
										<tr>
											<td width="30%" colspan="2"></td>
											<td width="30%" colspan="1"></td>
											<td align="center" colspan="1" width="20%"><label
												class="set" name="bal.amount" id="bal.amount"
												ondblclick="callShowDiv(this);"><%=label.get("bal.amount")%></label></td>
											<td colspan="2" width="50%" nowrap="nowrap"><input
												type="text" readonly="readonly" style="border: none;"
												size="10" name="hiddenBalanceAmt" id="hiddenBalanceAmt"
												value="<s:property value="hiddenBalanceAmt" />" /> <s:property
												value="applApprvdAdvAmtCurrency" /></td>
										</tr>
									</table>

									</td>
								</tr>
								<!--<tr>
									<td width="20%" colspan="1"><label class="set"
										id="statusname" name="statusname"
										ondblclick="callShowDiv(this);"><%=label.get("statusname")%></label>
									<font color="red">*</font>: </td>
									<td><s:select name="status"
										list="#{'S':'--Select--','C':'Application Closed','P':'Partially Paid'}" /></td>
								</tr>

							-->
								<s:hidden name="status" />
							</table>
							</td>
						</tr>

						<!-- end of payment details -->
					</table>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="60%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>


</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>




<script><!--


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
var currentTime = new Date();
var month = currentTime.getMonth() + 1;
var day = currentTime.getDate();
var year = currentTime.getFullYear();
var currentDate=day+"-"+month+"-"+year ;
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



function returntolistFun()
	{
	document.getElementById('paraFrm').action = "AdvClmDisbursement_returnHome.action";
	 document.getElementById('paraFrm').submit();
	
	
	}
callonload();
 function callonload()
{
document.getElementById('check').style.display='none';
document.getElementById('trans').style.display='none';
document.getElementById('salary').style.display='none';
}
 function changeCombo()
 {
 //alert("changeCombo");
 var status=document.getElementById('paraFrm_paymentmode').value;
 if(status=='CA')
	 {
	 document.getElementById('check').style.display='none';
	 document.getElementById('trans').style.display='none';
	 document.getElementById('salary').style.display='none';
	 }
	else if(status=='CH')
	 {
	 document.getElementById('check').style.display='';
	 document.getElementById('trans').style.display='none';
	 document.getElementById('salary').style.display='none';
	 }
	 else if(status=='IS')
	 {
	 document.getElementById('check').style.display='none';
	 document.getElementById('trans').style.display='none';
	 document.getElementById('salary').style.display='';
	 }
	else
	 {
	 document.getElementById('check').style.display='none';
	 document.getElementById('salary').style.display='none';
	 document.getElementById('trans').style.display='';
	 }
 }
 function addrow(){
    var fieldName = ["paraFrm_paymentDate"];
	var lableName = ["pay.date"];
	var flag = ["enter"];
    var paydate=trim(document.getElementById('paraFrm_paymentDate').value);
    var amount=trim(document.getElementById('paraFrm_amount').value);
    var requiredadv= parseFloat(document.getElementById('paraFrm_pendingadvAmount').value);
    var comments=trim(document.getElementById('paraFrm_comment').value);
    //alert('1');
   if(paydate==''){
     alert('Please select or enter '+document.getElementById('pay.date').innerHTML.toLowerCase());
     return false;
    }
 // alert('2');
   if(!validateDate("paraFrm_paymentDate","pay.date"))
			return false; 
   if(!dateCompare(currentDate,paydate,'paraFrm_paymentDate','pay.date'))
				               return false;		
	//alert('3');		
   var mode=document.getElementById('paraFrm_paymentmode').value;
	//alert('4'+mode);
   if(mode==''){
		 alert('Please select '+document.getElementById('pay.mode').innerHTML.toLowerCase());
		 return false;
    }
   if(mode=='CH'){
		var chkno=trim(document.getElementById('paraFrm_checkNumber').value);
		var chkdate=trim(document.getElementById('paraFrm_chkDate').value);
		if(chkno==''){
		    alert('Please enter '+document.getElementById('chk.no').innerHTML.toLowerCase());
		     return false;
		    }
		if(chkdate==''){
		     alert('Please enter '+document.getElementById('chk.date').innerHTML.toLowerCase());
		     return false;
		   }
		if(!validateDate("paraFrm_chkDate","chk.date"))
			return false;   
			
	    if(!dateDifferenceEqual(paydate, chkdate, 'paraFrm_chkDate', 'pay.date','chk.date'))
			return false;
    }
    else if(mode=='TR'){
     	 try{
     	 var accono=document.getElementById('paraFrm_account').value;
		 var bankname = trim(document.getElementById('paraFrm_bank').value);
		 var bankid = document.getElementById('paraFrm_bankid').value;
		 
		 		  
		  if(accono=='') { 
		    alert('Please enter '+document.getElementById('acco.no').innerHTML.toLowerCase());
		    document.getElementById('paraFrm_account').focus();
		     return false;
		    } 
		  			  	
		  	if(bankid==''){
		    alert('Please Select Bank Name');
		     document.getElementById('paraFrm_bank').focus();
		     return false;
		   } 	 
		   }catch(e){
		   alert(e);
		   }
	}	
    else if(mode=='IS'){     
     	var month=document.getElementById('paraFrm_monthAdv').value;
		var year=document.getElementById('paraFrm_yearAdv').value;
		var currentTime = new Date();
		var currentMonth = currentTime.getMonth() + 1;
	    var currentYear = currentTime.getFullYear();
		if(month==''){ 
		     alert('Please enter '+document.getElementById('month').innerHTML.toLowerCase());
		     return false;
		}
		if(year==''){
		    alert('Please Select '+document.getElementById('year').innerHTML.toLowerCase());
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
 	  if(amount==''){
 			alert('Please enter the '+document.getElementById('amount1').innerHTML.toLowerCase());
 			return false;
      }
      if(!valCTC('paraFrm_amount','amount'))
        return false;
                
      var amt=document.getElementById('paraFrm_amount').value
      if(amt.indexOf(".")!=-1){
		var dot=amt.split('.');
		if(dot[1].length >2){
		  alert("Only two digits are allowed after the dot.");
		  document.getElementById('paraFrm_amount').focus();
		  return false;
        }
	  }
     // alert('5');
 	 if(amount>requiredadv){
 		alert(document.getElementById('amount1').innerHTML.toLowerCase() +' should not be greater than '+document.getElementById('requiredadvamount').innerHTML.toLowerCase());
		return false;
 	 }
 	//for checking -ve value when enter
 	var calamount;
 	var cal=parseFloat(document.getElementById('hiddenAdvPaid').value);
    var chkedit=document.getElementById('paraFrm_checkEdit').value;
 	var tbl = document.getElementById('disbursement');
	var rowLen = tbl.rows.length;
 	//var srno=srno
 	if(cal!=''){//alert('amount'+amount);
 	if(chkedit!=''){
	 	for(var t=1;t<rowLen;t++ ){
	 	var srno=document.getElementById('SrNo'+t).value;
	 		if(chkedit==srno){
	 			var minusamt=requiredadv-(cal-parseFloat(document.getElementById('itamount' + t).value));
	 			   if(amount>minusamt){
	 			   alert('you cannot edit this amount as it is greater than required amount');
	 			   return false;
	 			   }
	 		}
	 	}
 	}
 	else {
	  calamount=cal+parseFloat(amount);	 	  
	    if(calamount>requiredadv){
	 	  alert(document.getElementById(' paraFrm_pendingadvAmount').innerHTML.toLowerCase() +' should not be greater than '+document.getElementById('hiddenAdvPaid').innerHTML.toLowerCase());
	 	  return false;
	   }	
 	} 	
 }
 	
 	
  //alert('6');
   if(comments.length > 2000){
 	  alert("Maximum length of "+document.getElementById('comments').innerHTML.toLowerCase()+" is 2000 characters.");
 	  document.getElementById('paraFrm_comment').focus();
 	  return false;
 	}
   var enddate=document.getElementById('paraFrm_travelEnddate').value;
   // alert('7');
   //alert('rowLen--'+rowLen);
   var tot=0;
   var amt=0;
   if(rowLen=='1') {
		// alert('in if');
	document.getElementById('hiddenAdvPaid').value=amount;
	var advamount=document.getElementById('paraFrm_pendingadvAmount').value;
	var balance=parseFloat(advamount)-parseFloat(document.getElementById('hiddenAdvPaid').value);
	// alert('balance'+balance);
	document.getElementById('hiddenBalanceAmt').value=parseFloat(balance);
   }
   else{
	var y=0;
	for(var i=1;i<rowLen;i++){  
		var srno=document.getElementById('SrNo' + i).value;
  		var chkedit= document.getElementById('paraFrm_checkEdit').value;
		if(srno!=chkedit){
			amt=parseFloat(document.getElementById('itamount' + i).value);  
			//alert('amt--'+amt);
			if(amt==''){ 
	             amt=0;
		    }
			y+=parseFloat(amt);
			//alert('y--'+y); 
		}
	} 
	var x=parseFloat(document.getElementById('paraFrm_amount').value)
	tot=parseFloat(x)+parseFloat(y);
	//alert('tot'+tot);	
	document.getElementById('hiddenAdvPaid').value=parseFloat(tot);
	var advamount=document.getElementById('paraFrm_pendingadvAmount').value;
	var balance=parseFloat(advamount)-parseFloat(tot);
    balance.toFixed(2) ;
    try{
		 document.getElementById('hiddenBalanceAmt').value=parseFloat(balance);
		   
	}catch(e){
		alert(e)
    }
}

document.getElementById('paraFrm').action="AdvClmDisbursement_addRow.action";
document.getElementById('paraFrm').submit();	
 }
 

function calForedit(paydate,paymode,chkno,chkdate,accountno,bankname,amount,comment,bankid,srno,month,year)
{
//alert("edit");
	    document.getElementById('paraFrm_paymentmode').value=paymode;
        document.getElementById('paraFrm_paymentDate').value=paydate;
    	   if(paymode=='Cash') {
	        document.getElementById('check').style.display='none';
	        document.getElementById('trans').style.display='none';
	        document.getElementById('salary').style.display='none';
	        document.getElementById('paraFrm_paymentmode').value='CA';
	        }
	       else if(paymode=='Cheque'){
	       document.getElementById('trans').style.display='none';
	       document.getElementById('salary').style.display='none';
	       document.getElementById('paraFrm_paymentmode').value='CH';
	       try{
	         document.getElementById('check').style.display='';
	         document.getElementById('paraFrm_checkNumber').value=chkno;
	         document.getElementById('paraFrm_chkDate').value=chkdate;
	         
	       }catch(e)
	       {
	         alert(e);
	       }
	       }//end of elseif
	   
	   	   else  if(paymode=='Transfer'){
	       document.getElementById('trans').style.display='';
	       try{
	       		document.getElementById('check').style.display='none';
	       }catch(e){}
	       document.getElementById('paraFrm_paymentmode').value='TR';
	       document.getElementById('paraFrm_account').value=accountno;
	       document.getElementById('paraFrm_bank').value=bankname;
	       document.getElementById('paraFrm_bankid').value=bankid;
	       }
	       else  if(paymode=='In Salary'){
	       document.getElementById('salary').style.display='';
	       try{
	       		document.getElementById('check').style.display='none';
	       		document.getElementById('trans').style.display='none';
	       }catch(e){}
	       document.getElementById('paraFrm_paymentmode').value='IS';
	       document.getElementById('paraFrm_monthAdv').value=month;
	       document.getElementById('paraFrm_yearAdv').value=year;
	       }
	       
	       
	       
	       document.getElementById('paraFrm_amount').value=amount;
	       document.getElementById('paraFrm_comment').value=comment;
           document.getElementById('paraFrm_checkEdit').value=srno;
}//end of  callForedit
 
 
 
 //for save
  function saveFun() 
  {
  var tbl = document.getElementById('disbursement');
		var rowLen = tbl.rows.length;
		if(rowLen==1)
		{
		alert('Please add payment details. ');
		return false;
		}
 
	    var balanceamount=document.getElementById('hiddenBalanceAmt').value;
		/*var sts=document.getElementById('paraFrm_status').value
	
		  if(sts=='S')
		  {
		  alert('Please select status.');
		  return false;
		  }
		  if(balanceamount=='0' && sts=='P')
		  {
		  alert("Status cannot be kept as Partially Paid");
		  return false;
		  }
		 
		if( sts=='P'){
	
			var totadvpaid=document.getElementById('hiddenAdvPaid').value;
			
			var totaladvance=document.getElementById('paraFrm_pendingadvAmount').value;
			
			if(parseFloat(totadvpaid) > parseFloat(totaladvance))
			{
			alert(' Total advance paid cannot be greater than required advance amount');
			return false;
			}
		}*/
	  
	document.getElementById('paraFrm').action = "AdvClmDisbursement_saveFun.action?status=T";
	 document.getElementById('paraFrm').submit();
		
  }
  

   
 function callForAll()
  {
	 	  
		var tbl = document.getElementById('disbursement');
		var rowLen = tbl.rows.length;
	if (document.getElementById("confChk").checked == true){
	//alert('in if'+ rowLen);
	try{
	for (var idx = 1; idx < rowLen; idx++) {
	         // alert('in for');
	          if(document.getElementById('rownum'+idx).value=='')
		     {
				 document.getElementById("confChkSkill"+idx).checked = true;
				 document.getElementById('hdeleteSkill'+idx).value="Y";
			 }
				else
				{
				 continue;
				}
	    }
}catch(e)
{
alert(e);
}
 }
 else{
	for (var idx = 1; idx < rowLen; idx++) {
			 if(document.getElementById('rownum'+idx).value=='')
			{
						
			document.getElementById("confChkSkill"+idx).checked = false;
			document.getElementById('hdeleteSkill'+idx).value="N";
			}
			else{
			continue;
			}
     }
  }	 
  }
 
 //for removing 
 
 function deleteRow()
   {
    if(chkSkill()){
    var flag ='<%=count%>';
    var tbl = document.getElementById('disbursement');
		var rowLen = tbl.rows.length;
		//alert("rowLen"+rowLen);
		 var amount=0;
		
	    	 var con=confirm('Do you want to delete the row(s) ?');
	    	 
	    	 if(con)
	    	 {
	    	     for(var i=1;i<rowLen;i++)
				 {
				 	if( (document.getElementById('SrNo'+i).value!="") &&  (document.getElementById('hdeleteSkill'+i).value=="Y") )
				         {
				       	 amount=amount+parseFloat(document.getElementById('itamount'+i).value);
			 	         }
			  	}
	    	 	var tot=document.getElementById('hiddenAdvPaid').value;
			    tot=tot-amount;
			    document.getElementById('hiddenAdvPaid').value=tot;
			    var bal= document.getElementById('paraFrm_pendingadvAmount').value;
			    document.getElementById('hiddenBalanceAmt').value=bal-tot;
	    	 	resetFun();
	    	 return true;
	    	 }
	    	 else
	    	 {
	    	 return false;
	    	 }
	     
       
}
	
	else {
		 	alert('Please select at least one record to remove');
		 	 return false;
		 }
	return true;
}
 
 
 
 
 
 

 
  function chkSkill(){
  
   var tbl = document.getElementById('disbursement');
  
		var rowLen = tbl.rows.length;
		
		 for(var a=1;a<rowLen;a++){	
		
		 if(document.getElementById('rownum'+a).value=='')
		    {
		   if(document.getElementById('confChkSkill'+a).checked == true)
			   {	
			  
		 	    return true;
		        }
		     }
		     else{
		   
		     continue;
		     }
		      	   
		  }
		  return false;
	}
 
 function callForConsultant(id)
	   {
	 	  
	
	   if(document.getElementById('confChkSkill'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteSkill'+id).value="Y";
	   
	   }
	   else{
	   document.getElementById('hdeleteSkill'+id).value="N";
	   	
   		}
  }
function resetFun()
{
//alert("reset");
 	   document.getElementById('paraFrm_paymentDate').value="";
	   document.getElementById('paraFrm_paymentmode').value="CA";
	   document.getElementById('paraFrm_amount').value="";
	   document.getElementById('paraFrm_comment').value="";
	   document.getElementById('paraFrm_checkNumber').value="";
	   document.getElementById('paraFrm_chkDate').value="";
	   //document.getElementById('paraFrm_account').value="";
	   //document.getElementById('paraFrm_bank').value="";
	   //document.getElementById('paraFrm_bankid').value="";
	   document.getElementById('paraFrm_monthAdv').value="";
	   document.getElementById('paraFrm_yearAdv').value="";
	   changeCombo();
	       
}

 function newRowColor(cell) {   		
		   cell.className='Cell_bg_first';
	    }
	    
 	function oldRowColor(cell,val) {
	 cell.className='Cell_bg_second';
		} 
		
	function viewPolicy(gradId){		   
		//alert(gradId);  
		win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
		document.getElementById("paraFrm").target="win";
		document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradId;
		document.getElementById("paraFrm").submit();	
		document.getElementById("paraFrm").target="main"; 
	}
	
	function monthDifference(){	
	var payMonth = document.getElementById('paraFrm_monthAdv').value;
	var payYear = document.getElementById('paraFrm_yearAdv').value;
	
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var year = currentTime.getFullYear();
	if(month < payMonth && year <= payYear) { 		
		return true;
	}
	else{
	 alert("Entered Salary month should be greater than Current Month and Year");
		return false;
	}
}
 
--></script>