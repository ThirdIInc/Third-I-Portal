<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="VoucherMaster" validate="true" id="paraFrm" theme="simple">
<s:hidden name="show" />
<s:hidden name="myPage" id="myPage" />
<s:hidden name="rowId" />
<s:hidden name="modeLength"  />
<s:hidden name="hiddencode"  />

	
	<table width="100%" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Voucher Head</strong></td>
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
			<table width="100%" class="formbg">
				<tr>
					<td colspan="3">
					<table width="100%" cellspacing="2">
					<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">Voucher
					Head Master </strong><s:hidden name="voucherCode" /></td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><label  class = "set" name="voucher.head" id="voucher.head" ondblclick="callShowDiv(this);"><%=label.get("voucher.head")%></label> 
					<font color="red">*</font>
					:</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						name="voucherHead" size="35" maxlength="50"  onkeypress="return allCharacters();"/></td>
				</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>


	<tr>
			<td colspan="3" width="100%">
			<table width="100%">
				<tr><td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>



	</table>
	
	
	
	
	
</s:form>	
		
<script type="text/javascript">



function saveFun()
 {
		var f9Fields=["paraFrm_voucherHead"];
		var fieldName = ["paraFrm_voucherHead" ];
		var lableName = ["voucher.head"];
		var type = ['enter'];
          if(!validateBlank(fieldName,lableName,type)) 
          return false;
    	if(!f9specialchars(f9Fields))
		return false;
          
  	    document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='VoucherMaster_save.action';
     	document.getElementById('paraFrm').submit(); 
       
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';  	 
  	 	document.getElementById('paraFrm').action = 'VoucherMaster_reset.action';
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VoucherMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VoucherMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'VoucherMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}	
		

 	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>