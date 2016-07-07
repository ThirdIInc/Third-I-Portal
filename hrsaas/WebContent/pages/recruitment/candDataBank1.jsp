<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CandDataBank" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="rowId" />
	<s:hidden name="candCode" />
	<s:hidden name="candName" />
	<s:hidden name="experience" />
	<s:hidden name="postingDate" />
	<s:hidden name="shortStatus" />
	<s:hidden name="cityCode1" />
	<s:hidden name="stateCode1" />
	<s:hidden name="countryCode1" />
	<s:hidden name="updateSecond" />
	<s:hidden name="cancelSecond" />
	<s:hidden name="updateFirst" />
<s:hidden name="refFlag"/><s:hidden name="myPage"/>	
<s:hidden name="qualiFlag"/><s:hidden name="skillFlag"/>
<s:hidden name="expFlag"/><s:hidden name="domFlag"/>

	<table width="100%" class="formbg" height="250">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Candidate Databank </strong></td>
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
			<td colspan="3" width="100%">
			<table width="100%" border="0"  cellspacing="0" class="formbg">
			<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr><td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"	>
					<tr><td align="left" width="65%">
					<strong class="formhead">Qualification Details :</strong></td>
					<td align="right" width="35%" nowrap="nowrap"><input type="button" class="add"
								value="Add Row" onclick="return addRowToTableQualification();" />
							<input type="button" class="delete" value="Remove"
								onclick="return deleteQualification();" /></td>
					
					 </tr>
					
					 <tr>
							<td width="100%" colspan="9">
							<table id="tblQuali" width="100%" border="0" cellpadding="2"
								cellspacing="2" class="formbg">
								
								<tr>
									<td width="2%" valign="top" class="formth" nowrap="nowrap"><b><label
										class="set" name="sr" id="sr"
										ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
									<td width="7%" valign="top" class="formth" align="left"><b><label
										name="quaname" id="quaname" ondblclick="callShowDiv(this);"><%=label.get("quaname")%></label></b></td>
									<td width="2%" class="formth">&nbsp;</td>
									<td width="7%" valign="top" class="formth" align="left"
										><b><label name="lvl" id="lvl"
										ondblclick="callShowDiv(this);"><%=label.get("lvl")%></label></b></td>
									<td width="7%" valign="top" class="formth" align="left"><b><label
										name="spl" id="spl" ondblclick="callShowDiv(this);"><%=label.get("spl")%></label></b></td>
									<td width="2%" valign="top" class="formth" align="left">&nbsp;</td>
									<td width="5%" valign="top" class="formth" align="left"><b><label
										name="estb" id="estb" ondblclick="callShowDiv(this);"><%=label.get("estb")%></label></b></td>
									<td width="4%" valign="top" class="formth" align="left"><b><label
										name="year" id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label></b></td>
									<td width="2%" valign="top" class="formth" abbr="left"
										align="center"><input class="classcheck"
										style="text-align: center; border: none; margin: 1px"
										type="checkbox" size="2" name="chkEmpAll"
										id="paraFrm_chkEmpAll" onclick="return callForQualiAll();"></td>
									
								</tr>
								
								<%int i = 0;%><%!int j = 0;%>
								<%Object[][] qualObj = (Object[][]) request.getAttribute("qualObj");%>
								<s:iterator value="qualList">

									<tr>
										<td width="2%" align="center" class="sortableTD"><%=++i%></td>
										<td width="7%" class="sortableTD"><input type="text"
											name="<%="qualName" +i%>" id="paraFrm_qualName<%=i%>"
											readonly="true"
											value="<%=String.valueOf(qualObj[j][0]).equals("null")? "":String.valueOf(qualObj[j][0]) %>"
											size="20" /></td>
										<td width="2%" class="sortableTD"><img
											src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="callQualification('<s:property value="<%=""+i%>"/>')">
										</td>
										<td width="7%" class="sortableTD"><input type="text"
											name="<%="qualLevel" +i%>" id="paraFrm_qualLevel<%=i%>"
											readonly="true" size="20"
											value="<%=String.valueOf(qualObj[j][1]).equals("null")? "":String.valueOf(qualObj[j][1])%>" /></td>

										<td width="7%" class="sortableTD"><input type="text"
											name="<%="spelName" +i%>" id="paraFrm_spelName<%=i%>"
											readonly="true"
											value="<%=String.valueOf(qualObj[j][2]).equals("null")? "":String.valueOf(qualObj[j][2])%>"
											size="20" /></td>

										<td width="2%" class="sortableTD"><img
											src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="callSpecialization('<s:property value="<%=""+i%>"/>')">
										</td>

										<td width="5%" class="sortableTD"><s:textfield
											name="estbName" id="<%="paraFrm_estbName"+i%>" size="20"
											maxlength="50" /></td>

										<td width="4%" class="sortableTD"><s:textfield
											name="yearofPass" size="10" id="<%="paraFrm_yearofPass"+i%>"
											maxlength="4" onkeypress="return numbersOnly();" /></td>

										<td width="2%" align="center" class="sortableTD"><input
											type="checkbox" class="checkbox" value="N"
											name="quaChk<%=i%>" id="paraFrm_quaChk<%=i%>"
											onclick="callForQuali('<%=i%>')" />
											<input type="hidden"
											name="<%="deleteQuali"+i%>" id="paraFrm_deleteQuali<%=i%>"
											value="<%=""%>" />
											
											<input type="hidden" name="<%="spl"+i%>"
											id="paraFrm_spl<%=i%>" />
											<input type="hidden" name="<%="qua"+i%>"
											id="paraFrm_qua<%=i%>" />
											<input type="hidden" name="qualDetCode"
											id="qualDetCode" />
											<input type="hidden" name="<%="splId"+i%>"
											id="paraFrm_splId<%=i%>"
											value="<%=String.valueOf(qualObj[j][4]).equals("null")? "":String.valueOf(qualObj[j][4])%>" />
											<input type="hidden"
											name="<%="qualId"+i%>" id="paraFrm_qualId<%=i%>"
											value="<%=String.valueOf(qualObj[j][3]).equals("null")? "":String.valueOf(qualObj[j][3])%>" />
											</td>
										
										<%
										j++;
										%>
									</tr>

								    </s:iterator>
										<% j = 0;%>
								  </table>
							  </td>
					     </tr>	
			        </table>
					</td>
				</tr>
				
				
			<tr><td>
							<table width="100%"  border="0" class="formbg"><tr>	 
								 
							<td align="left" width="65%"><strong class="text_head">Experience Details :</strong></td>
							<td align="right" nowrap="nowrap" width="35%"><input type="button" class="add" value="Add Row" onclick="return addRowToTableExp();" />
							<input type="button" class="delete" value="Remove" onclick="return deleteExper();" /></td>
						 	</tr>
						 	
						 	<tr><td width="100%" colspan="15">
						 	
						 			<table id="tblExp" width="100%" border="0"  class="formbg">
						 				<tr>
									<td valign="top" class="formth" nowrap="nowrap" width="3%"><b><label
										class="set" name="sr" id="sr1"
										ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
									<td valign="top" class="formth" align="left" width="7%"><b><label
										name="empr" id="empr" ondblclick="callShowDiv(this);"><%=label.get("empr")%></label></b></td>
									<td valign="top" class="formth" align="left" width="6%"><b><label
										name="lastJobPrf" id="lastJobPrf"
										ondblclick="callShowDiv(this);"><%=label.get("lastJobPrf")%></label></b></td>
									<td valign="top" class="formth" align="left" width="4%"><b><label
										name="jDate" id="jDate" ondblclick="callShowDiv(this);"><%=label.get("jDate")%></label></b></td>
									<td valign="top" class="formth" align="left" width="2%">&nbsp;</td>
									<td valign="top" class="formth" align="left" width="4%"><b><label
										name="rDate" id="rDate" ondblclick="callShowDiv(this);"><%=label.get("rDate")%></label></b></td>
									<td valign="top" class="formth" align="left" width="2%">&nbsp;</td>
									<td valign="top" class="formth" align="left" width="6%"><b><label
										name="jDesc" id="jDesc" ondblclick="callShowDiv(this);"><%=label.get("jDesc")%></label></b></td>
									<td width="2%" class="formth">&nbsp;</td>
									<td valign="top" class="formth" align="left" width="5%"><b><label
										name="ach" id="ach" ondblclick="callShowDiv(this);"><%=label.get("ach")%></label></b></td>
									<td width="2%" class="formth">&nbsp;</td>
									<td valign="top" class="formth" align="left"><b><label
										name="indsabbr" id="indsabbr" ondblclick="callShowDiv(this);"><%=label.get("indsabbr")%></label></b></td>
									<td valign="top" class="formth" align="left">&nbsp;</td>
									<td valign="top" class="formth" align="left"><b><label
										name="ctc" id="ctc" ondblclick="callShowDiv(this);"><%=label.get("ctc")%></label></b></td>
									<td valign="top" class="formth" abbr="center" align="center">
									<input class="classcheck"
										style="text-align: center; border: none; margin: 1px"
										type="checkbox" size="2" name="chkExpAll"
										id="paraFrm_chkExpAll" onclick="return callForExpAll();"></td>
								
										
									
									
								</tr>
						 			<%
									int k = 0, e = 0;
									Object[][] expObj = (Object[][]) request.getAttribute("expObj");
								%>
								<s:iterator value="expList">

									<tr>
										<td align="center" width="3%" class="sortableTD"><%=++e%>
										</td>

										<td nowrap="nowrap" width="7%" class="sortableTD"><input
											type="text" name="emprName"  maxlength="50" id="paraFrm_emprName<%=e%>"
											size="8" value="<s:property value="emprName"/>" /> <!--<s:hidden name="expDtlId"  id="expDtlId"   />
							   						<s:hidden name="indsId"  id="indsId"   />
							   						 <s:hidden name="deleteExp"	id="deleteExp"  />
									  					--></td>
										<td width="6%" align="center" class="sortableTD"><input
											type="text" name="lastJobPro" maxlength="50" id="paraFrm_lastJobPro<%=e%>"
											value="<s:property value="lastJobPro"/>" size="8" /></td>

										<td width="4%" align="left" 
											class="sortableTD"><input type="text" name="joinDate"
											id="paraFrm_joinDate<%=e%>" size="8" maxlength="10"
											value="<s:property value="joinDate"/>"
											onkeypress="return numbersWithHiphen();" /></td>
										<td width="2%" class="sortableTD"><a
											href="javascript:NewCal('paraFrm_joinDate<%=e%>','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16"></a></td>


										<td width="4%" align="center" 
											class="sortableTD"><input type="text" name="relvDate"
											id="paraFrm_relvDate<%=e%>" size="8"
											value="<s:property value="relvDate"/>" maxlength="10"
											onkeypress="return numbersWithHiphen();" /></td>

										<td width="2%" class="sortableTD"><a
											href="javascript:NewCal('paraFrm_relvDate<%=e%>','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16"></a></td>

										<td width="6%" align="center" nowrap="nowrap"
											class="sortableTD"><input type="text"
											name="<%="jdExp" +e%>" id="paraFrm_jdExp<%=e%>"
											readonly="false"
											value="<%=String.valueOf(expObj[k][0]).equals("null") ? "":String.valueOf(expObj[k][0])%>"
											size="12" /></td>

										<td width="2%" align="left" nowrap="nowrap" class="sortableTD">
										<img src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="callJdExp('<s:property value="<%=""+e%>"/>')"></td>
											
											
										<td width="5%" align="left" class="sortableTD"><s:textarea
											rows="1" cols="10" name="specAch"
											id="<%="paraFrm_specAch" +e%>" /></td>
											
										<td width="2%" class="sortableTD"><img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
									          				onclick="javascript:callWindow('<%="paraFrm_specAch"+e%>','ach','','300','300');" ></td>
									          				
									     <td width="7%" align="left" nowrap="nowrap"
											class="sortableTD"><input type="text"
											name="<%="indType" +e%>" id="paraFrm_indType<%=e%>"
											readonly="true"
											value="<%=String.valueOf(expObj[k][1]).equals("null") ? "":String.valueOf(expObj[k][1])%>"
											size="7" /></td>

										<td width="2%" align="left" nowrap="nowrap" class="sortableTD">
										<img src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="callIndExp('<s:property value="<%=""+e%>"/>')"> </td>
											
										<td width="4%" align="left" class="sortableTD"><s:textfield
											name="ctcExp" id="<%="paraFrm_ctcExp" +e%>" size="3"
											onkeypress="return numbersWithDot();" maxlength="5" /></td>

										<td width="2%" align="center" class="sortableTD"><input
											type="checkbox" class="checkbox" value="N"
											name="expChk<%=e%>" id="paraFrm_expChk<%=e%>"
											onclick="callForExp('<%=e%>')" />
											<input type="hidden"
											name="<%="indsName"+e%>" id="paraFrm_indsName<%=e%>" />
											<input type="hidden"
											name="<%="deleteExp"+e%>" id="paraFrm_deleteExp<%=e%>"
											value="<%=""%>" />
											<input type="hidden" name="expDtlId"
											id="expDtlId" />
											<input type="hidden"
											name="<%="indsId"+e%>" id="paraFrm_indsId<%=e%>"
											value="<%=String.valueOf(expObj[k][2]).equals("null") ? "":String.valueOf(expObj[k][2])%>" />
										</td>	    					
										
										
									</tr>
									<%
									k++;
									%>
								</s:iterator>
					
						 		</table>
						 							 	
						 	</td></tr>
						 	
						 	
						 </table>
							
						</td>	
					</tr>
					
					<tr>
			<td width="100%" >
							<table width="100%" border="0" bordercolor="red" cellpadding="2" cellspacing="2" class="formbg">
					
			
							<tr><td width="50%" align="left" colspan="3">
								<div style="overflow: auto; width: 100%; height: 100">
								<table width="100%" border="0"  class="formbg" bordercolor="blue">
										<tr>	 
								 
										<td align="left" width="50%"><strong class="text_head">Skill Details :</strong></td>
										<td align="right" width="50%"><input type="button"
															class="add" value="Add Row"
															onclick="return addRowToTableSkill();" /> <input
															type="button" class="delete" value="Remove"
															onclick="return deleteSkill();" /></td>
												
										
						 		        </tr>
						 		  
						 		   <tr><td width="20%" colspan="5"><table id="tblSkill"  width="100%" border="0" cellpadding="0"	cellspacing="0" >
						 			<tr>
											<td valign="top" class="formth" width="3%"><b><label
												class="set" name="sr" id="sr2"
												ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
											<td valign="top" class="formth" align="left" width="7%"><b><label
												name="sname" id="sname" ondblclick="callShowDiv(this);"><%=label.get("sname")%></label></b></td>
											<td valign="top" class="formth" align="left" width="2%">&nbsp;</td>
											<td valign="top" class="formth" align="left" nowrap="nowrap"
												width="6%"><b><label name="expInY" id="expInY"
												ondblclick="callShowDiv(this);"><%=label.get("expInY")%></label></b></td>

											<td valign="top" class="formth" abbr="center" align="left"
												width="2%"><input class="classcheck"
												style="text-align: center; border: none; margin: 1px"
												type="checkbox" size="2" name="chkEmpAll"
												id="paraFrm_chkSkillAll" onclick="return callForSkillAll();"></td>
										</tr>
						 			
										<%
											Object[][] skillObj = (Object[][]) request.getAttribute("skillObj");
											int s = 0;
											int sk = 0;
										%>

										<s:iterator value="skillList">

											<tr>
												<td width="3%" align="center" class="sortableTD"><%=++sk%></td>

												<td  width="7%" class="sortableTD" align="center" ><input
													type="text" name="<%="skillName" +sk%>"
													id="paraFrm_skillName<%=sk%>" readonly="true"
													value="<%=String.valueOf(skillObj[s][0]).equals("null") ? "" :String.valueOf(skillObj[s][0]) %>"
													size="25" /></td>
												<td width="2%" align="left"  class="sortableTD"><img
													src="../pages/images/search2.gif" height="16"
													align="absmiddle" width="16" theme="simple"
													onclick="callSkill('<s:property value="<%=""+sk%>"/>')">
												</td>
												<td width="6%" align="center" class="sortableTD"><s:textfield
													name="skillExp" id="<%="paraFrm_skillExp" +sk%>" size="15"
													maxlength="5" onkeypress="return numbersWithDot();" /></td>

												<td width="2%" align="center" class="sortableTD"><input
													type="checkbox" class="checkbox" value="N"
													name="skillChk<%=sk%>" id="paraFrm_skillChk<%=sk%>"
													onclick="callForSkill('<%=sk%>')" />
													<input type="hidden" name="skillDtlId"
													id="skillDtlId" />
													<input type="hidden"
													name="<%="deleteSkill"+sk%>"
													id="paraFrm_deleteSkill<%=sk%>" value="<%=""%>" />
													<input type="hidden"
													name="<%="skillCode"+sk%>" id="paraFrm_skillCode<%=sk%>"
													value="<%=String.valueOf(skillObj[s][1]).equals("null") ? "":String.valueOf(skillObj[s][1])%>" />
													</td>

											

											</tr>
											<%
												i++;
												s++;
											%>
										</s:iterator>
										<%
											i = 0;
											s = 0;
										%>
					
						 		    </table>
						 		   
						 		   
						 		   </td>
						 		   </tr>
						 		   
							
								</table>
								</div>
							</td>
						  
							
							
							<td width="50%" align="left">
								<div style="overflow: auto; width: 100%; height: 100">
								<table width="100%" border="0" class="formbg">
										<tr>	 
								 
									<td align="left" width="50%"><strong class="text_head">Domain\Functional Details :</strong></td>
									<td align="right" width="50%"><input type="button"
														class="add" value="Add Row"
														onclick="return addRowToTableFunc();" /> <input
														type="button" class="delete" value="Remove"
														onclick="return deleteFunc();" /></td>
												
										
						 	     </tr>
						 	     
						 	     <tr><td width="50%" colspan="5">
						 	     	<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0" id="tblFunc" class="sortable">
						 			<tr>
											<td valign="top" class="formth" nowrap="nowrap" width="5%"><b><label
												class="set" name="sr" id="sr3"
												ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
											<td valign="top" class="formth" align="left" width="20%"><b><label
												name="fun" id="fun" ondblclick="callShowDiv(this);"><%=label.get("fun")%></label></b></td>
											<td valign="top" class="formth" align="left" width="2%">&nbsp;</td>
											<td valign="top" class="formth" align="left" width="20%"><b><label
												name="expInY" id="expInY" ondblclick="callShowDiv(this);"><%=label.get("expInY")%></label></b></td>

											<td valign="top" class="formth" abbr="right" align="left" width="3%">
											<input class="classcheck"
												style="text-align: center; border: none; margin: 1px"
												type="checkbox" size="2" name="chkFuncAll"
												id="paraFrm_chkFuncAll" onclick="return callForFuncAll();"></td>
										</tr>
						 			
										<%
											Object[][] funcObj = (Object[][]) request.getAttribute("funcObj");
											int f = 0;
											int fd = 0;
										%>

										<s:iterator value="funcList">

											<tr>
												<td width="5%" align="center" class="sortableTD"><%=++fd%></td>

												<td width="20%" class="sortableTD"  align="center" ><input
													type="text" name="<%="funcName" +fd%>"
													id="paraFrm_funcName<%=fd%>" readonly="true"
													value="<%=String.valueOf(funcObj[f][0]).equals("null") ? "" :String.valueOf(funcObj[f][0]) %>"
													size="25" /></td>
												<td width="2%" align="left" class="sortableTD"><img
													src="../pages/images/search2.gif" height="16"
													align="absmiddle" width="16" theme="simple"
													onclick="callFunc('<s:property value="<%=""+fd%>"/>')">
												</td>
												<td width="20%" align="center"  class="sortableTD"><s:textfield
													name="funcExp" id="<%="paraFrm_funcExp"+fd%>" size="15"
													maxlength="5" onkeypress="return numbersWithDot();" /></td>

												<td width="3%" align="center" class="sortableTD"><input
													type="checkbox" class="checkbox" value="N"
													name="funcChk<%=i%>" id="paraFrm_funcChk<%=fd%>"
													onclick="callForFunc('<%=fd%>')" />
													<input type="hidden"
													name="<%="deleteFunc"+fd%>" id="paraFrm_deleteFunc<%=fd%>"
													value="<%=""%>" />
													<input type="hidden"
													name="<%="funcCode"+fd%>" id="paraFrm_funcCode<%=fd%>"
													value="<%=String.valueOf(funcObj[f][1]).equals("null") ? "":String.valueOf(funcObj[f][1])%>" />
													<input type="hidden" name="funcDtlId"
													id="funcDtlId" />
													</td>
												
											</tr>
											<%
											f++;
											%>
										</s:iterator>
										<%
										f = 0;
										%>
					
						 		</table>
						 							 
						 	     
						 	     
						 	     </td></tr>
							
								</table>
								</div>
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
							<td width="22%" align="right"><strong>Page 2 of 2</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				

			</table></td></tr></table></td></tr></table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
	    function addRowToTableQualification()
		{
		  var tbl = document.getElementById('tblQuali');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		//  if(chkDuplicate(iteration)){
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cellLeft = row.insertCell(0);
		  var textNode = document.createTextNode(iteration);
		  cellLeft.className='sortableTD';
		  cellLeft.align='center';
		  cellLeft.appendChild(textNode);
		  
		   var cellQualName = row.insertCell(1);
		   var quaName = document.createElement('input');
		   cellQualName.className='sortableTD';
		  quaName.type = 'text';
		  quaName.name = 'qualName' + iteration;
		  quaName.id = 'paraFrm_qualName' + iteration;
		  quaName.size ='20';
		  quaName.readOnly='true';
		  cellQualName.appendChild(quaName);
		  
		 var cellQuaNameImg = row.insertCell(2);
		 cellQuaNameImg.className='sortableTD';
		 var img=  document.createElement('img');
		 img.type='image';
		 img.src='../pages/images/search2.gif';
		 img.height='16';
		 img.align='absmiddle';
		 img.width='16';
		 img.id='img'+ iteration;
		 img.theme='simple';
		 img.onclick=function(){
		
		 	document.getElementById('paraFrm_rowId').value=iteration; 
		    callsF9(500,325,'CandDataBank_f9Quali.action'); 
		};
		 cellQuaNameImg.appendChild(img);
		  
		  // right cell
		  var cellQualevel = row.insertCell(3);
		  cellQualevel.className='sortableTD';
		  var quaLvl = document.createElement('input');
		  quaLvl.type = 'text';
		  quaLvl.name = 'qualLevel'+iteration;
		  quaLvl.id =  'paraFrm_qualLevel'+iteration;
		  quaLvl.size = '20';
		  quaLvl.readOnly='true';
		  cellQualevel.appendChild(quaLvl);
		  
		  
		   var cellSpecializ = row.insertCell(4);
		  var spl = document.createElement('input');
		  cellSpecializ.className='sortableTD';
		  spl.type = 'text';
		  spl.name = 'spelName'+iteration;
		  spl.id =   'paraFrm_spelName'+iteration;
		  spl.size = '20';
		  spl.readOnly='true';
		  cellSpecializ.appendChild(spl);
		  
		  var cellQuaSplImg = row.insertCell(5);
		  cellQuaSplImg.className='sortableTD';
		var splImg=  document.createElement('img');
		splImg.type='image';
		splImg.src='../pages/images/search2.gif';
		 splImg.height='16';
		 splImg.align='absmiddle';
		 splImg.width='16';
		 splImg.id='img'+ iteration;
		 splImg.theme='simple';
		 splImg.onclick=function(){
		 	document.getElementById('paraFrm_rowId').value=iteration; 
		    callsF9(500,325,'CandDataBank_f9Specialization.action'); 
		};
		 cellQuaSplImg.appendChild(splImg);
		  
		  
		  var cellQuaCut = row.insertCell(6);
		  cellQuaCut.className='sortableTD';
		  var cut = document.createElement('input');
		  cut.type = 'text';
		  cut.name = 'estbName';
		  cut.id='paraFrm_estbName'+iteration;
		  cut.size = '20';
		  cut.maxLength='50';
		  cellQuaCut.appendChild(cut);
		  
		  
		  // select cell
		  var cellQuaOpt = row.insertCell(7);
		  cellQuaOpt.className='sortableTD';
		  var quaOpt = document.createElement('input');
		  quaOpt.type = 'text';
		  quaOpt.name = 'yearofPass' ;
		  quaOpt.id='paraFrm_yearofPass'+iteration;
		  quaOpt.size = '10';
		  quaOpt.maxLength='4';
		  quaOpt.onkeypress= function(){
		  		document.onkeypress = numbersOnly;
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = e.which
			clear();
			// Was key that was pressed a numeric character (0-9) or backspace (8)?
			if ( key > 47 && key < 58 || key == 8 || key == 0)
			 	return true; // if so, do nothing
			else // otherwise, discard character
				return false;m
			if (window.event) //IE
			    window.event.returnValue = null;     else //Firefox
			    e.preventDefault();
  }
		  cellQuaOpt.appendChild(quaOpt);
		  
		  
		  var cellQuaChk = row.insertCell(8);
		  cellQuaChk.className='sortableTD';
		  var qualiChk = document.createElement('input');
		 
		   qualiChk.type = 'checkbox';
		  qualiChk.name = 'quaChk'+iteration;
		  qualiChk.id = 'paraFrm_quaChk'+iteration;
		  qualiChk.onclick=function(){
		 			  if(document.getElementById('paraFrm_quaChk'+iteration).checked == true)
			   {	
			    		document.getElementById('paraFrm_deleteQuali'+iteration).value="Y";
			    		
			
			   }  else{
			  			 document.getElementById('paraFrm_deleteQuali'+iteration).value="";
			   	
		   		}
		 		
		
			     	
			     
			     	
		};cellQuaChk.align='center';
		  cellQuaChk.appendChild(qualiChk);
		  
		   
		 // var hiddenDel=row.insertCell(9);
		  var hid = document.createElement('input');
		  hid.type = 'hidden';
		  hid.name = 'deleteQuali'+iteration;
		  hid.id = 'paraFrm_deleteQuali' + iteration;
		//  hiddenDel.appendChild(hid);
		  cellQuaChk.appendChild(hid);
		  
		  
		 // var hiddenQual=row.insertCell(10);
		  var hidQualiId = document.createElement('input');
		  hidQualiId.type = 'hidden';
		  hidQualiId.name = 'qualId'+iteration;
		  hidQualiId.id = 'paraFrm_qualId'+iteration;
		   cellQuaChk.appendChild(hidQualiId);
		//  hiddenQual.appendChild(hidQualiId);
		  
		  //var hiddenSplCell=row.insertCell(11);
		  var hiddenSpl= document.createElement('input');
		  hiddenSpl.type = 'hidden';
		  hiddenSpl.name = 'splId'+iteration;
		  hiddenSpl.id = 'paraFrm_splId'+iteration;
		  cellQuaChk.appendChild(hiddenSpl);
		 // hiddenSplCell.appendChild(hiddenSpl);
		  
		  
		  
		 // var hiddenDtlCode=row.insertCell(12);
		  var hiddenDtl= document.createElement('input');
		  hiddenDtl.type = 'hidden';
		  hiddenDtl.name = 'qualDetCode';
		   cellQuaChk.appendChild(hiddenDtl);
		//  hiddenDtlCode.appendChild(hiddenDtl);
		  
		//  var hiddenQuaName=row.insertCell(13);
		  var hiddenQua= document.createElement('input');
		  hiddenQua.type = 'hidden';
		  hiddenQua.name = 'qua';
		  hiddenQua.id = 'paraFrm_qua'+iteration;
		  cellQuaChk.appendChild(hiddenQua);
		//  hiddenQuaName.appendChild(hiddenQua);
		  
		//  var hiddenSplName=row.insertCell(14);
		  var hiddenSpl= document.createElement('input');
		  hiddenSpl.type = 'hidden';
		  hiddenSpl.name = 'spl';
		  hiddenSpl.id = 'paraFrm_spl'+iteration;
		  cellQuaChk.appendChild(hiddenSpl);
		 // hiddenSplName.appendChild(hiddenSpl);
		  //}
		 
		  
   }  
   
   function addRowToTableExp()
		{
		  var tbl = document.getElementById('tblExp');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		//  if(chkDuplicate(iteration)){
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cellLeft = row.insertCell(0);
		  cellLeft.className='sortableTD';
		  var textNode = document.createTextNode(iteration);
		  cellLeft.align='center';
		  cellLeft.appendChild(textNode);
		  
		   var cellEmprName = row.insertCell(1);
		     cellEmprName.className='sortableTD';
		  var emprName = document.createElement('input');
		  emprName.type = 'text';
		  emprName.name = 'emprName';
		  emprName.id = 'paraFrm_emprName'+iteration;
		  emprName.size ='8';
		  emprName.maxLength='50';
		  cellEmprName.appendChild(emprName);
		  
		
		  var cellLastJobPro = row.insertCell(2);
		  cellLastJobPro.className='sortableTD';
		  var lastJobProf = document.createElement('input');
		  lastJobProf.type = 'text';
		  lastJobProf.name = 'lastJobPro';
		  lastJobProf.id =  'paraFrm_lastJobPro'+iteration;
		  lastJobProf.size = '8';
		  lastJobProf.maxLength='50';
		  cellLastJobPro.align='center';
		  cellLastJobPro.appendChild(lastJobProf);
		  
		  
		  var cellJoinDate = row.insertCell(3);
		  cellJoinDate.className='sortableTD';
		  var joinDate = document.createElement('input');
		  joinDate.type = 'text';
		  joinDate.name = 'joinDate';
		  joinDate.id =   'paraFrm_joinDate'+iteration;
		  joinDate.size = '8';
		  joinDate.maxLength='10';
		  joinDate.onkeypress=function(){
		  
		  document.onkeypress = numbersWithHiphen;
						var key //= (window.event) ? event.keyCode : e.which;
						if (window.event)
						    key = event.keyCode
						else
						   	key = e.which
						clear();
						// Was key that was pressed a numeric character (0-9) or backspace (8)?
						if ( (key > 47 && key < 58) || key == 8 || key == 45 || key == 0){
						 	return true;} // if so, do nothing
						else {// otherwise, discard character
							return false;
						}
						if (window.event) //IE
						    window.event.returnValue = null;     else //Firefox
						    e.preventDefault();
							  
		  
		  
		  };
		  cellJoinDate.appendChild(joinDate);
		  
		   var cellJoinDtImg= row.insertCell(4);
		   cellJoinDtImg.className='sortableTD';
		   var JoinDtImg=  document.createElement('img');
		   JoinDtImg.type='image';
		   JoinDtImg.src="../pages/images/Date.gif";
		  JoinDtImg.height='16';
		  JoinDtImg.align='absmiddle';
		   JoinDtImg.width='16';
		  JoinDtImg.id='img'+ iteration;
		   JoinDtImg.theme='simple';
		  JoinDtImg.align="center";
		  JoinDtImg.onclick=function(){	 	
		 
				NewCal('paraFrm_joinDate'+iteration,'DDMMYYYY');
	 		
	     };
	  	 cellJoinDtImg.appendChild(JoinDtImg);
		 
		  
		  var cellRelvDate = row.insertCell(5);
		  cellRelvDate.className='sortableTD';
		  var relvDate = document.createElement('input');
		  
		  relvDate.type = 'text';
		  relvDate.name = 'relvDate';
		  relvDate.id =   'paraFrm_relvDate'+iteration;
		  relvDate.size = '8';
		  relvDate.maxLength='10';
		  relvDate.onkeypress=function(){
		  
		  document.onkeypress = numbersWithHiphen;
						var key //= (window.event) ? event.keyCode : e.which;
						if (window.event)
						    key = event.keyCode
						else
						   	key = e.which
						clear();
						// Was key that was pressed a numeric character (0-9) or backspace (8)?
						if ( (key > 47 && key < 58) || key == 8 || key == 45 || key == 0){
						 	return true;} // if so, do nothing
						else {// otherwise, discard character
							return false;
						}
						if (window.event) //IE
						    window.event.returnValue = null;     else //Firefox
						    e.preventDefault();
							  
		  
		  
		  };
		  cellRelvDate.align='center';
		  cellRelvDate.appendChild(relvDate);
		 //  relvDate.onblur=validateDate('paraFrm_relvDate'+iteration,'Relieving Date')
		  
		  var cellRelDtImg= row.insertCell(6);
		  cellRelDtImg.className='sortableTD';
		  var RelDtImg=  document.createElement('img');
		  RelDtImg.type='image';
		  RelDtImg.src="../pages/images/Date.gif";
		  RelDtImg.height='16';
		  RelDtImg.align='absmiddle';
		  RelDtImg.width='16';
		  RelDtImg.id='img'+ iteration;
		  RelDtImg.theme='simple';
		  RelDtImg.align="center";
		  RelDtImg.onclick=function(){	 	
		 
				NewCal('paraFrm_relvDate'+iteration,'DDMMYYYY');
	 		
	     };
	  
 		 cellRelDtImg.appendChild(RelDtImg);
		  
		  
		  
		  
		  var cellJD = row.insertCell(7);
		  cellJD.className='sortableTD';
		  var jdExp = document.createElement('input');
		  jdExp.type = 'text';
		  jdExp.name = 'jdExp'+iteration;
		  jdExp.id =  'paraFrm_jdExp'+iteration;
		  jdExp.size = '12';
		  jdExp.maxLength='200';
		  cellJD.align='center';
		  cellJD.appendChild(jdExp);
		  
		 var cellJDImg = row.insertCell(8);
		 cellJDImg.className='sortableTD';
		 var jdImg=  document.createElement('img');
		 jdImg.type='image';
		 jdImg.src='../pages/images/search2.gif';
		 jdImg.height='16';
		 jdImg.align='absmiddle';
		 jdImg.width='16';
		 jdImg.id='img'+ iteration;
		 jdImg.theme='simple';
		 jdImg.onclick=function(){
		 	document.getElementById('paraFrm_rowId').value=iteration; 
		    callsF9(500,325,'CandDataBank_f9JobExp.action'); 
		};
		 cellJDImg.appendChild(jdImg);
		  
		  
		  var cellSpeAch = row.insertCell(9);
		  cellSpeAch.className='sortableTD';
		  var specAch = document.createElement('textarea');
		 
		  specAch.name = 'specAch';
		  specAch.id =  'paraFrm_specAch'+iteration;
		  specAch.rows =  '1';
		  specAch.cols =  '10';
		  cellSpeAch.appendChild(specAch);
		  
		  
		  
		 var cellSpecImg = row.insertCell(10);
		    cellSpecImg.className='sortableTD';
			var img=  document.createElement('img');
			img.type='image';
			img.src="../pages/images/zoomin.gif";
	 		img.height='12';
	 		img.align='bottom';
 			img.width='12';
 			img.id='img'+ iteration;
			img.theme='simple';
 			img.onclick=function(){
 			readFlag='';
 			fieldName='paraFrm_specAch'+iteration;
 			windowName='ach';
 			charCount='300';
 			maxLength='300';
 			mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
 			window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
			document.getElementById('paraFrm').target ="main";
 	
			};
 			cellSpecImg.appendChild(img);
		  
		  
		  
		  
		  
		  
		  
		   var cellInds = row.insertCell(11);
		  cellInds.className='sortableTD';
		  var indType = document.createElement('input');
		  indType.type = 'text';
		  indType.name = 'indType'+iteration;
		  indType.id =  'paraFrm_indType'+iteration;
		  indType.size = '7';
		  indType.readOnly='true';
		  cellInds.appendChild(indType);
		  
		 var cellindTypeImg = row.insertCell(12);
		 cellindTypeImg.className='sortableTD';
		 var indTypeImg=  document.createElement('img');
		 indTypeImg.type='image';
		 indTypeImg.src='../pages/images/search2.gif';
		 indTypeImg.height='16';
		 indTypeImg.align='absmiddle';
		 indTypeImg.width='16';
		 indTypeImg.id='img'+ iteration;
		 indTypeImg.theme='simple';
		 indTypeImg.onclick=function(){
		 	document.getElementById('paraFrm_rowId').value=iteration; 
		    callsF9(500,325,'CandDataBank_f9IndExp.action'); 
		  };
		  cellindTypeImg.align='center';
		 cellindTypeImg.appendChild(indTypeImg);
		 
		 
		  var cellCTC = row.insertCell(13);
		  cellCTC.className='sortableTD';
		  var ctcExp = document.createElement('input');
		  ctcExp.type = 'text';
		  ctcExp.name = 'ctcExp';
		  ctcExp.id =  'paraFrm_ctcExp'+iteration;
		  ctcExp.size='3';
		  ctcExp.maxLength='5';
		  ctcExp.onkeypress=function(){
		  			document.onkeypress = numbersWithDot;
					var key //= (window.event) ? event.keyCode : e.which;
					if (window.event)
					    key = event.keyCode
					else
					   	key = e.which
					clear();
					// Was key that was pressed a numeric character (0-9) or backspace (8)?
					if ( key > 47 && key < 58 || key == 8 || key == 46 || key == 0)
					 	return true; // if so, do nothing
					else // otherwise, discard character
						return false;
					if (window.event) //IE
					    window.event.returnValue = null;     else //Firefox
					    e.preventDefault();
						  
		  
		  };
		  cellCTC.appendChild(ctcExp);
		   
		  var cellExpChk = row.insertCell(14);
		   cellExpChk.className='sortableTD';
		  var expChk = document.createElement('input');
		  expChk.type = 'checkbox';
		  expChk.name = 'expChk'+iteration;
		  expChk.id = 'paraFrm_expChk'+iteration;
		  expChk.onclick=function(){
		 		if(document.getElementById('paraFrm_expChk'+iteration).checked == true)
			   {	
			    		document.getElementById('paraFrm_deleteExp'+iteration).value="Y";
			   }  else{
			  			 document.getElementById('paraFrm_deleteExp'+iteration).value="";
		   		}
		  };
		  cellExpChk.align='center';
		  cellExpChk.appendChild(expChk);
		    		  
		  
		  //var cellExpDel = row.insertCell(14);
		  var expDel = document.createElement('input');
		  expDel.type = 'hidden';
		  expDel.name = 'deleteExp'+iteration;
		  expDel.id = 'paraFrm_deleteExp'+iteration;
		   cellExpChk.appendChild(expDel);
		 // cellExpDel.appendChild(expDel);
		  
		 // var cellExpId = row.insertCell(15);
		  var expId = document.createElement('input');
		  expId.type = 'hidden';
		  expId.name = 'indsId'+iteration;
		  expId.id = 'paraFrm_indsId'+iteration;
		  cellExpChk.appendChild(expId);
		  //cellExpId.appendChild(expId);
		  
		  
		//  var cellExpDet = row.insertCell(16);
		 var expDet = document.createElement('input');
		  expDet.type = 'hidden';
		  expDet.name = 'expDtlId';
		 expDet.id = 'expDtlId'+iteration;
		  cellExpChk.appendChild(expDet);
		// cellExpDet.appendChild(expDet);
		//cellExpChk.appendChild(expDetInd);
		  
		  // var cellExpIndDet = row.insertCell(17);
		  var expDetInd = document.createElement('input');
		  expDetInd.type = 'hidden';
		  expDetInd.name = 'indsName'+iteration;
		  expDetInd.id = 'paraFrm_indsName'+iteration;
		   cellExpChk.appendChild(expDetInd);
		 // cellExpIndDet.appendChild(expDetInd);

		
   }  
   
   
   function addRowToTableSkill()
	{
		  var tbl = document.getElementById('tblSkill');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		//  if(chkDuplicate(iteration)){
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cellLeft = row.insertCell(0);
		  cellLeft.className='sortableTD';
		  var textNode = document.createTextNode(iteration);
		  cellLeft.align='center';
		  cellLeft.appendChild(textNode);
		  
		    var cellskillName = row.insertCell(1);
		    cellskillName.className='sortableTD';  
		  var skillName = document.createElement('input');
		  skillName.type = 'text';
		  skillName.name = 'skillName' + iteration;
		  skillName.id = 'paraFrm_skillName' + iteration;
		  skillName.size ='25';
		  skillName.readOnly='true';
		  cellskillName.align='center';
		  cellskillName.appendChild(skillName);
		  
		 var skillNameImg = row.insertCell(2);
		 skillNameImg.className='sortableTD';  
		 var img=  document.createElement('img');
		 img.type='image';
		 img.src='../pages/images/search2.gif';
		 img.height='16';
		 img.align='absmiddle';
		 img.width='16';
		 img.id='img'+ iteration;
		 img.theme='simple';
		 img.onclick=function(){
		 	document.getElementById('paraFrm_rowId').value=iteration; 
		    callsF9(500,325,'CandDataBank_f9Skill.action'); 
		};
		skillNameImg.align='left';
		 skillNameImg.appendChild(img);
		  
		  var cellSkillExp = row.insertCell(3);
		  cellSkillExp.className='sortableTD';
		  var skillExp = document.createElement('input');
		  skillExp.type = 'text';
		  skillExp.name = 'skillExp';
		  skillExp.id = 'paraFrm_skillExp'+iteration;
		  skillExp.size ='15';
		  skillExp.maxLength='5';
		  skillExp.onkeypress=function(){
		  			document.onkeypress = numbersWithDot;
					var key //= (window.event) ? event.keyCode : e.which;
					if (window.event)
					    key = event.keyCode
					else
					   	key = e.which
					clear();
					// Was key that was pressed a numeric character (0-9) or backspace (8)?
					if ( key > 47 && key < 58 || key == 8 || key == 46 || key == 0)
					 	return true; // if so, do nothing
					else // otherwise, discard character
						return false;
					if (window.event) //IE
					    window.event.returnValue = null;     else //Firefox
					    e.preventDefault();
						  
		  
		  };
		  cellSkillExp.align='center';
		  cellSkillExp.appendChild(skillExp);
		  
		  var cellSkillChk = row.insertCell(4);
		  cellSkillChk.className='sortableTD';
		  var skillChk = document.createElement('input');
		 
		  skillChk.type = 'checkbox';
		  skillChk.name = 'skillChk'+iteration;
		  skillChk.id = 'paraFrm_skillChk'+iteration;
		  skillChk.onclick=function(){
		 		if(document.getElementById('paraFrm_skillChk'+iteration).checked == true)
			   {	
			    		document.getElementById('paraFrm_deleteSkill'+iteration).value="Y";
			   }  else{
			  			 document.getElementById('paraFrm_deleteSkill'+iteration).value="";
		   		}
		};
		cellSkillChk.align='center';
		  cellSkillChk.appendChild(skillChk);
		  
		  
		 //  var cellSkillDel = row.insertCell(5);
		  var skillDel = document.createElement('input');
		  skillDel.type = 'hidden';
		  skillDel.name = 'deleteSkill'+iteration;
		  skillDel.id = 'paraFrm_deleteSkill'+iteration;
		   cellSkillChk.appendChild(skillDel);
		//  cellSkillDel.appendChild(skillDel);
		  
		 // var cellSkillId = row.insertCell(6);
		  var skillCodeId = document.createElement('input');
		  skillCodeId.type = 'hidden';
		  skillCodeId.name = 'skillCode'+iteration;
		  skillCodeId.id = 'paraFrm_skillCode'+iteration;
		   cellSkillChk.appendChild(skillCodeId);
		  //cellSkillId.appendChild(skillCodeId);
		  
		  
		  //var cellExpDet = row.insertCell(7);
		 var expDet = document.createElement('input');
		  expDet.type = 'hidden';
		 expDet.name = 'skillDtlId';
		  expDet.id = 'skillDtlId'+iteration;
		   cellSkillChk.appendChild(expDet);
		  //cellExpDet.appendChild(expDet);
		  		  
		  
   }  
   
    function addRowToTableFunc()
	{
		  var tbl = document.getElementById('tblFunc');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		//  if(chkDuplicate(iteration)){
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cellLeft = row.insertCell(0);
		  cellLeft.className='sortableTD';
		  var textNode = document.createTextNode(iteration);
		  cellLeft.align='center';
		  cellLeft.appendChild(textNode);
		  
		    var cellFuncName = row.insertCell(1);
		     cellFuncName.className='sortableTD';
		  var funcName = document.createElement('input');
		  funcName.type = 'text';
		  funcName.name = 'funcName' + iteration;
		  funcName.id = 'paraFrm_funcName' + iteration;
		  funcName.size ='25';
		  funcName.readOnly='true';
		  cellFuncName.align='center';
		  cellFuncName.appendChild(funcName);
		  
		 var skillNameImg = row.insertCell(2);
		 skillNameImg.className='sortableTD';
		 var img=  document.createElement('img');
		 img.type='image';
		 img.src='../pages/images/search2.gif';
		 img.height='16';
		 img.align='absmiddle';
		 img.width='16';
		 img.id='img'+ iteration;
		 img.theme='simple';
		 img.onclick=function(){
		 	document.getElementById('paraFrm_rowId').value=iteration; 
		    callsF9(500,325,'CandDataBank_f9Function.action'); 
		};
		skillNameImg.align='left';
		 skillNameImg.appendChild(img);
		  
		  var cellfuncExp = row.insertCell(3);
		  cellfuncExp.className='sortableTD';
		  var funcExp = document.createElement('input');
		  funcExp.type = 'text';
		  funcExp.name = 'funcExp';
		  funcExp.id = 'paraFrm_funcExp'+iteration;
		  funcExp.size ='15';
		  funcExp.maxLength='5';
		 funcExp.onkeypress=function(){
		  			document.onkeypress = numbersWithDot;
					var key //= (window.event) ? event.keyCode : e.which;
					if (window.event)
					    key = event.keyCode
					else
					   	key = e.which
					clear();
					// Was key that was pressed a numeric character (0-9) or backspace (8)?
					if ( key > 47 && key < 58 || key == 8 || key == 46 || key == 0)
					 	return true; // if so, do nothing
					else // otherwise, discard character
						return false;
					if (window.event) //IE
					    window.event.returnValue = null;     else //Firefox
					    e.preventDefault();
						  
		  
		  };
		  cellfuncExp.align='center';
		  cellfuncExp.appendChild(funcExp);		  
		  
		  var cellFuncChk = row.insertCell(4);
		  cellFuncChk .className='sortableTD';
		  var Chk = document.createElement('input');
		 
		  Chk.type = 'checkbox';
		  Chk.name = 'funcChk'+iteration;
		  Chk.id = 'paraFrm_funcChk'+iteration;
		 Chk.onclick=function(){
		 		if(document.getElementById('paraFrm_funcChk'+iteration).checked == true)
			   {	
			    		document.getElementById('paraFrm_deleteFunc'+iteration).value="Y";
			   }  else{
			  			 document.getElementById('paraFrm_deleteFunc'+iteration).value="";
		   		}
		};
		  cellFuncChk.align='center'; 	
		  cellFuncChk.appendChild(Chk);
		  
		  
		  
		  
		 // var cellFuncDel = row.insertCell(5);
		  var funcDel = document.createElement('input');
		  funcDel.type = 'hidden';
		  funcDel.name = 'deleteFunc'+iteration;
		  funcDel.id = 'paraFrm_deleteFunc'+iteration;
		   cellFuncChk.appendChild(funcDel);
		//  cellFuncDel.appendChild(funcDel);
		  
		 // var cellFuncId = row.insertCell(6);
		  var funcCodeId = document.createElement('input');
		  funcCodeId.type = 'hidden';
		  funcCodeId.name = 'funcCode'+iteration;
		  funcCodeId.id = 'paraFrm_funcCode'+iteration;
		     cellFuncChk.appendChild(funcCodeId);
		 // cellFuncId.appendChild(funcCodeId);
		  
		  
		 // var cellExpDet = row.insertCell(7);
		 var expDet = document.createElement('input');
		  expDet.type = 'hidden';
		  expDet.name = 'funcDtlId';
		 expDet.id = 'funcDtlId'+iteration;
		   cellFuncChk.appendChild(expDet);
		 // cellExpDet.appendChild(expDet);
		  
		  
   }   
   
   
    

		
   
   function chkSize(){
	
				  var tbl = document.getElementById('tblExp');
		  		  var lastRow = tbl.rows.length;
		  		  
		  		  
		  		  for(var idx=1;idx<lastRow;idx++){
		  		  var achv=document.getElementById('paraFrm_specAch'+idx).value;
		  		 	var jd=document.getElementById('paraFrm_jdExp'+idx).value;
		  		  if(achv.length >300){
		  		  alert("Maximum length of "+document.getElementById('ach').innerHTML.toLowerCase()+" is 300 characters");
		  		  return false;
		  		  }else if(jd.length >200){
		  		    alert("Maximum length of "+ document.getElementById('jDesc').innerHTML.toLowerCase()+" is 200 characters");
		  		 	 return false;
		  		  }
		  		  
		  		  
		  		  }
	
	return true;
	}
	
   
   function saveFun(){
   
   		if(document.getElementById("paraFrm_updateSecond").value="true"){
   			if(!chkDate()){
   				return false;
   			}else if(!chkSize()){
   			    return false;
   			}else if(!checkQuaValue()){
   				return false;   			
   			}else if(!checkExpValue()){
   				return false;   			
   			}else if(!checkSkillValue()){
   				return false;   			
   			}else if(!checkFuncValue()){
   				return false;   			
   			}
   			
   	//checkQuaValue
   			else{
   			
   		 	document.getElementById("paraFrm").action="CandDataBank_updateSecond.action";
	    	document.getElementById("paraFrm").submit();
	    	}
   		}else{
   				
   				if(!chkDate()){
   				   return false;
   			    }else if(!chkSize()){
   			       return false;
   			    }else if(!checkQuaValue()){
   				return false;   			
	   			}else if(!checkExpValue()){
	   				return false;   			
	   			}else if(!checkSkillValue()){
	   				return false;   			
	   			}else if(!checkFuncValue()){
	   				return false;   			
	   			}  
   			    
   			    else{
   			  
   				 document.getElementById("paraFrm").action="CandDataBank_saveSecond.action";
	   			 document.getElementById("paraFrm").submit();
	   		} 
	    }
	    return false;
	}
	

  
  function callSpecialization(id){
  
    	document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'CandDataBank_f9Specialization.action'); 
  
  }
   function callQualification(id)
    {
    
      document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'CandDataBank_f9Quali.action'); 
     
     
    }
    
    function callIndExp(id){
    
     document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'CandDataBank_f9IndExp.action'); 
     
    
    }
    
      function callJdExp(id){
    
     document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'CandDataBank_f9JobExp.action'); 
     
    
    }
    
     function callSkill(id)
    {
	    
	      document.getElementById('paraFrm_rowId').value=id; 
	      callsF9(500,325,'CandDataBank_f9Skill.action'); 
     
     
   }
   
    function callFunc(id){
    
     document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'CandDataBank_f9Function.action'); 
     
    
    }
    
    
    function deleteExper()
	{	
		var tbl = document.getElementById('tblExp');
		var rowLen = tbl.rows.length;
	 if(chkExp()){
	 var con=confirm('Do you really want to remove the records ?');
	 if(con){
	    document.getElementById('paraFrm').action="CandDataBank_deleteExperience.action";
  		document.getElementById('paraFrm').submit();
	    } else{
	    		for(i = 1; i < rowLen; i++){
				document.getElementById('paraFrm_expChk'+i).checked =false;
			  	document.getElementById('paraFrm_deleteExp'+i).value="";
			  	document.getElementById('paraFrm_chkExpAll').checked=false;
		 	 }
	    
	     
	    }
	 }else {
	 	alert('Please select a record to remove');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkExp(){
			   var tbl = document.getElementById('tblExp');
			  var rowLen = tbl.rows.length;
	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('paraFrm_expChk'+a).checked == true)
		    {	
	 	    return true;
	        }	   
	    }
	  return false;
 }
    
    function callForExpAll()
	   {
	 
			var tbl = document.getElementById('tblExp');
		var rowLen = tbl.rows.length;
		if(document.getElementById('paraFrm_chkExpAll').checked){
		 for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_expChk'+i).checked = true;
			  document.getElementById('paraFrm_deleteExp'+i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_expChk'+i).checked =false;
			  document.getElementById('paraFrm_deleteExp'+i).value="";
		  }
	   }	
	
  } 
   function callForExp(id)
	   {
	 	
	
	   if(document.getElementById('paraFrm_expChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_deleteExp'+id).value="Y";
	    
	   }
	   else{
	   document.getElementById('paraFrm_deleteExp'+id).value="";
	   	
   		}
  }
    
       function callForSkillAll()
	   {
	 
			var tbl = document.getElementById('tblSkill');
		var rowLen = tbl.rows.length;
		if(document.getElementById('paraFrm_chkSkillAll').checked){
		 for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_skillChk'+i).checked = true;
			  document.getElementById('paraFrm_deleteSkill'+i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_skillChk'+i).checked =false;
			  document.getElementById('paraFrm_deleteSkill'+i).value="";
		  }
	   }	
	
  }
		
   
    function callForSkill(id)
	   {
	 	
	
	   if(document.getElementById('paraFrm_skillChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_deleteSkill'+id).value="Y";
	    
	   }
	   else{
	   document.getElementById('paraFrm_deleteSkill'+id).value="";
	   	
   		}
  }
  
  
   function deleteSkill()
	{
		var tbl = document.getElementById('tblSkill');
		var rowLen = tbl.rows.length;
	 if(chkSkill()){
	 var con=confirm('Do you really want to remove the records ?');
	 if(con){
	    document.getElementById('paraFrm').action="CandDataBank_deleteSkill.action";
  		document.getElementById('paraFrm').submit();
	    } else{
	     	for(i = 1; i < rowLen; i++){
	     		document.getElementById('paraFrm_chkSkillAll').checked=false;
				document.getElementById('paraFrm_skillChk'+i).checked = false;
			    document.getElementById('paraFrm_deleteSkill'+i).value="";
		  }
	    }
	 }else {
	 	alert('Please select a record to remove');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkSkill(){
	
		
			var tbl = document.getElementById('tblSkill');
			var rowLen = tbl.rows.length;
	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('paraFrm_skillChk'+a).checked == true)
		   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
       
         function callForFuncAll()
	   {
	 
			var tbl = document.getElementById('tblFunc');
		var rowLen = tbl.rows.length;
		if(document.getElementById('paraFrm_chkFuncAll').checked){
		 for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_funcChk'+i).checked = true;
			  document.getElementById('paraFrm_deleteFunc'+i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_funcChk'+i).checked =false;
			  document.getElementById('paraFrm_deleteFunc'+i).value="";
		  }
	   }	
	
  }
		
   
    function callForFunc(id)
	   {
	 	
	
	   if(document.getElementById('paraFrm_funcChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_deleteFunc'+id).value="Y";
	    
	   }
	   else{
	   document.getElementById('paraFrm_deleteFunc'+id).value="";
	   	
   		}
  }
  
  
   function deleteFunc()
	{
	
	var tbl = document.getElementById('tblFunc');
	var rowLen = tbl.rows.length;
	
	 if(chkFunc()){
	 var con=confirm('Do you really want to remove the records ?');
	 if(con){
	    document.getElementById('paraFrm').action="CandDataBank_deleteFunc.action";
  		document.getElementById('paraFrm').submit();
	    } else{
	     		for(i = 1; i < rowLen; i++){
	     		document.getElementById('paraFrm_chkFuncAll').checked=false;
				document.getElementById('paraFrm_funcChk'+i).checked = false;
			    document.getElementById('paraFrm_deleteFunc'+i).value="";
		       }
	        }
	 }else {
	 	alert('Please select a record to remove');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkFunc(){
	
		
			var tbl = document.getElementById('tblFunc');
			var rowLen = tbl.rows.length;
	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('paraFrm_funcChk'+a).checked == true)
		   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
       
       
       
   function callForQualiAll()
	   {
	 
			var tbl = document.getElementById('tblQuali');
		var rowLen = tbl.rows.length;
		if(document.getElementById('paraFrm_chkEmpAll').checked){
		 for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_quaChk'+i).checked = true;
			  document.getElementById('paraFrm_deleteQuali'+i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_quaChk'+i).checked =false;
			  document.getElementById('paraFrm_deleteQuali'+i).value="";
		  }
	   }	
	
  }
		
   
    function callForQuali(id)
	   {
	 	
	
	   if(document.getElementById('paraFrm_quaChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_deleteQuali'+id).value="Y";
	    
	   }
	   else{
	   document.getElementById('paraFrm_deleteQuali'+id).value="";
	   	
   		}
  }
  
  
   function deleteQualification()
	{	var tbl = document.getElementById('tblQuali');
		var rowLen = tbl.rows.length;
	 if(chkQuali()){
	 var con=confirm('Do you really want to remove the records ?');
	 if(con){
	    document.getElementById('paraFrm').action="CandDataBank_deleteQualification.action";
  		document.getElementById('paraFrm').submit();
	    } else{
	     for(i = 1; i < rowLen; i++){
	     		document.getElementById('paraFrm_chkEmpAll').checked=false;
				document.getElementById('paraFrm_quaChk'+i).checked =false;
			  document.getElementById('paraFrm_deleteQuali'+i).value="";
		  }
	     return false;
	    }
	 }else {
	 	alert('Please select a record to remove');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkQuali(){
	
		
			var tbl = document.getElementById('tblQuali');
			var rowLen = tbl.rows.length;
	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('paraFrm_quaChk'+a).checked == true)
		   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
   
   function deleteFun(){
	var conf=confirm("Do you really want to remove this record?");
	  if(conf){
		document.getElementById("paraFrm").action="CandDataBank_deleteRec.action";
	    document.getElementById("paraFrm").submit();
	
	  }
	   return false;
	}
   	function cancelFun(){
	
		if(document.getElementById("paraFrm_cancelSecond").value="true"){
			document.getElementById("paraFrm").action="CandDataBank_cancelSec.action";
	    	document.getElementById("paraFrm").submit();
		
		}else{	
	
		document.getElementById("paraFrm").action="CandDataBank_input.action";
	    document.getElementById("paraFrm").submit();
	    }
	    return false;
	}
	
	
	function saveandpreviousFun(){
	if(!chkDate()){
   				return false;
   			}else if(!chkSize()){
   			    return false;
   			}else if(!checkQuaValue()){
   				return false;   			
   			}else if(!checkExpValue()){
   				return false;   			
   			}else if(!checkSkillValue()){
   				return false;   			
   			}else if(!checkFuncValue()){
   				return false;   			
   			}
   			
   			else{
			 document.getElementById("paraFrm").action="CandDataBank_saveAndPrev.action";
	   		 document.getElementById("paraFrm").submit();
	   		 }
	   		    return false;
	}
	
	
	
	function chkDate(){
		var tbl = document.getElementById('tblExp');
  		var lastRow = tbl.rows.length;
  		
  		for(var i=1;i<lastRow;i++){
  		var fromdate= document.getElementById('paraFrm_joinDate'+i).value;
  		var todate= document.getElementById('paraFrm_relvDate'+i).value;
  				if(!validateDate('paraFrm_relvDate'+i,'rDate'))
  		    	return false;
  		    	
  		    	if(!validateDate('paraFrm_joinDate'+i,'jDate'))
  		    	return false;
  		    	
  		    	if(!dateDifferenceEqual(fromdate, todate, 'paraFrm_relvDate'+i, 'jDate','rDate'))
				return false;
  		
  		 }

return true;

}



function checkFuncValue(){
var tbl = document.getElementById('tblFunc');
var lastRow = tbl.rows.length;


for(var i=1;i<lastRow;i++){
 		 		var funcName= document.getElementById('paraFrm_funcName'+i).value;
 		 		var exp=document.getElementById('paraFrm_funcExp'+i).value;
 		 	
 		 	 if(!(exp=="")){
 		 	 
 		 			if(funcName==""){
 		 		alert("Please select the "+ document.getElementById('fun').innerHTML.toLowerCase()+" in Functional/Domain Details.");
 		 		 	return false;
 		       }
 			}
 			
 			 if(!(valCTC('paraFrm_funcExp'+i,'expInY'))){
 		     	return false;
 		     }  
 			
 		}
 	
 return true;	 
 		
 }
 
 
 function checkSkillValue(){
var tbl = document.getElementById('tblSkill');
var lastRow = tbl.rows.length;
for(var i=1;i<lastRow;i++){
 		 		var sName= document.getElementById('paraFrm_skillName'+i).value;
 		 		var exp=document.getElementById('paraFrm_skillExp'+i).value;
 		 	
 		 	 if(!(exp=="")){
 		 	 
 		 			if(sName==""){
 		 		alert("Please select the "+ document.getElementById('sname').innerHTML.toLowerCase()+" in Skill Details.");
 		 		 	return false;
 		       }
 			}
 			  if(!(valCTC('paraFrm_skillExp'+i,'expInY'))){
 		     	return false;
 		     }  		
 			
 		}
 	
 return true;	 

 
 
 }				
	
function checkExpValue(){
var tbl = document.getElementById('tblExp');
var lastRow = tbl.rows.length;
for(var i=1;i<lastRow;i++){
 		 		var emp= document.getElementById('paraFrm_emprName'+i).value;
 		 		var jobPro=document.getElementById('paraFrm_lastJobPro'+i).value;
 		 		var doj=document.getElementById('paraFrm_joinDate'+i).value;
 		 		var rd=document.getElementById('paraFrm_relvDate'+i).value;
 		 		var je=document.getElementById('paraFrm_jdExp'+i).value;
 		 		var sa=document.getElementById('paraFrm_specAch'+i).value;
 		 		var it=document.getElementById('paraFrm_indType'+i).value;
 		 		var exCt=document.getElementById('paraFrm_ctcExp'+i).value;
 		 		
 		 		if(!(jobPro=="")){
 		 	 
 		 			if(emp==""){
 		 			alert("Please enter the "+ document.getElementById('empr').innerHTML.toLowerCase()+" in Experience Details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(doj=="")){
 		 	 
 		 			if(emp==""){
 		 			alert("Please enter the "+ document.getElementById('empr').innerHTML.toLowerCase()+" in Experience Details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(rd=="")){
 		 	 
 		 			if(emp==""){
 		 		alert("Please enter the "+ document.getElementById('empr').innerHTML.toLowerCase()+" in Experience Details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(je=="")){
 		 	 
 		 			if(emp==""){
 		 			alert("Please enter the "+ document.getElementById('empr').innerHTML.toLowerCase()+" in Experience Details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(sa=="")){
 		 	 
 		 			if(emp==""){
 		 			alert("Please enter the "+ document.getElementById('empr').innerHTML.toLowerCase()+" in Experience Details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(it=="")){
 		 	 
 		 			if(emp==""){
 		 		alert("Please enter the "+ document.getElementById('empr').innerHTML.toLowerCase()+" in Experience Details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(exCt=="")){
 		 	 
 		 			if(emp==""){
 		 		alert("Please enter the "+ document.getElementById('empr').innerHTML.toLowerCase()+" in Experience Details.");
 		 		 	return false;
 		       }
 		       
 		       
 		       
 			}
 			
 	  if(!(valCTC('paraFrm_ctcExp'+i,'ctc'))){
 		     	return false;
 		     }  		
 		}
 	
   return true;	 

 }	
 
 function checkQuaValue(){
var tbl = document.getElementById('tblQuali');
var lastRow = tbl.rows.length;
for(var i=1;i<lastRow;i++){
 		 		var qn= document.getElementById('paraFrm_qualName'+i).value;
 		 		var sn=document.getElementById('paraFrm_spelName'+i).value;
 		 		var en=document.getElementById('paraFrm_estbName'+i).value;
 		 		var yp=document.getElementById('paraFrm_yearofPass'+i).value;
 		 	
 		 		
 		 		if(!(sn=="")){
 		 	 		if(qn==""){
 		 			alert("Please select the "+ document.getElementById('quaname').innerHTML.toLowerCase()+" in Qualification details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(en=="")){
 		 	 
 		 			if(qn==""){
 		 			alert("Please select the "+ document.getElementById('quaname').innerHTML.toLowerCase()+" in Qualification details.");
 		 		 	return false;
 		 		 	}
 		       }else if(!(yp=="")){
 		 	 
 		 			if(qn==""){
 		 			alert("Please select the "+ document.getElementById('quaname').innerHTML.toLowerCase()+" in Qualification details.");
 		 		 	return false;
 		 		 	}
 		       }
 			
 		}
 	
   return true;	 

 
 }
 
 	function valCTC(ctcfieldname,lableName)
	{
	
	var amount=document.getElementById(ctcfieldname).value;
	var amountlabel=document.getElementById(lableName).innerHTML.toLowerCase();
	
		
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only one dot is allowed in "+amountlabel+"");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		 
		 }	
		 }
		  return true;
		 }				
   
  </script>

