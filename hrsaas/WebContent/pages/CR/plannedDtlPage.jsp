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
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="PerformanceMetrics" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="accountCode" />
	<s:hidden name="myPage" id="myPage" />
<s:hidden name="show" />
<s:hidden name="dashBoardID" id="dashBoardID" />
<s:hidden name="screenWidth"/> 
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Configuration</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left"><input type="button" class="back"
				value="   Back " onclick="return backFun();" /></td>
		</tr>
		<tr>
			<td>
				<fieldset><legend class="legend1"> Planned
					New Calls </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
		
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td width="15%" colspan="1" height="22"><label class="set"
								name="business.name" id="business.name"
								ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label>
							:<font color="red">*</font></td>
							<td width="85%" colspan="2" height="22"><s:textfield
								theme="simple" name="accountName" size="50" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>

							<td width="25%" id="calculatedBox"><label id="misc.name"
								name="misc.name" ondblclick="callShowDiv(this);"><%=label.get("misc.name")%></label>:<font
								color="red">*</font></td>
							<td width="25%" colspan="1" id="calculatedBox1" nowrap="nowrap"><s:hidden
								name="miscCode" /> <s:textfield name="miscName" size="25"
								readonly="true" cssStyle="background-color: #F2F2F2;" /> <img
								src="../pages/images/recruitment/search2.gif" width="16" 
								height="15" class="iconImage" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'PerformanceMetrics_f9misc.action');"></td>


							<td width="25%" ><label 
								id="from.date" name="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:<font
								color="red">*</font></td>
							<td width="35%"><s:textfield name="fromDate" size="15"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							<!--<td width="10%" class="formtext"><label class="set"
								id="to.date" name="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<font
								color="red">*</font><s:hidden name="today" /></td>
							<td width="35%"><s:textfield name="toDate" size="15"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
						-->
						</tr>
						<tr>
							<td width="15%%" colspan="1"><label id="misc.value"
								name="misc.value" ondblclick="callShowDiv(this);"><%=label.get("misc.value")%></label>
							:<font color="red">*</font></td>
							<td width="15%"><s:textfield name="miscValue" size="15"
								maxlength="12" onkeypress="return numbersOnly();"
								cssStyle="text-align:right" /></td>

							<td width="25%"><label id="misc.weekend.value"
								name="misc.weekend.value" ondblclick="callShowDiv(this);"><%=label.get("misc.weekend.value")%></label>
							:<font color="red">*</font></td>
							<td width="15%"><s:textfield name="miscWeekendValue"
								size="15" maxlength="12" onkeypress="return numbersOnly();"
								cssStyle="text-align:right" /></td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td align="center"><input type="button" class="add"
						value="   Add to List " onclick="return callAdd();" />&nbsp;<input
						type="button" class="reset" value="   Reset "
						onclick="return resetFun();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="viewPlannedDtlFlag">
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong > Planned
					New Calls List </strong></td>
						<td width="70%" align="right">
						<%
							int totalPage = 0;
									int pageNo = 0;
						%> <s:if test="plannedListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPage = (Integer) request.getAttribute("totalPage");
													pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'PerformanceMetrics_callForShowPlanned.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'PerformanceMetrics_callForShowPlanned.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNo"
										id="pageNo" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'PerformanceMetrics_callForShowPlanned.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'PerformanceMetrics_callForShowPlanned.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PerformanceMetrics_callForShowPlanned.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>
					<tr>
						<td colspan="8">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">

							<tr>
								<td class="formtext">
								<table width="100%" border="0" cellpadding="1" cellspacing="1"
									class="sortable" id="tblSlabForMen">


									<tr class="td_bottom_border">

										<td class="formth" width="5%"><label class="set"
											id="srno" name="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td class="formth" width="10%"><label class="set"
											id="misc.name.itt" name="misc.name.itt"
											ondblclick="callShowDiv(this);"><%=label.get("misc.name.itt")%></label></td>

										<td class="formth" width="10%"><label class="set"
											id="from.date.itt" name="from.date.itt"
											ondblclick="callShowDiv(this);"><%=label.get("from.date.itt")%></label></td>
										<!--<td class="formth" width="10%"><label class="set"
								id="to.date.itt" name="to.date.itt" ondblclick="callShowDiv(this);"><%=label.get("to.date.itt")%></label></td>
							-->
										<td class="formth" width="10%"><label class="set"
											id="misc.value" name="misc.value"
											ondblclick="callShowDiv(this);"><%=label.get("misc.value")%></label></td>
										<td class="formth" width="10%"><label class="set"
											id="misc.weekend.value" name="misc.weekend.value"
											ondblclick="callShowDiv(this);"><%=label.get("misc.weekend.value")%></label></td>
										<td class="formth" nowrap="nowrap" width="10%">Edit | Delete</td>

									</tr>
									<s:if test="plannedListLength">
									<%!int d2 = 0;%>
									<%
										int i2 = pageNo * 10 - 10;
									%>
									<s:iterator value="plannedList">

										<tr class="sortableTD"
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this);">
											<td class="sortableTD" width="5%" align="center"><%=++i2%>
											</td>
											<s:hidden name="ittMiscId" />
											<s:hidden name="ittMiscCode" />
											<td class="sortableTD" width="10%" align="left"><s:property
												value="ittMiscName" /><s:hidden name="ittMiscName" /></td>

											<td class="sortableTD" width="10%" align="center"><s:property
												value="ittFromDate" /><s:hidden name="ittFromDate" /></td>
											<!--<td class="sortableTD" width="10%" align="center">
										<s:property	value="ittToDate" /><s:hidden name="ittToDate" />
									</td>
									-->
											<td class="sortableTD" width="10%" align="right"><s:property
												value="ittMiscValue" /><s:hidden name="ittMiscValue" /></td>
											<td class="sortableTD" width="10%" align="right"><s:property
												value="ittMiscWeekendValue" /><s:hidden
												name="ittMiscWeekendValue" /></td>
											<td class="sortableTD" width="5%" id="ctrlShow"
												align="center"><input type="button" class="rowEdit"
												onclick="callForEdit('<s:property value="ittMiscId"/>')" />
											| <input type="button" class="rowDelete"
												onclick="callDelete('<s:property value="ittMiscId"/>')" />
											</td>
										</tr>

									</s:iterator>
									<%
										d2 = i2;
									%>
								</s:if>
							
								<s:else>
									<td align="center" colspan="6" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
								</table>
								<s:hidden name="paracode" /></td>
							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!--<tr>
			<td align="center"><input type="button" class="save"
				value="   Save " onclick="return saveFun();" />&nbsp;<input type="button" class="reset"
				value="   Reset " onclick="return resetFun();" />&nbsp;<input
				type="button" class="back" value="   Back "
				onclick="return backFun();" /></td>
		</tr>
		-->
		</s:if>
		</table>
		</fieldset>
		</td>
		</tr>
		<tr>
			<td>
				<fieldset><legend class="legend1"> Part Wait Time </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
		<!-- Part Wait Time Start -->
		<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="formbg">
					<tr>
						<td>
							<table width="98%" border="0" align="center" cellpadding="1"
										cellspacing="2">
								<tr>
									<td colspan="6" class="formhead"><strong
												class="forminnerhead"> </strong></td>
									</tr>
								<tr>
								<td width="28%" height="22" ><label
									name="is.part.wait.time.appl" class="set" id="is.part.wait.time.appl"
									ondblclick="callShowDiv(this);"><%=label.get("is.part.wait.time.appl")%></label>
								:</td>
								<td><s:checkbox name="isPartWaitTimeChecked"  onclick="showPartWaitTimeBox();"/>
					<s:hidden name="hiddenPartWaitTimeFlag" id="hiddenPartWaitTimeFlag"/></td>
								<td height="22" class="formtext">&nbsp;</td>
								<td height="22">&nbsp;</td>

							</tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
		</fieldset>
		</td>
		</tr>
		
		<!-- Part Wait Time End -->
		<tr>
			<td>
				<fieldset><legend class="legend1"> Call Type </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
		<!-- Call Types Start -->
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="formbg">
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="1"
										cellspacing="2">
							<tr>
									<td colspan="6" class="formhead"><strong
												class="forminnerhead"> </strong></td>
							</tr>
							<tr>
									<td  class="formhead">&nbsp;</td>
							</tr>
						<tr>
							<td width="10%" ><label id="add.new.call"
								name="add.new.call" ondblclick="callShowDiv(this);"><%=label.get("add.new.call")%></label>
							:<font color="red"></font></td>
							<td width="15%"><s:textfield name="addNewCall" size="30"
								maxlength="45" />
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap"><label id="add.new.call.desc" 
								name="add.new.call.desc" ondblclick="callShowDiv(this);"><%=label.get("add.new.call.desc")%></label>
							:<font color="red"></font></td>
							<td width="15%"><s:textfield name="addNewCallDesc" size="50"
								maxlength="45" />
							</td>
						</tr>
						<tr>
						<td width="3%" ></td>
							<td ><input type="button" class="add"
								value="   Add to List " onclick="return callType();" /></td>
						</tr>
						<tr>
									<td  class="formhead">&nbsp;</td>
							</tr>
							<tr>
               					<td width="10%" class="formtext" colspan="1" nowrap="nowrap"></td>
               					<td width="25%" colspan="1" nowrap="nowrap"><s:optiontransferselect size="10" doubleSize="10" doubleId="selDivId" id="availDivId"
								label="Employee Division" rightTitle="Excluded Call Types" leftTitle="Available Call Types" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:220px;"
								doubleCssStyle="width:220px;" name="availDiv" multiple="true"
								buttonCssClass="token" list="%{hashmapDiv}"
								doubleName="selDiv" doubleList="%{hashmapDivSel}" /></td>
              			    </tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		 <tr>
              <td  colspan="1" nowrap="nowrap" cssStyle="font-size: 1em;">Note : Any new Call Type added to Excluded Call Types list will take effect from next schedule run.</td>
          </tr>
		</table>
		</fieldset>
		</td>
		</tr>
		<!-- Call Types End -->
			<!-- Repair code start -->
		<tr>
			<td>
				<fieldset><legend class="legend1"> Repair Code </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="formbg">
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="1"
										cellspacing="2">
							<tr>
									<td colspan="6" class="formhead"><strong
												class="forminnerhead"> </strong></td>
							</tr>
							<tr>
									<td  class="formhead">&nbsp;</td>
							</tr>
						<tr>
							<td width="10%" ><label id="add.repair.code"
								name="add.repair.code" ondblclick="callShowDiv(this);"><%=label.get("add.repair.code")%></label>
							:<font color="red"></font></td>
							<td width="15%"><s:textfield name="addRepairCode" size="30"
								maxlength="45" />
							</td>
						</tr>
						<tr>
							<td width="10%"  nowrap="nowrap"><label id="add.repair.code.desc"
								name="add.repair.code.desc" ondblclick="callShowDiv(this);"><%=label.get("add.repair.code.desc")%></label>
							:<font color="red"></font></td>
						<td width="15%"><s:textfield name="addRepairCodeDesc" size="50"
								maxlength="45" />
						</tr>
						<tr>
						<td width="3%" ></td>
							<td ><input type="button" class="add"
								value="   Add to List " onclick="return callAddRepairCode();" /></td>
						</tr>
						<tr>
									<td  class="formhead">&nbsp;</td>
							</tr>
							<tr>
               					<td width="10%" class="formtext" colspan="1" nowrap="nowrap"></td>
               					<td width="25%" colspan="1" nowrap="nowrap"><s:optiontransferselect size="10" doubleSize="10" doubleId="selRepairCodeId" id="availRepairCodeId"
								label="Repair Codes" rightTitle="Excluded Repair Codes" leftTitle="Available Repair Codes" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:220px;"
								doubleCssStyle="width:220px;" name="repairCode" multiple="true"
								buttonCssClass="token" list="%{hashmapRepairCode}"
								doubleName="selRepairCode" doubleList="%{hashmapRepairCodeSel}" /></td>
              			    </tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr> 
		 <tr>
              <td  colspan="1" nowrap="nowrap" cssStyle="font-size: 1em;">Note : Any new Repair Code added to Excluded Repair Codes list will take effect from next schedule run.</td>
          </tr>
		</table>
		</fieldset>
		</td>
		</tr>
		<!-- repair code end -->
			<!-- closure code start -->
		<tr>
			<td>
				<fieldset><legend class="legend1"> Cause Code </legend>
					<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="formbg">
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="1"
										cellspacing="2">
							<tr>
									<td colspan="6" class="formhead"><strong
												class="forminnerhead"> </strong></td>
							</tr>
							<tr>
									<td  class="formhead">&nbsp;</td>
							</tr>
						<tr>
							<td width="10%" ><label id="add.closure.code"
								name="add.closure.code" ondblclick="callShowDiv(this);"><%=label.get("add.closure.code")%></label>
							:<font color="red"></font></td>
							<td width="15%"><s:textfield name="addClosureCode" size="30"
								maxlength="45" />
							</td>
						</tr>
						<tr>
							<td width="10%"  nowrap="nowrap"><label id="add.closure.code.desc"
								name="add.closure.code.desc" ondblclick="callShowDiv(this);"><%=label.get("add.closure.code.desc")%></label>
							:<font color="red"></font></td>
							
						<td width="15%"><s:textfield name="addClosureCodeDesc" size="50"
								maxlength="45" />
						</tr>
						
						<tr>
						<td width="3%" ></td>
							<td ><input type="button" class="add"
								value="   Add to List " onclick="return callAddClosureCode();" /></td>
						</tr>
						<tr>
									<td  class="formhead">&nbsp;</td>
							</tr>
							<tr>
               					<td width="10%" class="formtext" colspan="1" nowrap="nowrap"></td>
               					<td width="25%" colspan="1" nowrap="nowrap"><s:optiontransferselect size="10" doubleSize="10" doubleId="selClosureCodeId" id="availClosureCodeId"
								label="Repair Codes" rightTitle="Excluded Closure Codes" leftTitle="Available Closure Codes" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:220px;"
								doubleCssStyle="width:220px;" name="closureCode" multiple="true"
								buttonCssClass="token" list="%{hashmapClosureCode}"
								doubleName="selclosureCode" doubleList="%{hashmapClosureCodeSel}" /></td>
              			    </tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr> 
		 <tr>
              <td  colspan="1" nowrap="nowrap" cssStyle="font-size: 1em;">Note : Any new Cause Code added to Excluded Cause Codes list will take effect from next schedule run.</td>
          </tr>
		</table>
		</fieldset>
		</td>
		</tr>
		<!-- closure code end -->
		<tr>
					<td align="center"><input type="button" class="add"
						value="   Save " onclick="return callSave();" />&nbsp;<input
						type="button" class="reset" value="   Cancel "
						onclick="return backFun();" /></td>
				</tr>
	</table>
