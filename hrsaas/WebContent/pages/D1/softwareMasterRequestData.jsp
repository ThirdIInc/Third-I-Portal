<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Nilesh Dhandare -->
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="SoftwareRequest" validate="true" id="paraFrm"
	validate="true" theme="simple">

<!-- Hidden Fields Start-->
	<s:hidden name="softwareReqCode" />
<!-- Hidden Fields End-->

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">

				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Software Request </strong></td>

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
					<td colspan="1" width="15%" ><label class="set" name="software_code"
						id="software_code" ondblclick="callShowDiv(this);"><%=label.get("software_code")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="25" theme="simple" maxlength="10"
						name="softwareCode" onkeypress="return isNumberKey(event)" /></td>
				</tr>
				<tr>
					<td colspan="1"width="15%"><label class="set" name="software_name"
						id="software_name" ondblclick="callShowDiv(this);"><%=label.get("software_name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="25" theme="simple" maxlength="50"
						name="softwareName" onkeypress="return isCharactersKey(event)" /></td>
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
<!-- Scripting Start -->
<script>
    function saveFun()
{

  var softwareCode =  document.getElementById('paraFrm_softwareCode').value; 
  var softwareName =  document.getElementById('paraFrm_softwareName').value; 
  
	
	
	 if(softwareCode==""){
			 alert("Please Enter Software Code");
		  return false;
		}
	
      	
   	if(!(softwareCode==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < softwareCode.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(softwareCode.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==softwareCode.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
     
      if(softwareName==""){
			 alert("Please Enter Software Name");
		  return false;
		}
	
    	if(!(softwareName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < softwareName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(softwareName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==softwareName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
	
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SoftwareRequest_saveSoftwareReqData.action';
		document.getElementById('paraFrm').submit();
  			
  }	 
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SoftwareRequest_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SoftwareRequest_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SoftwareRequest_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SoftwareRequest_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'SoftwareRequest_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
		
		
		function editFun() {
		return true;
		
	}		
	 /*This function used for only name where name comes call this function,it restricts only ", ' , \ and allow all special chars*/

function isCharactersKey(evt)
	 {
	//alert("hi");
         var charCode = (evt.which) ? evt.which : event.keyCode
            
          //  alert("charCode--"+charCode);
            
            if(charCode!=44)
            {
             if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode==34 ) || (charCode==39) || (charCode==63) ||(charCode==92) )
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

<!-- Scripting Ends-->