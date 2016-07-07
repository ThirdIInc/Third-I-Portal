<%@include file="/pages/common/labelManagement.jsp" %>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="BranchWiseSal" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Branch Wise Salary Report
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
						<tr>

							<td colspan="1" width="20%"><label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="brnWiseSal.month" cssStyle="width:152"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>

							<td colspan="1" width="20%"><label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:</td>
							<td><s:textfield name="brnWiseSal.year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25"  /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="brnWiseSal.divCode" /><s:hidden name="brnWiseSal.divAdd" /><s:hidden name="brnWiseSal.divCity" />
							<s:textfield name="brnWiseSal.divName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'BranchWiseSal_f9div.action');">

							</td>

						</tr>
						<tr>

							<td colspan="1" width="20%"><label id="recordperpage" name="recordperpage" ondblclick="callShowDiv(this);"><%=label.get("recordperpage")%></label>:</td>
							<td colspan="1" width="30%"><s:select theme="simple" name="brnWiseSal.rowNumber"
									cssStyle="width:150"		list="#{'10':'10','9':'9','8':'8'}" />
							</td>

							<td colspan="1" width="20%"><label id="columnumber" name="columnumber" ondblclick="callShowDiv(this);"><%=label.get("columnumber")%></label>:</td>
							<td><s:select theme="simple" name="brnWiseSal.columnNumber"
								 	cssStyle="width:150"		list="#{'10':'10','9':'9','8':'8'}" /></td>
						</tr>

						<tr>
						<tr>
							<td colspan="1" width="20%"><label id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="brnWiseSal.report" cssStyle="width:152"
								list="#{'Pdf':'Pdf'}" /></td>
							<td colspan="1" width="20%"><label id="nhold" name="nhold" ondblclick="callShowDiv(this);"><%=label.get("nhold")%></label> :</td>
							<td><s:select theme="simple" name="onHold"
								cssStyle="width:152" list="#{'A':'All','N':'No','Y':'Yes'}" />
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="cons.arrears" name="cons.arrears" ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="consolidateCheck" id="consolidateCheck" onclick="callCheck();" /></td>
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
 
 var checkDefault = document.getElementById('consolidateCheck').checked;
 	if(checkDefault){
 			document.getElementById('consolidateCheck').value="Y";
 		}
 
 }
function check()
 {	
 
	 var month  =document.getElementById("paraFrm_brnWiseSal_month").value;
	 var yea   =document.getElementById("paraFrm_brnWiseSal_year").value;
	var rep     =document.getElementById("paraFrm_brnWiseSal_report").value;
	var divNm   =document.getElementById("paraFrm_brnWiseSal_divName").value;
	var mont=document.getElementById('month').innerHTML.toLowerCase();
	var yer=document.getElementById('year').innerHTML.toLowerCase();
	
	var div=document.getElementById('division').innerHTML.toLowerCase();
	var report=document.getElementById('report.type').innerHTML.toLowerCase();
	
	var rows = document.getElementById("paraFrm_brnWiseSal_rowNumber").value;
	var cols = document.getElementById("paraFrm_brnWiseSal_columnNumber").value;
	
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
	 if(!checkYear('paraFrm_brnWiseSal_year','year')){
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
	document.getElementById('paraFrm').action="BranchWiseSal_getBranchWiseSal.action";	
	document.getElementById('paraFrm').submit();	
	document.getElementById('paraFrm').target="main";
}


function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_brnWiseSal_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_brnWiseSal_year").value =year;
	  }
}

getYear();	

function reset(){
	document.getElementById("paraFrm_brnWiseSal_month").value="0";
	document.getElementById("paraFrm_brnWiseSal_year").value="";
	document.getElementById("paraFrm_brnWiseSal_report").value="0";
	document.getElementById("paraFrm_brnWiseSal_rowNumber").value='0';
	document.getElementById("paraFrm_brnWiseSal_columnNumber").value='0';
	getYear();
}
</script>

