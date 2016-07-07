<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="PurchaseOrder" validate="true" id="paraFrm"
	theme="simple">
	 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
				
				<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Purchase
					Inwards </strong></td>
				</tr>
			</table>
			</td>
		</tr>
    
    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" class="formbg">
				<tr>
					<td height="27" class="formtxt"><input type="button" value="Back" class="token" onclick="return callBack();"></input></td><s:hidden name="purchaseCode" />
     					 <s:hidden name="empCode"/>
     					 <s:hidden name="vendorCode" />
     					 <s:hidden name="subTypeCode" />
				</tr>
			</table>
		</tr>
   				     
    <tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
              <tr>
                <td colspan="4" class="formhead"><strong class="forminnerhead"><label  class = "set"  id="perOrder1" name="perOrder" ondblclick="callShowDiv(this);"><%=label.get("perOrder")%></label></strong></td>
              </tr>
              
              <tr>
                	<td width="25%" colspan="1"> <label  class = "set"  id="purchaseBy1" name="purchaseBy" ondblclick="callShowDiv(this);"><%=label.get("purchaseBy")%></label> :</td>
                	<td width="75%" colspan="3" class="formtext"><s:textfield name="empToken" size="10" readonly="true"></s:textfield><s:textfield name="empName" size="50" readonly="true"></s:textfield>
                	 </td>
			 </tr>
              <tr>
                	<td width="25%" colspan="1"><label  class = "set"  id="venName1" name="venName" ondblclick="callShowDiv(this);"><%=label.get("venName")%></label>  <font color="red">*</font> :</td>
                	<td width="25%" colspan="1" class="formtext"><s:textfield name="vendorName" size="25" readonly="true"></s:textfield>
                	</td>
			 		<td width="25%" colspan="1"><label  class = "set"  id="city1" name="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>  :</td>
                	<td width="25%" colspan="1" class="formtext"><s:textfield name="cityName" size="25" readonly="true"></s:textfield></td>
			 		
			 				  
              </tr>
             
             
			 <tr>
                <td width="25%" colspan="1" class="formtext"><label  class = "set"  id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label>  :</td>
                <td width="25%" colspan="1"><s:textfield size="25" maxlength="10" name="orderDate" readonly="true"/> 
                <!-- <s:a href="javascript:NewCal('paraFrm_orderDate','DDMMYYYY');">
				<img src="../pages/images/Date.gif" class="iconImage"   height="16" align="absmiddle" width="16"></s:a>-->
				</td>
			   <td width="25%" colspan="1" class="formtext"><label  class = "set"  id="status1" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
			  <td width="25%" colspan="1"><s:hidden	name="hiddenStatus" value="%{hiddenStatus}" /> <s:select theme="simple" name="status"
					disabled="true" cssStyle="width:130"
								list="#{'P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded'}" /></td>
			 </tr>
             </table>
			</td>
		</tr>
		
   <tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
                <tr >
                  <td colspan="8" class="formth"><strong class="forminnerhead"><label  class = "set"  id="list" name="list" ondblclick="callShowDiv(this);"><%=label.get("list")%></label> </strong></td>
                </tr>
		

		<tr>
			<td class="formth" width="5%" height="22" valign="top"><label  class = "set"  id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label> </td>
			<td class="formth" width="25%" height="22" valign="top"><label  class = "set"  id="asset" name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label> </td>
			<td class="formth" width="10%" height="22" valign="top"><label  class = "set"  id="asset.price" name="asset.price" ondblclick="callShowDiv(this);"><%=label.get("asset.price")%></label> </td>
			<td class="formth" width="10%" height="22" valign="top"><label  class = "set"  id="quty" name="quty" ondblclick="callShowDiv(this);"><%=label.get("quty")%></label> </td>
			<td class="formth" width="20%" height="22" valign="top"><label  class = "set"  id="unit" name="unit" ondblclick="callShowDiv(this);"><%=label.get("unit")%></label> </td>
			<td class="formth" width="15%" height="22" valign="top"><label  class = "set"  id="tPrice" name="tPrice" ondblclick="callShowDiv(this);"><%=label.get("tPrice")%></label> </td>
			<td class="formth" width="15%" height="22" valign="top"><label  class = "set"  id="add/cancel" name="add/cancel" ondblclick="callShowDiv(this);"><%=label.get("add/cancel")%></label> </td>
	   </tr>
			
			<s:if test="noData">
	                        <tr>
	                        	<td width="100%" colspan="8" align="center"><font color="red"><label  class = "set"  id="nopendingrecords" name="nopendingrecords" ondblclick="callShowDiv(this);"><%=label.get("nopendingrecords")%></label> </font></td>
	                        </tr></s:if>
			
			
			<% int k = 1;%>
			<s:iterator value="orderList" status="stat">
				<tr>
					<td width="5%" align="center" class="border2"><%=k%></td>
					<td width="25%" class="border2"><s:property value="itemIterator" /><s:hidden name="itemIterator"/><s:hidden name="itemCodeIterator"/></td>
					<td width="10%" class="border2" align="right"><s:property value="priceIterator" /><s:hidden name="priceIterator"/></td>
					<td width="10%" class="border2"><s:property value="quantityIterator" /><s:hidden name="quantityIterator"/></td><s:hidden name="purchaseDtlCode"/>
					<td width="20%" class="border2"><s:property value="unitIterator" /><s:hidden name="unitIterator"/><s:hidden name="cityIterator"/></td>
					<td width="15%" class="border2" align="right"><s:property value="totalIterator" /><s:hidden name="totalIterator"/></td></td>
					<s:if test="purchaseOrder.isApprove"></s:if><s:else>
					<td align="center" width="10%" class="border2" nowrap="nowrap"><input type="button" class="add" onclick="callForAdd('<s:property value="itemIterator"/>','<s:property value="itemCodeIterator"/>',
					'<s:property value="quantityIterator"/>','<s:property value="totalIterator"/>')" value="   Add To Master" />
					<input type="button" class="cancel" onclick="callForCancel('<s:property value="purchaseDtlCode"/>')" value="   Cancel" /> 
					</td></s:else>
				</tr>
				<%k++; %>
		</s:iterator>
			<s:if test="noData"></s:if><s:else>
			<tr>
			<td class="border2" width="5%">&nbsp;</td>
			<td class="border2" width="25%">&nbsp;</td>
			<td class="border2" width="25%">&nbsp;</td>
			<td class="border2" width="10%" >&nbsp;</td>
			<td class="border2" width="20%" align="right"><label  class = "set"  id="tAmount1" name="tAmount" ondblclick="callShowDiv(this);"><%=label.get("tAmount")%></label> </td>
			<td class="border2" width="15%" align="right">&nbsp;<s:property value="totalAmount" /><s:hidden name="totalAmount"></s:hidden></td>
			<td class="border2" width="10%">&nbsp;</td>
		</tr>
		</s:else>
			<input type="hidden" name="count" id="count" value="<%=k-1%>"/>	
			</table></td></tr>
			
					
	<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
                <tr >
                  <td colspan="8" class="formth"><strong class="forminnerhead"><label  class = "set"  id="cancellist" name="cancellist" ondblclick="callShowDiv(this);"><%=label.get("cancellist")%></label> </strong></td>
                </tr>
		

		<tr>
			<td class="formth" width="5%" height="22" valign="top"><label  class = "set"  id="sno2" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label> </td>
			<td class="formth" width="35%" height="22" valign="top"><label  class = "set"  id="asset2" name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label> </td>
			<td class="formth" width="10%" height="22" valign="top"><label  class = "set"  id="asset.price2" name="asset.price" ondblclick="callShowDiv(this);"><%=label.get("asset.price")%></label> </td>
			<td class="formth" width="10%" height="22" valign="top"><label  class = "set"  id="quty2" name="quty" ondblclick="callShowDiv(this);"><%=label.get("quty")%></label> </td>
			<td class="formth" width="20%" height="22" valign="top"><label  class = "set"  id="unit2" name="unit" ondblclick="callShowDiv(this);"><%=label.get("unit")%></label> </td>
			<td class="formth" width="15%" height="22" valign="top"><label  class = "set"  id="tPrice2" name="tPrice" ondblclick="callShowDiv(this);"><%=label.get("tPrice")%></label> </td>
	   </tr>
			
			<s:if test="noDataForCancel">
	                        <tr>
	                        	<td width="100%" colspan="6" align="center"><font color="red"><label  class = "set"  id="nocancelorder" name="nocancelorder" ondblclick="callShowDiv(this);"><%=label.get("nocancelorder")%></label> </font></td>
	                        </tr></s:if>
			
			
			<% int j = 1;%>
			<s:iterator value="cancelList" status="stat">
				<tr>
					<td width="5%" align="center" class="border2"><%=j%></td>
					<td width="25%" class="border2"><s:property value="itemIterator" /><s:hidden name="itemIterator"/><s:hidden name="itemCodeIterator"/></td>
					<td width="10%" class="border2" align="right"><s:property value="priceIterator" /><s:hidden name="priceIterator"/></td>
					<td width="10%" class="border2"><s:property value="quantityIterator" /><s:hidden name="quantityIterator"/></td><s:hidden name="purchaseDtlCode"/>
					<td width="20%" class="border2"><s:property value="unitIterator" /><s:hidden name="unitIterator"/><s:hidden name="cityIterator"/></td>
					<td width="15%" class="border2" align="right"><s:property value="totalIterator" /><s:hidden name="totalIterator"/></td></td>
				</tr>
				<%j++; %>
		</s:iterator>
			
			</table></td></tr>
			
			<s:hidden name="hiddenVendorCode"/>
			<s:hidden name="hiddenVendorName"/>
			<s:hidden name="hiddenSubTypeCode"/>
			<s:hidden name="hiddenSubTypeName"/>
			<s:hidden name="hiddenQuantity"/>
			<s:hidden name="hiddentotalPrice"/>
			<s:hidden name="hiddenDtlCode"/>
						
  	</table>
  	
   
	</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>
