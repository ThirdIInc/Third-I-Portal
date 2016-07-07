<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="PerformanceMetrics" id="paraFrm" validate="true"
	theme="simple" target="main">
	
	<s:hidden name="accountCode" />
<s:hidden name="myPage" id="myPage" />
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
					<td width="93%" class="txt"><strong class="text_head">Escalation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left"><input
				type="button" class="back" value="   Back "
				onclick="return backFun();" /></td>
		</tr>
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
							<td width="15%" class="formtext"><label class="set"
								id="esca.from.date" name="esca.from.date" ondblclick="callShowDiv(this);"><%=label.get("esca.from.date")%></label>:<font
								color="red">*</font></td>
							<td width="35%"><s:textfield name="fromDate" size="15"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							<td width="10%" class="formtext"><label class="set"
								id="esca.to.date" name="esca.to.date" ondblclick="callShowDiv(this);"><%=label.get("esca.to.date")%></label>:<font
								color="red">*</font><s:hidden name="today" /></td>
							<td width="35%"><s:textfield name="toDate" size="15"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>
						<tr>
							<td colspan="5" align="center"><input type="button"
								class="add" value="   Generate List "
								onclick="return callAdd();" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="viewListDtlFlag">

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
								id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
								
							<td class="formth" width="10%"><label class="set"
								id="escalation.number" name="escalation.number" ondblclick="callShowDiv(this);"><%=label.get("escalation.number")%></label></td>
								

							</tr>
									<%!int d2 = 0;%>
									<%
										int i2 = 0;		
																	
									%>
							<s:iterator value="dateList">

								<tr class="sortableTD"
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this);">
								<td class="sortableTD" width="5%"  align="center">
								<%=++i2%>
								</td>
									<td class="sortableTD" width="10%"  align="center"><s:property
										value="dayName" /><s:hidden name="dayName" /></td>

									<td class="sortableTD" width="10%" align="center">
									
									<input type="text" class="text" name="escalation"  value='<s:property value="escalation" />'
														id="<%="escalation"+i2%>" maxlength="5"
														Style="text-align:right" /><!--  
									
									<s:textfield name="escalation"
									size="15" maxlength="4" onkeypress="return numbersOnly();" />
									
									--></td>


								</tr>


							</s:iterator>
							<%
										d2 = i2;
									%>

						</table>
						</td>
					</tr>

				</table>
				</td>
			</tr>
			<tr>
			<td align="center"><input type="button" class="save"
				value="   Save " onclick="return saveFun();" />&nbsp;<input type="button" class="reset"
				value="   Reset " onclick="return resetFun();" />&nbsp;<input
				type="button" class="back" value="   Back "
				onclick="return backFun();" /></td>
		</tr>
		</s:if>


		
	</table>
</s:form>


<script type="text/javascript">

		function callAdd(){
	
		///Date Shift for period start
		var fDate=document.getElementById('paraFrm_fromDate').value;
 		var tDate=document.getElementById('paraFrm_toDate').value;
 
 		if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('esca.from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
		if(trim(document.getElementById('paraFrm_toDate').value) == "") {
				alert("Please select or enter "+document.getElementById('esca.to.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
		}
		
		if(!validateDate("paraFrm_fromDate","esca.from.date")){
  			return false;
  		
  		
  		}
  	
  	
  		if(!validateDate("paraFrm_toDate","esca.to.date")){
		return false;
	}
		
		if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'esca.from.date', 'esca.to.date')){
	
	return false;
	}
		
		var fromDate = fDate.split("-");	
		var toDate = tDate.split("-");	
		var newDate = (toDate[0] - fromDate[0])+1; 
		
			if( newDate > 7 ){
				alert("From date and to date difference should not be greater than 7 days.");
				document.getElementById('paraFrm_toDate').value = "";
				document.getElementById('paraFrm_toDate').focus();
				return false;
			}
		
	
			document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="PerformanceMetrics_generateListDtl.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	}


	function saveFun(){	
		
			
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='PerformanceMetrics_saveEscalationDtl.action';
		document.getElementById("paraFrm").submit();
		return true;
	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PerformanceMetrics_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function resetFun(){
	try{
		var table = document.getElementById('tblSlabForMen');
		var rowCount = table.rows.length; 
		///alert("rowCount=="+rowCount); 
		for(var i=1;i<(rowCount);i++){
		
			document.getElementById('escalation'+i).value = "";
		}
	  }catch(e){
	  	///alert(e);
	  }
	 
	}
</script>
