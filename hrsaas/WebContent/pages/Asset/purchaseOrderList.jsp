<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PurchaseOrder" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="hiddencode" />
	<s:hidden name="hdeleteCode" />
	<s:hidden name="hiddenStatus" />
	<s:hidden name="myPageRejected" id="myPageRejected"></s:hidden>
	
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
					<td width="93%" class="txt"><strong class="text_head">Purchase Order</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<!-- Tab Options Stars -->
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td>
						<a href="#" onclick="setAction('P')">Pending Application</a> |
						<a href="#" onclick="setAction('A')">Approved List</a> |
						<a href="#" onclick="setAction('R')">Rejected List</a> 				
					</td>
					<script type="text/javascript">
							function setAction(listType)
							{
								if(listType == "P") {
									document.getElementById('paraFrm').action = 'PurchaseOrder_input.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "A") {
									document.getElementById('paraFrm').action = 'PurchaseOrder_getApprovedList.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "R") {
									document.getElementById('paraFrm').action = 'PurchaseOrder_getRejectedList.action';
									document.getElementById('paraFrm').submit();
								}
								
							}
						</script>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Tab Options Ends -->
		<!-- Pending Tab Starts -->
		<s:if test="%{listType=='pending'}">
			<!-- Draft Table Starts -->
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
								<td class="formth" width="10%"><b><label id="purchaseorder.srno"
									name="purchaseorder.srno" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.srno")%></label></b></td>
								<td class="formth" width="10%"><b><label id="purchaseorder.purchaseId"
									name="purchaseorder.purchaseId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.purchaseId")%></label></b></td>
								<td class="formth" width="22%"><b><label id="purchaseorder.emptoken" name="purchaseorder.emptoken"
									class="set" ondblclick="callShowDiv(this)"> <%=label.get("purchaseorder.emptoken")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label id="purchaseorder.empname"
									name="purchaseorder.empname" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.empname")%></label></b></td>
								<td class="formth" width="25%"><b><label id="purchaseorder.orderdate"
									name="purchaseorder.orderdate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.orderdate")%></label></b></td>
								<td class="formth" width="13%"><b><label id="purchaseorder.edit"
									name="purchaseorder.edit" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.edit")%></label></b></td>
							</tr>
							<% int i=0; %>
							<s:iterator value="draftList">
								<tr  class="tableCell1" >
									<td class="sortableTD" width="10%"><%=++i %></td>
									<td class="sortableTD" width="10%" nowrap="nowrap">&nbsp;<s:property value="purchaseNoItt"/> </td>
									<td class="sortableTD" width="22%" nowrap="nowrap">&nbsp;<s:property value="empToken" /></td>
									<td class="sortableTD" width="20%" nowrap="nowrap">&nbsp;<s:property value="empName" /></td>
									<td class="sortableTD" width="25%" align="center" >&nbsp;<s:property value="orderDate" /></td>
									<td class="sortableTD" width="13%" align="center"><input
										type="button" name="view_Details" class="token"
										value=" Edit Application "
										onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>
									
								</tr>
							</s:iterator>
							<%if(i==0){ %>
							<tr>
								<td colspan="6" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
							</tr>
							<%} %>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Draft Table Ends -->
			<!-- Submitted List Starts -->
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
								<td class="formth" width="10%"><b><label id="purchaseorder.srno"
									name="purchaseorder.srno" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.srno")%></label></b></td>
								<td class="formth" width="10%"><b><label id="purchaseorder.purchaseId"
									name="purchaseorder.purchaseId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.purchaseId")%></label></b></td>
								<td class="formth" width="22%"><b><label id="purchaseorder.emptoken" name="purchaseorder.emptoken"
									class="set" ondblclick="callShowDiv(this)"> <%=label.get("purchaseorder.emptoken")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label id="purchaseorder.empname"
									name="purchaseorder.empname" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.empname")%></label></b></td>
								<td class="formth" width="25%"><b><label id="purchaseorder.orderdate"
									name="purchaseorder.orderdate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.orderdate")%></label></b></td>
								<td class="formth" width="13%"><b><label id="purchaseorder.view"
									name="purchaseorder.view" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.view")%></label></b></td>
							</tr>
							<% int j=0; %>
							<s:iterator value="submittedList">
								<tr  class="tableCell1" >

									<td class="sortableTD" width="10%"><%=++j %></td>
									<td class="sortableTD" width="10%" nowrap="nowrap">&nbsp;<s:property value="purchaseNoItt"/></td>
									<td class="sortableTD" width="22%" nowrap="nowrap"><s:property value="empToken" /></td>
									<td class="sortableTD" width="20%" nowrap="nowrap"><s:property value="empName" /></td>
									<td class="sortableTD" width="25%" align="center" ><s:property value="orderDate" />&nbsp;</td>
									<td class="sortableTD" width="13%" align="center" nowrap="nowrap"><input
										type="button" name="view_Details" class="token"
										value=" View Application "
										onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>
									
								</tr>
							</s:iterator>
							<%if(j==0){ %>
							<tr>
								<td colspan="7" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
							</tr>
							<%} %>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Submitted List Ends -->
			<!-- Returned List Starts -->
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
								<td class="formth" width="10%"><b><label id="purchaseorder.srno"
									name="purchaseorder.srno" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.srno")%></label></b></td>
								<td class="formth" width="10%"><b><label id="purchaseorder.purchaseId"
									name="purchaseorder.purchaseId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.purchaseId")%></label></b></td>
								<td class="formth" width="22%"><b><label id="purchaseorder.emptoken" name="purchaseorder.emptoken"
									class="set" ondblclick="callShowDiv(this)"> <%=label.get("purchaseorder.emptoken")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label id="purchaseorder.empname"
									name="purchaseorder.empname" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.empname")%></label></b></td>
								<td class="formth" width="25%"><b><label id="purchaseorder.orderdate"
									name="purchaseorder.orderdate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.orderdate")%></label></b></td>
								<td class="formth" width="13%"><b><label id="purchaseorder.edit"
									name="purchaseorder.edit" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.edit")%></label></b></td>
							</tr>
							<% int k=0; %>
							<s:iterator value="returnedList">
								<tr  class="tableCell1" >

									<td class="sortableTD" width="10%"><%=++k %></td>
									<td class="sortableTD" width="10%" nowrap="nowrap">&nbsp;<s:property value="purchaseNoItt"/></td>
									<td class="sortableTD" width="22%"><s:property value="empToken" /></td>
									<td class="sortableTD" width="20%" nowrap="nowrap"><s:property value="empName" /></td>
									<td class="sortableTD" width="25%" align="center" ><s:property value="orderDate" />&nbsp;</td>
									<td class="sortableTD" width="13%" align="center" nowrap="nowrap"><input
										type="button" name="view_Details" class="token"
										value=" Edit Application "
										onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>
									
								</tr>
							</s:iterator>
							<%if(k==0){ %>
							<tr>
								<td colspan="7" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
							</tr>
							<%} %>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Returned List Ends -->
		</s:if>
		<s:hidden name="myPage" id="myPage" />
		<!-- Approved Tab Starts -->
		<s:if test="%{listType=='approved'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Approved Application List</b></td>
					</tr>
					<tr>
						<% int totalPage1 = 0; int pageNo1 = 0; %>
						<s:if test="listLengthApproved">
							<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
							<%	 totalPage1 = (Integer) request.getAttribute("totalPage1");
										 pageNo1 = (Integer) request.getAttribute("pageNo1");
									%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage1%>', 'PurchaseOrder_getApprovedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage1%>', 'PurchaseOrder_getApprovedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo1%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage1%>', 'PurchaseOrder_getApprovedList.action');return numbersOnly();" />
							of <%=totalPage1%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage1%>', 'PurchaseOrder_getApprovedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage1%>', 'L', '<%=totalPage1%>', 'PurchaseOrder_getApprovedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</s:if>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%"><b><label id="purchaseorder.srno"
									name="purchaseorder.srno" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.srno")%></label></b></td>
								<td class="formth" width="10%"><b><label id="purchaseorder.purchaseId"
									name="purchaseorder.purchaseId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.purchaseId")%></label></b></td>
								<td class="formth" width="22%"><b><label id="purchaseorder.emptoken" name="purchaseorder.emptoken"
									class="set" ondblclick="callShowDiv(this)"> <%=label.get("purchaseorder.emptoken")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label id="purchaseorder.empname"
									name="purchaseorder.empname" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.empname")%></label></b></td>
								<td class="formth" width="25%"><b><label id="purchaseorder.orderdate"
									name="purchaseorder.orderdate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.orderdate")%></label></b></td>
								<td class="formth" width="13%"><b><label id="purchaseorder.view"
									name="purchaseorder.view" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.view")%></label></b></td>
							</tr>
							<% int m=0; %>
							<s:if test="listLengthApproved">
								<s:iterator value="approvedList">
									<tr  class="tableCell1" >

										<td class="sortableTD" width="10%"><%=++m %></td>
										<td class="sortableTD" width="10%" nowrap="nowrap">&nbsp;<s:property value="purchaseNoItt"/></td>
										<td class="sortableTD" width="22%" nowrap="nowrap"><s:property value="empToken" /></td>
										<td class="sortableTD" width="20%" nowrap="nowrap"><s:property value="empName" /></td>
										<td class="sortableTD" width="25%" align="center" ><s:property value="orderDate" />&nbsp;</td>
										<td class="sortableTD" width="13%" align="center" nowrap="nowrap"><input
											type="button" name="view_Details" class="token"
											value=" View Application "
											onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>
										
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="7" class="sortableTD" align="center" width="100%">
									<font color="red">No Data to display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<s:if test="listLengthApproved">
					<td align="right"><b>Total No. of Records: <s:property
						value="totalRecords" /></b></td>
				</s:if>
			</tr>
		</s:if>
		<!-- Approved Tab Ends -->
		
		<s:if test="%{listType=='rejected'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Rejected Applications List</b></td>
					</tr>
					
					
						<tr>

						<%
							int totalPageRejected = 0;
							int PageNoRejected = 0;
						%>

						<s:if test="listLengthRejected">
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageRejected = (Integer) request
 			.getAttribute("totalPageRejected");
 	PageNoRejected = (Integer) request.getAttribute("PageNoRejected");
 %> <a href="#"
									onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'PurchaseOrder_getRejectedList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'PurchaseOrder_getRejectedList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField3"
									id="pageNoField3" size="3" value="<%=PageNoRejected%>"
									maxlength="10"
									onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'PurchaseOrder_getRejectedList.action');return numbersOnly();" />
								of <%=totalPageRejected%> <a href="#"
									onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'PurchaseOrder_getRejectedList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'PurchaseOrder_getRejectedList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
						</s:if>
					</tr>
					
					
			 
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%"><b><label id="purchaseorder.srno"
									name="purchaseorder.srno" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.srno")%></label></b></td>
								<td class="formth" width="10%"><b><label id="purchaseorder.purchaseId"
									name="purchaseorder.purchaseId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("purchaseorder.purchaseId")%></label></b></td>
								<td class="formth" width="22%"><b><label id="purchaseorder.emptoken" name="purchaseorder.emptoken"
									class="set" ondblclick="callShowDiv(this)"> <%=label.get("purchaseorder.emptoken")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label id="purchaseorder.empname"
									name="purchaseorder.empname" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.empname")%></label></b></td>
								<td class="formth" width="25%"><b><label id="purchaseorder.orderdate"
									name="purchaseorder.orderdate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.orderdate")%></label></b></td>
								<td class="formth" width="13%"><b><label id="purchaseorder.view"
									name="purchaseorder.view" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("purchaseorder.view")%></label></b></td>
							</tr>
							<% int n=0; %>
							<s:if test="listLengthRejected">
								<s:iterator value="rejectedList">
									<tr  class="tableCell1" >

										<td class="sortableTD" width="10%"><%=++n %></td>
										<td class="sortableTD" width="10%" nowrap="nowrap">&nbsp;<s:property value="purchaseNoItt"/></td>
										<td class="sortableTD" width="22%" nowrap="nowrap"><s:property value="empToken" /></td>
										<td class="sortableTD" width="43%" nowrap="nowrap"><s:property value="empName" /></td>
										<td class="sortableTD" width="25%" align="center" ><s:property value="orderDate" />&nbsp;</td>
										<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><input
											type="button" name="view_Details" class="token"
											value=" View Application "
											onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>
										
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="7" class="sortableTD" align="center" width="100%">
									<font color="red">No Data to display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<s:if test="listLengthRejected">
					<td align="right"><b>Total No. of Records: <s:property
						value="totalRecords" /></b></td>
				</s:if>
			</tr>
		</s:if>
		<!-- Rejected Tab Ends -->
		
	
		
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
	function addapplicationFun(){
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="PurchaseOrder_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	function callForEdit(id,s) {
		document.getElementById('paraFrm_hiddencode').value = id;
		document.getElementById('paraFrm_hiddenStatus').value = s;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "PurchaseOrder_callforedit.action";
		document.getElementById('paraFrm').submit();
	}
	function viewDetails_OLD(id,s) {
		
		alert("id  "+id);
		document.getElementById('paraFrm_hiddencode').value = id;
		document.getElementById('paraFrm_hiddenStatus').value = s;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "PurchaseOrder_callforedit.action";
		document.getElementById('paraFrm').submit();
	}
	
	function viewDetails(id,s) {
		
		///alert("id  "+id);
		document.getElementById('paraFrm_hiddencode').value = id;
		document.getElementById('paraFrm_hiddenStatus').value = s;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PurchaseOrder_callforedit.action?applicationCode='+id+'&applStatus='+s;
		document.getElementById('paraFrm').submit();
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
			cell.className='tableCell2';
		}
		else cell.className='tableCell1';
	}
	
	function newRowColor(cell) {
		 cell.className='Cell_bg_first'; 
	}
	
	
	
	function callPageRejected(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField3').value;	
		actPage = document.getElementById('myPageRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField3').value = actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField3').value=actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField3').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	
	function callPageTextReject(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField3').value;
		 	var actPage = document.getElementById('myPageRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField3').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField3').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	
	
	
	
	
</script>