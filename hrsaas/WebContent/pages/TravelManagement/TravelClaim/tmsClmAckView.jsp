<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ClmAcknowledgement" validate="true" id="paraFrm"
	theme="simple" method="post" target="main">

	<s:hidden name="tmsClmAppId" />
	<s:hidden name="trvlAppId" />
	<s:hidden name="trvlAppCode" />
	<s:hidden name="applicantId" />
	 <s:hidden name="source" id="source"/>
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">


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
						class="text_head">Claim Acknowledgement</strong></td>

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
					<td width="78%" align="left"><input type="button"
						class="token" value="Acknowledge"
						onclick="acceptClaimDisb('<s:property value="tmsClmAppId"/>',
												'<s:property value="trvlAppId"/>','<s:property value="trvlAppCode"/>')">
					<input type="button" value="Send Back" class="token"
						onclick="sendBack('<s:property value="tmsClmAppId"/>',
												'<s:property value="trvlAppId"/>','<s:property value="trvlAppCode"/>');" />
					<input type="button" value="Return to List" class="token"
						onclick="callBack();" /></td>
					<td width="22%" align="right"><s:if test="disburseFlag"></s:if><s:else>
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
					</s:else></td>
				</tr>

			</table>
			</td>
		</tr>
		<s:if test="approverCommentsFlag">
			<tr>
				<td>
				<table class="formbg" width="100%">
					<tr>
						<td width="25%"><label class="set" name="trvlClm.apprCmts"
							id="trvlClm.apprCmts" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.apprCmts")%></label>
						 :</td>
						<td><s:textarea theme="simple" cols="70" rows="3"
							name="clmApprCmts" /></textarea>
						</td>
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
								<td class="formth" width="10%" height="22" valign="top" ondblclick="callShowDiv(this);">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date </td>
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
					<tr >
					<td width="10%" class="sortableTD"><%=m%><s:hidden name="appSrNo" value="%{<%=m%>}"/> </td>
						<td width="15%" class="sortableTD"><s:property value="prevApproverID"/><s:hidden name="prevApproverID"/></td>
							<td width="25%" class="sortableTD"><s:property value="prevApproverName"/><s:hidden name="prevApproverName"/></td>
								<td width="10%" class="sortableTD" align="center"><s:property value="prevApproverDate"/><s:hidden name="prevApproverDate"/></td>
								<td width="10%" class="sortableTD">&nbsp;<s:property value="prevApproverStatus"/><s:hidden name="prevApproverStatus"/></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property value="prevApproverComment"/><s:hidden name="prevApproverComment"/></td>
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
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS END-->
		<tr>
							<td width="25%"><strong>Applicant Comments</strong></td>
						</tr>
						<tr>
							<td width="100%" colspan="4">
							<table width="100%" align="center" class="formbg" cellpadding="0"
								cellspacing="0" border="0">
								<tr>
									<td width="25%" class="formtext" height="22" colspan="1"
										valign="top"><label class="set" name="trvlClm.applCmts"
										id="trvlClm.applCmts" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.applCmts")%></label></td>
									<td width="75%" colspan="3"><s:property
										value="clmAppCmts" />
									<input type="hidden" name="descCntCmtA"
										id="paraFrm_descCntCmtA"> &nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
						
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


						</tr>

					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>

	<tr>
				<td colspan="8" width="100%">
				<table width="100%" align="center" class="formbg" theme="simple"
					border="0">
					<tr>
						<td colspan="8" width="100%"><strong>Expense Details
						</strong></td>
					</tr>
					<tr>
						<td width="100%" colspan="8">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td class="formth" colspan="1" width="10" nowrap="nowrap"><strong><label
									class="set" name="trvlClm.srNo" id="trvlClm.srNo"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></strong></td>
								<td class="formth" colspan="1" width="15%"><strong><label
									class="set" name="trvlClm.ExpDate" id="trvlClm.ExpDate"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.ExpDate")%></label></strong></td>
								<td class="formth" colspan="1" width="15%"><strong><label
									class="set" name="trvlClm.ExpType" id="trvlClm.ExpType"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.ExpType")%></label></strong></td>
								<td class="formth" colspan="1" width="22%"><strong>
								<label class="set" name="trvlClm.partclrs" id="trvlClm.partclrs"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.partclrs")%></label>
								</strong></td>
								<td class="formth" colspan="1" width="15%"><strong><label
									class="set" name="trvlClm.eligibleAmt" id="trvlClm.eligibleAmt"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.eligibleAmt")%></label></strong></td>
								<td class="formth" colspan="1" width="15%"><strong><label
									class="set" name="trvlClm.expAmt" id="trvlClm.expAmt"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.expAmt")%></label></strong></td>
								<td class="formth" colspan="1" width="15%"><strong><label
									class="set" name="trvlClm.Proof" id="trvlClm.Proof"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.Proof")%></label>
								Required</strong></td>
								<td class="formth" colspan="1" width="15%"><strong><label
									class="set" name="trvlClm.Proof" id="trvlClm.Proof"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.Proof")%></label></strong></td>
							</tr>
							<%!int i = 0;%>
							<%
								int k = 1;
							%>
							<s:iterator value="expDtls">
								<tr>
									<s:hidden name="expDtlId" />
									<s:hidden name="expExpAppId" />
									<td class="sortableTD"><%=k%></td>
									<td class="sortableTD"><s:property value="expDate" />&nbsp;</td>
									<td class="sortableTD"><s:property value="expName" />&nbsp;</td>
									<!--<td class="sortableTD"><textarea rows="2" cols="19"
										name="expParticlrs<%=k %>" id="paraFrm_expParticlrs<%=k %>"
										readonly="readonly"><s:property
										value="expParticlrs" /></textarea> &nbsp; <img
										src="../pages/images/zoomin.gif" height="12" align="bottom"
										width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_expParticlrs<%=k %>','trvlClm.partclrs','readonly','paraFrm_descCntAppl<%=k%>','500');">
									-->
									<td class="sortableTD"><s:property value="expParticlrs" />

									<input type="hidden" name="descCntAppl<%=k%>"
										id="paraFrm_descCntAppl<%=k%>"> &nbsp;</td>
									<td class="sortableTD" align="right">
										<s:property value="expElgblAmt" />&nbsp;
									</td>
									<td class="sortableTD" align="right">
										<s:property value="expExpAmt" />
										<s:property value="currencyExpenseAmt" />
										&nbsp;
									</td>
									<td class="sortableTD" nowrap="nowrap"><s:property
										value="expIsProof" />&nbsp;</td>
									<s:if test="(expIsProof == 'NO')">
										<td class="sortableTD"><s:hidden name="expProofPath" />
										&nbsp;</td>
									</s:if>
									<s:else>
										<td class="sortableTD"><s:hidden name="expProofPath" /><s:iterator
											value="expProofPathList">
											<a href="#"
												onclick="return showproofname('<s:property value="expProofPath" />');">
											<s:property value="expProofPath" /><br>
											</a>&nbsp;</s:iterator></td>
									</s:else>
								</tr>
								<%
									k++;
								%>
							</s:iterator>
							<%
								i = k;
							%>
							
						<s:if test="noData">
							<tr>
								<td colspan="9" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td align="right">
									<strong><label class="set" name="trvlClm.Total" id="trvlClm.Total"
									 ondblclick="callShowDiv(this);"><%=label.get("trvlClm.Total")%></label> : </strong>
								</td>
								<td class="sortableTD" align="right">
									<s:hidden name="totElgblAmt" />&nbsp;
								</td>
								<td class="sortableTD" align="right">
									<s:property	value="totExpAmt" />
									<s:property value="totalCurrencyExpense" />
									&nbsp;
								</td>
								<td nowrap="nowrap">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
 

		<tr>
			<td colspan="4" width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Payment Details</strong></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%">
						<tr>

							<td class="formtext" height="22"><label
								class="set" name="apprvAmt" id="apprvAmt"
								ondblclick="callShowDiv(this);"><%=label.get("apprvAmt")%></label>
							:</td>

							<td nowrap="nowrap">
								<s:label name="apprvdAmt" theme="simple" value="%{apprvdAmt}" />
								<s:property value="totalCurrencyExpense" />
							</td>
						</tr>
						<tr>
							<td width="" class="formtext" height="22" colspan="1"><label
								class="set" name="lessAdvAmt" id="lessAdvAmt"
								ondblclick="callShowDiv(this);"><%=label.get("lessAdvAmt")%></label>
							:</td>
							<td nowrap="nowrap">
								<s:label name="advAmt" value="%{advAmt}" theme="simple" onkeyup="callMeOnload();" />
								<s:property value="totalCurrencyExpense" />
							</td>

						</tr>
						<tr>
							<td width="" colspan="1" class="formtext" height="22"><label
								class="set" name="pay.date" id="pay.date"
								ondblclick="callShowDiv(this);"><%=label.get("expDisburseDate")%></label>
							:<font color="red">*</font></td>
							<td width="" colspan="1"><s:textfield name="disburseDate"
								size="15" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_disburseDate','dd-mm-yyyy');"
								 /> <s:a
								href="javascript:NewCal('paraFrm_disburseDate','DDMMYYYY');">
								<img src="../pages/common/css/default/images/Date.gif"
									width="16" height="16" border="0" />
							</s:a></td>
						</tr>
						
					</table>
					</td>
				</tr>


			</table>
			</td>
			
		</tr>

		<tr>
			<td colspan="2">
			<table width="100%" border="0" align="center" cellpadding="0" bordercolor=""
				cellspacing="0">


				<tr>

					<td width="78%" align="left"><input type="button"
						class="token" value="Acknowledge"
						onclick="acceptClaimDisb('<s:property value="tmsClmAppId"/>',
												'<s:property value="trvlAppId"/>','<s:property value="trvlAppCode"/>')">
					<input type="button" value="Send Back" class="token"
						onclick="sendBack('<s:property value="tmsClmAppId"/>',
												'<s:property value="trvlAppId"/>','<s:property value="trvlAppCode"/>');" />
					<input type="button" value="Return to List " class="token"
						onclick="callBack();" /></td>
					<td width="22%" align="right"></td>

				</tr>



			</table>
			</td>
		</tr>

	</table>


