<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TestTemplate" validate="true" id="paraFrm" theme="simple">
<s:hidden name="testTemplateCode"/>
<s:hidden name="cancelFlag" />
<s:hidden name="myPage" id="myPage" />
<s:hidden name="show"/>
<s:hidden name="compLevel"/>
<s:hidden name="questionFrom"/>
<s:hidden name="hiddenSubject"/>
<s:hidden name="hiddenCategory"/>
<s:hidden name="buttonPanelcode"/>
<s:hidden name="cCode" />
<s:hidden name="sCode" />
<s:hidden name="cnt"/>
<s:hidden name="qusSubject" /> 	
<s:hidden name="qusName"/>
<s:hidden name="selectQuCode"/>
<s:hidden name="paraId"/>
<s:hidden name="equalWeightageFlag" />
<s:hidden name="complexicity" /> 
<s:hidden name="correctCheck" />
<s:hidden name="checkedQuestions"/>
<s:hidden name="addToListQuestions"/>
<s:hidden name="actAddQuesPage" id="actAddQuesPage"/>
<s:hidden name="scriptPageNo" value="10"/>
<s:hidden name="listFlag"/>
<table class="formbg" width="100%"><!--Start Final table  -->
	<tr>
	   <td colspan="3">
	     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">  
			<tr>
				<td width="4%" valign="bottom" class="txt" ><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
				<td width="93%" class="txt" ><strong class="formhead" >Test Template </strong></td>
				<td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
			</tr>
	     </table>
	  </td>
	</tr>
	<tr>
       <td colspan="3">
       		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		         <tr>
		            <td width="78%">
		              	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
		            </td>
		            <td width="22%"><div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required </div>
					</td>
		         </tr>
          </table>
       </td>
    </tr>
     <tr>
	    <td colspan="3">
	    	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Start of template Header table  -->
	    		<tr>
					<td colspan="4"><strong>Test Details</strong>
					</td>
				</tr>
	    		 <tr id="reqSearchDiv">
		             <td width="20%">
		             <label  class = "set" name="test.name" id="test.name" 
			            	ondblclick="callShowDiv(this);"><%=label.get("test.name")%></label> :</span></td>
		             <td  width="30%">
							<s:property  value="testName"/><s:hidden name="testName"/></td>
		             <td width="20%">
		             <label  class = "set" name="test.duration" id="test.duration" 
			            	ondblclick="callShowDiv(this);"><%=label.get("test.duration")%></label> :</span></td>
		             <td width="30%">
						<s:property value="testDuration"/><s:hidden name="testDuration"/>
					 </td>
		         </tr>
		         <tr>
		             <td width="20%">
		             <label  class = "set" name="test.type" id="test.type" 
			            	ondblclick="callShowDiv(this);"><%=label.get("test.type")%></label> :</span></td>
		             <td width="30%">
						<s:property value="testType"/><s:hidden name="testType"/>
					 </td>
		             <td width="20%">
		             <label  class = "set" name="online.score" id="online.score" 
			             ondblclick="callShowDiv(this);"><%=label.get("online.score")%></label> :</td>
		             <td width="30%"><s:property value="enableScore"/>
		                 <s:hidden name="enableScore"/>
		             </td>
		         </tr>
		         <tr>
		             <td  valign="top" colspan="1">
		             <label  class = "set" name="instruction" id="instruction" 
			            	ondblclick="callShowDiv(this);"><%=label.get("instruction")%></label> :</span></td>
		             <td  colspan="3"><s:property value="instructionNotes"/>
		                  <s:hidden name="instructionNotes"/>
		             </td>
		         </tr>
	    	</table><!--End of template Header table  -->
	    </td>
	 </tr>
	 <tr>
		<td  width="100%"  colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Start of Marking System  -->
				<tr>
					<td colspan="4"><strong>Marking System</strong>
					</td>
				</tr>
				 <tr>
		             <td width="20%">
		             <label  class = "set" name="total.ques" id="total.ques" 
			            	ondblclick="callShowDiv(this);"><%=label.get("total.ques")%></label> :</span></td>
		             <td width="30%">
						<s:property value="totalNoOfQues"/><s:hidden name="totalNoOfQues"/>
					 </td>
		             <td width="20%">
		             <label  class = "set" name="total.marks" id="total.marks" 
			            	ondblclick="callShowDiv(this);"><%=label.get("total.marks")%></label> :</td>
		             <td width="30%"><s:property value="testTotalMarks"/>
		                <s:hidden name="testTotalMarks"/>
		             </td>
		         </tr>
		         <tr>
		             <td width="20%">
		             <label  class = "set" name="passing.marks" id="passing.marks" 
			            	ondblclick="callShowDiv(this);"><%=label.get("passing.marks")%></label> :</td>
		             <td width="30%"><s:property value="passingMark"/>
		                 <s:hidden name="passingMark"/>
		             </td>
		         </tr>
		         <s:hidden name="defineDistriRadio"/>
		       	 <s:hidden name="equalDistriRadio"/>
		        <tr>
		         	<td  colspan="4"><input type="radio" disabled="disabled" value="Define" name="radioOption" 
		         		<s:property value="defineDistriRadio"/>
						 	onclick="callRadioOptionFun('Define');" ><strong><label  class = "set" 
						 	 id="define.distribution" 
						 	name="define.distribution" ondblclick="callShowDiv(this);"><%=label.get("define.distribution")%></label></strong>
					</td>
		        </tr>
		        <s:if test="correctCheck">
		        	<tr>
						<td  colspan="4"><label class="set"
							name="correct.marks" id="correct.marks" ondblclick="callShowDiv(this);"><%=label.get("correct.marks")%></label> :</td>
						</td>
					</tr>
					<tr>
						<td  width="4%" align="right" colspan="1"><label class="set"
							name="difficulty.level" id="difficulty.level" ondblclick="callShowDiv(this);"><%=label.get("difficulty.level")%></label> :-
						</td>
						<td  width="20%" align="left" colspan="3">&nbsp;<label class="set"
							name="hard.marks" id="hard.marks" ondblclick="callShowDiv(this);"><%=label.get("hard.marks")%></label>:
							<s:property value="marksForHard" />&nbsp;&nbsp;<s:hidden name="marksForHard" />
						<label class="set" name="medium.marks"
							id="medium.marks" ondblclick="callShowDiv(this);"><%=label.get("medium.marks")%></label> :
							<s:property value="marksForMedium" />&nbsp;&nbsp;<s:hidden name="marksForMedium" />
						<label class="set" name="easy.marks"
							id="easy.marks" ondblclick="callShowDiv(this);"><%=label.get("easy.marks")%></label> :
							<s:property	 value="marksForEasy" /><s:hidden name="marksForEasy" />										
						</td>
					</tr>
		        </s:if>
		        <tr>
		         	<td  colspan="4"><input type="radio" disabled="disabled" value="Equal" name="radioOption" <s:property value="equalDistriRadio"/>
						 onclick="callRadioOptionFun('Equal');" ><label  class = "set"  id="equal.distribution" 
						 name="equal.distribution" ondblclick="callShowDiv(this);"><strong><%=label.get("equal.distribution")%></strong></label>
					</td>
		        </tr>
		        <s:else>
		        	<tr>
						<td  colspan="1"><label class="set"
							name="marks.allocated" id="marks.allocated" ondblclick="callShowDiv(this);"><%=label.get("marks.allocated")%></label> :</td>
						<td  colspan="3"><s:property value="marksAllocatedForEach"/><s:hidden name="marksAllocatedForEach" />
						</td>
					</tr>
		        </s:else>
		        <tr>
					<td  colspan="2"><label class="set"
						name="wrong.marks" id="wrong.marks" ondblclick="callShowDiv(this);"><%=label.get("wrong.marks")%></label> :-</td>
					</td>
				</tr>
				<tr>
					<td  width="20%" align="right" colspan="1"><label class="set"
						name="difficulty.level" id="difficulty.level" ondblclick="callShowDiv(this);"><%=label.get("difficulty.level")%></label> :-
					</td>
					<td  width="20%" align="left" colspan="3">&nbsp;<label class="set"
						name="hard.marks" id="hard.marks" ondblclick="callShowDiv(this);"><%=label.get("hard.marks")%></label>:
						<s:property value="wrongmarksForHard" />&nbsp;&nbsp;<s:hidden name="wrongmarksForHard" />
						<label class="set" name="medium.marks"
								id="medium.marks" ondblclick="callShowDiv(this);"><%=label.get("medium.marks")%></label> :
						<s:property value="wrongmarksForMedium" />&nbsp;&nbsp;<s:hidden name="wrongmarksForMedium" />
						<label class="set" name="easy.marks"
							   id="easy.marks" ondblclick="callShowDiv(this);"><%=label.get("easy.marks")%></label> :
						<s:property value="wrongmarksForEasy" /><s:hidden name="wrongmarksForEasy" />										
					</td>
				</tr>
				<tr>
					<td  width="20%"><label class="set"
						name="no.marks" id="no.marks" ondblclick="callShowDiv(this);"><%=label.get("no.marks")%></label> :</td>
					<td height="22" width="30%"><s:property value="marksForNoAns" /><s:hidden name="marksForNoAns" />
					</td>
				</tr>
			</table><!--End of Marking System  -->
		</td>
	</tr>
	 <tr>
		<td  width="100%"  colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Start of Question Details  -->
				<tr>
					<td width="100%" colspan="4" class="text_head"><strong>Question Details</strong></td>
				</tr>
				<s:hidden name="addQuestion"/>
				<s:hidden name="randomQuestion"/>
				<tr>
					<td  class="formtext" width="100%"  colspan="4">
						<input name="question" type="radio"  disabled="disabled" value="add" <s:property value="addQuestion"/>	onclick="enableQuestion('add');" id="idcheckadd"/>
                    <label class="set" name="from.qesbank"
						id="from.qesbank" ondblclick="callShowDiv(this);"><%=label.get("from.qesbank")%></label>
                    	<input name="question" type="radio" value="random" disabled="disabled" <s:property value="randomQuestion"/> onclick="enableQuestion('random');" id="idcheckrandom"/>
                    <label class="set" name="random.ques"
						id="random.ques" ondblclick="callShowDiv(this);"><%=label.get("random.ques")%></label>
					</td>
						<input type="hidden" id="add.ques">
				</tr>
				<tr>
					<td  class="formtext" width="7%" valign="top" colspan="1">
					<b><label  class = "set" name="subject" id="subject" 
						ondblclick="callShowDiv(this);"><%=label.get("subject")%></label></b> :</span></td>
					<td  width="30%" colspan="1">
						<s:property value="sCode"/><s:hidden name="subject"/>
					</td>
					<td class="formtext" width="7%" colspan="1" valign="top">
					<b><label  class = "set" name="category" id="category" 
						ondblclick="callShowDiv(this);"><%=label.get("category")%></label></b> :</span></td>
					<td  width="30%" colspan="1">										 
						<s:property value="cCode"/><s:hidden name="category"/></td>
				</tr>
				
				
				
				<tr>
					<td width="100%" colspan="4">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Question List table-->
							<tr>
								<td colspan="4"><strong class="text_head">Question Assignment</strong></td>
							</tr>
							<tr>
								<td width="30%"><label	class="set" name="hard.ques" id="hard.ques"	
									ondblclick="callShowDiv(this);"><%=label.get("hard.ques")%></label> :</td>
								<td width="30%"><s:property value="cntRandomHard" /><s:hidden name="cntRandomHard" /></td>
								<td width="30%"><label	class="set" name="marks.hardques" 
									id="marks.hardques"	ondblclick="callShowDiv(this);"><%=label.get("marks.hardques")%></label> :</td>
								<td width="30%"><s:property value="markRandomHard" /><s:hidden name="markRandomHard" /></td>
								</td>
							</tr>
							<tr>
								<td width="30%"><label	class="set" name="medium.ques" id="medium.ques"	
									ondblclick="callShowDiv(this);"><%=label.get("medium.ques")%></label> :</td>
								<td width="30%"><s:property value="cntRandomMedium" /><s:hidden name="cntRandomMedium" /></td>
								<td width="30%"><label	class="set" name="marks.mediumques" 
									id="marks.mediumques"	ondblclick="callShowDiv(this);"><%=label.get("marks.mediumques")%></label> :</td>
								<td width="30%"><s:property value="markRandomMedium" /><s:hidden name="markRandomMedium" /></td>
							</tr>
							<tr>
								<td width="30%"><label	class="set" name="easy.ques" id="easy.ques"	
									ondblclick="callShowDiv(this);"><%=label.get("easy.ques")%></label> :</td>
								<td width="30%"><s:property value="cntRandomEasy" /><s:hidden name="cntRandomEasy" /></td>
								<td width="30%"><label	class="set" name="marks.easyques" 
									id="marks.easyques"	ondblclick="callShowDiv(this);"><%=label.get("marks.easyques")%></label> :</td>
								<td width="30%"><s:property value="markRandomEasy" /><s:hidden name="markRandomEasy" /></td>
							</tr>
							<tr>
								<td width="30%"><b><label	class="set" name="total.listQues" id="total.listQues"	
									ondblclick="callShowDiv(this);"><%=label.get("total.listQues")%></label> :</b></td>
								<td width="30%"><b><s:property value="totalRandomnoOfQus" /><s:hidden name="totalRandomnoOfQus" /></b></td>
								<td width="30%"><b><label	class="set" name="total.listMarks" id="total.listMarks"	
									ondblclick="callShowDiv(this);"><%=label.get("total.listMarks")%></label> :</b></td>
								<td width="30%"><b><s:property value="totalRandomMarkss" /><s:hidden name="totalRandomMarkss" /></b>
								</td>
							</tr>
							<tr>
								<td colspan="4">&nbsp;</td>
							</tr>
							<s:hidden name="addQuestionList"/>
							
							<tr id="pageList">
							<td width="100%" colspan="4">
								<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
									<tr>
										<%!int addListPages =0;%>
										<% try {
											addListPages = Integer.valueOf(String.valueOf(request.getAttribute("addQuestionTotalPages")));
											} catch (Exception e) {
												addListPages=0;
										 	}
										%>
                                        <td align="right" colspan="4"><b>Page:</b>
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
		      	 				 </tr> 		
							</table>
						</td>
					</tr>
							<s:if test="addQuestionList">
								<tr>
									<td colspan="4">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" class="sortable"><!--Question List iterator for add question table-->
											<tr>
												<td width="8%" valign="top" class="formth"><label
													class="set" name="serial.no" id="serial.no"
													ondblclick="callShowDiv(this);"><b><%=label.get("serial.no")%></label></b></td>
												<td width="15%" valign="top" class="formth"><label
													class="set" name="sub.cat" id="sub.cat"
													ondblclick="callShowDiv(this);"><b><%=label.get("sub.cat")%></label></b></td>
												<td width="40%" valign="top" class="formth"><label
													class="set" name="sub.cat" id="sub.cat"
													ondblclick="callShowDiv(this);"><b><%=label.get("ques")%></label></b></td>
												<td width="10%" valign="top" class="formth"><label
													class="set" name="complexi" id="complexi"
													ondblclick="callShowDiv(this);"><b><%=label.get("complexi")%></label></b>
												</td>	
											</tr>
											<s:if test="notAvailable">
												<tr>
													<td width="100%" colspan="8" align="center"><font
														color="red">There is no data to display</font></td>
													</tr>
											</s:if>
											
													<%
															int h1 = 1;
															int f1 = 0;
													%>
													<%!int a = 0;%>
													<s:iterator  value="addlist">
														<tr id="row<%=f1%>" style="display: none;">
															<td width="8%" class="sortabletd" align="center"><%=h1%></td>
															<td width="15%" class="sortabletd">
																<s:property	value="dupQusSubject" />
																<s:hidden name="dupQusSubject" />
															</td>
															<td width="40%" class="sortabletd">
																<s:property	value="dupQusName" />
																<s:hidden name="dupQusName" />
																<s:hidden name="dupSelectQuCode" />
															</td>
															<td width="10%" class="sortabletd">
																<s:property value="dupComplexicity" /> 
																<s:hidden 	name="dupComplexicity" />
														     </td>															
																											
														</tr>
														<%
																h1++;
																f1++;
														%>
													</s:iterator>
													<%a = f1;%>
										</table><!--end of Question List iterator for add question table-->
									</td>
									<input type="hidden" name="count" id="count" value="<%=f1%>" />
								</tr>		
							</s:if>
							<s:else>
								<tr>
									<td colspan="4">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" 
											class="sortable"><!--Question List iterator for random question table-->
											<tr>
												<td width="8%" valign="top" class="formth"><label
													class="set" name="serial.no" id="serial.no"
													ondblclick="callShowDiv(this);"><b><%=label.get("serial.no")%></label></b></td>
												<td width="30%" valign="top" class="formth"><label
													class="set" name="sub.cat" id="sub.cat"
													ondblclick="callShowDiv(this);"><b><%=label.get("sub.cat")%></label></b></td>
												<td width="15%" valign="top" class="formth"><label
													class="set" name="complexi" id="complexi"
													ondblclick="callShowDiv(this);"><b><%=label.get("complexi")%></label></b></td>
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
											<%
												int h = 0;
												int f = 0;
											%>
											<s:iterator status="stat" value="questionList">
											<tr>
												<td width="8%" class="sortabletd" align="center"><%=++h%></td>
												<td width="30%" class="sortabletd">
													<s:property	value="subjectCategory" /><s:hidden name="randomCatCode" />
													<s:hidden name="subjectCategory"/>
													<s:hidden name="randomSubCode"/>
												</td>
												<td width="15%" class="sortabletd">
													<s:property value="RandomComplexicity" /> 
													<s:hidden 	name="RandomComplexicity" />
													<input type="hidden" name="complexicityCode" 
													 value="<s:property value="complexicityCode" />" />
												</td>
												<td width="25%" class="sortabletd" align="center">
													<s:property value="availableQuestion" /> 
													<input type="hidden" value='<s:property value="availableQuestion" />'	
													name="availableQuestion"  /></td>
												<td width="20%" class="sortabletd">
													<s:property value="testQuestion" /> 
													<s:hidden name="testQuestion" />
												</td>
											</tr>
											</s:iterator>
										</table><!--End of Question List iterator for random question table-->
									</td>
								</tr>			
							</s:else>
						</table><!--End of Question List table-->
					</td>
				</tr>		
			</table><!--End of Question Details  -->
		</td>
	</tr>		
	<tr>
		<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td width="78%">
					  <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
					<td width="22%">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>		   	
