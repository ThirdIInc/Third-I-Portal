<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<script type="text/javascript">
var records = new Array();
</script>

<s:form action="VendorMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="vendorCode" />
	
	
	<%
		String check1 = "";

		try {

			if (request.getAttribute("sendPO") != null) {
				check1 = (String) request.getAttribute("sendPO");
			} else {
				check1 = "";
			}

			System.out.println("JSP check1 value is:---" + check1);

		} finally {

			if (check1 == null) {
				check1 = "";
			}
		}
	%>



	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt" colspan="4">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td colspan="1" width="4%"><strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
					<td colspan="1" width="93%" class="txt"><strong class="text_head">Vendor </strong></td>
					<td colspan="2" width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%" colspan="1"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%" colspan="3"><div align="right"><font color="red">*</font> Indicates Required</div></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="vendor_name" id="vendor_name"
						ondblclick="callShowDiv(this);"><%=label.get("vendor_name")%></label>
					<font color="red">*</font>:</td>
					<td width="30%" colspan="1"><s:textfield size="31"
						theme="simple" maxlength="50" name="vendorName"  onkeypress="return isCharactersKey(event)" /></td>
					<td colspan="1" width="20%"><label class="set" name="ein"
						id="ein" ondblclick="callShowDiv(this);"><%=label.get("ein")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="10" name="einNumber"
						onkeypress="return isNumberKey(event)" /></td>

				</tr>
				
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="po_address" id="po_address" ondblclick="callShowDiv(this);"><%=label.get("po_address")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textarea
						name="postboxAddress" cols="33" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_postboxAddress','po_address','', '', '2000','2000');">

					<td colspan="1" width="20%"><label class="set" name="zip_code"
						id="zip_code" ondblclick="callShowDiv(this);"><%=label.get("zip_code")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="6" name="zip"
						onkeypress="return isNumberKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" name="city"
						id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:hidden name="cityId" /><s:textfield
						size="31" theme="simple" maxlength="30" name="city"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'VendorMaster_f9city.action');"
						id="ctrlHide"></td>
					<td colspan="1" width="20%"><label class="set" name="state"
						id="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="30" name="state" readonly="true" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="phone_number" id="phone_number"
						ondblclick="callShowDiv(this);"><%=label.get("phone_number")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield size="31" theme="simple" maxlength="15" name="phoneNumber" onkeypress="return isphoneNumber(event)"/></td>

					<td colspan="1" width="20%">Send P.O :</td>
					<td colspan="1" width="30%"><input type="radio" name="sendPO"
						id="yes" onclick="setRadioValue(this);" value="Y"
						<%=check1.equals("Y")?"checked":"" %> />&nbsp;<label class="set"
						name="yes" id="yes" ondblclick="callShowDiv(this);"><%=label.get("yes")%></label>&nbsp;&nbsp;&nbsp;<input
						type="radio" name="sendPO" id="no" onclick="setRadioValue(this);"
						value="N" <%=check1.equals("N")?"checked":"" %> />&nbsp;<label
						class="set" name="no" id="no" ondblclick="callShowDiv(this);"><%=label.get("no")%></label></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="contact_name" id="contact_name"
						ondblclick="callShowDiv(this);"><%=label.get("contact_name")%></label>
					:<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="50" name="contactName" onkeypress="return isCharactersKey(event)"  /></td>

					<td colspan="1" width="20%"><label class="set"
						name="remit_name" id="remit_name" ondblclick="callShowDiv(this);"><%=label.get("remit_name")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield name="remitName"
						size="31" theme="simple" maxlength="50" onkeypress ="return isCharactersKey(event)"/></td>
				</tr>
				
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="remit_address" id="remit_address"
						ondblclick="callShowDiv(this);"><%=label.get("remit_address")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textarea
						name="remitAddress" cols="33" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_remitAddress','remit_address','', '', '2000','2000');"></td>
					<td colspan="1" width="20%"><label class="set" name="zip_code"
						id="zip_code" ondblclick="callShowDiv(this);"><%=label.get("zip_code")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="6" name="remitZip"
						onkeypress="return isNumberKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" name="city"
						id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>:</td>
					<td colspan="1" width="30%"><s:hidden name="remitCityId" /><s:textfield
						size="31" theme="simple" maxlength="30" name="remitCity"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'VendorMaster_f9remitcity.action');"
						id="ctrlHide"></td>

					<td colspan="1" width="20%"><label class="set" name="state"
						id="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="30" name="remitState" readonly="true" /></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="disc_percent" id="disc_percent"
						ondblclick="callShowDiv(this);"><%=label.get("disc_percent")%></label>:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="10" name="discPercent"
						onkeypress="return isNumWithCharKey(event)" /></td>
					<td colspan="1" width="20%"><label class="set" name="net_days"
						id="net_days" ondblclick="callShowDiv(this);"><%=label.get("net_days")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="10" name="netDays"
						onkeypress="return isNumberKey(event)" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="20%"><label class="set" name="comments"
						id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>:</td>
					<td colspan="1" width="30%"><s:textarea
						name="comments1" cols="33" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_comments1','comments','', '', '2000','2000');"></td>
					<td colspan="1" width="20%"><label class="set"
						name="class_code" id="class_code" ondblclick="callShowDiv(this);"><%=label.get("class_code")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="5" name="classCode" /></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="minnimum_order" id="minnimum_order"
						ondblclick="callShowDiv(this);"><%=label.get("minnimum_order")%></label>:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="15" name="minOrder"
						onkeypress="return isNumberWithDollerKey(event)" /></td>
				
					<td colspan="1" width="20%"><label class="set"
						name="freight_msg" id="freight_msg"
						ondblclick="callShowDiv(this);"><%=label.get("freight_msg")%></label>:</td>
					<td colspan="1" width="30%"><s:textarea
						name="freightMessage" cols="33" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_freightMessage','freight_msg','', '', '2000','2000');"></td>
				</tr>
				
				<tr>
					<td colspan="1" width="20%"><label class="set" name="tax_msg"
						id="tax_msg" ondblclick="callShowDiv(this);"><%=label.get("tax_msg")%></label>:</td>
					<td colspan="1" width="30%"><s:textarea
						name="taxMessage" cols="33" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_taxMessage','tax_msg','', '', '2000','2000');"></td>

					<td colspan="1" width="20%"><label class="set"
						name="ship_root" id="ship_root" ondblclick="callShowDiv(this);"><%=label.get("ship_root")%></label>:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="50" name="shipVia" onkeypress ="return isCharactersKey(event)"/></td>
				</tr>
			
				<tr>
					<td colspan="1" width="20%"><label class="set" name="fob"
						id="fob" ondblclick="callShowDiv(this);"><%=label.get("fob")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="31"
						theme="simple" maxlength="50" name="fob" /></td>
					<td colspan="1" width="20%"><label class="set" name="comments"
						id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>:</td>
					<td colspan="1" width="30%"><s:textarea
						name="comments2" cols="33" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_comments2','comments','', '', '2000','2000');"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	
	</table>

