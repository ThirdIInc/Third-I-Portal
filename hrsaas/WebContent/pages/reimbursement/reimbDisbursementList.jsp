<!-- @author: Mangesh Jadhav 19 Jan 2011  -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReimbDisbursement" validate="true" id="paraFrm"
	validate="true" theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Reimbursement
					Claim Disbursement</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="alertFlag">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<td><input type="button" class="back" value=" Back "
						onclick="return backFun();" /></td>
					<td>&nbsp;&nbsp;</td>
					
				</tr>
			</table>
			</td>
		</tr>
		</s:if>
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
				    function setAction(listType){
					    if(listType=="p"){
					      	document.getElementById('paraFrm').action='ReimbDisbursement_input.action';
					      	document.getElementById('paraFrm').submit();
					    }
					    else if(listType=="c"){
					      	document.getElementById('paraFrm').action='ReimbDisbursement_getClosedList.action';
					      	document.getElementById('paraFrm').submit();
					    }
					     
				    }
				     
				   </script>
					<td><a href="#" onclick="setAction('p');bacckFun()">Pending
					Disbursement</a> | <a href="#" onclick="bacckFun();setAction('c');">Closed
					Disbursement</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="10">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="4"><a href="#" id="hideLink" onclick="setFilter();">Hide/
					Expand Search</a></td>
					<td><input class="token" type="button" value="Generate Statement"
						onclick="callGenStatement();" /></td>
				</tr>
				<tr valign="top" id='FTR1'>
					<td width="22%" nowrap="nowrap"><label name="applId"
						id="applId3" ondblclick="callShowDiv(this);"><%=label.get("applId")%></label>
					<font color="red">*</font>:</td>
					<td width="28%" colspan="2"><s:textfield name="applIdSearch"
						theme="simple" size="25" /></td>
					<td width="22%" nowrap="nowrap"><label name="employee"
						id="employee3" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="22%"><s:hidden name="empIdSearch" theme="simple" />
					<s:hidden name="empTokenSearch" theme="simple" /><s:textfield
						name="empNameSearch" theme="simple" size="25" readonly="true" />
					</td>
					<td width="6%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ReimbAdminApproval_f9employeeSearch.action');">
					</td>

				</tr>

				<tr valign="top" id='FTR2'>
					<td width="22%" nowrap="nowrap"><label name="reimbHead"
						id="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label></td>
					<td width="22%"><s:hidden name="reimbHeadSearch" /> <s:textfield
						name="reimbHeadNameSearch" theme="simple" size="25"
						readonly="true" /></td>
					<td width="6%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ReimbAdminApproval_f9reimbHeadSearch.action');">
					</td>
					<td width="22%" nowrap="nowrap"><label name="applDate"
						id="applDate3" ondblclick="callShowDiv(this);"><%=label.get("applDate")%></label></td>
					<td width="22%"><s:textfield theme="simple"
						name="claimDateSearch" onkeypress="return numbersWithHiphen();"
						size="25" /></td>
					<td width="5%"><s:a
						href="javascript:NewCal('paraFrm_claimDateSearch','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>
					<td width="20%" nowrap="nowrap"><a href="#"
						onclick="return callSearch();">Search</a> <a href="#"
						style="display: inline;" onclick="return clearSearch();">Clear
					Search</a></td>
				</tr>


			</table>
			</td>
		</tr>
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" border="0">
					<%
						int totalPagePending = 0;
								int pageNoPending = 0;
					%>
					<s:if test="pendingLength">
						<tr>
							<td width="30%"><b>Pending Disbursements</b></td>
							<td><input type="button" class="token" value="Disburse"
								onclick="return callDisburse();"></td>
							<td id="ctrlShow" width="70%" align="right"><b>Page:</b> <%
 	totalPagePending = (Integer) request.getAttribute("totalPagePending");
 				pageNoPending = (Integer) request.getAttribute("pageNoPending");
 %> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPagePending%>', 'ReimbDisbursement_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPagePending%>', 'ReimbDisbursement_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNoPending%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPagePending%>', 'ReimbDisbursement_input.action');return numbersOnly();" />
							of <%=totalPagePending%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPagePending%>', 'ReimbDisbursement_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPagePending%>', 'L', '<%=totalPagePending%>', 'ReimbDisbursement_input.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
				<table width="100%" class="sorttable" border="0">
					<tr>
						<s:hidden name="myPage" id="myPage" />
						<td width="5%" class="formth"><label class="set" id="srno"
							name="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
						<td width="15%" class="formth"><label class="set" id="applId"
							name="applId" ondblclick="callShowDiv(this);"><%=label.get("applId")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td width="10%" class="formth"><label class="set"
							id="applDate" name="applDate" ondblclick="callShowDiv(this);"><%=label.get("applDate")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="claimAmt" name="claimAmt" ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></td>
						<td width="10%" class="formth">View Details</td>

					</tr>
					<%int count = 0; %>
					<s:if test="pendingLength">
						<%
							int p = pageNoPending * 20 - 20;
							
						%>
						<s:iterator value="disburseList">
							<tr>
								<td width="5%" class="sortableTD" align="center"><%=++p%>
								<%++count;%>
								</td>
								<td width="15%" class="sortableTD" width="30%"><s:property
									value="applRefNoList" /><s:hidden name="applRefNoList" /><s:hidden
									name="applIdList" /><s:hidden name="disbIdList" /><s:hidden
									name="empIdList" /><s:hidden name="empIdList" /><input type="hidden"
									name="claimIdHiddenList" id="claimIdList<%=count%>"
									value='<s:property value="applIdList"/>' /></td>
								<td width="30%" class="sortableTD" width="25%"><s:property
									value="empNameList" /></td>
								<td width="10%" class="sortableTD" width="25%" align="center"><s:property
									value="claimDateList" /><s:hidden name="claimDateList" /></td>
								<td width="15%" class="sortableTD" width="25%" align="right"><s:property
									value="claimAmtList" /><s:hidden name="claimAmtList" /></td>
								<td width="10%" class="sortableTD" width="25%" align="center"><s:hidden
									name="statusList" /> <s:if test="statusList=='D_'">
									<input type="button" class="token" value="Add To Statement"
										onclick="viewDetails('<s:property value="applIdList"/>',
									'D','<s:property value="disbIdList"/>')">
									<input type="hidden" name='disburse' id='disburseChk<%=count%>' />
								</s:if><s:else>
									<input type="checkbox" name='disburse'
										id='disburseChk<%=count%>' />
								</s:else></td>

							</tr>

						</s:iterator>

					</s:if>
					<s:else>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					</s:else>
					<input type="hidden" name="count" id="count" value='<%=count%>' />
				</table>
				</td>
			</tr>
		</s:if>

		<s:if test="%{listType == 'closed'}">
			<tr>
				<td>
				<table width="100%" border="0">
					<%
						int totalPage = 0;
								int pageNo = 0;
					%>
					<s:if test="closedLength">
						<tr>
							<td width="30%"><b>Closed Disbursements</b></td>
							<td id="ctrlShow" width="70%" align="right"><b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 				pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
								onclick="callPageClosed('1', 'F', '<%=totalPage%>', 'ReimbDisbursement_getClosedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageClosed('P', 'P', '<%=totalPage%>', 'ReimbDisbursement_getClosedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoFieldClosed" size="3"
								value="<%=pageNo%>" maxlength="10"
								onkeypress="callPageTextClosed(event, '<%=totalPage%>', 'ReimbDisbursement_getClosedList.action');return numbersOnly();" />
							of <%=totalPage%> <a href="#"
								onclick="callPageClosed('N', 'N', '<%=totalPage%>', 'ReimbDisbursement_getClosedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageClosed('<%=totalPage%>', 'L', '<%=totalPage%>', 'ReimbDisbursement_getClosedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
				<table width="100%" class="sorttable" border="0">
					<tr>
						<s:hidden name="myPageClosed" id="myPageClosed" />
						<td width="5%" class="formth"><label class="set" id="srno"
							name="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
						<td width="15%" class="formth"><label class="set" id="applId"
							name="applId" ondblclick="callShowDiv(this);"><%=label.get("applId")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td width="10%" class="formth"><label class="set"
							id="applDate" name="applDate" ondblclick="callShowDiv(this);"><%=label.get("applDate")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="claimAmt" name="claimAmt" ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></td>
						<td width="10%" class="formth">View Details</td>

					</tr>
					<s:if test="closedLength">
						<%
							int cn = pageNo * 20 - 20;
						%>
						<s:iterator value="closedList">
							<tr>
								<td width="5%" class="sortableTD" align="center"><%=++cn%></td>
								<td width="15%" class="sortableTD" width="30%"><s:property
									value="applRefNoList" /><s:hidden name="applRefNoList" /><s:hidden
									name="applIdList" /><s:hidden name="disbIdList" /><s:hidden
									name="empIdList" /></td>
								<td width="30%" class="sortableTD" width="25%"><s:property
									value="empNameList" /></td>
								<td width="10%" class="sortableTD" width="25%" align="center"><s:property
									value="claimDateList" /><s:hidden name="claimDateList" /></td>
								<td width="15%" class="sortableTD" width="25%" align="right"><s:property
									value="claimAmtList" /><s:hidden name="claimAmtList" /></td>
								<td width="10%" class="sortableTD" width="25%" align="center"><s:hidden
									name="statusList" /><input type="button" class="token"
									value="View Details"
									onclick="viewDetails('<s:property value="applIdList"/>',
									'C','<s:property value="disbIdList"/>')"></td>

							</tr>

						</s:iterator>

					</s:if>
					<s:else>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					</s:else>
				</table>
				</td>
			</tr>

		</s:if>
		<s:hidden name="listType"></s:hidden> 
		<s:hidden name="alertFlag"/>
		  <s:hidden name="source" id="source" />
	</table>
