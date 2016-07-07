<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%
			int questionListLength = request.getAttribute("questionListLength") == null ? 0
			: Integer.parseInt(request.getAttribute("questionListLength").toString());

			int totalPage = request.getAttribute("abc") == null ? 0
								: (Integer) request.getAttribute("abc");
			int pageNo = request.getAttribute("xyz") == null ? 0
								: (Integer) request.getAttribute("xyz");
%>
<s:form action="ManageQuestion" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="programQuesID"></s:hidden>
	<s:hidden name="questionFlag"></s:hidden>
	<input type="hidden" name="programID"  value="<s:property value="queProgrameCode"/>" />
	<input type="hidden" name="moduleID" value="<s:property value="queModuleCodeItt"/>"/>
	<input type="hidden" name="progName" value="<s:property value="queProgrameName"/>"/>
	<input type="hidden" name="modName" value="<s:property value="queModuleItt"/>"/>
	<input type="hidden" name="sectionCode" value="<s:property value="queSectionCodeItt"/>"/>
	<input type="hidden" name="sectionName" value="<s:property value="queSectionItt"/>"/>
	<s:hidden name="queModuleCodeItt" />
	<s:hidden name="queModuleItt" />
	<s:hidden name="queSectionItt" />
	<s:hidden name="queSectionCodeItt" />
	<s:hidden name="totalRecords" />
	<s:hidden name="totalQuestion" />
	<s:hidden name="totalEasyQuestion" />
	<s:hidden name="totalMediumQuestion" />
	<s:hidden name="totalHardQuestion" />
	<s:hidden name="myPage" id="myPage" />
	
	<table width="100%" cellpadding="2" cellspacing="2" border="0"
		class="formbg" align="right">
		<tr>
			<td width="100%" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manage
					Question </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table border="0" cellpadding="2" cellspacing="0" align="left">
				<tr>
					<td width="2%" align="left"><input type="button"
						name="saveBtn" id="saveBtn" value="Save" onclick="javaScript:saveQuesFun();" /></td>
					<td width="98%" align="left"><input type="button"
						name="=backBtn" value="Back" onclick="javaScript:backQuesFun();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="22%"><label class="set" name="programe"
								id="programe" ondblclick="callShowDiv(this);"><%=label.get("programe")%></label></td>
							<td width="1%">:</td>
							<td width="33%" align="left"><s:property
								value="queProgrameName" />
							<s:hidden name="queProgrameCode" />
							<s:hidden name="queProgrameName" />
							</td>
						</tr>
						
						
						
							<tr>
								<td width="32%" align="left"><label class="set"
									name="moduleNm" id="moduleNm" ondblclick="callShowDiv(this);"><%=label.get("moduleNm")%></label></td>
								<td width="1%">:</td>
								<td width="11%" align="left"><s:property value="queModuleItt" />
								</td>
							</tr>
						<s:if test="questionFlag=='module'" >
						</s:if>
						<s:else>
						<tr>
							<td width="20%"><label class="set" name="sectionNm"
								id="sectionNm" ondblclick="callShowDiv(this);"><%=label.get("sectionNm")%></label></td>
							<td width="1%">:</td>
							<td width="80%"><s:property value="queSectionItt" />
							</td>
						</tr>
						</s:else>
						<tr>
							<td width="22%"><label class="set" name="random" id="random"
								ondblclick="callShowDiv(this);"><%=label.get("random")%></label></td>
							<td width="1%">:</td>
							<td width="33%"><s:checkbox name="randomCheck"
								onclick="checkRandom();" 
								/></td>
						</tr>
						<tr>
							<td width="32%" align="left"><label class="set" name="showQues" id="showQues"
								ondblclick="callShowDiv(this);"><%=label.get("showQues") %></label></td>
							<td width="1%">:</td>
							<td width="11%"><s:textfield name="showNoQues" theme="simple" onkeyup="setTotalMarks();" onkeypress="return numbersOnly();" maxlength="3"
								size="20" /></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="addQues"
								id="addQues" ondblclick="callShowDiv(this);"><%=label.get("addQues")%></label></td>
							<td width="1%">:</td>
							<td width="80%"><s:textarea name="quesName" cols="50" rows="1" theme="simple" readonly="true"/>
								<s:hidden name="quesCode"/>
								<s:hidden name="quesType"/>
								<s:hidden name="quesLevel"/><img src="../pages/images/recruitment/search2.gif"
								height="18" align="absmiddle" width="18" 
								onclick="javascript:f9que(event);"
								>
						    <input type="button" name="btnAddQues" value="Add" onclick="javaScript:addQuestion();"/></td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<s:if test="questionList==null">
			</s:if>
			<s:else>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortableTD">
						<tr>
							<td ><b>Questions</b></td>
							<s:hidden name="deleteQueCode"></s:hidden>
							
							<td colspan="6" id="ctrlShow" width="38%" align="right" class=""><b>Page:</b>
						<%
					
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>',
							 'ProgrammeMaster_callQuesManagePageView.action?programID=<s:property value="queProgrameCode"/>&moduleID=<s:property value="queModuleCodeItt"/>&modName=<s:property value="queModuleItt"/>&sectionCode=<s:property value="queSectionCodeItt"/>&progName=<s:property value="queProgrameName"/>&sectionName=<s:property value="queSectionItt"/>');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'ProgrammeMaster_callQuesManagePageView.action?programID=<s:property value="queProgrameCode"/>&moduleID=<s:property value="queModuleCodeItt"/>&modName=<s:property value="queModuleItt"/>&sectionCode=<s:property value="queSectionCodeItt"/>&progName=<s:property value="queProgrameName"/>&sectionName=<s:property value="queSectionItt"/>');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'ProgrammeMaster_callQuesManagePageView.action?programID=<s:property value="queProgrameCode"/>&moduleID=<s:property value="queModuleCodeItt"/>&modName=<s:property value="queModuleItt"/>&sectionCode=<s:property value="queSectionCodeItt"/>&progName=<s:property value="queProgrameName"/>&sectionName=<s:property value="queSectionItt"/>');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'ProgrammeMaster_callQuesManagePageView.action?programID=<s:property value="queProgrameCode"/>&moduleID=<s:property value="queModuleCodeItt"/>&modName=<s:property value="queModuleItt"/>&sectionCode=<s:property value="queSectionCodeItt"/>&progName=<s:property value="queProgrameName"/>&sectionName=<s:property value="queSectionItt"/>')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ProgrammeMaster_callQuesManagePageView.action?programID=<s:property value="queProgrameCode"/>&moduleID=<s:property value="queModuleCodeItt"/>&modName=<s:property value="queModuleItt"/>&sectionCode=<s:property value="queSectionCodeItt"/>&progName=<s:property value="queProgrameName"/>&sectionName=<s:property value="queSectionItt"/>');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
							
						</tr>
						<tr>
							<td class="formth" align="center" width="5%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							<td class="formth" align="center" width="45%"><label
								class="set" name="question" id="question"
								ondblclick="callShowDiv(this);"><%=label.get("question")%></label></td>							
							<td class="formth" align="center" width="10%"><label
								class="set" name="typeQues" id="typeQues"
								ondblclick="callShowDiv(this);"><%=label.get("typeQues")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="level" id="level"
								ondblclick="callShowDiv(this);"><%=label.get("level")%></label></td>
							<td class="formth" align="center" width="10%"><label
								class="set" name="marks" id="marks"
								ondblclick="callShowDiv(this);"><%=label.get("marks")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="programorder" id="programorder"
								ondblclick="callShowDiv(this);"><%=label.get("programorder")%></label></td>						
							<td class="formth" align="center" width="5%"><label
								class="set" name="remove" id="remove"
								ondblclick="callShowDiv(this);"><%=label.get("remove")%></label></td>
						</tr>
						<%!int count = 0;%>
						<%
						int i = 1;
						
						int cn = pageNo * 5 - 5 ;
						%>
						<s:iterator value="questionList">
							<tr>
								<td class="sortableTD"><%=++cn%><input type="hidden"
									name="SrNoQ" id="SrNoQ<%=i %>" value="<%=i%>">&nbsp;</td>
								<td class="sortableTD" align="left" id="quesIttTd<%=i%>"
									title="<s:property value="quesAbbrItt"/>"
								><input type="hidden"
									name="quesItt" id="quesItt"
									value="<s:property value="quesItt" />"><s:property
									value="quesItt" />&nbsp;<s:hidden name="quesCodeItt" /></td>
								<td class="sortableTD" align="center" id="quesTypeIttTd<%=i%>"><s:property
									value="quesTypeItt" /><s:hidden name="quesTypeItt"/></td>
								<td class="sortableTD" align="center" id="quesLevelIttTd<%=i%>"><s:property
									value="quesLevelItt" /><s:hidden name="quesLevelItt"/></td>																
								<td class="sortableTD" align="center" id="quesMarkIttTd<%=i%>"><s:textfield 
									name="quesMarkItt" size="10" theme="simple" maxlength="5" readonly="true" onkeypress="return numbersOnly();"
									onkeyup="setTotalMark();"
									/></td>									
								<td class="sortableTD" align="center">
									<s:hidden name="quesOrderItt" />
									<%
										if (i != 1) {
										%>
									<a href="#" onclick="orderUp('<s:property value="quesOrderItt"/>',<%=i %>);"  >
										<img border="0" src="../pages/common/css/default/images/up.GIF" width="10"	height="10" >
									</a>
									<%} %>
									<%
										if (i != questionListLength) {
									%>
									<a href="#" onclick="orderDown('<s:property value="categoryOrder"/>',<%=i%>);">
										<img border="0" src="../pages/common/css/default/images/down.GIF" width="10" height="10" >
									</a>
									<% } %>
									
									<% if (i == 1 && i == questionListLength) { %>
									-
									<%} %>
									</td>
								<td class="sortableTD" align="center" id="quesDeleteIttTd<%=i%>">
								
								<img style="cursor: pointer;"  border="0" 
								onclick="callfordelete('<s:property value="quesCodeItt"/>',<%=i %>);"
								src="../pages/mypage/images/icons/delete.png"  />
								
								 </td>
							</tr>
							<%
							i++;
							%>
						</s:iterator>
						<%
						count = i;
						%>
						<tr><td align="right" colspan="7" height="10"></td></tr>
						<tr>
							<td align="right" colspan="7" >
								<b>Total no. of question :  <s:property value="totalRecords" /></b>
							</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			</s:else>
			</td>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width=""><s:checkbox name="equalEachMarkChk" onclick="funEqualMark();" /><label
								class="set" name="equalMark" id="equalMark"
								ondblclick="callShowDiv(this);"><%=label.get("equalMark")%></label>:</td>
							<td colspan=""><s:textfield name="equalEachMark" theme="simple" onkeypress="return numbersOnly();" 
								size="20" maxlength="3" onkeyup="setEqualMarks();" /></td>
						</tr>
						<tr>
							<td width="40%" colspan="2">
								<s:checkbox name="equalEachMarkType" onclick="funEqualMarkType();" /><label
										class="set" name="equalMarkType" id="equalMarkType"
										ondblclick="callShowDiv(this);"><%=label.get("equalMarkType")%></label>								
							</td>
							<td width="10%"></td>
							<td width="40%" colspan="2">
								<s:checkbox name="equalNegaMarkType" onclick="funEqualNegaMarkType();" /><label
										class="set" name="equalNegativeMark" id="equalNegativeMark"
										ondblclick="callShowDiv(this);"><%=label.get("equalNegativeMark")%></label>							
							</td>
						</tr>
						<tr>
							<td width="40%" colspan="2">
							<table cellpadding="2" cellspacing="2" border="0">
								<tr>
									<td width="32%" align="left"><label
										class="set" name="easy" id="easy"
										ondblclick="callShowDiv(this);"><%=label.get("easy")%></label></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="easyMarkQue" onkeypress="return numbersOnly();" 
										theme="simple" size="20" maxlength="3" onkeyup="setEqualMarksType('Easy');" /></td>
								</tr>
								<tr>
									<td width="32%" align="left"><label
										class="set" name="medium" id="medium"
										ondblclick="callShowDiv(this);"><%=label.get("medium")%></label></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="mediumMarkQue" onkeypress="return numbersOnly();" 
										theme="simple" size="20" maxlength="3" onkeyup="setEqualMarksType('Medium');" /></td>
								</tr>
								<tr>
									<td width="32%" align="left"><label
										class="set" name="hard" id="hard"
										ondblclick="callShowDiv(this);"><%=label.get("hard")%></label></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="hardMarkQue" onkeypress="return numbersOnly();" 
										theme="simple" size="20" maxlength="3" onkeyup="setEqualMarksType('Hard');" /></td>
								</tr>
								<tr>
									<td width="32%" align="left"><label
										class="set" name="totalMarks" id="totalMarks"
										ondblclick="callShowDiv(this);"><%=label.get("totalMarks")%></label></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="totalMark" readonly="true"
										theme="simple" size="20" /></td>
								</tr>
							</table>
							</td>
							<td width="10%"></td>
							<td width="40%" colspan="2">
							<table cellpadding="2" cellspacing="2" border="0">
								<tr>
									<td width="32%" align="left"><label
										class="set" name="easy" id="easy"
										ondblclick="callShowDiv(this);"><%=label.get("easy")%></label></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="easyNegaMarkQ" onkeypress="return numbersOnly();" 
										theme="simple" maxlength="3" size="20" /></td>
								</tr>
								<tr> 
									<td width="32%" align="left"><label
										class="set" name="medium" id="medium"
										ondblclick="callShowDiv(this);"><%=label.get("medium")%></label></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="mediumNegaMarkQ" onkeypress="return numbersOnly();" 
										theme="simple" maxlength="3" size="20" /></td>
								</tr>
								<tr>
									<td width="32%" align="left"><label
										class="set" name="hard" id="hard"
										ondblclick="callShowDiv(this);"><%=label.get("hard")%></label></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="hardNegaMarkQ" onkeypress="return numbersOnly();" 
										theme="simple" maxlength="3" size="20" /></td>
								</tr>
								<tr>
									<td width="32%" align="left"><label
										class="set" name="passMarks" id="passMarks"
										ondblclick="callShowDiv(this);"><%=label.get("passMarks")%></label><font
										color="red">*</font></td>
									<td width="1%">:</td>
									<td width="11%"><s:textfield name="passQuesMark" onkeypress="return numbersOnly();"
										theme="simple" maxlength="3" size="20" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				</td>
				</tr>
			</table>
			</td>
						
			
		</tr>
		<tr>
			<td>
			<table border="0" cellpadding="2" cellspacing="0" align="left">
				<tr>
					<td width="2%" align="left"><input type="button"
						name="saveBtn" id="saveBtn" value="Save" onclick="javaScript:return saveQuesFun();" /></td>
					<td width="98%" align="left"><input type="button"
						name="=backBtn" value="Back" onclick="javaScript:backQuesFun();" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>


