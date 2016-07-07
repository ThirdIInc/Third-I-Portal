<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="LangMaster" id="paraFrm" validate="true" theme="simple">
<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	<s:hidden name="langCode" />
	<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Language
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
			<td colspan="3">
				<table width="100%" border="0"  >
			
			<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		      <td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
				</td>
		</table>
		</td>
		</tr>
		
	    <tr>
		   <td width="100%">
				<table width="100%" border="0" align="center"  class="formbg">
					<tr>
						
						<td width="15%" colspan="1" height="22" >
							<label  class = "set" name="langtype" id="langtype" ondblclick="callShowDiv(this);"><%=label.get("langtype")%></label>:
								<font color="red">*</font>
						</td>
						<td colspan="2" width="85%">
							<s:textfield 
							theme="simple" name="langType" maxlength="50"  size="25" onkeypress="return allCharacters();"/>

						</td>
					</tr>
					<tr>	
						<td width="15%" colspan="1" height="22" >
							<label  class = "set" name="countrytype" id="countrytype" ondblclick="callShowDiv(this);"><%=label.get("countrytype")%></label>:
								<font color="red">*</font>
						</td>
						<td colspan="2" width="85%">
							<s:textfield 
							theme="simple" name="countryType" maxlength="50"  size="25" onkeypress="return allCharacters();"/>
						</td>
					</tr>
					
					
					<tr>
						<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" 			ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
						</td>
						<td>
							<s:select name="isActive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
						</td>
					</tr>
			   </table>
		    </td>
		</tr>
		
		
		
		<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
		
		</table></s:form>
		<script>
		
		
function saveFun(type)
{
	var fieldName = ["paraFrm_langType","paraFrm_countryType"];
	var lableName = ["langtype","countrytype"];
	var flag = ["enter"];
	var fieldName1 = ["paraFrm_langType","paraFrm_countryType"];

     if(!(validateBlank(fieldName, lableName,flag))){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      document.getElementById('paraFrm').target = "_self";
      document.getElementById('paraFrm').action = 'LangMaster_save.action';
		document.getElementById('paraFrm').submit();
     	    return true;
}
  	
		function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'LangMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LangMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LangMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'LangMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	

    function editFun() {
    document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LangMaster_callEdit1.action';
		document.getElementById('paraFrm').submit();
		//return true;
	}
         </script>	