<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CandidateProfile" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="candName" />
	<s:hidden name="experience" />
	<s:hidden name="postingDate" />
	<s:hidden name="shortStatus" />
	<s:hidden name="cityCode1" />
	<s:hidden name="stateCode1" />
	<s:hidden name="countryCode1" />
	<s:hidden name="candCode" />
	<s:hidden name="refFlag"/>
	<s:hidden name="expFlag"/>
	<s:hidden name="skillFlag"/>
	<s:hidden name="qualiFlag"/>
	<s:hidden name="domFlag"/>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Profile </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16"
						height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" value="Edit" class="edit" onclick="return callEdit();"/>
					<input type="button" value="  Previous" class="previous" onclick="return previousFun();"/>
					</td>
					<td width="22%">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
	<tr>
	   <td colspan="3">	
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
						<tr>
					   <td width="100%" colspan="3"><strong class="formhead">Qualification Details :</strong></td>
					  </tr>
					 <tr>
					<td>
					<table id="tblQuali" width="100%" class="formbg">
						<tr>
							<td valign="top" class="formth" nowrap="nowrap">Sr.No</td>
							<td valign="top" class="formth" align="left">Qualification Abbr</td>
							<td valign="top" class="formth" align="left">Qualification Level</td>
							<td valign="top" class="formth" align="left">Specialization Abbr</td>
							<td valign="top" class="formth" align="left">Establishment Name</td>
							<td valign="top" class="formth" align="left">Year Of Passing</td>



						</tr>
					<%
						int i = 0;
						%>
						<%
						Object[][] qualObj = (Object[][]) request.getAttribute("qualObj");
						%>
						<s:iterator value="qualList">

							<tr>
								<td width="3%" align="center" class="sortableTD"><s:property
									value="srNo" /></td>

								<td align="left" width="14%" class="sortableTD">&nbsp; <%=String.valueOf(qualObj[i][0])%>

								</td>
								<td width="12%" align="left" class="sortableTD">&nbsp; <%=String.valueOf(qualObj[i][1]).equals("null") ? ""
					: String.valueOf(qualObj[i][1])%></td>

								<td width="12%" align="left" class="sortableTD">&nbsp; <%=String.valueOf(qualObj[i][2]).equals("null") ? ""
					: String.valueOf(qualObj[i][2])%></td>



								<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
									value="estbName" /></td>

								<td width="12%" align="left" class="sortableTD">&nbsp;<s:property
									value="yearofPass" /></td>



								</td>
							</tr>
							<%
							i++;
							%>
						</s:iterator>
						<%
						i = 0;
						%>
						
						
						 <tr><td align="center" width="100%" colspan="6">
                     			 <s:if test="qualiFlag"></s:if>
                     			 <s:else>
                     			 
                     			 <font color="red">There is no data to display.</font>
                     			 
                     			 </s:else>
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
				<table width="100%" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="3"><strong class="text_head">Experience Details :</strong></td>
				</tr>
				<tr><td>
					<table id="tblExp" width="100%" class="formbg">
						<tr>
							<td valign="top" class="formth" nowrap="nowrap" width="3%">Sr.No</td>
							<td valign="top" class="formth" align="left" nowrap="nowrap" width="14%">Employer Name</td>
							<td valign="top" class="formth" align="left" nowrap="nowrap" width="12%">Last Job Profession</td>
							<td valign="top" class="formth" align="left" width="12%" nowrap="nowrap">Joining Date</td>
							<td valign="top" class="formth" align="left" width="12%" nowrap="nowrap">Relieving Date</td>
							<td valign="top" class="formth" align="left" width="15%">Job Description</td>
							<td valign="top" class="formth" align="left" width="10%">Special Achievement</td>
							<td valign="top" class="formth" align="left" width="10%">Industry Abbr</td>
							<td valign="top" class="formth" align="left" width="12%">CTC in lacs</td>

						</tr>

					
						<%
						Object[][] expObj = (Object[][]) request.getAttribute("expObj");
						%>
						<s:iterator value="expList">

							<tr>
								<td width="3%" align="center" class="sortableTD"><s:property
									value="srNo" /></td>

								<td width="14%" class="sortableTD">&nbsp; <s:property
									value="emprName" /></td>
								<td width="12%" align="left" class="sortableTD">&nbsp; <s:property
									value="lastJobPro" /></td>

								<td width="12%" align="left" class="sortableTD" nowrap="nowrap">&nbsp; <s:property
									value="joinDate" /></td>

								<td width="12%" align="left" nowrap="nowrap" class="sortableTD">&nbsp;
								<s:property value="relvDate" /></td>

								<td width="15%" align="left"   class="sortableTD">&nbsp;
								<%=String.valueOf(expObj[i][0]).equals("null") ? ""
					: String.valueOf(expObj[i][0])%>
								</td>


								<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
									value="specAch" /></td>
								<td width="10%" align="left" nowrap="nowrap" class="sortableTD">&nbsp;
								<%=String.valueOf(expObj[i][1]).equals("null") ? ""
					: String.valueOf(expObj[i][1])%>
								</td>


								<td width="12%" align="left" class="sortableTD">&nbsp;<s:property
									value="ctcExp" /></td>

							</tr>
							<%
							i++;
							%>
						</s:iterator>
						<%
						i = 0;
						%>
						
							<tr><td align="center" width="100%" colspan="9">
                     			 <s:if test="expFlag"></s:if>
                     			 <s:else>
                     			 
                     			 <font color="red">There is no data to display.</font>
                     			 
                     			 </s:else>
                     			</td>
                     			 
                     			 </tr>
								
						
					</table>
				</td>
		   	</tr>
		 </table>
	</td>
