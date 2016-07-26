<!--@ Author: Prajakta B-->
<!--@ Date: 26 March 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<script type="text/javascript" src="../pages/common/include/javascript/menuManageAjax.js"></script>
<script type="text/javascript">
var records = new Array();
</script>

<s:form action="profileAction" validate="true" id="paraFrm"
	 theme="simple">
	<input type="hidden" id="changeNameId" name="changeNameId" />
	<input type="hidden" id="changeMenuId" name="changeMenuId" />
	<s:hidden name="noConf" />
	<s:hidden name="listType"/>
	<s:hidden name="hiddenProfile" />
	<s:hidden name="hiddenProfileId" />
	<s:hidden name="profileId" />
	<s:hidden name="paraId"/>
	<s:hidden name="check"/>
	<table width="100%" border="0" class="formbg">
	<tr>
			<td width="100%">
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
			<%!int d=0; %>
			<%
			int c=0;
			%>
		</tr>
		<tr>
			<td width="40%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" class="formbg">
				<tr>
					<td class="formtext" colspan="1"><label name="profileName"
						id="profileName" ondblclick="callShowDiv(this);"><%=label.get("profileName")%></label>
					:</td>
					<td colspan="5"class="formtext"><s:textfield label="Menu Profile" size="20"
						name="profile" readonly="true" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
				<td>
				<table width="100%">
				<s:if test="noConf"></s:if><s:else><tr>
				<td>
				<%
				String[]type=(String[])request.getAttribute("type");
				System.out.println(""+type.length);
				if(type!=null && type.length > 0){
					if(type.length >= 1){
					if( (type[0]!=null || !type[0].equals("null") || !type[0].equals(""))){%>
					<a href="#" onclick="setAction('Config')">Configurations</a>
					<% }}
					if(type.length >= 2){
					if((type[1]!=null || !type[1].equals("null")|| !type[1].equals(""))){
					%>
					| <a href="#" onclick="setAction('Process')">Processes</a> 
					<%} }
					if(type.length >= 3){
					if((type[2]!=null || !type[2].equals("null") || !type[2].equals(""))){
						%>
						| <a href="#"
							onclick="setAction('Report')">Reports</a>
						<%} }
				}
				%>
					</td>
					</tr></s:else>
					
				</table>
				</td>
			</tr>
			<!-- Displaying  forms -->
			
			<s:if test="listType=='Config'">
			<tr>
					<td width="100%">
					<table width="100%" class="formbg" border="0">
						<tr>
							<td><b>Configuration Forms</b></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:if>
			<s:if test="listType=='Process'" >
			<tr>
					<td width="100%">
					<table width="100%" class="formbg" border="0">
						<tr>
							<td><b>Process Forms</b></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:if>
			<s:if test="listType=='Report'">
			<tr>
					<td width="100%">
					<table width="100%" class="formbg" border="0">
						<tr>
							<td><b>Report Forms</b></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:if>
			<s:if test="check=='true' || noConf=='true'">
							<tr>
					<td width="100%">
					<table width="100%" class="formbg" border="0" align="center">
					<tr>
					<td width="30%" class="formth"><b>Menu List</b></td>
							<td class="formth" width="15%" align="center"><b>Insert</b><br><input type="checkbox"  id="allInsertCheck" onclick="checkAll('allInsertCheck')" /></td>
							<td class="formth" width="15%" align="center"><b>Update</b><br><input type="checkbox"  id="allUpdateCheck" onclick="checkAll('allUpdateCheck')" /></td>
							<td class="formth" width="15%" align="center"><b>Delete</b><br><input type="checkbox"  id="allDeleteCheck" onclick="checkAll('allDeleteCheck')" /></td>
							<td class="formth" width="15%" align="center"><b>View</b><br><input type="checkbox"  id="allViewCheck" onclick="checkAll('allViewCheck')" /></td>
							<td class="formth" width="15%" align="center"><b>General</b><br><input type="checkbox"  id="allGeneralCheck" onclick="checkAll('allGeneralCheck')" /></td>
					</tr>
					<%
					
					try{
						Object[][] image=(Object[][])request.getAttribute("image");
						String[][] menuGroup = (String[][]) request.getAttribute("menuGroup");//Menu Groups
						HashMap menuForm = (HashMap) request.getAttribute("menuForm");//Menu Groups
						Set set = menuForm.entrySet();
						Iterator it = (Iterator) set.iterator();
						int k=0;
						while (it.hasNext()) {
							Map.Entry me = (Map.Entry) it.next();
							System.out.println("key " + me.getKey());
							
				%>
				<s:if test="noConf"></s:if><s:else>
				<tr>
				<td align="left" colspan="6" class="formbg"><b>
				<%if(image!=null && image.length > 0){
				for(k=0; k < image.length; k++){
				if(String.valueOf(image[k][0]).equals(String.valueOf(me.getKey()))){
				%>	
				<img align="absmiddle" height="24" src="../pages/mypage/images/icons/<%=image[k][2]%>">
				&nbsp;
				<%}
				}
				}%>
				<%=me.getKey()%></b></td></tr>
				
				</s:else>
					<%k++;
								Object[][] menuObject = (Object[][]) me.getValue();
					if(menuObject!=null && menuObject.length > 0){
								for (int i = 0; i < menuObject.length; i++) {
					%>
					<tr>
					
					<td class="sortableTD" width="30%"><label id="newMenuName<%=menuObject[i][0]%>" name="newMenuName" ondblclick="callNewName(this,'<%=menuObject[i][0]%>');" title="Double click to edit"><%=menuObject[i][1]%></label></td>
					<input type="hidden" name="menuCodeId"  value="<%=menuObject[i][0]%>"/>
					<td width="15%" align="center"><input type="checkbox" name= "insertCheck"  id="insertCheck<%=c%>" onclick="isChecked(this,'hidInsertCheck<%=c%>');" />
					<input type="hidden" name="hidInsertCheck" id="hidInsertCheck<%=c%>" value="<%=menuObject[i][3]%>"/>
					</td>
					<td width="15" align="center"><input type="checkbox" name= "updateCheck" id="updateCheck<%=c%>" onclick="isChecked(this,'hidUpdateCheck<%=c%>')" ></td>
					<input type="hidden" name="hidUpdateCheck" id="hidUpdateCheck<%=c%>" value="<%=menuObject[i][4]%>"/>
					<td width="15" align="center"> <input type="checkbox" name= "deleteCheck"  id="deleteCheck<%=c%>" onclick="isChecked(this,'hidDeleteCheck<%=c%>')" /></td>
					<input type="hidden" name="hidDeleteCheck" id="hidDeleteCheck<%=c%>" value="<%=menuObject[i][5]%>"/>
					<td width="15" align="center"><input type="checkbox" name= "viewCheck" id="viewCheck<%=c%>" onclick="isChecked(this,'hidViewCheck<%=c%>')" /></td>
					<input type="hidden" name="hidViewCheck" id="hidViewCheck<%=c%>" value="<%=menuObject[i][6]%>"/>
					<td width="15" align="center"><input type="checkbox" name= "generalCheck"  id="generalCheck<%=c%>" onclick="isChecked(this,'hidGeneralCheck<%=c%>')" /></td>
					<input type="hidden" name="hidGeneralCheck" id="hidGeneralCheck<%=c%>" value="<%=menuObject[i][7]%>"/>
					<%if(String.valueOf(menuObject[i][3]).equals("Y"))
					{%>
						<script>
						document.getElementById('insertCheck<%=c%>').checked =true;
						</script>
					<% }
					%>
					<%if(String.valueOf(menuObject[i][4]).equals("Y"))
					{%>
						<script>
						document.getElementById('updateCheck<%=c%>').checked =true;
						</script>
					<% }
					%>
					<%if(String.valueOf(menuObject[i][5]).equals("Y"))
					{%>
						<script>
						document.getElementById('deleteCheck<%=c%>').checked =true;
						</script>
					<% }
					%>
					<%if(String.valueOf(menuObject[i][6]).equals("Y"))
					{%>
						<script>
						document.getElementById('viewCheck<%=c%>').checked =true;
						</script>
					<% }
					%>
					<%if(String.valueOf(menuObject[i][7]).equals("Y"))
					{%>
						<script>
						document.getElementById('generalCheck<%=c%>').checked =true;
						</script>
					<% }
					%>
					</tr>
					<%
						d=c++;
						
					%>
					
					<%}//end of for
						}//end of if
						else{
							
						}
					
					%>
					<input type="hidden" id="count" name="count" value="<%=c%>"/>
						<%}//end of while
						}catch(Exception e){
						e.printStackTrace();
					}
					%>
					
					</table>
					</td>
				</tr>
		
		</s:if>
		<s:else>
			<tr>
				<td colspan="6" align="center"><font color="red" >No data
				to display</font></td>
			</tr>
		</s:else>
		<tr>
			<td width="40%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<div id="nameDiv" style='position:absolute; z-index:3; width:470px; height:120px; display:none;border:2px solid; top: 70px; 
