<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.HashMap"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeavePolicy" method="post" name="LeavePolicy"
	id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<s:hidden name="policyCancel" />
					<s:hidden name="excepCancel" />
					<s:hidden name="editPolicies" />
					<s:hidden name="editExceptions" />
					<s:hidden name="hiddenPenelty" />
					<s:hidden name="addRuleField" />
					<s:hidden name="unplaneFlag" />
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Leave
					Policy</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><s:hidden name="f9Flag" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						action="LeavePolicy_save" value=" Save"
						onclick="return formValidate();" /> <s:submit cssClass="reset"
						action="LeavePolicy_reset" value=" Reset" /> <s:submit
						cssClass="delete" action="LeavePolicy_delete" value=" Delete"
						onclick="return callDelete('paraFrm_policyCode');" /> <input
						type="button" class="search" value=" Search "
						onclick="javascript:callsF9(500,325,'LeavePolicy_f9action.action');" />

					</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
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
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="0">
						<tr>
							<td nowrap="nowrap"><label name="policyname" id="policyname"
								ondblclick="callShowDiv(this);"><%=label.get("policyname")%></label>
							: <font color="red">*</font></td>
							<td width="11%" nowrap="nowrap"><s:textfield
								name="policyName" value="%{policyName}" maxlength="30"
								onkeypress="return allCharacters(this);" /><s:hidden
								name="policyCode" /></td>
							<td width="6%"></td>
						</tr>
						<tr>
							<td width="15%" nowrap="nowrap"><label name="division"
								id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><span
								class="formtext"> </span><span class="style2"> : <font
								color="red"> *</font></span></td>
							<td height="22" nowrap="nowrap"><s:hidden
								name="divisionCode" /><s:textfield name="divisionName"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							<td width="6%"><s:if test="f9Flag"></s:if> <s:else>
								<img src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage"
									onclick="javascript:callsF9(500,325,'LeavePolicy_f9divisionaction.action');" />
							</s:else></td>
						</tr>
						<tr>
							<td nowrap="nowrap"><label name="leave.management.year"
								id="leave.management.year" ondblclick="callShowDiv(this);"><%=label.get("leave.management.year")%></label>
							:</td>
							<td><s:select name="fromMonth" onchange="callDate()"
								list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"></s:select>
							</td>
							<td>To:</td>
							<td width="63%"><s:select name="toMonth" disabled="true"
								list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"></s:select>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="flag">
			<tr>
				<td>
				<div id="tabnav" style="vertical-align: bottom">
				<ul>
					<li id="list1"><a href="#" id="menuid1" class="on"
						onclick="showCurrent('menuid1','first')"> <span>Leave
					Policy</span></a></li>
					<li id="list2"><a href="#" id="menuid2"
						onclick="showCurrent( 'menuid2','second')"> <span>Leaves
					Entitlement</span></a></li>

					<li id="list5"><a href="#" id="menuid5"
						onclick="showCurrent( 'menuid5','five')"> <span>Settelment
					Leaves Entitlement</span></a></li>

					<li id="list3"><a href="#" id="menuid3"
						onclick="showCurrent( 'menuid3','third')"> <span>Leaves
					Encashment</span></a></li>
					<li id="list4"><a href="#" id="menuid4"
						onclick="showCurrent( 'menuid4','fourth')"> <span>Unplanned/Unauthorized
					Leaves Setting</span></a></li>
				</ul>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><img
					src="../pages/images/recruitment/space.gif" width="5" height="4" />

				<div id="first">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					class="formbg">
					<tr>
						<td width="10%" valign="top" class="formth"><strong
							class="text_head">
						<div align="center"><label name="leaveid" id="leaveid"
							ondblclick="callShowDiv(this);"> <%=label.get("leaveid")%>
						</label></div>
						</strong></td>
						<td width="12%" valign="top" class="formth"><strong
							class="text_head"><label name="leavename" id="leavename"
							ondblclick="callShowDiv(this);"><%=label.get("leavename")%></label></strong></td>
						<td width="15%" valign="top" class="formth"><strong
							class="text_head"><label name="applicable"
							id="applicable" ondblclick="callShowDiv(this);"><%=label.get("applicable")%></label></strong></td>
						<td width="17%" valign="top" class="formth"><strong
							class="text_head"><label name="genderwiseleave"
							id="genderwiseleave" ondblclick="callShowDiv(this);"><%=label.get("genderwiseleave")%></label></strong></td>

						<td width="14%" valign="top" class="formth"><strong
							class="text_head"><label name="creditinterval"
							id="creditinterval" ondblclick="callShowDiv(this);"><%=label.get("creditinterval")%></label></strong>
						</td>												
						<td width="14%" valign="top" class="formth"><strong
							class="text_head"><label name="carryforward"
							id="carryforward" ondblclick="callShowDiv(this);"><%=label.get("carryforward")%></label></strong></td>
						<td width="16%" valign="top" class="formth"><strong
							class="text_head"> <label name="carryforwardmonth"
							id="carryforwardmonth" ondblclick="callShowDiv(this);"> <%=label.get("carryforwardmonth")%>
						</label> </strong></td>
					
						<td width="16%" valign="top" class="formth"><strong
							class="text_head"><label name="carryforward"
							id="carryforward" ondblclick="callShowDiv(this);"><%=label.get("max.leave.carried.forward")%></label></strong></td>
						<td width="14%" valign="top" class="formth"><strong
							class="text_head"><label name="negativebalupto"
							id="negativebalupto" ondblclick="callShowDiv(this);"><%=label.get("negativebalupto")%></label></strong></td>
						<td width="14%" valign="top" class="formth"><strong
							class="text_head"><label name="maxbal" id="maxbal"
							ondblclick="callShowDiv(this);"><%=label.get("maxbal")%>
						</label></strong></td>
						<td width="12%" valign="top" class="formth"><strong
							class="text_head"><label name="grantedminbal"
							id="grantedminbal" ondblclick="callShowDiv(this);"><%=label.get("grantedminbal")%></label></strong></td>
						<td width="12%" valign="top" class="formth"><strong
							class="text_head"><label name="maxLeavesToApply"
							id="maxLeavesToApply" ondblclick="callShowDiv(this);"><%=label.get("maxLeavesToApply")%></label></strong></td>
						<td width="12%" valign="top" class="formth"><strong
							class="text_head"><label name="hoildayexclude"
							id="hoildayexclude" ondblclick="callShowDiv(this);"><%=label.get("hoildayexclude")%></label></strong></td>
						<td width="15%" valign="top" class="formth"><strong
							class="text_head"><label name="weeklyoffexclude"
							id="weeklyoffexclude" ondblclick="callShowDiv(this);"><%=label.get("weeklyoffexclude")%></label></strong></td>
						<td width="14%" valign="top" class="formth" nowrap="nowrap"><strong
							class="text_head"><label name="cantcombine"
							id="cantcombine" ondblclick="callShowDiv(this);"><%=label.get("cantcombine")%></label></strong></td>
						<td width="19%" valign="top" class="formth"><strong
							class="text_head"><label name="zerobalapp"
							id="zerobalapp" ondblclick="callShowDiv(this);"><%=label.get("zerobalapp")%></label></strong></td>
						<td width="16%" valign="top" class="formth"><strong
							class="text_head"><label name="leavecredittype"
							id="leavecredittype" ondblclick="callShowDiv(this);"><%=label.get("leavecredittype")%></label></strong></td>
						<td width="16%" valign="top" class="formth"><strong
							class="text_head"><label name="halflevapp"
							id="halflevapp" ondblclick="callShowDiv(this);"><%=label.get("halflevapp")%></label></strong></td>
						<td width="16%" valign="top" class="formth"><strong
							class="text_head"><label name="paid.unpaid"
							id="paid.unpaid" ondblclick="callShowDiv(this);"><%=label.get("paid.unpaid")%></label></strong></td>
						<td width="12%" valign="top" class="formth"><strong
							class="text_head"><label name="notice.period"
							id="notice.period" ondblclick="callShowDiv(this);"><%=label.get("notice.period")%></label></strong></td>
						<td width="16%" valign="top" class="formth"><strong
							class="text_head"><label name="proof.required"
							id="proof.required" ondblclick="callShowDiv(this);"><%=label.get("proof.required")%></label></strong></td>						
					</tr>
					<%
						String[][] result = (String[][]) request.getAttribute("data");
						try {
							int i = 0;							
							for (int k = 0; k < result.length; k++) {
					%>

					<tr>
						<td width="10%">
						<div align="center"><%=String.valueOf(result[k][0])%></div>
						</td>

						<td width="14%"><%=String.valueOf(result[k][1])%></td>

						<td align="center" width="10%"><input type="checkbox"
							name="applicable" id="a<%=i%>"
							value="<%=String.valueOf(result[k][0])%>"
							onclick="checkapp(<%=i%>)" /> <input type="hidden"
							name="applicablevalue+<%=i%>" /> <%
					 		if (String.valueOf(result[k][2]).equals("Y")) {
						 %> <script>
							document.getElementById('a<%=i%>').checked =true;
						</script> <%
 							}
 						%>
						</td>

						<td width="17%" align="center"><select
							name="leaveApplicableto">
							<%	if (String.valueOf(result[k][3]).equals("B")) 
							{
						%>
							<option value="M">Male</option>
							<option value="F">Female</option>
							<option value="B" selected="selected">Both</option>
							<%
							}
							 if (String.valueOf(result[k][3]).equals("M")) 
							 {
							%>
							<option value="M" selected="selected">Male</option>
							<option value="F">Female</option>
							<option value="B">Both</option>
							<%
							}
							%>

							<%
							 if (String.valueOf(result[k][3]).equals("F")) {
							%>
							<option value="M">Male</option>
							<option value="F" selected="selected">Female</option>
							<option value="B">Both</option>
							<%
							}if (String.valueOf(result[k][3]).equals("")
									|| String.valueOf(result[k][3]).equals("null")
									|| result[k][3] == null) {
							%>
							<option value="B">Both</option>
							<option value="M">Male</option>
							<option value="F">Female</option>

							<%} %>
						</select></td>
						<td align="center" width="16%"><select name="creditInterval"
							id="creditIntervalId<%=i%>"
							onchange="return resetintervalmonth('<%=k%>');">
							<%
							if (String.valueOf(result[k][4]).equals("Mo")) {
							%>
							<option value="Mo" selected="selected">Monthly</option>
							<option value="Qu">Quarterly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}
							if (String.valueOf(result[k][4]).equals("Qu")) {
							%>
							<option value="Qu" selected="selected">Quarterly</option>
							<option value="Mo">Monthly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}							
							if (String.valueOf(result[k][4]).equals("Hy")) {
							%>
							<option value="Qu">Quarterly</option>
							<option value="Mo">Monthly</option>
							<option value="Hy" selected="selected">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}							
							if (String.valueOf(result[k][4]).equals("Ye")) {
							%>
							<option value="Qu">Quarterly</option>
							<option value="Mo">Monthly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye" selected="selected">Yearly</option>
							<%
							}							
										if (String.valueOf(result[k][4]).equals("")||String.valueOf(result[k][4]).equals("0")
										|| String.valueOf(result[k][4]).equals("null")
										|| result[k][4] == null) {
							%>
							<option value="" selected="selected">Select</option>
							<option value="Mo">Monthly</option>
							<option value="Qu">Quarterly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}
							%>
						</select></td>						
						<td align="center" width="16%"><input type="checkbox"
							name="carryForword" id="c<%=i%>"
							value="<%=String.valueOf(result[k][0])%>" /> <%
 								if (String.valueOf(result[k][5]).equals("Y")) {
 							%> <script>document.getElementById('c<%=i%>').checked =true;</script>
						<%
 								}
							 %>
						</td>
						
						<td width="12%" align="center"><input type="text"
							name="carryforwardmonth" id="carryforwardmonth<%=i%>"
							value="<%=String.valueOf(result[k][28])%>" maxlength="5"
							style="text-align: right" size="5"
							onkeypress="return numbersWithDot();" /></td>

						<!--  NEW FIELD -->
						<td width="10%" align="center"><input type="text"
							name="maxLevsToBeForward"
							value="<%=String.valueOf(result[k][6])%>" maxlength="5"
							style="text-align: right" size="3"
							onkeypress="return numbersWithDot();" /></td>
						<!--  NEW FIELD -->

						<td width="10%" align="center"><input type="text"
							name="maxLevsInMonth" value="<%=String.valueOf(result[k][7])%>"
							maxlength="5" size="10" onkeypress="return numbersWithDot();"
							style="text-align: right"></td>

						<!--  Maximum Leave Accumulation allowed up to (in Days)  -->
						<td width="10%" align="center"><input type="text"
							name="maxLeaveAccm" value="<%=String.valueOf(result[k][8])%>"
							maxlength="5" size="10" onkeypress="return numbersWithDot();"
							style="text-align: right"></td>
							
						<td width="10%" align="center"><input type="text"
							name="minLeaveApply" value="<%=String.valueOf(result[k][9])%>"
							maxlength="5" size="10" onkeypress="return numbersWithDot();"
							style="text-align: right" /></td>
						
						<td width="10%" align="center"><input type="text"
							name="maxLeaveApply" value="<%=String.valueOf(result[k][29])%>"
							maxlength="5" size="10" onkeypress="return numbersWithDot();"
							style="text-align: right" /></td>
						
						<td align="center" width="10%"><input type="checkbox"
							name="holiDay" id="h<%=i%>"
							value="<%=String.valueOf(result[k][0])%>" /> <%
						 if (String.valueOf(result[k][10]).equals("Y")) {
						 %> <script>
													document.getElementById('h<%=i%>').checked =true;
												</script> <%
						 }
						 %>
						</td>
						<td align="center" width="10%"><input type="checkbox"
							name="weekoff" id="w<%=i%>"
							value="<%=String.valueOf(result[k][0])%>" /> <%
						 if (String.valueOf(result[k][11]).equals("Y")) {
						 %> <script>
													document.getElementById('w<%=i%>').checked =true;
												</script> <%
						 }
						 %>
						</td>
						<td align="center" width="10%" nowrap="nowrap">
						<table>
							<tr>
								<td><input type="hidden" name="hiddesLeaveCode"
									id="hiddesLeaveCode<%=i%>"
									value="<%=String.valueOf(result[k][0])%>" /> <input
									type="hidden" name="hiddesncons" id="hiddesncons<%=i%>"
									value="<%=String.valueOf(result[k][12])%>" /><input
									type="text" name="conse" id="conse<%=i%>"
									value="<%=String.valueOf(result[k][13])%>" maxlength="20"
									size="20" readonly="true">
								<td><input type="button" class="token" value=" >> "
									onclick="call('conse<%=i %>','hiddesncons<%=i %>','hiddesLeaveCode<%=i %>');" /></td>
							</tr>
						</table>
						</td>
						<td align="center" width="12%"><input type="checkbox"
							name="zeroBal" id="z<%=i%>"
							value="<%=String.valueOf(result[k][0])%>" /> <%
							 if (String.valueOf(result[k][14]).equals("Y")) {
							 %> <script>
														document.getElementById('z<%=i%>').checked =true;
													</script> <%
							 }
							 %>
						</td>
						<td width="17%" align="center"><select name="leaveCreditType">
							<%
							if (String.valueOf(result[k][15]).equals("PR")) {
							%>
							<option value="PR" selected="selected">Advance Credit</option>
							<option value="PO">Accrual Credit</option>
							<%
							}
							%>
							<%
							if (String.valueOf(result[k][15]).equals("PO")) {
							%>
							<option value="PR">Advance Credit</option>
							<option value="PO" selected="selected">Accrual Credit</option>
							<%
							}
							%>
							<%
										if (String.valueOf(result[k][15]).equals("")
										|| String.valueOf(result[k][15]).equals("null")
										|| result[k][15] == null) {
							%>
							<option value="PR" selected="selected">Advance Credit</option>
							<option value="PO">Accrual Credit</option>
							<%
							}
							%>
						</select></td>
						<td align="center" width="12%"><input type="checkbox"
							name="halfLeave" id="x<%=i%>"
							value="<%=String.valueOf(result[k][0])%>" /> <%
						 if (String.valueOf(result[k][16]).equals("Y")) {
						 %> <script>
													document.getElementById('x<%=i%>').checked =true;
												</script> <%
						 }
						 %>
						</td>
						<td width="17%" align="center"><select name="levType"
							id="levType<%=i%>" onchange="checkUnpaid('<%=i%>')">
							<%
							if (String.valueOf(result[k][17]).equals("U")) {
							%>
							<option value="P">Paid</option>
							<option value="U" selected="selected">Unpaid</option>
							<%
							}
							%>
							<%
							if (String.valueOf(result[k][17]).equals("P")) {
							%>
							<option value="P" selected="selected">Paid</option>
							<option value="U">Unpaid</option>
							<%
							}
							%>
							<%
										if (String.valueOf(result[k][17]).equals("")
										|| String.valueOf(result[k][17]).equals("null")) {
							%>
							<option value="P" selected="selected">Paid</option>
							<option value="U">Unpaid</option>
							<%
							}
							%>
						</select></td>
						<td width="12%" align="center">
						<%
							if (String.valueOf(result[k][24]).equals("Y")) {
							%> <input type="checkbox" name="noticePeriod" checked="checked"
							value="<%=String.valueOf(result[k][0])%>"> <%
							}
							else{
							%> <input type="checkbox" name="noticePeriod"
							value="<%=String.valueOf(result[k][0])%>"> <%} %>
						</td>
						<td width="10%" align="center">
						<%
							if (String.valueOf(result[k][18]).equals("Y")) {
							%> <input type="checkbox" name="proofRequired" checked="checked"
							value="<%=String.valueOf(result[k][0])%>"> <%
							}
							else{
							%> <input type="checkbox" name="proofRequired"
							value="<%=String.valueOf(result[k][0])%>"> <%} %>
						</td>
						<td align="center" width="10%" nowrap="nowrap">
						<table>
							<tr>
								<td><input type="hidden" name="hiddesLeaveCodePool"
									id="hiddesLeaveCodePool<%=i%>"
									value="<%=String.valueOf(result[k][0])%>" /> <input
									type="hidden" name="hiddesnconsPool" id="hiddesnconsPool<%=i%>"
									value="<%=String.valueOf(result[k][25])%>" /></td>
							</tr>
						</table>
						</td>						
					</tr>
					<%
							i++;
							}
							request.removeAttribute("result");
						} catch (Exception e) {

						}
					%>
					<!--<tr>
						<td colspan="4" height="25" nowrap="nowrap"><font color="red">Note:-'0'(Zero)
					    indicates unlimited balance.</font></td>
					</tr>
					
				-->
				</table>

				</div>

				<div id="second">
				<table width="100%" border="0" class="formbg">
					<tr>
						<td colspan="5" nowrap="nowrap"><strong class="text_head"><label
							name="leave.entitlement.for.existing.employee"
							id="leave.entitlement.for.existing.employee"
							ondblclick="callShowDiv(this);"><%=label.get("leave.entitlement.for.existing.employee")%></label></strong></td>
					</tr>
					<tr>
						<td width="7%" nowrap="nowrap">
						<div align="center" class="formth"><strong class="text_head"><label
							name="leavename" id="leavename" ondblclick="callShowDiv(this);"><%=label.get("leavename")%></label></strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>JAN</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>FEB</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>MAR</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>APR</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>MAY</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>JUN</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>JUL</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>AUG</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>SEP</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>OCT</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>NOV</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>DEC</strong></div>
						</td>
					</tr>

					<%
						String[][] newEmpData = (String[][]) request.getAttribute("newEmpData");
                	String[][] regEmpData = (String[][]) request.getAttribute("regEmpData");
						try {
							
							for (int k = 0; k < regEmpData.length; k++) {
								if(k<regEmpData.length-1){
					%>
					<!-- CODING FOR LEAVE TYPE & MONTH -->
					<tr>
						<td align="left"><%=String.valueOf(regEmpData[k][1])%></td>
						<%for (int j = 2; j < 14; j++) { %>
						<td align="center"><input name="exMonth<%=k%>" type="text"
							maxlength="5" onkeypress="return numbersWithDot();"
							value="<%=String.valueOf(regEmpData[k][j])%>" size="3"
							style="text-align: right" /></td>
						<%} %>
					</tr>
					<s:hidden name="hiddenLeaveCode"
						value="<%=String.valueOf(regEmpData[k][0])%>" />
					<!-- CODING FOR LAST ROW PROCESSING DATE -->
					<%} else{%>
					<tr>
						<td><strong> Processing</strong> <strong>Date</strong></td>
						<%for (int j = 2; j < 14; j++) { %>
						<td align="center"><s:select name="dop"
							value="<%=String.valueOf(regEmpData[k][j])%>"
							list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}"></s:select></td>
						<%} %>
					</tr>
						<%	
					}}request.removeAttribute("regEmpData");						
						} catch (Exception e) {

						}
					%>
				</table>
				<!-- Leave Entitlement for new joining employee -->
				<table width="100%" border="0" class="formbg">
					<tr>
						<td colspan="6"><strong class="text_head"><label
							name="leave.entitlement.for.existing.employee"
							id="leave.entitlement.for.existing.employee"
							ondblclick="callShowDiv(this);"><%=label.get("leave.entitlement.for.new.employee")%></label></strong></td>
					</tr>
					<tr>
						<td colspan="2"><strong><label
							name="consider.joiningDate" id="consider.joiningDate"
							ondblclick="callShowDiv(this);"><%=label.get("consider.joiningDate")%></label>:
						</strong></td>
						<td colspan="4"><s:select theme="simple"
							name="joiningDateFlag"
							list="#{'J':'Joining Date','C':'Confirmation Date'}" /></td>
					</tr>
					<tr>
						<td width="8%" nowrap="nowrap">
						<div align="center" class="formth"><strong><label
							name="leavename" id="leavename" ondblclick="callShowDiv(this);"><%=label.get("leavename")%></label></strong>
						</div>
						</td>
						<td width="5%" nowrap="nowrap">
						<div align="center" class="formth"><strong>Duration</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>Day</strong></div>
						</td>
						<td width="6%">
						<div align="center" class="formth"><strong>JAN</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>FEB</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>MAR</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>APR</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>MAY</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>JUN</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>JUL</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>AUG</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>SEP</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>OCT</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>NOV</strong></div>
						</td>
						<td width="8%">
						<div align="center" class="formth"><strong>DEC</strong></div>
						</td>
					</tr>
					<%	try {		int count=0;					
							for (int n =0 ; n <newEmpData.length; n++) {
								 count=3;	
					%>
					<tr>
						<td><%=String.valueOf(newEmpData[n][1])%></td>
						<td nowrap="nowrap"><strong>On / Before</strong></td>
						<td align="center"><s:select name="beforeAfterDay"
							value="<%=String.valueOf(newEmpData[n][2])%>"
							list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}"></s:select>
						</td>
						<%for(int nn=0;nn<12;nn++){ 
                		%>
						<td align="center"><input maxlength="5"
							name="newMonthBefore<%=n%>" onkeypress="return numbersWithDot();"
							value="<%=String.valueOf(newEmpData[n][count])%>" type="text"
							size="3" style="text-align: right" /></td>
						<%count++;
                		} %>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td nowrap="nowrap"><strong>After</strong></td>
						<td align="center">&nbsp;</td>

						<%for(int nn=0;nn<12;nn++){ %>
						<td align="center"><input maxlength="5"
							name="newMonthAfter<%=n%>" onkeypress="return numbersWithDot();"
							value="<%=String.valueOf(newEmpData[n][count])%>" type="text"
							size="3" style="text-align: right" /></td>
						<%
               				 count++;
               			 } %>
					</tr>
					<%	}
							request.removeAttribute("newEmpData");			
                 		} 
                 catch (Exception e) {
						}
					%>
				</table>
				</div>
				<div id="five"><!-- Leave Entitlement for new joining employee -->
				<table width="100%" border="0" class="formbg">
					<tr>
						<td width="8%" nowrap="nowrap">
						<div align="center" class="formth"><strong><label
							name="leavename" id="leavename" ondblclick="callShowDiv(this);"><%=label.get("leavename")%></label></strong>
						</div>
						</td>
						<td width="5%" nowrap="nowrap">
						<div align="center" class="formth"><strong>Duration</strong></div>
						</td>
						<td width="5%">
						<div align="center" class="formth"><strong>Day</strong></div>
						</td>
						<td width="6%">
						<div align="center" class="formth"><strong>JAN</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>FEB</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>MAR</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>APR</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>MAY</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>JUN</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>JUL</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>AUG</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>SEP</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>OCT</strong></div>
						</td>
						<td width="7%">
						<div align="center" class="formth"><strong>NOV</strong></div>
						</td>
						<td width="8%">
						<div align="center" class="formth"><strong>DEC</strong></div>
						</td>
					</tr>
					<% 
			 		 String[][] empDataPool = (String[][]) request.getAttribute("empDataPool");
			  		%>
					<%	try {		int count=0;					
							for (int n =0 ; n <empDataPool.length; n++) {
								 count=3;		
					%>
					<tr>
						<td><%=String.valueOf(empDataPool[n][1])%></td>
						<td nowrap="nowrap"><strong>On / Before</strong></td>
						<td align="center"><s:select name="beforeAfterDayPool"
							value="<%=String.valueOf(empDataPool[n][2])%>"
							list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}"></s:select>
						</td>
						<%for(int nn=0;nn<12;nn++){                 	
                		%>
						<td align="center"><input maxlength="5"
							name="newMonthBeforePool<%=n%>"
							onkeypress="return numbersWithDot();"
							value="<%=String.valueOf(empDataPool[n][count])%>" type="text"
							size="3" style="text-align: right" /></td>
						<%count++;
               			 } %>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td nowrap="nowrap"><strong>After</strong></td>
						<td align="center">&nbsp;</td>

						<%for(int nn=0;nn<12;nn++){ %>
						<td align="center"><input maxlength="5"
							name="newMonthAfterPool<%=n%>"
							onkeypress="return numbersWithDot();"
							value="<%=String.valueOf(empDataPool[n][count])%>" type="text"
							size="3" style="text-align: right" /></td>
						<%
               			 count++;
               			 } %>
					</tr>
					<%	}
							request.removeAttribute("empDataPool");			
                 		} 
                 		catch (Exception e) {
						}
					%>
				</table>
				</div>
				<div id="third">
				<table width="100%" border="0" class="formbg">
					<tr>
						<td width="10%" class="formth"><strong class="text_head"><label
							name="leavename" id="leavename" ondblclick="callShowDiv(this);"><%=label.get("leavename")%></label></strong></td>
						<td width="12%" class="formth"><strong class="text_head"><label
							name="is.encashable" id="is.encashable"
							ondblclick="callShowDiv(this);"><%=label.get("is.encashable")%></label></strong></td>
						<td width="30%" class="formth" colspan="2"><strong
							class="text_head"><label name="encashment.formula"
							id="encashment.formula" ondblclick="callShowDiv(this);"><%=label.get("encashment.formula")%></label></strong></td>
						<td width="25%" class="formth" colspan="2"><strong
							class="text_head"><label name="max.encashment.limit"
							id="max.encashment.limit" ondblclick="callShowDiv(this);"><%=label.get("max.encashment.limit")%></label></strong></td>
						<td width="23%" class="formth"><strong class="text_head"><label
							name="max.encashment.separation" id="max.encashment.separation"
							ondblclick="callShowDiv(this);"><%=label.get("max.encashment.separation")%></label></strong></td>
					</tr>
					<%						
						try {							
							for (int k = 0; k < result.length; k++) {
					%>
					<tr>
						<td><%=String.valueOf(result[k][1])%></td>
						<td align="center"><input type="checkbox"
							onclick="callIsFormula('<%=k%>');" name="enableFlag"
							id="<%="enableFlag"+k%>"
							value="<%=String.valueOf(result[k][0])%>" /> <% if (String.valueOf(result[k][19]).equals("Y")) {
						 %> <script>
							document.getElementById('enableFlag<%=k%>').checked =true;
						</script> <%
 							}							
							%>
						</td>
						<td align="right"><s:textfield name="encashmentFormula"
							id="<%="encashFormula"+k%>" readonly="true" size="20"
							value="<%=String.valueOf(result[k][20])%>" /></td>
						<td align="left" width="10%"><input type="button"
							class="token" value=" >> "
							onclick="callFormula('encashFormula<%=k %>');" /></td>
						<td width="10%" align="right"><s:textfield
							name="maxEncashLimit" size="3" maxlength="5"
							cssStyle="text-align:right"
							value="<%=String.valueOf(result[k][21])%>"
							onkeypress="return numbersWithDot();" /></td>
						<td align="center"><select name="maxEncashSelect">
							<%
							if (String.valueOf(result[k][22]).equals("Mo")) {
							%>
							<option value="Mo" selected="selected">Monthly</option>
							<option value="Qu">Quarterly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}							
							if (String.valueOf(result[k][22]).equals("Qu")) {
							%>
							<option value="Qu" selected="selected">Quarterly</option>
							<option value="Mo">Monthly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}							
							if (String.valueOf(result[k][22]).equals("Hy")) {
							%>
							<option value="Qu">Quarterly</option>
							<option value="Mo">Monthly</option>
							<option value="Hy" selected="selected">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}							
							if (String.valueOf(result[k][22]).equals("Ye")) {
							%>
							<option value="Qu">Quarterly</option>
							<option value="Mo">Monthly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye" selected="selected">Yearly</option>
							<%
							}							
							if (String.valueOf(result[k][22]).equals("")||String.valueOf(result[k][22]).equals("0")
								|| String.valueOf(result[k][22]).equals("null")
								|| result[k][22] == null) {
							%>
							<option value="" selected="selected">Select</option>
							<option value="Mo">Monthly</option>
							<option value="Qu">Quarterly</option>
							<option value="Hy">Half Yearly</option>
							<option value="Ye">Yearly</option>
							<%
							}
							%>
						</select></td>
						<td align="center"><s:textfield name="maxEncSepration"
							maxlength="5" onkeypress="return numbersWithDot();" size="3"
							cssStyle="text-align:right"
							value="<%=String.valueOf(result[k][23])%>" /></td>
					</tr>
					<%	
							}
							request.removeAttribute("result");
						} catch (Exception e) {

						}
					%>
				</table>
				</div>
				<div id="fourth">
				<table width="100%" border="0" class="formbg">
					<!-- <tr>
				 <td colspan="7">
				 <table  border="0">
                 <tr>
                  <td colspan="1"> <s:checkbox name="leaveBalEnableStatus"></s:checkbox>
					 </td>
                    <td colspan="1">
                    <strong class="text_head"><label
							name="leave.balance.notavailable" id="leave.balance.notavailable"
							ondblclick="callShowDiv(this);"><%=label.get("leave.balance.notavailable")%></label></strong>                    
                    </td>
                    <td colspan="1"><s:select name="leaveBalanceNotAvail" list="#{'S':'From Salary','M':'From next month leave'}"></s:select> </td>
					                    
                  </tr>
				 </table>				 
				 </td>
				 </tr>-->
					<tr>
						<td colspan="7">
						<table border="0">
							<tr>
								<td colspan="1"><s:checkbox name="unAuthorizedStatus"></s:checkbox>
								</td>
								<td colspan="1"><strong class="text_head"><label
									name="enable.penalty.unauthorized"
									id="enable.penalty.unauthorized"
									ondblclick="callShowDiv(this);"><%=label.get("enable.penalty.unauthorized")%></label>
								: <s:textfield name="unAuthorizedDay" size="02"
									cssStyle="text-align:right"
									onkeypress="return numbersWithDot();" maxlength="5" /> <label
									name="days.penalty" id="days.penalty"
									ondblclick="callShowDiv(this);"><%=label.get("days.penalty")%></label>
								<s:textfield name="absentDays" size="02"
									onkeypress="return numbersWithDot();"
									cssStyle="text-align:right" maxlength="5" /> <label
									name="leave.absent" id="leave.absent"
									ondblclick="callShowDiv(this);"><%=label.get("leave.absent")%></label>
								</strong></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="7">
						<table border="0">
							<tr>
								<td colspan="1" nowrap="nowrap"><strong class="text_head">
								<label name="unAuthorized.leave.adjust.in"
									id="unAuthorized.leave.adjust.in"
									ondblclick="callShowDiv(this);"><%=label.get("unAuthorized.leave.adjust.in")%></label>
								</strong> <s:textfield name="lateMarksLeave" size="25"
									id="lateMarksLeaveId" readonly="true" /><s:hidden
									name="lateMarksLeaveCode" /></td>
								<td><input type="button" class="token" align="middle"
									value=" >> " id="lateMarksLeaveBtn"
									onclick="callLeaveCombination('lateMarksLeaveCode','lateMarksLeaveId');" />
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<s:hidden name="leaveCodeHid" />
					<s:hidden name="leaveAbbrHid" />
					<tr>
						<td colspan="7">
						<table border="0">
							<tr>
								<td colspan="1"><s:checkbox name="penaltyStatus"></s:checkbox>
								</td>
								<td colspan="1"><strong class="text_head"><label
									name="enable.penalty.unplane.unauthorized"
									id="enable.penalty.unplane.unauthorized"
									ondblclick="callShowDiv(this);"><%=label.get("enable.penalty.unplane.unauthorized")%></label></strong>

								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="6"><s:submit value="Add Rule" cssClass="add"
							action="LeavePolicy_addUnplaneRule"
							onclick="return formValidate();" /></td>
						<td colspan="1" align="right"><s:submit value="Remove"
							cssClass="delete" action="LeavePolicy_removeUnplaneRule"
							onclick="return callRemove();" /></td>
					</tr>
					<tr>
						<td class="formth"><strong class="text_head"><label
							name="leavename" id="leavename" ondblclick="callShowDiv(this);"><%=label.get("leavename")%></label></strong>
						</td>
						<td class="formth" colspan="3"><strong class="text_head"><label
							name="for.Leaves" id="for.Leaves" ondblclick="callShowDiv(this);"><%=label.get("for.Leaves")%></label></strong>
						</td>
						<td class="formth"><strong class="text_head"><label
							name="applied.in.advance" id="applied.in.advance"
							ondblclick="callShowDiv(this);"><%=label.get("applied.in.advance")%></label></strong>
						</td>
						<td class="formth"><strong class="text_head"><label
							name="penalty" id="penalty" ondblclick="callShowDiv(this);"><%=label.get("penalty")%></label></strong>

						</td>
						<td class="formth"></td>
					</tr>
					<%int t=0; %>
					<s:iterator value="unplaneArray">
						<tr>
							<td><s:hidden name="unPlaneHidCode"></s:hidden> <s:select
								theme="simple" name="unPlaneLeave" list="leaveMap" /></td>
							<td align="center"><s:textfield maxlength="5"
								name="forLeaves" size="08" onkeypress="return numbersWithDot();"
								cssStyle="text-align:right"></s:textfield></td>
							<td align="center">To</td>
							<td align="center"><s:textfield maxlength="5"
								name="toLeaves" size="08" onkeypress="return numbersWithDot();"
								cssStyle="text-align:right"></s:textfield></td>
							<td align="center"><s:textfield maxlength="5"
								name="appliedInAdvance" size="08"
								onkeypress="return numbersWithDot();"
								cssStyle="text-align:right"></s:textfield> <input type="hidden"
								name="hideenY" id="hideenY<%=t%>"
								value='<s:property value="hideenY"/>' /></td>

							<td align="center"><s:textfield name="penaltyForUnPlane"
								size="08" maxlength="5" onkeypress="return numbersWithDot();"
								cssStyle="text-align:right"></s:textfield></td>
							<td align="center"><input type="checkbox"
								name="removeUnPlane" value='<s:property value="unPlaneLeave"/>'
								id="chk<%=t%>" onclick="callY('<%=t%>')" /></td>
						</tr>
						<%t++; %>
					</s:iterator>
					<tr>
						<td colspan="7"><s:submit value="Add Rule"
							onclick="return formValidate();" cssClass="add"
							action="LeavePolicy_addUnplaneRule" /></td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
		</s:if>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%"><input type="submit"
						id="paraFrm_LeavePolicy_save" name="action:LeavePolicy_save"
						value=" Save" class="save" onclick="return formValidate();" /> <input
						type="submit" id="paraFrm_LeavePolicy_reset"
						name="action:LeavePolicy_reset" value=" Reset" class="reset" /> <input
						type="submit" id="paraFrm_LeavePolicy_delete"
						name="action:LeavePolicy_delete" value=" Delete" class="delete"
						onclick="return callDelete('paraFrm_policyCode');" /> <input
						type="button" class="search" value=" Search "
						onclick="javascript:callsF9(500,325,'LeavePolicy_f9action.action');" />
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

	</table>
