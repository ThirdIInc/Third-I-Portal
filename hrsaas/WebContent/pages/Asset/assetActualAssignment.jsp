<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="AssetEmployee" validate="true" id="paraFrm"
	theme="simple">
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset
					Assignment to Employee</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<s:hidden name="hiddenCategoryCode"></s:hidden>
				<s:hidden name="hiddenSubTypeCode"></s:hidden>
				<s:hidden name="hiddenappCode"></s:hidden>
				<s:hidden name="rowId"></s:hidden>
				<s:hidden name="source" id="source"/>
				<tr>
					<td width="78%"><s:if test="%{insertFlag}">
						<input type="button" class="add" value="  Save" onclick="return saveValidate(this);" />
					</s:if> <s:submit action="AssetAssignment_input" cssClass="token"
						value="Back" />
						
							<s:submit action="AssetEmployee_report"
						 cssClass="token"
						value="Report" />
						
					<!--<s:submit action="AssetEmployee_rejectApplication"
						cssClass="token" value="Reject" onclick="return callReject();" />
					<s:submit action="AssetEmployee_sendBackToEmp"
						onclick="return callSendBack();" cssClass="token"
						value="Send back to Employee" />--></td>
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
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td colspan="5" class="text_head"><strong class="forminnerhead">Asset Assignment To Employee </strong></td>
              </tr>
              <tr>
              <td width="25%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
								color="red">*</font> :<s:hidden name="empCode" value="%{empCode}" /><s:hidden name="code"/></td>
							<td width="75%" colspan="3"><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="empName" size="50" value="%{empName}" theme="simple"
								readonly="true" /><s:hidden name="empWarehouseCode"/></td> 
				</tr>
				
              <tr>
              <td height="22" width="25%" class="formtext"><label  class = "set"  id="assetAppDate" name="assetAppDate" ondblclick="callShowDiv(this);"><%=label.get("assetAppDate")%></label> <font color="red">*</font> :</td>
                  <td width="25%" colspan="1" nowrap="nowrap"><s:textfield size="25" name="assignDate1" readonly="true"/> 
			 </td>
              </tr>
            </table>
            </td>
          </tr>
    <tr>
      <td colspan="3" >
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
                 <td colspan="9" class="formth"><strong class="forminnerhead">Available  Assets </strong></td>
            </tr>
                <tr>
                  <td width="5%" class="formth"><label  class = "set"  id="assign.sr.no" name="assign.sr.no" ondblclick="callShowDiv(this);"><%=label.get("assign.sr.no")%></label></td>
                  <td class="formth" width="20%"><label  class = "set"  id="asset2" name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label></td>
                   <td class="formth" width="15%"><label  class = "set"  id="assetSubType2" name="assetSubType" ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label></td>
                   <td class="formth" width="15%"><label  class = "set"  id="warehouse1" name="warehouse" ondblclick="callShowDiv(this);"><%=label.get("warehouse")%></label></td>
                   <td class="formth" width="5%"><label  class = "set"  id="required1" name="required" ondblclick="callShowDiv(this);"><%=label.get("required")%></label></td>
                   <td class="formth" width="20%"><label  class = "set"  id="selectInventory" name="selectInventory" ondblclick="callShowDiv(this);"><%=label.get("selectInventory")%></label></td>
                   <td class="formth" width="15%"><label  class = "set"  id="assetAssignDate" name="assetAssignDate" ondblclick="callShowDiv(this);"><%=label.get("assetAssignDate")%></label></td>
                   <td class="formth" width="5%"><label  class = "set"  id="available" name="available" ondblclick="callShowDiv(this);"><%=label.get("available")%></label></td>
                   <td class="formth" width="5%"><label  class = "set"  id="assigned" name="assigned" ondblclick="callShowDiv(this);"><%=label.get("assigned")%></label></td><s:hidden name="tableLength"></s:hidden>
                  </tr> 
         <% int j = 1; %>         
   <s:iterator value="assignList">
                <tr>
                  <td class="td_bottom_border" width="5%"><%= j%></td><s:hidden name="assetCode" value="%{assetCode}"></s:hidden><s:hidden name="applCode"/>
                  <td class="td_bottom_border" width="20%"><s:property value="asstHdType"/><s:hidden name="asstHdType"/><s:hidden  name ="<%="assetMasterCode"+j%>" /></td>
                  <td class="td_bottom_border" width="15%"><s:property value="assetSubTypeIterator"/>&nbsp;<s:hidden name="assetSubTypeIterator"/><s:hidden name="subTypeCodeIterator"/></td>
                	<td class="td_bottom_border" width="15%"><s:property value="empWarehouse"/></td><s:hidden name="empWarehouse"/>
                  <td class="td_bottom_border" width="5%" align="center"><s:property value="assetRequiredIterator"/>&nbsp;<s:hidden name="returnFlagIterator"></s:hidden><s:hidden name="assetRequiredIterator" id="<%="assetRequiredIterator"+j%>"/>
                  <td class="td_bottom_border" nowrap="nowrap" width="20%"><!-- <input type="button" name="view_Details" class="pagebutton" value="Select Inventory" 
	      	   		onclick = "showInventory('<s:property value="assetCode"/>','<s:property value="subTypeCodeIterator"/>','<s:property value=">')"/> -->
	      	   		<s:textfield name ="<%="inventoryCode"+j%>"    size="25" readonly="true"></s:textfield>
	      	   		<s:hidden name="<%="assetItemcode"+j%>" />
	      	   		<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
	      	   		onclick = "showInventory('<s:property value="assetCode"/>','<s:property value="subTypeCodeIterator"/>','<s:property value="<%=""+j%>"/>')"></td>
	      	   		<td class="td_bottom_border" nowrap="nowrap" width="15%">
	      	   		
	      	   		
	      	   		<s:textfield
								theme="simple" name="assetAssignDate" id="<%="assetAssignDate"+j%>" size="10"
								maxlength="10" onkeypress="return numbersWithHiphen();" /><a
								href="javascript:NewCal('<%="assetAssignDate"+j%>','DDMMYYYY');"> <img
								src="../pages/images/Date.gif" class="iconImage" height="16"
								align="absmiddle" width="16" id='ctrlHide'> </a>
				 
				 </td>
	      	   		 <td class="td_bottom_border" width="5%"><s:textfield name ="<%="quantityAvailable"+j%>" size="5" readonly="true"/></td>
	      	   		<td class="td_bottom_border" width="5%" nowrap="nowrap"><s:textfield id ="<%="quantityAssigned"+j%>" name="quantityAssigned" size="5" onkeypress="return numbersWithDot();"></s:textfield>&nbsp;</td>
                </tr>
              <%
			j++;
			%>
    </s:iterator>   
           </tr>
          </table>
            
           </td>
          </tr>
          	
      <tr>
      <td colspan="3" >
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
                  <td colspan="9" class="formth"><strong class="forminnerhead"> Other Warehouse Requests</strong></td>
           </tr>
                <tr >
                  <td width="5%" class="formth"><label  class = "set"  id="assign.sr.no1" name="assign.sr.no" ondblclick="callShowDiv(this);"><%=label.get("assign.sr.no")%></label></td>
                  <td class="formth" width="20%"><label  class = "set"  id="asset1" name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label></td>
                   <td class="formth" width="15%"><label  class = "set"  id="assetSubType1" name="assetSubType" ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label></td>
                   <td class="formth" width="15%"><label  class = "set"  id="warehouse" name="warehouse" ondblclick="callShowDiv(this);"><%=label.get("warehouse")%></label></td>
                   <td class="formth" width="5%"><label  class = "set"  id="required" name="required" ondblclick="callShowDiv(this);"><%=label.get("required")%></label></td>
                   <td class="formth" width="15%"><label  class = "set"  id="otherwarehouseorder" name="otherwarehouseorder" ondblclick="callShowDiv(this);"><%=label.get("otherwarehouseorder")%></label></td>
                  <!-- <td class="formth" width="5%">Purchase Order</td> --> 
         <% int k = 1; %>         
   <s:iterator value="otherWarehouseList">
                <tr>
                  <td class="td_bottom_border" width="5%"><%= k%></td><s:hidden name="assetCode" value="%{assetCode}"></s:hidden><s:hidden name="applCode"/>
                  <td class="td_bottom_border" width="20%"><s:property value="asstHdType"/><s:hidden name="asstHdType"/><s:hidden  name ="<%="assetMasterCode"+k%>" /></td>
                  <td class="td_bottom_border" width="15%"><s:property value="assetSubTypeIterator"/>&nbsp;<s:hidden name="assetSubTypeIterator"/><s:hidden name="subTypeCodeIterator"/></td>
                  <td class="td_bottom_border" width="15%"><s:property value="empWarehouse"/></td><s:hidden name="empWarehouse"/>
                  <td class="td_bottom_border" width="5%" align="center"><s:property value="assetRequiredIterator"/>&nbsp;<s:hidden name="returnFlagIterator"></s:hidden><s:hidden name="assetRequiredIterator" id="<%="assetRequiredIterator"+k%>"/>
                  <td class="td_bottom_border" width="15%" nowrap="nowrap"><input type="button" name="warehouseOrder" class="token" value=" Other warehouse " 
	      	  				onclick = "return callOrderRequest(<s:property value="assetCode"/>,<s:property value="subTypeCodeIterator"/>,'<s:property value="asstHdType"/>','<s:property value="assetSubTypeIterator"/>','wareHouse');"/></td>
                </tr>
              <%
			k++;
			%>
    </s:iterator>   
           </tr>
            </table>
           </td>
          </tr>
    
    <tr>
     <td colspan="3">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              
            <tr>
              <td width="16%" colspan="1" class="formtext"><label  class = "set"  id="assignComments" name="assignComments" ondblclick="callShowDiv(this);"><%=label.get("assignComments")%></label> :</td>
			  <td width="84%" colspan="2"><s:textarea name="assignComments" theme="simple" rows="3" cols="100" 
			  								 onkeyup="callLength('descCnt');" />
					<img
					src="../pages/images/zoomin.gif" height="12" align="absmiddle" id='ctrlHide'
					width="12" theme="simple"
					onclick="javascript:callWindow('paraFrm_assignComments','assignComments','','500','500');"></td>
				<td colspan="1" id='ctrlHide'>Remaining chars<s:textfield name="descCnt"
				readonly="true" size="5"></s:textfield></td>
			</tr>

       </table>
      </td>
    </tr>
          
          
        <s:hidden name="reqCategoryCode"></s:hidden>
      <s:hidden name="reqSubTypeCode"></s:hidden>
      <s:hidden name="reqCategory"></s:hidden>
      <s:hidden name="reqSubType"></s:hidden>
      
      <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="%{insertFlag}">
						<input type="button" class="add" value="  Save" onclick="return saveValidate(this);" />
					</s:if> <s:submit action="AssetAssignment_input" cssClass="token"
						value="Back" />
						
							<s:submit action="AssetEmployee_report"
						 cssClass="token"
						value="Report" />
				 </td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
    
  </table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<script type="text/javascript">
