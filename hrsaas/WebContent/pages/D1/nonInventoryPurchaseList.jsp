<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="NonInventoryPurchase" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt"><s:hidden name="myPage"
				id="myPage" /> <s:hidden name="myPage1" id="myPage1" /> <s:hidden
				name="myPage2" id="myPage2" />
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Non
					Inventory Purchase List </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><s:hidden name="nonInventoryCode" /> <input type="submit"
						value="Add Application" class="add" onclick="addapplicationFun();">
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- 
		<a href="#" onclick="setAction('CC')"> Cancelled
					Applications</a> |
		 -->

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><a href="#" onclick="setAction('')">Pending
					Application</a>| <a href="#" onclick="setAction('AA')">Approved
					List</a> | <a href="#" onclick="setAction('RR')">Rejected List</a> <s:hidden
						name="flag" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<%
			int totalPage = 0;
			int pageNo = 0;
		%>
		<s:if test="%{flag == ''}">
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="2"><strong> Draft</strong></td>
						<td width="70%" colspan="3">
						<table width="100%" cellpadding="1" cellspacing="1">
							<s:if test="listDraft ||listReject||listApprove">
								<tr>


									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<%
											try {
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										} catch (Exception e) {

										}
									%> <a href="#"
										onclick="callPageLocal('1', 'F', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageLocal('P', 'P', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageTextLocal(event, '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPageLocal('N', 'N', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageLocal('<%=totalPage%>', 'L', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" class="formbg">
							<tr>
								<td class="formth" width="05%">Sr No</td>
								<td class="formth" width="25%"><label class="set"
									name="tracking.no" id="tracking.no"
									ondblclick="callShowDiv(this);"> <%=label.get("tracking.no")%></label>
								</td>
								<td class="formth" width="25%"><label class="set"
									name="employee" id="employee4" ondblclick="callShowDiv(this);">
								<%=label.get("employee")%></label></td>
								<td class="formth" width="25%" align="center"><label
									class="set" name="aplicationDate" id="aplicationDate"
									ondblclick="callShowDiv(this);"> <%=label.get("aplicationDate")%></label>
								</td>
								<td class="formth" width="25%" align="center">Edit
								Application</td>
							</tr>
							<%
							int i = 1;
							%>
							<s:if test="listDraft">

								<s:iterator value="listDraft">
									<tr>
										<td class="sortableTD"><%=i++%></td>
										<td class="sortableTD"><s:hidden name="ittEmployeeId" /><s:hidden
											name="ittnonInventoryCode" /> <s:property
											value="ittEmployeeToken" />&nbsp;</td>
										<td class="sortableTD"><s:property value="ittEmployee" />
										</td>
										<td align="center" class="sortableTD"><s:property
											value="ittApplicationDate" /></td>
										<td align="center" class="sortableTD"><input
											type="submit" value="Edit Application" class="token"
											onclick="editapplicationFun('<s:property value="ittnonInventoryCode"/>','D');">
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<td align="center" colspan="5" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
							</tr>
						</table
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>


		<s:if test="%{flag =='AA'}">
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="2"><strong> Approved Application</strong></td>
						<td width="70%" colspan="3">
						<table width="100%" cellpadding="1" cellspacing="1">
							<s:if test="listDraft ||listReject||listApprove">
								<tr>
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<%
											try {
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										} catch (Exception e) {

										}
									%> <a href="#"
										onclick="callPageLocal('1', 'F', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageLocal('P', 'P', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageTextLocal(event, '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPageLocal('N', 'N', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageLocal('<%=totalPage%>', 'L', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" class="formbg">

							<tr>
								<td class="formth" width="05%">Sr No</td>
								<td class="formth" width="25%"><label class="set"
									name="tracking.no" id="tracking.no3"
									ondblclick="callShowDiv(this);"> <%=label.get("tracking.no")%></label>
								</td>
								<td class="formth" width="25%"><label class="set"
									name="employee" id="employee1" ondblclick="callShowDiv(this);">
								<%=label.get("employee")%></label></td>
								<td class="formth" width="25%" align="center"><label
									class="set" name="aplicationDate" id="aplicationDate3"
									ondblclick="callShowDiv(this);"> <%=label.get("aplicationDate")%></label>
								</td>
								<td class="formth" width="25%" align="center">View
								Application</td>
							</tr>

							<s:if test="listApprove">
								<%
								int kk = 1;
								%>
								<s:iterator value="listApprove">
									<tr>
										<td class="sortableTD"><%=kk++%></td>
										<td class="sortableTD"><s:hidden name="ittEmployeeId" /><s:hidden
											name="ittnonInventoryCode" /> <s:property
											value="ittEmployeeToken" /></td>
										<td class="sortableTD"><s:property value="ittEmployee" />
										</td>
										<td align="center" class="sortableTD"><s:property
											value="ittApplicationDate" /></td>
										<td align="center" class="sortableTD"><input
											type="submit" value="View Application" class="token"
											onclick="editapplicationFun('<s:property value="ittnonInventoryCode"/>','A');">
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<td align="center" colspan="5" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
							</tr>
						</table
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>


		<s:if test="%{flag =='RR'}">
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="2"><strong> Rejected Application</strong></td>
						<td width="70%" colspan="3">
						<table width="100%" cellpadding="1" cellspacing="1">
							<s:if test="listDraft ||listReject||listApprove">
								<tr>
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<%
											try {
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										} catch (Exception e) {

										}
									%> <a href="#"
										onclick="callPageLocal('1', 'F', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageLocal('P', 'P', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageTextLocal(event, '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPageLocal('N', 'N', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageLocal('<%=totalPage%>', 'L', '<%=totalPage%>', 'NonInventoryPurchase_input.action','pageNoField','myPage');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" class="formbg">

							<tr>
								<td class="formth" width="05%">Sr No</td>
								<td class="formth" width="25%"><label class="set"
									name="tracking.no" id="tracking.no4"
									ondblclick="callShowDiv(this);"> <%=label.get("tracking.no")%></label>
								</td>
								<td class="formth" width="25%"><label class="set"
									name="employee" id="employee" ondblclick="callShowDiv(this);">
								<%=label.get("employee")%></label></td>
								<td class="formth" width="25%" align="center"><label
									class="set" name="aplicationDate" id="aplicationDate4"
									ondblclick="callShowDiv(this);"> <%=label.get("aplicationDate")%></label>
								</td>
								<td class="formth" width="25%" align="center">View
								Application</td>
							</tr>
							<s:if test="listReject">
								<%
								int r = 1;
								%>
								<s:iterator value="listReject">
									<tr>
										<td class="sortableTD"><%=r++%></td>
										<td class="sortableTD"><s:hidden name="ittEmployeeId" /><s:hidden
											name="ittnonInventoryCode" /> <s:property
											value="ittEmployeeToken" /></td>
										<td class="sortableTD"><s:property value="ittEmployee" />
										</td>
										<td align="center" class="sortableTD"><s:property
											value="ittApplicationDate" /></td>
										<td align="center" class="sortableTD"><input
											type="submit" value="View Application" class="token"
											onclick="editapplicationFun('<s:property value="ittnonInventoryCode"/>','R');">
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<td align="center" colspan="5" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
							</tr>
						</table
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!--  ********************   -->


		<!-- ********************* -->
		<s:if test="%{flag == ''}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td colspan="2"><strong> Applications In Process</strong></td>
						<td colspan="3" align="right"><s:if test="listInProgress">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<%
										int totalPageP = 0;
										int pageNoP = 0;
									%>

									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<%
											try {
											totalPageP = (Integer) request.getAttribute("totalPageP");
											pageNoP = (Integer) request.getAttribute("pageNoP");
										} catch (Exception e) {

										}
									%> <a href="#"
										onclick="callPageLocal('1', 'F', '<%=totalPageP%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage1');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageLocal('P', 'P', '<%=totalPageP%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage1');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoFieldP"
										id="pageNoFieldP" size="3" value="<%=pageNoP%>" maxlength="10"
										onkeypress="callPageTextLocal(event, '<%=totalPageP%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage1');return numbersOnly();" />
									of <%=totalPageP%> <a href="#"
										onclick="callPageLocal('N', 'N', '<%=totalPageP%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage1')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageLocal('<%=totalPageP%>', 'L', '<%=totalPageP%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage1');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>

								</tr>
							</table>
						</s:if></td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" class="formbg">


							<tr>
								<td class="formth" width="05%">Sr No</td>
								<td class="formth" width="25%"><label class="set"
									name="tracking.no" id="tracking.no1"
									ondblclick="callShowDiv(this);"> <%=label.get("tracking.no")%></label>
								</td>
								<td class="formth" width="25%"><label class="set"
									name="employee" id="employee3" ondblclick="callShowDiv(this);">
								<%=label.get("employee")%></label></td>
								<td class="formth" width="25%" align="center"><label
									class="set" name="aplicationDate" id="aplicationDate1"
									ondblclick="callShowDiv(this);"> <%=label.get("aplicationDate")%></label>
								</td>
								<td class="formth" width="25%" align="center">View
								Application</td>
							</tr>
							<%
							int ii = 1;
							%>
							<s:if test="listInProgress">
								<s:iterator value="listInProgress">
									<tr>
										<td class="sortableTD"><%=ii++%></td>
										<td class="sortableTD"><s:hidden name="ittEmployeeId" /><s:hidden
											name="ittnonInventoryCode" /> <s:property
											value="ittEmployeeToken" /></td>
										<td class="sortableTD"><s:property value="ittEmployee" />
										</td>
										<td align="center" class="sortableTD"><s:property
											value="ittApplicationDate" /></td>
										<td align="center" class="sortableTD"><input
											type="submit" value="View Application" class="token"
											onclick="editapplicationFun('<s:property value="ittnonInventoryCode"/>','P');">
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<td align="center" colspan="5" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
							</tr>
						</table
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>


		<s:if test="%{flag == ''}">
			<tr>
				<td colspan="5">
				<table width="100%" class="formbg" border="0">
					<tr>
						<td colspan="3"><strong> Returned Applications </strong></td>
						<td colspan="2" align="right"><s:if test="listSendBack">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<%
										int totalPageB = 0;
										int pageNoB = 0;
									%>

									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<%
										try {
											totalPageB = (Integer) request.getAttribute("totalPageB");
											pageNoB = (Integer) request.getAttribute("pageNoB");
										} catch (Exception e) {

										}
									%> <a href="#"
										onclick="callPageLocal('1', 'F', '<%=totalPageB%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage2');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageLocal('P', 'P', '<%=totalPageB%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage2');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoFieldP"
										id="pageNoFieldP" size="3" value="<%=pageNoB%>" maxlength="10"
										onkeypress="callPageTextLocal(event, '<%=totalPageB%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage2');return numbersOnly();" />
									of <%=totalPageB%> <a href="#"
										onclick="callPageLocal('N', 'N', '<%=totalPageB%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage2')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageLocal('<%=totalPageB%>', 'L', '<%=totalPageB%>', 'NonInventoryPurchase_input.action','pageNoFieldP','myPage2');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>

								</tr>
							</table>
						</s:if></td>



					</tr>

					<tr>
						<td colspan="5">
						<table width="100%" class="formbg">

							<tr>
								<td class="formth" width="05%">Sr No</td>
								<td class="formth" width="25%"><label class="set"
									name="tracking.no" id="tracking.no2"
									ondblclick="callShowDiv(this);"> <%=label.get("tracking.no")%></label>
								</td>
								<td class="formth" width="25%"><label class="set"
									name="employee" id="employee2" ondblclick="callShowDiv(this);">
								<%=label.get("employee")%></label></td>
								<td class="formth" width="25%" align="center"><label
									class="set" name="aplicationDate" id="aplicationDate2"
									ondblclick="callShowDiv(this);"> <%=label.get("aplicationDate")%></label>
								</td>
								<td class="formth" width="25%" align="center">Edit
								Application</td>
							</tr>
							<%
							int k = 1;
							%>

							<s:if test="listSendBack">
								<s:iterator value="listSendBack">
									<tr>
										<td class="sortableTD"><%=k++%></td>
										<td class="sortableTD"><s:hidden name="ittEmployeeId" /><s:hidden
											name="ittnonInventoryCode" /> <s:property
											value="ittEmployeeToken" /></td>
										<td class="sortableTD"><s:property value="ittEmployee" />
										</td>
										<td align="center" class="sortableTD"><s:property
											value="ittApplicationDate" /></td>
										<td align="center" class="sortableTD"><input
											type="submit" value="Edit Application" class="token"
											onclick="editapplicationFun('<s:property value="ittnonInventoryCode"/>','B');">
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<td align="center" colspan="5" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
							</tr>
						</table
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

			<tr>
				<td width="100%">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><input type="submit" value="Add Application" class="add"
							onclick="addapplicationFun();"></td>
					</tr>
				</table>
				</td>
			</tr>
	</table>
