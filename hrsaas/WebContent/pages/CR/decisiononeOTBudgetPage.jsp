<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>
<STYLE type=text/css>
a:hover {
	COLOR: #FF0000;
	text-decoration: underline;
}
</STYLE>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="DecisionOneOTBudget" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="screenWidth" />
	<s:hidden name="viewOTDtlFlag" />
	<s:hidden name="yearMonth" />
	<s:hidden name="autoid" id="autoID"/>
	<s:hidden name="previousData" id="previousData"/>
	<s:hidden name="previousMonthYear" id="previousMonthYear"/>
	
	
	

	<table width="100%" align="right" class="formbg" border="0"
		style="margin-bottom: 5.8em;">
		<tr>
			<td valign="bottom" width="100%">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" height="25" />
					</strong></td>
					<td width="70%" class="txt"><strong class="text_head">
					DecisionOne OT Budget </strong></td>
					<td width="15%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="15%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<!--<tr>
			<td align="left"><input type="button" class="back"
				value=" Back to list " onclick="return backFun();" /></td>
		</tr>
		--><tr>
			<td colspan="4">
			<table width="100%" class="formbg">

				<tr>
					<td>
					<table width="100%" border="0">

						<tr>
							<td colspan="2" width="60%">Month : <font color="red">*</font>:<s:select
								name="otMonth" cssStyle="width:154"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Year:<font color="red">*</font> <s:textfield
								name="otYear" id="otYear" maxlength="4" size="7" 
								onkeypress="return numbersOnly();" /> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="add"
								value=" Go " onclick="return callOTDetails('');" /></td>

						</tr>
						



					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr style="height: 700px">
		<td >
		
		</td>
		</tr>	
<s:if test="previousData=='NN'">
	<div align="center" id="overlay" 
	style="z-index: 3;  position: absolute; width: 930px;  
		height: 400px;margin: 0px; left: 100; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;display: none;">