<s:hidden name="radioValue" id="radioValue" value="<%=check1%>" />
</s:form>

<script>


function setRadioValue(id){
/*alert("setRedio");
alert("id");*/

		var opt=document.getElementById('radioValue').value =id.value;	
	}

 function saveFun()
{

    var vendorName = document.getElementById('paraFrm_vendorName').value;
  	var einNumber = document.getElementById('paraFrm_einNumber').value;
  	var postboxAddress = document.getElementById('paraFrm_postboxAddress').value;
  	var zip = document.getElementById('paraFrm_zip').value;
  	var city = document.getElementById('paraFrm_city').value;
  	var phoneNumber = document.getElementById('paraFrm_phoneNumber').value;
  	var contactName = document.getElementById('paraFrm_contactName').value;
 
  	
  	
  	if(vendorName=="")
  	{
  	alert("Please Enter Vendor Name");
  	return false;
  	
  	}

 if(!(vendorName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < vendorName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(vendorName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==vendorName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	
  		}

  if(einNumber=="")
{
alert("Please Enter EIN Number");
return false;
}

  if(postboxAddress=="")
{
alert("Please Enter the Send P.O Addres");
return false;
}

  if(!(postboxAddress==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < postboxAddress.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(postboxAddress.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==postboxAddress.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	
  		}


  if(zip=="")
{
alert("Please Enter ZIP");
return false;
}

  if(!(zip==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < zip.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(zip.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==zip.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	
  		}
  		
  	if(city=="")
  	{
  	alert("Please Select City");
  	return false;
  	
  	}
  		
  if(phoneNumber=="")
{
alert("Please Enter Phone Number");
return false;
}		
  		

 if(contactName=="")
{
alert("Please Enter Contact Name");
return false;
}

 if(!(contactName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < contactName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(contactName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==contactName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	
  		}

 	
      	 document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'VendorMaster_saveVendorData.action';
		document.getElementById('paraFrm').submit();
  	  	
  	
  }	
  
  function resetFun() {
		
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'VendorMaster_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	   
  	}
	
   function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VendorMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	
	 function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VendorMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	return true;
	}
  
   function editFun() {
	return true;
	}
  
  /*Function which accepts only numbers with dot & % char*/
function isNumWithCharKey(evt)
	 {
	/* alert("Only Numbers and Special Characters are allowed here!");*/
         var charCode = (evt.which) ? evt.which : event.keyCode
            ///alert("charCode"+charCode);
        if((charCode!=37 ) &&(charCode!=46) )
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57) || (charCode==44) && (charCode==46) && (charCode==37))
                return false;
             }
             return true;
      
      
      }		 
  
  
  
   //Text Area 
	 function enterMaxLength(Event, Object, MaxLen)
	{
	
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
  
  
 	/* phone validations*/
	function isphoneNumber(evt)
	{
	var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=39)
            {
              if (charCode > 31 && (charCode < 40 || charCode > 57))
                return false;
             }
             return true;
	}
  
  
  /*Function only Characters in small & capital letters*/
function isCharactersKey(evt)
	 {
	
         var charCode = (evt.which) ? evt.which : event.keyCode
            
          //  alert("charCode--"+charCode);
            
            if(charCode!=65)
            {
             if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode==34 ) || (charCode==39) || (charCode==63) )
                return false;
             }
             return true;
      
      }		 
	 	
  
   function deleteFun() {
	/*alert("in deleteFun");*/
		var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		
		
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VendorMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	 /*This function used for only name where name comes call this function,it restricts only ", ' , \ and allow all special chars*/
function isCharactersKey(evt)
	 {
	
         var charCode = (evt.which) ? evt.which : event.keyCode
            
          //  alert("charCode--"+charCode);
            
            if(charCode!=65)
            {
             if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode==34 ) || (charCode==39) || (charCode==63) ||(charCode==92) )
                return false;
             }
             return true;
      
      }		
	
  
 function numbersOnly(e){
 
	document.onkeypress = numbersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}
/*Function which accepts only numbers with $,chars A-Z & a-z*/
function isNumberWithDollerKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
            
            if((charCode!=36) && (charCode!=32))
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57) && (charCode < 65 || charCode > 90)  && (charCode < 97 || charCode > 122))
                return false;
             }
             return true;
      
      }		 



	 /* Numvers Only Function*/
function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=46)
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
             }
             return true;
      
      }		 
	 
/*Function only Characters in small & capital letters*/
function isCharactersKey(evt)
	 {
	
         var charCode = (evt.which) ? evt.which : event.keyCode
            
          //  alert("charCode--"+charCode);
            
            if(charCode!=65)
            {
             if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode==34 ) || (charCode==39) || (charCode==63) )
                return false;
             }
             return true;
      
      }		 
	 	
	 	 
	 
	 
	 /*Phone Validation Function*/
function validatePhoneNo(e){

	document.onkeypress = validatePhoneNo;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 47 && key < 58) || key == 8 || key == 40 || key == 41 
		|| key == 43 || key == 45 || key == 44 || key == 32 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}
	 
	 
</script>

