<!--Dilip Dewangan-->
<!--Aug 26, 2009-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="DivisionMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="divMast.divId" />
	<s:hidden name="divMast.locId" />
	<table width="100%" border="0" align="right" class="formbg" cellpadding="2"
				cellspacing="2">

		<tr>
			<td width="100%" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Division 
					</strong></td>
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
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="25%" align="right"><span class="style2"><font
						color="red">*</font></span>Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>

					<td width="20%"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:<font color="red">*</font></td>
					<td colspan="2" width="25%"><s:textfield theme="simple"
						name="divMast.divName" size="30" maxlength="50" /></td>
					<td width="10%"></td>
					<td width="20%"><label class="set" name="divAbbr" id="divAbbr"
						ondblclick="callShowDiv(this);"><%=label.get("divAbbr")%></label>:</td>
					<td colspan="2" width="25%"><s:textfield theme="simple"
						name="divMast.divAbbr" size="30" maxlength="15" /></td>

				</tr>
				
				<tr>

					<td width="20%"><label class="set" name="divisionDisplayName"
						id="divisionDisplayName" ondblclick="callShowDiv(this);"><%=label.get("divisionDisplayName")%></label>
					:<font color="red">*</font></td>
					<td colspan="2" width="25%"><s:textfield theme="simple"
						name="divisionDisplayName" size="30" maxlength="50" /></td>
					<td width="10%"></td>
					<td width="20%"></td>
					<td colspan="2" width="25%"></td>

				</tr>
				
				<tr>
					<td><label name="divisiondesc" class="set" id="divisiondesc"
						ondblclick="callShowDiv(this);"><%=label.get("divisiondesc")%>
					</label>:<font color="red">*</font></td>
					<td colspan="3"><s:textarea name="divMast.divDesc" cols="68"
						rows="3" onkeyup="callLength('descCnt');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_divMast_divDesc','divisiondesc','','200','200');"></td>
					
					<td colspan="2" id='ctrlHide'>
						Remaining chars<s:textfield name="descCnt" readonly="true" size="5"></s:textfield>
					</td>
				</tr>
				<tr>

					<td><label class="set" name="address" id="address"
						ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
					:<font color="red">*</font></td>
					<td colspan="6"><s:textfield size="30" theme="simple"
						name="divMast.add1" maxlength="90" /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="6"><s:textfield size="30" theme="simple"
						name="divMast.add2" maxlength="90" /></td>
				</tr>
				<tr>

					<td></td>
					<td colspan="6"><s:textfield size="30" theme="simple"
						name="divMast.add3" maxlength="90" /></td>
				</tr>
				<tr>
					<td><label class="set" name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font
						color="red">*</font></td>
					<td colspan="2">
						<s:textfield size="30" theme="simple" name="divMast.city" readonly="true" />
						<s:if test="divMast.viewFlag">
							<img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" onclick="callSearch('f9city');">
						</s:if>	
					</td>
					
					<td></td>
					<td><label class="set" name="pincode" id="pincode"
						ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label>
					:<font color="red">*</font></td>
					<td colspan="2"><s:textfield size="30" theme="simple"
						name="divMast.pin" onkeypress="return numbersOnly();"
						maxlength="6" /></td>
				</tr>
				<tr>
					<td><label class="set" name="telephone" id="telephone"
						ondblclick="callShowDiv(this);"><%=label.get("telephone")%></label>:
					</td>
					<td colspan="2"><s:textfield size="8" theme="simple"
						name="divMast.telStd" onkeypress="return numbersOnly();"
						maxlength="15" onfocus="clearText('paraFrm_divMast_telStd','STD Code')"
							onblur="setText('paraFrm_divMast_telStd','STD Code')"/> <s:textfield size="15" theme="simple"
						name="divMast.tel" onkeypress="return numbersOnly();"
						maxlength="15" /></td>
					<td></td>
					<td><label class="set" name="website" id="website"
						ondblclick="callShowDiv(this);"><%=label.get("website")%></label>
					:</td>
					<td colspan="2"><s:textfield theme="simple"
						name="divMast.website" size="30" maxlength="100" /></td>
				</tr>
				<tr>
					<td><label class="set" name="email" id="email"
						ondblclick="callShowDiv(this);"><%=label.get("email")%></label><font color="red">*</font>:
					</td>
					<td colspan="3">
						<s:textfield theme="simple"	name="divMast.emailAdress" size="30"/>
					</td>
					<td width="20%">
						<label name="logo" id="logo" ondblclick="callShowDiv(this);"><%=label.get("logo")%></label>	:
					</td>
					<td colspan="1">
						<s:textfield size="20" name="logoName" readonly="true" />
					</td>
					<td>
						<input type="button" class="token" name="Browse" value="Browse" onclick="uploadFile('logoName');" />
					</td>
				</tr>
				<tr>
					<td ><label class="set" name="is.active"
						id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>:
					</td>
					<td colspan="3"><s:select cssStyle="width:100"
						list=" #{'Y':'Yes','N':'No'}" name="divMast.isActive" /></td>
					
				</tr>
				
				<!-- Updated by Anantha lakshmi -->
				
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td width="100%" colspan="6"><b align="left">Employer
					Details</b></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td width="15%"><label class="set" name="ename"
						id="ename" ondblclick="callShowDiv(this);"><%=label.get("ename")%></label>
					:</td>
					<td width="25%" colspan="2"><s:textfield size="30"
						maxlength="35" name="divMast.ename" /></td>
					<td width="14%"></td>
					<td width="15%" ><label class="set"
						name="edesignation" id="edesignation"
						ondblclick="callShowDiv(this);"><%=label.get("edesignation")%></label>
					:</td>
					<td width="25%" colspan="2"><s:textfield size="30"
						maxlength="35" name="divMast.edesignation" /></td>

				</tr>
				<tr>
					<td width="15%" ><label class="set" name="eteleNo"
						id="eteleNo" ondblclick="callShowDiv(this);"><%=label.get("eteleNo")%></label>
					:</td>
					<td width="25%" colspan="2">
					<s:textfield size="8" theme="simple"
						name="divMast.etelStd" onkeypress="return numbersOnly();"
						maxlength="15" onfocus="clearText('paraFrm_divMast_etelStd','STD Code')"
							onblur="setText('paraFrm_divMast_etelStd','STD Code')"/> <s:textfield size="15" theme="simple"
						name="divMast.eteleNo" onkeypress="return numbersOnly();"
						maxlength="15" /></td>
					<td width="14%"></td>
					
					<td width="15%" ><label class="set" name="emobNo"
						id="emobNo" ondblclick="callShowDiv(this);"><%=label.get("emobNo")%></label>
					:</td>
					<td width="25%" colspan="2"><s:textfield size="30"
						maxlength="10" name="divMast.emobNo"
						onkeypress="return numbersOnly()" /></td>
				</tr>
				<tr>
					<td width="15%"><label class="set" name="efax"
						id="efax" ondblclick="callShowDiv(this);"><%=label.get("efax")%></label>
					:</td>
					<td width="25%" colspan="2"><s:textfield size="30"
						maxlength="35" name="divMast.efax" onkeypress="return numbersOnly()" /></td>
					<td width="14%"></td>
					<td width="15%" ><label class="set" name="eemail"
						id="eemail" ondblclick="callShowDiv(this);"><%=label.get("eemail")%></label>
					:</td>
					<td width="25%" colspan="2"><s:textfield size="30"
						maxlength="35" name="divMast.eemail" /></td>
				</tr>
				
				<!-- Updated by Anantha lakshmi -->

			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	
	</table>
	
