<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="TravelRating" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" value="%{show}" />



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
					<td width="93%" class="txt"><strong class="text_head">Travel
					Rating </strong></td>

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

					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<s:hidden name="hiddencode" />
				<tr>
					<td width="25%"><label class="set" name="ratingparameter"
						id="ratingparameter" ondblclick="callShowDiv(this);"><%=label.get("ratingparameter")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield size="25" theme="simple" maxlength="30"
						name="ratingParameter" id="ratingParameter" onkeypress="return allCharacters();" /></td>

				</tr>

				<tr>
					<td><label name="ratingtype" class="set" id="ratingtype"
						ondblclick="callShowDiv(this);"><%=label.get("ratingtype")%>
					</label><font color="red">*</font>:</td>

					<td colspan="1"><s:select name="ratingType" id="ratingType"  headerKey=" " headerValue="-Select Type-" list="#{'H':'Hotel', 'D':'Desk'}"></s:select></td>
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
<script type="text/javascript">
function saveFun()
 {
 try{
var ratingParameterName = trim(document.getElementById('ratingParameter').value); 
var ratingParameterType=  trim(document.getElementById('ratingType').value);     	 
     	 if(ratingParameterName==""){
		  alert("Please enter "+document.getElementById('ratingparameter').innerHTML.toLowerCase());
		  document.getElementById('ratingParameter').focus();
		 return false;
		 
		 }

       if(ratingParameterType==""){
		  alert("Please select "+document.getElementById('ratingtype').innerHTML.toLowerCase());
		  document.getElementById('ratingType').focus();
		 return false;
		 
		 }
  		
  		
  		document.getElementById('paraFrm').target = "_self";
  		 		
  		document.getElementById('paraFrm').action = 'TravelRating_save.action';
		
		document.getElementById('paraFrm').submit();
  	  	return true ;
  	}catch(e){
   alert(e);}		
  }
	
	function resetFun() {
		document.getElementById('paraFrm').target = "_self";
     	
  		document.getElementById('paraFrm').action = 'TravelRating_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    return true;
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TravelRating_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TravelRating_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'TravelRating_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
		
		
		function editFun() {
		
		document.getElementById('paraFrm').action = 'TravelRating_edit.action';
		document.getElementById('paraFrm').submit();
		
		
		return true;
	}	
	
	
	</script>



