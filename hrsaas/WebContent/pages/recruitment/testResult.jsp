<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TestResult" id="paraFrm" theme="simple">
	<s:hidden name="resultCode"/>
	<s:hidden name="hiringManager"/>
	<s:hidden name="hiringManagerCode"/>
	<s:hidden name="showFlag"/>
	<s:hidden name="fromSchdIntListFlag"/>
	<s:hidden name="reqDate"/>	
	<s:hidden name="hidTestResultFlag"/> 
	<s:hidden name="dummyField"/>
	<s:hidden name="hidTestTypeForSchTest"></s:hidden>
	<table class="formbg" width="100%"><!-- main table -->
        <tr>
        	<td width="100%" colspan="3">
        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
        			 <tr>
			          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
			          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
			          <td width="93%" class="txt"><strong class="text_head">Test Result</strong></td>
			          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
			        </tr>
        		</table>
        	</td>
        </tr>
        
        <tr>
	        <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	           <tr>
	             <td width="78%" colspan="2">
					<s:submit cssClass="token" value="Schedule For Next Test"
            			onclick="return scheduleForNextRound();" action="TestResult_toScheduleTest"/>
            		<s:submit cssClass="token" value="Schedule For Interview"
            			onclick="return scheduleForInterview();" action="TestResult_toScheduleInterview"/>	
            		<s:submit cssClass="save" value="   Save" onclick="return validateSave();" action="TestResult_save"/>
            		<s:submit cssClass="reset" value=" Clear" action="TestResult_reset"/>	
				 </td>
	             <td width="22%"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
	          </tr>
	         </table>            
	         <label></label></td>
	   </tr>
	   
	   <tr>
        	<td width="100%" colspan="3">
        		<table width="770" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 1 -->
        			<tr>
        				<td width="100%" class="txt" colspan="4"><strong class="text_head">Test Result</strong></td>
                    </tr>
                    
                    <tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2"><!--Table 1-->	
			                    <tr>
			                    	<td width="17%"> <label  class = "set" name="reqs.code" id="requisition.code" 
								            	ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
								            	 : <font color="red" size="2">*</font></td>
								    <td width="30%"><s:textfield name="requisitionName" size="25" readonly="true"/>
									    <img class="iconImage" src="../pages/images/recruitment/search2.gif" width="15"
											height="16" onclick="javascript:callsF9(500,325,'TestResult_f9RequisitionCodeAction.action');"/>
											<s:hidden name="requisitionCode"/></td>
			                    	
			                   		<td width="15%"> <label  class = "set" name="position" id="position" 
								            	ondblclick="callShowDiv(this);"><%=label.get("position")%></label> : </td>
								    <td width="28%"><s:textfield name="position" size="25" readonly="true"/></td>
			                    </tr>
			                    
			                     <tr>
			                    	<td width="17%" nowrap="nowrap"> <label  class = "set" name="fDate" id="from.date" 
								            	ondblclick="callShowDiv(this);"><%=label.get("fDate")%></label> : </td>
								    <td width="30%"><s:textfield name="fromDate" size="25" onkeypress="return numbersWithHiphen();" maxlength="10"/>
									    <span id="birthDate"><s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
											<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
													height="16" border="0" align="absmiddle" />
											</s:a></span></td>
			                    	
			                   		<td width="15%" nowrap="nowrap"> <label  class = "set" name="tDate" id="to.date" 
								            	ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label> : </td>
								    <td width="28%"><s:textfield name="toDate" size="25" onkeypress="return numbersWithHiphen();" maxlength="10"/>
								    	<span id="birthDate"><s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
												<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
													height="16" border="0" align="absmiddle" />
											</s:a></span></td>
			                    </tr>
			                    
			                    <tr>
			                    	<td width="17%"> <label  class = "set" name="result.type" id="result.type" 
								            	ondblclick="callShowDiv(this);"><%=label.get("result.type")%></label>
								            	 : <font color="red" size="2">*</font></td>
								    <td width="30%"><s:select name="testType" cssStyle="width:140" list="#{'B':'Both', 'W':'Written Test',
								    	'O':'Online Test'}" onchange="callToSetHiddenTestType();"/></td>
			                    	
			                   		<td width="15%">&nbsp;</td>
								    <td width="28%">&nbsp;</td>
			                    </tr>
			                   </table>
			                </td>
			            </tr>
        		</table><!-- table 1 -->
        	</td>
        </tr>
        
	   <tr>
        	<td width="100%" colspan="3">
        		<table width="770" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 2 -->
        			<tr>
        				<td width="100%" class="txt" colspan="4"><strong class="text_head">Search Criteria</strong></td>
                    </tr>
                    
                    <tr>
                    	<td width="15%"> <label  class = "set" name="marks.obtained" id="marks.obtained" 
					            	ondblclick="callShowDiv(this);"><%=label.get("marks.obtained")%></label> : </td>
					    <td width="30%">
					    	<s:select name="searchCriteria" cssStyle="width:70" list="#{'':'Select', '=':'=', '<':'<',
					    		'>':'>', '<=':'<=', '>=':'>=', '!=':'!='}"/>
					    	<s:textfield name="marksObtained" size="10"  maxlength="5" onkeypress="return numbersWithDot();"/></td>
                    	
                   		<td width="15%"> <label  class = "set" name="result" id="result" 
					            	ondblclick="callShowDiv(this);"><%=label.get("result")%></label> : </td>
					    <td width="30%"><s:select name="resultType" cssStyle="width:140" list="#{'':'Select', 'F':'Fail',
					    	'P':'Pass'}"/></td>
                    </tr>
        		</table><!-- table 2 -->
        	</td>
        </tr>
        
        <!--<tr>
			<td height="5" colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="1" /></td>
		</tr>
              
                    
       -->
       <tr>
        	<td width="100%" colspan="3">
        		<table width="770" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 2 -->
        		
       <tr>
            <td width="100%" colspan="3" align="center"><s:submit cssClass="token" value="Show Result"
            	onclick="return showTestResult();" action="TestResult_showTestResult"/></td>
        </tr>
        </table></td></tr>
        <tr>
        	<td width="100%" colspan="3">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 3 -->
        			<tr>
        				<td width="100%" class="txt" colspan="3"><strong class="text_head">Result List For Written Test</strong></td>
                    </tr>
                    
                    <tr>
                   		<td>
                  			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="sortable"><!-- table 4 -->
                  				<tr>
				        			<td width="6%"   class="formth" align="center">
				        				<b><label  class = "set" name="serial.no" id="serial.no" 
					            			ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
					                <!--<td width="15%"  class="formth" align="center">
					                	<b><label  class = "set" name="reqs.code" id="reqs.code1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>  -->
					                <td  nowrap="nowrap"   class="formth" align="center">
					                	<b><label  class = "set" name="cand.name" id="cand.name1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
					                <td width="15%"  class="formth" align="center">
					                	<b><label  class = "set" name="email.id" id="email.id1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></b></td>
					                <td    class="formth" align="center">
					                	<b><label  class = "set" name="contact.no" id="contact.no1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></label></b></td>
					            	<td nowrap="nowrap"   class="formth" align="center"><b><label  class = "set" name="test.round" id="test.round" 
			            				ondblclick="callShowDiv(this);"><%=label.get("test.round")%></label></b></td>		
					                <td     class="formth" align="center">
					                	<b><label  class = "set" name="test.date" id="test.date1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("test.date")%></label></b></td>
					                <td   class="formth" align="center" >
					                	<b><label  class = "set" name="test.time" id="test.time1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("test.time")%></label></b></td>
					                <td width="8%"   class="formth" align="center">
					                	<b><label  class = "set" name="marks.obtained" id="marks.obtained1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("marks.obtained")%></label></b></td>
					                <td  width="8%"    class="formth" align="right">
					                	<b><label  class = "set" name="total.marks1" id="total.marks1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("total.marks1")%></label></b></td>
					                <td width="10%"   class="formth" align="center">
					                	<b><label  class = "set" name="result" id="result1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("result")%></label></b></td>
					            	<td width="10%"   class="formth"><b><label  class = "set" name="Comments" id="Comments" 
											ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td>
									<td width="2%"  class="formth" align="center">&nbsp;</td>		
					                <td width="3%"   class="formth" align="center">
					                	<s:checkbox name="writtenChkAll" onclick="selectAll('writtenCount', 'writtenChkAll', 'writtenChk', 'written');"/></td>
					            </tr>
					            <s:if test="notAvailable">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">There is no data to display</font></td>
									</tr>
								</s:if>
					            
					            <%! int i = 0 ; %>
					            <% int k = 1; int c=0;%>
					            
					            <s:iterator status="status" value="writtenTestList">
					            	<tr><s:hidden name="writtenResultCode" id='<%="writtenResultCode"+c %>'/><s:hidden name="resultFlag"/>
					            		<s:hidden name="writtenTestDtlCode" id='<%="writtenTestDtlCode"+c %>'/>
					        			<td width="6%"   class="sortabletd" align="center"><%=k %>&nbsp;</td>
						                <!--<td width="15%" valign="middle" class="sortabletd" align="center"><s:property value="writtenReqName"/>
						                	<s:hidden name="writtenReqName"/><s:hidden name="writtenReqCode"/>&nbsp;</td>  -->
						                	<s:hidden name="writtenReqName"/><s:hidden name="writtenReqCode"/><s:hidden name="writtenReqName"/>
						                <td width="25%"  class="sortabletd" align="center"><s:property value="writtenCandName"/>
						                	<s:hidden name="writtenCandName"/><s:hidden name="writtenCandCode"/>&nbsp;</td>
						                <td width="15%"  class="sortabletd" align="center" valign="middle" ><s:property value="writtenEmailId"/>
						                	<s:hidden name="writtenEmailId"/></td>
						                <td  class="sortabletd" align="center" nowrap="nowrap" ><s:property value="writtenContactNo"/>
						                	<s:hidden name="writtenContactNo"/>&nbsp;</td>
						                 <td width="10%" valign="middle"  class="sortabletd" align="center">
						                	<s:property value="writtenTestRound"/><s:hidden name="writtenTestRound"/> </td>	
						                <td nowrap="nowrap"valign="middle"  class="sortabletd" align="center">
						                	<s:property value="writtenTestDate"/><s:hidden name="writtenTestDate"/> </td>
						                <td nowrap="nowrap" class="sortabletd" align="center" nowrap="nowrap">
											<s:property value="writtenTestTime"/><s:hidden name="writtenTestTime"/> </td>
						                <td width="8%"  class="sortabletd" align="right"    >
						                	<s:if test="writtenFlag"><s:property value="writtenObtMarks"/>
						                		<s:hidden name="writtenObtMarks" id='<%="writtenObtMarks"+c %>'/>
						                	</s:if>
						                	<s:else>
						                		<s:textfield name="writtenObtMarks" id='<%="writtenObtMarks"+c %>' size="4" maxlength="5"
						                			onkeypress="return numbersWithDot();" cssStyle="text-align: right;"  />
						                	</s:else></td>
						                <td width="8%"  class="sortabletd" align="right">
						                	<s:if test="writtenFlag"><s:property value="writtenTotalMarks"/>
						                		<s:hidden name="writtenTotalMarks" id='<%="writtenTotalMarks"+c %>'/>
						                	</s:if>
						                	<s:else>
						                		<s:textfield name="writtenTotalMarks" id='<%="writtenTotalMarks"+c %>' size="4" maxlength="5"
													onkeypress="return numbersWithDot();" cssStyle="text-align: right;"  />
						                	</s:else> </td>
						                <td width="10%"   class="sortabletd" align="center" ><s:hidden name="writtenFlag" id='<%="writtenFlag"+c %>'/>
						                	<s:if test="writtenFlag"><s:property value="writtenHiddenResult"/>
						                		<s:hidden name="writtenResult" id='<%="writtenResult"+c %>'/>
						                	</s:if>
						                	<s:else>
						                		<s:select name="writtenResult" id='<%="writtenResult"+c %>'
						                			list="#{'':'Select', 'F':'Fail', 'P':'Pass'}"/>
						                	</s:else> </td>
						                <td class="sortableTD" width="10%"><s:textarea label="%{getText('Comments')}" 
											theme="simple" cols="15" rows="2" name="writtenComments" id='<%="writtenComments"+c%>' /></td>
						               <td class="sortableTD" width="2%" valign="bottom"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
													onclick="javascript:callWindow('<%="writtenComments"+c%>','Comments','','200','200');" ></td> 	
						                <td width="3%"  valign="middle" class="sortabletd" align="center">
						                	<input type="checkbox" name="writtenChk" id="<%="writtenChk"+c %>" 
						                		onclick="deselectAll('writtenCount', 'writtenChkAll', 'writtenChk', 'written'); callChk('<%="written"+c %>')"/>
			                        				<input type="hidden" name="written" id="<%="written"+c %>" value="N"/>&nbsp;</td>
					            	</tr>
					            	<%k++; c++; %>
					            </s:iterator>
					            <% i=k ; %>
                  			</table><!-- table 4 -->
                  			<input type="hidden" name="writtenCount" id="writtenCount" value="<%=c%>"/>
                  		</td>
                  	</tr>
                  	
                  	<tr>
			          <td colspan="11" width="100%"><img src="../pages/common/css/default/images/space.gif" width="5" height="2" /></td>
			        </tr>
        		</table><!-- table 3 -->
        	</td>
        </tr>
        
        <tr>
        	<td width="100%" colspan="3">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 5 -->
        			<tr>
        				<td width="100%" class="txt" colspan="4"><strong class="text_head">Result List For On Line Test</strong></td>
                    </tr>
                    
                    <tr>
                   		<td>
                  			<table width="100%" border="0" cellpadding="0" id="calulateMarksTable" cellspacing="0" class="sortable"><!-- table 6 -->
                  				<tr>
				        			<td width="6%"   class="formth" align="center">
				        				<b><label  class = "set" name="serial.no" id="serial.no1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
					                <!--<td width="15%"  class="formth" align="center">
					                	<b><label  class = "set" name="reqs.code" id="reqs.code2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>-->
					                <td nowrap="nowrap"  class="formth" align="center">
					                	<b><label  class = "set" name="cand.name" id="cand.name2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
					                <td width="12%"  class="formth" align="center">
					                	<b><label  class = "set" name="email.id" id="email.id2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></b></td>
					                <td nowrap="nowrap"  class="formth" align="center">
					                	<b><label  class = "set" name="contact.no" id="contact.no2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></label></b></td>
					            	<td nowrap="nowrap"  class="formth" align="center"><b><label  class = "set" name="test.round" id="test.round" 
			            				ondblclick="callShowDiv(this);"><%=label.get("test.round")%></label></b></td>		
					                <td nowrap="nowrap"   class="formth" align="center">
					                	<b><label  class = "set" name="test.date" id="test.date2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("test.date")%></label></b></td>
					                <td nowrap="nowrap" class="formth" align="center">
					                	<b><label  class = "set" name="test.time" id="test.time2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("test.time")%></label></b></td>
					                
					                
					                <td width="8%" class="formth" align="center">
					                	<b><label class = "set" name="objectiveMarksObtainedLabel" id="objectiveMarksObtainedLabel" 
					            			ondblclick="callShowDiv(this);"><%=label.get("objectiveMarksObtainedLabel")%></label></b>
					            	</td>
					                <td width="8%"  class="formth" align="center">
					                	<b><label  class = "set" name="subjectiveMarksObtainedLabel" id="subjectiveMarksObtainedLabel" 
					            			ondblclick="callShowDiv(this);"><%=label.get("subjectiveMarksObtainedLabel")%></label></b>
					            	</td>
					                
					                
					                <td width="8%"  class="formth" align="center">
					                	<b><label  class = "set" name="marks.obtained" id="marks.obtained2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("marks.obtained")%></label></b>
					            	</td>
					                <td width="8%"   class="formth" align="center">
					                	<b><label  class = "set" name="total.marks1" id="total.marks1" 
					            			ondblclick="callShowDiv(this);"><%=label.get("total.marks1")%></label></b></td>
					                <td width="5%"   class="formth" align="center">
					                	<b><label  class = "set" name="result" id="result2" 
					            			ondblclick="callShowDiv(this);"><%=label.get("result")%></label></b></td>
					            	<td width="8%"   class="formth" align="center">
					            		<b><label  class = "set" name="view.result" id="view.result" 
					            			ondblclick="callShowDiv(this);"><%=label.get("view.result")%></label></b></td>
					            	<td width="10%"  class="formth"><b><label  class = "set" name="Comments" id="Comments" 
											ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td>
									<td width="2%"  class="formth" align="center">&nbsp;</td>			
					                <td width="4%"   class="formth" align="center">
					                	<s:checkbox name="onLineChkAll" onclick="selectAll('onLineCount', 'onLineChkAll', 'onLineChk', 'onLine');"/></td>
					            </tr>
					            <s:if test="notAvailableOnline">
									<tr>
										<td width="100%" colspan="10" align="center"><font
											color="red">There is no data to display</font></td>
									</tr>
								</s:if>
					            <%! int j = 0 ; %>
					            <% int h = 1; int g=0;%>
					            
					            <s:iterator status="status" value="onLineTestList">
					            	<tr>
					        			<td width="6%"  valign="middle" class="sortabletd" align="center"><%=h %> </td>
						                <!--<td width="15%" valign="middle" class="sortabletd"><s:property value="onLineReqName"/>
						                	<s:hidden name="onLineReqName"/><s:hidden name="onLineReqCode"/>&nbsp;</td>  -->
						                <s:hidden name="onLineReqName"/>
						                <s:hidden name="onLineReqCode"/>
						                <s:hidden name="onlineTestTemplateCode"/>
						                <s:hidden name="onlineTestType"/>
						                <s:hidden name="onlineResultCode" id='<%="onlineResultCode"+g %>'/>
						                <s:hidden name="onlineTestDtlCode" id='<%="onlineTestDtlCode"+g %>'/>		
						                <td width="15%"  class="sortabletd"><s:property value="onLineCandName"/>
						                	<s:hidden name="onLineCandName"/><s:hidden name="onLineCandCode"/> </td>
						                <td width="15%"  class="sortabletd"><s:property value="onLineEmailId"/>
						                	<s:hidden name="onLineEmailId"/> </td>
						                <td nowrap="nowrap" class="sortabletd"><s:property value="onLineContactNo"/>
						                	<s:hidden name="onLineContactNo"/> </td>
						                 <td width="10%"  class="sortabletd" align="center">
						                	<s:property value="onLineTestRound"/><s:hidden name="onLineTestRound"/>  </td>	
						                <td nowrap="nowrap" class="sortabletd" align="center">
						                	<s:property value="onLineTestDate"/><s:hidden name="onLineTestDate"/> </td>
						                <td nowrap="nowrap" class="sortabletd" align="center">
											<s:property value="onLineTestTime"/><s:hidden name="onLineTestTime"/> </td>
						               
						               
						               
						                <td nowrap="nowrap" class="sortabletd" align="center">
											<s:property value="objectiveMarksObtained"/>
											<s:hidden name="objectiveMarksObtained" id='<%="objectiveMarksObtained"+g %>'/> 
										</td>
						               
						                <td nowrap="nowrap" class="sortabletd" align="center">
											<s:if test="bothOrSubjectiveTypeTestFlag">
												<s:textfield name="subjectiveMarksObtained" id='<%="subjectiveMarksObtained"+g %>' 
							                	size="4" maxlength="5" onkeypress="return numbersOnly();" onchange="calculateMarksObtained();" cssStyle="text-align: right;" />
							                </s:if> 
							                <s:else>
												<s:property value="subjectiveMarksObtained"/>
												<s:hidden name="subjectiveMarksObtained" id='<%="subjectiveMarksObtained"+g %>'/>														                
							                </s:else>
										</td>
						               
						               
						               
						               
						                <td width="8%"  class="sortabletd" align="right">
							                <s:hidden name="onlineFlag" id='<%="onlineFlag"+g %>'/>
							                	<s:textfield size="2" name="onLineObtMarks" readonly="true" cssStyle="background-color: #F2F2F2;" id='<%="onLineObtMarks"+g %>' />
							               <!--  
							                <s:if test="onlineFlag">
								                <s:textfield size="4" readonly="true" name="onLineObtMarks" id='<%="onLineObtMarks"+g %>' />
							                </s:if>
							               
							               		<s:else>
							                	<s:textfield name="onLineObtMarks" id='<%="onLineObtMarks"+g %>' 
							                	size="4" maxlength="5" onkeypress="return numbersOnly();" cssStyle="text-align: right;" />
							                </s:else>
							                --> 
						                </td>
						                
						                <!-- 
						                <td width="8%"   class="sortabletd" align="right" >
							                <s:if test="onlineFlag">
							                	<s:property value="onLineTotalMarks"/>
							                	<s:hidden name="onLineTotalMarks" id='<%="onLineTotalMarks"+g %>'/>
							                </s:if>
							                <s:else>
							                	<s:textfield name="onLineTotalMarks" id='<%="onLineTotalMarks"+g %>' 
							                	size="4" maxlength="5" onkeypress="return numbersOnly();" cssStyle="text-align: right;"  />
							                </s:else>
										</td>
						                 -->
						                
						                <td width="8%"   class="sortabletd" align="right" >
						                	<s:property value="onLineTotalMarks"/>
						                	<s:hidden name="onLineTotalMarks" id='<%="onLineTotalMarks"+g %>'/>
										</td>
						                
						                <td width="8%" class="sortabletd" align="center">
						                	<s:if test="bothOrSubjectiveTypeTestFlag">
						                		<s:select name="onLineResult" id='<%="onLineResult"+g %>'
						                			list="#{'':'Select', 'F':'Fail', 'P':'Pass'}"/>
						                	</s:if>
						                	<s:else>
												<s:property value="onLineResult"/>
						                		<s:hidden name="onLineResult" id='<%="onLineResult"+g %>'/>						                		
						                	</s:else> 
						                </td>
						                 <td width="8%"   class="sortabletd">
						                	<input type="button" value="View" class="token" onclick="viewTestDetails('<s:property value="onLineCandCode"/>', '<s:property value="onlineTestDtlCode"/>', '<s:property value="onlineTestTemplateCode" />')"/></td>
						                 <td class="sortableTD" width="10%"><s:textarea label="%{getText('Comments')}" 
											theme="simple" cols="15" rows="2" name="onlineComments" id='<%="onlineComments"+g%>' /></td>
						               <td class="sortableTD" width="2%" valign="bottom"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
													onclick="javascript:callWindow('<%="onlineComments"+g%>','Comments','','200','200');" ></td> 		
						                 <td width="4%"   class="sortabletd" align="center">
						                	<input type="checkbox" name="onLineChk" id='<%="onLineChk"+g %>' 
						                		onclick="deselectAll('onLineCount', 'onLineChkAll', 'onLineChk'); callChk('<%="onLine"+g %>')"/>
			                        				<input type="hidden" name="onLine" id='<%="onLine"+g %>' value="N"/>&nbsp;</td>
					            	</tr>
					            	<%h++; g++; %>
					            </s:iterator>
					            <% j=h ; %>
                  			</table><!-- table 6 -->
                  			<input type="hidden" name="onLineCount" id="onLineCount" value="<%=g%>"/>
                  		</td>
                  	</tr>
        		</table><!-- table 5 -->
        	</td>
        </tr>
        
       <!-- 
       	<tr>
            	<td width="58%" colspan="6" align="left"><font color="red">
            		Note&nbsp;:&nbsp;Once saved the result,&nbsp; you can not edit the record.</font></td>
            	<td width="42%" colspan="5">&nbsp;</td>
        </tr>
        --> 
        
        <tr>
            <td width="100%" colspan="3" align="left">
            	<s:submit cssClass="token" value="Schedule For Next Test"
            			onclick="return scheduleForNextRound();" action="TestResult_toScheduleTest"/>
            	<s:submit cssClass="token" value="Schedule For Interview"
            	onclick="return scheduleForInterview();" action="TestResult_toScheduleInterview"/>
            	<s:submit cssClass="save" value="   Save" onclick="return validateSave();" action="TestResult_save"/>
            	<s:submit cssClass="reset" value=" Clear" action="TestResult_reset"/>		
            </td>
        </tr>
	   
	</table><!-- main table -->
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
	function showTestResult(){
		if(document.getElementById("paraFrm_requisitionName").value == ""){
			alert("Please select "+document.getElementById("requisition.code").innerHTML.toLowerCase());
			document.getElementById("paraFrm_requisitionName").focus();
			return false;
		}
		
		var fromDate = document.getElementById("paraFrm_fromDate").value;
		var toDate   = document.getElementById("paraFrm_toDate").value
		
		if(fromDate != ""){
			if(!validateDate("paraFrm_fromDate", "from.date"))return false;
		}
		
		if(toDate != ""){
			if(!validateDate("paraFrm_toDate", "to.date"))return false;
		}
		
		if(fromDate != "" && toDate != ""){
			if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_toDate", "from.date", "to.date"))return false;
		}
		
		if(document.getElementById("paraFrm_searchCriteria").value != ""){
			if(document.getElementById("paraFrm_marksObtained").value == ""){
				alert("Please enter "+document.getElementById("marks.obtained").innerHTML.toLowerCase());
				document.getElementById("paraFrm_marksObtained").focus();
				return false;
			}
		}
		return true;
	}
	
	function selectAll(count, all, chk, hidden){
		var count = document.getElementById(count).value;
		
		if(document.getElementById('paraFrm_'+all).checked){
			for(var i=0; i<count; i++){
				document.getElementById(chk+i).checked = true;
				document.getElementById(hidden+i).value = "Y";
			}
		}else{
			for(var i=0; i<count; i++){
				document.getElementById(chk+i).checked = '';
				document.getElementById(hidden+i).value = "N";
			}
		}
	}
	
	function deselectAll(count, all, chk, hidden){
		var count = document.getElementById(count).value;
		
		for(var i=0; i<count; i++){
			if(!document.getElementById(chk+i).checked){
				document.getElementById('paraFrm_'+all).checked = '';
			}
		}
	}
	
	function validateSave(){
		var count = document.getElementById("writtenCount").value;
		var flag  = false;
		var editCount = 0;
		var onlineEditCount = 0;
		
		var onlineCount = document.getElementById('onLineCount').value;
		var showFlag = document.getElementById("paraFrm_showFlag").value;
		if(document.getElementById("paraFrm_requisitionName").value == ""){
			alert("Please select "+document.getElementById("requisition.code").innerHTML.toLowerCase());
			document.getElementById("paraFrm_requisitionName").focus();
			return false;
		}
		
		for(var i=0; i<count; i++){
			if(document.getElementById("writtenChk"+i).checked){
				flag = true;
			}
			
			if(document.getElementById("writtenFlag"+i).value == "true"){
				editCount++;
			}
		}
		
		for(var k=0; k<onlineCount; k++){
			if(document.getElementById("onLineChk"+k).checked){
				flag = true;
			} //end of if
			
			if(document.getElementById("onlineFlag"+k).value == "true"){
				onlineEditCount++;
			} //end of if
		} //end of loop
		
		
		if(showFlag=="" || showFlag=="false"){
			alert("Please click on Show Result button");
			return false;
		}
		
		if(count == 0 && onlineCount == 0){
			alert("There is no record in the list");
			return false;
		}
		 
	/*	
		if(eval(editCount) == eval(count) && eval(onlineEditCount) == eval(onlineCount)){
			alert("Result is already saved");
			return false;
		}
	*/	
		if(!flag){
			alert("Please select atleast one record");
			return false;
		}
		
		for(var i=0; i<count; i++){
			if(document.getElementById("writtenChk"+i).checked){
				var obtMarks = document.getElementById("writtenObtMarks"+i).value;
				var totMarks = document.getElementById("writtenTotalMarks"+i).value;
				var writtenResultCode = document.getElementById("writtenResultCode"+i).value;
				
				if(writtenResultCode != ""){
						alert('Records once saved cannot be edited');
						return false;					
				} //end of if
				
				if(obtMarks == ""){
					alert("Please enter "+document.getElementById("marks.obtained").innerHTML.toLowerCase()+" for the selected candidates");
					document.getElementById("writtenObtMarks"+i).focus();
					return false;
				}
				if(totMarks == ""){
					alert("Please enter "+document.getElementById("total.marks1").innerHTML.toLowerCase()+" for the selected candidates");
					document.getElementById("writtenTotalMarks"+i).focus();
					return false;
				}
				if(eval(obtMarks) > eval(totMarks)){
					alert(document.getElementById("marks.obtained").innerHTML.toLowerCase()+" should be less than or equal to "
							+document.getElementById("total.marks1").innerHTML.toLowerCase());
					document.getElementById("writtenObtMarks"+i).focus();
					return false;
				}
				if(document.getElementById("writtenResult"+i).value == ""){
					alert("Please select "+document.getElementById("result").innerHTML.toLowerCase()+" for the selected candidates");
					document.getElementById("writtenResult"+i).focus();
					return false;
				}
				try{
					var writtenComment = document.getElementById("writtenComments"+i).value;
					var writtenTest=document.getElementById('Comments').innerHTML.toLowerCase();
					if(writtenComment != "" && writtenComment.length > 200){
						alert("Maximum length of "+writtenTest+" is 200 characters.");
						return false;
					} //end of if
				}
				catch(e){
					alert(e);
				}
				
			}
		}
		
		try{
			for(var k=0; k<onlineCount; k++){
				if(document.getElementById("onLineChk"+k).checked){
					var onlineObtMarks = document.getElementById("onLineObtMarks"+k).value;
					var onlineTotMarks = document.getElementById("onLineTotalMarks"+k).value;
					var onlineResultCode = document.getElementById("onlineResultCode"+k).value;
					
				/*	
					if(onlineResultCode != ""){
						alert('Records once saved cannot be edited');
						return false;					
					} //end of if
				*/	
					if(onlineObtMarks == ""){
						alert("Please enter "+document.getElementById("marks.obtained").innerHTML.toLowerCase()+" for the selected candidates");
						document.getElementById("onLineObtMarks"+k).focus();
						return false;
					} //end of if
					if(onlineTotMarks == ""){
						alert("Please enter "+document.getElementById("total.marks1").innerHTML.toLowerCase()+" for the selected candidates");
						document.getElementById("onLineTotalMarks"+k).focus();
						return false;
					} //end of if
					
					if(eval(onlineObtMarks) > eval(onlineTotMarks)){
						alert(document.getElementById("marks.obtained").innerHTML.toLowerCase()+" should be less than or equal to "
								+document.getElementById("total.marks1").innerHTML.toLowerCase());
						document.getElementById("onLineObtMarks"+k).focus();
						return false;
					} //end of if
					
					if(document.getElementById("onLineResult"+k).value == ""){
						alert("Please select "+document.getElementById("result").innerHTML.toLowerCase()+" for the selected candidates");
						document.getElementById("onLineResult"+k).focus();
						return false;
					} //end of if
					
					try{
					var onlineComment = document.getElementById("onlineComments"+k).value;
					var onlineTest=document.getElementById('Comments').innerHTML.toLowerCase();
						if(onlineComment != "" && onlineComment.length > 200){
							alert("Maximum length of "+onlineTest+" is 200 characters.");
							return false;
						} //end of if
					}catch(e){
						alert(e);
					}
					
				} //end of if
			} //end of online loop
			
		}catch(e){
			alert('exception in online lop'+e);
		}
		
		//alert(1);
		var conf = confirm("Do you really want to save the test result ?");
		//alert(2);
		if(conf)return true;
		else return false;
	}
	
	function scheduleForInterview(){
		var writtenCount = document.getElementById("writtenCount").value;
		var onLineCount  = document.getElementById("onLineCount").value;
		var showFlag = document.getElementById("paraFrm_showFlag").value;
		var writtenFlag  = false;
		var onLineFlag   = false;
		document.getElementById('paraFrm_hidTestResultFlag').value="true";
		for(var i=0; i<writtenCount; i++){
			if(document.getElementById("writtenChk"+i).checked){
				writtenFlag = true;
			}
		}
		
		for(var i=0; i<onLineCount; i++){
			if(document.getElementById("onLineChk"+i).checked){
				onLineFlag = true;
			}
		}
		
		if(document.getElementById("paraFrm_requisitionName").value == ""){
			alert("Please select "+document.getElementById("requisition.code").innerHTML.toLowerCase());
			document.getElementById("paraFrm_requisitionName").focus();
			return false;
		}
		
		if(showFlag=="" || showFlag=="false"){
			alert("Please click on Show Result button");
			return false;
		}
		
		if(writtenCount == 0 && onLineCount == 0){
			alert("There are no records for schedule for interview");
			return false;
		}
		
		if(!writtenFlag && !onLineFlag){
			alert("Please select atleast one record");
			return false;
		}
		return true;
	}
	
	function viewTestDetails(candCode, testDetailCode, onlineTestTemplateCode) {
		var reqCode = document.getElementById('paraFrm_requisitionCode').value;
		window.open('InterviewSchedule_viewTestDetails.action?requisitionCode='+reqCode+'&candCode='+candCode+'&testDetailCode='+testDetailCode+'&onlineTestTemplateCode='+onlineTestTemplateCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	} //end of method
	
	function scheduleForNextRound(){
	try{
		var writtenCount = document.getElementById("writtenCount").value;
		var onLineCount  = document.getElementById("onLineCount").value;
		var writtenFlag  = false;
		var onLineFlag   = false;
		var showFlag = document.getElementById("paraFrm_showFlag").value;
		
		if(document.getElementById("paraFrm_requisitionName").value == ""){
			alert("Please select "+document.getElementById("requisition.code").innerHTML.toLowerCase());
			document.getElementById("paraFrm_requisitionName").focus();
			return false;
		}
		
		for(var i=0; i<writtenCount; i++){
			if(document.getElementById("writtenChk"+i).checked){
				writtenFlag = true;
			} //end of if
		} //end of loop
		
		for(var i=0; i<onLineCount; i++){
			if(document.getElementById("onLineChk"+i).checked){
				onLineFlag = true;
			} //end of if
		} //end of loop
		
		
		
		if(showFlag=="" || showFlag=="false"){
			alert("Please click on Show Result button");
			return false;
		}
		
		if(writtenCount == 0 && onLineCount == 0){
			alert("There is no records in list");
			return false;
		}
		
		if(!writtenFlag && !onLineFlag){
			alert("Please select atleast one record");
			return false;
		} //end of if
		
		for(var i=0; i<writtenCount; i++){
			if(document.getElementById("writtenChk"+i).checked){
				var isSaveFlag = document.getElementById("writtenFlag"+i).value;
				
				if(isSaveFlag == "false"){
					alert("Please save the selected record");
					return false;
				} //end of if
			} //end of if
		} //end of loop
		
		for(var k=0; k<onLineCount; k++){
			if(document.getElementById("onLineChk"+k).checked){
				var isSaveOnlineFlag = document.getElementById("onlineFlag"+k).value;
				
				if(isSaveOnlineFlag == "false"){
					alert("Please save the selected record");
					return false;
				} //end of if
			} //end of if
		} //end of loop
		
		var con = confirm("Do you really want to schedule next test for this candidate?");
		if(con) {
		  return true;
		} else {
			return false;
		}
		
		
	}catch(e){
		alert(e);
	}
	} //end of method
	
	function callToSetHiddenTestType(){
		var testType = document.getElementById("paraFrm_testType").value;
		document.getElementById("paraFrm_hidTestTypeForSchTest").value = testType;
	}
	
	onload();
	
	function onload(){
		document.getElementById("paraFrm_hidTestTypeForSchTest").value = "B";
	}
	
	function calculateMarksObtained() {
		var table = document.getElementById('calulateMarksTable'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;
		var totalObtainedMarks = 0;
		var subjectiveMarksObtained = 0;
		var objectiveMarksObtained = 0;
		for (var i=0; i<iteration; i++) {
			subjectiveMarksObtained = document.getElementById('subjectiveMarksObtained'+i).value;
			if (subjectiveMarksObtained == '') {
				subjectiveMarksObtained = '0';
				document.getElementById('subjectiveMarksObtained'+i).value = subjectiveMarksObtained;
			}
			
			var objectiveMarksObtained = document.getElementById('objectiveMarksObtained'+i).value;
			if (objectiveMarksObtained == '') {
				objectiveMarksObtained = '0';
			}
			
			totalObtainedMarks = (eval(objectiveMarksObtained)+eval(subjectiveMarksObtained));
			document.getElementById('onLineObtMarks'+i).value = totalObtainedMarks; 
		}	
	}
</script>