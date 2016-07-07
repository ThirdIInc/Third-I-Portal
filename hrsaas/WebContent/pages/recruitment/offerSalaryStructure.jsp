<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OfferDetails" id="paraFrm" theme="simple" method="post">
<s:hidden name="candidateCode"/>
<s:hidden name="requisitionCode"/>

<s:hidden name="calflag"  />
<s:hidden name="gradeflag"  />


	<table class="formbg" width="100%"><!-- main table -->
		 <tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				     <tr>
					     <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
					     <td width="93%" class="txt"><strong class="formhead">Define Salary Structure </strong></td>
					     <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
				     </tr>
				</table>
			</td>
		</tr>
		
		<tr>
	          <td colspan="3" width="100%">
	          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		            	<tr>
		              		<td width="5%">
		              		<!--<s:submit value=" Save" cssClass="save" action="OfferDetails_saveSalaryStruc"
		              			onclick="return validateSave();"/>
				     		<input type="button" value="Cancel" class="token"/>-->
				     		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>
				     		<td width="80%" id="ctrlShow">
				     			<input type="button" value = "Close" class="token" onclick="return salWindowClose();"/>
				     		</td>
		               	<td width="15%"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
		            	</tr>
	          	</table>            
	          </td>
	     </tr>
	     
	     <tr><!--Schedule interview-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"><strong>Salary Structure</strong></td>
					</tr>
					<tr>
						<td>
							<s:hidden name="viewFlagStruc"></s:hidden>
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
							<s:if test="viewFlagStruc">
								<tr>
									<td width="20%">Offered CTC in Lacs :</td>
									<td width="25%"><s:textfield name="offeredCtc" size="25" readonly="true" /></td>
									<td width="20%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
								</tr>	
								
								<tr>
									<td width="20%" colspan="1">&nbsp;</td>
									<td width="25%" colspan="1"><s:checkbox 
										name="grdFlag"  id="paraFrm_grdFlag" onclick="chkflag(1);"/>Grade 
										&nbsp;</td>
									<td width="20%" colspan="1">&nbsp;</td>
									<td width="25%" colspan="3"><s:checkbox  
										name="frmFlag" id="paraFrm_frmFlag" onclick="chkflag(2);" />Formula 
										&nbsp;</td>
								</tr>
								
								<tr>
							<td width="100%" colspan="4">
							<div id="grd_Flag">
							<table width="100%">
								<tr>
									<td width="20%" colspan="1">Salary Grade :</td>
									<td width="25%" colspan="1"><s:textfield theme="simple"
										readonly="true" name="salgrdName" size="27" />
										<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="15"
										onclick="javascript:callToGradeF9();" /> <s:hidden name="salgrdId" /></td>
									<td width="20%" colspan="1">
										<input type="button" name="calculate" value="Calculate" class="token" onclick="calculateGradeWise();" />
									</td>
									<td width="25%" colspan="1">&nbsp;</td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
						
						<tr>
							<td width="100%" colspan="4">
							<div id="frm_Flag">
							<table width="100%">
								<tr>

									<td width="20%" colspan="1">Formula:</td>
									<td width="25%" colspan="1"><s:textfield size="27"
										theme="simple" name="frmName" readonly="true"
										value="%{frmName}" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="15"
										onclick="javascript:rowcallsF9();">
									<s:hidden name="frmId" value="%{frmId}" /> <s:hidden
										name="sCode" value="%{sCode}" /> <s:hidden name="sType"
										value="%{sType}" /> <s:hidden name="sFrm" value="%{sFrm}" /></td>
									<td width="20%" colspan="1">Gross Amount:</td>
									<td width="25%" colspan="4"><s:textfield
										cssStyle="width:130" size="27" theme="simple" name="grsAmt"
										value="%{grsAmt}" maxlength="7" readonly="true"/> <s:submit cssClass="token"
										name="calculate" value="Calculate" onclick="return calcFormula();" /></td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
						</s:if>
						<s:else>
							<tr>
								<td width="20%" >Offered CTC in Lacs :</td>
								<td width="25%"><s:property value="offeredCtc"/><s:hidden name="offeredCtc"/></td>
								<td width="20%">&nbsp;</td>
								<td width="25%">&nbsp;</td>
							</tr>
							<tr>
									<td width="20%" colspan="1">&nbsp;</td>
									<td width="25%" colspan="1"><s:checkbox 
										name="grdFlag"  id="paraFrm_grdFlag" disabled="true" onclick="chkflag(1);"/>Grade 
										&nbsp;</td>
									<td width="20%" colspan="1">&nbsp;</td>
									<td width="25%" colspan="3"><s:checkbox  
										name="frmFlag" id="paraFrm_frmFlag" disabled="true" onclick="chkflag(2);" />Formula 
										&nbsp;</td>
							</tr>
							<tr>
							<td width="100%" colspan="4">
							<div id="grd_Flag">
							<table width="100%">
								<tr>
									<td width="20%" colspan="1">Salary Grade :</td>
									<td width="25%" colspan="1"><s:property value="salgrdName"/><s:hidden name="salgrdId" /></td>
									<s:hidden name="salgrdName" />
									<td width="20%" colspan="1">&nbsp;</td>
									<td width="25%" colspan="1">&nbsp;</td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4">
							<div id="frm_Flag">
							<table width="100%">
								<tr>

									<td width="20%" colspan="1">Formula:</td>
									<td width="25%" colspan="1"><s:property value="frmName"/> 
									<s:hidden name="frmName" />
									<s:hidden name="frmId" value="%{frmId}" /> <s:hidden
										name="sCode" value="%{sCode}" /> <s:hidden name="sType"
										value="%{sType}" /> <s:hidden name="sFrm" value="%{sFrm}" /></td>
									<td width="20%" colspan="1" >Gross Amount:</td>
									<td width="25%" colspan="4"><s:property value="grsAmt"/></td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
						</s:else>
							</table><!--Table header-->
						</td>
					</tr>		
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule interview-->
		<tr>
			<td>
				<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2" class="formbg"><!--salaryDetails-->
					<tr>
						<td>
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Salary List-->
								<tr>
									<td><strong class="formhead">Salary Details : </strong></td>
							    </tr>
							    <tr>
									
												<td class="formth" width="30%">Salary Head</td>
												<td class="formth" width="20%">Periodicity</td>
												<td class="formth" width="25%" align="center">Amount in Salary structure</td>
											
								</tr>
								<%
								int id = 0;
								%>		
								<s:iterator value="salList">
							<tr>

								<td class="border2" width="30%"><s:hidden name="salCode" /><s:property
									value="salHead" /><s:hidden name="salHead" /></td>
								<td class="border2" width="20%"><s:hidden name="salPerdiocity" />
										<s:property
									value="salPerdiocity" />
									<input type="hidden" name="period" id="period<%=id%>" value='<s:property value="salPerdiocity" />' />
									</td>
								<td class="border2" width="25%" align="right"><input type="text"  maxlength="12"  
									name="newAmt" value='<s:property value="newAmt" />' size="32%" Style="text-align:right"
									id="newAmt<%=id%>" onkeypress="return numbersWithDot();" 
									onblur="return valCTC('newAmt<%=id %>','<s:property value="salHead" />');"/> 
									<s:hidden name="proSalCode" />
									
									</td>
							</tr>
							
							<%
							id++;
							%>
						</s:iterator>
					
						<%!int totRows = 0;%>
						<%
						totRows = id;
						%>	
							</table><!--Salary List-->	
						</td>
					</tr>
				</table><!--salaryDetails-->
			</td>
		</tr><!--salaryDetails-->
		<!--Debit Details added on 31/08/2010 -->
		<tr>
			<td>
				<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2" class="formbg"><!--DebitDetails-->
					<tr>
						<td>
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Debit List-->
								<tr>
									<td><strong class="formhead">Debit Details : </strong></td>
							    </tr>
							    <tr>
									
												<td class="formth" width="30%">Debit Head</td>
												<td class="formth" width="20%">Periodicity</td>
												<td class="formth" width="25%" align="center">Amount in Salary structure</td>
											
								</tr>
								<%
								int id1 = 0;
								%>		
								<s:iterator value="dbtList">
							<tr>

								<td class="border2" width="30%"><s:hidden name="dbtCode" /><s:property
									value="dbtHead" /><s:hidden name="dbtHead" /></td>
								<td class="border2" width="20%"><s:hidden name="dbtPerdiocity" />
										<s:property
									value="dbtPerdiocity" />
									<input type="hidden" name="period" id="period<%=id%>" value='<s:property value="dbtPerdiocity" />' />
									</td>
								<td class="border2" width="25%" align="right"><input type="text"  maxlength="12"  
									name="newAmtDbt" value='<s:property value="newAmtDbt" />' size="32%" Style="text-align:right"
									id="newAmtDbt<%=id%>" onkeypress="return numbersWithDot();" 
									onblur="return valCTC('newAmtDbt<%=id %>','<s:property value="dbtHead" />');"/> 
									<s:hidden name="proDbtCode" />
									
									</td>
							</tr>
							
							<%
							id++;
							%>
						</s:iterator>
					
						<%!int totRows1 = 0;%>
						<%
						totRows1 = id;
						%>	
							</table><!--Debit List-->	
						</td>
					</tr>
				</table><!--DebitDetails-->
			</td>
		</tr><!--DebitDetails-->
						
		<tr>
	          <td colspan="3" width="100%">
	          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		            	<tr>
		              		<td width="5%">
		              		<!--<s:submit value=" Save" cssClass="save" action="OfferDetails_saveSalaryStruc"
		              			onclick="return validateSave();"/>
				     		<input type="button" value="Cancel" class="token"/>-->
				     		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>
				     		<td width="80%" id="ctrlShow">
				     		<input type="button" value = "Close" class="token" onclick="return salWindowClose();"/>
				     		</td>
				     		<td width="15%"></td>
		               	</tr>
	          	</table>            
	          </td>
	     </tr>
	     
	</table><!-- main table -->	
