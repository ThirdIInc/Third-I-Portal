<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form validate="true" id="paraFrm" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Professional
					Tax Parameters </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%">
							<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
								<div align="right">
									<font color="red">*</font> Indicates Required
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
								<tr>
									<td colspan="5" class="formhead"><strong
										class="forminnerhead">Professional Tax</strong> <s:hidden
										name="ptMaster.ptCode" /></td>
								</tr>
								<tr>
									<td><label class="set" name="pftax.edate" id="pftax.edate"
										ondblclick="callShowDiv(this);"><%=label.get("pftax.edate")%></label>
									<!--Effective Date--> <font color="red">*</font>:</td>
									<td><s:textfield size="25" name="ptMaster.effDate"
										readonly="true" theme="simple" /> <s:a
										href="javascript:NewCal('paraFrm_ptMaster_effDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16" id="ctrlHide">
									</s:a></td>
								</tr>

								<tr>
									<td width="25%"><label class="set" name="pftax.location"
										id="pftax.location" ondblclick="callShowDiv(this);"><%=label.get("pftax.location")%></label>
									<!--Location--><font color="red">*</font>:</td>
									<td colspan="2" width="50%"><s:hidden
										name="ptMaster.ptLocCode" /> <s:textfield size="25"
										name="ptMaster.locName" readonly="true" /> <img id="ctrlHide"
										src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'ProfTaxMaster_f9locaction.action'); ">


									</td>
								</tr>

								<tr>
									<td width="25%"><label class="set"
										name="pftax.pftaxdebCode" id="pftax.pftaxdebCode"
										ondblclick="callShowDiv(this);"><%=label.get("pftax.pftaxdebCode")%></label>
									<!--Professional Tax Debit Code--> <font color="red">*</font>:</td>
									<td colspan="2" width="50%"><s:hidden
										name="ptMaster.ptDebitCode" /> <s:textfield size="25"
										name="ptMaster.ptDebitName" readonly="true" /> <img
										src="../pages/images/search2.gif" height="16" id="ctrlHide"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'ProfTaxMaster_f9debitaction.action'); ">


									</td>
								</tr>
								<tr>
									<td colspan="3"><img
										src="../pages/images/recruitment/space.gif" width="5"
										height="4" /></td>
								</tr>
								<tr>
									<td colspan="3"><img
										src="../pages/images/recruitment/space.gif" width="5"
										height="4" /></td>
								</tr>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="formbg">
									<tr>
										<td>


										<table width="98%" border="0" align="center" cellpadding="0"
											cellspacing="0">

											<tr>
												<td colspan="5" class="formhead"><strong
													class="forminnerhead">Professional Tax Details:</strong></td>
											</tr>

											<tr>
												<td colspan="1"><label class="set"
													name="pftax.fromAmount" id="pftax.fromAmount"
													ondblclick="callShowDiv(this);"><%=label.get("pftax.fromAmount")%></label>
												<!--From Amount--> <font color="red">*</font>:</td>
												<td><s:textfield name="ptMaster.frmAmount" size="25"
													maxlength="10" onkeypress="return numbersWithDot();" /> <s:hidden
													name="ptMaster.chkEdit" /> <s:hidden
													name="ptMaster.taxDtlCode" /></td>
												<td><label class="set" name="pftax.toAmount"
													id="pftax.toAmount" ondblclick="callShowDiv(this);"><%=label.get("pftax.toAmount")%></label>
												<!--To Amount--> <font color="red">*</font>:</td>
												<td><s:textfield name="ptMaster.toAmount" size="25"
													maxlength="10" onkeypress="return numbersWithDot();" /></td>

											</tr>
											<tr>
												<td colspan="3"><img
													src="../pages/images/recruitment/space.gif" width="5"
													height="4" /></td>
											</tr>
											<tr>
												<td><label class="set" name="pftax.fixedAmount"
													id="pftax.fixedAmount" ondblclick="callShowDiv(this);"><%=label.get("pftax.fixedAmount")%></label>
												<!--Fixed Amount--> <font color="red">*</font>:</td>
												<td><s:textfield name="ptMaster.fixedAmt" size="25"
													maxlength="10" onkeypress="return numbersWithDot();" /></td>
											</tr>
											<tr>
												<td colspan="3"><img
													src="../pages/images/recruitment/space.gif" width="5"
													height="4" /></td>
											</tr>
											<tr>
												<td colspan="1"><label class="set"
													name="pftax.varAmount" id="pftax.varAmount"
													ondblclick="callShowDiv(this);"><%=label.get("pftax.varAmount")%></label>:
												<!-- Variable Amount : --></td>
												<td><s:textfield name="ptMaster.varAmt" size="25"
													maxlength="10" onkeypress="return numbersWithDot();" /></td>
												<td><label class="set" name="pftax.varMonth"
													id="pftax.varMonth" ondblclick="callShowDiv(this);"><%=label.get("pftax.varMonth")%></label>:
												<!--Variable Month :  --></td>
												<td><s:select name="ptMaster.varMonth"
													list="#{' ':'--Select--','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />

												</td>


											</tr>
											<tr>
												<td colspan="3"><img
													src="../pages/images/recruitment/space.gif" width="5"
													height="4" /></td>
											</tr>
											<tr align="center">

												<td colspan="4"><s:submit cssClass="token" id="ctrlHide"
													action="ProfTaxMaster_addTax" theme="simple"
													value="   Add   " onclick="return callAdd();" /></td>
											</tr>

										</table>
										</td>
									</tr>


									<table class="bodyTable" width="100%" border="0">
										<tr>
											<td colspan="4">&nbsp;</td>
										</tr>
										<tr>
											<td height="15" class="formth"><!--Sr. No.--> <label
												class="set" name="pftax.srno" id="pftax.srno"
												ondblclick="callShowDiv(this);"><%=label.get("pftax.srno")%></label>:
											</td>

											<td height="15" class="formth"><!--From Amount--> <label
												class="set" name="pftax.fromAmount" id="pftax.fromAmount1"
												ondblclick="callShowDiv(this);"><%=label.get("pftax.fromAmount")%></label>
											</td>
											<td height="15" class="formth"><!--To Amount--> <label
												class="set" name="pftax.toAmount" id="pftax.toAmount1"
												ondblclick="callShowDiv(this);"><%=label.get("pftax.toAmount")%></label>
											</td>
											<td height="15" class="formth"><!--Fixed Amount--> <label
												class="set" name="pftax.fixedAmount" id="pftax.fixedAmount1"
												ondblclick="callShowDiv(this);"><%=label.get("pftax.fixedAmount")%></label>
											</td>
											<td height="15" class="formth"><!--Variable Amount--> <label
												class="set" name="pftax.varAmount" id="pftax.varAmount1"
												ondblclick="callShowDiv(this);"><%=label.get("pftax.varAmount")%></label>
											</td>
											<td height="10" class="formth"><!--Variable Month--> <label
												class="set" name="pftax.varMonth" id="pftax.varMonth1"
												ondblclick="callShowDiv(this);"><%=label.get("pftax.varMonth")%></label>
											</td>
											<!-- Updated by Anantha lakshmi -->		
											<td height="15" class="formth">
												<label
													class="set" name="editDelete" id="editDelete"
													ondblclick="callShowDiv(this);"><%=label.get("editDelete")%>
												</label>
											</td>

										</tr>

										<%
										int i = 0, k = 1;
										%>

										<s:iterator value="ptMaster.taxList" status="stat">
											<tr>
												<td class="border2" width="5%"><%=i + 1%><s:hidden
													name="srNo"></s:hidden> <s:hidden name="ptDtlCode">
												</s:hidden></td>
												<td class="border2"><s:property value="famt" /> <input
													type="hidden" name="famt"
													value="<s:property value="famt"/>" id="famt<%=i%>" /></td>
												<td class="border2"><s:property value="tamt" /> <input
													type="hidden" name="tamt"
													value="<s:property value="tamt"/>" id="tamt<%=i%>" /></td>
												<td class="border2"><s:property value="fdamt" /> <s:hidden
													name="fdamt" /></td>
												<td class="border2"><s:property value="vamt" /> <s:hidden
													name="vamt" /></td>
												<td class="border2"><s:property value="vmth" /> <s:hidden
													name="vmth" /></td>
												<s:hidden name="dupVarmonth" />

												<td class="border2">&nbsp;&nbsp;&nbsp; <input
													type="button" class="edit" id="ctrlHide"
													onclick="callForEdit('<s:property value="ptDtlCode"/>','<s:property value="famt"/>',
													'<s:property value="tamt"/>','<s:property value="fdamt"/>','<s:property value="vamt"/>',
													'<s:property value="dupVarmonth"/>','<s:property value="srNo"/>')"
													value="    Edit" /> <input type="button" class="delete"
													onclick="callForDelete('<s:property value="srNo"/>')"
													value="   Remove" id="ctrlHide"/></td>

											</tr>
											<%
											i++;
											%>

										</s:iterator>
									</table>



									<s:hidden name="ptMaster.paraID" />
									<s:hidden name="ptMaster.isFromEdit" />
									<s:hidden name="tableLength" />


									</td>
									</tr>
								</table>
								</td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%">
							<div align="right">
								<font color="red">*</font> Indicates Required
							</div>
						</td>
					</tr>
				</table>
				</td>
		</tr>
	</table>