left: 200px;padding: 10px' class="formbg" >
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
		<tr>
			<td width="35%" colspan="3" align="center" class="formth" style="cursor:move" onmouseout="Drag.end();" 
			onmouseover="Drag.init(document.getElementById('nameDiv'), null, 0, 350, 0, 700);" >
				<b>Change Name</b>
			</td>
			<td width="3%" align="right" border="1" class="formth" style="font-family:Arial;cursor: pointer" onclick="hideDiv();">
				<b>X</b>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
		<tr>
			<td width="30%" >Current Name :</td>
			<td><input type="text" name="label" id="myName" readonly="readonly" size="50"/></td>
		</tr>		
		<tr>
			<td width="30%">New Name :</td>
			<td><input type="text" name="newLabel" id="myNewName" maxlength="50" size="50"/></td>
		</tr>
		<tr>
			<td></td>
			<td width="100%">
				<input type="button" name="changeLabelButtons" value=" Apply" class="add" style="cursor: pointer;" 
				onclick="callNameChange();" />&nbsp;
				<input type="button" name="changeLabelButtons" value=" Restore Default" class="reset" style="cursor: pointer;" 
				onclick="restoreMenuName()" />&nbsp;
				<input type="button" name="changeLabelButtons" value=" Cancel" class="cancel" style="cursor: pointer;" 
				onclick="hideDiv();" />
			</td>
		</tr>
	</table>
