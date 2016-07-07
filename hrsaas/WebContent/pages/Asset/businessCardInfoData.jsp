<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="BusinessCardInfo" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Request for Business Card Application Form </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font>Indicates
					Required</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="0"
						class="formbg">
						<tr>
							<td width="50%" nowrap="nowrap"><strong>Manager:</strong></td>
							<td colspan="2" nowrap="nowrap"></td>
							<td width="11%" nowrap="nowrap"><strong>Keep
							Informed To : </strong></td>
							<td width="13%"><s:if test="%{keepInfoFlag}">
								<s:hidden name="kiEmployeeId" />
								<s:hidden name="kiEmployeeToken" />
								<s:textfield name="kiEmployeeName" readonly="true" />
							</s:if></td>
							<td width="5%" colspan="1"><s:if test="%{keepInfoFlag}">
								<img src="../pages/common/css/default/images/search2.gif"
									class="iconImage" width="16" height="15"
									onclick="javascript:getKeepInformedEmp();" />
							</s:if></td>
							<td width="15%"><s:if test="%{keepInfoFlag}">
								<s:submit name="" value=" Add" cssClass=" add"
									action="BusinessCardInfo_addKeepInformedEmpList"
									onclick="return callKeepInformed();" />
							</s:if></td>
						</tr>
						<!-- APPROVER LIST  TABLE  STARTS -->
						<tr valign="top">
							<td colspan="3" rowspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<%int y = 1;%>
									<%!int z = 0;%>
									<s:iterator value="approverList">
										<tr>
											<td><s:hidden name="apprNameIt" /> <STRONG> <s:property
												value="srNoIt" /></STRONG> <s:property value="apprNameIt" /></td>
										</tr>
										<%y++;%>
									</s:iterator>
									<%
										z = y;
									%>
								</tr>
							</table>
							</td>
						</tr>

						<!-- APPROVER LIST  TABLE  ENDS -->
						<!-- KEEP INFORMED LIST TABLE  STARTS -->
						<tr valign="top">
							<td colspan="3" rowspan="5">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<%
									int counter11 = 0;
									int counter2 = 0;
								%>
								<s:iterator value="keepInformedList">
									<tr>
										<td width="80%"><%=++counter11%> <s:hidden
											name="serialNoIt" /> <s:hidden name="keepInformedEmpNameIt" />
										<s:property value="keepInformedEmpNameIt" /> <s:hidden
											name="keepInformedEmpIdIt" /></td>
										<td width="20%"><s:if test="%{keepInfoFlag}">
											<a href="#" onclick="callForRemove(<%=counter11%>);">Remove</a>
										</s:if></td>
									</tr>
									<%
										counter2 = counter11;
									%>
								</s:iterator>
								<%
									counter2 = 0;
								%>
							</table>
							</td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- 36 line tr close -->
				<!-- KEEP INFORMED LIST TABLE  ENDS -->
				<tr>
					<td><label class="set" id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td><s:textfield name="empToken" size="10" value="%{empToken}"
						theme="simple" readonly="true" /> <s:textfield name="empName"
						size="50" value="%{empName}" theme="simple" readonly="true" /></td>
					<td><label class="set" id="desig1" name="desig1"
						onDblClick="callShowDiv(this);"><%=label.get("desig1")%></label>:</td>
					<td><s:textfield size="25" name="desig" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="dept1" name="dept1"
						onDblClick="callShowDiv(this);"><%=label.get("dept1")%></label>:</td>
					<td><s:textfield size="25" name="dept" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="branch1" name="branch1"
						onDblClick="callShowDiv(this);"><%=label.get("branch1")%></label>:</td>
					<td><s:textfield size="25" name="branch" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="pinCode1" name="pinCode1"
						onDblClick="callShowDiv(this);"><%=label.get("pinCode1")%></label>:</td>
					<td><s:textfield size="25" name="pinCode" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="location1" name="location1"
						onDblClick="callShowDiv(this);"><%=label.get("location1")%></label>:</td>
					<td><s:textfield size="25" name="location" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="officeNum1" name="officeNum1"
						onDblClick="callShowDiv(this);"><%=label.get("officeNum1")%></label>:</td>
					<td><s:textfield size="25" name="officeNum" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="mobile1" name="mobile1"
						onDblClick="callShowDiv(this);"><%=label.get("mobile1")%></label>:</td>
					<td><s:textfield size="25" name="mobile" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="fax" name="fax"
						onDblClick="callShowDiv(this);"><%=label.get("fax")%></label> :</td>
					<td><s:textfield size="25" name="faxNum" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="emailId1" name="emailId1"
						onDblClick="callShowDiv(this);"><%=label.get("emailId1")%></label>:</td>
					<td><s:textfield size="25" name="emailId" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
var fieldName  = [ "paraFrm_asstHdType1","paraFrm_assetSubType","paraFrm_assetRequired"];
var lableName  = ["asset","assetSubType","assetQtyReq"];
var types  = ["select","select","enter"];
var fieldName1  = [ "paraFrm_empName","paraFrm_assignDate1"];
var lableName1  = ["employee","assetAppDate"];
var types1  = ["select","enter"];

</script>