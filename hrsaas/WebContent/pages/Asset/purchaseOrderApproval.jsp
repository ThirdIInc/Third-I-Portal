<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="PurchaseOrderApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="applicationLevel"></s:hidden>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Purchase
					Order Approval </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="2" class="formbg">
				<tr>
					<td width="78%">
				<tr>
					<td height="27" class="formtxt"><a
						href="PurchaseOrderApproval_checkData.action?status=P">Pending
					List</a> | <a href="PurchaseOrderApproval_checkData.action?status=A">Approved
					List</a> | <a href="PurchaseOrderApproval_checkData.action?status=R">Rejected
					List</a></td>
				</tr>
				<s:hidden name="listLength"></s:hidden>
				</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<td width="50%" align="left"></td>

					<div align="right"><s:hidden name="myPage" /> <% int totalPage = 0; int pageNo = 0; %>
					<s:if test="noData">
					</s:if> <s:else>
						<td id="ctrlShow" width="100%" align="right" class=""><b>Page:</b>
						<%	 totalPage = (Integer) request.getAttribute("totalPage");
										 pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'PurchaseOrderApproval_checkData.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'PurchaseOrderApproval_checkData.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'PurchaseOrderApproval_checkData.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'PurchaseOrderApproval_checkData.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PurchaseOrderApproval_checkData.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:else></div>

					<s:hidden name="status" />
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td height="27" class="formtxt" colspan="3"><strong>
					<%
                    		String status = (String) request.getAttribute("stat");
                    		if (status != null) {
                    			out.println(status);
                    		} else {
                    			out.println("Pending List");
                    		}
                    	%>
					</strong></td>
				</tr>
				<tr>
					<td width="5%" valign="top" class="formth"><label class="set"
						id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
					<td class="formth" width="10%"><b><label id="purchaseId"
						name="purchaseId" class="set" ondblclick="callShowDiv(this);">
					<%=label.get("purchaseId")%></label></b></td>
					<td width="10%" valign="top" class="formth"><label class="set"
						id="employee.id" name="employee.id"
						ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="23%" valign="top" class="formth"><label class="set"
						id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td width="20%" valign="top" class="formth"><label class="set"
						id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>

					<td width="32%" valign="top" class="formth"><label class="set"
						id="details" name="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="8" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%!int i = 0;%>
				<%
                        	int k = 1;
                        	int c = 0;
                        %>
                        
                        <%
							int cn5 = pageNo * 20 - 20;
							%>

				<s:iterator value="recordList">
					<tr class="sortableTD">
						<td class="sortableTD" width="5%" nowrap="nowrap">&nbsp;<%=++cn5%>
						<td class="sortableTD" width="10%">&nbsp;<s:property
							value="purchaseNoItt" /></td>
						<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
							value="empToken" /><s:hidden name="purchaseCode"
							value="%{purchaseCode}" /><s:hidden name="level" /><s:hidden
							name="empID" /></td>
						<td class="sortableTD" width="23%" nowrap="nowrap"><s:property
							value="empName" /></td>
						<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><s:property
							value="orderDate" /></td>
						<s:hidden name="hiddenPurchaseCode" />
						<s:hidden name="empCode" value="%{empID}" />
						<td class="td_bottom_border" width="32%" align="center">
						<%	
					if (status != null && status.trim().equals("Pending List"))
					{
				   %> <input type="button" name="view_Details" class="token"
							value="View / Approve"
							onclick="viewDetails('<s:property value="purchaseCode"/>','<s:property value="level"/>')" />
						<%}else{ %> <input type="button" name="view_Details" class="token"
							value="View Details"
							onclick="viewDetails('<s:property value="purchaseCode"/>','<s:property value="level"/>')" />

						<%} %>
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
			</table>
			</td>
		</tr>


	</table>
</s:form>
<script>
	// callOnload();
	
	function saveValidate(obj){
	//alert("safd"+document.getElementById("count").value);
	//alert(document.getElementById("paraFrm_listLength").value);
	if(document.getElementById("count").value==0){
			alert("There is no record to save");
			return false;
		}else{
		var count = document.getElementById("count").value;
		var status ="";
		for (var length = 1 ;length <=count;length++ ){
			if(document.getElementById("check"+length).value !="P"){
				status="changed";
			}
		}
		if(status != "changed"){
			alert("Please change the status of atleast one application .");
			return false;
		}
		obj.disabled= true;
	document.getElementById("paraFrm").action="PurchaseOrderApproval_save.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
		function callReset(){
			document.getElementById("paraFrm_status").value=='P';
			
			document.getElementById("paraFrm").action="PurchaseOrderApproval_input.action";
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
	
	
	function viewDetails_OLD(assetCode,level){
		document.getElementById('paraFrm_hiddenPurchaseCode').value = assetCode;
		document.getElementById('paraFrm_applicationLevel').value = level;
		document.getElementById('paraFrm').action = 'PurchaseOrder_callByApprover.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
	
	function viewDetails(assetCode,level){

		document.getElementById('paraFrm').action = 'PurchaseOrder_callByApprover.action?hiddenPurchaseCode='+assetCode+'&level='+level;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
</script>