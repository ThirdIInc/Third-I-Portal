<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript" src="..pages/common/include/javascript/sorttable.js">
</script>
<script type="text/javascript" src="../pages/recruitment/Ajax.js"></script>
<s:form action="TestTemplate" validate="true" id="paraFrm" name="testTempForm" theme="simple">
<table class="formbg" width="100%"><!-- start of test template -->
<s:hidden name="radioStatus"/>
<s:hidden name="testTemplateCode" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="myPage"/>
	<s:hidden name="show" />
	<s:hidden name="compLevel" />
	<s:hidden name="compLevel1" />
	<s:hidden name="questionFrom" />
	<s:hidden name="buttonPanelcode" />
	<s:hidden name="cCode" />
	<s:hidden name="sCode" />
	<s:hidden name="subCode" />
	<s:hidden name="catCode" />
	<s:hidden name="complexicity" /> 	
	<s:hidden name="cnt"/>
	<s:hidden name="qusSubject" /> 	
	<s:hidden name="qusName"/>
	<s:hidden name="selectQuCode"/>
	<s:hidden name="paraId"/>
	<s:hidden name="hardCompLevel" />
	<s:hidden name="mediumCompLevel" />
	<s:hidden name="easyCompLevel" />
	<s:hidden name="correctCheck" />
	<s:hidden name="addQuestionList"/>
	<s:hidden name="notAvailable"/>
	
	<s:hidden name="cntHard"/>
	<s:hidden name="cntMedium"/>
	<s:hidden name="cntEasy"/>
	<s:hidden name="totalnoOfQus"/>
	
	<s:hidden name="markHard"/>
	<s:hidden name="markMedium"/>
	<s:hidden name="markEasy"/>
	<s:hidden name="totalMarkss"/>
	<s:hidden name="counterVar"/>
	<s:hidden name="hiddenQuestionCode"/>
	<s:hidden name="hiddenCode"/>
	<s:hidden name="recCount"/>
	
	<s:hidden name="equalWeightageFlag" />
	<s:hidden name="questionListFlag" />
	<s:hidden name="chFlag"/>
	<s:hidden name="checkedQuestions"/>
	<s:hidden name="addToListQuestions"/>
	<s:hidden name="scriptPageNo" value="10"/>
	<s:hidden name="actAddQuesPage" id="actAddQuesPage"/>
<tr>
	<td width="100%" colspan="3">
		<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
			<tr>
				<td width="4%" valign="bottom" class="txt"><strong
					class="formhead"><img
					src="../pages/common/css/default/images/review_shared.gif"
					width="25" height="25" /></strong></td>
				<td width="93%" class="txt"><strong class="text_head">Test
					Template </strong></td>
				<td width="3%" valign="top" class="txt">
				<div align="right"><img
					src="../pages/common/css/default/images/help.gif" width="16"
					height="16" />
				</div>
				</td>
			</tr>
		</table>
	</td>
