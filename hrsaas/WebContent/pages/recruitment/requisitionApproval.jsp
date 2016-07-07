<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"	src="../pages/common/include/javascript/sorttable.js"></script>
	
	
<s:form action="RequisitionApproval" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="myPage" />
	<s:hidden name="apprflag" />
	<s:hidden name="commentHdr" />
	<s:hidden name="commentItr" />
	<s:hidden name="listLength"></s:hidden>
	<s:hidden name="status" />
	<s:hidden name="hiddenEmpReqId"></s:hidden>
	<s:hidden name="holdFlag" />
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Manpower Requisition Approval </strong></td>
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
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td height="27" class="formtxt"><a
								href="RequisitionApproval_showApplicationList.action?status=P">Pending List
								</a> |<a
								href="RequisitionApproval_showApplicationList.action?status=A">Approved 	List
								</a> |<a
								href="RequisitionApproval_showApplicationList.action?status=R">Rejected List
								</a> | <a
								href="RequisitionApproval_showApplicationList.action?status=S">Send Back List
								</a> | <a
								href="RequisitionApproval_showApplicationList.action?status=H">On Hold List
								</a> | <a
								href="RequisitionApproval_showApplicationList.action?status=Q">Quick Requisition List
								</a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<%
				String data = (String) request.getAttribute("listType");
				String stat = "";
				if (data == null || data.equals("null") || data.equals("")
						|| data.equals(" ")) {
					stat = "P";
				} else if (data.equals("Pending List")) {
					stat = "P";
				} else if (data.equals("On Hold List")) {
					stat = "H";
				}
			%>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td></td>
					<td width="22%">&nbsp;</td>
				</tr>
			</table>

			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%">

					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="27" class="text_head"><strong> <%
 	String status = (String) request.getAttribute("listType");
 	String statusPass = "";
 	if (status != null) {
 		out.println(status);
 	} else {
 		out.println("Pending List");
 	}
 	if (status == null) {
 		statusPass = "P";
 	} else if (status.equals("Pending List")) {
 		statusPass = "P";
 	} else if (status.equals("Approved List")) {
 		statusPass = "A";
 	} else if (status.equals("Rejected List")) {
 		statusPass = "R";
 	} else if (status.equals("Send Back List")) {
 		statusPass = "S";
 	} else if (status.equals("On Hold List")) {
 		statusPass = "H";
 	}else if (status.equals("Quick Requisition List")) {
 		statusPass = "Q";
 	}
 	
 %> </strong></td>

							<%
								int totalPage = (Integer) request.getAttribute("totalPage");
								int pageNo = (Integer) request.getAttribute("PageNo");
							%>
							<s:if test="noData"></s:if>
							<s:else>

								<td align="right"><b>Page:</b> <input type="hidden"
									name="totalPage" id="totalPage" value="<%=totalPage%>">
								<a href="#" onclick="callPage('1','F','<%=statusPass %>');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P','P','<%=statusPass %>');"> <img
									title="Previous Page" src="../pages/common/img/previous.gif"
									width="10" height="10" class="iconImage" /> </a> <input
									type="text" name="pageNoField" id="pageNoField" theme="simple"
									size="3" value="<%= pageNo%>"
									onkeypress="callPageText(event,'<%=statusPass%>');return numbersOnly()"
									maxlength="4" /> of <%=totalPage%> <a href="#"
									onclick="callPage('N','N','<%=statusPass %>')"> <img
									title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPage%>','L','<%=statusPass %>');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</s:else>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="5%" valign="top" class="formth"><b> <label
								class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
							<td width="20%" valign="top" class="formth"><b> <label
								class="set" name="reqs.code" id="requisition.code"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
							<td width="11%" valign="top" class="formth" nowrap="nowrap"><b>
							<label class="set" name="reqs.date" id="reqs.date"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b></td>
							<td width="20%" valign="top" class="formth"><b> <label
								class="set" name="position" id="position"
								ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
							<td width="20%" valign="top" class="formth"><b> <label
								class="set" name="applied.by" id="applied.by"
								ondblclick="callShowDiv(this);"><%=label.get("applied.by")%></label></b></td>
							<td width="20%" valign="top" class="formth"><b> <label
								class="set" name="hiring.mgr" id="hiring.mgr"
								ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
							<!--
								<s:if test="commentHdr"></s:if>
							<s:else>
								<td width="4%" valign="top" class="formth" nowrap="nowrap"><b>
								<label class="set" name="rec.comments" id="rec.comments"
									ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td>
							</s:else>
							-->
							
						</tr>

						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">There is no data to display</font></td>
							</tr>
						</s:if>

						<%!int i = 0;%>
						<%
							int k = 1;
							int c = 0;
						%>
						<%
							int cnt = pageNo * 20 - 20;
							int m = 0;
						%>

						<s:iterator value="list">
							<tr onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this);"
								title="Double click for view Requisition"
								ondblclick="javascript:viewRequisition('<s:property value="reqNo"/>','<%=statusPass %>','<s:property value="level"/>')" />
								<td width="5%" class="sortabletd" align="center"><%=++cnt%>
								<%
								++m;
								%>
								</td>
								<td width="20%" class="sortabletd"><s:property
									value="reqName" /> <s:hidden name="empCode" /> <s:hidden
									name="reqNo" /></td>
								<td width="11%" class="sortabletd" align="center"><s:property
									value="reqDate" /><s:hidden name="reqDate" /></td>
								<td width="20%" class="sortabletd"><s:property
									value="appliedFor" /></td>
								<td width="20%" class="sortabletd"><s:property
									value="appliedBy" />&nbsp;</td>
								<td width="20%" class="sortabletd"><s:property
									value="hrManager" /></td>
								<s:hidden name="level" />
							<!-- 
										<s:if test="commentItr"></s:if>
								<s:else>
									<td width="4%" class="sortabletd" align="center"><s:hidden
										name="comment" id='<%="comment"+c %>' /> <img
										src="../pages/images/zoomin.gif" height="12" align="absmiddle"
										width="12" theme="simple"
										onclick="javascript:callWindow('<%="comment"+c %>','rec.comments','readonly','200','200');">
									</td>
								</s:else>
							 -->	
							
							</tr>
							<%
								k++;
								c++;
							%>
						</s:iterator>

						<%
							i = k;
							m = i;
						%>
					</table>
					<input type="hidden" name="count" id="count" value="<%=c%>" /></td>
				</tr>



			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td></td>
					<td width="22%">&nbsp;</td>
				</tr>
			</table>

			</td>
		</tr>



	</table>
