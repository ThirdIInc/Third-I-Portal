<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Nilesh Dhandare  -->
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="BusinessUnit" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

<!-- Hidden Field -->
<s:hidden name="businessCode" />
<!--End of Hidden Field -->

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">

				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Business
					Unit </strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>

					<td width="80%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%" colspan="1"> 
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td colspan="1" width="15%" ><label class="set" name="business_unit_code"
						id="business_unit_code" ondblclick="callShowDiv(this);"><%=label.get("business_unit_code")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="25" theme="simple" maxlength="10"
						name="unitCode" onkeypress="return isNumberKey(event)" /></td>
				</tr>
				<tr>
					<td colspan="1"width="15%"><label class="set" name="business_unit_name"
						id="business_unit_name" ondblclick="callShowDiv(this);"><%=label.get("business_unit_name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="25" theme="simple" maxlength="50"
						name="unitName"  onkeypress="return isCharactersKey(event)"/></td>
				</tr>				
				<tr>
					<td colspan="1"width="15%"><label class="set" name="business_emp"
						id="business_emp" ondblclick="callShowDiv(this);"><%=label.get("business_emp")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="25" theme="simple" maxlength="50"
						name="empToken"/><s:textfield size="25" theme="simple" maxlength="50"
						name="empName"/><s:hidden name="empID"/><img
						src="../pages/common/css/default/images/search2.gif"
						class="iconImage" width="16" height="15" id="ctrlHide"
						onclick="callsF9(325,325,'BusinessUnit_f9EmpAction.action');" /></td>
				</tr>				
				<tr>
				<!--<td colspan="1" width="25%"><label class="set" name="business_unit_div"
						id="business_unit_div" ondblclick="callShowDiv(this);"><%=label.get("business_unit_div")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="75%"><s:hidden name="divId"/><s:textfield size="40" theme="simple" maxlength="30"
						name="unitDivision" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'BusinessUnit_f9division.action');"
						id="ctrlHide"></td> -->

				</tr>


			</table>
			</td>
		</tr>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	
	
	</table>

</s:form>
<script>
function saveFun(){
   var unitCode =  document.getElementById('paraFrm_unitCode').value; 
   var unitName =  document.getElementById('paraFrm_unitName').value; 
   var empCode  =  document.getElementById('paraFrm_empToken').value;
   // var unitDivision =  document.getElementById('paraFrm_unitDivision').value; 		
   if(unitCode==""){
		alert("Please enter Business Unit Code");
		return false;
   }	      	
   if(!(unitCode=="")){
  		var count =0;
		var iChars =" ";
		for (var i = 0; i < unitCode.length; i++){				  			 
			if (iChars.indexOf(unitCode.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  			}
  		}
  		if(count==unitCode.length){
  			alert ("Blank Spaces Not Allowed");
  			return false;
  		}  		
  	}	     
    if(unitName==""){
		alert("Please enter Business Unit Name");
		return false;
	}	
    if(empCode==""){
			 alert("Please select Employee");
		  return false;
	}	
    document.getElementById('paraFrm').target = "_self";
  	document.getElementById('paraFrm').action = 'BusinessUnit_saveVendorData.action';
	document.getElementById('paraFrm').submit();  			
  }	 
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'BusinessUnit_reset.action';
     	document.getElementById('paraFrm').submit();      	    
  	}
	
	function cancelFun() {	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'BusinessUnit_cancel.action';
		document.getElementById('paraFrm').submit();	
	}
	
	function backtolistFun() {	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'BusinessUnit_cancel.action';
		document.getElementById('paraFrm').submit();	
	}
	
	
function deleteFun() {
	  var con=confirm('Do you want to delete the record(s) ?');
	  if(con){
		 document.getElementById('paraFrm').target = "_self";
      	 document.getElementById('paraFrm').action = 'BusinessUnit_delete.action';
		 document.getElementById('paraFrm').submit();
	  }
  }
  
function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'BusinessUnit_'+action+'.action';
		document.getElementById("paraFrm").submit();
  }	
		
		
function editFun() {
 	return true;	
  }		
	
	
/* Numvers Only Function*/
function isNumberKey(evt){
     var charCode = (evt.which) ? evt.which : event.keyCode            
     if(charCode!=46){
          if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
          }
          return true;
     }		 
	 
/*This function used for only name where name comes call this function,it restricts only ", ' , \ and allow all special chars*/

function isCharactersKey(evt){
	var charCode = (evt.which) ? evt.which : event.keyCode
    if(charCode!=44){
         if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode==34 ) || (charCode==39) || (charCode==63) ||(charCode==92) )
                return false;
     }
          return true;
  }
		 
</script>