</tr>
<s:hidden name="listFlag"/>
<s:if test="listFlag"><!--Start of Test Template initial list  -->
			<tr>
				<td  width="100%"  colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
							
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td  width="100%" colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="3">
						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
											<td height="27" class="text_head"><strong>Test Template </strong></td>
                              <%
								int totalPage = (Integer) request.getAttribute("abc");
								int pageNo = (Integer) request.getAttribute("xyz");
							%>
							<s:if test="noData"> </s:if> <s:else>
                                          <td align="right"><b>Page:</b>
							 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							 
							</td>
					 </s:else>	  
		      	  </tr> 		
						</table>
						</td>
					</tr>

					<tr>
						<td width="100%" colspan="3" >
						<s:hidden name="testName" /><s:hidden
							name="testType" /><s:hidden name="testDuration" /> <s:hidden
							name="totalNoOfQues" /><s:hidden name="testTotalMarks" /><s:hidden
							name="passingMark" />

						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="sortable">
							<tr>
								<td width="6%" valign="top" class="formth"><label
									class="set" name="serial.no" id="serial.no"
									ondblclick="callShowDiv(this);"><b><%=label.get("serial.no")%></b></label></td>
								<td width="15%" valign="top" class="formth"><label
									class="set" name="test.name" id="test.name1"
									ondblclick="callShowDiv(this);"><b><%=label.get("test.name")%></b></label></td>
								<td width="10%" valign="top" class="formth"><label
									class="set" name="test.duration" id="test.duration1"
									ondblclick="callShowDiv(this);"><b><%=label.get("test.duration")%></b></label></td>
								<td width="10%" valign="top" class="formth"><label
									class="set" name="test.type" id="test.type1"
									ondblclick="callShowDiv(this);"><b><%=label.get("test.type")%></b></label></td>
								<td width="15%" valign="top" class="formth"><label
									class="set" name="total.ques" id="total.ques1"
									ondblclick="callShowDiv(this);"><b><%=label.get("total.ques")%></b></label></td>
								<td width="10%" valign="top" class="formth"><label
									class="set" name="total.marks" id="total.marks1"
									ondblclick="callShowDiv(this);"><b><%=label.get("total.marks")%></b></label></td>
								<td width="10%" valign="top" class="formth"><label
									class="set" name="passing.marks" id="passing.marks1"
									ondblclick="callShowDiv(this);"><b><%=label.get("passing.marks")%></b></label></td>
							</tr>

							<s:if test="noData">
								<tr>
									<td width="100%" colspan="6" align="center"><font
										color="red">There is no data to display</font></td>
								</tr>
							</s:if>

							<%!int i = 0;%>
							<%
									int k = 1;
									int c = 0;
							%>
							<%
									int cnt = pageNo * 20 - 20;
									int i = 0;
							%>

							<s:iterator value="list">
								<tr <%if(cnt%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}cnt++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=cnt%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="testTemplateCode" />');">
									
									<td width="6%" class="sortabletd" align="center"><s:property
										value="srNo" /></td>
									<td width="15%" class="sortabletd"><s:property
										value="testName" /></td>
									<td width="15%" class="sortabletd" align="center"><s:property
										value="testDuration" /></td>
									<td width="15%" class="sortabletd"><s:property
										value="testType" /></td>
									<td width="15%" class="sortabletd" align="center"><s:property
										value="totalQuestions" /></td>
									<td width="10%" class="sortabletd" align="center"><s:property
										value="testTotalMarks" /></td>
									<td width="10%" class="sortabletd" align="center"><s:property
										value="passingMark" />
										
									</td>
								</tr>
								<%
										k++;
										c++;
								%>
							</s:iterator>

							<%
									i = k;
									k = 1;
							%>
						</table>
						<input type="hidden" name="count" id="count1" value="<%=c%>" /></td>
					</tr>

				</table>
				</td>
			</tr>
		</s:if><!--End of Test Template initial list  -->
		
		
		<s:else><!--Start of Test Template form  -->
		<tr>
			<td  width="100%"  colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"><!--Start of table 1  -->
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
				</table><!--End of table 1  -->
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!-- table 2 -->
				<tr>
					<td colspan="4"><strong>Test Details</strong>
					</td>
				</tr>
				<tr>
					<td  width="20%"><label class="set"
						name="test.name" id="test.name" ondblclick="callShowDiv(this);"><%=label.get("test.name")%></label> :<font color="red">*</font></td>
					<td  width="30%"><s:textfield name="testName"
						size="25" maxlength="30" onkeypress="return allCharacters();" />
					</td>
					<td  width="20%"><label class="set"
						name="test.duration" id="test.duration"
						ondblclick="callShowDiv(this);"><%=label.get("test.duration")%></label> :<font
						color="red">*</font></td>
					<td  width="30%"><s:textfield
						name="testDuration" size="25" maxlength="5"
						onkeypress="return numbersWithColon();" />
					</td>
				</tr>
				<tr>
					<td  width="20%"><label class="set"
						name="test.type" id="test.type" ondblclick="callShowDiv(this);"><%=label.get("test.type")%></label> :<font
						color="red">*</font></td>
					<td  width="30%"><s:select theme="simple"
						name="testType" cssStyle="width:165" onchange="disableScore();"
						disabled="false"
						list="#{'':'Select','O':'Objective','S':'Subjective','B':'Both'}" />
					</td>
					<td  width="20%"><label class="set"
						name="online.score" id="online.score"
						ondblclick="callShowDiv(this);"><%=label.get("online.score")%></label> :</td>
					<td  width="30%"><s:checkbox name="enableScore" /></td>
				</tr>
				<tr>
					<td  valign="top" colspan="1"><label class="set"
						name="instruction" id="instruction"
						ondblclick="callShowDiv(this);"><%=label.get("instruction")%></label> : </td>
					<td  nowrap="nowrap" colspan="4"><s:textarea theme="simple"
						name="instructionNotes" rows="2" cols="70" onkeyup="return callLength('maxchar');" />
						<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_instructionNotes','instruction','','500','500');" >&nbsp;&nbsp;
						Remaining Chars :<s:textfield name="maxchar" readonly="true" size="5" />
					</td>
				</tr>
				</table><!-- table 2 -->
			</td>
		</tr>
		<tr>
			<td  width="100%"  colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Start of table 3  -->
				<tr>
					<td colspan="4"><strong>Marking System</strong>
					</td>
				</tr>
				<tr>
					<td  width="20%"><label class="set"
						name="total.noques" id="total.noques" ondblclick="callShowDiv(this);"><%=label.get("total.noques")%></label> :<font color="red">*</font></td>
					<td  width="30%"><s:textfield name="totalNoOfQues"
						size="25" maxlength="3" onkeypress="return numbersOnly();" onkeyup="calMarksAllocated()" />
					</td>
					<td  width="20%"><label class="set"
						name="total.mark" id="total.mark"
						ondblclick="callShowDiv(this);"><%=label.get("total.mark")%></label> :<font
						color="red">*</font></td>
					<td  width="30%"><s:textfield
						name="testTotalMarks" size="25" maxlength="4"
						onkeypress="return numbersOnly();" onkeyup="calMarksAllocated()"  />
					</td>
				</tr>
				<tr>
					<td  width="20%"><label class="set"
						name="passing.marks" id="passing.marks" ondblclick="callShowDiv(this);"><%=label.get("passing.marks")%></label> :<font color="red">*</font></td>
					<td  colspan="3"><s:textfield name="passingMark"
						size="25" maxlength="4" onkeypress="return numbersOnly();" onkeyup="checkPassingMark()" />
					</td>
				</tr>
				
				
				<s:hidden name="equalDistriRadio"/>
				<s:hidden name="defineDistriRadio"/>
				
				<tr><!-- Start of radio for equal and Define Distribution -->
					<td  colspan="4"><input type="radio" value="Define" name="radioOption" <s:property value="defineDistriRadio"/> onclick="callRadioOptionFun('Define');" ><strong><label  class = "set"  id="define.distribution" 
						 name="define.distribution" ondblclick="callShowDiv(this);"><%=label.get("define.distribution")%></label></strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<div id="correctAns">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"><!--Table 5  -->
								<tr>
									<td  colspan="4"><label class="set"
										name="correct.marks" id="correct.marks" ondblclick="callShowDiv(this);"><%=label.get("correct.marks")%></label> :<font color="red">*</font></td>
								</tr>
								<tr>
									<td  width="5%" align="right" colspan="1"><label class="set"
										name="difficulty.level" id="difficulty.level" ondblclick="callShowDiv(this);"><%=label.get("difficulty.level")%></label> :-
									</td>
									<td  width="20%" align="left" colspan="3">&nbsp;<label class="set"
										name="hard.marks" id="hard.marks" ondblclick="callShowDiv(this);"><%=label.get("hard.marks")%></label>:
										<s:textfield name="marksForHard" size="2"
												maxlength="3" onkeypress="return numbersOnly();" onkeyup="validateQuestionInTest()" />&nbsp;&nbsp;
										<label class="set" name="medium.marks"
												id="medium.marks" ondblclick="callShowDiv(this);"><%=label.get("medium.marks")%></label> :
										<s:textfield name="marksForMedium"
												size="2" maxlength="3" onkeypress="return numbersOnly();" onkeyup="validateQuestionInTest()" />&nbsp;&nbsp;
										<label class="set" name="easy.marks"
											   id="easy.marks" ondblclick="callShowDiv(this);"><%=label.get("easy.marks")%></label> :
										<s:textfield name="marksForEasy" size="2"
												maxlength="3" onkeypress="return numbersOnly();" onkeyup="validateQuestionInTest()"/>										
									</td>
								</tr>
							</table><!--Table 5 -->
						</div>
					</td>
				</tr>
				
				<tr><!-- Start of radio for equal and Define Distribution -->
					<td  colspan="4"><input type="radio" value="Equal" name="radioOption" <s:property value="equalDistriRadio"/>
						 onclick="callRadioOptionFun('Equal');calMarksAllocated();" ><label  class = "set"  id="equal.distribution" 
						 name="equal.distribution" ondblclick="callShowDiv(this);"><strong><%=label.get("equal.distribution")%></strong></label>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<div id="allocateForEachAns">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"><!--Table 4  -->
								<tr>
									<td  width="7%"><label class="set"
										name="marks.allocated" id="marks.allocated" ondblclick="callShowDiv(this);"><%=label.get("marks.allocated")%></label> :</td>
									<td  width="30%"><s:textfield name="marksAllocatedForEach"
										size="25" maxlength="4" onkeypress="return numbersOnly();" 
										readonly="true"/>
									</td>
								</tr>
							</table><!--Table 4  -->
						</div>
					</td>
				</tr>
				
				<tr>
					<td  colspan="4"><label class="set"
						name="wrong.marks" id="wrong.marks" ondblclick="callShowDiv(this);"><%=label.get("wrong.marks")%></label> :-</td>
				</tr>
				<tr>
					<td  width="4%" align="right" colspan="1"><label class="set"
						name="difficulty.level" id="difficulty.level" ondblclick="callShowDiv(this);"><%=label.get("difficulty.level")%></label> :-
					</td>
					<td  width="20%" align="left" colspan="3">&nbsp;<label class="set"
						name="hard.marks" id="hard.marks" ondblclick="callShowDiv(this);"><%=label.get("hard.marks")%></label>:
						<s:textfield name="wrongmarksForHard" size="2"
								maxlength="3" onkeypress="return numbersOnly();" />&nbsp;&nbsp;
						<label class="set" name="medium.marks"
								id="medium.marks" ondblclick="callShowDiv(this);"><%=label.get("medium.marks")%></label> :
						<s:textfield name="wrongmarksForMedium"
								size="2" maxlength="3" onkeypress="return numbersOnly();" />&nbsp;&nbsp;
						<label class="set" name="easy.marks"
							   id="easy.marks" ondblclick="callShowDiv(this);"><%=label.get("easy.marks")%></label> :
						<s:textfield name="wrongmarksForEasy" size="2" 
								maxlength="3" onkeypress="return numbersOnly();" />										
					</td>
				</tr>
				<tr>
					<td  width="20%"><label class="set"
						name="no.marks" id="no.marks" ondblclick="callShowDiv(this);"><%=label.get("no.marks")%></label> :</td>
					<td  colspan="3"><s:textfield name="marksForNoAns"
						size="25" maxlength="4" onkeypress="return numbersOnly();" />
					</td>
				</tr>
				</table><!--End of table 3  -->
			</td>
		</tr>
		<tr>
			<td  width="100%"  colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Question Details table 6  -->
					<tr>
						<td width="100%" class="text_head"><strong>Question Details</strong></td>
					</tr>
					<s:hidden name="addQuestion"/>
					<s:hidden name="randomQuestion"/>
					<tr>
						<td  class="formtext" width="100%" >
							<input name="question" type="radio"  value="add" <s:property value="addQuestion"/>	onclick="enableQuestion('add');" id="idcheckadd"/>
                    		<label class="set" name="from.qesbank"
									id="from.qesbank" ondblclick="callShowDiv(this);"><%=label.get("from.qesbank")%></label>
                    	    <input name="question" type="radio" value="random" <s:property value="randomQuestion"/> onclick="enableQuestion('random');" id="idcheckrandom"/>
                    	 	<label class="set" name="random.ques"
									id="random.ques" ondblclick="callShowDiv(this);"><%=label.get("random.ques")%></label>
						</td>
							<input type="hidden" id="add.ques">
					</tr>
					<tr id="reqSearchDiv">
								<td  class="formtext" width="100%" >
								<table width="100%" border="0"  align="center" cellpadding="1"
										cellspacing="1">
							<tr>
							<td valign="top">
								<label class="set" name="subject" id="subject" 
									ondblclick="callShowDiv(this);"><%=label.get("subject")%></label> :<font
									color="red">*</font></td>
								<td  width="30%"><s:select name="subject"
									cssStyle="width:150" theme="simple" size="5" multiple="true"
									 headerValue="Select" list="subjectMap" onchange='getValue1(this);'  />
									<s:hidden name="hiddenSubject" id="hiddenSubject" />
									<s:hidden name="hidAjaxSubject" id="hidAjaxSubject"/></td>
								<td  class="formtext" width="20%" valign="top">
								<label class="set" name="category" id="category"
									ondblclick="callShowDiv(this);"><%=label.get("category")%></label> :<font
									color="red">*</font></td>
								<td  width="30%"><s:select name="category"
									cssStyle="width:150" size="5" multiple="true" 
									headerValue="Select" list="categoryMap"  /><s:hidden
									name="hiddenCategory" /></td>
									</tr>
								</table></td>
							</tr>

							<tr id="random">
								<td class="formtext" align="center">
							<input type="button" class="token" value="Assign Questions" onclick="return showQuestionDetails('go');" /></td>
							</tr>
							
							<tr id="addQuestionRow">
									<td  class="formtext" width="20%" valign="top" align="center">
										<input name="selectQue" type="button" class="token"
											value="Select Questions" onclick="selectQuestion();" />
									</td>
									<s:hidden name="testReqCode" />
									   </tr>
							
							<tr>
								<td width="100%">
									<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Question List table 7  -->
									<tr>
										<td width="70%" colspan="3"><strong class="text_head">Question Assignment</strong></td>
										<td width="30%" align="right">&nbsp;</td>
									</tr>
									<tr>
										<td width="30%"><label	class="set" name="hard.ques" id="hard.ques"	
										ondblclick="callShowDiv(this);"><%=label.get("hard.ques")%></label> :</td>
										<td width="30%"><s:textfield name="cntRandomHard" readonly="true" 
										cssStyle="border:none;" id="cntRandomHard"/></td>
										 <s:if test="equalWeightage"><td>&nbsp;</td></s:if>
										 <s:else> 
											<td width="30%"><label	class="set" name="marks.hardques" 
											id="marks.hardques"	ondblclick="callShowDiv(this);"><%=label.get("marks.hardques")%></label> :</td>
											<td width="30%"><s:textfield name="markRandomHard" readonly="true"
											 cssStyle="border:none;" id="markRandomHard"/></td>
										 </s:else>
									</tr>
									<tr>
										<td width="30%"><label	class="set" name="medium.ques" id="medium.ques"	
										ondblclick="callShowDiv(this);"><%=label.get("medium.ques")%></label> :</td>
										<td width="30%"><s:textfield name="cntRandomMedium" readonly="true" 
										cssStyle="border:none;" id="cntRandomMedium"/></td>
										 <s:if test="equalWeightage"><td>&nbsp;</td></s:if>
										 <s:else> 
										<td width="30%"><label	class="set" name="marks.mediumques" 
										id="marks.mediumques"	ondblclick="callShowDiv(this);"><%=label.get("marks.mediumques")%></label> :</td>
										<td width="30%"><s:textfield name="markRandomMedium" readonly="true" 
										cssStyle="border:none;" id="markRandomMedium"/></td>
										 </s:else>
									</tr>
									<tr>
										<td width="30%"><label	class="set" name="easy.ques" id="easy.ques"	
										ondblclick="callShowDiv(this);"><%=label.get("easy.ques")%></label> :</td>
										<td width="30%"><s:textfield name="cntRandomEasy" readonly="true" 
										cssStyle="border:none;" id="cntRandomEasy"/></td>
										<s:if test="equalWeightage"><td>&nbsp;</td></s:if>
										 <s:else> 
										<td width="30%"><label	class="set" name="marks.easyques" 
										id="marks.easyques"	ondblclick="callShowDiv(this);"><%=label.get("marks.easyques")%></label> :</td>
										<td width="30%"><s:textfield name="markRandomEasy" readonly="true" 
										cssStyle="border:none;" id="markRandomEasy"/></td>
										 </s:else>
									</tr>
									<tr>
										<td width="30%"><b><label	class="set" name="total.listQues" id="total.listQues"	
										ondblclick="callShowDiv(this);"><%=label.get("total.listQues")%></label> :</b></td>
										<td width="30%"><b><s:textfield name="totalRandomnoOfQus" readonly="true"
										 cssStyle="border:none;" id="totalRandomnoOfQus"/></b></td>
										<s:if test="equalWeightage"><td>&nbsp;</td></s:if>
										 <s:else> 
										<td width="30%"><b><label	class="set" name="total.listMarks" id="total.listMarks"	
										ondblclick="callShowDiv(this);"><%=label.get("total.listMarks")%></label> :</b></td>
										<td width="30%"><b><s:textfield name="totalRandomMarkss" readonly="true" 
										cssStyle="border:none;" id="totalRandomMarkss"/></b>
										 </s:else>
									</tr>
									
									<tr id="randomList">
										<td colspan="4">
											<table width="100%" border="0" cellpadding="1" cellspacing="1" 
											class="sortable"><!--Question List iterator for random question table 8  -->
											<tr>
												<td width="8%" valign="top" class="formth"><label
													class="set" name="serial.no" id="serial.no"
													ondblclick="callShowDiv(this);"><b><%=label.get("serial.no")%></b></label></td>
												<td width="30%" valign="top" class="formth"><label
													class="set" name="sub.cat" id="sub.cat"
													ondblclick="callShowDiv(this);"><b><%=label.get("sub.cat")%></b></label></td>
												<td width="15%" valign="top" class="formth"><label
													class="set" name="complexi" id="complexi"
													ondblclick="callShowDiv(this);"><b><%=label.get("complexi")%></b></label></td>
												<td width="25%" valign="top" class="formth"><b><label
													class="set" name="avil.ques" id="avil.ques"
													ondblclick="callShowDiv(this);"><%=label.get("avil.ques")%></label></b></td>
												<td width="20%" valign="top" class="formth"><b><label
													class="set" name="test.ques" id="ques.test"
													ondblclick="callShowDiv(this);"><%=label.get("ques.test")%></label></b></td>
											</tr>
											<s:if test="notAvailable">
														<tr>
															<td width="100%" colspan="8" align="center"><font
																color="red">There is no data to display</font></td>
														</tr>
													</s:if>

													<%!int g = 0;%>
													<%!int j = 0;%>
													<%
															int h = 1;
															int f = 0;
													%>
													<s:iterator status="stat" value="questionList">
														<tr>
															<td width="8%" class="sortabletd" align="center"><%=h%></td>
															<td width="30%" class="sortabletd">
															<s:property	value="subjectCategory" /><s:hidden name="randomCatCode" />
															<s:hidden name="subjectCategory" id='<%="subjectCategory"+f %>'/>
															<s:hidden name="randomSubCode" id='<%="randomSubCode"+f %>'/></td>
															<td width="15%" class="sortabletd">
															<s:property value="RandomComplexicity" /> 
															<s:hidden 	name="RandomComplexicity" id='<%="RandomComplexicity"+f %>'/>
															<input type="hidden" name="complexicityCode" 
															 id="complexicityCode<%=f%>" value="<s:property value="complexicityCode" />" />
															</td>
															<td width="25%" class="sortabletd" align="center">
															<s:property value="availableQuestion" /> 
															<input type="hidden" value='<s:property value="availableQuestion" />'	
															name="availableQuestion" id="availableQuestion<%=f%>" /></td>
															<td width="20%" class="sortabletd">
															<s:textfield name="testQuestion"  id='<%="testQuestion"+f%>'
																onkeyup="validateQuestionInTest()" maxlength="3" onkeypress="return numbersOnly();" />
															
																</td>
														</tr>
														<%
																h++;
																f++;
														%>
													</s:iterator>
													
													<input type="hidden" name="rec" id="rec" value='<%=(f)%>' />				
													<%j = f;%>
											</table><!--Question List iterator for random question table 8  -->
										</td>
										<input type="hidden" name="count2" id="count2" value="<%=f%>" />
									</tr>
									
						<tr id="pageList">
							<td width="100%" colspan="4">
								<table width="99%" border="0" align="center" cellpadding="1"
								cellspacing="1">
									<tr>
										<%!int addListPages =0;%>
										<% try {
											addListPages = Integer.valueOf(String.valueOf(request.getAttribute("addQuestionTotalPages")));
											} catch (Exception e) {
												addListPages=0;
										 	}
										%>
                                        <td align="right" colspan="3" ><b>Page:</b>
						 					<a href="#" onclick="callAddQuesPage('1','F');"  >
											<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPage('P','P');" >
												<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
											</a> 
							   				 <input type ="text" name="addQuesPageNoField" id="addQuesPageNoField" theme="simple" size="3"    onkeypress="callPageAddQues(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=addListPages %>
						 		 			<a href="#" onclick="callAddQuesPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPage('<%=addListPages %>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
											</a>
										</td>
										<td colspan="1" align="right" width="10%">
		      	 				 		<input type="button" class="delete" value="Remove" onclick="removeQuestions();" />
		      	 				 		</td>
		      	 				 </tr> 
							</table>
						</td>
					</tr>
									
									<tr id="addList">
										<td colspan="4">
											<table width="100%" border="0" id="tblSelectQuestion" cellpadding="1" cellspacing="1" 
											class="sortable"><!--Question List iterator for add question table 9  -->
											<tr>
														<td width="8%" valign="top" class="formth"><label
															class="set" name="serial.no" id="serial.no"
															ondblclick="callShowDiv(this);"><b><%=label.get("serial.no")%></b></label></td>
														<td width="15%" valign="top" class="formth"><label
															class="set" name="sub.cat" id="sub.cat"
															ondblclick="callShowDiv(this);"><b><%=label.get("sub.cat")%></b></label></td>
															<td width="40%" valign="top" class="formth"><label
															class="set" name="sub.cat" id="sub.cat"
															ondblclick="callShowDiv(this);"><b><%=label.get("ques")%></b></label></td>
														<td width="10%" valign="top" class="formth"><label
															class="set" name="complexi" id="complexi"
															ondblclick="callShowDiv(this);"><b><%=label.get("complexi")%></b></label></td>	
														<td class="formth" align="center" width="5%">
														<input type="checkbox"  name="checkAllQues" id="checkAllQues"   
																onclick="return checkAllAddQues();" /> </td>													
													</tr>
													<s:if test="notAvailable">
														<tr>
															<td width="100%" colspan="4" align="center"><font
																color="red">There is no data to display</font></td>
														</tr>
													</s:if>
													
													<%
															int h1 = 1;
															int f1 = 0;
													%>
														<%!int a = 0;%>
													
													<s:iterator  value="addlist" id="mylist">
														<tr id="row<%=f1%>" style="display: none;">
															<td width="8%" class="sortabletd" align="center"><%=h1%></td>
															<td width="15%" class="sortabletd">
															<s:property	value="dupQusSubject" />
															<s:hidden name="dupQusSubject" id='<%="dupQusSubject"+f1 %>'/>
															</td>
															<td width="40%" class="sortabletd">
															<s:property	value="dupQusName" />
															<s:hidden name="dupQusName" id='<%="dupQusName"+f1 %>'/>
															<s:hidden name="dupSelectQuCode" id='<%="dupSelectQuCode"+f1 %>'/>
															</td>
															<td width="10%" class="sortabletd">
															<s:property value="dupComplexicity" /> 
															<s:hidden 	name="dupComplexicity" id='<%="dupComplexicity"+f1 %>'/>
															<td width="5%" align="center" class="sortableTD" nowrap="nowrap" >																						
															<input type="checkbox"  name="check" id='<%="check"+f1 %>'   value='<s:property value="dupSelectQuCode"/>' 
																onclick="return checkedbox('<%=f1%>');" /> 
															<input type="hidden" name="checkFlag" id="checkFlag<%=f1%>" />																
															</td>															
																												
														</tr>
														<%
																h1++;
																f1++;
														%>
													</s:iterator>
													<%a = f1;%>
											</table><!--Question List iterator for add question table 9  -->
										</td>
										<input type="hidden" name="count" id="count" value="<%=f1%>" />
									</tr>			
										
									</table><!--Question List table 7  -->
								</td>
							</tr>		
				</table><!--Question Details table 6  -->
			</td>
		</tr>								
		</s:else><!--End of Test Template form  -->
		
		
		<tr>
			<td  width="100%"  colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%" align="right"><s:if test="listFlag"><b>Total records:</b>&nbsp;<s:property value="totalRecords" /> </s:if></td>
					</tr>
				</table>
			</td>
		</tr>