</s:form>


<script type="text/javascript">
	
function saveFun(){
		var tel =document.getElementById('paraFrm_divMast_tel').value;
		var etel =document.getElementById('paraFrm_divMast_eteleNo').value;
		var locId =document.getElementById('paraFrm_divMast_locId').value;
		var pin =document.getElementById('paraFrm_divMast_pin').value;
		var city =document.getElementById('paraFrm_divMast_city').value;
		var web =document.getElementById('paraFrm_divMast_website').value;
		var desc =document.getElementById('paraFrm_divMast_divDesc').value;
		var stdCode =document.getElementById('paraFrm_divMast_telStd').value;
		var eStdCode=document.getElementById('paraFrm_divMast_etelStd').value;
		var fieldName = ["paraFrm_divMast_divName","paraFrm_divisionDisplayName","paraFrm_divMast_divDesc","paraFrm_divMast_add1","paraFrm_divMast_city","paraFrm_divMast_pin","paraFrm_divMast_emailAdress"];
		var lableName = ["division","divisionDisplayName","divisiondesc","address","city","pincode","email"];
		var flag = ["enter","enter","enter","enter","select","enter","enter" ];
		var fieldName1 = ["paraFrm_divMast_divName"];
		var pincode=document.getElementById('pincode').innerHTML.toLowerCase();
		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
        
      	if(!f9specialchars(fieldName1)){
           	return false;
   		}
   		
     
    	if(!(pin=="")){ 
			var iChars = "0123456789";
	  		for (var i = 0; i < pin.length; i++){		  	
		    	if (!(iChars.indexOf(pin.charAt(i)) != -1)){ 
		  	 		alert ("Please Enter Number Only in"+pincode);
			  		return false;				  	
  				} 
  			}
		}
	 		
		if(!(locId=="")){
			var iChars = "0123456789";
	  		for (var i = 0; i < locId.length; i++) {			
		  		if (!(iChars.indexOf(locId.charAt(i)) != -1)) {
					alert ("Division City Code should be Number !");
					return false;
  				}
  			}
		}
		if(stdCode!="STD Code" && stdCode!=""){
			if((tel=="")){
					alert ("Please Enter Telephone Number. ");
					document.getElementById('paraFrm_divMast_tel').focus();
			  		return false;
			  		}
		}
		if(eStdCode!="STD Code" && eStdCode!=""){
			if((etel=="")){
					alert ("Please Enter Telephone Number. ");
					document.getElementById('paraFrm_divMast_eteleNo').focus();
			  		return false;
			}
		}
		
  		if(!(tel=="")){
			var iChars = "0123456789-";
	 		for (var i = 0; i < tel.length; i++) {			
		  		if (!(iChars.indexOf(tel.charAt(i)) != -1)) {
			  		alert ("Please Enter Number Only in Telephone Number !");
			  		return false;
  				}
  			}
		}
		if(tel!=""){
			if(stdCode=="STD Code" || stdCode==""){
					alert ("Please Enter STD code for Telephone Number. ");
					document.getElementById('paraFrm_divMast_telStd').focus();
			  		return false;
			}
		}
		
		if(!(etel=="")){
			var iChars = "0123456789-";
	 		for (var i = 0; i < etel.length; i++) {			
		  		if (!(iChars.indexOf(etel.charAt(i)) != -1)) {
			  		alert ("Please Enter Number Only in Telephone Number !");
			  		return false;
  				}
  			}
		}
		if(etel!=""){
			if(eStdCode=="STD Code" || eStdCode==""){
					alert ("Please Enter STD code for Telephone Number. ");
					document.getElementById('paraFrm_divMast_etelStd').focus();
			  		return false;
			}
		}
		
		
		/* Validation format for website */
		var chkStart = web.substring(0,4);
		if(chkStart.length!=0)
		{
		if(!(chkStart=="www." || chkStart=="WWW."))
		{
			alert("WebSite Name Should Start with www. ");
			return false;
		}
	}
		

	var length_Desc=200;
 	var divDesc =document.getElementById('paraFrm_divMast_divDesc').value;
	if (eval(divDesc.length)> 200)
    {
      alert('Description field accepts only ' + length_Desc +' characters. Please remove ' + (divDesc.length - length_Desc) + ' characters.');
      return false; 
  	}
  	
  	var abc=validateEmail('paraFrm_divMast_emailAdress');
	if(!abc){
		return false;
	}
	
	var abc=validateEmail('paraFrm_divMast_eemail');
	if(!abc){
		return false;
	}
	
	var stdCode =document.getElementById('paraFrm_divMast_telStd').value;
  	if(stdCode=="STD Code"){
  		document.getElementById('paraFrm_divMast_telStd').value="";
  	}
  	if(eStdCode=="STD Code"){
  		document.getElementById('paraFrm_divMast_etelStd').value="";
  	}
  	
  	document.getElementById('paraFrm').target = "_self";
  	document.getElementById('paraFrm').action = 'DivisionMaster_save.action';
	document.getElementById('paraFrm').submit();
  	return true ;
  			
  }

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'DivisionMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DivisionMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DivisionMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'DivisionMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		setText('paraFrm_divMast_telStd','STD Code');
		setText('paraFrm_divMast_etelStd','STD Code');
		//Update by Anantha lakshmi
		
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'DivisionMaster_getRecords.action';
		document.getElementById('paraFrm').submit();
		return true;
	}
	
	function callLength(type){ 
		 if(type=='descCnt'){
				var cmt =document.getElementById('paraFrm_divMast_divDesc').value;
				var remain = 200 - eval(cmt.length);
				document.getElementById('paraFrm_descCnt').value = remain;
				if(eval(remain)< 0){
					document.getElementById('paraFrm_divMast_divDesc').style.background = '#FFFF99';
				}else document.getElementById('paraFrm_divMast_divDesc').style.background = '#FFFFFF';
		}
	}  
				
function uploadFile(fieldName) 
	{
		var path="upload";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName+'&image='+"image",'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}  	
</script>