<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="AccommodationMaster" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="accommodationID"> </s:hidden>
	<s:hidden name="AccommodationId"> </s:hidden>
	
	
<%
 String valueChk = "";
String valueIndFamChk="";
String checkForId="";
try {
	valueChk = (String) request.getAttribute("valueRadioStatus");
	valueIndFamChk = (String) request.getAttribute("indFamRadioStatus");
	
	checkForId = (String) request.getAttribute("accId");
	
} finally {
	if (valueChk == null) {
		valueChk = "";
	}
	if (valueIndFamChk == null) {
		valueIndFamChk = "";
	}
	if (checkForId == null) {
		checkForId = "";
	}
}
%>
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
					<td width="93%" class="txt"><strong class="text_head">Accommodation
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
				class="formbg"  colspan="4">
				


				<tr>
				<td  class="txt"><strong class="text_head">Accommodation 
					 </strong></td>
				</tr>
				
			
				<tr>
					<td width="25%" colspan="1"><label class="set" name="emp.name" id="emp.name"><%=label.get("emp.name")%></label><font color="red">*</font> :
					</td>
						<td width="75%" colspan="3">
						<s:hidden  name="empID" />
						<s:textfield size="10"
						theme="simple" name="empToken" readonly="true" />
						<s:textfield size="60" theme="simple" name="empName" readonly="true" />  <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'AccommodationMaster_f9Employee.action')"
						width="16" height="15"></td>
											
				</tr>
				
				<tr>
						<td  width="25%" colspan="1"><label class="set" name="branch" id="branch"><%=label.get("branch")%></label>:
						</td>
						<td width="25%" colspan="1">
						<s:textfield size="25"
						theme="simple" name="branchName" readonly="true" />
						</td>
						<td width="25%" colspan="1">
						<label class="set" name="designation" id="designation">
						<%=label.get("designation")%></label>:<s:textfield size="25"
						theme="simple" name="designationName" readonly="true" />
						</td>
						
								
						<td  width="25%" colspan="1"></td>
						
						
					
				</tr>
				</table>
				<br/>
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				<tr>
				<td  class="txt"><strong class="text_head">Accommodation  Details :
					 </strong></td>
				</tr>
				
				<tr>
				<td  class="txt"  width="25%"><strong class="text_head">Accommodation  Type
					 </strong></td>
							
				<td align="left" width="25%">	
					<input type="radio" name="freeAccommodations" id="freeAccommodationsID" onclick="setRadioValue(this);"  value="F"
					<%=valueChk.equals("F")?"checked":"" %>> 
					<label  class = "set" name="free"  id="free" 
							ondblclick="callShowDiv(this);"><%=label.get("free")%>
					</label>
					</td>
					<td width="25%">
					<input type="radio" name="freeAccommodations" id="hraDeductionID" onclick="setRadioValue(this);"  value="H"
					<%=valueChk.equals("H")?"checked":"" %>> 
					<label  class = "set" name="hra"  id="hra" 
							ondblclick="callShowDiv(this);"><%=label.get("hra")%>
					</label>
					<td width="25%">
					<input type="radio" name="freeAccommodations" id="rentRecID" onclick="setRadioValue(this);"  value="R"
					<%=valueChk.equals("R")?"checked":"" %>> 
					<label  class = "set" name="rent.rec"  id="rent.rec" 
							ondblclick="callShowDiv(this);" ><%=label.get("rent.rec")%>
					</label>
				</td>
				</tr>
			
				<%if(valueChk.equals("R")){%>
				<tr id="rentShow" >		
						<td id="rentID" width="25%" ><label class="set" name="rent.amount" id="rent.amount">
						<%=label.get("rent.amount")%> </label> <font color="red">*</font> :</td>
						<td id="rentID" width="25%" >
						<s:textfield size="25"
						theme="simple" name="rentAmount" />
						</td>
						
						<td id="rentID" width="25%" ></td>
						<td id="rentID" width="25%" ></td>

				</tr>
			<%}else{ %>
				<tr id="rentShow" style="display:none">		
						<td id="rentID" width="25%" ><label class="set" name="rent.amount" id="rent.amount">
						<%=label.get("rent.amount")%></label> <font color="red">*</font> :</td>
						<td id="rentID" width="25%" >
						<s:textfield size="25"
						theme="simple" name="rentAmount" />
						</td>
						
						<td id="rentID" width="25%" ></td>
						<td id="rentID" width="25%" ></td>

				</tr>
			<%} %>


			<tr>
				<td  class="txt" width="25%"><strong class="text_head">Accommodation  For
					 </strong></td>
			
			<td width="25%">
			<input type="radio" name="individual" id="individual1" onclick="setRadioValueFor(this);"  value="I"
					<%=valueIndFamChk.equals("I")?"checked":"" %>> 
					<label  class = "set" name="individual"  id="individual" 
							ondblclick="callShowDiv(this);"><%=label.get("individual")%>
					</label>
			</td>
			
			<td width="25%">
			<input type="radio" name="individual" id="individual2" onclick="setRadioValueFor(this);"  value="F"
					<%=valueIndFamChk.equals("F")?"checked":"" %>> 
					<label  class = "set" name="family"  id="family" 
							ondblclick="callShowDiv(this);"><%=label.get("family")%>
					</label>
			</td>
			<td width="25%"></td>
			</tr>
			

			<tr>
				<td width="25%"><label class="set" name="colony.Name" id="colony.Name"><%=label.get("colony.Name")%></label>:
				<s:hidden name="colonyID"></s:hidden>
				</td>
				<td width="25%">
				<s:textfield size="25" theme="simple" name="colonyName" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'AccommodationMaster_f9Colony.action')"
						width="16" height="15">
				</td>
				
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>

			<tr>
				<td width="25%"><label class="set" name="house.no" id="house.no"><%=label.get("house.no")%></label><font color="red">*</font> :
				</td>
				<td width="25%">
				<s:hidden name="houseID"/>
				<s:textfield size="25" theme="simple" name="houseNo" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'AccommodationMaster_f9House.action')"
						width="16" height="15">
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>

			<tr>
				<!--<td><label class="set" name="house.addr" id="house.addr"><%=label.get("house.addr")%></label><font color="red">*</font> :
					<s:textarea cols="15" rows="5" theme="simple" name="houseAddr" readonly="true" />
				</td>
			-->
			
			<td width="25%">
					<label class="set" name="house.addr" id="house.addr"><%=label.get("house.addr")%></label>
					</td>
					<td width="25%">
							<s:textarea name="houseAddr" cols="45" rows="3" readonly="true" /> 
								
								</td>	
								
				<!--<td id='ctrlHide' width="25%">Remaining chars :
							<s:textfield name="descriptionLength" readonly="true" size="5"></s:textfield>
						</td>
						
				--><td width="25%"></td>			
			</tr>


			<tr>
				<td width="25%"><label class="set" name="amount" id="amount"><%=label.get("amount")%></label> :</td>
				<td width="25%">
					<s:textfield size="25" theme="simple" name="amount"  onkeypress="return numbersOnly();" />
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>
			<tr>
			<td width="25%" ><label class="set"
										name="validity" id="validity"
										ondblclick="callShowDiv(this);"><%=label.get("validity")%></label>
									</td>
									
									<td width="25%" colspan="1"><s:textfield
										cssStyle="width:130" size="10" theme="simple" name="validityDate" /><s:a
										href="javascript:NewCal('paraFrm_validityDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
									<td width="25%"></td>
									<td width="25%"></td>
									