</div>
</s:form>
<script>
function isChecked(obj,id){
	  if(obj.checked){
	  document.getElementById(id).value='Y';
	  }else{
	  document.getElementById(id).value='N';
	  }
	  
	  if(obj.name=='insertCheck'){
	  document.getElementById('allInsertCheck').checked=false;
	  }
	  if(obj.name=='updateCheck'){
	  document.getElementById('allUpdateCheck').checked=false;
	  }
	   if(obj.name=='deleteCheck'){
	  document.getElementById('allDeleteCheck').checked=false;
	  }
	  if(obj.name=='viewCheck'){
	  document.getElementById('allViewCheck').checked=false;
	  }
	  if(obj.name=='generalCheck'){
	  document.getElementById('allGeneralCheck').checked=false;
	  }
 }
	  
function checkAll(id){
try{
	var count ='<%=d+1%>';
	if(id=='allInsertCheck'){
	if(document.getElementById("allInsertCheck").checked){
		for(var check=0;check <count; check++){
			document.getElementById('insertCheck'+check).checked=true;
			document.getElementById('hidInsertCheck'+check).value='Y';
			}
		}else{
		   for(var check=0;check <count; check++){
			document.getElementById('insertCheck'+check).checked=false;
			document.getElementById('hidInsertCheck'+check).value='N';
		}
		}
	}
	if(id=='allUpdateCheck'){
	if(document.getElementById("allUpdateCheck").checked){
		for(var check=0;check <count; check++){
			document.getElementById('updateCheck'+check).checked=true;
			document.getElementById('hidUpdateCheck'+check).value='Y';
			}
		}else{
		   for(var check=0;check <count; check++){
			document.getElementById('updateCheck'+check).checked=false;
			document.getElementById('hidUpdateCheck'+check).value='N';
		}
		}
	}
	if(id=='allDeleteCheck'){
	if(document.getElementById("allDeleteCheck").checked){
		for(var check=0;check <count; check++){
			document.getElementById('deleteCheck'+check).checked=true;
			document.getElementById('hidDeleteCheck'+check).value='Y';
			}
		}else{
		   for(var check=0;check <count; check++){
			document.getElementById('deleteCheck'+check).checked=false;
			document.getElementById('hidDeleteCheck'+check).value='N';
		}
		}
		
	}
	if(id=='allViewCheck'){
	if(document.getElementById("allViewCheck").checked){
		for(var check=0;check <count; check++){
			document.getElementById('viewCheck'+check).checked=true;
			document.getElementById('hidViewCheck'+check).value='Y';
			}
		}else{
		   for(var check=0;check <count; check++){
			document.getElementById('viewCheck'+check).checked=false;
			document.getElementById('hidViewCheck'+check).value='N';
		}
		}
	
	}
	if(id=='allGeneralCheck'){
	if(document.getElementById("allGeneralCheck").checked){
		for(var check=0;check <count; check++){
			document.getElementById('generalCheck'+check).checked=true;
			document.getElementById('hidGeneralCheck'+check).value='Y';
			}
		}else{
		   for(var check=0;check <count; check++){
			document.getElementById('generalCheck'+check).checked=false;
			document.getElementById('hidGeneralCheck'+check).value='N';
		}
		}
	}
	}catch(e){
	alert(e);
	  }
 
}
//Function to go back to main page
function backFun(){
try{
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'profileAction_backProfileList.action';
	document.getElementById('paraFrm').submit();
	}catch(e){
	alert(e);
  }
}
//Function to save profile flags
function saveFun(){
try{

	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'profileAction_saveProfile.action';
	document.getElementById('paraFrm').submit();
	}catch(e){
	alert(e);
  }
}
//Function to call diferent lists(Configurations/Processes/Reports)
function setAction(listType){
 try{
	if(listType=="Config"){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'profileAction_callConfigMenu.action?lstType='+listType;
		document.getElementById('paraFrm').submit();

 	}
 	if(listType=="Process"){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'profileAction_callConfigMenu.action?lstType='+listType;
		document.getElementById('paraFrm').submit();
		      
	}
	if(listType=="Report"){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'profileAction_callConfigMenu.action?lstType='+listType;
		document.getElementById('paraFrm').submit();
	 }
	}catch(e){
	alert(e);
  }
}
//Function to reset all checkboxes		    
function resetFun(){
 var count ='<%=d+1%>';
 document.getElementById("allInsertCheck").checked=false;
 document.getElementById("allDeleteCheck").checked=false;
 document.getElementById("allUpdateCheck").checked=false;
 document.getElementById("allViewCheck").checked=false;
 document.getElementById("allGeneralCheck").checked=false;
 for(var check=0;check <count; check++){
	document.getElementById('insertCheck'+check).checked=false;
	document.getElementById('hidInsertCheck'+check).value='N';
	document.getElementById('updateCheck'+check).checked=false;
	document.getElementById('hidUpdateCheck'+check).value='N';
	document.getElementById('deleteCheck'+check).checked=false;
	document.getElementById('hidDeleteCheck'+check).value='N';
	document.getElementById('viewCheck'+check).checked=false;
	document.getElementById('hidViewCheck'+check).value='N';
	document.getElementById('generalCheck'+check).checked=false;
	document.getElementById('hidGeneralCheck'+check).value='N';
 }
}
//Function to show New Name Div
function callNewName(obj,id){
 try{
 document.getElementById('nameDiv').style.display = "";
			document.getElementById('nameDiv').style.top=getTopPos(obj) + 'px';
			document.getElementById('nameDiv').style.left=getleftPos(obj) + 'px';
			var val = trim(obj.innerHTML);
			while(val.indexOf("  ", " ") > -1) {
				val = val.replace("  ", " ");
			}
			if(val.length > 35) {
				if(val.indexOf("- ") > 0) {
					val = val.replace("- ", "");
				}
			}
			document.getElementById('myName').value = val;
			document.getElementById('myNewName').focus();
			document.getElementById('changeNameId').value = obj.getAttribute('id');
			document.getElementById('changeMenuId').value =id;
			
			
 }catch(e){
	alert(e);
  }
}
//Function to hide 'New Name' Div
function hideDiv(){
	document.getElementById('myNewName').value = "";
	document.getElementById('nameDiv').style.display = "none";
} 

