<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="EmpConfForPension" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Pension Calculation </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1" >
				<tr>
					<td width="78%"><input type="button" value=" Save" class="save" onclick="return validateSave();"/> <input type="button" value=" Recalculate" class="token" onclick="return reCalculate();"/> <input type="button" value=" Close" class="cancel" onclick="javascript:window.close();"/></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
			
			<tr>
						<td width="90%" colspan="4"><strong>Employee Details</strong></td>
			</tr>
			<tr>
				<td width="25%"><label  class = "set"  id="employee"  name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> : 
				</td>
				<td width="25%"><s:textfield size="25" theme="simple" readonly="true" name='empNamePC'></s:textfield>  
				</td>
				
				<td width="25%"></label> 
				</td>
				<td width="25%">  
				</td>
				
			</tr>
			<tr>
				<td width="25%"><label  class = "set"  id="dateOfRetirement"  name="dateOfRetirement" ondblclick="callShowDiv(this);"><%=label.get("dateOfRetirement")%></label> : 
				</td>
				<td width="25%"><s:textfield size="25" theme="simple" readonly="true" name='dateOfRetirementPC'></s:textfield>  
				</td>
				
				<td width="25%"><label  class = "set"  id="QualfYearsService"  name="QualfYearsService" ondblclick="callShowDiv(this);"><%=label.get("QualfYearsService")%></label> : 
				</td>
				<td width="25%"><s:textfield theme="simple" size="25" readonly="true" name='qualfYearsServicePC'></s:textfield>  
				</td>
				
			</tr>
			
			<tr>
				
			</tr>
			
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
						<td width="90%" colspan="3"><strong>Emolument Details </strong></td>
				</tr>
			
			<tr>
			<td colspan="6">
				<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="20%"><label class = "set" name="totEmolument" id="totEmolument" ondblclick="callShowDiv(this);"><%=label.get("totEmolument")%></label> :</td>
					<td width="15%"><s:textfield theme="simple" name='totEmolument'  onkeypress=" return numbersOnly()" onkeyup="return calcAvgEmol()" onblur="return calcAvgEmol()" maxlength="15" /></td>
					<td width="20%"><label class = "set" name="avgEmolument" id="avgEmolument" ondblclick="callShowDiv(this);"><%=label.get("avgEmolument")%></label> :</td>
					<td width="15%"><s:textfield theme="simple" name='avgEmolumentPC' readonly="true"/></td>
					<td width="20%"><label class = "set" name="pensionAmt" id="pensionAmt" ondblclick="callShowDiv(this);"><%=label.get("pensionAmt")%></label> :</td>
					<td width="15%"><s:textfield theme="simple" name='pensionAmtPCDet' readonly="true"/> </td>
			</tr>
			</table></td></tr>
			<tr>
					<td width="5%" class="formth"><label  class = "set" name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
					<td width="20%" class="formth"><label  class = "set" name="creditHead" id="creditHead" ondblclick="callShowDiv(this);"><%=label.get("creditHead")%></label></td>
					<td width="34%" class="formth"><label  class = "set" name="creditAmt" id="creditAmt" ondblclick="callShowDiv(this);"><%=label.get("creditAmt")%></label></td>
			</tr>
			<% int j = 0;%>
				<s:iterator value="creditList">
					<tr>
						<td class="sortableTD"  width="5%"><%=++j %></td>
						<td class="sortableTD" width="20%" nowrap="nowrap"><s:property value="creditHead"/><s:hidden name="creditHead" id='<%="creditHead"+j%>'/><s:hidden name="creditHeadCode" id='<%="creditHeadCode"+j%>'/></td>
						<td class="sortableTD" width="34%" align="left" nowrap="nowrap"><s:textfield theme="simple" name="creditHeadAmt" onkeypress="return numbersOnly();"  id='<%="creditHeadAmt"+j%>' onkeyup="return calcPensionAmt();" maxlength="15"/></td>
					</tr>
				</s:iterator>
				<!--  <tr>
					<td width="5%" class="sortableTD">&nbsp;</td>
					<td width="20%" class="sortableTD"><b><label  class = "set" name="avgEmolument" id="avgEmolument" ondblclick="callShowDiv(this);"><%//=//label.get("avgEmolument")%></label>(<s:property value='avgEmolFormula'/>)</b><s:hidden name='avgEmolFormulaHidden'/></td>
					<td width="34%" class="sortableTD"><input name='avgEmolumentAmt' type="text" style="text-align: right" id='avgEmolumentAmt' onkeypress="return numbersWithDot();" value='<s:property value='avgEmolumentPC'/>' ></td>
				</tr>-->
				<tr>
					<td width="5%" class="sortableTD">&nbsp;</td>
					<td width="20%" class="sortableTD"><b><label  class = "set" name="grossPensionAmt" id="grossPensionAmt" ondblclick="callShowDiv(this);"><%=label.get("grossPensionAmt")%></label></b><s:hidden name='pensionFormula'/><s:hidden name='pensionFormulaHidden'/></td>
					<td width="34%" class="sortableTD"><input name='pensionAmtText' type="text" readonly="readonly"  id='pensionAmtText' onkeypress="return numbersWithDot();" value='<s:property value='pensionAmtPCList'/>' ><!-- <label  class = "set" name="grossPensionAmt" id="grossPensionAmt1" ondblclick="callShowDiv(this);"><%=label.get("grossPensionAmt")%></label>=<label  class = "set" name="pensionAmtNote" id="pensionAmtNote" ondblclick="callShowDiv(this);"><%=label.get("pensionAmtNote")%></label> --></td>
				</tr>
				<input type="hidden" name='count1' id='count1' value='<%=j%>'/>
				
				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
			<tr>
						<td width="90%" colspan="4"><strong>Commutation Details :</strong></td>
			</tr>
			<tr>
				<td width="25%"><label  class = "set"  id="commutationAmt"  name="commutationAmt" ondblclick="callShowDiv(this);"><%=label.get("commutationAmt")%></label> : 
				</td>
				<td width="25%"><s:textfield size="25" theme="simple" name='commutationAmtPC' onkeyup="return clearCommtDet();" onkeypress="return numbersOnly();" ></s:textfield>  
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>
			
			<tr>
				<td width="25%"><label  class = "set"  id="CommEffectFrom"  name="CommEffectFrom" ondblclick="callShowDiv(this);"><%=label.get("CommEffectFrom")%></label> : 
				</td>
				<td width="25%" nowrap="nowrap"><s:select theme="simple" name="commEffectFromMonth" cssStyle="width:100"
							list="#{'':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							<s:textfield theme="simple" size="5" name='commEffectFromYear' onkeypress="return numbersOnly();" maxlength="4"></s:textfield>  
				</td>
				
				<td width="25%"><label  class = "set"  id="CommEffectTo"  name="CommEffectTo" ondblclick="callShowDiv(this);"><%=label.get("CommEffectTo")%></label> : 
				</td>
				<td width="25%" nowrap="nowrap"><s:select theme="simple" name="commEffectToMonth" cssStyle="width:100"
							list="#{'':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							<s:textfield theme="simple" size="5" name='commEffectToYear' onkeypress="return numbersOnly();" maxlength="4"></s:textfield>  
				</td>
				
			</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
			<tr>
						<td width="90%" colspan="4"><strong>Recovery Details :</strong></td>
			</tr>
			<tr>
				<td width="25%"><label  class = "set"  id="recoveryHead"  name="recoveryHead" ondblclick="callShowDiv(this);"><%=label.get("recoveryHead")%></label> : 
				</td>
				<td width="25%" nowrap="nowrap"><s:textfield size="25" theme="simple" name='recoveryHead' readonly="true"></s:textfield>
				<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9recoveryHead.action'); "><s:hidden name='recoveryHeadCode'/>
				</td>
				<td width="25%"><label  class = "set"  id="recoveryAmt"  name="recoveryAmt"  ondblclick="callShowDiv(this);"><%=label.get("recoveryAmt")%></label> :</td>
				<td width="25%"><s:textfield size="25" theme="simple" name='recoveryAmt' onkeypress="return numbersWithDot();" maxlength="10"></s:textfield></td>
			</tr>
			
			<tr>
				<td width="25%"  nowrap="nowrap"><label  class = "set"  id="recoveryUpto"  name="recoveryUpto" ondblclick="callShowDiv(this);"><%=label.get("recoveryUpto")%></label> : 
				</td>
				<td width="25%" nowrap="nowrap"><s:select theme="simple" name="recoveryMonth" cssStyle="width:100"
							list="#{'':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							<s:textfield theme="simple" size="5" onkeypress="return numbersOnly();" name='recoveryYear' maxlength="4"></s:textfield>  
				</td>
				
				<td width="25%"><label  class = "set"  id="recoveryCmnts"  name="recoveryCmnts" ondblclick="callShowDiv(this);"><%=label.get("recoveryCmnts")%></label> :</td>
				<td width="25%" nowrap="nowrap"><s:textarea name='recoveryComments' cols="25" theme="simple" rows="2"/></td>
				
			</tr>
			<tr>
				<td colspan="4" width="100%" align="center"><input type="button" value=" Add Recovery" class="add" onclick="return callAddRecovery();"/>
				</td>
			</tr>
			</table>
			</td>
		</tr>
		<% int i = 0;%>
		<s:if test="recoveryFlag">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
						<td width="90%" colspan="4"><strong>Recovery List </strong></td>
			</tr>
			<tr>
					
					<td width="5%" class="formth"><label  class = "set" name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
					<td width="20%" class="formth"><label  class = "set" name="recoveryHead" id="recoveryHead1" ondblclick="callShowDiv(this);"><%=label.get("recoveryHead")%></label></td>
					<td width="34%" class="formth"><label  class = "set" name="recoveryAmt" id="recoveryAmt1" ondblclick="callShowDiv(this);"><%=label.get("recoveryAmt")%></label></td>
					<td width="11%" class="formth"><label  class = "set" name="recoveryUpto" id="recoveryUpto1" ondblclick="callShowDiv(this);"><%=label.get("recoveryUpto")%></label></td>
					<td width="11%" class="formth"><label  class = "set" name="recoveryCmnts" id="recoveryCmnts1" ondblclick="callShowDiv(this);"><%=label.get("recoveryCmnts")%></label></td>
					<td width="11%" class="formth"><label  class = "set" name="edit/remove" id="edit/remove" ondblclick="callShowDiv(this);"><%=label.get("edit/remove")%></label></td>
			</tr>
			
				<s:iterator value="recoveryList">
					<tr>
						<td class="sortableTD"  width="5%"><%=++i %></td>
						<td class="sortableTD" width="20%" nowrap="nowrap"><s:property value="recoveryHeadList"/><s:hidden name="recoveryHeadList" id='<%="recoveryHeadList"+i%>'/><s:hidden name="recoveryHeadCodeList" id='<%="recoveryHeadCodeList"+i%>'/></td>
						<td class="sortableTD" width="34%" align="left" nowrap="nowrap"><s:property value="recoveryAmtList"/><s:hidden name="recoveryAmtList" id='<%="recoveryAmtList"+i%>'/></td>
						<td class="sortableTD" width="11%" align="left" nowrap="nowrap"><s:property value="recoveryMonthList"/>-<s:property value="recoveryYearList"/><s:hidden name="recoveryMonthList" id='<%="recoveryMonthList"+i%>' /><s:hidden name="recoveryYearList" id='<%="recoveryYearList"+i%>'/></td>
						<td class="sortableTD" width="34%" align="left" nowrap="nowrap"><s:property value="recoveryCommentsList"/>&nbsp;<s:hidden name="recoveryCommentsList" id='<%="recoveryCommentsList"+i%>'/></td>
						<td align="center" width="25%" class="sortableTD">
								<input type="button" class="rowEdit" title="Edit Record" onclick="callForEdit(<%=i%>)"/> 
								<input type="button" class="rowDelete" title="Remove Record" onclick="callForRemove(<%=i%>)"/></td>
					
					</tr>
				</s:iterator>
				<input type="hidden" name='count' id='count' value='<%=i%>'/>
				</table>
			</td>
		</tr>
		</s:if>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1" >
				<tr>
					<td width="78%"><input type="button" value=" Save" class="save" onclick="return validateSave();"/> <input type="button" value=" Recalculate" class="token" onclick="return reCalculate();"/> <input type="button" value=" Close" class="cancel" onclick="javascript:window.close();"/></td>
					<td width="22%">
					<div align="right"><span class="style2"></span></div>
					</td>
				</tr>
			
			</td>
		</tr>
		<s:hidden name="paraId"/>
		<s:hidden name='empCodePCDet'/>
		<s:hidden name="pensionTypePC"/>
		<s:hidden name="maxYearsServicePC"/>
		<s:hidden name='avgEmolFormula'/>
		<s:hidden name='pensionAmtPCList'/>
		<s:hidden name="emolMonths"/>
		
	</table>
