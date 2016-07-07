<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="TestTemplate" id="paraFrm" validate="true" theme="simple" target="main">
 <s:hidden name="replaceEmpId"/>
 <s:hidden name="hiddenEmpId" />
 <s:hidden name="hiddenEmpName" />
 <s:hidden name="dummyTokenForReplace" />
 <s:hidden name="reqCode"/>
 <s:hidden name="hiringcode" />
<table class="formbg" width="100%"><!--Start of initial table  -->
	<tr>
       <td colspan="3">
         <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg"><!--table 1  -->
         	<tr>
				<td valign="bottom" class="txt"><strong class="formhead">
					<img src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
				<td width="93%" class="txt"><strong class="formhead">Employee List </strong></td>
				<td width="3%" valign="top" class="txt">
					<div align="right">
						<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
			</tr>
         </table><!--end of table 1  -->
       </td>
    </tr>
     <tr>
        <td colspan="3">
        	<table width="100%" border="0" cellpadding="0" cellspacing="0">
        		<tr>
			        <td width="15%">
			        	<input type="button" class="token" name="Remove" value="Remove" onclick="remove();"/>				
			        </td>
			    </tr>   
        	</table>
        </td>
     </tr>  
     <tr>
		<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--table 3  -->
				<tr>	
					<td width="6%" valign="top" class="formth"><b>Sr No</b></td>
					<td width="20%" valign="top" class="formth"><b>Employee Id</b></td>
					<td width="30%" valign="top" class="formth"><b>Employee Name</b></td>
					<td width="10%" valign="top" class="formth" align="center"><b>Select</b></td>
				</tr>
				<%!int l = 0, t=0;%>
				<% int i=1; %>
				<s:iterator status="stat" value="removeEmpDataList">
					<tr>		
						<td class="sortabletd" width="6%"><%=i%>&nbsp;</td>
						<td class="sortabletd" width="25%">
							<input type="hidden" name="removeEmpId" value='<s:property value="removeEmpId"/>' id="removeEmpId<%=l%>"/>
							<s:property value="removeEmpToken"/>&nbsp;</td>
						<td class="sortabletd" width="30%">
							<s:property value="removeEmpName"/>&nbsp;</td>
						<td class="sortabletd" width="10%" align="center">
							<input type="checkbox"  name="check" id="<%="check"+l %>" onclick="getCheckedEmpID('<s:property value="removeEmpId"/>','<%=l%>');" /> 
							<input type="text" name="checkFlag" id="checkFlag<%=l%>" />
						</td>
						<input type="text" name="hdeleteCode" id="hdeleteCode<%=l%>" />
					</tr>
							<%l++;i++;%>							
				</s:iterator>
				<%t=l;l=0; %>
			</table>
		</td>
	</tr>
	<tr>
        <td colspan="3">
        	<table width="100%" border="0" cellpadding="0" cellspacing="0">
        		<tr>
			        <td width="15%">
			        	<input type="button" class="token" name="Remove" value="Remove" onclick="remove();"/>				
			        </td>
			    </tr>   
        	</table>
        </td>
     </tr> 
</table>
</s:form>
<script> 
function remove(){
	var totalRows = <%=t%>;
	var count = 0;
	for(var i=0;i<totalRows;i++){
		if(document.getElementById('check'+i).checked){
			count = 1;
		} 
	} 
	if(count == 0){
		alert('Please select employee');
		return false;
	}  
		document.getElementById("paraFrm").action="EmployeeRequi_removeEmpFrmList.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	    window.close();
}

function getCheckedEmpID(checkedEmpID, i){
	try{
	   if(document.getElementById('check'+i).checked == true) {	  
	    	document.getElementById('hdeleteCode'+i).value=checkedEmpID;
	    	document.getElementById('checkFlag'+i).value="employeeCheck";
	   } else {
	   		document.getElementById('hdeleteCode'+i).value="";
	   }
   	}catch(e){
		alert("Unable to select Employees >>"+e);
	}
}
</script>