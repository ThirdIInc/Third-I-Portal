<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="SalaryPlan" validate="true" id="paraFrm" validate="true"
	theme="simple">

	<s:hidden name="salCode" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td colspan="3" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">

				<tr>
					<td colspan="1" width="10"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td colspan="1" width="80%" class="txt"><strong
						class="text_head">Salary Plan </strong></td>

					<td colspan="1" width="10" valign="top" class="txt"><div align="right"><img
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
					<td colspan="1" width="15%"><label class="set" name="zip_no"
						id="zip_no" ondblclick="callShowDiv(this);"><%=label.get("zip_no")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textarea cols="40" rows="3"
						name="zipCode" onkeypress="return isNumberKey(event)" /></td>
				</tr>
				<tr>
					<td colspan="1" width="15%"><label class="set"
						name="salary_plan" id="salary_plan"
						ondblclick="callShowDiv(this);"><%=label.get("salary_plan")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="38"
						theme="simple" maxlength="6" name="salaryPlan"
						onkeypress="return isNumWithCharKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="2" width="100%"><font color="red">* </font>Zip
					Code must be comma seperated & three digits only</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		</table>
</s:form>

<script>
var counter =0;
function addColumn(tblId) {

	var tblHeadObj = document.getElementById(tblId).tHead;
	
	
	for (var h=0; h<tblHeadObj.rows.length; h++) {
		var newTH = document.createElement('th');
		tblHeadObj.rows[h].appendChild(newTH);
		if(counter<=4){
			newTH.innerHTML = '<input type="text" name="textValue" size="5" maxLength="3"/>';
		}else{
			newTH.innerHTML = '<input type="text" name="textValue" size="5" maxLength="3"/>';
		}
	}
	counter++;
}
	
	//Add textfield on Add Button
	 function addTextbox() {
	 	var count = document.getElementById('counter').value=5;
	  	var tbl = document.getElementById('addBox');
	  	 var lastRow = tbl.rows.length;
	  	 var row = tbl.insertRow(lastRow);
	  	var a ="";
		var j = "";
	  	 for(i=0,j=0;i<count;i++){
	  	 		j = document.createElement('input');
				a = row.insertCell(i);
				j.setAttribute('type', 'text');
				j.setAttribute('size', '5');
				j.maxLength='3';
				a.appendChild(j);
	  	 }
   }  

    function saveFun() {
	var zipCode1 =  document.getElementById('paraFrm_zipCode').value; 
 	var salaryPlan1 =  document.getElementById('paraFrm_salaryPlan').value; 
	
	 if(zipCode1 == ""){
			 alert("Please Enter Zip");
		  return false;
		}
	
		if(!(zipCode1==""))
  		{
  			
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < zipCode1.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(zipCode1.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==zipCode1.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
	 if(salaryPlan1 == ""){
			 alert("Please Enter Salary Plan in %");
		  return false;
		}
	
	if(!(salaryPlan1==""))
  		{
  			
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < salaryPlan1.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(salaryPlan1.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==salaryPlan1.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
   
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SalaryPlan_saveSalaryPlan.action';
		document.getElementById('paraFrm').submit();
  			
  }	 
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SalaryPlan_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SalaryPlan_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SalaryPlan_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SalaryPlan_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
		
		function editFun() {
		return true;
		
	}		
	
	/* This Function accepts only numbers with comma*/
function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
            ///alert("charCode"+charCode);
        if(charCode!=44 )  
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57) || (charCode==44) && (charCode==46) && (charCode==37))
                return false;
             }
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
	 
	
	/*Function only Characters in small & capital letters*/
function isCharactersKey(evt)
	 {
	/*alert("isCharKey");*/
         var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=65)
            {
             if (charCode > 31 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
                return false;
             }
             return true;
      
      }		 
	 
	
	
	
	
		 function charactersOnly(e){
		/* alert("char only");*/
	document.onkeypress = charactersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91)|| key == 8 || key == 32 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}
		 
</script>
