 <%@ taglib prefix="s" uri="/struts-tags"%>
 <%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var policyNum = new Array();
</script>
<s:form action="EmpLic" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="updatePolicyFlag" value="false"/>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" valign="bottom" class="txt"><%@ include file="hrmHeader.jsp"%></td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">L.I.C.</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
	
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"> <s:if test="%{el.viewFlag}">
							<input type="button" class="search" value="Search "
								onclick="javascript:callsF9(500,325,'EmpLic_f9licEmp.action');" />
						</s:if>
						</td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		
		
		<!-- <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<s:if
								test="%{el.generalFlag}">
							</s:if> <s:else><s:if test="%{el.viewFlag}">
					<td  width="78%"><input type="button" class="search"
							onclick="javascript:callsF9(500,325,'EmpLic_f9empaction.action');"
							value="    Search " /></td></s:if>
							</s:else>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>-->
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
					
						<tr>
							<td width="25%" colspan="1" ><label name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<font color="red">*</font></td>
							<td width="75%" colspan="3"><s:hidden
								name="empId" theme="simple"  /><s:textfield
								readonly="true" name="empToken" theme="simple"  
								size="10" /><s:textfield readonly="true" name="empName" size="70" /></td>
							</tr>
						<tr>
							<td width="24%" colspan="1" class="formtext"><label name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td width="30%" colspan="1"><s:textfield name="empCent" size="30"
								readonly="true" /></td>

							<td width="17%" colspan="1" class="formtext"><label	name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td width="35%" colspan="1"><s:textfield name="empRank" size="30" readonly="true" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	<tr>
							<td colspan="5" class="text_head"><strong
								class="form_text">L.I.C. Particulars </strong></td>
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
							<td width="24%" colspan="1"><label name="licName" id="licName" ondblclick="callShowDiv(this);"><%=label.get("licName")%></label>
							:<font color="red">*</font></td><s:hidden
								name="licIdHead" theme="simple"  />
							<td width="30%" colspan="1"><s:textfield
								label="%{getText('licName')}" name="licName" maxlength="50"
								size="30" onkeypress="return alphanumericsonly(this)" /></td>

							<td colspan="1" width="17%"><label
								name="licPolNo" id="licPolNo" ondblclick="callShowDiv(this);"><%=label.get("licPolNo")%></label>
							:<font color="red">*</font></td>
							<td width="35%"><s:textfield
								label="%{getText('licPolicyNo')}" name="licPolicyNo"
								maxlength="20" size="30" /></td>
						</tr>
						<tr>
							<td width="24%" colspan="1"><label
								name="insAmt" id="insAmt" ondblclick="callShowDiv(this);"><%=label.get("insAmt")%></label> :</td>
							<td width="25%" colspan="1"><s:textfield
								label="%{getText('insuranceAmt')}" theme="simple"
								name="insuranceAmt" maxlength="10" size="30"
								onkeypress="return numbersWithDot()" /></td>
							<td width="17%" colspan="1"><label
								name="premAmt" id="premAmt" ondblclick="callShowDiv(this);"><%=label.get("premAmt")%></label>
							:<font color="red">*</font></td>
							<td width="25%" colspan="1"><s:textfield
								label="%{getText('licPremiumAmt')}" theme="simple"
								name="licPremiumAmt" onkeypress="return numbersWithDot()" maxlength="10" size="30"
								 /></td>
						</tr>

						<tr>
							<td width="24%" colspan="1"><label name="stDt" id="stDt" ondblclick="callShowDiv(this);"><%=label.get("stDt")%></label>
							:</td>
							<td width="25%" colspan="1">
							<s:textfield label="%{getText('licStDate')}" theme="simple" name="licStDate"
										 size="30" onkeypress="return numbersWithHiphen();" maxlength="10"/>
						    <s:a href="javascript:NewCal('paraFrm_licStDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"	align="absmiddle" class="iconImage" height="16" width="16">
							</s:a></td>

							<td width="17%" colspan="1"><label
								name="endDt" id="endDt" ondblclick="callShowDiv(this);"><%=label.get("endDt")%></label>
							:</td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
								label="%{getText('licEndDt')}" theme="simple" name="licEndDt" size="30" onkeypress="return numbersWithHiphen();" maxlength="10"/>
							<s:a href="javascript:NewCal('paraFrm_licEndDt','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									align="absmiddle" class="iconImage" height="16" width="16">
							</s:a></td>

						</tr>

						<tr>
							<td width="25%" colspan="1"><label
								name="taxEx" id="taxEx" ondblclick="callShowDiv(this);"><%=label.get("taxEx")%></label> :</td>
							<td width="25%" colspan="1"><s:select name="taxExempt" cssStyle="width:175" headerKey="N" headerValue="No"
								list="#{'Y':'Yes'}" onchange="showDebitExmpt();"/></td>
							
							<td width="25%" colspan="1" id="debitExemption1"><label
								name="licUndSec" id="licUndSec" ondblclick="callShowDiv(this);"><%=label.get("licUndSec")%></label> :<font color="red">*</font>
							
							
							<td width="25%" colspan="1" id="debitExemption" nowrap="nowrap"><s:hidden name="invCode"/><s:textfield
								name="invName" size="30"
								readonly="true" /><img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'EmpLic_f9debitExempt.action');"></td>
							
					 </tr>
					
					 
					 <tr>
							<td width="16%" class="formtext" nowrap="nowrap"><label
								name="licDedUndDeb" id="licDedUndDeb" ondblclick="callShowDiv(this);"><%=label.get("licDedUndDeb")%></label><font color="red">*</font> :</td>
							<td width="25%" colspan="1"><s:hidden name="debitCode"/><s:textfield
								name="debitName" size="30"
								readonly="true" /><img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'EmpLic_f9debitHead.action');"></td>
								
							<td width="25%" colspan="1">
							<label name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
							<td width="25%" colspan="1">
								<s:select name="licStatus" cssStyle="width:175" headerKey="A" headerValue="Active"	list="#{'D':'Deactive'}"/></td>
								
					 </tr>
					
					 </table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><s:if test="%{el.insertFlag}">
				<s:submit cssClass="add" action="EmpLic_insertPolicyDet"
					value="   Insert " onclick="return formValidate('insert');" />
			</s:if><s:else></s:else> <s:if test="%{el.updateFlag}">
				<s:submit cssClass="edit" action="EmpLic_updatePolicyDet"
					value="  Update " onclick="return formValidate('update');" />
			</s:if><s:else></s:else> <s:if test="%{el.generalFlag}">
			</s:if> <s:else>
				<input type="button" class="reset" value="    Reset" theme="simple" onclick="callReset();" />
			</s:else></td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" id="tblLic">
					
						<tr>
							<td width="3%" height="36" class="formth" nowrap="nowrap"><label name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							<td width="10%" height="36" class="formth"><label name="licName" id="licName1" ondblclick="callShowDiv(this);"><%=label.get("licName")%></label></td>
							<td width="10%" height="36" class="formth"><label name="licPolNo" id="licPolNo1" ondblclick="callShowDiv(this);"><%=label.get("licPolNo")%></td>
							<td width="8%" height="36" class="formth"><label name="insAmt" id="insAmt1" ondblclick="callShowDiv(this);"><%=label.get("insAmt")%></td>
							<td width="8%" height="36" class="formth"><label name="premAmt" id="premAmt1" ondblclick="callShowDiv(this);"><%=label.get("premAmt")%></td>
							<td width="10%" height="36" class="formth"><label name="stDt" id="stDt1" ondblclick="callShowDiv(this);"><%=label.get("stDt")%></td>
							<td width="10%" height="36" class="formth"><label name="endDt" id="endDt1" ondblclick="callShowDiv(this);"><%=label.get("endDt")%></td>
							<td width="5%" height="36" class="formth"><label name="taxEx" id="taxEx1" ondblclick="callShowDiv(this);"><%=label.get("taxEx")%></td>
							<td width="10%" height="36" class="formth"><label	name="licUndSec" id="licUndSec1" ondblclick="callShowDiv(this);"><%=label.get("licUndSec")%></label></td>
							<td width="10%" height="36" class="formth"><label name="licDedUndDeb" id="licDedUndDeb1" ondblclick="callShowDiv(this);"><%=label.get("licDedUndDeb")%></label></td>
							<td width="10%" height="36" class="formth"><label name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
							<td width="16%" height="36" class="formth">Edit|Delete</td>
							<%int i=0; %>
							<%int cnt = 0;%>
							<s:iterator value=" licList" status="stat">
								<tr>
							<td width="3%" class="sortableTD" align="center"><%=++i %></td>
							<td width="10%" class="sortableTD"><input type="hidden" name="licId" value="<s:property value="licId"/>"><s:property	value="hLicName" /><s:hidden name="hLicName"/></td>
							<td width="10%" class="sortableTD"><s:property value="hPolicyNo" /><s:hidden name="hPolicyNo" id='<%="hPolicyNo"+cnt%>'/></td>
							<script type="text/javascript">
							
							policyNum[<%=cnt%>] = document.getElementById('hPolicyNo'+<%=cnt%>).value;
							
							</script>
							<td width="8%" class="sortableTD">&nbsp;<s:property value="hInsAMt" /><s:hidden name="hInsAMt" /></td>
							<td width="8%" class="sortableTD">&nbsp;<s:property value="hPremAMt" /><s:hidden name="hPremAMt"/></td>
							<td width="10%" class="sortableTD" nowrap="nowrap">&nbsp;<s:property value="hStartDate"/><s:hidden name="hStartDate"/></td>
							<td width="10%" class="sortableTD" nowrap="nowrap">&nbsp;<s:property value="hEndDate" /><s:hidden name="hEndDate"/></td>
							<td width="5%" class="sortableTD" align="center">&nbsp;<s:property	value="hTaxEx" /><s:hidden name="hTaxEx"/></td>
							<td width="10%" class="sortableTD">&nbsp;<s:property	value="hDebitExempt" /><s:hidden name="hDebitExempt"/><input type="hidden" name="hDebitExemptCode" value="<s:property	value="hDebitExemptCode" />"/></td>
							<td width="10%" class="sortableTD">&nbsp;<s:property	value="hDebitName" /><s:hidden name="hDebitName"/><input type="hidden" name="hDebitCode" value="<s:property	value="hDebitCode" />"/></td>
							<td width="10%" class="sortableTD">&nbsp;
								<s:property	value="statusIt" />
								<s:hidden name="statusIt"/>
							</td>
							<td width="12%" class="sortableTD" nowrap="nowrap">&nbsp;
								<s:if test="%{el.updateFlag}">
									 <input type="button" class="rowEdit"
										    onclick="callForEdit('<s:property value="licId"/>','<%=i%>',
																 '<s:property	value="hLicName" />','<s:property	value="hPolicyNo" />','<s:property value="hInsAMt" />','<s:property value="hPremAMt" />',
																 '<s:property	value="hStartDate" />','<s:property value="hEndDate" />','<s:property	value="hTaxEx" />',
																 '<s:property	value="hDebitExempt" />','<s:property	value="hDebitExemptCode" />','<s:property	value="hDebitName" />','<s:property	value="hDebitCode" />',
																 '<s:property	value="statusIt" />')" />
								</s:if> 
								<s:if test="%{el.deleteFlag}">|
										<input type="button" class="rowDelete"	onclick="callDelete('<s:property value="licId"/>','<%=i%>')" />
								</s:if>
							</td>
							</tr>
								<%++cnt;%>
							</s:iterator>
							
					</table>
					<s:hidden name="paraId" />
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br />