<script>
autoDate();
function callForAdd(itemName,itemCode,quantity,total){
		
			document.getElementById("paraFrm_hiddenVendorCode").value=document.getElementById("paraFrm_vendorCode").value;
			document.getElementById("paraFrm_hiddenVendorName").value=document.getElementById("paraFrm_vendorName").value;
			document.getElementById("paraFrm_hiddenSubTypeName").value=itemName;
			document.getElementById("paraFrm_hiddenSubTypeCode").value=itemCode;
			document.getElementById("paraFrm_hiddenQuantity").value=quantity;
			document.getElementById("paraFrm_hiddentotalPrice").value=total;
			
			document.getElementById("paraFrm").action="AssetMaster_showForPurchase.action";
			document.getElementById("paraFrm").submit();
}
function callForCancel(dtlCode){
		var conf=confirm("Do you really want to cancel this item ?");
  			if(conf) {
			document.getElementById("paraFrm_hiddenDtlCode").value=dtlCode;
			document.getElementById("paraFrm").action="PurchaseOrder_cancelItem.action";
			document.getElementById("paraFrm").submit();
}
	}
	   
	function calculateTotal()
	{
		var price=document.getElementById("paraFrm_price").value;
		var quantity=document.getElementById("paraFrm_quantity").value;
		var totalPrice="0";
		if(price=="" || quantity==""){
			totalPrice="0";
		}else {
			totalPrice=eval(price)* eval(quantity);
		}
		document.getElementById("paraFrm_totalPrice").value=totalPrice;
	}
	
	
	
	
	function callBack(){
				document.getElementById("paraFrm").action="PurchaseInward_input.action";
	   		 	document.getElementById("paraFrm").submit();
	}
	
</script>