</s:form>
<script type="text/javascript">
function validateSave(){
	/*var fields=["paraFrm_dateOfRetirementPC","paraFrm_qualfYearsServicePC","paraFrm_avgEmolumentPC","paraFrm_pensionAmtPCDet","paraFrm_commutationAmtPC",
			"paraFrm_commEffectFromMonth","paraFrm_commEffectFromYear","paraFrm_commEffectToMonth","paraFrm_commEffectToYear"];
	var labels=["dateOfRetirement","QualfYearsService","avgEmolument","pensionAmt","commutationAmt","CommEffectFrom","CommEffectFrom","CommEffectTo","CommEffectTo"];
	var flag=["select","enter","enter","enter","enter","select","enter","select","enter"];*/
	
	document.getElementById('paraFrm_pensionAmtPCList').value=document.getElementById('pensionAmtText').value;
	
	var fields=["paraFrm_qualfYearsServicePC","paraFrm_avgEmolumentPC","paraFrm_pensionAmtPCDet","paraFrm_pensionAmtPCList"];
	var labels=["QualfYearsService","avgEmolument","pensionAmt","grossPensionAmt"];
	var flag=["enter","enter","enter","enter"];
	
	var fieldsComm=["paraFrm_commEffectFromMonth","paraFrm_commEffectFromYear","paraFrm_commEffectToMonth","paraFrm_commEffectToYear"];
	var lablesComm=["CommEffectFrom","CommEffectFrom","CommEffectTo","CommEffectTo"];
	var flagComm=["select","enter","select","enter"];
	//if(!validateBlank(fields, labels, flag)){
				//return false;			  
			//}
	if(document.getElementById('paraFrm_commutationAmtPC').value!=""){
	
		if(!validateBlank(fieldsComm, lablesComm, flagComm)){
				return false;			  
			}
		if(!checkYear("paraFrm_commEffectFromYear","CommEffectFrom")){
		return false;
		
		}
		if(!checkYear("paraFrm_commEffectToYear","CommEffectTo")){
		return false;
		
		}
	}
	
		var countCredit=document.getElementById('count1').value;
		for(var k=1;k<=eval(countCredit);k++){
			if(document.getElementById('creditHeadAmt'+k).value==""){
				alert("Please enter "+document.getElementById('creditAmt').innerHTML);
				document.getElementById('creditHeadAmt'+k).focus();
				return false;
			}
		}
		var dateOfRetirementPC=document.getElementById('paraFrm_dateOfRetirementPC').value;
		var qualfYearsServicePC=document.getElementById('paraFrm_qualfYearsServicePC').value;
		var avgEmolumentPC=document.getElementById('paraFrm_avgEmolumentPC').value;
		var pensionAmtPCDet=document.getElementById('paraFrm_pensionAmtPCDet').value;
		var pensionAmtPCList=document.getElementById('paraFrm_pensionAmtPCList').value;
		var commutationAmtPC=document.getElementById('paraFrm_commutationAmtPC').value;
		var commEffectFromMonth=document.getElementById('paraFrm_commEffectFromMonth').value;
		var commEffectFromYear=document.getElementById('paraFrm_commEffectFromYear').value;
		var commEffectToMonth=document.getElementById('paraFrm_commEffectToMonth').value;
		var commEffectToYear=document.getElementById('paraFrm_commEffectToYear').value;
		
		
		var recoveryHeadCodeList=new Array();
		var recoveryAmt=new Array();
		var recoveryMonthList=new Array();
		var recoveryYearList=new Array();
		var recoveryCommentsList=new Array();
		try{
		var count=document.getElementById('count').value;
		for(var i=0;i<eval(count);i++){
			recoveryHeadCodeList[i]=document.getElementById("recoveryHeadCodeList"+(i+1)).value;
			recoveryAmt[i]=document.getElementById("recoveryAmtList"+(i+1)).value;
			recoveryMonthList[i]=getMonthNumber(document.getElementById("recoveryMonthList"+(i+1)).value);
			recoveryYearList[i]=document.getElementById("recoveryYearList"+(i+1)).value;
			recoveryCommentsList[i]=document.getElementById("recoveryCommentsList"+(i+1)).value;
		
		}
		}catch(e){}
		
		
		var creditHeadCodeList=new Array();
		var creditAmt=new Array();
		try{
		var creditCount=document.getElementById('count1').value;
		for(var i=0;i<eval(creditCount);i++){
			creditHeadCodeList[i]=document.getElementById("creditHeadCode"+(i+1)).value;
			creditAmt[i]=document.getElementById("creditHeadAmt"+(i+1)).value;
		
		}
		}catch(e){}
		if(eval(commutationAmtPC)>eval(pensionAmtPCDet) ){
			alert(document.getElementById("commutationAmt").innerHTML+" should be less than "+document.getElementById("pensionAmt").innerHTML);
				document.getElementById('paraFrm_commutationAmtPC').focus();
				return false;
		}
		if(commEffectFromYear==commEffectToYear){
			if(eval(commEffectFromMonth)>eval(commEffectToMonth)){
				alert(document.getElementById("CommEffectFrom").innerHTML+" should be less than "+document.getElementById("CommEffectTo").innerHTML);
				document.getElementById('paraFrm_commEffectToMonth').focus();
				return false;
			}
		}else if(eval(commEffectFromYear)>eval(commEffectToYear)){
				alert(document.getElementById("CommEffectFrom").innerHTML+" should be less than "+document.getElementById("CommEffectTo").innerHTML);
				document.getElementById('paraFrm_commEffectToYear').focus();
				return false;
			
		}
		var conf=confirm("Do you really want to save this record ?");
  			if(conf) {
		opener.document.getElementById('paraFrm').target="main";
		opener.document.getElementById('paraFrm').action="EmpConfForPension_updatePensionDet.action?dateOfRetirementPC="+dateOfRetirementPC+"&qualfYearsServicePC="+qualfYearsServicePC+"&pensionAmtPCDet="+pensionAmtPCDet+"&avgEmolumentPC="+avgEmolumentPC+"&pensionAmtPCList="+pensionAmtPCList+
							"&commutationAmtPC="+commutationAmtPC+"&commEffectFromMonth="+commEffectFromMonth+"&commEffectFromYear="+commEffectFromYear+"&commEffectToMonth="+commEffectToMonth+"&commEffectToYear="+commEffectToYear+"&recoveryHeadCodeList="+recoveryHeadCodeList+"&recoveryAmt="+recoveryAmt+
							"&recoveryMonthList="+recoveryMonthList+"&recoveryYearList="+recoveryYearList+"&recoveryCommentsList="+recoveryCommentsList+"&creditHeadCodeList="+creditHeadCodeList+"&creditAmt="+creditAmt;						
		opener.document.getElementById('paraFrm').submit();
		window.close();
		}
	
}
function callAddRecovery(){
	var fieldsRec=["paraFrm_recoveryHead","paraFrm_recoveryAmt","paraFrm_recoveryMonth","paraFrm_recoveryYear"];
	var labelsRec=["recoveryHead","recoveryAmt","recoveryUpto","recoveryUpto"];
	var flagRec=["select","enter","select","enter"];
	
	
	if(!validateBlank(fieldsRec, labelsRec, flagRec)){
				return false;			  
			}
			
	if(!checkYear("paraFrm_recoveryYear","recoveryUpto")){
		return false;
	}
	var dateOfRet=document.getElementById('paraFrm_dateOfRetirementPC').value;
	var month=document.getElementById("paraFrm_recoveryMonth").value;
	var dedDate="";
	if(eval(month)<10){
		dedDate="01-0"+month+"-"+document.getElementById("paraFrm_recoveryYear").value;
	}else{
		dedDate="01-"+month+"-"+document.getElementById("paraFrm_recoveryYear").value;
	}
	if(!dateDifference(dateOfRet, dedDate, 'paraFrm_recoveryMonth', 'dateOfRetirement', 'recoveryUpto'))
	{
	return false;
	}
	if(!checkRecoveryHead()){
		return false;
	}
	if(!checkRecoveryAmt()){
		return false;
	}
	
	document.getElementById('paraFrm').target="";
	document.getElementById('paraFrm').action="EmpConfForPension_addRecovery.action";
	document.getElementById('paraFrm').submit();
}
function clearCommtDet(){
	if(document.getElementById('paraFrm_commutationAmtPC').value==""){
	
		var commFields=["paraFrm_commEffectFromMonth","paraFrm_commEffectFromYear","paraFrm_commEffectToMonth","paraFrm_commEffectToYear"];
		for(var i=0;i<eval(commFields.length);i++){
			document.getElementById(commFields[i]).value="";
		}
	}
}
function checkRecoveryAmt(){
		var recovMonth=document.getElementById("paraFrm_recoveryMonth").value;
		var recovYear=document.getElementById("paraFrm_recoveryYear").value;
		var pensionAmt=eval(document.getElementById("pensionAmtText").value*1000000000000000/1000000000000000);
		var recoveryAmt=eval(document.getElementById("paraFrm_recoveryAmt").value*1000000000000000/1000000000000000);
		try{
		var count =document.getElementById("count").value;
		var paraId=document.getElementById("paraFrm_paraId").value;
		for(var i=1;i<=eval(count);i++){
			if(recovMonth==getMonthNumber(document.getElementById("recoveryMonthList"+i).value) &&
				recovYear==document.getElementById("recoveryYearList"+i).value && paraId!=i ){
				recoveryAmt= eval(eval(recoveryAmt)+eval(document.getElementById("recoveryAmtList"+i).value))*1000000000000000/1000000000000000;
			}
		}
		}catch(e){}
		//alert("recoveryAmt="+recoveryAmt);
		//alert("pensionAmt="+pensionAmt);
		if(eval(recoveryAmt)>eval(pensionAmt)){
			var conf=confirm(document.getElementById("recoveryAmt").innerHTML+" for "+getmonthString(recovMonth)+"-"+recovYear+" is greater than "+document.getElementById('pensionAmt').innerHTML
			+" \nDo you really want to continue ?");
  			if(conf) {
  				return true;
		}else return false;
		}
		return true;
}

