<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="CashClaimApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cash
					Claim Approval</strong></td>
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
					<td width="78%"><s:if test="%{apprflag}"></s:if> <s:elseif
						test="%{insertFlag}">
						<input name="save" type="button" class="save" value="    Save "
							onclick="return saveValidate();" />
					</s:elseif> <input name="Submit3" type="button" class="reset"
						value="     Reset" onclick="return callReset();" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
					<s:hidden name="status" />
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="27" class="formtxt"><a
								href="CashClaimApproval_checkData.action?status=P">Pending
							List</a> | <a href="CashClaimApproval_checkData.action?status=A">Approved
							List</a> | <a href="CashClaimApproval_checkData.action?status=R">Rejected
							List</a></td>
						</tr>
						<s:hidden name="listLength"></s:hidden>

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
							<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="27" class="formtxt"><strong> <%
 	String status = (String) request.getAttribute("stat");
 	if (status != null) {
 		out.println(status);
 	} else {
 		out.println("Pending List");
 	}
 %> </strong></td>

								</tr>
								<tr>
									<td>
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable">
										<tr>
											<td width="5%" valign="top" class="sortableTD"><label
												class="set" id="sno" name="sno"
												ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>.</td>
											<td width="10%" valign="top" class="sortableTD"><label
												class="set" id="employee.id" name="employee.id"
												ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
											<td width="23%" valign="top" class="sortableTD"><label
												class="set" id="employye" name="employee"
												ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
											<td width="10%" valign="top" class="sortableTD"><label
												class="set" id="appDate" name="appDate"
												ondblclick="callShowDiv(this);"><%=label.get("appDate")%></label></td>
											<td width="8%" valign="top" class="sortableTD"><label
												class="set" id="stat" name="stat"
												ondblclick="callShowDiv(this);"><%=label.get("stat")%></label></td>
											<td width="12%" valign="top" class="sortableTD"><label
												class="set" id="details" name="details"
												ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>
											<td width="32%" valign="top" class="sortableTD"><label
												class="set" id="commt" name="commt"
												ondblclick="callShowDiv(this);"><%=label.get("commt")%></label></td>
										</tr>
										<s:if test="noData">
											<tr>
												<td width="100%" colspan="8" align="center"><font
													color="red">No Data To Display</font></td>
											</tr>
										</s:if>
										<%!int i = 0;%>
										<%
											int k = 1;
											int c = 0;
										%>

										<s:iterator value="recordList">

											<tr>
												<td class="border2" width="5%"><%=k%>
												<td class="border2" width="10%"><s:property
													value="empToken" /><s:hidden name="claimCode"
													value="%{claimCode}" /><s:hidden name="level" /><s:hidden
													name="empID" /></td>
												<td class="border2" width="23%"><s:property
													value="empName" /></td>
												<td class="border2" width="13%"><s:property
													value="appliedDate" /></td>
												<td align="center" width="8%" class="border2" align="left">
												<s:if test="apprflag">
													<s:property value="statusNew" />
												</s:if> <s:else>
													<s:select name="checkStatus" id="<%="check"+k %>"
														cssStyle="width:100" theme="simple"
														list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
												</s:else></td>

												<td class="border2" width="12%" align="left"><input
													type="button" name="view_Details" class="token"
													value=" View Details "
													onclick="viewDetails('<s:property value="claimCode"/>')" />
												<!--<a href="<s:url action="LTCClaim_callByApprover"><s:param name="ltcCode" value="ltcID"/>
	      <s:param name="empCode" value="empID"/></s:url>" target="_blank">Show Details</a>-->
												<s:hidden name="hiddenClaimCode" /> <s:hidden
													name="empCode" value="%{empID}" /></td>
												<td width="32%" class="border2"><s:if test="apprflag">
													<s:property value="comment" />&nbsp;</s:if> <s:else>
													<s:textarea name="comment" value="%{comment}" rows="2"
														cols="34"></s:textarea>
												</s:else></td>

											</tr>
											<%
												k++;
												c++;
											%>
										</s:iterator>
										<%
										i = k;
										%>
									</table>
									</td>
								</tr>
								<tr>
									<input type="hidden" name="count" id="count" value="<%=c%>" />
									<td class="cellbg">
									<table width="130" border="0" align="right" cellpadding="0"
										cellspacing="0">
										<tr>
											<td><strong>Page </strong></td>
											<td><img src="../pages/images/recruitment/first.gif"
												width="10" height="10" /></td>
											<td><img src="../pages/images/recruitment/previous.gif"
												width="10" height="10" /></td>
											<td>
											<div align="center">0-1</div>
											</td>
											<td><img src="../pages/images/recruitment/next.gif"
												width="10" height="10" /></td>
											<td><img src="../pages/images/recruitment/last.gif"
												width="10" height="10" /></td>
											<s:hidden name="hiddenTrfCode" />
										</tr>



									</table>
									</td>
								</tr>
								<tr>
									<td colspan="3"><img
										src="../pages/images/recruitment/space.gif" width="5"
										height="4" /></td>
								</tr>
								<tr>
									<td colspan="3"><s:if test="%{apprflag}"></s:if><s:elseif
										test="%{insertFlag}">
										<input name="Submit222" type="button" class="save"
											value="    Save " onclick="return saveValidate();" />
									</s:elseif> <input name="Submit32" type="button" class="reset"
										value="     Reset" onclick="return callReset();" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script>
	// callOnload();
	
	function saveValidate(){
	//alert("safd"+document.getElementById("count").value);
	//alert(document.getElementById("paraFrm_listLength").value);
	if(document.getElementById("count").value==0){
			alert("There is no record to save");
			return false;
		}else{
	document.getElementById("paraFrm").action="CashClaimApproval_save.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
		function callReset(){
			document.getElementById("paraFrm_status").value=='P';
			document.getElementById("paraFrm").action="CashClaimApproval_input.action";
 			document.getElementById("paraFrm").submit();
 			document.getElementById("paraFrm").target="main";
 	
	 }
	 function callOnload(){
	 	var check =<%=i%>;
	 alert(document.getElementById("paraFrm_status").value);

	 if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
	}
	
	function viewDetails(claimCode){
		document.getElementById('paraFrm_hiddenClaimCode').value = claimCode;
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "CashClaim_callByApprover.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
</script>