</div>
	
	<div id="confirmationDiv" 
		style='position: absolute; 
		z-index: 3; 100 px; 
		height: 150px; 
		visibility: hidden; 
		top: 100px;  
		left: 300px;background-color: #FFF;'>
	</div>
	<script>
	displayConfirmDiv();
	function displayConfirmDiv(){
		var yearmonth=document.getElementById("previousMonthYear").value;
		document.getElementById("confirmationDiv").style.visibility = 'visible';
		document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=150 border=0 class=formbg>'
		+'<tr><td class="txt"><b><center>Previous Month '+yearmonth+' Data Available in System. Do you want to View Data?</td></tr>'
		+'<tr><td><b><center><input type="button" value="OK" onclick="setlatestData();" class="token"/>'
		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
 		//document.getElementById("overlay").style.display = "block";
	}
	</script>
	</s:if>
	<s:else>
		<s:if test="viewOTDtlFlag">
		<tr>
						<td colspan="2" width="60%">
						Total Budget : 
						
						<s:textfield  name="totalBudget" id="totalBudget" ></s:textfield>
						
						Number of Working Days :
						
						<s:textfield  name="NumberOfWorkingDays" id="noOfWorkingDays" ></s:textfield>
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="add"
								value=" Save " onclick="return saveTotalBudget()");" />
						</td>
						
						
						
						</tr>
			<tr>
				<td colspan="8">
				<table width="100%" border="0">
					<tr>
						<td align="left"><strong class="text_head"> DecisionOne OT Budget List </strong></td>

					</tr>

					<tr>
						<td >
						<table width="60%" class="formbg" border="0">

							<tr class="formth">
								<td height="22" width="10%" colspan="1">Region </td>
								<td height="22" width="8%">PersonType</td>
								<td height="22" width="8%">Reporting ID</td>
								<td height="22" width="8%" colspan="1">Person  </td>
								
								<td height="22" width="8%">Budget %</td>
								<td height="22" width="10%">Budget amount</td>
								<td height="22" width="10%">Budget Daily</td>
								<td height="22" width="10%">Budget Weekly</td>
								<td height="22" width="5%"></td>

							</tr>
							
							<tr > 
								<td align="center" class="sortableTD"  nowrap="nowrap">
								<s:textfield  name="regionID"  size="3"  readonly="true"	cssStyle="background-color: #F2F2F2;"/>
								
								<s:textfield  name="regionName"  size="7"  readonly="true" 	cssStyle="background-color: #F2F2F2;"/>
								<img src="../pages/images/recruitment/search2.gif" width="16" height="15" class="iconImage" id="ctrlShow"
											onclick="javascript:callsF9(500,325,'DecisionOneOTBudget_f9RegionID.action'),javascript:checkData();" />
								</td>
								<td align="center" class="sortableTD" width="3%">
								<s:select name="otPersonType" id="otPersonType" cssStyle="width:154"
								list="#{'0':'Select --','M':'Manager','S':'supervisor'}" />
								
								</td>
								<td align="left" class="sortableTD" width="3%" nowrap="nowrap">
								<s:textfield name="ReportingID" size="3" readonly="true"	cssStyle="background-color: #F2F2F2;"></s:textfield>
								<s:textfield  name="ReportingName"  size="7"  readonly="true" 	cssStyle="background-color: #F2F2F2;"/>
								<img src="../pages/images/recruitment/search2.gif" width="16" height="15" class="iconImage" id="ctrlShow"
											onclick="javascript:callsF9(500,325,'DecisionOneOTBudget_f9ReportingID.action');" />
								</td>
								<td align="center" class="sortableTD" width="3%" nowrap="nowrap">
								<s:textfield name="PersonID" size="3"  readonly="true"	cssStyle="background-color: #F2F2F2;"></s:textfield>
								
								<s:textfield name="PersonName" size="9"  readonly="true"	cssStyle="background-color: #F2F2F2;"></s:textfield>
								<img src="../pages/images/recruitment/search2.gif" width="16" height="15" class="iconImage" id="ctrlShow"
											onclick="javascript:callsF9(500,325,'DecisionOneOTBudget_f9PersonID.action');" />
								</td>
								
								<td align="center" class="sortableTD" width="3%">
								<s:textfield name="budgetPer" id= "budgetPer" ></s:textfield>
								</td>
								<td align="center" class="sortableTD" width="3%">
								<s:textfield name="" id ="budgetAmt" readonly="true" cssStyle="background-color: #F2F2F2;"></s:textfield>
								</td>
								<td align="center" class="sortableTD" width="3%">
								<s:textfield name="" id="budgetDaily" readonly="true" cssStyle="background-color: #F2F2F2;"></s:textfield>
								</td>
								<td align="center" class="sortableTD" width="3%">
								<s:textfield name="" id="budgetWeekly" readonly="true" cssStyle="background-color: #F2F2F2;"></s:textfield>
								</td>
								<td align="center" class="sortableTD" width="3%"><input type="button" value="Add" name="Add" onclick="calBudget()"> </td>
				
							</tr>
						
					
					<s:iterator value="otBudgetList">
							
							<tr style=" border: 2" bordercolor="red"> 
								
								<td align="center" class="sortableTD" width="10%" colspan="1"><s:property value="regionName"/>
								<s:hidden name="autoid"/>
								<s:hidden name="regionIDItt"/>
								</td>
								<td align="center" class="sortableTD" width="8%" ><s:property value="otPersonType"/></td>
								<td align="center" class="sortableTD" width="8%" ><s:property value="ReportingID"/></td>
								<td align="center" class="sortableTD" width="8%"nowrap="nowrap" colspan="1"><s:property value="PersonID"/></td>
								<td align="center" class="sortableTD" width="8%"><s:property value="budgetPer"/></td>
								<td align="center" class="sortableTD" width="10%"><s:property value="budgetAmt"/></td>
								<td align="center" class="sortableTD" width="10%"><s:property value="budgetDaily"/></td>
								<td align="center" class="sortableTD" width="10%"><s:property value="budgetWeekly"/></td>
								<td align="center" class="sortableTD" width="5%">
								<a href="#"><img src="../pages/mypage/images/icons/edit.png" 
														onclick="editFun('<s:property value="autoid"/>')" width="13" height="13" border="0" /></a>&nbsp;&nbsp;
								<a href="#"><img src="../pages/common/css/icons/delete.gif" width="13" height="13" class="iconImage" onclick="deleteCurrentRow('<s:property value="autoid"/>')"></a>
								 </td>
				
							</tr>
				</s:iterator>
				
				
				
				<s:if test="forMonthYear!=''">
					
				
				</s:if>
						</table>
					</td>
					</tr>

				</table>
				</td>
			</tr>


		</s:if>
</s:else>
	</table>

</s:form>


