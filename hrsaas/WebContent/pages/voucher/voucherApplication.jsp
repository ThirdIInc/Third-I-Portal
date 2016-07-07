<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="VoucherApplication" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="selectedVCode"/>
	<s:hidden name="selectedStatus"/>

	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Voucher
					Application</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><a href="#" onclick="setAction('P')">Pending
					Application</a> | <a href="#" onclick="setAction('A')">Approved
					List</a> | <a href="#" onclick="setAction('R')">Rejected List</a></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Pending Application Tab Starts -->
		<s:if test="%{sListType == 'pending'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Draft</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="20%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="35%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="18%"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%"><b><label id="viewDetails"
									name="viewDetails" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("viewDetails")%></label></b></td>
							</tr>
							<s:if test="%{lstDraft == null || lstDraft.size() == 0}">
								<tr>
									<td width="100%" colspan="4" align="center" class="sortableTD">
									<font color="red">There is no data to display.</font></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="lstDraft">
									<% int count1 = 0; %>
									<tr>

										<td class="sortableTD" align="center"><s:property
											value="sSrNo" /></td>
										<td class="sortableTD"><s:property value="empId" /></td>
										<td class="sortableTD"><s:property value="ename" /></td>
										<td class="sortableTD" align="center"><s:property
											value="vchDate" /></td>
										<td class="sortableTD" width="20%" align="center"><input	type="button" name="viewBtn" class="token"
											value="View Details" onclick="callForEdit('<s:property value="vCode" />','<s:property value="status" />');"/></td>
									</tr>
								</s:iterator>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Draft Table Ends - all Saved records -->

			<!-- Submitted List Starts - all send to approval records -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Application In Process</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="20%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="35%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="18%"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%"><b><label id="viewDetails"
									name="viewDetails" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("viewDetails")%></label></b></td>
								
							</tr>

							<s:if test="%{lstPending == null || lstPending.size() == 0}">
								<tr>
									<td width="100%" colspan="4" align="center" class="sortableTD">
									<font color="red">There is no data to display.</font></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="lstPending">
									<% int count2 = 0; %>
									<tr>
										<td class="sortableTD" align="center"><s:property
											value="sSrNo" /></td>
										<td class="sortableTD"><s:property value="empId" /></td>
										<td class="sortableTD"><s:property value="ename" /></td>
										<td class="sortableTD" align="center"><s:property
											value="vchDate" /></td>
										<td class="sortableTD" width="20%" align="center"><input	type="button" name="viewBtn" class="token"
											value="View Details" onclick="callForEdit('<s:property value="vCode" />','<s:property value="status" />');"/></td>
									
									</tr>
								</s:iterator>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Submitted List Ends - all Send to Approval records -->

			<!-- Returned List Starts - all send to back records -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Returned Applications(Please view the comments,
						update Application)</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="20%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="35%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="18%"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%"><b><label id="viewDetails"
									name="viewDetails" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("viewDetails")%></label></b></td>
							</tr>

							<s:if test="%{lstReturn == null || lstReturn.size() == 0}">
								<tr>
									<td width="100%" colspan="4" align="center" class="sortableTD">
									<font color="red">There is no data to display.</font></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="lstReturn">
									<% int count3 = 0; %>
									<tr>
										<td class="sortableTD" align="center"><s:property
											value="sSrNo" /></td>
										<td class="sortableTD"><s:property value="empId" /></td>
										<td class="sortableTD"><s:property value="ename" /></td>
										<td class="sortableTD" align="center"><s:property
											value="vchDate" /></td>
										<td class="sortableTD" width="20%" align="center"><input	type="button" name="viewBtn" class="token"
											value="View Details" onclick="callForEdit('<s:property value="vCode" />','<s:property value="status" />');"/></td>
									
									</tr>
								</s:iterator>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Returned List Ends - all Send to back records -->

		</s:if>
		<!-- Pending Application Tab Ends -->

		<!-- Approved List Tab Starts -->
		<s:if test="%{sListType == 'approved'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Approved Application List</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="20%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="35%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="18%"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%"><b><label id="viewDetails"
									name="viewDetails" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("viewDetails")%></label></b></td>
							</tr>

							<s:if test="%{lstApproved == null || lstApproved.size() == 0}">
								<tr>
									<td width="100%" colspan="4" align="center" class="sortableTD">
									<font color="red">There is no data to display.</font></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="lstApproved">
									<% int count4 = 0; %>
									<tr>
										<td class="sortableTD" align="center"><s:property
											value="sSrNo" /></td>
										<td class="sortableTD"><s:property value="empId" /></td>
										<td class="sortableTD"><s:property value="ename" /></td>
										<td class="sortableTD" align="center"><s:property
											value="vchDate" /></td>
										<td class="sortableTD" width="20%" align="center"><input	type="button" name="viewBtn" class="token"
											value="View Details" onclick="callForEdit('<s:property value="vCode" />','<s:property value="status" />');" /></td>
									
									</tr>
								</s:iterator>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- Approved List Tabe Ends -->

		<!-- Rejected List Tab Starts -->
		<s:if test="%{sListType=='rejected'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Rejected Application List</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="20%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="35%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="8%"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%"><b><label id="viewDetails"
									name="viewDetails" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("viewDetails")%></label></b></td>
							</tr>

							<s:if test="%{lstRejected == null || lstRejected.size() == 0}">
								<tr>
									<td width="100%" colspan="4" align="center" class="sortableTD">
									<font color="red">There is no data to display.</font></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="lstRejected">
									<% int count5 = 0; %>
									<tr>
										<td class="sortableTD" align="center"><s:property
											value="sSrNo" /></td>
										<td class="sortableTD"><s:property value="empId" /></td>
										<td class="sortableTD"><s:property value="ename" /></td>
										<td class="sortableTD" align="center"><s:property
											value="vchDate" /></td>
										<td class="sortableTD" width="20%" align="center"><input	type="button" name="viewBtn" class="token"
											value="View Details" onclick="callForEdit('<s:property value="vCode"/>','<s:property value="status"/>')" /></td>
									
									</tr>
								</s:iterator>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- Rejected List Tab Ends -->

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	function setAction(sListType) {
		if(sListType == "P") {
			document.getElementById('paraFrm').action = 'VoucherApplication_input.action';
			document.getElementById('paraFrm').submit();
		}
		if(sListType == "A") {
			document.getElementById('paraFrm').action = 'VoucherApplication_getApprovedList.action';
			document.getElementById('paraFrm').submit();
		}
		if(sListType == "R") {
			document.getElementById('paraFrm').action = 'VoucherApplication_getRejectedList.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function newRowColor(cell) {
		//alert(cell);
		cell.className='Cell_bg_first';
	}
	
	function oldRowColor(cell,val) {
		//alert(cell + "" + val);
		if(val=='1')
		{
	 		cell.className='tableCell2';
		}
		else
		{ 
			cell.className='tableCell1';
		}
		
	}
	
	function callForEdit_OLD(vId, status) {
	  	document.getElementById('paraFrm_selectedVCode').value = vId;
	  	document.getElementById('paraFrm_selectedStatus').value = status;
	  
	   	document.getElementById("paraFrm").action="VoucherApplication_callforedit.action"; 
	    document.getElementById("paraFrm").submit();
    }
    
    function callForEdit(vId, status) {
	  
	   	document.getElementById("paraFrm").action='VoucherApplication_callforedit.action?voucherCode='+vId+'&voucherStatus='+status; 
	    document.getElementById("paraFrm").submit();
    }
    
    function addapplicationFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'VoucherApplication_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'VoucherApplication_f9action.action';
		document.getElementById("paraFrm").submit();
	}
</script>