</s:form>
<script>
onLoad();
	function onLoad(){
 		var checkActiveValue = document.getElementById('hiddenPartWaitTimeFlag').value;
 		var checkboxActiveVar = document.getElementById('paraFrm_isPartWaitTimeChecked').checked;
 		if((checkboxActiveVar == false) && checkActiveValue=='L'){
	 		document.getElementById('hiddenPartWaitTimeFlag').value="L";
			document.getElementById('paraFrm_isPartWaitTimeChecked').checked =false;
 		}else{
 			document.getElementById('paraFrm_isPartWaitTimeChecked').checked=true;
			document.getElementById('hiddenPartWaitTimeFlag').value="S";
 		}
		showPartWaitTimeBox();
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PerformanceMetrics_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function callAdd(){
		var miscCodeVar = trim(document.getElementById('paraFrm_miscCode').value);
		if (miscCodeVar=="") {
			alert("please select Misc Name.");
			document.getElementById('paraFrm_miscName').focus();
			return false;
		}
		var fDate=document.getElementById('paraFrm_fromDate').value;
 		////var tDate=document.getElementById('paraFrm_toDate').value;
 		if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
	////*	if(trim(document.getElementById('paraFrm_toDate').value) == "") {
	///			alert("Please select or enter "+document.getElementById('to.date').innerHTML.toLowerCase());
	///			document.getElementById('paraFrm_toDate').focus();
	///			return false;
	///	}
		
	///	 if(!validateDate("paraFrm_fromDate","from.date")){
  	///		return false;
  		
  	///	}
  	///	if(!validateDate("paraFrm_toDate","to.date")){
	///	return false;
	///	}
		
	///	if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'from.date', 'to.date')){
	///	return false;
	///	}
	///*/
		if(trim(document.getElementById('paraFrm_miscValue').value) == "") {
				alert("Please enter "+document.getElementById('misc.value').innerHTML.toLowerCase());
				document.getElementById('paraFrm_miscValue').focus();
				return false;
		}
		if(trim(document.getElementById('paraFrm_miscWeekendValue').value) == "") {
				alert("Please enter "+document.getElementById('misc.weekend.value').innerHTML.toLowerCase());
				document.getElementById('paraFrm_miscWeekendValue').focus();
				return false;
		}
			document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="PerformanceMetrics_generatePlannedListDtl.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	}
  	function callForEdit(plannedId){
			    try{
				   	document.getElementById("paraFrm").action="PerformanceMetrics_editPlannedLstDtl.action";
				  	document.getElementById('paraFrm_paracode').value=plannedId;
				  	document.getElementById("paraFrm").target="main";
				    document.getElementById("paraFrm").submit();
			   	}catch (e)
				{
				alert(e);
				}
		}
	function resetFun() {
			document.getElementById('paraFrm_miscCode').value="";
			document.getElementById('paraFrm_miscName').value="";
			document.getElementById('paraFrm_fromDate').value="";
			////document.getElementById('paraFrm_toDate').value="";
			document.getElementById('paraFrm_miscValue').value="";
			document.getElementById('paraFrm_miscWeekendValue').value="";
			document.getElementById('paraFrm_paracode').value="";
	}
	function callDelete(id){
		var conf=confirm("Are you sure to delete this record?");   
   		if(conf) {
		   	document.getElementById("paraFrm").action="PerformanceMetrics_callDelete.action";
		  	document.getElementById('paraFrm_paracode').value=id;
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	   }else {
	   		return false;
	   }
   }
   
   function callPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNo').value;	
		actPage = document.getElementById('myPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNo').value = actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNo').value=actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNo').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNo').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNo').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNo').focus();
		      return false;
	        }
	         document.getElementById('myPage').value=pageNo;
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	function callType(){
		if(trim(document.getElementById('paraFrm_addNewCall').value) == "") {
				alert("Please enter call type.");
				document.getElementById('paraFrm_addNewCall').focus();
				return false;
		}
		if(trim(document.getElementById('paraFrm_addNewCallDesc').value) == "") {
				alert("Please enter call type description.");
				document.getElementById('paraFrm_addNewCallDesc').focus();
				return false;
		}
		document.getElementById('paraFrm').target="main";		  
		document.getElementById("paraFrm").action="PerformanceMetrics_addNewCallType.action";
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target="main";
	}
	
	function callSave(){
	selectList();
	
				document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="PerformanceMetrics_saveClientCallType.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	}
	
	
	
	function selectList(){
		var selId = document.getElementById('selDivId');
	 	var availId = document.getElementById('availDivId');
		selectAllOptionsList(selId,availId);
		
		selId = document.getElementById('selRepairCodeId');
	 	availId = document.getElementById('availRepairCodeId');
	 	selectAllOptionsList(selId,availId);
	 	
	 	selId = document.getElementById('selClosureCodeId');
	 	availId = document.getElementById('availClosureCodeId');
	 	selectAllOptionsList(selId,availId);
	 	
	}
	function selectAllOptionsList(selId,availId){
		
		try{
		for(i=0;i<selId.length;i++)
		selId.options[i].selected=true;
		for(i=0;i<availId.length;i++)
		availId.options[i].selected=true;
	
	}catch(e)
	{
		alert(e);
	}
}

	
	
	
	function criteriaValidation(){
		selectList();
		
			
				if(document.getElementById('selDivId').value==""){
					deSelectList();
					alert("Please select atleast one call type.");
					document.getElementById('selDivId').focus();
					return false;
				}
				
				if(document.getElementById('selRepairCodeId').value==""){
					deSelectList();
					alert("Please select atleast one repair.");
					document.getElementById('selRepairCodeId').focus();
					return false;
				}
				if(document.getElementById('selClosureCodeId').value==""){
					deSelectList();
					alert("Please select atleast one closure.");
					document.getElementById('selClosureCodeId').focus();
					return false;
				}
				
		/////deSelectList();
		
		return true;
	}
	function deSelectList(){
		try{
		var selId = document.getElementById('selTypeId');
		var availId = document.getElementById('availTypeId');
		deSelectAllOptions(selId,availId);
		
		 selId = document.getElementById('selRepairCodeId');
		 availId = document.getElementById('availRepairCodeId');
		deSelectAllOptions(selId,availId);
		
		selId = document.getElementById('selClosureCodeId');
	 	availId = document.getElementById('availClosureCodeId');
	 	deSelectAllOptions(selId,availId);
	 	
		
		}catch(e){
			///alert(e);
		}
	}
	function deSelectAllOptions(selId,availId){
		try{
			for(i=0;i<selId.length;i++)
			selId.options[i].selected=false;
			for(i=0;i<availId.length;i++)
			availId.options[i].selected=false;
		}catch(e)
		{
			///alert(e);
		}
	}
	function showPartWaitTimeBox(){
	
		if(document.getElementById('paraFrm_isPartWaitTimeChecked').checked ==true){
			document.getElementById('hiddenPartWaitTimeFlag').value="S";
		} else {
			document.getElementById('hiddenPartWaitTimeFlag').value="L";
		}
	}
	
	function callAddRepairCode(){
		if(trim(document.getElementById('paraFrm_addRepairCode').value) == "") {
				alert("Please enter repair code.");
				document.getElementById('paraFrm_addRepairCode').focus();
				return false;
		}
		if(trim(document.getElementById('paraFrm_addRepairCodeDesc').value) == "") {
				alert("Please enter repair code description.");
				document.getElementById('paraFrm_addRepairCodeDesc').focus();
				return false;
		}
		
		document.getElementById('paraFrm').target="main";		  
		document.getElementById("paraFrm").action="PerformanceMetrics_addNewRepairCode.action";
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target="main";
	}
	
	
	function callAddClosureCode(){
		if(trim(document.getElementById('paraFrm_addClosureCode').value) == "") {
				alert("Please enter closure code.");
				document.getElementById('paraFrm_addClosureCode').focus();
				return false;
		}
		if(trim(document.getElementById('paraFrm_addClosureCodeDesc').value) == "") {
				alert("Please enter closure code description.");
				document.getElementById('paraFrm_addClosureCodeDesc').focus();
				return false;
		}
		
		document.getElementById('paraFrm').target="main";		  
		document.getElementById("paraFrm").action="PerformanceMetrics_addNewClosureCode.action";
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target="main";
	}
	function imposeMaxLength(Event, Object, MaxLen)	{
	        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
	
</script>