function checkRecoveryHead(){
		var recovMonth=document.getElementById("paraFrm_recoveryMonth").value;
		var recovYear=document.getElementById("paraFrm_recoveryYear").value;
		var recovHead=document.getElementById("paraFrm_recoveryHeadCode").value;
		var recovHeadName=document.getElementById("paraFrm_recoveryHead").value;
		try{
		var count =document.getElementById("count").value;
		var paraId=document.getElementById("paraFrm_paraId").value;
		for(var i=1;i<=eval(count);i++){
			var listMonth=getMonthNumber(document.getElementById("recoveryMonthList"+i).value);
			var listYear=document.getElementById("recoveryYearList"+i).value;
			var listHead=document.getElementById("recoveryHeadCodeList"+i).value;
			if((recovMonth==listMonth) && (recovYear==listYear) && (paraId!=i) && (recovHead==listHead) ){
				alert(document.getElementById("recoveryHead").innerHTML+" '"+recovHeadName+"' is already added for "+getmonthString(recovMonth)+"-"+recovYear);
				document.getElementById("paraFrm_recoveryHead").focus();
				return false;
			}
		}
		}catch(e){}
		return true;
		
}
function callForRemove(id){
	var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
		document.getElementById("paraFrm_paraId").value=id;
		document.getElementById('paraFrm').target="";
		document.getElementById('paraFrm').action="EmpConfForPension_removeRecovery.action";
		document.getElementById('paraFrm').submit();
	    }
		
}

