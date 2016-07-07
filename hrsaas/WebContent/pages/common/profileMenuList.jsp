<!--@ Author: Prajakta B-->
<!--@ Date: 11 june 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="profileAction" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="hiddenProfile" />
	<s:hidden name="hiddenProfileId" />
	<s:hidden name="profileId" />
	<s:hidden name="paraId"/>
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
			<td width="40%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td class="formtext" width="15%"><label name="profileName"
						id="profileName" ondblclick="callShowDiv(this);"><%=label.get("profileName")%></label>
					:</td>
					<td colspan="2"><s:textfield label="Menu Profile" size="20" name="profile"
						readonly="true" /></td>
				</tr>
				<tr>
				<td width="100%" colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td class="formtext" >
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<td class="formth" align="center" width="10%"><label
										class="set" name="sr.no" id="sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td class="formth" align="center" ><label
										class="set" name="moduleName" id="moduleName"
										ondblclick="callShowDiv(this);"><%=label.get("moduleName")%></label>
								</tr>
								<%
					int cn = 0;
					try {
						
						String[][] menuName1 = (String[][]) request
						.getAttribute("menuName1");//Parent menus
						for (int i = 0; i < menuName1.length; i++) {
				%>
				
				<!--Display all Modules  -->
				<tr>
					<td align="center" class="sortableTD" width="10%"><%=++cn%></td>
					<td class="sortableTD" ><a href="#" onclick="callMenu('<%=menuName1[i][0]%>')"><%=menuName1[i][1]%></a></td>
				</tr>
				<%
						}
						cn++;
					%>
				<tr>
				<td align="center" class="sortableTD" width="10%"><%=cn++%></td>
					<td class="sortableTD" ><a href="#" onclick="callMenu('My Services')">My Services</a></td>
				</tr>
				<tr>
				<td align="center" class="sortableTD" width="10%"><%=cn++%></td>
					<td class="sortableTD" ><a href="#" onclick="callMenu('My Analytics')">My Analytics</a></td>
				</tr>
				<!--Ends display all Modules  -->
					 <%} catch(Exception e) {
						e.printStackTrace();
					}
				%>
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
			<td width="40%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script>
//Function to go to main page on click of Back button
function backFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'profileAction_reset.action';
	document.getElementById('paraFrm').submit();
	}	
//Function to call child forms of selected module name
function callMenu(id){
document.getElementById('paraFrm_paraId').value=id;
var listType='Config';
document.getElementById('paraFrm').target = "_self";
document.getElementById('paraFrm').action = 'profileAction_callConfigMenu.action?lstType='+listType;
document.getElementById('paraFrm').submit();
}

</script>