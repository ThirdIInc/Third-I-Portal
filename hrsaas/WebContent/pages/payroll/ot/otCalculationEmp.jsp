<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="OtCalculations" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="otCalculationId" />
	<s:hidden name="backAction" />
	<s:hidden name="status" />

	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td colspan="2">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					OT Calculation List </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" align="center">
				<tr>
					<td><s:if test='%{status =="Pending" || status =="Unlock"}'>
						<input type="button" class="save" value=" Save "
							onclick="return callSave();" />
						<input type="button" class="back" value=" Back "
							onclick="return callBack();" />
					</s:if> <s:elseif test='%{status =="Lock"}'>
						<input type="button" class="back" value=" Back "
							onclick="return callBack();" />

					</s:elseif> <!--<input type="button" class="reset" value="    Reset " onclick="return callReset();" />
						--></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- section for select parameters -->

		<tr>
			<td colspan="1">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead"> Parameters are :</strong></td>
						</tr>
						<tr>
							<td colspan="1" width="15%"><label id="paid.month"
								name="paid.month" ondblclick="callShowDiv(this);"><%=label.get("paid.month")%></label>:<font
								color="red">*</font></td>
							<td colspan="1"><s:hidden name="paidMonth" /> <s:select
								theme="simple" disabled="true" name="paidMonth"
								cssStyle="width:152"
								list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td>


							<td colspan="1" width="15%"><label class="set"
								id="paid.year" name="paid.year" ondblclick="callShowDiv(this);"><%=label.get("paid.year")%></label>:<font
								color="red">*</font></td>
							<td width="15%"><s:property value="paidYear" /><s:hidden
								name="paidYear" /></td>
							
						</tr>

						<tr>
							<td width="10%"><label class="set" id="pay.in.component"
								name="pay.in.component" ondblclick="callShowDiv(this);"><%=label.get("pay.in.component")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="20%"><s:property value="creditName" /><s:hidden
								name="creditCode" /></td>


						</tr>
						<tr>

							<td><label id=pay.in.salary name="pay.in.salary"
								ondblclick="callShowDiv(this);"><%=label.get("pay.in.salary")%></label>:</td>
							<td colspan="1" width=""><s:checkbox name="payInSalaryFlag"
								disabled="true"></s:checkbox><s:hidden name="payInSalaryFlag" />
							</td>
						</tr>

						<tr>

							<td><label id=deduct.it name="deduct.it"
								ondblclick="callShowDiv(this);"><%=label.get("deduct.it")%></label>:</td>
							<td colspan="1"><s:checkbox name="deductInconeTaxFlag"
								disabled="true"></s:checkbox><s:hidden
								name="deductInconeTaxFlag" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="1">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="">

				<tr>
					<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">

						<tr>
							<td width="30%" align="left"><strong class="text_head">Employee
							OT Calculation Details :</strong></td>
							<td width="60%" align="right">
							<%
								int totalPage = 0;
									int pageNo = 0;
							%> <s:if test="calOtEmpListLength">
								<!--<table>
								<tr> 
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalPage = (Integer) request.getAttribute("totalPage");
									pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'PersonalDataChange_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'PersonalDataChange_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'PersonalDataChange_input.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'PersonalDataChange_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PersonalDataChange_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
								</table>
							-->
							</s:if></td>
						</tr>

						<tr>
							<td class="formtext" colspan="2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable" id="tblSlabForMen">


								<tr class="td_bottom_border">

									<td class="formth" width="10%"><label name="emp.id"
										id="emp.id" ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></td>
									<td class="formth" width="10%"><label name="emp.name"
										id="emp.name" ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
									<td class="formth" width="10%"><label
										name="single.ot.hours" id="single.ot.hours"
										ondblclick="callShowDiv(this);"><%=label.get("single.ot.hours")%></label></td>
									<td class="formth" width="10%"><label
										name="double.ot.hours" id="double.ot.hours"
										ondblclick="callShowDiv(this);"><%=label.get("double.ot.hours")%></label></td>
									<td class="formth" width="10%"><label
										name="total.ot.hours" id="total.ot.hours"
										ondblclick="callShowDiv(this);"><%=label.get("total.ot.hours")%></label></td>
									<td class="formth" width="10%"><label name="single.ot.amt"
										id="single.ot.amt" ondblclick="callShowDiv(this);"><%=label.get("single.ot.amt")%></label></td>
									<td class="formth" width="10%"><label name="double.ot.amt"
										id="double.ot.amt" ondblclick="callShowDiv(this);"><%=label.get("double.ot.amt")%></label></td>
									<td class="formth" width="10%"><label name="total.ot"
										id="total.ot" ondblclick="callShowDiv(this);"><%=label.get("total.ot")%></label></td>

									<td class="formth" width="10%"><label name="tds" id="tds"
										ondblclick="callShowDiv(this);"><%=label.get("tds")%></label></td>
									<td class="formth" width="10%"><label name="net.ot.amt"
										id="net.ot.amt" ondblclick="callShowDiv(this);"><%=label.get("net.ot.amt")%></label></td>

								</tr>

								<s:if test="calOtEmpListLength">
									<%
										int count3 = 0;
									%>
									<%!int d = 0;%>
									<%
										int i = 0;
												int cn = pageNo * 2 - 2;
									%>
									<s:iterator value="calOtEmpIteratorList" status="stat">
										<%
											int count1 = 0;
										%>
										<tr>
											<td class="sortableTD" width="10%"><s:hidden
												name="ittYear" /> <s:hidden name="ittMonth" /> <input
												type="hidden" name="ittOTCalID"
												id="paraFrm_ittOTCalID<%=count1%>"
												value="<s:property value="ittOTCalID"/>" /> <input
												type="hidden" name="ittEmpID"
												id="paraFrm_ittEmpID<%=count1%>"
												value="<s:property value="ittEmpID"/>" /> <s:property
												value="ittEmpToken" /></td>
											<td><s:property value="ittEmployeeName" /></td>
											<td width="10%" align="right"><input type="text"
												name="ittSingleOtHours"
												style="text-align: right; border: none;"
												value="<s:property value="ittSingleOtHours"/>"
												id="<%="ittSingleOtHours"+i%>" size="4" maxlength="5"
												readonly="readonly" /> 
											</td>

										<td width="10%" align="right"><input type="text"
												name="ittDoubleOtHours"
												style="text-align: right; border: none;"
												value="<s:property value="ittDoubleOtHours"/>"
												id="<%="ittDoubleOtHours"+i%>" size="4" maxlength="5"
												readonly="readonly" /> 
											</td>

										<td width="10%" align="right"><input type="text"
												name="ittTotalOtHours"
												style="text-align: right; border: none;"
												value="<s:property value="ittTotalOtHours"/>"
												id="<%="ittTotalOtHours"+i%>" size="4" maxlength="5"
												readonly="readonly" /> 
											</td>


										<td width="10%" align="right"><input type="text"
												name="ittSingleOtAmount"
												style="text-align: right; border: none;"
												value="<s:property value="ittSingleOtAmount"/>"
												id="<%="ittSingleOtAmount"+i%>" size="4" maxlength="5"
												readonly="readonly" /> 
											</td>

										<td width="10%" align="right"><input type="text"
												name="ittDoubleOtAmount"
												style="text-align: right; border: none;"
												value="<s:property value="ittDoubleOtAmount"/>"
												id="<%="ittDoubleOtAmount"+i%>" size="4" maxlength="5"
												readonly="readonly" /> 
											</td>
											
										

											<td align="right"><s:textfield name="ittTotalOt"
												cssStyle="text-align:right" id="<%="ittTotalOt"+i%>"
												size="10" maxlength="10"
												onkeypress="return restrictChars(event, this);"
												onblur="return showValidAmt();" /></td>

											<td align="right"><s:textfield name="ittTds"
												cssStyle="text-align:right" id="<%="ittTds"+i%>" size="10"
												maxlength="10"
												onkeypress="return restrictChars(event, this);"
												onblur="return checkValidTDSAmt();" /></td>

											<td class="sortableTD" width="10%" align="right"><s:textfield
												name="ittNetOtAmount" id="<%="ittNetOtAmount"+i%>"
												readonly="true"
												cssStyle="background-color: #F2F2F2;text-align:right"
												size="10" /></td>

										</tr>

										<%
											count3++;
														i++;
										%>
									</s:iterator>
									<input type="hidden" name="otherLengthVar" id="otherLengthVar"
										value="<%=i%>" />
								</s:if>

							</table>
							</td>
						</tr>

					</table>
					
					</td>
				</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">


						<tr>
							<td width="10%"><strong>Total:</strong></td>
							<td width="10%">&nbsp;</td>

							<td width="10%" align="right"><s:textfield
								name="totalSingleOtHours" id="totalSingleOtHours"
								readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="5" /></td>
							<td width="10%"  align="right"><s:textfield name="totalDoubleOtHours"
								id="totalDoubleOtHours" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="5" /></td>
							
							<td width="10%"  align="right"><s:textfield name="totalOtHours"
								id="totalOtHours" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="5" /></td>
								
							<td width="10%"  align="right"><s:textfield name="totalSingleOtAmt"
								id="totalSingleOtAmt" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="5" /></td>
							<td width="10%"  align="right"><s:textfield name="totalDoubleOtAmt"
								id="totalDoubleOtAmt" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="5" /></td>

							<td width="10%"  align="right"><s:textfield name="totalOtAmt"
								id="totalOtAmt" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="10" /></td>

							<td width="10%"  align="right"><s:textfield name="totalTdsAmt"
								id="totalTdsAmt" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="10" /></td>
								
							<td width="10%"  align="right"><s:textfield name="netOtAmt"
								id="netOtAmt" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="10" /></td>
						</tr>
					</table>
					</td>

				</tr>


			</table>
			</td>
		</tr>


		<tr>
			<td><s:if test='%{status =="Pending" || status =="Unlock"}'>
				<input type="button" class="save" value=" Save "
					onclick="return callSave();" />
				<input type="button" class="back" value=" Back "
					onclick="return callBack();" />
			</s:if> <s:elseif test='%{status =="Lock"}'>
				<input type="button" class="back" value=" Back "
					onclick="return callBack();" />

			</s:elseif> <!--<input type="button" class="reset" value="    Reset " onclick="return callReset();" />
						--></td>
		</tr>
	</table>
