
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript"> 
	var jourApp = new Array(); 
</script>


<s:form action="ExpenseClaimApproval" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
<s:hidden name="show" value="%{show}" />
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="exAppId"/>

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
						class="text_head">Expense Claim Approval </strong></td>
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
						href="ExpenseClaimApproval_checkData.action?statusFld=P">Pending List</a> | <a
						href="ExpenseClaimApproval_checkData.action?statusFld=A">Approved List</a> | <a
						href="ExpenseClaimApproval_checkData.action?statusFld=R">Rejected List</a></td>
				</tr>
				<s:hidden name="apprflag" />
				<s:hidden name="listLength" />

			</table>
			</td>
		</tr>



		<tr>
			<td>
			<s:if test="%{apprflag}"></s:if><s:else>
				<input name="Submit222" type="button" class="save" value="    Save "
					onclick="return saveValidate();" />
			</s:else></td>
			<td width="22%">
			<div align="right"><span class="style2"><font
				color="red">*</font></span> Indicates Required</div>
			</td>
			<s:hidden name="statusFld" />
			 
		</tr>





		<tr>
			<td colspan="3">

			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class=formbg>
				 <tr>
                    <td class="text_head">
                    	<strong><%
                    		String status = (String) request.getAttribute("stat");
                    		if (status != null) {
                    			out.println(status);
                    		} else {
                    			out.println("Pending List");
                    		}
                    	%></strong></td><s:hidden name="status" value="<%=status%>"/>
                  </tr>
				
				
				<tr>
					<td align="right"><b>Page:</b> <%
							int total1 = (Integer) request.getAttribute("abc");
							int PageNo1 = (Integer) request.getAttribute("xyz");
						%> <%
							if (!(PageNo1 == 1)) {
						%><a href="#" onclick="callPage('1');"> <img
												src="../pages/common/img/first.gif" width="10" height="10"
												class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
												src="../pages/common/img/previous.gif" width="10" height="10"
												class="iconImage" /></a> <%
						 }
						 if (total1 < 5) {
						 	for (int i = 1; i <= total1; i++) {
						 %> <a href="#" onclick="callPage('<%=i %>');"> <%
						 if (PageNo1 == i) {
						 %> <b><u><%=i%></u></b> <%
						 } else
						 %><%=i%> </a> <%
						 		}
						 		}
						
						 		if (total1 >= 5) {
						 			for (int i = 1; i <= 5; i++) {
						 %> <a href="#" onclick="callPage('<%=i %>');"> <%
						 if (PageNo1 == i) {
						 %> <b><u><%=i%></u></b> <%
						 } else
						 %><%=i%> </a> <%
						 		}
						 		}
						 		if (!(PageNo1 ==1)) {
						 %>...<a href="#" onclick="next()"> <img
												src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
												href="#" onclick="callPage('<%=total1%>');"> <img
												src="../pages/common/img/last.gif" width="10" height="10"
												class="iconImage" /></a> <%
						 }
						 %> <select name="selectname" onchange="on()" id="selectname">
												<%
						for (int i = 1; i <= total1; i++) {
							%>
						
												<option value="<%=i%>"><%=i%></option>
												<%
						}
							%>
					</select></td>
				</tr>

				<tr>
					<td><s:hidden name="travelViewNo" />
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="sortable">
						<tr>
							<td width="5%" valign="top" class="formth"><label  class = "set" name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="22%" valign="top" class="formth"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td width="22%" valign="top" class="formth"><label  class = "set" name="request" id="request" ondblclick="callShowDiv(this);"><%=label.get("request")%></label></td>
							<td width="15%" valign="top" class="formth"><label  class = "set" name="application.date" id="application.date" ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label></td>
							<td width="10%" valign="top" class="formth"><label  class = "set" name="sts" id="sts1" ondblclick="callShowDiv(this);"><%=label.get("sts")%></label></td>
							<td width="15%" valign="top" class="formth"><label  class = "set" name="view" id="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label></td>
							<td width="25%" valign="top" class="formth"><label  class = "set" name="remarks" id="remarks" ondblclick="callShowDiv(this);"><%=label.get("remarks")%></label></td>
						</tr>
						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						</s:if>
						<%! int i = 0 ; %>
						<% int k = 1; %>

						<s:iterator value="claimList">
							<tr>
								<td class="border2" width="5%"><%=k %><s:hidden
									name="empId" /> <s:hidden name="appDate" /> <s:hidden
									name="claimAppId" /> <s:hidden name="level" /> <input
									type="hidden" name="empName"/> <input type="hidden"
									name="empToken" /></td>
								<s:hidden name="empToken" />
								<td class="border2" width="22%"><s:property value="empName" /></td>
								<td class="border2" width="22%"><s:property value="reqName" /></td>
								<td class="border2" width="15%"><s:property value="appDate" /></td>
								<td align="center" width="10%" class="border2"><s:if
									test="claimAppr.apprflag">
									<s:property value="statusNew" />
								</s:if> <s:else>
									<s:select name="checkStatus" id="<%="check"+k %>"
										cssStyle="width:100" theme="simple"
										list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
								</s:else></td>
								<td class="border2" width="15%" align="center"><input
									type="button" name="view_Details" class="token" value="Details"
									onclick="viewDetails('<s:property value="claimAppId"/>','<s:property value="level"/>','<s:property value="empId"/>')" />
								</td>
								<td class="border2" width="20%"><s:if
									test="claimAppr.apprflag">
									<s:property value="remark" />
								</s:if><s:else>
									<s:textarea name="remark" value="%{remark}" rows="1" cols="20"></s:textarea>
								</s:else></td>
								

							</tr>
							<%k++; %>
						</s:iterator>
						<% i=k ; %>
					</table>
					</td>
					
				</tr>

				<input type="hidden" name="count" id="count" value="<%=i%>"/>
				<s:hidden name="hiddenLevel"/>
				<s:hidden name="hiddenEmpId"/>
				<s:hidden name="hiddenClaimId"/>
				
			</table>
			</td>
		</tr>
		<s:if test="%{apprflag}"></s:if><s:else><tr>
					<td colspan="3">
						<input name="Submit222" type="button" class="save"
							value="    Save " onclick="return saveValidate();" />
					</td>
				</tr></s:else>
		
	</table>
	
