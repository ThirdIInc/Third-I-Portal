<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="ColonyMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">

	
	<s:hidden name="ittSrN0"></s:hidden>

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
					<td width="93%" class="txt"><strong class="text_head">Colony
					Master </strong></td>

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
				<tr>

					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>







					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>




		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				




				<tr>
					<td><label class="set" name="colony.Name" id="colony.Name"
						ondblclick="return roomAvailable;"><%=label.get("colony.Name")%></label><font color="red">*</font> :</td>

					<td><s:hidden theme="simple" name="colonyId" /><s:textfield size="25"
						theme="simple" name="colonyName"/> </td>
				</tr>

				<tr>
					<td width="20%"><label class="set" name="colony.Address"
						id="colony.Address" ondblclick="callShowDiv(this);"><%=label.get("colony.Address")%></label>
					:</td>


					
						
						<td colspan="4">
							<s:textarea name="colonyAddress" cols="50" rows="3" onkeyup="callLength('colonyAddress','descriptionLength','2000');" /> 
								<img src="../pages/images/zoomin.gif" height="12" align="absmiddle"	id='ctrlHide' width="12" theme="simple"	onclick="javascript:callWindow('paraFrm_colonyAddress','colonyAddress','', 'praFrm_descriptionLength', '2000','2000');">&nbsp;&nbsp;
								</td>	<td id='ctrlHide'>Remaining chars 
							<s:textfield name="descriptionLength" readonly="true" size="5"></s:textfield>
						</td>	
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


<script>
  	
  	function saveFun() {
		
		
		var colonyName=document.getElementById('paraFrm_colonyName').value;
  		
  		
  	if(colonyName==""){
			alert("Please select "+document.getElementById('colony.Name').innerHTML.toLowerCase());
			return false;
	}

  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ColonyMaster_save.action';
		document.getElementById('paraFrm').submit();
      	
		
	
  	  
  	
  			}
  			
  			function searchFun(){
  			
  			
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='ColonyMaster_search.action';
			document.getElementById("paraFrm").submit();
  	}
		
  	
  		function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ColonyMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="ColonyMaster_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ColonyMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	
}
	
	
	function editFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="ColonyMaster_edit.action";
	  	document.getElementById('paraFrm').submit();  
	}
  	</script>