</s:form>


<script>

	

	function callBack()
		{  
		
		document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else{
			document.getElementById('paraFrm').action = "ClmAcknowledgement_input.action";
		}
		document.getElementById('paraFrm').submit();  
		}

							    
  function acceptClaimDisb(clmAppId,trvlAppId,trvlAppCode){
  
  //alert("clmAppId -- "+clmAppId);
	//alert("appId -- "+trvlAppId);
	//alert("appCode -- "+trvlAppCode);
	 
			if(trim(document.getElementById('paraFrm_disburseDate').value)==""){
				   alert("Please select or enter the "+document.getElementById('pay.date').innerHTML.toLowerCase());
					 document.getElementById('paraFrm_disburseDate').focus();
					// document.getElementById('paraFrm_disburseDate').style.background="#CCCCFF";
					   return false;
			
			 	}
	
		if(!dateCheckWithToday('paraFrm_disburseDate', 'pay.date')){
				  	 document.getElementById('paraFrm_disburseDate').focus();
					   return false;
			
			 	}
	
		if(confirm('Do you want to accept the claim application?')){
			document.getElementById('paraFrm').action = 'ClmAcknowledgement_acceptClaimDisbursement.action?cailmAppId='+clmAppId
				+'&travelApplId='+trvlAppId+'&travelApplCode='+trvlAppCode;	  
			document.getElementById('paraFrm').submit();
		    document.getElementById('paraFrm').target = "main";
		 
		} 
		else return false;
	}
	
	 function showproofname(fileName)
	{
	 	document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "ClmAcknowledgement_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function sendBack(clmAppId, trvlAppId, trvlAppCode){
		if(confirm('Do you want to send back the claim application?')){
		document.getElementById('paraFrm').target = "main";
		document.getElementById('paraFrm').action = 'ClmAcknowledgement_sendBackAcknowledgement.action?cailmAppId='+clmAppId
			+'&travelApplId='+trvlAppId+'&travelApplCode='+trvlAppCode;	  
		document.getElementById('paraFrm').submit();
		 
		} 
	}
	
</script>
