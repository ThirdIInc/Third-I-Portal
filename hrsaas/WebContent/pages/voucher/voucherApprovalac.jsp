<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="VoucherApprovalAc" id="paraFrm" theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" align="center" colspan="6" class="pageHeader">Voucher
			Approval 456</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">Voucher Status:<font color="red">*</font></td>
			<td colspan="2" width="55%"><s:hidden name="voucherApprovAc.check" />
				<s:select name="voucherApprovAc.status" headerKey="" headerValue="Select"
					cssStyle="width:110" theme="simple"
					list="#{'P':'Pending','A':'Approved','R':'Rejected'}"
					onchange="return Status();" ></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr></table>
		
		<table  width="100%">
  		<tr><td>&nbsp;</td></tr>
  		<tr>
  			<td class="headercell" width="10%" >Voucher No.</td>
			<td class="headercell" width="20%">Employee Name</td>
			<td class="headercell" width="20%">Date</td>
			<td class="headercell"width="15%" >Total Amount</td>
			<td class="headercell" width="35%">status</td>
		 </tr>
		<%! int i = 0 ; %>
		<% int k = 1, c=0; %>
		<s:iterator value="list">
			<tr>
				<td  class="bodycell"><s:property value = "voucherNo"/>
					<s:hidden name="voucherApprovAc.voucherNo" value="%{voucherNo}"></s:hidden><s:hidden name = "empCode"/></td>
		        <td  class="bodycell"><s:property value = "empName"/></td>
		        <td  class="bodycell"><s:property value = "date"/></td>
		        <td  class="bodycell"><s:property value = "totalAmt"/></td>
		       <td  class="bodycell"> <s:select name="checkStatus" id="<%="check"+k %>"
					cssStyle="width:110" theme="simple" list="#{'P':'Pending','A':'Approved','R':'Rejected'}" 	 /></td>
					
			</tr>
			<%k++;c++; %>
		</s:iterator>
		<% i=k ; %>
			<tr>
		<td>&nbsp;&nbsp;</td> 
		</tr>
		<tr>
			<td>&nbsp;&nbsp;</td> <td>&nbsp;&nbsp;</td> 
			<td width="50%" colspan="6">
			<div id="saveButton" >  
				<s:submit action="VoucherApprovalAc_save" cssClass="pagebutton" value="  Save  " id="save" onclick="return saveCheck();"></s:submit>
		</div>
		</tr>
	</table>
	<input type="hidden" name="count" id="count" value=<%=c%> >
</s:form>

<script>
	callOnload();
 function Status()
	{ 
	
		document.getElementById('paraFrm').target="_main";
		document.getElementById('paraFrm').action="VoucherApprovalAc_callstatus.action";	
		document.getElementById('paraFrm').submit();
	}
	
function callOnload(){
		var check =<%=i%>;
		if(document.getElementById("voucherApprovAc.status").value=='A' || document.getElementById("voucherApprovAc.status").value=='R'){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
			document.getElementById("save").disabled=true;
			document.getElementById('saveButton').style.display = 'none';
		}	
}

function saveCheck(){
     var count=document.getElementById('count').value;
	if(document.getElementById("voucherApprovAc.status").value=="")
	{
		alert("Please select voucher Application status:");
		document.getElementById("voucherApprovAc.status").focus();
		return false;
	}
	if(count==0){
		alert('No Pending Records ');		
		return false;
	}
	
	
}
</script>