function checkRandom(){
	if(document.getElementById('paraFrm_randomCheck').checked){
		if(document.getElementById('paraFrm_showNoQues').value == ""){		
			document.getElementById('paraFrm_showNoQues').value = "0";
		}
		document.getElementById('paraFrm_showNoQues').disabled=false;
		document.getElementById("paraFrm_equalEachMarkChk").checked = "checked";
		funEqualMark();
		document.getElementById("paraFrm_equalEachMarkType").disabled=true;
		
		var noque = document.getElementById('paraFrm_showNoQues').value;
		var mark = document.getElementById("paraFrm_equalEachMark").value
		totmark = noque * mark;
		document.getElementById("paraFrm_totalMark").value = totmark;
	}else{
		document.getElementById('paraFrm_showNoQues').value = "";
		document.getElementById('paraFrm_showNoQues').disabled=true;
		document.getElementById("paraFrm_equalEachMarkType").disabled=false;
	}
}
 
checkRandom();

function setTotalMarks(){
	if(document.getElementById('paraFrm_randomCheck').checked){
		if(document.getElementById('paraFrm_showNoQues').value == ""){		
			document.getElementById('paraFrm_showNoQues').value = "0";
		}
		var noque = document.getElementById('paraFrm_showNoQues').value;
		var mark = document.getElementById("paraFrm_equalEachMark").value
		totmark = noque * mark;
		document.getElementById("paraFrm_totalMark").value = totmark;
	}
}

