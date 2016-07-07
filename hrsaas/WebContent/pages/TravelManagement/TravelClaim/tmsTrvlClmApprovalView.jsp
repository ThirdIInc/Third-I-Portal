<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelClmAppvr" validate="true" id="paraFrm"
	theme="simple" method="post">

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">



		<!-- For Claim Application  -->

		<s:hidden name="travelApplicationId" />
		<s:hidden name="clmAppFlag" />
		<s:hidden name="clmAppCmts" />
		<s:hidden name="clmApplStatus" />
	
		<s:hidden name="gradId" />
		<s:hidden name="noData" />
		<!-- for navigation -->
		<s:hidden name="claimApplnCode" />
		<s:hidden name="tmsClmAppId" />
		<s:hidden name="tmsTrvlId" />
	
		<s:hidden name="tmsTrvlCode" />
		<s:hidden name="tmsExpType" />
		<s:hidden name="tmsApprvrLevel" />
		<s:hidden name="trvlEmpId" />

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
		<!-- for approved amount -->
		<s:hidden name="apprvdAmt" value="%{apprvdAmt}" />
		<!-- for approver -->
		<s:hidden name="approverId" />
		<s:hidden name="initiatorId" />
	

		<!-- For setting params for showing booking details -->

		<s:hidden name="bDtlAppId" />
		<s:hidden name="bDtlAppCode" />
		<s:hidden name="bDtlInitrId" />
		<s:hidden name="bDtlEmpId" />
		<s:hidden name="bDtlAppDate" />
		<s:hidden name="status"></s:hidden>
		<s:hidden name="checkStatus" /> 
		 <s:hidden name="source" id="source"></s:hidden>
		
		<tr>
			<td width="100%" colspan="4">
			<table width="100%" align="center" class="formbg" cellpadding="0"
				cellspacing="0">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<s:if test='%{clmAppFlag=="Y"}'>
						<td width="93%" class="txt"><strong class="text_head">Travel
						Claim Application </strong></td>
					</s:if>
					<s:else>
						<td width="93%" class="txt"><strong class="text_head">Travel
						Claim Approval </strong></td>
					</s:else>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<s:if test='%{clmAppFlag=="Y"}'>
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<s:if test="clmApplStatus=='CL'">
							<td width="78%" align="left"><s:submit
								value=" Return to list " theme="simple"
								onclick=" callBackCldApp();" cssClass="token" /></td>
						</s:if>
						<s:else>
							<td width="78%" align="left">
							<s:submit value=" Report " theme="simple"
								onclick=" report();" cssClass="token" />
							<s:submit
								value=" Return to list " theme="simple"
								onclick=" callBackApp();" cssClass="token" /></td>
						</s:else>
						<td width="22%" align="right"></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
							<td width="78%" align="left"><s:if test='%{navStatus=="P"}'>
								<input type="button"  value="Approve "  
									  onclick=" return callFun('A');" class="token" />
							<!-- <input type="button"    value="Reject"
									theme="simple" onclick=" return callFun('R');" class="token" /> -->	
								<input type="button"     value="Send Back "
									theme="simple" onclick=" return callFun('B');" class="token" />
							</s:if> <input type="button"  value=" Return to list " theme="simple"   
								onclick=" callBack();" class="token" />
								</td>
							<td width="22%" align="right"></td>
						</tr>
				</table>
				</td>
			</tr>
		</s:else>
		
		<s:if test='%{revokeFlag}'>
			<tr>
				<td colspan="4">
				<table class="formbg" width="100%">
					<tr>
						<td colspan="4"><font color="red"><b>Application has been revoked. So cannot be approved.</b></font>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:if test='%{clmAppFlag=="Y"}'>
			
		</s:if>
		<s:else>
		<s:if test="approverCommentsFlag">
			<tr>
				<td colspan="4">
				<table class="formbg" width="100%">
					<tr>
						<td width="25%"><label class="set" name="trvlClm.apprCmts"
							id="trvlClm.apprCmts" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.apprCmts")%></label>
						 :</td>
						<td><s:textarea theme="simple" cols="70" rows="3"
							name="clmApprCmts" />
													</td>
					</tr>
					
				</table>
				</td>
			</tr>

		</s:if>
		
		</s:else>
		
		<s:if test="true">
			<tr>
			<td colspan="4">
				<table class="formbg" width="100%">
					<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="trvlClm.budgetExp" id="trvlClm.budgetExp"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.budgetExp")%></label>:</td>
							<td width="25%"><s:label name="budgetExpenditure" theme="simple"
								value="%{budgetExpenditure}" /></td>
							<td width="20%" class="formtext"><label class="set"
								name="trvlClm.ActExp" id="trvlClm.ActExpt"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.ActExp")%></label>:</td>
							<td width="25%"> <s:label name="actualExpenditure" theme="simple"
								value="%{actualExpenditure}" />&nbsp; 
								<input name="viewExpenditure" class="addnew" type="button" class="token" value="  Details  "
								onclick="viewSplitExpenditure();" /></td>
						</tr>
						</table>
						</td>
						</tr>
						</s:if>
		
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->

		<s:if test="approverListFlag">
			
			
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
								<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
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
							

							</tr>
							
						 
							<%
							int m = 1;
							%>
				<s:iterator value="approverCommentList" status="stat">
					<tr>
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
			<td width="100%" colspan="4">
			<table width="100%" align="center" class="formbg" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><strong>Employee Details</strong></td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="trvlClm.empName" id="trvlClm.empName"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.empName")%></label>:</td>
							<td width="25%"><s:label name="empName" theme="simple"
								value="%{empName}" /></td>
							<td width="20%" class="formtext"><label class="set"
								name="trvlClm.tourStrtDt" id="trvlClm.tourStrtDt"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.tourStrtDt")%></label>:</td>
							<td width="25%"><s:label name="trvlStartDate" theme="simple"
								value="%{trvlStartDate}" /></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="25%"><s:label name="empBranch" theme="simple"
								value="%{empBranch}" /></td>
							<td width="20%" class="formtext"><label class="set"
								name="trvlClm.tourEndDt" id="trvlClm.tourEndDt"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.tourEndDt")%></label>:</td>
							<td width="25%"><s:label name="trvlEndDate" theme="simple"
								value="%{trvlEndDate}" /></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="25%"><s:label name="empDept" theme="simple"
								value="%{empDept}" /></td>
							<td width="20%" class="formtext"><label class="set"
								name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td width="25%"><s:label name="empGrade" theme="simple"
								value="%{empGrade}" /></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td width="25%"><s:label name="empDesgn" theme="simple"
								value="%{empDesgn}" /></td>
							<td width="20%" class="formtext"></td>
							<td width="25%"></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="trvlClm.appDate" id="trvlClm.appDate"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.appDate")%></label>:</td>
							<td width="25%"><s:label name="clmApplDate" theme="simple"
								value="%{clmAppDate}" /></td>
							<td width="20%" class="formtext"></td>
							<!--
							<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; <input
								type="button" value="View Travel Policy" class="token"
								onclick="viewPolicy(document.getElementById('paraFrm_gradId').value);"
								align="top"><br>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; <s:if test='%{tmsExpType=="T"}'>

								<input type="button" value="View Booking Details" class="token"
									onclick="showBookingDtls(<s:property value="bDtlAppId" />,<s:property value="bDtlAppCode" />,<s:property value="bDtlEmpId" />,<s:property value="bDtlAppDate" />,<s:property value="bDtlInitrId" />)"
									align="top">
							</s:if></td>

							-->
							<script>
			        function showBookingDtls(appId,appCode,empId,appDate,initId){	          		
	           		win=window.open('','win','top=260,left=250,width=900,height=600,scrollbars=yes,status=no,resizable=no');
					document.getElementById("paraFrm").target="win";
					document.getElementById("paraFrm").action="TravelApplication_bookingDtls.action?applicationId="+appId+"&empApplId="+appCode+"&status=FI&empId="+empId+"&applDate="+appDate+"&iniEmpId="+initId+"&dtlsType=SCH&userType=SCH&dtlsFor=CLAIM";
					document.getElementById("paraFrm").submit();
					document.getElementById("paraFrm").target="main";
	                }
	                </script>
						</tr>
						<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvlClm.trvlClmReqName"
									id="trvlClm.trvlClmReqName1" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.trvlClmReqName")%></label>:</td>
								<td width="25%"><s:label name="clmTrvlRqstName"
									theme="simple" value="%{clmTrvlRqstName}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvlClm.Purpose" id="trvlClm.Purpose"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.Purpose")%></label>:</td>
								<td width="25%"><s:label name="clmPurpose" theme="simple"
									value="%{clmPurpose}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvlClm.TrvlType" id="trvlClm.TrvlType"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.TrvlType")%></label>:</td>
								<td width="25%"><s:label name="clmTrvlType" theme="simple"
									value="%{clmTrvlType}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvlClm.AdvAmtTaken" id="trvlClm.AdvAmtTaken"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.AdvAmtTaken")%></label>:</td>
								<td width="25%" nowrap="nowrap">
									<s:property value="currencyEmployeeAdvance"/><s:hidden name="currencyEmployeeAdvance"/> 
									<s:label name="clmAdvance" theme="simple" value="%{clmAdvance}" />
								</td>
							</tr>
							<tr>
								<td width="20%"><label class="set" name="Project"
									id="Project" ondblclick="callShowDiv(this);"><%=label.get("Project")%></label>
								:</td>
								<td width="25%"><s:label name="project" theme="simple"
									value="%{projectId}" /></td>

								<td width="20%"><label class="set" name="customer"
									id="customer" ondblclick="callShowDiv(this);"><%=label.get("customer")%></label>
								:</td>
								<td width="25%"><s:label name="customerName" theme="simple"
									value="%{customerId}" /></td>

							</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="clmApplStatus=='CL'">
			<tr>
				<td width="100%" colspan="4">
				<table width="100%" align="center" class="formbg" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><strong>Payment Details</strong></td>
					</tr>
					<tr>
						<td width="100%" colspan="4">
						<table width="100%">
							<tr>
								<td width="20%" class="formtext" height="22">Total
								Disbursement Amount:</td>
								<td width="25%"><s:label name="totDisbAmt" theme="simple"
									value="%{totDisbAmt}" /></td>
								<td width="20%" class="formtext"></td>
								<td width="25%"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="5" width="100%">
				<table width="100%" align="center" class="formbg" cellpadding="0"
					cellspacing="0" "
					border="0">
					<tr>
						<td width="100%" colspan="8">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td class="formth" colspan="1" width="42" nowrap="nowrap"><strong>Sr
								No</strong></td>
								<td class="formth" colspan="1" width="15%"><strong>Date</strong></td>
								<td class="formth" colspan="1" width="25%"><strong>Mode
								of Payment</strong></td>
								<td class="formth" colspan="1" width="20%"><strong>Amount</strong></td>
								<td class="formth" colspan="1" width="35%"><strong>Comment</strong></td>
							<tr>
								<%!int m = 0;%>
								<%
									int n = 1;
								%>
								<s:iterator value="payDtls">
									<tr>
										<td class="sortableTD"><%=n%></td>
										<td class="sortableTD"><s:property value="payDate" />&nbsp;</td>
										<td class="sortableTD"><s:property value="payMode" />&nbsp;</td>
										<td class="sortableTD" align="right"><s:property
											value="payAmt" />&nbsp;</td>
										<td class="sortableTD"><textarea rows="2" cols="20"
											name="payCmt<%=n %>" id="paraFrm_payCmt<%=n %>"
											readonly="readonly"><s:property value="payCmt" /></textarea>
										&nbsp; <img src="../pages/images/zoomin.gif" height="12"
											align="bottom" width="12" theme="simple"
											onclick="javascript:callWindow('paraFrm_payCmt<%=n %>','trvlClm.partclrs','readonly','paraFrm_descCntPayCmt<%=n%>','500');">
										<input type="hidden" name="descCntPayCmt<%=n%>"
											id="paraFrm_descCntPayCmt<%=n%>"></td>
									</tr>
									<%
										n++;
									%>
								</s:iterator>
								<%
									m = n;
								%>
							
						</table>
						</td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="6" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="4" align="right" width="38%"><strong><label
								class="set" name="trvlClm.Total" id="trvlClm.Total"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.Total")%></label>Amount
							:</strong></td>
							<td colspan="1" width="20%" align="right"><s:hidden
								value="totPayAmt" />&nbsp;</td>
							<td colspan="3" width="37%" align="right">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" width="38%" align="right"><strong>
							Balance Amount :</strong></td>
							<td colspan="1" width="20%" align="right"><s:property
								value="balPayAmt" />&nbsp;</td>
							<td colspan="3" width="37%" align="right">&nbsp;</td>
						</tr>
					</s:else>
				</table>
				</td>
			</tr>
			<s:if test='%{clmAppFlag=="Y"}'>
				<tr>
					<td>
					<table width="100%" align="center" theme="simple">
						<tr>
							<s:if test="clmApplStatus=='CL'">
								<td width="78%" align="left">
								
								<s:submit
									value=" Return to list " theme="simple"
									onclick=" callBackCldApp();" cssClass="token" /></td>
							</s:if>
							<s:else>
								<td width="78%" align="left">
								
								<s:submit
									value=" Return to list " theme="simple"
									onclick=" callBackApp();" cssClass="token" /></td>
							</s:else>
							<td width="22%" align="right"></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:if>
		</s:if>
		<s:else>
		
		<!-- TAVEL RATINGS SECTION  BEGINS-->
		<%
			int deskVal = 1;
			int hotelVal = 1;
			int hotelNameVal = 1;
		%>
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="20%" colspan="1"><b>Travel Desk Ratings</b></td>
					<td colspan="2">
					<div id="paraFrm_vote" style="font-family:tahoma; color:green;"></div>
					</td>
				</tr>
				<tr id="ratingsTable">
					<td colspan="4">
					<table width="100%" class="formbg" border="0">
						<%!int totalCount = 0;%>

						<s:iterator value="travelRatingParameterList">
							<tr>
								<td width="20%"><s:hidden name="deskRatingIdItt"
									id="paraFrm_deskRatingIdItt<%=deskVal%>" /> <input
									type="hidden" name="deskRatingItt"
									id="paraFrm_deskRatingItt<%=deskVal%>"
									value='<s:property value="deskRatingItt"/>' /> 
									<input type="hidden" name="deskRatingNameItt"
									 id="paraFrm_deskRatingNameItt<%=deskVal%>"
									value='<s:property value="deskRatingNameItt"/>' />
									
									<s:property	value="deskRatingNameItt" /> :</td>
								<td colspan="3">
									<img src="../pages/images/starGrey.png"	class="iconImage"					
									id="paraFrm_deskRatingIdItt<%=deskVal%>1" /> 
									<img src="../pages/images/starGrey.png" class="iconImage"
									id="paraFrm_deskRatingIdItt<%=deskVal%>2" /> 
									<img src="../pages/images/starGrey.png" class="iconImage"
									id="paraFrm_deskRatingIdItt<%=deskVal%>3" />
									<img src="../pages/images/starGrey.png" class="iconImage"
									id="paraFrm_deskRatingIdItt<%=deskVal%>4" />
									<img src="../pages/images/starGrey.png" class="iconImage"
									id="paraFrm_deskRatingIdItt<%=deskVal%>5" />
								</td>
									
								<%
								deskVal++;
								%>

							</tr>
						</s:iterator>
						<%
						totalCount = deskVal;
						%>
					</table>
					</td>
				</tr>
				
				<s:if test="showHotelRatingFlag">
				<tr>
					<td width="20%"><b>Hotel Ratings</b></td>
					<td colspan="2"><div id="paraFrm_Hotelvote" style="font-family:tahoma; color:green;"></div></td>
				</tr>
				<tr id="ratingsHotelTable">
					<td colspan="4">
					<table width="100%" class="formbg" border="0">
						<s:iterator value="hotelNameList">
							<tr>
								<td><s:hidden name="hotelIdItt"
									id="paraFrm_hotelIdItt<%=hotelNameVal%>" /> 
									<input type="hidden" name="hotelNameItt"
									 id="paraFrm_hotelNameItt<%=hotelNameVal%>"
									value='<s:property value="hotelNameItt"/>' />
									<strong>Hotel Name  : &nbsp;<s:property	value="hotelNameItt"
									 /></strong>
								
								<%!int totalHotelCount = 0;%>
								
								<table width="100%" border="0">
									<s:iterator value="hotelRatingParameterList">
										<tr>
											<td width="20%"><s:hidden name="hotelRatingIdItt"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>" /> <input
												type="hidden" name="hotelRatingItt"
												id="paraFrm_hotelRatingItt<%=hotelVal%>"
												value='<s:property value="hotelRatingItt"/>' /> 
												<input type="hidden" name="hotelRatingNameItt"
									 			id="paraFrm_hotelRatingNameItt<%=hotelVal%>"
												value='<s:property value="hotelRatingNameItt"/>' />
												<s:property	value="hotelRatingNameItt" /> :</td>
											<td colspan="3">
												<img src="../pages/images/starGrey.png"	class="iconImage"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>1" />
												<img src="../pages/images/starGrey.png" class="iconImage"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>2" /> 
												<img src="../pages/images/starGrey.png" class="iconImage"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>3" />
												<img src="../pages/images/starGrey.png" class="iconImage"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>4" />
												<img src="../pages/images/starGrey.png" class="iconImage"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>5" /></td>
											<%
											hotelVal++;
											%>

										</tr>
									</s:iterator>
								</table>
								</td>
								<%
								totalHotelCount = hotelVal;
								%>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<!-- TAVEL RATINGS SECTION  ENDS-->
		<!-- TOUR REPORT SECTION BEGINS -->
				<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="4"><b>Tour Report</b> <font color="red">*</font>:</td>
				</tr>
				<tr id="tourTable">
					<td colspan="4">
					<table class="formbg" border="0" width="100%">
						<tr>
							<td width="30%"><b><label class="set" name="tourDesc"
								id="tourDesc" ondblclick="callShowDiv(this);"><%=label.get("tourDesc")%></label>
							<font color="red">*</font>:</b></td>
							<td colspan="3"><s:property value="tourComments"/></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
					<table class="formbg" border="0" width="100%">
						<tr>
							<td width="100%"><b><label class="set" name="action"
								id="action" ondblclick="callShowDiv(this);"><%=label.get("action")%></label>
							<font color="red">*</font></b></td>
						</tr>
						<tr>
							<td width="100%" colspan="4">
							<table id="followUpTable" width="100%" border="0" cellpadding="1" cellspacing="1" >
								<tr>
									<td class="formth"><b><label class="set" name="action"
										id="action1" ondblclick="callShowDiv(this);"><%=label.get("action")%></label></b>
									<font color="red">*</font>
									</td>
									<td class="formth"><b><label class="set"
										name="responsiblePerson" id="responsiblePerson1"
										ondblclick="callShowDiv(this);"><%=label.get("responsiblePerson")%></label></b>
									<font color="red">*</font>
									</td>
									<td class="formth"><b><label class="set" name="targetDt"
										id="targetDt1" ondblclick="callShowDiv(this);"><%=label.get("targetDt")%></label></b>
									<font color="red">*</font>
									</td>
								</tr>
								<%
								int counter = 0;
								%>
								<s:iterator value="followUpActionList">
									<tr>
										<td class="sortableTD"><s:property value="followUpCommentsItt"/></td>
										<td class="sortableTD" align="center">
										<s:hidden value="responsibleEmpIdItt"/><s:hidden value="responsibleEmpTokenItt"/>
										<s:property value="responsibleEmpItt"/></td>
										<td class="sortableTD" align="center"><s:property value="targetDateItt"/></td>
									</tr>
									<%counter++;%>
								</s:iterator>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- TOUR REPORT SECTION ENDS -->
			<tr>
				<td colspan="8" width="100%">
				<table width="100%" align="center" class="formbg" theme="simple"
					border="0">
					<s:hidden name="expDtlId" />
					<s:hidden name="expExpAppId" />
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
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.srNo")%></label></strong></td>
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
								
								<td class="formth" colspan="1" width="10%">
									<strong>
										<label class="set" name="trvlClm.isPolicyViolated" id="trvlClm.isPolicyViolated"
									ondblclick="callShowDiv(this);"><%=label.get("trvlClm.isPolicyViolated")%></label>
									</strong>
								</td>	
								
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

								
									<td class="sortableTD" ><s:property
										value="expParticlrs" />

									<input type="hidden" name="descCntAppl<%=k%>"
										id="paraFrm_descCntAppl<%=k%>">&nbsp;</td>
									<td class="sortableTD" align="right" nowrap="nowrap"><s:property
										value="expElgblAmt" />&nbsp;</td>
									<td class="sortableTD" align="right" nowrap="nowrap">
										<s:property value="expExpAmt" />
										<s:property value="currencyExpenseAmt" />
									&nbsp;</td>
									
									<td class="sortableTD" align="left" nowrap="nowrap"><s:property value="isPolicyViolated" />&nbsp;</td>	
									
									<td class="sortableTD" nowrap="nowrap"><s:property value="expIsProof" />&nbsp;</td>
									<s:if test="(expIsProof == 'NO')">


										<td class="sortableTD" nowrap="nowrap"><s:hidden name="expProofPath" /> &nbsp;</td>

									</s:if>
									<s:else>
										<td class="sortableTD" ><s:hidden name="expProofPath" /><s:iterator	value="expProofPathList">
										<a href="#" onclick="return showproofname('<s:property value="expProofPath" />');">
										<s:property value="expProofPath" /><br> </a>&nbsp;</s:iterator>
										</td>
									</s:else>
								</tr>
								<%
									k++;
								%>
							</s:iterator>
							<%
								i = k;
							%>
						</table>
						</td>
					</tr>

					<s:if test="noData">
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>

					<s:else>
						<tr>
							<td colspan="1" align="right" width="40%" nowrap="nowrap" colspan="4"><strong><label
								class="set" name="trvlClm.Total" id="trvlClm.Total"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.Total")%></label>
							:</strong>&nbsp;</td>
							<td colspan="1" width="13%" align="right" nowrap="nowrap"><s:hidden
								name="totElgblAmt" />&nbsp;</td>
							<td colspan="1" align="right" width="14%" nowrap="nowrap"> 
								<s:property value="totExpAmt" />
								<s:property value="totalCurrencyExpense" />
								&nbsp;</td>
							<td colspan="1" align="right" width="14%" nowrap="nowrap"></td>
						</tr>
					</s:else>
				</table>
				</td>
			</tr>

		 
			<s:if test="clmApplStatus=='CL'">
			</s:if>
			<s:else>
				<tr>
					<td width="25%"><strong>Applicant Comments </strong></td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
					<table width="100%" align="center" class="formbg" cellpadding="0"
						cellspacing="0" border="0">
						<tr>
							<td width="25%" class="formtext" height="22" colspan="1"
								valign="top"><label class="set" name="trvlClm.applCmts"
								id="trvlClm.applCmts" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.applCmts")%>:</label></td>
							<td width="75%" colspan="3"><s:property value="clmAppCmts" />
							<input type="hidden" name="descCntCmtA" id="paraFrm_descCntCmtA">
							&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</s:else>
			<s:if test='%{clmAppFlag=="Y"}'>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="78%" align="left">
							<s:submit value=" Report " theme="simple"
								onclick=" report();" cssClass="token" />
							<s:submit
								value=" Return to list " theme="simple"
								onclick=" callBackApp();" cssClass="token" /></td>
							<td width="22%" align="right"></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="78%" align="left"><s:if test='%{navStatus=="P"}'>
								<input type="button"  value="Approve "  
									  onclick=" return callFun('A');" class="token" />
									  <!-- 
									  
									  	<input type="button"    value="Reject"
									theme="simple" onclick=" return callFun('R');" class="token" />
							
									   -->
								<input type="button"     value="Send Back "
									theme="simple" onclick=" return callFun('B');" class="token" />
							</s:if> <input type="button"  value=" Return to list " theme="simple"   
								onclick=" callBack();" class="token" />
								</td>
							<td width="22%" align="right"></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:else>
			<tr>
				<td><script>
			function checkOnClick(radio)
			{
			     if(radio=='elgblAmtRadio')
			     {
			        document.getElementById('expAmtRadio').checked=false;
			        document.getElementById('otherRadio').checked=false;
			        document.getElementById('paraFrm_otherAmt').value="";
			        document.getElementById('paraFrm_otherAmt').readOnly=true;
			     }
			     else if(radio=='expAmtRadio')
			     {
			      document.getElementById('elgblAmtRadio').checked=false;
			      document.getElementById('otherRadio').checked=false;
			      document.getElementById('paraFrm_otherAmt').value="";
			        document.getElementById('paraFrm_otherAmt').readOnly=true;
			     }
			     
			     else if(radio=='otherRadio')
			     {
			      document.getElementById('elgblAmtRadio').checked=false;
			      document.getElementById('expAmtRadio').checked=false;
			      document.getElementById('paraFrm_otherAmt').readOnly=false;
			     }
			
			    document.getElementById('paraFrm_apprvdAmt').value=document.getElementById(radio).value;
			
			}
			
			function setApprdAmt()
			{
			   document.getElementById('paraFrm_apprvdAmt').value=document.getElementById('paraFrm_otherAmt').value;
			
			}		
		   
		  </script></td>
			</tr>
		</s:else>


	</table>
	
	<s:hidden name="hiddenApplicationCode" />
	<s:hidden name="showHotelRatingFlag" />