</s:form>

<script>


 function callForEdit(id,famt,tamt,fdamt,vamt,vmth,edit){
 

	
	  	document.getElementById('paraFrm_ptDtlCode').value=id;
	  
	  	document.getElementById('paraFrm_ptMaster_frmAmount').value=famt;
	  	
	  	document.getElementById('paraFrm_ptMaster_toAmount').value=tamt;
	  	
	  	document.getElementById('paraFrm_ptMaster_fixedAmt').value=fdamt;
	 
	  	document.getElementById('paraFrm_ptMaster_varAmt').value=vamt;
	  
	  	document.getElementById('paraFrm_ptMaster_varMonth').value=vmth;
	   
	  	document.getElementById('paraFrm_ptMaster_chkEdit').value=edit;
	  	
	  
	  
	  	
	 
   }

	function saveFun()
	{
		
		var fieldName = ["paraFrm_ptMaster_effDate","paraFrm_ptMaster_locName","paraFrm_ptMaster_ptDebitName"];
		var lableName = ["pftax.edate", "pftax.location", "pftax.pftaxdebCode"];
		var flag=["enter","select","select"];
	    
	    if(!validateBlank(fieldName,lableName,flag))
	          return false;    
		   
		if(!validateDate('paraFrm_ptMaster_effDate',"Date"))
		 return false;
		
		 if(document.getElementById('paraFrm_tableLength').value=="0"){
			alert("Please Add At least one Professional Tax Details Record!");
			return false;
		}
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='ProfTaxMaster_save.action';
		document.getElementById("paraFrm").submit();
		
	}
	
	function resetFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='ProfTaxMaster_resetAll.action';
		document.getElementById("paraFrm").submit();
	}
	
	function backFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='ProfTaxMaster_input.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun()
	{
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="ProfTaxMaster_editRecord.action";
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ProfTaxMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}

