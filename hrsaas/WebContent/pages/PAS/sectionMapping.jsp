<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript" src="../pages/common/employeeTreeMapping.js"></script>
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
	Object [][] phaseList = null;
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


<s:form action="SectionMapping" theme="simple" method="post" name="paraFrm" id="paraFrm">
<%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>
		<s:hidden name="apprId"/>
		<s:hidden name="startDate"></s:hidden>
		<s:hidden name="endDate"></s:hidden>
		<s:hidden name="apprCode"></s:hidden>
		<s:hidden name="templateCode"></s:hidden>
		<s:hidden name="templateName"></s:hidden>
		<s:hidden name="addFLag"></s:hidden>
		<s:hidden name="moveGroupName"/>
		<s:hidden name="generateListFlag"/>
		<s:hidden name="modifiedListFlag"/>
		<s:hidden name="empTypeFlag"/>
		<s:hidden name="addQuestionFlag"/>
		<s:hidden name="isSectionDefinedFlag"/>
		<s:hidden name="check" value="%{check}" />
		
		
<table width="100%" border="0" align="right" cellpadding="2"
					cellspacing="1" class="formbg">

					
					
 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Section Mapping
						</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
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
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
				</table>                    
	          </td>
	        </tr>
					
 <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">

						<tr>
							
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="appraisal.code" id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="apprCode" />
							<td width="50%" colspan="2" height="20" nowrap="nowrap" class="formtext"><label name="template.appr.start.date" class = "set"  id="template.appr.start.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.start.date")%></label>: <s:property value="startDate" />&nbsp;&nbsp;<label name="template.appr.end.date" class = "set"  id="template.appr.end.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.end.date")%></label> : <s:property value="endDate" />
							</td>
						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="template.name" id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="templateName" /></td>
							<td width="25%" colspan="1" height="20" class="formtext"></td>
							<td width="25%" colspan="1" height="20"></td>
						</tr>
						
						<tr>
							<td width="25%"  ><label name="group.name" class = "set"  id="group.name" ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label> <font color="red">*</font> :</td>
													<td  width="50%" nowrap="nowrap" colspan="2"><s:hidden name="groupId"/><s:textfield name="groupName" size="20" maxlength="20" onkeypress="return allcharacters();"/></td>
							<td width="25%" colspan="1" height="20" class="formtext"><input type="button" value="Add Template Group" class="token" onclick="addTemplateGroupFun();" /></td>
							<td width="25%" colspan="1" height="20"></td>
						</tr>

					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		
		<tr>
					<td width="100%" colspan="3">
					<table class="formbg" width="100%">
					<tr>
					<td colspan="7"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);">Template Group List</label></strong></td>
				</tr>
						<tr>
							<!--   srNo -->
							<td align="center" class="formth" width="10%"><label
								class="set" name="rating.srno" id="rating.srno"
								ondblclick="callShowDiv(this);"><%=label.get("rating.srno")%></label></td>
							
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
								id="editRemove" ondblclick="callShowDiv(this);">Define Template Question</label>&nbsp;
							</td>
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<label class="set" class="token" theme="simple" name="editRemove"
								id="editRemove" ondblclick="callShowDiv(this);">Define Template Employee</label>&nbsp;
							</td>
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<input type="button" class="delete" value="    Delete" onclick="return deleteProcessConfig();">
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
								<s:hidden name="ittrGroupDate" /></label>
							</td>
							<td class="formth" class="sortableTD" width="20%" id="ctrlHide">
							<input type="button" name="defineAppraiser" id="defineAppraiser<%=ii %>" value="Define Template Ques" onclick="defineTemplateQues('<s:property value="ittrApprId" />','<s:property value="ittrGroupId" />')" />
							</td>
							<td class="formth" class="sortableTD" width="20%" id="ctrlHide">
							<input type="button" name="defineAppraisee" id="defineAppraisee<%=ii %>" value="Define Template Emp" onclick="defineTemplateEmployee('<s:property value="ittrApprId" />','<s:property value="ittrGroupId" />')"/>
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


	
				   		


 <tr>
	          <td colspan="3"> 
	          
	          <table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
            <td width="78%">
            
            
	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  			
	</td>
            
          </tr>
				</table>                    
	          </td>
	        </tr>

</table>



</s:form>

