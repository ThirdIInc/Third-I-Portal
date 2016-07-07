<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="WareHouseMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Warehouse
					</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td width="22%">
			<div align="right"><font color="red">*</font> Indicates
			Required</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="4" class="formhead"><strong class="forminnerhead">Warehouse</strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="warehouse.name" id="warehouse.name"
						ondblclick="callShowDiv(this);"><%=label.get("warehouse.name")%></label>
					<font color="red">*</font> :</td>
					<td width="30%" colspan="1"><s:textfield size="30"
						name="wareHouseName" maxlength="100" /><s:hidden
						name="wareHousecode" /></td>
					<td width="25%" colspan="1">&nbsp; <s:hidden
						name="centralizeFlag"></s:hidden>
					<td width="25%" colspan="1" class="formtext">&nbsp;<s:hidden
						name="branchFlag"></s:hidden></td>
				</tr>
				
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="warehouse.resperson" id="warehouse.resperson"
						ondblclick="callShowDiv(this);"><%=label.get("warehouse.resperson")%></label>
					<font color="red">*</font> : <s:hidden name="respCode" /></td>
					<td width="80%" colspan="3"><s:textfield size="10"
						name="respToken" readonly="true" /><s:textfield size="40"
						name="respName" readonly="true" /> <img class="imageIcon"
						id='ctrlHide' src="../pages/images/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'WareHouseMaster_f9actionResponsible.action'); ">
					<s:hidden name="tableLength" /></td>
				</tr>
				
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="warehouse.Phoneno" id="warehouse.Phoneno"
						ondblclick="callShowDiv(this);"><%=label.get("warehouse.Phoneno")%></label>
					 :</td>
					<td width="80%" colspan="3"><s:textfield  name="mobileno" size="60" cssStyle="border:none;" 
						onkeypress="return numbersOnly();" maxLength="12"/></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="warehouse.email" id="warehouse.email"
						ondblclick="callShowDiv(this);"><%=label.get("warehouse.email")%></label>
					 :</td>
					<td width="80%" colspan="3">
					<s:textfield name="emailid" size="60" cssStyle="border:none;" />  </td>
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
			<td colspan="3" width="100%" id='ctrlHide'>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td colspan="4" class="formhead"><strong class="forminnerhead">Add
					Branch</strong> </td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font color="red">*</font>:</td>

					<td colspan="3" id='ctrlHide' width="80%" height="28"><s:textfield
						theme="simple" name="branchName" size="30" readonly="true" /> <s:hidden
						name="branchCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'WareHouseMaster_f9actionBranchWiseSelect.action'); ">
					</td>
				</tr>
				<tr>
					<td width="20%" colspan="1"></td>
					<td width="80%" colspan="3"><input type="button"
						value="Add Branch" class="token" onclick="return addBranch();" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="3"><strong class="forminnerhead">Branch
					List</strong></td>
				</tr>
				<tr>
					<td class="formth" width="10%"><label class="set"
						name="warehouse.srno" id="warehouse.srno"
						ondblclick="callShowDiv(this);"><%=label.get("warehouse.srno")%></label>

					</td>
					<td class="formth" width="60%"><label class="set"
						name="branch" id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
					<td class="formth" width="10%" id='ctrlHide'>Delete</td>
				</tr>
				<%
				int j = 1;
				%>
				<s:iterator value="branchList" status="stat">
					<tr>
						<td width="10%" align="center" class="td_bottom_border"><%=j%></td>
						<s:hidden name="branchCodeIterator" />
						<td width="70%" class="td_bottom_border"><s:property
							value="branchNameIterator" /><s:hidden name="branchNameIterator" /></td>
						<td width="10%" align="left" class="td_bottom_border"
							id='ctrlHide'><input type="button" class="rowDelete"
							title="Delete Record"
							onclick="return callDeleteBranch('<%=(j)%>');" /></td>
					</tr>
					<%
					j++;
					%>
				</s:iterator>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<td width="79%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td align="right"></td>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<script type="text/javascript">
