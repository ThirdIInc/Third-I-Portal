<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CandDataBank" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="fromCandidateScreening" />
	<s:hidden name="candName" />
	<s:hidden name="experience" />
	<s:hidden name="postingDate" />
	<s:hidden name="shortStatus" />
	<s:hidden name="cityCode1" />
	<s:hidden name="stateCode1" />
	<s:hidden name="countryCode1" />
	<s:hidden name="candCode" />
	<s:hidden name="refFlag"/>	<s:hidden name="myPage"/>	
<s:hidden name="qualiFlag"/><s:hidden name="skillFlag"/>
<s:hidden name="expFlag"/><s:hidden name="domFlag"/>

	<table width="100%" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Candidate
					Databank </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><strong class="formhead">Qualification Details :</strong></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td>
					<table id="tblQuali" width="100%" class="formbg">
						<tr>
							<td valign="top" class="formth" nowrap="nowrap" width="4%"><b><label
								class="set" name="sr" id="sr"
								ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
							<td valign="top" class="formth" align="left" width="21%"><b><label
								name="quaname" id="quaname" ondblclick="callShowDiv(this);"><%=label.get("quaname")%></label></b></td>
							<td valign="top" class="formth" align="left" width="21%"><b><label
								name="lvl" id="lvl" ondblclick="callShowDiv(this);"><%=label.get("lvl")%></label></b></td>
							<td valign="top" class="formth" align="left" width="18"><b><label
								name="spl" id="spl" ondblclick="callShowDiv(this);"><%=label.get("spl")%></label></b></td>
							<td valign="top" class="formth" align="left" width="18%"><b><label
								name="estb" id="estb" ondblclick="callShowDiv(this);"><%=label.get("estb")%></label></b></td>
							<td valign="top" class="formth" align="left" width="18%"><b><label
								name="year" id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label></b></td>



						</tr>
				
						<%
						int i = 0;
						%>
						<%
						Object[][] qualObj = (Object[][]) request.getAttribute("qualObj");
						%>
							
						<s:iterator value="qualList">

							<tr>
								<td width="4%" align="center" class="sortableTD"><s:property
									value="srNo" /></td>

								<td align="left" width="21%" class="sortableTD">&nbsp; <%=String.valueOf(qualObj[i][0])%>

								</td>
								<td width="21%" align="left" class="sortableTD">&nbsp; <%=String.valueOf(qualObj[i][1]).equals("null") ? ""
					: String.valueOf(qualObj[i][1])%></td>

								<td width="18%" align="left" class="sortableTD">&nbsp; <%=String.valueOf(qualObj[i][2]).equals("null") ? ""
					: String.valueOf(qualObj[i][2])%></td>



								<td width="18%" align="left" class="sortableTD">&nbsp;<s:property
									value="estbName" /></td>

								<td width="18%" align="left" class="sortableTD">&nbsp;<s:property
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
							<s:if test="qualiFlag">	</s:if><s:else>
			
						<tr>
						<td width="100%" align="center" colspan="6"><font color="red">There is no data to display.</font> </td>
			    		</tr>
			
			
			</s:else>
					</table>
					</td>
				</tr>
				</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		
		<!--<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>-->
		
		<tr>
			<td colspan="3">

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td><strong class="text_head">Experience History :</strong></td>
				</tr>
				<tr>
					<td>
					<table id="tblExp" width="100%" class="formbg">
						<tr>
							<td valign="top" class="formth" nowrap="nowrap" width="3%"><b><label
								class="set" name="sr" id="sr1"
								ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
							<td valign="top" class="formth" align="left" nowrap="nowrap" width="14%"><b><label
								name="empr" id="empr" ondblclick="callShowDiv(this);"><%=label.get("empr")%></label></b></td>
							<td valign="top" class="formth" align="left" nowrap="nowrap" width="12%"><b><label
								name="lastJobPrf" id="lastJobPrf"
								ondblclick="callShowDiv(this);"><%=label.get("lastJobPrf")%></label></b></td>
							<td valign="top" class="formth" align="left" width="12%" nowrap="nowrap"><b><label
								name="jDate" id="jDate" ondblclick="callShowDiv(this);"><%=label.get("jDate")%></label></b></td>
							<td valign="top" class="formth" align="left" width="12%" nowrap="nowrap"><b><label
								name="rDate" id="rDate" ondblclick="callShowDiv(this);"><%=label.get("rDate")%></label></b></td>
							<td valign="top" class="formth" align="left" width="15%"><b><label
								name="jDesc" id="jDesc" ondblclick="callShowDiv(this);"><%=label.get("jDesc")%></label></b></td>
							<td valign="top" class="formth" align="left" width="10%"><b><label
								name="ach" id="ach" ondblclick="callShowDiv(this);"><%=label.get("ach")%></label></b></td>
							<td valign="top" class="formth" align="left" width="10%"><b><label
								name="indsabbr" id="indsabbr" ondblclick="callShowDiv(this);"><%=label.get("indsabbr")%></label></b></td>
							<td valign="top" class="formth" align="left" width="12%"><b><label
								name="ctc" id="ctc" ondblclick="callShowDiv(this);"><%=label.get("ctc")%></label></b></td>

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
					<s:if test="expFlag">
					
						<tr>
					<td colspan="9"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				      </tr>
					
					
						</s:if><s:else>
			
					<tr>
					<td width="100%" align="center" colspan="9"><font color="red">There is no data to display.</font> </td>
					
					
					</tr>
					
			
			</s:else>
					</table>
					</td>
				</tr>

				</td>
				</tr>
				
				
				
			
			

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><strong class="text_head">Skill Details :</strong></td>
							<td align="left"></td>
						</tr>

						<tr>
							<td>
							<table id="tblSkill" width="100%" class="formbg">
								<tr>
									<td valign="top" class="formth" nowrap="nowrap" width="3%"><b><label
										class="set" name="sr" id="sr2"
										ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
									<td valign="top" class="formth" align="left" width="48%"><b><label
										name="sname" id="sname" ondblclick="callShowDiv(this);"><%=label.get("sname")%></label></b></td>
									<td valign="top" class="formth" align="left" nowrap="nowrap"
										width="49%"><b><label name="expInY" id="expInY"
										ondblclick="callShowDiv(this);"><%=label.get("expInY")%></label></b></td>

									
								</tr>

								<%
								Object[][] skillObj = (Object[][]) request.getAttribute("skillObj");
								%>
								<s:iterator value="skillList">

									<tr>
										<td width="3%" align="center" class="sortableTD"><s:property
											value="srNo" /></td>

										<td nowrap="nowrap" width="48%" class="sortableTD">&nbsp;
										<%=String.valueOf(skillObj[i][0]).equals("null") ? ""
					: String.valueOf(skillObj[i][0])%>
										</td>
										<td width="49%" align="left" class="sortableTD">&nbsp;<s:property
											value="skillExp" /></td>

									</tr>
									<%
									i++;
									%>
								</s:iterator>
								<%
								i = 0;
								%>
									<s:if test="skillFlag"><tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
					     	</tr>
					     </s:if><s:else>
			
			
			               <tr>
			                  <td width="100%" align="center" colspan="3"><font color="red">There is no data to display.</font> </td>
			
			
			               </tr>
			
			
			           </s:else>
							</table>
							</td>
						</tr>
						</td>
						</tr>
						
						
						
						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><strong class="text_head">Functional/Domain
									Details :</strong></td>

								</tr>
								<tr>
									<td>
									<table id="tblFunc" width="100%" class="formbg">
										<tr>
											<td valign="top" class="formth" width="1%" nowrap="nowrap"><b><label
												class="set" name="sr" id="sr3"
												ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
											<td valign="top" class="formth" align="left" nowrap="nowrap"
												width="49%"><b><label name="fun" id="fun"
												ondblclick="callShowDiv(this);"><%=label.get("fun")%></label></b></td>
											<td valign="top" class="formth" align="left" nowrap="nowrap"
												width="50%"><b><label name="expInY" id="expInY"
												ondblclick="callShowDiv(this);"><%=label.get("expInY")%></label></b></td>



										</tr>

										<%
										Object[][] funcObj = (Object[][]) request.getAttribute("funcObj");
										%>
										<s:iterator value="funcList">

											<tr>
												<td align="center" width="1%" nowrap="nowrap"
													class="sortableTD"><s:property value="srNo" /></td>

												<td width="49%" align="left" class="sortableTD">&nbsp;
												<%=String.valueOf(funcObj[i][0]).equals("null") ? ""
					: String.valueOf(funcObj[i][0])%>

												</td>
												<td width="50%" class="sortableTD">&nbsp;<s:property
													value="funcExp" /></td>

											</tr>
											<%
											i++;
											%>
										</s:iterator>
										<%
										i = 0;
										%>
										<s:if test="domFlag"></s:if><s:else>
						
						                 <tr>
							                 <td width="100%" align="center" colspan="3"><font color="red">There is no data to dsiplay.</font></td>
						                </tr>
						
						              </s:else>
									</table>
									</td>
								</tr>
								</td>
								</tr>
							</table>
							</td>
						</tr>

						
						
						
						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="78%"><jsp:include
										page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
									<td width="22%" align="right"><b>Page 2 of 2</b></td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					</td></tr></table></td></tr></table>
					
</s:form>


<script>
   function editFun(){
   
  		 document.getElementById("paraFrm").action="CandDataBank_editSec.action";
	    document.getElementById("paraFrm").submit();
	       return false;
   
   }
   
   function deleteFun(){
	var conf=confirm("Do you really want to delete this record?");
	  if(conf){
		document.getElementById("paraFrm").action="CandDataBank_deleteRec.action";
	    document.getElementById("paraFrm").submit();
	
	  }
	   return false;
	}
	
	function cancelFun(){
	
	
		document.getElementById("paraFrm").action="CandDataBank_input.action";
	    document.getElementById("paraFrm").submit();
	    return false;
	}
	
	
	function previousFun(){
	  	document.getElementById("paraFrm").action="CandDataBank_previous.action";
	    document.getElementById("paraFrm").submit();
		   return false;
	}
	
	function exportpdfreportFun(){
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "CandDataBank_getReportInPdf.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';

}

function exporttextreportFun(){
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "CandDataBank_getReportInText.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';

}

function closeFun() {
	window.close();
}	
	
  </script>
