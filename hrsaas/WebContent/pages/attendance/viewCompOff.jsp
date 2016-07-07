<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CompOffApp" method="post" id="paraFrm">
	<table width="100%" class="formbg">
		<s:hidden name="cvoucher.isApprove" /><s:hidden name="checkEdit" />
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Compensatory Off Application</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" cellspacing="2" class="formbg">
					<s:hidden name="vCode" /><s:hidden name="level" />
					<tr>
						<td width="15%" colspan="1" class="formtext">
							Employee Name :<font color="red">*</font>
						</td>
						<td width="85%" colspan="3">
							<s:textfield name="empToken" size="10" theme="simple" readonly="true" />
							<s:textfield name="eName" size="65" theme="simple" readonly="true" />
						</td>
					</tr>
					<tr>
						<td width="15%" class="formtext">Department:</td>
						<td width="25%"><s:textfield name="dept" theme="simple" size="25" readonly="true" /></td>
					</tr>
					<tr>
						<td width="15%" class="formtext">Designation:</td>
						<td width="25%"><s:textfield name="desg" theme="simple" size="25" readonly="true" /></td>
					</tr>		
					<tr>
						<td width="15%" class="formtext">Date:<font color="red">*</font></td>
						<td width="25%">
							<s:textfield name="aDate" size="25" readonly="true" theme="simple" maxlength="10" />
						</td>
						<td width="25%" class="formtext">
							Status:&nbsp;&nbsp;&nbsp;
							<s:select theme="simple" name="status" cssStyle="width:130" disabled="true" 
							list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table class="formbg" width="100%">
					<tr><td colspan="5" class="formhead"><strong class="forminnerhead">CompOff Details</strong></td></tr>
					<tr>
						<td class="formth" width="5%">Sr No</td>
						<td class="formth" width="65%">Purpose</td>
						<td class="formth" width="10%">Date</td>
						<td class="formth" width="10%">Start Time</td>
						<td class="formth" width="10%">End Time</td>
					</tr>
					<% int srNo = 0; %>		
					<s:iterator value="compList">
						<tr>		
							<td class="sortableTD"><%=++srNo%></td>
							<td class="sortableTD"><s:property value="hprojName" /></td>
							<td class="sortableTD" align="center"><s:property value="hDate" /></td>
							<td class="sortableTD" align="center"><s:property value="hsTime" /></td>
							<td class="sortableTD" align="center">
								<s:property value="heTime" /><s:hidden name="serialNo" /><s:hidden name="hsTime" />
								<s:hidden name="heTime" /><s:hidden name="hprojName" /><s:hidden name="hDate" />
							</td>
						</tr>		
					</s:iterator>		
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<s:if test="commentFlag"></s:if>
				<s:else>
					<table class="formbg" width="100%">
						<tr><td colspan="5" class="formhead"><strong class="forminnerhead">Approver Details</strong></td></tr>
						<tr>
							<td width="5%" class="formth">Sr.No.</td>
							<td width="25%" class="formth">Approver Name</td>
							<td width="10%" class="formth">Approved Date</td>
							<td width="60%" class="formth">Comments</td>		
						</tr>
						<% int j = 0; %>
						<s:iterator value="approveList">
							<tr>		
								<td class="sortableTD"><%=++j%></td>
								<td class="sortableTD"><s:property value="apprName" /></td>
								<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
								<td class="sortableTD"><s:property value="apprComments" /></td>
							</tr>		
						</s:iterator>
					</table>
				</s:else>
			</td>
		</tr>
	</table>
	<br/>
</s:form>