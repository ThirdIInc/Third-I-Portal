<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="BankMaster" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="show" />
	<s:hidden name="hiddencode" id="hiddencode" />
	<s:hidden name="bankId" />
	<s:hidden name="hiddenBankId" id="hiddenBankId" />
	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Bank
					</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="3">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead"><label
						class="set" name="bank" id="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label></strong></td>


				</tr>

				<tr>

					<td width="20%" height="22" class="formtext"><label
						class="set" name="bank.name" id="bank.name"
						ondblclick="callShowDiv(this);"><%=label.get("bank.name")%></label>
					:<font color="red">*</font></td>
					<td width="25%" height="22"><s:if test="bankNameFlag">
						<s:textfield name="bankName" size="25"
							onkeypress="return allCharacters();" maxlength="90" />
					</s:if><s:else>
						<s:property value="bankName" />
						<s:hidden name="bankName" />
					</s:else></td>
					<td width="1%" height="22"></td>
					<td width="24%" class="formtext"></td>
					<td width="25%"></td>


				</tr>

				<s:if test="manageBranchFlag">
					<tr>
						<td colspan="5">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="2" class="formbg">
							<tr>
								<td class="text_head"><strong class="forminnerhead"><label
									class="set" id="manageBranches" name="manageBranches"
									ondblclick="callShowDiv(this);"><%=label.get("manageBranches")%></label>
								</strong></td>

							</tr>
							<tr>
								<td class="text_head"><strong class="forminnerhead"><label
									class="set" id="bank.name" name="bank.name"
									ondblclick="callShowDiv(this);"><%=label.get("bank.name")%></label>
								</strong></td>
								<td height="22" width="25%" colspan="4" nowrap="nowrap"><s:textfield
									size="25" name="bankMaster.bankName" readonly="true"
									cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td width="25%" height="22" class="formtext"><label
									class="set" id="branch.name" name="branch.name"
									ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label>
								:<font color="red">*</font></td>
								<td height="22" width="25%" nowrap="nowrap"><s:textfield
									size="25" name="branchName" maxlength="50" /></td>
								</td>

								<td width="25%" height="22" class="formtext"><label
									class="set" id="branch.code" name="branch.code"
									ondblclick="callShowDiv(this);"><%=label.get("branch.code")%></label>
								:<font color="red"></font></td>
								<td height="22" width="25%" nowrap="nowrap"><s:textfield
									size="25" name="branchCode" maxlength="15"
									onkeypress="return numbersOnly();" /></td>
								</td>
							</tr>

							<tr>
								<td width="25%" height="22" class="formtext"><label
									class="set" id="bsr.code" name="bsr.code"
									ondblclick="callShowDiv(this);"><%=label.get("bsr.code")%></label>
								:<font color="red">*</font></td>
								<td height="22" width="25%" nowrap="nowrap"><s:textfield
									size="25" name="bsrCode" maxlength="15" /></td>
								</td>

								<td width="25%" height="22" class="formtext"><label
									class="set" id="bank.micr.code" name="bank.micr.code"
									ondblclick="callShowDiv(this);"><%=label.get("bank.micr.code")%></label>
								:<font color="red">*</font></td>
								<td height="22" width="25%" nowrap="nowrap"><s:textfield
									size="25" name="bankMicrCode" maxlength="15"
									onkeypress="return numbersOnly();" /></td>
								</td>
							</tr>

							<tr>
								<td width="25%" height="22" class="formtext"><label
									class="set" id="ifsc.code" name="ifsc.code"
									ondblclick="callShowDiv(this);"><%=label.get("ifsc.code")%></label>
								:<font color="red"></font></td>
								<td height="22" width="25%" nowrap="nowrap"><s:textfield
									size="25" name="ifscCode" maxlength="15" /></td>
								</td>

								<td width="25%" height="22" class="formtext"><label
									class="set" id="city" name="city"
									ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
								:<font color="red"></font></td>
								<td height="22" width="25%" nowrap="nowrap"><s:textfield
									size="25" name="branchCity" maxlength="15" /></td>
								</td>
							</tr>

							<tr>
								<td width="25%" height="22" class="formtext"><label
									class="set" id="branch.address" name="branch.address"
									ondblclick="callShowDiv(this);"><%=label.get("branch.address")%></label>
								:</td>
								<td height="22" width="25%" nowrap="nowrap"><s:textarea
									cols="30" rows="2" name="branchAddress"
									onkeypress="return imposeMaxLength(event, this, 200);" /></td>
								<s:hidden name="hiddenBankMicrCode" id="hiddenBankMicrCode" />
							</tr>
							<!--
				
			
					<tr>
						
						<td width="100%" colspan="4" align="center">
						<input type="button" value="Add Branch" onclick="callValidationHS();" class="add">
						</td>
					</tr>
				
				
			-->
							<tr>
								<td colspan="5" align="center">
								
								<!--<s:if test="insertFlag">
									<input type="button" class="add" value="   Insert "
										onclick="return callAdd();" />
								</s:if> <s:if test="updateFlag">
									<input type="button" class="edit" value="   Update "
										onclick="return callUpdate();" />
								</s:if> 
								
								--><input type="button" class="add" value="   Add "
										onclick="return callAdd();" />
								
								<input type="button" class="reset" value="    Reset "
									onclick="return callReset();" /></td>
							</tr>



							<!-- New Added by Ganesh -->

							<tr>
								<td colspan="8">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="formbg">

									<tr>
										<td colspan="7">
										<table width="100%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<%
													int totalPage = 0;
															int pageNo = 0;
												%>
												<s:if test="modeLength">
													<td colspan="7" width="20%" class="text_head"><strong
														class="forminnerhead">Branch Details : </strong></td>

													<td id="ctrlShow" width="80%" align="right" class="">
													<b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 				pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
														onclick="callPage('1', 'F', '<%=totalPage%>', 'BankMaster_branchSavedDetails.action');">
													<img title="First Page" src="../pages/common/img/first.gif"
														width="10" height="10" class="iconImage" /> </a>&nbsp; <a
														href="#"
														onclick="callPage('P', 'P', '<%=totalPage%>', 'BankMaster_branchSavedDetails.action');">
													<img title="Previous Page"
														src="../pages/common/img/previous.gif" width="10"
														height="10" class="iconImage" /> </a> <input type="text"
														name="pageNo" id="pageNo" size="3" value="<%=pageNo%>"
														maxlength="10"
														onkeypress="callPageText(event, '<%=totalPage%>', 'BankMaster_branchSavedDetails.action');return numbersOnly();" />
													of <%=totalPage%> <a href="#"
														onclick="callPage('N', 'N', '<%=totalPage%>', 'BankMaster_branchSavedDetails.action')">
													<img title="Next Page" src="../pages/common/img/next.gif"
														class="iconImage" /> </a>&nbsp; <a href="#"
														onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'BankMaster_branchSavedDetails.action');">
													<img title="Last Page" src="../pages/common/img/last.gif"
														width="10" height="10" class="iconImage" /> </a></td>
												</s:if>
											</tr>
										</table>
										</td>


									</tr>
									<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr class="td_bottom_border">

							<td class="formth"><label name="branch.name" id="branch.name"
								ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label></td>
							<td class="formth"><label name="branch.code" id="branch.code"
								ondblclick="callShowDiv(this);"><%=label.get("branch.code")%></label></td>
							<td class="formth"><label name="bsr.code" id="bsr.code"
								ondblclick="callShowDiv(this);"><%=label.get("bsr.code")%></label></td>
							<td class="formth"><label name="bank.micr.code" id="bank.micr.code"
								ondblclick="callShowDiv(this);"><%=label.get("bank.micr.code")%></label>
							</td>
							<td class="formth"><label name="ifsc.code" id="ifsc.code"
								ondblclick="callShowDiv(this);"><%=label.get("ifsc.code")%></label></td>
							<td class="formth"><label name="city" id="city"
								ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
								<td class="formth"><label name="branch.address" id="branch.address"
								ondblclick="callShowDiv(this);"><%=label.get("branch.address")%></label></td>
							<td class="formth" nowrap="nowrap">Edit | Delete</td>
						</tr>
						<%
										int i = pageNo * 5 - 5;									
									%>
						
						<%int count1=1; %>
						<s:iterator value="branchesList" status="stat">

							<tr class="sortableTD">
								<td class="sortableTD"><s:hidden name="ittbankId" /><s:property
									value="ittbranchName" /></td>
								<td class="sortableTD" width="13%"><s:property
									value="ittbranchCode" />&nbsp;</td>
								<td class="sortableTD" width="15%"><s:property
									value="ittbsrCode" />&nbsp;</td>
								<td class="sortableTD" width="16%"><s:property
									value="ittbankMicrCode" />&nbsp;</td>
								<td class="sortableTD" width="11%">
								<s:property	value="ittifscCode" />&nbsp;
								</td>
								<td class="sortableTD" width="11%"><s:property	value="ittbranchCity" />&nbsp;</td>
									<td class="sortableTD" width="11%"><s:property	value="ittbranchAddress" />&nbsp;</td>
								<td class="sortableTD" width="10%" id="ctrlShow" align="center">
									<input type="button" class="rowEdit"
										onclick="callForEdit('<s:property value="ittbankId"/>')"
										 />
								|
									<input type="button" class="rowDelete"
										onclick="callBranchDelete('<s:property value="ittbankId"/>')"
										/>
								<!--
							 
							 <%count1++; %>
							 
						
						-->
								</td>
							</tr>
						</s:iterator>
					</table>
					<s:if test="modeLength"></s:if>
								<s:else>
									<table width="100%" >
										<tr>
											<td align="center"><font color="red">No Data To Display</font></td>
										</tr>
									</table>
								</s:else>
					<s:hidden name="paracode" /></td>
				</tr>


								</table>
								</td>
							</tr>
							<!-- New Add End -->

						</table>
						</td>
					</tr>

				</s:if>

			</table>
			</td>
		</tr>

		<tr>
			<td width="80%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>

