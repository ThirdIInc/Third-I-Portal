<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AssetEmployee" validate="true" id="paraFrm"
	theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Asset
					Assignment to Employee </strong></td>
				</tr>
			</table>
			</td>
		</tr>
    
     
     <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<s:hidden name="hiddenCategoryCode"></s:hidden><s:hidden name="hiddenSubTypeCode"></s:hidden><s:hidden name="inventoryRowId"></s:hidden>
        <s:hidden name="empWarehouseCode"/>
					<tr>
						<td width="78%"><s:if test="%{insertFlag}">
							<input type="button" class="add" theme="simple"  value="  Save" onclick="return saveValidate();" />
						</s:if> </td>
						<td width="22%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
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
                				<td colspan="5" class="formhead"><strong class="forminnerhead">Request from other warehouse </strong></td>
              				</tr>
              				<tr>
              					<td height="22" width="25%" class="formtext"><label  class = "set"  id="requiredCategory" name="requiredCategory" ondblclick="callShowDiv(this);"><%=label.get("requiredCategory")%></label>  :</td><s:hidden name="empCode"/>
                  				<td width="25%" colspan="1" nowrap="nowrap"><s:textfield size="25" name="reqCategory" readonly="true"/><s:hidden name="reqCategoryCode"></s:hidden></td>
			 					<td height="22" width="25%" class="formtext"><label  class = "set"  id="requiredSubType" name="requiredSubType" ondblclick="callShowDiv(this);"><%=label.get("requiredSubType")%></label>  :</td>
                  				<td width="25%" colspan="1" nowrap="nowrap"><s:textfield size="25" name="reqSubType" /> <s:hidden name="ReqSubTypeCode"></s:hidden></td>
			 				</tr>
                   		</table>
                   </td>
       </tr>
                   	
      					
    
    
    <tr>
      <td colspan="3" ><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
         <tr>
            <td class="formtext" >
            <div  style="overflow-y:scroll; height:320"> 
            <table width="100%" border="0" cellpadding="2" cellspacing="2"  class="sortable" >
            		<tr >
                  		<td colspan="8" class="formth"><strong class="forminnerhead">Warehouse list </strong></td>
                	</tr>
                	<tr >
                  		<td width="5%" class="formth"><label  class = "set"  id="assign.sr.no5" name="assign.sr.no" ondblclick="callShowDiv(this);"><%=label.get("assign.sr.no")%></label></td>
                  		<td class="formth" width="20%"><label  class = "set"  id="warehouseRequest" name="warehouseRequest" ondblclick="callShowDiv(this);"><%=label.get("warehouseRequest")%></label></td>
                   		<td class="formth" width="20%"><label  class = "set"  id="inventoryRequest" name="inventoryRequest" ondblclick="callShowDiv(this);"><%=label.get("inventoryRequest")%></label></td>
                   		<td class="formth" width="10%"><label  class = "set"  id="available8" name="available" ondblclick="callShowDiv(this);"><%=label.get("available")%></label></td>
                   		<td class="formth" width="25%"><label  class = "set"  id="required" name="required" ondblclick="callShowDiv(this);"><%=label.get("required")%></label></td>
                  	</tr>
                  	
                <% int j = 1; %>   
         		<s:if test="noData">
	          		<tr>
	          			<td width="100%" colspan="5" align="center"><font color="red">No matching Inventories available in other warehouse </font></td>
	             	</tr>
	             </s:if>
	             
	             <s:iterator value="warehouseList"> 
                	<tr>
                 		<td class="border2" width="5%"><%= j%></td>
                  		<td class="border2" width="20%"><s:property value="warehouseName"/><s:hidden name="warehouseName"/><s:hidden name="warehouseCode" value="%{warehouseCode}"></s:hidden></td>
                  		<td class="border2" width="20%"><s:property value="inventoryCodeReq"/>&nbsp;<s:hidden name="inventoryCodeReq"/><s:hidden name="masterCodeReq"/></td>
                  		<td class="border2" width="10%"><s:property value="availableReq"/>&nbsp;<s:hidden id ="<%="availableReq"+j%>" name="availableReq"></s:hidden></td>
                  		<td class="border2" width="25%"><s:textfield id ="<%="requiredReq"+j%>" name="requiredReq"   size="10" onkeypress="return numbersOnly();" ></s:textfield></td>
	      	   		</tr>
	      	   				<%j++;%>
                  </s:iterator>
                 
                  
                  <input type="hidden" name="count" id="count" value="<%=j-1%>"/>
                  </div></table></td></tr>
                  
                  </table></td></tr>
    		</table>
    		
    		
    		
 </s:form>
 
 
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">
	function saveValidate(){
	var count=document.getElementById("count").value;
		if(count=="0" || count=="")
			{
				alert("No assets available to request.");
				return false;
			}
		var selectFlag= false;
		for(var loop=1;loop <= eval(count);loop++){
				if(document.getElementById("requiredReq"+loop).value!=""){
				selectFlag = true;
				break;
			}
		}
		if(!selectFlag){
				alert("Please select atleast one required quantity");
				return false;
				}
			for(var loop=1;loop <= eval(count);loop++){
				if(!document.getElementById("requiredReq"+loop).value==""){
				
				if(document.getElementById("requiredReq"+loop).value=="0")
				{
					alert("Zero is not allowed in quantity required");
					document.getElementById("requiredReq"+loop).focus();
					return false;
					
				}
				
				if(eval(document.getElementById("availableReq"+loop).value)<eval(document.getElementById("requiredReq"+loop).value))
						{
							alert("Quantity  should be equal available quantity ");
							document.getElementById("requiredReq"+loop).focus();
							return false;
						}
					
			
				
				
				}
			}
		document.getElementById("paraFrm").action="AssetEmployee_saveRequest.action";
		document.getElementById("paraFrm").submit();
		alert("Assets requested succeessfully.");
		window.close();
}
	
</script>
 
     