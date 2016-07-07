<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<s:form action="AssetDirectAssign" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="usertypeRadioOptionValue" id="usertypeRadioOptionValue" />
	<s:hidden name="assetcategorycode" />
	<s:hidden name="assetsubtypecode" />
	<s:hidden name="assetcategory" />
	<s:hidden name="assetsubtype" />
	<s:hidden name="assetautoid" />

 
	<table width="100%" border="0" align="center" cellpadding="2"  
		cellspacing="0" class="formbg">
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
					Direct Assignment </strong></td>
				</tr>
			</table>
			</td>
		</tr>
	 
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<s:hidden name="assetassignmentid" />
				<tr>
					<td width="20%"><label name="assetDirAssign.assignto"
						id="assetDirAssign.assignto" ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.assignto")%></label>
					<font color="Red">*</font>:</td>
					<td width="35%"><s:radio name="usertype" id="usertype"
						value="%{usertype}" list="#{'E':'Employee'}"
						onclick="setUserTypeRadioValue(this);"></s:radio></td>
					<td width="35%" colspan="2"><s:radio name="usertype"
						id="usertype1" value="%{usertype}" list="#{'V':'Vendor'}"
						onclick="setUserTypeRadioValue(this);"></s:radio></td>
				</tr>

				<tr>
					<td  width="100%" colspan="4">
					<table  id="checkempId" border="0">
						<tr>
							<td width="46%"><label name="assetDirAssign.employee"
								id="assetDirAssign.employee" ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.employee")%></label>
							<font color="Red">*</font>:</td>
							<td width="54%"><s:hidden name="employeeToken" /> <s:hidden
								name="employeecode" /> <s:textfield name="employeename"
								theme="simple" size="25" readonly="true" /> <img id="ctrlHide"
								src="../pages/images/recruitment/search2.gif" height="18"
								class="iconImage" align="absmiddle" width="18"
								onclick="return callEmp();"></td>
						</tr>
					</table>
					<table  id="checkvendorId" border="0">
						<tr>
							<td width="46%"><label name="assetDirAssign.vendor"
								id="assetDirAssign.vendor" ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.vendor")%></label>
							<font color="Red">*</font>:</td>
							<td width="54%"><s:hidden name="vendorid" /> <s:textfield
								name="vendorname" theme="simple" size="25" readonly="true" /> <img
								id="ctrlHide" src="../pages/images/recruitment/search2.gif"
								height="18" class="iconImage" align="absmiddle" width="18"
								onclick="return callVendor();"></td>
						</tr>
					</table>
					</td>
				</tr>



				<tr>
					<td width="20%"><label name="assetDirAssign.inventory"
						id="assetDirAssign.inventory" ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.inventory")%></label>
					<font color="Red">*</font>:</td>
					<td width="30%"><s:textfield name="inventorycode"
						theme="simple" size="25" readonly="true" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="return callInventory();"></td>
					<td width="20%"><label name="assetDirAssign.assigndate"
						id="assetDirAssign.assigndate" ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.assigndate")%></label>
					<font color="Red">*</font>:</td>
					<td width="30%"><s:textfield size="25" maxlength="10"
						name="assignDate" onkeypress="return numbersWithHiphen();" /> <s:a
						href="javascript:NewCal('paraFrm_assignDate','DDMMYYYY');">
						<img src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16" id='ctrlHide'>
					</s:a></td>


				</tr>
				<tr>
					<td width="20%" class="formtext"><label class="set"
						id="assetDirAssign.availablequantity"
						name="assetDirAssign.availablequantity"
						ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.availablequantity")%></label>
					:</td>
					<td width="30%"><s:textfield size="25" readonly="true"
						name="availablequantity" maxlength="10" /></td>
					<td width="20%" class="formtext"><label class="set"
						id="assetDirAssign.assignquantity"
						name="assetDirAssign.assignquantity"
						ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.assignquantity")%></label><font
						color="Red">*</font> :</td>
					<td width="30%"><s:textfield size="25"
						onkeypress="return numbersOnly();" name="assignquantity"
						maxlength="10" /></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button" name="add"
						onclick="return callAdd();" value="Assign Asset" class="add">
						&nbsp;&nbsp;
						<s:submit action="AssetDirectAssign_reset"
						value="Reset" cssClass="reset" /></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
		<td>
		&nbsp;&nbsp;&nbsp;
		</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">
				<tr class="sortable">
					<s:hidden name="myPage" id="myPage" />
					<td class="formth" align="center"><label class="set"
						name="assetDirAssign.srno" id="assetDirAssign.srno"
						ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.srno")%></label>
					</td>
					<td class="formth" align="center"><label class="set"
						name="assetDirAssign.lstemployeorvendor"
						id="assetDirAssign.lstemployeorvendor"
						ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.lstemployeorvendor")%></label>
					</td>
					<td class="formth" align="center"><label class="set"
						name="assetDirAssign.assetcat"
						id="assetDirAssign.assetcat"
						ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.assetcat")%></label>
					</td>
					<td class="formth" align="center"><label class="set"
						name="assetDirAssign.assetsubtype"
						id="assetDirAssign.assetsubtype"
						ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.assetsubtype")%></label>
					</td>
					<td class="formth" align="center"><label class="set"
						name="assetDirAssign.lstinventory"
						id="assetDirAssign.lstinventory" ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.lstinventory")%></label>
					</td>
					<td class="formth" align="center"><label class="set"
						name="assetDirAssign.lstassigndate"
						id="assetDirAssign.lstassigndate" ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.lstassigndate")%></label>
					</td>
					<td class="formth" align="center"><label class="set"
						name="assetDirAssign.lstassignquantity"
						id="assetDirAssign.lstassignquantity"
						ondblclick="callShowDiv(this);"><%=label.get("assetDirAssign.lstassignquantity")%></label>
					</td>

				</tr>
				<%
					int i=0;
				%>
				<s:iterator value="assetassignmentList">
					<tr>
						<td align="center" class="sortableTD"><%=++i%></td>
						<td align="center" class="sortableTD"><s:property
							value="ittremployeename" /></td>
						<td align="center" class="sortableTD"><s:property
							value="ittrassetcategory" /></td>
						<td align="center" class="sortableTD"><s:property
							value="ittrassetsubtype" /></td>
						<td align="center" class="sortableTD"><s:property
							value="ittrinventory" /></td>
						<td align="center" class="sortableTD"><s:property
							value="ittrassigndate" /></td>
						<td align="center" class="sortableTD"><s:property
							value="ittrassignquantity" /></td>
					</tr>
				</s:iterator>
			</table>
			</td>
		</tr>

		


	</table>

