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
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="AccountMaster" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="hiddencode" />
	<s:hidden name="accountCode" />
	<s:hidden name="businessName" />
	<s:hidden name="isActive" />
	<s:hidden name="recordCreatedOn" />
	<s:hidden name="recordModifiedOn" />
	<s:hidden name="accountId" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="hiddenSearchMessage" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Account
					Master List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<td width="79%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td  align="right"><input type="button" value="Usage Tracking" class="token" onclick="callCustomerHistoryReport();"/></td>
				
			</table>
			</td>
		</tr>
		
		<tr height="28">
			<td bgcolor="#6979AC">

			<table width="97%" align="center" border="0" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="3%" nowrap="nowrap" align="left" id="ctrlShow">
					
					<s:textfield size="30" maxlength="30"
												name="crmName" onkeypress="setSearchCRMFocus();" 
												
									onfocus="clearText('paraFrm_crmName','Search CRM')"
						onblur="setCRMText('paraFrm_crmName','Search CRM')" 
						 size="20" />			
												
												
												<s:hidden name="crmToken" /> 
												<s:hidden name="crmCode" />
												
												<img
						onclick="return callSearchCRM();" align="absmiddle"
						style="cursor: pointer;" src="../pages/mypage/images/search.gif" />
												
					
					</td>
					
					<td width="12%" nowrap="nowrap" class="mainheader">&nbsp;</td>
					<td colspan="3" width="85%" class="mainheader" nowrap="nowrap"
						align="right" id="ctrlShow"><!--<s:select
						name="reportType" id="paraFrm_reportType"
						list="#{'':'--Select--','I':'Account ID','N':'Account Name'}" /> </select>
					 --><s:textfield onkeypress="setSearchFocus();" 
						onfocus="clearText('paraFrm_searchMessageText','Search Account')"
						onblur="setText('paraFrm_searchMessageText','Search Account')" 
						name="searchMessageText" size="20" /> <img
						onclick="return callSearch();" align="absmiddle"
						style="cursor: pointer;" src="../pages/mypage/images/search.gif" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
				border="0">
				<tr>
					<td width="30%" align="left"><strong class="text_head">
					Account Master List </strong></td>
					<td width="70%" align="right">
					<%
						int totalPage = 0;
							int pageNo = 0;
					%> <s:if test="listLength">
						<table>
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 			pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
									onclick="callPage('1', 'F', '<%=totalPage%>', 'AccountMaster_input.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P', 'P', '<%=totalPage%>', 'AccountMaster_input.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
									onkeypress="callPageText(event, '<%=totalPage%>', 'AccountMaster_input.action');return numbersOnly();" />
								of <%=totalPage%> <a href="#"
									onclick="callPage('N', 'N', '<%=totalPage%>', 'AccountMaster_input.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AccountMaster_input.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
						</table>
					</s:if></td>
				</tr>


				<tr>
					<td colspan="2">
					<table width="100%" border="0" class="formbg">
						<tr>
							<td class="formtext">
							<table width="100%" border="0">
								<tr>
									<td class="formth" align="center" width="05%"><label
										name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>
									</td>
									<td class="formth" align="center" width="15%"><label
										name="acc.id" id="acc.id" ondblclick="callShowDiv(this);"><%=label.get("acc.id")%></label>
									</td>
									<td class="formth" align="center" width="20%"><label
										name="business.name" id="business.name"
										ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label>
									</td>
									<!--<td class="formth" align="center" width="10%"><label
										name="crm.report.mapping" id="crm.report.mapping"
										ondblclick="callShowDiv(this);"><%=label.get("crm.report.mapping")%></label>
									</td>

									<td class="formth" align="center" width="10%"><label
										name="business.users" id="business.users"
										ondblclick="callShowDiv(this);"><%=label.get("business.users")%></label>
									</td>

									-->
									<td class="formth" align="center" width="15%" nowrap="nowrap"><label
										name="created.on" id="created.on"
										ondblclick="callShowDiv(this);"><%=label.get("created.on")%></label>
									</td>

									<td class="formth" align="center" width="15%" nowrap="nowrap"><label
										name="last.modified" id="last.modified"
										ondblclick="callShowDiv(this);"><%=label.get("last.modified")%></label>
									</td>

									<td class="formth" align="center" width="5%"><label
										class="set" name="is.active" id="is.active1"
										ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									</td>
									<td class="formth" align="center" width="5%">Edit</td>

								</tr>
								<s:if test="listLength">

									<%
										int count = 0;
									%>
									<%!int d = 0;%>
									<%
										int i = 0;
												int cn = pageNo * 20 - 20;
									%>
									<s:iterator value="iteratorList">
										<s:hidden name="isParent" />
										<s:if test="isParent=='YY'">
											<tr bgcolor="#C5BE97">
												<td width="05%" align="center" class="sortableTD"><%=++cn%>
												<%
													++i;
												%> <s:hidden name="srNo" /></td>
												<s:hidden value="%{accountCode}" id='<%="accountCode" + i%>' />
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('accountCode' + '<%=i%>').value;
												</script>


												<td class="sortableTD" align="left" width="10%"><s:property
													value="accountId" /></td>

												<td class="sortableTD" align="left" width="20%"><s:property
													value="businessName" /></td>
												<!--<td class="sortableTD" width="10%" align="center"><a
													href="#?" class="link"
													onclick="javascript:callForCRMMapping('<s:property value="accountCode"/>');">Manage
												</a></td>
												<td class="sortableTD" width="10%" align="center"><s:if
													test="isParent=='YY'">
													<a href="#?" class="link"
														onclick="javascript:callForBusinessUser('<s:property value="accountCode"/>');">Manage</a>
												</s:if> <s:else></s:else></td>

												-->
												<td class="sortableTD" align="center" width="15%" nowrap="nowrap"><s:property
													value="recordCreatedOn" /></td>
												<td class="sortableTD" align="left" width="15%" nowrap="nowrap"><s:property
													value="recordModifiedOn" /></td>
												<td class="sortableTD" align="center" width="5%"><s:property
													value="isActive" /></td>

												<td class="sortableTD" width="5%" id="ctrlShow"
													align="center"><input type="button" class="rowEdit"
													title="Click for edit"
													onclick="callForEdit('<s:property value="accountCode"/>')" />

												</td>


											</tr>
										</s:if>
										<s:else>
											<tr>
												<td width="05%" align="center" class="sortableTD"><%=++cn%>
												<%
													++i;
												%> <s:hidden name="srNo" /></td>
												<s:hidden value="%{accountCode}" id='<%="accountCode" + i%>' />
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('accountCode' + '<%=i%>').value;
												</script>


												<td class="sortableTD" align="left" width="10%"><s:property
													value="accountId" /></td>

												<td class="sortableTD" align="left" width="20%"><s:property
													value="businessName" /></td>
												<!--<td class="sortableTD" width="10%" align="center"><a
													href="#?" class="link"
													onclick="javascript:callForCRMMapping('<s:property value="accountCode"/>');">Manage
												</a></td>
												<td class="sortableTD" width="10%" align="center"><s:if
													test="isParent=='YY'">
													<a href="#?" class="link"
														onclick="javascript:callForBusinessUser('<s:property value="accountCode"/>');">Manage</a>
												</s:if> <s:else></s:else></td>

												-->
												<td class="sortableTD" align="center" width="15%"  nowrap="nowrap"><s:property
													value="recordCreatedOn" /></td>
												<td class="sortableTD" align="left" width="15%"  nowrap="nowrap"><s:property
													value="recordModifiedOn" /></td>
												<td class="sortableTD" align="center" width="5%"><s:property
													value="isActive" /></td>

												<td class="sortableTD" width="5%" id="ctrlShow"
													align="center"><input type="button" class="rowEdit"
													title="Click for edit"
													onclick="callForEdit('<s:property value="accountCode"/>')" />

												</td>


											</tr>

										</s:else>





									</s:iterator>

									<%
										d = i;
									%>
								</s:if>

								<s:else>
									<td align="center" colspan="6" nowrap="nowrap"><font
										color="red">There is no data to display</font></td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="rptType" />
		<tr>
			<td width="100%">
			<table width="100%">
				<td width="79%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td  align="right"><input type="button" value="Usage Tracking" class="token" onclick="callCustomerHistoryReport();"/></td>
				
			</table>
			</td>
		</tr>
	</table>
