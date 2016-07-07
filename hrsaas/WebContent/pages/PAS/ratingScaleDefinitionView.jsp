<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="RatingScaleDefinition" validate="true" id="paraFrm" theme="simple">

  <s:hidden name="hiddencode"></s:hidden>	
  <s:hidden name="readFlag"></s:hidden>
  <s:hidden name="sMode"></s:hidden>	
  
  <s:hidden name="sAppId"></s:hidden>
  <s:hidden name="sAppCode"></s:hidden>
  <s:hidden name="sAppStartDate"></s:hidden>
  <s:hidden name="sAppEndDate"></s:hidden>
  <s:hidden name="isStarted"/>
  <s:hidden name="show" value="%{show}" />
		<s:hidden name="myPage" id="myPage" />
		
  
  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    <tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<tr>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/review_shared.gif" width="25" height="25" /></strong>
						</td>
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
    
    <s:if test="readFlag">
    <tr>
	  <td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2">
			<tr>
				<td width="78%">
	            	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>
				<td align="right"><b>Page:</b> 
				
					<%
					int total1 = (Integer) request.getAttribute("abc");
					int PageNo1 = (Integer) request.getAttribute("xyz");
					%> 
					<%
					if (!(PageNo1 == 1)) 
					{
					%>
					<a href="#" onclick="callPage('1');"> 
					<img src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
						src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /></a> 
					<%
	 				}
	 				if (total1 < 5) 
	 				{
		 				for (int i = 1; i <= total1; i++) 
		 				{
			 				%> 
			 				<a href="#" onclick="callPage('<%=i %>');"> 
			 				<%
			 				if (PageNo1 == i) {
			 				%> <b><u><%=i%></u></b> <%
			 				} else
			 				%><%=i%> </a> <%
		 				}
	 				}
	
	 				if (total1 >= 5) 
	 				{
	 					for (int i = 1; i <= 5; i++) 
	 					{
		 					%> <a href="#" onclick="callPage('<%=i %>');"> <%
		 					if (PageNo1 == i) {
		 					%> <b><u><%=i%></u></b> <%
		 					} else
		 					%><%=i%> </a> <%
	 					}
	 				}
	 				
	 				if (!(PageNo1 ==1)&& !(total1==PageNo1)) 
	 				{
	 					%>...<a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
						href="#" onclick="callPage('<%=total1%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /></a> <%
	 				}
	 				%> 
	 				<select name="selectname" onchange="on()" id="selectname">
					<%
					for (int i = 1; i <= total1; i++) {
					%>
						<option value="<%=i%>" <%=PageNo1==i?"selected":"" %>><%=i%></option>
					<% } %>
					</select>
				
				</td>
			</tr>
		</table>
	  </td>
	</tr>
	    
    <tr>
      <td colspan="3">
	      <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
	          <tr>	          	 
				<td class="formth" width="5%" align="center"> <label name="ratingscaledefinition.srno" class = "set" id="ratingscaledefinition.srno" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.srno")%></label></td>
				<td class="formth" width="45%" align="left"> <label name="ratingscaledefinition.apprcode" class = "set"  id="ratingscaledefinition.apprcode" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.apprcode")%></label></td>
				<td class="formth" width="12%" align="center" nowrap="nowrap"> <label name="ratingscaledefinition.startdate" class = "set"  id="ratingscaledefinition.startdate" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.startdate")%></label></td>
				<td class="formth" width="12%" align="center" nowrap="nowrap"> <label name="ratingscaledefinition.enddate" class = "set" id="ratingscaledefinition.enddate" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.enddate")%></label></td>
				<td class="formth" width="20%" align="center" >
					<input type="button" name="delete" value="Delete" class="delete" onClick="deleteMultipleRecordFun()">
				<input type="checkbox" name="allChk" id="allChk" onclick="callAllCheck()" />
				</td>
			  </tr>
	
			 <%int count = 0; %>
			 <%! int d = 0; %>
			 <%
			 	int cnt = PageNo1*20-20;	
				int i = 0;
			%>
			
			<s:if test="flglstAppraisal">
                 <tr>
                 	<td width="100%" colspan="5" align="center" class="sortableTD"><font color="red">There is no data to display</font></td>
                 </tr>
            </s:if>
			  
			<s:iterator value="lstAppraisal">
					<tr
						<%if(count%2==0){%> class="tableCell1" <%}else{%>
					 	 class="tableCell2" <%	}count++; %>
						 onmouseover="javascript:newRowColor(this);"
						 title="Double click for edit"
						 onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
						 ondblclick="javascript:callForEdit('<s:property value="sAppId" />');" >
						 
							<td width="10%" align="center" class="sortableTD"><%=++cnt%><%++i;%></td>
							<td width="45%" align="left" class="sortableTD"><s:property value="sAppCode" /></td>
							<td width="12%" align="center" class="sortableTD"><s:property value="sAppStartDate" /></td>
							<td width="12%" align="center" class="sortableTD"><s:property value="sAppEndDate" /></td>
							<input type="hidden" name="sAppIdList" value="<s:property value='sAppId'/>" id="sAppId<%=i%>" />
							<td width="20%" align="center" class="sortableTD">
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
								<input type="checkbox" align="right" class="checkbox" id="confChk<%=i%>"
								name="confChk" onclick="callForDelete1('<%=i%>')" />
							</td>
					</tr>
			 </s:iterator>
			 <% d = i; %>
			 <input type="hidden" name="count" id="count" value="<%=i%>"/>
	      </table>
      </td>
    </tr>
	    
	<tr>
    	<td colspan="3">
	    	<table width="100%" border="0" cellpadding="2" cellspacing="2">
	       		<tr>
	           		<td width="78%">
	           			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
	         	</tr>
	        </table>
        </td>
   	</tr>
   	
	</s:if> 
	<s:else>
  
	<tr>
    	<td colspan="3">
      		<table width="100%" border="0" cellpadding="2" cellspacing="2">
          		<tr>
            		<td width="78%">
            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
          		</tr>
        	</table>
        </td>
   	</tr>
    	
	<tr>
	   <td colspan="3">
	     	<table width="98%" border="0" cellpadding="0" cellspacing="0" class="formbg">
	        <tr>
	        <td>
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
		             <tr>
		            	<td width="12%" colspan="1" height="20" class="formtext" align="left"><label name="ratingscaledefinition.apprcode" class = "set"  id="ratingscaledefinition.apprcode" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.apprcode")%></label> : </td>
					  	<td width="38%" colspan="1" height="20" align="left"><s:property value="sAppCode"/> </td>
		              	<td width="50%" colspan="2" height="20" class="formtext">
		              		<label name="ratingscaledefinition.from" class = "set"  id="ratingscaledefinition.from" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.from")%></label> : <s:property value="sAppStartDate"></s:property> &nbsp; <label name="ratingscaledefinition.to" class = "set"  id="ratingscaledefinition.to" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.to")%></label> : <s:property value="sAppEndDate"></s:property>
		             	</td>
					  	<td width="25%" colspan="1" height="20"></td> 
		            </tr>
		        </table>
		    </td>
		    </tr>
		    </table>
	   </td>
	</tr>
	
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
	                    		<td height="25" class="formtext" width="25%">
	                    			<label name="ratingscaledefinition.allowfraction"  class = "set"  id="ratingscaledefinition.allowfraction" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.allowfraction")%></label>
								</td>
	                    		<td height="25" width="25%">
	                    			<s:if test="%{sAllowFractionFlg == 'on'}">
	                    				<input type="checkbox" name="sAllowFraction" class="checkbox" checked="checked" disabled="disabled">
	                    			</s:if>
	                    			<s:else>
	                    				<input type="checkbox" name="sAllowFraction" class="checkbox" disabled="disabled">
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
								<td height="25" width="25%">
									<input type="checkbox" name="sPercentChk" id="sPercentChk" class="checkbox" disabled="disabled" />
								</td>
	                  		</tr>
	                  		<!-- Scale Rating -->
	                  		<tr>
	                  			<td height="25" class="formtext" width="25%">
	                    			<label name="ratingscaledefinition.scalerating" class = "set"  id="ratingscaledefinition.scalerating" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.scalerating")%></label>
								</td>
								<td height="25" width="25%">
	                  				<input type="checkbox" name="sScaleChk" id="sScaleChk" class="checkbox" disabled="disabled" />
								</td>
	                  		</tr>	            
	                  		<tr id="ratinglevel">
	                    		<td height="25" class="formtext" width="25%%">
	                    			<label name="ratingscaledefinition.minrating"  class = "set"  id="ratingscaledefinition.minrating" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.minrating")%></label> 
	                    			<font color="#FF0000">*</font>:
								</td>
	
	                    		<td height="25" width="25%">
								 	<!--  <s:textfield name="sAppMinRating" maxlength="2" size="10" onkeypress="return numbersOnly()" /> -->
								 	<s:property value="sAppMinRating" />
								</td>
	                    		
	                    		<td height="25" width="25%">
	                    			<label name="ratingscaledefinition.maxrating"  class = "set"  id="ratingscaledefinition.maxrating" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.maxrating")%></label> 
	                    			<font color="#FF0000">*</font>:<font color="#FF0000"> </font>
								</td>
	                    	
	                    		<td height="25" class="formtext" width="25%">
	                    			<!-- <s:textfield name="sAppMaxRating" maxlength="2" size="10" onkeypress="return numbersOnly()" /> -->
	                    			<s:property value="sAppMaxRating" />
								</td>
							</tr>
	                  		
	                  		<tr id="gobutton">

	                    		<td height="25" width="25%">	
	                    			<input type="button" name=" Go " class="token" value=" Go " border="0" disabled="disabled">
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
    			
    
    <!-- Question Rating  List -->    
    <tr  id="questRatList">
    	<td colspan="3">
        	<table width="100%" border="0" cellpadding="2" cellspacing="2"  class="formbg" >
                   <tr>
	                  	<td colspan="4" height="25" valign="top" class="formhead">
							<strong class="forminnerhead">
							<label name="ratingscaledefinition.questionlevellistheading"  class = "set"  id="ratingscaledefinition.questionlevellistheading" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.questionlevellistheading")%></label> 
							</strong>
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
							<label name="ratingscaledefinition.ratingdesc" class = "set"  id="ratingscaledefinition.ratingdesc" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.ratingdesc")%></label>
					   </td>
	                   <td width="10%" valign="top" class="formth" align="left">
							<s:if test="%{lstQuestionRatingList == null}">
								<input type="button" value="     Add" name="add" class="add" onClick="addAction()" disabled="disabled">
							</s:if>
							<s:else>
								<!--  <input type="button" value="     Add" name="add" class="add" onClick="addAction()"> -->
							</s:else>
					   </td>
                   </tr>
                   
                   <% int j=0,count1=0; %> 
                   <s:iterator value="lstQuestionRatingList">
	                   <% ++j; ++count1; %>
	                   <tr>
	                   		<td width="10%" align="center" class="sortableTD">
	                   			<s:property value="iSrNo" />
	                   		</td>			
	                   		
	                   		<td width="25%" align="center" class="sortableTD">
	                   			<s:property value ="sAppQuestionRatingName" />
	                   		</td>
	                   		
	                   		<td width="70%" align="left" class="sortableTD">
	                   			<s:property value ="sAppQuestionRatingValue" />
	                   		</td>
	                   		
	                   		<td width="10%" class="sortableTD"> &nbsp;</td>
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
                <td height="29" class="formtext" width="100%">Multi level Rating Calculation :</td>
            </tr>
            <tr>
                <td height="29" width="100%" nowrap="nowrap">
                	<s:if test="%{iflg == 1}">
						<input type="radio" name="sAppScoreFlg" value="1" checked="checked" disabled="disabled"> Minimum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="2" disabled="disabled"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3" disabled="disabled"> Average Score Among all appraiser<br>
						<input type="radio" name="sAppScoreFlg" value="4" disabled="disabled"> Score of last appraiser Among all appraisers
					</s:if> <s:elseif test="%{iflg == 2}">
						<input type="radio" name="sAppScoreFlg" value="1" disabled="disabled"> Minimum Score Among all appraisers <br>
						<input type="radio" name="sAppScoreFlg" value="2" checked="checked" disabled="disabled"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3" disabled="disabled"> Average Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="4" disabled="disabled"> Score of last appraiser Among all appraisers
					</s:elseif> <s:elseif test="%{iflg == 3}">
						<input type="radio" name="sAppScoreFlg" value="1" disabled="disabled"> Minimum Score Among all appraisers <br>
						<input type="radio" name="sAppScoreFlg" value="2" disabled="disabled"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3" checked="checked" disabled="disabled"> Average Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="4" disabled="disabled"> Score of last appraiser Among all appraisers
					</s:elseif> <s:elseif test="%{iflg == 4}">
						<input type="radio" name="sAppScoreFlg" value="1" disabled="disabled"> Minimum Score Among all appraisers <br>
						<input type="radio" name="sAppScoreFlg" value="2" disabled="disabled"> Maximum Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="3" disabled="disabled"> Average Score Among all appraisers<br>
						<input type="radio" name="sAppScoreFlg" value="4" checked="checked" disabled="disabled"> Score of last appraiser Among all appraisers
					</s:elseif>
				</td>
            </tr>
          </table>
        </td>
    </tr>
    
    
    <!--  Overall Score Description -->
    <tr>
    	<td colspan="3">
        	<table width="100%" border="0" cellpadding="2" cellspacing="2"  class="formbg" >
                   <tr>
	                  	<td colspan="7" height="25" valign="top" class="formhead">
							<strong class="forminnerhead">
								<label name="ratingscaledefinition.overallheading"  class="set" id="ratingscaledefinition.overallheading" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.overallheading")%></label> 
							</strong>
						</td>
                   </tr>
                   <tr>
	                   <td width="5%" valign="top" align="center" class="formth" >
	                   		<label name="ratingscaledefinition.srno" class = "set"  id="ratingscaledefinition.srno" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.srno")%></label>
	                   </td>
	                   <td width="10%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.scorefrom" class = "set"  id="ratingscaledefinition.scorefrom" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.scorefrom")%></label>
					   </td>
	                   <td width="10%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.scoreto" class = "set"  id="ratingscaledefinition.scoreto" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.scoreto")%></label>
					   </td>
					   <td width="20%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.ratingvalue" class = "set"  id="ratingscaledefinition.ratingvalue" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.ratingvalue")%></label>
					   </td>
					   <td width="30%" valign="top" class="formth" align="left">
							<label name="ratingscaledefinition.ratingdescription" class = "set"  id="ratingscaledefinition.ratingdescription" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.ratingdescription")%></label>
					   </td>
					   <td width="20%" align="left" valign="top" class="formth" >
							<label name="ratingscaledefinition.bellcurve" class = "set"  id="ratingscaledefinition.bellcurve" ondblclick="callShowDiv(this);"><%=label.get("ratingscaledefinition.bellcurve")%></label>
					   </td>
	                   <td width="5%" valign="top" class="formth" align="left">
					   		<!--  <input type="button" value="     Add" name="add" class="add" onClick="addActionOverallScore()"> -->
					   </td>
                   </tr>
                   
                   <% int k=0, count2=0; %> 
                   <s:iterator value="lstOverAllScoreList">
	                   <% ++k; ++count2; %>
	                   <tr>
	                   		<td width="5%" align="center" class="sortableTD">
	                   			<s:property value="iSrNoOverall" />
	                   		</td>			
	                   		
	                   		<td width="10%" align="center" class="sortableTD">
	                   			<s:property value="sAppOverAllScoreFrom" />
	                   		</td>
	                   		
	                   		<td width="10%" align="center" class="sortableTD">
	                   			<s:property value="sAppOverAllScoreTo" />
	                   		</td>
	                   		
	                   		<td width="20%" align="left" class="sortableTD">
	                   			<s:property value="sAppOverAllScoreValue" />
	                   		</td>
	                   		
	                   		<td width="30%" align="left" class="sortableTD">
	                   			<s:property value="sAppOverAllScoreDesc" />
	                   		</td>
	                   		
	                   		<td width="20%" align="center" class="sortableTD">
	                   			<s:property value="sAppOverAllBellCurve" />
	                   		</td>
	                   		
	                   		<td width="5%" class="sortableTD">&nbsp;</td>
	                   </tr>
                   </s:iterator>
                   <input type="hidden" name="count2" id="count2" value="<%=count2%>"/>
                   
                   <tr>
	                  <td colspan="7"  class="sortableTD">
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
                    	<td align="center">
                    		<s:property value="sTotalBullCurve" />
                    	</td>
                    	<td>
                    		&nbsp;
                    	</td>
                   </tr>
                   
        	</table>	
        </td>
    </tr>
	   
 		
    <tr>
    	<td colspan="3">
     		<table width="100%" border="0" cellpadding="2" cellspacing="2">
        		<tr>
	           		<td width="78%">
	           			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
         		</tr>
       		</table>
        </td>
    </tr>	
	   
	</s:else> 