</s:form>
<script type="text/javascript">
	//alert(opener.document.getElementById(textId).value);
	//var st= document.getElementById('paraFrm_hiddenSalCode').value;
function callIsFormula(id){	
	if(document.getElementById('enableFlag'+id).checked){
	if(document.getElementById('encashFormula'+id).value==''){
	document.getElementById('enableFlag'+id).checked=false;
	alert('Please select first '+document.getElementById('encashment.formula').innerHTML.toLowerCase());
	return false;
	}
	}	
}
	
function allCharacters(e){
	document.onkeypress = allCharacters;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	   //	alert(key);
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if (key == 39 || key == 34 || key == 92)
	 	return false; // if so, do nothing
	else // otherwise, discard character
		return true;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}
	
	
function callRemove(){
				var con=confirm('Do you really want to Remove the Record');
				if(con){
				return true;
				}
				else{
				return false;
				}
}
	
	
	
function callY(id){	
	if(document.getElementById('chk'+id).checked==true){	
	document.getElementById('hideenY'+id).value='Y';
	}
	else{
	document.getElementById('hideenY'+id).value='N';
	}
}
	
function checkEncash(id,check){
		if(document.getElementById('a'+id).checked){
		
		}else{
		
			document.getElementById(check+id).checked =false;
		}
		
	if(document.getElementById('e'+id).checked)
		{
			var unpaid=document.getElementById('levType'+id).value;
	 	
	 		if(unpaid=='U')
	 		{
			 		document.getElementById('e'+id).checked=false;
			 	
	 		}
		 	
		 }
		 if(document.getElementById('e'+id).checked==false)
		 {
		 	 document.getElementById('encashFormula'+id).value="";
		 }
		 
		 if(document.getElementById('e'+id).checked==true)
		 {
		 
		 
		 	if(document.getElementById('encashFormula'+id).value=="")
		 	 {
		 	 alert("Please select "+document.getElementById('encashformula').innerHTML.toLowerCase());
		 	 document.getElementById('e'+id).checked=false;
		 	 }
		 	 
		 }
		 
}
	
