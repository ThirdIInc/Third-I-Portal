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
<s:hidden name="flag1"/>
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
				          <td width="93%" class="txt"><strong class="text_head">EPF Parameter</strong></td>
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
          <!--
            <td width="78%">	
	    <s:if test="%{insertFlag}">
			<s:submit   cssClass="add" action="PFParameter_save" onclick="return callSave();"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="PFParameter_save" onclick="return callUpdate();"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'PFParameter_f9action.action');" />
						</s:if>
	
      
	<s:if test="%{viewFlag}">
				<input  type="button" class="token"
					value=" Report" onclick="callReport('PFParameter_report.action')" />	
	</s:if>-->
		
		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	    <s:hidden name="pfParam.pfCode"></s:hidden>
	    
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
   
    <tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td>
            	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead">EPF Parameter </strong>  </td>
					</tr>
					<tr>
						<td  width="25%"><label  class = "set" name="pfparam.edate" id="pfparam.edate" ondblclick="callShowDiv(this);"><%=label.get("pfparam.edate")%></label>
						<!--Effective Date-->
						:<font color="red">*</font></td>
						<td width="25%">
						<s:textfield name="pfParam.effDate" theme="simple"  />
					 	<s:a href="javascript:NewCal('paraFrm_pfParam_effDate','DDMMYYYY');">
					 	
					 	<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16" id="ctrlHide" ></s:a></td>
					 	<td colspan="3"></td>
					</tr>
					<tr>
						<td  width="25%"><label  class = "set" name="pfparam.PFdebiCode" id="pfparam.PFdebiCode" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFdebiCode")%></label>
						<!--PF Debit Code--> :<font color="red">*</font></td>
						<td width="25%"><s:hidden name="pfParam.debitCode"></s:hidden>
						<s:textfield name="pfParam.debitName" readonly="true"></s:textfield>	
								<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" id="ctrlHide"
							width="18" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_debitaction.action'); ">		
						</td>
					</tr>
					<tr>
					<td ><label  class = "set" name="pfparam.PFformulea" id="pfparam.PFformulea" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFformulea")%></label>
					<!--PF Formula  --> :<font color="red">*</font></td><td colspan="2">
						<s:textfield 
							theme="simple" name="pfParam.pfFormula"  maxlength="50" readonly="true"  />
						<input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
										onclick="callFormulaBuilder('paraFrm_pfParam_pfFormula');"/>	
					</td>
					</tr>		
					<tr>
						<td  width="25%"><label  class = "set" name="pfparam.percent" id="pfparam.percent" ondblclick="callShowDiv(this);"><%=label.get("pfparam.percent")%></label>
						<!--PF Percentage--> :<font color="red">*</font></td>
						<td width="25%">
						<s:textfield 
							theme="simple" name="pfParam.deduction"  maxlength="6" onkeypress="return checkNumbersWithDot(this);" />	
						</td>
						<!--PF Type-->
						<!--  
						<td width="25%"><label  class = "set" name="pfparam.type" id="pfparam.type" ondblclick="callShowDiv(this);"><%=label.get("pfparam.type")%></label>:
						</td>
						<td width="25%">
						<s:select name="pfParam.pfType"
							list="#{'','CT':'Company Trust','G':'Government'}"/>
					
						</td>	
						-->
					</tr>
			 
				</table>
				</td>
		</tr>
	</table>
	</td>
	</tr>
	
	<tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td>
            	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
    
					<tr>
						<td colspan="2" class="formhead"><strong
							class="forminnerhead">Employee's Contribution </strong>  </td>
						<td colspan="3" class="formhead"><strong
							class="forminnerhead">Employer's Contribution </strong>  </td>
					</tr>
					<tr>
						<td  width="25%"><label  class = "set" name="pfparam.empPF" id="pfparam.empPF" ondblclick="callShowDiv(this);"><%=label.get("pfparam.empPF")%></label>%
						<!--Employee PF %-->:<font color="red">*</font></td>
						<td width="25%"><s:textfield 
							theme="simple" name="pfParam.empPF"  maxlength="6" onkeyup="chkPercentage();" onkeypress="return checkNumbersWithDot(this);"/></td>
						<td  width="25%"><label  class = "set" name="pfparam.pensionFund" id="pfparam.pensionFund" ondblclick="callShowDiv(this);"><%=label.get("pfparam.pensionFund")%></label>
						<!--Pension Fund--> % :<font color="red">*</font></td>
						<td width="25%"><s:textfield 
							theme="simple" name="pfParam.pensionFund" maxlength="6"  onkeyup="chkPercentage();" onkeypress="return checkNumbersWithDot(this);"/></td>			
					</tr>
					<tr>
						<td  width="25%"><label  class = "set" name="pfparam.empfamilyFund" id="pfparam.empfamilyFund" ondblclick="callShowDiv(this);"><%=label.get("pfparam.empfamilyFund")%></label>
						<!--Employee Family PF  --> % :<font color="red">*</font></td>
						<td width="25%"><s:textfield 
							theme="simple" name="pfParam.empFamilyPF"  readonly="true" onkeypress=" return checkNumbersWithDot(this);"/></td>
						<td  width="25%"><label  class = "set" name="pfparam.compFund" id="pfparam.compFund" ondblclick="callShowDiv(this);"><%=label.get("pfparam.compFund")%></label>
						<!--Company Fund-->  % :<font color="red">*</font></td>
						<td width="25%"><s:textfield 
							theme="simple" name="pfParam.compFund" maxlength="6" readonly="true" onkeypress="return checkNumbersWithDot(this);"/></td>
					</tr>
					
				</table>
				</td>
		</tr>
	</table>
	</td>
	</tr>
