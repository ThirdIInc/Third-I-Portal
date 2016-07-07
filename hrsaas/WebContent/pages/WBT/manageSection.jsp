<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
 
<%
 			int sectionListCount = request.getAttribute("sectionListLen") == null ? 0
 			: Integer.parseInt(request.getAttribute("sectionListLen")
 			.toString());
 %>

<s:form action="ManageSection" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" cellpadding="2" cellspacing="2" border="0" class="formbg" align="right">
	<tr>
			<td width="100%" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manage Section</strong></td>
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
			<table border="0" cellpadding="2" cellspacing="0" align="left">
				<tr>
					<td width="2%" align="left">
					<s:if test="sectionList == null">
					</s:if>
					<s:else>
					<input type="button" name="saveBtn" id="saveBtn"
						value="Save" onclick="javaScript:saveSectionFun();" />
					</s:else>
					</td>
					<td width="98%" align="left"><input type="button" name="=backBtn"
						value="Back" onclick="javaScript:backSectionFun();"/></td>
				</tr>
			</table>
		</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="10%"><label class="set" name="programe"
						id="programe" ondblclick="callShowDiv(this);"><%=label.get("programe")%></label></td>
						<td width="1%">:</td>
					<td width="50%"><s:property value="sectionProgrameName"/>
					<s:hidden name="sectionProgrameCode"/>
					<s:hidden name="sectionProgrameName"/>
					</td>
				</tr>
				<tr>
					<td width="10%"><label class="set" name="moduleNm"
						id="moduleNm" ondblclick="callShowDiv(this);"><%=label.get("moduleNm")%></label></td>
					<td width="1%">:</td>
					<td width="50%">
					<s:property value="sectionModuleName"/>
					<s:hidden name="sectionModuleCode"/>
					<s:hidden name="sectionModuleName"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortableTD">
						<tr>
							<td><b>Sections</b></td>
						</tr>
						<tr>
							<td class="formth" align="center" width="5%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							<td class="formth" align="center" width="25%"><label
								class="set" name="sectionNm" id="sectionNm"
								ondblclick="callShowDiv(this);"><%=label.get("sectionNm")%></label></td>							
							<td class="formth" align="center" width="5%"><label
								class="set" name="programorder" id="programorder"
								ondblclick="callShowDiv(this);"><%=label.get("programorder")%></label></td>							
							<td class="formth" align="center" width="10%"><label
								class="set" name="enableContentLbl" id="enableContentLbl"
								ondblclick="callShowDiv(this);"><%=label.get("enableContentLbl")%></label></td>
							<td class="formth" align="center" width="10%"><label
								class="set" name="enableQuestion" id="enableQuestion"
								ondblclick="callShowDiv(this);"><%=label.get("enableQuestion")%></label></td>						
							<td class="formth" align="center" width="10%"><label
								class="set" name="quesManage" id="quesManage"
								ondblclick="callShowDiv(this);"><%=label.get("quesManage")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="passMarks" id="passMarks"
								ondblclick="callShowDiv(this);"><%=label.get("passMarks")%></label></td>
						</tr>
						<%!int count = 0;%>
						<%
						int i = 1;
						%>
						<s:if test="sectionList == null">
							<tr><td align="center" colspan="7" >No data to display</td></tr>
						</s:if>
						<s:else>
						<s:iterator value="sectionList">
								<tr>
									<td class="sortableTD"><%=i%></td>
									<td class="sortableTD" align="left" id="secCodeTD<%=i%>"
										title="<s:property value="sectionAbbrItt"/>"><s:property
										value="sectionItt" /> <s:hidden name="sectionCodeItt" /></td>
									<td align="center"><input type="hidden"
										name="sectionOrder" id="sectionOrder<%=i%>"
										value="<s:property value="sectionOrder"/>" />
									<table width="100%">
										<tr width="100%">
											<td align="right" width="50%">
											<%
											if (sectionListCount == 1) {
											%>- 
											<% } %>
											<%
											if (i != 1) {
											%> <a href="#"
												onclick="orderUp('<s:property value="sectionOrder"/>','<%=i%>');">
											<img border="0" src="../pages/common/css/default/images/up.GIF"
												width="10" height="10"> </a> <%
 }
 %>
											</td>
											<td align="left" width="50%">
											<%
											if (i != sectionListCount) {
											%> <a href="#"
												onclick="orderDown('<s:property value="sectionOrder"/>',<%=i %>);">
											<img border="0" src="../pages/common/css/default/images/down.GIF"
												width="10" height="10"> </a> <%
																				 }
 																			 %></td>
										</tr>
									</table>
									</td>
									<td class="sortableTD" align="center" id="secContentTD<%=i%>"><input
										type="hidden" value="<s:property value="hiddenSecContChk"/>"
										name="hiddenSecContChk" id="hiddenSecContChk<%=i%>" /> <input
										type="checkbox" class="checkbox" id="content<%=i%>"
										name="content" onclick="setChkBoxSecCont('<%=i%>')" /> <script>
									if(document.getElementById('hiddenSecContChk<%=i%>').value=='Y'){
									document.getElementById('content<%=i%>').checked =true;
									}</script></td>

									<td class="sortableTD" align="center" id="secQuesTD<%=i%>"><input
										type="hidden" value="<s:property value="hiddenSecQuesChk"/>"
										name="hiddenSecQuesChk" id="hiddenSecQuesChk<%=i%>" /> <input
										type="checkbox" class="checkbox" id="ques<%=i%>" name="ques"
										onclick="setChkBoxSecQues('<%=i%>')" /> <script>
									if(document.getElementById('hiddenSecQuesChk<%=i%>').value=='Y'){
									document.getElementById('ques<%=i%>').checked =true;
									}									
									</script></td>
									<td class="sortableTD" align="center" id="secManageQueTD<%=i%>">
									<a id="secManageQueHyper<%=i %>"
										href="#"
										onclick="return manageQuestion('<s:property value="sectionCodeItt" />','<s:property
																		value="sectionItt" />');">
									<u><font color="blue">Manage</font></u></a></td>
									<td class="formth" align="center" id="secMarkTD<%=i%>"><s:property
										value="passMarkSecItt" /> <s:hidden name="passMarkSecItt" /></td>
								</tr>
								<%
								i++;
								%>
						</s:iterator>
						</s:else>
						<%
						count = i;
						%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		<tr>
			<td>
			<table border="0" cellpadding="2" cellspacing="0" align="left">
				<tr>
				<td width="2%" align="left">
					<s:if test="sectionList == null">
					</s:if>
					<s:else>
					<input type="button" name="saveBtn" id="saveBtn"
						value="Save" onclick="javaScript:saveSectionFun();" />
					</s:else>
					</td>
					<td width="98%" align="left"><input type="button" name="=backBtn"
						value="Back" onclick="javaScript:backSectionFun();"/></td>
				</tr>
			</table>
		</td>
		</tr>
	</table>
	</s:form>
	