</s:form>


<script>

	function callCustomerHistoryReport(){		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AccountMaster_customerHistoryReport.action';
		document.getElementById('paraFrm').submit();		
	}



function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AccountMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		  
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='AccountMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function callReport(type){
			document.getElementById('paraFrm_rptType').value=type;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='AccountMaster_report.action';
			document.getElementById('paraFrm').submit();
	}
		
  	function callForEdit(accountId){
 /// alert(accountId);
	 
	   	document.getElementById("paraFrm").action="AccountMaster_calforedit.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
   
    function callForCRMMapping(accountId){
    	//// alert(accountId);
	   	document.getElementById("paraFrm").action="AccountMaster_callForCRMMapping.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
    
    function callForBusinessUser(accountId){
    	/// alert(accountId);
	   	document.getElementById("paraFrm").action="AccountMaster_callForBusinessUser.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
     
	 setText();
	setCRMText();
 function setText(){
 
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
	function setSearchFocus()
	{
		 
		if(window.event && window.event.keyCode == 13)
		{
	callSearch();
	
		}
	
	}
	
	function setSearchCRMFocus()
	{
		 
		if(window.event && window.event.keyCode == 13)
		{
	
	callSearchCRM();
		}
	
	}
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
	
	function callSearchCRM(){
	try{
		var searchMessagestr =document.getElementById('paraFrm_crmName').value;
		 if(searchMessagestr=="Search CRM")
		 {
		 searchMessagestr="";
		 }
		javascript:callsF9(500,325,'AccountMaster_f9crmEmployee.action');
		}catch(e){
			alert("Error ----- "+e); 
		}	
	}
	
	function callCRMReport(type){
			document.getElementById('paraFrm_rptType').value=type;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='AccountMaster_callCRMReport.action';
			document.getElementById('paraFrm').submit();
	}
	function callClientReport(type){
			document.getElementById('paraFrm_rptType').value=type;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='AccountMaster_callClientReport.action';
			document.getElementById('paraFrm').submit();
	}
	

</script>



