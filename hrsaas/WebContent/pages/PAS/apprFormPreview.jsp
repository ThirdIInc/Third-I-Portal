<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="ApprFormSection" validate="true" id="paraFrm" theme="simple">
		
<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
<tr>
	    <td colspan="3" width="100%">
       		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
       			 <tr>
			          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
	                      	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				      <td width="93%" class="txt"><strong class="text_head"><label name="appraisal.form.head" class = "set"  id="appraisal.form.head" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.head")%></label></strong></td>
				      <td width="3%" valign="top" class="txt"><div align="right">
				           	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				 </tr>
	        </table>
	     </td>
    </tr>
     <tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Phase</label></td>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Section</label></td>
								
								<td class="formth" width="60%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.desc")%></label></td>
									<td class="formth" width="5%"  align="center" ><label name="appraisal.form.ques.wt" class = "set"  id="appraisal.form.ques.wt" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.wt")%></label></td>
									
									<td class="formth" width="5%"  align="center" nowrap="nowrap"><label name="appraisal.form.rating" class = "set"  id="appraisal.form.rating" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.rating")%></label></td>
								
										<td class="formth" width="5%"  align="center" ><label name="appraisal.form.ques.avg" class = "set"  id="appraisal.form.ques.avg" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.avg")%></label></td>
									
									<td class="formth" width="50%" align="left"><label name="appraisal.form.comment"  class = "set"  id="appraisal.form.comment" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.comment")%></label></td>
								
							</tr>
							<%
								int cnt=0;
							%>
							<s:iterator value="apprRatingList">
								<tr>
									<td class="sortableTD"><%=++cnt %></td>
									<td class="sortableTD"><s:hidden name="ittrPhaseName"/> <s:property value="ittrPhaseName" /></td>
									<td class="sortableTD"><s:hidden name="ittrSectionName"/> <s:property value="ittrSectionName" /></td>
									<td class="sortableTD"><s:hidden name="ittrQuesDesc"/> <s:property value="ittrQuesDesc" /></td>
									<td class="sortableTD"><s:hidden name="ittrQuesWt"/> <s:property value="ittrQuesWt" /></td>
									<td class="sortableTD"><s:hidden name="ittrQuesRating"/> <s:property value="ittrQuesRating" /></td>
									<td class="sortableTD"><s:hidden name="ittrActual"/> <s:property value="ittrActual" /></td>
									<td class="sortableTD"><s:hidden name="ittrComment"/> <s:property value="ittrComment" /></td>
								</tr>
							</s:iterator>
							</table>
						</td>
		</tr>
		
		
		 <tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	<tr>
	            		<td colspan="6"><b>Section :Training Attended</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Training Desc</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Training From</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Training To</label></td>
									<td class="formth" width="5%"  align="center" ><label name="appraisal.form.ques.wt" class = "set"  id="appraisal.form.ques.wt" ondblclick="callShowDiv(this);">Training Duration</label></td>
									
									<td class="formth" width="5%"  align="center" nowrap="nowrap"><label name="appraisal.form.rating" class = "set"  id="appraisal.form.rating" ondblclick="callShowDiv(this);">Training Sponcer</label></td>
								
																		
							</tr>
							<%
								int i=0;
							%>
							<s:iterator value="appTrnAttndList">
								<tr>
									<td class="sortableTD"><%=++i %></td>
									<td class="sortableTD"><s:hidden name="ittrTrnAttndDesc"/> <s:property value="ittrTrnAttndDesc" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnAttndFrom"/> <s:property value="ittrTrnAttndFrom" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnAttndTo"/> <s:property value="ittrTrnAttndTo" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnAttndDuration"/> <s:property value="ittrTrnAttndDuration" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnAttndSponser"/> <s:property value="ittrTrnAttndSponser" /></td>							
								</tr>
							</s:iterator>
							<%
								if(i==0)
								{
							%>
								<tr>
									<td colspan="6" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>
		
		
		<tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	<tr>
	            		<td colspan="4"><b>Section :Training Attended Comment</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Phase</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Training Desc</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Comments</label></td>
									
																		
							</tr>
							<%
								int j=0;
							%>
							<s:iterator value="apprTrnAttndCommList">
								<tr>
									<td class="sortableTD"><%=++j %></td>
									<td class="sortableTD"><s:hidden name="ittrTrnPhase"/> <s:property value="ittrTrnPhase" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnAttendDesc"/> <s:property value="ittrTrnAttendDesc" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnAttndComment"/> <s:property value="ittrTrnAttndComment" /></td>
																	</tr>
							</s:iterator>
							<%
								if(j==0)
								{
							%>
								<tr>
									<td colspan="4" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>
		
		<tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	<tr>
	            		<td colspan="4"><b>Section :Training Recommendations</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Phase</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Question Description</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Comments</label></td>
									
																		
							</tr>
							<%
								int k=0;
							%>
							<s:iterator value="apprTrnRecomndList">
								<tr>
									<td class="sortableTD"><%=++k %></td>
									<td class="sortableTD"><s:hidden name="ittrTrnRecomndPhase"/> <s:property value="ittrTrnRecomndPhase" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnRecomndQusDesc"/> <s:property value="ittrTrnRecomndQusDesc" /></td>
									<td class="sortableTD"><s:hidden name="ittrTrnRecomndComment"/> <s:property value="ittrTrnRecomndComment" /></td>
								</tr>
							</s:iterator>
							<%
								if(k==0)
								{
							%>
								<tr>
									<td colspan="4" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>
	
	<tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	<tr>
	            		<td colspan="4"><b>Awards & Recognition</b></td>
	            	</tr>
	            	<tr>
	            		<td colspan="4"><b>Section :Awards Achieved</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Award Description</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Award Date</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Issuing Authority</label></td>
									
																		
							</tr>
							<%
								int l=0;
							%>
							<s:iterator value="apprAwdAchvList">
								<tr>
									<td class="sortableTD"><%=++l %></td>
									<td class="sortableTD"><s:hidden name="ittrAwdAchvdDesc"/> <s:property value="ittrAwdAchvdDesc" /></td>
									<td class="sortableTD"><s:hidden name="ittrAwdAchvDate"/> <s:property value="ittrAwdAchvDate" /></td>
									<td class="sortableTD"><s:hidden name="ittrAwdAchvIssueAuth"/> <s:property value="ittrAwdAchvIssueAuth" /></td>
								</tr>
							</s:iterator>
							<%
								if(l==0)
								{
							%>
								<tr>
									<td colspan="4" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>	
		
		
		<tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	
	            	<tr>
	            		<td colspan="4"><b>Section :Awards Achieved Comment</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Phase</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Award Description</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Comments</label></td>
									
																		
							</tr>
							<%
								int m=0;
							%>
							<s:iterator value="apprAwdAchvdCommList">
								<tr>
									<td class="sortableTD"><%=++m %></td>
									<td class="sortableTD"><s:hidden name="ittrAwdAchvdCommPhase"/> <s:property value="ittrAwdAchvdCommPhase" /></td>
									<td class="sortableTD"><s:hidden name="ittrAwdAchvdCommDesc"/> <s:property value="ittrAwdAchvdCommDesc" /></td>
									<td class="sortableTD"><s:hidden name="ittrAwdAchvdComment"/> <s:property value="ittrAwdAchvdComment" /></td>
								</tr>
							</s:iterator>
							<%
								if(m==0)
								{
							%>
								<tr>
									<td colspan="4" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>		
		
		<tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	
	            	<tr>
	            		<td colspan="4"><b>Section :Nominate Awards</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Phase</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Award Name</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Reason For Award</label></td>
									
																		
							</tr>
							<%
								int n=0;
							%>
							<s:iterator value="apprAwdNomnList">
								<tr>
									<td class="sortableTD"><%=++n %></td>
									<td class="sortableTD"><s:hidden name="ittrAwdNominPhase"/> <s:property value="ittrAwdNominPhase" /></td>
									<td class="sortableTD"><s:hidden name="ittrAwdNominAwardName"/> <s:property value="ittrAwdNominAwardName" /></td>
									<td class="sortableTD"><s:hidden name="ittrAwdNominAwardReason"/> <s:property value="ittrAwdNominAwardReason" /></td>
								</tr>
							</s:iterator>
							<%
								if(n==0)
								{
							%>
								<tr>
									<td colspan="4" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>	
		
		
		<tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	<tr>
	            		<td colspan="4"><b>Disciplinary Action Details</b></td>
	            	</tr>
	            	<tr>
	            		<td colspan="4"><b>Section :Disciplinary Action History</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Disciplinary Action</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Action Taken Date</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Issuing Authority</label></td>
									
																		
							</tr>
							<%
								int p=0;
							%>
							<s:iterator value="apprDispActList">
								<tr>
									<td class="sortableTD"><%=++p %></td>
									<td class="sortableTD"><s:hidden name="ittrDispAction"/> <s:property value="ittrDispAction" /></td>
									<td class="sortableTD"><s:hidden name="ittrDispActDate"/> <s:property value="ittrDispActDate" /></td>
									<td class="sortableTD"><s:hidden name="ittrDispActIsssueAuth"/> <s:property value="ittrDispActIsssueAuth" /></td>
								</tr>
							</s:iterator>
							<%
								if(p==0)
								{
							%>
								<tr>
									<td colspan="4" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>	
		
		<tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            	
	            	<tr>
	            		<td colspan="4"><b>Section :Disciplinary Action Comment</b></td>
	            	</tr>
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="30%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Phase</label></td>
								<td class="formth" width="20%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);">Disciplinary Action</label></td>
								
								<td class="formth" width="20%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);">Comments</label></td>
									
																		
							</tr>
							<%
								int q=0;
							%>
							<s:iterator value="apprDispActCommentList">
								<tr>
									<td class="sortableTD"><%=++q %></td>
									<td class="sortableTD"><s:hidden name="ittrDispActPhase"/> <s:property value="ittrDispActPhase" /></td>
									<td class="sortableTD"><s:hidden name="ittrDispActCommDesc"/> <s:property value="ittrDispActCommDesc" /></td>
									<td class="sortableTD"><s:hidden name="ittrDispActComment"/> <s:property value="ittrDispActComment" /></td>
								</tr>
							</s:iterator>
							<%
								if(q==0)
								{
							%>
								<tr>
									<td colspan="4" align="center"><font color="red">No data to display</font></td>
								</tr>
							<%
								}
							%>
							</table>
						</td>
		</tr>	
    
</table>
</s:form>