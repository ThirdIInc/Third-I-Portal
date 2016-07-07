
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript"> 
	var jourApp = new Array(); 
</script>
<s:form action="ExpenseClaimApp" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<!-- Flagas used For Cancel Button -->
	<s:hidden name="employeeName" />
	<s:hidden name="statusFld" />
	<s:hidden name="employeeId" />
	<s:hidden name="applDate" />
	<s:hidden name="totAmt" />
	<s:hidden name="expAmt" />
	<s:hidden name="mode" />
	<s:hidden name="comment" />
	<s:hidden name="trvAppIdDtl" />
	<s:hidden name="advAmt" />
	<s:hidden name="bankName" />
	<s:hidden name="month" />
	<s:hidden name="year" />
	<s:hidden name="requestName" />
	<s:hidden name="accNo" />
	<s:hidden name="grdId" />
	<s:hidden name="hiddencode" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="hidSts" />
	<s:hidden name="branchName" />

	<s:hidden name="deptName" />
	<s:hidden name="desgName" />
	<s:hidden name="empToken" />
	<s:hidden name="grdName" />
	<s:hidden name="exAppId" />
	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Expense
					Claim Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!--  <tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">
			Expense Claim Application</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/common/css/default/images/help.gif" width="16"
				height="16" /></div>
			</td>
		</tr>-->

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
										href="ExpenseClaimApp_callStatus.action?status=N">New
									Requisition </a> |<a
										href="ExpenseClaimApp_callStatus.action?status=P">Pending
									List</a> | <a href="ExpenseClaimApp_callStatus.action?status=A">Approved
									List</a> | <a href="ExpenseClaimApp_callStatus.action?status=R">Rejected
									List</a></td>
								</tr>
								<s:hidden name="apprflag" />
								<s:hidden name="listLength" />
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- <tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
					<s:hidden name="status" />
				</tr>-->
			</table>
			</td>
		</tr>


		<!-- The Following code Denotes  Include JSP Page For Button Panel -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
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
							<s:if test="%{newReq}">
								<td height="27" class="formtxt"><strong>New
								Requisition </strong></td>
							</s:if>
							<s:elseif test="%{pen}">
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:elseif>
							<s:elseif test="%{app}">
								<td height="27" class="formtxt"><strong>Approved
								List</strong></td>
							</s:elseif>
							<s:elseif test="%{rej}">
								<td height="27" class="formtxt"><strong>Rejected
								List</strong></td>
							</s:elseif>

							<s:else>
								<td height="27" class="formtxt"><strong>New
								Requisition </strong></td>
							</s:else>
						</tr>

						<tr>
							<td width="100%" colspan="5"><s:hidden name="hdPage"
								id="hdPage" value="%{hdPage}" /> <%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
					%>
							<table width="98%">
								<tr>
									<td width="98%" align="center">
									<%	if(pageNo != 1)
								{
							%> <a href="#" onclick="callPage('1');"> <img
										src="../pages/common/img/first.gif" width="10" height="10"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('P')"> <img
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <%	}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%> <b><u><%=totalPage%></u></b> <%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}
									}
								}
								else
								{
									if(pageNo == totalPage - 1 ||pageNo == totalPage)
									{									
										for(int z = pageNo - 2; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0){}
									else
									{
							%> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
										src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>');"> <img
										src="../pages/common/img/last.gif" width="10" height="10"
										class="iconImage" /> </a> <%		}
								}
							%>
									</td>
								</tr>
							</table>
							</td>


						</tr>

						<tr>
							<td><s:hidden name="travelViewNo" />
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td width="5%" valign="top" class="formth"><label  class = "set" name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
									<td width="25%" valign="top" class="formth"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
									<td width="25%" valign="top" class="formth"><label  class = "set" name="request" id="request" ondblclick="callShowDiv(this);"><%=label.get("request")%></label></td>
									<td width="15%" valign="top" class="formth"><label  class = "set" name="travel.date" id="travel.date" ondblclick="callShowDiv(this);"><%=label.get("travel.date")%></label></td>
									<s:if test="%{app}">
										<td width="15%" valign="top" class="formth"><label  class = "set" name="expense.amount" id="expense.amount" ondblclick="callShowDiv(this);"><%=label.get("expense.amount")%></label></td>
										<!--  <td width="15%" valign="top" class="formth">Status</td>-->
										<td width="15%" valign="top" class="formth"><label  class = "set" name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>
									</s:if>
									<s:elseif test="%{rej}">
										<td width="15%" valign="top" class="formth"><label  class = "set" name="expense.amount" id="expense.amount1" ondblclick="callShowDiv(this);"><%=label.get("expense.amount")%></label></td>
										<!--<td width="15%" valign="top" class="formth">Status</td>-->
										<td width="15%" valign="top" class="formth"><label  class = "set" name="details" id="details1" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>
									</s:elseif>
									<s:else>
										<td width="35%" valign="top" class="formth"><label  class = "set" name="claim" id="claim" ondblclick="callShowDiv(this);"><%=label.get("claim")%></label></td>
									</s:else>



								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>


								<%! int i = 0 ; %>
								<% int k = 1; %>

								<s:iterator value="travelList">
									<tr
										ondblclick="javascript:callForEdit('<s:property  value="travelAppId"/>');">
										<td  width="5%" class="sortableTD"><%=k %><s:hidden name="travelAppId" /> 
											<s:hidden name="itExpId" /> </td>
										<td  width="12%" class="sortableTD"><s:property
											value="empName" />&nbsp;<s:hidden name="empName" /></td>
										<s:hidden name="empId" />
										<td  width="22%" class="sortableTD"><s:property
											value="reqName" />&nbsp;<s:hidden name="reqName" /></td>
										<td  width="22%" class="sortableTD"><s:property
											value="trvDate" /><s:hidden name="trvDate" /></td>
										<s:if test="%{appFlag}">
											<td  width="15%" class="sortableTD" align="right"><s:property
												value="expAmount" />&nbsp;<s:hidden name="expAmount" /></td>
											<!--  <td  width="15%" class="sortableTD"><s:property
												value="status" /><s:hidden name="status" /></td>-->
											<td  width="15%" align="center" class="sortableTD"><input
												type="button" class="token" value="View"
												onclick="viewAppr('<s:property value="travelAppId"/>','<s:property value="itExpId"/>')" />
											</td>
										</s:if>
										<s:elseif test="%{rejFlag}">
											<td  width="15%" class="sortableTD" align="right"><s:property
												value="expAmount" /><s:hidden name="expAmount" /></td>
											<!-- <td  width="15%" class="sortableTD"><s:property
												value="status" /><s:hidden name="status" /></td>-->
											<td  width="15%" align="center" class="sortableTD"><input
												type="button" class="token" value="View"
												onclick="viewRej('<s:property value="travelAppId"/>','<s:property value="itExpId"/>')" /></td>
										</s:elseif>
										<s:elseif test="%{penFlag}">
											<td  width="15%" align="center" class="sortableTD"><input
												type="button" class="token" value="View"
												onclick="viewPen('<s:property value="travelAppId"/>','<s:property value="itExpId"/>')" /></td>
										</s:elseif>
										<s:else>
											<td  width="15%" align="center" class="sortableTD"><input
												type="button" class="token" value="Claim"
												onclick="viewClaim('<s:property value="travelAppId"/>','<s:property value="itExpId"/>')" />
											</td>
										</s:else>



									</tr>
									<%k++; %>
								</s:iterator>
								<% i=k ; %>

							</table>
							</td>
							<%}
					catch(Exception e)
					{
				    	e.printStackTrace();
					}%>
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<!-- <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="27" class="formtxt"><strong>Approved
							List</strong></td>
						</tr>

						<tr>
							<td><s:hidden name="travelViewNo" />
							<table width="100%" border="1" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth">Sr.No.</td>									
									<td width="22%" valign="top" class="formth">Employee Name</td>
									<td width="22%" valign="top" class="formth">Travel request Name</td>
									<td width="10%" valign="top" class="formth">Travel date</td>
									<td width="15%" valign="top" class="formth">Expense Amount</td>
									<td width="15%" valign="top" class="formth">Status</td>
									<td width="15%" valign="top" class="formth">Detail</td>
								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>

							</table>
							</td>

						</tr>

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
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="27" class="formtxt"><strong>Rejected
							List</strong></td>
						</tr>

						<tr>
							<td><s:hidden name="travelViewNo" />
							<table width="100%" border="1" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth">Sr.No.</td>									
									<td width="22%" valign="top" class="formth">Employee Name</td>
									<td width="22%" valign="top" class="formth">Travel request Name</td>
									<td width="10%" valign="top" class="formth">Travel date</td>
									<td width="15%" valign="top" class="formth">Expense Amount</td>
									<td width="15%" valign="top" class="formth">Status</td>
									<td width="15%" valign="top" class="formth">Detail</td>
								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>

							</table>
							</td>

						</tr>

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
		</tr>-->



		<!-- The Following code Denotes  Include JSP Page For Button Panel -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