</table><!-- end of test template -->
</s:form>
<script>
var recCount = 10;
pageLoading();
function pageLoading(){
	var listFlag = document.getElementById('paraFrm_listFlag').value;
	if(listFlag == "false"){
		callOnLoad();

		calculateOnAddQuestion();
	
		enableQuestion(document.getElementById("paraFrm_questionFrom").value);	
		
		fun();
	} //end of if
} //end of pageLOading method


function callOnLoad(){
try{
	var questionList = document.getElementById('paraFrm_addQuestion').value;
	if(questionList == "checked"){
		document.getElementById('paraFrm_addQuestionList').value = "true";
	} //end of if
	
	document.getElementById('correctAns').style.display = 'none';
	document.getElementById('allocateForEachAns').style.display = '';
	document.getElementById('addList').style.display = '';
	document.getElementById('pageList').style.display = '';
	document.getElementById('randomList').style.display = 'none';
	var distriRadio = document.getElementById('paraFrm_correctCheck').value;
	document.getElementById("random").style.display = 'none';
	
	if(distriRadio == "true"){
		document.getElementById('correctAns').style.display = '';
		document.getElementById('allocateForEachAns').style.display = 'none';
	}
	else{
		document.getElementById('correctAns').style.display = 'none';
		document.getElementById('allocateForEachAns').style.display = '';
	}
	
	//var distributionRadio = document.getElementById('paraFrm_radioStatus').value;
	//alert("distributionRadio==="+distributionRadio);
	
	//document.getElementById("randomList").style.display = 'none';
	//document.getElementById("addList").style.display = '';
	}catch(e){
		//alert(e);
	}
} //end of callOnLoad method