</s:form>
<script>

onLoad();

function onLoad()
{
	//alert("grade="+document.getElementById('paraFrm_grdFlag').checked);
	//alert("formula="+document.getElementById('paraFrm_frmFlag').checked);
		//alert("grdFlag -- "+document.getElementById('paraFrm_grdFlag').value);
		//alert("frmFlag -- "+document.getElementById('paraFrm_frmFlag').value);
var totalrows = <%=totRows%>;
			if(document.getElementById('paraFrm_grdFlag').checked == true)
			{
				document.getElementById('grd_Flag').style.display = '';
				document.getElementById('frm_Flag').style.display = 'none';
				document.getElementById('paraFrm_frmFlag').checked = false;
				var ctcAmt = document.getElementById('paraFrm_offeredCtc').value;
				document.getElementById('paraFrm_grsAmt').value = ctcAmt; 
				for( var i=0; i< totalrows; i++)
				{
					document.getElementById('newAmt'+i).readOnly='true';
				}
			}
			else if(document.getElementById('paraFrm_frmFlag').checked == true)
			{
				document.getElementById('frm_Flag').style.display = '';
				document.getElementById('grd_Flag').style.display = 'none';
				document.getElementById('paraFrm_grdFlag').checked = false;
				var ctcAmt = document.getElementById('paraFrm_offeredCtc').value;
				document.getElementById('paraFrm_grsAmt').value = ctcAmt; 
				for( var i=0; i< totalrows; i++)
				{
					document.getElementById('newAmt'+i).readOnly='true';
				}
			}else {
				document.getElementById('paraFrm_grdFlag').checked = false;
				document.getElementById('paraFrm_frmFlag').checked = false;
				document.getElementById('frm_Flag').style.display = 'none';
				document.getElementById('grd_Flag').style.display = 'none';
				//document.getElementById('paraFrm_newAmt').disabled="false";
			}
	
}

	function chkflag(val)
{
var totalrows = <%=totRows%>;
		if(val == 1)
		{
 
			if(document.getElementById('paraFrm_grdFlag').checked == true)
			{
				document.getElementById('grd_Flag').style.display = '';
				document.getElementById('frm_Flag').style.display = 'none';
				document.getElementById('paraFrm_frmFlag').checked = false;
				var ctcAmt = document.getElementById('paraFrm_offeredCtc').value;
				document.getElementById('paraFrm_grsAmt').value = ctcAmt; 
				for( var i=0; i< totalrows; i++)
				{
					document.getElementById('newAmt'+i).readOnly='true';
				}
			}
			else 
			{
				document.getElementById('frm_Flag').style.display = 'none';
				document.getElementById('grd_Flag').style.display = 'none';
				document.getElementById('paraFrm_grdFlag').checked = false;
				var ctcAmt = document.getElementById('paraFrm_offeredCtc').value;
				document.getElementById('paraFrm_grsAmt').value = ctcAmt; 
				document.getElementById('paraFrm_salgrdName').value = '';
				for( var i=0; i< totalrows; i++)
				{
					document.getElementById('newAmt'+i).readOnly='';
				}
			}
		}
	else if(val == 2)
	{
		if(document.getElementById('paraFrm_frmFlag').checked == true)
		{
			document.getElementById('frm_Flag').style.display = '';
			document.getElementById('grd_Flag').style.display = 'none';
			document.getElementById('paraFrm_grdFlag').checked = false;
			var ctcAmt = document.getElementById('paraFrm_offeredCtc').value;
			document.getElementById('paraFrm_grsAmt').value = ctcAmt; 
			for( var i=0; i< totalrows; i++)
			{
				document.getElementById('newAmt'+i).readOnly='true';
			}
		}
		else 
		{
			document.getElementById('grd_Flag').style.display = 'none';
			document.getElementById('frm_Flag').style.display = 'none';
			document.getElementById('paraFrm_frmFlag').checked = false;
			var ctcAmt = document.getElementById('paraFrm_offeredCtc').value;
			//alert('ctcAmt  :'+ctcAmt);
			document.getElementById('paraFrm_grsAmt').value = ctcAmt; 
			document.getElementById('paraFrm_frmName').value = '';
			for( var i=0; i< totalrows; i++)
			{
				document.getElementById('newAmt'+i).readOnly='';
			}
		}
	
 	}else
 	{
 		document.getElementById('paraFrm_grdFlag').checked = false;
 		document.getElementById('paraFrm_frmFlag').checked = false;
 		document.getElementById('frm_Flag').style.display = 'none';
 		document.getElementById('grd_Flag').style.display = 'none';
 		for( var i=0; i< totalRows; i++)
 		{
 			document.getElementById('newAmt'+i).readOnly='';
 		}
 	}
	
} 

