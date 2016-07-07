<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ListOfTravelMode" validate="true" id="paraFrm"
	validate="true" theme="simple">

<s:hidden   name="ModeId"></s:hidden  > 
<s:hidden  name="ittModeCode"></s:hidden > 
<s:hidden  name="travelId"></s:hidden > 
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
					<td width="93%" class="txt"><strong class="text_head">List
					Of Airlines and Trains </strong></td>

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
					<td width="20%"><label class="set" name="travelMode.Name"
						id="travelMode.Name" ondblclick="callShowDiv(this);"><%=label.get("travelMode.Name")%></label>
					<font color="red">*</font>:</td>


					<td><s:hidden name="journeyId"></s:hidden><s:textfield size="25" theme="simple" maxlength="60"
						name="travelModeName" onkeypress="return allCharacters();" readonly="true"/><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ListOfTravelMode_searchMode.action')"
						width="16" height="15"></td>
						
						
						</td>
				</tr>




				<tr>
					<td><label class="set" name="airlines.Bus.Train" id="airlines.Bus.Train"
						ondblclick="callShowDiv(this);"><%=label.get("airlines.Bus.Train")%></label><font
						color="red">*</font> :
						
						</td>

					<td>
					
					
					<s:textfield size="25" theme="simple" name="airlineBusTrain"  /> 
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
		
		
		var travelModeName = trim(document.getElementById('paraFrm_travelModeName').value);
		

		if(travelModeName==""){

			alert("Please select "+document.getElementById('travelMode.Name').innerHTML.toLowerCase());
			return false;
			
		}
		
		
			
		
		var airlineBusTrain = trim(document.getElementById('paraFrm_airlineBusTrain').value);
	
		
		if(airlineBusTrain==""){
			alert("Please enter "+document.getElementById('airlines.Bus.Train').innerHTML.toLowerCase());
			return false;
		}
		
		



  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ListOfTravelMode_save.action';
		document.getElementById('paraFrm').submit();
      	
		
	
  	  
  	
  			}
  			
  			function searchFun(){
  			
  			
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='ListOfTravelMode_searchMode.action';
			document.getElementById("paraFrm").submit();
  	}
		
  	
  		function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ListOfTravelMode_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="ListOfTravelMode_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ListOfTravelMode_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	
}
	
	
	function editFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="ListOfTravelMode_edit.action";
	  	document.getElementById('paraFrm').submit();  
	}
  	</script>


