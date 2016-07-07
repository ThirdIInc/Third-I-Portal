
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TravelAdvanceDisbursement" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="98%" class="txt"><strong class="text_head">Travel
					Advance Disbursement </strong></td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>

			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="payStatus" />
					<td height="27" class="formtxt"><a
						href="TravelAdvanceDisbursement_callStatus.action?payStatus=pend">Pending
					Advance List</a> | <a
						href="TravelAdvanceDisbursement_callStatus.action?payStatus=paid">Paid
					Advance List</a></td>
				</tr>
				<s:hidden name="apprflag" />
				<s:hidden name="listLength" />
			</table>
		</td>

		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="27" class="formtxt"><strong> <%
 	String status = (String) request.getAttribute("stat");

 	if (status != null) {
 		out.println(status);
 	} else {
 		out.println("Pending Advance List");
 	}
 %> </strong></td>


						</tr>

						<s:hidden name="accNo" />
						<tr>
							<td width="100%" colspan="5"><s:hidden name="hdPage"
								id="hdPage" value="%{hdPage}" /> <%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
		System.out.println("============JSP========"+totalPage);
						int pageNo = (Integer) request.getAttribute("pageNo");
						System.out.println("============JSP========"+pageNo);
					%>
							<table width="98%">
								<tr>
									<td width="98%" align="right"><s:if test="noData"></s:if>
									
									<s:else>Page :</s:else>
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
							<td width="100%" colspan="3">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth" ><label
										class="set" id="sr.no" name="sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
									<td width="20%" valign="top" class="formth"><label
										class="set" id="emp.name" name="emp.name"
										ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
									<td width="20%" valign="top" class="formth"><label
										class="set" id="travel.name" name="travel.name"
										ondblclick="callShowDiv(this);"><%=label.get("travel.name")%></label>
									</td>
									<td width="10%" valign="top" class="formth"><label
										class="set" id="date" name="date"
										ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
									<td width="15%" valign="top" class="formth"><label
										class="set" id="amount" name="amount"
										ondblclick="callShowDiv(this);"><%=label.get("amount")%></label>
									</td>
									<td width="10%" valign="top" class="formth"><label
										class="set" id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>
								</tr>



								<s:if test="noData">
									<tr>
										<td width="100%" colspan="6" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%!int i = 0;
	                                int c = 0;%>
								<%
								int k = 1;
								%>

								<s:iterator value="advanceList">
									<tr>
										<td class="sortableTD" width="5%"><%=k%> <s:hidden
											name="empId" /> <s:hidden name="appId" /></td>



										<td class="sortableTD" width="25%" ><s:property
											value="empName" /> <s:hidden name="empName" /></td>
										<td class="sortableTD" width="25%"><s:property
											value="reqName" /><s:hidden name="reqName" /></td>
										<td class="sortableTD" width="10%"><s:property
											value="appDate" /><s:hidden name="appDate" /></td>
										<td class="sortableTD" width="10%"><s:property
											value="advAmount" /><s:hidden name="advAmount" /></td>

										<td class="sortableTD" width="10%"><s:hidden name="flag"
											value="%{flag}" /> <s:if test="flag">
											<input type="button" name="view_Details" class="token"
												value="  Advance  "
												onclick="viewDetails('<s:property value="empId"/>','<s:property value="appId"/>')" />
										</s:if> <s:else>
											<input type="button" name="view_Details" class="token"
												value="     View     "
												onclick="PaymentDtl('<s:property value="empId"/>','<s:property value="appId"/>')" />
										</s:else></td>

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
							<%}
					catch(Exception e)
					{
				    	e.printStackTrace();
					}%>
						</tr>
						<tr>
							<td class="cellbg">
							<table width="130" border="0" align="right" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="100%"></td>

								</tr>

							</table>
							</td>
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
	
	 callOnload()
	function saveValidate(){
	 
	if(document.getElementById("paraFrm_listLength").value==0){
	alert("No record to save");
	return false;
	}else{
	document.getElementById("paraFrm").action="TravelAdvanceDisbursement_save.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
		 
	 
	 function viewDetails(empId,appId){ 
		 document.getElementById('paraFrm_empId').value = empId;  
		 document.getElementById('paraFrm_appId').value = appId;  
		 
		// var statusFlag = document.getElementById('paraFrm_status').value; 
		document.getElementById('paraFrm').action = 'TravelAdvanceDisbursement_callView.action?empCode='+empId+'&appCode='+appId; 
		document.getElementById('paraFrm').submit(); 
		 
	}
	
	
	 function PaymentDtl(empId,appId){
		 document.getElementById('paraFrm_empId').value = empId;  
		 document.getElementById('paraFrm_appId').value = appId;  
		// var statusFlag = document.getElementById('paraFrm_status').value; 
		document.getElementById('paraFrm').action = 'TravelAdvanceDisbursement_paymentDtl.action?empCode='+empId+'&appCode='+appId; 
		document.getElementById('paraFrm').submit(); 
		 
	}
	
	
	
	function callOnload(){
	 	var check =<%=i%>; 
	 		 if(document.getElementById("paraFrm_payStatus").value=='pend' ){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
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
		document.getElementById('paraFrm').action = "TravelAdvanceDisbursement_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
</script>