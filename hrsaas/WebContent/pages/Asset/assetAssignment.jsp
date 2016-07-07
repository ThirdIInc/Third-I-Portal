<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="AssetAssignment" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="show" value="%{show}" />
		<s:hidden name="source" id="source"/>
<%
		int pageNo = 0;
%>		
		
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset 
					Assignment</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<s:if test="pendingFlag">
					<tr>
						<td width="20%" colspan="1" class="formtext"><label
							class="set" id="employee" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						: <s:hidden name="selectedEmpCode" value="%{selectedEmpCode}" /></td>
						<td width="80%" colspan="3"><s:textfield name="selectedEmpId"
							size="10" value="%{selectedEmpId}" theme="simple" readonly="true" /><s:textfield
							name="selectedEmpName" size="50" value="%{selectedEmpName}"
							theme="simple" readonly="true" /><img
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'AssetAssignment_f9actionEmp.action'); "></td>
					</tr>
				</s:if>
				<tr>
					<td height="27" class="formtxt" colspan="5"><a
						href="AssetAssignment_checkRequest.action?status=P">Pending
					List</a> | <a href="AssetAssignment_checkRequest.action?status=R">Request
					from other warehouse</a></td>
				</tr>
			</table>
			<s:hidden name="status" /></td>
		</tr>
		
		<s:if test="showFlag">		<tr>
			<td colspan="3" width="100%">
			<table width="100%" align="left" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"></td>
					<%
						int totalPage = 0;
						
					%>
					
						<td id="ctrlShow" width="30%" align="right"><b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 	pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'AssetAssignment_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'AssetAssignment_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'AssetAssignment_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'AssetAssignment_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AssetAssignment_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					
				</tr>
			</table>
			</td>
		</tr></s:if>


		<s:if test="pendingFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">

					<s:hidden name="status" />
					<tr>
						<td colspan="3" class="formtxt"><strong> <%
 		{
 		out.println("Pending List");
 	}
 %> </strong></td>
					</tr>
					<tr>
						<td width="5%" valign="top" class="formth"><label class="set"
							id="asstSrNo" name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>
						<td width="10%" valign="top" class="formth"><label
							class="set" id=employee.id name="employee.id"
							ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
						<td width="30%" valign="top" class="formth"><label
							class="set" id="employee" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td width="20%" valign="top" class="formth"><label
							class="set" id="assetIsInvAvailable" name="assetIsInvAvailable"
							ondblclick="callShowDiv(this);"><%=label.get("assetIsInvAvailable")%></label></td>
						<td width="30%" valign="top" class="formth"><label
							class="set" id="assetAssign" name="assetAssign"
							ondblclick="callShowDiv(this);"><%=label.get("assetAssign")%></label></td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">No Pending applications</font></td>
						</tr>
					</s:if>
					<%!int p = 0;%>
					<%
						int q = 1;
						int r = 0;
						int cn = pageNo * 20 - 20;
					%>
					
					<s:iterator value="recordList">
						<tr>
							<td class="sortableTD" width="5%"><%=++cn%>
							<td class="sortableTD" width="10%"><s:property
								value="empToken" /><s:hidden name="assetCode"
								value="%{assetCode}" /><s:hidden name="empID" /></td>
							<td class="sortableTD" width="30%"><s:property
								value="empName" /></td>
							<td class="sortableTD" width="20%"><s:property
								value="availability" />&nbsp;</td>
							<td class="sortableTD" width="30%" align="left"><input
								type="button" name="assignAsset" class="token"
								value=" Assign Asset "
								onclick="callAssignAsset('<s:property value="assetCode"/>','<s:property value="availability"/>');" />
							<input type="button" name="viewAsset" class="token"
								value=" View Details "
								onclick="callViewDetails('<s:property value="assetCode"/>');" />
							<s:hidden name="hiddenAssetCode" /></td>
						</tr>
						<%
							q++;
							r++;
						%>
					</s:iterator>
					<%
					p = q;
					%>

				</table>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="formtxt"><strong> <%
 		{
 		out.println("Request From Other warehouse");
 	}
 %> </strong></td>

					</tr>
					<tr>
						<td width="20%"><input type="button" name="transferAsset"
							class="token" onclick="return callTransfer();"
							value=" Transfer Asset " /></td>
					</tr>
					<tr>
						<td width="5%" valign="top" class="formth"><label class="set"
							id="asstSrNo" name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>
						<td width="20%" valign="top" class="formth"><label
							class="set" id="assetWarehouse" name="assetWarehouse"
							ondblclick="callShowDiv(this);"><%=label.get("assetWarehouse")%></label></td>
						<td width="20%" valign="top" class="formth"><label
							class="set" id="assetCat" name="assetCat"
							ondblclick="callShowDiv(this);"><%=label.get("assetCat")%></label></td>
						<td width="20%" class="formth"><label class="set"
							id="assetSubType" name="assetSubType"
							ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label></td>
						<td width="10%" valign="top" class="formth"><label
							class="set" id="assetQtyReq" name="assetQtyReq"
							ondblclick="callShowDiv(this);"><%=label.get("assetQtyReq")%></label></td>
						<td width="25%" valign="top" class="formth"><label
							class="set" id="assetInvCd" name="assetInvCd"
							ondblclick="callShowDiv(this);"><%=label.get("assetInvCd")%></label></td>
						<td width="7%" valign="top" class="formth"><label class="set"
							id="assetQtyAvailable" name="assetQtyAvailable"
							ondblclick="callShowDiv(this);"><%=label.get("assetQtyAvailable")%></label></td>
						<td width="7%" valign="top" class="formth"><label class="set"
							id="assetQtyTrans" name="assetQtyTrans"
							ondblclick="callShowDiv(this);"><%=label.get("assetQtyTrans")%></label></td>
						<td width="7%" valign="top" class="formth"><label class="set"
							id="assetCancReq" name="assetCancReq"
							ondblclick="callShowDiv(this);"><%=label.get("assetCancReq")%></label></td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="9" align="center"><font
								color="red">No requests from other warehouse</font></td>
						</tr>
					</s:if>
					<%!int i = 0;%>
					<%
						int k = 1;
						int c = 0;
						int cnt = pageNo * 20 - 20;
					%>

					<s:iterator value="recordList">

						<tr>
							<td class="border2" width="5%"><%=++cnt%>
							<td class="border2" width="20%"><s:property
								value="sourceWarehouseName" /><s:hidden name="destWarehouse" /></td>
							<td class="border2" width="20%"><s:property value="category" /></td>
							<s:hidden name="category" />
							<s:hidden name="categoryCode" />
							<s:hidden name="<%="assetMasterCode"+k%>" />

							<td class="border2" width="20%"><s:property value="subType" /><s:hidden
								name="subType" /><s:hidden name="subTypeCode" />&nbsp;</td>
							<s:hidden name="reqCode" />
							<td class="border2" width="10%"><s:property
								value="quantityRequired" />&nbsp;</td>
							<s:hidden name="sourceWarehouse"></s:hidden>
							<td class="border2" nowrap="nowrap" width="25%"><s:textfield
								name="<%="inventoryCode"+k%>" size="25" readonly="false"></s:textfield>
							<img src="../pages/images/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="showInventory('<s:property value="categoryCode"/>','<s:property value="subTypeCode"/>','<s:property value="<%=""+k%>"/>')"></td>
							<td class="border2" nowrap="nowrap" width="7%"><s:textfield
								name="<%="quantityAvailable"+k%>" size="5" readonly="true" /></td>
							<td class="border2" nowrap="nowrap" width="7%"><s:textfield
								name="quantityAssigned" id="<%="quantityAssigned"+k%>" size="5"
								onkeypress="return numbersOnly();"></s:textfield>&nbsp;</td>
							<td class="border2" nowrap="nowrap" width="7%"><input
								type="button" class="token" value="Cancel Request"
								onclick="return cancelRequest('<s:property value="reqCode"/>');" />
							</td>
						</tr>
						<%
							k++;
							c++;
						%>
					</s:iterator>
					<%
					i = k;
					%>
					<s:hidden name="rowId"></s:hidden>
					<s:hidden name="hiddenCategoryCode"></s:hidden>
					<s:hidden name="hiddenSubTypeCode"></s:hidden>
				</table>
				</td>
			</tr>
		</s:else>

	</table>

	<s:hidden name="tableList"></s:hidden>
	<s:hidden name="hiddenReqCode"></s:hidden>
	
	