function checkapp(id){
	
		if(document.getElementById('a'+id).checked==false)
		{
			//document.getElementById('e'+id).checked =false;
			//document.getElementById('v'+id).checked =false; //
			document.getElementById('z'+id).checked =false;
			document.getElementById('c'+id).checked =false;
			document.getElementById('w'+id).checked =false;
			document.getElementById('h'+id).checked =false;
			document.getElementById('x'+id).checked =false;
		}
}
	
function formValidate(){
	try
	{ 	var fieldName = ["paraFrm_policyName"];
			var lableName = ["policyname"];
			var flag = ["enter"];
  		var policy =document.getElementById('paraFrm_policyName').value;
  		var division=document.getElementById('paraFrm_divisionName').value;
  		if(policy == ""){
  			alert("Please enter "+document.getElementById('policyname').innerHTML.toLowerCase());
  			return false;
  		} 
  		
  		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
        
  		if(division == ""){
  			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
  			return false;
  		} 
  		
  		return true;
  	}
  	catch(e)
  	{
  		//alert(e);
  	}
}
  	
function checkUnpaid(id){
 	var unpaid=document.getElementById('levType'+id).value;
 	
 		if(unpaid=='U')
 		{
		 		document.getElementById('e'+id).checked=false;
		 	
 		}
 	
}
  	
