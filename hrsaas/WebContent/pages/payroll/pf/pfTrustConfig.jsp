<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PFParameter" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	
<s:hidden name="show"  />
<s:hidden name="hiddencode" />
<s:hidden name="paraId" />
<s:hidden name='EPFflag'/>
<s:hidden name='GPFflag'/>
<s:hidden name='VPFflag'/>
<s:hidden name='PFTflag'/>
<table class="formbg" width="100%" cellpadding="2" cellspacing="1">

		<tr>
			<td colspan="3" width="100%"><%@ include file="pfConfigHeader.jsp"%></td>
		</tr>
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">PF Trust Parameter</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" >
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
          <tr>
            <td width="78%">	
	    <s:if test="%{insertFlag}">
			<s:submit   cssClass="add" action="PFParameter_savePFT" onclick="return callSave();"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="PFParameter_savePFT" onclick="return callUpdate();"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'PFParameter_f9actionPFT.action');" />
						</s:if>
	<s:submit cssClass="reset" action="PFParameter_resetPFT"
					theme="simple" value="    Reset"  />
        <s:if test="%{deleteFlag}">	
	            <s:submit cssClass="delete" 
					theme="simple" value="   Delete" action="PFParameter_deletePFT" onclick="return callDelete('paraFrm_pfCodePFT');"  />
	 </s:if>
	<!--<s:if test="%{viewFlag}">
				<input  type="button" class="token"
					value=" Report" onclick="callReport('PFParameter_report.action')" />	
	</s:if>-->
	
	    <s:hidden name="pfCodePFT"></s:hidden>
	    </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
   
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
            <tr>
							<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">PF Trust Parameter</strong>  </td>
						</tr>
		
		
		<tr>
			<td width="30%" ><label  class = "set" name="pfparam.edatepft" id="pfparam.edatepft" ondblclick="callShowDiv(this);"><%=label.get("pfparam.edatepft")%></label>
			<font color="red">*</font>:</td>
			<td width="20%" nowrap="nowrap" ><s:textfield theme="simple" name="effDatePFT" size="25" onkeypress="return numbersWithHiphen();" maxlength="12" />
			<s:a href="javascript:NewCal('paraFrm_effDatePFT','DDMMYYYY');">
		 	<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16" ></s:a>
			</td>
			<td  width="25%" ></td>
			<td width="25%" ></td>
	 
		</tr>
		<tr>
			<td width="30%" valign="top" ><label  class = "set" name="pfparam.percOFDed" id="pfparam.percOFDed" ondblclick="callShowDiv(this);"><%=label.get("pfparam.percOFDed")%></label>
			<font color="red">*</font>:</td>
			<td width="20%" ><s:textfield theme="simple" readonly="true" name="percOfDedPFT" size="25"  /></td>
			<td  width="20%" valign="top" ><label  class = "set" name="pfparam.PFdebiCode" id="pfparam.PFdebiCode" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFdebiCode")%></label>
			<!--PF Debit Code--><font color="red">*</font>:</td>
			<td width="30%" valign="top" nowrap="nowrap" ><s:hidden name="debitCodePFT"></s:hidden>
			<s:textfield name="debitNamePFT" readonly="true" size="25" ></s:textfield>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9debitActionPFT.action'); ">
		
			</td>
	 
		</tr>
		<tr>
			<td width="30%" valign="top" ></td>
			<td width="20%" valign="top"><input type="button" class="token" value=Calculator id='paraFrm_formCalc2'	onclick="callFormulaBuilder('paraFrm_percOfDedPFT');"/></td>
			<td  width="25%" valign="top" ></td>
			<td width="25%" valign="top">	
			</td>
	 
		</tr>
		
		<tr >
				<td width="30%" valign="top"><label  class = "set" name="pfparam.maxLimitOFDed" id="pfparam.maxLimitOFDed" ondblclick="callShowDiv(this);"><%=label.get("pfparam.maxLimitOFDed")%></label>
				<font color="red">*</font>:</td>
				<td width="20%" ><s:textfield theme="simple" readonly="true" name="maxLimitOFDed" size="25"  />
				</td>
				<td width="25%" valign="top" ><label  class = "set" name="pfparam.interestRate" id="pfparam.interestRate" ondblclick="callShowDiv(this);"><%=label.get("pfparam.interestRate")%></label>
				<font color="red" valign="top">*</font>:</td>
				<td width="25%" valign="top" ><s:textfield theme="simple" name="interestRate" onkeypress="return numbersWithDot();" size="25" maxlength="5"  />
				</td>
				
		</tr>
		<tr valign="top" >
			<td width="30%" valign="top" ></td>
			<td width="20%" ><input type="button" class="token" value=Calculator id='paraFrm_formCalc2'	onclick="callFormulaBuilder('paraFrm_maxLimitOFDed');"/></td>
			<td  width="25%" valign="top" ></td>
			<td width="25%" valign="top">	
			</td>
	 
		</tr>
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
            			<tr>
							<td colspan="3" class="formhead"><strong
								class="forminnerhead"><label  class = "set" name="pfparam.loanParamSpec" id="pfparam.loanParamSpec" ondblclick="callShowDiv(this);"><%=label.get("pfparam.loanParamSpec")%></label> </strong>  </td>
						</tr>
		<tr>
			<td width="30%"><label  class = "set" name="pfparam.minLoanLImit" id="pfparam.minLoanLImit" ondblclick="callShowDiv(this);"><%=label.get("pfparam.minLoanLImit")%></label>
			<font color="red">*</font>:</td>
			<td width="20%"><s:textfield theme="simple" name="minLoanLimit" size="25" maxlength="3" onkeypress="return numbersOnly();"  /></td>
			<td width="25%"><label  class = "set" name="pfparam.maxLoanLImit" id="pfparam.maxLoanLImit" ondblclick="callShowDiv(this);"><%=label.get("pfparam.maxLoanLImit")%></label>
			<font color="red">*</font>:</td>
			<td width="25%" ><s:textfield name="maxLoanLimit" onkeypress="return numbersOnly();" size="25" maxlength="3"  ></s:textfield></td>
	
		</tr>
		
		<tr>
				<td width="30%" ><label  class = "set" name="pfparam.loanType" id="pfparam.loanType" ondblclick="callShowDiv(this);"><%=label.get("pfparam.loanType")%></label>
				<font color="red">*</font>:</td>
				<td width="20%" nowrap="nowrap"><s:textfield theme="simple" name="loanTypeName" readonly="true" size="25"  /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9loanTypeAction.action'); "><s:hidden name='loanTypeCode'/>
				</td>
				<td width="25%" ></td>
				<td width="25%" ></td>
		</tr>
		
		<tr>
				<td colspan="5" class="formhead"><strong class="forminnerhead"><label  class = "set" name="pfparam.DefLoanPur" id="pfparam.DefLoanPur" ondblclick="callShowDiv(this);"><%=label.get("pfparam.DefLoanPur")%></label> </strong>  </td>
		</tr>
		<tr>
			<td colspan="3">
			
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
						<tr>
						<td colspan="5" align="right"><input type="button" style="" value=' Add Row' class="add" onclick="return addRow();">&nbsp;<input type="button" value=' Remove' class="delete" onclick="callRemove();"></td>
						</tr>
						
			<tr>
					<td class="formtext" >
					
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						
									
					        <tr>
							
							<td class="formth" align="center"><!--Sr No.  -->
								<label  class = "set" name="pfparam.srno" id="pfparam.srno" ondblclick="callShowDiv(this);"><%=label.get("pfparam.srno")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="pfparam.loanPurpose" id="pfparam.loanPurpose" ondblclick="callShowDiv(this);"><%=label.get("pfparam.loanPurpose")%></label>
							</td>
					        <td class="formth" align="center">
					        	<label  class = "set" name="pfparam.loanEligiblity" id="pfparam.loanEligiblity" ondblclick="callShowDiv(this);"><%=label.get("pfparam.loanEligiblity")%></label>
					        </td>
					        
					        <td class="formth" width="5%" align="center"><!--check box  -->
					        	<input type="checkbox" id='checkAllIds' name='delAddCheckBox' onclick="checkAll();"/>
					        </td>
					        </tr>
					        <%int i=1; int k=0;%>
							<s:iterator value="purposeList">
								<%k=i; %>
								<tr>
									<td width="10%" class="td_bottom_border" align="center"><%=k %></td>
									<td width="40%" align="left" class="td_bottom_border"><s:textfield name='loanPurpose' size="30" id='<%="loanPurpose"+k%>' /><s:hidden name='loanPurposeCode' id='<%="loanPurposeCode"+k%>'/></td>
									<td width="40%" class="td_bottom_border"><s:textarea name='loanEligibility' cols="30" rows="2" id='<%="loanEligibility"+k%>'/></td>
									<td width="10%" class="td_bottom_border" align="center"><input type="checkbox" name='delCheckBoxIterator' id='<%="removeCheckId"+k%>' onclick="confirmCheclAll();" /></td>
								</tr>
								<%k++;i++; %>
							</s:iterator>
							<%i=1; %>
							
					<input type="hidden" id='count' value=<%=k-1%> />
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
              
            </table></td>
          </tr>
      </table></td>
    </tr>
   
  
  </table></td></tr></table>
  

	</s:form>

		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">


