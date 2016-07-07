<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript" src="../pages/common/employeeTreeMapping.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SectionMapping" theme="simple" method="post" name="paraFrm" id="paraFrm">
<%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>
		<s:hidden name="apprId"/>
		<s:hidden name="startDate"></s:hidden>
		<s:hidden name="endDate"></s:hidden>
		<s:hidden name="apprCode"></s:hidden>
		<s:hidden name="templateCode"/>
		<s:hidden name="templateName"></s:hidden>
		<s:hidden name="addFLag"></s:hidden>
		<s:hidden name="moveGroupName"/>
		<s:hidden name="generateListFlag"/>
		<s:hidden name="modifiedListFlag"/>
		<s:hidden name="empTypeFlag"/>
		<s:hidden name="addQuestionFlag"/>
		<s:hidden name="isSectionDefinedFlag"/>
		<s:hidden name="check" value="%{check}" />
		<s:hidden name="groupId"/>
		<s:hidden name="groupName"></s:hidden>
	
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
						<td width="93%" class="txt"><strong class="text_head">Define Template Employee
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
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="6"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);">Configured Employee List</label></strong></td>
				</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						
						<tr class="td_bottom_border">
							<td width="5%" class="formth" nowrap="nowrap"><label name="section.srno" class = "set"  id="section.srno" ondblclick="callShowDiv(this);"><%=label.get("section.srno")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraiser.emptoken" class = "set"  id="appraiser.emptoken" ondblclick="callShowDiv(this);"><%=label.get("appraiser.emptoken")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraiser.empName" class = "set"  id="appraiser.empName" ondblclick="callShowDiv(this);"><%=label.get("appraiser.empName")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraiser.branch" class = "set"  id="appraiser.branch" ondblclick="callShowDiv(this);"><%=label.get("appraiser.branch")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label name="appraiser.department" class = "set"  id="appraiser.department" ondblclick="callShowDiv(this);"><%=label.get("appraiser.department")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label name="appraiser.designation" class = "set"  id="appraiser.designation" ondblclick="callShowDiv(this);"><%=label.get("appraiser.designation")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label name="appraiser.repotingTo" class = "set"  id="appraiser.repotingTo" ondblclick="callShowDiv(this);"><%=label.get("appraiser.repotingTo")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><input type="button" value="   Move to Other Group" class="del" onclick="moveToGroup();"></td>
							<td width="10%" class="formth" nowrap="nowrap"><input type="button" value="Delete" class="del" onclick="delAppraisee();"></td>
						</tr>
						<% int i = 0;%>

						<s:iterator value="confAppraiseeList">


							<tr class="sortableTD">
								<td class="sortableTD" width="5%" align="center"><%=i+1%></td>
								<td class="sortableTD" width="5%"><s:property value="ittrConfEmpToken" /><s:hidden
									name="ittrConfEmpToken"></s:hidden><s:hidden
									name="ittrConfEmpId" id='<%="ittrConfEmpId"+i%>' ></s:hidden></td>
								<td class="sortableTD" width="5%"><s:property value="ittrConfEmpName" /><s:hidden
									name="ittrConfEmpName"/></td>
								<td class="sortableTD" width="5%"><s:property value="ittrConfEmpBranch" /><s:hidden
									name="ittrConfEmpBranch"/></td>
								<td class="sortableTD" width="5%" align="center"><s:property value="ittrConfEmpDepartment" /><s:hidden
									name="ittrConfEmpDepartment" /></td>
									<td class="sortableTD" width="5%" align="center"><s:property value="ittrConfEmpDesgination" /><s:hidden
									name="ittrConfEmpDesgination" /></td>
									<td class="sortableTD" width="5%" align="center"><s:property value="ittrConfEmpReptTo" /><s:hidden
									name="ittrConfEmpReptTo" /></td>
								<td class="sortableTD" width="5%" align="center"><input type="checkbox" id='<%="moveAppraisee"+i%>' name="moveAppraisee"></td>
								<td class="sortableTD" width="5%" align="center"><input type="checkbox" id='<%="deleteAppraisee"+i%>' name="deleteAppraisee"></td>
								
							</tr>

							<%
			i++;
			%>
						</s:iterator>
			
			<input type="hidden" name="count" id="count" value="<%=i%>"/>
			<s:hidden name="paraId"/>
				<%if(i==0){ %>			       	
				       	 <tr>
				 	          <td colspan="7" class="sortableTD" align="center"><font color="red">No Data To Display</font></td>
				 	     </tr>			       	
			       	<%} %>   
					</table>

					</td>
				
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
function delAppraisee(){
		var countCheck = document.getElementById("count").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("deleteAppraisee"+j).checked){
						//counter += j+",";
						counter += document.getElementById("ittrConfEmpId"+j).value+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one employee to delete.");
			return false;
			}
			//alert("counter :: "+counter);
		var conf=confirm("Do you really want to delete employee ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
					//win=window.open('','win','top=240,left=130,width=400,height=200,scrollbars=no,status=no,resizable=no');
					//document.getElementById("paraFrm").target="win";
					document.getElementById("paraFrm").action="SectionMapping_deleteAppraisee.action";
					document.getElementById("paraFrm").submit();	
					//document.getElementById("paraFrm").target="main";
  			}else{
  				return false;
  			}
		
}
function moveToGroup(){
		var countCheck = document.getElementById("count").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("moveAppraisee"+j).checked){
						//counter += j+",";
						counter += document.getElementById("ittrConfEmpId"+j).value+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one employee to move.");
			return false;
			}
			//alert("counter :: "+counter);
		var conf=confirm("Do you really want to move employee ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
					win=window.open('','win','top=240,left=130,width=400,height=200,scrollbars=no,status=no,resizable=no');
					document.getElementById("paraFrm").target="win";
					document.getElementById("paraFrm").action="SectionMapping_openMoveToGroupForm.action";
					document.getElementById("paraFrm").submit();	
					document.getElementById("paraFrm").target="main";
  			}else{
  				return false;
  			}
		
}
	function addemployeeFun()
	{
					document.getElementById("paraFrm").action="SectionMapping_addEmployee.action";
					document.getElementById("paraFrm").submit();
	}
function backFun(){
	
				
				document.getElementById("paraFrm").action="SectionMapping_input.action";
				document.getElementById("paraFrm").submit();
	}
</script>