function saveFun(){
try{
		var fomulaName = document.getElementById("paraFrm_frmFlag").checked;
		var gradeName = document.getElementById("paraFrm_grdFlag").checked;
		if(fomulaName == false && gradeName== false) {
		alert("Please select either Grade or Formula");
			return false;
		}
		
		if(document.getElementById('paraFrm_frmFlag').checked == true) {		
			if(document.getElementById("paraFrm_frmName").value == "") {
				alert("Please select formula");
				document.getElementById("paraFrm_frmName").focus();
				return false;
			}
			if(document.getElementById('paraFrm_calflag').value!="1") {
				alert("Please Click Calculate button");			 
				return false;
			}
		} else if(document.getElementById('paraFrm_grdFlag').checked == true) {
				if(document.getElementById("paraFrm_salgrdName").value == "") {
					alert('Please select salary grade');
					document.getElementById("paraFrm_salgrdName").focus();
					return false;
				}
				if(document.getElementById('paraFrm_gradeflag').value!="2") {
					alert("Please Click Calculate button");			 
					return false;
			}
		} else {
			if(!checkAmt())
				return false;
			document.getElementById('paraFrm_salgrdId').value = ' ';
			document.getElementById('paraFrm_frmId').value = ' ';
			document.getElementById('paraFrm_grdFlag').value = false;
			document.getElementById('paraFrm_frmFlag').value = false;
		}
	//alert("grade="+document.getElementById('paraFrm_grdFlag').checked);
	//alert("formula="+document.getElementById('paraFrm_frmFlag').checked);
		//alert("grdFlag -- "+document.getElementById('paraFrm_grdFlag').value);
		//alert("frmFlag -- "+document.getElementById('paraFrm_frmFlag').value);
			var totalrow = <%=totRows%>;
	for(var row = 0;row < totalrow ;row++)
	{
		var values=document.getElementById("newAmt"+row).value;
		
	if(values =="" || values=='.'){
		values =0;
	}
	document.getElementById("newAmt"+row).value = eval(values*100/100);
	}
		document.getElementById("paraFrm").action="OfferDetails_saveSalaryStruc.action";
	    document.getElementById("paraFrm").submit();
	    }catch(e){
	    alert("Unable to define Struvture "+e);
	    }
}

