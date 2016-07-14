<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript" src="../pages/common/employeeTree.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var Tree = new Array;
	var mainMenu=0;
	</script>


<%
	String[][] temp = null;
	Object[][] items = null;
	try {
		items = (Object[][]) request.getAttribute("items");
	} catch (Exception e) {
		System.out.println("Exception ---0");
	}

	try {
		int i;
		if (items != null && items.length > 0) {
			for (i = 0; i < items.length; i++) {
%>
<script type="text/javascript">
					// nodeId | parentNodeId | nodeName | nodeUrl
		var value= '<%=items[i][0] %>'+'|'+'<%=items[i][1] %>'+'|'+'<%=items[i][2] %>'+'|#';
		Tree[<%=i%>]  = value;
	</script>
<%
		}
		}
	} catch (Exception e) {
	System.out.println("Exception ---1");
	}
%>


<s:form action="AppraiserConfig" theme="simple" method="post" name="paraFrm" id="paraFrm">

	<s:hidden name="check" value="%{check}" /><s:hidden name="moveGroupName"/><s:hidden name="generateListFlag"/>
<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg">
 			<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraiser Configuration </strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
			
			<tr>
	          <td colspan="3"> 
	          
	          <table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
            <td width="78%">
            
            
	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  			
	</td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
				</table>                    
	          </td>
	        </tr>
					
 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
              
              
              <td width="25%" colspan="1" height="20" class="formtext"><label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font> :</td>
			  <s:if test="calUpdateflag" >
			   <td width="25%" colspan="1" height="20" align="left">
			   <s:property value="apprCode"/>
			   <s:hidden name="apprCode"></s:hidden>
			   <s:hidden name="apprId"></s:hidden>
			   </td>
			  </s:if>
			  <s:else>
			  <td width="25%" colspan="1" height="20" align="left">
			  <s:textfield name="apprCode" size="20" maxlength="25" readonly="true" />
			  <s:hidden name="apprId" />
			  <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9AppCal.action'); "></td>
			 
			  </s:else>
			  
              <td width="50%" height="20" class="formtext" colspan="2"><label name="appraisal.from" class = "set"  id="appraisal.from" ondblclick="callShowDiv(this);"><%=label.get("appraisal.from")%></label>: 
              <s:property value="frmDate" />&nbsp;&nbsp; 
              <label name="appraisal.to" class = "set"  id="appraisal.to" ondblclick="callShowDiv(this);">
              <%=label.get("appraisal.to")%></label> : <s:property value="toDate" /></td>
            </tr>
            
                 <tr>
					<td><label name="group.name" class = "set"  id="group.name" ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label> <font color="red">*</font> :</td>
					<td nowrap="nowrap" colspan="2"><s:textfield name="groupName" size="20" onkeypress="return allcharacters();" maxlength="20" /></td>
					<td><input type="button" name="importgroup" id="importgroup" value="Import" onclick="return importGrp();"/></td>
													
				</tr>    
           
          
            </table></td>
          </tr>
      </table></td>
    </tr>
    
     <tr>
	          <td colspan="3"> 
	          
	          <table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
            <td width="78%">
            
            
	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  			
	</td>
            
          </tr>
				<tr>
					<td width="100%" colspan="3">
					<table class="formbg" width="100%">
					<tr>
					<td colspan="7"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);">Appraisee Group List</label></strong></td>
				</tr>
						<tr>
							<!--   srNo -->
							<td align="center" class="formth" width="10%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							
							<td align="center" class="formth" width="25%"><label
								class="set" name="appraisal.code" id="appraisal.code1"
								ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							</td>
							<td align="center" class="formth" width="25%"><label
								class="set" name="group.name" id="group.name1"
								ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label>
							</td>
							<td align="center" class="formth" width="25%"><label
								class="set" name="groupCreationDate" id="groupCreationDate"
								ondblclick="callShowDiv(this);"><%=label.get("groupCreationDate")%></label>
							</td>
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<label class="set" class="token" theme="simple" name="editRemove"
								id="editRemove" ondblclick="callShowDiv(this);">Define Appraiser</label>&nbsp;
							</td>
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<label class="set" class="token" theme="simple" name="editRemove"
								id="editRemove" ondblclick="callShowDiv(this);">Define Appraisee</label>&nbsp;
							</td>
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<input type="button" class="delete" value="Delete" onclick="return deleteProcessConfig();">
							</td>
						</tr>
						<%
							int count1 = 0;
						%>
						<%!int d1 = 0;%>
						<%
							int ii = 0;
						%>
						<%!int val = 0;%>
						<s:iterator value="apprGroupList">
						
						<tr <%if(count1%2==0){
															%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count1++; %>
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
								ondblclick="javascript:callForEdit('<s:property value="category"/>',<s:property value="srNo"/>);">
							<!--   srNo -->
							<td width="10%" align="center" class="sortableTD"><%=++ii%><input type="hidden" name="srNo" value="<%=ii%>" /></td>
							
							<td align="center" class="sortableTD" width="25%"><s:property value="ittrApprCode" />&nbsp;
								<s:hidden name="ittrApprCode" />
								<s:hidden name="ittrApprId" />
								<s:hidden name="ittrGroupId" />
							</td>
							<td align="center" class="sortableTD" width="25%"><s:property value="ittrGroupName" />&nbsp;
								<s:hidden name="ittrGroupName" />
							</td>
							<td align="center" class="sortableTD" width="25%"><s:property value="ittrGroupDate" />&nbsp;
								<s:hidden name="ittrGroupDate" />
							</td>
							<td class="formth" class="sortableTD" width="20%" id="ctrlHide">
							<input type="button" name="defineAppraiser" id="defineAppraiser<%=ii %>" value="Define Appraiser" onclick="defineAppraiserGrp('<s:property value="ittrApprId" />','<s:property value="ittrGroupId" />')" />
							</td>
							<td class="formth" class="sortableTD" width="20%" id="ctrlHide">
							<input type="button" name="defineAppraisee" id="defineAppraisee<%=ii %>" value="Define Appraisee" onclick="defineAppraiseeGrp('<s:property value="ittrApprId" />','<s:property value="ittrGroupId" />')"/>
							</td>
							<td class="formth" class="sortableTD" width="20%" id="ctrlHide">
							<input type="checkbox" name="chkDel" id="chkDel<%=ii%>" value="<s:property value="ittrGroupId" />"  >
							</td>
						</tr>
						
						</s:iterator>
						<input type="hidden" id="rowCount" value="<%=ii %>">		
			       	<%if(ii==0){ %>			       	
				       	 <tr>
				 	          <td colspan="7" class="sortableTD" align="center"><font color="red">No Data To Display</font></td>
				 	     </tr>			       	
			       	<%} %>   
						</table>
						</td>
					</tr>
		




				</table>                    
	          </td>
	        </tr>

</table>
<s:hidden name="addAppraiserFlag"/>
<s:hidden name="modifiedListFlag"/>
<s:hidden name="frmDate"/>
<s:hidden name="toDate"/>
<s:hidden name="calUpdateflag"/>
<!--<s:hidden name="apprCode"/>
--><s:hidden name="groupId"/>

</s:form>

<script>

function importGrp(){
	
	var name = document.getElementById('importgroup').value;
	alert(name);
	document.getElementById('paraFrm').action = 'AppraiserConfig_importGrpdetails.action';
	document.getElementById('paraFrm').submit();
	
	
	
	return true;
}
function resetFun()
{
	
	document.getElementById('paraFrm').action='AppraiserConfig_input.action';
	document.getElementById('paraFrm').submit();
}

function rowCheck(){
		  	  
		  	  limit = document.getElementById('rowCount').value;
		  	  var count = 0;
			  for(i=1;i<=limit;i++){
			  	
			  	if(document.getElementById('chkDel'+i).checked){
			  	 	count++;
		  	  	}
		  	  	
		  	  }
		  	  if(count==0){
		  	  	alert('Please select a record to delete.');
		  	  	return false;
		  	  }	return true;	  	   
		  	  
		  	   
		  	
		  	  
		  	 }
		  	
		  	 function deleteProcessConfig(){
		  	 
		  	 
		  	  if(rowCheck()){
		  	  limit = document.getElementById('rowCount').value;
		  	  var confRequired = true;
		  	  
			  for(i=1;i<=limit;i++){
			  	if(document.getElementById('chkDel'+i).checked){
			  			
			  		if(confRequired){
			  			var con=confirm('Do you really want to delete the record?')			  			
			  			if(con){	 
					  	 	document.getElementById('paraFrm').action='AppraiserConfig_deleteGroupApprConfigurations.action';
					  	 	document.getElementById('paraFrm').submit();
					  	 	return true; 
				  	 	}else{ 
				  	 	    
				  	 	    document.getElementById('chkDel'+i).checked=false;
				  	 	    i=1;
				  	 	    confRequired=false;				  	 	    
				  	 	    
				  	 	}
				  	 }else{
				  	  
				  	   document.getElementById('chkDel'+i).checked=false;
				  	   
				  	 }
			  	 	
			  	}	   
		  	  }
		  	  
		  	  }
		  	 }
		  	 
		  	 
function defineAppraiserGrp(mycheck,groupId)
{alert();
	
	document.getElementById('paraFrm_groupId').value=groupId;
	document.getElementById('paraFrm').action='AppraiserConfig_defineAppraiser.action';
	document.getElementById('paraFrm').submit();
	return true;
}

function defineAppraiseeGrp(mycheck,groupId)
{
	
	document.getElementById('paraFrm_groupId').value=groupId;
	document.getElementById('paraFrm').action='AppraiserConfig_defineAppraisee.action';
	document.getElementById('paraFrm').submit();
	return true;
}  	 

function hasParentNode(chilNode,flagType) {

	
	for (i=0; i<nodes.length; i++) {
		var nodChild = nodes[i].split("|");
		 if (nodChild[0] == chilNode) {
		//	alert(chilNode);
			if( nodChild[1] > 0){
			//	alert(nodChild[1]);
				if(document.getElementById(flagType+chilNode).checked){
					document.getElementById(flagType+nodChild[1]).checked = true;
				}else{
					//document.getElementById('insert'+nodChild[1]).checked = false;
				}
				hasParentNode(nodChild[1],flagType) ;
			}  //END OF 2ND IF
		
		} 
	}
	
//	return false;
}


function checkNode(parent,flagType) {


	for (var i = 0; i < nodes.length; i++) {
		
		var nodeVal= nodes[i].split("|");
		if (nodeVal[1] == parent) {
			if(document.getElementById(flagType+parent).checked){
				document.getElementById(flagType+nodeVal[0]).checked=true;
				var hcn	= hasChild(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}else{
				document.getElementById(flagType+nodeVal[0]).checked=false;
				var hcn	= hasChildFalse(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}
		}
		
	}
}

function hasChild(newNode,flagType) {

	for (i=0; i< nodes.length; i++) {
		var nodeVal = nodes[i].split("|");
		if (nodeVal[1] == newNode){ 
			document.getElementById(flagType+newNode).checked = true;
			return true;
		}
	}
	return false;
}

function hasChildFalse(newNode,flagType) {

	for (i=0; i< nodes.length; i++) {
		var nodeVal = nodes[i].split("|");
		if (nodeVal[1] == newNode){ 
			document.getElementById(flagType+newNode).checked = false;
			return true;
		}
	}
	return false;
}


	function onload(){
	if(document.getElementById("paraFrm_empTypeFlag").value=="false"){
		document.getElementById('configDiv').style.display='';
		document.getElementById('pendingDiv').style.display='none';
	}else{
		document.getElementById('configDiv').style.display='none';
		document.getElementById('pendingDiv').style.display='';
	}
	if(document.getElementById("paraFrm_isSelfPhase").value=="Y"){
		document.getElementById('apprDiv').style.display='none';
		document.getElementById('paraFrm_appraiserName').value="";
		document.getElementById('paraFrm_appraiserId').value="";
		document.getElementById('paraFrm_appraiserCode').value="";
		document.getElementById('paraFrm_appraiserLevel').value="1";
	}else
	{
	document.getElementById('apprDiv').style.display='';
	document.getElementById('paraFrm_appraiserLevel').value="";
	}
	
	}
	onload();
	
	function showGroupDiv(obj)
	{
		document.getElementById("paraFrm_groupId").value="";
		document.getElementById("paraFrm_selectGroupId").value="";
		document.getElementById("paraFrm_groupName").value="";
		document.getElementById("paraFrm_selectGroupName").value="";
		callClear();
		if(obj.value=="C")
		{
			document.getElementById("paraFrm_empTypeFlag").value="false";
			document.getElementById('configDiv').style.display='';
			document.getElementById('pendingDiv').style.display='none';
		}
		else 
		{
			document.getElementById("paraFrm_empTypeFlag").value="true";
			document.getElementById('configDiv').style.display='none';
			document.getElementById('pendingDiv').style.display='';
		}
		document.getElementById("paraFrm_generateListFlag").value = false;
		if(document.getElementById("paraFrm_apprId").value !=""){
				document.getElementById("paraFrm").action="AppraiserConfig_resetDetails.action";
				document.getElementById("paraFrm").submit();
				}
	}
	
	function callSelectGroup(){
		if(document.getElementById("paraFrm_apprId").value==""){
			alert("Please select "+document.getElementById("appraisal.code").innerHTML);
			return false;
		}
		else
		javascript:callsF9(500,325,'AppraiserConfig_f9SelectGroupName.action');
	}
	
	function callShowEmp(){
	if(document.getElementById("paraFrm_apprId").value==""){
		alert("Please select "+document.getElementById("appraisal.code").innerHTML+".");
		return false;
	}	
	if(document.getElementById("paraFrm_divCode").value==""){
		alert("Please select "+document.getElementById("division").innerHTML+".");
		return false;
	}
				document.getElementById("paraFrm").action="AppraiserConfig_getEmployees.action";
				document.getElementById("paraFrm").submit(); 
	}
	function saveFun(){
	if(document.getElementById("paraFrm_apprCode").value==""){
				 	alert("Please select "+document.getElementById("appraisal.code").innerHTML+"." );
					document.getElementById("paraFrm_apprCode").focus();
					return false;
				 	}
				/*var count = document.getElementById("count").value;
				var empType = document.getElementById("paraFrm_empTypeFlag").value;
				var selectGroupName = document.getElementById("paraFrm_selectGroupName").value;
				var selectGroupId = document.getElementById("paraFrm_selectGroupId").value;*/
				var groupName = document.getElementById("paraFrm_groupName").value;
				if(groupName==""){
					alert("Please enter "+document.getElementById("group.name").innerHTML+"." );
					document.getElementById("paraFrm_groupName").focus();
					return false;
				}
				
				document.getElementById("paraFrm").action="AppraiserConfig_save.action";
				document.getElementById("paraFrm").submit();
	}
	
function callPhase(){

	if(document.getElementById("paraFrm_apprId").value==""){
		alert("Please select "+document.getElementById("appraisal.code").innerHTML+" .");
		return false;
	}	
	javascript:callsF9(500,325,'AppraiserConfig_f9SelectPhase.action'); 
}
function callAdd(){
		var countCheck = document.getElementById("count").value;
		var fields=["paraFrm_phaseName","paraFrm_appraiserName","paraFrm_appraiserLevel"];
		var labels=[document.getElementById("phase.name").innerHTML,document.getElementById("appraiser.name").innerHTML,
		document.getElementById("appraiser.level").innerHTML];
		var types=["select","select","enter"];
		
			
		var phaseId= document.getElementById("paraFrm_phaseId").value;
		var appraiserId = document.getElementById("paraFrm_appraiserCode").value;
		var appraiserLevel = document.getElementById("paraFrm_appraiserLevel").value;
		
		if(document.getElementById("paraFrm_isSelfPhase").value=="Y"){
			if(document.getElementById("paraFrm_phaseName").value=="")
			{
				alert("Please select "+document.getElementById("phase.name").innerHTML);
				document.getElementById("paraFrm_phaseName").focus();
				return false;
			}
		}else
		{
			if(!(checkMandatory(fields,labels,types)))
			{
				return false;
			}
			
			/* Appraisal Level */
			if (appraiserLevel == 0)
			{
				alert("Appraisal Level '0' is not allowed.");			
				document.getElementById("paraFrm_appraiserLevel").value = "";
				document.getElementById("paraFrm_appraiserLevel").focus();
				return false;
			} 
		}
		
		
		
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
				var phaseIdList= document.getElementById("phaseIdList"+j).value;
				var appraiserCodeList= document.getElementById("appraiserCodeList"+j).value;
				var appraisalLevelList = document.getElementById("appraisalLevelList"+j).value;
				if(phaseId == phaseIdList && appraiserId == appraiserCodeList ){
					if(appraiserId==""){
					alert("Phase is already added");
						return false;
					}else{
					alert("' "+document.getElementById("paraFrm_appraiserName").value+" ' is already added for this phase. ");
					return false;
					}
				}
				if(phaseId == phaseIdList && appraiserLevel == appraisalLevelList ){
				
					alert("Level  ' "+appraiserLevel+" ' Appraiser is already added for this phase. ");
					return false;
				}
				
			}
		}
		
		document.getElementById("paraFrm_addQuestionFlag").value="true";
}

function callRemove(){
		var countCheck = document.getElementById("count").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("removeAppraiser"+j).checked){
						counter += j+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one Appriser to remove.");
			return false;
			}
		var conf=confirm("Do you really want to remove these Appraisers ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
					document.getElementById("paraFrm").action="AppraiserConfig_removeMultiple.action";
					document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		
	}
	function callClear(){
		document.getElementById("paraFrm_divCode").value ="";
		document.getElementById("paraFrm_branchCode").value ="";
		document.getElementById("paraFrm_deptCode").value ="";
		document.getElementById("paraFrm_divName").value ="";
		document.getElementById("paraFrm_branchName").value ="";
		document.getElementById("paraFrm_deptName").value ="";
	}
	function backFun(){
		
		document.getElementById("paraFrm").action="AppraisalCalendar_input.action?menuCode=748";
		document.getElementById("paraFrm").submit();
		
}
</script>
