<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"  %>
<script type="text/javascript"
	src="../pages/common/include/javascript/payrollAjax.js"></script>
<s:form action="PFInterestCal" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%" cellpadding="2"
		cellspacing="1">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">PF Interest Calculation</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2">
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
				<td width="78%">
				<s:if test="%{insertFlag}">
  						<input type="button" class="add" theme="simple"  value="  Save" onclick="return formValidate()" />
  						</s:if>
  				<!--<s:if test="%{deleteFlag}">
  						<input type="button" class="delete" theme="simple"  value="  Delete" onclick="return formValidate('save',this)" />
  						</s:if>-->
  				<input type="button" class="cancel" theme="simple"  value="  Close" onclick="javascript:window.close();" />
  						
				</td>   
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set" id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							: <font color="red">*</font></td>
							<td colspan="3" width="80%">
							
							<s:textfield theme="simple" readonly="true" name="empToken" size="10"/><s:hidden name='empId'/>
							<s:textfield theme="simple" readonly="true" size="40" name="empName" /><s:hidden name='empStatus'/>
							</td>
						</tr>
						
						
						<tr>
							<td colspan="4" align="center">
							<s:hidden name='calcFlag'/>
							<s:hidden name='intRate'/>
							<s:hidden name='intAmount'/>
							<s:hidden name='totDeposite'/>
							<s:hidden name='totWithdraw'/>
							<s:hidden name='closingBalance'/>
							<s:hidden name='fromMonth'/>
							<s:hidden name='fromYear'/>
							<s:hidden name='toMonth'/>
							<s:hidden name='toYear'/>
							</td>
						</tr>
						
							
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="calcFlag">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="1" >
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"><label class="set" id="pfSubDet" name="pfSubDet" ondblclick="callShowDiv(this);"><%=label.get("pfSubDet")%></label></strong></td>
						</tr>
						
						<tr>
							<td colspan="7" width="90%" align="right"><b><label class="set" id="opBalance" name="opBalance" ondblclick="callShowDiv(this);"><%=label.get("opBalance")%></label> :</b> </td>
							<td colspan="1" width="10%" align="center"><s:property value='openingBalance'/><s:hidden name='openingBalance'/></td>
						</tr>
							
						<tr>
							
							<td width='5%' class="formth" align="center"><!--Sr No.  -->
								<label  class = "set" name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>
							</td>
							<td width='15%' class="formth" align="center">
								<label  class = "set" name="monthName" id="monthName" ondblclick="callShowDiv(this);"><%=label.get("monthName")%></label>
							</td>
					        <td width='13%' class="formth" align="center">
					        	<label  class = "set" name="pfSub" id="pfSub" ondblclick="callShowDiv(this);"><%=label.get("pfSub")%></label>
					        </td>
					        <td width='13%' class="formth" align="center">
								<label  class = "set" name="pfRefund" id="pfRefund" ondblclick="callShowDiv(this);"><%=label.get("pfRefund")%></label>
							</td>
					        <td width='13%' class="formth" align="center">
					        	<label  class = "set" name="mplContr" id="mplContr" ondblclick="callShowDiv(this);"><%=label.get("mplContr")%></label>
					        </td>
					         <td width='13%' class="formth" align="center">
								<label  class = "set" name="pfLoan" id="pfLoan" ondblclick="callShowDiv(this);"><%=label.get("pfLoan")%></label>
							</td>
					        <td width='13%' class="formth" align="center">
					        	<label  class = "set" name="pfRepay" id="pfRepay" ondblclick="callShowDiv(this);"><%=label.get("pfRepay")%></label>
					        </td>
					        <td width='15%' class="formth" align="center">
					        	<label  class = "set" name="pfProg" id="pfProg" ondblclick="callShowDiv(this);"><%=label.get("pfProg")%></label>
					        </td>
					     </tr>
					     <%int i=1; int k=0;%>
							<s:iterator value="monthList">
								<%k=i; %>
								<tr>
									<td width="5%" align="center"><%=k%></td>
									<td width="15%" align="left"><s:property value="listMonthName" /><s:hidden name='listMonthName' id='<%="listMonthName"+k%>'/></td>
									<td width="13%" align="right"><s:property value="listPfSub" /> <s:hidden name="listPfSub" /></td>
									<td width="13%" align="right"><s:property value="listPfRefund" /> <s:hidden name="listPfRefund" /></td>
									<td width="13%" align="right"><input id="<%="listPfMPCont"+k%>" name="listPfMPCont" style="text-align: right"  size="10" onkeypress="return numbersWithDot();" value="<s:property value='listPfMPCont'/>" /><s:hidden name="listPfMPCont" /></td>
									<td width="13%" align="right"><s:property value="listPfLoan" /> <s:hidden name="listPfLoan" /></td>
									<td width="13%" align="right"><input id="<%="listPfRepay"+k%>" name="listPfRepay" style="text-align: right"  size="10" onkeypress="return numbersWithDot();" value="<s:property value='listPfRepay'/>" /><s:hidden name="listPfRepay" /></td>
									<td width="15%" align="right"><s:property value="listPfProg"/><s:hidden name="listPfProg" /></td>
								</tr>
								<%k++;i++; %>
							</s:iterator>
							<%i=1; %>
							
              <input type="hidden" name='count' id='count' value="<%=k-1%>"/>
						</table>
				<s:if test="calcFlag">		
				<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" >
								<tr style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;">
									<td width="5%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse">&nbsp;</td>
									<td width="15%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse">&nbsp;</td>
									<td width="13%" align="right" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfSub" /></b> <s:hidden name="totPfSub" /></td>
									<td width="13%" align="right" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfRefund" /></b> <s:hidden name="totPfRefund" /></td>
									<td width="13%" align="right" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfMPCont" /></b> <s:hidden name="totPfMPCont" /></td>
									<td width="13%" align="right" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfLoan" /></b> <s:hidden name="totPfLoan" /></td>
									<td width="13%" align="right" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfRepay" /></b><s:hidden name="totPfRepay" /></td>
									<td width="15%" align="right" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfProg" /></b><s:hidden name="totPfProg" /><s:hidden name="totPfProgActual" /></td>
								</tr>
							
           
						</table></s:if>
					</td>
				</tr>
			</table>
	
	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="1" >
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"><label class="set" id="intCalculation" name="intCalculation" ondblclick="callShowDiv(this);"><%=label.get("intCalculation")%></label></strong></td>
						</tr>
						
						<tr>
							<td width='2%'>
							<td colspan="2" width="70%" align="left"><b><label class="set" id="intAmount" name="intAmount" ondblclick="callShowDiv(this);"><%=label.get("intAmount")%></label> :</b>
							 [( <label class="set" id="opBalance2" name="opBalance" ondblclick="callShowDiv(this);"><%=label.get("opBalance")%></label>+<label class="set" id="totProg" name="totProg" ondblclick="callShowDiv(this);"><%=label.get("totProg")%></label>)*(
							 <label class="set" id="intRate" name="intRate" ondblclick="callShowDiv(this);"><%=label.get("intRate")%></label>/100) ]</td>
							<td colspan="1" width="20%" align="left">[(<s:property value='openingBalance'/>+<s:property value="totPfProgActual" />)*(<s:property value='intRate'/>/100)]</td>
							<td colspan="1" width="10%" align="center"><input type="text" value='<s:property value='intAmount'/>' style="text-align: right" size='12' readonly="true"/></td>
						</tr>
						
						<tr>
							<td width='2%'>
							<td colspan="2" width="70%" align="left"><b><label class="set" id="totDeposite" name="totDeposite" ondblclick="callShowDiv(this);"><%=label.get("totDeposite")%></label> :</b>
							 ( Total <label class="set" id="pfSub1" name="pfSub" ondblclick="callShowDiv(this);"><%=label.get("pfSub")%></label>
							 + Total <label class="set" id="pfRefund1" name="pfRefund" ondblclick="callShowDiv(this);"><%=label.get("pfRefund")%></label>
							 + Total <label class="set" id="mplContr1" name="mplContr" ondblclick="callShowDiv(this);"><%=label.get("mplContr")%></label>)
							 </td>
							<td colspan="1" width="20%" align="left">(<s:property value="totPfSub"/>+<s:property value="totPfRefund"/>+<s:property value="totPfMPCont"/>)</td>
							<td colspan="1" width="10%" align="center"><input type="text" style="text-align: right" value='<s:property value='totDeposite'/>' size='12' readonly="true"/></td>
						</tr>
						
						<tr>
							<td width='2%'>
							<td colspan="2" width="70%" align="left"><b><label class="set" id="totWithdraw" name="totWithdraw" ondblclick="callShowDiv(this);"><%=label.get("totWithdraw")%></label> :</b>
							 (Total <label class="set" id="pfLoan1" name="pfLoan" ondblclick="callShowDiv(this);"><%=label.get("pfLoan")%></label>)</td>
							<td colspan="1" width="20%" align="left">(<s:property value='totPfLoan'/>)</td>
							<td colspan="1" width="10%" align="center"><input type="text"style="text-align: right" value='<s:property value='totWithdraw'/>'  size='12' readonly="true"/></td>
						</tr>
						
						<tr>
							<td width='2%'>
							<td colspan="2" width="70%" align="left"><b><label class="set" id="cloBalance" name="cloBalance" ondblclick="callShowDiv(this);"><%=label.get("cloBalance")%></label> :</b>
							 (<label class="set" id="opBalance1" name="opBalance" ondblclick="callShowDiv(this);"><%=label.get("opBalance")%></label>
							 + <label class="set" id="totDeposite1" name="totDeposite" ondblclick="callShowDiv(this);"><%=label.get("totDeposite")%></label>
							 + <label class="set" id="intAmount1" name="intAmount" ondblclick="callShowDiv(this);"><%=label.get("intAmount")%></label> 
							 - <label class="set" id="totWithdraw1" name="totWithdraw" ondblclick="callShowDiv(this);"><%=label.get("totWithdraw")%></label>) </td>
							<td colspan="1" width="20%" align="left">(<s:property value='openingBalance'/>+<s:property value="totDeposite"/>+<s:property value="intAmount"/>-<s:property value="totWithdraw"/>)</td>
							<td colspan="1" width="10%" align="center"><input type="text" style="text-align: right" value='<s:property value='closingBalance'/>'  size='12' readonly="true"/></td>
						</tr>
							
						
						</table>
						
				
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:if>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
				<td width="78%">
				<s:if test="%{insertFlag}">
  						<input type="button" class="add" theme="simple"  value="  Save" onclick="return formValidate()" />
  						</s:if>
  				<!--<s:if test="%{deleteFlag}">
  						<input type="button" class="delete" theme="simple"  value="  Delete" onclick="return formValidate('save',this)" />
  						</s:if>-->
  				<input type="button" class="cancel" theme="simple"  value="  Close" onclick="javascript:window.close();" />
  						
				</td>   
				</tr>
			</table>
			</td>
		</tr>
		</table></td></tr>
		</table>
		</s:form>



