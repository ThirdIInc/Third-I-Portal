<%@ taglib prefix="s" uri="/struts-tags" %>  
 	<%@include file="/pages/common/labelManagement.jsp" %>
 	
 	<s:form action="CategoryMaster"  validate="true" id="paraFrm" validate="true" theme="simple" >
  	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	<s:hidden name="catgMaster.catgID"/>
	
	<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Category
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
		
		<tr>
				<td width="100%">
					<table width="100%">
						<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="20%">
						<div align="right"><span class="style2"><font	color="red">*</font></span> Indicates Required</div>
						</td>
					</table>
				</td>
		</tr>
		
				<tr>
					<td width="100%">

					<table width="100%" border="0" align="center" class="formbg" >
			
  		<tr>
					<td width="100%">

					<table width="100%" border="0" align="center" >
			
	<tr> 
		
	  	<td width="15%" colspan="1" height="22"  ><label  name="categoryname" id="categoryname" ondblclick="callShowDiv(this);"><%=label.get("categoryname")%></label> :<font color="red">*</font></td>
	  	<td  width="85%" colspan="2"   height="22"><s:textfield size="25" maxlength="100" label="%{getText('catgName')}"  name="catgMaster.catgName"  onkeypress="return allCharacters();"/></td>
	  	
	</tr>
	 <tr>	
	  		<td width="25%" colspan="1" height="22" > <label name="categorydescription" id="categorydescription" ondblclick="callShowDiv(this);"><%=label.get("categorydescription")%></label> :</td>
	  		<td  width="75%" colspan="2"   height="22"><s:textfield size="25" maxlength="200" label="%{getText('catgDesc')}" name="catgMaster.catgDesc"  /></td>

	</tr>
    <tr>
    		
	  		<td width="15%" colspan="1" height="22" > <label name="categoryage" id="categoryage" ondblclick="callShowDiv(this);"><%=label.get("categoryage")%></label> :</td>
	  		<td width="85%" colspan="2"   height="22"><s:textfield size="25" label="%{getText('catgAge')}" name="catgMaster.catgAge" onkeypress="return numbersOnly();" maxlength="4"  /></td>	  		
	</tr>
		
	<tr>
		<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" 			ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
		</td>
		<td>
			<s:select name="isActive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
		</td>
	</tr>

	</table></td></tr>
	
	
	</table></td></tr>
	<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
	</table></s:form>
	
	<script>
	
	function saveFun(type){
	
 


var fieldName = ["paraFrm_catgMaster_catgName"];
var lableName = ["categoryname"];
var flag = ["enter"];
var fieldName1 = ["paraFrm_catgMaster_catgName"];
/*
if(type == 'update'){
		if(document.getElementById('paraFrm_catgMaster_catgID').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_catgMaster_catgID').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}*/
     if(!(validateBlank(fieldName,lableName,flag))){       
              return false;
        }
     
        
      if(!f9specialchars(fieldName1))
      {
      
              return false;
       }

 	var amountpat= /^[0-9]/;
	var age =document.getElementById('paraFrm_catgMaster_catgAge').value;

	if(!(age=="")){
    	// if(!age.match(amountpat)) {
    	 if(isNaN(age)) { 
	   alert("Only Numbers are allowed in  !"+document.getElementById('categoryage').innerHTML.toLowerCase());
		 document.getElementById('paraFrm_catgMaster_catgAge').focus();
		return false;
		}

    }  
       	 document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CategoryMaster_save.action';
		document.getElementById('paraFrm').submit();
		
		return true;
	}
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CategoryMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CategoryMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CategoryMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'CategoryMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	

function editFun() {
		return true;
	}
</script>
	