</s:form>
<script>


function saveFun_old(type){
var fieldName = ["paraFrm_bankMaster_bankMicrCode","paraFrm_bankMaster_bsrCode","paraFrm_bankMaster_bankName"];
var lableName = ["bankmicrcode","bsrcode","bank.name"];

var flag = ["enter","enter","enter"];
var fieldName1 = ["paraFrm_bankMaster_bankMicrCode","paraFrm_bankMaster_bankName" ];
/*if(type == 'update'){
		if(document.getElementById('paraFrm_hiddenBankMicrCode').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
		
	else 
	{
		if(!document.getElementById('paraFrm_hiddenBankMicrCode').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}*/

     if(!(validateBlank(fieldName, lableName,flag)))
      {  return false; }
      
      var amount=document.getElementById('paraFrm_bankMaster_bankMicrCode').value;
      if(isNaN(amount)) {
	   alert("Only numbers are allowed in"+document.getElementById('bankmicrcode').innerHTML.toLowerCase()+"field! ");
		 document.getElementById('paraFrm_bankMaster_bankMicrCode').focus();
		return false;
		}
		
		  if(!f9specialchars(fieldName1))
      {
              return false;
       }
		 var branchcode=document.getElementById('paraFrm_bankMaster_branchCode').value;
      if(branchcode!="")
      {
      
      if(isNaN(branchcode)) {
	   alert("Only numbers are allowed in Branch Code field !");
	 	document.getElementById('paraFrm_bankMaster_branchCode').focus();
  		return false;
		}
      
      }
           	var addresslength=document.getElementById('paraFrm_bankMaster_branchAddress').value  
             	if(addresslength.length>200)
             	{
             	   alert(document.getElementById('address').innerHTML.toLowerCase() +"length should be less than 200 characters..!");
             	   return false;
             	} 
             	document.getElementById('paraFrm').target = "_self";
             	document.getElementById('paraFrm').action = 'BankMaster_save.action';
	     	document.getElementById('paraFrm').submit();
     	    return true;

}
function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'BankMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'BankMaster_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'BankMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'BankMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	
function editFun() {
		return true;
	}
	
	function callLength(type){ 
		
		
					var cmt =document.getElementById('paraFrm_bankMaster_branchAddress').value;
					var remain = 200 - eval(cmt.length);
						if(eval(remain)< 0){
					document.getElementById('paraFrm_bankMaster_branchAddress').style.background = '#FFFF99';
				
					}else 
					document.getElementById('paraFrm_bankMaster_branchAddress').style.background = '#FFFFFF';
					document.getElementById('paraFrm_descCnt').value = remain;
				}
	
	
		
		
				function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}


	
			function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
	}	
	
	function saveFun() {
		try{
		
		
		
		var bankNameVar=document.getElementById('paraFrm_bankName').value;
  	if(bankNameVar==""){
			alert("Please enter "+document.getElementById('bank.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_bankName').focus();
			return false;
	}
	}
	catch(e){
	alert(e);
	}
  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'BankMaster_save.action';
		document.getElementById('paraFrm').submit();
      	
  	
  			}
  	
  	// Application In-Process List Begins
	function callPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNo').value;	
		actPage = document.getElementById('myPageInProcess').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNo').value = actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNo').value=actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNo').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageInProcess').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNo').value;
		 	var actPage = document.getElementById('myPageInProcess').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNo').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNo').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageInProcess').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Application In-Process List Ends
	
	function callAdd(){
	try{
	
	//alert(document.getElementById('paraFrm_paracode').value);
	var pCode = document.getElementById('paraFrm_paracode').value
	var bankCode =  document.getElementById('paraFrm_bankId').value
	
	
	//if(document.getElementById('paraFrm_paracode').value==""){
	//	alert("You can't Update.Please Insert");
	//	return false;
	//	
		
	//	}
		
	
	var qtt=trimData(document.getElementById('paraFrm_branchName').value);
		
		if(document.getElementById('paraFrm_branchName').value==''){
			 alert ("Please enter " +document.getElementById('branch.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_branchName').focus();
			return false;
		}
		
		if(qtt==''){
			 alert ("Please enter " +document.getElementById('branch.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_branchName').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_bsrCode').value==''){
			 alert ("Please enter " +document.getElementById('bsr.code').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_bsrCode').focus();
			return false;
		}
		
	//	if(document.getElementById('paraFrm_bsrCode').value == document.getElementById('paraFrm_ittbsrCode').value ){
	//		 alert ("BSR Code already Exist. Please Enter new BSR Code.");
	//		  document.getElementById('paraFrm_bsrCode').focus();
	//		return false;
	//	}
		
		if(document.getElementById('paraFrm_bankMicrCode').value==''){
			 alert ("Please enter " +document.getElementById('bank.micr.code').innerHTML.toLowerCase());
			  document.getElementById('paraFrm_bankMicrCode').focus();
			return false;
		}
		//alert(document.getElementById('paraFrm_bankMicrCode').value);
		
		
		 var branchcode=document.getElementById('paraFrm_branchCode').value;
      if(branchcode!="")
      {
      
      if(isNaN(branchcode)) {
	   alert("Only numbers are allowed in Branch Code field !");
	 	document.getElementById('paraFrm_branchCode').focus();
  		return false;
		}
      
      }
	
	}catch (e)
	{
	alert(e);
	}
		
	
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="BankMaster_addBranchDtl.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
		
	
		
		
	}
	
	function callBranchDelete(id){
		var conf=confirm("Are you sure to delete this record?");   
   		if(conf) {
   		  		  
	   	document.getElementById("paraFrm").action="BankMaster_branchDelete.action";
	  	document.getElementById('paraFrm_paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
   }else {
   		return false;
   
   }
   }
    function callForEdit(id){
    try{
    
    
	
	   	document.getElementById("paraFrm").action="BankMaster_edit.action";
	  	document.getElementById('paraFrm_paracode').value=id;
	  	document.getElementById('paraFrm_bankId').value=id;
	  	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   	}catch (e)
	{
	alert(e);
	}
	}
	
	function callUpdate(){
		
		if(document.getElementById('paraFrm_paracode').value==""){
		alert("You can't Update.Please Insert");
		return false;
		
		
		}
		
		  
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="BankMaster_addBranchDtl.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
	}
	
	function callReset() {
	document.getElementById('paraFrm').target="main";		  
	document.getElementById("paraFrm").action="BankMaster_reset.action";
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target="main";
}   
function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'BankMaster_reportBank.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callAddDetails(){
	
	
	}
	
	
</script>