function addQuestion(){
 	var quesName = document.getElementById("paraFrm_quesName").value;
 	if(quesName==""){
 		alert("Please select " + document.getElementById("addQues").innerHTML);
 	}else{ 	
	   document.getElementById('paraFrm').action="ProgrammeMaster_addQuestion.action";
	   document.getElementById('paraFrm').submit();
	}
}

function callfordelete(queCode,i){
 var conf=confirm("Do you really want to remove this record ?");
 var cnt = '<s:property value="totalRecords" />';
	if(conf) {
        if(cnt == 0){
          	alert('Required One Record atleast');
          	return false;
        } else {
        	var ele = document.getElementsByName("quesMarkItt");
        	var mark = ele[i-1].value;
        	var totmark = document.getElementById("paraFrm_totalMark").value;
        	totmark = totmark - mark;
			document.getElementById("paraFrm_totalMark").value = totmark;
			document.getElementById("paraFrm_deleteQueCode").value=queCode;
			document.getElementById("paraFrm").action="ProgrammeMaster_deleteQues.action";
    		document.getElementById("paraFrm").submit();
   		}
    }
}

function saveQuesFun(){

	var total ='<s:property value="totalRecords" />';
	var ele = document.getElementsByName("quesCodeItt");
	if(ele.length == 0 || total == null || total == "" || eval(total)==0 ){
		alert("Please add atleast one question");
		return false;
	}
	
	if(document.getElementById('paraFrm_randomCheck').checked){
		if(document.getElementById("paraFrm_equalEachMarkChk").checked == false) {
			alert("Please select \"" + document.getElementById("equalMark").innerHTML + "\"");
			return false;
		}
	}else{
	if(document.getElementById("paraFrm_equalEachMarkChk").checked == false) {
		if(document.getElementById("paraFrm_equalEachMarkType").checked == false) {
			alert("Please select either \"" + document.getElementById("equalMark").innerHTML + "\" or \"" + document.getElementById("equalMarkType").innerHTML + "\"");
			return false;
		}
	}
	}
	
	if(document.getElementById("paraFrm_equalEachMarkChk").checked) {
		var equalmark = document.getElementById('paraFrm_equalEachMark').value;
		if(equalmark == ""){
			alert("Please insert " + document.getElementById("equalMark").innerHTML);
			return false;
		}else if(equalmark==0){
			alert(document.getElementById("equalMark").innerHTML + " should be greater than 0" );
			return false;
		}
	}	
	
	if(document.getElementById("paraFrm_equalEachMarkType").checked) {
		var easyMarkQue = document.getElementById('paraFrm_easyMarkQue').value;
		var mediumMarkQue = document.getElementById('paraFrm_mediumMarkQue').value;
		var hardMarkQue = document.getElementById('paraFrm_hardMarkQue').value;
		if(easyMarkQue == ""){
			alert("Please enter " + document.getElementById("easy").innerHTML );
			return false;
		}else if(easyMarkQue==0){
			alert(document.getElementById("easy").innerHTML + " should be greater than 0" );
			return false;
		}
		if(mediumMarkQue == ""){
			alert("Please enter " + document.getElementById("medium").innerHTML);
			return false;
		}else if(mediumMarkQue==0){
			alert(document.getElementById("medium").innerHTML + " should be greater than 0" );
			return false;
		}
		if(hardMarkQue == ""){
			alert("Please enter " + document.getElementById("hard").innerHTML);
			return false;
		}else if(hardMarkQue==0){
			alert(document.getElementById("hard").innerHTML + " should be greater than 0" );
			return false;
		}
	}
	
	if(document.getElementById("paraFrm_equalNegaMarkType").checked) {
		var easyNegaMarkQ = document.getElementById('paraFrm_easyNegaMarkQ').value;
		var mediumNegaMarkQ = document.getElementById('paraFrm_mediumNegaMarkQ').value;
		var hardNegaMarkQ = document.getElementById('paraFrm_hardNegaMarkQ').value;
		
		if(easyNegaMarkQ == ""){
			alert("Please insert " + document.getElementById("easy").innerHTML);
			return false;
		}else if(easyNegaMarkQ==0){
			alert(document.getElementById("easy").innerHTML + " should be greater than 0" );
			return false;
		}
		if(mediumNegaMarkQ == ""){
			alert("Please insert " + document.getElementById("medium").innerHTML);
			return false;
		}else if(mediumNegaMarkQ==0){
			alert(document.getElementById("medium").innerHTML + " should be greater than 0" );
			return false;
		}
		if(hardNegaMarkQ == ""){
			alert("Please insert " + document.getElementById("hard").innerHTML);
			return false;
		}else if(hardNegaMarkQ==0){
			alert(document.getElementById("hard").innerHTML + " should be greater than 0" );
			return false;
		}
	}
	
	var no = document.getElementById("paraFrm_showNoQues").value;
	if(document.getElementById('paraFrm_randomCheck').checked){
		if(no==null || no==""){
			alert("Please enter " + document.getElementById("showQues").innerHTML);
			return false;
		} 
		if(parseInt(no) <= 0 ){
			alert(document.getElementById("showQues").innerHTML + " should be greater than 0");
			return false;
		}
		if(parseInt(no) > (parseInt(total))) {
			alert(document.getElementById("showQues").innerHTML + " should not be greater than total no. of question");
			return false;
		}
	}
	var ele = document.getElementsByName("quesMarkItt");
	for(i=0; i< ele.length; i++){
		//ele[i].value = mark;
		if(ele[i].value == "" || isNaN(ele[i].value)){
			alert("Please insert marks");
			return false;
		}
		
	}
	var totmark = parseInt(document.getElementById('paraFrm_totalMark').value);
	var passmark = parseInt(document.getElementById('paraFrm_passQuesMark').value);
	if(passmark <= 0 ) {
		alert("Passing marks should be greater than 0");
	} else if(totmark < passmark){
		alert("Passing marks should be less than total marks");
		return false;
	} else {
		//setTotalMark();
		var ele = document.getElementsByName("saveBtn");
		for(i=0; i< ele.length; i++){
			ele[i].disabled="true";
		}		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ProgrammeMaster_saveQuestion.action';
		document.getElementById('paraFrm').submit();
		
	}
   return true;
}