var fieldName = ["paraFrm_effDatePFT","paraFrm_percOfDedPFT","paraFrm_debitNamePFT","paraFrm_maxLimitOFDed","paraFrm_interestRate","paraFrm_minLoanLimit","paraFrm_maxLoanLimit","paraFrm_loanTypeName"];
var lableName = ["pfparam.edatepft","pfparam.percOFDed","pfparam.PFdebiCodePft","pfparam.maxLimitOFDed","pfparam.interestRate","pfparam.minLoanLImit","pfparam.maxLoanLImit","pfparam.loanType"];
var badflag = ["select","enter","select","enter","enter","enter","enter","select"];

document.getElementById('PF TrusttabId').className= "on";


function callSave()
 {
		if(!document.getElementById('paraFrm_pfCodePFT').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 }
	   if(!validateBlank(fieldName,lableName,badflag))
          return false;
          
         if(!validateDate('paraFrm_effDatePFT','pfparam.edatepft'))
             return false;	 
       var minLimit = eval(document.getElementById('paraFrm_minLoanLimit').value)* 100000000/100000000;
       var maxLimit = eval(document.getElementById('paraFrm_maxLoanLimit').value)* 100000000/100000000;
       
       if(eval(minLimit) > 100){
       	alert(document.getElementById('pfparam.minLoanLImit').innerHTML+" should be less than or equal to 100");
       	document.getElementById('paraFrm_minLoanLimit').focus();
       	return false;
       }
       if(eval(maxLimit) > 100){
       	alert(document.getElementById('pfparam.maxLoanLImit').innerHTML+" should be less than or equal to 100");
       	document.getElementById('paraFrm_maxLoanLimit').focus();
       	return false;
       }
       if(eval(minLimit) > eval(maxLimit)){
       	alert(document.getElementById('pfparam.minLoanLImit').innerHTML+" should be less than or equal to "+document.getElementById('pfparam.maxLoanLImit').innerHTML);
       	document.getElementById('paraFrm_minLoanLimit').focus();
       	return false;
       }
       if(!checkPurposeList()){
       		return false;
       }
       
}
function checkPurposeList(){
	var noOfRow = eval(document.getElementById('count').value);
       if(noOfRow=='' || noOfRow <=0){
       		alert("Please define loan purpose");
       		return false;
       }else{
       		for(var i=1;i<=eval(noOfRow);i++){
			if(trim(document.getElementById('loanPurpose'+i).value)==""){
				alert('Please enter '+document.getElementById('pfparam.loanPurpose').innerHTML);
				document.getElementById('loanPurpose'+i).focus();
				return false;
			}else if(trim(document.getElementById('loanEligibility'+i).value)==""){
				alert('Please enter '+document.getElementById('pfparam.loanEligiblity').innerHTML);
				document.getElementById('loanEligibility'+i).focus();
				return false;
			}
			else{
			}
		}
       }
       return true;
}
 function callUpdate(){
 		if(document.getElementById('paraFrm_pfCodePFT').value==""){
 			alert("Please select the record to update!");
 			return false;
 		}else {
 			     if(!validateBlank(fieldName,lableName,badflag))
          return false; 
 		}
 		if(!validateDate('paraFrm_effDatePFT','pfparam.edatepft'))
             return false;	 	
 		var minLimit = eval(document.getElementById('paraFrm_minLoanLimit').value)* 100000000/100000000;
       var maxLimit = eval(document.getElementById('paraFrm_maxLoanLimit').value)* 100000000/100000000;
 		if(eval(minLimit) > 100){
       		alert(document.getElementById('pfparam.minLoanLImit').innerHTML+" should be less than or equal to 100");
       		document.getElementById('paraFrm_minLoanLimit').focus();
       		return false;
       }
       if(eval(maxLimit) > 100){
       		alert(document.getElementById('pfparam.maxLoanLImit').innerHTML+" should be less than or equal to 100");
       		document.getElementById('paraFrm_maxLoanLimit').focus();
       		return false;
       }
       if(eval(minLimit) > eval(maxLimit)){
       	alert(document.getElementById('pfparam.minLoanLImit').innerHTML+" should be less than or equal to "+document.getElementById('pfparam.maxLoanLImit').innerHTML);
       	document.getElementById('paraFrm_minLoanLimit').focus();
       	return false;
       }
       if(!checkPurposeList()){
       		return false;
       }
 }
 function addRow(){
 		document.getElementById('paraFrm').action = 'PFParameter_addPurposeRow.action';
 		document.getElementById('paraFrm').submit();
 	}
