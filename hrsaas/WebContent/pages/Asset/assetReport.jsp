<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="AssetReport" method="post" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg" >
       <tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset MIS Report
					 </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%">
						<s:if test="%{viewFlag}">
							<input type="button" class="token" onclick="return callReportMis();" value=" Show Report" />
						</s:if>
					    <s:submit cssClass="reset" action="AssetReport_reset" theme="simple" value=" Reset"  />
				    </td>
					<td width="22%" align="right">
						<div align="right">
							<font color="red">*</font> Indicates Required
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="assetWaraHouse" name="assetWaraHouse" ondblclick="callShowDiv(this);"><%=label.get("assetWaraHouse")%></label>:<s:hidden
						name="warehouseCode" /></td>
					<td width="25%" colspan="1"><s:textfield name="warehouseName"
						size="25" theme="simple" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'AssetReport_f9actionWarehouse.action');"></td>

					<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="assetVendorName" name="assetVendorName" ondblclick="callShowDiv(this);"><%=label.get("assetVendorName")%></label> :<s:hidden
						name="vendorCode" /></td>
					<td width="25%" colspan="1"><s:textfield name="vendorName"
						size="25" theme="simple" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'AssetReport_f9actionVendor.action');"></td>
				</tr>
				<tr>
					<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="assetCat" name="assetCat" ondblclick="callShowDiv(this);"><%=label.get("assetCat")%></label> :<s:hidden
						name="categoryCode" /></td>
					<td width="25%" colspan="1"><s:textfield name="categoryName"
						size="25" theme="simple" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'AssetReport_f9actionCategory.action');"></td>

					<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="assetSubType" name="assetSubType" ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label> :<s:hidden
						name="subTypeCode" /></td>
					<td width="25%" colspan="1"><s:textfield name="subTypeName"
						size="25" theme="simple" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'AssetReport_f9actionSubType.action');"></td>
				</tr>
				<tr>
					<td width="25%" class="formtext"><label  class = "set"  id="assetRetType" name="assetRetType" ondblclick="callShowDiv(this);"><%=label.get("assetRetType")%></label>:</td>
					<td width="25%"><s:select theme="simple" name="returnType"
						cssStyle="width:130"
						list="#{'':'---Select---','R':'Returnable','N':'Non-Returnable'}" /></td>

					<td width="25%" class="formtext"> <label  class = "set"  id="assetAvailability" name="assetAvailability" ondblclick="callShowDiv(this);"><%=label.get("assetAvailability")%></label>:</td>
					<td width="25%"><s:select theme="simple" name="availability"
						cssStyle="width:130"
						list="#{'':'---Select---','U':'Available','A':'Assigned','L':'Lost','D':'Damaged'}" /></td>

				</tr>

				<tr>
					<td width="25%" class="formtext"><label  class = "set"  id="assetPurDate" name="assetPurDate" ondblclick="callShowDiv(this);"><%=label.get("assetPurDate")%></label>:</td>
					<td width="25%"><s:textfield name="fromDate" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td width="25%" class="formtext"><label  class = "set"  id="assetPurToDate" name="assetPurToDate" ondblclick="callShowDiv(this);"><%=label.get("assetPurToDate")%></label>:</td>
					<td width="25%"><s:textfield name="toDate" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>

				</tr>
				<!-- LEASE FROM DATE & LEASE TO DATE -->
				<tr>
					<td width="25%" class="formtext"><label  class = "set"  id="assetLeaFromDate" name="assetLeaFromDate" ondblclick="callShowDiv(this);"><%=label.get("assetLeaFromDate")%></label>:</td>
					<td width="25%"><s:textfield name="leaFromDate" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_leaFromDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td width="25%" class="formtext"><label  class = "set"  id="assetLeaToDate" name="assetLeaToDate" ondblclick="callShowDiv(this);"><%=label.get("assetLeaToDate")%></label>:</td>
					<td width="25%"><s:textfield name="leaToDate" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_leaToDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>

				</tr>
				<!-- INSURANCE FROM DATE & INSURANCE TO DATE -->
				<tr>
					<td width="25%" class="formtext">
						<label  class = "set"  id="assetInsFromDate" name="assetInsFromDate" ondblclick="callShowDiv(this);"><%=label.get("assetInsFromDate")%></label>:
					</td>
					<td width="25%">
						<s:textfield name="insFromDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" /> 
						<s:a href="javascript:NewCal('paraFrm_insFromDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16" align="absmiddle" width="16">
						</s:a>
					</td>
					<td width="25%" class="formtext">
					    <label  class = "set"  id="assetInsToDate" name="assetInsToDate" ondblclick="callShowDiv(this);"><%=label.get("assetInsToDate")%></label>:
					</td>
					<td width="25%">
						<s:textfield name="insToDate" size="25"	onkeypress="return numbersWithHiphen();" theme="simple"	maxlength="10" /> 
						<s:a href="javascript:NewCal('paraFrm_insToDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16" align="absmiddle" width="16">
						</s:a>
					</td>
				</tr>
				<tr>
					<td width="25%" class="formtext">
						<label  class = "set"  id="assetRepType" name="assetRepType" ondblclick="callShowDiv(this);"><%=label.get("assetRepType")%></label>
					</td>
					<td width="25%">
						<s:select name="reportType" headerKey="Pdf"	headerValue="Pdf" theme="simple" list="#{'Xls':'Xls', 'Txt':'Text'}" />
					</td>
				</tr>
			</table>
			<s:hidden name="today" />
		</td>
	</tr>
		
</table>
</s:form>
<script type="text/javascript">
	
	function callReportMis(){
		var purFromDt = document.getElementById('paraFrm_fromDate').value;
		var purToDt = document.getElementById('paraFrm_toDate').value;
		var leFromDt = document.getElementById('paraFrm_leaFromDate').value;
		var leToDt = document.getElementById('paraFrm_leaToDate').value;
		var inFromDt = document.getElementById('paraFrm_insFromDate').value;
		var inToDt = document.getElementById('paraFrm_insToDate').value;
		if(purFromDt!=""){
			if(!validateDate('paraFrm_fromDate','assetPurDate')){
			return false;
			}
		}
		if(purToDt!=""){
			if(!validateDate('paraFrm_toDate','assetPurToDate')){
			return false;
			}
		}
		if(leFromDt!=""){
			if(!validateDate('paraFrm_leaFromDate','assetLeaFromDate')){
			return false;
			}
		}
		if(leToDt!=""){
			if(!validateDate('paraFrm_leaToDate','assetLeaToDate')){
			return false;
			}
		}
		if(inFromDt!=""){
			if(!validateDate('paraFrm_insFromDate','assetInsFromDate')){
			return false;
			}
		}
		if(inToDt!=""){
			if(!validateDate('paraFrm_insToDate','assetInsToDate')){
			return false;
			}
		}		
  		callReport('AssetReport_report.action');
	}
	function callReturnType()
	{
		document.getElementById("paraFrm").action="AssetReport_changeReturnType.action";
		document.getElementById("paraFrm").submit();
	}
 	function autoDate () {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		if ( tMonth < 10) tMonth = "0"+tMonth;
		if ( tDate < 10) tDate = "0"+tDate;
		document.getElementById("paraFrm_today").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
}
autoDate();
</script>
