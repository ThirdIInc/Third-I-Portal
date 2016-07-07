<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TaxInv" id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Tax Configuration </strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%">
					<tr>
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="20%">
							<div align="right"><font color="red">*</font> Indicates Required</div>
						</td>
					</tr>
			</table>
		</tr>
		<!-- Hiding this block as per new scope changes -->
		<!-- 
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
			<s:if test='%{pullInvestmentFlag}'>	
				<tr>
					<td width="20%"><label class="set" id="frmYr1"
							name="frmYr1" ondblclick="callShowDiv(this);"><%=label.get("frmYr")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="fromYear" size="24" onkeypress="return numbersOnly()" onblur="add()" maxlength="4"/></td>
					<td width="20%"><label class="set" id="toYr1"
							name="toYr" ondblclick="callShowDiv(this);"><%=label.get("toYr")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="toYear" size="24" onkeypress="return numbersOnly()" maxlength="4"/></td>
				</tr>
			
				<tr>
					<td colspan="4" align="center">
						<input type="button" class="token"  onclick=" return pullInvestment()" value="Import from previous financial year"/>
					</td>
				</tr>
				</s:if>
				<s:else>
					<tr>
					<td width="20%"><label class="set" id="frmYr1"
							name="frmYr1" ondblclick="callShowDiv(this);"><%=label.get("frmYr")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:hidden name="fromYear"/> <s:property value="fromYear"/></td>
					<td width="20%"><label class="set" id="toYr1"
							name="toYr" ondblclick="callShowDiv(this);"><%=label.get("toYr")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:hidden name="toYear"/> <s:property value="toYear"/></td>
				</tr>
				</s:else>
			</table>
		</tr>-->
		<tr>
			<td colspan="4">
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="20%"><label class="set" id="invName1"
							name="invName" ondblclick="callShowDiv(this);"><%=label.get("invName")%></label>:<font color="red">*</font></td>
						<td width="80%" colspan="3">
							<s:hidden name="investmentCode"/>
							<s:textfield name="investmentName" size="100"/>
						</td>
					</tr>
					<tr>
						<td width="20%"><label class="set" id="invChapter1"
							name="invChapter" ondblclick="callShowDiv(this);"><%=label.get("invChapter")%></label>:<font color="red">*</font></td>
						<td width="25%">
						<s:select name="investmentChapter" cssStyle="width:160" headerKey="" headerValue="-- Select --"
								list="#{'EXEMPT':'EXEMPT','OTHER':'OTHER','PARA':'PARA','VI-A':'VI-A','VI':'VI'}" />
						</td>
						<td width="20%">
						<label class="set" id="invSection1"	name="invSection" ondblclick="callShowDiv(this);"><%=label.get("invSection")%></label>:<font color="red">*</font></td>
						<td width="25%">
						<s:select name="investmentSection" cssStyle="width:150" onchange="return showCheckBox();" headerKey="" headerValue="-- Select --"
								list="#{'10(10)':'10(10)','10(14)':'10(14)','10(13A)':'10(13A)','10(14)':'10(14)','10(5)':'10(5)','10(10AA)':'10(10AA)','17(2)':'17(2)','27':'27','80C':'80C','80D':'80D','80DD':'80DD','80DDB':'80DDB','80E':'80E','80G':'80G','80GG':'80GG','80U':'80U','80CCC':'80CCC','80CCF':'80CCF','80CCG':'80CCG','89':'89'}" />
						</td>
					</tr>
					<tr>
						<td width="20%"><label class="set" id="invGroup1"
							name="invGroup" ondblclick="callShowDiv(this);"><%=label.get("invGroup")%></label>:<font color="red">*</font></td>
						<td width="25%">
							<s:select name="investmentGroup" cssStyle="width:160" headerKey="" headerValue="-- Select --"
								list="#{'E':'Exemption under 10-17','D':'Deductions under chapter VI-A','S':'Deductions under chapter VI(Sec 80C)','O':'Other Income'}" onchange="checkSelection();"/>
						</td>
						
						<td width="45%" colspan="2" ></td>
					</tr>
					<tr>
						<td width="20%" colspan="" id="otherTd" style="display:none"><label class="set" id="invAdditive"
							name="invAdditive"><%=label.get("invAdditive")%></label>:
						</td>
							
						<td width="25%" id="otherTdCheck" style="display:none">	 
							<s:checkbox name="isAdditive"/>
						</td>
							
						<td width="20%" colspan="" id="checkboxId" style="display:none">
							<label class="set"
								id="taxation.Invstincludein80c"
								name="taxation.Invstincludein80c"
								ondblclick="callShowDiv(this);"><%=label.get("taxation.Invstincludein80c")%></label>: 
						</td>
							
						<td width="25%" id="checkboxIdCheck" style="display:none">
							<s:checkbox name="invIncludeCheck" />
						</td>
					</tr>
					<tr>
						<td width="20%"><label class="set" id="invType1"
							name="invType" ondblclick="callShowDiv(this);"><%=label.get("invType")%></label>:<font color="red">*</font></td>
						<td width="25%">
							<s:select name="investmentType" cssStyle="width:160" headerKey="" headerValue="-- Select --"
								list="#{'I':'Investment','N':'Non Investment'}" />
						</td>
						<td width="20%"><label class="set" id="invLimit1" name="invLimit" ondblclick="callShowDiv(this);"><%=label.get("invLimit")%></label> :</td>
						<td width="25%">
						<s:textfield name="investmentLimit" size="24" onkeypress="return numbersOnly()"/>
						</td>
					</tr>
					<!-- 
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="token"  onclick=" return addInvestment()" value="  Add   "/>
						</td>
					</tr>
					 -->
				</table>
			</td>
		</tr>
		<!-- DISPLAYED ONLY IF INVESTMENTS ARE DEFINED -->
		<s:if test='%{investmentFlag}'>	
			<!-- ################ INVESTMENT BLOCKS -->
			<s:if test='%{exemptionFlag}'>	
				<tr>
					<td colspan="5">
						<table width="100%" class="formbg" border="0" id="exemptionTable">
							<tr>
								<td colspan="5"><strong>
									Exemption Under 10 & 17</strong>
								</td>
							</tr>
							<tr>
								<td class="formth">
									<label class="set" id="invName2"
									name="invName" ondblclick="callShowDiv(this);"><%=label.get("invName")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invChapter2"
									name="invChapter" ondblclick="callShowDiv(this);"><%=label.get("invChapter")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invSection2"
									name="invSection" ondblclick="callShowDiv(this);"><%=label.get("invSection")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invType2"
									name="invType" ondblclick="callShowDiv(this);"><%=label.get("invType")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invLimit2"
									name="invLimit" ondblclick="callShowDiv(this);"><%=label.get("invLimit")%></label>
								</td>
								<td class="formth">
									Edit | Delete</label>
								</td>
							</tr>
							<% int srCounter=1; %>
							<s:iterator value="investmentExemptList">
								<tr>
									<td class="sortableTD" align="left">
									<input	type="hidden" id="paraFrm_investmentCodeExemptItt<%=srCounter%>" name="investmentCodeExemptItt"
									value='<s:property value="investmentCodeExemptItt"/>'/> 
										<s:property value="investmentNameExemptItt"/></td>
									<td class="sortableTD" align="left">
										<s:property value="investmentChapterExemptItt"/></td>
									<td class="sortableTD" align="left">
										<s:property value="investmentSectionExemptItt"/></td>
									<td class="sortableTD" align="left">
										<s:property value="investmentTypeNameExemptItt"/></td>
									<td class="sortableTD" align="center">
										<s:property value="investmentLimitExemptItt"/></td>
									<td align="center" class="sortableTD">
										<input type="button" class="rowEdit" onclick="editCurrentRow('<s:property value="investmentCodeExemptItt"/>', '<s:property value="investmentNameExemptItt"/>', '<s:property value="investmentLimitExemptItt"/>', '<s:property value="investmentChapterExemptItt"/>', '<s:property value="investmentSectionExemptItt"/>', '<s:property value="investmentGroupExemptItt"/>', '<s:property value="investmentTypeNameExemptItt"/>','N', '<s:property value="invIncludeCheck"/>');" />
										<input type="button" class="rowDelete" 	onclick="deleteCurrentRow('<s:property value="investmentCodeExemptItt"/>');" />
									</td>
								</tr>
								<%srCounter++; %>
							</s:iterator>
						</table>
					</td>
				</tr>
				</s:if>
				<s:if test='%{deductionFlag}'>	
				<tr>
					<td colspan="5">
						<table width="100%" class="formbg" border="0" id="deductionTable">
							<tr>
								<td colspan="5"><strong>
									Deduction Under VI - A</strong>
								</td>
							</tr>
							<tr>
								<td class="formth">
									<label class="set" id="invName3"
									name="invName" ondblclick="callShowDiv(this);"><%=label.get("invName")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invChapter3"
									name="invChapter" ondblclick="callShowDiv(this);"><%=label.get("invChapter")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invSection3"
									name="invSection" ondblclick="callShowDiv(this);"><%=label.get("invSection")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invType3"
									name="invType" ondblclick="callShowDiv(this);"><%=label.get("invType")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invLimit3"
									name="invLimit" ondblclick="callShowDiv(this);"><%=label.get("invLimit")%></label>
								</td>
								<td class="formth">
									Edit | Delete</label>
								</td>
							</tr>
							<% int srCounter1=1; %>
							<s:iterator value="investmentDeductionList">
								<tr>
									<td class="sortableTD" align="left">
									<input	type="hidden" id="paraFrm_investmentCodeDeductionItt<%=srCounter1%>" name="investmentCodeDeductionItt"
									value='<s:property value="investmentCodeDeductionItt"/>' /> 
									<s:property value="investmentNameDeductionItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentChapterDeductionItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentSectionDeductionItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentTypeNameDeductionItt"/></td>
									<td class="sortableTD" align="center">
									<s:property value="investmentLimitDeductionItt"/></td>
									<td align="center" class="sortableTD">
										<input type="button" class="rowEdit" onclick="editCurrentRow('<s:property value="investmentCodeDeductionItt"/>', '<s:property value="investmentNameDeductionItt"/>', '<s:property value="investmentLimitDeductionItt"/>', '<s:property value="investmentChapterDeductionItt"/>', '<s:property value="investmentSectionDeductionItt"/>', '<s:property value="investmentGroupDeductionItt"/>', '<s:property value="investmentTypeNameDeductionItt"/>','N', '<s:property value="invIncludeCheck"/>');"/>
										<input type="button" class="rowDelete" 	onclick="deleteCurrentRow('<s:property value="investmentCodeDeductionItt"/>');"/>
									</td>
								</tr>
								<%srCounter1++; %>
							</s:iterator>	
						</table>
					</td>
				</tr>
				</s:if>
				<s:if test='%{sectionFlag}'>	
				<tr>
					<td colspan="5">
						<table width="100%" class="formbg" border="0" id="sectionTable">
							<tr>
								<td colspan="5"><strong>
									Deduction Under VI (Sec 80C)</strong>
								</td>
							</tr>
							<tr>
								<td class="formth">
									<label class="set" id="invName4"
									name="invName" ondblclick="callShowDiv(this);"><%=label.get("invName")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invChapter4"
									name="invChapter" ondblclick="callShowDiv(this);"><%=label.get("invChapter")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invSection4"
									name="invSection" ondblclick="callShowDiv(this);"><%=label.get("invSection")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invType4"
									name="invType" ondblclick="callShowDiv(this);"><%=label.get("invType")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invLimit4"
									name="invLimit" ondblclick="callShowDiv(this);"><%=label.get("invLimit")%></label>
								</td>
								<td class="formth">
									Edit | Delete</label>
								</td>
							</tr>
							<% int srCounter2=1; %>
							<s:iterator value="investmentSectionList">
								<tr>
									<td class="sortableTD" align="left">
									<input	type="hidden" id="paraFrm_investmentCodeSectionItt<%=srCounter2%>" name="investmentCodeSectionItt"
									value='<s:property value="investmentCodeSectionItt"/>' /> 
									<s:property value="investmentNameSectionItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentChapterSectionItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentSectionSectionItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentTypeNameSectionItt"/></td>
									<td class="sortableTD" align="center">
									<s:property value="investmentLimitSectionItt"/></td>
									<td align="center" class="sortableTD">
										<input type="button" class="rowEdit" onclick="editCurrentRow('<s:property value="investmentCodeSectionItt"/>', '<s:property value="investmentNameSectionItt"/>', '<s:property value="investmentLimitSectionItt"/>', '<s:property value="investmentChapterSectionItt"/>', '<s:property value="investmentSectionSectionItt"/>', '<s:property value="investmentGroupSectionItt"/>', '<s:property value="investmentTypeNameSectionItt"/>','N', '<s:property value="invIncludeCheck"/>');"/>
										<input type="button" class="rowDelete" 	onclick="deleteCurrentRow('<s:property value="investmentCodeSectionItt"/>');"/>
									</td>
								</tr>
								<%srCounter2++; %>
							</s:iterator>		
						</table>
					</td>
				</tr>
				</s:if>
				<s:if test='%{otherFlag}'>	
				<tr>
					<td colspan="5">
						<table width="100%" class="formbg" border="0" id="otherTable">
							<tr>
								<td colspan="5"><strong>
									Other Income</strong>
								</td>
							</tr>
							<tr>
								<td class="formth">
									<label class="set" id="invName5"
									name="invName" ondblclick="callShowDiv(this);"><%=label.get("invName")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invChapter5"
									name="invChapter" ondblclick="callShowDiv(this);"><%=label.get("invChapter")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invSection5"
									name="invSection" ondblclick="callShowDiv(this);"><%=label.get("invSection")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invType5"
									name="invType" ondblclick="callShowDiv(this);"><%=label.get("invType")%></label>
								</td>
								<td class="formth">
									<label class="set" id="addDed"
									name="addDed" ondblclick="callShowDiv(this);"><%=label.get("addDed")%></label>
								</td>
								<td class="formth">
									<label class="set" id="invLimit5"
									name="invLimit" ondblclick="callShowDiv(this);"><%=label.get("invLimit")%></label>
								</td>
								<td class="formth">
									Edit | Delete</label>
								</td>
							</tr>
							<% int srCounter3=1; %>
							<s:iterator value="investmentOtherList">
								<tr>
									<td class="sortableTD" align="left">
									<input	type="hidden" id="paraFrm_investmentCodeOtherItt<%=srCounter3%>" name="investmentCodeOtherItt"
									value='<s:property value="investmentCodeOtherItt"/>' /> 
									<s:property value="investmentNameOtherItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentChapterOtherItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentSectionOtherItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentTypeNameOtherItt"/></td>
									<td class="sortableTD" align="left">
									<s:property value="investmentIsAdditiveItt"/></td>
									<td class="sortableTD" align="center">
									<s:property value="investmentLimitOtherItt"/></td>
									<td align="center" class="sortableTD">
										<input type="button" class="rowEdit" onclick="editCurrentRow('<s:property value="investmentCodeOtherItt"/>', '<s:property value="investmentNameOtherItt"/>', '<s:property value="investmentLimitOtherItt"/>', '<s:property value="investmentChapterOtherItt"/>', '<s:property value="investmentSectionOtherItt"/>', '<s:property value="investmentGroupOtherItt"/>', '<s:property value="investmentTypeNameOtherItt"/>','<s:property value="investmentIsAdditiveItt"/>', '<s:property value="invIncludeCheck"/>');"/>
										<input type="button" class="rowDelete" 	onclick="deleteCurrentRow('<s:property value="investmentCodeOtherItt"/>');"/>
									</td>
								</tr>
								<%srCounter3++; %>
							</s:iterator>			
						</table>
					</td>
				</tr>
				</s:if>
				</s:if><s:else>
				<tr>
					<td colspan="5" align="center">
						<table width="100%" class="formbg" border="0" >
							<tr>
								<td><font color="red">Tax configurations not yet defined</font></td>
							</tr>
						</table>
					</td>
				</tr>
			</s:else>
				<tr>
					<td width="100%" colspan="3"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
	</table>
