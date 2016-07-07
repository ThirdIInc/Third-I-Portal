<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form	action="ApprRatingDefinition" id="paraFrm" validate="true"
	theme="simple" target="main">
<!-- Main Div Starts Here -->
<div id='div_main'
		style='position: absolute; z-index: 3; width: auto; min-width:620px; height:auto;  display: none; border: 1px solid; top: 200px; left: 200px; padding: 10px;'
		class="formbg">
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
		<tr>
			<td width="35%" colspan="3" align="center" class="formth" style="cursor:move" onmouseout="Drag.end();" 
			onmouseover="Drag.init(document.getElementById('div_main'), null, 0, 350, 0, 700);" >
				<b>Add Functions and Parameters</b>
			</td>
			<td width="3%" align="right" border="1" class="formth" style="font-family:Arial;cursor: pointer" onclick="hide_Div();">
				<b>X</b>
			</td>
		</tr>
	</table>
		<table width="100%" border="0" align="right">
		<tr>
		</tr>
		<tr>
		<td colspan="5" nowrap="nowrap">
			<div id='div_parameterId'
		style='position: relative;  min-width: 350px; width:auto; min-height:200px;  display: none; border: 0px solid; top: 10px; left: 0px; padding: 10px; float: left;'
		class="formbg" align="absmiddle">
	  	<table width="100%">
	  	
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
				<td   width="20%" colspan="1">
				  <label id="apprDivID" style="cursor: move" />
				  <b>
				  <div align="left">To Define Question Rating Formula Add Parameters. </div>
				  </b>
				  </td>
			  </tr>
			  
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0">
				<tr>
				<td   width="33%" colspan="1">&nbsp;</td>
				
    <td width="33%" colspan="1"><p align="left"> 
					  <input
						type="button" class="token" name="ratingGiven"
						value="Rating Given " onclick="setValueOfParameter(this);" />
					</p>
					  <p align="left">
					     <input
						type="button" class="token" name="maxQRating"
						value="Max Que Rating " onclick="setValueOfParameter(this);" />
					  </p>
					  <p align="left">
					     <input
						type="button" class="token" name="quesWeightage"
						value="Question Weightage  " onclick="setValueOfParameter(this);" />
			          </p></td>
					  <td width="33%" colspan="1">&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	</div>

	<!-- div for parameter ends here -->
		</td>

		<td colspan="5" nowrap="nowrap">
			<!-- div for Previous rating formula starts here -->
	
	<div id='div_previousApprId'
		style='position: relative;  min-width: 350px; width:auto; min-height:200px; height:auto;  display: none; border: 0px solid; top: 10px; left: 0px; padding: 10px; float: left;'
		class="formbg" align="absmiddle">
		
		<table width="100%">
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="93%" align="center">
					<b>
					<label id="moduleName" style="cursor: move" />To Add Previous Rating Formula. 
					</b></td>
					 
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td  width="30%" >Appraisal Code</td>
					<td width="70%" ><s:textfield name="preapprCode"
						value="%{preapprCode}" size="20" readonly="true" />
						
						<img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="15"
						onclick="javascript:callsF9(500,325,'ApprRatingDefinition_f9PrevSelectAppraisalCode.action');" />
						
						
					<s:hidden name="preapprId" /><s:hidden name="preapprcodeId" /></td> 
				</tr>
							<tr align="right">
			<td width="100%" align="right">
			<table width="100%" align="right">
			<tr>
					<td align="center"><input id="ctrlShow" type="button"
						value="Add" class="token" onclick="addPrevious(this);"></td>
			
			
					<td align="center"><input id="ctrlShow" type="button"
						value="Close" class="token" onclick="hide_Div();"></td>
			</tr>
			</table>
			</td>
		</tr>
			</table>
			</td>
		</tr>
	</table>	
	</div>
	
	
	<!-- div for phase rating formula starts here -->
		</td>
		<td colspan="5" nowrap="nowrap">
				<div id='div_parameterPhaseId'
		style='position: relative;  min-width: 350px; width:auto; min-height:200px; height:auto;  display: none; border: 0px solid; top: 10px; left: 0px; padding: 10px; float: left;'
		class="formbg" align="absmiddle">
  		 
  		  <table width="100%">
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="93%" align="center">
					<b>
					<label id="apprDivID" style="cursor: move" />Add Parameter To Define Phase Rating Formula 
					</b></td>
						
					</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
				<td   width="100%" colspan="3"><strong>Phase Wise Parameter</strong></td>
					 
				</tr>
				<tr>
				  <td colspan="1"><input
						type="button" class="token" name="sumofque"
						value="Sum Of Ques Rating" onclick="setValueOfParameter(this);" /></td>
				  <td colspan="1">  <input
						type="button" class="token" name="totalques"
						value="Total Questions" onclick="setValueOfParameter(this);" /></td>
				  <td colspan="1"> <input
						type="button" class="token" name="phaseQuesWeightage"
						value="Sum Of Que Weightage" onclick="setValueOfParameter(this);" />
			      </td>
			  </tr>
			  
				<tr>
				  <td colspan="3"><input
						type="button" class="token" name="phaseWeightage"
						value="Phase Weightage " onclick="setValueOfParameter(this);" /></td>
				 
			  </tr>
				<tr>
				  <td colspan="3"><strong>Section Wise Parameter </strong></td>
				 
			  </tr>
			  
			       			<%
			       					Object[][] sectionValues = (Object[][]) request.getAttribute("sectionValues");
									try {
									if (sectionValues!=null && sectionValues.length > 0) {
										for (int k = 0; k < sectionValues.length; k++) {
							%>
				<tr>
				  <td colspan="3"><input type="hidden" name="sectionId"
						id="sectionId<%=k%>" value="<%= String.valueOf(sectionValues[k][0]) %>" />
					<input type="hidden" name="sectionName" id="sectionName<%=k%>"
						value="<%= String.valueOf(sectionValues[k][1]) %>" /> 
						
						<strong><%=k+1%>)  <%= String.valueOf(sectionValues[k][1]) %></strong></td>
				 
			  </tr>
			  <tr>
				   <td colspan="1"><input
						type="button" class="token" name="sumofque"
						value="Sum Of Ques Rating" onclick="setValueOfParameter2(this,'<%=String.valueOf(sectionValues[k][1]) %>','<%=String.valueOf(sectionValues[k][2]) %>');" /></td>
				  <td colspan="1">  <input
						type="button" class="token" name="totalques"
						value="Total Questions" onclick="setValueOfParameter2(this,'<%=String.valueOf(sectionValues[k][1]) %>','<%=String.valueOf(sectionValues[k][2]) %>');" /></td>
				  <td colspan="1"> <input
						type="button" class="token" name="phaseQuesWeightage"
						value="Sum Of Que Weightage" onclick="setValueOfParameter2(this,'<%=String.valueOf(sectionValues[k][1]) %>','<%=String.valueOf(sectionValues[k][2]) %>');" />
			      </td>
			  </tr>
			    <%
									}
									}
								} catch (Exception e) {
									 e.printStackTrace();

								}
							%>
				 
			</table>
			</td>
		</tr>
	</table> 
 
 
		</div>
		
	
	<!-- div for phase rating formula ends here -->
		</td>
		<td colspan="5" nowrap="nowrap">
			<!-- div for Define Final Rating Formula  starts here -->
	
		<div id='div_finalRatingId'
		style='position: relative;   min-width: 350px; width:auto; min-height:200px; height:auto;  display: none; border: 0px solid; top: 10px; left: 0px; padding: 10px; float: left;'
		class="formbg" align="absmiddle">
	<table width="100%">
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="93%" align="center">
					<b><label id="apprDivID" style="cursor: move" />Add Parameter To Define Phase Rating Formula</b></td>
						
					</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
				<td   width="33%" colspan="1"></td>
					<td   width="33%" colspan="1">
			       					<%
			       					Object[][] data = (Object[][]) request.getAttribute("values");
									try {
									if (data!=null && data.length > 0) {
										for (int k = 0; k < data.length; k++) {
							%>
							<p><input
						type="button" class="token" name="buttonName"
						value="   <%=String.valueOf(data[k][1])%>   " onclick="setValueOfParameter1(this,'<%=String.valueOf(data[k][1]) %>','<%=String.valueOf(data[k][2]) %>');" />
					  </p>
					  <%
									}
									}
								} catch (Exception e) {
									 e.printStackTrace();

								}
							%>
							</td>
								<td   width="33%" colspan="1"></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	</div>

	<!-- div for Define Final Rating Formula  ends here -->
		</td>
		<td colspan="5" nowrap="nowrap">
				<!-- div for Configure with previous appraisal  starts here -->
	
	
	<div id='div_preAppraisalId'
		style='position: relative;  min-width: 350px; width:auto; min-height:200px; height:auto;   display: none; border: 0px solid; top: 10px; left: 0px; padding: 10px; float: left;'
		class="formbg" align="absmiddle">
	<table width="100%">
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="93%" align="center">
					<b><label id="preApprDivIDn" style="cursor: move" />Add Current Score To Configure With Previous Appraisal</b></td>
						
					</tr>
			</table>
			</td>
		</tr>
				<tr>
			<td width="100%">
			<table width="100%">
				<tr>
				     <td align="center"><input type="button" name="currentScore" value="Current Score" onclick="addCurrentScore();"/></td>
						</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
	<!-- div for Configure with previous appraisal ends here -->
		</td>
			<td nowrap="nowrap">
			<!-- div for number and function starts here -->
	<div id='div_functionId'
		style='position: relative; min-width: 200px; width:auto; height: auto; min-height:200px; display: none; border: 0px solid; top: 10px; left: 10px; padding: 10px; float:left;'
		class="formbg" align="absmiddle">
 	<table width="100%">
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="93%" align="center">
					<b>
					<label id="moduleName" />To Add Numbers And Functions. 
					</b></td>
					 
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn7" value=" 7 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn7" value=" 8 " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn8" value=" 9 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn9" value=" / " onclick="setValue(this);" /></td>
				</tr>
				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn4" value=" 4 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn5" value=" 5 " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn6" value=" 6 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn*" value=" * " onclick="setValue(this);" /></td>
				</tr>
				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn1" value=" 1 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn2" value=" 2 " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn3" value=" 3 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn-" value=" - " onclick="setValue(this);" /></td>
				</tr>
				<tr>

					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn0" value=" 0 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn(" value=" ( " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn)" value=" ) "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn+" value=" + " onclick="setValue(this);" /></td>
					<!-- <input
						type="button" class="token" name="btn." value=" . "
						onclick="setValue(this);" /> -->

				</tr>

				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn." value=" . "
						onclick="setValue(this);" /></td>
				</tr>
			</table>
			</td>
		</tr>
			<tr align="right">
			<td width="100%" align="right">
			<table width="100%" align="right">
			<tr>
					<td align="center"><input id="ctrlShow" type="button"
						value="Close" class="token" onclick="hide_Div();"></td>
			</tr>
			</table>
			</td>
		</tr>
	</table>	
	</div>
			</td>
		</tr>
	</table>
</div>
<!-- Main Div Ends here -->
<!-- Main Table Starts -->	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Appraisal  
					Rating Definition</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="add"
						onclick="return callForsave();" value=" Save" /> <input
						type="button" class="reset"   onclick="return callForResetForm();"
						value=" Reset" />
						<!--added by prajakta B  -->
						<input type="button" class="add"   onclick="return callForBackForm();"
						value=" Back"/>
						</td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<!-- Define Appraisal Code table starts here-->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">

				<tr>
					<td align="left" width="50%" colspan="1"><label
						name="appraisal.name" id="appraisal.name" ondblclick="callShowDiv(this);"><%=label.get("appraisal.name")%></label>: <font
						color="red">*</font>: <s:textfield name="apprCode"
						value="%{apprCode}" size="20" readonly="true" />
						<s:if test="displayFlag">
						</s:if>
						<s:else>
						<img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="15"
						onclick="javascript:callsF9(500,325,'ApprRatingDefinition_f9SelectAppraisalCode.action');" />
						</s:else>
					<s:hidden name="apprId" /></td>
					
					<td align="left" width="50%" colspan="2"><label
						name="template.name" id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>: <font
						color="red">*</font>: <s:textfield name="templateName"
						value="%{templateName}" size="20" readonly="true" />
					<s:hidden name="templateCode" /></td>

				</tr>
			 
				<tr>
					<td height="5" colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>

				</tr>

			</table>
			</td>
		</tr>

		<!-- Define Appraisal Code table ends here-->

		<!-- Define Question Rating Formula table starts here-->
		<s:if test="showFlag"> 
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="3" class="text_head"><strong>Define
					Question Rating Formula</strong></td>
				</tr>
				<tr>
				  <td colspan="1" width="34%"><s:textarea cols="40" rows="4"
						name="quesRatingFormula"  readonly="true" /></td>
					<td width="66%" colspan="1" rowspan="2" valign="top">
					<p><a href="#" onclick="callDiv(this);callParameter('99');">Add Parameters </a></p>
				</tr>
				<tr>
					<td colspan="1"><input type="button" class="token" name="Back"
						value=" Backspace " onclick="callClear('99');" /> <input
						type="button" class="token" name="Reset" value=" Reset "
						onclick="callreset('99');" /> </td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Define Question Rating Formula table ends here-->


		<!-- Define phase Rating Formula table starts here-->

		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="3" class="text_head"><strong>Define
					Phase Rating Formula</strong></td>
				</tr>
				<%
									try {
										 
									if (data!=null && data.length > 0 ) {
										 System.out.println("data for rating====-=-=-=---_=-=-::::"+data);
										for (int k = 0; k < data.length; k++) { System.out.println("data for rating====-=-=-=---_=-=-::::"+data[k]);
							%>
				<tr>
					<td><input type="hidden" name="apprPhaseId"
						id="apprPhaseId<%=k%>" value="<%= String.valueOf(data[k][0]) %>" />
					<input type="hidden" name="apprPhaseName" id="apprPhaseName<%=k%>"
						value="<%= String.valueOf(data[k][1]) %>" /> 
						
						<strong><%=k+1%>)  <%= String.valueOf(data[k][1]) %></strong>
					</td>
				</tr>
				<tr>
				  <td colspan="1" width="34%"><s:textarea cols="40" rows="4"
						name="phaseRatingFormula" value="<%= String.valueOf(data[k][3])%>"  readonly="true" id='<%="phaseRatingFormula"+k %>'/></td>
					<td width="66%" colspan="1" rowspan="2" valign="top">
					<p><a href="#" onclick="callDiv(this);callPhaseParameter('<%=k%>');">Add Parameters </a></p>
				</tr>
				<tr>
					<td colspan="1"><input type="button" class="token" name="Back"
						value=" Backspace " onclick="callClear('<%=k%>');" /> <input
						type="button" class="token" name="Reset" value=" Reset "
						onclick="callreset('<%=k%>');" /></td>
				</tr>
				<%
									}
									}
								} catch (Exception e) {
									 e.printStackTrace();

								}
							%>

			</table>
			</td>
		</tr>
		<!-- Define phase Rating Formula table ends here-->

		<!-- Define final Rating Formula table starts here-->
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="3" class="text_head"><strong>Define
					Final Rating Formula</strong></td>
				</tr>
				<tr>
				  <td colspan="1" width="34%"><s:textarea cols="40" rows="4"
						name="finalRatingFormula" readonly="true" /></td>
					<td width="66%" colspan="1" rowspan="2" valign="top">
					<p><a href="#" onclick="callDiv(this);callFInalRatingParameter('999');">Add Parameters </a></p>
				</tr>
				<tr>
					<td colspan="1"><input type="button" class="token" name="Back"
						value=" Backspace " onclick="callClear('999');" /> <input
						type="button" class="token" name="Reset" value=" Reset "
						onclick="callreset('999');" /> </td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Define final Rating Formula table ends here-->
		<!-- configure with previous appraisal table begins here-->
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="3" class="text_head"><strong>Configure With Previous Appraisal</strong></td>
				</tr>
				<tr>
				  <td colspan="1" width="34%"><s:textarea cols="40" rows="4"
						name="preRatingFormula" readonly="true" /></td>
					<td width="66%" colspan="1" rowspan="2" valign="top">
					<p><a href="#" onclick="callDiv(this);callPreAppraisal('888');">Add Current Score</a></p>
					<p><a href="#" onclick="callDiv(this);callPreviousApprisal('888');">Add Previous Rating</a></p>
				</tr>
				<tr>
					<td colspan="1"><input type="button" class="token" name="Back"
						value=" Backspace " onclick="callClear('888');" /> <input
						type="button" class="token" name="Reset" value=" Reset "
						onclick="callreset('888');" /> </td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- configure with previous appraisal table ends here-->
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%"><input type="button" class="add"
						onclick="return callForsave();" value=" Save" /> <input
						type="button" class="reset"   onclick="return callForResetForm();"
						value=" Reset" />
						<!--added by prajakta B  -->
						<input type="button" class="add"   onclick="return callForBackForm();"
						value=" Back"/>
				</tr>
			</table>
			<label></label></td>
		</tr>

	</s:if>
	<s:hidden name="hiddenCode" /> 
	</table>
<!-- Main Table Ends  -->
</s:form>

<script><!--
 
function callForResetForm()
{
document.getElementById('paraFrm').action='ApprRatingDefinition_reset.action';
		document.getElementById('paraFrm').submit();
		

}

//Added by Prajakta B for Back Button
function callForBackForm()
{
var apprId = document.getElementById('paraFrm_apprId').value;
document.getElementById('paraFrm').action='TemplateDefination_input.action?menuCode=748&appraisalIdList='+apprId;
document.getElementById('paraFrm').submit();
		

}

function callForsave()
{
 	var apprCodeLabel=document.getElementById('appraisal.name').innerHTML.toLowerCase();
	var templateCodeLabel=document.getElementById('template.name').innerHTML.toLowerCase();
	
	var apprId = document.getElementById('paraFrm_apprId').value;
	var templateCode = document.getElementById('paraFrm_templateCode').value;
	
	if(apprId=="")
	{
		alert ("Please select "+apprCodeLabel);
		return false;
	}
	if(templateCode=="")
	{
		alert ("Please select "+templateCodeLabel);
		return false;
	}
		document.getElementById('paraFrm').action='ApprRatingDefinition_save.action';
		document.getElementById('paraFrm').submit();
		
		return true;
		
}

function callReset1()
{
	document.getElementById('paraFrm').action='ApprRatingDefinition_save.action';
		document.getElementById('paraFrm').submit();
		
}

	function callPreviousApprisal(id)
	{
	document.getElementById('div_main').style.display='';
	document.getElementById('paraFrm_hiddenCode').value=id;
	document.getElementById('div_previousApprId').style.display = '';
	document.getElementById('div_parameterId').style.display = 'none';
	document.getElementById('div_functionId').style.display = 'none';
	document.getElementById('div_parameterPhaseId').style.display = 'none';
	document.getElementById('div_finalRatingId').style.display = 'none';
	document.getElementById('div_preAppraisalId').style.display = 'none';
	}
	
	function hide_Div() {
		document.getElementById('div_functionId').style.display = 'none';
		document.getElementById('div_main').style.display = 'none';
	}
	
function callParameter(id)
{
	document.getElementById('div_main').style.display='';
	document.getElementById('paraFrm_hiddenCode').value=id;
	document.getElementById('div_parameterId').style.display = '';
	document.getElementById('div_functionId').style.display = '';
	document.getElementById('div_previousApprId').style.display = 'none';
	document.getElementById('div_parameterPhaseId').style.display = 'none';
	document.getElementById('div_finalRatingId').style.display = 'none';
	document.getElementById('div_preAppraisalId').style.display = 'none';
	
}

function callPhaseParameter(id)
{
	document.getElementById('div_main').style.display='';
	document.getElementById('paraFrm_hiddenCode').value=id;
	document.getElementById('div_parameterPhaseId').style.display = '';
	document.getElementById('div_functionId').style.display = '';
	document.getElementById('div_parameterId').style.display = 'none';
	document.getElementById('div_previousApprId').style.display = 'none';
	document.getElementById('div_finalRatingId').style.display = 'none';
	document.getElementById('div_preAppraisalId').style.display = 'none';
	
}

function callFInalRatingParameter(id)
{
	document.getElementById('div_main').style.display='';
	document.getElementById('paraFrm_hiddenCode').value=id;
	document.getElementById('div_finalRatingId').style.display = '';
	document.getElementById('div_functionId').style.display = '';
	document.getElementById('div_parameterId').style.display = 'none';
	document.getElementById('div_previousApprId').style.display = 'none';
	document.getElementById('div_parameterPhaseId').style.display = 'none';
	document.getElementById('div_preAppraisalId').style.display = 'none';

}

function callPreAppraisal(id)
{
	document.getElementById('div_main').style.display='';
	document.getElementById('paraFrm_hiddenCode').value=id;
	document.getElementById('div_preAppraisalId').style.display = '';
	document.getElementById('div_functionId').style.display = '';
	document.getElementById('div_finalRatingId').style.display = 'none';
	document.getElementById('div_parameterId').style.display = 'none';
	document.getElementById('div_previousApprId').style.display = 'none';
	document.getElementById('div_parameterPhaseId').style.display = 'none';

}
	function setValueOfParameter1(obj,btnName,btnOrder)
	{
				try{	 	
	var val=document.getElementById('paraFrm_hiddenCode').value;
	 
			if(val=='999'){
				var setValue;
			 	 		
				if(LTrim1(RTrim1(obj.value))== LTrim1(RTrim1(btnName)))
				{
					setValue='PHASE'+eval(btnOrder-1)+'_RATING'; 
				}
						
			document.getElementById('paraFrm_finalRatingFormula').value+=LTrim1(RTrim1(setValue));
		}else if(val=='888'){
		     var setValue;
			 	 		
				if(LTrim1(RTrim1(obj.value))== LTrim1(RTrim1(btnName)))
				{
					setValue='PHASE'+eval(btnOrder-1)+'_RATING'; 
				}
						
			document.getElementById('paraFrm_preRatingFormula').value+=LTrim1(RTrim1(setValue));
		
		}
	}catch(e){ alert(""+e);}
	
	}	
	
	
	function setValueOfParameter2(obj,btnName,btnOrder)
	{
	
			try{	 	
	var val=document.getElementById('paraFrm_hiddenCode').value;
	  
	  var setValue;
				if(LTrim1(RTrim1(obj.value))=='Sum Of Ques Rating')
				{
					setValue='S_RATING_SUM'+eval(btnOrder-1);
				}
				if(LTrim1(RTrim1(obj.value))=='Total Questions')
				{
					setValue='S_TOTAL_QUES'+eval(btnOrder-1);
				}
				if(LTrim1(RTrim1(obj.value))=='Sum Of Que Weightage')
				{
					setValue='S_QUES_WEIGHTAGE_SUM'+eval(btnOrder-1);
				}
			
			document.getElementById('phaseRatingFormula'+val).value+=LTrim1(RTrim1(setValue));
	  
		 
	}catch(e){ alert(""+e);}
	
	}	
	
	function setValueOfParameter(obj)
	{
		var val=document.getElementById('paraFrm_hiddenCode').value;
	if(val=='99'){
			var setValue;
				if(LTrim1(RTrim1(obj.value))=='Rating Given')
				{
					setValue='Q_RATING';
				}
				if(LTrim1(RTrim1(obj.value))=='Max Que Rating')
				{
					setValue='Q_MAX_RATING';
				}
				if(LTrim1(RTrim1(obj.value))=='Question Weightage')
				{
					setValue='Q_WEIGHTAGE';
				}
			
			document.getElementById('paraFrm_quesRatingFormula').value+=LTrim1(RTrim1(setValue));
		}
		else {
			var setValue;
				if(LTrim1(RTrim1(obj.value))=='Sum Of Ques Rating')
				{
					setValue='P_RATING_SUM';
				}
				if(LTrim1(RTrim1(obj.value))=='Total Questions')
				{
					setValue='P_TOTAL_QUES';
				}
				if(LTrim1(RTrim1(obj.value))=='Phase Weightage')
				{
					setValue='P_WEIGHTAGE';
				}
				if(LTrim1(RTrim1(obj.value))=='Sum Of Que Weightage')
				{
					setValue='P_QUES_WEIGHTAGE_SUM';
				}
			
			document.getElementById('phaseRatingFormula'+val).value+=LTrim1(RTrim1(setValue));
		}
	
	}
	function addPrevious(obj)
	{
		var val=document.getElementById('paraFrm_hiddenCode').value;
		
		var previousApprFormula=document.getElementById('paraFrm_preapprCode').value
		if(val=='999')	
		{
		document.getElementById('paraFrm_finalRatingFormula').value+=LTrim1(RTrim1(previousApprFormula));
		}else if(val=='888'){
		
		if(previousApprFormula==""){
		alert("Please select Appraisal Code");
		return false;
		}else{
		document.getElementById('paraFrm_preRatingFormula').value+=LTrim1(RTrim1(previousApprFormula));
		var appCode=document.getElementById('paraFrm_preRatingFormula').value;
		document.getElementById('paraFrm_preRatingFormula').value=appCode+"#";
		document.getElementById('paraFrm_preapprCode').value="";
		}
		}
	}

function setValue(obj){
	var val=document.getElementById('paraFrm_hiddenCode').value;
	
	if(val=='999' || val=='99'){
	if(val=='99'){
	document.getElementById('paraFrm_quesRatingFormula').value+=LTrim1(RTrim1(obj.value));
		}
	else if(val=='999')	
		{
		document.getElementById('paraFrm_finalRatingFormula').value+=LTrim1(RTrim1(obj.value));
		}
	else {
	document.getElementById('phaseRatingFormula'+val).value+=LTrim1(RTrim1(obj.value));
	}
	}else if(val=='888'){
	  document.getElementById('paraFrm_preRatingFormula').value+=LTrim1(RTrim1(obj.value));
	}else {
	document.getElementById('phaseRatingFormula'+val).value+=LTrim1(RTrim1(obj.value));
	}
	
}

// Trims all spaces to the left of a specific string
function LTrim1(str)
{
        var whitespace = new String(" \t\n\r "); 
        // last space character is not a space, but alt+0160, 
        // another invisible char. 
        var s = new String(str);
        if (whitespace.indexOf(s.charAt(0)) != -1) {
            // We have a string with leading blank(s)...
            var j=0, i = s.length;
            // Iterate from the far left of string until we
            // don't have any more whitespace...
            while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
                j++;
            // Get the substring from the first non-whitespace
            // character to the end of the string...
            s = s.substring(j, i);
        }
        return s;
}
// Trims all spaces to the right of a specific string
function RTrim1(str)
{
        // We don't want to trip JUST spaces, but also tabs,
        // line feeds, etc.  Add anything else you want to
        // "trim" here in whitespace
        var whitespace = new String(" \t\n\r "); 
        // last space character is not a space, but alt+0160,
        // another invisible char. 
        var s = new String(str);
        if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
            // We have a string with trailing blank(s)...
            var i = s.length - 1;       // Get length of string
            // Iterate from the far right of string until we
            // don't have any more whitespace...
            while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
                i--;
            // Get the substring from the front of the string to
            // where the last non-whitespace character is...
            s = s.substring(0, i+1);
        }
        return s;
}


