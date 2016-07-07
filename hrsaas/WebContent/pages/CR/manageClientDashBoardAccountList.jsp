	<%@ taglib prefix="s" uri="/struts-tags"%>
	
	<%@ include file="/pages/common/labelManagement.jsp"%>
	<STYLE type=text/css>
a:hover {
	COLOR: #FF0000;
	text-decoration: underline;
}
</STYLE>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
	<script type="text/javascript"
		src="../pages/common/include/javascript/sorttable.js"></script>
		
	<script type="text/javascript">
	
	
	
	function addManageAccount(){
	 var accountID = document.getElementById("paraFrm_informToken").value;
	 var accountCode=document.getElementById("paraFrm_informCode").value;
		
		if(accountID!=""){
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='ManageClientDashBoard_saveDashBoardManageAccountInfo.action?accountID='+accountCode;
		document.getElementById("paraFrm").submit();
	}
	else{
		alert("Please Select Account");
	}
	
	}
	
	function deleteCurrentRow(informCode){
	
	var conf=confirm("Are you sure to delete this record?");   
  		if(conf) {
  		document.getElementById("paraFrm").target='_self';
	document.getElementById("paraFrm").action='ManageClientDashBoard_removeManageAccount.action?informCode='+informCode;
	document.getElementById("paraFrm").submit();
		
			}
   }
	function backFun() {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ManageClientDashBoard_input.action';
			document.getElementById('paraFrm').submit();
		}
		
	function callforInternalUser(accountID,accountName){
    var dashBoardID=document.getElementById("mdashBoardID").value;
    var dashBoardName=document.getElementById("mdashBoardName").value;
    var backToAccountLsit="1";
    document.getElementById("paraFrm").action="ManageClientDashBoard_callForInternalUsers.action?dashBoardID="+dashBoardID+"&dashBoardName="+dashBoardName+"&accountID="+accountID+"&accountName="+accountName+"&backToAccountLsit="+backToAccountLsit;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
    }
    
     function callforExternalUser(accountID,accountName){
	 var dashBoardID=document.getElementById("mdashBoardID").value;
	 var dashBoardName=document.getElementById("mdashBoardName").value;
	var backToAccountLsit="1"
	 document.getElementById("paraFrm").action="ManageClientDashBoard_callForExternalUsers.action?dashBoardID="+dashBoardID+"&dashBoardName="+dashBoardName+"&accountID="+accountID+"&accountName="+accountName+"&backToAccountLsit="+backToAccountLsit;
     document.getElementById('paraFrm').target = "_self";
	 document.getElementById("paraFrm").submit();
    }
    
	function callSearchAccount(){
	
	try{
		var searchMessagestr =document.getElementById('paraFrm_searchMessageText').value;
		
		 if(searchMessagestr=="Search Account")
		 {
		 searchMessagestr="";
		 }
		document.getElementById("paraFrm").action="ManageClientDashBoard_getDashBoardAccountList.action?searchMessagestr="+searchMessagestr
     	document.getElementById('paraFrm').target = "_self";
		 document.getElementById("paraFrm").submit();
		}catch(e){
			alert("Error ----- "+e); 
		}	
	}
	function setSearchFocus(e)
	{
		//alert("Hi");
		if(e.keyCode == 13)
		{
		
		//alert(window.event.keyCode.value);
	callSearchAccount();
		}
	
	}	
	setText();
	setCRMText();
 
 	function setText(){
 	//alert("Set Search is called")
	//alert(document.getElementsByName("searchMessageText").value);
	if(document.getElementById('paraFrm_searchMessageText').value == '' ){
		document.getElementById('paraFrm_searchMessageText').value ='Search Account'; 
		document.getElementsByName('paraFrm_searchMessageText').value ='Search Account';
		document.getElementById('paraFrm_searchMessageText').style.color = '#ACACAC';
	}else if(document.getElementById('paraFrm_searchMessageText').value=='Search Account'){
			document.getElementById('paraFrm_searchMessageText').style.color = '#ACACAC';
		}
	} 
	function setCRMText(){
 
	if(document.getElementById('paraFrm_crmName').value == '' ){
		document.getElementById('paraFrm_crmName').value ='Search CRM'; 
		document.getElementsByName('paraFrm_crmName').value ='Search CRM';
		document.getElementById('paraFrm_crmName').style.color = '#ACACAC';
	}else if(document.getElementById('paraFrm_crmName').value=='Search CRM'){
			document.getElementById('paraFrm_crmName').style.color = '#ACACAC';
		}
	}
	
	/*function setSearchFocus()
	{
		 
		if(window.event && window.event.keyCode == 13)
		{
	callSearch();
	
		}
	
	}*/
	function callSearch(){
	try{
		var searchAccountstr =document.getElementById('paraFrm_searchMessageText').value;
		 if(searchAccountstr=="Search Account")
		 {
		 searchAccountstr="";
		 	document.getElementById('paraFrm_crmName').value = '';
		 	document.getElementById('paraFrm_crmCode').value = '';
		 }else{
		 	document.getElementById('paraFrm_crmName').value = '';
		 document.getElementById('paraFrm_crmCode').value = '';
		 }
		 
		document.getElementById('paraFrm').action= 'AccountMaster_applyFilter.action?searchMessage='+searchAccountstr;
		// alert("Action:---"+document.getElementById('paraFrm').action);
			document.getElementById("paraFrm").target="_self";
			document.getElementById('paraFrm').submit();
		}catch(e){
			//alert("Error ----- "+e);
		}	
	}
	function setSearchCRMFocus()
	{
		 
		if(window.event && window.event.keyCode == 13)
		{
	
	callSearchCRM();
		}
	
	}
	function callSearchCRM(){
	try{
		var searchMessagestr =document.getElementById('paraFrm_crmName').value;
		 if(searchMessagestr=="Search CRM")
		 {
		 searchMessagestr="";
		 }
		javascript:callsF9(500,325,'ManageClientDashBoard_f9crmEmployee.action');
		}catch(e){
			alert("Error ----- "+e); 
		}	
	}
	</script>
		
		
	
	<s:form action="ManageClientDashBoard" id="paraFrm" validate="true"
		theme="simple" target="main">
		<s:hidden name="show" value="%{show}" />
		<s:hidden name="hiddencode" />
		<s:hidden name="accountCode" />
		<s:hidden id="mdashBoardID" name="dashBoardID"/>
		<s:hidden id="mdashBoardName" name="dashBoardName"/>
		<s:hidden name="listLength" />
		<s:hidden name="myPage" id="myPage" />
		<table width="100%" border="0" align="right" class="formbg">
			<tr>
				<td valign="bottom" class="txt">
				<table width="100%" align="right" class="formbg">
					<tr>
						<td><strong class="text_head"> <img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /> </strong></td>
						<td width="93%" class="txt"><strong class="text_head">DashBoard Manage Accounts</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table> 
				</td>
			</tr>
			<tr>
			<td>
			<table width="100%" align="right">
			<tr>
			<td>
				<input type="button" value="Back" onclick="backFun()"/>
			</td>	
			</tr>
			</table>
			</td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" class="formbg">
					<tr>
						<td>
						<table width="100%" border="0">
							<tr>
								<td width="15%" colspan="1" height="22">
								<label class="set"  ondblclick="callShowDiv(this);">
								DashBoard Name</label>:
								<font color="red"></font>
								</td>
								<td width="85%" colspan="2" height="22">
								<s:textfield  theme="simple"  size="25" readonly="true" value="%{dashBoardName}"cssStyle="background-color: #F2F2F2;">
								</s:textfield> </td>
								
							</tr>
							
							<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
	
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="1"
							cellspacing="2">
							<tr>
								<td class="formhead"><strong class="forminnerhead">Manage 
								Accounts</strong></td>
							</tr>
		
							<tr>
								<td colspan="3">
								<table width="100%" border="0" cellpadding="1" cellspacing="0">
									<tr>
	
										<td width="15%" nowrap="nowrap" colspan="3"><label
											class="set" id="crm" name="crm"
											ondblclick="callShowDiv(this);">Select Account</label>:<font
											color="red">*</font></td>
										<td nowrap="nowrap"><s:hidden name="keepHidden" /> 
										<s:hidden name="informCode" id="paraFrm_informCode" />
										
										<s:textfield name="informToken" readonly="true"	cssStyle="background-color: #F2F2F2;"/> 
										<s:textfield name="informName" id="paraFrm_informName" size="40" readonly="true"
										cssStyle="background-color: #F2F2F2;" /> 
										<img src="../pages/images/recruitment/search2.gif" width="16" height="15" class="iconImage" id="ctrlShow"
											onclick="javascript:callsF9(500,325,'ManageClientDashBoard_f9ManageAccount.action');" />
										<input type="button" value="Add" Class="add" onclick="return addManageAccount();"></td>
									</tr>
									</table>
									</td>
									</tr>
									<tr height="28">
						<td bgcolor="#6979AC">
						<table width="97%" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr>	
						<!--  <td width="3%" nowrap="nowrap" align="left" id="ctrlShow">
					
						<s:textfield size="30" maxlength="30" name="crmName" onkeypress="setSearchCRMFocus();" 
						onfocus="clearText('paraFrm_crmName','Search CRM')"
						onblur="setCRMText('paraFrm_crmName','Search CRM')" 
						size="20" />			
						<s:hidden name="crmToken" /> 
						<s:hidden name="crmCode" />
						<img
						onclick="return callSearchCRM();" align="absmiddle"
						style="cursor: pointer;" src="../pages/mypage/images/search.gif" />
					
					</td> -->
						<td width="12%" nowrap="nowrap" class="mainheader">&nbsp;</td>
						<td colspan="3" width="85%" class="mainheader" nowrap="nowrap" align="right" id="ctrlShow">
						
					 	<s:textfield onkeypress="setSearchFocus(event);" onfocus="clearText('paraFrm_searchMessageText','Search Account')" 
					 	onblur="setText('paraFrm_searchMessageText','Search Account')" 	name="searchMessageText" size="20" />
						<img onclick="return callSearchAccount()" align="absmiddle"	style="cursor: pointer;" src="../pages/mypage/images/search.gif" />
						</td>
					</tr>
				
					</table>
			
					</td>
					</tr>
					<%
							int totalPage = 0;
							int pageNo = 0;
						%>
						
						<!--	Pagination code  -->
					<s:if test="listLength">
					<table width="100%">
					<tr align="right">
						<td id="ctrlShow" width="30%" align="right" class="" width="100%"><b>Page:</b>
						<%
								totalPage = (Integer) request.getAttribute("totalPage");
								pageNo = (Integer) request.getAttribute("pageNo");
							%> 
							<a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'ManageClientDashBoard_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'ManageClientDashBoard_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'ManageClientDashBoard_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'ManageClientDashBoard_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ManageClientDashBoard_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
							</tr>
							</table>
					</s:if>
					
									<tr valign="top">
										<!-- KEEP INFORMED LIST TABLE  STARTS -->
										<td align="left" colspan="4">
										<table width="100%" border="0"   class="sortable">
											
											<tr>
												<td width="5%" class="formth"><strong class="text_head">Sr.No.</strong></td>
												<td width="15%" class="formth"><strong class="text_head">Account Code</strong></td>
												<td width="30%" class="formth"><strong class="text_head">Account Name</strong></td>
												<td width="10%" class="formth"><strong class="text_head">Employee Mapping</strong></td>
												<td width="10%" class="formth"><strong class="text_head">Client Mapping</strong></td>
												<td width="5%" align="center" class="formth"><strong class="text_head">Remove</strong></td>
											</tr>
											
										
										
										
									<tr valign="top">
										
										<!-- KEEP INFORMED LIST TABLE  STARTS -->
										<td colspan="4"> 
										
											
											<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
												int i = 0;
												int cn = pageNo * 20 - 20;
										%>
											<%
												int counter = 1;
											%>
											<s:iterator value="keepInformedList">
											
											<s:if test="isParent=='YY'">
												<tr bgcolor="#C5BE97">
													<td  align="center">
													<input type="hidden" name="informCode" id="code"/> <s:property	value="srno" />)
													</td>
													<td  id="informToken" align="center">
													<s:property value="informToken" />	&nbsp;
													</td>
													<td  >&nbsp; <s:property	value="informName" /> &nbsp;
													</td>
													<td class="sortableTD"  align="center">
													<a href="#?" class="link"
														onclick="javascript:callforInternalUser('<s:property value="informCode"/>','<s:property value="informName"/>');">Manage</a>
													</td>
													<td class="sortableTD"  align="center">
													<a href="#?" class="link"
														onclick="javascript:callforExternalUser('<s:property value="informCode"/>','<s:property value="informName"/>');">Manage</a>
													</td>
													<td  align="center">
													
													<img src="../pages/common/css/icons/delete.gif"	onclick="deleteCurrentRow('<s:property value="informCode"/>')">
													</td>
	
												</tr>
												<%
													counter++;
												%>
												<%
										d = i;
										%>
												</s:if>
												<s:else>
												
												
												<tr>
													<td  align="center">
													<input type="hidden" name="informCode" id="code"/> <s:property	value="srno" />)
													</td>
													<td  id="informToken" align="center">
													<s:property value="informToken" />	&nbsp;
													</td>
													<td  >&nbsp; <s:property	value="informName" /> &nbsp;
													</td>
													<td class="sortableTD" align="center">
													<a href="#?" class="link"
														onclick="javascript:callforInternalUser('<s:property value="informCode"/>','<s:property value="informName"/>');">Manage</a>
													</td>
													<td class="sortableTD"  align="center">
													<a href="#?" class="link"
														onclick="javascript:callforExternalUser('<s:property value="informCode"/>','<s:property value="informName"/>');">Manage</a>
													</td>
													<td  align="center">
													
													<img src="../pages/common/css/icons/delete.gif"	onclick="deleteCurrentRow('<s:property value="informCode"/>')">
													</td>
	
												</tr>
												<%
													counter++;
												%>
												<%
										d = i;
										%>
												
												
												
												
												
												
												</s:else>
											</s:iterator>
										</table>
										</td>
									</tr>
							
								
	
	
						</table>
						</td>
					</tr>
					<tr>
			
			<td>
				<input type="button" value="Back" onclick="backFun()"/>
			</td>	
			</tr>
				</table>
				</td>
			</tr>
			
			<table>
			
			</table>
			
		</table>
	</s:form>
	
	
	
