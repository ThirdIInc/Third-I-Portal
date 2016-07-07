<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="PurchaseOrder" validate="true" id="paraFrm"
	theme="simple">

	<%
		String cmpName = "";
		String companyAddress = "";
		try {
			cmpName = (String) request.getAttribute("logo");
			companyAddress = (String) request
			.getAttribute("companyAddress");

		} catch (Exception e) {
			cmpName = "";
			companyAddress = "";
			e.printStackTrace();
		}
	%>
	<s:hidden name="applicationLevel" />
	<s:hidden name="referenceId" />
	<s:hidden name="purchaseCode" />
	<s:hidden name="empCode" />
	<s:hidden name="vendorCode" />
	<s:hidden name="subTypeCode" />
	<s:hidden name="paraId"></s:hidden>
	<s:hidden name="today"></s:hidden>
	<s:hidden name="costCenterId"></s:hidden>
	<s:hidden name="subcostCenterId"></s:hidden>
	<s:hidden name="checkRemove" />
	<s:hidden name="hiddenPurchaseCode" />
	<s:hidden name="hiddenStatusval"></s:hidden>

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="isApprove"></s:hidden>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Purchase
					Order </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font>
					Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>

<tr>
	<td colspan="3">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					
					<s:if test="approverCommentsFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td class="formtext" width="25%"><label class="set"
							id="comment1" name="comment1" ondblclick="callShowDiv(this);"><%=label.get("comment")%></label>
					<font color="red">*</font>	:</td>

						<td><s:textarea name="appcomment" cols="75" rows="3"
							onkeyup="callLength('descCnt');"></s:textarea> <img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id='ctrlHide' width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_appcomment','appcomment','','500','500');"></td>
						<td colspan="1" id='ctrlHide'>Remaining chars<s:textfield
							name="descCnt" readonly="true" size="5"></s:textfield></td>

					</tr>
				</table>
				</td>
			</tr>
		</s:if>


		<s:if test="commentFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="4" class="formhead"><strong
							class="forminnerhead"><label class="set" id="approver"
							name="approver" ondblclick="callShowDiv(this);"><%=label.get("approver")%></label>
						</strong></td>
					</tr>
					<tr>
						<td class="formth" width="8%"><label class="set" id="sno"
							name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
						<td class="formth" width="30%"><label class="set"
							id="appName" name="appName" ondblclick="callShowDiv(this);"><%=label.get("appName")%></label></td>
						<td class="formth" width="15%" align="center"><label
							class="set" id="dat" name="dat" ondblclick="callShowDiv(this);"><%=label.get("dat")%></label></td>
						<td class="formth" width="10%" align="center"><label
							class="set" id="status" name="status"
							ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
						<td class="formth" width="30%"><label class="set"
							id="appComments1" name="appComments"
							ondblclick="callShowDiv(this);"><%=label.get("appComments")%></label></td>
					</tr>
					<%
					int j = 1;
					%>
					<s:iterator value="apprList" status="stat">
						<tr>
							<td width="8%" class="td_bottom_border"><%=j++%></td>
							<td width="30%" class="td_bottom_border"><s:property
								value="approverName" /></td>
							<td width="15%" align="center" class="td_bottom_border"><s:property
								value="approvedDate" /></td>
							<td width="10%" align="center" class="td_bottom_border"><s:property
								value="approveStatus" /></td>
							<td width="30%" class="td_bottom_border"><s:property
								value="approverComment" />&nbsp;</td>

						</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td width="30%" valign="top"><img
				src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>" />
			</td>
			<td width="30%" valign="top"><%=companyAddress%></td>
			<td width="40%">
			<table class="formbg" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="30%"><label class="set" id="orderno"
					name="orderno" ondblclick="callShowDiv(this);"><%=label.get("orderno")%></label> : </td>
					<td width="70%"><s:textfield name="purchaseOrderNo" size="25"
						readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td width="30%"><label class="set" id="poName"
					name="poName" ondblclick="callShowDiv(this);"><%=label.get("poName")%></label> : </td>
					<td width="70%"><s:if test="isApprove">
						<s:textfield name="poName" size="25" readonly="true"></s:textfield>
					</s:if> <s:else>
						<s:textfield name="poName" size="25"></s:textfield>
					</s:else></td>
				</tr>
				<tr>
					<td width="30%" nowrap="nowrap"><label class="set" id="requiredby"
					name="requiredby" ondblclick="callShowDiv(this);"><%=label.get("requiredby")%></label> : </td>
					<td width="70%"><s:if test="isApprove">
						<s:textfield name="requiredBy" size="12" readonly="true"
							onkeypress="return numbersWithHiphen();" />
						<s:if test="%{keepInfoFlag}">
							<s:a href="javascript:NewCal('paraFrm_requiredBy','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="16" id='ctrlHide'>
							</s:a>
						</s:if>
					</s:if> <s:else>
						<s:textfield name="requiredBy" size="12"
							onkeypress="return numbersWithHiphen();" />
						<s:if test="%{keepInfoFlag}">
							<s:a href="javascript:NewCal('paraFrm_requiredBy','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="16" id='ctrlHide'>
							</s:a>
						</s:if>
					</s:else></td>
				</tr>
				<td width="30%" class="formtext"><label class="set" id="status"
					name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
				<td width="70%" colspan="1"> 
				<s:select theme="simple" name="status"
					disabled="true" cssStyle="width:130"
					list="#{'P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded','C':'Canceled','D':'Drafted'}" /></td>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table class="formbg" width="100%" cellpadding="0" cellspacing="0"
				border="0">

				<tr>
					<td width="33%" height="100%">
					<table class="formbg" width="100%" height="100%" cellpadding="0"
						cellspacing="0" border="0">
						<tr>
							<td class="formhead" align="center" colspan="2"><font
								color="red">*</font><strong class="forminnerhead">VENDOR 
							DETAILS </strong></td>
						</tr>
						<tr>
							<td width="50%"><label class="set" id="venName"
								name="venName" ondblclick="callShowDiv(this);"><%=label.get("venName")%> </label> :</td>
							<td width="50%" align+"right"><s:if test="%{keepInfoFlag}">
								<img class="imageIcon" src="../pages/images/search2.gif"
									height="16" align="absmiddle" width="16" theme="simple"
									onclick="return callVendor();">
							</s:if>
																				
							</td>
						</tr>
						<tr>
							
							<td colspan="2">
							
							<s:if test="isApprove">
							
								<s:textfield name="vendorName" readonly="true"
									cssStyle="border:none;" />
							</s:if> <s:else>
								<s:textfield name="vendorName" readonly="true" cssStyle="border:none;" />
							</s:else></td>
						</tr>
						<tr>
							<td colspan="2">
							
							<s:if test="isApprove">
							
								<s:textfield name="vendorAddress" readonly="true"
									cssStyle="border:none;" />
							</s:if> <s:else>
								<s:textfield name="vendorAddress" readonly="true" cssStyle="border:none;" />
							</s:else></td>
						</tr>
						<tr>
							<td colspan="2">
							<s:textfield readonly="true" name="vendorContact"
								cssStyle="border:none;" /></td>
						</tr>

					</table>
					</td>
					<td width="33%" height="100%">
					<table class="formbg" width="100%" height="100%" cellpadding="0"
						cellspacing="0" border="0">
						<tr>
							<td class="formhead" align="center" colspan="2"><font
								color="red">*</font><strong class="forminnerhead">
								<label class="set" id="shippingAddress"
					name="shippingAddress" ondblclick="callShowDiv(this);"><%=label.get("shippingAddress")%></label>
								 </strong></td>
						</tr>

						<tr>
							
							<td  colspan="2" align="center">
							<s:if test="isApprove">
								<s:textarea name="shippingAddress" cols="40" readonly="true"
									rows="7"></s:textarea>
							</s:if> <s:else>
								<s:textarea name="shippingAddress" cols="40" rows="7"></s:textarea>
							</s:else></td>
						</tr>
					</table>
					</td>
					<td width="34%" height="100%">
					<table class="formbg" width="100%" height="100%" cellpadding="0"
						cellspacing="0" border="0">
						<tr>
							<td class="formhead" align="center" colspan="2"><font
								color="red">*</font><strong class="forminnerhead">
								<label class="set" id="billAddress"
					name="billAddress" ondblclick="callShowDiv(this);"><%=label.get("billAddress")%></label>
								 </strong>
								
							 </strong></td>
						</tr>

						<tr>
							
							<td align="center" colspan="2"><s:if test="isApprove">
								<s:textarea name="billingAddress" cols="40" readonly="true"
									rows="7"></s:textarea>
							</s:if> <s:else>
								<s:textarea name="billingAddress" cols="40" rows="7"></s:textarea>
							</s:else></td>
						</tr>
					</table>
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
					<td colspan="4" class="text_head"><strong
						class="forminnerhead"><label class="set" id="details"
						name="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></strong></td>
				</tr>
				<tr>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" id="asset1" name="asset"
						ondblclick="callShowDiv(this);"><%=label.get("asset")%></label> <font
						color="red">*</font> :</td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="subTypeName" readonly="true" /><s:if test="%{keepInfoFlag}">
						<img class="imageIcon" src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple"
							onclick="return callAsset();">
					</s:if></td>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" id="asset.price" name="asset.price"
						ondblclick="callShowDiv(this);"><%=label.get("asset.price")%></label>
					(per <s:property value="unit" />) <font color="red">*</font> :</td>
					<td width="25%" colspan="1">
					
					<s:if test="isApprove">
						<s:textfield size="25"  readonly="true"
						name="price" maxlength="20"   />
					</s:if>
					<s:else>
					<s:textfield size="25"
						name="price" maxlength="20" onkeyup="return calculateTotal();"
						onkeypress="return numbersWithDot()"
						onblur="return calculateTotal();" />
					</s:else>
				</td>
				</tr>
				<tr>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" id="quty1" name="quty" ondblclick="callShowDiv(this);"><%=label.get("quty")%></label>
					(<s:property value="unit" />) <font color="red">*</font> :</td>
					<s:hidden name="unit" />
					<td width="25%" colspan="1">
					
					<s:if test="isApprove">
						<s:textfield size="25"  readonly="true"
						name="quantity" maxlength="20"
						  />
					</s:if>
					
					<s:else>
						<s:textfield size="25"
						name="quantity" maxlength="20"
						onkeypress="return numbersWithDot();"
						onkeyup="return calculateTotal();"
						onblur="return calculateTotal();" />
					
					</s:else>
					
				</td>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" id="tPrice1" name="tPrice"
						ondblclick="callShowDiv(this);"><%=label.get("tPrice")%></label> :</td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="totalPrice" readonly="true" /></td>
				</tr>
				<tr>
					<td width="25%"></td>
					<td width="25%"></td>
					<td width="25%"><s:if test="%{keepInfoFlag}">
						<s:submit cssClass="add" onclick="return callAdd();"
							action="PurchaseOrder_addItem" theme="simple" value="   Add   " />
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
					<td colspan="8"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);"><%=label.get("list")%></label></strong></td>
				</tr>
				<tr>
					<td class="formth" width="5%" height="22" valign="top"><label
						class="set" id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
					<td class="formth" width="25%" height="22" valign="top"
						align="center"><label class="set" id="asset" name="asset"
						ondblclick="callShowDiv(this);"><%=label.get("asset")%></label></td>
					<td class="formth" width="12%" height="22" valign="top"
						align="center"><label class="set" id="asset.price1"
						name="asset.price" ondblclick="callShowDiv(this);"><%=label.get("asset.price")%></label></td>
					<td class="formth" width="12%" height="22" valign="top"
						align="center"><label class="set" id="quty" name="quty"
						ondblclick="callShowDiv(this);"><%=label.get("quty")%></label></td>
					<td class="formth" width="15%" height="22" valign="top"
						align="center"><label class="set" id="unit" name="unit"
						ondblclick="callShowDiv(this);"><%=label.get("unit")%></label></td>
					<td class="formth" width="20%" height="22" valign="top"
						align="center"><label class="set" id="tPrice" name="tPrice"
						ondblclick="callShowDiv(this);"><%=label.get("tPrice")%></label></td>
					<s:if test="isApprove">
						<td class="formth" width="20%" height="22" valign="top">&nbsp;</td>
					</s:if>
					<s:else>
						<td class="formth" width="20%" height="22" valign="top"
							align="center"><label class="set" id="eDel" name="eDel"
							ondblclick="callShowDiv(this);"><%=label.get("eDel")%></label></td>
					</s:else>
				</tr>
				<%
				int k = 1;
				%>
				<s:iterator value="orderList" status="stat">
					<tr>
						<td width="5%" align="center" class="sortableTD"><%=k%></td>
						<td width="25%" class="sortableTD"><s:property
							value="itemIterator" /><s:hidden name="itemIterator" /><s:hidden
							name="itemCodeIterator" id="<%="itemCodeIterator"+k%>" /></td>
						<td width="12%" class="sortableTD" align="right"><s:property
							value="priceIterator" /><s:hidden name="priceIterator" /></td>
						<td width="12%" class="sortableTD" align="right"><s:property
							value="quantityIterator" /><s:hidden name="quantityIterator" /></td>
						<td width="15%" class="sortableTD"><s:property
							value="unitIterator" /><s:hidden name="unitIterator" /><s:hidden
							name="cityIterator" /></td>
						<td width="20%" class="sortableTD" align="right"><s:property
							value="totalIterator" /><s:hidden name="totalIterator" /></td>
						</td>
						<td align="center" width="20%" class="sortableTD" nowrap="nowrap"><s:if
							test="%{PurchaseOrder.keepInfoFlag}">
							<input type="button" class="rowEdit"
								onclick="callForEdit('<s:property value="itemIterator"/>','<s:property value="itemCodeIterator"/>',<s:property value="priceIterator"/>,
					'<s:property value="quantityIterator"/>','<s:property value="unitIterator"/>','<s:property value="totalIterator"/>','<%=k%>')"
								title="Edit Record" />
							<input type="button" class="rowDelete"
								onclick="callForDelete(<%=k%>)" title="Delete Record" />
						</s:if></td>
					</tr>
					<%
					k++;
					%>
				</s:iterator>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="tAmount" name="tAmount"
						ondblclick="callShowDiv(this);">Sub Total</label></td>
					<td class="text_head" width="20%" align="right">&nbsp;<s:textfield cssStyle="text-align: right;"
						name="totalAmount" readonly="true"></s:textfield></td>
					<td class="border2" width="20%">&nbsp;</td>
				</tr>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="tAmount" name="tAmount"
						ondblclick="callShowDiv(this);">- Discount(%)</label></td>
					<td class="text_head" width="20%" align="right">&nbsp; <s:if
						test="isApprove">
						<s:textfield readonly="true" name="discount" size="5" cssStyle="text-align: right;"
							onkeypress="return numbersWithDot() "
							onkeyup="return calculateDiscount();"
							onblur="return calculateDiscount();" maxlength="6"></s:textfield>
						<s:textfield readonly="true" name="caldiscount" size="9"  cssStyle="text-align: right;"
							readonly="true" onkeypress="return numbersWithDot()"></s:textfield>
					</s:if> <s:else>
						<s:textfield name="discount" size="5" cssStyle="text-align: right;"
							onkeypress="return numbersWithDot() "
							onkeyup="return calculateDiscount();"
							onblur="return calculateDiscount();" maxlength="6"></s:textfield>
						<s:textfield name="caldiscount" size="9" readonly="true"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()"></s:textfield>
					</s:else></td>
					<td class="border2" width="20%">&nbsp;</td>

				</tr>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="tAmount" name="tAmount"
						ondblclick="callShowDiv(this);">Total (Net)</label></td>
					<td class="text_head" width="20%" align="right">&nbsp;<s:textfield  cssStyle="text-align: right;"
						name="totalnetAmount" onkeypress="return numbersWithDot()"
						readonly="true"></s:textfield></td>
					<td class="border2" width="20%">&nbsp;</td>

				</tr>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="tAmount" name="tAmount"
						ondblclick="callShowDiv(this);">+ Shipping Cost</label></td>
					<td class="text_head" width="20%" align="right">&nbsp; <s:if
						test="isApprove">
						<s:textfield readonly="true" name="shippingCost" maxlength="10"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()"
							onkeyup="return addShippingCost();"
							onblur="return addShippingCost();"></s:textfield>
					</s:if> <s:else>
						<s:textfield name="shippingCost" maxlength="10"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()"
							onkeyup="return addShippingCost();"
							onblur="return addShippingCost();"></s:textfield>
					</s:else></td>
					<td class="border2" width="20%">&nbsp;</td>
				</tr>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="saleTax" name="saleTax"
						ondblclick="callShowDiv(this);">+ Sales Tax Rate(%)</label></td>
					<td class="text_head" width="20%" align="right">&nbsp; <s:if
						test="isApprove">
						<s:textfield name="salesTax" size="5" maxlength="6"  cssStyle="text-align: right;"
							readonly="true" onkeypress="return numbersWithDot()"
							onkeyup="return calculateSaletax();"></s:textfield>
						<s:textfield name="salesTaxAmount" size="9"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()" readonly="true"></s:textfield>
					</s:if> <s:else>
						<s:textfield name="salesTax" size="5" maxlength="6"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()"
							onkeyup="return calculateSaletax();"></s:textfield>
						<s:textfield name="salesTaxAmount" size="9"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()" readonly="true"></s:textfield>
					</s:else></td>
					<td class="border2" width="20%">&nbsp;</td>

				</tr>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="tAmount" name="tAmount"
						ondblclick="callShowDiv(this);">+Additional Tax Rate(%)</label></td>
					<td class="text_head" width="20%" align="right">&nbsp; <s:if
						test="isApprove">
						<s:textfield readonly="true" name="additionalTaxRate" size="5"  cssStyle="text-align: right;"
							maxlength="6" onkeypress="return numbersWithDot()"
							onkeyup="return calculateAdditionaltax();"></s:textfield>
						<s:textfield name="additionalTaxAmount" size="9"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()" readonly="true"></s:textfield>
					</s:if> <s:else>
						<s:textfield name="additionalTaxRate" size="5" maxlength="6"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()"
							onkeyup="return calculateAdditionaltax();"></s:textfield>
						<s:textfield name="additionalTaxAmount" size="9"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()" readonly="true"></s:textfield>
					</s:else></td>
					<td class="border2" width="20%">&nbsp;</td>

				</tr>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="tAmount" name="tAmount"
						ondblclick="callShowDiv(this);">Price Adjustment </label></td>
					<td class="text_head" width="20%" align="right">&nbsp; <s:if
						test="isApprove">
						<s:select theme="simple" name="operationType" id="operationType"
							disabled="true" list="#{'A':'+','S':'-'}"
							onchange="return updatePriceAdj();" />
						<s:textfield name="priceAdjust" readonly="true"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()" maxlength="10" size="12"
							onkeyup="return calculatePriceAdj();"></s:textfield>
					</s:if> <s:else>
						<s:select theme="simple" name="operationType" id="operationType"
							list="#{'A':'+','S':'-'}" onchange="return updatePriceAdj();" />
						<s:textfield name="priceAdjust"  cssStyle="text-align: right;"
							onkeypress="return numbersWithDot()" maxlength="10" size="12"
							onkeyup="return calculatePriceAdj();"></s:textfield>
					</s:else></td>
					<td class="border2" width="20%">&nbsp;</td>

				</tr>
				<tr>
					<td class="border2" width="5%">&nbsp;</td>
					<td class="border2" width="25%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="border2" width="12%">&nbsp;</td>
					<td class="text_head" width="20%" align="right"><label
						class="set" id="tAmount" name="tAmount"
						ondblclick="callShowDiv(this);">Total</label></td>
					<td class="text_head" width="20%" align="right">&nbsp;<s:textfield  cssStyle="text-align: right;"
						name="totalallAmount" onkeypress="return numbersWithDot()"
						readonly="true"></s:textfield> <s:hidden
						name="hiddentotalallAmount" id="hiddentotalallAmount"></s:hidden>

					</td>
					<td class="border2" width="20%">&nbsp;</td>

				</tr>

				<input type="hidden" name="count" id="count" value="<%=k-1%>" />
			</table>
			</td>
		</tr>






		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead"><label class="set" id="perOrder"
						name="perOrder" ondblclick="callShowDiv(this);">General
					Information</label></strong></td>
				</tr>

				<s:if test="generalFlag">
					<tr>
						<td width="25%" colspan="1"><label class="set"
							id="purchaseBy" name="purchaseBy" ondblclick="callShowDiv(this);"><%=label.get("purchaseBy")%></label>
						<font color="red">*</font> :</td>
						<td width="35%" class="formtext"><s:textfield
							name="empToken" size="10" readonly="true"></s:textfield><s:textfield
							name="empName" size="30" readonly="true"></s:textfield></td>
							
							<td width="15%" class="formtext"><label class="set" id="date"
						name="date" ondblclick="callShowDiv(this);">Created Date</label> <font
						color="red">*</font> :</td>
					<td width="15%"><s:textfield size="12" maxlength="10"
						name="orderDate" readonly="true" /> <!--<s:a href="javascript:NewCal('paraFrm_orderDate','DDMMYYYY');">
				<img src="../pages/images/Date.gif" class="iconImage"   height="16" align="absmiddle" width="16"></s:a>-->
					</td>
					</tr>

				</s:if>
				<s:elseif test="isApprove">
					<tr>
						<td width="25%" ><label class="set"
							id="purchaseBy" name="purchaseBy" ondblclick="callShowDiv(this);"><%=label.get("purchaseBy")%></label>
						<font color="red">*</font> :</td>
						<td width="35%"  class="formtext"><s:textfield
							name="empToken" size="10" readonly="true"></s:textfield><s:textfield
							name="empName" size="30" readonly="true"></s:textfield></td>
						<td width="15%" class="formtext"><label class="set" id="date"
						name="date" ondblclick="callShowDiv(this);">Created Date</label> <font
						color="red">*</font> :</td>
					<td width="15%"><s:textfield size="12" maxlength="10"
						name="orderDate" readonly="true" /> <!--<s:a href="javascript:NewCal('paraFrm_orderDate','DDMMYYYY');">
				<img src="../pages/images/Date.gif" class="iconImage"   height="16" align="absmiddle" width="16"></s:a>-->
					</td>
					</tr>

				</s:elseif>
				<s:else>

					<tr>
						<td width="25%" ><label class="set"
							id="purchaseBy" name="purchaseBy" ondblclick="callShowDiv(this);"><%=label.get("purchaseBy")%></label>
						<font color="red">*</font> :</td>
						<td width="45%"  class="formtext"><s:textfield
							name="empToken" size="10" readonly="true"></s:textfield><s:textfield
							name="empName" size="30" readonly="true"></s:textfield><s:if
							test="%{PurchaseOrder.keepInfoFlag}">
							<img class="imageIcon" src="../pages/images/search2.gif"
								height="16" align="absmiddle" width="16" theme="simple"
								onclick="return callEmp();">
						</s:if></td>
						<td width="15%" class="formtext"><label class="set" id="date"
						name="date" ondblclick="callShowDiv(this);">Created Date</label> <font
						color="red">*</font> :</td>
					<td width="15%"><s:textfield size="12" maxlength="10"
						name="orderDate" readonly="true" /> <!--<s:a href="javascript:NewCal('paraFrm_orderDate','DDMMYYYY');">
				<img src="../pages/images/Date.gif" class="iconImage"   height="16" align="absmiddle" width="16"></s:a>-->
					</td>
					</tr>

				</s:else>


				<!--<tr>
					<td width="25%" class="formtext"><label class="set" id="date"
						name="date" ondblclick="callShowDiv(this);">Created Date</label> <font
						color="red">*</font> :</td>
					<td width="25%"><s:textfield size="12" maxlength="10"
						name="orderDate" readonly="true" /> <s:a href="javascript:NewCal('paraFrm_orderDate','DDMMYYYY');">
				<img src="../pages/images/Date.gif" class="iconImage"   height="16" align="absmiddle" width="16"></s:a>
					</td>
					<td width="25%" class="formtext">
					 <label class="set" id="owner"
						name="owner" ondblclick="callShowDiv(this);"><%=label.get("owner")%></label>
					<font color="red">*</font> : 
					</td>
					<td width="25%">
					<!-- <s:textfield size="25" maxlength="10"
						name="owner" readonly="true" /> 
					</td>
				</tr>-->
				<tr>

					<td width=""><label class="set" id="costcenter"
						name="costcenter" ondblclick="callShowDiv(this);"><%=label.get("costcenter")%></label>
					 :</td>
					<td width="" class="formtext"><s:textfield
						name="costcenter" size="25" readonly="true"></s:textfield><s:if
						test="%{keepInfoFlag}">
						<img class="imageIcon" src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple"
							onclick="return callCostCenter();">
					</s:if></td>
					<td width=""><label class="set" id="subcostcenter"
						name="subcostcenter" ondblclick="callShowDiv(this);"><%=label.get("subcostcenter")%></label>
					 :</td>
					<td width="" class="formtext"><s:textfield
						name="subcostcenter" size="12" readonly="true"></s:textfield><s:if
						test="%{keepInfoFlag}">
						<img class="imageIcon" src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple"
							onclick="return callSubCostCenter();">
					</s:if></td>

				</tr>

			</table>
			</td>
		</tr>
		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap"><strong>The
					Approver(s) for this application :</strong></td>
					<td colspan="2" nowrap="nowrap"></td>

					<td width="11%" nowrap="nowrap"><strong>Keep Informed
					To : </strong></td>
					<td width="13%"><s:if test="%{keepInfoFlag}">
						<s:hidden name="employeeId" />
						<s:hidden name="employeeToken" />
						<s:textfield name="employeeName" readonly="true" />
					</s:if></td>
					<td width="5%" colspan="1"><s:if test="%{keepInfoFlag}">
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getKeepInformedEmp();" />
					</s:if></td>
					<td width="15%"><s:if test="%{keepInfoFlag}">
						<s:submit name="" value=" Add" cssClass=" add"
							action="PurchaseOrder_addKeepInformedEmpList"
							onclick="return callKeepInformed();" />
					</s:if></td>
				</tr>
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<%
							int y = 1;
							%>
							<%!int z = 0;%>
							<s:iterator value="approverList">
								<tr>
									<td><s:hidden name="approverName" /><STRONG><s:property
										value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>

								</tr>
								<%
								y++;
								%>
							</s:iterator>
							<%
							z = y;
							%>
						</tr>
					</table>
					</td>

				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->
				<!-- KEEP INFORMED LIST TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<%
							int counter11 = 0;
							int counter2 = 0;
						%>

						<s:iterator value="keepInformedList" status="stat">

							<tr>

								<td width="80%"><%=++counter11%><s:hidden name="serialNo" /><s:hidden
									name="keepInformedEmpName" /><s:property
									value="keepInformedEmpName" /><s:hidden
									name="keepInformedEmpId" /></td>
								<td width="20%"><s:if test="%{purchaseOrder.keepInfoFlag}">
									<a href="#" onclick="callForRemove(<%=counter11%>);">Remove</a>
								</s:if></td>
							</tr>
							<%
							counter2 = counter11;
							%>
						</s:iterator>
						<%
						counter2 = 0;
						%>
					</table>
					</td>
					<td></td>
				</tr>

			</table>
			</td>
		</tr>

		<!-- KEEP INFORMED LIST TABLE  ENDS -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="50%">
					<table width="100%" border="0" align="" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td class="text_head" align="center"><strong
								class="forminnerhead"><label class="set" id="perOrder"
								name="perOrder" ondblclick="callShowDiv(this);"><%=label.get("remarks")%></label></strong></td>
						</tr>
						<tr>
							<td><s:if test="isApprove">
								<s:textarea name="remarks" cols="60" rows="3" readonly="true"></s:textarea>
							</s:if> <s:else>
								<s:textarea name="remarks" cols="60" rows="3"></s:textarea>
							</s:else></td>
						</tr>

					</table>
					</td>
					<td width="50%">
					<table width="100%" border="0" align="" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td class="text_head" align="center"><strong
								class="forminnerhead"><label class="set" id="terms"
								name="terms" ondblclick="callShowDiv(this);"><%=label.get("terms")%></label></strong></td>
						</tr>
						<tr>
							<td><s:if test="isApprove">
								<s:textarea name="terms" cols="60" rows="3" readonly="true"></s:textarea>
							</s:if> <s:else>
								<s:textarea name="terms" cols="60" rows="3"></s:textarea>

							</s:else></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
					
		</table>			
		
	</td>
