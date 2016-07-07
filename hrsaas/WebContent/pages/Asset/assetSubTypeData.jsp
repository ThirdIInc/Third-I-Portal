<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AssetSubTypes" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
	<s:hidden name="hiddenassetCategoryCode"></s:hidden>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset
					Sub Type </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="3"><s:hidden name="assetsubTypeCode" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">Asset
					Sub-Type</strong></td>
					<s:hidden name="assetCategoryCode" />
				</tr>
				<tr>
					<td width="25%" colspan="1" nowrap="nowrap" class="formtext">
					<label class="set" name="assetsubtype.cat" id="assetsubtype.cat"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.cat")%></label>
					<font color="red">*</font> :</td>
					<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
						name="assetCategoryName" size="25" maxlength="70" readonly="true"></s:textfield>
					<img src="../pages/images/search2.gif" height="16" id='ctrlHide'
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'AssetSubTypes_f9actionCategory.action'); "></td>
					<td width="25%" colspan="1" nowrap="nowrap" class="formtext">
					<label class="set" name="assetsubtype.name" id="assetsubtype.name"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.name")%></label>
					<font color="red">*</font> :</td>
					<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
						name="assetSubTypeName" size="25" maxlength="50"
						onkeypress="return allCharacters();"></s:textfield></td>
				</tr>
				<tr>
					<td height="22" class="formtext"><label class="set"
						name="assetsubtype.rettype" id="assetsubtype.rettype"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.rettype")%></label>
					<font color="red">*</font> :</td>
					<td height="22"><s:select name="returnType" headerKey=""
						cssStyle="width:153"
						list="#{'':'--Select--','R':'Returnable','N':'Non-Returnable'}"
						onchange="return callReturnable();" /></td>
					<td height="22" class="formtext"><label class="set"
						name="assetsubtype.invtype" id="assetsubtype.invtype"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.invtype")%></label>
					<font color="red">*</font> :<s:hidden name="hiddenInvType" /></td>
					<td height="22"><s:select name="invType" headerKey=""
						cssStyle="width:153"
						list="#{'':'--Select--','I':'Itemized Inventory','S':'Bulk Inventory'}"
						onchange="return callReturnable1();" /></td>
				</tr>
				<tr>
					<td height="22" class="formtext"><label class="set"
						name="assetsubtype.relevel" id="assetsubtype.relevel"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.relevel")%></label>:</td>
					<td height="22"><s:textfield name="reOrderLevel" size="25"
						onkeypress="return numbersOnly();">
					</s:textfield></td>
					<td colspan="1" width="25%"><label class="set"
						name="assetsubtype.leadtime" id="assetsubtype.leadtime"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.leadtime")%></label>
					:</td>
					<td colspan="1" width="25%"><s:textfield name="leadTime"
						size="25" maxlength="20" theme="simple"
						onkeypress="return numbersOnly();"></s:textfield></td>
				</tr>
				<tr>
					<td height="22" class="formtext"><label class="set"
						name="assetsubtype.unit" id="assetsubtype.unit"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.unit")%></label>
					<font color="red">*</font> :</td>
					<td height="22"><s:select name="unit" headerKey=""
						headerValue="--Select--" list="%{hashmap}" cssStyle="width:153" />
					</td>
					<td width="25%" colspan="1" nowrap="nowrap" class="formtext">
					<label class="set" name="assetsubtype.safetystock" id="assetsubtype.safetystock"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.safetystock")%></label>
					 :</td>
					<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
						name="safetystock" size="25" maxlength="50"
						onkeypress="return numbersOnly();"></s:textfield></td>

				</tr>

				<tr>

					<td width="20%" height="22" class="formtext"><label
						name="is.active" class="set" id="is.active"
						ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					:</td>
					<td><s:select name="isActive" list="#{'Y':'Yes','N':'No'}"
						cssStyle="width:151;z-index:5;" /></td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>

				</tr>



			</table>
			</td>
		</tr>
		<s:hidden name="paraId" />
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">Properties</strong></td>
				</tr>
				<tr>
					<td colspan="2"><label class="set" name="assetsubtype.propertyName"
						id="assetsubtype.propertyName" ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.propertyName")%></label><font color="red">*</font> :</td>
					<td width="75%" colspan="3"><s:textfield name="propertyName"
						size="25" maxlength="20" theme="simple"></s:textfield></td>
				</tr>
				<tr>
					<td colspan="2"><label class="set" name="assetsubtype.propertyUnit"
						id="assetsubtype.propertyUnit" ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.propertyUnit")%></label> :</td>
					<td colspan="3"><s:select name="properUnit" headerKey=""
						headerValue="--Select--" list="%{hashmap}" cssStyle="width:153" /></td>
				</tr>
				
				<tr>
					<td colspan="5" align="center"><input type="button" name="add"
						onclick="return callAdd();" value="Add" class="add"></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">
				<tr class="sortable">
					<s:hidden name="myPage" id="myPage" />
					<td class="formth" width="25%" align="center"  colspan="1"><label class="set"
						name="assetsubtype.srno" id="assetsubtype.srno"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.srno")%></label>
					</td>
					<td class="formth" width="25%" colspan="1" align="center"><label class="set"
						name="assetsubtype.propertyNamelst" id="assetsubtype.propertyNamelst"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.propertyNamelst")%></label>
					</td>
					<td class="formth" width="25%" colspan="1" align="center"><label class="set"
						name="assetsubtype.propertyUnitlst" id="assetsubtype.propertyUnitlst"
						ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.propertyUnitlst")%></label>
					</td>
				
					<td class="formth" width="25%"  colspan="1" align="center" class="sortableTD" id='ctrlHide' ><label class="set"
						name="assetsubtype.delete" id="assetsubtype.delete"
						ondblclick="callShowDiv(this);">Edit/Delete</label>
					</td>
				</tr>
				<%
					int i=1;
				%>
				<s:iterator value="propertyList">
					<tr class="sortable">
					     <td   width="25%" align="center" class="sortableTD"><%=i%></td>
						<td  width="25%" align="center" class="sortableTD">&nbsp;
							<s:hidden name="propertyNameItt"></s:hidden> 
							<s:property	value="propertyNameItt" />
						</td>
						<td  width="25%"  align="center" class="sortableTD">&nbsp;
							<s:hidden name="propertyUnitItt" />
							<s:hidden name="propertyUnitNameItt" />
							<s:property value="propertyUnitNameItt"/>
						</td>
						<td  width="25%" align="center"  class="sortableTD" id='ctrlHide' >&nbsp;
						<input type="button" class="rowEdit" title="Edit Record"
								onclick="callForEdit('<s:property	value="propertyNameItt" />','<s:property value="propertyUnitItt"/>',<%=(i)%>)" />
							<input type="button" class="rowDelete"	theme="simple"  title="Delete Record"
								   onclick="return callDeleteAssetSubType('<%=(i)%>');" />
							
						</td>
					</tr>
					<%
					 i++;
				    %>
				</s:iterator>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	
	<s:hidden name="hiddenEdit" id="hiddenEdit" /> 

