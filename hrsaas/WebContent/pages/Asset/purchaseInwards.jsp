<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="PurchaseInward" validate="true" id="paraFrm"
	validate="true" theme="simple">
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
					Inwards </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" class="formbg">
				<tr>
					<td height="27" class="formtxt"><a
						href="PurchaseInward_checkData.action?status=P">Pending List</a> |
						<a
						href="PurchaseInward_checkData.action?status=I">Inward List</a> |
					<a href="PurchaseInward_checkData.action?status=C">Cancelled
					List</a></td>
				</tr>
			</table>
		</tr>
		<s:hidden name="listLength"></s:hidden>
		<s:hidden name="status" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="3"><strong>
					<%String status = (String)request.getAttribute("stat");
				 
	                    	if(status!=null)
	                    	{
	                    		out.println(status);
	                    	}
	                    	else{
	                    		out.println("Pending List");
	                    		}%>
					</strong></td>
					<s:hidden name="hiddenPurchaseCode" />
					<s:hidden name="empCodeHidden" />
				</tr>
				<tr>
					<s:if test="pendingFlag">
					
					<td width="5%" valign="top" class="formth"><label  class = "set"  id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
					<td width="15%" valign="top" class="formth"><label  class = "set"  id="employee.id" name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="30%" valign="top" class="formth"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td width="15%" valign="top" class="formth"><label  class = "set"  id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
					<td width="35%" valign="top" class="formth"><label  class = "set"  id="asset.inorder" name="asset.inorder" ondblclick="callShowDiv(this);"><%=label.get("asset.inorder")%></label>
					</s:if>
					<s:else>
					
					<td width="5%" valign="top" class="formth"><label  class = "set"  id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
					<td width="15%" valign="top" class="formth"><label  class = "set"  id="employee.id" name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="30%" valign="top" colspan="2" class="formth"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td width="15%" valign="top" colspan="4" class="formth"><label  class = "set"  id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
					</s:else>
				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="8" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%! int i = 0 ; %>
				<% int k = 1;  int c=0;%>

				<s:iterator value="recordList">

					<tr>
						

						<s:if test="pendingFlag">
						<td class="sortableTD" width="5%"><%=k %>
						<td class="sortableTD" width="15%"><s:property value="empToken" /><s:hidden
							name="purchaseCode" value="%{purchaseCode}" /><s:hidden
							name="level" /><s:hidden name="empID" /></td>
						<td class="sortableTD" width="30%"><s:property value="empName" /></td>
						<td class="sortableTD" width="15%" align="center"><s:property value="orderDate" /></td>
							<td class="sortableTD" width="35%" align="left"><input
								type="button" name="view_Details" class="token"
								value=" Add Inventory "
								onclick="viewDetails('<s:property value="purchaseCode"/>')" /> <!--<a href="<s:url action="LTCClaim_callByApprover"><s:param name="ltcCode" value="ltcID"/>
	      <s:param name="empCode" value="empID"/></s:url>" target="_blank">Show Details</a>-->
							<input type="button" class="token" value=" Cancel Order "
								onclick="return callCancelOrder('<s:property value="purchaseCode"/>',<s:property value="empID"/>)" />

							</td>
						</s:if><s:else>
						<td class="sortableTD"  width="5%"><%=k %>
						<td class="sortableTD"  width="15%"><s:property value="empToken" /><s:hidden
							name="purchaseCode" value="%{purchaseCode}" /><s:hidden
							name="level" /><s:hidden name="empID" /></td>
						<td class="sortableTD"  width="30%" colspan="2"><s:property value="empName" /></td>
						<td class="sortableTD"  width="15%" colspan="4" align="center"><s:property value="orderDate" /></td>
						
						</s:else>
					</tr>
					<%k++; c++; %>
				</s:iterator>
				<% i=k ; %>
			</table>
			</td>
		</tr>
		
		<!--  
		<tr>
			<input type="hidden" name="count" id="count" value="<%=c%>" />
			<td>
			<table width="100" border="0" align="right" cellpadding="2"
				cellspacing="2">
				<tr>
					<td><strong>Page </strong></td>
					<td><img src="../pages/images/recruitment/first.gif"
						width="10" height="10" /></td>
					<td><img src="../pages/images/recruitment/previous.gif"
						width="10" height="10" /></td>
					<td>
					<div align="center">0-1</div>
					</td>
					<td><img src="../pages/images/recruitment/next.gif" width="10"
						height="10" /></td>
					<td><img src="../pages/images/recruitment/last.gif" width="10"
						height="10" /></td>
				</tr>
			</table>
			</td>
		</tr>
	-->	
	</table>
</s:form>

<script>
	
	
	function viewDetails(purchaseCode){
		document.getElementById('paraFrm_hiddenPurchaseCode').value = purchaseCode;
		document.getElementById('paraFrm').action = "PurchaseOrder_callForInward.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function callCancelOrder(purchaseCode,empCode){
		
	 var conf=confirm("Do you really want to cancel this order ?");
  			if(conf) {
  			document.getElementById('paraFrm_hiddenPurchaseCode').value = purchaseCode;
  			document.getElementById('paraFrm_empCodeHidden').value = empCode;
			document.getElementById('paraFrm').action = "PurchaseInward_cancelOrder.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
  			}
  			else {
  				return false;
  			}
  			
	}	
</script>