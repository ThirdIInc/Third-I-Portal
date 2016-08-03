<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="RatingScaleDefinition" validate="true" id="paraFrm"	theme="simple">
	
   <s:hidden name="sMode"></s:hidden>
   <s:hidden name="sQuestionCode"></s:hidden>
   <s:hidden name="addFlg"></s:hidden>
   <s:hidden  name="iflg"></s:hidden>
   <s:hidden name="paraId"/>
   <s:hidden name="sAllowFractionFlg" ></s:hidden>
   
  <%-- Progress Bar - Start --%>
  <div align="center" id="overlay" style="z-index:3;  absolute;margin:0px;left:0;top:0;background-color:#A7BEE2;background-image:url('images/grad.gif');filter:progid:DXImageTransform.Microsoft.alpha(opacity=15);-moz-opacity:.1;opacity:.1;"></div>
  <div align="center" id="progressBar" style="z-index:3;position:absolute;">
		<table width="100%" height="475" border=1>
			<tr>
				<td align="center" valign="middle" class='load'>
					<img src='../pages/images/ajax-loader.gif' width='30' height='30'/><br>
					<span style="color:red;font-size:11px;font-weight:bold;z-index:900px;">Processing .... Please wait</span>
				</td>
			</tr>
		</table>
  </div>
  <div id="confirmationDiv" style='position:absolute;z-index:3; 100px; height:150px; visibility: hidden; top: 200px; left: 150px;'></div>
  <%-- Progress Bar - End --%> 
   
   
  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

						<tr>
							<td valign="bottom" class="txt"><strong class="formhead"><img
								src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Rating Scale Definition </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/help.gif"
								width="16" height="16" /></div>
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
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		   </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
 
   	<!-- Start Body -->

	<tr>
    	<td colspan="3">

	    	<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	          <tr>
	            <td>
	            <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
		            <tr>
		             <s:hidden name="sAppId"></s:hidden>
   					 <s:hidden name="sAppStartDate"></s:hidden>
   					 <s:hidden name="sAppEndDate"></s:hidden>
   					 <s:hidden name="isStarted"/>
		             <td width="12%" colspan="1" height="20" class="formtext">
		              	<label name="ratingscaledefinition.apprcode" class = "set"  id="ratingscaledefinition.apprcode1" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.apprcode")%></label> <font color="red">*</font> :
		             </td>
					 <td width="38%" colspan="1" height="20" align="left">
					 	<s:if test="%{null != sAppCode && sAppCode == ''}">
					 		<s:textfield name="sAppCode" size="20" readonly="true" />
					  		<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'RatingScaleDefinition_f9SelectAppraisal.action'); ">
						</s:if>
						<s:else>
							<s:property value="sAppCode" />
							 <s:hidden name="sAppCode"></s:hidden>
						</s:else>
		             </td> 
		             <td width="50%" colspan="2" height="20" nowrap="nowrap" class="formtext">
		             	<s:if test="%{sAppCode != null && sAppCode != ''}">
		              		<label name="ratingscaledefinition.from" class = "set"  id="ratingscaledefinition.to" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.from")%></label> : <s:property value="sAppStartDate"></s:property>&nbsp;&nbsp;&nbsp;<label name="ratingscaledefinition.from" class = "set"  id="ratingscaledefinition.to" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.to")%></label> : <s:property value="sAppEndDate"></s:property>
		              	</s:if>	
		             </td>
		             
		            </tr>
	            </table>
	            </td>
	          	</tr>
	      	</table>
    		
    	</td>
    </tr>
    
    <s:if test="%{sAppCode != null && sAppCode != ''}">
    
    <tr>
    	<td colspan="3">
    		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
            <tr>
            <td>
              		<table width="99%" border="0" align="center" cellpadding="2" cellspacing="2">
                	<tr>
	                  	<td colspan="4" height="25" valign="top" class="formhead">
							<strong class="forminnerhead">
							<label name="ratingscaledefinition.questionlevelheading" class = "set"  id="ratingscaledefinition.questionlevelheading" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.questionlevelheading")%></label> 
							</strong>
						</td>
                    </tr>
                	
                	<tr>
                  		<td>
	                  		<table width="100%" border="0" cellpadding="2" cellspacing="2">
	                  		<!-- Allow Fraction Rating -->
	                  		<tr>
	                  			<td height="25" width="25%">
	                  				<label name="ratingscaledefinition.allowfraction" class = "set"  id="ratingscaledefinition.allowfraction" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.allowfraction")%></label>
	                    		</td>
	                    		<td height="25" width="25%" colspan="2">
	                    			<s:if test="%{sAllowFractionFlg == 'on'}">
	                    				<input type="checkbox" name="sAllowFraction" class="checkbox" checked="checked">
	                    			</s:if>
	                    			<s:else>
	                    				<input type="checkbox" name="sAllowFraction" class="checkbox">
	                    			</s:else>
	                    			
								</td>
	                  		</tr>
	                  		<tr>
	                  			<td height="25" class="formtext" width="100%" colspan="4">
	                  			<strong class="forminnerhead">Define rating mechanism (type)</strong>
	                  			</td>
	                  		</tr>
	                  		<!-- Percent Rating -->
	                  		<s:hidden name="sRatingType" />
	                  		<tr>
	                  			<td height="25" class="formtext" width="25%">
	                  				<label name="ratingscaledefinition.percentrating" class = "set"  id="ratingscaledefinition.percentrating" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.percentrating")%></label>
	                  			</td>
	                  			<td height="25"  width="25%" colspan="2">
	                  				<input type="checkbox" name="sPercentChk" id="sPercentChk" class="checkbox" onclick="setSRatingType1()" />
								</td>
	                  		</tr>
	                  		<!-- Scale Rating -->
	                  		<tr>
	                  			<td height="25" class="formtext" width="25%">
	                    			<label name="ratingscaledefinition.scalerating" class = "set"  id="ratingscaledefinition.scalerating" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.scalerating")%></label>
								</td>
								<td height="25" width="25%">
									<input type="checkbox" name="sScaleChk" id="sScaleChk" class="checkbox" onclick="setSRatingType2()" />
								</td>
	                  		</tr>	            
	                  		<tr id="ratinglevel">
	                  			<s:hidden name ="sAppRatingId"></s:hidden>
	                    		<td height="25" class="formtext" width="25%">
	                    			<label name="ratingscaledefinition.minrating" class = "set"  id="ratingscaledefinition.minrating" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.minrating")%></label> 
	                    			<font color="#FF0000">*</font>:
								</td>
	
	                    		<td height="25" width="25%">
									<s:textfield name="sAppMinRating" maxlength="2" size="10" onkeypress="return numbersOnly()" />
								</td>
	                    		
	                    		<td height="25" width="25%">
	                    			<label name="ratingscaledefinition.maxrating" class = "set"  id="ratingscaledefinition.maxrating" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.maxrating")%></label> 
	                    			<font color="#FF0000">*</font>:<font color="#FF0000"> </font>
								</td>
	                    	
	                    		<td height="25" class="formtext" width="25%">
	                    			<s:textfield name="sAppMaxRating" maxlength="2" size="10" onkeypress="return numbersOnly()" />
								</td>
							</tr>
	                  		<tr id="gobutton">
	                    		<td height="25" width="25%">	
	                    			<input type="button" name="Go" class="token" value=" Go " border="0" onClick="return callGoAction();">
	                    		</td>
	                    		<td height="25" class="formtext" width="25%">
				  					&nbsp;
				  				</td>
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
    			
    <!-- Question Rating List -->    
    <tr id="questRatList">
    	<td colspan="3">
        	<table width="100%" id="tblQuestionRating" border="0" cellpadding="2" cellspacing="1" class="formbg" >
                   <tr>
	                  	<td colspan="2" height="25" valign="top" class="formhead">
							<strong class="forminnerhead">
							<label  name="ratingscaledefinition.questionlevellistheading" class = "set"  id="ratingscaledefinition.questionlevellistheading" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.questionlevellistheading")%></label> 
							</strong>
						</td>
						<td align="right">
							<s:if test="%{lstQuestionRatingList == null}">
								<input type="button" value="Add Row" name="add1" class="add" disabled="disabled" />
							</s:if>
							<s:else>
								<input type="button" value="Add Row" name="add1" class="add" onClick="addAction()" />
							</s:else>
						</td>
						<td>
							<s:if test="%{lstQuestionRatingList == null}">
								<input type="button" value="Delete Row" name="deleteRow" class="delete" disabled="disabled" />
							</s:if>
							<s:else>
								<input type="button" value="Delete Row" name="deleteRow" class="delete" onClick="deleteQuestionRating()" />
							</s:else>
						</td>
                   </tr>
                   <tr>
	                   <td width="10%" valign="top" align="center" class="formth" >
	                   		<label name="ratingscaledefinition.srno" class = "set"  id="ratingscaledefinition.srno" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.srno")%></label>
	                   </td>
	                   <td width="25%" valign="top" class="formth" align="left">
							 <label name="ratingscaledefinition.ratingname" class = "set"  id="ratingscaledefinition.ratingname" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.ratingname")%></label> #
					   </td>
	                   <td width="70%" valign="top" class="formth" align="left">
							<label  name="ratingscaledefinition.ratingdesc" class = "set"  id="ratingscaledefinition.ratingdesc" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.ratingdesc")%></label>
					   </td>
	                   <td width="10%" valign="top" class="formth" align="left">
							<input type="checkbox" id="chkQuestionRatingAll" name="chkQuestionRatingAll" class="checkbox" onclick="questionRatingSelectAll();" />
					   </td>
                   </tr>
                   
                   <s:if test="flgQuestionRatingList">
                        <tr>
                        	<td width="100%" colspan="4" align="center" class="sortableTD"><font color="red">There is no data to display</font></td>
                        </tr>
                   </s:if>
                   
                   <% int j = 0; %>
                   <% int count1 = 0; %>
                   <s:iterator value="lstQuestionRatingList">
	                   <% ++j; ++count1; %>
	                   <tr>
	                   		<td width="10%" align="center" class="sortableTD">
	                   			<s:property value="iSrNo" />
	                   			<s:hidden name = "iSrNo" id="iSrNo<%=j%>" ></s:hidden>
	                   			<s:hidden name="sDtlId"></s:hidden>
	                   		</td>			
	                   		
	                   		<td width="25%" align="left" class="sortableTD">
	                   			<input type="text" name = "sAppQuestionRatingName" id="sAppQuestionRatingName<%=j%>" value="<s:property value='sAppQuestionRatingName' />" maxlength="2" onkeypress="return numbersOnly()" /> 
	                   		</td>
	                   		
	                   		<td width="70%" align="left" class="sortableTD">
	                   			<input type="text" size="80"  name = "sAppQuestionRatingValue" id="sAppQuestionRatingValue<%=j%>" value="<s:property value='sAppQuestionRatingValue' />" maxlength="50" /> 
	                   		</td>
	                   		
	                   		<td width="10%" align="center" class="sortableTD"> 
								<input type="checkbox" align="right" class="checkbox" id="questionRatingChk<%=count1%>"
								name="questionRatingChk" onclick="callForDelete('<s:property value="iSrNo" />','<%=count1%>')" /> 
	                   		</td>
	                   </tr>
                   </s:iterator>
                   <input type="hidden" name="count1" id="count1" value="<%=count1%>"/>
                   
                   <tr>
                   		<td colspan="4">
                   			<font color="#FF0000">
                    		# : Rating Name will be displayed in the "Appraisal Form"
                    		</font>
                    	</td>
                   </tr>
                   
        	</table>	
        </td>
    </tr>
    
    
    <tr>
    	<td colspan="3" >
			<table width="100%" border="0" cellpadding="2" cellspacing="2"  class="formbg" height="56" >
            <tr>
            	<td height="25" class="formtext" width="16%" colspan="2">
			  		&nbsp;
			  	</td>
            </tr>
            
            <tr>
                <td height="29" class="formtext" width="100%">Multi level Rating Calculation :</td>
            </tr>
            <tr>
                <td height="29" width="100%" nowrap="nowrap">
                	<s:if test="%{iflg == null}">
                		<input type="radio" name="sAppScoreFlg" value="1"> Minimum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="2"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3"> Average Score Among all appraiser<br>
						<input type="radio" name="sAppScoreFlg" value="4"> Score of last appraiser Among all appraisers
                	</s:if>
                	<s:if test="%{iflg == 1}">
						<input type="radio" name="sAppScoreFlg" value="1" checked="checked"> Minimum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="2"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3"> Average Score Among all appraiser<br>
						<input type="radio" name="sAppScoreFlg" value="4"> Score of last appraiser Among all appraisers
					</s:if> <s:elseif test="%{iflg == 2}">
						<input type="radio" name="sAppScoreFlg" value="1"> Minimum Score Among all appraisers <br>
						<input type="radio" name="sAppScoreFlg" value="2" checked="checked"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3"> Average Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="4"> Score of last appraiser Among all appraisers
					</s:elseif> <s:elseif test="%{iflg == 3}">
						<input type="radio" name="sAppScoreFlg" value="1"> Minimum Score Among all appraisers <br>
						<input type="radio" name="sAppScoreFlg" value="2"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3" checked="checked"> Average Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="4"> Score of last appraiser Among all appraisers
					</s:elseif> <s:elseif test="%{iflg == 4}">
						<input type="radio" name="sAppScoreFlg" value="1"> Minimum Score Among all appraisers <br>
						<input type="radio" name="sAppScoreFlg" value="2"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3"> Average Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="4" checked="checked"> Score of last appraiser Among all appraisers
					</s:elseif>
				</td>
            </tr>
          </table>
        </td>
    </tr>
    
    
    <!--  Overall Score Description -->
    <tr>
    	<td colspan="3">
        	<table width="100%" border="0" cellpadding="2" cellspacing="1"  class="formbg" >
                   <tr>
	                  	<td colspan="5" height="25" valign="top" class="formhead">
							<strong class="forminnerhead">
								<label name="ratingscaledefinition.overallheading" class="set" id="ratingscaledefinition.overallheading" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.overallheading")%></label> 
							</strong>
						</td>
						<td align="right" class="formhead">
							<input type="button" value="Add Row" name="add2" class="add" onClick="addActionOverallScore();">
						</td>
						<td align="left" class="formhead">
							<input type="button" value="Delete Row" name="delete2" class="delete" onClick="deleteOverallRating();">
						</td>
                   </tr>
                   <tr>
	                   <td width="5%" valign="top" align="center" class="formth" >
	                   		<label name="ratingscaledefinition.srno"  class = "set"  id="ratingscaledefinition.srno" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.srno")%></label>
	                   </td>
	                   <td width="8%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.scorefrom" class = "set"  id="ratingscaledefinition.scorefrom" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.scorefrom")%></label>
					   </td>
	                   <td width="8%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.scoreto" class = "set"  id="ratingscaledefinition.scoreto" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.scoreto")%></label>
					   </td>
					   <td width="20%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.ratingvalue" class = "set"  id="ratingscaledefinition.ratingvalue" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.ratingvalue")%></label>
					   </td>
					   <td width="34%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.ratingdescription" class = "set"  id="ratingscaledefinition.ratingdescription" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.ratingdescription")%></label>
					   </td>
					   <td width="10%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.bellcurve" class = "set"  id="ratingscaledefinition.bellcurve" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.bellcurve")%></label>
					   </td>
	                   
	                   <td width="5%" valign="top" class="formth" align="left">
							<input type="checkbox" id="chkOverallRatingAll" name="chkOverallRatingAll" class="checkbox" onclick="overallRatingSelectAll();" />
					   </td>
                   </tr>
                   
                   <s:if test="flgOverAllScoreList">
                        <tr>
                        	<td width="100%" colspan="7" align="center" class="sortableTD"><font color="red">There is no data to display</font></td>
                        </tr>
                   </s:if>
                   
                   <% int i=0, count2=0; %> 
                   <s:iterator value="lstOverAllScoreList">
	                   <% ++i; ++count2; %>
	                   <tr>
	                   		<td width="5%" align="center" class="sortableTD">
	                   			<s:property value="iSrNoOverall" />
	                   			<s:hidden name = "iSrNoOverall" id="iSrNoOverall<%=i%>"></s:hidden>
	                   			<s:hidden name ="sAppScoreId"></s:hidden>
	                   		</td>			
	                   		
	                   		<td width="8%" align="left" class="sortableTD">
	                   			<input type="text" name = "sAppOverAllScoreFrom" id="sAppOverAllScoreFrom<%=i%>" value="<s:property value='sAppOverAllScoreFrom' />" maxlength="6" onkeypress="return numbersWithDot()" />
	                   		</td>
	                   		
	                   		<td width="8%" align="left" class="sortableTD">
	                   			<input type="text" name = "sAppOverAllScoreTo" id="sAppOverAllScoreTo<%=i%>" value="<s:property value='sAppOverAllScoreTo' />" maxlength="6" onkeypress="return numbersWithDot()" />
	                   		</td>
	                   		
	                   		<td width="20%" align="left" class="sortableTD">
	                   			<input type="text" name = "sAppOverAllScoreValue" id="sAppOverAllScoreValue<%=i%>" value="<s:property value='sAppOverAllScoreValue' />" maxlength="50" />
	                   		</td>
	                   		
	                   		<td width="34%" align="left" class="sortableTD">
	                   			<input type="text" name = "sAppOverAllScoreDesc" id="sAppOverAllScoreDesc<%=i%>" value="<s:property value='sAppOverAllScoreDesc' />" maxlength="100" />
	                   		</td>
	                   		
	                   		<td width="20%" align="left" class="sortableTD">
	                   			<input type="text" name = "sAppOverAllBellCurve" id="sAppOverAllBellCurve<%=i%>" value="<s:property value='sAppOverAllBellCurve' />" maxlength="3" onkeypress="return numbersOnly()" onblur="return calculateTotal()" />
	                   		</td>
	                   		
	                   		<td width="5%" align="center" class="sortableTD">
	                   			<input type="checkbox" align="right" class="checkbox" id="overallRatingChk<%=count2%>"
								name="overallRatingChk" onclick="callForDelete1('<s:property value="iSrNoOverall" />','<%=count2%>')" /> 
	                   		</td>
	                   </tr>
                   </s:iterator>
                   <input type="hidden" name="count2" id="count2" value="<%=count2%>"/>
                   
                   <tr>
	                  <td colspan="7"  class="border2">
	                  	&nbsp;
	                  </td>
                   </tr>
                   
                   <tr>
                   		<td colspan="4">
                    		 &nbsp;
                    	</td>
                    	<td align="right">
                    		Total Bell Curve % &nbsp;
                    	</td>
                    	<td>
                    		<%-- 
                    		&nbsp;<s:property value="sTotalBullCurve" /> 
                    		<s:hidden name="sTotalBullCurve"></s:hidden>
                    		--%>
                    		<s:textfield name="sTotalBullCurve" readonly="true" cssStyle="background-color: #F2F2F2"></s:textfield>
                    	</td>
                    	<td>
                    		&nbsp;
                    	</td>
                   </tr>
                   
        	</table>	
        </td>
    </tr>
    
    
    </s:if>
    <!-- End Body -->
	
    
	<tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
            <tr>
            <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		    </td>
            <td width="22%">
            	&nbsp;
            </td>
            </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
  </table>
	
