<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="AssetApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset
					Approval  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td class="text_head"><a
						href="AssetApproval_checkData.action?status=P">Pending List</a> |
					<a href="AssetApproval_checkData.action?status=A">Approved List</a>
					| <a href="AssetApproval_checkData.action?status=R">Rejected
					List</a></td>
				</tr>
				<s:hidden name="listLength"></s:hidden>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<td width="50%" align="left"></td>

					<div align="right"><s:hidden name="myPage" /> <% int totalPage = 0; int pageNo = 0; %>
					<s:if test="noData">
					</s:if> <s:else>
						<td id="ctrlShow" width="100%" align="right" class=""><b>Page:</b>
						<%	 totalPage = (Integer) request.getAttribute("totalPage");
										 pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'AssetApproval_checkData.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'AssetApproval_checkData.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'AssetApproval_checkData.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'AssetApproval_checkData.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AssetApproval_checkData.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:else></div>

					<s:hidden name="status" />
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td class="text_head" colspan="3"><strong>
					<%
                    		String status = (String) request.getAttribute("stat");
                    	out.println(status);
                    		if (status != null) {
                    			
                    		} else {
                    			out.println("Pending List");
                    		}
                    	%>
					</strong></td>
				</tr>
				<tr>
					<td width="5%" valign="top" class="formth" nowrap="nowrap"><label class="set"
						id="asstSrNo" name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>


					<td class="formth" width="10%" nowrap="nowrap"><b><label
						id="assetId5" name="assetId" class="set"
						ondblclick="callShowDiv(this);"> <%=label.get("assetId")%></label></b></td>

					<td width="23%" valign="top" class="formth" nowrap="nowrap"><label class="set"
						id="employee.id" name="employee.id"
						ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="42%" valign="top" class="formth" nowrap="nowrap"><label class="set"
						id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td width="18%" valign="top" class="formth" nowrap="nowrap"><label class="set"
						id="assetAppDate" name="assetAppDate"
						ondblclick="callShowDiv(this);"><%=label.get("assetAppDate")%></label></td>

					<td width="12%" valign="top" class="formth" nowrap="nowrap"><label class="set"
						id="assetDetails" name="assetDetails"
						ondblclick="callShowDiv(this);"><%=label.get("assetDetails")%></label></td>

				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="8" align="center" nowrap="nowrap"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%!int i = 0;%>
				<%
                        	int k = 1;
                        	int c = 0;
                        %>
                        
                        	<%
							int cn5 = pageNo * 20 - 20;
							%>

				<s:iterator value="recordList">

					<tr>
						<td class="sortableTD" width="5%" nowrap="nowrap"><%=++cn5%>
						
							<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="assetId" /></td>
										
						<td class="sortableTD" width="23%" nowrap="nowrap" ><s:property
							value="empToken" /><s:hidden name="assetCode"
							value="%{assetCode}" /><s:hidden name="level" /><s:hidden
							name="empID" /></td>
						<td class="sortableTD" width="42%" nowrap="nowrap"><s:property
							value="empName" /></td>
						<td class="sortableTD" width="18%" nowrap="nowrap"><s:property
							value="appliedDate" /></td>


						<td class="sortableTD" width="12%" align="left">
						<%	
					if (status != null && status.trim().equals("Pending List"))
					{
				   %> <input type="button" name="view_Details" class="token"
							value=" View / Approve"
							onclick="viewDetails('<s:property value="assetCode"/>','<s:property value="level"/>')" />
						<%}else{ %> <input type="button" name="view_Details" class="token"
							value="View Details"
							onclick="viewDetails('<s:property value="assetCode"/>','<s:property value="level"/>')" />

						<%} %>  <s:hidden name="empCode"
							value="%{empID}" /></td>

					</tr>
					<%
						k++;
						c++;
					%>
				</s:iterator>
				<%
               i = k;
               %>
			</table>
			</td>
		</tr>
		<tr>
			<input type="hidden" name="count" id="count" value="<%=c%>" />
			<td>
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="2">
				<tr>



					<s:hidden name="hiddenTrfCode" />
				</tr>
			</table>
			</td>
		</tr>

	</table>

	<s:hidden name="applicationLevel"></s:hidden>
	<s:hidden name="hiddenAssetCode" />


</s:form>

<script>
	// callOnload();
	
	function saveValidate(obj){
	var count = eval(document.getElementById("count").value);
	//alert(document.getElementById("paraFrm_listLength").value);
	//alert(1);
	if(eval(document.getElementById("count").value)==0){
			alert("There is no record to save.");
			return false;
		}
		
		else{
		
		
		var status ="";
		for (var length = 1 ;length <= count;length++ ){
			if(document.getElementById("check"+length).value !="P"){
				status="changed";
			}
		}
		if(status != "changed"){
			alert("Please change the status of atleast one application .");
			return false;
		}
		for( var len = 1; len<=count; len++ ) {
			var val=document.getElementById('comm'+len).value
			if(eval(val.length)>200) {
				alert(document.getElementById('assetComment').innerHTML.toLowerCase()+' accepts only 200 ' + 
				   ' characters. Please remove ' + (eval(val.length) - 200) + ' characters in record no: '+len +'.');
				return false;
			}
		}

		obj.disabled= true;
	document.getElementById("paraFrm").action="AssetApproval_save.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
		function callReset(){
			document.getElementById("paraFrm_status").value=='P';
			document.getElementById("paraFrm").action="AssetApproval_input.action";
 			document.getElementById("paraFrm").submit();
 			document.getElementById("paraFrm").target="main";
 	
	 }
	 function callOnload(){
	 	var check =<%=i%>;
	 alert(document.getElementById("paraFrm_status").value);

	 if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
	}
	
	function viewDetails(assetCode,level){
		document.getElementById('paraFrm_hiddenAssetCode').value = assetCode;
		
		document.getElementById('paraFrm_applicationLevel').value = level;
		
		
		//document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'AssetEmployee_callByApprover.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
	 function callLength(obj){ 
					var cmt =document.getElementById(obj.id).value;
					var remain = 200 - eval(cmt.length);
					if(eval(remain)< 0)
						document.getElementById(obj.id).style.background = '#FFFF99';
					else 
						document.getElementById(obj.id).style.background = '#FFFFFF';
 }  			
</script>