function backQuesFun(){
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ProgrammeMaster_backQuestion.action';
    document.getElementById('paraFrm').submit();
}

function funEqualMark(){
	try{
	if(document.getElementById("paraFrm_equalEachMarkChk").checked) {
		document.getElementById("paraFrm_equalEachMark").disabled = false;
		document.getElementById("paraFrm_equalEachMarkType").checked = false;
		document.getElementById("paraFrm_easyMarkQue").value="";
		document.getElementById("paraFrm_mediumMarkQue").value="";
		document.getElementById("paraFrm_hardMarkQue").value="";
		document.getElementById("paraFrm_easyMarkQue").disabled = true;
		document.getElementById("paraFrm_mediumMarkQue").disabled = true;
		document.getElementById("paraFrm_hardMarkQue").disabled = true;	
	} else {
		document.getElementById("paraFrm_equalEachMark").value="";
		document.getElementById("paraFrm_equalEachMark").disabled = true;
	}
	
	}catch(e){
		alert(e);
	}
}

funEqualMark();

function funEqualMarkType(){
	if(document.getElementById("paraFrm_equalEachMarkType").checked) {
		document.getElementById("paraFrm_easyMarkQue").disabled = false;
		document.getElementById("paraFrm_mediumMarkQue").disabled = false;
		document.getElementById("paraFrm_hardMarkQue").disabled = false;
		document.getElementById("paraFrm_equalEachMarkChk").checked = false;	
		document.getElementById("paraFrm_equalEachMark").value="";
		document.getElementById("paraFrm_equalEachMark").disabled = true; 
		if(document.getElementById("paraFrm_easyMarkQue").value==""){
			document.getElementById("paraFrm_easyMarkQue").value="0";
		}
		if(document.getElementById("paraFrm_mediumMarkQue").value==""){
			document.getElementById("paraFrm_mediumMarkQue").value="0";
		}
		if(document.getElementById("paraFrm_hardMarkQue").value==""){
			document.getElementById("paraFrm_hardMarkQue").value="0";
		}
		setEqualMarksType('Easy');
		setEqualMarksType('Medium');
		setEqualMarksType('Hard');
		
	} else {
		document.getElementById("paraFrm_easyMarkQue").value="";
		document.getElementById("paraFrm_mediumMarkQue").value="";
		document.getElementById("paraFrm_hardMarkQue").value="";
		document.getElementById("paraFrm_easyMarkQue").disabled = true;
		document.getElementById("paraFrm_mediumMarkQue").disabled = true;
		document.getElementById("paraFrm_hardMarkQue").disabled = true;	 	
	}
	
}
funEqualMarkType();