</s:form>
<script type="text/javascript">
	chkRatingType();// call onload
	function chkRatingType()
	{
		if(document.getElementById("paraFrm_sRatingType").value == "perc")
		{
			document.getElementById("sPercentChk").checked=true;
			document.getElementById('ratinglevel').style.display='none';
			document.getElementById('gobutton').style.display='none';
			document.getElementById('questRatList').style.display='none';
		}
		else if(document.getElementById("paraFrm_sRatingType").value == "scale")
		{
			document.getElementById("sScaleChk").checked=true;
			document.getElementById('ratinglevel').style.display='';
			document.getElementById('gobutton').style.display='';
			document.getElementById('questRatList').style.display='';
		}
		else
		{
			document.getElementById("sPercentChk").checked=false;
			document.getElementById("sScaleChk").checked=false;
			document.getElementById('ratinglevel').style.display='none';
			document.getElementById('gobutton').style.display='none';
			document.getElementById('questRatList').style.display='none';
		}
	}
	function setSRatingType1()
	{
		if(document.getElementById("sPercentChk").checked)
		{
			document.getElementById("sScaleChk").checked=false;
			document.getElementById("paraFrm_sRatingType").value = "perc";
			document.getElementById("paraFrm_sAppMinRating").value = "0";
			document.getElementById("paraFrm_sAppMaxRating").value = "100";
			document.getElementById('ratinglevel').style.display='none';
			document.getElementById('gobutton').style.display='none';
			document.getElementById('questRatList').style.display='none';
		}
	}
	function setSRatingType2()
	{
		if(document.getElementById("sScaleChk").checked)
		{
			document.getElementById("sPercentChk").checked=false;
			document.getElementById("paraFrm_sRatingType").value = "scale";
			document.getElementById('ratinglevel').style.display='';
			document.getElementById('gobutton').style.display='';
			document.getElementById('questRatList').style.display='';			
		}
		else
		{
			document.getElementById("sPercentChk").checked=false;
			document.getElementById("paraFrm_sRatingType").value = "";
			document.getElementById('ratinglevel').style.display='none';
			document.getElementById('gobutton').style.display='none';
			document.getElementById('questRatList').style.display='none';			
		}
	}