</table><!--End of Final table  --> 
</s:form>
<script>
var recCount = 10;
onLoad();

function onLoad(){
var questionType = document.getElementById('paraFrm_questionFrom').value;
	if(questionType == "add"){
		document.getElementById('pageList').style.display = '';
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
	else{
		document.getElementById('pageList').style.display = 'none';
	}
}

//=========this function is called on edit button===========================//
function editFun(){
		document.getElementById("paraFrm").action="TestTemplate_edit.action";	   	
	    document.getElementById("paraFrm").submit();
} //end of editFun method
//=======end of function for edit button===================================//

//=========this function is called on cancel button======================//
function cancelFun(){
  	if(document.getElementById('paraFrm_buttonPanelcode').value=="2"){
	   document.getElementById('paraFrm').action="TestTemplate_cancelSec.action";
	} //end of if
	else if(document.getElementById('paraFrm_buttonPanelcode').value=="3"){
	   document.getElementById('paraFrm').action="TestTemplate_cancelThird.action";
	} //end of else if
	else{
	   document.getElementById('paraFrm').action="TestTemplate_cancelFirst.action";
	} //end of else
	document.getElementById('paraFrm').submit();
	return true;
} //end of cancelFun method	  
//=========end of function for cancel button=============================//

//=======this function is called on delete button=======================//
function deleteFun(){
	var conf=confirm("Do you really want to delete this record ?");
	if(conf){
		document.getElementById('paraFrm').action="TestTemplate_delete.action";
	  	document.getElementById('paraFrm').submit();
	} //end of if
} //end of deleteFun method
//=======end of function for delete button==============================//

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
		document.getElementById('addQuesPageNoField').focus();
} //end of callAddQuesPage method


</script>