</tr>

			</table>
			
			</td>
		</tr>


		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>


	</table>
	<input type="hidden" name="radioValue" id="radioValue" value="<%=valueChk%>"/>
	<input type="hidden" name="radio1Value" id="radioValue" value="<%=valueIndFamChk%>"/>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>

  	
 function saveFun() {
 
 			
 			
			var empID=document.getElementById('paraFrm_empID').value;
  		
  			if(empID==""){
					alert("Please enter "+document.getElementById('emp.name').innerHTML.toLowerCase());
					return false;
			}
			var opt=document.getElementById('radioValue').value;
			if(opt=="R"){
				var rentAmount=document.getElementById('paraFrm_rentAmount').value;
				if(rentAmount==""){
					alert("Please enter "+document.getElementById('rent.amount').innerHTML.toLowerCase());
					return false;
			}
			
			}
			var houseNo=document.getElementById('paraFrm_houseNo').value;
		  		
		  		
		  	if(houseNo==""){
					alert("Please select "+document.getElementById('house.no').innerHTML.toLowerCase());
					return false;
			}
			
			
	  		document.getElementById('paraFrm').target = "_self";
	  		document.getElementById('paraFrm').action = 'AccommodationMaster_save.action';
			document.getElementById('paraFrm').submit();
 		 }
  			
  			function searchFun(){
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='AccommodationMaster_search.action';
			document.getElementById("paraFrm").submit();
  	}
		
  	
  		function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AccommodationMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="AccommodationMaster_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	//function dataValue(id){
		//alert("id here---"+id)
		//document.getElementById("rentID").enabled=true;
	//}
	
	function deleteFun() 
	{
		 var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'AccommodationMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	
	function editFun(){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="AccommodationMaster_edit.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function setRadioValue(id){
		var opt=document.getElementById('radioValue').value =id.value;	
		if(opt=="R"){
			document.getElementById('rentShow').style.display="";
			//document.getElementById('rentAmount').readOnly=false;
		}
		if(opt=="F"){
			document.getElementById('rentShow').style.display="none";
		}
		if(opt=="H"){
			document.getElementById('rentShow').style.display="none";
		}
	}
	
	function setRadioValueFor(id){
		var opt=document.getElementById('radio1Value').value =id.value;	
	}
	
	 
	 function rentAmount(){
		var opt=document.getElementById('radio1Value').value =id.value;	
	}
  	</script>