</s:form>

<script>
		
	function callReset(){
			document.getElementById("paraFrm_status").value=='P';
			document.getElementById("paraFrm").action="AssetAssignment_input.action";
 			document.getElementById("paraFrm").submit();
 			document.getElementById("paraFrm").target="main";
 	
	 }
 function showInventory(categoryCode,subTypeCode,rowId){

	document.getElementById("paraFrm_hiddenCategoryCode").value=categoryCode;
	document.getElementById("paraFrm_hiddenSubTypeCode").value=subTypeCode;
	document.getElementById("paraFrm_rowId").value = rowId;
	
	callsF9(500,325,'AssetAssignment_f9actionInventory.action'); 
}
	function callAssignAsset(assetCode,availability){
		if (availability=="Not Available"){
			var	conf=confirm("Assets are "+availability+",\n Are you sure to view it now?");
			if(!conf){
				return false;
			}
			
			}else 
		if(availability=="Partially Available"){
		var	conf=confirm("Assets are "+ availability+ ",\n Are you sure to assign it now.");
		if(!conf){
		return false;
		}
		}
		
		document.getElementById('paraFrm_hiddenAssetCode').value = assetCode;
		//document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "AssetEmployee_showForAssignment.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
		
		
	}
	function callViewDetails(assetCode){
		document.getElementById('paraFrm_hiddenAssetCode').value = assetCode;
		document.getElementById('paraFrm').action = "AssetEmployee_callByAssigner.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	function callTransfer(){
		var length=document.getElementById("paraFrm_tableList").value;
		var invCount="0";
		if(length=="0" || length==""){
			alert("No assets to transfer !");
			return false;
		}
		for (count=1; count <= length;count++){
			if(document.getElementById("paraFrm_inventoryCode"+count).value==""){
				invCount =eval(invCount)+eval(1);
			}
		}
		if(invCount==length){
		alert("Please enter atleast one Inventory to transfer.");
		return false;
		}
		
		for (count1=1; count1 <= length;count1++){
		
			
						
			if(document.getElementById("paraFrm_inventoryCode"+count1).value!=""){
				if(document.getElementById("quantityAssigned"+count1).value=="")
				{
				alert("Please enter quantity to transfer.");
				document.getElementById("quantityAssigned"+count1).focus();
				return false;
				}
			}
			if(document.getElementById("quantityAssigned"+count1).value!=""){
				if(document.getElementById("paraFrm_inventoryCode"+count1).value=="")
				{
				alert("Please enter inventory to transfer.");
				document.getElementById("paraFrm_inventoryCode"+count1).focus();
				return false;
				}
			}
		}
		document.getElementById('paraFrm').action = "AssetAssignment_transferAsset.action";
		document.getElementById('paraFrm').submit();
	}
	
	function cancelRequest(reqCode){
			var conf=confirm("Do you really want to cancel this request ?");
  			if(conf) {
  			document.getElementById('paraFrm_hiddenReqCode').value = reqCode;
			document.getElementById('paraFrm').action = "AssetAssignment_cancelRequest.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
  			}
  			else {
  				return false;
  			}
	}	
</script>