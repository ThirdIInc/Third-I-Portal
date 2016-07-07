
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="TravelSchAppr" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="noData" />
	<s:hidden name="stat" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">

		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>

		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Travel
			Schedule Approval222222</strong></td>
			<td width="3%" valign="top" class="txt"></td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
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
										href="TravelSchAppr_callStatus.action?status=P">Pending
									List</a> | <a href="TravelSchAppr_callStatus.action?status=A">Approved
									List</a> | <a href="TravelSchAppr_callStatus.action?status=R">Regected
									List</a></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>

					<td width="22%"><input name="Submit222" type="button"
						class="save" value="    Save " onclick="return saveValidate();" />
					<input name="Submit333" type="button" class="reset"
						value="    Reset " onclick="" /></td>
					<td>
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
					
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
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
							<s:if test="%{pen}">
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:if>
							<s:elseif test="%{apprvd}">
								<td height="27" class="formtxt"><strong>Approved
								List</strong></td>
							</s:elseif>
							<s:elseif test="%{regctd}">
								<td height="27" class="formtxt"><strong>Rejected
								List</strong></td>
							</s:elseif>

							<s:else>
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:else>
						</tr>

						<tr>
							<td width="100%" colspan="5"><s:hidden name="hdPage"
								id="hdPage" value="%{hdPage}" /> <%
 	int totalPage = (Integer) request.getAttribute("totalPage");
 	int pageNo = (Integer) request.getAttribute("pageNo");
 %>


							<table width="98%">
								<tr>
									<td width="98%" align="center">
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


						<td><s:hidden name="trvlAppId" />
						<tr>
							<td width="100%">
							<table width="100%" border="1" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth">Sr.No.</td>
									<td width="12%" valign="top" class="formth">Employee Name</td>
									<td width="25%" valign="top" class="formth">Travel request
									Name</td>
									<td nowrap="nowrap" valign="top" class="formth">Travel
									Date</td>
									<td width="15%" valign="top" class="formth">Status</td>
									<td valign="top" class="formth"></td>

									<td width="15%" valign="top" class="formth">Comment</td>
								</tr>

								<s:if test="noData">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>




								<s:hidden name="penFlag" />
								<s:hidden name="apprvdFlag" />
								<s:hidden name="regctedFlag" />


								<%!int i = 0;%>
								<%
								int k = 1;
								%>



								<s:iterator value="trvlSchList">
									<tr>


										<td class="border2" width="5%"><%=k%><s:hidden
											name="travelAppId" /></td>

										<td class="border2" width="12%"><s:property
											value="empName" /></td>

										<td class="border2" width="25%"><s:property
											value="trvlReqName" /></td>

										<td class="border2" width="15%"><s:property
											value="trvlDate" /></td>

										<s:if test="penFlag">

											<td class="border2" width="15%"><s:select
												name="checkStatus" id="<%="check"+k %>" cssStyle="width:100"
												theme="simple"
												list="#{'P':'Pending','A':'Approved','R':'Rejected'}" /></td>


											<td class="border2" width="20%" align="right"><input
												type="button" name="view" class="token" value="View"
												onclick="callView('<s:property value="travelAppId"/>');" />&nbsp;&nbsp;

											<input type="button" name="forward" class="token"
												align="left" value="Forward"
												onclick="callForward('<s:property value="travelAppId"/>');" /></td>


										</s:if>


										
										<s:else>

											<td class="border2" width="15%"><s:property
												value="status" /></td>

											<td class="border2" width="20%" align="right"><input
												type="button" name="view" class="token" value="View"
												onclick="callView('<s:property value="travelAppId"/>')" />&nbsp;&nbsp;

											</td>


										</s:else>



										<td class="border2" width="25%"><s:textfield
											name="comment" /></td>



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

						</tr>


						<!-- iterator -->


						<tr>

							<td colspan="3"><img
								src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
						</tr>

						<tr>

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
	
	
	function callView(Id){ 
		//alert(travelExpId);
		
		 
	}
	
	function callForward(id){ 
		document.getElementById('paraFrm_trvlAppId').value = Id;
		document.getElementById('paraFrm').action = "TravelSchAppr_callForward.action";
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
		document.getElementById('paraFrm').action = "TravelSchAppr_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
</script>