</s:form>
<script>
function callForEdit(propertyName,unitId,rowEditNo)
{
	document.getElementById('hiddenEdit').value=rowEditNo;
	
	
	document.getElementById('paraFrm_propertyName').value=propertyName;
	document.getElementById('paraFrm_properUnit').value=unitId;
}

function callAdd()
{
	var checkpropertyName=document.getElementById('paraFrm_propertyName').value;
	 
		var field=["paraFrm_propertyName"];
	    	var label=["assetsubtype.propertyName"];
	    	var types=["enter"];
	    	
	     		if(!validateBlank(field,label,types)){
			return false;
			}

 
	document.getElementById('paraFrm').action='AssetSubTypes_addToList.action';
	document.getElementById('paraFrm').submit();
}

	function saveFun()
	{	
		var fieldName = ["paraFrm_assetCategoryName","paraFrm_assetSubTypeName","paraFrm_returnType","paraFrm_invType","paraFrm_unit"];
		var fieldNameSpecial = ["paraFrm_assetSubTypeName"];
		var lableName = ["assetsubtype.cat","assetsubtype.name","assetsubtype.rettype","assetsubtype.invtype","assetsubtype.unit"];
		var types = ["select","enter","select","select","select"];
	      if(!validateBlank(fieldName,lableName,types))
	          return false;
	        
	      if(!f9specialchars(fieldNameSpecial))
	      {
	              return false;
	       }
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='AssetSubTypes_save.action';
		document.getElementById("paraFrm").submit();
		
		return true;
		
	}
	function editFun()
	{
		return true;
	}

	function resetFun() {
		//document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AssetSubTypes_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AssetSubTypes_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'AssetSubTypes_delete.action';
			document.getElementById('paraFrm').submit();
		}
			
	}
	function callDeleteAssetSubType(id){
	 var con=confirm('Do you  really want to delete this record ?');
		    if(con){
			document.getElementById("paraFrm_paraId").value =id;
			document.getElementById("paraFrm").action="AssetSubTypes_deleteAssetSubType.action";
			document.getElementById("paraFrm").submit();
			}else 
			return false;
	}
</script>