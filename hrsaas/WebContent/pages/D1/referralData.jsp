<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="ReferralSource" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="refCode" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">

				<tr>
					<td colspan="1" width="2%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td colspan="1" width="90%" class="txt"><strong class="text_head">Referral Source </strong></td>

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
					<td colspan="1"width="15%"><label class="set" name="ref_source"
						id="ref_source" ondblclick="callShowDiv(this);"><%=label.get("ref_source")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="85%"><s:textfield size="40" theme="simple" 
						name="refSource" onkeypress="return isCharactersKey(event)" /></td>
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
    function saveFun()
{
 
  var refSource =  document.getElementById('paraFrm_refSource').value; 
	
	 if(refSource==""){
			 alert("Please Enter Referral Source ");
		  return false;
		}
	
      	
   	if(!(refSource==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < refSource.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(refSource.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==refSource.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
     
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ReferralSource_save.action';
		document.getElementById('paraFrm').submit();
  			
  }	 
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ReferralSource_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReferralSource_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReferralSource_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReferralSource_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
		function editFun() {
		return true;
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
	 

</script>