function callAdd()
{
	var fieldName = ["paraFrm_ptMaster_frmAmount", "paraFrm_ptMaster_toAmount", "paraFrm_ptMaster_fixedAmt"];
	var lableName = ["pftax.fromAmount", "pftax.toAmount1", "pftax.fixedAmount"];
	var flag=["enter","enter","enter"];
	
	if(!validateBlank(fieldName,lableName,flag))
          return false;  
	   
	 var mon=document.getElementById('paraFrm_ptMaster_varMonth').value;
	/* if(mon>12 || mon <0 )
	 {
	    alert("Please Enter Proper Month");
	    return false;
	 } */
	
var  b=formValidate();


if(b)
{


var chk=newcheckrange();
if(chk){
 // alert("with in chk function...!!! ");
  return true;
}
else{
 //alert("Professional Tax Slab already exits in this Range.");
 return false;
}
}
else
{ 
return false;
}
}





function newcheckrange()
{
// alert("newcheckrange");
      var FrmAmount = document.getElementById('paraFrm_ptMaster_frmAmount').value;
 		var ToAmount = document.getElementById('paraFrm_ptMaster_toAmount').value;
 		var listsize=document.getElementById('paraFrm_tableLength').value;
 		var chkhidden=-1;
 	 if(!document.getElementById('paraFrm_ptMaster_chkEdit').value=="")
 	 chkhidden=document.getElementById('paraFrm_ptMaster_chkEdit').value-1;
 	 
 	    var  data1=new Array();
	    var  data=new Array();
if(listsize>0){   
 		for(var j=0;j<listsize;j++)
 		{
 		  data[j]=document.getElementById('famt'+j).value;
	     data1[j]=document.getElementById('tamt'+j).value;
	    //alert(data[j]);
	    //alert(data1[j]);
 		  
 		}

	if(chkhidden==-1){
			for (var i = 0; i < listsize; i++) {
				if(eval(FrmAmount)<= eval(data1[i]))
				{
						 //alert("not satisfy...first if state!");
						 alert("Professional Tax Slab already exits in this Range.");
							return false;
				}
				else if(eval(FrmAmount)>=eval(data[i]) && eval(FrmAmount)<= eval(data1[i]))
				{
						 //alert("not satisfy...first if state!");
						 alert("Professional Tax Slab already exits in this Range.");
							return false;
				}
				else if(eval(ToAmount)>=eval(data[i]))
				{
					if(eval(ToAmount)>=eval(data[i]) && eval(ToAmount)<= eval(data1[i]))
					{
					 //alert("not satisfy...second if state!");
					 alert("Professional Tax Slab already exits in this Range.");
						return false;
					}
				}
				
			}
			//Updated by Anantha lakshmi
			/*
			for (var i = 0; i <=listsize; i++) {
				if(i==listsize-1){
					var amt=eval(data1[i])+1;	 
					if(eval(FrmAmount)!=amt){
						alert(" Professional Tax Slab  limit not in Range ");
					 	return false;
				    }
				} 
		 }//second for close
		 
		 */	
	}
	else{
			//alert("with in else block...!"+chkhidden);
			for (var i = 0; i < listsize; i++) {
			//alert("counting...!"+i);
				if(i!=chkhidden){
				//alert("when editing"+chkhidden);
				
				if(eval(FrmAmount)<= eval(data1[i]))
				{
						 //alert("not satisfy...first if state!");
						 alert("Professional Tax Slab already exits in this Range.");
							return false;
				}
				else if(eval(FrmAmount)>=eval(data[i]) && eval(FrmAmount)<= eval(data1[i]))
							{
						//	alert("not satisfy...first if state!"+i+"  value");
						alert("Professional Tax Slab already exits in this Range.");
								return false;
							}
					else if(eval(ToAmount)>=eval(data[i]))
					{
						if(eval(ToAmount)>=eval(data1[i]) && eval(ToAmount)<=eval(data[i]))
						{
						
					/*	alert("sssssssssssss");
						alert("not satisfy...second if state!"+i+"  value");
						alert("to amount "+data1[i]+"from amt"+FrmAmount+"user to amt"+ToAmount+" and from amount"+data[i]);
					*/
							alert("Professional Tax Slab already exits in this Range.");
							return false;
						}
						/*
						alert("not satify 2nd if also...!!");
						alert("to amount "+data1[i]+"from amt"+FrmAmount+"user to amt"+ToAmount+" and from amount"+data[i]);
						alert("range....AT the end of"+i+"loop");
						*/
					}
				}
			}//for close
			
			//Updated by Anantha lakshmi
			
			 /* if(chkhidden==0){
		        	 var amt=eval(data[1])-1;
		        	 alert("--------AMT-------"+amt);
		        	 alert("--------ToAmount-------"+ToAmount);
		        	 alert("--------FrmAmount-------"+FrmAmount);
		        	 if(eval(FrmAmount)<=ToAmount ){
		        	 	if(eval(ToAmount)<= amt){
			        	 	alert(" Professional Tax Slab  limit not in Range ");
			        	 	return false;
			        	 }	
		        	 }
		     }else{
		  		for (var i = 0; i <=listsize; i++) {
					var r=chkhidden-1;
			        var amt=eval(data1[r])+1;
			        if(eval(FrmAmount)!=amt){
			        	alert(" Professional Tax Slab limit not in range ");
			        	return false;
			        }			  
			    }	   
		    }// else closed		
		    
		    */
	}//outer else close
}
//	alert("satisfy...and returning..!");
return true;
}










	 function callForDelete(id){
	 	var conf=confirm("Do you want to remove this record.");
		if(conf) {
	 		document.getElementById('paraFrm_ptMaster_chkEdit').value=eval(id)-1;
	 	    document.getElementById("paraFrm").action="ProfTaxMaster_deleteDtl.action";
	   		document.getElementById("paraFrm").submit();
		}
	  		
	 }	  
   
   
   
   	function formValidate1(){
  		 		
  		var conf=confirm("Are you sure to delete this record? ");
  			if(conf) {
  			return true;
  			
  			}else
  			{
  			return false;
  			}
  	  		
  		}
  		
  		
  		
  	
  		
	 function formValidate(){
	
 		
 		var frmAmount = document.getElementById('paraFrm_ptMaster_frmAmount').value;
 		var toAmount = document.getElementById('paraFrm_ptMaster_toAmount').value;
 		var taxPercent = document.getElementById('paraFrm_ptMaster_fixedAmt').value;
	  
	  /*  if (frmAmount==""){
	    	alert("Please Enter From Amount");
			return false;
	 	 }
	 	 
	 	 if (toAmount==""){
	    	alert("Please Enter To Amount");
			return false;
	 	 }
	 	 if (taxPercent==""){
	    	alert("Please Enter Tax Percent");
			return false;
	 	 }
	 	 
	 	 */
	 	 
	 	 if(eval(frmAmount) > eval(toAmount)){
  			alert("Please  Enter To Amount Greater Than From Amount ");
  			return false;
  		}	
	 	 
	 	//  alert("with in formValidate returning true");
 		return true;
 		
 }
   
   
   </script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>