</s:form>
<script>
	onload(); 
	function onload() { 
	try{
		showValidTotalAmt();
		showValidAmt();
		
		if(document.getElementById('paraFrm_status').value=="Lock"){
			
			totalRecords = document.getElementById('otherLengthVar').value;
			
			for(var i = 0; i <= totalRecords; i++) {
			
				document.getElementById('ittTotalOt'+i).readOnly = true;
				document.getElementById('ittTotalOt'+i).style.background = '#F2F2F2';
				
				
				document.getElementById('ittTds'+i).readOnly = true;
				document.getElementById('ittTds'+i).style.background = '#F2F2F2';
			
			}
		}
		if(document.getElementById('paraFrm_status').value=="Pending"){
			
			totalRecords = document.getElementById('otherLengthVar').value;
			
			for(var i = 0; i <= totalRecords; i++) {
			
				document.getElementById('ittTotalOt'+i).readOnly = false;
				document.getElementById('ittTds'+i).readOnly = false;
			////document.getElementById('ittTotalOt'+i).style.background = 'white';
			}
		}
	
	
	}catch(e){
	////alert(e);
	}
}
	
function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}	
	
	function callBack(){
	
	var otConfigId = document.getElementById('paraFrm_otCalculationId').value;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").action="OtCalculations_calforedit.action?otConfigId="+otConfigId;
	

	document.getElementById('paraFrm').submit();
	}
	
	
	function callSave(){
	var con=confirm('Do you want to save the OT Calculation detail(s) ?');
	 if(con){
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="OtCalculations_saveCalEmpOtDetails.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
	    }
	}


	function showValidAmt() {
		try
		{
			var table = document.getElementById('tblSlabForMen');
						var rowCount = table.rows.length; 
			///alert(rowCount);
			var sum = 0;
			var otsum=0;
			var netsum=0;
			for(var i=0;i<(rowCount-1);i++){
				var invlimit= parseFloat(document.getElementById('ittTotalOt'+i).value);
				if(invlimit=="NaN"|| document.getElementById('ittTotalOt'+i).value==''){ 
						   document.getElementById('ittTds'+i).value="0.0";
						   document.getElementById('ittTotalOt'+i).value="0.0";
					}
				///alert(invlimit);
				var ittTds = parseFloat(document.getElementById('ittTds'+i).value);
				if(ittTds=="NaN" || document.getElementById('ittTds'+i).value==''){ 
						   document.getElementById('ittTds'+i).value="0.0";
					}
				///alert(ittTds);
				 if(ittTds > invlimit){
					sum = parseFloat(invlimit) - parseFloat(ittTds);	
					} else {
					sum = parseFloat(invlimit) - parseFloat(ittTds);
				}
					///alert("sum==="+sum);
				document.getElementById('ittNetOtAmount'+i).value = sum.toFixed(2);
				if(document.getElementById('ittNetOtAmount'+i).value=="NaN"){
					   document.getElementById('ittNetOtAmount'+i).value="0.0";
				}
				
				var netOtAmt = parseFloat(document.getElementById('ittNetOtAmount'+i).value);
				
				invlimit= parseFloat(document.getElementById('ittTotalOt'+i).value);
				ittTds = parseFloat(document.getElementById('ittTds'+i).value);
				
				otsum = parseFloat(otsum) + parseFloat(invlimit);
				netsum = parseFloat(netsum) + parseFloat(netOtAmt);
			}
			document.getElementById('totalOtAmt').value = otsum.toFixed(2);
			
			
			
			if(document.getElementById('totalOtAmt').value=="NaN"){
					   	document.getElementById('totalOtAmt').value = otsum.toFixed(2);
				}
				
				
			document.getElementById('netOtAmt').value = netsum.toFixed(2);
			
			
			
			if(document.getElementById('netOtAmt').value=="NaN"){
					   document.getElementById('netOtAmt').value="0.0";
				}
			
		}catch(e)
		{
			////alert("Error Occured in callUpload===================> "+e);		
		}
	}
	
	function checkValidTDSAmt() {
	try
		{
			var table = document.getElementById('tblSlabForMen');
			var rowCount = table.rows.length; 
				////alert(rowCount);
			var sum = 0;
			var tdssum=0;
			var netsum=0;
			for(var i=0;i<(rowCount-1);i++){
				var invlimit= parseFloat(document.getElementById('ittTotalOt'+i).value);
					if(invlimit=="NaN" || document.getElementById('ittTotalOt'+i).value==''){ 
						   document.getElementById('ittTds'+i).value="0.0";
					}
				var ittTds = parseFloat(document.getElementById('ittTds'+i).value);
				if(ittTds=="NaN" || document.getElementById('ittTds'+i).value==''){ 
						   document.getElementById('ittTds'+i).value="0.0";
						  
					}
			 	if(parseFloat(ittTds) > parseFloat(invlimit)){
					alert("TDS Amount must be less than Total OT Amount i.e."+document.getElementById('ittTotalOt'+i).value);
	    			document.getElementById('ittTds'+i).focus();
	    			 document.getElementById('ittTds'+i).value='';
			 		return false;
				} else{
				
				var SIGDIG= 100000000;
			////alert( Math.floor(((invlimit) - (ittTds))*SIGDIG)/SIGDIG );

					sum = Math.floor(((invlimit) - (ittTds))*SIGDIG)/SIGDIG ;
				}
				
				document.getElementById('ittNetOtAmount'+i).value = sum.toFixed(2);
				if(document.getElementById('ittNetOtAmount'+i).value=="NaN"){
				
					   document.getElementById('ittNetOtAmount'+i).value=invlimit.toFixed(2);
					   
				}
				if(document.getElementById('ittTds'+i).value=="NaN"){
					   document.getElementById('ittTds'+i).value="0.0";
				}
				var netOtAmt = parseFloat(document.getElementById('ittNetOtAmount'+i).value);
				 ittTds = parseFloat(document.getElementById('ittTds'+i).value);
				tdssum = parseFloat(tdssum) + parseFloat(ittTds);
				netsum = parseFloat(netsum) + parseFloat(netOtAmt);
				
				
					
			}
			
				document.getElementById('totalTdsAmt').value = tdssum.toFixed(2);
			
				if(document.getElementById('totalTdsAmt').value=="NaN"){
				
					   document.getElementById('totalTdsAmt').value="0.0";
				}
				
				
				
				
			document.getElementById('netOtAmt').value = netsum.toFixed(2);
			if(document.getElementById('netOtAmt').value=="NaN"){
					   document.getElementById('netOtAmt').value="0.0";
				}	
		}catch(e)
		{
			alert("Error Occured in callUpload===================> "+e);		
		}
	}
	
	
	// Retrieve last key pressed.  Works in IE and Netscape.
          // Returns the numeric key code for the key pressed.
          function getKey(e)
          {
            if (window.event)
               return window.event.keyCode;
            else if (e)
               return e.which;
            else
               return null;
          }
          function restrictChars(e, obj)
          {
            var CHAR_AFTER_DP = 2;  // number of decimal places
            var validList = "0123456789.";  // allowed characters in field
            var key, keyChar;
            key = getKey(e);
            if (key == null) return true;
            // control keys
            // null, backspace, tab, carriage return, escape
            if ( key==0 || key==8 || key==9 || key==13 || key==27 )
               return true;
            // get character
            keyChar = String.fromCharCode(key);
            // check valid characters
            if (validList.indexOf(keyChar) != -1)
            {
              // check for existing decimal point
              var dp = 0;
              if( (dp = obj.value.indexOf( ".")) > -1)
              {
                if( keyChar == ".")
                  return false;  // only one allowed
                else
                {
                  // room for more after decimal point?
                  if( obj.value.length - dp <= CHAR_AFTER_DP)
                    return true;
                }
              }
              else return true;
            }
            // not a valid character
            return false;
          }
          
          function showValidTotalAmt() {
		try
		{
			var table = document.getElementById('tblSlabForMen');
						var rowCount = table.rows.length; 
		///	alert(rowCount);
			var hour=0;
			var min=0;
			///alert(hour);
			///alert(min);
			var Dbhour=0;
			var Dbmin=0;
			var tothour=0;
			var totmin=0;
			var sum = 0; 
			var dbsum =0;
			var otsum=0;
			var tdssum=0;
			var netsum=0;
			for(var i=0;i<(rowCount-1);i++){
			
			if(document.getElementById('ittSingleOtHours'+i).value==""){
					document.getElementById('ittSingleOtHours'+i).value="00:00";
			} 
			if(document.getElementById('ittSingleOtHours'+i).value=="0.00"){
			
					document.getElementById('ittSingleOtHours'+i).value="00:00";
			}
				if(document.getElementById('ittSingleOtHours'+i).value=="NaN"){
				   document.getElementById('ittSingleOtHours'+i).value="00:00"; 
				}
			//ittDoubleOtHours start
			if(document.getElementById('ittDoubleOtHours'+i).value==""){
					document.getElementById('ittDoubleOtHours'+i).value="00:00";
			} 
			if(document.getElementById('ittDoubleOtHours'+i).value=="0.00"){
			
					document.getElementById('ittDoubleOtHours'+i).value="00:00";
			}
				if(document.getElementById('ittDoubleOtHours'+i).value=="NaN"){
				   document.getElementById('ittDoubleOtHours'+i).value="00:00"; 
				}
				
			// ittDoubleOtHours end 
			//ittTotalOtHours start
			if(document.getElementById('ittTotalOtHours'+i).value==""){
					document.getElementById('ittTotalOtHours'+i).value="00:00";
			} 
			if(document.getElementById('ittTotalOtHours'+i).value=="0.00"){
			
					document.getElementById('ittTotalOtHours'+i).value="00:00";
			}
				if(document.getElementById('ittTotalOtHours'+i).value=="NaN"){
				   document.getElementById('ittTotalOtHours'+i).value="00:00"; 
				}
			
			var invlimit= parseFloat(document.getElementById('ittSingleOtAmount'+i).value);
			var doubleOtAmt= parseFloat(document.getElementById('ittDoubleOtAmount'+i).value);
			var totalOtAmt= parseFloat(document.getElementById('ittTotalOt'+i).value);
			var totalTdsAmt= parseFloat(document.getElementById('ittTds'+i).value);
			var netOtAmt= parseFloat(document.getElementById('ittNetOtAmount'+i).value);
			
			//ittTotalOtHours  end	
				var time=document.getElementById('ittSingleOtHours'+i).value;
				var timeSplit=time.split(':');
				hour =eval(hour)+eval(timeSplit[0]);
				min =eval(min)+eval(timeSplit[1]);
				
				var Dbtime=document.getElementById('ittDoubleOtHours'+i).value;
				var DbtimeSplit=Dbtime.split(':');
				Dbhour =eval(Dbhour)+eval(DbtimeSplit[0]);
				Dbmin =eval(Dbmin)+eval(DbtimeSplit[1]);
				
				var tottime=document.getElementById('ittTotalOtHours'+i).value;
				var tottimeSplit=tottime.split(':');
				tothour =eval(tothour)+eval(tottimeSplit[0]);
				totmin =eval(totmin)+eval(tottimeSplit[1]);
				
				sum = parseFloat(sum) + parseFloat(invlimit);
				dbsum = parseFloat(dbsum) + parseFloat(doubleOtAmt);
				otsum = parseFloat(otsum) + parseFloat(totalOtAmt);
				tdssum = parseFloat(tdssum) + parseFloat(totalTdsAmt);
				netsum = parseFloat(netsum) + parseFloat(netOtAmt);
				
			} 
			hour=eval(hour)+eval(Math.floor(min/60));
			min=eval(min%60);
			document.getElementById('totalSingleOtHours').value=hour+':'+min;
			///totalDoubleOtHours
			Dbhour=eval(Dbhour)+eval(Math.floor(Dbmin/60));
			Dbmin=eval(Dbmin%60);
			document.getElementById('totalDoubleOtHours').value=Dbhour+':'+Dbmin;
			
			//ittTotalOtHours
			tothour=eval(tothour)+eval(Math.floor(totmin/60));
			totmin=eval(totmin%60);
			document.getElementById('totalOtHours').value=tothour+':'+totmin;
			document.getElementById('totalSingleOtAmt').value = sum.toFixed(2);
			document.getElementById('totalDoubleOtAmt').value = dbsum.toFixed(2);
			document.getElementById('totalOtAmt').value = otsum.toFixed(2);
			document.getElementById('totalTdsAmt').value = tdssum.toFixed(2);
			document.getElementById('netOtAmt').value = netsum.toFixed(2);
			
		}catch(e)
		{
			///alert("Error Occured in callUpload===================> "+e);		
		}
	}
	
	
</script>
