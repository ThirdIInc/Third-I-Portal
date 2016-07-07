<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var salC1 = new Array();
	var salT1 = new Array();
	var salP1 = new Array();
</script>
<s:form action="FormulaBuilder" id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg" height="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong class="formhead"> 
						<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Formula Builder </strong></td>
					<td width="3%" valign="top" class="txt">
						<div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="70%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%">
							<div align="right"><font color="red">*</font> Indicates	Required</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" class="formbg" cellpadding="0" cellspacing="0" >
				<tr>
					<td width="20%"><label class="set" id="for.name"
						name="for.name" ondblclick="callShowDiv(this);"><%=label.get("for.name")%></label><font
						color="red">*</font>:</td>
					<td><s:hidden name="frmbuildCode" /> <s:textfield
						theme="simple" size="30" name="frmbuildName"
						value="%{frmbuildName}" onkeypress="return alphaNumeric();" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" id="ctrlHide">
						<tr>
							<td width="20%"><label class="set" id="salhead2"
								name="salheadlabel" ondblclick="callShowDiv(this);"><%=label.get("salhead")%></label>
							<font color="red">*</font>:</td>
							<td><s:textfield name="salHead" value="%{salHead}" size="30"
								readonly="true" /><s:hidden name="salCode" /><s:hidden
								name="salprior" /> <s:hidden name="chkEdit" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'FormulaBuilder_f9salaction.action');"/></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><label class="set" id="salarytype" name="salarytype"
								ondblclick="callShowDiv(this);"><%=label.get("salarytype")%></label>:</td>
							<td><s:select name="salType" value="%{salType}"
								list="#{'-1':'--Select--','Fi':'Fixed','Fr':'Formula','Df':'Difference'}"
								onchange="calc();" />&nbsp;<input type="button"
								class="token" name="formCalc"
								value="  Calculator   " id='paraFrm_formCalc'
								onclick="callCalc('paraFrm_salAmt');" align="absmiddle" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><label class="set" id="salAmount" name="salAmount"
								ondblclick="callShowDiv(this);"><%=label.get("salAmount")%></label>
							<font color="red">*</font>:
							<td><s:textfield name="frmBuild.salAmt" value="%{salAmt}"
								size="30" id="paraFrm_salAmt" onkeypress="return numbersOnly();" /></td>
							<td>&nbsp;</td>

						</tr>
						<tr>
							<td width="20%">&nbsp;</td>
							<td width="30%" align="center"><s:submit cssClass="add"
								action="FormulaBuilder_addFormula" theme="simple"
								value="   Add   " onclick="return callAdd();" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="paraFrm_copyBlock" style="display: none;">
					<td width="20%">New Formula <font color="red">*</font>:</td>
					<td colspan="2"  align="left" >
							<s:hidden name="hiddenFormulaId" />
							<s:textfield name="copiedFormulaName" size="31" />&nbsp; <input
								type="button" style="background: #D6EAFD" name="saveFormula"
								value="Save Formula" id='paraFrm_saveFormula'
								onclick="saveCopiedFormula();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5" width="100%">
				<table width="100%" border="0" align="center" class="formbg" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="5" class="formhead"><strong><label class="set" id="salDtl"
							name="salDtl" ondblclick="callShowDiv(this);"><%=label.get("salDtl")%></label></strong></td>
					</tr>
					<tr>
						<td colspan="5" width="100%">
						<table width="100%" border="0" align="center" class="formbg" cellpadding="0" cellspacing="0">
							<tr>
						<td width="10%" class=formth><label class="set"
							id="salCode1" name="salCodeLabel" ondblclick="callShowDiv(this);"><%=label.get("salCode")%></label></td>
						<td width="35%" class=formth><label class="set"
							id="salhead3" name="salheadLabel" ondblclick="callShowDiv(this);"><%=label.get("salhead")%></label></td>
						<td width="20%" class=formth><label class="set"
							id="salarytype2" name="salarytype"
							ondblclick="callShowDiv(this);"><%=label.get("salarytype")%></label></td>
						<td width="23%" class=formth><label class="set"
							id="sallAmount" name="salAmount"
							ondblclick="callShowDiv(this);"><%=label.get("salAmount")%></label></td>
						<td width="18%" class=formth id="ctrlHide"><label class="set"
							id="remove" name="remove" ondblclick="callShowDiv(this);"><%=label.get("remove")%></label>
							<s:hidden name="serial" id="serial" />
						</td>
					</tr>
					
					<%
											int i = 0, cnt = 0, j = 0, salt = 0, salp = 0;
											String len = "", lenp = "";
											int serial = 1;
										%>
					<s:iterator value="frmList" status="stat">
					<tr>
						<td width="5%" class="sortableTD" nowrap="nowrap" align="center"><s:hidden
							name="serial" value='<%=String.valueOf(serial)%>' /> <s:hidden
							name="srNo" value="%{srNo}"></s:hidden> <s:hidden
							name="frmdtlCode" value="%{frmdtlCode}" /> <s:property
							value="salCode1" /><s:hidden name="salCode1"
							value="%{salCode1}" id='<%="sId"+cnt%>' /> <s:hidden
							name="salOrder" value="%{salOrder}" /><s:hidden
							name="salPrior1" id='<%="pId"+salp%>' value="%{salPrior1}" />
						<script>
							salC1[<%=cnt%>] = document.getElementById('sId'+<%=cnt%>).value;
							salP1[<%=salp%>] = document.getElementById('pId'+<%=salp%>).value;
						</script> <s:hidden name="priorCode" /></td>
						<td width="35%" class="sortableTD" nowrap="nowrap"><s:property
							value="salHead1" /><s:hidden name="salHead1"
							value="%{salHead1}" /></td>
						<td width="20%" class="sortableTD" nowrap="nowrap"><s:property
							value="salTy1" /><s:hidden name="salType1"
							value="%{salType1}" id='<%="cId"+salt%>' /><s:hidden
							name="salTy1" value="%{salTy1}" /> <script>
							salT1[<%=salt%>] = document.getElementById('cId'+<%=salt%>).value;
						 </script></td>
						<td width="20%" class="sortableTD" nowrap="nowrap" align="center"><s:property
							value="salAmt1" /><s:hidden name="salAmt1"
							value="%{salAmt1}" /></td>

						<td width="20%" class="sortableTD" align="center" nowrap="nowrap"><input
							type="button" class="edit"
							onclick="callForEdit('<s:property value="srNo"/>','<s:property value="salCode1"/>','<s:property value="salHead1"/>','<s:property value="salType1"/>','<s:property value="salAmt1"/>','<s:property value="salOrder"/>','edit','<%=serial%>')"
							value="    Edit   " /> <input type="button" class="delete"
							onclick="callForEdit('<s:property value="srNo"/>','<s:property value="salCode1"/>','<s:property value="salHead1"/>','<s:property value="salType1"/>','<s:property value="salAmt1"/>','<s:property value="salOrder"/>','del','<%=serial%>')"
							value="    Remove " /></td>
					</tr>
						<%
							i++;
							cnt++;
							salt++;
							salp++;
							serial++;
						%>
					</s:iterator>
						<%
							len = String.valueOf(cnt);
							lenp = String.valueOf(salp);
						%>
						<s:hidden id="length" value="<%=len %>" />
						</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="70%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		
		<s:hidden id="lengthp" name="lengthp" />
		<s:hidden name="paraID" />
		<s:hidden name="isFromEdit" />
	</table>