function calculateOnAddQuestion(){
try{
	
	var checkedQues = document.getElementById('paraFrm_addQuestionList').value;
	var questionType = document.getElementById('paraFrm_questionFrom').value;
	var defineDistribution = document.getElementById('paraFrm_correctCheck').value;
	var complexity = "";
	var countEasyQues = "0";
	var countMediumQues = "0";
	var countHardQues = "0";
	var marksEasyList = "0";
	var marksMediumList = "0";
	var marksHardList = "0";
	if(questionType == "random"){
	
	} //end of if
	else{
		if(checkedQues == ""){
		} //end of if
		else{
			var marksAllocatedForEachAns = document.getElementById('paraFrm_marksAllocatedForEach').value;
			var defineEasyMarks = document.getElementById('paraFrm_marksForEasy').value;
			var defineMediumMarks = document.getElementById('paraFrm_marksForMedium').value;
			var defineHardMarks = document.getElementById('paraFrm_marksForHard').value;
			var value='<%=a%>';
			if(value > 0){
					for(var q=0;q<value;q++){
					complexity = trim(document.getElementById('dupComplexicity'+q).value);
					if(complexity == "Easy"){
						countEasyQues = Math.abs(countEasyQues) + Math.abs(1);
					} //end of if
					else if(complexity == "Medium"){
						countMediumQues = Math.abs(countMediumQues) + Math.abs(1);
					} //end of if
					else if(complexity == "Hard"){
						countHardQues = Math.abs(countHardQues) + Math.abs(1);
					} //end of if
				} //end of loop
				//alert("countEasyQues===>"+countEasyQues);
				 document.getElementById('cntRandomHard').value = countHardQues;
				 document.getElementById('cntRandomMedium').value = countMediumQues;
				 document.getElementById('cntRandomEasy').value = countEasyQues;
				 document.getElementById('totalRandomnoOfQus').value = Math.abs(countHardQues) + Math.abs(countMediumQues)+ Math.abs(countEasyQues);
				
				if(defineDistribution == "true"){
					document.getElementById('markRandomHard').value = Math.abs(defineHardMarks) * Math.abs(countHardQues);
				 	document.getElementById('markRandomMedium').value = Math.abs(defineMediumMarks) * Math.abs(countMediumQues);
				    document.getElementById('markRandomEasy').value = Math.abs(defineEasyMarks) * Math.abs(countEasyQues);
				    document.getElementById('totalRandomMarkss').value = (Math.abs(defineHardMarks) * Math.abs(countHardQues))
				    	+(Math.abs(defineMediumMarks) * Math.abs(countMediumQues)) + (Math.abs(defineEasyMarks) * Math.abs(countEasyQues));
				} //end of if
				else{
					document.getElementById('markRandomHard').value = Math.round(Math.abs(marksAllocatedForEachAns) * Math.abs(countHardQues));
				 	document.getElementById('markRandomMedium').value = Math.round(Math.abs(marksAllocatedForEachAns) * Math.abs(countMediumQues));
				    document.getElementById('markRandomEasy').value = Math.round(Math.abs(marksAllocatedForEachAns) * Math.abs(countEasyQues));
				    document.getElementById('totalRandomMarkss').value = Math.round((Math.abs(marksAllocatedForEachAns) * Math.abs(countHardQues))
				    	+(Math.abs(marksAllocatedForEachAns) * Math.abs(countMediumQues)) + (Math.abs(marksAllocatedForEachAns) * Math.abs(countEasyQues)));
				} //end of else
				
				//======used for add question paging=========//
				var trCount = '<%=a%>';
				//alert("trCount=="+trCount);
				for(var i=0;i<trCount;i++){
					if(i<recCount){
						document.getElementById("row"+i).style.display = '';
					}//end of if
				} //end of loop
				document.getElementById('addQuesPageNoField').value = 1;
				document.getElementById('actAddQuesPage').value = 1;
			} //end of if
		} //end of else
	} //end of else
}catch(e){
	alert(e);
} //end of catch
} //end of calculateOnAddQuestion method