<script type="text/javascript">
	function checkData(){
	alert("23423");
	}
	function saveTotalBudget(){
	var totBudget=document.getElementById("totalBudget").value;
	if(totBudget==""){
	alert("Please Select Total Budget");
	return false;
	}
	var noWorkingDay=document.getElementById("noOfWorkingDays").value;
	if(noWorkingDay==""){
	alert("Please Select Number of Working Days");
	return false;
	}
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DecisionOneOTBudget_saveTotalBugdetforMonth.action?total_budget='+totBudget+'&noWorkingDay='+noWorkingDay //+'&yearMonth='+yearMonth;
		document.getElementById('paraFrm').submit();
	}
	
	
	
	
	function callOTDetails(priyearMonth) {
	 var month = document.getElementById('paraFrm_otMonth').value;
	
	if(month==0)
		{
		alert("Please Select Month");
		document.getElementById('otMonth').focus();
		return false;
		}
	
	var year = document.getElementById('otYear').value;// Year
	
	if(year=='')
		{
		alert("Please Enter Year ");
		document.getElementById('otYear').focus(); 
		return false;
		}
	//alert('priyearMonth - '+priyearMonth)	
	//var budgetper=document.getElementById('budgetPer').value;
	//if(budgetper==''){
	//alert("Please Enter Budget")
	//}
	var yearmonth=''
	if(priyearMonth!=''){
	yearmonth=priyearMonth;
	}
	else{
	yearmonth=year+month;
	}
	//alert("yearmonth - "+yearmonth);	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DecisionOneOTBudget_callOTDetails.action?yearmonth='+yearmonth;
		document.getElementById('paraFrm').submit();
	}
	
	function setlatestData(){
	var month = document.getElementById('paraFrm_otMonth').value;
	
	if(month==0)
		{
		alert("Please Select Month");
		document.getElementById('otMonth').focus();
		return false;
		}
	
	var year = document.getElementById('otYear').value;// Year
	
	if(year=='')
		{
		alert("Please Enter Year ");
		document.getElementById('otYear').focus(); 
		return false;
		}
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById("overlay").style.display = "none";
	//alert("yearmonth - "+yearmonth);	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DecisionOneOTBudget_setLatestData.action';
		document.getElementById('paraFrm').submit();
		
	}
	
	
	
	function calBudget(){
	var autoID=document.getElementById('autoID').value;
	//alert("autoID -"+autoID);
	
	var totBudget=document.getElementById("totalBudget").value;
	if(totBudget==""){
	alert("Please Select Total Budget");
	return false;
	}
	var noWorkingDay=document.getElementById("noOfWorkingDays").value;
	if(noWorkingDay==""){
	alert("Please Select Number of Working Days");
	return false;
	}

	var regionName=document.getElementById('paraFrm_regionName').value;

	if(regionName==''){
	alert("Please Enter Region");
	return false;
	}

	var personType=document.getElementById('otPersonType').value;
	if(personType=='0'){
	alert("Please Select Person Type ");
	return false;
	}
	
	if(personType!='M'){
	var reportingid=document.getElementById('paraFrm_ReportingID').value
	if(reportingid==''){
	alert("Please Enter Reporting Id Details");
	return false;
	}
	}
	var personID=document.getElementById('paraFrm_PersonID').value
	
	if(personID==''){
	alert("Please Enter Person Details");
	return false;
	}
	
	var budgetPer=document.getElementById('budgetPer').value;
	if(budgetPer==''){
	alert("Please Enter Buget percentage")
	return false;
	}
	
	//alert("personType - "+personType)
	//if(personType=='M'){
	//var budgetper=eval(document.getElementById('budgetPer').value);
	//var budgetAmt=eval((totalBudget*budgetPer)/100);
	//var budgetDaily=eval(budgetAmt/noOfWorkingDays);
	//var budgetWeekly=eval(budgetDaily*5);
	//alert('budgetper -'+budgetper);
	//alert('budgetAmt -'+budgetAmt);
	//alert('budgetDaily -'+budgetDaily);
	//alert('budgetWeekly -'+budgetWeekly);
	
	//document.getElementById('budgetAmt').value=budgetAmt;
	//document.getElementById('budgetDaily').value=budgetDaily;
	//document.getElementById('budgetWeekly').value=budgetWeekly;}
	
	//else{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DecisionOneOTBudget_calculateBudget.action?autoid='+autoID;
		document.getElementById('paraFrm').submit();
	//}
	}
	function addOTBudget(){
	var autoID=document.getElementById('autoID').value;
	
	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DecisionOneOTBudget_addOTBudget.action?autoID='+autoID;
		document.getElementById('paraFrm').submit();
	}
	
	function editFun(autoid){
	
	document.getElementById('paraFrm').target = "_self";
     	document.getElementById('paraFrm').action = 'DecisionOneOTBudget_editBudget.action?autoid='+autoid;
		document.getElementById('paraFrm').submit();
	
	}
	function deleteCurrentRow(autoid){
	//return false;
	//alert(autoid);
	var conf=confirm("Are you sure to delete this record?");   
  		if(conf) {
	document.getElementById('paraFrm').target = "_self";
     	document.getElementById('paraFrm').action = 'DecisionOneOTBudget_deleteBudget.action?autoid='+autoid;
		document.getElementById('paraFrm').submit();
	}
	}
	function cancel(){
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById("overlay").style.display = "none";
	}
	
	function previousData(){
	}
	
 
</script>
