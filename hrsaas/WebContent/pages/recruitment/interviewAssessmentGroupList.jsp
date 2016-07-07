<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>
<s:form action="InterviewAssessMasterAction" validate="true" id="paraFrm"  theme="simple">
<s:hidden name="groupCode" />
<s:hidden name="groupName" />
<s:hidden name="groupDescription" />
<s:hidden name="isGroupActive" />


	
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td  width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td  width="80%" class="txt"><strong
						class="text_head">Interview Assessment Group </strong></td>
					<td  width="5%" valign="top" class="txt">
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
				<td width="40%" ><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				<%
					int totalPage = 0;
					int pageNo = 0;
				%>

				<s:if test="listLength">
					<td id="ctrlShow" width="60%" align="right" class="" colspan="2"><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'InterviewAssessMasterAction_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'InterviewAssessMasterAction_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'InterviewAssessMasterAction_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'InterviewAssessMasterAction_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'InterviewAssessMasterAction_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="2" cellspacing="2"
				border="0">
<!--				<tr>-->
<!--					<td  class="txt" width="60%">&nbsp;</td>-->
<!--					<td colspan="2" align="right" width="40%">-->
<!--						<input type="button" id="ctrlShow" class="delete" theme="simple" value=" Delete"-->
<!--						onclick="return deleteCheckedGroupData();" />-->
<!--					</td>-->
<!--				</tr>-->

				 
				<tr>
					<td colspan="3">
					<table width="100%" border="0" class="formbg" cellspacing="2"
						cellspacing="2">
						<tr>
							<td class="formtext">
							<table width="100%" border="0" cellpadding="2" cellspacing="2" id="groupTableID">
									<tr>
										<s:hidden name="myPage" id="myPage" />
										<td class="formth" align="center" width="5%" ><label
											class="set" name="sr.no" id="sr.no"
											ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
										</td>
										<td class="formth" align="center" width="15%" ><label
											class="set" name="groupNameLabel" id="groupNameLabel"
											ondblclick="callShowDiv(this);"><%=label.get("groupNameLabel")%></label>
										</td>
										<td class="formth" align="center" width="60%" ><label
											class="set" name="descriptionLabel" id="descriptionLabel"
											ondblclick="callShowDiv(this);"><%=label.get("descriptionLabel")%></label>
										</td>
										<td class="formth" align="center" width="60%" ><label
											class="set" name="is.active" id="is.active"
											ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
										</td>
										<td class="formth" align="center" width="10%" ><label
											class="set" name="actionLabel" id="actionLabel"
											ondblclick="callShowDiv(this);"><%=label.get("actionLabel")%></label>
										</td>
	
<!--										<s:if test="listLength">-->
<!--											<td align="right" class="formth" id="ctrlShow"-->
<!--												nowrap="nowrap" width="10%" ><input-->
<!--												type="checkbox" id="selAll" style="cursor: hand;"-->
<!--												title="Select all records to remove"-->
<!--												onclick="selectAllRecords('groupTableID');" />-->
<!--											</td>-->
<!--										</s:if>-->
<!--										<s:else>-->
<!--											<td width="10%" class="formth">&nbsp;</td>-->
<!--										</s:else>-->
									</tr>
								<s:if test="listLength">
									<% int count = 0; %>
									<%!int d = 0;%>
									<%
											int i = 0;
											int cn = pageNo * 20 - 20;
									%>

								<s:iterator value="groupList">
									<tr <%if(count%2==0){
										%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="groupCode"/>');">

										<td align="center"  class="sortableTD"><%=++cn%>
											<% ++i; %>
											<s:hidden name="srNo" />
										</td>
										
										<s:hidden value="%{groupCode}" id='<%="groupCode" + i%>'></s:hidden>
										<script type="text/javascript">
											records[<%=i%>] = document.getElementById('groupCode' + '<%=i%>').value;
										</script>
										
										<td class="sortableTD" >
											<s:property value="groupNameItr" /> 
											<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
										</td>
										
										<td class="sortableTD" >
											<s:property value="groupDescriptionItr" /> 
										</td>
										
										<td class="sortableTD" align="center">
											<s:property value="groupIsActiveItr" /> 
										</td>
										
										<td class="sortableTD" >
											<a href="#" onclick="callManageParameters('<s:property value="groupCode"/>')"> <font color="blue">Manage</font></a> 
										</td>

<!--										<td id="ctrlShow" align="center" nowrap="nowrap"-->
<!--											class="sortableTD"><input type="checkbox"-->
<!--											class="checkbox" id="confChk<%=i%>" name="confChk"-->
<!--											onclick="callForDelete1('<s:property value="groupCode"/>','<%=i%>')" />-->
<!--										</td>-->
									</tr>
								</s:iterator>
									<% d = i; %>
								</s:if>
								<s:else>
									<tr>
										<td colspan="5" align="center">
											<font color="red">No data to display</font>
										</td>
									</tr>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr >
					<td width="100%" colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td width="75%" ><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

							<td width="25%" align="right"><s:if test="listLength">
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
function addnewFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_addNewGroup.action';
	document.getElementById('paraFrm').submit();
}

function searchFun() {
	if(navigator.appName == 'Netscape') {
		var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	} else {
		var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action ='InterviewAssessMasterAction_f9Groupaction.action';
	document.getElementById("paraFrm").submit();
}


function callForDelete1(id,i) {
	   if(document.getElementById('confChk'+i).checked == true) {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   } else {
	   document.getElementById('hdeleteCode'+i).value="";
   }
}

function callForEdit(id){
	callButton('NA', 'Y', 2);
  	document.getElementById('paraFrm_groupCode').value=id;
   	document.getElementById("paraFrm").action="InterviewAssessMasterAction_setGroupRecord.action";
   	document.getElementById('paraFrm').target = "_self";
    document.getElementById("paraFrm").submit();
}

function selectAllRecords(typeTableId) {
	var selectAllCheckBox = document.getElementById('selAll').checked;
	var table = document.getElementById(typeTableId);
	var rowcount = table.rows.length;
	var iteration = rowcount - 1;
	if (selectAllCheckBox) {
		for(var i =1; i<=iteration; i++) {
			document.getElementById('confChk' + i).checked = true; 
			document.getElementById('hdeleteCode' + i).value = document.getElementById('groupCode' + i).value;
		} 
	} else {
		for(var i =1; i<=iteration; i++) {
			document.getElementById('confChk' + i).checked = false; 
			document.getElementById('hdeleteCode' + i).value = "";
		} 	
	}
}


function chk(){
var flag='<%=d %>';
  for(var a=1;a<=flag;a++){	
   	if(document.getElementById('confChk'+a).checked == true) {	
	   	return true;
	}	   
  }
  return false;
}

function deleteCheckedGroupData() {
	 if(chk()){
		 var con=confirm('Do you want to delete the record(s) ?');
		 if(con){
		   	document.getElementById('paraFrm').action="InterviewAssessMasterAction_deleteChkGroupRecords.action";
		   	document.getElementById('paraFrm').target = "_self";
		   	document.getElementById('paraFrm').submit();
		 } else { 
			document.getElementById('selAll').checked = false;
		    var flag='<%=d %>';
		  	for(var a=1;a<=flag;a++){	
				document.getElementById('confChk'+a).checked = false;
				document.getElementById('hdeleteCode'+a).value="";
			}
		  return false;
		}
	} else {
		 alert('please select atleast one record');
		 return true ;
	}
}

function callManageParameters(groupCode) {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_manageParameters.action?groupCode='+groupCode;
	document.getElementById('paraFrm').submit();
}
</script>