//=======this functions is used on Add New button======================================//
function addnewFun(){
try{
	document.getElementById('paraFrm').action="TestTemplate_addNew.action";
  	document.getElementById('paraFrm').submit();
  	}catch(e){
  		alert(e);
  	}
} //end of addnewFun method
//=======End of Add New button function======================================//

/**
	This function is called when radio options for equal marks distribution and
	Define Marks distribution is clicked.
**/
function callRadioOptionFun(id)
{
	try{
	if(id=="Equal")
	{
		  callForEqualDistribution();
	}
	if(id=="Define")
	{
		callForDefineDistribution();
	}
	document.getElementById('paraFrm_radioStatus').value=id;
	}catch(e){
		alert(e);
	}
}
//==========End of radio function===============================//

function callForDefineDistribution(){
	document.getElementById('correctAns').style.display = '';
	document.getElementById('allocateForEachAns').style.display = 'none';
	document.getElementById('paraFrm_correctCheck').value = "true";
	document.getElementById('paraFrm_marksAllocatedForEach').value = "";
} //end of callForDefineDistribution method

function callForEqualDistribution(){
	document.getElementById('correctAns').style.display = 'none';
	document.getElementById('allocateForEachAns').style.display = '';
	document.getElementById('paraFrm_correctCheck').value = "false";
	document.getElementById('paraFrm_marksForHard').value = "";
	document.getElementById('paraFrm_marksForMedium').value = "";
	document.getElementById('paraFrm_marksForEasy').value = "";
	document.getElementById('paraFrm_marksAllocatedForEach').value = 0;
} //end of callForDefineDistribution method

/**
	This function is called when the respective Subject is selected....
**/
function getValue1(nom)
{
try{
	var a=nom; var result=[];
	for (var i=0; i<a.length; i++) {
		a[i].selected?result.push(a[i].value):"";
	} //end of loop
	document.getElementById('hidAjaxSubject').value = result;
	document.getElementById('paraFrm_subCode').value=result;
	// alert('sub.....'+document.getElementById('paraFrm_subCode').value);	  
	retrieveURL('TestTemplate_selCategory.action?','paraFrm');
	}catch(e){
		alert(e);
	}		
} //end of getValue1 method
//=================End of getValue1 function===================================//

function enableQuestion(value1){
		if(value1 == "random"){
		try{
			document.getElementById("paraFrm_questionFrom").value = "random";
			document.getElementById("random").style.display = '';
			document.getElementById("addQuestionRow").style.display ='none';
			document.getElementById('idcheckrandom').checked=true;
			document.getElementById("addList").style.display = 'none';
			document.getElementById("pageList").style.display = 'none';
			document.getElementById("randomList").style.display = '';
			document.getElementById("paraFrm_addQuestionList").value = 'false';
			}catch(e){
				alert(e);
			}
		}
		else{
		    document.getElementById("paraFrm_questionFrom").value = "add";
		    document.getElementById("random").style.display = 'none';
			document.getElementById("addQuestionRow").style.display = '';
			document.getElementById('idcheckadd').checked=true;
			document.getElementById("randomList").style.display = 'none';
			document.getElementById("addList").style.display = '';
			document.getElementById("pageList").style.display = '';
			document.getElementById("paraFrm_addQuestionList").value = 'true';
		}
	}			

//====================This function is called when clicked on Go button==========================//
function showQuestionDetails(id){
try{
	var fieldName = ["paraFrm_testType"];
	var lableName = ["test.type"];
	var flag      = ["select"];
	if(!validateBlank(fieldName, lableName, flag))return false;
	
	if(document.getElementById("paraFrm_subject").value == ""){
			alert("Please select "+document.getElementById("subject").innerHTML.toLowerCase());
			return false;
		}
	
		if(document.getElementById("paraFrm_category").value == ""){
			alert("Please select "+document.getElementById("category").innerHTML.toLowerCase());
			return false;
		}
	
		if(id == "go"){
			document.getElementById('cntRandomHard').value = 0;
			 document.getElementById('cntRandomMedium').value = 0;
			 document.getElementById('cntRandomEasy').value = 0;
			 document.getElementById('totalRandomnoOfQus').value = 0;
			  
			 document.getElementById('markRandomHard').value = 0;
			 document.getElementById('markRandomMedium').value = 0;
			 document.getElementById('markRandomEasy').value = 0;
			 document.getElementById('totalRandomMarkss').value = 0;
		}
		 

	var defineDistribution = document.getElementById('paraFrm_correctCheck').value;
	if(defineDistribution == 'true'){
		var DefineMarkDistrFieldName = ["paraFrm_marksForHard","paraFrm_marksForMedium","paraFrm_marksForEasy"];
		var DefineMarkDistriLableName = ["hard.marks","medium.marks","easy.marks"];
		var DefineMarkDistriFlag = ["enter","enter","enter"];
		if(!validateBlank(DefineMarkDistrFieldName, DefineMarkDistriLableName, DefineMarkDistriFlag))return false;
	}
	else{
		var equalMarksDistribution = ["paraFrm_marksAllocatedForEach"];
		var equalMarksDistributionName = ["marks.allocated"];
		var equalMarksDistributionFlag = ["enter"];
		if(!validateBlank(equalMarksDistribution, equalMarksDistributionName, equalMarksDistributionFlag))return false;
	}
	
	var questionType = document.getElementById('paraFrm_questionFrom').value;	
	if(questionType == "add"){
	
	} //end of if
	else{
		document.getElementById("paraFrm").action = "TestTemplate_selectRandomQuestions.action";
		document.getElementById("paraFrm").target = "main"; 		
		document.getElementById("paraFrm").submit();
	} //end of else
	return true;
}catch(e){
	alert(e);
}	
} //end of showQuestionDetails method


