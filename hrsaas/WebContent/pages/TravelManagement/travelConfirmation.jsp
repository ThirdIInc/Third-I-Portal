<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>

<s:form action="TravelConfirmation" method="post"
	name="TravelConfirmation" id="paraFrm" theme="simple" target="main">



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
					<td width="93%" class="txt"><strong class="text_head">Travel
					Confirmation Form </strong></td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="27" class="formtxt"><a
										href="TravelConfirmation_callConfirm.action?status=D">Pending
									List</a> | <a href="TravelConfirmation_callConfirm.action?status=C">Confirmed
									List</a> | <a href="TravelConfirmation_callConfirm.action?status=N">Canceled
									List</a></td>
								</tr>
								<s:hidden name="status" />
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>

							<s:if test="%{pend}">
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:if>
							<s:elseif test="%{confirm}">
								<td height="27" class="formtxt"><strong>Confirmed
								List</strong></td>
							</s:elseif>
							<s:elseif test="%{cancel}">
								<td height="27" class="formtxt"><strong>Canceled
								List</strong></td>
							</s:elseif>

							<s:else>
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:else>

						</tr>

						<tr>
							<td width="100%"><s:hidden name="hdPage" id="hdPage"
								value="%{hdPage}" /> <%
 		try {
 		int totalPage = (Integer) request.getAttribute("totalPage");
 		int pageNo = (Integer) request.getAttribute("pageNo");
 %>
							<table width="98%">
								<tr>
									<td width="10%" align="center">
									<%
									if (pageNo != 1) {
									%> <a href="#" onclick="callPage('1');"> <img
										src="../pages/common/img/first.gif" width="10" height="10"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('P')"> <img
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <%
 		}
 		if (totalPage <= 5) {
 			if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 		} else {
 		for (int z = 1; z <= totalPage; z++) {
 			if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 		}
 		}
 			}
 		} else {
 			if (pageNo == totalPage - 1 || pageNo == totalPage) {
 		for (int z = pageNo - 2; z <= totalPage; z++) {
 			if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 		}
 		}
 			} else if (pageNo <= 3) {
 		for (int z = 1; z <= 5; z++) {
 			if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 		}
 		}
 			} else if (pageNo > 3) {
 		for (int z = pageNo - 2; z <= pageNo + 2; z++) {
 			if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 		}
 		}
 			}
 		}
 		if (!(pageNo == totalPage)) {
 			if (totalPage == 0) {
 			} else {
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
										src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>');"> <img
										src="../pages/common/img/last.gif" width="10" height="10"
										class="iconImage" /> </a> <%
 		}
 		}
 %>
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr>

									<td class="formth">Employee ID</td>
									<td class="formth">Employee Name</td>
									<td class="formth">Application Date</td>
									<td class="formth"><s:if test="%{pend}">Confirm / Cancel </s:if></td>
									<s:iterator value="travelConf.travelConfList">
										<tr>
											<s:hidden name="empId" value="%{empId}" />
											<s:hidden name="linkStatus" value="C" />
											<td width="15%" class="border2"><s:property
												value="tokenNo" /></td>
											<td width="50%" class="border2"><s:property
												value="empName" /></td>
											<td width="15%" class="border2"><s:property
												value="travelDate" /><s:hidden name="travelID" /> <s:hidden
												name="itPend" /></td>
											<td width="10%" nowrap="nowrap" class="border2">&nbsp; <s:if
												test="%{itPend}">
												<input type="button" name="view_Details" class="token"
													value=" Confirm "
													onclick="return callConfirm('<s:property value="travelID"/>')" />
												<input type="button" class="token" value=" Cancel "
													onclick="cancel('<s:property value="travelID"/>')" />
											</s:if></td>
										</tr>
									</s:iterator>
								</tr>
								<s:if test="noData">
									<tr>
										<td colspan="4" align="center"><font color="red">No
										Data To Display</font></td>
									</tr>
								</s:if>
								<%
										} catch (Exception e) {
										e.printStackTrace();
									}
								%>
								<tr>
									<td colspan="6">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
					<s:hidden name="hiddenTravelId" />
				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>

<script>

function callConfirm(travelId)
{ 
   var conf=confirm("Do you really want to confirm this travel ?");
  	 		if(conf) 
  	     	{
  	     	 document.getElementById("paraFrm_hiddenTravelId").value=travelId;
			 document.getElementById("paraFrm").action="TravelConfirmation_save.action";
			 document.getElementById("paraFrm").submit();
		     return true;
  			 }
	  		 else
	  		 {  return false;
	  		 }

	
	return true;	
}
function cancel(travelId)
{
   var conf=confirm("Do you really want to cancel the travel ?");
  	 		if(conf) 
  	 	    {   document.getElementById("paraFrm_hiddenTravelId").value=travelId;
				document.getElementById("paraFrm").action="TravelConfirmation_travelCancel.action";
				document.getElementById("paraFrm").submit();	
  	 	    	return true;
  			 }
	  		 else
	  		 {
	  		 	 return false;
	  		 }
	return true;
}

function callPage(id)
	{
 
		if(id == 'P')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) - 1;
		}
		if(id == 'N')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) + 1;
		}
		document.getElementById('hdPage').value = id;
		document.getElementById('paraFrm').action = "TravelConfirmation_callConfirm.action";
		document.getElementById('paraFrm').submit();
	}

</script>