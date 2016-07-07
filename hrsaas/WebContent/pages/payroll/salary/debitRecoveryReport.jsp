<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
 <s:form action="DebitRecoveryReport" id="paraFrm" theme="simple"  >
 <table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Debit Recovery Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
        	
        	<tr>
	<td colspan="3" width="100%">
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="3" align="right" width="100%">
				<div align="right"><font color="red">*</font> Indicates Required</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%">
						<input type="button" class="token"  onclick="callReset()" value=" Reset "/>
						<input type="button" class="token"  name = "mailReportBtn" onclick="mailReportFun(this);" value=" Mail Report "/>
					</td>
					<td align="right" width="60%">
						<!--<s:select theme="simple" name="reportType" cssStyle="width:130" list="#{'Pdf':'Pdf','Xls':'Xls'}" />
						--><img src="../pages/images/icons/file-pdf.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="callReport('Pdf');" title="PDF">
						<img src="../pages/images/icons/file-xls.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="callReport('Xls');" title="Excel">
						<img src="../pages/images/icons/file_extension_html.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="callReport('Html');" title="HTML">
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
	
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>
							<td colspan="1" width="20%">
							</td>
							<td colspan="3" width="80%">
							<s:hidden name="tdsCode" />
									
									
							</td>
						</tr>


						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple" name="month" cssStyle="width:152"
							list="#{'0':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td  colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield name="year" maxlength="4" onkeypress="return numbersOnly();"  theme="simple" size="24"  />	
							</td>
						</tr>
						
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="debit.name" name="debit.name" ondblclick="callShowDiv(this);"><%=label.get("debit.name")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="debitCode"/><s:textfield name="debitHead" theme="simple" readonly="true"  size="21" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'DebitRecoveryReport_f9debitHead.action');">	
							</td>
							<td colspan="1" width="20%">Branch:</td>
							<td colspan="1" width="30%"><s:hidden name="branchCode"/><s:textfield name="branchName" theme="simple" readonly="true"  size="21" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'DebitRecoveryReport_f9branch.action');">	
							</td>
							
						</tr>
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="debit.divName" name="debit.divName" ondblclick="callShowDiv(this);"><%=label.get("debit.divName")%></label><font color="red">&nbsp;</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="divId"/><s:hidden name="divName"/><s:textfield name="divisionAbbrevation" theme="simple" readonly="true"  size="21" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'DebitRecoveryReport_f9div.action');">	
							</td>
							<td colspan="1" width="20%"><label  class = "set"  id="paybillName" name="paybillName" ondblclick="callShowDiv(this);"><%=label.get("paybillName")%></label>:</td>
							<td colspan="1" width="30%"><s:hidden name="payBillId"/><s:textfield name="payBillName" theme="simple" readonly="true"  size="21" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'DebitRecoveryReport_f9payBillaction.action');">	
							</td>
							
						</tr>
						<tr>
							<td  colspan="1" width="20%"><label  class = "set"  id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label> :</td>
							<td colspan="1" width="30%"><s:select theme="simple" name="onHold" cssStyle="width:152"
							list="#{'A':'All','N':'No','Y':'Yes'}" />
							</td>
							<td  colspan="1" width="20%">&nbsp;</td>
							<td colspan="1" width="30%"><s:hidden name="reportType" />
							</td>	
						</tr>
						
						
					
						
					
					
							
							
							<tr>
							<td colspan="3" >
									</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table></td></tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%">
						<input type="button" class="token"  onclick="callReset()" value=" Reset "/>
						<input type="button" class="token"  name = "mailReportBtn" onclick="mailReportFun(this);" value=" Mail Report "/>
					</td>
					<td align="right" width="60%">
						<!--<s:select theme="simple" name="reportType" cssStyle="width:130" list="#{'Pdf':'Pdf','Xls':'Xls'}" />
						--><img src="../pages/images/icons/file-pdf.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="callReport('Pdf');" title="PDF">
						<img src="../pages/images/icons/file-xls.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="callReport('Xls');" title="Excel">
						<img src="../pages/images/icons/file_extension_html.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="callReport('Html');" title="HTML">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
</s:form>
 <script>
 function mailReportFun(obj){
		if(!validateFields()){
			return false;
		} else {
			callDropdown(obj.name,200,250,'DebitRecoveryReport_f9reportType.action','false');
			return true;
		}	
	}
 function validateFields()
 {
 	if(document.getElementById('paraFrm_month').value=="0"){
			alert("Please select the "+document.getElementById('month').innerHTML.toLowerCase());
			return false;
			}
			
			if(document.getElementById('paraFrm_debitHead').value==""){
			alert("Please select the "+document.getElementById('debit.name').innerHTML.toLowerCase());
			return false;
			}
			return true;
 }
function callReset(){
 document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action='DebitRecoveryReport_reset.action';
	document.getElementById('paraFrm').submit();
	
 }

function callReport(type){		
		document.getElementById('paraFrm_reportType').value=type;
 		//var rep = document.getElementById('paraFrm_reportType').value;
			if(document.getElementById('paraFrm_month').value=="0"){
			alert("Please select the "+document.getElementById('month').innerHTML.toLowerCase());
			return false;
			}
			
			if(document.getElementById('paraFrm_debitHead').value==""){
			alert("Please select the "+document.getElementById('debit.name').innerHTML.toLowerCase());
			return false;
			}
			
			/*if(rep=='0'){
	 		alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	 		return false;
			}*/
			 
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="DebitRecoveryReport_report.action";
			document.getElementById('paraFrm').submit();
			
							
}

function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
}
getYear();	
</script>