</script>
<script type="text/javascript">
	
    document.getElementById("overlay").style.visibility = "hidden";
    document.getElementById("overlay").style.display = "none";
    document.getElementById("progressBar").style.visibility = "hidden";
    document.getElementById("progressBar").style.display = "none";
    
    function scaleRatValidation()
    {
		var fields=["paraFrm_sAppMinRating","paraFrm_sAppMaxRating"];
		var labels=[document.getElementById("ratingscaledefinition.minrating").innerHTML,document.getElementById("ratingscaledefinition.maxrating").innerHTML];
		var types=["enter","enter"];
		
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		var minValue = document.getElementById("paraFrm_sAppMinRating").value;
		var maxValue = document.getElementById("paraFrm_sAppMaxRating").value;
		
		if (minValue == 0)
		{
			alert("Minimum Rating Level not allow zero ");
			document.getElementById("paraFrm_sAppMinRating").focus();
			return false;		
		}
		if (maxValue == 0)
		{
			alert("Maximum Rating Level not allow zero ");
			document.getElementById("paraFrm_sAppMaxRating").focus();
			return false;
		}
		
		if (eval(minValue) > eval(maxValue))
		{
			alert("Minimum Rating should not greater than Maximum Rating.");
			document.getElementById("paraFrm_sAppMinRating").value='';
			document.getElementById("paraFrm_sAppMaxRating").value='';
			document.getElementById("paraFrm_sAppMinRating").focus();
			return false;
		}
		
	
		var tableLength1 = document.getElementById("count1").value;
		if(tableLength1 == 0 )
		{
			alert("Please define the question level rating.");
			document.getElementById("paraFrm_sAppMinRating").focus();
			
			return false;
		}
		else
		{
			var iMinRange = trim(document.getElementById("sAppQuestionRatingName1").value);
			var iMaxRange = trim(document.getElementById("sAppQuestionRatingName"+tableLength1).value);
			
			//alert(iMinRange);
			//alert(iMaxRange);
			
			if (eval(minValue) != eval(iMinRange))
			{
				alert("Please check 'Minimum Rating Level' and 'Minimum Question Rating' are not match.")
				return false;
			}
			
			if (eval(maxValue) != eval(iMaxRange))
			{
				alert("Please check 'Maximum Rating Level' and 'Maximum Question Rating' are not match.")
				return false;
			}
			
			
			for(i=1;i<=tableLength1;i++)
			{
				var sAppQuestionRatingName = trim(document.getElementById("sAppQuestionRatingName"+i).value);
				if (sAppQuestionRatingName == "")
				{
					alert ("Rating Name should not be blank")
					document.getElementById("sAppQuestionRatingName"+i).value = '';
					document.getElementById("sAppQuestionRatingName"+i).focus();
					return false;
				}

				var sAppQuestionRatingValueList = trim(document.getElementById("sAppQuestionRatingValue"+i).value);
				if (sAppQuestionRatingValueList == "")
				{
					alert ("Rating Description should not be blank")
					document.getElementById("sAppQuestionRatingValue"+i).value = '';
					document.getElementById("sAppQuestionRatingValue"+i).focus();
					return false;
				}
			}
		}    
    return true;
    }
    
	function saveFun() 
	{
		/*if(document.getElementById("paraFrm_isStarted").value=="Y")
		{
			alert("Appraisal process has been started.\You can't update the ratings.");
			return false;
		}*/
		
		var apprCode = document.getElementById("paraFrm_sAppId").value;
		if(apprCode == "")
		{
			alert("Please select the appraisal.");
			return false;
		}
		if(document.getElementById('sScaleChk').checked)
		{
			if(!scaleRatValidation())
				return false;
		}else if(!document.getElementById('sPercentChk').checked)
		{
			alert("Please select Rating type");
			return false;
		}
		var tableLength2 = document.getElementById("count2").value;
		if(tableLength2 == 0 )
		{
			alert("Please define the overall rating.");
			return false;
		}
		else
		{
			//alert(tableLength2);
			var totalBellCurve=0;
			
			for(j=1;j<=tableLength2;j++)
			{
				/* Score From */
				var sAppOverAllScoreFrom = trim(document.getElementById("sAppOverAllScoreFrom"+j).value);
				if (sAppOverAllScoreFrom == "")
				{
					alert ("Score From should not be blank");
					document.getElementById("sAppOverAllScoreFrom"+j).value = '';
					document.getElementById("sAppOverAllScoreFrom"+j).focus();
					return false;
				}
				
				if (eval(sAppOverAllScoreFrom) > 100)
				{
					alert ("Score From should not greater than 100");
					document.getElementById("sAppOverAllScoreFrom"+j).value = '';
					document.getElementById("sAppOverAllScoreFrom"+j).focus();
					return false;
				}

				/* Score To */
				var sAppOverAllScoreTo = trim(document.getElementById("sAppOverAllScoreTo"+j).value);
				if (sAppOverAllScoreTo == "")
				{
					alert ("Score To should not be blank");
					document.getElementById("sAppOverAllScoreTo"+j).value = '';
					document.getElementById("sAppOverAllScoreTo"+j).focus();
					return false;
				}
				
				if (eval(sAppOverAllScoreTo) > 100)
				{
					alert ("Score To should not greater than 100");
					document.getElementById("sAppOverAllScoreTo"+j).value = '';
					document.getElementById("sAppOverAllScoreTo"+j).focus();
					return false;
				}
				
				/* Rating Value */
				var sAppOverAllScoreValue = trim(document.getElementById("sAppOverAllScoreValue"+j).value);
				if (sAppOverAllScoreValue == "")
				{
					alert ("Rating Value should not be blank");
					document.getElementById("sAppOverAllScoreValue"+j).value = '';
					document.getElementById("sAppOverAllScoreValue"+j).focus();
					return false;
				}
				
				/* Rating Description */
				var sAppOverAllScoreDesc = trim(document.getElementById("sAppOverAllScoreDesc"+j).value);
				if (sAppOverAllScoreDesc == "")
				{
					alert ("Rating Description should not be blank");
					document.getElementById("sAppOverAllScoreDesc"+j).value = '';
					document.getElementById("sAppOverAllScoreDesc"+j).focus();
					return false;
				}
				
				/* Bell Curve */
				var sAppOverAllBellCurve = trim(document.getElementById("sAppOverAllBellCurve"+j).value);
				if (sAppOverAllBellCurve == "")
				{
					alert ("Bell Curve should not be blank");
					document.getElementById("sAppOverAllBellCurve"+j).value = '';
					document.getElementById("sAppOverAllBellCurve"+j).focus();
					return false;
				}
				else
				{
					totalBellCurve = totalBellCurve + eval(sAppOverAllBellCurve);
				}
			}
			
			/* Bell Curve Validation */
			if (eval(totalBellCurve) > 100)
			{
				alert("Bell Curve % should not greater than 100%");
				return false;
			}
			else if (eval(totalBellCurve) < 100)
			{
				alert("Bell Curve % should not less than 100%");
				return false;
			}
		}
		
		var countCheck = document.getElementById("count2").value;
		for (k=1; k<= countCheck;k++){
			var fromScore = document.getElementById("sAppOverAllScoreFrom"+k).value;
			var toScore = document.getElementById("sAppOverAllScoreTo"+k).value;
			if(eval(fromScore) >= eval(toScore)){
				alert("From score must be less than To score.");
				return false;
			}
		}
		if(!checkRange()){
			return false;
		}
		document.getElementById("paraFrm").action="RatingScaleDefinition_save.action";
		document.getElementById("paraFrm").submit();
	}
	function checkRange(){
	
		var countCheck = document.getElementById("count2").value;
		for (k=1; k<= countCheck;k++){
			var fromScore = document.getElementById("sAppOverAllScoreFrom"+k).value;
			var toScore = document.getElementById("sAppOverAllScoreTo"+k).value;
			if(eval(fromScore) >= eval(toScore)){
				alert("From score must be less than To score.");
				return false;
			}
		}
		for (i=1; i<= countCheck;i++){
		//alert("for");
			var fromScore = document.getElementById("sAppOverAllScoreFrom"+i).value;
			var toScore = document.getElementById("sAppOverAllScoreTo"+i).value;
			//alert("fromScore="+fromScore);
			//alert("toScore="+toScore);
			for(j=1; j<= countCheck ;j++){
			//alert("i="+i);
			//alert("j="+j);
			if(i !=j){
			
			//alert("document.getElementById(sAppOverAllScoreFrom+j)"+document.getElementById("sAppOverAllScoreFrom"+j).value);
			
			//alert("document.getElementById(sAppOverAllScoreTo+j)"+document.getElementById("sAppOverAllScoreTo"+j).value);
				if((fromScore >=document.getElementById("sAppOverAllScoreFrom"+j).value && fromScore <=document.getElementById("sAppOverAllScoreTo"+j).value))
				{
					alert("Please check the score From!");
					//alert("inside if from Score=="+fromScore+" and list FromScore=="+document.getElementById("sAppOverAllScoreFrom"+j).value+" and list toScore="+document.getElementById("sAppOverAllScoreTo"+j).value);
					document.getElementById("sAppOverAllScoreFrom"+i).focus();
					return false;
				}
				if(toScore >=eval(document.getElementById("sAppOverAllScoreFrom"+j).value) && toScore <=eval(document.getElementById("sAppOverAllScoreTo"+j).value))
				{
				//alert("inside if to Score=="+toScore+" and list FromScore=="+document.getElementById("sAppOverAllScoreFrom"+j).value+" and list toScore="+document.getElementById("sAppOverAllScoreTo"+j).value);
					alert("Please check the score To !");
					document.getElementById("sAppOverAllScoreTo"+i).focus();
					return false;
				}
				}
			}
		}
		return true;
		
	}
	
	function cancelFun() 
	{
		document.getElementById("paraFrm").action="AppraisalCalendar_input.action";
		document.getElementById("paraFrm").submit();	
	}
	/*
	callCharDiv(status);   Call the function on onload event
	
	function callCharDiv(obj)
	{
		if(obj.value=="D")
		{
			document.getElementById('charDiv').style.display='';
		}
		else 
		{
			document.getElementById('charDiv').style.display='none';
		}
	}
	 */
	/* Question Level Rating */
	function callGoAction()
	{
		var fields=["paraFrm_sAppMinRating","paraFrm_sAppMaxRating"];
		var labels=[document.getElementById("ratingscaledefinition.minrating").innerHTML,document.getElementById("ratingscaledefinition.maxrating").innerHTML];
		var types=["enter","enter"];
		
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		var min = document.getElementById("paraFrm_sAppMinRating").value;
		var max = document.getElementById("paraFrm_sAppMaxRating").value;
		
		if (min == 0)
		{
			alert("Minimum Rating Level is not allow zero");
			document.getElementById("paraFrm_sAppMinRating").focus();
			return false;
		}
		
		if (max == 0)
		{
			alert("Maximum Rating Level is not allow zero");
			document.getElementById("paraFrm_sAppMaxRating").focus();
			return false;
		}
		
		
		if (eval(min) > eval(max))
		{
			alert("Minimum Rating should not greater than Maximum Rating.");
			document.getElementById("paraFrm_sAppMinRating").value='';
			document.getElementById("paraFrm_sAppMaxRating").value='';
			document.getElementById("paraFrm_sAppMinRating").focus();
			return false;
		}
		
		document.getElementById('paraFrm_addFlg').value='go';
		
		/* Progress Bar - Start */
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		/* Progress Bar - End */
		
		document.getElementById("paraFrm").action="RatingScaleDefinition_goAction.action";
		document.getElementById("paraFrm").submit();
	}
	
	/* Question Level Rating */
	function addAction()
	{
		var minValue = document.getElementById("paraFrm_sAppMinRating").value;
		var maxValue = document.getElementById("paraFrm_sAppMaxRating").value;
		
		if (minValue == 0)
		{
			alert("Minimum Rating Level not allow zero ");
			document.getElementById("paraFrm_sAppMinRating").focus();
			return false;		
		}
		if (maxValue == 0)
		{
			alert("Maximum Rating Level not allow zero ");
			document.getElementById("paraFrm_sAppMaxRating").focus();
			return false;
		}
		
		if (eval(minValue) > eval(maxValue))
		{
			alert("Minimum Rating should not greater than Maximum Rating.");
			document.getElementById("paraFrm_sAppMinRating").value='';
			document.getElementById("paraFrm_sAppMaxRating").value='';
			document.getElementById("paraFrm_sAppMinRating").focus();
			return false;
		}
		
		document.getElementById('paraFrm_addFlg').value='add';
	
		document.getElementById("paraFrm").action="RatingScaleDefinition_goAction.action";
		document.getElementById("paraFrm").submit();
	}
	
	/* Overall Score Description */
	function addActionOverallScore()
	{
		document.getElementById("paraFrm").action="RatingScaleDefinition_goActionOverallScore.action";
		document.getElementById("paraFrm").submit();
			
	}
	
	/* Delete the selected row in Question Rating Scale */
	function deleteQuestionRating()
	{
		 if(chkQuestionRating())
		 {
			 var countCheck = document.getElementById("count1").value;
			 
			var counter="";
			 
		 	for(j=1;j<=countCheck;j++){
		 	
					if(document.getElementById("questionRatingChk"+j).checked){
						counter = counter+ (eval(j)-eval(1))+",";
					}			
			}
			document.getElementById("paraFrm_paraId").value= counter;
		 	var con = confirm('Do you want to delete these records?');
		 	if(con)
		 	{
		    	document.getElementById('paraFrm').action="RatingScaleDefinition_removeQuestionRatingRow.action";
	  			document.getElementById('paraFrm').submit();
	  			
		    }
		    else
		    {
		    	return true;
		    }
		 }
		 else 
		 {
		 	alert('Please select atleast one record to delete');
		  	return false;
		 }
	}
	
	function chkQuestionRating()
	{
		var tbl = document.getElementsByName("questionRatingChk");
		var rowLen = tbl.length;
		
	  	for (var a=1;a<=rowLen;a++)
	  	{	
		   	if(document.getElementById('questionRatingChk'+a).checked == true)
		   	{	
		 	    return true;
		   	}	   
	  	}
	  	return false;
	}
	
	/* Select all checkbox for Question Rating Scale */
	function questionRatingSelectAll()
  	{
		var tbl = document.getElementsByName("questionRatingChk");
		var rowLen = tbl.length;
		
		if (document.getElementById("chkQuestionRatingAll").checked == true)
		{
			for (var idx = 1; idx <= rowLen; idx++) 
			{
				document.getElementById("questionRatingChk"+idx).checked = true;
	 		 	document.getElementById('questionRatingChk'+idx).value="Y";
		    }
	 	}
	 	else
	 	{
			for (var idx = 1; idx <= rowLen; idx++) 
			{
				document.getElementById("questionRatingChk"+idx).checked = false;
				document.getElementById('questionRatingChk'+idx).value="";
	     	}
  		}	 
  	}
  	
  	function callForQuestionRating(id)
	{
   		if(document.getElementById('questionRatingChk'+id).checked == true)
	   	{	  
	    	document.getElementById('hdeletequestionRating'+id).value="Y";
	   	}
	   	else
	   	{
	   		document.getElementById('hdeletequestionRating'+id).value="";
   		}
  	}
  	
  	/* Delete the selected row in Question Rating Scale */
	function deleteOverallRating()
	{
		 if(chkOverallRating())
		 {
		 var countCheck = document.getElementById("count2").value;
			 
			var counter="";
			 
		 	for(j=1;j<=countCheck;j++){
		 	
					if(document.getElementById("overallRatingChk"+j).checked){
						counter = counter+ (eval(j)-eval(1))+",";
					}			
			}
			document.getElementById("paraFrm_paraId").value= counter;
		 	var con = confirm('Do you want to delete these records?');
		 	if(con)
		 	{
		    	document.getElementById('paraFrm').action="RatingScaleDefinition_removeOverallRatingRow.action";
	  			document.getElementById('paraFrm').submit();
		    }
		    else
		    {
		    	return true;
		    }
		 }
		 else 
		 {
		 	alert('Please Select Atleast one Record To Delete');
		  	return false;
		 }
	}
	
	function chkOverallRating()
	{
		var tbl = document.getElementsByName("overallRatingChk");
		var rowLen = tbl.length;

	  	for (var a=1;a<=rowLen;a++)
	  	{	
		   	if(document.getElementById('overallRatingChk'+a).checked == true)
		   	{	
		 	    return true;
		   	}	   
	  	}
	  	return false;
	}
  	
	
	/* Select all checkbox for Overall Rating Scale */
	function overallRatingSelectAll()
  	{
		var tbl = document.getElementsByName("overallRatingChk");
		var rowLen = tbl.length;
		
		if (document.getElementById("chkOverallRatingAll").checked == true)
		{
			for (var idx = 1; idx <= rowLen; idx++) 
			{
				document.getElementById("overallRatingChk"+idx).checked = true;
	 		 	document.getElementById('overallRatingChk'+idx).value="Y";
		    }
	 	}
	 	else
	 	{
			for (var idx = 1; idx <= rowLen; idx++) 
			{
				document.getElementById("overallRatingChk"+idx).checked = false;
				document.getElementById('overallRatingChk'+idx).value="";
	     	}
  		}	 
  	}
	
	/**
	Calculate the Bell Curve Total
	*/
	function calculateTotal()
	{
		var tbl = document.getElementsByName("sAppOverAllBellCurve");
		var rowLen = tbl.length;
		var total=0;
		
		for (var idx = 1; idx <= rowLen; idx++) 
		{
 		 	total = total + eval(setToZero(document.getElementById('sAppOverAllBellCurve'+idx).value));
	    }
	    
	    document.getElementById('paraFrm_sTotalBullCurve').value = eval(setToZero(total));
	    //document.getElementById('paraFrm_sTotalBullCurve').style.background = '#F2F2F2';
	}
	
	function setToZero(value)
	{
		if(value == "")
		{
			return "0";
		}
		else
		{
			return value;
		}
	}
	
</script>
