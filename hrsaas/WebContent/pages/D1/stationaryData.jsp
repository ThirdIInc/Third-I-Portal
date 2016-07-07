<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Nilesh Dhandare -->
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="Stationary" validate="true" id="paraFrm"
	validate="true" theme="simple">

<!-- Hidden Fields Start-->
<s:hidden name="stationaryhiddenCode" />
<!-- Hidden Fields Start-->

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
					<td width="93%" class="txt"><strong class="text_head">Stationary </strong></td>

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
					<td colspan="1" width="15%" ><label class="set" name="stationary_code"
						id="stationary_code" ondblclick="callShowDiv(this);"><%=label.get("stationary_code")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="25" theme="simple" maxlength="10"
						name="stationaryCode" onkeypress="return isNumberKey(event)" /></td>
				</tr>
				<tr>
					<td colspan="1"width="15%"><label class="set" name="stationary_name"
						id="stationary_name" ondblclick="callShowDiv(this);"><%=label.get("stationary_name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="25" theme="simple" maxlength="50"
						name="stationaryName" onkeypress="return isCharactersKey(event)"/></td>
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


<!-- Scripting Start-->
<script>
    function saveFun()
{

  var stationaryCode =  document.getElementById('paraFrm_stationaryCode').value; 
  var stationaryName =  document.getElementById('paraFrm_stationaryName').value; 
  
	
	
	 if(stationaryCode==""){
			 alert("Please enter Stationary Code");
		  return false;
		}
	
      	
   	if(!(stationaryCode==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < stationaryCode.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(stationaryCode.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==stationaryCode.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
     
      if(stationaryName==""){
			 alert("Please enter Stationary Name");
		  return false;
		}
	
	
		if(!(stationaryName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < stationaryName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(stationaryName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==stationaryName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
	
    
	
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'Stationary_saveImprintData.action';
		document.getElementById('paraFrm').submit();
  	  	
  			
  }	 
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'Stationary_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'Stationary_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'Stationary_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'Stationary_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'Stationary_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
		
		
		function editFun() {
		
		return true;
		}
		
		
	
	 /*This function used for only name where name comes call this function,it restricts only ", ' , \ and allow all special chars*/

function isCharactersKey(evt)
	 {
	
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
	 
	
		
</script>

<!-- Scripting Ends-->