<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalCalendar" validate="true" id="paraFrm"
	theme="simple">
	
	<s:hidden name="appraisalId" />
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    
   
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Import Appraisal</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    
   <s:hidden name="importStartDate"></s:hidden><s:hidden name="importEndDate"></s:hidden>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <s:if test="isApprove"></s:if><s:else>
          <tr>
            
	
	<td  width="60%"><input type="button" class="add" value=" Save "
							onclick="return saveAppraisalFun();" /> 
					<input type="button" class="back" value=" Back "
							onclick="return callBack();" />
							
					</td>
	
	
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr></s:else>
        </table>
          </td>
    </tr>
    <tr>
			<td>
				<fieldset><legend class="legend1"> Import over all appraisal configuration </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
							<tr>
               <td colspan="3" width="100%" ><label  class = "set" name="import.appraisal" id="import.appraisal" ondblclick="callShowDiv(this);"><%=label.get("import.appraisal")%></label> 
               <input type="checkbox"  name="importConfig" class="checkbox" id="importConfig"   onclick="callImport();"></input><s:hidden name="hideImportConfig"/></td>
               </tr>
							<tr>
               <td width="25%"  colspan="1"><label name="select.appraisal.code" class = "set"  id="select.appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("select.appraisal.code")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="importAppraisalCode" readonly="true" size="20"></s:textfield><s:hidden name="importAppraisalID"/>
               		<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" id="ctrlHide"
						onclick="javascript:callsF9(500,325,'AppraisalCalendar_f9actionImportApprCode.action'); "></td>
					<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>				
					</table>
				</fieldset>
			</td>
	</tr>
	<tr>
		<td align="center"><strong class="formhead">OR</strong></td>
		</tr>
	<tr>
			<td>
				<fieldset><legend class="legend1"> Import content wise appraisal configuration </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
					<tr>
               <td colspan="3" width="100%" ><label name="import.content" class = "set"  id="import.content" ondblclick="callShowDiv(this);"><%=label.get("import.content")%></label> 
               <input type="checkbox"  name="importContentConfig" class="checkbox" id="importContentConfig"   onclick="callContentImport();"></input>
               <s:hidden name="hideImportContentConfig"/></td>
               </tr>
					
						<tr>
               		<td width="25%" colspan="1" nowrap="nowrap"><label  class = "set" name="import.partialApprcode" id="import.partialApprcode" ondblclick="callShowDiv(this);"><%=label.get("import.partialApprcode")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="appraisalCodePhase" size="20" readonly="true"></s:textfield><s:hidden name="appraisalIdPhase"/>
               		<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"  id="ctrlHide1"
						onclick="javascript:callsF9(500,325,'AppraisalCalendar_f9actionImportPhases.action'); "></td>
						<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
                <tr>
               		<td width="25%" colspan="1" nowrap="nowrap"><label  class = "set" name="import.phase" id="import.phase" ondblclick="callShowDiv(this);"><%=label.get("import.phase")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><input type="checkbox"  name="importPhaseIdFlag" class="checkbox" id="importPhaseIdFlag"   onclick="callsetPhaseFlag();">
               		<s:hidden name="hiddenimportPhaseIdFlag" id="hiddenimportPhaseIdFlag"/>
               		</td>
               		<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               
               <tr>
               		<td width="25%"  colspan="1" nowrap="nowrap"><label name="import.appraiser"  class = "set"  id="import.appraiser" ondblclick="callShowDiv(this);"><%=label.get("import.appraiser")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><input type="checkbox"  name="importAppriaserFlag" class="checkbox" id="importAppriaserFlag"   onclick="callsetAppraiserFlag();">
               		<s:hidden name="hiddenimportAppriaserFlag" id="hiddenimportAppriaserFlag"/>
               		</td>
               		<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               <tr>
               		<td width="25%"  colspan="1" nowrap="nowrap"><label name="import.template" class = "set"  id="import.template" ondblclick="callShowDiv(this);"><%=label.get("import.template")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><input type="checkbox"  name="importTemplateFlag" class="checkbox" id="importTemplateFlag"   onclick="callsetTemplageFlag();">
               		<s:hidden name="hiddenimportTemplateFlag" id="hiddenimportTemplateFlag"/>
               		</td>
               		<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               <tr>
               		<td width="25%" colspan="1" nowrap="nowrap"><label name="import.rating" class = "set"  id="import.rating" ondblclick="callShowDiv(this);"><%=label.get("import.rating")%></label> :</td>  
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="appraisalCodeRating" size="20" readonly="true"></s:textfield><s:hidden name="appraisalIdRating"/>
               		<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"  id="ctrlHide2"
						onclick="javascript:callsF9(500,325,'AppraisalCalendar_f9actionImportRating.action'); "></td>
						<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
              		
               </tr>					
					</table>
				</fieldset>
			</td>
		</tr>
										
    <!--<tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
               <tr>
               <td colspan="3" width="100%" ><label  class = "set" name="import.appraisal" id="import.appraisal" ondblclick="callShowDiv(this);"><%=label.get("import.appraisal")%></label> 
               <input type="checkbox"  name="importConfig" class="checkbox" id="importConfig"   onclick="callImport();"></input><s:hidden name="hideImportConfig"/></td>
               </tr>
               <tr>
               <td><div id="importDiv"><table border="0" width="100%">
               <tr>
               <td width="25%" class="formtext" colspan="1"><label name="select.appraisal.code" class = "set"  id="select.appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("select.appraisal.code")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="importAppraisalCode" readonly="true" size="20"></s:textfield><s:hidden name="importAppraisalID"/>
               		<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AppraisalCalendar_f9actionImportApprCode.action'); "></td>
					<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               </table></div>
               </td></tr>
               
               <tr>
               <td colspan="3" width="100%" ><label name="import.content" class = "set"  id="import.content" ondblclick="callShowDiv(this);"><%=label.get("import.content")%></label> 
               <input type="checkbox"  name="importContentConfig" class="checkbox" id="importContentConfig"   onclick="callContentImport();"></input>
               <s:hidden name="hideImportContentConfig"/></td>
               </tr>
               <tr>
               <td><div id="importContentDiv"><table border="0" width="100%">
               <tr>
               		<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label  class = "set" name="import.partialApprcode" id="import.partialApprcode" ondblclick="callShowDiv(this);"><%=label.get("import.partialApprcode")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="appraisalCodePhase" size="20" readonly="true"></s:textfield><s:hidden name="appraisalIdPhase"/>
               		<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AppraisalCalendar_f9actionImportPhases.action'); "></td>
						<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
                <tr>
               		<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label  class = "set" name="import.phase" id="import.phase" ondblclick="callShowDiv(this);"><%=label.get("import.phase")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><input type="checkbox"  name="importPhaseIdFlag" class="checkbox" id="importPhaseIdFlag"   onclick="callsetPhaseFlag();">
               		<s:hidden name="hiddenimportPhaseIdFlag" id="hiddenimportPhaseIdFlag"/>
               		</td>
               		<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               
               <tr>
               		<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label name="import.appraiser"  class = "set"  id="import.appraiser" ondblclick="callShowDiv(this);"><%=label.get("import.appraiser")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><input type="checkbox"  name="importAppriaserFlag" class="checkbox" id="importAppriaserFlag"   onclick="callsetAppraiserFlag();">
               		<s:hidden name="hiddenimportAppriaserFlag" id="hiddenimportAppriaserFlag"/>
               		</td>
               		<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               <tr>
               		<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label name="import.template" class = "set"  id="import.template" ondblclick="callShowDiv(this);"><%=label.get("import.template")%></label> :</td>
               		<td width="25%" colspan="1" nowrap="nowrap"><input type="checkbox"  name="importTemplateFlag" class="checkbox" id="importTemplateFlag"   onclick="callsetTemplageFlag();">
               		<s:hidden name="hiddenimportTemplateFlag" id="hiddenimportTemplateFlag"/>
               		</td>
               		<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
               </tr>
               <tr>
               		<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><label name="import.rating" class = "set"  id="import.rating" ondblclick="callShowDiv(this);"><%=label.get("import.rating")%></label> :</td>  
               		<td width="25%" colspan="1" nowrap="nowrap"><s:textfield name="appraisalCodeRating" size="20" readonly="true"></s:textfield><s:hidden name="appraisalIdRating"/>
               		<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AppraisalCalendar_f9actionImportRating.action'); "></td>
						<td width="25%" class="formtext" colspan="1"></td>
               		<td width="25%" colspan="1" nowrap="nowrap"></td>
              		
               </tr>
              
               </table></div>
               </td></tr>
    
            </table></td>
          </tr>
      </table></td>
    </tr>
    
  

	-->
	 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <s:if test="isApprove"></s:if><s:else>
          <tr>
            
	
	<td  width="60%"><input type="button" class="add" value=" Save "
							onclick="return saveAppraisalFun();" /> 
					<input type="button" class="back" value=" Back "
							onclick="return callBack();" />
							
					</td>
	
	
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr></s:else>
        </table>
          </td>
    </tr>
	
	</table>
	</s:form>



