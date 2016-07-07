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
				          <td width="93%" class="txt"><strong class="text_head">GPF Parameter</strong></td>
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
			<s:submit   cssClass="add" action="PFParameter_saveGPF" onclick="return callSave();"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="PFParameter_saveGPF" onclick="return callUpdate();"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'PFParameter_f9actionGPF.action');" />
						</s:if>
	<s:submit cssClass="reset" action="PFParameter_resetGPF"
					theme="simple" value="    Reset" />
        <s:if test="%{deleteFlag}">	
	            <s:submit cssClass="delete" 
					theme="simple" value="    Delete" action="PFParameter_deleteGPF" onclick="return callDelete('paraFrm_pfCodeGPF');"  />
	 </s:if>
	<!-- <s:if test="%{viewFlag}">
				<input  type="button" class="token"
					value=" Report" onclick="callReport('PFParameter_reportGPF.action')" />	
	</s:if>-->
		
	    <s:hidden name="pfCodeGPF"></s:hidden>
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
							<td colspan="4" class="formhead"><strong
								class="forminnerhead">GPF Parameter </strong>  </td>
						</tr>
			
		<tr>
			<td width='27%'><label  class = "set" name="pfparam.maxLimitOFDedGPF" id="pfparam.maxLimitOFDedGPF" ondblclick="callShowDiv(this);"><%=label.get("pfparam.maxLimitOFDedGPF")%></label>
				<font color="red">*</font>:</td>
			<td width='27%'><s:textfield theme="simple" readonly="true" name="maxLimitOFDedGPF"  maxlength="25"  />
			<input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
					onclick="callFormulaBuilder('paraFrm_maxLimitOFDedGPF');"/></td>
			<td  width="23%"><label  class = "set" name="pfparam.PFdebiCodeGpf" id="pfparam.PFdebiCodeGpf" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFdebiCodeGpf")%></label>
			<!--PF Debit Code--><font color="red">*</font>:</td>
			<td width="23%"><s:hidden name="debitCodeGPF"></s:hidden>
			<s:textfield name="debitNameGPF" readonly="true"></s:textfield>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9debitActionGPF.action'); ">
		
			</td>
		
		</tr>
		
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
   
    
  </table>
  </td>
  </tr>
  </table>
  

	</s:form>

		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">
document.getElementById('GPFtabId').className= "on";
var fieldName = ["paraFrm_maxLimitOFDedGPF","paraFrm_debitNameGPF"];
var lableName = ["pfparam.maxLimitOFDedGPF","pfparam.PFdebiCodeGpf"];
var badflag = ["enter","select"];


function callSave()
 {
		if(!document.getElementById('paraFrm_pfCodeGPF').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 }
	   if(!validateBlank(fieldName,lableName,badflag))
          return false; 
}
 function callUpdate(){
 		if(document.getElementById('paraFrm_pfCodeGPF').value==""){
 			alert("Please select the record to update");
 			return false;
 		}else {
 			    if(!validateBlank(fieldName,lableName,badflag))
          return false; 
        
 		}
 }
	

</script>		
		