<script type="text/javascript">
		var fields =["paraFrm_empName","paraFrm_fromYear","paraFrm_toMonth"];
		var labels =["employee","dateFrm","dateTo"];
		var types =["select","enter","select"];
function callRecalculate(){
		
		if(!validateBlank(fields,labels, types)){
			return false;
			}
		document.getElementById('paraFrm_calcFlag').value='true';
		document.getElementById('paraFrm').action='PFInterestCal_openEmpCalcWindow.action';
		document.getElementById('paraFrm').submit();
}

function setToYear(){
	var toMonth = document.getElementById('paraFrm_toMonth').value;
	var fromYear = document.getElementById('paraFrm_fromYear').value;
	if(fromYear !=""){
	if(eval(toMonth)<4){
		document.getElementById('paraFrm_toYear').value=eval(fromYear)+1;
	}else if(eval(toMonth)>=4){
		document.getElementById('paraFrm_toYear').value=eval(fromYear);
	}else{
		document.getElementById('paraFrm_toYear').value='';
	}
	}else{
		document.getElementById('paraFrm_toYear').value='';
	}
}
function formValidate(){
	if(!validateBlank(fields,labels, types)){
			return false;
			}
		if(document.getElementById('paraFrm_calcFlag').value=='false'){
			alert("Please calculate the interest first");
			document.getElementById('view').focus();
			return false;
		}
		var opnBalance=document.getElementById('paraFrm_openingBalance').value;
		var closBalance=document.getElementById('paraFrm_closingBalance').value;
		var intAmount=document.getElementById('paraFrm_intAmount').value;
		var deposit=document.getElementById('paraFrm_totDeposite').value;
		var withdraw=document.getElementById('paraFrm_totWithdraw').value;
		var empId=document.getElementById('paraFrm_empId').value;
		
		opener.document.getElementById('paraFrm').target="main";
		opener.document.getElementById('paraFrm').action="PFInterestCal_reCalculate.action?openingBalance="+opnBalance+"&closingBalance="+closBalance+"&intAmount="+intAmount+"&totDeposite="+deposit+"&totWithdraw="+withdraw+"&empId="+empId;
		opener.document.getElementById('paraFrm').submit();
		window.close();
	}
	function callReset(){
		document.getElementById('paraFrm').action='PFInterestCal_reset.action';
		document.getElementById('paraFrm').submit();
	}
</script>