<script type="text/javascript">
callImport();
callContentImport();
	function callBack(){
		var apprId = document.getElementById('paraFrm_appraisalId').value;
		///alert("apprId=="+apprId);
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="AppraisalCalendar_callForEdit.action?apprId="+apprId;
		document.getElementById('paraFrm').submit();
	}
	
	function callImport(){
	try{
			if(document.getElementById('importConfig').checked ==true){
			   	document.getElementById("importConfig").value='Y';
			   	document.getElementById("paraFrm_hideImportConfig").value='Y';
			   	document.getElementById("ctrlHide").style.display ='';
			  	document.getElementById('paraFrm_importAppraisalCode').readOnly = false;
				document.getElementById('paraFrm_importAppraisalCode').style.background = '';
			   	document.getElementById("importContentConfig").value='N';
			   	document.getElementById("importContentConfig").checked=""
			   	document.getElementById("paraFrm_hideImportContentConfig").value='N';
			   	document.getElementById("paraFrm_appraisalCodePhase").value="";
				document.getElementById("paraFrm_appraisalIdPhase").value=""
				document.getElementById("importPhaseIdFlag").checked=""
				document.getElementById("hiddenimportPhaseIdFlag").value="N"
				document.getElementById("importAppriaserFlag").checked=""
				document.getElementById("hiddenimportAppriaserFlag").value="N"
				document.getElementById("importTemplateFlag").checked=""
				document.getElementById("hiddenimportTemplateFlag").value="N"
				document.getElementById("paraFrm_appraisalCodeRating").value="";
				document.getElementById("paraFrm_appraisalIdRating").value="";
				document.getElementById("ctrlHide1").style.display ='none';
				document.getElementById("ctrlHide2").style.display ='none';
				
				document.getElementById("paraFrm_appraisalCodePhase").style.background = '#F2F2F2';
			  	document.getElementById("importPhaseIdFlag").disabled=true;
			  	document.getElementById("importAppriaserFlag").disabled=true;
			  	document.getElementById("importTemplateFlag").disabled=true;
			  	document.getElementById("paraFrm_appraisalCodeRating").style.background = '#F2F2F2';
			  	
			  	
		  	  }else  {
		  	  	document.getElementById("ctrlHide").style.display ='none';
		  	  	document.getElementById("paraFrm_importAppraisalCode").value="";
		  	  	document.getElementById('paraFrm_importAppraisalCode').readOnly = true;
				document.getElementById('paraFrm_importAppraisalCode').style.background = '#F2F2F2';
			  	document.getElementById("importConfig").value='N';
			  	document.getElementById("paraFrm_hideImportConfig").value='N';
				document.getElementById("importContentConfig").value='N';
				document.getElementById("paraFrm_hideImportContentConfig").value='N';
				document.getElementById("importContentConfig").checked="";
		  	 } 
		  	}catch(e)
		{
			alert(e);
		}
}
function callContentImport(){
		try{
			if(document.getElementById('importContentConfig').checked ==true){
			   	document.getElementById("importContentConfig").value='Y';
			   	document.getElementById("paraFrm_hideImportContentConfig").value='Y';
			   	document.getElementById("ctrlHide1").style.display ='';
			   	document.getElementById("ctrlHide2").style.display ='';
			   	document.getElementById('importConfig').checked =false;
			   	document.getElementById("ctrlHide").style.display ='none';
			  	document.getElementById('paraFrm_importAppraisalCode').readOnly = true;
			  	document.getElementById('paraFrm_importAppraisalCode').value='';
				document.getElementById('paraFrm_importAppraisalCode').style.background = '#F2F2F2';
				 document.getElementById('paraFrm_importAppraisalID').value='';
				
			  	document.getElementById("importConfig").value='N';
			  	document.getElementById("paraFrm_hideImportConfig").value='N';
			  	document.getElementById("paraFrm_appraisalCodePhase").style.background = 'white';
			  	document.getElementById("importPhaseIdFlag").disabled=false;
			  	document.getElementById("importAppriaserFlag").disabled=false;
			  	document.getElementById("importTemplateFlag").disabled=false;
			  	document.getElementById("paraFrm_appraisalCodeRating").style.background = 'white';
		  	  }else  {
			  	document.getElementById("importContentConfig").value='N';
			  	document.getElementById("paraFrm_hideImportContentConfig").value='N';
				document.getElementById("importConfig").value='N';
				document.getElementById("paraFrm_hideImportConfig").value='N';
				document.getElementById("paraFrm_importAppraisalID").value="";
				document.getElementById("paraFrm_importAppraisalCode").value="";
				document.getElementById("importConfig").checked="";
				document.getElementById("paraFrm_appraisalCodePhase").style.background = '#F2F2F2';
				document.getElementById("paraFrm_appraisalIdPhase").value=""
				document.getElementById("importPhaseIdFlag").checked=""
				document.getElementById("importPhaseIdFlag").disabled=true;
		
				document.getElementById("hiddenimportPhaseIdFlag").value="N"
				document.getElementById("importAppriaserFlag").checked=""
				document.getElementById("importAppriaserFlag").disabled=true;
				document.getElementById("hiddenimportAppriaserFlag").value="N"
				document.getElementById("importTemplateFlag").checked=""
				document.getElementById("importTemplateFlag").disabled=true;
				document.getElementById("hiddenimportTemplateFlag").value="N"
				document.getElementById("paraFrm_appraisalCodeRating").value="";
				document.getElementById("paraFrm_appraisalIdRating").value="";
				document.getElementById("paraFrm_appraisalCodeRating").style.background = '#F2F2F2';
				document.getElementById("ctrlHide1").style.display ='none';
				document.getElementById("ctrlHide2").style.display ='none';
			} 
		  	}catch(e)
		{
			alert(e);
		}
}
	