//Function to set new form name
function callNameChange(){
try {
			var nameVal = trim(document.getElementById('myNewName').value);
			if(nameVal.length > 35) {
				if(nameVal.indexOf(" ") < 0) {
					nameVal = nameVal.substr(0,30) + "- " + nameVal.substr(30, nameVal.length);
				}
			}
			while(nameVal.indexOf("  ") > -1) {
				nameVal = nameVal.replace("  "," ");
			}
			var changeNameId = document.getElementById('changeNameId').value;
			var changeMenuId= document.getElementById('changeMenuId').value;
			if(nameVal == "") {
				alert("Please enter new name name");
				document.getElementById('myNewName').value= "";
				document.getElementById('myNewName').focus();
				return false;
			}
			if(nameVal == document.getElementById('myName').value) {
				alert("Current name and new name cannot be same. Please select different name");
				document.getElementById('myNewName').focus();
				return false;
			}
			changeMenu('<%=request.getContextPath()%>/common/profileAction_changeMenu.action?nameId=' + changeNameId + '&nameVal=' + nameVal + '&menuCode=' + changeMenuId + '&abc=' + Math.random());
		} catch(e) {}

}
function restoreMenuName() {
		try {
			var changeNameId = document.getElementById('changeNameId').value;
			var menuCode = document.getElementById('changeMenuId').value;
			restoreMenu('<%=request.getContextPath()%>/common/profileAction_restoreMenu.action?menuCode=' + menuCode + '&nameId=' + changeNameId + '&abc=' + Math.random());
		} catch(e){
		}
	}

//Function to set div's top position  
	function getTopPos(inputObj) {
		var returnValue = inputObj.offsetTop + inputObj.offsetHeight;
	  
		while((inputObj = inputObj.offsetParent) != null) {
	  		returnValue += inputObj.offsetTop;
		}
		if(returnValue < 70) {
			return returnValue;
	  	}
		return returnValue - 100;
	}
//Function to set div's left position 
	function getleftPos(inputObj) {
		var returnValue = inputObj.offsetLeft;
		
	  	while((inputObj = inputObj.offsetParent) != null) {
	  		returnValue += inputObj.offsetLeft;
	  	}
	  	if(returnValue > 350) {
	  		returnValue = returnValue - 400;
	  			  	
		  	while(returnValue < 0) {
		  	 	returnValue += 10;
		  	}
		  	return returnValue;
	  	} else {
	  		return returnValue + 40;
		}
	}
</script>