</s:form>
<script>
	function saveFun() {
	
		if(!callValidate()){
			return false;
		}
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'FormulaBuilder_save.action';
			document.getElementById('paraFrm').submit();
			
	}
  	 
	function editFun() {
	
		document.getElementById('paraFrm').action = "FormulaBuilder_edit.action";
		document.getElementById('paraFrm').submit();
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'FormulaBuilder_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function resetFun() {
	
  	 	document.getElementById('paraFrm').action = "FormulaBuilder_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	
	function deleteFun() {
		var conf=confirm("Are you sure you want to delete this record ?");
  			if(conf) {
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = "FormulaBuilder_delete.action";
		document.getElementById('paraFrm').submit();
		}
  	}
	
	function copyformulaFun(){
		document.getElementById('paraFrm_copyBlock').style.display='';
	}
	
	function callValidate()
	{
		
		var frname=document.getElementById('paraFrm_frmbuildName').value;
	   
		var formulaname=document.getElementById('for.name').innerHTML.toLowerCase();
		
		if(frname == "")
			{
				alert("Please Enter "+formulaname);
				return false;
			}
	var count;
		
		var salDtl=document.getElementById('salDtl').innerHTML.toLowerCase();
		if(!salC1.length > 0)
		{
				alert("Please Enter "+salDtl);
				return false;
		}
		
		return true;
		
	}
	
	function callAdd()
	{
		var salA = document.getElementById('paraFrm_salAmt').value;
		var salc = document.getElementById('paraFrm_salCode').value;
		var frname=document.getElementById('paraFrm_frmbuildName').value;
		var salpp = document.getElementById('paraFrm_salprior').value;
		var formulaname=document.getElementById('for.name').innerHTML.toLowerCase();
		var salaryhead=document.getElementById('salhead2').innerHTML.toLowerCase();
		var salarytype=document.getElementById('salarytype').innerHTML.toLowerCase();
		var salaryAmount=document.getElementById('salAmount').innerHTML.toLowerCase();
		var sal=document.getElementById('paraFrm_salType').selectedIndex;
		if(frname == "")
			{
				alert("Please Enter "+formulaname);
				return false;
			}
	   	if(salc == "")
			{
				alert("Please select the "+salaryhead);
				return false;
			}
	
		if(sal==0)
		{
		alert("Please Select "+salarytype);
		return false;
		}
		if(document.getElementById('paraFrm_salType').selectedIndex==1){
			if(salA == ""){
			//alert(3);
				alert("Please Enter "+salaryAmount);
				return false;
			}
			return true;
		}
			
		
		if(document.getElementById('paraFrm_salType').selectedIndex==2){
			if(salA == ""){
			//alert(3);
				alert("Please Select "+salaryAmount);
				return false;
			}
			return true;
		}
		
		if(document.getElementById('paraFrm_salType').selectedIndex==3)
		{
			if(salT1.length > 0)
				{
					for(var i = 0; i < salT1.length; i++)
					{
						if((document.getElementById('paraFrm_salType').value) == salT1[i])
						{	
							alert("Salary Type already exists. Please choose another one.");
							return false;
						}
					}
				}
			 document.getElementById('paraFrm_salAmt').value="";
	   		 document.getElementById('paraFrm_salAmt').disabled=true;
		 }
		 document.getElementById('paraFrm_salAmt').disabled=false;
		 
		 opener.document.getElementById('paraFrm_frmBuild_hiddenSalCode').value=frmCalc;
		
	return true;
	}
	
 function callForEdit(id,salC,salH,salT,salA,salO,fun,serial){

	 
	  	document.getElementById('paraFrm_srNo').value=id;
	  	document.getElementById('serial').value=serial;
	
	 if(fun == 'edit')
 		{
 				
	  	document.getElementById('paraFrm_salCode').value=salC;
	  	
	  	document.getElementById('paraFrm_salHead').value=salH;
	  	 
	  	document.getElementById('paraFrm_salType').value=salT;
	  	
	  	document.getElementById('paraFrm_salAmt').value=salA;
	 
	  	document.getElementById('paraFrm_salOrder').value=salO;
	  	document.getElementById('paraFrm_chkEdit').value=id;
		
		
		if(document.getElementById('paraFrm_salType').selectedIndex==2){
		
			 document.getElementById('paraFrm_salAmt').readOnly=true;
			 document.getElementById('paraFrm_formCalc').disabled=false;
	    }
	   
	    
	    if(document.getElementById('paraFrm_salType').selectedIndex==3)
	    {
			 document.getElementById('paraFrm_salAmt').value="";
	   		 document.getElementById('paraFrm_salAmt').disabled=true;
	 		
	 	}
	 
	 	if(document.getElementById('paraFrm_salType').selectedIndex==1){
		
	    	 //document.getElementById('paraFrm_frmBuild_salAmt').value="";
			 document.getElementById('paraFrm_salAmt').readOnly=false;
			 document.getElementById('paraFrm_formCalc').disabled=true;
	    }
	 		document.getElementById('paraFrm_salAmt').disabled=false;
	 	
			 document.getElementById('paraFrm_salAmt').focus();
		
		
	//  document.getElementById("paraFrm").submit();
      }
      
      	if(fun == 'del')
	 	{
	 		document.getElementById('paraFrm_chkEdit').value=id;
	 //		alert("remove  "+document.getElementById('paraFrm_chkEdit').value);
	 		var conf = confirm("Do you really want to remove this record ?");
	 		if(conf)
	 		{
	 			 document.getElementById('paraFrm').action = 'FormulaBuilder_remove.action';
	 			document.getElementById('paraFrm').submit();
	 		}
	 		else{
	 		return false;
	 		}
	 	}
	 	
   }
   
    function callForDelete(id){
 //alert('Record'+id);
  		 var conf=confirm("Are you sure !\n You want to Remove this record ?");
  			if(conf) {
  			document.getElementById("paraFrm").action="FormulaBuilder_edit.action";
	  		document.getElementById('paraFrm').chkEdit.value=id;
	    	document.getElementById("paraFrm").submit();
  			}
	  		
	 }	  
	 
	
	   function calc(){
	
	   	if(document.getElementById('paraFrm_salType').selectedIndex==2){
	    	 document.getElementById('paraFrm_formCalc').disabled=false;
	    	 document.getElementById('paraFrm_salAmt').readOnly=true;
	    	  document.getElementById('paraFrm_salAmt').disabled=false;
	    	     	
	   }
	    else
	    	document.getElementById('paraFrm_formCalc').disabled=true;
	    
	    if(document.getElementById('paraFrm_salType').selectedIndex==3)
	    {
			 document.getElementById('paraFrm_salAmt').value="";
	   		 document.getElementById('paraFrm_salAmt').disabled=true;
	 		
	 	}
	 	
	 	if(document.getElementById('paraFrm_salType').selectedIndex==1)
	    {
			 document.getElementById('paraFrm_salAmt').value="";
	   		 document.getElementById('paraFrm_formCalc').disabled=true;
	   		 document.getElementById('paraFrm_salAmt').disabled=false;
	 		 document.getElementById('paraFrm_salAmt').readOnly=false;
	 	}
	 	if(document.getElementById('paraFrm_salType').selectedIndex==0)
	    {
			 document.getElementById('paraFrm_salAmt').value="";
	   		 document.getElementById('paraFrm_formCalc').disabled=true;
	 		 document.getElementById('paraFrm_salAmt').readOnly=true;
	 	}

	 }	  
	 function callCalc()
	 {
	

		var salc = document.getElementById('paraFrm_salHead').value;
	 	var salT = document.getElementById('paraFrm_salType').value;
	 	var frname=document.getElementById('paraFrm_frmbuildName').value;
	 	var salAmt=document.getElementById('paraFrm_salAmt').value;
		var formulaname=document.getElementById('for.name').innerHTML.toLowerCase();
		var salaryhead=document.getElementById('salhead3').innerHTML.toLowerCase();	 
		var salarytype=document.getElementById('salarytype').innerHTML.toLowerCase();
		var salaryAmount=document.getElementById('salAmount').innerHTML.toLowerCase();
		if(document.getElementById('paraFrm_salType').selectedIndex==0)
	    {
	    	// document.getElementById('paraFrm_formCalc').disabled=true;
		 
			if(frname == "")
			{
				alert("Please Enter "+formulaname+" First!");
				return false;
			}
	    	if(salc == "")
			{
				alert("Please select the "+salaryhead);
				return false;
			}
	    }	 
			if(salc == "")
			{
				alert("Please select the "+salaryhead);
				return false;
			}
	 	
	 	window.open('','new','top=50,left=300,width=450,height=400,scrollbars=no,status=no,resizable=no');
	 		document.getElementById("paraFrm").target="new";
	 		document.getElementById("paraFrm").action='FormulaBuilder_formulaCalc.action?id=paraFrm_salAmt'; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  		
	  	 }
	  	 
	function saveCopiedFormula(){
		var newFormulaName = document.getElementById('paraFrm_copiedFormulaName').value; 
		if(newFormulaName==""){
			alert("Please enter new formula name to copy");
		}else{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'FormulaBuilder_saveCopiedFormula.action';
			document.getElementById('paraFrm').submit();
		}
	}
   
  	</script>