</s:form>

<script>
	
	radio();
	autoDate();
	function radio()
	{
	       var radioval=document.getElementById('usertypeRadioOptionValue').value;
	       //alert(radioval);
	       if(radioval=="E")
	       {
	       		document.getElementById('paraFrm_vendorid').value="";
		       document.getElementById('paraFrm_vendorname').value="";
		       document.getElementById('checkvendorId').style.display='none';  
		       document.getElementById('checkempId').style.display='inline';
		       document.getElementById('usertype').checked="true";
		       
		   }
		   else if(radioval=="V")
		   {
		   		
		   		document.getElementById('paraFrm_employeecode').value="";
		       document.getElementById('paraFrm_employeename').value="";
		   		document.getElementById('checkempId').style.display='none';  
		       document.getElementById('checkvendorId').style.display='inline';
		        document.getElementById('usertype1').checked="true";
		   }
		   else
		   {
		   		document.getElementById('checkvendorId').style.display='none';  
		       document.getElementById('checkempId').style.display='none';
		   }
	}
	function callInventory()
	{
		
			callsF9(500,325,'AssetDirectAssign_f9inventory.action');
	}
	
	function callAssetcat()
	{
		document.getElementById('assetsubtypecode').value="";
		document.getElementById('assetsubtype').value="";
		document.getElementById('paraFrm_inventorycode').value="";
		callsF9(500,325,'AssetDirectAssign_f9assetcategory.action');
	}
	function callSubType()
	{
			var labAssetCat1=document.getElementById('assetDirAssign.assetcat').innerHTML.toLowerCase();
			if(document.getElementById("paraFrm_assetcategorycode").value==""){
			alert("Please select "+labAssetCat1);
			return false;
			}else 
			{
				document.getElementById('paraFrm_inventorycode').value="";
				callsF9(500,325,'AssetDirectAssign_f9actionSubType.action');
		}
	}
	function callEmp()
	{
			document.getElementById("paraFrm_vendorid").value="0";
			document.getElementById("paraFrm_vendorname").value="";
		callsF9(500,325,'AssetDirectAssign_f9actionEmp.action');
	}
	
	function callVendor()
	{
			document.getElementById("paraFrm_employeecode").value="0";
			document.getElementById("paraFrm_employeename").value="";
		callsF9(500,325,'AssetDirectAssign_f9vendor.action');
	}
	function autoDate () 
 	{
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			
			
				//if(document.getElementById("paraFrm_assignDate").value!="")
				document.getElementById("paraFrm_assignDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//alert("date"+document.getElementById("paraFrm_assignDate").value);
}
	function callAdd()
	{
		try{
						var userType=document.getElementById('usertypeRadioOptionValue').value;
						if(userType=="")
						{
							alert("Please Select Assign To");
		  					return false;
						}
		  				var checkemployeeval = document.getElementById('paraFrm_employeecode').value;
		  				var vendorid = document.getElementById('paraFrm_vendorid').value;
		  				 
		  				 if(userType=="E")
		  				 {
		  				 	if(checkemployeeval == "")
		  					{
		  						alert("Please Select Employee");
		  						
		  						return false;
		  					}	
		  				 }
		  				 else if(userType=="V")
		  				 {
		  				 	if(vendorid == "")
		  					{
		  						alert("Please Select Vendor");
		  						
		  						return false;
		  					}	
		  				 }
		  				if(vendorid=="" && checkemployeeval == "")
		  					{
		  						alert("Please Select Employee or Vendor");
		  						
		  						return false;
		  					}	
		  					
		  				
		  					
		  				
		  					var checkinventorycode = document.getElementById('paraFrm_inventorycode').value;
		  					
		  				if(checkinventorycode=="")
		  					{
		  						alert("Please Select Inventory");
		  						return false;
		  					}
		  							  							  				
		  					var assignDate = document.getElementById('paraFrm_assignDate').value;
		  					if(assignDate=="")
		  					{
		  						alert("Please enter Assign date");
		  						
		  						return false;
		  					}
		  					
								  						 
									var check= validateDate('paraFrm_assignDate', 'assetDirAssign.assigndate');
									if(!check){
									return false;
								}
		
		  					var assignQuantity=document.getElementById('paraFrm_assignquantity').value;
		  					if(assignQuantity=="" )
		  					{
		  						alert("Please enter Assign quantity");
		  						
		  						return false;
		  					}
		  					if(assignQuantity=="0" ||assignQuantity=="0.0")
		  					{
		  					alert("Assign quantity should be greater than zero.");
		  						
		  						return false;
		  					}
		  					
		  					if(eval(document.getElementById('paraFrm_assignquantity').value) > eval(document.getElementById('paraFrm_availablequantity').value))
		  					{
		  						alert("Assign quantity should not be greater than available quantity");
		  						document.getElementById('paraFrm_assignquantity').focus();
		  						return false;
		  					}
		  					document.getElementById('paraFrm').action='AssetDirectAssign_save.action';
		  					document.getElementById('paraFrm').submit();
		  				}
		  				catch(e){alert(e);}
		  				return true; 	
	}
			function saveFun()
			{
				try{
						
		  					document.getElementById('paraFrm').action='AssetDirectAssign_save.action';
		  					document.getElementById('paraFrm').submit();
		  				}
		  				catch(e){alert(e);}
		  				return true; 		
	
			}
	
	
	function setUserTypeRadioValue(id){
	//alert(id);
		var opt=document.getElementById('usertypeRadioOptionValue').value =id.value;
		radio();	
}

</script>