
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="TravelExpDisbur" validate="true" id="paraFrm"
	validate="true" theme="simple">


	<s:hidden name="noData"/>
	<s:hidden name="stat" />

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

		    
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel
					Expenses Disbursement </strong></td>
				</tr>
			</table>
			</td>
		</tr>
	       
	       
	    
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
							<td height="27" class="formtxt"><a
								href="TravelExpDisbur_callStatus.action?status=P">Pending
							List</a> | <a href="TravelExpDisbur_callStatus.action?status=B">Balance
							Amount List</a> | <a
								href="TravelExpDisbur_callStatus.action?status=F">Fully Paid
							Amount List</a></td>
						</tr>
			</table>
			</td>
		</tr>
		
		
		
	
		
				<tr>

					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">

						<tr>

							<s:if test="%{pen}">
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:if>
							<s:elseif test="%{bal}">
								<td height="27" class="formtxt"><strong>Balance
								Amount List</strong></td>
							</s:elseif>
							<s:elseif test="%{full}">
								<td height="27" class="formtxt"><strong>Fully Paid
								Amount List</strong></td>
							</s:elseif>

							<s:else>
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:else>
						</tr>

						<tr>
							<td width="100%" colspan="5"><s:hidden name="hdPage"
								id="hdPage" value="%{hdPage}" /> 
								<%
 								try {
 									int totalPage = (Integer) request.getAttribute("totalPage");
 									int pageNo = (Integer) request.getAttribute("pageNo");
 								%>


							<table width="100%%">
								<tr>
									<td width="100%%" align="center">
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








						<!-- iterator -->


						<s:hidden name="travelExpense" />
						<s:hidden name="travelAppId" />

						<tr>
							<td width="100%">
							<table width="100%" cellpadding="2" cellspacing="2"
								class="sortable">


								<tr>
									<td width="6%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrSrNo" name="tms.trvlExpDisbrSrNo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrSrNo")%></label></td>
									<td width="12%" valign="top" class="formth"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
									<td width="25%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrTrvlReqName" name="tms.trvlExpDisbrTrvlReqName" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTrvlReqName")%></label></td>
									<td nowrap="nowrap" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrTrvlAppDate" name="tms.trvlExpDisbrTrvlAppDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTrvlAppDate")%></label></td>
									<td width="15%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrAdvAmtTaken" name="tms.trvlExpDisbrAdvAmtTaken" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrAdvAmtTaken")%></label></td>
									<td width="15%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrExpAmt" name="tms.trvlExpDisbrExpAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrExpAmt")%></label></td>
									<td  width="10%" valign="top" class="formth">&nbsp;</td>

								</tr>


								<s:if test="noData">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>




								<s:hidden name="penFlag" />
								<s:hidden name="ApprvdFlag" />
								<s:hidden name="regctedFlag" />


								<%!int i = 0;%>
								<%
								int k = 1;
								%>



								<s:iterator value="travelDtlList">








									<tr>


										<td class="sortableTD" width="5%"><%=k%><s:hidden
											name="trvlExpId" />&nbsp;</td>

										<td class="sortableTD" width="12%"><s:property
											value="empName" />&nbsp;</td>

										<td class="sortableTD" width="25%"><s:property
											value="trvlReqName" />&nbsp;</td>

										<td class="sortableTD" width="15%"><s:property
											value="expAppDate" />&nbsp;</td>


										<td class="sortableTD" width="15%"><s:property
											value="expAdvAmt" />&nbsp;</td>


										<td class="sortableTD" width="15%"><s:property
											value="expExpnsAmt" />&nbsp;</td>



										<s:if test="penFlag">


											<td class="sortableTD" width="20%"><input type="button"
												name="expense" class="token" value="Expense"
												onclick="trvlExpense('<s:property value="trvlExpId"/>','<s:property value="expAppId"/>')" />

											</td>

										</s:if>

										<s:elseif test="balFlag">


											<td class="sortableTD" width="20%"><input type="button"
												name="expense" class="token" value="Balance"
												onclick="trvlExpense('<s:property value="trvlExpId"/>')" /></td>

										</s:elseif>
										<s:elseif test="fullFlag">


											<td class="sortableTD" width="20%"><input type="button"
												name="expense" class="token" value="    View"
												onclick="trvlExpense('<s:property value="trvlExpId"/>')" /></td>

										</s:elseif>

										<s:else>
											<td class="sortableTD" width="20%"><input type="button"
												name="expense" class="token" value="Expense"
												onclick="trvlExpense('<s:property value="trvlExpId"/>')" /></td>

										</s:else>




									</tr>


									<%
									k++;
									%>
								</s:iterator>
								<%
								i = k;
								%>
							</table>
							</td>
							<%
									} catch (Exception e) {
									e.printStackTrace();
								}
							%>
						</tr>


						<!-- iterator -->


					</table>
					</td>
				</tr>
				</table>
				
</s:form>


<script>
	
	function trvlExpense(travelExpId){ 
		document.getElementById('paraFrm_travelExpense').value = travelExpId;
		//document.getElementById('paraFrm_travelAppId').value = appId;
		document.getElementById('paraFrm').action = "TravelExpDisbur_callExpense.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "";
		 
	}
	
	
	</script>





<script>
	 
	
	
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
		document.getElementById('paraFrm').action = "TravelExpDisbur_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
</script>