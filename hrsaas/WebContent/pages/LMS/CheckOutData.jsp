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
				class="formbg">
				


				<tr>
				<td  class="txt"><strong class="text_head">Accommodation 
					 </strong></td>
				</tr>
				
			
				<tr>
					<td width="20%"><label class="set" name="emp.name" id="emp.name"><%=label.get("emp.name")%></label> :
					</td>
						<td width="0%">
						<s:hidden name="empID" />
						<s:property value="empToken" /> &nbsp;
						<s:property value="empName" /> 
						
						</td>
						<td width="20%"></td>
						
				</tr>
				
				<tr>
						<td  width="20%"><label class="set" name="branch" id="branch"><%=label.get("branch")%></label>:
						</td>
						<td width="30%">
						<s:property value="branchName" />
						</td>
						
						
						<td width="50%"><label class="set" name="branch" id="designation">
						<%=label.get("designation")%></label>:
						<s:property value="designationName" />
						</td>
						
					
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
							
				<%if(valueChk.equals("F")){ %>
				<td align="left" width="25%">	
					<label  class = "set" name="free"  id="free" 
							ondblclick="callShowDiv(this);"><%=label.get("free")%>
					</label>
					</td>
					<%} %>
				<%if(valueChk.equals("H")){ %>
					<td width="25%">
					<label  class = "set" name="hra"  id="hra" 
							ondblclick="callShowDiv(this);"><%=label.get("hra")%>
					</label>
					</td>
					<%} %>
				<%if(valueChk.equals("R")){ %>
					<td width="25%">
					<label  class = "set" name="rent.rec"  id="rent.rec" 
							ondblclick="callShowDiv(this);"><%=label.get("rent.rec")%>
					</label>
				</td>
				<%} %>
				</tr>
			<%if(valueChk.equals("R")){ %>
				<tr>		
						<td id="rentID" width="25%" ><label class="set" name="rent.amount" id="rent.amount">
						<%=label.get("rent.amount")%></label>:</td>
						<td id="rentID" width="25%" >
						<s:property value="rentAmount" />
						</td>
						<td id="rentID" width="25%" ></td>
						<td id="rentID" width="25%" ></td>

				</tr>
		<%}%>

			<tr>
				<td  class="txt" width="25%"><strong class="text_head">Accommodation  For
					 </strong></td>
			
			<%if(valueIndFamChk.equals("I")){ %>
			<td width="25%">
					<label  class = "set" name="individual"  id="individual" 
							ondblclick="callShowDiv(this);"><%=label.get("individual")%>
					</label>
			</td>
			<%} %>
			<%if(valueIndFamChk.equals("F")){ %>
			<td width="25%">
					<label  class = "set" name="family"  id="family" 
							ondblclick="callShowDiv(this);"><%=label.get("family")%>
					</label>
			</td>
			<%} %>
			<td width="25%"></td>
			</tr>
			<br/>

			<tr>
				<td width="25%"><label class="set" name="colony.Name" id="colony.Name"><%=label.get("colony.Name")%></label>:
				<s:hidden name="colonyID"></s:hidden>
				</td>
				<td width="25%">
				<s:property value="colonyName" />
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>

			<tr>
				<td width="25%"><label class="set" name="house.no" id="house.no"><%=label.get("house.no")%></label> :
				<s:hidden name="houseID"></s:hidden></td>
				<td width="25%">
				<s:property value="houseNo" />
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>

			<tr>
				
			
			<td width="25%">
					<label class="set" name="house.addr" id="house.addr"><%=label.get("house.addr")%></label>:
					</td>
					<td width="25%">
							<s:property value="houseAddr" /> 
								</td>
								<td width="25%"></td>			
			</tr>


			<tr>
				<td width="25%"><label class="set" name="amount" id="amount"><%=label.get("amount")%></label> :</td>
				<td width="25%">
					<s:property value="amount" /> 
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>
			<tr>
			<td width="25%" ><label class="set"
										name="validity" id="validity"
										ondblclick="callShowDiv(this);"><%=label.get("validity")%></label>:
									</td>
									
									<td width="25%" colspan="1">
										<s:property value="validityDate" /> 
										</td>
									<td width="25%"></td>
									<td width="25%"></td>
									