function showInventory(categoryCode,subTypeCode,rowId){
	document.getElementById("paraFrm_hiddenCategoryCode").value=categoryCode;
	document.getElementById("paraFrm_hiddenSubTypeCode").value=subTypeCode;
	document.getElementById("paraFrm_rowId").value = rowId;
	
	callsF9(500,325,'AssetEmployee_f9actionInventory.action'); 
}
function saveValidate(obj){
	var count =document.getElementById("paraFrm_tableLength").value;
		if(document.getElementById("paraFrm_empName").value==""){
			alert("Please select asset application.");
			return false;
		}else if(count=="" ||count=="0" ){
		alert("No "+document.getElementById('asset2').innerHTML.toLowerCase()+" available in the list.");
		return false;
		}
		var selectFlag= false;
		for(var loop=1;loop <= eval(count);loop++){
			if(document.getElementById("paraFrm_inventoryCode"+loop).value!=""){
			selectFlag = true;
			break;
		}
		}
		if(!selectFlag){
		alert("Please select atleast one Asset to Assign");
		return false;
		}
	for(var loop=1;loop <= eval(count);loop++){
		if(!document.getElementById("paraFrm_inventoryCode"+loop).value==""){
			if(document.getElementById("assetAssignDate"+loop).value=="")
			{
				alert("Please enter  "+document.getElementById('assetAssignDate').innerHTML);
				document.getElementById("assetAssignDate"+loop).focus();
				return false;
			}		
			else if(document.getElementById("quantityAssigned"+loop).value=="")
			{
				alert("Please enter quantity assigned "+document.getElementById('assigned').innerHTML);
				document.getElementById("quantityAssigned"+loop).focus();
				return false;
			}else if(eval(document.getElementById("quantityAssigned"+loop).value)=="0"){
				alert("Zero is not allowed in quantity "+document.getElementById('assigned').innerHTML);
				document.getElementById("quantityAssigned"+loop).focus();
				return false;
		}
		
			}
		if(!document.getElementById("quantityAssigned"+loop).value==""){
		if(eval(document.getElementById("paraFrm_quantityAvailable"+loop).value)<eval(document.getElementById("quantityAssigned"+loop).value))
				{
					alert("Quantity "+document.getElementById('assigned').innerHTML+" should be less than quantity "+document.getElementById('available').innerHTML);
					document.getElementById("quantityAssigned"+loop).focus();
					return false;
				}
			if(eval(document.getElementById("assetRequiredIterator"+loop).value)<eval(document.getElementById("quantityAssigned"+loop).value))
				{
					alert("Quantity "+ document.getElementById('assigned').innerHTML +" should be less than quantity "+document.getElementById('required1').innerHTML);
					document.getElementById("quantityAssigned"+loop).focus();
					return false;
				}
	
			if(document.getElementById("paraFrm_inventoryCode"+loop).value=="")
				{
					alert("Please select Inventory");
					document.getElementById("quantityAssigned"+loop).focus();
					return false;
				}
		
			}
	 }
	 
				obj.disabled=true;
				document.getElementById('paraFrm').target="_self";
				document.getElementById('paraFrm').action='AssetEmployee_saveAssignment.action';	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
} 
	
 function callOrderRequest(categoryCode,subTypeCode,categoryName,subtypeName,orderType){
	
	document.getElementById("paraFrm_reqCategoryCode").value=categoryCode;
	document.getElementById("paraFrm_reqSubTypeCode").value=subTypeCode;
	document.getElementById("paraFrm_reqCategory").value=categoryName;
	document.getElementById("paraFrm_reqSubType").value=subtypeName;
	var warehouse = document.getElementById("paraFrm_empWarehouseCode").value;
	
	if(orderType=="wareHouse"){
	win=window.open('','win','top=260,left=150,width=600,height=500,scrollbars=no,status=no,resizable=no');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action="AssetEmployee_requstFromOtherWarehouse.action";
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
	}else{
	
	}
	
}

function callLength(type){ 
		
	 if(type=='descCnt'){
				var cmt =document.getElementById('paraFrm_assignComments').value;
				var remain = 500 - eval(cmt.length);
				document.getElementById('paraFrm_descCnt').value = remain;
				
					if(eval(remain)< 0){
				document.getElementById('paraFrm_assignComments').style.background = '#FFFF99';
				
				}else document.getElementById('paraFrm_assignComments').style.background = '#FFFFFF';
	}
}  	
	
</script>