</table>
	
</s:form>
<script tyep="text/javascript"><!--
displayRatingType()
function displayRatingType() {
	if(document.getElementById('paraFrm_sRatingType').value == "scale") {
		document.getElementById('sScaleChk').checked = true;
		document.getElementById('sPercentChk').checked = false;
		document.getElementById('ratinglevel').style.display = '';
		document.getElementById('gobutton').style.display = '';
		document.getElementById('questRatList').style.display = '';
	}
	else {
		document.getElementById('sScaleChk').checked = false;
		document.getElementById('sPercentChk').checked = true;
		document.getElementById('ratinglevel').style.display = 'none';
		document.getElementById('gobutton').style.display = 'none';
		document.getElementById('questRatList').style.display = 'none';
	}
}
	function addnewFun() 
	{
		document.getElementById("paraFrm").action="RatingScaleDefinition_addNew.action";
		document.getElementById("paraFrm").submit();	
	}
	
	function searchFun() 
	{
		callsF9(500,325,'RatingScaleDefinition_search.action');
	}
	
	function saveFun() {
		document.getElementById("paraFrm").action="RatingScaleDefinition_addNew.action";
		document.getElementById("paraFrm").submit();
			
	}
	
	function resetFun() {
		document.getElementById("paraFrm").action="RatingScaleDefinition_reset.action";
		document.getElementById("paraFrm").submit();
			
	}
	
	function cancelFun() {
		document.getElementById("paraFrm").action="AppraisalCalendar_input.action";
		document.getElementById("paraFrm").submit();			
	}
	
	function editFun() {
	/*if(document.getElementById("paraFrm_isStarted").value=="Y"){
			alert("Appraisal process has been started.\You can't edit the ratings.");
			return false;
		}*/
		document.getElementById("paraFrm").action="RatingScaleDefinition_editSearch.action";
		document.getElementById("paraFrm").submit();			
	}
	
	function callForDelete1(i)
	{
	   var flag='<%=d %>';
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	   		document.getElementById('hdeleteCode'+i).value=document.getElementById('sAppId'+i).value;
	   }
	   else
	   {
	   		document.getElementById('hdeleteCode'+i).value="";
	   	}
   	}
	
	function deleteMultipleRecordFun()
	{
		 if(chk())
		 {
		 	var con=confirm('Do you want to  delete the record ?');
			if(con)
			{
				document.getElementById("paraFrm").action="RatingScaleDefinition_delete.action";
				document.getElementById("paraFrm").submit();
			}
			else{
			var flag = document.getElementById('count').value;
			    document.getElementById('allChk').checked=false;
			    for(var a=1;a<=flag;a++){	
			    document.getElementById('confChk'+a).checked=false;
			    document.getElementById('hdeleteCode'+a).value="";
			    
			 	}
			     return false;
			    }
		 }
		 else 
		 {
		  		alert('Please select atleast one record to delete');
		 	 	return false;
		 }
	}
   	
   function deleteFun()
	{
		var conf=confirm("Do you really want to delete this record ?");
		if(conf) 
		{
			document.getElementById("paraFrm").action="RatingScaleDefinition_deleteSelectedRecord.action";
			document.getElementById("paraFrm").submit();
		}
	}
	
	function chk() 
	{
		var flag = '<%=d %>';
	  	for (var a=1;a<=flag;a++)
	  	{	
		   	if(document.getElementById('confChk'+a).checked == true)
		   	{	
		 	    return true;
		   	}	   
	  	}
	  	return false;
	}
	 function callAllCheck(){
   		var flag=document.getElementById("count").value;
   		var checkedFlag = document.getElementById("allChk").checked;
   		
   			for(var x=1;x<=flag;x++){
   			
   			document.getElementById('confChk'+x).checked = checkedFlag;
   			if(checkedFlag){
   			var sAppId = document.getElementById('sAppId'+x).value;
			document.getElementById('hdeleteCode'+x).value=sAppId;
			}else{
				document.getElementById('hdeleteCode'+x).value="";
			}
	 
		}
		}
	function callPage(id)
	{
	   	//alert(id);
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('selectname').value = id;
	    document.getElementById('paraFrm').action="RatingScaleDefinition_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
	
    function next()
    {
   		var pageno=	document.getElementById('myPage').value;
   		if(pageno=="1")
   		{	
   			document.getElementById('myPage').value=2;
	 		document.getElementById('paraFrm_show').value=2;
	 	}
	 	else
	 	{
	 		document.getElementById('myPage').value=eval(pageno)+1;
	 		document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 	}
	   	
	   	document.getElementById('paraFrm').action="RatingScaleDefinition_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
  	
    function previous()
    {
   		var pageno = document.getElementById('myPage').value;
   	
		document.getElementById('myPage').value=eval(pageno)-1;
	 	document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   	document.getElementById('paraFrm').action="RatingScaleDefinition_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
   
    function on()
    {
    	try{
    		var val= document.getElementById('selectname').value;
			document.getElementById('paraFrm_show').value=val;
		 	document.getElementById('myPage').value=eval(val);
		 	document.getElementById('selectname').value=val;
		   	document.getElementById('paraFrm').action="RatingScaleDefinition_callPage.action";
		   
		   	document.getElementById('paraFrm').submit();
    	}
    	catch(e)
    	{
    		alert("Error Message : "+e)
    	}
    	
    }
   
   	pgshow();
  	
  	function pgshow()
  	{
  		var pgno=document.getElementById('paraFrm_show').value;
  		if(!(pgno==""))
  	 	document.getElementById('selectname').value = pgno;
  	}
  	
  	function newRowColor(cell)
   	{
		cell.className='onOverCell';
	}
	
	function oldRowColor(cell,val) 
	{
		if(val=='1')
		{
	 		cell.className='tableCell2';
		}
		else
		{ 
			cell.className='tableCell1';
		}
		
	}
	
	/*function editFun() 
	{
		document.getElementById("paraFrm").action="RatingScaleDefinition_editSearch.action";
		document.getElementById("paraFrm").submit();			
	}*/
	
	function callForEdit(id)
	{
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  
	   	document.getElementById("paraFrm").action="RatingScaleDefinition_edit.action"; 
	    document.getElementById("paraFrm").submit();
    }
	
--></script>
