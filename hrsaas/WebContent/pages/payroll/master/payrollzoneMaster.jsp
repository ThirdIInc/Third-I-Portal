<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PayrollZone" theme="simple" validate="true" id="paraFrm">
	<s:hidden name="show" />
	<s:hidden name="Branchshow" />
	<s:hidden name="myPageEmp" id="myPageEmp" />
	<s:hidden name="showEmp" />
	<s:hidden name="Employeetypehiddencode" />
	<s:hidden name="Branchhiddencode" />
	<s:hidden name="rowId" />
	<s:hidden name="empTypeTabFlag" />
	<s:hidden name="branchTabFlag" />
	<s:hidden name="empExceptionTabFlag" />
	<s:hidden name="noExcepData" />
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">SalaryZone Configuration</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		
		<s:hidden name="Ptaxcityname" />
		<s:hidden name="Ptaxcitycode" />
					<tr>
							<td style="padding-left: 2px;" width="78%">
							<div id="tabnav">
							<ul>
								<li><a href="javascript:callDivLoad('ET');"> <span>Employee
								Type Configuration</span></a> <a href="javascript:callDivLoad('BC');">
								<span align="center">Branch wise Configuration</span></a> <a
									href="javascript:callDivLoad('EE');"> <span align="center">Employee
								Exception for PTAX</span></a></li>
							</ul>
							</td>
							<td width="22%" align="right">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
		
		<!--  Employee Type Conf. start. -->
		<s:if test="empTypeTabFlag">
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
						

						<tr>
							<td><b>Employee Type Configuration Details :</b></td>
							<td align="right"><b>Page:</b> <%
						int total = (Integer) request.getAttribute("abc");
						int PageNo = (Integer) request.getAttribute("xyz");%>
 						<%
 							if (!(PageNo == 1)) {%>
 						<a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
								src="../pages/common/img/previous.gif" width="10" height="10"
								class="iconImage" /></a> 
								<%}
 							if (total < 5) {
 					for (int i = 1; i <= total; i++) {%> 
 					<a href="#" onclick="callPage('<%=i %>');"> 
 					<% if (PageNo == i) { %> 
 					<b><u><%=i%></u></b> 
 					<%} else  %><%=i%> </a> <%
 				}
 				} 	if (total >= 5) {
 						for (int i = 1; i <= 5; i++) {  %>
 				 <a href="#" onclick="callPage('<%=i %>');"> 
 				 <% if (PageNo == i) {%> 
 				 <b><u><%=i%></u></b>
 				  <%} else%><%=i%>
 				   </a> <%
 				}
 				} %> <select name="selectname" onchange="on()" id="selectname">
								<%
								for (int i = 1; i <= total; i++) {
								%>
				<option value="<%=i%>"><%=i%></option>
								<%}%>
						</select>
					</td></tr>

					</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center">
								<label  class = "set" name="payroll.srno" id="payroll.srno" ondblclick="callShowDiv(this);"><%=label.get("payroll.srno")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="payroll.emptype" id="payroll.emptype" ondblclick="callShowDiv(this);"><%=label.get("payroll.emptype")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="payroll.empptzone" id="payroll.empptzone" ondblclick="callShowDiv(this);"><%=label.get("payroll.empptzone")%></label>
							</td>
							<!--<td class="formth" align="center">
								<label  class = "set" name="payroll.emppfzone" id="payroll.emppfzone" ondblclick="callShowDiv(this);"><%=label.get("payroll.emppfzone")%></label>
							</td>
							--><td class="formth" align="center">
								<label  class = "set" name="payroll.emppfminamt" id="payroll.emppfminamt" ondblclick="callShowDiv(this);"><%=label.get("payroll.emppfminamt")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="payroll.empesizone" id="payroll.empesizone" ondblclick="callShowDiv(this);"><%=label.get("payroll.empesizone")%></label>
							</td>
						</tr>
						<%
						int count = 0;
						%>
						<%!int d = 0;%>
						<%
						int i = 0, cn = PageNo * 20 - 20;
						%>
						<s:iterator value="EmptypeList">

							<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
								class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


								<td width="7%" class="sortableTD" align="left"><%=++cn%>
								<%
								++i;
								%>
								</td>
								<s:hidden name="EmptypeID"></s:hidden>

								<td width="30%"  class="sortableTD" align="left"><s:property value="typeName" />
								</td>

								<td width="10%" class="sortableTD" align="center"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="ETptZone" /></td>
								<!--<td width="10%" class="sortableTD"  align="center"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="ETpfZone" /></td>
								--><td width="10%" class="sortableTD"  align="center"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="ETpfMinAmt" /></td>	
								<td width="10%" class="sortableTD" align="center"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="ETesiZone" /></td>

							</tr>


						</s:iterator>
						<%
						d = i;
						%>


						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
						<tr>
							<td align="center"></td>
							<td align="right"><s:submit cssClass="save"
								action="PayrollZone_empUpdate" theme="simple"
								value="     Update " /></td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			<%
				} catch (Exception e) {
				}
			%>
			</td>
		</tr>
		</s:if>
		<!--  Employee Type Conf. End -->
		
		
		<!--  Branch Conf. Start -->
		<s:if test="branchTabFlag">
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td><b>Branch wise Configuration Details :</b></td>
					<td align="right"><b>Page:</b> <%
 	int total1 = (Integer) request.getAttribute("abc1");
 	int PageNo1 = (Integer) request.getAttribute("xyz1");
 %> <%
 if (!(PageNo1 == 1)) {
 %><a href="#" onclick="callPageBr('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previousBr()"><img
						src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /></a> <%
 	}
 	if (total1 < 5) {
 		for (int i = 1; i <= total1; i++) {
 %> <a href="#" onclick="callPageBr('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}

 	if (total1 >= 5) {
 		for (int i = 1; i <= 5; i++) {
 %> <a href="#" onclick="callPageBr('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}
 %> <select name="Branchselectname" id="Branchselectname"
						onchange="onBr()">
						<%
						for (int i = 1; i <= total1; i++) {
						%>

						<option value="<%=i%>"><%=i%></option>
						<%
						}
						%>
					</select></td>
				</tr>



			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="BranchmyPage" id="BranchmyPage" />
							<td class="formth" align="center" width="7%">
								<label  class = "set" name="payroll.branchsrno" id="payroll.branchsrno" ondblclick="callShowDiv(this);"><%=label.get("payroll.branchsrno")%></label>
							</td>
							<td class="formth" align="center" width="20%">
								<label  class = "set" name="payroll.branchname" id="payroll.branchname" ondblclick="callShowDiv(this);"><%=label.get("payroll.branchname")%></label>
							</td>
							<td class="formth" align="center" width="10%">
								<label  class = "set" name="payroll.branchptzone" id="payroll.branchptzone" ondblclick="callShowDiv(this);"><%=label.get("payroll.branchptzone")%></label>
							</td>
							<td class="formth" align="center" width="25%">
								<label  class = "set" name="payroll.branchptstate" id="payroll.branchptstate" ondblclick="callShowDiv(this);"><%=label.get("payroll.branchptstate")%></label>
							</td>
							<!-- Added By Nilesh Dhandare start -->
							<td class="formth" align="center" width="10%">
								<label  class = "set" name="payroll.esicode" id="payroll.esicode" ondblclick="callShowDiv(this);"><%=label.get("payroll.esicode")%></label>
							</td>
							<!-- Added By Nilesh Dhandare end-->
							<td class="formth" align="center" width="10%">
								<label  class = "set" name="payroll.branchmetro" id="payroll.branchmetro" ondblclick="callShowDiv(this);"><%=label.get("payroll.branchmetro")%></label>
							</td>
							
							<!--<td class="formth" align="center" width="10%">
								<label  class = "set" name="payroll.branchpfzone" id="payroll.branchpfzone" ondblclick="callShowDiv(this);"><%=label.get("payroll.branchpfzone")%></label>
							</td>-->
							<td class="formth" align="center" width="10%">
								<label  class = "set" name="payroll.branchesizone" id="payroll.branchesizone" ondblclick="callShowDiv(this);"><%=label.get("payroll.branchesizone")%></label>
							</td>
						</tr>
						<%
						int count1 = 0;
						%>
						<%!int dd = 0;%>
						<%
						int ii = 0, zz = 0, cn1 = PageNo1 * 20 - 20;
						%>
						<s:iterator value="BranchList">

							<tr <%if(count1%2==0){
									%> class="tableCell1" <%}else{%>
								class="tableCell2" <%	}count1++; %>
								onmouseover="javascript:newRowColor(this);"
								
								onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);">

								<td width="7%" class="sortableTD" align="left"><%=++cn1%>
								<%
								++ii;
								%>
								</td>
								<s:hidden name="BranchID"></s:hidden>

								<td width="20%" align="left" class="sortableTD"><s:property value="BranchName" />
								</td>
								<td width="10%" align="center" class="sortableTD"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="BranchPtZone" /></td> 
									 <%
 									 		String locname = (String) request.getAttribute("Ptaxcityname"
 									 		+ zz);
 									 		String locid = (String) request.getAttribute("Ptaxcitycode"
 									 		+ zz);
 									 %>
	      	   		                <td width="25%" align="center" class="sortableTD"> 
	      	   		               <s:textfield
									name="<%="Ptaxcityname"+zz %>" value="<%=locname%>"
									readonly="true" /> <s:hidden name="<%="Ptaxcitycode"+zz %>"
									value="<%=locid%>" /> 
									 <img src="../pages/images/recruitment/search2.gif"
									height="17" width="17"
									onclick="rowcallsF9('<s:property value="<%=""+zz%>"/>');">
								</td>
								<td width="10%" class="sortableTD" align="left"><s:textfield name="esiCode" /></td>
								
								<td width="10%" align="center" class="sortableTD"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="Branchmetro" /></td>
									
								<!--<td width="10%" align="center" class="sortableTD"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="BranchPfZone" /></td>-->
								<td width="10%" align="center" class="sortableTD"><s:select
									list=" #{'Y':'YES','N':'NO'}" name="BranchEsiZone" /></td>




							</tr>
							<%
							zz++;
							%>

						</s:iterator>

						<%
								dd = ii;
								zz = 0;
						%>


						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
						<tr>
							<td align="center"></td><td>&nbsp;</td>
							<td align="right"><s:submit cssClass="save"
								action="PayrollZone_branchUpdate" theme="simple"
								value="     Update "  /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			<%
				} catch (Exception e) {
				}
			%>
			</td>
		</tr>
		</s:if>
		<!--  Branch Conf. End -->
		
		
		<!--  Employee exception start -->
		<s:if test="empExceptionTabFlag">
			<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">Employee Exceptions For PTAX</strong></td>
				</tr>
				<tr>
					
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<font
							color="red">*</font><s:hidden name="empCode" /><s:hidden name="empBranch" /><s:hidden
							name="code" /></td>
						<td width="75%" colspan="3"><s:textfield name="empToken"
							size="10" theme="simple" readonly="true" /><s:textfield
							name="empName" size="50" theme="simple"
							readonly="true" /><img src="../pages/images/search2.gif"
								height="16" align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'PayrollZone_f9actionEmp.action');" ></td>
					</tr>
					
					<tr>	
							<td width="25%" colspan="4" align="center" class="formtext"><input type="button" value="Add to Exception" class="add" onclick="return addEmployee();" /></td>
						
					</tr>	

				
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
						

						<tr>
							<td><b>Employee List :</b></td>
							<td align="right"><b>Page:</b> <%
						int totalEmp = (Integer) request.getAttribute("rowCountEmp");
						int pageNoEmp = (Integer) request.getAttribute("pageNoEmp");%>
 						<%
 							if (!(pageNoEmp == 1)) {%>
 						<a href="#" onclick="callPageEmp('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="previousEmp()"><img
								src="../pages/common/img/previous.gif" width="10" height="10"
								class="iconImage" /></a> 
								<%}
 							if (totalEmp < 5) {
 					for (int i = 1; i <= totalEmp; i++) {%> 
 					<a href="#" onclick="callPageEmp('<%=i %>');"> 
 					<% if (pageNoEmp == i) { %> 
 					<b><u><%=i%></u></b> 
 					<%} else  %><%=i%> </a> <%
 				}
 				} 	if (totalEmp >= 5) {
 						for (int i = 1; i <= 5; i++) {  %>
 				 <a href="#" onclick="callPageEmp('<%=i %>');"> 
 				 <% if (pageNoEmp == i) {%> 
 				 <b><u><%=i%></u></b>
 				  <%} else%><%=i%>
 				   </a> <%
 				}
 				} %> <select name="selectnameEmp" onchange="onEmp()" id="selectnameEmp">
								<%
								for (int i = 1; i <= totalEmp; i++) {
								%>
				<option value="<%=i%>"><%=i%></option>
								<%}%>
						</select>
					</td></tr>

					</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							
							<td class="formth" align="center">
								<label  class = "set" name="payroll.srno" id="payroll.srno" ondblclick="callShowDiv(this);"><%=label.get("payroll.srno")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							</td>
							<td class="formth" align="center">
								<input type="button" class="delete" value=" Remove " onclick="return removeEmpExcep();"/>
								<input type="checkbox" id="checkAll" onclick="return checkAllEmpExcep(this)"/>
								
							</td>
							
						</tr>
						<%
						int count = 0;
						%>
						<%!int s = 0;%>
						<%
						int i = 0, cn = pageNoEmp * 20 - 20;
						%>
							<s:if test="noExcepData">
							<tr>
								<td colspan="5" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
							</tr>
							</s:if>
						<s:iterator value="empExcpList">

							<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
								class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


								<td width="7%" class="sortableTD" align="center"><%=++cn%>
								<%
								++i;
								%>
								</td>
								<s:hidden name="empCodeList"></s:hidden>
								
								<input type="hidden" name="empCodeListH" value='<s:property value="empCodeList" />' id="empCodeList<%=i%>"> 
								<td width="15%"  class="sortableTD" align="left">&nbsp;<s:property value="empTokenList" />
								</td>
								<td width="40%"  class="sortableTD" align="left">&nbsp;<s:property value="empNameList" />
								</td><input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
								<td width="28%"  class="sortableTD" align="left">&nbsp;<s:property value="branchNameList" />
								</td>
								<td width="10%"  class="sortableTD" align="center"><input type="checkbox" id="checkbox<%=i%>" onclick="return checkEmpExcep('<s:property value="empCodeList" />',<%=i%>)"/>
								</td>
							</tr>


						</s:iterator>
						<%
						s = i;
						%>

					<input type="hidden" name="empCount" id="empCount" value="<%=s%>"/>
					</table>
					</td>
				</tr>

			</table>
			<%
				} catch (Exception e) {
				}
			%>
			</td>
		</tr>
		</s:if>
		<!--  Employee Type Conf. End -->
	</table>
	</td></tr></table>
	