</tr>

		

		<tr>
			<td colspan="3" width="78%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>


	</table>
	
	<s:hidden name="hiddenStatus"
					value="%{hiddenStatus}" />
					
					
					 <s:hidden name="source" id="source" />
	 
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<script>
autoDate();
function callForEdit(itemName,itemCode,price,quantity,unit,total,rowId){
	
	/**if(document.getElementById('paraFrm_isApprove').value=="false"){
		if(!(document.getElementById('paraFrm_status').value=="P")){
			alert("You can't update the application.");
			return false;
		}
		}else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}
	**/	
			
			document.getElementById("paraFrm_subTypeName").value=itemName;
			document.getElementById("paraFrm_subTypeCode").value=itemCode;
			document.getElementById("paraFrm_price").value=price;
			document.getElementById("paraFrm_quantity").value=quantity;
			document.getElementById("paraFrm_unit").value=unit;
			document.getElementById("paraFrm_totalPrice").value=total;
			document.getElementById("paraFrm_paraId").value=rowId;
			
			document.getElementById("paraFrm").action="PurchaseOrder_showList.action";
			document.getElementById("paraFrm").submit();
}
	function callForDelete(rowId){
	
	
	/**
	if(document.getElementById('paraFrm_isApprove').value=="false"){
		if(!(document.getElementById('paraFrm_status').value=="P")){
			alert("You can't update the application.");
			return false;
		}
		}
		
		else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}
	**/	
			var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
				document.getElementById("paraFrm_paraId").value=rowId;
				document.getElementById("paraFrm").action="PurchaseOrder_removeItem.action";
	   		 	document.getElementById("paraFrm").submit();
	    }
	    }
	 function callAdd(){
	    var price =document.getElementById("paraFrm_price").value;
	    var quantity =document.getElementById("paraFrm_quantity").value;
	    var paraId=document.getElementById("paraFrm_paraId").value;
	    var count=document.getElementById("count").value;
	   /* if(document.getElementById('paraFrm_isApprove').value=="false"){
	   		 if(!(document.getElementById('paraFrm_status').value=="P")){
				alert("You can't update the application.");
				return false;
			}
			}
			else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}*/
	    		var fields=["paraFrm_vendorName","paraFrm_subTypeName","paraFrm_price","paraFrm_quantity"];
	    		var labels=["venName","asset","asset.price","quty"];
	    		var types=["select","select","enter","enter"];
	    		if(!validateBlank(fields,labels, types)){
			return false;
			}
			if(isNaN(price)) {
	   			alert("Only numbers are allowed in price field.");
		 		document.getElementById('paraFrm_price').focus();
				return false;
			}
			if(isNaN(quantity)) {
	   			alert("Only numbers are allowed in quantity field.");
		 		document.getElementById('paraFrm_quantity').focus();
				return false;
			}
			
			if(paraId=="" && count!="" && count!="0")
			{
			
			for(j=1;j<=count;j++)
			{
				
				
				var iteratorItem=document.getElementById("itemCodeIterator"+j).value;
				
				
				var item=document.getElementById("paraFrm_subTypeCode").value;
				
				if(item==iteratorItem)
				{
				alert("Asset is already added.");
				return false;
				}
				
			}
			
			}else if(paraId!="")
			{
				for(j=1;j<=count;j++)
				{
				if(paraId!=j){
				var iteratorItem=document.getElementById("itemCodeIterator"+j).value;
				var item=document.getElementById("paraFrm_subTypeCode").value;
				
				if(item==iteratorItem)
				{
				alert("Asset is already added.");
				return false;
				}
				}
				}
			}
			
	    }
	    function formValidate(type,obj)
	    {
	    
	    var approverFlag=document.getElementById("paraFrm_isApprove").value;
	    if(approverFlag=="false"){
	    	if(!(document.getElementById('paraFrm_status').value=="P"))
	    		{
					alert("You can't update the application.");
					return false;
				}
			}
			else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}
	    	var field=["paraFrm_empName","paraFrm_orderDate"];
	    	var label=["purchaseBy","date"];
	    	var types=["select","enter"];
	    	var purchaseCode=document.getElementById("paraFrm_purchaseCode").value;
			var count = document.getElementById("count").value;
			var asset=document.getElementById('asset').innerHTML.toLowerCase();
			if(type=='save'){
			if(purchaseCode !=""){
				alert("Please click on Update button to update the record.");
				return false;
			}
			}else{
				if(purchaseCode ==""){
				alert("Please select a record to update !");
				return false;
			}
			}
			if(!validateBlank(field,label,types)){
			return false;
			}
			if(count=="" || count=="0"){
			alert("Please add "+asset+" in table.");
			return false;;
			}
			
			
			var actionName = "PurchaseOrder_save.action";
			if(approverFlag =="true"){
				actionName = "PurchaseOrder_saveByApprover.action";
			}
			
			obj.disabled =true;
			document.getElementById("paraFrm").action= actionName;
	    	document.getElementById("paraFrm").submit();
	    }
	    function callCostCenter()
	    {
	    	callsF9(500,325,'PurchaseOrder_f9actionCostCenter.action');
	    }
	    function callSubCostCenter()
	    {
	    	var name=document.getElementById('costcenter').innerHTML.toLowerCase();
	    	if(document.getElementById("paraFrm_costCenterId").value=="")
	    	{
	    		alert("Please select "+name);
	    		return fasle;
	    	}
	    	callsF9(500,325,'PurchaseOrder_f9actionSubCostCenter.action');
	    }
	    function callAsset()
	    {
	    /*if(document.getElementById('paraFrm_isApprove').value=="false"){
	    	if(!(document.getElementById('paraFrm_status').value=="P")){
				alert("You can't update the application.");
				return false;
			}
			}
			else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}*/
		var name=document.getElementById('venName').innerHTML.toLowerCase();
		var venName=document.getElementById("paraFrm_vendorName").value
	    	if(venName=="")
	    	{
	    		alert("Please select "+name);
	    		return fasle;
	    	}else 
	    	callsF9(500,325,'PurchaseOrder_f9actionSubType.action');
	    }
	    
 	function autoDate () 
 	{
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			document.getElementById("paraFrm_today").value=tDate+"-"+tMonth+"-"+tDay.getFullYear();
			if(document.getElementById('paraFrm_purchaseCode').value=="")
			{
				document.getElementById("paraFrm_orderDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//document.getElementById("paraFrm_createdDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				
			}
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
}
	function calculateTotal()
	{
		var price=document.getElementById("paraFrm_price").value;
		var quantity=document.getElementById("paraFrm_quantity").value;
		var totalPrice="0";
		if(price=="" || quantity==""){
			totalPrice="0";
		}else {
			totalPrice=(parseFloat(price) *1000000000 * parseFloat(quantity)) /1000000000;
		}
		document.getElementById("paraFrm_totalPrice").value=parseFloat(totalPrice);
	}
	
	function callEmp()
	{
	
	/**
	if(document.getElementById('paraFrm_isApprove').value=="false"){
		if(!(document.getElementById('paraFrm_status').value=="P"))
			{
				alert("You can't update the application.");
				return false;
			}
			}
			else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}
	**/	
		callsF9(500,325,'PurchaseOrder_f9ActionEmp.action');
			
	}
	function validateForm()
	{
		/*if(document.getElementById("paraFrm_poName").value=="")
		{
			alert("Please enter po name.")
			return false;
		}
		if(document.getElementById("paraFrm_requiredBy").value=="")
		{
			alert("Please select required by.")
			return false;
		}*/
		
		
		if(!document.getElementById("paraFrm_requiredBy").value=="")
		{
			 var check= validateDate('paraFrm_requiredBy', 'requiredby');
			if(!check){
			return false;
		}
			 
		}
		
			
		
		
		var fields=["paraFrm_vendorCode","paraFrm_shippingAddress","paraFrm_billingAddress"];
    var labels=["venName","shippingAddress","billAddress"];
    var flag = ["select","enter","enter"];
		 if(!validateBlank(fields,labels,flag))
     return false;
     
     
		/**if(document.getElementById("paraFrm_vendorCode").value=="")
		{
			alert("Please select vendor name.")
			return false;
		}
		if(document.getElementById("paraFrm_shippingAddress").value=="")
		{
			alert("Please enter shipping address.")
			return false;
		}
		if(document.getElementById("paraFrm_billingAddress").value=="")
		{
			alert("Please enter billing address.")
			return false;
		}
		**/
				var count = document.getElementById("count").value;
				var asset=document.getElementById('asset').innerHTML.toLowerCase();
			if(count=="" || count=="0"){
			alert("Please add "+asset+" in table.");
			return false;;
			}
			
		var field=["paraFrm_empName","paraFrm_orderDate"];
	    	var label=["purchaseBy","date"];
	    	var types=["select","enter"];
	    	var purchaseCode=document.getElementById("paraFrm_purchaseCode").value;
		
			
			
			if(!validateBlank(field,label,types)){
			return false;
			}
		
		if(document.getElementById("paraFrm_empCode").value=="")
		{
			alert("Please select purchase order placed by person.")
			return false;
		}
		/*if(document.getElementById("paraFrm_costCenterId").value=="")
		{
			alert("Please select cost center.")
			return false;
		}
		if(document.getElementById("paraFrm_subcostCenterId").value=="")
		{
			alert("Please select sub cost center .")
			return false;
		}*/
		return true;
		
	}
	function callVendor()
	{
	/*if(document.getElementById('paraFrm_isApprove').value=="false"){
		if(!(document.getElementById('paraFrm_status').value=="P"))
			{
				alert("You can't update the application.");
				return false;
			}
			}
			else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}*/
			callsF9(500,325,'PurchaseOrder_f9actionVendor.action');
	}
	function callDelete1()
	{
	status=document.getElementById('paraFrm_status').value;
	if(status=='C'){
				alert("Application is canceled already.");
				return false;
	}
	
	if(document.getElementById('paraFrm_isApprove').value=="false"){
	if(!(document.getElementById('paraFrm_status').value=="P"))
			{
				alert("You can't cancel the application.");
				return false;
			}
			}
			else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}
		return callCancel("paraFrm_purchaseCode");
			
	}
	function callBack(){
	
				var status = document.getElementById("paraFrm_status").value;
				document.getElementById("paraFrm").action="PurchaseOrderApproval_checkData.action?status="+status;
	   		 	document.getElementById("paraFrm").submit();
	}
	
	function deleteFun()
	{
	 var conf=confirm("Are you sure !\n You want to delete this record ?");
	 
	 if(conf)
	 {	
		document.getElementById('paraFrm').action='PurchaseOrder_deletePurchaseOrder.action';
			document.getElementById('paraFrm').submit();
	}
	else{
			return false ;
	}
			
	}
	
	function resetFun()
	{
		
		document.getElementById('paraFrm').action='PurchaseOrder_reset.action';
			document.getElementById('paraFrm').submit();	
	}
	
	function backFun()
	{
		
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
		document.getElementById('paraFrm').action='PurchaseOrder_cancel.action';
		}
			document.getElementById('paraFrm').submit();	
	}
	
	function editFun() 
	    {
			return true;
		}
		
	function saveFun()
	{
		var approverFlag=document.getElementById("paraFrm_isApprove").value;
	    if(approverFlag=="false"){
	    	if(!(document.getElementById('paraFrm_status').value=="P"))
	    		{
					alert("You can't update the application.");
					return false;
				}
			}
			else {
			if((document.getElementById('paraFrm_status').value!="P") && (document.getElementById('paraFrm_status').value!="F") ){
			alert("You can't update the application.");
			return false;
			}
		}
	    	var field=["paraFrm_empName","paraFrm_orderDate"];
	    	var label=["purchaseBy","date"];
	    	var types=["select","enter"];
	    	var purchaseCode=document.getElementById("paraFrm_purchaseCode").value;
			var count = document.getElementById("count").value;
			var asset=document.getElementById('asset').innerHTML.toLowerCase();
			
			if(!validateBlank(field,label,types)){
			return false;
			}
			if(count=="" || count=="0"){
			alert("Please add "+asset+" in table.");
			return false;;
			}
			
			
			var actionName = "PurchaseOrder_save.action";
			if(approverFlag =="true"){
				actionName = "PurchaseOrder_saveByApprover.action";
			}
			
			document.getElementById("paraFrm").action= actionName;
	    	document.getElementById("paraFrm").submit();
			
			obj.disabled =true;
			
	}
	function backtolistFun(){
	
	 
		document.getElementById('paraFrm').target = "_self";
		
	if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
		document.getElementById('paraFrm').action = 'PurchaseOrderApproval_input.action';
		}
	
		document.getElementById('paraFrm').submit();
	}
	
	function approveFun(){
	
	var cmt =document.getElementById('paraFrm_appcomment').value;
	if(cmt=="")
	{
		alert("Please enter comments");
		document.getElementById('paraFrm_appcomment').focus();
		return false;
	}
	
	 var conf=confirm("Are you sure !\n You want to approve this record ?");
  				if(conf){
  				
  				document.getElementById('paraFrm_hiddenStatus').value = 'A';
		document.getElementById("paraFrm").action="PurchaseOrderApproval_callApprove.action";
		
		document.getElementById("paraFrm").submit();
		 
  				}
  				else{
  				return false;
  				}
		
	
	}
	
	function rejectFun(){
	
	var cmt =document.getElementById('paraFrm_appcomment').value;
	if(cmt=="")
	{
		alert("Please enter comments");
		document.getElementById('paraFrm_appcomment').focus();
		return false;
	}
	
	 var conf=confirm("Are you sure !\n You want to reject this record ?");
  				if(conf){
  				
  				document.getElementById('paraFrm_hiddenStatus').value = 'R';
		document.getElementById("paraFrm").action="PurchaseOrderApproval_callApprove.action";
		
		document.getElementById("paraFrm").submit();
		 
  				}
  				else{
  				return false;
  				}
		
	
	}
	function callLength(type){ 
		
		 if(type=='descCnt'){
					var cmt =document.getElementById('paraFrm_appcomment').value;
					var remain = 500 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_appcomment').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_appcomment').style.background = '#FFFFFF';
				
				}
				}  
				
		function sendbackFun(){
		
	var cmt =document.getElementById('paraFrm_appcomment').value;
	if(cmt=="")
	{
		alert("Please enter comments");
		document.getElementById('paraFrm_appcomment').focus();
		return false;
	}
	 var conf=confirm("Are you sure !\n You want to sendback this record ?");
  				if(conf){
  				
  				document.getElementById('paraFrm_hiddenStatus').value = 'B';
		document.getElementById("paraFrm").action="PurchaseOrderApproval_callApprove.action";
		
		document.getElementById("paraFrm").submit();
		 
  				}
  				else{
  				return false;
  				}
		
	
	}	
	
	function draftFun(){
	
	if(validateForm())
	{	
	document.getElementById('paraFrm_hiddenStatus').value = 'D';	
	document.getElementById("paraFrm").action="PurchaseOrder_save.action";
	document.getElementById("paraFrm").submit();
	}
	
}
function sendforapprovalFun(){
	
	if(validateForm())
	{
	document.getElementById('paraFrm_hiddenStatus').value = 'P';	
	 var conf=confirm("Are you sure !\n You want to send for approval this record ?");
  				if(conf)
  				{
	document.getElementById("paraFrm").action="PurchaseOrder_save.action";
	document.getElementById("paraFrm").submit();
  				}
  				else{
  					return false ;
  				}

	}
	
}