function callFormula(id){
			window.open('','new','top=100,left=300,width=400,height=400,scrollbars=no,status=no,resizable=no');
	 		document.getElementById("paraFrm").target="new";
	 		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/payroll/FormulaBuilder_formulaCalc.action?id='+id; 
	  		document.getElementById("paraFrm").submit();
	  	//	alert(window.location);
	  		document.getElementById("paraFrm").target="main";
}
	  	 
function call(id,id1,LeaveCode){
	  	   	// alert(id);	  	   	
	  	   	 var hCode=document.getElementById(LeaveCode).value;
	  	   	//  alert(hCode);
	  	   	 
			window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	 		document.getElementById("paraFrm").target="new";
			document.getElementById("paraFrm").action='LeavePolicy_leaveConsecutive.action?id='+id+'&hCode='+hCode+'&id1='+id1; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
}

onloadCall();
	
function onloadCall(){
	try{
	document.getElementById('first').style.display='block';
	document.getElementById('second').style.display='none';
	document.getElementById('third').style.display='none';
	document.getElementById('fourth').style.display='none';
	document.getElementById('five').style.display='none';
	document.getElementById('menuid1').className='on';
	document.getElementById('menuid2').className='li';
	document.getElementById('menuid3').className='li';
	document.getElementById('menuid4').className='li';
	document.getElementById('menuid5').className='li';
	if(document.getElementById('paraFrm_unplaneFlag').value=='menuid4'){
	document.getElementById('fourth').style.display='block';
	document.getElementById('menuid4').className='on';
	document.getElementById('first').style.display='none';
	document.getElementById('menuid1').className='li';
	}
	}
	catch(e)
	{
	}
	
	callDate();
}