</s:form>

<script>
 function setAction(listType){
	document.getElementById('myPage').value = "1";
	document.getElementById('myPage1').value = "1";
	document.getElementById('myPage2').value = "1";
	document.getElementById('paraFrm_flag').value=listType;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'NonInventoryPurchase_input.action';
	document.getElementById('paraFrm').submit(); 
 }


function addapplicationFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'NonInventoryPurchase_addApplication.action';
	document.getElementById('paraFrm').submit();
}
	
function editapplicationFun(id,status) {
	document.getElementById('paraFrm_nonInventoryCode').value=id;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'NonInventoryPurchase_editApplication.action?status='+status;
	document.getElementById('paraFrm').submit();
}
	
	
function editapplicationResubmitFun(id) {
	document.getElementById('paraFrm_nonInventoryCode').value=id;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'NonInventoryPurchase_editApplicationResubmit.action';
	document.getElementById('paraFrm').submit();
}
	
	
function callPageLocal(id, pageImg, totalPageHid, action,pageNoId,myPage) {		
		pageNo = document.getElementById(pageNoId).value;	
		actPage = document.getElementById(myPage).value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById(pageNoId).focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById(pageNoId).value = actPage;
			    document.getElementById(pageNoId).focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById(pageNoId).value=actPage;
			    document.getElementById(pageNoId).focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById(pageNoId).focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById(pageNoId).focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById(pageNoId).focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById(pageNoId).focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById(pageNoId).value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById(pageNoId).value;
			id = eval(p) + 1;
		}
		document.getElementById(myPage).value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	function callPageTextLocal(id, totalPage, action,pageNoFieldText,myPageText){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById(pageNoFieldText).value;
		 	var actPage = document.getElementById(myPageText).value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById(pageNoFieldText).focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById(pageNoFieldText).focus();
		     document.getElementById(pageNoFieldText).value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById(pageNoFieldText).focus();
		     document.getElementById(pageNoFieldText).value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById(pageNoFieldText).focus();
		      return false;
	        }
	        
	         document.getElementById(myPageText).value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
</script>