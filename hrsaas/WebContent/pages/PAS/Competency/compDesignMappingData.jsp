<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="CompDesigMapping" id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" align="right" class="formbg">	
		<tr>
			<td valign="bottom" class="txt">
				<table  width="100%" border="0"  class="formbg" >
					<tr>
						<td>
							<strong class="text_head"><img	src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" />	</strong>
						</td>
						<td width="93%" class="txt">
							<strong class="text_head">Competency Designation Mapping </strong>
						</td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img 	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					    </td>
					</tr> 
				</table>
			</td>
		</tr>
		
	   <tr>
			<td colspan="3">
				<table width="100%" border="0"  >
					<tr>
						<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				        <td width="20%">
							<div align="right"><span class="style2"><font	color="red">*</font></span> Indicates Required</div>
						</td>
					</tr>
		       </table>
			</td>
		</tr>
		
		
	    <tr>
		   <td width="100%">
				<table width="100%" border="0" align="center"  class="formbg">
					<tr>
						<td class="formtext"><label  name="designation1" id="designation1" ondblclick="callShowDiv(this);"><%=label.get("designation1")%></label>:<font color="red">*</font></td>
						<td nowrap="nowrap">
						
							<s:if test="%{editFlag}">
							</s:if>
							<s:else>
								<s:hidden name="desgCode" />
								<s:textfield name="desgName" size="30" readonly="true" />
								<img src="../pages/images/recruitment/search2.gif" width="16" class="iconImage" height="15"
									onclick="javascript:callsF9(500,325,'CompDesigMapping_f9desg.action');" />
							</s:else>	
						</td>
					</tr>
					<tr>	
						<td class="formtext"><label  class = "set" name="competency" id="competency" ondblclick="callShowDiv(this);"><%=label.get("competency")%></label>:<font color="red">*</font>
						</td>
						<td nowrap="nowrap">
							
							<s:if test="%{editFlag}">
							</s:if>
							<s:else>
								<s:hidden name="competencyCode" />
								<s:textfield name="competencyName" size="30" readonly="true"  />
								<img src="../pages/images/recruitment/search2.gif" width="16"	class="iconImage" height="15"
									  onclick="clearFields()" />
							</s:else>
							
						</td>
					</tr>
					
					<tr>	
						<td class="formtext"><label  class = "set" name="weight" id="weight" ondblclick="callShowDiv(this);"><%=label.get("weight")%></label>:<font color="red">*</font>
						</td>
						<td  nowrap="nowrap">
							
							<s:if test="%{editFlag}">
							</s:if>
							<s:else>
								 
								<s:textfield name="compWeightname" 
								onkeyup="setOldvalue();"
								onkeypress="return numbersOnly();" maxlength="3" size="30"/>   
								
							</s:else>
							
						</td>
					</tr>
					
					
					<tr>
						<td width="20%" height="22" class="formtext">
						<label name="category" class="set" id="category" 	ondblclick="callShowDiv(this);"><%=label.get("category")%></label> :
						</td>
						<td nowrap="nowrap">
							<s:if test="%{editFlag}">
							</s:if>
							<s:else>
								<s:hidden name="categoryCode" />
								
								<s:textfield name="category" size="30" readonly="true" />
								<img src="../pages/images/recruitment/search2.gif" width="16"	class="iconImage" height="15"
								     onclick="javascript:callsF9(500,325,'CompDesigMapping_f9categoryAction.action');"  />
							</s:else>
							
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="22" class="formtext">
						<label name="profreciency" class="set" id="profreciency" 	ondblclick="callShowDiv(this);"><%=label.get("profreciency")%></label> :<font color="red">*</font>
						</td>
						<td nowrap="nowrap">
							
							
							<s:if test="%{editFlag}">
							</s:if>
							<s:else>
								<s:textfield name="proLevel" size="30" readonly="true" />
								<img src="../pages/images/recruitment/search2.gif" width="16"	class="iconImage" height="15"
								     onclick="javascript:callsF9(500,325,'CompDesigMapping_f9levelAction.action');"  />
							</s:else>
							
						</td>
					</tr>
					<tr>
						<td  colspan="2" align="center">
						    <s:if test="%{editFlag}">
							</s:if>
							<s:else>
								<input type="button" value="Add" cssClass="add"  onclick="validate()" ></input>
							</s:else>
						
							
						</td>
					</tr>
			   </table>
		    </td>
		</tr>
		
		
		<tr>
			<td>
				<table  id="myTable"  width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg" >
						<tr>
							
							<td class="formth" align="center" width="20%"><label  name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							<td class="formth" align="center" width="20%"><label  name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
							<td class="formth" align="center" width="20%"><label  name="competency" id="competency" ondblclick="callShowDiv(this);"><%=label.get("competency")%></label></font></td>
							<td class="formth" align="center" width="20%"><label  name="weight" id="weight" ondblclick="callShowDiv(this);"><%=label.get("weight")%></label></font></td>
							<td class="formth" align="center" width="20%"><label name="category"  id="category" 	ondblclick="callShowDiv(this);"><%=label.get("category")%></label></td>
							<td class="formth" align="center" width="20%"><label name="profreciency"  id="profreciency" 	ondblclick="callShowDiv(this);"><%=label.get("profreciency")%></label></td>
							<td class="formth" align="center" width="20%" ><label name="deleteEdit"   id="deleteEdit" ondblclick="callShowDiv(this);"><%=label.get("deleteEdit")%></label></td>
						</tr>
						<% int cnt = 0;int cn=0;%>
						<s:iterator value="compDataList" >
						<tr>
							<td align="center">  
							     <%=++cn%>
							     <s:hidden name="srNoIt"/>
							</td>
							<td>  
								<s:hidden  name="desigNameIt" />
							    <s:property value="desigNameIt" />
							    <s:hidden name="desigCodeIt" />
							 </td>
							<td>
							
							  <s:hidden name="competencyNameIt" />
							  <s:property value="competencyNameIt" />
							  <s:hidden name="competencyCodeIt" />
							  
							</td>
							
							<td>  
								<s:hidden  name="weightNameIt" />
							    <s:property value="weightNameIt" id="weightNameIt<%=cn %>"/>
							 </td>
							 
							 
							<td>
							  <s:hidden name="categoryCodeIt" />
							  <s:property value="categoryIt" />
							  <s:hidden name="categoryIt" />
							</td>
							<td>
							  <s:hidden name="proLevelIt" />
							  <s:property value="proLevelIt" />
							</td>	
							<td align="center" >
								<input type="button" class="rowEdit" theme="simple"  title="Edit Record"
								       onclick="editRecord(<%=cn %>, '<s:property value="desigNameIt"/>',
								       								 '<s:property value="desigCodeIt"/>',	 
								       								 '<s:property value="competencyNameIt"/>',
								       								 '<s:property value="competencyCodeIt"/>',
								       								 '<s:property value="weightNameIt"/>', 
								       								 '<s:property value="categoryCodeIt"/>',
								       								 '<s:property value="categoryIt"/>',
																	 '<s:property value="proLevelIt"/>')"/>
								
								<input type="button" class="rowDelete"	theme="simple"  title="Delete Record" 
										onclick="deleteRecord('<s:property value="competencyCodeIt"/>','<s:property value="desigCodeIt"/>','<s:property value="weightNameIt"/>')"/>
							</td>	
						</tr>
						
					    </s:iterator>	
					
					</table>
					 
					<tr>
				 <td><font color="red">Note:</font>Total Competency Weightage should not be greater than or less than 100 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<b>  Total:</b>&nbsp; &nbsp; &nbsp; &nbsp;  &nbsp;<s:property value="totalWeightage"/><s:hidden  
							name="totalWeightage"/></td>   
				    
				</tr>  
				</td>
				<input type="hidden" name="rowNum" id="rowNum" value="<%=cn%>"/>
				<s:hidden name="hiddenEdit" />     
		</tr>
		<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
		<s:hidden name="editCode" />
		
		
		<s:hidden name="oldvalue" id="oldvalue"/>   
