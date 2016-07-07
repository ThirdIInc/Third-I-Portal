<%@ taglib prefix="s" uri="/struts-tags"%>
	<div align="center" id="overlay" style="z-index:3; visibility:hidden; position:absolute;width:776px;height:350px;margin:0px;left:0;top:0;background-color:#A7BEE2;background-image:url('images/grad.gif');filter:progid:DXImageTransform.Microsoft.alpha(opacity=15);-moz-opacity:.1;opacity:.1;">
</div>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
<s:form action="LeaveEncashApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr><s:hidden name="show" />
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave Encashment Approval</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
					<td colspan="2"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
						<tr>
							<td height="27" class="formtxt"><a
								href="LeaveEncashApproval_callstatus.action?status=P">Pending List
							</a> | <a href="LeaveEncashApproval_callstatus.action?status=A">Approved
							List</a> | <a href="LeaveEncashApproval_callstatus.action?status=R">Rejected
							List</a></td>
						</tr>
						<s:hidden name="apprflag" />
						<s:hidden name="listLength"/> 
					</table>
					</td>
				</tr><!--
				<tr>
					<td width="78%"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /><br />
					<s:if test="%{apprflag}"></s:if><s:else>
						<input name="save" type="button" class="save" value="    Save "
							onclick="saveValidate1(this);" />
					</s:else> <input name="Submit3" type="button" class="reset"
						value="     Reset" onclick="return callReset();" /></td>
					<s:hidden name="status" />
				</tr>
			--></table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    	<td height="27" class="formtxt">
                    	<strong><%String status = (String)request.getAttribute("stat");
	                    	if(status!=null){out.println(status);}
	                    	else{out.println("Pending List");}%></strong></td>
	                    	
	                   <!-- Paging implementation -->
						 <%
						 	int total1 = (Integer) request.getAttribute("abc");
						 	int PageNo1 = (Integer) request.getAttribute("xyz");
						 %>
						<%if(total1 >0){ %> 
							<td align="right" colspan="2"><b>Page:</b>  
							<%if (!(PageNo1 == 1)) { %>
							 	<a href="#" onclick="callPage('1');"> <img
									src="../pages/common/img/first.gif" width="10" height="10"
									class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /></a> 
							<% }
							 if (total1 < 5) {
							 	for (int i = 1; i <= total1; i++) { %> 
							 		<a href="#" onclick="callPage('<%=i %>');"> 
							 		<% if (PageNo1 == i) { %> 
							 			<b><u><%=i%></u></b> 
							 		<% } else{ %>
							 			<%=i%><%} %> </a> 
							 	<% }
							 }
							
							 if (total1 >= 5) {
							 	for (int i = 1; i <= 5; i++) { %> 
							 		<a href="#" onclick="callPage('<%=i %>');"> 
							 		<% if (PageNo1 == i) { %>
							 			 <b><u><%=i%></u></b> 
							 		<% } else %>
							 			<%=i%> </a> 
							 	<%}
							 }
							 	
							if (!(PageNo1 == total1)) { %>
								...<a href="#" onclick="next()"> <img
										src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
										href="#" onclick="callPage('<%=total1%>');"> <img
										src="../pages/common/img/last.gif" width="10" height="10"
										class="iconImage" /></a> 
							<% } %> 
							
							<select name="selectname" onchange="on()" id="selectname">
							<% for (int i = 1; i <= total1; i++) { %>
								<option value="<%=i%>"><%=i%></option>
							<% } %>
							</select></td>&nbsp;
						<%} %>
				  <!-- Paging implementation ends -->
                  </tr>
             </table>
             </td>
        </tr>

						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr class="td_bottom_border">
								<s:hidden name="myPage" id="myPage" />
									<td width="10%" valign="top" class="formth"><label
								name="srno" id="srno"
								ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
									<td width="20%" valign="top" class="formth"><label
								name="employee.id" id="employee.id"
								ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
									<td width="30%" valign="top" class="formth"><label
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap"><label
								name="encashdt" id="encashdt"
								ondblclick="callShowDiv(this);"><%=label.get("encashdt")%></label></td>
									<!--<td width="15%" valign="top" class="formth" nowrap="nowrap"><label
								name="stat" id="stat"
								ondblclick="callShowDiv(this);"><%=label.get("stat")%></label></td>
									--><td width="20%" valign="top" class="formth" nowrap="nowrap"><label
								name="details" id="details"
								ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>
									<!--<td width="25%" valign="top" class="formth"><label
								name="remark" id="remark"
								ondblclick="callShowDiv(this);"><%=label.get("remark")%></label></td>
								--></tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="5" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%!int i = 0;%>
								<%
								int k = 1;
								%>
								
								<!-- For paging -->
								<%int count=0; %>
								<%!int d=0; %>
								<%
								int j = 0;
								int cn= PageNo1*20-20;
								%>

								<s:iterator value="list">

									<tr class="sortableTD">
										<td width="5%" align="left" class="sortableTD"><%=++cn%>
									</td>
										<td class="sortableTD"width="10%"><s:property
											value="tokenNo" /><s:hidden name="encashAppNo"  /><s:hidden
											name="level" /><s:hidden name="empCode" /></td>
										<td class="sortableTD" width="30%"><s:property
											value="empName" /></td>
										<td class="sortableTD" width="20%" nowrap="nowrap"><s:property value="date" />
										<s:hidden name="date" /></td>
										<!--<td align="center" width="10%" class="sortableTD" nowrap="nowrap">
										<s:if
											test="%{leaveencash.apprflag}">
											<s:property value="statusNew" /></s:if>
										 <s:else>
											<s:select name="checkStatus" id="<%="check"+k %>"
												cssStyle="width:100" theme="simple"
												list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
										</s:else></td> 
										--><td class="sortableTD" width="10%" align="center" nowrap="nowrap"><input
											type="button" name="view_Details" class="token"
											value=" View Details "
											onclick="viewDetails('<s:property value="encashAppNo"/>','<s:property value="empCode"/>')" />&nbsp;
											<!--<input 
											type="button" name="view_Details" class="token"
											value=" Encashment History "
											onclick="viewHistory('<s:property value="empCode"/>')" />
											
										--></td>
										<!--<td class="sortableTD" width="20%"><s:if
											test="%{leaveencash.apprflag}">
											<s:property value="statusRemark" />
										</s:if> <s:else>
											<s:textarea   name="statusRemark" value="%{statusRemark}" 
												 cols="20" rows="2" ></s:textarea>
										</s:else></td>

									--></tr>
									<%
									k++;
									%>
								</s:iterator>
								<%
								i = k;
								k=1;
								%>
							</table>
							</td>
						</tr>
						<!--<tr>
							<td colspan="3"><s:if test="%{apprflag}"></s:if><s:else>
								<input type="button" name="Submit222" class="save"
									value="    Save " onclick="saveValidate1(this);" />
							</s:else> <input name="Submit32" type="button" class="reset"
								value="     Reset" onclick="return callReset();" /></td>
						</tr>