<!-- Rules Definition -->
	<tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td>
            	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">	
					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead">Rules Definition </strong>  </td>
					</tr>		
					<tr>
						<td  width="25%" colspan="3">1) Deduct PF for Employees having PF emolument
							<s:select name="deductCriteria" headerKey="3"  headerValue=">"
								list="#{'1':'=','2':'<','4':'<=','5':'>='}"
								/> 
							<s:textfield name="pfDedEmolument" theme="simple" 
							onkeypress="return checkNumbersWithDot(this);" maxlength="10"/>
						</td>	
						<td colspan="2"></td>
					</tr>
					<tr>
						<td  width="25%">2) Max Limit check for PF emoluments :</td>
						 <td width="25%"><s:hidden name="noMaxLimitChk"/>
						 	<input type="checkbox" name="eliAllCheck" id="eliAllCheckId" onclick="setEligibleAll()"/>
						 </td>
						 <td colspan="3"></td>
					</tr>					
					<tr id="EmoMaxLimit" style="display: none;">
						<td  width="25%" colspan="2">   Maximum Limit on PF Emoluments
							<s:textfield name="pfEmoMaxLimit" theme="simple" maxlength="10" 
							onkeypress="return checkNumbersWithDot(this);"/> for PF Deduction
						</td>	
					</tr>		
            	</table>
            </td>
          </tr>
         </table>
       </td>
     </tr>
