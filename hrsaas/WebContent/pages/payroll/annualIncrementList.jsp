<!--@author Ayyappa @date 27-05-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="AnnualIncrement" validate="true" id="paraFrm" name="paraFrm"	theme="simple">
<s:hidden name="listLength"/>

<s:hidden name="month"/>
<s:hidden name="year"/>
<s:hidden name="divisionId"/>
<s:hidden name="divisionName"/>
<s:hidden name="branchId"/>
<s:hidden name="branchName"/>
<s:hidden name="departmentId"/>
<s:hidden name="departmentName"/>
<s:hidden name="employeeTypeId"/>
<s:hidden name="incrementCode"/>
<s:hidden name="incrementStatus"/>
<s:hidden name="monthView"/>

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Annual Increment Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>				

		<tr>
			<td width="100%" align="left" colspan="3">
				<table width="100%" align="left" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="70%"><input type="button" value="Add New" class="token" onclick="return addnewFun();"/> 
							<input type="button" class="search" value="    Search "	onclick="searchFun();" /></td>
					  	<% int totalPage = 0; int pageNo = 0; %>
					  	<s:if test="listLength">
						<td width="30%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'AnnualIncrement_input.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'AnnualIncrement_input.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a>
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'AnnualIncrement_input.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'AnnualIncrement_input.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AnnualIncrement_input.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>						
						</td>						
						</s:if>						
					</tr>
				</table>
			</td>
		</tr>	
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
					<tr>
						<td class="txt" colspan="2"><strong class="text_head">Annual Increment Process
						  </strong></td>
						<td colspan="1" align="right">
						</td>
					</tr>			
					<tr>
						<td colspan="3">
						<%
						try {
						%>
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="formbg">
							<tr class="sortableTD">
										<s:hidden name="myPage" id="myPage" />
										<td class="formth" >
										<label  class = "set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
										</td>
										<td class="formth" >
										<label  class = "set" name="month" id="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>
										</td>
										<td class="formth">
										<label  class = "set" name="year" id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>
										</td>
										<td class="formth" >
										<label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
										</td>
										<td class="formth">
										<label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
										</td>
										<td class="formth" >
										<label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
										</td>
										<td class="formth">
										<label  class = "set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
										</td>
										<%int count=0; %>
										<%! int d=0; %>
										<%
											int i = 0;
										int cnt= pageNo*10-10;
											%>
							</tr>
										<s:iterator value="iteratorList">
											<tr 
											<%if(count%2==0){
												%>
												 class="tableCell1" <%}else{%>
												class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="listIncrementCode"/>',
																'<s:property value="listMonthCode"/>','<s:property value="listYear"/>',
																'<s:property value="listDeptName"/>','<s:property value="listDeptId"/>',
																'<s:property value="listBranchName"/>','<s:property value="listBranchId"/>',
																'<s:property value="listDivName"/>','<s:property value="listDivId"/>',
																'<s:property value="listIncrementStatus" />');">
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('listIncrementCode' + '<%=i%>').value;									
												</script>	
												<!-- SNO -->
												<td width="5%" align="center" class="sortableTD"><%=++cnt%><%++i;%></td>
												<s:hidden value="%{listIncrementCode}" id='<%="listIncrementCode" + i%>'></s:hidden>
												<!-- MONTH -->
												<td width="10%" align="left" class="sortableTD">
			                                        <s:property value="listMonthName" /> 
			                                        <s:hidden name="listMonthName" />
			                                    	<s:hidden name="listMonthCode" />
												</td>
												<!-- YEAR -->
												<td width="10%" align="center" class="sortableTD">
													<s:property value="listYear" />
												</td>
												<!-- DEPARTMENT -->
												<td width="10%" align="left" class="sortableTD">
													<s:property value="listDeptName" />&nbsp;
													<s:hidden name="listDeptId" />
												</td>
												<!-- BRANCH -->
												<td width="10%" align="left" class="sortableTD">
													<s:property value="listBranchName" />&nbsp;
													<s:hidden name="listBranchId" />
												</td>
												<!-- DIVISION -->
												<td width="10%" align="left" class="sortableTD">
													<s:property value="listDivName" />&nbsp;
													<s:hidden name="listDivId" />
												</td>
												<!-- STATUS -->
												<td width="10%" align="left" class="sortableTD">
													<s:property value="listIncrementStatus" />
													<s:hidden name="listIncrementStatus"/>
												</td>
											</tr>
										</s:iterator>
										<%d=i; %>
								</table>
									<s:if test="listLength"></s:if>
											<s:else>
												<table width="100%">
													<tr>
											<td align="center"><font color="red">No Data To Display</font></td>
										</tr>
										</table></s:else>
								<%
							} catch (Exception e) {
							}
						%>
						</td>
					</tr>
				</table>	
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<input type="button" value="Add New" class="token" onclick="return addnewFun();"/> 
							<input type="button" class="search" value="    Search "	onclick="searchFun();" />
						</td>
						<s:if test="listLength">
							<td align="right"><b>Total No. of Records: <s:property value="totalRecords" /></b></b></td>
						</s:if>
					</tr>
				</table>
			</td>			
		</tr>				
</table>
</s:form>
<script type="text/javascript">
   	function newRowColor(cell)
   	{
		 cell.className='Cell_bg_first'; 
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
		 cell.className='tableCell2';
		}
		else 
		cell.className='tableCell1';
	}
	function addnewFun() {
	try{
		document.getElementById("paraFrm_incrementCode").value = "";
		document.getElementById("paraFrm_month").value = "";
		document.getElementById("paraFrm_year").value = "";
		document.getElementById("paraFrm_departmentName").value = "";
		document.getElementById("paraFrm_departmentId").value = "";
		document.getElementById("paraFrm_branchName").value = "";
		document.getElementById("paraFrm_branchId").value = "";
		document.getElementById("paraFrm_divisionName").value = "";
		document.getElementById("paraFrm_divisionId").value = "";
		document.getElementById("paraFrm_incrementStatus").value = "";
		document.getElementById("paraFrm").action='AnnualIncrement_addNew.action';
		document.getElementById("paraFrm").submit();
		}catch(e){
			alert(e);
		}
	}
	function searchFun(){
		callsF9(500,325,'AnnualIncrement_f9searchAction.action');
	}
	function callForEdit(increcode, month,year,deptname,deptid,branchname,branchid,divname,divid,status) 
	{
		try{
		document.getElementById("paraFrm_incrementCode").value = increcode;
		document.getElementById("paraFrm_month").value = month;
		document.getElementById("paraFrm_year").value = year;
		document.getElementById("paraFrm_departmentName").value = deptname;
		document.getElementById("paraFrm_departmentId").value = deptid;
		document.getElementById("paraFrm_branchName").value = branchname;
		document.getElementById("paraFrm_branchId").value = branchid;
		document.getElementById("paraFrm_divisionName").value = divname;
		document.getElementById("paraFrm_divisionId").value = divid;
		document.getElementById("paraFrm_incrementStatus").value = status;
		}catch(e){
			alert(e);
		}
		document.getElementById("paraFrm").action="AnnualIncrement_callForEdit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	}

	
</script>