function calMarksAllocated(){
 
	var defineDistribution = document.getElementById('paraFrm_correctCheck').value;
	var questionType = document.getElementById('paraFrm_questionFrom').value;
	var totalFinalQues = document.getElementById('paraFrm_totalNoOfQues').value;
	//document.getElementById('paraFrm_marksAllocatedForEach').value = 0;
	if(totalFinalQues == ""){
		//document.getElementById('paraFrm_totalNoOfQues').value = 0;
		totalFinalQues = 0;
	} //end of if
	var totalFinalMarks  = document.getElementById('paraFrm_testTotalMarks').value;
	if(totalFinalMarks == ""){
		//document.getElementById('paraFrm_testTotalMarks').value = 0;
		totalFinalMarks = 0;
	} //end of if
	 if(defineDistribution == "false"){
		 if(Math.abs(totalFinalQues) > 0 && Math.abs(totalFinalMarks) > 0){
			 var value = 0;
			 value = Math.abs(totalFinalMarks*1/1) / Math.abs(totalFinalQues*1/1);
			 document.getElementById('paraFrm_marksAllocatedForEach').value = Math.round(Math.abs(value) * Math.pow(10, 2)) / Math.pow(10, 2);
     		 validateQuestionInTest();
		 } //end of if
	 } //end of if
	 
	 else{
	 	validateQuestionInTest();
	 } //end of else
	 if(totalFinalQues == "" ||totalFinalMarks =="" ){
	  document.getElementById('paraFrm_marksAllocatedForEach').value = "";
	 }
} //end of calMarksAllocated method

function validateQuestionInTest(){
	try{
		var availableNoOfQues = 0;
		var questionsInTest = 0;
		var complexity = "";
		var countHardQues = 0;
		var countMediumQues = 0;
		var countEasyQues = 0;
		var marksHardQues = 0;
		var marksMediumQues = 0;
		var marksEasyQues = 0;
		var totalFinalQues = document.getElementById('paraFrm_totalNoOfQues').value;
		var totalFinalMarks = document.getElementById('paraFrm_testTotalMarks').value;
		var marksAllocatedForEachAns = document.getElementById('paraFrm_marksAllocatedForEach').value;
		var questionType = document.getElementById('paraFrm_questionFrom').value;
		if(totalFinalQues == ""){
		 alert("Please enter "+document.getElementById("total.noques").innerHTML.toLowerCase());
		 return false;
		} //end of if
		if(totalFinalMarks == ""){
		 alert("Please enter "+document.getElementById("total.mark").innerHTML.toLowerCase());
		 return false;
		} //end of if
		
		if(marksAllocatedForEachAns == ""){
			document.getElementById('paraFrm_marksAllocatedForEach').value = 0;
		} //end of if
		var defineHardMarks =  document.getElementById('paraFrm_marksForHard').value;
		if(defineHardMarks == ""){
			document.getElementById('paraFrm_marksForHard').value = 0;
			defineHardMarks = 0;
		} //end of if
		var defineMediumMarks = document.getElementById('paraFrm_marksForMedium').value;
		if(defineMediumMarks == ""){
			document.getElementById('paraFrm_marksForMedium').value = 0;
			defineMediumMarks = 0;
		} //end of if
		var defineEasyMarks = document.getElementById('paraFrm_marksForEasy').value;
		if(defineEasyMarks == ""){
			document.getElementById('paraFrm_marksForEasy').value = 0;
			defineEasyMarks = 0;
		} //end of if
		var defineDistribution = document.getElementById('paraFrm_correctCheck').value;
		try{
		if(questionType == "random"){
			 var value='<%=j%>';	
			 for(var q=0;q<value;q++){
			 
				 availableNoOfQues = document.getElementById('availableQuestion'+q).value;
				 questionsInTest = document.getElementById('testQuestion'+q).value;
				 if(questionsInTest == ""){
				 	questionsInTest = 0;
				 }
				 complexity = document.getElementById('complexicityCode'+q).value;
				 
				 if(questionsInTest == ""){
				 	//document.getElementById('testQuestion'+q).value = 0;
				 } //end of if
					
				if(Math.abs(questionsInTest) > Math.abs(availableNoOfQues)){
					//alert(document.getElementById("ques.test").innerHTML.toLowerCase()+" should not be greater than "+
					 	//document.getElementById("avil.ques").innerHTML.toLowerCase());
					 alert("Questions in Test should not be greater than "+
					 	document.getElementById("avil.ques").innerHTML.toLowerCase());	
					document.getElementById('testQuestion'+q).value = 0;
					questionsInTest = 0;
				} //end of if
				
				if(complexity == 'H'){
					countHardQues = Math.abs(countHardQues) + Math.abs(questionsInTest);
					 var forHardCount = Math.abs(countHardQues) + Math.abs(countMediumQues)+ Math.abs(countEasyQues);
					 //alert("forHardCount==="+forHardCount);
					 if(Math.abs(forHardCount) > Math.abs(totalFinalQues)){
			 				alert(document.getElementById("total.listQues").innerHTML.toLowerCase()+" should not be greater than "+
			 				document.getElementById("total.noques").innerHTML.toLowerCase());
			 				document.getElementById('testQuestion'+q).value = 0;
			 				countHardQues = Math.abs(countHardQues) - Math.abs(questionsInTest);
							questionsInTest = 0;
					 } //end of if
					
				} //end of if for complexity hard
				if(complexity == 'M'){
					countMediumQues = Math.abs(countMediumQues) + Math.abs(questionsInTest);
					 var forMediumCount = Math.abs(countHardQues) + Math.abs(countMediumQues)+ Math.abs(countEasyQues);
					 //alert("forMediumCount==="+forMediumCount);
					 if(Math.abs(forMediumCount) > Math.abs(totalFinalQues)){
			 				//alert(document.getElementById("total.listQues").innerHTML.toLowerCase()+" should not be greater than "+
			 				//document.getElementById("total.noques").innerHTML.toLowerCase());
			 				alert("Questions in test should not be greater than "+
			 				document.getElementById("total.noques").innerHTML.toLowerCase());
			 				document.getElementById('testQuestion'+q).value = 0;
			 				countMediumQues = Math.abs(countMediumQues) - Math.abs(questionsInTest);
							questionsInTest = 0;
					 } //end of if
					
				} //end of if for complexity medium
				if(complexity == "E"){
					
					countEasyQues = Math.abs(countEasyQues) + Math.abs(questionsInTest);
					 var forEasyCount = Math.abs(countHardQues) + Math.abs(countMediumQues)+ Math.abs(countEasyQues);
					 //alert("forEasyCount==="+forEasyCount);
					 if(Math.abs(forEasyCount) > Math.abs(totalFinalQues)){
			 				//alert(document.getElementById("total.listQues").innerHTML.toLowerCase()+" should not be greater than "+
			 				//document.getElementById("total.noques").innerHTML.toLowerCase());
			 				alert("Questions in test should not be greater than "+
			 				document.getElementById("total.noques").innerHTML.toLowerCase());
			 				document.getElementById('testQuestion'+q).value = 0;
			 				countEasyQues = Math.abs(countEasyQues) - Math.abs(questionsInTest);
							questionsInTest = 0;
							//alert("countEasyQues=in if=="+countEasyQues);
					 } //end of if
					
				} //end of if for complexity easy
			 } //end of loop
			
		} //end of if
		else{
			var addQuesCount = '<%=a%>';
			 for(var q=0;q<addQuesCount;q++){
			 	complexity = trim(document.getElementById('dupComplexicity'+q).value);
				 	if(complexity == 'Hard'){
						countHardQues = Math.abs(countHardQues) + Math.abs(1);
					} //end of if for complexity hard
					if(complexity == 'Medium'){
						countMediumQues = Math.abs(countMediumQues) + Math.abs(1);
					} //end of if for complexity medium
					if(complexity == 'Easy'){
						countEasyQues = Math.abs(countEasyQues) + Math.abs(1);
					} //end of if for complexity easy
			 }//end of loop 
		} //end of else
	}catch(e){
		alert(e);
	}
		 document.getElementById('cntRandomHard').value = countHardQues;
		 document.getElementById('cntRandomMedium').value = countMediumQues;
		 document.getElementById('cntRandomEasy').value = countEasyQues;
		 document.getElementById('totalRandomnoOfQus').value = Math.abs(countHardQues) + Math.abs(countMediumQues)+ Math.abs(countEasyQues);
		  
		 if(defineDistribution == "false"){
		 	document.getElementById('markRandomHard').value = Math.round(Math.abs(marksAllocatedForEachAns) * Math.abs(countHardQues));
		 	document.getElementById('markRandomMedium').value = Math.round(Math.abs(marksAllocatedForEachAns) * Math.abs(countMediumQues));
		    document.getElementById('markRandomEasy').value = Math.round(Math.abs(marksAllocatedForEachAns) * Math.abs(countEasyQues));
		    document.getElementById('totalRandomMarkss').value = Math.round((Math.abs(marksAllocatedForEachAns) * Math.abs(countHardQues))
		    	+(Math.abs(marksAllocatedForEachAns) * Math.abs(countMediumQues)) + (Math.abs(marksAllocatedForEachAns) * Math.abs(countEasyQues)));
		 } //end of if
		 else{
		 	document.getElementById('markRandomHard').value = Math.abs(defineHardMarks) * Math.abs(countHardQues);
		 	document.getElementById('markRandomMedium').value = Math.abs(defineMediumMarks) * Math.abs(countMediumQues);
		    document.getElementById('markRandomEasy').value = Math.abs(defineEasyMarks) * Math.abs(countEasyQues);
		    document.getElementById('totalRandomMarkss').value = (Math.abs(defineHardMarks) * Math.abs(countHardQues))
		    	+(Math.abs(defineMediumMarks) * Math.abs(countMediumQues)) + (Math.abs(defineEasyMarks) * Math.abs(countEasyQues));
		 }
	}catch(e){
		alert(e);
	} //end of catch
} //end of validateQuestionInTest method

 function fun(){
   		
   		
  
		 	var a = document.getElementById('hiddenSubject').value; 
		 	var sub=a.split(",");
		 
		   	var j=0;
		   	var i=0;
		      
		   	document.getElementById('paraFrm_subject').multiple=true;
		   	
		   	for(j=0;j<sub.length;)
		   	{
		  	
		   		for(i=0;i<document.getElementById('paraFrm_subject').options.length;i++)
		   		{   		   		
		   			if(eval(document.getElementById('paraFrm_subject').options[i].value) == eval(sub[j])){
		   					document.getElementById('paraFrm_subject').options[i].selected=true;
		 			}   					
		   		}
		   		j++;
		   	}
	   	
		    var b = document.getElementById('paraFrm_hiddenCategory').value;
		 	var cat=b.split(",");
		 	
		   	var l=0;
		   	var m=0;
		   
		   	for(l=0;l<cat.length;)
		   	{
		   		for(m=0;m<document.getElementById('paraFrm_category').options.length;m++)
		   		{
			   		if(eval(document.getElementById('paraFrm_category').options[m].value) == eval(cat[l])){   	
			   			document.getElementById('paraFrm_category').options[m].selected=true;   			
			   		}   	
		   		}
		   	   	l++;
		   }
   } //end of fun method
   
   //=======this function is used on Save button========================================//