</table></s:form>



<script> 


 
function callAdd()
{
		var fields=["paraFrm_divName","paraFrm_competencyName","paraFrm_proLevel"];
		if(!f9specialchars(fields)){
	          return false;
	    }
	    document.getElementById('paraFrm').target = "main";
	    return true;
}


function saveFun(){

	var wtg=document.getElementById('paraFrm_totalWeightage').value;     
	 if(wtg!=100)
	 {
	 alert("Total Competency Weightage should not be less than or greater than 100");
	 callButton('NA', 'Y', 2);
	 return false;
	 }
	  
	callButton('NA', 'Y', 2);
	if(document.getElementById("myTable").rows.length==1)
	{
		alert("Please add atleast one record")
	}else{
		var con=confirm('Do you want to save the record(s) ?');
	 	if(con){
		   	document.getElementById("paraFrm").action="CompDesigMapping_save.action";
		   	document.getElementById('paraFrm').target = "_self";
		    document.getElementById("paraFrm").submit();
		    return true;
		}
    }	    
}

function resetFun(){ 
	callButton('NA', 'Y', 2);
	document.getElementById("paraFrm").action="CompDesigMapping_reset.action";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}
function editRecord(srNo, desgName,desgCode,compName,compCode,wtname,catCode,catagory,proLevel){
    document.getElementById('paraFrm_hiddenEdit').value=srNo;
	document.getElementById('paraFrm_desgName').value = desgName;
	document.getElementById('paraFrm_desgCode').value = desgCode;
	document.getElementById('paraFrm_competencyCode').value = compCode;
	document.getElementById('paraFrm_competencyName').value =compName  ;
	document.getElementById('paraFrm_compWeightname').value = wtname;
	document.getElementById('paraFrm_categoryCode').value = catCode;
	document.getElementById('paraFrm_category').value =catagory;
	document.getElementById('paraFrm_proLevel').value = proLevel;   
	document.getElementById('oldvalue').value=wtname; 
	
}
function deleteRecord(compCode1,desgCode1,weightage){
	callButton('NA', 'Y', 2);
	var con=confirm('Do you want to delete the record(s) ?');
	if(con){
	
	document.getElementById('oldvalue').value=weightage; 
	
		document.getElementById("paraFrm").action="CompDesigMapping_delete.action?compCode="+compCode1+"&desgCode="+desgCode1+"";
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").submit();
	}
}
function validate(){
 
	if(!validateFields()){
			return false;
	} else {
		document.getElementById("paraFrm").action="CompDesigMapping_addItem.action";
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").submit();
	}
}
function validateFields(){

	var desgName=document.getElementById('paraFrm_desgName').value;
	var compName=document.getElementById('paraFrm_competencyName').value;
	var level=document.getElementById('paraFrm_proLevel').value;
	var wtname=document.getElementById('paraFrm_compWeightname').value;
	if(desgName==""){
		alert("Please select Designation");
		return false;
	}	
	if(compName==""){
		alert("Please select Competency");
		return false;
	}
	if(wtname==""){
		alert("Please select Competency Weightage"); 
		return false;
	}	
	   
	if(level==""){
		alert("Please select Proficiency");
		return false;
	}	
	return true;			
}
function clearFields(){
	document.getElementById('paraFrm_proLevel').value="";
	javascript:callsF9(500,325,'CompDesigMapping_f9compAction.action');
}
 
function checkWeightage(){ 
	var rowCount=document.getElementById('rowNum').value;
	alert(rowcount);
	var totalWeightage=0;
	for(var i=0;i<=eval(rowCount);i++)
	{
		totalWeightage = eval(eval(totalWeightage)+eval(document.getElementById("weightNameIt"+i).value)); 
		alert(totalWeightage);
	}
  
}
checkWeightage();

</script>