function callForEdit(rowId){
	document.getElementById("paraFrm_paraId").value=rowId;
	document.getElementById("paraFrm_recoveryHead").value=document.getElementById('recoveryHeadList'+rowId).value;
	document.getElementById("paraFrm_recoveryHeadCode").value=document.getElementById('recoveryHeadCodeList'+rowId).value;
	document.getElementById("paraFrm_recoveryAmt").value=document.getElementById('recoveryAmtList'+rowId).value;
	document.getElementById("paraFrm_recoveryMonth").value=getMonthNumber(document.getElementById('recoveryMonthList'+rowId).value);
	document.getElementById("paraFrm_recoveryYear").value=document.getElementById('recoveryYearList'+rowId).value;
	document.getElementById("paraFrm_recoveryComments").value=document.getElementById('recoveryCommentsList'+rowId).value;
}

/*function calcPensionAmt(){
	var splitFormula=(document.getElementById('paraFrm_avgEmolFormula').value).split("#");
	var count=document.getElementById('count1').value;
	for(var i=0;i<splitFormula.length;i++){
		splitFormula[i]=splitFormula[i].replace("C","");
		}
	for(var i=0;i<splitFormula.length;i++){
		for(var k=1;k<=eval(count);k++){
			if(splitFormula[i]==document.getElementById('creditHeadCode'+k).value){
				splitFormula[i]=document.getElementById('creditHeadAmt'+k).value;
				i++;
			}
		}
	}
	
	var avgEmoulment1="";
	for(var j=0;j<splitFormula.length;j++){
		avgEmoulment1+= splitFormula[j];
	}
	document.getElementById('avgEmolumentAmt').value=eval(avgEmoulment1);
	var pensionAmt=document.getElementById('pensionAmtText').value;
	var avgEmoulment=document.getElementById('avgEmolumentAmt').value;
	var yearsOfService=document.getElementById('paraFrm_qualfYearsServicePC').value;
	var maxYearsOfService=document.getElementById('paraFrm_maxYearsServicePC').value;
	
	if(eval(yearsOfService)>eval(maxYearsOfService)){
		yearsOfService = maxYearsOfService;
	}
	pensionAmt= formatNumber((eval(avgEmoulment)*0.5*eval(yearsOfService))/(eval(maxYearsOfService)));
	document.getElementById('pensionAmtText').value=pensionAmt;
}
*/
function reCalculate(){
	
		if(document.getElementById('paraFrm_avgEmolumentPC').value==""){
			alert("Please enter "+document.getElementById('avgEmolument').innerHTML);
			document.getElementById('paraFrm_avgEmolumentPC').focus();
			return false;
		}
		var conf=confirm("Do you really want to recalculate this record ?");
  			if(conf) {
		document.getElementById('paraFrm').target="";
		document.getElementById('paraFrm').action="EmpConfForPension_reCalculatePension.action";
		document.getElementById('paraFrm').submit();
		}

}
function calcAvgEmol(){
	var emolMonth=eval(document.getElementById('paraFrm_emolMonths').value);
	var avgEmolument = eval(document.getElementById('paraFrm_totEmolument').value/emolMonth);
	if(isNaN(avgEmolument)){
	avgEmolument=0;
	}
	document.getElementById('paraFrm_avgEmolumentPC').value=Math.round(eval(avgEmolument*1000000000000000)/1000000000000000);
}
function calcPensionAmt(){
		var pensionAmt=eval(document.getElementById('pensionAmtText').value*1000000000000000/1000000000000000);
		var count=document.getElementById('count1').value;
		var creditAmt=0;
		for(var k=1;k<=eval(count);k++){
		if(isNaN(eval(document.getElementById('creditHeadAmt'+k).value))){
		
			creditAmt+=eval(0);
			}
			else{
			//alert("list="+eval(document.getElementById('creditHeadAmt'+k).value *1000000000000000/1000000000000000));
			creditAmt+= eval(document.getElementById('creditHeadAmt'+k).value *1000000000000000/1000000000000000);
			}
		}
		if(isNaN(creditAmt)){
		creditAmt=0;
		}
		document.getElementById('pensionAmtText').value=Math.ceil(eval(creditAmt*1000000000000000/1000000000000000));
		
}
function getMonthNumber(monthString){
	if(monthString=="Jan"){
		return 1;
	}	
	else if(monthString=="Feb"){
		return 2;	
	}
	else if(monthString=="March"){
		return 3;	
	}
	else if(monthString=="Apr"){
		return 4;	
	}
	else if(monthString=="May"){
		return 5;	
	}
	else if(monthString=="June"){
		return 6;	
	}
	else if(monthString=="July"){
		return 7;	
	}
	else if(monthString=="Aug"){
		return 8;	
	}
	else if(monthString=="Sep"){
		return 9;	
	}
	else if(monthString=="Oct"){
		return 10;	
	}
	else if(monthString=="Nov"){
		return 11;	
	}
	else if(monthString=="Dec"){
		return 12;	
	}
}

function getmonthString(mon) {
	switch(eval(mon)) {
	case 1: return "Jan";
	case 2: return "Feb";
	case 3: return "March";
	case 4: return "Apr";
	case 5: return "May";
	case 6: return "June";
	case 7: return "July";
	case 8: return "Aug";
	case 9: return "Sep";
	case 10: return "Oct";
	case 11: return "Nov";
	case 12: return "Dec";
	default : return "Jan";
	}
}



</script>