</s:form>

<script>
showDebitExmpt();

function callReset(){
	document.getElementById("paraFrm").action="EmpLic_reset.action";
	document.getElementById("paraFrm").submit();

}

  function callForEdit(licId,srNo,licName,policyNo,insAMt,premAmt,startDt,endDt,hTaxEx,debitExemption,exemptionCode,debitName,debitCode,statIt){
	   	alert("asdasjdlaskjd"+statIt);
	   	document.getElementById("paraFrm_updatePolicyFlag").value="true";
	   	document.getElementById("paraFrm_licName").value=licName;
	  	document.getElementById("paraFrm_licPolicyNo").value=policyNo;
	  	document.getElementById("paraFrm_insuranceAmt").value=insAMt;
	  	document.getElementById('paraFrm_paraId').value=srNo;
	  	document.getElementById('paraFrm_licIdHead').value=licId;
	  	document.getElementById("paraFrm_licPremiumAmt").value=premAmt;
	  	document.getElementById("paraFrm_licStDate").value=startDt;
	  	document.getElementById("paraFrm_licEndDt").value=endDt;
	  	if(hTaxEx=="Yes"){
	  	       document.getElementById("paraFrm_taxExempt").value="Y";
	     }else if(hTaxEx=="No"){
	         document.getElementById("paraFrm_taxExempt").value="N";
	     }else{
	          document.getElementById("paraFrm_taxExempt").value="";
	    }
	     if(hTaxEx=="Yes"){
	      	  document.getElementById('debitExemption1').style.display='';
     		  document.getElementById('debitExemption').style.display='';
    	 }else{
    	      document.getElementById('debitExemption1').style.display='none';
     		  document.getElementById('debitExemption').style.display='none';
      	  }
      	
	    document.getElementById('paraFrm_invName').value=debitExemption;
	    document.getElementById('paraFrm_invCode').value=exemptionCode;
	    document.getElementById('paraFrm_debitName').value=debitName;
	    document.getElementById('paraFrm_debitCode').value=debitCode;
	    if(statIt=="Active"){
	  	    document.getElementById("paraFrm_licStatus").value="A";
	    }else if(statIt=="Deactive"){
	        document.getElementById("paraFrm_licStatus").value="D";
	    }else{
	        document.getElementById("paraFrm_licStatus").value="";
	    }
	   
	   
   }
   
   
   function callDelete(id,id1){
   
        var conf=confirm("Are you sure to delete this record ? ");  
        if(conf) {
   	   	document.getElementById("paraFrm").action="EmpLic_delete.action";
	   	document.getElementById('paraFrm_licIdHead').value=id;
	  	document.getElementById("paraFrm").submit();
   }
   }
	
	
	function formValidate(type) {
			var licIdHead = document.getElementById('paraFrm_licIdHead').value;
			if(type=="update"){
				if(licIdHead ==""){
					alert("You can't Update.Please Insert");
					return false;
				}
			}else{
				if(licIdHead !=""){
					alert("You can't Insert.Please Update");
					return false;
				}
			}
			
			var policyNo=trim(document.getElementById("paraFrm_licPolicyNo").value);
			document.getElementById("paraFrm_licPolicyNo").value = policyNo;
	       	var emp=document.getElementById("paraFrm_empName").value;
	     	var debit=trim(document.getElementById("paraFrm_debitCode").value);
	     	var lic=trim(document.getElementById("paraFrm_licName").value);
	     	document.getElementById("paraFrm_licName").value = lic;
	     	var premAmt=document.getElementById("paraFrm_licPremiumAmt").value;
	     	var insurAmt=document.getElementById("paraFrm_insuranceAmt").value;
	     	document.getElementById("paraFrm_licPremiumAmt").value = premAmt;
	     	var taxExmpt=trim(document.getElementById("paraFrm_taxExempt").value);
	     	var invest=trim(document.getElementById('paraFrm_invName').value);
            var stDt=trim(document.getElementById("paraFrm_licStDate").value);
            var endDt=trim(document.getElementById("paraFrm_licEndDt").value);
		
  		
  						
	     		
		  			if(emp=="") {
		  				
		  			    alert("Please select the "+document.getElementById('employee').innerHTML.toLowerCase()+".");
		  			    document.getElementById('paraFrm_empName').focus();
		  			    return false;
		  			   
		  			}
		  			
		  			if(lic==""){
		  			     alert("Please enter the "+document.getElementById('licName').innerHTML+".");
		  			     document.getElementById('paraFrm_licName').focus();
		  			     return false;
		  			
		  			}
		  			
		  			
		  			if(policyNo==""){
		  				 alert("Please enter the "+document.getElementById('licPolNo').innerHTML);
		  				 document.getElementById('paraFrm_licPolicyNo').focus();
		  			     return false;
		  			
		  			}
		  			
		  			if(!(valCTC('paraFrm_insuranceAmt','insAmt'))){
		  				return false;
		  			
		  			}
		  			if(premAmt==""){
		  				alert("Please enter the "+document.getElementById('premAmt').innerHTML.toLowerCase()+".");
		  				document.getElementById('paraFrm_licPremiumAmt').focus();
		  				return false; 
		  			
		  			}
  					if(eval(premAmt)>eval(insurAmt)){
  						alert("Premium Amount should not be greater than Insurance Amount.");
  						document.getElementById('paraFrm_licPremiumAmt').value="";
		  				document.getElementById('paraFrm_licPremiumAmt').focus();
		  				return false; 
  					}
		  			
		  			if(!(valCTC('paraFrm_licPremiumAmt','premAmt'))){
	  						return false;
	  				}
		  			
	  				if(taxExmpt=="Y"){
	  				  if(invest==""){
	  				   alert("Please select "+document.getElementById('licUndSec').innerHTML+".");
	  				   document.getElementById('paraFrm_invName').focus();
		  			   return false;
	  				  }
	  				
	  				}
	  				
	  				if(debit==""){
	  				   alert("Please select "+document.getElementById('licDedUndDeb').innerHTML+".");
	  				   document.getElementById('paraFrm_debitName').focus();
		  			   return false;
	  				
	  				}
	  				
	  				
  			
  			if(stDt!=""){
  			
		  			if(!validateDate('paraFrm_licStDate',"stDt")){
					return false;
					}
		  			
		  	}
		  	if(endDt!=""){
		  			if(!validateDate('paraFrm_licEndDt',"endDt")){
					return false;
		  	}		
		  		
  			}
			  			
			  			if(stDt!="" && endDt!=""){
				  			  if(!dateDifferenceEqual(stDt, endDt, "paraFrm_licEndDt", "stDt", "endDt")){
								return false;
								}
			  			}  
			  			
			  			
			  			
				
			var updtFlg=document.getElementById("paraFrm_updatePolicyFlag").value;
			var para=document.getElementById("paraFrm_paraId").value;
			
			var flag=false;  			
					
			  	 if(updtFlg=="true"){ 
	     
						     for(var i = 0; i <policyNum.length; i++)
						        {
						     
						          if(para-1!=i){
						            if(policyNo==policyNum[i]){
						        	    	flag=true;
						        
						               }
						        }
						                   
						        }
					  		 	if(flag){
									alert(document.getElementById('licPolNo').innerHTML+" "+policyNo +" already exists.");  			
					  				document.getElementById('paraFrm_licPolicyNo').focus();
					  				return false;
					  			
					  			}
						       			
  		}else{
		  		
			  		for(var i = 0; i <policyNum.length; i++)
				        {
				       
				        if(policyNo==policyNum[i]){
				        		flag=true;
				        
				              }
				        
				                   
				        }
				        
				        if(flag){
							alert(document.getElementById('licPolNo').innerHTML+" "+policyNo +" already exists.");  			 			
			  				document.getElementById('paraFrm_licPolicyNo').focus();
					  				return false;
			  			
			  			}
			  			
  		
  		}	
			  			
  		}


function showDebitExmpt(){
 
  
   if(document.getElementById('paraFrm_taxExempt').value=="Y"){
   		
     	document.getElementById('debitExemption').style.display='';
     	document.getElementById('debitExemption1').style.display='';
     }else{
     	document.getElementById('debitExemption').style.display='none';
     	document.getElementById('debitExemption1').style.display='none';
     	document.getElementById('paraFrm_invCode').value="";
   		document.getElementById('paraFrm_invName').value="";
     
     }
         
     
  }
  
  
  	function valCTC(ctcfieldname,ctclabelname)
	{
	
	var amount=document.getElementById(ctcfieldname).value;
	var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();
	
		
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only numbers are allowed in "+amountlabel+" field.");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		 
		 }	
		 }
		  return true;
		 }
</script>
<script type="text/javascript"	src="../pages/common/datetimepicker.js"></script>