function callClear(id){

	document.getElementById('paraFrm_hiddenCode').value=id;
	var val=document.getElementById('paraFrm_hiddenCode').value;
	
	if(val=='99' || val=='999'){
	if(val=='99'){
	var str=document.getElementById('paraFrm_quesRatingFormula').value;

	str=str.substring(0,str.length-1);

	document.getElementById('paraFrm_quesRatingFormula').value=str;
		}
	else if(val=='999')	
		{
		var str=document.getElementById('paraFrm_finalRatingFormula').value;

		str=str.substring(0,str.length-1);

		document.getElementById('paraFrm_finalRatingFormula').value=str;
		 
		}
	else {
		var str=document.getElementById('phaseRatingFormula'+val).value;

	str=str.substring(0,str.length-1);

	document.getElementById('phaseRatingFormula'+val).value=str;
		 
		}
		}else if(val=='888'){
		var str=document.getElementById('paraFrm_preRatingFormula').value;

		str=str.substring(0,str.length-1);

		document.getElementById('paraFrm_preRatingFormula').value=str;
		}else {
			var str=document.getElementById('phaseRatingFormula'+val).value;

	 	 str=str.substring(0,str.length-1);

		document.getElementById('phaseRatingFormula'+val).value=str;
		 
		}
	 
} 