</tr>

			</table>
			
	<br/>	
	<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				<tr>
				<td  class="txt"><strong class="text_head">Check Out Details :
					 </strong></td>
				</tr>
			<tr>
			
			
			<td width="25%" colspan="1"><label class="set"
				name="checkout.date" id="checkout.date"
				ondblclick="callShowDiv(this);"><%=label.get("checkout.date")%></label>
			<font color="red">*</font> :</td>
			
			<td width="25%" colspan="1"><s:textfield
				cssStyle="width:130" size="10" theme="simple" name="checkOutDate" /><s:a
				href="javascript:NewCal('paraFrm_checkOutDate','DDMMYYYY');">
				<img src="../pages/images/Date.gif" class="iconImage"
					height="16" align="absmiddle" width="16">
			</s:a>
			</td>
			<td width="25%" ></td>
			<td width="25%" ></td>
			</tr>
			
			<tr>						
			<td id="rentID" width="25%"><label class="set" name="reason" id="reason">
						<%=label.get("reason")%> <font color="red">*</font> :</label></td>
						<td width="25%" >
						<s:textfield size="25"
						theme="simple" name="reasonCheckOut"  />
						</td>
						<td width="25%" ></td>
						<td width="25%" ></td>
			</tr>	
					
			<tr>
			<!--<td><label class="set" name="remark" id="house.addr"><%=label.get("remark")%></label><font color="red">*</font> :
					<s:textarea cols="15" rows="5" theme="simple" name="remarkCheckOut"  />
				</td>
				
				
				--><td width="25%">
					<label class="set" name="remark" id="remark"><%=label.get("remark")%><font color="red">*</font> :</label>
					</td>
					<td width="25%">
							<s:textarea name="remarkCheckOut" cols="30" rows="3" onkeyup="callLength('remarkCheckOut','descriptionLength','2000');" /> 
								<img src="../pages/images/zoomin.gif" height="12" align="absmiddle"	id='ctrlHide' width="12" theme="simple"	onclick="javascript:callWindow('paraFrm_remarkCheckOut','remarkCheckOut','', 'praFrm_descriptionLength', '2000','2000');">&nbsp;&nbsp;
								</td>	
								
				<td id='ctrlHide' width="25%">Remaining chars :
							<s:textfield name="descriptionLength" readonly="true" size="5"></s:textfield>
						</td>
						
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
<script><!--<!--
  	
 function saveFun() {
 
 		var checkOut=document.getElementById('paraFrm_checkOutDate').value;
  			if(checkOut==""){
					alert("Please enter "+document.getElementById('checkout.date').innerHTML.toLowerCase());
					return false;
			}
			
			
			if(!(checkOut == "")){
			var checkDate = validateDate(checkOut);
		
			if(!checkDate){
				alert("Please enter "+document.getElementById('checkout.date').innerHTML.toLowerCase() +" in proper format (DD/MM/YYYY)");
				return false;
			}
  		}
			
			var reason=document.getElementById('paraFrm_reasonCheckOut').value;
  			if(reason==""){
					alert("Please enter "+document.getElementById('reason').innerHTML.toLowerCase());
					return false;
			}
			var remark=document.getElementById('paraFrm_remarkCheckOut').value;
  			if(remark==""){
					alert("Please enter "+document.getElementById('remark').innerHTML.toLowerCase());
					return false;
			}
 
	  		document.getElementById('paraFrm').target = "_self";
	  		document.getElementById('paraFrm').action = 'AccommodationMaster_checkOutDatasave.action';
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
		function validateDate(fld) {
var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
if (!((fld.match(RegExPattern)) && (fld!=''))){
    return false;
}
    return true;
   }
  	
  		function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AccommodationMaster_resetCheckOutData.action';
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
	

	function setRadioValue(id){
		var opt=document.getElementById('radioValue').value =id.value;	
	}
	
	function setRadioValueFor(id){
	alert(id.value)
		var opt=document.getElementById('radio1Value').value =id.value;	
	}
	
	
  	</script>