</s:form>

<script>
function viewDetails(claimId,claimStatus,disbId){

var alertMsgFlag = document.getElementById("paraFrm_alertFlag").value;
////alert("alertMsgFlag==="+alertMsgFlag);

	document.getElementById('paraFrm').action='ReimbDisbursement_retrieveDetails.action?claimId='+claimId+'&disbId='+disbId+'&status='+claimStatus+'&alertMsgFlag='+alertMsgFlag;
   	document.getElementById('paraFrm').submit();
}

function callPageClosed(id, pageImg, totalPageHid, action) {
		
		pageNo = document.getElementById('pageNoFieldClosed').value;	
		actPage = document.getElementById('myPageClosed').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldClosed').value = actPage;
			    document.getElementById('pageNoFieldClosed').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldClosed').value=actPage;
			    document.getElementById('pageNoFieldClosed').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldClosed').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldClosed').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldClosed').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageClosed').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	
		function callPageTextClosed(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldClosed').value;
		 	var actPage = document.getElementById('myPageClosed').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldClosed').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldClosed').focus();
		     document.getElementById('pageNoFieldClosed').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldClosed').focus();
		     document.getElementById('pageNoFieldClosed').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldClosed').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageClosed').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	function callReport(){
			document.getElementById('paraFrm').action="ReimbursementReport_getDisbReport.action";
	 		document.getElementById('paraFrm').submit();
	 		document.getElementById('paraFrm').target = 'main';
	 		
	}
	function callDisburse(){
		var count =document.getElementById('count').value;
		var selected=false;
		var claimIds="";
		try{
		for(var i=1;i<=eval(count);i++){
			if(document.getElementById('disburseChk'+i).checked){
			selected=true;
			if(claimIds=="")
			claimIds+=document.getElementById('claimIdList'+i).value;
			else{
				claimIds+=","+document.getElementById('claimIdList'+i).value;
			}
			}
		}
		if(!selected){
				alert('Please select atleast one record');
				return false;
		}else{
		//alert(claimIds);
			var conf=confirm('Do you really want to process the application?');
			 	if(!conf){
			 	return false;
			 	}
			document.getElementById('paraFrm').action="ReimbDisbursement_disburseClaim.action?claimIds="+claimIds;
	 		document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	  function callSearch() 
 {
 		if(document.getElementById('paraFrm_applIdSearch').value=="")
 		{
 			alert("Please enter Application Id ");
 			return false;
 		}
  	 	 if(document.getElementById('paraFrm_listType').value=="pending"){
  	 	 	document.getElementById('paraFrm').action='ReimbDisbursement_input.action';
  	 	 }else{
  	 	 	document.getElementById('paraFrm').action='ReimbDisbursement_getClosedList.action';
  	 	 }
  		document.getElementById('paraFrm').target = "_self";
		
		document.getElementById('paraFrm').submit();

 }
 function clearSearch(){
 		document.getElementById('paraFrm_empIdSearch').value='';
 		document.getElementById('paraFrm_empNameSearch').value='';
 		document.getElementById('paraFrm_empTokenSearch').value='';
 		document.getElementById('paraFrm_applIdSearch').value='';
 		document.getElementById('paraFrm_claimDateSearch').value='';
 		document.getElementById('paraFrm_reimbHeadNameSearch').value='';
 		document.getElementById('paraFrm_reimbHeadSearch').value='';
 		document.getElementById('paraFrm').target = "_self";
		 if(document.getElementById('paraFrm_listType').value=="pending"){
  	 	 	document.getElementById('paraFrm').action='ReimbDisbursement_input.action';
  	 	 }else{
  	 	 	document.getElementById('paraFrm').action='ReimbDisbursement_getClosedList.action';
  	 	 }
		document.getElementById('paraFrm').submit();
 }
 function setFilter(){
 		var style=document.getElementById('FTR1').style.display;
					    if(style=="none"){
					      	document.getElementById('FTR1').style.display='';
					      	document.getElementById('FTR2').style.display='';
					    }
					    else {
					      	document.getElementById('FTR1').style.display='none';
					      	document.getElementById('FTR2').style.display='none';
					    }
					     
				    }
 function callGenStatement(){
 			document.getElementById('paraFrm').action="ReimbDisbursement_generateStatement.action";
	 		document.getElementById('paraFrm').submit();
	 		document.getElementById('paraFrm').target = 'main';
 }
 function backFun() 
		{
			try{
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
				document.getElementById('paraFrm').submit();
			}
			catch(e)
			{
				alert("Error-------"+e);
			}
	}
	function bacckFun(){
				document.getElementById('paraFrm').action = "ReimbDisbursement_bacck.action";
				document.getElementById('paraFrm').submit();
			}
</script>