function callDate(){			
	var sr=document.getElementById('paraFrm_fromMonth').value;	
	if(sr=='1'){
	document.getElementById('paraFrm_toMonth').value='12';
	}
	else{
	sr=sr-1;
	document.getElementById('paraFrm_toMonth').value=sr;
	}
}


function showCurrent(menuId, id){
	document.getElementById('first').style.display='none';
	document.getElementById('second').style.display='none';
	document.getElementById('third').style.display='none';
	document.getElementById('fourth').style.display='none';
	document.getElementById('five').style.display='none';
	document.getElementById('menuid1').className='li';
	document.getElementById('menuid2').className='li';
	document.getElementById('menuid3').className='li';
	document.getElementById('menuid4').className='li';
	document.getElementById('menuid5').className='li';
	document.getElementById(menuId).className='on';
	document.getElementById(id).style.display='block';
	document.getElementById('paraFrm_unplaneFlag').value=menuId;
}

function callLeaveCombination(setLeaveCode, setLeaveAbbr) {
		document.getElementById('paraFrm_leaveCodeHid').value = setLeaveCode;
		document.getElementById('paraFrm_leaveAbbrHid').value = setLeaveAbbr;		
		window.open('','new','top=300,left=300,width=350,height=300,scrollbars=yes,status=no,resizable=no');
	 	document.getElementById("paraFrm").target = "new";
		document.getElementById("paraFrm").action = 'LeavePolicy_leaveCombination.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
}


function callLeaveInterval(id,id1,LeaveCode){
	var hCode=document.getElementById(LeaveCode).value;
	window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="new";
	document.getElementById("paraFrm").action='LeavePolicy_getLeaveIntervalMonth.action?id='+id+'&hCode='+hCode+'&id1='+id1; 
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
}

/*function resetintervalmonth(id){
	 document.getElementById('intervalmonthname'+id).value='';
}*/
</script>