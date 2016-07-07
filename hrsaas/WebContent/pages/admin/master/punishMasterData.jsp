<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action = "PunishMaster" theme="simple" validate ="true" id ="paraFrm">
<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	<s:hidden name="punishMaster.punishId"/>
	<table width="100%" border="0"  align="right"
		 class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" class="formbg" align="right">
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Punishment
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
			      <table width="100%" border="0" class="formbg" >
				
							<tr>
								<td width="20%" colspan="1" height="22"><label  class = "set" name="punishname" id="punishname" ondblclick="callShowDiv(this);"><%=label.get("punishname")%></label> :   	<font color="red">*</font></td>
									<td colspan="2" width="80%" height="22">
									<s:textfield theme="simple" name="punishMaster.punishName" maxlength="50"  size="25"  onkeypress="return allCharacters();"/></td>
							</tr>
							<tr >
							      <td width="20%" colspan="1" height="22"><label  class = "set" name="financial" id="financial" ondblclick="callShowDiv(this);"><%=label.get("financial")%></label>:   	</td>
									<td colspan="2" width="80%" height="22">
									<s:textfield theme="simple" name="punishMaster.fImplication" maxlength="20"  size="25"  onkeypress="return allCharacters();" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1" height="22"><label  class = "set" name="ismajor" id="ismajor" ondblclick="callShowDiv(this);"><%=label.get("ismajor")%></label> :   	</td>
									<td colspan="2" width="80%" height="22"><s:select name="punishMaster.isMajor"
										headerKey="1" headerValue="--Select--" cssStyle="width:154"
										list="#{'Y':'Yes','N':'No'}" /></td>
							</tr>	
							<tr>
									<td width="20%" colspan="1" height="22"><label  class = "set" name="isCredit" id="isCredit" ondblclick="callShowDiv(this);"><%=label.get("isCredit")%></label> :   	</td>
									<td colspan="2" width="80%" height="22"><s:hidden name="creditChk"/><s:checkbox name="isCredit"  onclick="callCreditChk();"/></td>
							</tr>	
					
		</table>
		</td>
		</tr>
		<s:if test="flag">		
		<tr>
		<td >
		
		<table class="formbg" width="100%" id="tblPunish"><tr>
		
		<td class="formth" align="center" width="10%"><label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>.</td>
		<td class="formth" align="center" width="25%"><label  class = "set" name="credit.name" id="credit.name" ondblclick="callShowDiv(this);"><%=label.get("credit.name")%></label>.</td>
		<td class="formth" align="center" width="15%"><label  class = "set" name="creditPerc" id="creditPerc" ondblclick="callShowDiv(this);"><%=label.get("creditPerc")%></label>.</td>
		
		
		</tr>
		<%int c=0; %>
		<s:iterator value="creditList">
		
		<tr>
		<td width="10%" align="center" class="sortableTD"><%=++c %></td>
		<td width="10%" align="left" class="sortableTD"><s:hidden name="creditCode"/><s:property value="creditHead"/></td>
		<td width="10%" align="center" class="sortableTD" ><input type="text" size="10" name="creditPercent" id="creditPercent<%=c%>"  value="<s:property value="creditPercent"/>"  onkeypress="return numbersWithDot();" maxlength="5"></td>
		</tr>
		
		</s:iterator>
		
		
		</table>
		
		
		</td>
		
		
		</tr>
		
		</s:if>
		
		
		<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		
		</table></s:form>
		
		<script>
		
		
		
			function saveFun() {
  		var punishName =trim(document.getElementById("paraFrm_punishMaster_punishName").value);
		var fieldName = ["paraFrm_punishMaster_punishName"];
		var lableName = ["punishname"];
		var flag = ["enter"];


		if(!validateBlank(fieldName, lableName, flag)) {
			return false;
       }
	

      	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PunishMaster_save.action';
		document.getElementById('paraFrm').submit();
      	
		return true;
  	}
		
				function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'PunishMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PunishMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PunishMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'PunishMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	

function editFun() {
		return true;
	}
  			
 function callCreditChk()
	   {
	 	  		var fieldName = ["paraFrm_punishMaster_punishName"];
		var lableName = ["punishname"];
		var flag = ["enter"];


		if(!validateBlank(fieldName, lableName, flag)) {
	      document.getElementById('paraFrm_isCredit').checked=false;		
			return false;
        }
	
	   if(document.getElementById('paraFrm_isCredit').checked)
	   {	  
	  
	      document.getElementById('paraFrm_creditChk').value="true";
	      document.getElementById("paraFrm").action="PunishMaster_callCreditHead.action";
	      document.getElementById('paraFrm').target = "_self";
  		  document.getElementById("paraFrm").submit();
	      
		
	   }else{
	       document.getElementById('paraFrm_creditChk').value="";
	       document.getElementById("paraFrm").action="PunishMaster_callCreditHeads.action";
	       document.getElementById('paraFrm').target = "_self";
  		   document.getElementById("paraFrm").submit();
				  
   		}
  }	
  	
	
	
		</script>
		