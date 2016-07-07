<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="AwardMaster" id="paraFrm" validate="true" theme="simple">
<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	<s:hidden name="awardCode"/>
	
	<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Award
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
				
			<td width="15%" colspan="1" height="22" ><label  class = "set" name="awardtype" id="awardtype" ondblclick="callShowDiv(this);"><%=label.get("awardtype")%></label> :<font color="red">*</font></td>
			<td colspan="2" width="85%"><s:textfield label="%{getText('awardType')}"
				theme="simple" name="awardType" maxlength="50"  size="25" onkeypress="return allCharacters();"/></td>
		</tr>
		
		<tr>
			<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
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

var fieldName = ["paraFrm_awardType"];
var lableName = ["awardtype"];
var flag = ["enter"];
var fieldName1 = ["paraFrm_awardType"];

/*if(type == 'update'){
		if(document.getElementById('paraFrm_awardCode').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_awardCode').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}*/

     if(!(validateBlank(fieldName, lableName,flag))){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      document.getElementById('paraFrm').target = "_self";
      document.getElementById('paraFrm').action = 'AwardMaster_save.action';
		document.getElementById('paraFrm').submit();
     	    return true;
}
  	
		function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AwardMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AwardMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AwardMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'AwardMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	

function editFun() {
		return true;
	}
         </script>	