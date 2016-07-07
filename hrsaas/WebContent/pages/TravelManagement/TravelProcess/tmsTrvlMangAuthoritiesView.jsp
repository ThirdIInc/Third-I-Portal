<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TrvlMangAuthorities_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<script type="text/javascript">
	var records = new Array();
</script>
	
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="hiddenBranchId" />
	<s:hidden name="hiddenAppFlag" />
	<s:hidden name="myHidden" />
	<!-- flags for paging and navigation panel -->
	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="subTabLength" />
	<s:hidden name="hidTabEmpId"/>
	<s:hidden name="claimwrkflowflag"/>
	<s:hidden name="hiddenackwrkflowflag"/>
	<s:hidden name="hiddenclaimwrkflowflag"/>
	

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Management Authorities </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- The Following code Denotes  Include JSP Page For Button Panel -->


		<s:hidden name="mangAuthCode" />
		<s:hidden name="testBranchCode" />
		<s:hidden name="appFlag" />
		<s:hidden name="hidAppFlag" />
		<s:hidden name="branchCode" />
		<s:hidden name="mainSchdlrCode" />
		<s:hidden name="altMainSchdlrCode" />
		<s:hidden name="schdlrApprCode" />
		<s:hidden name="accntCode" />
		<s:hidden name="branch" />
		<s:hidden name="mainSchdlr" />
		<s:hidden name="altMainSchdlr" />
		<s:hidden name="schdlrAppr" />
		<s:hidden name="accnt" />
		<s:hidden name="status" />
		<s:hidden name="accntToken" />
		<s:hidden name="mainSchdlrToken" />
		<s:hidden name="altMainSchdlrToken" />
		<s:hidden name="schdlrApprToken" />
		<s:hidden name="hidChkFlg" />
		<s:hidden name="hidStatus" />
		
		<!-- ADDED BY REEBA -->
		<s:hidden name="alterAccountantCode" />
		<s:hidden name="alterAccountantToken" />
		<s:hidden name="escalationEmployeeCode" />
		<s:hidden name="escalationEmployeeToken" />
		<s:hidden name="alterAccountant"/>
		<s:hidden name="escalationEmployee"/>
		<s:hidden name="accountantEmailID"/>
		
		
		<s:if test="onLoadFlag"></s:if>
		<s:else>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"><!-- <div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>--></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>

						<td colspan="3">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr>
								<td width="19%" height="22" class="formtext" colspan="1"><label
									class="set" id="all.branch1" name="all.branch"
									ondblclick="callShowDiv(this);"><%=label.get("all.branch")%></label>
								:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="hidChkFlg" /><s:hidden value="hidAppFlag" /></td>

							</tr>
							<tr>
								<td valign="top" height="22" class="formtext" colspan="1"
									width="19%"><label class="set" id="branch.name1"
									name="branch.name" ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label>:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="branch" /></td>

							</tr>


							<tr>
								<td valign="top" height="22" class="formtext" colspan="1"
									width="19%"><label class="set" id="mainSch.name1"
									name="mainSch.name" ondblclick="callShowDiv(this);"><%=label.get("mainSch.name")%></label>:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="mainSchdlr" /></td>

							</tr>



							<tr>
								<td valign="top" height="22" class="formtext" colspan="1"
									width="19%"><label class="set" id="alt.mainSch1"
									name="alt.mainSch" ondblclick="callShowDiv(this);"><%=label.get("alt.mainSch")%></label>
								:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="altMainSchdlr" /></td>

							</tr>


							<tr>
								<td valign="top" height="22" class="formtext" colspan="1"
									width="19%"><label class="set" id="sch.appr1"
									name="sch.appr" ondblclick="callShowDiv(this);"><%=label.get("sch.appr")%></label>
								:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="schdlrAppr" /></td>

							</tr>



							<tr>
								<td valign="top" height="22" class="formtext" colspan="1"
									width="19%"><label class="set" id="accnt.name1"
									name="accnt.name" ondblclick="callShowDiv(this);"><%=label.get("accnt.name")%></label>
								:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="accnt" /></td>

							</tr>
							
							<!-- ADDED BY REEBA BEGINS -->
							<tr>
								<td valign="top" height="22" class="formtext" colspan="1"
									width="19%"><label class="set" id="alt.accnt1"
									name="alt.accnt" ondblclick="callShowDiv(this);"><%=label.get("alt.accnt")%></label>
								:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="alterAccountant" /></td>
							</tr>
							
							
							
							<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="27%"><label class="set" id="accountant.emailID"
								name="accountant.emailID" ondblclick="callShowDiv(this);"><%=label.get("accountant.emailID")%></label>
							:</td>

							<td width="30%" height="22" colspan="3">
								<s:property value="accountantEmailID" />
							</td>
						</tr>
						
						
							<!--<tr>
								<td valign="top" height="22" class="formtext" colspan="1"
									width="19%"><label class="set" id="violation.escalation1"
									name="violation.escalation" ondblclick="callShowDiv(this);"><%=label.get("violation.escalation")%></label>
								:</td>
								<td width="20%" height="22" colspan="2"><s:property
									value="escalationEmployee" /></td>

							</tr>
							--><!-- ADDED BY REEBA ENDS  -->


							<tr>
								<td width="19%" valign="top" height="22" class="formtext"
									nowrap="nowrap" colspan="1"><label class="set" id="desc1"
									name="desc" ondblclick="callShowDiv(this);"><%=label.get("desc")%></label>
								:</td>
								<td colspan="2" width="50%" style="overflow: hidden;"><s:textarea
									readonly="true" cssStyle="border:none;overflow: hidden;"
									name="description" cols="75" rows="5" /> <s:hidden
									name="description" /></td>

							</tr>

							<tr>
								<td height="22" class="formtext" colspan="1" width="19%"><label
									class="set" id="travel.sts1" name="travel.sts"
									ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label>
								:</td>
								<td height="22" colspan="2" width="20%"><s:property
									value="status" /></td>


							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"><!-- <div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>--></td>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="3" width="100%">
				<%
				try {
				%>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="formbg" class="sortable">

					<tr>

						<td width="100%">
						<table width="100%" border="0" cellpadding="1" cellspacing="2">
							<tr>
								<td height="15" class="formhead" colspan="6"><strong
									class="forminnerhead"><label class="set"
									id="subSch.list" name="subSch.list"
									ondblclick="callShowDiv(this);"><%=label.get("subSch.list")%></label></strong></td>
							</tr>
							<tr>

								<td class="formth" align="center" width="5%"><strong><label
									class="set" id="sr.no" name="sr.no"
									ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>

								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="emp.id" name="emp.id"
									ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></strong></td>

								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="emp" name="emp" ondblclick="callShowDiv(this);"><%=label.get("emp")%></label>
								</strong></td>

								<!-- 
									
									<td class="formth" align="center" width="15%"><strong><label
									class="set" id="travel" name="travel"
									ondblclick="callShowDiv(this);"><%=label.get("travel")%></label></strong></td>

								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="local.conv" name="local.conv"
									ondblclick="callShowDiv(this);"><%=label.get("local.conv")%></label></strong></td>

								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="lodge" name="lodge"
									ondblclick="callShowDiv(this);"><%=label.get("lodge")%></label></strong></td>
								 -->
								
							</tr>
							<%
							int i = 0;
							%>

							<s:iterator value="viewMangAuthList">
								<s:hidden name="itAltSchdlrCode" />
								<s:hidden name="itAuthCode" />
								
								<!-- ADDED BY REEBA -->
								<s:hidden name="itAltAccntCode" />
								<s:hidden name="itEscalationEmployeeCode" />

								<tr id="tr1" onmouseover="javascript:newRowColor(this);" 
								onmouseout="javascript:oldRowColor(this);">


									<td align="left" class="sortableTD" nowrap="nowrap"><s:hidden
										name="viewSrNo" /><s:property value="viewSrNo" /> <%
 ++i;
 %>
									</td>


									<td align="left" class="sortableTD"><s:hidden
										name="itViewEmpToken"></s:hidden> <s:property
										value="itViewEmpToken" /></td>

									<td class="sortableTD"><s:property value="itViewEmpName" /><s:hidden
										name="itViewEmpName" />
										<s:hidden name="itViewTrvl" />
										<s:hidden name="itViewConv" />
										<s:hidden name="itViewLodge" />										
									</td>

								<!-- 
									<td class="sortableTD"><s:property value="itViewTrvl" /><s:hidden
										name="itViewTrvl" /></td>

									<td class="sortableTD"><s:property value="itViewConv" /><s:hidden
										name="itViewConv" /></td>

									<td align="left" class="sortableTD"><s:property
										value="itViewLodge" /><s:hidden name="itViewLodge" /></td>
								
								 -->
									

								</tr>

							</s:iterator>



						</table>


						</td>
					</tr>
				</table>


				<%
					} catch (Exception e) {
					}
				%>
				</td>
			</tr>

		</s:else>

		<s:if test="onLoadFlag">
			<tr>
				<td colspan="3">

				<table width="100%" border="0" cellpadding="0" cellspacing="0">


					<tr>
						<td><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>


						<td align="right"><b>Page:</b> <%
 	int totalPage = (Integer) request.getAttribute("abc");
 	int pageNo = (Integer) request.getAttribute("xyz");
 %> <%
 		if (pageNo != 1) {
 		if (pageNo > totalPage) {
 		} else {
 %> <a href="#" onclick="callPage('1');"> <img
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P');">
						<img src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 	if (totalPage <= 5) {
 		if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (pageNo == totalPage - 1 || pageNo == totalPage) {
 			for (int z = pageNo - 2; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo > 3) {
 			for (int z = pageNo - 2; z <= pageNo + 2; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(pageNo == totalPage)) {
 		if (totalPage == 0 || pageNo > totalPage) {
 		} else {
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
							src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
							href="#" onclick="callPage('<%=totalPage%>');"> <img
							src="../pages/common/img/last.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 %> <select name="selectname" onchange="on()" id="selectname">
							<%
							for (int i = 1; i <= totalPage; i++) {
							%>

							<option value="<%=i%>"><%=i%></option>
							<%
							}
							%>
						</select></td>
					</tr>



				</table>
				</td>
			</tr>
			<s:if test="msgFlag">
				<tr>
					<td width="100%" colspan="3"><font color="red">The
					Travel Management Authorities of <s:property value="msg" /> is
					applicable to following branches.</font>&nbsp;&nbsp;<a href="#"
						title="Click here" onclick="return showBranch();">Branch List</a>
					<!--  <input type="button" class="token" value="Branch List" onclick="return showBranch();" >--></td>
				</tr>
			</s:if>


			


			<tr>
				<td colspan="3">
				<%
				try {
				%>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="formbg" class="sortable">

					<tr>

						<td class="formtext" width="100%">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td height="15" class="formhead" nowrap="nowrap" colspan="7"><strong
									class="forminnerhead">Travel Management Authorities
								List</strong></td>
								<td align="right" nowrap="nowrap" width="10%">
								<input type="button" class="delete" theme="simple"
									value="Delete " onclick=" return chkDeleteAuth();" /></td>
							</tr>




							<tr>
								<s:hidden name="myPage" id="myPage" />
								<td class="formth" align="center" width="5%"><strong><label
									class="set" id="sr.no1" name="sr.no"
									ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>
								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="branch.name2" name="branch.name"
									ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label></strong></td>

								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="mainSch.name2" name="mainSch.name"
									ondblclick="callShowDiv(this);"><%=label.get("mainSch.name")%></label></strong></td>

								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="sch.appr2" name="sch.appr"
									ondblclick="callShowDiv(this);"><%=label.get("sch.appr")%></label></strong></td>

								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="accnt.name2" name="accnt.name"
									ondblclick="callShowDiv(this);"><%=label.get("accnt.name")%></label></strong></td>

								<td class="formth" align="center" width="10%"><strong><label
									class="set" id="all.branch2" name="all.branch"
									ondblclick="callShowDiv(this);"><%=label.get("all.branch")%></label></strong></td>

								<td class="formth" align="center" width="10%"><strong><label
									class="set" id="travel.sts2" name="travel.sts"
									ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label></strong></td>
								<td  align="center" class="formth" width="10%"  class="set" nowrap="nowrap" >
								<input type="checkbox" id="checkAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />
						     </td>

							</tr>
							<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
							</s:if>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
									int cnt = pageNo * 20 - 20;
									int i = 0;
							%>




							<s:iterator value="authoritiesList">
								<s:hidden name="itAltSchdlrCode" />
								<s:hidden name="itAuthCode" />
								
								<!-- ADDED BY REEBA -->
								<s:hidden name="itAltAccntCode" />
								<s:hidden name="itEscalationEmployeeCode" />

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="itAuthCode"/>');">


									<td align="left" class="sortableTD" nowrap="nowrap"><%=++cnt%>
									<%
									++i;
									%>
									<s:hidden value="%{itAuthCode}" id='<%="itAuthCode" + i%>'></s:hidden>									
									</td>
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('itAuthCode' + '<%=i%>').value;
									</script>
									


									<td align="left" class="sortableTD"><s:hidden
										name="itBranchCode" /> <s:hidden name="itBranch" /><s:property
										value="itBranch" />&nbsp;</td>

									<td class="sortableTD"><s:property value="itSchdlr" /><s:hidden
										name="itSchdlr" /><s:hidden name="itSchdlrCode" />&nbsp;</td>

									<td class="sortableTD"><s:property value="itSchdlrAppr" /><s:hidden
										name="itSchdlrAppr" /><s:hidden name="itSchdlrApprCode" />&nbsp;</td>

									<td class="sortableTD"><s:property value="itAccnt" /><s:hidden
										name="itAccnt" /><s:hidden name="itAccntCode" />&nbsp;</td>

									<td class="sortableTD"><s:property value="itAllBranch" /><s:hidden
										name="itAllBranch" /><s:hidden name="itAllBranchCode" />&nbsp;</td>

									<td align="left" class="sortableTD"><s:property
										value="itStatus" /><s:hidden name="itStatus" />&nbsp;</td>
									<input type="hidden" name="hdeleteAuth" id="hdeleteAuth<%=i%>" />

									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChkAuth<%=i%>"
										name="confChkAuth"
										onclick="callForDeleteAuth('<s:property  value="itAuthCode"/>','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%
							d = i;
							%>



						</table>


						</td>
					</tr>
				</table>
				<%
					} catch (Exception e) {
					}
				%>
				</td>
			</tr>
			
			
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"><!-- <div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>--></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>



	</table>

</s:form>


<script>



//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="TrvlMangAuthorities_addNew.action";
	document.getElementById('paraFrm').submit();
}
//for multiple delete
function chkDeleteAuth()
		{
		 if(chk2()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="TrvlMangAuthorities_deleteAuth.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d %>';
	    document.getElementById('checkAll').checked = false;	    
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChkAuth'+a).checked=false;
	   document.getElementById('confChkAuth'+a).checked="";
	    document.getElementById('hdeleteAuth'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	function chk2(){
	 	var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkAuth'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDeleteAuth(id,i)
	   {
	 	  //alert(id);
	   var m=eval(id)-1;
	   //alert(document.getElementById('confChkAuth'+i).checked);
	   if(document.getElementById('confChkAuth'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteAuth'+i).value=eval(id);
	   }
	   else
	   document.getElementById('hdeleteAuth'+i).value="";	   
   		}
   		
   		
   		
   		
   	//end of delete
   	
   	//to edit by double clicking
   	
   function callForEdit(id){
	  	document.getElementById('paraFrm_mangAuthCode').value=id;	
	   	document.getElementById("paraFrm").action="TrvlMangAuthorities_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   	
   	
// For Save Button

function saveFun()
{
	var f9Fields= ["paraFrm_branch","paraFrm_mainSchdlr"];
	var fieldName = ["paraFrm_branch","paraFrm_mainSchdlr"];
	var lableName = ["branch.name","mainSch.name"];
	var type = ['select','select'];
	if(!validateBlank(fieldName, lableName, type))
    return s;
    if(!f9specialchars(f9Fields))
	return false;
	var desc =	document.getElementById('paraFrm_description').value;
	
	if(desc != "" && desc.length >2000){
		alert("Maximum length of "+document.getElementById('desc').innerHTML.toLowerCase()+" is 2000 characters.");
		return false;
    }    
    document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="TrvlMangAuthorities_save.action";
	document.getElementById('paraFrm').submit();
	
	return true;
}


	function reportFun()
	{		
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="TrvlMangAuthorities_report.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
	}

	function editFun()
	{
		document.getElementById('paraFrm').action="TrvlMangAuthorities_edit.action";
		document.getElementById('paraFrm').submit();
	}

	//For Delete Button
	function deleteFun()
		{
		if(document.getElementById('paraFrm_mainSchdlr').value==""){
				alert("Please select the record.");
				return false;			
		}
		var con=confirm('Do you really want to delete this record ? ')
		if(con){
				var del="TrvlMangAuthorities_delete.action";
			   document.getElementById('paraFrm').action=del;
			   document.getElementById('paraFrm').submit();
		} else{
	   	  return false;
		}
	}

	//For F9Window

	function searchFun()
	{
	callsF9(500,300,"TrvlMangAuthorities_f9Action.action");
	}

	//For Cancel Button
	function cancelFun(){
	//alert("cancelSec");
     	document.getElementById("paraFrm").action="TrvlMangAuthorities_cancelSec.action";
	    document.getElementById("paraFrm").submit();       
    }
    
    
    //for paging
    function callPage(id){
		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='TrvlMangAuthorities_callPageView.action';
		document.getElementById('paraFrm').submit();
		
	}	
	
	
   
	function on(){
		var val= document.getElementById('selectname').value;			
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="TrvlMangAuthorities_callPageView.action";
		document.getElementById('paraFrm').submit();
	}

	pgshow();

	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=document.getElementById('myPage').value;
  	}
  	
  	
  	function showBranch(){
  	
  	var wind = window.open('','win','width=600,height=300,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
	document.getElementById('paraFrm').target = "win";
	document.getElementById('paraFrm').action = "TrvlMangAuthorities_showBranch.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
  	}
  	
  	function newRowColor(cell) {   		
		   cell.className='Cell_bg_first';
	    }
	    
 	function oldRowColor(cell,val) {
	 cell.className='Cell_bg_second';
		}
		
		
		 function selectAllRecords(object) {
		
		try{
		if(object.checked) {
		
			for(var i = 1; i <= records.length; i++) {
			
				document.getElementById('confChkAuth' + i).checked = true;
				document.getElementById('hdeleteAuth' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChkAuth' + i).checked = false;
				document.getElementById('hdeleteAuth' + i).value = "";
			}
		}
		}catch(e)
		{
		//alert(e);
		}
	}	  
		

	

</script>



