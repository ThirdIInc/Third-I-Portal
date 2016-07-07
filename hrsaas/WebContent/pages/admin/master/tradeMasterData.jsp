
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="TradeMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="tradeMaster.tradeCode" />
	
	<!--<s:hidden name="tradeMaster.tradeName" />
	<s:hidden name="tradeMaster.tradeParentName" />-->

	<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Trade
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
		
		<tr><td width="100%"><table width="100%">
			<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td width="20%">
									<div align="right"><span class="style2"><font
										color="red">*</font></span> Indicates Required</div>
									</td>
									</table></td>
		</tr>


				

		

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">




						<tr>

							<td width="15%" ><label  class = "set" name="tradename" id="tradename" ondblclick="callShowDiv(this);"><%=label.get("tradename")%></label> :<font color="red">*</font></td>
							<td width="85%" colspan="2" height="22"><s:textfield size="25"
								maxlength="50" name="tradeMaster.tradeName"
								label="%{getText('tradename')}"
								onkeypress="return allCharacters();" />
						</tr>

						<tr>

							<td width="15%" ><label  class = "set" name="tradeabbr" id="tradeabbr" ondblclick="callShowDiv(this);"><%=label.get("tradeabbr")%></label> :<font color="red">*</font></td>
							<td width="85%" colspan="2" height="22"><s:textfield size="25"
								maxlength="50" name="tradeMaster.tradeAbbr"
								label="%{getText('tradeabbr')}"
								onkeypress="return allCharacters();" />
						</tr>
						
						<tr>

							<td width="20%" ><label  class = "set" name="tradedescription" id="tradedescription" ondblclick="callShowDiv(this);"><%=label.get("tradedescription")%></label> :</td>
							<td width="80%" colspan="2" height="22"><s:textfield
								size="25" label="%{getText('tradeDesc')}"
								name="tradeMaster.tradeDesc" maxlength="100"
								onkeypress="return allCharacters();" />
						</tr>


						<tr>

							<td width="15%" ><label  class = "set" name="tradeparent" id="tradeparent" ondblclick="callShowDiv(this);"><%=label.get("tradeparent")%></label> :</td>
							<td width="85%" colspan="2" height="22"><s:hidden theme="simple"
				name="tradeMaster.tradeParentCode" /><s:textfield
								size="25"  maxlength="10" readonly="true"
								name="tradeMaster.tradeParentName"
								 /><img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif"
									width="16" height="15"onclick="callSearch('f9Tradaction');"  />
							</td>
						</tr>
</table></td></tr></table></td></tr>


<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr></table></s:form>

<script>
function saveFun(type)
 {

var fieldName = ["paraFrm_tradeMaster_tradeName","paraFrm_tradeMaster_tradeAbbr"];
var lableName = ["tradename","tradeabbr"];
var flag = ["enter","enter"];
var fieldName1 = ["paraFrm_tradeMaster_tradeName","paraFrm_tradeMaster_tradeAbbr"];
/*if(type == 'update'){
		if(document.getElementById('paraFrm_tradeMaster_tradeCode').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_tradeMaster_tradeCode').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}
*/

     //if(!checkMandatory(fieldName, lableName,flag)){
     if(!validateBlank(fieldName,lableName,flag)){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      document.getElementById('paraFrm').target = "_self";
      document.getElementById('paraFrm').action = 'TradeMaster_save.action';
		document.getElementById('paraFrm').submit();
     	    return true;
}





function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'TradeMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TradeMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TradeMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'TradeMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	function editFun() {
		return true;
	}	
</script>

			