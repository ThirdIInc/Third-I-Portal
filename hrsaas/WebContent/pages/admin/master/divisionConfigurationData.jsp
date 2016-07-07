<!--Dilip Dewangan-->
<!--Aug 26, 2009-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="DivisionConfiguration" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="divMast.divId" />

	<s:hidden name="divMast.locId" />


	<table width="100%" border="0" align="right" class="formbg" cellpadding="2"	cellspacing="2">
		<tr>
			<td width="100%" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Division Configuration
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
					<td><label name="divisiondesc" class="set" id="divisiondesc"
						ondblclick="callShowDiv(this);"><%=label.get("divisiondesc")%>
					</label>:<font color="red">*</font></td>
					<td colspan="4"><s:textarea name="divMast.divDesc" cols="68"
						rows="3" onkeyup="callLength('descCnt');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_divMast_divDesc','divisiondesc','','200','200');"></td>
					<td colspan="2" id='ctrlHide'>Remaining chars<s:textfield
						name="descCnt" readonly="true" size="5"></s:textfield></td>
				</tr>
		
		
		
				<tr>
					<td><label class="set" name="bank" id="bank"
						ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> :<font
						color="red">*</font></td>
					<td><s:textfield theme="simple" name="divMast.bank" size="30"
						readonly="true" /></td>
					<td><img id='ctrlHide' align="absmiddle"
						src="../pages/common/css/default/images/search2.gif"
						onclick="callSearch('f9bank');"> <s:hidden
						name="divMast.bankid" /></td>
					<td></td>
					<td><label class="set" name="accountno" id="accountno"
						ondblclick="callShowDiv(this);"><%=label.get("accountno")%></label>
					:<font color="red">*</font></td>
					<td colspan="2"><s:textfield theme="simple"
						onkeypress="return numbersOnly();" name="divMast.accno" size="30"
						maxlength="25" /></td>
				</tr>
				<tr>
					<td><label class="set" name="prtno" id="prtno"
						ondblclick="callShowDiv(this);"><%=label.get("prtno")%></label> :</td>
					<td colspan="2"><s:textfield theme="simple"
						name="divMast.protaxregNo" size="30" maxlength="70" /></td>
					<td></td>
					<td colspan="1"><label class="set" name="esizone" id="esizone"
						ondblclick="callShowDiv(this);"><%=label.get("esizone")%></label>
					:</td>
					<td colspan="2"><s:select cssStyle="width:152"
						list=" #{'Y':'YES','N':'NO'}" name="divMast.esiZone"
						headerValue="--Select--" headerKey="1" /></td>
				</tr>
				<tr>
					<td><label class="set" name="panno" id="panno"
						ondblclick="callShowDiv(this);"><%=label.get("panno")%></label> :<font
						color="red">*</font></td>
					<td colspan="2"><s:textfield theme="simple"
						name="divMast.panNo" size="30" maxlength="15" /></td>
					<td></td>
					<td><label class="set" name="tanno" id="tanno"
						ondblclick="callShowDiv(this);"><%=label.get("tanno")%></label> :<font
						color="red">*</font></td>
					<td colspan="2"><s:textfield theme="simple"
						name="divMast.tanNo" size="30" maxlength="15" /></td>
				</tr>
				<tr>
					
					<td><label class="set" name="acctGrpNo" id="acctGrpNo"
						ondblclick="callShowDiv(this);"><%=label.get("acctGrpNo")%></label>
					:</td>
					<td colspan="2"><s:textfield theme="simple"
						name="divMast.acctGrpNo" 
						size="30" maxlength="15" /></td>
					<td></td>
					<td><label class="set" name="pf.number" id="pf.number"
						ondblclick="callShowDiv(this);"><%=label.get("pf.number")%></label>	:<font color="red">*</font></td>
					<td colspan="2">
					   <s:textfield theme="simple" name="divMast.pfNumber" size="30" maxlength="15" />
					</td>
				</tr>
				<tr>
					<td>
						<label class="set" name="esi.number" id="esi.number" ondblclick="callShowDiv(this);"><%=label.get("esi.number")%></label>:<font color="red">*</font>
					</td>
					<td colspan="2">
						<s:textfield theme="simple"	name="divMast.esiNumber" size="30" maxlength="25" />	
					</td>
					<td></td>
					<td ><label class="set" name="estbCode"
						id="estbCode" ondblclick="callShowDiv(this);"><%=label.get("estbCode")%></label>:
					</td>
					<td colspan="2">
						<s:textfield theme="simple"	name="divMast.estbCode" size="30"/>
					</td>	
					
				</tr>
			  </table>
			</td>
		</tr>
		<tr>
			<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
						<tr><td>CIT Details</td></tr>
						<tr>
							<td width="15%"><label name="citAdd" id="citAdd" ondblclick="callShowDiv(this);"><%=label.get("citAdd")%></label>:</td>
							<td width="40%"colspan="1"><s:textarea cols="30" rows="2" name="citAddress" /></td>
							
							<td width="15%"><label name="citCityName" id="citCityName" ondblclick="callShowDiv(this);">
										   <%=label.get("citCityName")%></label>	:</td>
							<td colspan="1"width="30%">
								<s:hidden name="citCityId"></s:hidden>
								<s:textfield size="30"  name="citCity"  readonly="true" />
								<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18" id='ctrlHide' 
								onclick="javascript:callsF9(500,325,'DivisionConfiguration_f9citCity.action');">
								
							</td>
							
							
						</tr>
						<tr>
							<td width="15%"><label name="citPinCode1"	id="citPinCode1" ondblclick="callShowDiv(this);"><%=label.get("citPinCode1")%></label>:</td>
							<td colspan="1">&nbsp;<s:textfield size="30" name="citPinCode" id="citPinCode" /></td>
						</tr>
					</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include  page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	
	</table>
	
	
</s:form>


<script type="text/javascript">
	
function saveFun(){
		var fieldName = ["paraFrm_divMast_divName","paraFrm_divMast_divDesc","paraFrm_divMast_bank",
						"paraFrm_divMast_accno","paraFrm_divMast_panNo","paraFrm_divMast_tanNo",
						"paraFrm_divMast_pfNumber","paraFrm_divMast_esiNumber"];
		var lableName = ["division","divisiondesc","bank","accountno","panno","tanno","pf.number","esi.number"];
		var flag = ["enter","enter","select","enter","enter","enter","enter","enter"];
		
		var fieldName1 = ["paraFrm_divMast_divName"];
		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
        
      	if(!f9specialchars(fieldName1)){
           	return false;
   		}
		
		var length_Desc=200;
 		var divDesc =document.getElementById('paraFrm_divMast_divDesc').value;
		if (eval(divDesc.length)> 200)
		{
		  alert('Description field accepts only ' + length_Desc + 
		            ' characters. Please remove ' + (divDesc.length - length_Desc) + ' characters.');
		   return false; 
		 }
		
  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'DivisionConfiguration_save.action';
		document.getElementById('paraFrm').submit();
  	  	return true ;	
  }

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'DivisionConfiguration_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DivisionConfiguration_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		 var con=confirm('Do you want to delete the record(s) ?');
		 if(con){
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'DivisionConfiguration_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'DivisionConfiguration_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
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
				
</script>