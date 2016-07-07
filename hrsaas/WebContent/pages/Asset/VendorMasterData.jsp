<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="VenueMaster" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="rowId" />
	<s:hidden name="modeLength" />
	<s:hidden name="hiddencode" />


	<table width="100%" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Vendor
					Master </strong></td>
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
			<table width="100%">
				<tr>

					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right"><font color="red">*</font> Indicates
					Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Vendor Master </strong></td>
				</tr>
				<tr>
					<s:hidden name="vendorCode" />
					<td width="15%" colspan="1"><label class="set"
						name="vendor.name" id="vendor.name"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.name")%></label>
					<font color="red">*</font> :</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						name="vendorName" size="35" maxlength="40" /></td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="vendor.address" id="vendor.address"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.address")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2" width="54%"><s:textarea theme="simple"
						name="vendorAdd" cols="31" rows="3" /></td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="vendor.contno" id="vendor.contno"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.contno")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						name="vendorCon" size="35" maxlength="14"
						onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="vendor.email" id="vendor.email"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.email")%></label>:
					</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						name="vendorEmail" size="35" maxlength="40"
						onkeypress="return emailCheck();" /></td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="vendor.city" id="vendor.city"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.city")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						name="vendorCty" size="35" maxlength="40" readonly="true" /> <s:hidden
						theme="simple" name="ctyId" /> <s:hidden name="locParentCode" />
					<img id='ctrlHide'
						src="../pages/common/css/default/images/search2.gif" width="16"
						height="15" onclick="clearState();"></td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="vendor.state" id="vendor.state"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.state")%></label>:
					</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						name="vendorState" readonly="true" size="35" maxlength="40" /> <s:hidden
						theme="simple" name="stateId" />
				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="vendor.pincode" id="vendor.pincode"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.pincode")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2" width="55%" colspan="2"><s:textfield size="35"
						theme="simple" maxlength="12" onkeypress="return numbersOnly();"
						name="pinCode" /></td>
				</tr>
				
				<tr>
					<td width="20%" height="22" class="formtext"><label
						name="vendor.type" class="set" id="vendor.type"
						ondblclick="callShowDiv(this);"><%=label.get("vendor.type")%></label>
					:</td>
					<td><s:select name="vendortype" list="#{'S':'Supplier','E':'External Vendor'}"
						cssStyle="width:151;z-index:5;" /></td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>



				</tr>

				<tr>
					<td width="20%" height="22" class="formtext"><label
						name="is.active" class="set" id="is.active"
						ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					:</td>
					<td><s:select name="isActive" list="#{'Y':'Yes','N':'No'}"
						cssStyle="width:151;z-index:5;" /></td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>



				</tr>










			</table>
			</td>
		</tr>



		<tr>
			<td colspan="3" width="100%">
			<table width="100%">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>



	</table>





</s:form>

<script type="text/javascript">

var f9Fields=["paraFrm_vendorName"];
var fieldName = ["paraFrm_vendorName","paraFrm_vendorAdd","paraFrm_vendorCon","paraFrm_vendorCty","paraFrm_pinCode"];
var lableName = ["vendor.name","vendor.address","vendor.contno","vendor.city","vendor.pincode"];
var types = ["enter","enter","enter","select","enter"];


function saveFun()
 {

         if(!validateBlank(fieldName,lableName,types))
          return false;
        else if(!validateEmail('paraFrm_vendorEmail'))
        {
        	document.getElementById('paraFrm_vendorEmail').focus()
        	return false;        
        }
			
    	else if(!f9specialchars(f9Fields))
		return false;
		
          
  	    document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='VendorMaster_save.action';
     	document.getElementById('paraFrm').submit(); 
       
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';  	 
  	 	document.getElementById('paraFrm').action = 'VendorMaster_reset.action';
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VendorMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VendorMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'VendorMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}	
		
function numbersonly(myfield){
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
	
	
function clearState()
{
document.getElementById('paraFrm_vendorState').value="";
javascript:callsF9(500,325,'VendorMaster_f9city.action');
}	
 	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>