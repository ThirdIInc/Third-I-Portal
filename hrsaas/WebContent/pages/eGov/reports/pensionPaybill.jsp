<%@include file="/pages/common/labelManagement.jsp" %>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PensionPaybilleGov" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Pension
					 Paybill </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td><s:if test="%{viewFlag}">
						<input type="button" class="token" onclick="return check();"
							value=" Report " />
					</s:if> <input type="button" class="token" onclick="return reset()"
						value=" Reset " /></td>
					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
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
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<!--  <tr>
							<td  width="20%"><label id="brn.order" name="brn.order" ondblclick="callShowDiv(this);"><%//=label.get("brn.order")%></label> :
							<input type="checkbox"
								name="chkBrnOrder" id="chkBrnOrder" onclick="callCheck();" /></td>
							<td  width="20%"><label id="dept.order" name="dept.order" ondblclick="callShowDiv(this);"><%//=label.get("dept.order")%></label> :
							<input type="checkbox"
								name="chkDeptOrder" id="chkDeptOrder" onclick="callCheck();" /></td>
							<td  width="20%"><label id="desg.order" name="desg.order" ondblclick="callShowDiv(this);"><%//=label.get("desg.order")%></label> :
							<input type="checkbox"
								name="chkDesgOrder" id="chkDesgOrder" onclick="callCheck();" /></td>		
						</tr>-->
						
						<input type="hidden" name='chkBrnOrder' id='chkBrnOrder'/>
						<input type="hidden" name='chkDeptOrder' id='chkDeptOrder'/>
						<input type="hidden" name='chkDesgOrder' id='chkDesgOrder'/>
						
						<tr>

							<td colspan="1" width="20%"><label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="month" cssStyle="width:150"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>

							<td colspan="1" width="20%"><label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:</td>
							<td><s:textfield name="year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="divCode" />
							<s:textfield name="divName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PensionPaybilleGov_f9div.action');">

							</td>
							<td width="20%"><label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
							</td>
							<td width="30%"><s:hidden name="branchCode"></s:hidden><s:textfield name="branchName" readonly="true" size="30" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PensionPaybilleGov_f9Branch.action');">
							</td>
							
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="recordperpage" name="recordperpage" ondblclick="callShowDiv(this);"><%=label.get("recordperpage")%></label>:</td>
							<td colspan="1" width="30%"><s:select theme="simple" name="rowNumber"
									cssStyle="width:150"		list="#{'15':'10','5':'5','4':'4','3':'3'}" />
							</td>

							<td colspan="1" width="20%"><label id="columnumber" name="columnumber" ondblclick="callShowDiv(this);"><%=label.get("columnumber")%></label>:</td>
							<td colspan="1" width="30%"><s:select theme="simple" name="columnNumber"
								 	cssStyle="width:150"		list="#{'10':'10','9':'9','8':'8'}" /></td>
						</tr>
						
						 <tr>
							<!--<td colspan="1" width="20%"><label id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="report" cssStyle="width:150"
								list="#{'Pdf':'Pdf'}" /></td>< -->
							<td colspan="1" width="20%"><label id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label> :</td>
							<td><s:select theme="simple" name="onHold"
								cssStyle="width:150" list="#{'A':'All','N':'No','Y':'Yes'}" />
							</td>
						</tr>
						<!--<tr>
							<td colspan="1" width="20%"><label id="cons.arrears" name="cons.arrears" ondblclick="callShowDiv(this);"><%//=label.get("cons.arrears")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkFlag" id="checkFlag" onclick="callCheck();" /></td>
							<td colspan="1" width="20%"><label id="paybillFor" name="paybillFor" ondblclick="callShowDiv(this);"><%//=label.get("paybillFor")%></label>:</td>
							
							 <td colspan="1" width="30%">
							<s:select theme="simple"
								name="paybillFor" cssStyle="width:150"
								list="#{'AL':'All','SA':'Salary','AR':'Arrears'}" />
							</td>
						</tr>-->
						
						<s:hidden name="report" value="Pdf"></s:hidden>
						<s:hidden name="paybillFor"></s:hidden>
						<input type="hidden" name="checkFlag" id="checkFlag"/>
						<tr>
							<td colspan="3">
							<CENTER></CENTER>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td><s:if test="%{viewFlag}">
						<input type="button" class="token" onclick="return check();"
							value=" Report " />
					</s:if> <input type="button" class="token" onclick="return reset()"
						value=" Reset " /></td>
				
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>



<script>
 function callCheck(){
 
 var chkBrn=document.getElementById('chkBrnOrder').checked;
 var chkDept=document.getElementById('chkDeptOrder').checked;
 var chkDesg=document.getElementById('chkDesgOrder').checked;
 var chkCons=document.getElementById('checkFlag').checked;
 
 if(chkBrn){
 			document.getElementById('chkBrnOrder').value="Y"; 	
 	}
 	if(chkDept){
 			document.getElementById('chkDeptOrder').value="Y"; 	
 	}
 	if(chkDesg){
 			document.getElementById('chkDesgOrder').value="Y"; 	
 	}
 	if(chkCons){
 			document.getElementById('checkFlag').value="Y"; 	
 	}

 }
function check()
 {	
	 var month  =document.getElementById("paraFrm_month").value;
	 var yea   =document.getElementById("paraFrm_year").value;
	var rep     =document.getElementById("paraFrm_report").value;
	var divNm   =document.getElementById("paraFrm_divName").value;
	var mont=document.getElementById('month').innerHTML.toLowerCase();
	var yer=document.getElementById('year').innerHTML.toLowerCase();
	var div=document.getElementById('division').innerHTML.toLowerCase();
	//var report=document.getElementById('report.type').innerHTML.toLowerCase();
	var rows = document.getElementById("paraFrm_rowNumber").value;
	var cols = document.getElementById("paraFrm_columnNumber").value;
	var rowsNm = document.getElementById('recordperpage').innerHTML.toLowerCase();
	var colsNm = document.getElementById('columnumber').innerHTML.toLowerCase();
	 if(month =='0'){
	 	alert("Please select "+mont);
	 	return false;
	 }
	 if(yea ==''){
	 	alert("Please enter "+yer);
	 	return false;
	 }
	 if(!checkYear('paraFrm_year','year')){
	 	return false;	 
	 }
	 if(divNm==""){
	 	alert("Please select the "+div);
	 	return false;
	 
	 }
	 if(rows =='0'){
	 	alert("Please select the "+rowsNm);
	 	return false
	 }
	  if(cols=='0'){
	 	alert("Please select the "+colsNm);
	 	return false
	 }
	
	 if(rep=='0'){
	 	alert("Please select the "+report);
	 	return false;
	}
	 	
	 
	document.getElementById('paraFrm').target="_blank";	
	document.getElementById('paraFrm').action="PensionPaybilleGov_getpageWiseSal.action";	
	document.getElementById('paraFrm').submit();	
	document.getElementById('paraFrm').target="main";
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

function reset(){
	document.getElementById("paraFrm_month").value="0";
	document.getElementById("paraFrm_year").value="";
	document.getElementById("paraFrm_report").value="0";
	document.getElementById("paraFrm_rowNumber").value='0';
	document.getElementById("paraFrm_columnNumber").value='0';
	getYear();
}
--></script>