function callreset(id)
{
	document.getElementById('paraFrm_hiddenCode').value=id;
	var val=document.getElementById('paraFrm_hiddenCode').value;
	
	if(val=='99' || val=='999'){
		if(val=='99'){
	document.getElementById('paraFrm_quesRatingFormula').value="";
		}
	else if(val=='999')	
		{
		document.getElementById('paraFrm_finalRatingFormula').value="";
		}
	else {
			document.getElementById('phaseRatingFormula'+val).value="";
		}
	}else if(val=='888'){
		document.getElementById('paraFrm_preRatingFormula').value="";
		}else{
			document.getElementById('phaseRatingFormula'+val).value="";
		}
	 
}

	function addCurrentScore(){
	     var oldValue=document.getElementById('paraFrm_preRatingFormula').value;
	     document.getElementById('paraFrm_preRatingFormula').value=oldValue+"CURRENT_SCORE";
	     
	}
	
//Function for setting main div position
	function callDiv(obj) {
		try {
			document.getElementById('div_main').style.display = "";
			document.getElementById('div_main').style.top=getTopPos(obj) + 'px';
			document.getElementById('div_main').style.left=getleftPos(obj) + 'px';
			
			var val = trim(obj.innerHTML);
			
			while(val.indexOf("  ", " ") > -1) {
				val = val.replace("  ", " ");
			}
			if(val.length > 35) {
				if(val.indexOf("- ") > 0) {
					val = val.replace("- ", "");
				}
			}
		} catch(e) {}
	}
	
	function getTopPos(inputObj) {
		var returnValue = inputObj.offsetTop + inputObj.offsetHeight;
	  
		while((inputObj = inputObj.offsetParent) != null) {
	  		returnValue += inputObj.offsetTop;
		}
		if(returnValue < 70) {
			return returnValue;
			
	  	}
		return returnValue + 100;
	}

	function getleftPos(inputObj) {
		var returnValue = inputObj.offsetLeft;
		
	  	while((inputObj = inputObj.offsetParent) != null) {
	  		returnValue += inputObj.offsetLeft;
	  	}
	  	if(returnValue > 350) {
	  		returnValue = returnValue - 400;
	  			  	
		  	while(returnValue < 0) {
		  	 	returnValue += 10;
		  	}
		  	return returnValue;
	  	} else {
	  		return returnValue + 400;
		}
	}
	

	
</script>