<s:hidden name="level" />
 
</s:form>

<script>

	
	
	function showRecord(fileName){
		document.getElementById('paraFrm').target ="_blank";
		document.getElementById('paraFrm').action = "TravelMonitor_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ="main";
	}

	function callFun(stat){
		document.getElementById('paraFrm_statusSave').value=stat;
		document.getElementById('paraFrm_checkStatus').value=stat;
		var disMsg="";
		if(stat=="A"){
			disMsg="Approve";
		}else if(stat=="B"){
			disMsg="send back";
		}else if(stat=="R"){
			disMsg="Reject";
		}else{ 
			disMsg="submit"
		}		
			 						
		var confr=confirm('Do you want to '+disMsg+' the application?');
			if(confr){
				document.getElementById('paraFrm').target ="main";	 		
	  			document.getElementById('paraFrm').action = "TravelClmAppvr_approveRejectSendBackTravelClaimApp.action";
				document.getElementById('paraFrm').submit();
				return true;		
			}else{
				return false;	
			}
	}//end of callFun
					
					
		   function checkOtherAmt()
		   {
		   var expenseAmt=document.getElementById('expAmtRadio').value;
		   var otherAmt=document.getElementById('paraFrm_otherAmt').value;		   
		   if(eval(otherAmt)>eval(expenseAmt))
		   {
		   alert("Approved amount should not be greater than Expense Amount ");
		   document.getElementById('paraFrm_otherAmt').focus();
		   return false;
		   }	
		   else
		   return true;
		   }
					
					