function saveFun(){
try{
	var questionType = document.getElementById('paraFrm_questionFrom').value;
	var fieldName = ["paraFrm_testName", "paraFrm_testDuration"];
	var lableName = ["test.name", "test.duration"];
	var flag = ["enter", "enter"];
	if(!validateBlank(fieldName, lableName, flag))return false;
	if(!validateTime("paraFrm_testDuration", "test.duration"))return false;
	
	var fieldName1 = ["paraFrm_testType"];
	var lableName1 = ["test.type"];
	var flag1 = ["select"];
	if(!validateBlank(fieldName1, lableName1, flag1))return false;
	
	var instructionNotes = document.getElementById("paraFrm_instructionNotes").value;
	if(instructionNotes != "" && instructionNotes.length > 500){
			alert("Maximum length of "+document.getElementById("instruction").innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	} //end of if
	
	//=======Marking System validations started====================================//
	var markinSystemFieldName = ["paraFrm_totalNoOfQues", "paraFrm_testTotalMarks","paraFrm_passingMark"];
	var markinSystemLableName = ["total.noques", "total.mark","passing.marks"];
	var markinSystemFlag = ["enter", "enter","enter"];
	if(!validateBlank(markinSystemFieldName, markinSystemLableName, markinSystemFlag))return false;
	
	var passingMarks = document.getElementById('paraFrm_passingMark').value;
	var testTotMarks = document.getElementById('paraFrm_testTotalMarks').value;
	document.getElementById('paraFrm_testTotalMarks').value = Math.abs(testTotMarks*1/1);
	if(Math.abs(testTotMarks) <= 0 || Math.abs(testTotMarks) == ""){
		alert("Please enter "+document.getElementById("total.mark").innerHTML.toLowerCase());
		return false;
	} //end of if
	
	if(Math.abs(passingMarks) > Math.abs(testTotMarks)){
		alert(document.getElementById("passing.marks").innerHTML.toLowerCase()+" should not be greater than "+
			document.getElementById("total.mark").innerHTML.toLowerCase());
			passingMarks = 0;
			document.getElementById('paraFrm_passingMark').value = 0;
		return false;	
	} //end of if
	
	var defineDistribution = document.getElementById('paraFrm_correctCheck').value;
	
	if(defineDistribution == "true"){
		var defineMarksFieldName = ["paraFrm_marksForHard", "paraFrm_marksForMedium","paraFrm_marksForEasy"];
		var defineMarksLableName = ["hard.marks", "medium.marks","easy.marks"];
		var defineMarksFlag = ["enter", "enter","enter"];
		if(!validateBlank(defineMarksFieldName, defineMarksLableName, defineMarksFlag))return false;
	} //end of if
	//=======End of Marking System validations ====================================//
	
	//=========Validations for Question Details=======================================//
		if(document.getElementById("paraFrm_subject").value == ""){
			alert("Please select "+document.getElementById("subject").innerHTML.toLowerCase());
			return false;
		} //end of subject if
		if(document.getElementById("paraFrm_category").value == ""){
			alert("Please select "+document.getElementById("category").innerHTML.toLowerCase());
			return false;
		} //end of category if
		
		if(questionType == "random"){
			 var randomList='<%=j%>';	
			 if(randomList <=0){
			 	alert("Please add questions in the list");
			 	return false;
			 } //end of if
		} //end of if
		else{
			var addList='<%=a%>';
			if(addList <=0){
				alert("Please add questions in the list");
				return false;
			} //end of if	
		} //end of else
		
		var totalListQuestion = document.getElementById('totalRandomnoOfQus').value;
		var recValue = document.getElementById('rec').value;
		
		//alert("totalListQuestion.........."+document.getElementById('rec').value);
		if(totalListQuestion <= 0 ){
			alert("Please enter questions in test");
			if(recValue>0){
			document.getElementById('testQuestion0').focus();
			}
			return false;
		} //end of if
		
		var totalQuestion = document.getElementById('paraFrm_totalNoOfQues').value;
		
		if(Math.abs(totalListQuestion) != Math.abs(totalQuestion)){
			alert(document.getElementById("total.noques").innerHTML.toLowerCase()+" should be equal to "+
						document.getElementById("total.listQues").innerHTML.toLowerCase());
									
			for(var i=0;i<recValue;i++){
			
			var testQuestion = document.getElementById('testQuestion'+i).value;
			if(testQuestion == ""){
				document.getElementById('testQuestion'+i).focus();
				return false;			
			}
			
			
		}
			
		} //end of if
		
		var totalListMarks = document.getElementById('totalRandomMarkss').value;
		
		if(Math.abs(testTotMarks*1/1) != Math.abs(totalListMarks)){
			alert(document.getElementById("total.mark").innerHTML.toLowerCase()+" should be equal to "+
					document.getElementById("total.listMarks").innerHTML.toLowerCase());
			return false;		
		} //end of if
		
	//=========End of Validations for Question Details=======================================//
	
	//=======save function is called====================================//
	var con=confirm('Do you really want to save the test template ?');
	if(con){
		document.getElementById("paraFrm").action="TestTemplate_saveFirst.action";
		document.getElementById("paraFrm").submit();
	} //end of if
	else{
		return false;
	} //end of else
		
	//======End of save function=======================================//	
	
}catch(e){
	alert(e);
} //end of catch	
} //end of saveFun method
//=======End of Save button function========================================//

//==========function performed on search buttom=============================//
function searchFun(){
		callsF9(500,325,'TestTemplate_f9Search.action');
} //end of searchFun method
//==========end of function performed on search buttom=============================//

//=========function performed on cancel button====================================//
function cancelFun(){
  	if(document.getElementById('paraFrm_buttonPanelcode').value=="2"){
	   	document.getElementById('paraFrm').action="TestTemplate_cancelSec.action";
	} //end of if
	else if(document.getElementById('paraFrm_buttonPanelcode').value=="3"){
	   	document.getElementById('paraFrm').action="TestTemplate_cancelThird.action";
	} //end of if
	else{
	  	document.getElementById('paraFrm').action="TestTemplate_cancelFirst.action";
	} //end of else
	  	document.getElementById('paraFrm').submit();
	  	return true;
} //end of cancelFun method
//=======end of function performed on cancel button=============================//	

function checkPassingMark(){
	var passingMarks = document.getElementById('paraFrm_passingMark').value;
	var testTotMarks = document.getElementById('paraFrm_testTotalMarks').value;
	if(Math.abs((passingMarks)) > Math.abs(testTotMarks)){
		alert(document.getElementById("passing.marks").innerHTML.toLowerCase()+" should not be greater than "+
			document.getElementById("total.mark").innerHTML.toLowerCase());
			passingMarks = 0;
			document.getElementById('paraFrm_passingMark').value = 0;	
	} //end of if
} //end of checkPassingMark

function newRowColor(cell)
{
	cell.className='Cell_bg_first'; 
} //end of newRowColor method

function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
} //end of oldRowColor method

