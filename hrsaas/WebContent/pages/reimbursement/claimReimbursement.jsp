<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReimbursementClmAppl" validate="true"
	id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Claim
					Reimbursement</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="20%" colspan="1"><label class="set" id="employee1"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					<font color="red">*</font> :</td>
					<td width="80%" colspan="3"><s:hidden name="empId"
						theme="simple" />  <s:textfield theme="simple"
						readonly="true" size="10" name="empToken" /> <s:textfield
						label="%{getText('empName')}" theme="simple" size="77"
						readonly="true" name="empName" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" height="22"><s:textfield theme="simple"
						size="20" readonly="true" name="designation" /></td>
					<td width="20%" colspan="1"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="1" height="22"><s:textfield
						theme="simple" size="30" readonly="true" name="branch" /></td>


				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="grade" name="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
					<td width="30%" colspan="1"><s:textfield theme="simple"
						size="20" readonly="true" name="grade" /></td>
					<td width="20%" colspan="1"><label class="set"
						id="claim.date4" name="claim.date" ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label><font
						color="red">*</font> :</td>
					<td width="30%" colspan="1"><s:textfield theme="simple"
						name="claimDate" onkeypress="return numbersWithHiphen();"
						size="30" /><s:a
						href="javascript:NewCal('paraFrm_claimDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>


				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="division" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="30%" colspan="1">
					<s:hidden name="divisionCode" />
					<s:textfield theme="simple"
						size="20" readonly="true" name="division" />	<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16"
									onclick="javascript:callsF9(500,325,'ReimbursementClmAppl_f9division.action');"></td>
					<td width="20%" colspan="1"></td>
					<td width="30%" colspan="1"></td>


				</tr>

			</table>
			</td>
		</tr>
	 
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="4" class="formhead"><strong class="forminnerhead">
					<label class="set" id="reimbursementDtl" name="reimbursementDtl"
						ondblclick="callShowDiv(this);"><%=label.get("reimbursementDtl")%></label></strong>
					</td>
				</tr>
			 
					<tr>
						<td width="20%" colspan="1"><label class="set" id="reimbursementType1"
							name="reimbursementType" ondblclick="callShowDiv(this);"><%=label.get("reimbursementType")%></label><font
							color="red">*</font>:</td>
						<td width="30%" colspan="1">
						<s:textfield  
							name="reimbursementCrediCode" size="30"
									align="absmiddle" width="16" /><img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16"
									onclick="javascript:callsF9(500,325,'ReimbursementClmAppl_f9creditHead.action');">
									  </td>
						<td width="20%" colspan="1"><label class="set"
							id="expense.date" name="expense.date"
							ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label><font
							color="red">*</font>:</td>
						<td width="30%" colspan="1"> <s:textfield theme="simple"
						name="claimDate" onkeypress="return numbersWithHiphen();"
						size="30" /><s:a
						href="javascript:NewCal('paraFrm_claimDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>
					</tr>
					<tr>
						<td width="20%" colspan="1" nowrap="nowrap"><label
							class="set" id="claim.amount" name="claim.amount"
							ondblclick="callShowDiv(this);"><%=label.get("claim.amount")%></label>
						 <font color="red">*</font>:</td>
						<td width="30%" colspan="1"><s:textfield size="12"
							name="application.startTime"
						 
							maxlength="5" onkeypress="return numbersWithColon();" /></td>
						<td width="19%" colspan="1" nowrap="nowrap"><label
							class="set" id="proof" name="proof"
							ondblclick="callShowDiv(this);"><%=label.get("proof")%></label>
						  <font color="red">*</font>:</td>
						<td colspan="3"><s:textfield size="12"
							name="application.endTime"
							 
							maxlength="5" onkeypress="return numbersWithColon();" /></td>
					</tr>

			 
					<tr>
						<td colspan="4" align="center"><input type="button"
							class="add" value="   Add" onclick="return callAdd();" /> <input
							type="button" class="reset" value="  Clear"
							onclick="return callClear();" /></td>
					</tr>
		 
				<tr>
					<td colspan="4"> 
					<table width="100%">
							<tr>
								<td class="formth" width="05%"><label id="sno" name="sno"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
								<td class="formth" width="15%" align="center"><label
									id="reimbursementType2" name="reimbursementType" ondblclick="callShowDiv(this);"><%=label.get("reimbursementType")%></label></td>
								<td class="formth" width="15%"><label id="expense.date1"
									name="expense.date" ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label></td>
								<td class="formth" width="10%" align="center"><label
									id="claim.amount1" name="claim.amount" ondblclick="callShowDiv(this);"><%=label.get("claim.amount")%></label></td>
								<td class="formth" width="35%"><label id="proof1"
									name="proof" ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></td>
									<td class="formth" width="10%" align="center">Edit/Delete</td>
								 
							</tr>
							<%
							int srNo = 0;
							%>
							<s:iterator value="list">
								<tr>
									<td class="sortableTD" width="5%"><%=++srNo%></td>
									<td class="sortableTD" width="15%" nowrap="nowrap"
										align="center"><s:property value="hDate" /></td>
									<td class="sortableTD" width="15%" nowrap="nowrap"
										align="center"><s:property value="hsType" /> <s:hidden
										name="hsType" /></td>
									<td class="sortableTD" width="10%" nowrap="nowrap"
										align="center"><s:property value="hsTime" /></td>
									<td class="sortableTD" width="10%" nowrap="nowrap"
										align="center"><s:property value="heTime" /> <s:hidden
										name="hprojName" /><s:hidden name="hDate"
										id='<%="hDate"+srNo%>' /> <s:hidden name="hDay"
										id='<%="hDay"+srNo%>' /><s:hidden name="hsTime" /><s:hidden
										name="heTime" /><s:hidden name="serialNo" /></td>
									<td class="sortableTD" width="35%" align="left"><s:property
										value="hprojName" /></td>
									<td class="sortableTD" width="10%" nowrap="nowrap"> 
										<input type="button" class="edit" value="Edit"
											onclick="return setEdit('<%=srNo-1%>','<s:property value="hprojName" />','<s:property 	value="hDate" />','<s:property value="hsType" />',
												'<s:property value="hsTime" />','<s:property value="heTime" />','<s:property value="hidMon" />','<s:property value="hyear" />');" />
										<input type="button" class="delete" value="  Delete"
											onclick="return setSerialNo(<%=(srNo-1)%>);" />
								  </td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- EXTRA WORK DETAILS LIST TABLE ENDS -->
		
	</table>

</s:form>