</s:form>
<script>
	
	function saveValidate(){
		var count = document.getElementById("count").value;
		
		var flag  = false;
		
		if(count == 0){
			alert("There is no record to save");
			return false;
		}
		
		for(var i=0; i<count; i++){
			if(document.getElementById("reqStatus"+i).value != "")flag = true;
			
			var textareaValue = document.getElementById("comment"+i).value;
		
			if(!textareaValue == "" && textareaValue.length > 200){
				alert("Maximum length of comments is 200 characters");
				document.getElementById("comment"+i).focus();
				return false;
			}
		}
		
		if(!flag){
			alert("Please change the status of atleast one record");
			return false;
		}
		
		var con=confirm("Do you really want to save the records?");
		
		if(con){
			document.getElementById("paraFrm").action='RequisitionApproval_save.action';
	 		document.getElementById("paraFrm").submit();
		
		}else{
			return false;
		}

	}
	
	function resetStatus(status){
	
		document.getElementById("paraFrm").action="RequisitionApproval_reset.action?status="+status;
 		document.getElementById("paraFrm").submit();
	}
	 
	function viewDetails(loanCode, empCode){
	
		//var year = new Date().getFullYear();
		
		document.getElementById('paraFrm_loanCode').value = loanCode;
		document.getElementById('paraFrm_empCode').value  = empCode;
		//document.getElementById('paraFrm_year').value  = year;
		
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "LoanApplication_retriveForApproval.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
	
	function viewRequisition(reqCode,status,level){
	    var count=document.getElementById("count").value;
		var flag="true";
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetailsFromApproval.action?reqCode='+reqCode+'&formAction=RequisitionApproval_showApplicationList.action&statusKey='+status+'&flag='+flag+'&level='+level;
	    document.getElementById("paraFrm").submit();
	}
	
	
	function callPageText(id,status1){  
	   if(status1=="null" || status1=="" ){		
				status1="P";
			}
			
		    
		    var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action='RequisitionApproval_showApplicationList.action?status='+status1;
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
	
	
 function callPage(id,pageImg,status1){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
       
       
      if(status1=="null" || status1=="" ){		
			status1="P";
		}
       
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id;
	
		document.getElementById('paraFrm').action='RequisitionApproval_showApplicationList.action?status='+status1;
		document.getElementById('paraFrm').submit(); 
	}		
	
	
	
	
	function commntCountColor(){
	
	var count = document.getElementById("count").value;
	
	for(var i=0; i<count; i++){
	var val=document.getElementById('comment'+i).value;
	var count1=val.length;
	if(count1>200){
		document.getElementById('comment'+i).style.backgroundColor="yellow";
		
	}else{
		document.getElementById('comment'+i).style.backgroundColor="white";
		
	}
	
   }	
}

function newRowColor(cell)
   		 {
		   cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell) {
	
	 cell.className='Cell_bg_second';
	
		
	}
	
	</script>