<!-- Administrative Overheads -->
	<tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td>
            	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">	
            					
					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead">Administrative Overheads </strong>  </td>
					</tr>		
					<tr>
						 <td  width="25%"><label  class = "set" name="pfparam.EDLIContribut" id="pfparam.EDLIContribut" ondblclick="callShowDiv(this);"><%=label.get("pfparam.EDLIContribut")%></label>
						 <!--EDLI Employee Contribution  --> %:</td>
						<td width="25%"><s:textfield 
							theme="simple" name="edlicontribution" maxlength="6"  onkeypress="return checkNumbersWithDot(this);"/></td>
					   <td  width="25%"><label  class = "set" name="pfparam.PFAdmin" id="pfparam.PFAdmin" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFAdmin")%></label>
					   <!--PF Admin Charge  --> %:</td>
						<td width="25%"><s:textfield 
							theme="simple" name="pfadmincharge" maxlength="6"  onkeypress="return checkNumbersWithDot(this);"/></td>
					</tr>
					<tr>
			
						<td  width="25%"><label  class = "set" name="pfparam.PFEDLICharge" id="pfparam.PFEDLICharge" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFEDLICharge")%></label>
						<!--PF EDLI Admin Charge  --> %:</td>
						<td width="25%"><s:textfield 
							theme="simple" name="pfedlicharge" maxlength="6" onkeypress="return checkNumbersWithDot(this);"/></td>
					</tr>
					<!--PF Minimum AMt check  --> 
					<!--  
					<tr>
					   <td  width="25%"><label  class = "set" name="pfparam.minumumAmt" id="pfparam.minumumAmt" ondblclick="callShowDiv(this);"><%=label.get("pfparam.minumumAmt")%></label>
					   :</td>
						<td width="25%"><s:textfield theme="simple" name="pfMinAmt" maxlength="6"  onkeypress="return checkNumbersWithDot();"/></td>
						<td  width="25%"></td>
						<td width="25%"></td>
					
					</tr>
					-->
	           </table>
            </td>
          </tr>
      	</table>
      </td>
    </tr>

  <tr>
  <td align="left">
  <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  </td>
  </tr>
  </table></td></tr>

  </table>
  

	</s:form>

		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">

onload();
function checkNumbersWithDot(obj) {
	var count = 0;
	var txtNo = obj.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
}
setEligibleAll();
	
function onload(){
	var noMaxChk=document.getElementById('paraFrm_noMaxLimitChk').value;
	if(noMaxChk=="Y"){
		document.getElementById('eliAllCheckId').checked=true;
	}	
	else{
		document.getElementById('eliAllCheckId').checked=false;
	}	
}

function saveFun(){

		var fieldName = ["paraFrm_pfParam_effDate","paraFrm_pfParam_debitName","paraFrm_pfParam_pfFormula","paraFrm_pfParam_deduction","paraFrm_pfParam_empPF","paraFrm_pfParam_pensionFund","paraFrm_pfParam_debitName","paraFrm_pfParam_empFamilyPF","paraFrm_pfParam_compFund"];
		var lableName = ["pfparam.edate","pfparam.PFdebiCode","pfparam.PFformulea","pfparam.percent","pfparam.empPF","pfparam.pensionFund","pfparam.empfamilyFund","pfparam.compFund"];
		var badflag = ["enter","select","enter","enter","enter","enter","enter","enter"];

       
	   if(!validateBlank(fieldName,lableName,badflag))
          return false; 
       	
       if(!validateDate('paraFrm_pfParam_effDate','pfparam.edate'))
             return false;	   
            if(!checkPercentage('paraFrm_pfParam_deduction',"pfparam.percent"))
             {  return false;	}
             
            if(!checkPercentage('paraFrm_pfParam_empPF',"Employee PF"))
            {return false;}	
           if(!checkPercentage('paraFrm_pfParam_pensionFund',"Pension Fund"))
           { return false; }
           
if(!checkPercentage('paraFrm_edlicontribution',"pfparam.EDLIContribut"))
             {  return false;	}

	if(!checkPercentage('paraFrm_pfadmincharge',"pfparam.PFAdmin"))
             {  return false;	}

	if(!checkPercentage('paraFrm_pfedlicharge',"pfparam.PFEDLICharge"))
             {  return false;	}           

   		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="PFParameter_save.action";
		document.getElementById('paraFrm').submit();
		return true;
}

function resetFun()
{
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="PFParameter_reset.action";
		document.getElementById('paraFrm').submit();
}