function editFun(){
		document.getElementById("paraFrm").action="OfferDetails_editSalaryStruc.action";
	    document.getElementById("paraFrm").submit();
	}
	
function calcFormula()
 {
 	  var totalrow =<%=totRows%> ;
      var gAmt= document.getElementById('paraFrm_grsAmt').value;
 	  var frname= document.getElementById('paraFrm_frmId').value;
 	  var frnm= document.getElementById('paraFrm_frmName').value;
 	  var grdId= document.getElementById('paraFrm_salgrdId').value;
 	  var grdNm= document.getElementById('paraFrm_salgrdName').value; 
	
	  if(frnm=="") {
	  alert("please select formula first!");
	  document.getElementById('paraFrm_frmName').focus();
	  return false;
	  }
	  
	 if(totalrow<=0) {
  		alert('There are no salary details so salary can not be calculate');
  		return false;
  		}
  	
	document.getElementById("paraFrm").target ="_self";	
  	document.getElementById("paraFrm").action="OfferDetails_calculate.action";
 	document.getElementById("paraFrm").submit();	
 }
 
 
 function rowcallsF9()
    {
      document.getElementById('paraFrm_calflag').value="0";
       callsF9(500,325,'OfferDetails_f9frmaction.action'); 
    }
 
 function callToGradeF9() {
      document.getElementById('paraFrm_gradeflag').value="0";
       callsF9(500,325,'OfferDetails_f9salgradeAction.action'); 
    }   
    
    
    function salWindowClose(){
    try{
    	window.close();
    	}catch(e){
    		alert(e);
    	}
    	return true;
    }

	function checkAmt() {

	var count=0;
	var count1=0;
	var count2=0;
	var count3=0;
	var count4=0;
	var totalrow = <%=totRows%>;
	for(var row = 0;row < totalrow ;row++)
	{
	if(document.getElementById("period"+row).value=="Monthly"){
	var values=document.getElementById("newAmt"+row).value;
	if(values =="" || values=='.'){
		values =0;
	}
	values =eval(values*100/100);
	count=eval(count)+eval(values);
	count1=eval(count1)+eval(values)*12;
	}
	else if(document.getElementById("period"+row).value=="Half Yearly"){
			var values=document.getElementById("newAmt"+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
			
				count2=eval(count2)+(eval(values)*2);
	}
	
	else if(document.getElementById("period"+row).value=="Annually"){
		var values=document.getElementById("newAmt"+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count3=eval(count3)+eval(values);
		}
	else if(document.getElementById("period"+row).value=="Quarterly"){
		var values=document.getElementById("newAmt"+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count4=eval(count4)+eval(values)*4;
		}
	} 
	
		var annualAmt = eval(count1+count2+count3+count4)/100000;
		if(document.getElementById('paraFrm_offeredCtc').value < annualAmt)
		{
			alert("Total Amount Should not exceed offered CTC");
			return false;
		}
		else 
			return true;
	}
	
function valCTC(ctcfieldname,ctclabelname)
{

	var amount=document.getElementById(ctcfieldname).value;
	
	if(trim(amount)!=""){
	if(isNaN(amount)) { 
	alert("Single dot is allowed in "+ctclabelname+" field.");
	document.getElementById(ctcfieldname).value='';
	document.getElementById(ctcfieldname).focus();
	return false;
	
	}	
	}
	 return true;
}


function calculateGradeWise() {
	  var totalrow =<%=totRows%> ;
 	  var salgrdName = document.getElementById('paraFrm_salgrdName').value;
	 
	  if(salgrdName == "") {
	  	alert("please select grade first!");
	  	document.getElementById('paraFrm_salgrdName').focus();
	  	return false;
	  }
	  
	 if(totalrow<=0) {
  		alert('There are no salary details so salary can not be calculate');
  		return false;
  		}

		document.getElementById('paraFrm').target = "_self";
   	  	document.getElementById('paraFrm').action="OfferDetails_gradeDetail.action";
	  	document.getElementById('paraFrm').submit();
} 
	
</script>
