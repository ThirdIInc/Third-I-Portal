<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="AppraisalInstruction" validate="true" id="paraFrm"
	theme="simple">
<%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		
		<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Instruction</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" >
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
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
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="4%" colspan="1" height="20" class="formtext"><s:checkbox
								name="instrApplicable">
							</s:checkbox></td>
							<td width="96%" colspan="3" height="20"><label class="set" name="appr.instr.applicable"
								id="appr.instr.applicable" ondblclick="callShowDiv(this);"><%=label.get("appr.instr.applicable")%></label>
							</td>

						</tr>
					</table>
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
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">

						<tr>
							<s:hidden name="apprId"></s:hidden>
							<s:hidden name="startDate"></s:hidden>
							<s:hidden name="endDate"></s:hidden>
							<s:hidden name="apprCode"></s:hidden>
							<s:hidden name="saveFlag"></s:hidden>
							<s:hidden name="templateCode"></s:hidden>
							<s:hidden name="templateName"></s:hidden>
							<s:hidden name="ratingType"></s:hidden>

							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="appraisal.code" id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="apprCode" /></td>
							<td width="30%" colspan="1" height="20" nowrap="nowrap" class="formtext">
							<label class="set"  name="template.appr.start.date" id="template.appr.start.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.start.date")%></label>:
								<s:property	value="startDate"></s:property>&nbsp;&nbsp;
							<label class="set"  name="template.appr.end.date" id="template.appr.end.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.end.date")%></label>:
							<s:property value="endDate"></s:property>
							</td>
							<td width="20%" colspan="1" height="20"></td>
						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="template.name" id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="templateName" /></td>
							<td width="30%" colspan="1" height="20" class="formtext"></td>
							<td width="20%" colspan="1" height="20"></td>
						</tr>

					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		

	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<s:if test='%{ratingType == "scale" }'>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="sortable">
						<tr>
							<td width="100%" colspan="3"><b>Rating Scale</b></td>
							
						</tr>
						<tr>
							<td width="2%" class="formth"><label
								class="set" id="rating.srno" ondblclick="callShowDiv(this);"><%=label.get("rating.srno")%></label></td>
							<td width="10%" class="formth"><label
								class="set" id="rating.value" ondblclick="callShowDiv(this);"><%=label.get("rating.value")%></label></td>
							<td width="30%" class="formth"><label
								class="set" id="rating.desc" ondblclick="callShowDiv(this);"><%=label.get("rating.desc")%></label></td>
						</tr>
						<% int i = 0,j=1;%>

						<s:iterator value="ratingList">
							<tr>
								<td  width="2%" align="center" class="td_bottom_border"><%=j %></td>
								<td  width="10%" align="center" nowrap="nowrap" class="td_bottom_border"><s:property	value="ratingValue" /></td>
								<td  width="30%" align="left" class="td_bottom_border"><s:property value="ratingDesc" /></td>
							</tr>

							<%
			i++;
							j++;
			%>
						</s:iterator>
			
			
					</table>

					</td>
				</tr>
			</s:if>	
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
						<td width="25%" colspan="3" height="20" class="formtext"><label
								class="set" name="appr.instr.comment" id="appr.instr.comment" ondblclick="callShowDiv(this);"><%=label.get("appr.instr.comment")%></label>
							:</td>
													
						</tr>
							<td width="80%" colspan="3" height="20" class="formtext">
							<s:textarea name = "instruction" rows="3" cols="110"  ></s:textarea>
														
							</td>
						<tr>
						
						</tr>	
							</table>
							</td>
							</tr>
							</table>
							</td></tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" >
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>


	</table>
	


</s:form>




<script type="text/javascript">

function saveFun(){
			
			var comment = trim(document.getElementById('paraFrm_instruction').value);
			if(eval(comment.length) > 4000){
				alert("Please enter the "+document.getElementById("appr.instr.comment").innerHTML.toLowerCase()+" upto 4000 chars.");
				document.getElementById('paraFrm_instruction').focus();
				return false;
				}
			document.getElementById("paraFrm").action="AppraisalInstruction_save.action";
			document.getElementById("paraFrm").submit();
				
}
function saveandnextFun(){
			var comment = trim(document.getElementById('paraFrm_instruction').value);
			if(eval(comment.length) > 4000){
				alert("Please enter the "+document.getElementById("appr.instr.comment").innerHTML.toLowerCase()+" upto 4000 chars.");
				document.getElementById('paraFrm_instruction').focus();
				return false;
				}
			document.getElementById("paraFrm").action="AppraisalInstruction_saveAndNext.action";
			document.getElementById("paraFrm").submit();

}
function saveandpreviousFun(){
			var comment = trim(document.getElementById('paraFrm_instruction').value);
			if(eval(comment.length) > 4000){
				alert("Please enter the "+document.getElementById("appr.instr.comment").innerHTML.toLowerCase()+" upto 4000 chars.");
				document.getElementById('paraFrm_instruction').focus();
				return false;
				}
			document.getElementById("paraFrm").action="AppraisalInstruction_saveAndPrevious.action";
			document.getElementById("paraFrm").submit();

}


function cancelFun(){
		document.getElementById("paraFrm").action="TemplateDefination_input.action";
	    document.getElementById("paraFrm").submit();	
				
}

function resetFun(){
		
		document.getElementById("paraFrm").action="AppraisalInstruction_reset.action";
		document.getElementById("paraFrm").submit();
		
}
function nextFun(){
		
		document.getElementById("paraFrm").action="DefineSection_input.action";
		document.getElementById("paraFrm").submit();
		
}
function previousFun(){
		
		document.getElementById("paraFrm").action="TemplateDefination_input.action";
	    document.getElementById("paraFrm").submit();	
		
}

</script>