callOnload();
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
		document.getElementById('paraFrm').action = "ExpenseClaimApp_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
	
	function callOnload(){
	 	var check =<%=i%>; 
	 		 if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
	}
	
	function viewClaim(travelAppId,expId){  
 
		document.getElementById('paraFrm_exAppId').value = expId;	
		document.getElementById('paraFrm_travelViewNo').value = travelAppId;	
		document.getElementById('paraFrm').action = "ExpenseClaimApp_callView.action";
		document.getElementById('paraFrm').submit();	
		 
	}
	function viewPen(travelAppId,expId){ 
		document.getElementById('paraFrm_exAppId').value = expId;	
		document.getElementById('paraFrm_travelViewNo').value = travelAppId;	
		document.getElementById('paraFrm').action = "ExpenseClaimApp_callPen.action";
		document.getElementById('paraFrm').submit();			 
	}
	function viewAppr(travelAppId,expId){ 
		document.getElementById('paraFrm_exAppId').value = expId;	
		document.getElementById('paraFrm_travelViewNo').value = travelAppId;	
		document.getElementById('paraFrm').action = "ExpenseClaimApp_callAppr.action";
		document.getElementById('paraFrm').submit();			 
	}
	function viewRej(travelAppId,expId){ 
		document.getElementById('paraFrm_exAppId').value = expId;	
		document.getElementById('paraFrm_travelViewNo').value = travelAppId;	
		document.getElementById('paraFrm').action = "ExpenseClaimApp_callRej.action";
		document.getElementById('paraFrm').submit();			 
	}
	function addnewFun()
{	
	document.getElementById('paraFrm').action="ExpenseClaimApp_addNew.action";
	document.getElementById('paraFrm').submit();
}
//For F9Window

function searchFun()
{
	callsF9(500,300,"ExpenseClaimApp_f9Action.action");
}

function callForEdit(travelAppId){
	  	document.getElementById('paraFrm_travelViewNo').value=travelAppId;	
	   //	document.getElementById("paraFrm").action="ExpenseClaimApp_calforedit.action";
	   document.getElementById("paraFrm").action="";
	    //document.getElementById("paraFrm").submit();
}	 
</script>