function funEqualNegaMarkType(){
	if(document.getElementById("paraFrm_equalNegaMarkType").checked) {	  
		document.getElementById("paraFrm_easyNegaMarkQ").disabled = false;
		document.getElementById("paraFrm_hardNegaMarkQ").disabled = false;
		document.getElementById("paraFrm_mediumNegaMarkQ").disabled = false;	 	
	}else {
		document.getElementById("paraFrm_easyNegaMarkQ").value="";
		document.getElementById("paraFrm_hardNegaMarkQ").value="";
		document.getElementById("paraFrm_mediumNegaMarkQ").value="";	
		document.getElementById("paraFrm_easyNegaMarkQ").disabled = true;
		document.getElementById("paraFrm_hardNegaMarkQ").disabled = true;
		document.getElementById("paraFrm_mediumNegaMarkQ").disabled = true;	
	}
}
funEqualNegaMarkType();

function setEqualMarks() {
	var totmark = 0; 
	var mark = document.getElementById("paraFrm_equalEachMark").value;
	if(mark==null || mark==""){
		mark=0;
	}
	var ele = document.getElementsByName("quesMarkItt");
	for(i=0; i< ele.length; i++){
		ele[i].value = mark;
	}	
	if(document.getElementById('paraFrm_randomCheck').checked){
		setTotalMarks();
	} else {
		if(document.getElementById("paraFrm_equalEachMarkChk").checked) {
			
			var totQue = document.getElementById("paraFrm_totalQuestion").value;
			if(totQue==null || totQue==""){
				totQue=0;
			}
			totmark = parseInt(totQue) * parseInt(mark);
					
			document.getElementById("paraFrm_totalMark").value = totmark;
		}
	}
}
setEqualMarks();

