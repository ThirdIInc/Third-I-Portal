<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>




<s:form action="AssetMaster" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt"><strong class="text_head">Asset
					Master </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<s:hidden name="purchaseFlag"></s:hidden>

				<s:if test="purchaseFlag">
					<s:hidden name="backFlag" value="true" />
					<!--	<tr>
						<td width="78%"><s:if test="%{insertFlag}">
							<input type="button" class="add" theme="simple"
								value="  Add New" onclick="return formValidate('save',this)" />
						</s:if> <input type="button" class="token" onclick="return callBack();"
							value="  Back " /></td>
						<td width="22%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
						</td>
					</tr> -->
				</s:if>
				<s:else>
					<s:hidden name="backFlag" value="false" />
				</s:else>
				<tr>
					<!--  
						<td width="78%"><s:if test="%{insertFlag}">
							<input type="button" class="add"  
								value="Add New" onclick="return formValidate('save',this)" />
						</s:if> <s:if test="%{updateFlag}">
							<input type="button" class="edit"
								onclick="return formValidate('update',this);" value=" Update" />
						</s:if> <s:if test="viewFlag">
							<input type="button" class="search" value=" Search"
								onclick="javascript:callsF9(500,325,'AssetMaster_f9action.action');" />
						</s:if> <s:submit cssClass="reset" action="AssetMaster_reset"
							theme="simple" value=" Reset" /> <s:if test="%{deleteFlag}">
							<s:submit cssClass="delete" action="AssetMaster_delete"
								theme="simple" value=" Delete"
								onclick="return callDelete1('paraFrm_code');" />
						</s:if><s:else></s:else> <s:if test="%{viewFlag}">
							<input type="button" class="token"
								onclick="callReportforSelected('AssetMaster_report.action','paraFrm_code')"
								value=" Report" />
						</s:if><s:else></s:else></td> -->
					<td align="left"><jsp:include
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="1">
						<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead"><label class="set"
								id="asstMaster" name="asstMaster"
								ondblclick="callShowDiv(this);"><%=label.get("asstMaster")%></label></strong></td>
						</tr>
					</table>
					</td>
				</tr>

				<s:if test="%{updateApp}">
					<tr>
						<td width="25%" colspan="1" class="formtext"><label
							class="set" id="assetCat" name="assetCat"
							ondblclick="callShowDiv(this);"><%=label.get("assetCat")%></label>
						<font color="red">*</font> : <s:hidden name="code" /></td>
						<td width="30%" colspan="1"><s:textfield size="25"
							name="assetname" readonly="true" /> <s:hidden name="assetCode" /></td>
						<td width="20%" colspan="1" class="formtext"><label
							class="set" id="asset.subType" name="asset.subType"
							ondblclick="callShowDiv(this);"><%=label.get("asset.subType")%></label><font
							color="red">*</font> : <s:hidden name="subTypeCode" /><s:hidden
							name="invType" /></td>
						<td width="30%" colspan="1"><s:textfield size="25"
							name="subTypeName" readonly="true" /></td>
					</tr>


					<tr>
						<td width="20%" colspan="1"><label class="set" id="vndrName"
							name="vndrName" ondblclick="callShowDiv(this);"><%=label.get("vndrName")%></label>
						:</td>
						<td width="30%" colspan="1" class="formtext"><s:textfield
							name="vendorName" size="25" readonly="true"></s:textfield><s:hidden
							name="vendorCode" /> <img class="imageIcon" id='ctrlHide'
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'AssetMaster_f9actionVendor.action'); "></td>
						<td width="20%" colspan="1" class="formtext"></td>
						<td width="30%" colspan="1"></td>

					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="25%" colspan="1" class="formtext"><label
							class="set" id="assetCat1" name="assetCat"
							ondblclick="callShowDiv(this);"><%=label.get("assetCat")%></label><font
							color="red">*</font> : <s:hidden name="code" /></td>
						<td width="30%" colspan="1"><s:textfield size="25"
							name="assetname" readonly="true" /> <img class="imageIcon"
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'AssetMaster_f9actionCategory.action'); ">
						<s:hidden name="assetCode" /></td>
						<td width="20%" colspan="1" class="formtext"><label
							class="set" id="asset.subType" name="asset.subType"
							ondblclick="callShowDiv(this);"><%=label.get("asset.subType")%></label>
						<font color="red">*</font> : <s:hidden name="subTypeCode" /><s:hidden
							name="invType" /></td>
						<td width="30%" colspan="1"><s:textfield size="25"
							name="subTypeName" readonly="true" /> <img class="imageIcon"
							id="ctrlShow" src="../pages/images/search2.gif" height="16"
							align="absmiddle" width="16" theme="simple"
							onclick="return callSubType(); "></td>
					</tr>


					<tr>
						<td width="20%" colspan="1"><label class="set" id="vend.name"
							name="vend.name" ondblclick="callShowDiv(this);"><%=label.get("vend.name")%></label></td>
						<td width="30%" colspan="1" class="formtext"><s:textfield
							name="vendorName" size="25" readonly="true"></s:textfield><s:hidden
							name="vendorCode" /> <img class="imageIcon" id='ctrlHide'
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'AssetMaster_f9actionVendor.action'); "></td>

					</tr>
				</s:else>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="asst.purDate" name="asst.purDate"
						ondblclick="callShowDiv(this);"><%=label.get("asst.purDate")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						maxlength="10" name="purchaseDate"
						onkeypress="return numbersWithHiphen();" /> <s:a
						href="javascript:NewCal('paraFrm_purchaseDate','DDMMYYYY');">
						<img src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16" id='ctrlHide'>
					</s:a></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="asst.purPrice" name="asst.purPrice"
						ondblclick="callShowDiv(this);"><%=label.get("asst.purPrice")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="25"
						name="price" maxlength="10"
						onkeypress="return numbersWithSpaceandDot();" /></td>

				</tr>
				<tr>

					<!-- <td width="20%" colspan="1" class="formtext"><label
						class="set" id="asst.assignTo" name="asst.assignTo"
						ondblclick="callShowDiv(this);"><%//=//label.get("asst.assignTo")%></label>: </td>
						
					<td width="30%" colspan="1"><s:textfield name="assigned"
						size="25" readonly="true"></s:textfield></td>	
					-->
					<s:hidden name="assigned"></s:hidden>

					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="asstQnty" name="asstQnty"
						ondblclick="callShowDiv(this);"><%=label.get("asstQnty")%></label>
					<font color="red">*</font> :</td>
					<td width="30%" colspan="1"><s:if test="%{updateApp}">
						<s:textfield size="25" name="quant" readonly="true" />
					</s:if><s:else>
						<s:textfield size="25" name="quant"
							onkeypress="return numbersWithDot();"
							onblur="callAssignedAsset();" maxlength="20" />
					</s:else></td>

					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="asstAvailable" name="asstAvailable"
						ondblclick="callShowDiv(this);"><%=label.get("asstAvailable")%></label>:</td>
					<td width="30%" colspan="1"><s:textfield name="available"
						size="25" readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="asstStatus" name="asstStatus"
						ondblclick="callShowDiv(this);"><%=label.get("asstStatus")%></label>
					:</td>
					<td width="30%" colspan="1"><s:select name="status"
						list="#{'U':'Unassigned','A':'Assigned'}" disabled="true" /></td>
					<td width="20%" colspan="1"></td>
					<s:hidden name="tableLength"></s:hidden>
					<td width="30%" colspan="1"></td>

				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="asstDescr" name="asstDescr"
						ondblclick="callShowDiv(this);"><%=label.get("asstDescr")%></label>:&nbsp;</td>
					<td width="80%" colspan="2"><s:textarea cols="60" rows="3"
						name="description" onkeyup="callLength('descCnt');" /><s:hidden
						name="assetUnit" /> <img src="../pages/images/zoomin.gif"
						height="12" align="absmiddle" id='ctrlHide' width="12"
						theme="simple"
						onclick="javascript:callWindow('paraFrm_description','asstDescr','','200','200');">
					</td>


					</td>
					<td colspan="2" id='ctrlHide'>Remaining chars<s:textfield
						name="descCnt" readonly="true" size="5"></s:textfield></td>


				</tr>


				<s:if test="updateApp"></s:if>
				<s:else>
					<s:if test="itemizedFlag">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="asstWareHouse" name="asstWareHouse"
								ondblclick="callShowDiv(this);"><%=label.get("asstWareHouse")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="1" class="formtext"><s:textfield
								name="warehouseName" size="25" readonly="true"></s:textfield><s:hidden
								name="warehouseCode" /> <img class="imageIcon"
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'AssetMaster_f9actionWarehouse.action'); "></td>
							<td width="20%" colspan="1"><label class="set"
								id="assetQuantity" name="assetQuantity"
								ondblclick="callShowDiv(this);"><%=label.get("assetQuantity")%></label>
							<font color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield
								name="quantityWareHouse" size="25"
								onkeypress="return numbersWithDot();" maxlength="5"
								onblur="return callFromTo();"></s:textfield></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="asstInvPrefix" name="asstInvPrefix"
								ondblclick="callShowDiv(this);"><%=label.get("asstInvPrefix")%></label>
							<font color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield name="invPrefix"
								onkeypress="return allCharacters();" size="25"></s:textfield></td>
							<td width="50%" colspan="2"><label class="set"
								id="assetFrom" name="assetFrom" ondblclick="callShowDiv(this);"><%=label.get("assetFrom")%></label>
							:<s:textfield name="fromInv" size="5">
							</s:textfield><label class="set" id="assetTo" name="assetTo"
								ondblclick="callShowDiv(this);"><%=label.get("assetTo")%></label>
							:<s:textfield name="toInv" size="5"></s:textfield> <input
								type="button" value="Generate Inventory" class="token"
								onclick="return callGenerateInv();"></td>

						</tr>
					</s:if>
					<s:elseif test="commonFlag">


						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="asstWareHouse" name="asstWareHouse"
								ondblclick="callShowDiv(this);"><%=label.get("asstWareHouse")%></label>
							<font color="red">*</font> :</td>
							<td width="30%" colspan="1" class="formtext"><s:textfield
								name="warehouseName" size="25" readonly="true"></s:textfield><s:hidden
								name="warehouseCode" /> <img class="imageIcon" id='ctrlHide'
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'AssetMaster_f9actionWarehouse.action'); "></td>

							<td width="20%" colspan="1"><label class="set"
								id="assetQuantity" name="assetQuantity"
								ondblclick="callShowDiv(this);"><%=label.get("assetQuantity")%></label>
							<font color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield
								name="quantityWareHouse" size="25" maxlength="20"
								onkeypress="return numbersWithDot();"></s:textfield></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="invetory.code" name="invetory.code"
								ondblclick="callShowDiv(this);"><%=label.get("invetory.code")%></label>
							<font color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield name="invPrefix"
								onkeypress="return allCharacters();" size="25"></s:textfield></td>

							<td width="50%" colspan="2"><input type="button"
								value="Add Inventory" class="token"
								onclick="return callAddInv();"></td>

						</tr>
					</s:elseif>
				</s:else>
			</table>
			</td>
		</tr>


		<!-- Tab Coding starts-->
		<s:if test="tabDisplay">

			<tr>
				<td>
				<div id="tabnav" style="vertical-align: bottom">
				<ul>
					<li id="list1"><a href="#" id="menuid1" class="on"
						onclick="showCurrent('menuid1','first')"> <span>Inventory
					List </span></a></li>



					<li id="list2"><a href="#" id="menuid2"
						onclick="showCurrent( 'menuid2','second')"> <span>Property
					List </span></a></li>


				</ul>
				</div>
				</td>
			</tr>
		</s:if>
		<!-- Tab Coding ends-->

		<s:if test="commonFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead"><label class="set"
							id="asstInvList" name="asstInvList"
							ondblclick="callShowDiv(this);"><%=label.get("asstInvList")%></label></strong></td>
					</tr>
					<tr>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="assetSrNo" name="assetSrNo"
							ondblclick="callShowDiv(this);"><%=label.get("assetSrNo")%></label></td>
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="invetory.code1" name="invetory.code"
							ondblclick="callShowDiv(this);"><%=label.get("invetory.code")%></label></td>
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="asstWareHouse1" name="asstWareHouse"
							ondblclick="callShowDiv(this);"><%=label.get("asstWareHouse")%></label></td>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="assetQuantity1" name="assetQuantity"
							ondblclick="callShowDiv(this);"><%=label.get("assetQuantity")%></label>
						</td>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="assetAvailable" name="assetAvailable"
							ondblclick="callShowDiv(this);"><%=label.get("assetAvailable")%></label></td>
						<s:if test="updateApp"></s:if>
						<s:else>
							<td class="formth" width="10%" height="22" valign="top"><label
								class="set" id="assetDelete" name="assetDelete"
								ondblclick="callShowDiv(this);"><%=label.get("assetDelete")%></label></td>
						</s:else>
					</tr>
					<%
					int k = 1;
					%>
					<s:iterator value="tableList" status="stat">
						<tr>
							<td width="10%" class="sortableTD"><%=k%></td>
							<td width="30%" class="sortableTD"><s:textfield
								name="<%="inventoryCodeIterator"+k%>"
								onkeypress="return allCharacters();"
								value="%{inventoryCodeIterator}" size="30" /><s:hidden
								name="inventoryCodeIterator" /><s:hidden
								name="assignFlagIterator" /></td>
							<td width="30%" class="sortableTD"><s:property
								value="warehouseNameIterator" /><s:hidden
								name="warehouseNameIterator" /><s:hidden
								name="warehouseCodeIterator" /></td>
							<td width="10%" class="sortableTD"><s:property
								value="quantityIterator" /><s:hidden name="quantityIterator" /></td>
							<td width="10%" class="sortableTD"><s:property
								value="availableIterator" /></td>
							<s:hidden name="availableIterator" />
							<s:if test="assetmaster.updateApp"></s:if>
							<s:else>
								<td width="10%" class="sortableTD"><input type="button"
									class="rowDelete" class="delete" title="Delete Record"
									onclick="return callRemove('<%=k%>','<s:property value="assignFlagIterator"/>');" /></td>
							</s:else>
						</tr>
						<s:hidden name="isLeased" />
						<s:hidden name="isInsured" />
						<s:hidden name="leaseDate" />
						<s:hidden name="insureDate" />
						<%
						k++;
						%>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>
		<s:elseif test="itemizedFlag">
			<tr>
				<td colspan="3">
				<div id="first">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead"><label class="set"
							id="asstInvList" name="asstInvList"
							ondblclick="callShowDiv(this);"><%=label.get("asstInvList")%></label></strong></td>
					</tr>
					<tr>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="assetSrNo" name="assetSrNo"
							ondblclick="callShowDiv(this);"><%=label.get("assetSrNo")%></label></td>
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="invetory.code1" name="invetory.code"
							ondblclick="callShowDiv(this);"><%=label.get("invetory.code")%></label></td>
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="asstWareHouse1" name="asstWareHouse"
							ondblclick="callShowDiv(this);"><%=label.get("asstWareHouse")%></label></td>
						<!-- IS ON LEASE -->
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="is.On.Lease" name="is.On.Lease"
							ondblclick="callShowDiv(this);"><%=label.get("is.On.Lease")%></label></td>
						<!-- LEASE RENUAL DATE -->
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="lease.renewalDate" name="lease.renewalDate"
							ondblclick="callShowDiv(this);"><%=label.get("lease.renewalDate")%></label></td>
						<!-- IS INSURED -->
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="is.insured" name="is.insured"
							ondblclick="callShowDiv(this);"><%=label.get("is.insured")%></label></td>
						<!-- INSURE RENUAL DATE -->
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="insurance.renewalDate"
							name="insurance.renewalDate" ondblclick="callShowDiv(this);"><%=label.get("insurance.renewalDate")%></label></td>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="assetQuantity1" name="assetQuantity"
							ondblclick="callShowDiv(this);"><%=label.get("assetQuantity")%></label>
						</td>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="assetAvailable" name="assetAvailable"
							ondblclick="callShowDiv(this);"><%=label.get("assetAvailable")%></label></td>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="invStatus" name="invStatus"
							ondblclick="callShowDiv(this);"><%=label.get("invStatus")%></label></td>
						<s:if test="updateApp"></s:if>
						<s:else> 
							<td class="formth" width="10%" height="22" valign="top"><label
								class="set" id="assetDelete" name="assetDelete"
								ondblclick="callShowDiv(this);"><%=label.get("assetDelete")%></label></td>
						</s:else>
					</tr>
					<%
					int m = 1;
					%>
					<s:iterator value="tableList" status="stat">
						<tr>
							<td width="10%" class="sortableTD"><%=m%></td>
							<td width="30%" class="sortableTD"><s:textfield
								name="<%="inventoryCodeIterator"+m%>"
								onkeypress="return allCharacters();"
								value="%{inventoryCodeIterator}" size="30" /><s:hidden
								name="inventoryCodeIterator" /></td>
							<td width="30%" class="sortableTD"><s:property
								value="warehouseNameIterator" /><s:hidden
								name="warehouseNameIterator" /><s:hidden
								name="warehouseCodeIterator" /></td>
							<!-- IS ON LEASE -->
							<td class="sortableTD" align="center"><input type="checkbox"
								theme="simple" name="<%="L"+m%>" id="<%="L"+m%>"
								onclick="return callChkBox(this);" /> <s:hidden name="isLeased"
								id="<%="isLeased"+m%>" /></td>
							<!-- LEASE RENUAL DATE -->
							<td width="30%" class="sortableTD" nowrap="nowrap"><s:textfield
								theme="simple" name="leaseDate" id="<%="DTL"+m%>" size="10"
								maxlength="10" onkeypress="return numbersWithHiphen();" /><a
								href="javascript:NewCal('<%="DTL"+m%>','DDMMYYYY');"> <img
								src="../pages/images/Date.gif" class="iconImage" height="16"
								align="absmiddle" width="16" id='ctrlHide'> </a></td>
							<!-- IS INSURED -->
							<td class="sortableTD" align="center"><input type="checkbox"
								theme="simple" name="<%="I"+m%>" id="<%="I"+m%>"
								onclick="return callChkBox(this);" /> <s:hidden
								name="isInsured" id="<%="isInsured"+m %>" /></td>
							<!-- INSURE RENUAL DATE -->
							<td width="30%" class="sortableTD" nowrap="nowrap"><s:textfield
								theme="simple" name="insureDate" id="<%="DTI"+m%>" size="10"
								maxlength="10" onkeypress="return numbersWithHiphen();" /><a
								href="javascript:NewCal('<%="DTI"+m%>','DDMMYYYY');"> <img
								src="../pages/images/Date.gif" class="iconImage" height="16"
								align="absmiddle" width="16" id='ctrlHide'> </a></td>
							<td width="10%" class="sortableTD"><s:property
								value="quantityIterator" /><s:hidden name="quantityIterator"
								id="<%="quantityIterator"+m%>" /></td>
							<td width="10%" class="sortableTD"><s:textfield
								name="availableIterator"
								cssStyle="text-align: right ;border: hidden" readonly="true"
								id="<%="availableIterator"+m%>" size="7"
								onkeyup="return callAssetAvail(this);"
								onkeypress="return numbersWithDot();" /></td>

							<td width="30%" class="sortableTD"><s:select
								name='assignFlagIterator'
								list="#{'U':'Available','A':'Assigned','L':'Lost','D':'Damaged'}"
								id="<%="assignFlagIterator"+m%>"
								onchange="return callAssetStatus(this);" /></td>
							<s:if test="assetmaster.updateApp"></s:if>
							<s:else>
								<td width="10%" class="sortableTD"><input type="button"
									class="rowDelete" class="delete" title="Delete Record"
									onclick="return callRemove('<%=m%>','<s:property value="assignFlagIterator"/>');" /></td>
							</s:else>
						</tr>
						<%
						m++;
						%>
					</s:iterator>

				</table>
				</div>
				</td>
			</tr>

		</s:elseif>
 
		<s:if test="propertyDataFlag">
			
			
			<tr>
				<td colspan="3">
				<div id="second">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead"><label class="set"
							id="propertyList" name="propertyList"
							ondblclick="callShowDiv(this);"><%=label.get("propertyList")%></label></strong></td>
					</tr>
					<tr>
						<td class="formth" width="10%" height="22" valign="top"><label
							class="set" id="assetSrNo1" name="assetSrNo"
							ondblclick="callShowDiv(this);"><%=label.get("assetSrNo")%></label></td>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="invetory.code2" name="invetory.code"
							ondblclick="callShowDiv(this);"><%=label.get("invetory.code")%></label></td>
						
						<s:iterator value="propertyHeadList">
							<td class="formth" width="15%" height="22" valign="top">						
							<s:hidden name="propertyCode"/>
							<s:property  value="propertyName"/>  (<s:property value="propertyUnit"/>)
							</td>						
						</s:iterator>				
					</tr>
					
				<%int srNo=0;
				int COUNT=0;%>
				<s:iterator value="propertyList" status="stat">
						<tr>
							<td width="5%" class="sortableTD" align="center"><%=++srNo%></td>
							<td width="15%" class="sortableTD"><s:textfield
								name="inventoryCodeItt" /></td>	
								
							<s:iterator value="propertyListDtl" status="stat">
							<td width="15%" class="sortableTD">	<input type="text" value='<s:property  value="propertyNameDtl"/>'   name="<%=COUNT%>"   /> </td>							
							</s:iterator>
					
						</tr>
						<%COUNT++;%>
				</s:iterator>			
					
			</table>
			</div>
			</td>
		</tr>	
		</s:if>
		<tr>
			<td align="left"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<s:hidden name="paraId"></s:hidden>
	<s:hidden name="updateApp" /> 
	<s:hidden name="listLength"></s:hidden>
	<s:hidden name="hiddenPurchaseCode"></s:hidden>
	<s:hidden name="itemizedFlag"></s:hidden>
	<s:hidden name="commonFlag"></s:hidden>
	<s:hidden name="tabDisplay"></s:hidden>
	<s:hidden name="propertyDataFlag"></s:hidden>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<script type="text/javascript">

