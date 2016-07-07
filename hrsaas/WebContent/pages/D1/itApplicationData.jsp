<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<!-- Nilesh Dhandare -->

<s:form action="ITApplication" validate="true" id="paraFrm"
	validate="true" theme="simple">

<!-- Hidden Fields Start-->
	<s:hidden name="itCode" />
<!-- Hidden Fields End-->

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
					<td colspan="1" width="90%" class="txt"><strong
						class="text_head">IT Application </strong></td>

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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td colspan="1" width="15%"><label class="set"
						name="application_name" id="application_name"
						ondblclick="callShowDiv(this);"><%=label.get("application_name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2" width="85%"><s:textfield size="30"
						theme="simple" name="applicationName" maxlength="50"
						onkeypress="return isCharactersKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="15%"><label class="set"
						name="application_sec" id="application_sec"
						ondblclick="callShowDiv(this);"><%=label.get("application_sec")%></label>
					:</td>
					<td colspan="2" width="85%"><s:textfield size="30" maxlength="50"
						theme="simple" name="applicationSection"
						onkeypress="return isCharactersKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="15%" height="22" class="formtext"><label
						name="application_isactive" class="set" id="application_isactive"
						ondblclick="callShowDiv(this);"><%=label.get("application_isactive")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2" width="85%"><s:select name="applicationActive" headerKey="1" headerValue="-------------Select-------------------"
						list="#{'Y':'Yes','N':'No'}" cssStyle="width:178;z-index:6;" /></td>
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
<!-- Scripting Start -->

<script>

 function saveFun()
{
 
  var applicationName =  document.getElementById('paraFrm_applicationName').value;
   var applicationActive =  document.getElementById('paraFrm_applicationActive').value; 
     
	 if(applicationName==""){
			 alert("Please Enter Application Name");
		  return false;
		}
	
      	
   	if(!(applicationName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < applicationName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(applicationName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==applicationName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
  	
  if(applicationActive=="1"){
  alert("Select Application Status");
  return false;
  }
  	
     
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ITApplication_save.action';
		document.getElementById('paraFrm').submit();
  			
  }	 
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ITApplication_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ITApplication_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ITApplication_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ITApplication_delete.action';
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
            
            if(charCode!=44)
            {
             if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode==34 ) || (charCode==39) || (charCode==63) ||(charCode==92) )
                return false;
             }
             return true;
      
      }
      
  /*Function only Characters in small & capital letters,Spaces in field */
function isCharactersKeyNew(evt)
	 {
	
         var charCode = (evt.which) ? evt.which : event.keyCode
        // alert("charCode"+charCode);
            
          //  alert("charCode--"+charCode);
            
            if(charCode!=31)
            {
             if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode < 32 || charCode > 39) || (charCode==63) ||(charCode==34)||(charCode==39)||(charCode==92))
                return false;
             }
             return true;
      }		     
      
</script>

<!-- Scripting Ends-->