</s:form>

<script>
	function addFun(){
		if(!chkBlankFields()){
			return false;
		}else{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="TaxInv_addInvestments.action";
			document.getElementById('paraFrm').submit();
		}
	}
	function resetFun(){
		document.getElementById('paraFrm_investmentCode').value = "";
		document.getElementById('paraFrm_investmentName').value = "";
		document.getElementById('paraFrm_investmentLimit').value = "";
		document.getElementById('paraFrm_investmentChapter').value = "";
		document.getElementById('paraFrm_investmentSection').value = "";
		document.getElementById('paraFrm_investmentGroup').value = "";
		document.getElementById('paraFrm_investmentType').value = "";
	}
	function backFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="TaxInv_back.action";
		document.getElementById('paraFrm').submit();
	}
	function reportFun(){
		/*var frmYr = document.getElementById('paraFrm_fromYear').value;
		var toYr = document.getElementById('paraFrm_toYear').value;
		
		if(frmYr==''){
			alert("Please enter from year");
			return false;
		}
		if(toYr==''){
			alert("Please enter to year");
			return false;
		}*/
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="TaxInv_report.action";
		document.getElementById('paraFrm').submit();
	}
	
	function addInvestment(){
		if(!chkBlankFields()){
			return false;
		}else{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="TaxInv_addInvestments.action";
			//document.getElementById('paraFrm').submit();
		}
	}
	function viewInvestment(){
		var frmYr = document.getElementById('paraFrm_fromYear').value;
		var toYr = document.getElementById('paraFrm_toYear').value;
		
		if(frmYr==''){
			alert("Please enter from year");
			return false;
		}
		if(toYr==''){
			alert("Please enter to year");
			return false;
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="TaxInv_viewInvestments.action";
		document.getElementById('paraFrm').submit();
	}
	
	function pullInvestment(){
		var frmYr = document.getElementById('paraFrm_fromYear').value;
		var toYr = document.getElementById('paraFrm_toYear').value;
		
		if(frmYr==''){
			alert("Please enter from year");
			return false;
		}
		if(!checkYear('paraFrm_fromYear', 'frmYr1')){
			return false;
		}
		if(toYr==''){
			alert("Please enter to year");
			return false;
		}
		if(!checkYear('paraFrm_toYear', 'toYr1')){
			return false;
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="TaxInv_pullInvestments.action";
		document.getElementById('paraFrm').submit();
	}
	function chkBlankFields(){
		//var frmYr = document.getElementById('paraFrm_fromYear').value;
		//var toYr = document.getElementById('paraFrm_toYear').value;
		var investName = document.getElementById('paraFrm_investmentName').value;
		var investChapter = document.getElementById('paraFrm_investmentChapter').value;
		var investSection = document.getElementById('paraFrm_investmentSection').value;
		var investGroup = document.getElementById('paraFrm_investmentGroup').value;
		//var investisAdditive = document.getElementById('paraFrm_isAdditive').value;
		var investType = document.getElementById('paraFrm_investmentType').value;
		//var investLimit = document.getElementById('paraFrm_investmentLimit').value;
		
		/*if(frmYr==''){
			alert("Please enter from year");
			return false;
		}
		if(toYr==''){
			alert("Please enter to year");
			return false;
		}*/
		if(investName==''){
			alert("Please enter to investment name");
			return false;
		}
		if(investChapter==''){
			alert("Please select to investment chapter");
			return false;
		}
		if(investSection==''){
			alert("Please select to investment section");
			return false;
		}
		if(investGroup==''){
			alert("Please select to investment group");
			return false;
		}
		if(investType==''){
			alert("Please select to investment type");
			return false;
		}
		/*if(investLimit==''){
			alert("Please enter to investment limit");
			return false;
		}*/
		return true;
	}
	
	function deleteCurrentRow(investId){
		var conf=confirm("Are you sure you want to delete this record ?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="TaxInv_deleteInvestment.action?investmentCode="+investId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function editCurrentRow(investId, investName, investLimit, investChapter, investSection, invesGroup, invesType, additiveDeductive, invIncludeCheck){
		
		document.getElementById('paraFrm_investmentCode').value = investId;
		document.getElementById('paraFrm_investmentName').value = investName;
		document.getElementById('paraFrm_investmentLimit').value = trim(investLimit);
		document.getElementById('paraFrm_investmentChapter').value = investChapter;
		document.getElementById('paraFrm_investmentSection').value = investSection;
		document.getElementById('paraFrm_investmentGroup').value = invesGroup;
		
		if(investSection=="80C"){
			
			document.getElementById('checkboxId').style.display="";
			document.getElementById('checkboxIdCheck').style.display="";			
			if(invIncludeCheck=="true"){
				document.getElementById('paraFrm_invIncludeCheck').checked = true;
			}else{
				document.getElementById('paraFrm_invIncludeCheck').checked = false;
			}
		}else{			
			document.getElementById('checkboxId').style.display="none";
			document.getElementById('checkboxIdCheck').style.display="none";			
			document.getElementById('paraFrm_invIncludeCheck').checked = false;
		}
		
		if(invesType=='Investment'){
			invesType = 'I';
		}else{
			invesType = 'N';
		}
		document.getElementById('paraFrm_investmentType').value = invesType;
		
		if(invesGroup=="O"){
			document.getElementById('otherTd').style.display="";
			document.getElementById('otherTdCheck').style.display="";
			
			if(additiveDeductive=='Additive'){
				document.getElementById('paraFrm_isAdditive').checked = true;
			}else{
				document.getElementById('paraFrm_isAdditive').checked = false;
			}
		}else{
			document.getElementById('otherTd').style.display="none";
			document.getElementById('otherTdCheck').style.display="none";
			document.getElementById('paraFrm_isAdditive').checked = false;
		}
	}
	
	function editCurrentRowOld(investId, investName, investLimit, investChapter, investSection, invesGroup, fromYr, toYr, invesType, additiveDeductive){
		
		document.getElementById('paraFrm_investmentCode').value = investId;
		document.getElementById('paraFrm_investmentName').value = investName;
		document.getElementById('paraFrm_investmentLimit').value = investLimit;
		document.getElementById('paraFrm_investmentChapter').value = investChapter;
		document.getElementById('paraFrm_investmentSection').value = investSection;
		document.getElementById('paraFrm_investmentGroup').value = invesGroup;
		if(invesType=='Investment'){
			invesType = 'I';
		}else{
			invesType = 'N';
		}
		document.getElementById('paraFrm_investmentType').value = invesType;
		document.getElementById('paraFrm_fromYear').value = fromYr;
		document.getElementById('paraFrm_toYear').value = toYr;
		
		if(invesGroup=="O"){
			document.getElementById('otherTd').style.display="";
			if(additiveDeductive=='Additive'){
				document.getElementById('paraFrm_isAdditive').checked = true;
			}else{
				document.getElementById('paraFrm_isAdditive').checked = false;
			}
		}else{
			document.getElementById('otherTd').style.display="none";
		}
	}
	function add() {
    	var from = document.getElementById('paraFrm_fromYear').value;
    	if(from==""){
    		document.getElementById('paraFrm_toYear').value="";
    	} else {
   	 		var x=eval(from) +1;
	  		document.getElementById('paraFrm_toYear').value=x;
	  	}
	}
	function checkSelection(){
		var selectedGroup = document.getElementById('paraFrm_investmentGroup').value;
		if(selectedGroup=="O"){
			document.getElementById('otherTd').style.display="";
			document.getElementById('otherTdCheck').style.display="";
			document.getElementById('paraFrm_isAdditive').checked = true;
		}else{
			document.getElementById('otherTd').style.display="none";
			document.getElementById('otherTdCheck').style.display="none";
			document.getElementById('paraFrm_isAdditive').checked = false;
		}
	}
	
	
	function showCheckBox(){
		var selectedGroup = document.getElementById('paraFrm_investmentSection').value;
		if(selectedGroup=="80C"){
			document.getElementById('checkboxId').style.display="";
			document.getElementById('checkboxIdCheck').style.display="";
		}else{
			document.getElementById('checkboxId').style.display="none";
			document.getElementById('checkboxIdCheck').style.display="none";
		}
	}
</script>