function reportFun() {
 
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PurchaseOrder_report.action';
		document.getElementById('paraFrm').submit();
	 
}



function withdrawFun() {
	var conf = confirm("Do you really want to withdraw this application?");
 	if(conf) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PurchaseOrder_withdrawApplication.action';
		document.getElementById('paraFrm').submit();
	}
}
function getKeepInformedEmp()
	{
	try
	{
	 
	var empcode=document.getElementById('paraFrm_empCode').value;
	 	//var emp =document.getElementById('paraFrm_employeeId').value;
			 if(empcode==""){
				alert("Please select "+document.getElementById('purchaseBy').innerHTML.toLowerCase());
				return false;
			}
	else{
			callsF9(500,325,'PurchaseOrder_f9KeepInformedEmployee.action');
		 	}
	}
	catch(e)
	{
		alert(e);
		} 
	
	}
	function callKeepInformed()
	{
		
		 var empcode=document.getElementById('paraFrm_empCode').value;
		 var emp =document.getElementById('paraFrm_employeeId').value;
		 if(empcode==""){
			 alert("Please select "+document.getElementById('purchaseBy').innerHTML.toLowerCase());
		 return false;
			 }
			if(emp=="")
			{
			alert("Please select Keep Informed To ");
				return false;
			}
	
		return true;
	}
	
	function callForRemove(id)
	    {
	    	
	    		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkRemove').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="PurchaseOrder_removeKeepInformed.action";
		  					document.getElementById("paraFrm").submit();
		  				}	
		  				else
		  				{
		  					return false;
		  				}
		  	
		  	return true;			
	    }
	
	function calculateDiscount()
	{
		var total=document.getElementById("paraFrm_totalAmount").value;
		var discount=document.getElementById("paraFrm_discount").value;
		var totalPrice="0";
		var totalAllamount="0";
		if(total=="" || discount==""){
			totalPrice="0";
		}else {
			totalPrice=(parseFloat(total) * parseFloat(discount)) /100;
		}
		document.getElementById("paraFrm_caldiscount").value=roundNumber(parseFloat(totalPrice),2);
		document.getElementById("paraFrm_totalnetAmount").value=roundNumber((total-parseFloat(totalPrice)),2);
		addTotal();
		//document.getElementById("paraFrm_totalallAmount").value=eval(totalAllamount)+roundNumber((total-eval(totalPrice)),2);
		//document.getElementById("hiddentotalallAmount").value=roundNumber(document.getElementById("paraFrm_totalallAmount").value,2);
		
	}
	function addTotal()
	{
		var totalnetAmout=document.getElementById("paraFrm_totalnetAmount").value;
		var shippingcost=document.getElementById("paraFrm_shippingCost").value;
		var saleTaxAmount=document.getElementById("paraFrm_salesTaxAmount").value;
		var addionalTaxAmount=document.getElementById("paraFrm_additionalTaxAmount").value;
		var priceAdjust=document.getElementById("paraFrm_priceAdjust").value;
		var operationType=document.getElementById("operationType").value;
		if(document.getElementById("paraFrm_discount").value=="")
		document.getElementById("paraFrm_discount").value="0";
		if(document.getElementById("paraFrm_shippingCost").value=="")
		document.getElementById("paraFrm_shippingCost").value="0";
		if(document.getElementById("paraFrm_salesTax").value=="")
		document.getElementById("paraFrm_salesTax").value="0";
		if(document.getElementById("paraFrm_additionalTaxRate").value=="")
		document.getElementById("paraFrm_additionalTaxRate").value="0";
		if(document.getElementById("paraFrm_priceAdjust").value=="")
		document.getElementById("paraFrm_priceAdjust").value="0";
		var total="0";
		if(totalnetAmout=="")
		totalnetAmout="0";
		if(shippingcost=="")
		shippingcost="0";
		if(saleTaxAmount=="")
		saleTaxAmount="0";
		if(addionalTaxAmount=="")
		addionalTaxAmount="0";
		if(priceAdjust=="")
		priceAdjust="0";
		
		total=parseFloat(totalnetAmout)+parseFloat(shippingcost)+parseFloat(saleTaxAmount)+parseFloat(addionalTaxAmount);
		if(operationType=="A")
		{
			total=total+parseFloat(priceAdjust);
		}
		if(operationType=="S")
		{
			total=total-parseFloat(priceAdjust);
		}
		document.getElementById("paraFrm_totalallAmount").value=roundNumber((total),2);
		document.getElementById("hiddentotalallAmount").value=roundNumber(total,2);
	
		
	}
	function addShippingCost()
	{
		var total=document.getElementById("paraFrm_totalnetAmount").value;
		var shippingcost=document.getElementById("paraFrm_shippingCost").value;
		if(total=="")
		total="0";
		if(shippingcost=="")
		shippingcost="0";
		addTotal();
		//document.getElementById("paraFrm_totalallAmount").value=roundNumber((eval(total)+eval(shippingcost)),2);
		//document.getElementById("hiddentotalallAmount").value=roundNumber(document.getElementById("paraFrm_totalallAmount").value,2);
	}
	function calculateSaletax()
	{
		var total=document.getElementById("paraFrm_totalnetAmount").value;
		var saletax=document.getElementById("paraFrm_salesTax").value;
		var totalPrice="0";
		if(total=="" || saletax==""){
			totalPrice="0";
		}else {
			totalPrice=(parseFloat(total) * parseFloat(saletax)) /100;
		}
		document.getElementById("paraFrm_salesTaxAmount").value=roundNumber(parseFloat(totalPrice),2);
		addTotal();		
		//document.getElementById("paraFrm_totalallAmount").value=roundNumber((eval(document.getElementById("paraFrm_totalallAmount").value)+eval(totalPrice)),2);
		//document.getElementById("hiddentotalallAmount").value=roundNumber(document.getElementById("paraFrm_totalallAmount").value,2);
	}
	function calculateAdditionaltax()
	{
		var total=document.getElementById("paraFrm_totalnetAmount").value;
		var additionalTaxRate=document.getElementById("paraFrm_additionalTaxRate").value;
		var totalPrice="0";
		if(total=="" || additionalTaxRate==""){
			totalPrice="0";
		}else {
			totalPrice=(parseFloat(total) * parseFloat(additionalTaxRate)) /100;
		}
		document.getElementById("paraFrm_additionalTaxAmount").value=roundNumber(parseFloat(totalPrice),2);
		addTotal();		
		//document.getElementById("paraFrm_totalallAmount").value=roundNumber((eval(document.getElementById("paraFrm_totalallAmount").value)+eval(totalPrice)),2);
		//document.getElementById("hiddentotalallAmount").value=roundNumber(document.getElementById("paraFrm_totalallAmount").value,2);
	}
	
	function calculatePriceAdj()
	{
		var total=document.getElementById("paraFrm_totalallAmount").value;
		var operationType=document.getElementById("operationType").value;
		var priceAdjust=document.getElementById("paraFrm_priceAdjust").value;
		
		var totalHiddenAmt=document.getElementById("hiddentotalallAmount").value;
		
		
		if(priceAdjust=="")
		priceAdjust="0";
		var totalPrice="0";
		/*if(operationType=="add")
		{
			totalPrice=(eval(totalHiddenAmt)+eval(priceAdjust));
		}
		if(operationType=="sub")
		{
			totalPrice=(parseFloat(totalHiddenAmt)-parseFloat(priceAdjust));
		}
		document.getElementById("paraFrm_totalallAmount").value=roundNumber(totalPrice,2);*/
		addTotal();
	}
	function updatePriceAdj()
	{
		document.getElementById("paraFrm_priceAdjust").value="";
	}
	
	
	function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}
	
	OnloadCalculate();
	function OnloadCalculate()
	{
			calculateDiscount();
		addShippingCost();
		calculateSaletax();
		calculateAdditionaltax();
		calculatePriceAdj();
	}
	
</script>