</script>

<script>
		loadStars();
		
 		function callBack()
		{    	
        var status=document.getElementById('paraFrm_navStatus').value;		
        
        document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
	 	document.getElementById('paraFrm').action = "TravelClmAppvr_callStatus.action?status="+status;  
		}
	
		document.getElementById('paraFrm').submit();  
		}
		
		function callBackApp()
		{    	
		 document.getElementById('paraFrm').action="TravelClaim_input.action";
  		 document.getElementById('paraFrm').submit();
        var status=document.getElementById('paraFrm_navStatus').value;				
		document.getElementById('paraFrm').action = "TravelClaim_getApprovedList.action";  
		document.getElementById('paraFrm').submit();  
		}
		
		
		function callBackCldApp()
		{    	
		 document.getElementById('paraFrm').action="TravelClaim_input.action";
  		 document.getElementById('paraFrm').submit();
        var status=document.getElementById('paraFrm_navStatus').value;				
		document.getElementById('paraFrm').action = "TravelClaim_getClosedList.action";  
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
    
    function showproofname(fileName)
	{
	 	document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "TravelClmAppvr_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	} 
	
	function report() {
  		document.getElementById('paraFrm').action="TravelClaim_report.action";
   		document.getElementById('paraFrm').submit();
  	}
  	
  	/* STAR RATING CODE BEGINS*/
	
	function loadStars() {
	 
		star1 = new Image();
   		star1.src = "../pages/images/starGrey.png";
   		star2 = new Image();
   		star2.src= "../pages/images/starYellow1.png";
   		
   		var totalParameters='<%=totalCount%>';
   		var totalHotelParameters='<%=totalHotelCount%>';
   		
   		for (ii=1;ii<totalParameters;ii++){
   		
   		var hiddenValue=document.getElementById('paraFrm_deskRatingItt'+ii).value;
   		
   		for (i=1;i<=5;i++){
   				document.getElementById('paraFrm_deskRatingIdItt'+ii+''+i).src= star1.src;
   			}
   		for (i=1;i<=hiddenValue;i++){
   				document.getElementById('paraFrm_deskRatingIdItt'+ii+''+i).src= star2.src;
   			}
   		}
   		
   		if(document.getElementById('paraFrm_showHotelRatingFlag').value=='true') {
   			for (ii=1;ii<totalHotelParameters;ii++){
   				var hiddenHotelValue=document.getElementById('paraFrm_hotelRatingItt'+ii).value;
   				for (i=1;i<=5;i++){
   					document.getElementById('paraFrm_hotelRatingIdItt'+ii+''+i).src= star1.src;
   				}
   				for (i=1;i<=hiddenHotelValue;i++){
   					document.getElementById('paraFrm_hotelRatingIdItt'+ii+''+i).src= star2.src;
   				}
   			}
   		}
	}
	
	function viewSplitExpenditure(){
		window.open('','new','top=50,left=300,width=400,height=200,scrollbars=no,status=no,resizable=no');
		document.getElementById("paraFrm").target="new";
	 	document.getElementById("paraFrm").action='TravelClmAppvr_viewSplittedExpenditure.action'; 
	  	document.getElementById("paraFrm").submit();
	}   
	
</script>