</tr>
<tr><td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<tr><td width="100%" colspan="3"><strong class="text_head">Skill Details :</strong></td></tr>
			<tr>
				<td>
				<table id="tblSkill" width="100%" class="formbg">
				<tr>
									<td valign="top" class="formth" nowrap="nowrap" width="3%">Sr.No</td>
									<td valign="top" class="formth" align="left" width="40%">Skill Name</td>
									<td valign="top" class="formth" align="left" width="57%">Experience in Years</td>

									
								</tr>

							
								<%
								Object[][] skillObj = (Object[][]) request.getAttribute("skillObj");
								%>
								<s:iterator value="skillList">

									<tr>
										<td width="3%" align="center" class="sortableTD"><s:property
											value="srNo" /></td>

										<td nowrap="nowrap" width="40%" class="sortableTD">&nbsp;
										<%=String.valueOf(skillObj[i][0]).equals("null") ? ""
					: String.valueOf(skillObj[i][0])%>
										</td>
										<td width="57%" align="left" class="sortableTD">&nbsp;<s:property
											value="skillExp" /></td>

									</tr>
									<%
									i++;
									%>
								</s:iterator>
								<%
								i = 0;
								%>
								<tr><td align="center" width="100%" colspan="3">
                     			 <s:if test="skillFlag"></s:if>
                     			 <s:else>
                     			 
                     			 <font color="red">There is no data to display.</font>
                     			 
                     			 </s:else>
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
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
								<tr>
									<td width="100%" colspan="3"><strong class="text_head">Functional/Domain
									Details :</strong></td>

								</tr>
								<tr>
									<td>
									<table id="tblFunc" width="100%" class="formbg">
										<tr>
											<td valign="top" class="formth" width="3%" nowrap="nowrap">Sr.No</td>
											<td valign="top" class="formth" align="left" nowrap="nowrap"
												width="28%">Functional/Domain Name </td>
											<td valign="top" class="formth" align="left" nowrap="nowrap"
												width="40%">Experience in Years</td>



										</tr>

										
										<%
										Object[][] funcObj = (Object[][]) request.getAttribute("funcObj");
										%>
										<s:iterator value="funcList">

											<tr>
												<td align="center" width="3%" nowrap="nowrap"
													class="sortableTD"><s:property value="srNo" /></td>

												<td width="12%" align="left" class="sortableTD">&nbsp;
												<%=String.valueOf(funcObj[i][0]).equals("null") ? ""
					: String.valueOf(funcObj[i][0])%>

												</td>
												<td width="10%" class="sortableTD">&nbsp;<s:property
													value="funcExp" /></td>

											</tr>
											<%
											i++;
											%>
										</s:iterator>
										<%
										i = 0;
										%>
										<tr><td align="center" width="100%" colspan="3">
                     			       <s:if test="domFlag"></s:if>
                     			       <s:else>
                     			 
                     			 <font color="red">There is no data to display.</font>
                     			 
                     			 </s:else>
                     			</td>
                     			 
                     			 </tr>
								</table>
	</td>
</tr>
</table>
</td></tr>
<tr>
	<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="78%"><input type="button" value="Edit" class="edit" onclick="return callEdit();"/>
									<input type="button" value="  Previous" class="previous" onclick="return previousFun();"/></td>
									<td width="22%" align="right"><b>Page 2 of 2</b></td>
								</tr>
							</table>
							</td>
						</tr>			
			
			
			</table>		
</s:form>


<script>
   function callEdit(){
   
  		 document.getElementById("paraFrm").action="CandidateProfile_editSec.action";
	    document.getElementById("paraFrm").submit();
   
   }
   
    function previousFun(){
   
  		 document.getElementById("paraFrm").action="CandidateProfile_previousView.action";
	    document.getElementById("paraFrm").submit();
   
   }
   
   function deleteFun(){
	var conf=confirm("Do you really want to delete this record?");
	  if(conf){
		document.getElementById("paraFrm").action="CandDataBank_deleteRec.action";
	    document.getElementById("paraFrm").submit();
	
	  }
	
	}
	
	function cancelFun(){
	
	
		document.getElementById("paraFrm").action="CandDataBank_input.action";
	    document.getElementById("paraFrm").submit();
	}
	
	
	
	
	
  </script>
