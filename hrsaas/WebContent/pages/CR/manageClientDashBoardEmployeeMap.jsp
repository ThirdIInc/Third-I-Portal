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
	
	
	
	function addEmployee(){
	 var keepInformCode = document.getElementById("paraFrm_informCode").value;
	 var keepInformedName = document.getElementById("paraFrm_informName").value;
	 var keepInformedToken = document.getElementById("paraFrm_informToken").value;
	 var maccountID = document.getElementById("maccountID").value;
	 
	 
	if(keepInformedName!=""&&keepInformedToken!=""){
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='ManageClientDashBoard_saveDashBoardEmployeeInfo.action?keepInformCode='+keepInformCode+'&keepInformedName='+keepInformedName+'&keepInformedID='+keepInformedToken+'&maccountID='+maccountID;
		document.getElementById("paraFrm").submit();
	
	}
	else{
		alert("Please Select Employee First");
	}
	
	}
	function deleteCurrentRow(informCode,dashBoardID,dashBoardName,informName){
	
	var conf=confirm("Are you sure to delete this record?");   
  		if(conf) {
  		document.getElementById("paraFrm").target='_self';
	document.getElementById("paraFrm").action='ManageClientDashBoard_removeEmployee.action?informCode='+informCode+"&userType=I&informName="+informName;
	document.getElementById("paraFrm").submit();
		
			}
   }
	function backFun() {
			//window.history.back(); 
			 var back = document.getElementById("backToAccountLsit").value;
		
			if(back==1){
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ManageClientDashBoard_getDashBoardAccountList.action';
			document.getElementById('paraFrm').submit();}
			else{
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ManageClientDashBoard_input.action';
			document.getElementById('paraFrm').submit();
			}
		}

	function callforUserFilter(informCode,dashBoardID,dashBoardName,informName){
	document.getElementById('paraFrm').target = "_self";
    document.getElementById("paraFrm").action="ManageClientDashBoard_userFilter.action?informCode="+informCode+"&userType=I&informName="+informName;
	document.getElementById("paraFrm").submit();
    }
	
	</script>
	<s:form action="ManageClientDashBoard" id="paraFrm" validate="true"
		theme="simple" target="main">
		<s:hidden name="show" value="%{show}" />
		<s:hidden name="hiddencode" />
		<s:hidden name="accountCode" />
		<s:hidden name="dashBoardID"/>
		<s:hidden name="dashBoardName"/>
		<s:hidden name="accountName"/>
		<s:hidden id="maccountID" name="accountID"/>
		<s:hidden id="backToAccountLsit" name="backToAccountLsit"/>
		<s:hidden name="myPage" id="myPage" />
		<!--tab1-->
		<table width="100%" border="0" class="formbg">
			<tr>
				<td valign="bottom" class="txt">
				<table width="100%"  class="formbg">
					<tr>
						<td><strong class="text_head"> <img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /> </strong></td>
						<td width="93%" class="txt"><strong class="text_head">DashBoard Mapping To Employee</strong></td>
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
			<table width="100%" >
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
								<label class="set" name="dashBoardName" id="dashBoardName" ondblclick="callShowDiv(this);">
								DashBoard Name</label>:
								<font color="red"></font>
								</td>
								<td  colspan="2" height="22">
								<s:textfield  theme="simple" size="25" readonly="true" value="%{dashBoardName}"cssStyle="background-color: #F2F2F2;">
								</s:textfield> </td>
								<s:if test="accountID!=null">
								<s:if test="accountID!=''">
								<td  colspan="1" height="22">
								<label class="set" name="accountName" id="accountName" ondblclick="callShowDiv(this);">
								Account Name</label>:
								<font color="red"></font>
								</td>
								<td  colspan="2" height="22">
								<s:textfield  theme="simple" size="45" readonly="true" value="%{accountName}"cssStyle="background-color: #F2F2F2;">
								</s:textfield> </td>
								</s:if>
								</s:if>
								<s:elseif test="accountID!=null">
								<td></td>
								</s:elseif>
								
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
								<td class="formhead"><strong class="forminnerhead">Add
								CRM </strong></td>
							</tr>
	
							<tr>
								<td colspan="3">
								<table width="100%" border="0" cellpadding="1" cellspacing="0">
									<tr>
	
										<td width="5%" nowrap="nowrap" colspan="3">
										<label class="set" id="crm" >
										Select Employee</label>:
										<font color="red">*</font></td>
										<td nowrap="nowrap"><s:hidden name="keepHidden" /> 
										<s:hidden name="informCode" id="paraFrm_informCode" />
										<s:textfield name="informToken" readonly="true"	cssStyle="background-color: #F2F2F2;"/> 
										<s:textfield name="informName" id="paraFrm_informName" size="40" readonly="true"
										cssStyle="background-color: #F2F2F2;" /> 
										<img src="../pages/images/recruitment/search2.gif" width="16" height="15" class="iconImage" id="ctrlShow"
											onclick="javascript:callsF9(500,325,'ManageClientDashBoard_f9informTo.action');" />
										<input type="button" value="Add" Class="add" onclick="return addEmployee();"></td>
									</tr>
									<tr valign="top">
										
										<!-- KEEP INFORMED LIST TABLE  STARTS -->
										<td align="left" colspan="4">
										<table width="100%" border="0"   class="sortable">
											<tr>
												<td width="10%" class="formth"><strong class="text_head">Sr.No.</strong></td>
												<td width="20%" class="formth"><strong class="text_head">Employee ID</strong></td>
												<td width="30%" class="formth"><strong class="text_head">Employee Name</strong></td>
												<td width="20%" class="formth"><strong class="text_head">Dashboard View</strong></td>
												<td width="10%" align="center" class="formth"><strong class="text_head">Remove</strong></td>
											</tr>
											
										</table>
										</td>
									</tr>
	
									<tr valign="top">
										
										<!-- KEEP INFORMED LIST TABLE  STARTS -->
										<td colspan="4"> 
										<table width="100%" border="0" id="keepInformedTable">
	
											<%
												int counter = 1;
											%>
											<s:iterator value="keepInformedList">
												<tr>
	
													<td width="10%" align="left">
													<input type="hidden" name="informCode" id="code"/> <%=counter%>)
													</td>
													<td width="20%" id="informToken" align="left"><s:property value="informToken" />
													&nbsp;
													</td>
													<td width="30%" >&nbsp; <s:property	value="informName" /> &nbsp;
													</td>
													<td width="20%" align="center" class="sortableTD"><a href="#" class="link"
														onclick="javascript:callforUserFilter('<s:property value="informCode"/>','<s:property value="dashBoardID"/>','<s:property value="dashBoardName"/>','<s:property value='informName'/>');">Manage</a>
														</td>
													<td width="10%" align="center">
													
													<img src="../pages/common/css/icons/delete.gif"	onclick="deleteCurrentRow('<s:property value="informCode"/>','<s:property value="dashBoardID"/>','<s:property value="dashBoardName"/>','<s:property value="informName"/>')"></td>
	
												</tr>
												<%
													counter++;
												%>
											</s:iterator>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
	
	
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			
			<tr>
			<td>
			<table>
			<tr>
			
			<td>
				<input type="button" value="Back" onclick="backFun()"/>
			</td>	
			</tr>
			</table>
			</td>
			</tr>
		<!--tab1-->
		</table>
		
	</s:form>
	
	
	
