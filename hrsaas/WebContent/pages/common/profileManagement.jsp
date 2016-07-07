<!--@ Author: Prajakta B-->
<!--@ Date: 26 March 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>
<s:form action="profileAction" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="profile"/>
	<s:hidden name="profileId" />
	<s:hidden name="listType" />
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="80%" class="txt"><strong class="text_head">Menu
					Profile</strong></td>
					<td width="5%" valign="top" class="txt">
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
				<td width="40%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<%
					int totalPage = 0;
					int pageNo = 0;
				%>
				<s:if test="profileListLength">
					<td id="ctrlShow" width="60%" align="right" class="" colspan="2"><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%><a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'profileAction_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'profileAction_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'profileAction_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'profileAction_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'profileAction_callPage.action');">
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
				<tr>
					<td colspan="3">
					<table width="100%" border="0" class="formbg" cellspacing="2"
						cellspacing="2">
						<tr>
							<td class="formtext">
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" width="15%"><label
										class="set" name="sr.no" id="sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td class="formth" align="center" width="70%"><label
										class="set" name="profileName" id="profileName"
										ondblclick="callShowDiv(this);"><%=label.get("profileName")%></label>
									<td class="formth" align="center" width="15%"><label
										class="set" name="actionLabel" id="actionLabel"
										ondblclick="callShowDiv(this);"><%=label.get("actionLabel")%></label>
									</td>

								</tr>
								<s:if test="profileListLength">
									<%
									int count = 0;
									%>
									<%!int d = 0;%>
									<%
										int i = 0;
										int cn = pageNo * 20 - 20;
									%>

									<s:iterator value="profileList">
									<s:hidden name="hiddenProfileId" />
									<s:hidden name="hiddenProfile"/>
										<tr <%if(count%2==0){
										%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											ondblclick="javascript:callForEdit('<s:property value="hiddenProfileId"/>','<s:property value="hiddenProfile"/>');">
											<s:hidden value="%{hiddenProfileId}" id='<%="hiddenProfileId" + i%>'></s:hidden>
											<script type="text/javascript">
											records[<%=i%>] = document.getElementById('hiddenProfileId' + '<%=i%>').value;
										</script>
											<td align="center" class="sortableTD"><%=++cn%> <% ++i; %>
											</td>
											<td class="sortableTD"><s:property value="hiddenProfile" /></td>
											<td class="sortableTD" align="center" title="Copy profile"><a href="#"
												onclick="callCopy('<s:property value="hiddenProfileId"/>','<s:property value="hiddenProfile" />')">
											<font color="blue">Copy Profile</font></a></td>
										</tr>
									</s:iterator>
									<%
									i++;
									count++;
									%>
								</s:if>
								<s:else>
									<tr>
										<td colspan="5" align="center"><font color="red">No
										data to display</font></td>
									</tr>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="100%" colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

							<td width="25%" align="right"><s:if test="profileListLength">
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
	document.getElementById('paraFrm').action = 'profileAction_addNew.action';
	document.getElementById('paraFrm').submit();
}

function searchFun() {
	if(navigator.appName == 'Netscape') {
		var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	} else {
		var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action ='profileAction_f9action.action';
	document.getElementById("paraFrm").submit();
}

function callForEdit(id,profile){
	callButton('NA', 'Y', 2);
  	document.getElementById('paraFrm_profileId').value=id;
  	document.getElementById('paraFrm_profile').value=profile;
   	document.getElementById("paraFrm").action="profileAction_updateProfile.action";
   	document.getElementById('paraFrm').target = "_self";
    document.getElementById("paraFrm").submit();
}

function callCopy(profileId,profile) {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm_profileId').value=profileId;
	document.getElementById('paraFrm_profile').value=profile;
	document.getElementById('paraFrm').action = 'profileAction_copyProfile.action';
	document.getElementById('paraFrm').submit();
}
</script>
