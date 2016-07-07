<%--Bhushan Dasare--%><%--Dec 22, 2008--%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp" %>

<script type="text/javascript">
	var btns = new Array();
	var mods = new Array();
</script>

<s:form action="NavigationPanelSettings" name="NavigationPanelSettings" method="post" id="paraFrm" theme="simple">
	<s:hidden name="setCode" />
	<s:hidden name="btnSelected" />
	<s:hidden name="modeSelected" />
	<s:hidden name="tempExist" />
	
	<table width="100%" class="formbg">
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td valign="bottom" class="txt">
							<table width="100%" class="formbg">
								<tr>
									<td><strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
									<td width="93%" class="txt"><strong class="text_head">Navigation Panel </strong></td>
									<td width="3%" valign="top" class="txt">
										<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td>
							<s:submit cssClass="token" action="NavigationPanelSettings_getButtons1" theme="simple" value="Show" onclick="return getButtons();" />
							<s:if test="svFlag">
								<s:submit cssClass="save" action="NavigationPanelSettings_save" theme="simple" value="Save" onclick="return getSelectedButtons();" />
							</s:if>
							<s:submit cssClass="reset" action="NavigationPanelSettings_reset" theme="simple" value="Reset" />
						</td>
						<td align="right"><font color="red">*</font>Indicates Required</td>
					</tr>
				</table>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="1"><label  class = "set" name="module" id="module" ondblclick="callShowDiv(this);"><%=label.get("module")%></label><font color="red">*</font>:</td>
						<td colspan="2">
							<s:hidden name="modCode" />
							<s:textfield name="modName" size="30" readonly="true" cssStyle="background-color: #F2F2F2;" />
							<s:if test="imgFlag">
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="callsF9(500,325,'NavigationPanelSettings_f9Modules.action');">
							</s:if>
						</td>
						<td colspan="3"><label class="set" name="formname" id="formname" ondblclick="callShowDiv(this);"><%=label.get("formname")%></label><font color="red">*</font>:</td>
						<td colspan="4">
							<s:hidden name="formCode" />
							<s:textfield name="formName" size="30" readonly="true" cssStyle="background-color: #F2F2F2;" />
							<s:if test="imgFlag">
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="return getForms();">
							</s:if>
						</td>
					</tr>
					<tr>
						<td colspan="1"><label class="set" name="template" id="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label><font color="red">*</font>:</td>
						<td colspan="2">
							<s:select name="tempCode" list="#{'0':'---------Select-----------','1':'General Template','2':'Application Template','3':'Approval Template','4':'Process Template'}" />
						</td>
					</tr>
				</table>
				<%! Object[][] btnList = null;%>
				<%
					try {
						int cnt=0;
						btnList = (Object[][])request.getAttribute("btnList");
						if(btnList != null && btnList.length > 0) {				
				%>
				<table width="100%" class="formbg">
					<tr>
						<td align="left"><b><label class="set" name="btntype" id="btntype" ondblclick="callShowDiv(this);"><%=label.get("btntype")%></label></b></td>
						<td align="center"><b><label class="set" name="modofbtn" id="modofbtn" ondblclick="callShowDiv(this);"><%=label.get("modofbtn")%></label></b></td>
						<td align="center"><b><label class="set" name="sequence" id="sequence" ondblclick="callShowDiv(this);"><%=label.get("sequence")%></label></b></td>
						<td align="center"><input type="checkbox" name="checkMe" id="checkMe" onclick="return selectAll();"/></td>
					</tr>
					<%for(int i = 0; i < btnList.length; i++) {%>
					<tr>
						<td width="15%"><%=btnList[i][2]%></td>
						<td width="15%" align="center"><%=btnList[i][4]%></td>
						<td align="center"><%=btnList[i][3]%></td>		
						<td align="center">
							<input type="hidden" name="btnCode" id='<%="btn"+cnt%>' value="<%=btnList[i][0]%>" />
							<input type="hidden" name="modeCode" id='<%="mode"+cnt%>' value="<%=btnList[i][4]%>" />
							<script type="text/javascript">
								btns[<%=i%>] = document.getElementById('btn'+<%=cnt%>).value;
								mods[<%=i%>] = document.getElementById('mode'+<%=cnt%>).value;
							</script>
						
							<%if(String.valueOf(btnList[i][1]).equals("Y")) {%>
								<input type="checkbox" name="chkBtn" id='<%="chk"+cnt%>' checked="checked" />
							<%} else {%>
								<input type="checkbox" name="chkBtn" id='<%="chk"+cnt%>' value="<%=cnt%>" onclick="checkRecord('<%=cnt%>');" />
							<%}%>					
						</td>
					</tr>
					<%cnt++;}%>
				</table>
				<%}} catch(Exception e) {
						e.printStackTrace();
				}%>
			</td>
		</tr>
	</table>
</s:form>

<script>
	var cntRem = 0;
	
	function getForms() {
		var modCode = document.getElementById('paraFrm_modCode').value;
		
		if(modCode == '') {
			alert('Please select Module.');
			return false;
		}
		
		callsF9(500,325,'NavigationPanelSettings_f9Forms.action');
		return true;
	}
	
	function getButtons() {
		var modCode = document.getElementById('paraFrm_modCode').value;
		var formCode = document.getElementById('paraFrm_formCode').value;
		//var tempCode = document.getElementById('paraFrm_tempCode').value;
		
		if(modCode == '') {
			alert("Please select "+document.getElementById('module').innerHTML.toLowerCase());
			return false;
		}
		
		if(formCode == '') {
			alert("Please select "+document.getElementById('formname').innerHTML.toLowerCase());
			return false;
		}
		
		/*if(tempCode == 0) {
			alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
			return false;
		}*/
		return true;
	}
	
	function getSelectedButtons() {
		var selBtns = '';	 
		var cnt = 0;
		
		for(var i = 0; i < btns.length; i++) {
			if(document.getElementById('chk' + i).checked) {
				cnt += 1;
				selBtns += mods[i] + ',';
				selBtns += btns[i] + ',';
				selBtns += '@';
			}
		}
		
		if(cnt < 1) {
			alert('Please select atleast one button type');
			return false;
		}
		
		document.getElementById('paraFrm_btnSelected').value = selBtns;	 
		return true;
	}
	
	function selectAll() {
		var check = document.getElementById('checkMe').checked;
		
		if(check == true) {
		 	for(var i = 0; i < btns.length; i++) {
				document.getElementById('chk' + i).checked = true;
		 	}
	 	} else {
		 	for(var i = 0; i < btns.length; i++) {
				 document.getElementById('chk' + i).checked = false;				
	 		}
	 	}	
	}	
	
	function checkRecord(id) {
		try
		{
			if(document.getElementById('chk' + id).checked) {
				cntRem = cntRem + 1;				
			} else {
				cntRem = cntRem - 1;
			}
			
			if(cntRem == btns.length) {
				document.getElementById("checkMe").checked = true;
			} else {
				document.getElementById("checkMe").checked = false;
			}
		} catch(e) {}
	}
</script>