<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>
<s:form action="User" validate="true" id="paraFrm"	theme="simple">

	<s:hidden name="userBean.userToken" />
	<s:hidden name="userBean.userEmpName" />
	<s:hidden name="userBean.userName" />
	<s:hidden name="userBean.login_profile" />
	<s:hidden name="hiddenLockStatus" />
	<s:hidden name="loginCode" />
	<s:hidden name="userBean.userActive" />
	<s:hidden name="userBean.login_profile_ID" />
	<s:hidden name="userBean.userEmpID" />
	<s:hidden name="userBean.login_access_profile" />
	<s:hidden name="userBean.login_access_profile_ID" />
	<s:hidden name="userBean.validUpto"/>


	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td colspan="1" width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td colspan="1" width="80%" class="txt"><strong
						class="text_head">User Management</strong></td>
					<td colspan="1" width="5%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" cellspacing="2" cellspacing="2" border="0">
				<tr>
				<td width="40%" colspan="1"><s:submit cssClass="add"
					action="User_addNew" value="    Add New" /> <input type="button"
					class="search"
					onclick="javascript:callsF9(500,325,'User_f9User.action');"
					value="    Search " />
					<input  type="button" value="Report" onclick="callReport();"/></td>

				<%
					int totalPage = 0;
					int pageNo = 0;
				%>

				<s:if test="modeLength">
					<td id="ctrlShow" width="60%" align="right" class="" colspan="2"><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'User_calRlPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'User_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'User_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'User_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'User_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
				</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="2" cellspacing="2"
				border="0">
				<tr>
					<td colspan="1" class="txt" width="60%"><strong
						class="text_head">User Management</strong></td>
					<td colspan="2" align="right" width="40%"><input type="button"
						id="ctrlShow" class="delete" theme="simple" value=" Delete"
						onclick="return chkDelete();" /></td>
				</tr>


				<!-- start iterator -->

				<tr>
					<td colspan="5">
					<%
					try {
					%>
					<table width="100%" border="0" class="formbg" cellspacing="2"
						cellspacing="2">
						<tr>

							<td class="formtext">
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<tr>

									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" width="8%" colspan="1"><label
										class="set" name="sno" id="sno"
										ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
									<td class="formth" align="left" width="20%" colspan="1">Employee Id</td>
                                   
                                    <td class="formth" align="center" width="30%" colspan="1"><label
										class="set" name="employee" id="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
                                    
									<td class="formth" align="center" width="20%" colspan="1"><label
										class="set" name="user.name" id="user.name"
										ondblclick="callShowDiv(this);"><%=label.get("user.name")%></label></td>

									<!--<td class="formth" align="center" width="17%" colspan="1"><label
										class="set" name="profile" id="profile"
										ondblclick="callShowDiv(this);"><%=label.get("profile")%></label></td>

									--><td class="formth" align="center" width="17%" colspan="1"><label
										class="set" name="active" id="active"
										ondblclick="callShowDiv(this);"><%=label.get("active")%></label></td>



									<s:if test="modeLength">
										<td align="right" class="formth" id="ctrlShow" nowrap="nowrap"
											width="5%" colspan="1"><input type="checkbox"
											id="selAll" style="cursor: hand;"
											title="Select all records to remove"
											onclick="selectAllRecords(this);" /></td>
									</s:if>
								</tr>
								<s:if test="modeLength">

									<%
									int count = 0;
									%>
									<%!int d = 0;%>
									<%
											int i = 0;
											int cn = pageNo * 20 - 20;
									%>



									<s:iterator value="userList">
										<tr <%if(count%2==0){
												%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											ondblclick="javascript:callForEdit('<s:property value="loginCode"/>');">


											<td width="8%" align="center" colspan="1" class="sortableTD"><%=++cn%>
											<%
											++i;
											%><s:hidden name="srNo" /></td>
											<s:hidden value="%{loginCode}" id='<%="loginCode" + i%>'></s:hidden>
											<script type="text/javascript">
													records[<%=i%>] = document.getElementById('loginCode' + '<%=i%>').value;
												</script>
											<td width="20%" align="left" class="sortableTD" colspan="1"><s:property
												value="userToken" /> <input type="hidden"
												name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
                                            <td class="sortableTD" width="30%" colspan="1">&nbsp; <s:property
												value="userEmpName" /></td>
											<td class="sortableTD" width="20%" colspan="1">&nbsp; <s:property
												value="userName" /></td>
											<!--<td class="sortableTD" width="17%" colspan="1">&nbsp; <s:property
												value="login_profile" /></td>
											
											--><td class="sortableTD" width="17%" colspan="1">&nbsp; <s:property
												value="userActive" /></td>
									
											<td colspan="1" width="5%" id="ctrlShow" align="center"
												nowrap="nowrap" class="sortableTD"><input
												type="checkbox" class="checkbox" id="confChk<%=i%>"
												name="confChk"
												onclick="callForDelete1('<s:property value="loginCode"/>','<%=i%>')" /></td>
										</tr>

									</s:iterator>
									<%
									d = i;
									%>
								</s:if>
							</table>

							<%
								} catch (Exception e) {
								}
							%>
							</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td width="80%"><s:submit cssClass="add"
								action="User_addNew" value="    Add New" /> <input
								type="button" class="search"
								onclick="javascript:callsF9(500,325,'User_f9User.action');"
								value="    Search " />
								<input  type="button" value="Report" onclick="callReport();"/></td>
							<td colspan="2" width="20%" align="right"><s:if test="modeLength">
								<b>Total No. Of Records:</b>&nbsp;<s:property
									value="totalNoOfRecords" />
							</s:if></td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
/* function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'User_addNew.action';
		document.getElementById('paraFrm').submit();
	}

function searchFun() {
		
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='User_f9action.action';
		document.getElementById("paraFrm").submit();
	}*/




function callForDelete1(id,i)
	   {
	 /*alert("i am in dete fun in regionMasterInput jsp");*/
	   /** var flag='<%=d %>';*/
	/*alert('id----1-----'+id);*/
	/*alert('i----1-----'+i);*/
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   {
	   document.getElementById('hdeleteCode'+i).value="";
   }
}

function callForEdit(id){

	 
	  	document.getElementById('paraFrm_loginCode').value=id;
	   	 
	   	document.getElementById("paraFrm").action="User_editBusinessData.action";
	   	 
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }

function selectAllRecords(object) {
		if(object.checked) {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hdeleteCode' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
	}


function chk(){
   
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}

	function chkDelete()
	{
	 
	 if(chk()){
	
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	   document.getElementById('paraFrm').action="User_deleteChkRecords.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	document.getElementById('selAll').checked = false;
	    var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	
	}
	     return false;
	 }
	 }
	 else {
	 alert('please select atleast one record');
	 return true ;
	 }
	
}

function callReport(){
	 try{
	   document.getElementById('paraFrm').target = "_self";
	  document.getElementById('paraFrm').action="User_callReport.action";
	  document.getElementById('paraFrm').submit();
	 }catch(e){
 		alert(e);
	 }
	}
</script>