--></table>

<s:hidden name="eId"/>
</s:form>
<script>
	callOnload();
	
	function saveValidate(){
//	 alert("safd"+document.getElementById("paraFrm_listLength").value);
	if(document.getElementById("paraFrm_listLength").value==0){
	alert("Please Change the Status then Save");
	return false;
	}else{
	document.getElementById("paraFrm").action="LeaveEncashApproval_saveStatus.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
	
	function saveValidate1(obj)
	{
 		var count;
		var myCount = <%= --i %>; 
 		flag = false;
	    for(count = 1; count <= eval(myCount); count++)
		{
			idValue  = document.getElementById("check"+count).value;
			if(idValue == "A" || idValue == "R")
			{
				flag = true;
				break;
			}
		}
		if(!flag)
		{
			alert("Please change the "+document.getElementById('stat').innerHTML.toLowerCase());
			return false;
			
	 	}
	 	obj.disabled=true;
	 	document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
	 	document.getElementById("paraFrm").action="LeaveEncashApproval_saveStatus.action";
 	document.getElementById("paraFrm").submit();
	} 
	
function callReset(){
	document.getElementById("paraFrm").action="LeaveEncashApproval_reset.action";
	document.getElementById("paraFrm").submit();
}
	 
function callOnload(){
	var check =<%=i%>;
	if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
		for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;
		}
	}
}
	 
	 function viewDetails(encashAppNo,empCode){
	//   var wind = window.open('','wind','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "main";
		document.getElementById('paraFrm').action = 'LeaveEncashment_retriveForApproval.action?leaveEncashAppNo='+encashAppNo+'&encashEmpId='+empCode;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	  function viewHistory(empCode){
	 
		 var wind = window.open('','wind','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = 'LeaveEncashment_retriveForHistory.action?employeeNo='+empCode;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		
	}
	
	function callPage(id){
		try{
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="LeaveEncashApproval_callPage2.action";
	   	document.getElementById('paraFrm').submit();
	   	}catch(e){
	   		alert(e);
	   	}
	}

	function next(){
   		var pageno=	document.getElementById('myPage').value;
   		if(pageno=="1"){
	   		document.getElementById('myPage').value=2;
		    document.getElementById('paraFrm_show').value=2;
    	} else{
			 document.getElementById('myPage').value=eval(pageno)+1;
			 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="LeaveEncashApproval_callPage1.action";
	   document.getElementById('paraFrm').submit();
	}	
   
	function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="LeaveEncashApproval_callPage1.action";
		document.getElementById('paraFrm').submit();
	}
   
   
	function newRowColor(cell){
		cell.className='onOverCell';
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
	 		cell.className='tableCell2';
		}else {
			cell.className='tableCell1';
		}
	}
	
	pgshow();

	function pgshow(){
		var pgno=document.getElementById('paraFrm_show').value;
		if(!(pgno==""))
  			document.getElementById('selectname').value=pgno;
  	}
  	
  	function previous(){
	   	var pageno=	document.getElementById('myPage').value;
	   	document.getElementById('myPage').value=eval(pageno)-1;
		document.getElementById('paraFrm_show').value=eval(pageno)-1;
		document.getElementById('paraFrm').action="LeaveEncashApproval_callPage1.action";
		document.getElementById('paraFrm').submit();
	}
	
	 
</script>