<script>
function defineTemplateEmployee(mycheck,groupId)
{
	
	document.getElementById('paraFrm_groupId').value=groupId;
	document.getElementById('paraFrm').action='SectionMapping_defineTemplateEmployee.action';
	document.getElementById('paraFrm').submit();
	return true;
}
function defineTemplateQues(mycheck,groupId)
{
	
	document.getElementById('paraFrm_groupId').value=groupId;
	document.getElementById('paraFrm').action='SectionMapping_defineTemplateQues.action';
	document.getElementById('paraFrm').submit();
	//return true;
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
					  	 	document.getElementById('paraFrm').action='SectionMapping_deleteGroupApprConfigurations.action';
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
function addTemplateGroupFun()
{
	var groupName = document.getElementById("paraFrm_groupName").value;
		if(groupName==""){
					alert("Please enter "+document.getElementById("group.name").innerHTML+"." );
					document.getElementById("paraFrm_groupName").focus();
					return false;
				}
				document.getElementById("paraFrm").action="SectionMapping_save.action";
				document.getElementById("paraFrm").submit();
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
	
			
	}
	
	
	onload();
	
	function showGroupDiv(obj)
	{
		document.getElementById("paraFrm_groupId").value="";
		document.getElementById("paraFrm_selectGroupId").value="";
		document.getElementById("paraFrm_groupName").value="";
		document.getElementById("paraFrm_selectGroupName").value="";
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
		document.getElementById("paraFrm_generateListFlag").value = 'false';
		callClear();
				document.getElementById("paraFrm").action="SectionMapping_resetDetails.action";
				document.getElementById("paraFrm").submit();
				
	}
	
	function callShowEmp(type){
		document.getElementById("paraFrm_modifiedListFlag").value="true";
	if(document.getElementById("paraFrm_divCode").value==""){
		alert("Please select "+document.getElementById("division").innerHTML+".");
		return false;
	}
	
				document.getElementById("paraFrm").action="SectionMapping_getEmployees.action";
				document.getElementById("paraFrm").submit();
	}
	function saveandnextFun(){
	
				var count = document.getElementById("count").value;
				var empType = document.getElementById("paraFrm_empTypeFlag").value;
				var groupName = document.getElementById("paraFrm_groupName").value;
				var selectGroupName = document.getElementById("paraFrm_selectGroupName").value;
				var selectGroupId = document.getElementById("paraFrm_selectGroupId").value;
				 var f9Fields=["paraFrm_groupName"];
				 var f9Fields1=["paraFrm_selectGroupName"];
				if(empType=="true"){
				if(groupName==""){
					alert("Please enter "+document.getElementById("group.name").innerHTML+"." );
					document.getElementById("paraFrm_groupName").focus();
					return false;
				}
				if(!f9specialchars(f9Fields))
					return false;
				}else{
				if(selectGroupId==""){
					alert("Please select "+document.getElementById("select.group").innerHTML+"." );
					document.getElementById("paraFrm_selectGroupName").focus();
					return false;
				}
				if(selectGroupName==""){
					alert("Please enter "+document.getElementById("select.group").innerHTML+"." );
					document.getElementById("paraFrm_selectGroupName").focus();
					return false;
				}
				if(!f9specialchars(f9Fields1))
					return false;
				}
				if(document.getElementById("paraFrm_generateListFlag").value=="false"){
						alert("Please generate the Employee list.");
						return false;
					}
				if(count=="0"){
					alert("Please add atleast one Section in list");
					return false;
				}
				document.getElementById("paraFrm").action="SectionMapping_saveAndNext.action";
				document.getElementById("paraFrm").submit();
	}
	function saveandpreviousFun(){
	
				var count = document.getElementById("count").value;
				var empType = document.getElementById("paraFrm_empTypeFlag").value;
				var groupName = document.getElementById("paraFrm_groupName").value;
				var selectGroupName = document.getElementById("paraFrm_selectGroupName").value;
				var selectGroupId = document.getElementById("paraFrm_selectGroupId").value;
				 var f9Fields=["paraFrm_groupName"];
				 var f9Fields1=["paraFrm_selectGroupName"];
				if(empType=="true"){
				if(groupName==""){
					alert("Please enter "+document.getElementById("group.name").innerHTML+"." );
					document.getElementById("paraFrm_groupName").focus();
					return false;
				}
				if(!f9specialchars(f9Fields))
					return false;
				}else{
				if(selectGroupId==""){
					alert("Please select "+document.getElementById("select.group").innerHTML+"." );
					document.getElementById("paraFrm_selectGroupName").focus();
					return false;
				}
				if(selectGroupName==""){
					alert("Please enter "+document.getElementById("select.group").innerHTML+"." );
					document.getElementById("paraFrm_selectGroupName").focus();
					return false;
				}
				if(!f9specialchars(f9Fields1))
					return false;
				}
				if(document.getElementById("paraFrm_generateListFlag").value=="false"){
						alert("Please generate the Employee list.");
						return false;
					}
				if(count=="0"){
					alert("Please add atleast one Section in list");
					return false;
				}
				document.getElementById("paraFrm").action="SectionMapping_saveAndPrevious.action";
				document.getElementById("paraFrm").submit();
	}
	function saveFun(){
	
				var count = document.getElementById("count").value;
				var empType = document.getElementById("paraFrm_empTypeFlag").value;
				var groupName = document.getElementById("paraFrm_groupName").value;
				var selectGroupName = document.getElementById("paraFrm_selectGroupName").value;
				var selectGroupId = document.getElementById("paraFrm_selectGroupId").value;
				 var f9Fields=["paraFrm_groupName"];
				 var f9Fields1=["paraFrm_selectGroupName"];
				if(empType=="true"){
				if(groupName==""){
					alert("Please enter "+document.getElementById("group.name").innerHTML+"." );
					document.getElementById("paraFrm_groupName").focus();
					return false;
				}
				if(!f9specialchars(f9Fields))
					return false;
				}else{
				if(selectGroupId==""){
					alert("Please select "+document.getElementById("select.group").innerHTML+"." );
					document.getElementById("paraFrm_selectGroupName").focus();
					return false;
				}
				if(selectGroupName==""){
					alert("Please enter "+document.getElementById("select.group").innerHTML+"." );
					document.getElementById("paraFrm_selectGroupName").focus();
					return false;
				}
				if(!f9specialchars(f9Fields1))
					return false;
				}
				if(document.getElementById("paraFrm_generateListFlag").value=="false"){
						alert("Please generate the Employee list.");
						return false;
					}
				if(count=="0"){
					alert("Please add atleast one Section in list");
					return false;
				}
				document.getElementById("paraFrm").action="SectionMapping_save.action";
				document.getElementById("paraFrm").submit();
	}
	

function callAdd(){
		var countCheck = document.getElementById("count").value;
		var fields=["paraFrm_sectionName","paraFrm_parameter","paraFrm_weightage"];
		var labels=[document.getElementById("section.name").innerHTML,document.getElementById("parameter").innerHTML,
		document.getElementById("weightage").innerHTML];
		var types=["select","select","enter"];
		
		
		if(!(checkMandatory(fields,labels,types))){
		return false;
		}
		if(isNaN(document.getElementById("paraFrm_weightage").value)) {
	 // if(!amount.match(amountpat)) { 
	   alert("Only numbers are allowed in "+document.getElementById("weightage").innerHTML+" field.");
		 document.getElementById('paraFrm_weightage').focus();
		return false;
		}
		
		var phaseCount = document.getElementById("phaseCount").value ;
		var checked = false;
		for(j=0;j< phaseCount;j++){
				if(document.getElementById("phaseCode"+j).checked){
					checked= true;
					break ;
				}
			}
			if(!checked){
				alert("Please select atleast one "+document.getElementById("applicable.phase").innerHTML);
				return false;
			}
		
		var count = document.getElementById("count").value;
		if(count!="0"){
		var sectionId= document.getElementById("paraFrm_sectionId").value;
		var questionOrder = document.getElementById("paraFrm_questionOrder").value;
		var questionId = document.getElementById("paraFrm_parameterId").value;
		
				for (i=0;i<count;i++){
				var sectionIdList =document.getElementById("sectionId"+i).value;
				var questionOrderList = document.getElementById("questionOrder"+i).value;
				var questionIdList = document.getElementById("parameterIdList"+i).value;
					
					if(sectionIdList == sectionId && questionIdList==questionId){
						alert("This "+document.getElementById("parameter").innerHTML+" is already added for this "+document.getElementById("section.name").innerHTML);
						return false;					
					}
					
					if(sectionIdList == sectionId && questionOrderList==questionOrder){
						alert(document.getElementById("question.order").innerHTML +" "+ questionOrder+" is already added for this "+document.getElementById("section.name").innerHTML);
						return false;					
					}
					
					
				}
			}
			document.getElementById("paraFrm_addQuestionFlag").value="true";
			document.getElementById("paraFrm").action ="SectionMapping_addQuestion.action";
			document.getElementById("paraFrm").submit();
		}



function callRemove(){
		var countCheck = document.getElementById("count").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("removeSection"+j).checked){
						counter += j+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one record to remove.");
			return false;
			}
		var conf=confirm("Do you really want to remove these record ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
			  		document.getElementById("paraFrm_addQuestionFlag").value = "true";
					document.getElementById("paraFrm").action="SectionMapping_removeMultiple.action";
					document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		
	}
	
	function nextFun(){
		if(document.getElementById("paraFrm_isSectionDefinedFlag").value=="false"){
				alert("Section is not defined for this calendar");
				return false;
		}else{
					document.getElementById("paraFrm").action="SectionMapping_next.action";
					document.getElementById("paraFrm").submit();
		}
	}
	function previousFun(){
		/*if(document.getElementById("paraFrm_isSectionDefinedFlag").value=="false"){
				alert("Section is not defined for this calendar");
				return false;
		}else{*/
					document.getElementById("paraFrm").action="SectionMapping_previous.action";
					document.getElementById("paraFrm").submit();
		//}
	}
	function callClear(){
		document.getElementById("paraFrm_divCode").value ="";
		document.getElementById("paraFrm_branchCode").value ="";
		document.getElementById("paraFrm_deptCode").value ="";
		document.getElementById("paraFrm_divName").value ="";
		document.getElementById("paraFrm_branchName").value ="";
		document.getElementById("paraFrm_deptName").value ="";
	}
</script>