</s:form>

<script>

// every function end with Br means that function represents BranchZone   



  function callPage(id){
 
	   	document.getElementById('myPage').value=id;
	    document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="PayrollZone_callPage.action";
	    document.getElementById('paraFrm_Branchshow').value=1;
	   document.getElementById('paraFrm').submit();
   }
   
   function callDivLoad(tabName){
   //alert(tabName);
   		if(tabName=='ET'){
   			document.getElementById('paraFrm_empTypeTabFlag').value='true';
   			document.getElementById('paraFrm_empExceptionTabFlag').value='false';
   			document.getElementById('paraFrm_branchTabFlag').value='false';
   			document.getElementById('paraFrm').action="PayrollZone_empTypeData.action";
   		}else if(tabName=='EE'){
   			document.getElementById('paraFrm_empExceptionTabFlag').value='true';
   			document.getElementById('paraFrm_empTypeTabFlag').value='false';
   			document.getElementById('paraFrm_branchTabFlag').value='false';
   			document.getElementById('paraFrm').action="PayrollZone_getEmpExceptionData.action";
   		}else if(tabName=='BC'){
   			document.getElementById('paraFrm_branchTabFlag').value='true';
   			document.getElementById('paraFrm_empTypeTabFlag').value='false';
   			document.getElementById('paraFrm_empExceptionTabFlag').value='false';
   			document.getElementById('paraFrm').action="PayrollZone_branchData.action";
   			}
   		document.getElementById('paraFrm').submit();
   }
   function next()
   {
   
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	  document.getElementById('selectname').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	  document.getElementById('selectname').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="PayrollZone_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
     
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="PayrollZone_callPage.action";
	   if(pageno!=1)
	   document.getElementById('selectname').value=eval(pageno)-1;
	 
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
   
  	var val= document.getElementById('selectname').value;
  
  	//document.getElementById('selectname').value = "1";
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="PayrollZone_callPage.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	  
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  {
  	 document.getElementById('selectname').value=eval(pgno);
  	 }
  	
  	}
  	
  
    // for branch functions...!
    
    
  function callPageBr(id){
 
	   	document.getElementById('BranchmyPage').value=eval(id);
	    document.getElementById('paraFrm_Branchshow').value=eval(id);
	    document.getElementById('paraFrm').action="PayrollZone_callPageBr.action";
	    document.getElementById('paraFrm_show').value=1;
	   document.getElementById('paraFrm').submit();
   }
   
   
   function nextBr()
   {
   
   var pageno=	document.getElementById('BranchmyPage').value;
  
   	if(pageno=="1")
   	{	document.getElementById('BranchmyPage').value=2;
	 document.getElementById('paraFrm_Branchshow').value=2;
	 }
	 else{
	 document.getElementById('BranchmyPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_Branchshow').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="PayrollZone_callPageBr.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previousBr()
   {
   var pageno=	document.getElementById('BranchmyPage').value;
   	
	 document.getElementById('BranchmyPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_Branchshow').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="PayrollZone_callPageBr.action";
	   document.getElementById('paraFrm').submit();
   }
  	function onBr()
   {
  	var val= document.getElementById('Branchselectname').value;
	document.getElementById('paraFrm_Branchshow').value=eval(val);
	 document.getElementById('BranchmyPage').value=eval(val);
	 document.getElementById('Branchselectname').value=eval(val);
	   document.getElementById('paraFrm').action="PayrollZone_callPageBr.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshowBr();
  	function pgshowBr()
  	{
  		
  	var pgno=document.getElementById('paraFrm_Branchshow').value;

  	if(!(pgno==""))
  	 document.getElementById('Branchselectname').value=pgno;
  	 
  	}
  	
  	//---------------- for edit button
  	
  /*

  	 function callForEditBranch(id){
	  	document.getElementById('paraFrm_Branchhiddencode').value=id;
	   	document.getElementById("paraFrm").action="PayrollZone_callforBranchedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
  
  */
    // common functions for 2 iterators...!
   	function newRowColor(cell) {
		cell.className='onOverCell';
	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
	
	}
function rowcallsF9(id)
    {
      document.getElementById('paraFrm_rowId').value=id; 
      callsF9(500,325,'PayrollZone_f9city.action'); 
   
     
    }
    function addEmployee(){
    	if(document.getElementById('paraFrm_empCode').value==''){
    			alert('Please select '+document.getElementById('employee').innerHTML);
    			return false;
    		}else{
    			document.getElementById('paraFrm').action="PayrollZone_addEmployeeToException.action";
    			document.getElementById('paraFrm').submit();
    		}
    }
    function checkEmpExcep(empCode,rowId){
    if(document.getElementById('checkbox'+rowId).checked==true)
    	 document.getElementById('hdeleteCode'+rowId).value=empCode;
    	 else  document.getElementById('hdeleteCode'+rowId).value="";
    	 var allCheck = true;
    	 var empCount = document.getElementById('empCount').value;
    	 for(var k=1;k<=empCount;k++){
   		 if(!(document.getElementById('checkbox'+k).checked)){
   		 	allCheck =false;
   		 	break;
   		 }
   		 }
   		 document.getElementById('checkAll').checked=allCheck;
       }
       
    function removeEmpExcep(){
    var empCount = document.getElementById('empCount').value;
    var isChecked = false;
    	for(var k=1;k<=empCount;k++){
   		 if(document.getElementById('checkbox'+k).checked){
   		 	isChecked =true;
   		 	break;
   		 }
   		 }
   		// alert('isChecked=='+isChecked);
   		 if(!isChecked){
   		 	alert('Please select at least one '+document.getElementById('employee').innerHTML+' to remove');
   		 	return false;
   		 }else{
   		 var conf = confirm("Do you really want to delete this record?");
 			if(conf) {
	   		 	document.getElementById("paraFrm").action="PayrollZone_removeEmployee.action";
		    	document.getElementById("paraFrm").submit();	
	    	}
   		 }
       }
       
   function checkAllEmpExcep(obj){
   
   		 var checkValue = obj.checked;
   		 var empCount = document.getElementById('empCount').value;
   		 for(var k=1;k<=empCount;k++){
   		 if(checkValue){
   		 //alert(checkValue);
   		//alert(document.getElementById('empCodeList'+k).value);
   		 	document.getElementById('hdeleteCode'+k).value = document.getElementById('empCodeList'+k).value;
   		 	document.getElementById('checkbox'+k).checked=true;
   		 	}
   		 else{
   		 	document.getElementById('hdeleteCode'+k).value = "";
   		 	document.getElementById('checkbox'+k).checked=false;
   		 	}
   		 }
   		     	 
       }
       function callPageEmp(id){
 
	   	document.getElementById('myPageEmp').value=id;
	    document.getElementById('paraFrm_showEmp').value=id;
	    document.getElementById('paraFrm').action="PayrollZone_getEmpExceptionData.action";
	   document.getElementById('paraFrm').submit();
   }
   function onEmp()
   {
  	var val= document.getElementById('selectnameEmp').value;
	document.getElementById('paraFrm_showEmp').value=eval(val);
	 document.getElementById('myPageEmp').value=eval(val);
	 document.getElementById('selectnameEmp').value=eval(val);
	   document.getElementById('paraFrm').action="PayrollZone_getEmpExceptionData.action";
	   
	   document.getElementById('paraFrm').submit();
   }
   
   function previousEmp()
   {
     
   var pageno=	document.getElementById('myPageEmp').value;
   	
	 document.getElementById('myPageEmp').value=eval(pageno)-1;
	 document.getElementById('paraFrm_showEmp').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="PayrollZone_getEmpExceptionData.action";
	   if(pageno!=1)
	   document.getElementById('selectnameEmp').value=eval(pageno)-1;
	 
	   document.getElementById('paraFrm').submit();
   }
   pgshowEmp();
  	function pgshowEmp()
  	{
  		
  	var pgno=document.getElementById('paraFrm_showEmp').value;

  	if(!(pgno==""))
  	 document.getElementById('selectnameEmp').value=pgno;
  	 
  	}
</script>