onloadCall();
	function onloadCall(){
			
			try{
			
			if(document.getElementById('paraFrm_tabDisplay').value=="true")
			{
					if(document.getElementById('paraFrm_itemizedFlag').value=="true")
					{
						document.getElementById('first').style.display='block';
					}
					if(document.getElementById('paraFrm_propertyDataFlag').value=="true")
					{
					document.getElementById('second').style.display='none';
					}
					document.getElementById('menuid1').className='on';
					document.getElementById('menuid2').className='li';
			}
			
			}
			catch(e)
			{
				alert(e);
			}
	
	}


function showCurrent(menuId, id){

 
try{
if(document.getElementById('paraFrm_itemizedFlag').value=="true")
			{
document.getElementById('first').style.display='none';
}
	if(document.getElementById('paraFrm_propertyDataFlag').value=="true")
	{
document.getElementById('second').style.display='none';
 }
document.getElementById('menuid1').className='li';
document.getElementById('menuid2').className='li';
 
document.getElementById(menuId).className='on';
document.getElementById(id).style.display='block';
}
catch(e)
{
	alert("value of e----------"+e);
}


//document.getElementById('paraFrm_unplaneFlag').value=menuId;
}
		
	onload();
		function onload() {
			var rowCount = document.getElementById("paraFrm_tableLength").value;
			if(eval(rowCount) >= 1) {
				for(var count=1;count<=eval(rowCount);count++) {
					var idValue1 = document.getElementById('isLeased'+count).value;
					var idValue2 = document.getElementById('isInsured'+count).value;
					//alert("v1 - "+idValue1+"   v2 - "+idValue2);
					if(idValue1 == 'Y')
						document.getElementById('L'+count).checked = true;
					else
						document.getElementById('L'+count).checked = false;
					if(idValue2 == 'Y')
						document.getElementById('I'+count).checked = true;
					else
						document.getElementById('I'+count).checked = false;
				}
			}
		}
		function callChkBox(val) {
			if(!(val.checked))
			{
				document.getElementById('DT'+val.id).value='';
			}else {
				document.getElementById('DT'+val.id).focus();
			}
		}	
		
		//Validation for leased and insurance chk boxes
		function validateChkBox() {
			var rowCount = document.getElementById("paraFrm_tableLength").value;
			for(var count=1;count<=eval(rowCount);count++){
				//For Lease			
				var idValue1 = document.getElementById('L'+count).checked;
				var quantityValue1 = document.getElementById('DTL'+count).value;
				if(idValue1 && quantityValue1==''){
					alert('Please enter '+document.getElementById('lease.renewalDate').innerHTML.toLowerCase()+' at '+count+' row');
					document.getElementById('DTL'+count).focus();
					return false;
				}else if(idValue1 && !validateDate('DTL'+count,'lease.renewalDate')){
					return false;
				}
				if(!idValue1 && quantityValue1!=''){
					alert('Please select '+document.getElementById('is.On.Lease').innerHTML.toLowerCase()+' at '+count+' row');
					document.getElementById('L'+count).focus();
					return false;
				} 
				if(idValue1) {
					document.getElementById('isLeased'+count).value = 'Y';
				}else {
					document.getElementById('L'+count).checked = false;
					document.getElementById('isLeased'+count).value = 'N';
				}
				//For Insurance			
				var idValue2 = document.getElementById('I'+count).checked;
				var quantityValue2 = document.getElementById('DTI'+count).value;
				if(idValue2 && quantityValue2==''){
					alert('Please enter '+document.getElementById('insurance.renewalDate').innerHTML.toLowerCase()+' at '+count+' row');
					document.getElementById('DTI'+count).focus();
					return false;
				}else if(idValue2 && !validateDate('DTI'+count,'insurance.renewalDate')){
					return false;
				}
				if(!idValue2 && quantityValue2!=''){
					alert('Please select '+document.getElementById('is.insured').innerHTML.toLowerCase()+' at '+count+' row');
					document.getElementById('I'+count).focus();
					return false;
				} 
				if(idValue2) {
					document.getElementById('isInsured'+count).value = 'Y';
				}else {
					document.getElementById('I'+count).checked = false;
					document.getElementById('isInsured'+count).value = 'N';
				}
			}//End of for
			return true;
		}
