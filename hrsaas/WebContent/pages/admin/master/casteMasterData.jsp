<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="CasteMaster" id="paraFrm" validate="true" theme="simple" target="main">
<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	
	<s:hidden name="casteMaster.casteCode"/>
	<table width="100%" border="0" align="right"  class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Caste
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
			<td colspan="3">
			<table width="100%" border="0"
				class="formbg">
						
		
		<tr>
				
			<td width="15%" colspan="1" height="22" ><label  class = "set" name="castename" id="castename" ondblclick="callShowDiv(this);"><%=label.get("castename")%></label>
 :<font color="red">*</font></td>
			<td width="85%" colspan="2"   height="22"><s:textfield  size="25" theme="simple" name="casteMaster.casteName" maxlength="50" onkeypress="return allCharacters();"/></td>
		</tr>
         
	 <tr>
				
			<td width="15%" colspan="1" height="22" ><label  class = "set" name="castcategory" id="castcategory" ondblclick="callShowDiv(this);"><%=label.get("castcategory")%></label>
 :<font color="red">*</font></td>
			<td width="85%" colspan="2"   height="22"><s:textfield size="25" theme="simple" name="casteMaster.casteCatgName" maxlength="50" readonly="true"/>
			<s:if test="casteMaster.viewFlag"> <img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" width="16" height="15" align="middle"
			onclick="callSearch('f9catAction');"/></s:if>
				<s:hidden   name="casteMaster.casteCatgCode" />
			</td>
		</tr>
		
		<tr>
			<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
			</td>
			<td>
				<s:select name="isActive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
			</td>
		</tr>

   	</table>
   	</td></tr>
   	<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
   	</table>
   	</s:form>
   	
  <script> 	
   	
   	
 function saveFun(type)
 {

var fieldName = ["paraFrm_casteMaster_casteName"];
var lableName = ["castename"];
var fieldName1 = ["paraFrm_casteMaster_casteName","paraFrm_casteMaster_casteCatgName"];
var flag = ["enter","select"];
/*if(type == 'update'){
		if(document.getElementById('paraFrm_casteMaster_casteCode').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_casteMaster_casteCode').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}*/
     if(!(validateBlank(fieldName, lableName,flag))){
              return false;
        }
        
     if(document.getElementById("paraFrm_casteMaster_casteCatgName").value==""){
		  alert("Please Select "+document.getElementById('castcategory').innerHTML.toLowerCase());
		  document.getElementById("paraFrm_casteMaster_casteCatgName").focus();
		  return false;
		}
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      document.getElementById('paraFrm').target = "_self";
      document.getElementById('paraFrm').action = 'CasteMaster_save.action';
		document.getElementById('paraFrm').submit();
     	    return true;
}
   	
   	
   	
   	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CasteMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CasteMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CasteMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'CasteMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}


function editFun() {
		return true;
	}
</script>
   	