function setEqualMarksType(type){
	var totmark = 0; // document.getElementById("paraFrm_totalMark").value;
	/*if(totmark == null || totmark==""){
		totmark=0;
	}*/
	
	if(document.getElementById("paraFrm_equalEachMarkType").checked) {
		
			var mark = document.getElementById("paraFrm_easyMarkQue").value;
			if(mark==null || mark==""){
				mark=0;
			}
			var ele = document.getElementsByName("quesMarkItt");
			var ele1 = document.getElementsByName("quesLevelItt");
			for(i=0; i< ele.length; i++){
				if(ele1[i].value == "Easy"){
					ele[i].value = mark;
				}
			}
			
			var totQue = document.getElementById("paraFrm_totalEasyQuestion").value;
			if(totQue==null || totQue==""){
				totQue=0;
			}
			totmark = parseInt(totmark) + (parseInt(totQue) * parseInt(mark));
		
			var mark = document.getElementById("paraFrm_mediumMarkQue").value;
			if(mark==null || mark==""){
				mark=0;
			}
			var ele = document.getElementsByName("quesMarkItt");
			var ele1 = document.getElementsByName("quesLevelItt");
			for(i=0; i< ele.length; i++){
				if(ele1[i].value == "Medium"){
					ele[i].value = mark;
				}				
			}
			
			var totQue = document.getElementById("paraFrm_totalMediumQuestion").value;
			if(totQue==null || totQue==""){
				totQue=0;
			}
			totmark = parseInt(totmark) + (parseInt(totQue) * parseInt(mark));	
		
			var mark = document.getElementById("paraFrm_hardMarkQue").value;
			if(mark==null || mark==""){
				mark=0;
			}
			var ele = document.getElementsByName("quesMarkItt");
			var ele1 = document.getElementsByName("quesLevelItt");
			for(i=0; i< ele.length; i++){
				if(ele1[i].value == "Hard"){
					ele[i].value = mark;
				}				
			}
			
			var totQue = document.getElementById("paraFrm_totalHardQuestion").value;
			if(totQue==null || totQue==""){
				totQue=0;
			}
			totmark = parseInt(totmark) + (parseInt(totQue) * parseInt(mark));	
	
		document.getElementById("paraFrm_totalMark").value = totmark;	
	}
	
}