//onLoad();
function onLoad(){
		//alert("centralizeFlag=="+document.getElementById("paraFrm_centralizeFlag").value);
		//alert("branchFlag=="+document.getElementById("paraFrm_branchFlag").value);
		if(document.getElementById("paraFrm_centralizeFlag").value=="true"){
			document.getElementById('branchDiv').style.display='none';
			document.getElementById('centDiv').style.display='';
		}else if(document.getElementById("paraFrm_branchFlag").value=="true"){
			document.getElementById('branchDiv').style.display='';
			document.getElementById('centDiv').style.display='none';
		}else{
			document.getElementById('branchDiv').style.display='none';
			document.getElementById('centDiv').style.display='none';
		}
}
function callCentralizeCheck(obj){
	var check = obj.value;	
	if(check=='centralizeCheck'){
		document.getElementById('branchCheck').checked="";
		document.getElementById("paraFrm_centralizeFlag").value="true";
		document.getElementById("paraFrm_branchFlag").value="false";
		document.getElementById('centDiv').style.display='';
		document.getElementById('branchDiv').style.display='none';		
		document.getElementById('paraFrm_branchCode').value="";
		document.getElementById('paraFrm_branchName').value="";		
	}if(check=='branchCheck'){
		document.getElementById('centralizeCheck').checked="";
		document.getElementById("paraFrm_branchFlag").value="true";
		document.getElementById("paraFrm_centralizeFlag").value="false";
		document.getElementById('branchDiv').style.display='';
		document.getElementById('centDiv').style.display='none';		
		document.getElementById('paraFrm_centralizeBranchName').value="";
		document.getElementById('paraFrm_centralizeBranchCode').value="";		
	}
	//document.getElementById("paraFrm").action="WareHouseMaster_clearList.action";
	//document.getElementById("paraFrm").submit();
	//alert("centralizeFlag=="+document.getElementById("paraFrm_centralizeFlag").value);
	//alert("branchFlag=="+document.getElementById("paraFrm_branchFlag").value);
}
function addBranch(){
	var fields =["paraFrm_branchName"];
	var labels =["branch"];
	var types =["select"];
	if(!validateBlank(fields,labels,types))
          return false;          
    //document.getElementById('paraFrm').target = "_self";
  	document.getElementById('paraFrm').action ='WareHouseMaster_addBranch.action';
    document.getElementById('paraFrm').submit(); 
	
}
function formValidate(type){
	var fields =["paraFrm_wareHouseName","paraFrm_respName"];
	var labels =["warehouse.name","warehouse.resperson"];
	var types =["enter","select"];
	var f9Fields = ["paraFrm_wareHouseName"];
	var length=document.getElementById("paraFrm_tableLength").value;
	if(type=="save"){
	  if(document.getElementById("paraFrm_wareHousecode").value!=""){
		alert("Please click on Update button to update the record.");
		return false;
		}
	}else {
			if(document.getElementById("paraFrm_wareHousecode").value==""){
				alert("Select a record to update.");
				return false;	
		}
	}
	if(!validateBlank(fields,labels,types))
          return false;
	if(!f9specialchars(f9Fields))
		return false;
	/*if(document.getElementById("paraFrm_centralizeFlag").value=="false" && 
	document.getElementById("paraFrm_branchFlag").value=="false")
	{
	alert("Please check warehouse type.");
	return false;
	}*/	
	if(length=="0" || length==""){
		alert("Please add branch in the table.");
		return false;
	}
	

}
function callDeleteBranch(id){
    var con=confirm('Do you  really want to delete this record ?');
	if(con){
		 document.getElementById("paraFrm_paraId").value =id;
		 document.getElementById("paraFrm").action="WareHouseMaster_deleteBranch.action";
		 document.getElementById("paraFrm").submit();
    }else 
		 return false;
}


function saveFun(){
    var fields =["paraFrm_wareHouseName","paraFrm_respName"];
	var labels =["warehouse.name","warehouse.resperson"];
	var types =["enter","select"];
	var f9Fields = ["paraFrm_wareHouseName"];
	var email = document.getElementById("paraFrm_emailid").value;
	var length=document.getElementById("paraFrm_tableLength").value;	
	if(!validateBlank(fields,labels,types))
          return false;
	if(!f9specialchars(f9Fields))
		return false;		
	if(length=="0" || length==""){
		alert("Please add branch in the table.");
		return false;
	}   
	if(!validateEmail("paraFrm_emailid")){
		return false;
	}       
  	document.getElementById('paraFrm').target = "_self";
  	document.getElementById('paraFrm').action ='WareHouseMaster_save.action';
    document.getElementById('paraFrm').submit();        
}
		
	
function resetFun() {
		document.getElementById('paraFrm_show').value = '1';  	 
  	 	document.getElementById('paraFrm').action = 'WareHouseMaster_reset.action';
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').submit();
  	}
	
function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'WareHouseMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
function deleteFun() {
     var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'WareHouseMaster_delete.action';
		document.getElementById('paraFrm').submit();
	 }
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'WareHouseMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
function editFun() {
		return true;
	}	
</script>