<script>

function setChkBoxSecCont(i){
	 if(document.getElementById('content'+i).checked){	  
	    document.getElementById('hiddenSecContChk'+i).value='Y';
	  }
	  else{
	   document.getElementById('hiddenSecContChk'+i).value='N';
	  }
}

function setChkBoxSecQues(i){
	 if(document.getElementById('ques'+i).checked == true){	  
	    document.getElementById('hiddenSecQuesChk'+i).value='Y';
	     document.getElementById('secManageQueHyper'+i).style.visibility = "visible";
	  }
	  else{
	   document.getElementById('hiddenSecQuesChk'+i).value='N';
	   document.getElementById('secManageQueHyper'+i).style.visibility = "hidden";
	  }
}

function setMngQuesTd(){
	var ele = document.getElementsByName("hiddenSecQuesChk");
	for(i=1;i<=ele.length;i++){
		if(document.getElementById('ques'+i).checked == true){  
			document.getElementById('hiddenSecQuesChk'+i).value='Y';
	     	document.getElementById('secManageQueHyper'+i).style.visibility = "visible";
		} else{
		   document.getElementById('hiddenSecQuesChk'+i).value='N';
		   document.getElementById('secManageQueHyper'+i).style.visibility = "hidden";
		  }
	}
}
setMngQuesTd();

function saveSectionFun(){
	var ele = document.getElementsByName("saveBtn");
	for(i=0; i< ele.length; i++){
		ele[i].disabled="true";
	}
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'ProgrammeMaster_saveSection.action';
	document.getElementById('paraFrm').submit();
	
}

function backSectionFun(){
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ProgrammeMaster_backSection.action';
    document.getElementById('paraFrm').submit();
}


