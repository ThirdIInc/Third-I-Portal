<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AssetApplicationMisReport" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="50%" class="txt"><strong class="text_head">Asset Count Report</strong></td>
					<td width="50%">
					    <div align="right"><font color="red">*</font> Indicates	Required</div>
					</td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
				
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="78%" colspan="1">
				<input type="button" class="token"	onclick="return callMisReport();"
					   value=" Asset Count Report"  />
				<s:submit cssClass="reset" action="AssetCountReport_clear" theme="simple" value=" Reset" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
					<tr>
						<!-- Asset Type -->
						<td colspan="1" width="20%" class="formtext">
							<label 	name="assetType" id="assetType" ondblclick="callShowDiv(this);"><%=label.get("assetType")%></label>
							<font color="red">*</font> :
						</td>
						<td colspan="3">
							 <s:hidden name="assetTypeCode"></s:hidden>
							 <s:textfield name="assetTypeName" theme="simple" size="25" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18" class="iconImage"
								onclick="javascript:callsF9(500,325,'AssetCountReport_f9AssetType.action');">
						</td>
						<!-- Asset Sub Type -->
						<td colspan="1" width="20%" class="formtext">
							<label 	name="assetSubType" id="assetSubType" ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label>
							<font color="red">*</font> :
						</td>
						<td colspan="3">
							<s:hidden name="assetSubTypeCode"></s:hidden>
						 	<s:textfield name="assetSubTypeName" theme="simple" size="25" readonly="true" /> 
						 		<img src="../pages/images/recruitment/search2.gif" height="18"
									 align="absmiddle" width="18" class="iconImage"
									 onclick="javascript:callsF9(500,325,'AssetCountReport_f9AssetSubType.action');">
						</td>
						
					</tr>
					<!-- Ware House -->
					<tr>
						<td colspan="1" width="20%" class="formtext">
							<label 	name="wareHouse" id="wareHouse" ondblclick="callShowDiv(this);"><%=label.get("wareHouse")%></label>
							<font color="red">*</font> :
						</td>
						<td colspan="3">
							<s:hidden name="wareHouseCode"></s:hidden>
						 	<s:textfield name="wareHouseName" theme="simple" size="25" readonly="true" /> 
						 		<img src="../pages/images/recruitment/search2.gif" height="18"
									 align="absmiddle" width="18" class="iconImage"
									 onclick="javascript:callsF9(500,325,'AssetCountReport_f9WareHouse.action');">
						</td>
						
					</tr>

			</table>
			</td>
		</tr>
	</table>

</s:form>
<script type="text/javascript">
	function callMisReport(){
	  	document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action='AssetCountReport_generateReport.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
	}
</script>
