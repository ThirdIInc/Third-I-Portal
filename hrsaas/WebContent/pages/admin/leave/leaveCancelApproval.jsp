<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<div align="center" id="overlay"
	style="z-index: 3; visibility: hidden; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;">
</div>
<s:form action="LeaveCancelApproval" method="post"
	name="LeaveCancelApproval" id="paraFrm" theme="simple" target="main">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<s:hidden name="show" />
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					Cancel Approval </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><!--<s:submit cssClass="add"
						action="LeaveCancel_forwardForm" value="   Forward" /> --><s:submit
						cssClass="add" onclick="return chkCancel();" value=" Approve"
						action="LeaveCancelApproval_cancelForm" /></td>
					<!-- Paging implementation -->
					<%
		 	int total1 = (Integer) request.getAttribute("abc");
		 	int PageNo1 = (Integer) request.getAttribute("xyz");
		 %>
					<%if(total1 >0){ %>
					<td align="right" colspan="2"><b>Page:</b> <%if (!(PageNo1 == 1)) { %>
					<a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
						src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /></a> <% }
			 if (total1 < 5) {
			 	for (int i = 1; i <= total1; i++) { %> <a href="#"
						onclick="callPage('<%=i %>');"> <% if (PageNo1 == i) { %> <b><u><%=i%></u></b>
					<% } else{ %> <%=i%>
					<%} %> </a> <% }
			 }
			
			 if (total1 >= 5) {
			 	for (int i = 1; i <= 5; i++) { %> <a href="#"
						onclick="callPage('<%=i %>');"> <% if (PageNo1 == i) { %> <b><u><%=i%></u></b>
					<% } else %> <%=i%> </a> <%}
			 }
			 	
			if (!(PageNo1 == total1)) { %> ...<a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
						href="#" onclick="callPage('<%=total1%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /></a> <% } %> <select name="selectname"
						onchange="on()" id="selectname">
						<% for (int i = 1; i <= total1; i++) { %>
						<option value="<%=i%>"><%=i%></option>
						<% } %>
					</select></td>
					&nbsp;
					<%} %>
					<!-- Paging implementation ends -->

				</tr>
			</table>


			<label></label></td>
		</tr>


		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="sortable">
				<tr class="td_bottom_border">
					<s:hidden name="myPage" id="myPage" />
					<td class="formth"><label name="srno" id="srno"
						ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
					<td class="formth"><label name="employee.id" id="employee.id"
						ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td class="formth"><label name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td class="formth"><label name="appdate" id="appdate"
						ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
					<td class="formth"><label name="details" id="details"
						ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>
					<td class="formth"><label name="approve" id="approve"
						ondblclick="callShowDiv(this);"><%=label.get("approve")%></label></td>
				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="7" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%int count=0; %>
				<%!int d=0; %>
				<%
							int i = 0;
							int cn= PageNo1*20-20;
								%>

				<s:iterator value="leaveCancel.cancelList">
					<tr class="sortableTD">
						<s:hidden name="leaveAppNo" />
						<s:hidden name="level" />
						<s:hidden name="status" />
						<s:hidden name="empCode" />
						<td width="5%" class="sortableTD"><%=++cn%>
						<%++i;%>
						</td>
						<td width="15%" class="sortableTD"><s:property value="tokenNo" /></td>
						<td width="50%" class="sortableTD"><s:property value="empName" /></td>
						<td width="15%" class="sortableTD"><s:property
							value="leaveapplicationDate" /> <s:hidden
							name="leaveapplicationDate" /></td>

						<td width="15%" nowrap="nowrap" class="sortableTD"><input
							type="button" name="view_Details" class="token"
							value=" Leave Details "
							onclick="viewDetails('<s:property value="leaveAppNo"/>','<s:property value="empCode"/>')" />
						</td>
						<td width="15%" class="sortableTD" align="center"><input type="hidden"
							name="hdeleteCode" id="hdeleteCode<%=i%>" /> <input
							type="checkbox" class="checkbox" id="confChk<%=i%>"
							name="confChk"
							onclick="callForDelete1('<s:property value="leaveAppNo"/>','<%=i%>')" />
						</td>

					</tr>


				</s:iterator>
				<%
							  
								d = i;
							%>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="leaveApplication.isApprovalClick" />
</s:form>

<script>

function viewDetails(leaveAppNo,empCode)
{
 
	 var wind = window.open('','wind','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = 'LeaveApplication_retriveForApproval.action?leaveApplicationNo='+leaveAppNo+'&employeeId='+empCode;
		document.getElementById('paraFrm_leaveApplication_isApprovalClick').value ='true';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		 
		
	}
function callForDelete1(id,i)
	   {
	 
	  var flag='<%=d %>';
	 // alert('id----1-----'+id);
	//  alert('i----1-----'+i);
	
		   if(document.getElementById('confChk'+i).checked == true)
		   {	  
		    document.getElementById('hdeleteCode'+i).value=id;
		   }
		   else
		   {
		   	document.getElementById('hdeleteCode'+i).value="";
		   	
		   	}
   		}
   
    function chkCancel()
	{
	 
			 if(chk()) 
			 {
				 var con=confirm('Do you want to approve the cancellation');
			 		if(con)
			 		{
			 		document.getElementById("overlay").style.visibility = "visible";
					document.getElementById("overlay").style.display = "block";
			 		  return true ;
			  		 }
				    else
				    { 
				   		    var flag='<%=d %>';
				  				for(var a=1;a<=flag;a++)
				  				{	
								document.getElementById('confChk'+a).checked = false;
								document.getElementById('hdeleteCode'+a).value="";
				
								}
				     			return false;
				 	}
			 }
		 else 
		 {
						 alert('Please select atleast one record');
						 return false;
		 }
	}
	
	
		function chk()
		{
			 var flag='<%=d %>';
			 
			  for(var a=1;a<=flag;a++)
			  {	
			  	 if(document.getElementById('confChk'+a).checked == true)
			 	   {	
			  			 document.getElementById('confChk'+a).value = true;
			  			  return true;
			  	   }
			  }
			  return false;
		}
		
		function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="LeaveCancelApproval_callPage2.action";
	   	document.getElementById('paraFrm').submit();
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
	   document.getElementById('paraFrm').action="LeaveCancelApproval_callPage1.action";
	   document.getElementById('paraFrm').submit();
	}	
   
	function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="LeaveCancelApproval_callPage1.action";
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
		document.getElementById('paraFrm').action="LeaveCancelApproval_callPage1.action";
		document.getElementById('paraFrm').submit();
	}   
   
</script>