function manageQuestion(secCode,secName){
	var programCode =document.getElementById('paraFrm_sectionProgrameCode').value;  
	var proNm = document.getElementById('paraFrm_sectionProgrameName').value;
	var moduleName = document.getElementById('paraFrm_sectionModuleName').value
	var moduleId = document.getElementById('paraFrm_sectionModuleCode').value
	 try{
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ProgrammeMaster_callQuesManage.action?programID='+programCode+ '&moduleID='+ moduleId +'&modName='+ moduleName +'&sectionCode='+secCode+'&progName='+proNm+'&sectionName='+secName+'&questionFlag=section';
    //?programID='+programCode+'&moduleID='+moduleCode+'&progName='+proNm+'&modName='+modulName+'&qFlag='+true; 
    document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		alert(""+e);
	}
}

function orderUp(order, i){
		var temporary = ""; 
		if(document.getElementById('secCodeTD' +(i-1)) == null ){
			alert("This is First section you cannot go up");
		} else {
		temporary = document.getElementById('secMarkTD'+i).innerHTML;
		document.getElementById('secMarkTD'+i).innerHTML = document.getElementById('secMarkTD' +(i-1)).innerHTML;
		document.getElementById('secMarkTD' +(i-1)).innerHTML = temporary;
		
		temporary = document.getElementById('secContentTD'+i).innerHTML;
		document.getElementById('secContentTD'+i).innerHTML = document.getElementById('secContentTD' +(i-1)).innerHTML;
		document.getElementById('secContentTD' +(i-1)).innerHTML = temporary;
		
		temporary = document.getElementById('secQuesTD'+i).innerHTML;
		document.getElementById('secQuesTD'+i).innerHTML = document.getElementById('secQuesTD' +(i-1)).innerHTML;
		document.getElementById('secQuesTD' +(i-1)).innerHTML = temporary;
		
		temporary = document.getElementById('secManageQueTD'+i).innerHTML;
		document.getElementById('secManageQueTD'+i).innerHTML = document.getElementById('secManageQueTD' +(i-1)).innerHTML;
		document.getElementById('secManageQueTD' +(i-1)).innerHTML = temporary;
		
		temporary = document.getElementById('secCodeTD'+i).innerHTML;
		document.getElementById('secCodeTD'+i).innerHTML = document.getElementById('secCodeTD' +(i-1)).innerHTML;
		document.getElementById('secCodeTD' +(i-1)).innerHTML = temporary;
		}
		 setSectionChk ();	
}
	
	
function orderDown(order, i){
		var temporary = ""; 
		if(document.getElementById('secCodeTD' +(i+1)) == null ){
			alert("This is Last Record you cannot go Down");
		} else {
		temporary = document.getElementById('secMarkTD'+i).innerHTML;
		document.getElementById('secMarkTD'+i).innerHTML = document.getElementById('secMarkTD' +(i+1)).innerHTML;
		document.getElementById('secMarkTD' +(i+1)).innerHTML = temporary;
		
		temporary = document.getElementById('secContentTD'+i).innerHTML;
		document.getElementById('secContentTD'+i).innerHTML = document.getElementById('secContentTD' +(i+1)).innerHTML;
		document.getElementById('secContentTD' +(i+1)).innerHTML = temporary;
		
		temporary = document.getElementById('secQuesTD'+i).innerHTML;
		document.getElementById('secQuesTD'+i).innerHTML = document.getElementById('secQuesTD' +(i+1)).innerHTML;
		document.getElementById('secQuesTD' +(i+1)).innerHTML = temporary;
		
		temporary = document.getElementById('secManageQueTD'+i).innerHTML;
		document.getElementById('secManageQueTD'+i).innerHTML = document.getElementById('secManageQueTD' +(i+1)).innerHTML;
		document.getElementById('secManageQueTD' +(i+1)).innerHTML = temporary;
		
		temporary = document.getElementById('secCodeTD'+i).innerHTML;
		document.getElementById('secCodeTD'+i).innerHTML = document.getElementById('secCodeTD' +(i+1)).innerHTML;
		document.getElementById('secCodeTD' +(i+1)).innerHTML = temporary;
		}
		setSectionChk();
}
	
function setSectionChk (){
 	var content = document.getElementsByName('hiddenSecContChk');
 	var contentChk = document.getElementsByName('content');
 	for(i=0 ; i < content.length; i++){
 	 if(content[i].value == "Y"){
 	   contentChk[i].checked= true;
 	 }
 	 else
 	   contentChk[i].checked = false;
 	}
 	
 	var ques = document.getElementsByName('hiddenSecQuesChk');
 	var quesChk = document.getElementsByName('ques');
 	for(i=0 ; i< ques.length; i++){
 		if(ques[i].value== "Y"){
 			quesChk[i].checked = true;
 		}
 		else
 		 quesChk[i].checked = false;
 	}
}
</script>