function callsetPhaseFlag()
{
	if(document.getElementById("importPhaseIdFlag").checked)
	{
		
		document.getElementById("hiddenimportPhaseIdFlag").value="Y";
	}
	else
	{
		document.getElementById("hiddenimportPhaseIdFlag").value="N";
	}
}
function callsetAppraiserFlag()
{
	if(document.getElementById("importAppriaserFlag").checked)
	{
		document.getElementById("importPhaseIdFlag").checked=true;
		document.getElementById("hiddenimportPhaseIdFlag").value="Y";
		document.getElementById("hiddenimportAppriaserFlag").value="Y";
	}
	else
	{
		
		document.getElementById("hiddenimportAppriaserFlag").value="N";
		 callsetPhaseFlag();
	}
}
function callsetTemplageFlag()
{
	if(document.getElementById("importTemplateFlag").checked)
	{
		document.getElementById("hiddenimportTemplateFlag").value="Y";
		document.getElementById("importPhaseIdFlag").checked=true;
		document.getElementById("importAppriaserFlag").checked=true;
		document.getElementById("hiddenimportPhaseIdFlag").value="Y";
		document.getElementById("hiddenimportAppriaserFlag").value="Y";
	}
	else
	{
		document.getElementById("hiddenimportTemplateFlag").value="N";
		callsetAppraiserFlag();
	}
}

	function validateFields(){
	try {
			if(document.getElementById("paraFrm_hideImportConfig").value=='Y')
			{
				if(document.getElementById("paraFrm_importAppraisalID").value=="")
					{
						alert("Please select Appraisal Code");
						return false;
					}
			}
			if(document.getElementById("paraFrm_hideImportContentConfig").value=='Y')
			{
					if(document.getElementById("paraFrm_appraisalIdPhase").value=="")
					{
						alert("Please select Appraisal Code");
						return false;
					}
				}
		} catch(e) {
		  	alert("Exception in save import appraisal >>>>"+e);
		  	return false;
		  }
		  return true;
	}
	
	
	function saveAppraisalFun() {
		try {
			if(!validateFields()) {
				return false;
			}   
			var con = confirm("Do you really want to save Import Appraisal Details?");
			if (con) {
					var calCode = document.getElementById('paraFrm_appraisalId').value;
					///alert(calCode);
					document.getElementById("paraFrm").action="AppraisalCalendar_saveImportApprDetails.action";
					document.getElementById("paraFrm").submit();
				} else {
					return false;
				}
		  } catch(e) {
		  ///	alert("Exception in processFun >>>>"+e);
		  	return false;
		  }
	}
</script>