setEqualMarksType('Easy');
setEqualMarksType('Medium');
setEqualMarksType('Hard');


	function orderUp(order, ii){
		var temp = ""; 
		if(document.getElementById('quesIttTd' +(ii-1)) == null ){
			alert("This is first question you cannot go up");
		} else {
		temp = document.getElementById('quesTypeIttTd'+ii).innerHTML;
		document.getElementById('quesTypeIttTd'+ii).innerHTML = document.getElementById('quesTypeIttTd' +(ii-1)).innerHTML;
		document.getElementById('quesTypeIttTd' +(ii-1)).innerHTML = temp;
		
		temp = document.getElementById('quesLevelIttTd'+ii).innerHTML;
		document.getElementById('quesLevelIttTd'+ii).innerHTML = document.getElementById('quesLevelIttTd' +(ii-1)).innerHTML;
		document.getElementById('quesLevelIttTd' +(ii-1)).innerHTML = temp;
		/*
		temp = document.getElementById('quesMarkIttTd'+ii).innerHTML;
		document.getElementById('quesMarkIttTd'+ii).innerHTML = document.getElementById('quesMarkIttTd' +(ii-1)).innerHTML;
		document.getElementById('quesMarkIttTd' +(ii-1)).innerHTML = temp;
		*/
		var ele = document.getElementsByName("quesMarkItt");
		temp = ele[ii-1].value;
		ele[ii-1].value = ele[ii-2].value;
		ele[ii-2].value = temp; 
		
		temp = document.getElementById('quesDeleteIttTd'+ii).innerHTML;
		document.getElementById('quesDeleteIttTd'+ii).innerHTML = document.getElementById('quesDeleteIttTd' +(ii-1)).innerHTML;
		document.getElementById('quesDeleteIttTd' +(ii-1)).innerHTML = temp;
		
		temp = document.getElementById('quesIttTd'+ii).innerHTML;
		document.getElementById('quesIttTd'+ii).innerHTML = document.getElementById('quesIttTd' +(ii-1)).innerHTML;
		document.getElementById('quesIttTd' +(ii-1)).innerHTML = temp;
		}
	}

	function orderDown(order, ii){
		var temp = ""; 
		if(document.getElementById('quesIttTd' +(ii+1)) == null ){
			alert("This is last question you cannot go down");
		} else {
		temp = document.getElementById('quesTypeIttTd'+ii).innerHTML;
		document.getElementById('quesTypeIttTd'+ii).innerHTML = document.getElementById('quesTypeIttTd' +(ii+1)).innerHTML;
		document.getElementById('quesTypeIttTd' +(ii+1)).innerHTML = temp;
		
		temp = document.getElementById('quesLevelIttTd'+ii).innerHTML;
		document.getElementById('quesLevelIttTd'+ii).innerHTML = document.getElementById('quesLevelIttTd' +(ii+1)).innerHTML;
		document.getElementById('quesLevelIttTd' +(ii+1)).innerHTML = temp;
		/*
		temp = document.getElementById('quesMarkIttTd'+ii).innerHTML;
		document.getElementById('quesMarkIttTd'+ii).innerHTML = document.getElementById('quesMarkIttTd' +(ii+1)).innerHTML;
		document.getElementById('quesMarkIttTd' +(ii+1)).innerHTML = temp;
		*/
		var ele = document.getElementsByName("quesMarkItt");
		temp = ele[ii-1].value;
		ele[ii-1].value = ele[ii-2].value;
		ele[ii-2].value = temp; 
		
		temp = document.getElementById('quesDeleteIttTd'+ii).innerHTML;
		document.getElementById('quesDeleteIttTd'+ii).innerHTML = document.getElementById('quesDeleteIttTd' +(ii+1)).innerHTML;
		document.getElementById('quesDeleteIttTd' +(ii+1)).innerHTML = temp;
		
		temp = document.getElementById('quesIttTd'+ii).innerHTML;
		document.getElementById('quesIttTd'+ii).innerHTML = document.getElementById('quesIttTd' +(ii+1)).innerHTML;
		document.getElementById('quesIttTd' +(ii+1)).innerHTML = temp;
		}
	}
	
	function f9que(event){
		var quesCodeItt = "";
		
		var ele = document.getElementsByName("quesCodeItt");
		for(i=0; i<ele.length; i++){
			if(i==0){
				quesCodeItt +=  ele[i].value;
			} else {
				quesCodeItt +=  "," + ele[i].value;
			}
		}
		
		var queModuleCodeItt = "";
		var queSectionCodeItt = "";
		var programID = '<s:property value="queProgrameCode"/>';
		if(document.getElementById("paraFrm_queModuleCodeItt") != null){
			queModuleCodeItt = document.getElementById("paraFrm_queModuleCodeItt").value;
		}
		if(document.getElementById("paraFrm_queSectionCodeItt") != null){
			queSectionCodeItt = document.getElementById("paraFrm_queSectionCodeItt").value;
		}
		callDropdown('paraFrm_quesName',200,250,'ProgrammeMaster_f9Question.action?queModuleCodeItt='+queModuleCodeItt+'&queSectionCodeItt='+queSectionCodeItt+'&quesCodeItt='+ quesCodeItt + '&programID='+ programID,event,'false','','right');
								
	}

</script>