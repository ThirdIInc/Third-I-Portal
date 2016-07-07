<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PFParameter" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	
<s:hidden name="show"  />
<s:hidden name="hiddencode" />
<s:hidden name='EPFflag'/>
<s:hidden name='GPFflag'/>
<s:hidden name='VPFflag'/>
<s:hidden name='PFTflag'/>
<s:hidden name='vpfDedTypeHidden'/>
<table class="formbg" width="100%">

		<tr>
			<td colspan="3" width="100%"><%@ include file="pfConfigHeader.jsp"%></td>
		</tr>
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">VPF Parameter</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	 <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="78%">	
	    <s:if test="%{insertFlag}">
			<s:submit   cssClass="add" action="PFParameter_saveVPF" onclick="return callSave();"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="PFParameter_saveVPF" onclick="return callUpdate();"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'PFParameter_f9actionVPF.action');" />
						</s:if>
	<s:submit cssClass="reset" action="PFParameter_resetVPF"
					theme="simple" value="    Reset"  />
        <s:if test="%{deleteFlag}">	
	            <s:submit cssClass="delete" 
					theme="simple" value="    Delete" action="PFParameter_deleteVPF" onclick="return callDelete('paraFrm_pfCodeVPF');"  />
	 </s:if>
	<!-- <s:if test="%{viewFlag}">
				<input  type="button" class="token"
					value=" Report" onclick="callReport('PFParameter_reportVPF.action')" />	
	</s:if>-->
	
	    <s:hidden name="pfCodeVPF"></s:hidden>
	    </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
   
    <tr>
      <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
            <tr>
							<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">VPF Parameter </strong>  </td>
						</tr>
	<tr>
		
		<td  width="20%" colspan="1" ><label  class = "set" name="vpfDeductionType" id="vpfDeductionType" ondblclick="callShowDiv(this);"><%=label.get("vpfDeductionType")%></label>
			<!--PF Debit Code--><font color="red">*</font>:</td>
		<td width="25%" colspan="1"><s:radio name='vpfDedType' list="#{'FX':'Fixed','FR':'Formula'}" onclick="return callDedType(this);"></s:radio> </td>
		<td width='30%' colspan="1" id='dedFormTD'><label  class = "set" name="vpfDeductionForm" id="vpfDeductionForm" ondblclick="callShowDiv(this);"><%=label.get("vpfDeductionForm")%></label>
		<font color="red">*</font>:</td>
		<td width='30%' colspan="2" id='dedFormTD1' nowrap="nowrap" ><s:textfield readonly="true" theme="simple" name="deductionFormVPF"  maxlength="25"  />
			<input type="button" class="token" name="formCalc4" value=Calculator id='paraFrm_formCalc4'
				onclick="callFormulaBuilder('paraFrm_deductionFormVPF');"/></td>
		
	</tr>
	<tr>
		
		<td  width="20%" colspan="1" ><label  class = "set" name="pfparam.PFdebiCodeVpf" id="pfparam.PFdebiCodeVpf" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFdebiCodeVpf")%></label>
			<!--PF Debit Code--><font color="red">*</font>:</td>
		<td width="25%" colspan="1"><s:hidden name="debitCodeVPF"></s:hidden><s:textfield name="debitNameVPF" readonly="true"></s:textfield>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9debitActionVPF.action'); ">
			</td>
		<td width='30%' colspan="1"><label  class = "set" name="pfparam.maxLimitOFDedVPF" id="pfparam.maxLimitOFDedVPF" ondblclick="callShowDiv(this);"><%=label.get("pfparam.maxLimitOFDedVPF")%></label>
		<font color="red">*</font>:</td>
		<td width='30%' colspan="2" nowrap="nowrap" ><s:textfield readonly="true" theme="simple" name="maxLimitOFDedVPF"  maxlength="25"  />
			<input type="button" class="token" name="formCalc3" value=Calculator id='paraFrm_formCalc3'
				onclick="callFormulaBuilder('paraFrm_maxLimitOFDedVPF');"/></td>
		
	</tr>
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
   
   


   
    
   
    
  </table></td></tr></table>
  

	</s:form>

		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">
document.getElementById('VPFtabId').className= "on";




function callSave()
 {
		if(!document.getElementById('paraFrm_pfCodeVPF').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 }	
	var fieldName ;
	var lableName ;
	var badflag ;
	if(document.getElementById('paraFrm_vpfDedTypeHidden').value=='FR'){
	
	 	fieldName = ["paraFrm_deductionFormVPF","paraFrm_debitNameVPF","paraFrm_maxLimitOFDedVPF"];
		lableName = ["vpfDeductionForm","pfparam.PFdebiCodeVpf","pfparam.maxLimitOFDedVPF"];
		badflag = ["enter","select","enter"];
	}else{
	
		fieldName = ["paraFrm_debitNameVPF","paraFrm_maxLimitOFDedVPF"];
		lableName = ["pfparam.PFdebiCodeVpf","pfparam.maxLimitOFDedVPF"];
		badflag = ["select","enter"];
	}
	   if(!validateBlank(fieldName,lableName,badflag))
          return false; 
}
 function callUpdate(){
 	var fieldName ;
	var lableName ;
	var badflag ;
	if(document.getElementById('paraFrm_vpfDedTypeHidden').value=='FR'){
	
	 	fieldName = ["paraFrm_deductionFormVPF","paraFrm_debitNameVPF","paraFrm_maxLimitOFDedVPF"];
		lableName = ["vpfDeductionForm","pfparam.PFdebiCodeVpf","pfparam.maxLimitOFDedVPF"];
		badflag = ["enter","select","enter"];
	}else{
	
		fieldName = ["paraFrm_debitNameVPF","paraFrm_maxLimitOFDedVPF"];
		lableName = ["pfparam.PFdebiCodeVpf","pfparam.maxLimitOFDedVPF"];
		badflag = ["select","enter"];
	}
 		if(document.getElementById('paraFrm_pfCodeVPF').value==""){
 			alert("Please select the record to update");
 			return false;
 		}else {
 			    if(!validateBlank(fieldName,lableName,badflag))
          return false; 
        
 		}
 }
 
 function callDedType(obj){
 	//alert(obj.value);
 	if(obj.value=='FX'){
 		document.getElementById('dedFormTD').style.display='none';
 		document.getElementById('dedFormTD1').style.display='none';
 		document.getElementById('paraFrm_deductionFormVPF').value='';
 	}else{
 		document.getElementById('dedFormTD').style.display='';
 		document.getElementById('dedFormTD1').style.display='';
 	}
 	document.getElementById('paraFrm_vpfDedTypeHidden').value=obj.value;
 }
 callOnload();
 function callOnload(){
 //alert(document.getElementById('paraFrm_vpfDedTypeHidden').value);
 	if(document.getElementById('paraFrm_vpfDedTypeHidden').value=='FR'){
 		document.getElementById('dedFormTD').style.display='';
 		document.getElementById('dedFormTD1').style.display='';
 		}
 		else {
 		//alert('inside else');
 			document.getElementById('dedFormTD').style.display='none';
 			document.getElementById('dedFormTD1').style.display='none';
 			document.getElementById('paraFrm_deductionFormVPF').value='';
 			document.getElementById('paraFrm_vpfDedTypeFX').checked=true;
 		}
 }





</script>		
		
