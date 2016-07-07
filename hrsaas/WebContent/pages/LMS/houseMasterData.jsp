<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="House_Master" validate="true" id="paraFrm"
	validate="true" theme="simple">

	
	

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
					<td width="93%" class="txt"><strong class="text_head">House
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
					<td><label class="set" name="house.No" id="house.No"
						ondblclick="return roomAvailable;"><%=label.get("house.No")%></label><font color="red">*</font> :</td>

					<td><s:hidden theme="simple" name="houseId" /><s:textfield size="25"
						theme="simple" name="houseNo"/> </td>
				</tr>
				<tr>
					<td><label class="set" name="colony.Name" id="colony.Name"
						ondblclick="return roomAvailable;"><%=label.get("colony.Name")%></label><font color="red">*</font> :</td>

					
					 
					 <td><s:hidden theme="simple" name="colonyId"  /><s:textfield size="25"
						theme="simple" name="colonyName" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'HouseMaster_f9Colony.action')"
						width="16" height="15"></td>
				</tr>

				<tr>
					<td width="20%"><label class="set" name="house.Address"
						id="house.Address" ondblclick="callShowDiv(this);"><%=label.get("house.Address")%></label><font color="red">*
					:</td>


					
						
						<td colspan="4">
							<s:textarea name="houseAddress" cols="50" rows="3" onkeyup="callLength('houseAddress','descriptionLength','2000');" /> 
								<img src="../pages/images/zoomin.gif" height="12" align="absmiddle"	id='ctrlHide' width="12" theme="simple"	onclick="javascript:callWindow('paraFrm_houseAddress','houseAddress','', 'praFrm_descriptionLength', '2000','2000');">&nbsp;&nbsp;
								</td>	<td id='ctrlHide'>Remaining chars 
							<s:textfield name="descriptionLength" readonly="true" size="5"></s:textfield>
						</td>	
				</tr>
				<tr>
				<td colspan="4"><b><label class="set" name="capacity"
						id="capacity" ondblclick="callShowDiv(this);"><%=label.get("capacity")%></label>:</b>
				
				</td>
				
				</tr>
				<tr>
					<td><label class="set" name="No.Family" id="No.Family"
						ondblclick="return roomAvailable;"><%=label.get("No.Family")%></label> :</td>

					<td><s:textfield size="25"
						theme="simple" name="NoFamily" onkeypress="return numbersOnly();"/> </td>
				</tr>
				<tr>
					<td><label class="set" name="No.Individual" id="No.Individual"
						ondblclick="return roomAvailable;"><%=label.get("No.Individual")%></label>:</td>

					<td><s:textfield size="25"
						theme="simple" name="NoIndividual" onkeypress="return numbersOnly();"/> </td>
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
		
		
		var houseNo=document.getElementById('paraFrm_houseNo').value;
  		
  		
  	if(houseNo==""){
			alert("Please enter "+document.getElementById('house.No').innerHTML.toLowerCase());
			return false;
	}
	var colonyName=document.getElementById('paraFrm_colonyName').value;
  		
  		
  	if(colonyName==""){
			alert("Please select "+document.getElementById('colony.Name').innerHTML.toLowerCase());
			return false;
	}
	var houseAddress=document.getElementById('paraFrm_houseAddress').value;
  		
  		
  	if(houseAddress==""){
			alert("Please enter "+document.getElementById('house.Address').innerHTML.toLowerCase());
			return false;
	}

  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'HouseMaster_save.action';
		document.getElementById('paraFrm').submit();
      	
		
	
  	  
  	
  			}
  			
  			function searchFun(){
  			
  			
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='HouseMaster_search.action';
			document.getElementById("paraFrm").submit();
  	}
		
  	
  		function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HouseMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="HouseMaster_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HouseMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	
}
	
	
	function editFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="HouseMaster_edit.action";
	  	document.getElementById('paraFrm').submit();  
	}
  	</script>


