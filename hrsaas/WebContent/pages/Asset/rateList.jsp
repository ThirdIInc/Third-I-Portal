<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="RateList" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Vendor
					Rate List </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="4" class="formhead"><strong class="forminnerhead"><label
						class="set" id="venList" name="venList"
						ondblclick="callShowDiv(this);"><%=label.get("venList")%></label></strong></td>
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set" id="venName"
						name="venName" ondblclick="callShowDiv(this);"><%=label.get("venName")%></label><font
						color="red"> *</font>:</td>
					<s:hidden  name="rateCode" />
					<s:hidden name="unit" />
					<s:hidden name="vendorCode" />
					<td width="30%" colspan="1" class="formtext"><s:textfield
						name="vendorName" size="25" readonly="true"></s:textfield> <img
						class="imageIcon" src="../pages/images/search2.gif" height="16" id="ctrlHide"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'RateList_f9actionVendor.action'); "></td>

					<td width="20%" colspan="1"><label class="set" id="city"
						name="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
					:</td>
					<td width="30%" colspan="1" class="formtext"><s:textfield
						name="cityName" size="25" readonly="true"></s:textfield>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="itmName" name="itmName"
						ondblclick="callShowDiv(this);"><%=label.get("itmName")%></label>
					<font color="red">*</font> : <s:hidden name="subTypeCode" /></td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="subTypeName" readonly="true" /> <img class="imageIcon"
						src="../pages/images/search2.gif" height="16" align="absmiddle" id="ctrlHide"
						width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'RateList_f9actionSubType.action'); ">
					</td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="ven.prices1" name="ven.prices"
						ondblclick="callShowDiv(this);"><%=label.get("ven.prices")%></label>
					(per <s:property value="unit" />) <font color="red">*</font> :</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="price" maxlength="10" onkeypress="return numbersWithDot();" /></td>
				</tr>
				<tr>
					<td width="100%" colspan="4" align="center"><s:if
						test="%{insertFlag}">
						<s:submit cssClass="add" onclick="return callAdd();" id="ctrlHide"
							action="RateList_addItem" theme="simple"
							value="   Add To List   " />
					</s:if><s:else></s:else></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="8" class="text_head"><strong
						class="forminnerhead"><label class="set" id="rate.List"
						name="rate.List" ondblclick="callShowDiv(this);"><%=label.get("rate.List")%></label></strong></td>
				</tr>
				<tr>
					<td class="formth" width="10%" height="22" valign="top"><label
						class="set" id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
					<td class="formth" width="30%" height="22" valign="top"><label
						class="set" id="itmName1" name="itmName"
						ondblclick="callShowDiv(this);"><%=label.get("itmName")%></label></td>
					<td class="formth" width="30%" height="22" valign="top"><label
						class="set" id="ven.prices" name="ven.prices"
						ondblclick="callShowDiv(this);"><%=label.get("ven.prices")%></label></td>
					<td class="formth" width="10%" height="22" valign="top"><label
						class="set" id="unite" name="unite"
						ondblclick="callShowDiv(this);"><%=label.get("unite")%></label></td>
					<td class="formth" width="20%" height="22" valign="top"><label
						class="set" id="edt/remv" name="edt/remv"
						ondblclick="callShowDiv(this);"><%=label.get("edt/remv")%></label></td>
				</tr>
				<%
								int k = 1;
								%>
				<s:iterator value="rateList" status="stat">
					<tr>
						<td width="10%" align="center" class="border2"><%=k%></td>
						<td width="30%" class="border2"><s:property
							value="itemIterator" /><s:hidden name="itemIterator" /><s:hidden
							name="itemCodeIterator" /></td>
						<td width="30%" class="border2"><s:property
							value="priceIterator" /><s:hidden name="priceIterator" /><s:hidden
							name="cityNameItereator" /></td>
						<td width="10%" class="border2"><s:property
							value="unitIterator" /><s:hidden name="unitIterator" />
						<td align="center" width="20%" class="border2" nowrap="nowrap"><input
							type="button" class="edit"
							onclick="callForEdit('<s:property value="itemIterator"/>','<s:property value="itemCodeIterator"/>',<s:property value="priceIterator"/>,'<s:property value="unitIterator"/>','<%=k%>')"
							value="   Edit" /> <input type="button" class="delete"
							onclick="callForDelete(<%=k%>)" value="   Remove" /></td>
					</tr>
					<%
									k++;
									%>
				</s:iterator>
				<input type="hidden" name="count" id="count" value="<%=k-1%>" />
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="paraId"></s:hidden>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>
<script>


	function saveFun()
	{
		var rateCode=document.getElementById("paraFrm_rateCode").value;
			var count = document.getElementById("count").value;
	
			var venName=document.getElementById('venName').innerHTML.toLowerCase();
			var itmName=document.getElementById('itmName').innerHTML.toLowerCase();
			
			if(count=="" || count=="0"){
			alert("Please add "+itmName+" in table.");
			return false;;
			}
			
			document.getElementById('paraFrm').action='RateList_save.action';
			document.getElementById('paraFrm').submit();	
	
	}
	
	function resetFun()
	{
		
		document.getElementById('paraFrm').action='RateList_reset.action';
			document.getElementById('paraFrm').submit();	
	}
	
	function reportFun()
	{
		
		document.getElementById('paraFrm').action='RateList_report.action';
			document.getElementById('paraFrm').submit();	
	}
	
	function backFun()
	{
		
		document.getElementById('paraFrm').action='RateList_cancel.action';
			document.getElementById('paraFrm').submit();	
	}

		function formValidate(type){
			var rateCode=document.getElementById("paraFrm_rateCode").value;
			var count = document.getElementById("count").value;
			if(type=='save'){
			if(rateCode !=""){
				alert("Please click on Update button to update the record.");
				return false;
			}
			}else{
				if(rateCode ==""){
				alert("Please select a record to update.");
				return false;
			}
			}
			var venName=document.getElementById('venName').innerHTML.toLowerCase();
			var itmName=document.getElementById('itmName').innerHTML.toLowerCase();
			if(document.getElementById("paraFrm_vendorName").value==""){
			alert("Please select "+venName);
			return false;
			}
			if(count=="" || count=="0"){
			alert("Please add "+itmName+" in table.");
			return false;;
			}
		}
		function callAdd(){
			var fields =["paraFrm_vendorName","paraFrm_subTypeName","paraFrm_price"];
			var lables =["venName","itmName","ven.prices"];
			var type =["select","select","enter"];
			var price = document.getElementById("paraFrm_price").value;
			if(!validateBlank(fields,lables,type)){
			return false;
			}
			if(isNaN(price)) {
	   			alert("Enter valid price in price field.");
		 		document.getElementById('paraFrm_price').focus();
				return false;
			}
		}
		function callForEdit(itemName,itemCode,price,unit,rowId){
			document.getElementById("paraFrm_subTypeName").value=itemName;
			document.getElementById("paraFrm_subTypeCode").value=itemCode;
			document.getElementById("paraFrm_price").value=price;
			document.getElementById("paraFrm_unit").value=unit;
			document.getElementById("paraFrm_paraId").value=rowId;
			
			document.getElementById("paraFrm").action="RateList_showList.action";
			document.getElementById("paraFrm").submit();
		}
		 function deleteFun() {
			/*alert("in deleteFun");*/
				var con=confirm('Do you want to delete the record(s) ?');
			 if(con){
				
				
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'RateList_delete.action';
				document.getElementById('paraFrm').submit();
			}
		}
		function callForDelete(rowId){
			var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
				document.getElementById("paraFrm_paraId").value=rowId;
				document.getElementById("paraFrm").action="RateList_removeItem.action";
	   		 	document.getElementById("paraFrm").submit();
	    }
	    }
	    function editFun() 
	    {
			return true;
		}
</script>