function callRemove(){
		var noOfRow = document.getElementById('count').value;
		var checkedIds="";
		for(var i=1;i<=eval(noOfRow);i++){
			//alert("checked=="+document.getElementById('removeCheckId'+i).checked+" for i=="+i);
			if(document.getElementById('removeCheckId'+i).checked){
				checkedIds += i+",";
			}
		}
		if(checkedIds==""){
			alert("Please select atleast one record to remove.");
			return false;
		}
		var conf=confirm("Do you really want to remove these records?");
		if(!conf){
			return false;
		}else{
		
		document.getElementById('paraFrm_paraId').value=checkedIds;
 		document.getElementById('paraFrm').action = 'PFParameter_removePurposeRow.action';
 		document.getElementById('paraFrm').submit();
 		}
 	}
 	
 	function checkAll(){
 		var checkedValue = document.getElementById('checkAllIds').checked;
 		var noOfRow = document.getElementById('count').value;
 		for(var i=1;i<=eval(noOfRow);i++){
			document.getElementById('removeCheckId'+i).checked = checkedValue;
		}
 	}
 	function confirmCheclAll(){
 		var checkedValue = true;
 		var noOfRow = document.getElementById('count').value;
 		for(var i=1;i<=eval(noOfRow);i++){
			if(!document.getElementById('removeCheckId'+i).checked) {
				checkedValue = false;
			}
		}
		document.getElementById('checkAllIds').checked= checkedValue;
 	}

	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PFParameter_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
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
   
   	function newRowColor(cell)
   	 {
			cell.className='onOverCell';

	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
	
	
	

</script>		
		