//=========this function is called when doubled click on record==================//
function callForEdit(id){   
	  	document.getElementById('paraFrm_testTemplateCode').value=id;		
		document.getElementById("paraFrm").action="TestTemplate_editdblClk.action";	   	
	    document.getElementById("paraFrm").submit();
}

//========this function is called when clicked on select question button=========//
function selectQuestion(){	
		
		if(!showQuestionDetails())return false;
		document.getElementById('paraFrm_hiddenQuestionCode').value="";
		
		window.open('','new','top=100,left=300,width=700,height=400,scrollbars=yes,status=yes,resizable=no');
		document.getElementById('paraFrm_chFlag').value="true";
	 	document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action = 'TestTemplate_selectQuestion.action?chFlag=true'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	  	
		return false;
} //end of selectQuestion method 	

//===================================this functions is used for paging=================================//

	function callPageText(id){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action='TestTemplate_input.action';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	 function callPage(id,pageImg){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPage').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='TestTemplate_input.action';
		document.getElementById('paraFrm').submit(); 
	}		
//===================================end of paging functions======================================//

 function chkSelectQuestion(){
 try{
 
	var tbl = document.getElementById('tblSelectQuestion');
		var rowLen = tbl.rows.length;
		  for(var a=0;a<rowLen-1;a++){	
		   if(document.getElementById('check'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}catch(e){
 	alert(e);
 }		  
}

function removeQuestions(){
try{
	if(chkSelectQuestion()){
		var aa=confirm("Are you sure to remove the selected questions ?");
		if(aa){
			var rowCount = '<%=a%>';
			var k = "";
			for(var i=0;i<rowCount;i++){
				var queCodes = document.getElementById('dupSelectQuCode'+i).value;
				if(!document.getElementById('check'+i).checked){
					k+=queCodes+",";
				} //end of if
			} //end of loop
			//document.getElementById('paraFrm_checkedQuestions').value=k;
			document.getElementById('paraFrm_addToListQuestions').value = k;
			document.getElementById('paraFrm').action='TestTemplate_removeQuestions.action';
			document.getElementById('paraFrm').submit(); 
		} //end of if
		else{
			return false;
		} //end of else
	} //end of if	
	else {
	 	alert('Please select at least one record to remove');
	 	return false;
	} //end of else
	}catch(e){
 	alert(e);
 }		
} //end of removeQuestions method

//==================Add question page numbers==================================//
function callPageAddQues(id){
	try{
	
		var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key!=13) 
			{
				return false; 
			}
	
		var pageNoField = document.getElementById('addQuesPageNoField').value;
		var trCount = '<%=a%>';
		totalPage ='<%=addListPages %>';
		var actPage = document.getElementById('actAddQuesPage').value;
		if(Number(pageNoField)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoField').value = actPage;
			     document.getElementById('addQuesPageNoField').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNoField))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			     document.getElementById('addQuesPageNoField').value = actPage;
			     document.getElementById('addQuesPageNoField').focus();
				 return false;
			    } //end of if
	 
		
		document.getElementById('actAddQuesPage').value = pageNoField;
		
		for(i=0;i<trCount;i++){
			document.getElementById("row"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("row"+i).style.display = '';
			} //end of if
		} //end of loop
		checkAllAfterPage();
	}catch(e){
	 alert(e);
	} //end of catch
} //end of method

function callAddQuesPage(id,pageImg){
	 pageNo =document.getElementById('addQuesPageNoField').value;	
 	 totalPage ='<%=addListPages %>';
 	 var actPage = document.getElementById('actAddQuesPage').value;
 	  if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('addQuesPageNoField').focus();
				 return false;
		        } //end of if
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoField').value=actPage;
			     document.getElementById('addQuesPageNoField').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('addQuesPageNoField').value=actPage;
			     document.getElementById('addQuesPageNoField').focus();
				 return false;
			    } //end of if
		 } //end of if 	 
		 
		  if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('addQuesPageNoField').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoField').value = 1;
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('addQuesPageNoField').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoField').value = totalPage;
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('addQuesPageNoField').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoField').value = eval(pageNo)-eval(1);
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('addQuesPageNoField').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoField').value = eval(pageNo)+eval(1);
       }
      var pageNoField = document.getElementById('addQuesPageNoField').value;
      document.getElementById('actAddQuesPage').value = pageNoField;
        var trCount = '<%=a%>';
		for(i=0;i<trCount;i++){
			document.getElementById("row"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("row"+i).style.display = '';
			} //end of if
		} //end of loop
         checkAllAfterPage();
} //end of callAddQuesPage method


function checkAllAddQues(){
  var currentPage = document.getElementById('addQuesPageNoField').value;
  var j= (currentPage - 1)*recCount;
  var flag = document.getElementById('checkAllQues').checked;
  for(i=j;i<j+recCount;i++){
  	document.getElementById('check'+i).checked = flag;
  } //end of loop
}

function checkAllAfterPage(){
  var currentPage = document.getElementById('addQuesPageNoField').value;
  var j= (currentPage - 1)*recCount;
  var flag = true;
  for(i=j;i<j+recCount;i++){
  	if(!document.getElementById('check'+i).checked)
  	{
  		flag=false;
  		break;
  	}
  } //end of loop
  document.getElementById('checkAllQues').checked = flag;
}

function callLength(type){ 
	 if(type=='maxchar'){
				var cmt =document.getElementById('paraFrm_instructionNotes').value;
				var remain = 500 - eval(cmt.length);
				document.getElementById('paraFrm_maxchar').value = remain;
				
				if(eval(remain)< 0){
					document.getElementById('paraFrm_instructionNotes').style.background = '#FFFF99';
				}
				else document.getElementById('paraFrm_instructionNotes').style.background = '#FFFFFF';
			
	}
}
</script>