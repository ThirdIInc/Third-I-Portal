<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>

<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="VoucherApprovalMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" align="right" cellpadding="2" cellspacing="1"
		class="formbg">
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="status" />
		<tr>
			<td>
			<table width="100%" align="center" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head">
					<img src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Voucher
					Approval</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td height="27" class="formtxt"><a
						href="VoucherApprovalMaster_callstatus.action?status=P">Pending
					List</a> | <a href="VoucherApprovalMaster_callstatus.action?status=A">Approved
					List</a> | <a href="VoucherApprovalMaster_callstatus.action?status=R">Rejected
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
					<s:if test="${status=='A'}"><td><b>Approved List</b></td></s:if>
					<s:elseif test="${status=='R'}"><td><b>Rejected List</b></td></s:elseif>
					<s:else><td><b>Pending List</b></td></s:else>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">				
				<%
					int totalPage = 0;
					int pageNo = 0;
				%>
				<tr>
					<td colspan="3" class="formtxt"><strong> <!--<%
                    			//String stat = (String) request.getAttribute("stat");
	                    		//if (stat != null) {
	                    		//	out.println(stat);
	                    		//} else {
	                    		//	out.println("Pending List");
	                    		//}
	                    	%>
	                    	--></strong></td>
					<!-- Paging Start -->
					<s:if test="listLengthPage">
						<td id="ctrlShow" width="100%" align="right" colspan="3"><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						 %> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage%>', 'VoucherApprovalMaster_collect.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /></a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage%>', 'VoucherApprovalMaster_collect.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
								maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'VoucherApprovalMaster_collect.action');return numbersOnly();" />
								of <%=totalPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage%>', 'VoucherApprovalMaster_collect.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /></a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'VoucherApprovalMaster_collect.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
					<!-- Paging Start ending -->
				</tr>				
				<tr>
					<td width="5%" valign="top" class="formth"><label class="set"
						name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>.</td>
					<td width="10%" valign="top" class="formth"><label class="set"
						name="employee.id" id="employee.id"
						ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="10%" valign="top" class="formth"><label class="set"
						name="employee.name" id="employee.name"
						ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></td>
					<td width="10%" valign="top" class="formth"><label class="set"
						name="application.date" id="application.date"
						ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label></td>
					<td width="10%" valign="top" class="formth"><label class="set"
						name="total.amount" id="total.amount"
						ondblclick="callShowDiv(this);"><%=label.get("total.amount")%></label></td>
					<td width="10%" valign="top" class="formth"><label class="set"
						name="viewDetails" id="viewDetails"
						ondblclick="callShowDiv(this);"><%=label.get("viewDetails")%></label></td>
				</tr>
				<s:if test="noData">
					<tr>
						<td></td>
						<td width="100%" colspan="3" align="right"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%! int i = 0; %>
				<% int k = 1; %>
				<%
				    int cnt=1;
					k = pageNo * 20 - 20;
				%>
				<s:iterator value="list">
					<tr>
						<td class="sortableTD" width="5%" align="center"><%=cnt++%>
						<td class="sortableTD" width="30%" align="left"><s:property
							value="tokenNo" /> <s:hidden name="voucherNo" /> <s:hidden
							name="level" /> <s:hidden name="empCode" /><s:hidden name="checkStatus"/></td>
						<td class="sortableTD" width="30%" align="left"><s:property
							value="empName" /></td>
						<td class="sortableTD" width="10%" align="center"><s:property
							value="date" /> <s:hidden name="date" /></td>
						<td class="sortableTD" width="20%" align="center"><s:property
							value="totalAmt" /></td>
						<td class="sortableTD" width="5%" align="center"><input
							type="button" name="view_Details" class="token"
							value=" View Details "
							onclick="viewDetails('<s:property value="voucherNo"/>','<s:property value="checkStatus"/>')"/></td>
					</tr>
					<% k++; %>
				</s:iterator>
				<% i = k; %>
				<input type="hidden" name="count" id="count" value="<%=k-1%>" />
				<s:hidden name="listLength" />
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
/*function saveValidate(obj) {
		var count = document.getElementById("count").value;
	 	var s = "";		
		if(count == 0) {
			alert("There is no record to save");
			return false;
		} 
		else {
			for (var length = 1 ;length <=eval(count);length++ ) {
				if(document.getElementById("check"+length).value !="P") {
					s="changed";
				}
			}			
			if(s != "changed") {
				alert("Please change the "+document.getElementById('stat').innerHTML.toLowerCase());
				return false;
			}			
			obj.disabled = true;
			document.getElementById("paraFrm").action="VoucherApprovalMaster_save.action";
		 	document.getElementById("paraFrm").submit();
 		}
	}*/
		
function callReset() {
		document.getElementById("paraFrm").action="VoucherApprovalMaster_input.action";
 		document.getElementById("paraFrm").submit();
}
	
/*function callOnload() {
	 	var check = <%=i%>;	 	
	 	if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R') {
			for(var k = 1;k < check ;k++ ) {
				document.getElementById("check"+k).disabled = true;	
			}
		}
	}*/
	
function viewDetails(voucherNo,stat) {
		//document.getElementById('paraFrm_voucherNoView').value = voucherNo;
		document.getElementById('paraFrm').action = "VoucherApplication_retriveForApproval.action?voucherCode="+voucherNo+"&status="+stat;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}	
</script>