</script>
<script>
	   var fields=["paraFrm_assetname","paraFrm_subTypeName","paraFrm_quant"];
		var labels=["assetCat1","asset.subType","asstQnty"];
		var types=["select","select","enter"];		
		
		var labAssetQuantity=document.getElementById('asstQnty').innerHTML.toLowerCase();		
		var labInvCode=document.getElementById('invetory.code').innerHTML.toLowerCase();		
		var labQuantity=document.getElementById('assetQuantity1').innerHTML.toLowerCase();
		var labAssetAsgnTo=document.getElementById('asst.assignTo').innerHTML.toLowerCase();
		var labAssetDescr=document.getElementById('asstDescr').innerHTML.toLowerCase();
		var labAssetCat=document.getElementById('assetCat').innerHTML.toLowerCase();	
	//function formValidate(buttonType,obj){
	  function saveFun(){
	  try{
	   	var tableLength = document.getElementById("paraFrm_tableLength").value;
		var quantity    = document.getElementById("paraFrm_quant").value;
		var assetname   = document.getElementById('paraFrm_assetname').value;
		var date        = document.getElementById('paraFrm_purchaseDate').value;
		var desc        = document.getElementById('paraFrm_description').value;
		/*
		if(document.getElementById('paraFrm_code').value!="" && buttonType == 'save'){
	  		alert("Please click on update button to update the record.");
	  		return false;
  		}else{
  			if(document.getElementById('paraFrm_code').value=="" && buttonType == 'update'){
		  		alert("Please select a record to update.");
		  		return false;
	  		}
  		}*/
		if(!(validateBlank(fields,labels,types))){
		return false;
		}
		if(eval(quantity)<eval(document.getElementById("paraFrm_assigned").value)){
		alert(labAssetQuantity+" must be greater or equal to "+labAssetAsgnTo);
		return false;
		}
		if(tableLength=="" || tableLength =="0"){
		alert("Please Generate Inventory");
		return false;
		}
		if(eval(quantity)!= eval(tableLength)){
		alert("Please match the "+document.getElementById('assetQuantity1').innerHTML.toLowerCase()+" in table");
		return false;
		}
		if(date!=""){
			if(!validateDate('paraFrm_purchaseDate','asst.purDate')){
			return false;
			}
		}	
	if(document.getElementById('paraFrm_itemizedFlag').value=="true"){
	for(var i=1;i<=eval(tableLength);i++){
	if(document.getElementById('availableIterator'+i).value!=""){
		if(eval(document.getElementById('quantityIterator'+i).value) < eval(document.getElementById('availableIterator'+i).value)){
			alert("Asset "+document.getElementById('assetAvailable').innerHTML+" should be less than or eqaual to "+document.getElementById('assetQuantity1').innerHTML);
			document.getElementById('availableIterator'+i).focus();
			return false;
		}
	}else{
			alert("Please enter Asset "+document.getElementById('assetAvailable').innerHTML);
			document.getElementById('availableIterator'+i).focus();
			return false;
	}
	}
		if(!(validateChkBox())) {
		return false;
		}
	}
	if(desc.length >200){
	alert ("Maximum 200 characters are allowed in the asset description");
	return false;
	}
			//obj.disabled = true;
			document.getElementById("paraFrm").action="AssetMaster_save.action";
			document.getElementById("paraFrm").submit();
	}catch(e){}	
}
function callDelete1(id)
{
   if(document.getElementById('paraFrm_status').value=='A')
   {
   alert("Asset is already assigned, you can't delete. ");
   return false;
   }
   else{
  return callDelete(id);
   }
   
}
function callSubType(){
	var labAssetCat1=document.getElementById('assetCat1').innerHTML.toLowerCase();
	if(document.getElementById("paraFrm_assetname").value==""){
	alert("Please select "+labAssetCat1);
	return false;
	}else 
callsF9(500,325,'AssetMaster_f9actionSubType.action');
}
 function callAssignedAsset(){
 if(document.getElementById("paraFrm_code").value==""){
 	if(document.getElementById("paraFrm_quant").value==""){
  		document.getElementById("paraFrm_assigned").value="";
 		document.getElementById("paraFrm_available").value="";
	 }else{
 		document.getElementById("paraFrm_assigned").value="0";
 		document.getElementById("paraFrm_available").value=document.getElementById("paraFrm_quant").value;
 	}
 }else{
 	if(document.getElementById("paraFrm_quant").value!="")
 		document.getElementById("paraFrm_available").value=eval(document.getElementById("paraFrm_quant").value)-eval(document.getElementById("paraFrm_assigned").value);
 	else document.getElementById("paraFrm_available").value=="";
 }
 }
 
 
 
 function callGenerateInv()
 {
 	var fieldsInv =["paraFrm_subTypeName","paraFrm_warehouseName","paraFrm_quantityWareHouse","paraFrm_invPrefix"];
	var labelsInv =["asset.subType","asstWareHouse","assetQuantity","asstInvPrefix"];	
	var typesInv =["select","select","enter","enter"];	
	
		if(!(validateBlank(fieldsInv,labelsInv,typesInv))){
		return false;
		}
		else
		{
			document.getElementById("paraFrm").action="AssetMaster_generateInventory.action";
			document.getElementById("paraFrm").submit();
		}
 }
 
 function callFromTo()
 {
 		document.getElementById("paraFrm_fromInv").value="01";
 		if(eval(document.getElementById("paraFrm_quantityWareHouse").value)==0){
 		document.getElementById("paraFrm_fromInv").value="00";
 		}
 		if(eval(document.getElementById("paraFrm_quantityWareHouse").value)<10){
 		document.getElementById("paraFrm_toInv").value="0"+eval(document.getElementById("paraFrm_quantityWareHouse").value);
 		}else if(eval(document.getElementById("paraFrm_quantityWareHouse").value)>=10){
 		document.getElementById("paraFrm_toInv").value = eval(document.getElementById("paraFrm_quantityWareHouse").value);
 		}else{
 			document.getElementById("paraFrm_fromInv").value="";
 			document.getElementById("paraFrm_toInv").value ="";
 		}
 }
 
 function callAddInv()
 {
 	var fieldsInv =["paraFrm_subTypeName","paraFrm_warehouseName","paraFrm_quantityWareHouse","paraFrm_invPrefix"];
	var labelsInv =["asset.subType","asstWareHouse","assetQuantity","invetory.code"];
	var typesInv =["select","select","enter","enter"];	
	
		if(!(validateBlank(fieldsInv,labelsInv,typesInv))){
		return false;
		}
		else
		{
			document.getElementById("paraFrm").action="AssetMaster_addInventory.action";
			document.getElementById("paraFrm").submit();
		}
 }
 function callAssetAvail(){
 
 		var tableLength = document.getElementById("paraFrm_tableLength").value;
 		var assetAvailable=0;
 		
 		for(var i=1;i<=eval(tableLength);i++){
 			assetAvailable += eval(document.getElementById('availableIterator'+i).value);
 		}
 		document.getElementById('paraFrm_available').value=assetAvailable;
 }
 function callAssetStatus(obj){
 			var rowId=obj.id.replace("assignFlagIterator",'');
 			if(obj.value!='U'){
 				document.getElementById('availableIterator'+rowId).value=0;
 			}else{
 				document.getElementById('availableIterator'+rowId).value=1;
 			}
 			callAssetAvail();
 }
 function callRemove(rowid,flag)
 {
 if(document.getElementById('paraFrm_updateApp').value=="true"){
 		alert("You cant remove the inventory.");
 		return false;
 }
 	if(flag=="A"){
 		alert("This inventory is assigned, you can't remove it.");	
 		return false;
 	}else{
 	var con=confirm('Do you really want to remove this record ?');
	    if(con){
 		document.getElementById("paraFrm_paraId").value=rowid;
 		document.getElementById("paraFrm").action="AssetMaster_removeInventory.action";
 		document.getElementById("paraFrm").submit();
 	}
 	}
 }
 function callBack()
 {
 		document.getElementById('paraFrm').action = "PurchaseOrder_callForInward.action";
		document.getElementById('paraFrm').submit();
 }
 function editFun() {
 
 	return true;
 }
 function backFun() {
 	if(document.getElementById('paraFrm_backFlag').value == "true")
 	{
 		callBack();
 	}
 	else
 	{
 		document.getElementById('paraFrm').target="_self";
 		document.getElementById('paraFrm').action="AssetMaster_cancel.action";
 		document.getElementById('paraFrm').submit();
 	}
 }
 
 function resetFun(){
 	document.getElementById('paraFrm').target="_self";
 	document.getElementById('paraFrm').action="AssetMaster_reset.action";
 	document.getElementById('paraFrm').submit();
 }
 function deleteFun(){
  	var con=confirm('Do you really want to remove this record ?');
	    if(con){
 		document.getElementById("paraFrm").target="_self";
 		document.getElementById("paraFrm").action="AssetMaster_delete.action";
 		document.getElementById("paraFrm").submit();
 	}
 }
 function reportFun() {
 		document.getElementById("paraFrm").target="_self";
 		document.getElementById("paraFrm").action="AssetMaster_report.action";
 		document.getElementById("paraFrm").submit();
 }
 
 function callLength(type){ 
		
		 if(type=='descCnt'){
					var cmt =document.getElementById('paraFrm_description').value;
					var remain = 200 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_description').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_description').style.background = '#FFFFFF';
				
				}
 }  	
</script>

<script type="text/javascript">

</script>