</s:form>

<script>
	 callOnload();
	
	function saveValidate(){ 
	var count = document.getElementById("count").value;   
	if(document.getElementById("paraFrm_listLength").value==0){
	alert("No record to save");
	return false;
	}
	else{    
	var recordFlag ="false";
	for(var i=1; i< count;i++)
		{  
		//alert(""+document.getElementById("check"+i).value);
		if(document.getElementById("check"+i).value=="A" || document.getElementById("check"+i).value=="R")
			{  
			  recordFlag ="true";
			  break ;
			 }
	} //end of for
	//alert("recordFlag="+recordFlag);
	if(recordFlag =="false")
	{
	 alert("Please change the status of atleast one application!");
	 return false;
	} 
	
	
  document.getElementById("paraFrm").action="ExpenseClaimApproval_save.action";
  document.getElementById("paraFrm").submit();  
   }  
 }
		 
function callOnload(){
	 	var check =<%=i%>; 
	 		 if(document.getElementById("paraFrm_statusFld").value=='A' || document.getElementById("paraFrm_statusFld").value=='R'){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
	}
	function viewDetails(claimId,level,empId){ 

 status = document.getElementById('paraFrm_status').value;
 if(status=="Pending List")
 {
  status="P";
 }
		document.getElementById('paraFrm_exAppId').value = claimId;
		document.getElementById('paraFrm_hiddenClaimId').value = claimId;
		document.getElementById('paraFrm_hiddenLevel').value = level;
		document.getElementById('paraFrm_hiddenEmpId').value = empId;
		document.getElementById('paraFrm_status').value = status;
		//document.getElementById('paraFrm_statusFld').value = apprflag;
		//alert(status);
		//document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "ExpenseClaimApp_setClaimAppr.action";
		document.getElementById('paraFrm').submit();
		//document.getElementById('paraFrm').target = "";
		 
	}
	
	function callPage(id){
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action="ExpenseClaimApproval_checkData.action";
		document.getElementById('paraFrm').submit();
   }
   
   
   function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="ExpenseClaimApproval_checkData.action";
	   document.getElementById('paraFrm').submit();
   }
	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="ExpenseClaimApproval_checkData.action";
	   document.getElementById('paraFrm').submit();
	   
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="ExpenseClaimApproval_checkData.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
  	
  	
  	
  	
	
</script>