function backFun()
{
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="PFParameter_cancel.action";
		document.getElementById('paraFrm').submit();
}
function editFun() {
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="PFParameter_editRecord.action";
	document.getElementById('paraFrm').submit();
}
function deleteFun() {
	var conf = confirm("Do you really want to delete this record?");
 	if(conf) {
	  	document.getElementById('paraFrm_hiddencode').value=document.getElementById('paraFrm_pfParam_pfCode').value;
	   	document.getElementById("paraFrm").action="PFParameter_delete.action";
	   	document.getElementById("paraFrm").target="_self";
	    document.getElementById("paraFrm").submit();
	}
}
function chkPercentage()
{

	var percen= document.getElementById('paraFrm_pfParam_deduction').value;
	var empPf= document.getElementById('paraFrm_pfParam_empPF').value;
	var pensionPf=document.getElementById('paraFrm_pfParam_pensionFund').value;
	
	if(percen==""){
	  alert("Please enter Percentage first");
	  document.getElementById('paraFrm_pfParam_empPF').value='';
	  document.getElementById('paraFrm_pfParam_deduction').focus()=true;
	  
	  return false;
	}
	
	if(empPf!="")
	{
	if(eval(document.getElementById('paraFrm_pfParam_empPF').value)>eval(document.getElementById('paraFrm_pfParam_deduction').value))
	{
	  alert("Employee PF should be lessthan the PF Percentage");
	  document.getElementById('paraFrm_pfParam_empPF').value='';
	  document.getElementById('paraFrm_pfParam_empFamilyPF').value='';
	  return false;
	
	}
	var empfamilyPf = (eval(percen * 100)/100 -eval(empPf *100)/100);
	
	document.getElementById('paraFrm_pfParam_empFamilyPF').value= roundNumber(eval(empfamilyPf),2); //empfamilyPf;

	}
	if(pensionPf!="")
	{
	if(eval(document.getElementById('paraFrm_pfParam_pensionFund').value)>eval(document.getElementById('paraFrm_pfParam_deduction').value))
	{
	  alert("Pension Fund Percentage should be lessthan PF Percentage");
	  document.getElementById('paraFrm_pfParam_pensionFund').value='';
	  document.getElementById('paraFrm_pfParam_compFund').value='';
	  return false;
	
	}
	var compFund = (eval(percen * 100)/100-eval(pensionPf * 100)/100);
	
	document.getElementById('paraFrm_pfParam_compFund').value=roundNumber(compFund,2);

	}
	
	return true;
	
}






function callSave()
 {
		if(!document.getElementById('paraFrm_pfParam_pfCode').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 		}
       
	   if(!validateBlank(fieldName,lableName,badflag))
          return false; 
       	
       if(!validateDate('paraFrm_pfParam_effDate','pfparam.edate'))
             return false;	   
            if(!checkPercentage('paraFrm_pfParam_deduction',"PF Percentage"))
             {  return false;	}
             
            if(!checkPercentage('paraFrm_pfParam_empPF',"Employee PF"))
            {return false;}	
           if(!checkPercentage('paraFrm_pfParam_pensionFund',"Pension Fund"))
           { return false; }
   
		return true;
}
 function callUpdate(){
 		if(document.getElementById('paraFrm_pfParam_pfCode').value==""){
 			alert("Please select the record to update!");
 			return false;
 		}else {
 			    if(!checkMandatory(fieldName,lableName,badflag))
       				 return false;
        
      			 if(!validateDate('paraFrm_pfParam_effDate','PFEffective Date'))
             return false;	   
             
             if(!checkPercentage('paraFrm_pfParam_deduction',"PF Percentage"))
             {  return false;	}
             
            if(!checkPercentage('paraFrm_pfParam_empPF',"Employee PF"))
            {return false;}	
           if(!checkPercentage('paraFrm_pfParam_pensionFund',"Pension Fund"))
           { return false; }
              
		return true;   
 		}
 }


function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}







    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PFParameter_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   


function callForDelete1(id,i)
	   {

	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
   

	function setEligibleAll(){
		if(document.getElementById('eliAllCheckId').checked)
		{
			document.getElementById('EmoMaxLimit').style.display='';
			document.getElementById('paraFrm_noMaxLimitChk').value="Y";
		}
		else
		{
			document.getElementById('EmoMaxLimit').style.display='none';
			document.getElementById('paraFrm_noMaxLimitChk').value="N";
		}
	}

</script>		
		
