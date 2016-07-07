<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>
<s:form action="EmployeeRequi" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<s:hidden name="hiringcode" />
			<s:hidden name="cancel1" />
			<s:hidden name="hiddenStatus" />
			<s:hidden name="reqApprStatus" />
			<s:hidden name="reqName" />
			<s:hidden name="createNewFlag" />
			<s:hidden name="offerAppointFlag" />
			<s:hidden name="internal" />
			<s:hidden name="external" />
			<s:hidden name="quaFlag" />
			<s:hidden name="flagView" />
			<s:hidden name="jdEffDate" />
			<s:hidden name="reqCode" />
			<s:hidden name="viewDom" />
			<s:hidden name="viewCert" />
			<s:hidden name="viewQua" />
			<s:hidden name="viewSkill" />
			<s:hidden name="position" />
			<s:hidden name="division" />
			<s:hidden name="branch" />
			<s:hidden name="department" />
			<s:hidden name="requisitionName" />
			<s:hidden name="requisitionCode" />
			<s:hidden name="rowId" />
			<s:hidden name="reqPositionName" />
			<s:hidden name="reqDiv" />
			<s:hidden name="reqBrn" />
			<s:hidden name="reqDept" />
			<s:hidden name="addNewFlag" />
			<s:hidden name="updateSecondFlag" />

			<s:hidden name="positionCode" />
			<s:hidden name="divisionCode" />
			<s:hidden name="branchCode" />
			<s:hidden name="deptCode" />
			<s:hidden name="leadTimeFlag" />
			<s:hidden name="replaceEmpId" />
			<s:hidden name="source" id="source" />
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower
					Requisition </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:hidden name="cancelFrth" /> <jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> <s:if
						test="%{offerAppointFlag}">
						<s:submit cssClass="token"
							action="EmployeeRequi_backToOfferAppoint" theme="simple"
							value='Back To %{createNewFlag}' onclick="return callView()" />
					</s:if></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
	<!-- 
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">


				<tr>
					<td><strong class="text_head">Competency Details :</strong></td>
				</tr>
			</table>
			</td>
		</tr>
	 -->	
		
		<tr>
			<td class="formtext" colspan="3">
			<table width="100%" border="0" class="formbg">

				<!--  Qualification Details Table start- -->
				<tr>
					<td>
					<% 
				                       try{
				                      %>
					
					<td><strong class="formhead">Qualification Details :</strong></td>
					<td align="right"><input type="button" class="add"
						value="Add Row" onclick="return addRowToTableQualification();" />
					<input type="button" class="delete" value="Remove"
						onclick="return deleteQualification();" /></td>

				</tr>

				<tr>
					<td colspan="10">

					<table id="tblQuali" width="100%">
						<tr>
							<td width="5%" valign="top" class="formth" nowrap="nowrap"
								align="center" class="sortableTD"><label class="set"
								name="sr" id="sr" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label>
							</td>
							<td width="10%" valign="top" class="formth" align="center"
								class="sortableTD"><label class="set" name="qtyp" id="qtyp"
								ondblclick="callShowDiv(this);"><%=label.get("qtyp")%></label></td>
							<td width="10%" valign="top" class="formth" class="sortableTD"><label
								class="set" name="qabbr" id="qabbr"
								ondblclick="callShowDiv(this);"><%=label.get("qabbr")%></label></td>
							<td width="2%" valign="top" class="formth" class="sortableTD">&nbsp;</td>
							<td width="10%" valign="top" class="formth" class="sortableTD"><label
								class="set" name="qlvl" id="qlvl"
								ondblclick="callShowDiv(this);"><%=label.get("qlvl")%></label></td>
							<td width="10%" valign="top" class="formth" class="sortableTD"><label
								class="set" name="spabbr" id="spabbr"
								ondblclick="callShowDiv(this);"><%=label.get("spabbr")%></label></td>
							<td width="2%" valign="top" class="formth" class="sortableTD">&nbsp;</td>
							<td width="8%" valign="top" class="formth" class="sortableTD"><label
								class="set" name="mrks" id="mrks"
								ondblclick="callShowDiv(this);"><%=label.get("mrks")%></label></td>
							<td width="4%" valign="top" class="formth" align="center"
								class="sortableTD"><label class="set" name="opt" id="opt"
								ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
							<td width="3%" valign="top" class="formth" align="center"
								class="sortableTD"><input class="classcheck"
								style="text-align: center; border: none; margin: 1px"
								type="checkbox" size="2" name="chkEmpAll" id="paraFrm_chkEmpAll"
								onclick="return callForQualiAll();"></td>

						</tr>
						<%!int v = 0;%>
						<%int i=0; %>
						<%int jj=0; %>

						<s:if test="quaFlag">
							<s:iterator value="qualList">

								<tr>
									<td width="5%" align="center" class="sortableTD"><%=++i%></td>
									<td width="8%" align="center" class="sortableTD"><s:select
										name="hqualType" id="<%="hqualType"+i%>" cssStyle="width:72"
										theme="simple"
										list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>

									<%
	      	   		                               
	      	   		                               String quaId = (String)request.getAttribute("qualificationId"+i); 
	      	   		                      
		   											String qualName= (String)request.getAttribute("qualificationName"+i);
		   											String lvl=(String)request.getAttribute("hqualiLevelName"+i);
		   											String spl=(String)request.getAttribute("hsplName"+i);
		   											String splId=(String)request.getAttribute("hsplId"+i);
		   										
		   											
	      	   		                               %>

									<td width="10%" class="sortableTD"><s:textfield
										name="<%="qualificationName" +i%>" value="<%=qualName %>"
										readonly="true" size="16" /></td>
									<td width="2%" class="sortableTD"><img
										src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callQualification('<s:property value="<%=""+i%>"/>')">
									</td>



									<td width="10%" align="left" class="sortableTD"><s:textfield
										name="<%="hqualiLevelName" +i%>" value="<%=lvl %>"
										readonly="true" size="15" /></td>




									<td width="10%" class="sortableTD"><s:textfield
										name="<%="hsplName" +i%>" value="<%=spl %>" readonly="true"
										size="15" /></td>

									<td width="2%" align="right" nowrap="nowrap" class="sortableTD">
									<img src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callSpecialization('<s:property value="<%=""+i%>"/>')">
									</td>



									<td width="8%" align="left" class="sortableTD"><input
										type="text" name="hcutOff" id="<%="hcutOff"+i %>"
										maxlength="5" value='<s:property value="hcutOff" />' size="10"
										onkeypress="return numbersWithDot();" /></td>

									<td width="4%" align="center" class="sortableTD"><s:select
										name="sel" cssStyle="width:50" theme="simple"
										list="#{'':'','A':'And','R':'Or'}" /></td>

									<td width="3%" align="center" class="sortableTD"><input
										type="checkbox" class="checkbox" value="N"
										name="paraFrm_quaChk<%=i%>" id="paraFrm_quaChk<%=i%>"
										onclick="callForQuali(<%=i%>);" /> <input type="hidden"
										name="<%="paraFrm_hdeleteQuali"+i%>"
										id="paraFrm_hdeleteQuali<%=i%>" /> <input type="hidden"
										name="<%="qualificationId"+i%>"
										id="paraFrm_qualificationId<%=i%>" value="<%=quaId%>" /> <input
										type="hidden" name="<%="hsplId"+i%>" id="paraFrm_hsplId<%=i%>"
										value="<%=splId%>" /> <input type="hidden"
										name="<%="spname"+i%>" id="paraFrm_spname<%=i%>" /> <s:hidden
										name="quaDetCode" /> <input type="hidden"
										name="<%="qname"+i%>" id="paraFrm_qname<%=i%>" /></td>

								</tr>


							</s:iterator>
						</s:if>
						<s:else>


							<tr>
								<td width="5%" align="center" class="sortableTD"><%=++i%></td>
								<td width="8%" align="center" class="sortableTD"><s:select
									name="hqualType" id="hqualType1" cssStyle="width:72"
									theme="simple"
									list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>


								<td width="10%" class="sortableTD"><s:textfield
									name="qualificationName1" id="paraFrm_qualificationName1"
									readonly="true" size="16" /></td>


								<td width="2%" class="sortableTD"><img
									src="../pages/images/search2.gif" height="16" align="absmiddle"
									width="16" theme="simple"
									onclick="callQualification('<s:property value="<%=""+i%>"/>')">
								</td>
								<td width="12%" align="left" class="sortableTD"><s:textfield
									name="hqualiLevelName1" readonly="true" size="15" /></td>

								<td width="10%" align="center" class="sortableTD"><s:textfield
									name="hsplName1" readonly="true" size="15" /></td>


								<td width="2%" class="sortableTD"><img
									src="../pages/images/search2.gif" height="16" align="absmiddle"
									width="16" theme="simple"
									onclick="callSpecialization('<s:property value="<%=""+i%>"/>')">
								</td>

								<td width="8%" align="left" class="sortableTD"><s:textfield
									name="hcutOff" id="hcutOff1" maxlength="5" readonly="false"
									size="10" onkeypress="return numbersWithDot();" /></td>
								<td width="4%" align="center" class="sortableTD"><s:select
									name="sel" cssStyle="width:50" theme="simple"
									list="#{'':'','A':'And','R':'Or'}" /></td>

								<td width="3%" align="center" class="sortableTD">&nbsp; <input
									type="checkbox" class="checkbox" value="N"
									name="paraFrm_quaChk1" id="paraFrm_quaChk<%=i%>"
									onclick="callForQuali('<%=i%>');" /> <input type="hidden"
									name="paraFrm_hdeleteQuali1" id="paraFrm_hdeleteQuali1" /> <input
									type="hidden" name="qualificationId1"
									id="paraFrm_qualificationId1" /> <input type="hidden"
									name="hsplId1" id="paraFrm_hsplId1" /><s:hidden
									name="quaDetCode" /> <input type="hidden" name="qname1"
									id="paraFrm_qname1" /><input type="hidden" name="spname1"
									id="paraFrm_spname1" /></td>


							</tr>

						</s:else>

					</table>
					</td>
				</tr>




			</table>
			<%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
			</td>
		</tr>
		<!--Qualification End Table -->


		<tr>
			<td width="100%" colspan="3"><!--Start of Skill Details --> <% 
                        try{
                        	%>
			<table width="100%" border="0" class="formbg">
				</td>
				</tr>

				<tr>
					<td><strong class="text_head">Skill Details :</strong></td>

					<td align="right"><input type="button" class="add"
						value="Add Row" onclick="addRowToTableSkill();" /> <input
						type="button" class="delete" value="Remove"
						onclick="return deleteSkill();" /></td>
				</tr>
				<td colspan="10" width="100%">

				<table border="0" id="tblSkill" width="100%">
					<tr>
						<td width="5%" valign="top" class="formth" nowrap="nowrap"
							align="center"><label class="set" name="sr" id="sr1"
							ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></td>
						<td width="10%" valign="top" class="formth" align="center"><label
							class="set" name="styp" id="styp" ondblclick="callShowDiv(this);"><%=label.get("styp")%></label></td>
						<td width="20%" valign="top" class="formth" align="center"><label
							class="set" name="sname" id="sname"
							ondblclick="callShowDiv(this);"><%=label.get("sname")%></label></td>
						<td width="2%" valign="top" class="formth" align="left">&nbsp;</td>
						<td width="20%" valign="top" class="formth" align="center"><label
							class="set" name="experience" id="experience"
							ondblclick="callShowDiv(this);"><%=label.get("experience")%></label></td>
						<td width="4%" valign="top" class="formth" align="center"><label
							class="set" name="opt" id="opt1" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
						<td width="3%" valign="top" class="formth" align="center"
							abbr="right"><input class="classcheck" align="top"
							style="text-align: center; border: none; margin: 1px"
							type="checkbox" size="2" name="chkSkillAll"
							id="paraFrm_chkSkillAll" onclick="return callForSkillAll();">


						</td>

					</tr>
					<%! int s=0; %>

					<%int j=0; %>
					<s:if test="skill">
						<s:iterator value="skillList">

							<tr>
								<td width="5%" align="center" class="sortableTD"><%=++j%><s:hidden
									name="hssrlNo" /></td>
								<td width="9%" align="center" class="sortableTD"><s:select
									name="skillType" id="<%="skillType"+j %>" cssStyle="width:72"
									theme="simple"
									list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>


								<%
		      	   		                              
		      	   		                                String si = (String)request.getAttribute("skillId"+j);   
			   											String sn=(String)request.getAttribute("skillName"+j);
			   											
			   											
		      	   		                               %>
								<td align="left" width="21%" class="sortableTD"><s:textfield
									name="<%="skillName"+j%>" value="<%=sn%>" readonly="true"
									size="40" /></td>
								<td width="2%" align="left" class="sortableTD"><img
									src="../pages/images/search2.gif" height="16" align="absmiddle"
									width="16" theme="simple"
									onclick="callSkill('<s:property value="<%=""+j%>"/>')"></td>

								<td width="20%" align="center" class="sortableTD"><input
									type="text" maxlength="5" name="skillExp"
									id="<%="skillExp"+j %>" value='<s:property value="skillExp" />'
									size="30" onkeypress="return numbersWithDot();" /></td>
								<td width="4%" align="center" class="sortableTD"><s:select
									name="skillSel" cssStyle="width:48" theme="simple"
									list="#{'':'','A':'And','R':'Or'}" /></td>


								<td width="3%" align="center" class="sortableTD"><input
									type="checkbox" align="center" class="checkbox" value="N"
									name=<%="confChkSkill"+j%> id="paraFrm_confChkSkill<%=j%>"
									onclick="callForSkill('<%=j%>');" /> <s:hidden
									name="skillDetCode" /> <input type="hidden"
									name="<%="hdeleteSkill"+j%>" id="paraFrm_hdeleteSkill<%=j%>" />
								<input type="hidden" name="<%="skillId"+j%>"
									id="paraFrm_skillId<%=j%>" value="<%=si%>" /></td>




							</tr>


						</s:iterator>


					</s:if>
					<s:else>


						<tr>
							<td width="5%" align="center" class="sortableTD"><%=++j%></td>
							<td width="9%" align="center" class="sortableTD"><s:select
								name="skillType" id="<%="skillType"+j %>" cssStyle="width:72"
								theme="simple"
								list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>


							<td width="21%" align="left" class="sortableTD"><s:textfield
								name="skillName1" id="paraFrm_skillName1" readonly="true"
								size="40" /></td>
							<td width="2%" align="left" class="sortableTD"><img
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="callSkill('<s:property value="<%=""+j%>"/>')"></td>

							<td width="20%" align="center" class="sortableTD"><input
								type="text" maxlength="5" name="skillExp"
								id="<%="skillExp"+j %>" value='<s:property value="skillExp" />'
								size="30" onkeypress="return numbersWithDot();" /></td>
							<td width="4%" align="center" class="sortableTD"><s:select
								name="skillSel" cssStyle="width:50" theme="simple"
								list="#{'':'','A':'And','R':'Or'}" /></td>


							<td width="3%" align="center" class="sortableTD"><input
								type="checkbox" class="checkbox" value="N" name="confChkSkill"
								id="paraFrm_confChkSkill<%=j%>"
								onclick="callForSkill('<%=j%>');" /> <input type="hidden"
								name="<%="hdeleteSkill"+j%>" id="paraFrm_hdeleteSkill<%=j%>" />
							<input type="hidden" name="skillId1" id="paraFrm_skillId1" /> <input
								type="hidden" name="hdeleteSkill1" id="hdeleteSkill1" /> <s:hidden
								name="skillDetCode" /></td>


						</tr>






					</s:else>


				</table>
				</td>
				</tr>

			</table>
			<%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
			</td>
		</tr>
		<!--End of Skill Details  -->

		<tr>
			<!-- Start of Domain Details -->
			<td colspan="3" width="100%">
			<table width="100%" border="0" class="formbg">

				<tr>
					<% int m=0,n=1; %>

					<td width="100%">
					<% 
                        try{
                        	%>
					<table width="100%" border="0">
						</td>
						</tr>

						<tr>
							<td><strong class="text_head">Domain/Functional
							Details : </strong></td>
							<td align="right" nowrap="nowrap"><input type="button"
								class="add" value="Add Row" onclick="addRowToTableDomain();" />
							<input type="button" class="delete" value="Remove"
								onclick="deleteDomain();" /></td>
						</tr>

						<td colspan="10" width="100%">

						<table border="0" id="tblDom" width="100%">
							<% int d=0; %>
							<tr>



								<td width="5%" valign="top" class="formth" nowrap="nowrap"
									align="center"><label class="set" name="sr" id="sr2"
									ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></td>
								<td width="10%" valign="top" class="formth"><label
									class="set" name="dtyp" id="dtyp"
									ondblclick="callShowDiv(this);"><%=label.get("dtyp")%></label></td>
								<td width="20%" valign="top" class="formth"><label
									class="set" name="dname" id="dname"
									ondblclick="callShowDiv(this);"><%=label.get("dname")%></label></td>
								<td width="2%" valign="top" class="formth">&nbsp;</td>
								<td width="20%" valign="top" class="formth"><label
									class="set" name="experience" id="experience1"
									ondblclick="callShowDiv(this);"><%=label.get("experience")%></label></td>
								<td width="4%" valign="top" class="formth"><label
									class="set" name="opt" id="opt2"
									ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
								<td width="3%" valign="top" class="formth"><input
									type="checkbox" class="checkbox" value="N" name="confChkDom"
									id="paraFrm_confChkDom" onclick="clickAllDom();" /></td>


							</tr>

							<s:if test="%{domFlag}">

								<s:iterator value="domList">
									<tr>
										<td width="5%" class="sortableTD" align="center"><%=++m %></td>
										<%
		      	   		                               
		      	   		                               
			   											String domain= (String)request.getAttribute("domName"+m);
							    						String code=(String)request.getAttribute("domId"+m);
						
										   										
			   											
		      	   		                               %>
										<td width="9%" class="sortableTD" align="center"><s:select
											name="domType" id="<%="domType"+m%>" cssStyle="width:72"
											theme="simple"
											list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>
										<td width="21%" align="left" class="sortableTD"><s:textfield
											name="<%="domName" +m%>" value="<%=domain %>" readonly="true"
											size="40" id="<%="paraFrm_domName"+m%>" /></td>


										<td width="2%" align="left" class="sortableTD"><img
											src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="callDom('<s:property value="<%=""+m%>"/>')"></td>

										<td width="20%" align="center" class="sortableTD"><s:textfield
											name="domExp" maxlength="5" id="<%="domExp"+m%>" size="30"
											onkeypress="return numbersWithDot();" /></td>

										<td width="4%" align="center" class="sortableTD"><s:select
											name="domSel" cssStyle="width:50" theme="simple"
											list="#{'':'','A':'And','R':'Or'}" /></td>


										<td width="3%" align="center" class="sortableTD"><input
											type="checkbox" class="checkbox" value="N"
											name="<%="domChk"+m%>" id="paraFrm_domChk<%=m%>"
											onclick="callForDom('<s:property value="<%=""+m%>"/>')" /> <input
											type="hidden" name="<%="hdeleteDom"+m%>"
											id="paraFrm_hdeleteDom<%=m%>" /> <input type="hidden"
											name="<%="domId"+m%>" id="paraFrm_domId<%=m%>"
											value="<%=code%>" /> <s:hidden name="domDetCode" /></td>

									</tr>
								</s:iterator>

							</s:if>
							<s:else>

								<tr>
									<td width="5%" align="center" class="sortableTD"><%=++d %></td>
									<td width="9%" align="center" class="sortableTD"><s:select
										name="domType" id="<%="domType"+d%>" cssStyle="width:72"
										theme="simple"
										list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>
									<td width="21%" align="left" class="sortableTD"><s:textfield
										name="domName1" id="paraFrm_domName1" readonly="true"
										size="40" /></td>


									<td width="2%" align="left" class="sortableTD"><img
										src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callDom('<s:property value="<%=""+d%>"/>')"></td>

									<td width="20%" align="center" class="sortableTD"><input
										type="text" name="domExp" maxlength="5" id="<%="domExp"+d%>"
										size="30" onkeypress="return numbersWithDot();" /></td>

									<td width="4%" align="center" class="sortableTD"><s:select
										name="domSel" cssStyle="width:50" theme="simple"
										list="#{'':'','A':'And','R':'Or'}" /></td>


									<td width="3%" align="center" class="sortableTD"><input
										type="checkbox" class="checkbox" value="N" name="domChk1"
										id="paraFrm_domChk1" onclick="callForDom('<%=d%>');" /> <input
										type="hidden" name="domId1" id="paraFrm_domId1" /> <input
										type="hidden" name="hdeleteDom1" id="paraFrm_hdeleteDom1" />
									<s:hidden name="domDetCode" /></td>

								</tr>

							</s:else>
						</table>

						<%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
						</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- End of Domain Details -->

			</table>
		<tr>
			<!-- Start of Certification Details -->
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<% int c=0; %>

					<td width="100%">
					<% 
                        try{
                        	%>
					<table width="100%" border="0">
						</td>
						</tr>

						<tr>
							<td><strong class="text_head">Certification Details
							:</strong></td>

							<td align="right"><input type="button" class="add"
								value="Add Row" onclick="addRowToTableCert();" /> <input
								type="button" class="delete" value="Remove"
								onclick="deleteRow();" /></td>
						</tr>

						</strong>
						<td colspan="10" width="100%">

						<table id="tblCert" width="100%">

							<tr>



								<td width="5%" valign="top" class="formth" nowrap="nowrap"
									align="center"><label class="set" name="sr" id="sr3"
									ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></td>
								<td width="10%" valign="top" class="formth" align="center"><label
									class="set" name="ctyp" id="ctyp"
									ondblclick="callShowDiv(this);"><%=label.get("ctyp")%></label></td>
								<td width="14%" valign="top" class="formth"><label
									class="set" name="cname" id="cname"
									ondblclick="callShowDiv(this);"><%=label.get("cname")%></label></td>
								<td width="14%" valign="top" class="formth"><label
									class="set" name="ciss" id="ciss"
									ondblclick="callShowDiv(this);"><%=label.get("ciss")%></label></td>
								<td width="14%" valign="top" class="formth"><label
									class="set" name="cvalid" id="cvalid"
									ondblclick="callShowDiv(this);"><%=label.get("cvalid")%></label></td>
								<td width="4%" valign="top" class="formth" align="center"><label
									class="set" name="opt" id="opt3"
									ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
								<td width="3%" valign="top" class="formth" align="center"><input
									type="checkbox" name="confChkCert" id="paraFrm_confChkCert"
									onclick="return clickAll();" /></td>


							</tr>

							<s:if test="%{certFlag}">

								<s:iterator value="certList">
									<tr>
										<td width="5%" align="center" class="sortableTD"><%=++c%></td>

										<td width="9%" align="center" class="sortableTD"><s:select
											name="certType" id="<%="certType"+c%>" cssStyle="width:72"
											theme="simple"
											list="#{'':'Select','E':'Essential','D':'Desirable'}" /> </select></td>
										<td width="15%" align="left" class="sortableTD"><input
											type="text" maxlength="50" name="certName"
											value="<s:property value="certName" />"
											id="<%="certName"+c%>" size="25" /></td>

										<td width="14%" align="left" class="sortableTD"><s:textfield
											name="certIssueBy" id="<%="certIssueBy"+c%>" maxlength="50"
											size="25" /></td>
										<td width="14%" align="left" class="sortableTD"><s:textfield
											name="certValid" id="<%="certValid"+c%>" maxlength="25"
											readonly="false" size="25" /></td>

										<td width="4%" align="center" class="sortableTD"><s:select
											name="certOption" cssStyle="width:50" theme="simple"
											list="#{'':'','A':'And','R':'Or'}" /> </select></td>



										<td width="3%" align="center" class="sortableTD"><input
											type="checkbox" class="checkbox" value="N"
											name="<%="certChk"+c%>" id="paraFrm_certChk<%=c%>"
											onclick="callForCert('<%=c%>');" /> <s:hidden
											name="certDetCode" /> <input type="hidden"
											name="<%="hdeleteCert"+c%>" id="paraFrm_hdeleteCert<%=c%>" />
										</td>


									</tr>
								</s:iterator>

							</s:if>
							<s:else>

								<tr>
									<td width="5%" align="center" class="sortableTD"><%=++c%></td>

									<td width="9%" align="center" class="sortableTD"><s:select
										name="certType" id="<%="certType"+c%>" cssStyle="width:72"
										theme="simple"
										list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>
									<td width="15%" align="left" class="sortableTD"><s:textfield
										maxlength="50" name="certName" id="<%="certName"+c%>"
										readonly="false" size="25" /></td>

									<td width="14%" align="left" class="sortableTD"><input
										type="text" name="certIssueBy" id="<%="certIssueBy"+c%>"
										maxlength="50" size="25" /></td>
									<td width="14%" align="left" class="sortableTD"><input
										type="text" name="certValid" id="<%="certValid"+c%>"
										maxlength="25" size="25" /></td>

									<td width="4%" align="center" class="sortableTD"><s:select
										name="certOption" cssStyle="width:50" theme="simple"
										list="#{'':'','A':'And','R':'Or'}" /></td>


									<td width="3%" align="center" class="sortableTD"><input
										type="checkbox" class="checkbox" name="certChk1"
										id="paraFrm_certChk1" id="" value="N"
										onclick="callForCert('<%=c%>');" /> <input type="hidden"
										name="hdeleteCert1" id="paraFrm_hdeleteCert1" /> <s:hidden
										name="certDetCode" /></td>



								</tr>

							</s:else>
						</table>



						<%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
						</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>

			<s:if test="apprvFlag">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td nowrap="nowrap"><strong>Approver Details :</strong></td>
					</tr>
					<tr>
						<td class="formth" width="10%"><b> <label class="set"
							name="sr" id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b>
						</td>
						<td class="formth" width="25%"><b><label class="set"
							name="apprvName" id="apprvName" ondblclick="callShowDiv(this);"><%=label.get("apprvName")%></label></b></td>
						<td class="formth" width="20%"><b><label class="set"
							name="apprvDate" id="apprvDate" ondblclick="callShowDiv(this);"><%=label.get("apprvDate")%></label></b></td>
						<td class="formth" width="15%"><b><label class="set"
							name="appstatus" id="appstatus1" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label></b></td>
						<td class="formth" width="30%"><b><label class="set"
							name="apprvRem" id="apprvRem" ondblclick="callShowDiv(this);"><%=label.get("apprvRem")%></label></b></td>

					</tr>
					<%
								n = 0;
								%>
					<s:iterator value="apprvList">
						<tr>
							<td class="sortableTD" width="10%" align="center">&nbsp;<%=++n%></td>
							<td class="sortableTD" width="25%">&nbsp;<s:property
								value="apprvName" /></td>
							<td class="sortableTD" width="20%">&nbsp;<s:property
								value="apprvDate" /></td>
							<td class="sortableTD" width="15%">&nbsp;<s:property
								value="apprvStat" /></td>
							<td class="sortableTD" width="30%">&nbsp;<s:property
								value="apprvRem" /></td>

						</tr>
					</s:iterator>
				</table>
			</s:if>
			
		<tr>
			<td width="70%" nowrap="nowrap"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /> <s:if
				test="%{offerAppointFlag}">
				<s:submit cssClass="token" action="EmployeeRequi_backToOfferAppoint"
					theme="simple" value='Back To %{createNewFlag}'
					onclick="return callView()" />
			</s:if></td>
			<td width="30%" align="right"><strong>Page 2 of 2</strong></td>
		</tr>
	</table>
</s:form>


<script>
function verifyandsendFun(){
  var status=document.getElementById('paraFrm_reqName').value;
  var reqStatus=document.getElementById('paraFrm_reqApprStatus').value;
  
  			if(status=="" || status=="null"){
	  			alert("Please save the record first.");
	  			return false;	
  			}else if(reqStatus=="A"){
	  			alert("Application already approved.So can't be send for approval.");
	  			return false;
  			}else if(reqStatus=="P"){
	  			alert("Application is pending.So can't be send for approval.");
	  			return false;
  			}else if(reqStatus=="O"){
	  			alert("Application is on hold.So can't be send for approval.");
	  			return false;
  			}if(!chkDup()){
				return false;;
			}else if(!chkDupSkill()){
				return false;
			}else if(!chkDupDom()){
				return false;
			
			}else if(!chkDupCert()){
			
				return false;
			}
  			
  			
  			else{	
  			
  			/*	var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
				document.getElementById('paraFrm').target = "wind";
				document.getElementById('paraFrm').action = "EmployeeRequi_sendForApproveInSecond.action";
				document.getElementById('paraFrm').submit();
				document.getElementById('paraFrm').target = "main";*/
  			
  			// document.getElementById('paraFrm').target="_blank";
  		 	 document.getElementById('paraFrm').action="EmployeeRequi_sendForApproveInSecond.action";
	    	 document.getElementById('paraFrm').submit();
	      //	  document.getElementById('paraFrm').target="main";
	        }	       
  
  return false;
  }
   function previousFun(){
  
   					document.getElementById("paraFrm").action="EmployeeRequi_getPreviousPage.action";
				    document.getElementById("paraFrm").submit();
				    return false;
   
   
   }
   
    function callForQuali(id)
	   {
	 	
	
	   if(document.getElementById('paraFrm_quaChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_hdeleteQuali'+id).value="Y";
	    
	   }
	   else{
	   document.getElementById('paraFrm_hdeleteQuali'+id).value="";
	   	
   		}
  }
   
   function callForQualiAll()
	   {
	 
			var tbl = document.getElementById('tblQuali');
		var rowLen = tbl.rows.length;
		if(document.getElementById('paraFrm_chkEmpAll').checked){
		 for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_quaChk'+i).checked = true;
			  document.getElementById('paraFrm_hdeleteQuali'+i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_quaChk'+i).checked =false;
			  document.getElementById('paraFrm_hdeleteQuali'+i).value="";
		  }
	   }	
	
  }
   
   function deleteQualification()
	{
	
		var tbl = document.getElementById('tblQuali');
		var rowLen = tbl.rows.length;
	 if(chkQuali()){
	 var con=confirm('Do you really want to remove the records ?');
	 if(con){
	    document.getElementById('paraFrm').action="EmployeeRequi_deleteQualification.action";
  		document.getElementById('paraFrm').submit();
	    } else{
	    		 for(i = 1; i < rowLen; i++){
			       document.getElementById('paraFrm_quaChk'+i).checked =false;
			       document.getElementById('paraFrm_hdeleteQuali'+i).value="";
			       document.getElementById('paraFrm_chkEmpAll').checked=false;
		      }
	    
	    // return true;
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
   
   
     function callForSkillAll()
  {
	 	  
		var tbl = document.getElementById('tblSkill');
		var rowLen = tbl.rows.length;
	if (document.getElementById("paraFrm_chkSkillAll").checked == true){
	for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById("paraFrm_confChkSkill"+idx).checked = true;
				 document.getElementById('paraFrm_hdeleteSkill'+idx).value="Y";
				
	    }

 }else{
	for (var idx = 1; idx < rowLen; idx++) {
	
	document.getElementById("paraFrm_confChkSkill"+idx).checked = false;
	document.getElementById('paraFrm_hdeleteSkill'+idx).value="";
     }
  }	 
		
	
  }
  
  function clickAllDom() {

var tbl = document.getElementById('tblDom');
var rowLen = tbl.rows.length;


if (document.getElementById("paraFrm_confChkDom").checked == true){
for (var idx = 1; idx < rowLen; idx++) {

document.getElementById("paraFrm_domChk"+idx).checked = true;
document.getElementById("paraFrm_hdeleteDom"+idx).value ="Y";

}

}else{
for (var idx = 1; idx < rowLen; idx++) {

document.getElementById("paraFrm_domChk"+idx).checked = false;
document.getElementById("paraFrm_hdeleteDom"+idx).value ="";
}

}
}
  
  
  
  
  
  
  
  
  
  
  
   function callForSkill(id)
	   {
	 	  
	
	   if(document.getElementById('paraFrm_confChkSkill'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_hdeleteSkill'+id).value="Y";
	   
	   }
	   else{
	   document.getElementById('paraFrm_hdeleteSkill'+id).value="";
	   	
   		}
  }
  
  function callForDom(id)	   {
 	
	
   if(document.getElementById('paraFrm_domChk'+id).checked == true)
   {	  
	    document.getElementById('paraFrm_hdeleteDom'+id).value="Y";
	
   }else{
   		document.getElementById('paraFrm_hdeleteDom'+id).value="";
	   	
   		}
  }


   function deleteDomain()
	{
	
		var tbl = document.getElementById('tblDom');
		var rowLen = tbl.rows.length;
		
		 if(chkDomain()){
		 var con=confirm('Do you really want to remove the records ?');
		 if(con){
		    document.getElementById('paraFrm').action="EmployeeRequi_deleteDomain.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		    	for (var idx = 1; idx < rowLen; idx++) {

					document.getElementById("paraFrm_domChk"+idx).checked = false;
					document.getElementById("paraFrm_hdeleteDom"+idx).value ="";
					document.getElementById("paraFrm_confChkDom").checked = false;
					}
		    
		     //return true;
		    }
		 }else {
		 	alert('Please select a record to remove');
		 	 return false;
		 }
	 
	}
  
  
  
  
  
  function chkDomain(){
	var tbl = document.getElementById('tblDom');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('paraFrm_domChk'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
  
  
     function deleteSkill()
	{
		var tbl = document.getElementById('tblSkill');
		var rowLen = tbl.rows.length;	
	
		 if(chkSkill()){
		 var con=confirm('Do you really want to remove the records ?');
		 if(con){
		    document.getElementById('paraFrm').action="EmployeeRequi_deleteSkill.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
			    	for (var idx = 1; idx < rowLen; idx++) {
		
					document.getElementById("paraFrm_confChkSkill"+idx).checked = false;
					document.getElementById('paraFrm_hdeleteSkill'+idx).value="";
					document.getElementById("paraFrm_chkSkillAll").checked=false;
	    			 }		
		    
		     //return true;
		    }
		 }else {
		 	alert('Please select one record to remove');
		 	 return false;
		 }
	 
	}
   
   function chkSkill(){
	var tbl = document.getElementById('tblSkill');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('paraFrm_confChkSkill'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
	
     
   
   function callSkill(id)
    {
	    
	      document.getElementById('paraFrm_rowId').value=id; 
	      callsF9(500,325,'EmployeeRequi_f9Skill.action'); 
     
     
   }
   
    function callForCert(id)
	   {
	 	  
	
	   if(document.getElementById('paraFrm_certChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_hdeleteCert'+id).value="Y";
	
	   }
	   else{
	   document.getElementById('paraFrm_hdeleteCert'+id).value="";
	   	
   		}
  }

     
function addRowToTableCert()
{
  var tbl = document.getElementById('tblCert');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
 
  //reorderRows(tbl, iteration);
  
 
  var cellLeft = row.insertCell(0);
  cellLeft.className='sortableTD';
  var textNode = document.createTextNode(iteration);
  cellLeft.align='center';
  cellLeft.appendChild(textNode);
  
  
  var cellSel = row.insertCell(1);
  cellSel.className='sortableTD';
  var sel = document.createElement('select');
  sel.name = 'certType' ;
  sel.id='certType'+iteration ;
  sel.cssClass='border2';
  sel.options[0] = new Option('Select','');
  sel.options[1] = new Option('Essential', 'E');
  sel.options[2] = new Option('Desirable', 'D');
  sel.cssStyle="width:72";
  cellSel.align='center';
  cellSel.appendChild(sel);
  
 
  
  
  
  var cell = row.insertCell(2);
  cell.className='sortableTD';
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'certName' ;
  ed.id= 'certName' +iteration;
  ed.size ='25';
  ed.maxLength='50';
  cell.align='left';
  
  cell.appendChild(ed);
  

 
	var cell3 = row.insertCell(3);
	cell3.className='sortableTD';
	var issue=  document.createElement('input');
	issue.size='25';
	issue.type='text';
	issue.name='certIssueBy';
	issue.id='certIssueBy'+iteration;
	issue.maxLength='50';
	cell3.align='left';

 	cell3.appendChild(issue);
  

  // right cell
  var cellRight = row.insertCell(4);
  cellRight.className='sortableTD';
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'certValid';
  el.id = 'certValid'+iteration;
  el.maxLength='25';
  cellRight.align='left';
  el.size = '25';
 
  cellRight.appendChild(el);
  
  // select cell
  var cellRightSel = row.insertCell(5);
  cellRightSel.className='sortableTD';
  var domsel = document.createElement('select');
 
  domsel.name = 'certOption' ;
  domsel.options[0] = new Option('', '');
  domsel.options[1] = new Option('And', 'A');
  domsel.options[2] = new Option('Or', 'R');
  domsel.cssStyle="width:72";
  cellRightSel.align='center';
  cellRightSel.appendChild(domsel);
  
  
  
   // select cell
  var cellLast = row.insertCell(6);
  cellLast.className='sortableTD';
  var domChk = document.createElement('input');

 
  domChk.type = 'checkbox';
  domChk.name = 'certChk'+iteration;
  domChk.id = 'paraFrm_certChk' + iteration;
  domChk.onclick=function(){
 			  if(document.getElementById('paraFrm_certChk'+iteration).checked == true)
	   {	
	    		document.getElementById('paraFrm_hdeleteCert'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('paraFrm_hdeleteCert'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
  cellLast.align='center';
  cellLast.appendChild(domChk);
   
 // var hiddenDel=row.insertCell(7);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteCert'+iteration;
  hid.id = 'paraFrm_hdeleteCert' + iteration;
  cellLast.appendChild(hid);
  //hiddenDel.appendChild(hid);
  
  //var hiddenDet=row.insertCell(8);
  var hidCode = document.createElement('input');
  hidCode.type = 'hidden';
  hidCode.name = 'certDetCode';
  cellLast.appendChild(hidCode);
 // hiddenDet.appendChild(hidCode);
  
	
  
   }
   
   
   
   
   function clickAll() {




var tbl = document.getElementById('tblCert');
var rowLen = tbl.rows.length;
if (document.getElementById("paraFrm_confChkCert").checked == true){
for (var idx = 1; idx < rowLen; idx++) {

document.getElementById("paraFrm_certChk"+idx).checked = true;
document.getElementById("paraFrm_hdeleteCert"+idx).value ="Y";

}

}else{
for (var idx = 1; idx < rowLen; idx++) {

document.getElementById("paraFrm_certChk"+idx).checked = false;
document.getElementById("paraFrm_hdeleteCert"+idx).value ="";
}

}
}
   
   
   
   
   function deleteRow() {
   
   
var checkedObjArray = new Array();
var cCount = 0;
   
var tbl = document.getElementById("tblCert");
var error = false;
if (document.getElementById("paraFrm_confChkCert").checked == false)
error = true;


var rowLen = tbl.rows.length-1;

for (var idx = 1; idx <=rowLen; idx++) {

if(document.getElementById("paraFrm_certChk"+idx).checked==true){

error=false;
//checkedObjArray[cCount] = tbl.tBodies[0].rows[idx];
//				cCount++;

	   }

	}

if (error == true) {
		alert ("Please select a record to remove");
		return false;
}
		var conf=confirm("Do you really want to remove the records ?");
	if(conf){

			     document.getElementById('paraFrm').action="EmployeeRequi_deleteCertification.action";
			     document.getElementById('paraFrm').submit();
	}else{
			for (var idx = 1; idx <=rowLen; idx++) {
				document.getElementById("paraFrm_certChk"+idx).checked=false;
				document.getElementById("paraFrm_hdeleteCert"+idx).value="";
				document.getElementById("paraFrm_confChkCert").checked=false; 
				
			}
	// return false;
	}


}




   
   



 
 
function addRowToTableDomain()
{
  var tbl = document.getElementById('tblDom');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
  
  // left cell
  var cellLeft = row.insertCell(0);
  cellLeft.className='sortableTD';
  var textNode = document.createTextNode(iteration);
  cellLeft.align='center';
  cellLeft.appendChild(textNode);
  
  
  var cellSel = row.insertCell(1);
  cellSel.className='sortableTD';
  var sel = document.createElement('select');
  sel.name = 'domType' ;
  sel.id = 'domType' + iteration;
  sel.options[0] = new Option('Select','');
  sel.options[1] = new Option('Essential', 'E');
  sel.options[2] = new Option('Desirable', 'D');
   sel.cssStyle="width:72";
  cellSel.align='center';
  cellSel.appendChild(sel);
  
 
  
  
  
   var cell = row.insertCell(2);
    cell.className='sortableTD';
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'domName' + iteration;
  ed.id = 'paraFrm_domName' + iteration;
  ed.size ='40';
  ed.readOnly="true";
  cell.align='left';	
  cell.appendChild(ed);
  

 
var cell3 = row.insertCell(3);
cell3.className='sortableTD';
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
 			callsF9(500,325,'EmployeeRequi_f9Domain.action');
 		
	//     	callsF9(500,325,'//EmployeeRequi_f9Domain.action?field=domName'+iteration); 
	     	
	     
	     	
};
 cell3.appendChild(img);
  

  // right cell
  var cellRight = row.insertCell(4);
  cellRight.className='sortableTD';
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'domExp';
  el.id='domExp'+iteration;
  el.onkeypress= function(){
		  		
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
  }
  el.size ='30';
  el.maxLength='5';
  
  cellRight.align='center';
 // el.onkeypress = keyPressTest;
  cellRight.appendChild(el);
  
  // select cell
  var cellRightSel = row.insertCell(5);
  cellRightSel.className='sortableTD';
  var domsel = document.createElement('select');
 
  domsel.name = 'domSel' ;
 // domsel.id = 'paraFrm_domSel' + iteration;
  domsel.options[0] = new Option('', '');
  domsel.options[1] = new Option('And', 'A');
  domsel.options[2] = new Option('Or', 'R');
   domsel.cssStyle="width:50";
  cellRightSel.align='center';
  cellRightSel.appendChild(domsel);
  
  
  
   // select cell
  var cellLast = row.insertCell(6);
  cellLast.className='sortableTD';
  var domChk = document.createElement('input');
 
   domChk.type = 'checkbox';
  domChk.name = 'domChk'+iteration;
  domChk.id = 'paraFrm_domChk'+iteration;
  domChk.onclick=function(){
 			  if(document.getElementById('paraFrm_domChk'+iteration).checked == true)
	   {	
	    		document.getElementById('paraFrm_hdeleteDom'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('paraFrm_hdeleteDom'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
  
  
  
  
  
 // domChk.id = 'paraFrm_domChk' + iteration;
 cellLast.align='center';
  cellLast.appendChild(domChk);
  
  
  //var hiddenDel=row.insertCell(7);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteDom'+iteration;
  hid.id = 'paraFrm_hdeleteDom' + iteration;
 // hiddenDel.appendChild(hid);
  cellLast.appendChild(hid);
  
  
  
 // var hiddenDomId=row.insertCell(8);
  var hidDomId = document.createElement('input');
  hidDomId.type = 'hidden';
  hidDomId.name = 'domId'+iteration;
  hidDomId.id = 'paraFrm_domId' + iteration;
  cellLast.appendChild(hidDomId);
 // hiddenDomId.appendChild(hidDomId);
  
 // var hiddenDomCode=row.insertCell(9);
  var hiddenDom = document.createElement('input');
  hiddenDom.type = 'hidden';
  hiddenDom.name = 'domDetCode';
  cellLast.appendChild(hiddenDom);
 // hiddenDomCode.appendChild(hiddenDom);
 
	
  
   }  


  function callDom(id)
    {

		
	    document.getElementById('paraFrm_rowId').value=id; 
	    callsF9(500,325,'EmployeeRequi_f9Dom.action'); 
     	// document.getElementById('paraFrm').target="_blank";
      
   }
   
 

function addRowToTableSkill()
{
  var tbl = document.getElementById('tblSkill');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
 
  // left cell
  var cellLeft = row.insertCell(0);
  cellLeft.className='sortableTD';
  var textNode = document.createTextNode(iteration);
  cellLeft.align='center';
  cellLeft.appendChild(textNode);
  
  
  var cellSel = row.insertCell(1);
  cellSel.className='sortableTD';
  var sel = document.createElement('select');
  sel.name = 'skillType' ;
  sel.id = 'skillType' + iteration;
 sel.options[0]=new Option('Select','');
  sel.options[1] = new Option('Essential', 'E');
  sel.options[2] = new Option('Desirable', 'D');
  sel.cssStyle="width:72";
  cellSel.align='center'; 
  cellSel.appendChild(sel);
  
 
  
  
  
   var cell = row.insertCell(2);
     cell.className='sortableTD'; 
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'skillName' + iteration;
  ed.id = 'paraFrm_skillName' + iteration;
  ed.size ='40';
  ed.readOnly="true";
	cell.align='left';
    cell.appendChild(ed);
  

 
var cell3 = row.insertCell(3);
cell3.className='sortableTD';
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
 			callsF9(500,325,'EmployeeRequi_f9SkillAction.action');
 		
	
	     	
	     
	     	
};
 cell3.appendChild(img);
  

  // right cell
  var cellRight = row.insertCell(4);
  cellRight.className='sortableTD';
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'skillExp';
  el.id = 'skillExp'+iteration;
  el.maxLength='5';
  el.size = '30';
  el.onkeypress= function(){
		  		
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
  }
  cellRight.align='center';
  cellRight.appendChild(el);
  
  // select cell
  var cellRightSel = row.insertCell(5);
  cellRightSel.className='sortableTD';
  var skillSelect = document.createElement('select');
 
  skillSelect.name = 'skillSel' ;
 // domsel.id = 'paraFrm_skillSel' + iteration;
  skillSelect.options[0] = new Option('', '');
  skillSelect.options[1] = new Option('And', 'A');
  skillSelect.options[2] = new Option('Or', 'R');
   skillSelect.cssStyle="width:50";
  cellRightSel.align='center';
  cellRightSel.appendChild(skillSelect);
  
  
  
   // select cell
  var cellLast = row.insertCell(6);
  cellLast.className='sortableTD';
  var skillChk = document.createElement('input');
 
   skillChk.type = 'checkbox';
  skillChk.name = 'confChkSkill'+iteration;
  skillChk.id = 'paraFrm_confChkSkill'+iteration;
  skillChk.onclick=function(){
 			  if(document.getElementById('paraFrm_confChkSkill'+iteration).checked == true)
	   {	
	    		document.getElementById('paraFrm_hdeleteSkill'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('paraFrm_hdeleteSkill'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
 
  cellLast.align='center';	
  cellLast.appendChild(skillChk);
  
  
 // var hiddenDel=row.insertCell(7);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteSkill'+iteration;
  hid.id = 'paraFrm_hdeleteSkill' + iteration;
   cellLast.appendChild(hid);
 // hiddenDel.appendChild(hid);
  
  
  
  //var hiddenSkill=row.insertCell(8);
  var hidSkillId = document.createElement('input');
  hidSkillId.type = 'hidden';
  hidSkillId.name = 'skillId'+iteration;
  hidSkillId.id = 'paraFrm_skillId' + iteration;
  cellLast.appendChild(hidSkillId);
 // hiddenSkill.appendChild(hidSkillId);
  
  
   // var hiddenDetCode=row.insertCell(9);
  var hiddenCode = document.createElement('input');
  hiddenCode.type = 'hidden';
  hiddenCode.name = 'skillDetCode';
  cellLast.appendChild(hiddenCode);
 // hiddenDetCode.appendChild(hiddenCode);
 
	
  
   }  
   
   
   function callQualification(id)
    {
    
      document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'EmployeeRequi_f9Quali.action'); 
     
     
    }
    
    
     function callSpecialization(id)
    {
   
      document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'EmployeeRequi_f9Speci.action'); 
     
     
    }
   
   
   
   
   function addRowToTableQualification()
{
  var tbl = document.getElementById('tblQuali');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  

  var row = tbl.insertRow(lastRow);
  

  // left cell
  var cellLeft = row.insertCell(0);
  var textNode = document.createTextNode(iteration);
  cellLeft.className='sortableTD';
  cellLeft.align='center';
  cellLeft.appendChild(textNode);
  
  
  var cellQualType = row.insertCell(1);
  var quaTyp = document.createElement('select');
 	quaTyp.cssStyle='width:80';
  quaTyp.name = 'hqualType' ;
  cellQualType.className='sortableTD';
  quaTyp.id= 'hqualType'+iteration;
  quaTyp.options[0] = new Option('Select','');
  quaTyp.options[1] = new Option('Essential','E');
  quaTyp.options[2] = new Option('Desirable','D');
  //cellQualType.size='80';
  cellQualType.align='center';
    
  //cellQualType.width='72%';//cssStyle="width:50"
  cellQualType.appendChild(quaTyp);
  
 
  var cellQualName = row.insertCell(2);
  cellQualName.className='sortableTD';  
  var quaName = document.createElement('input');
  quaName.type = 'text';
  quaName.name = 'qualificationName' + iteration;
  quaName.id = 'paraFrm_qualificationName' + iteration;
  quaName.size ='16';
  quaName.readOnly="true";
  
  cellQualName.appendChild(quaName);
  

 
var cellQuaNameImg = row.insertCell(3);
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
        	callsF9(500,325,'EmployeeRequi_f9Qualification.action'); 
     
 		
	
	     	
	     
	     	
};
 cellQuaNameImg.appendChild(img);
  

  // right cell
  var cellQualevel = row.insertCell(4);
  cellQualevel.className='sortableTD';
  var quaLvl = document.createElement('input');
  
  quaLvl.type = 'text';
  quaLvl.name = 'hqualiLevelName'+iteration;
  quaLvl.id =  'paraFrm_hqualiLevelName'+iteration;
  quaLvl.size ='15';
  quaLvl.readOnly="true";
  cellQualevel.align='left';
  cellQualevel.appendChild(quaLvl);
  
  
   var cellSpecializ = row.insertCell(5);
   cellSpecializ.className='sortableTD';
  var spl = document.createElement('input');
  
  spl.type = 'text';
  spl.name = 'hsplName'+iteration;
  spl.id =   'paraFrm_hsplName'+iteration;
  spl.size = '15';
  spl.readOnly="true";
  cellSpecializ.align='center';
  cellSpecializ.appendChild(spl);
  
  var cellQuaSplImg = row.insertCell(6);
  cellQuaSplImg.className='sortableTD';
var splImg=  document.createElement('img');
splImg.type='image';
splImg.src='../pages/images/search2.gif';
 splImg.height='16';
 splImg.align='right';
 splImg.width='16';
 splImg.id='img'+ iteration;
 splImg.theme='simple';
 splImg.onclick=function(){
 			
 			 document.getElementById('paraFrm_rowId').value=iteration; 
        	callsF9(500,325,'EmployeeRequi_f9Specialization.action'); 
     
 		
	
	     	
	     
	     	
};
 cellQuaSplImg.appendChild(splImg);
  
  
  var cellQuaCut = row.insertCell(7);
   cellQuaCut.className='sortableTD';
  var cut = document.createElement('input');
  
  cut.type = 'text';
  cut.name = 'hcutOff';
  cut.maxLength='5';
  cut.size = '10';
  cut.id= 'hcutOff'+iteration;
  cut.onkeypress= function(){
		  		
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
  }
  cellQuaCut.align='left';
  cellQuaCut.appendChild(cut);
  
  
  // select cell
  var cellQuaOpt = row.insertCell(8);
  cellQuaOpt.className='sortableTD';
  var quaOpt = document.createElement('select');
  quaOpt.name = 'sel' ;
   quaOpt.options[0] = new Option('', '');
  quaOpt.options[1] = new Option('And', 'A');
  quaOpt.options[2] = new Option('Or', 'R');
  quaOpt.cssStyle="width:50";
  cellQuaOpt.align='center';
  cellQuaOpt.appendChild(quaOpt);
  
  
  var cellQuaChk = row.insertCell(9);
  cellQuaChk.className='sortableTD';
  var qualiChk = document.createElement('input');
 
   qualiChk.type = 'checkbox';
  qualiChk.name = 'paraFrm_quaChk'+iteration;
  qualiChk.id = 'paraFrm_quaChk'+iteration;
  qualiChk.onclick=function(){
 			  if(document.getElementById('paraFrm_quaChk'+iteration).checked == true)
	   {	
	    		document.getElementById('paraFrm_hdeleteQuali'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('paraFrm_hdeleteQuali'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
 
  cellQuaChk.align='center';
  cellQuaChk.appendChild(qualiChk);
  
   var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'paraFrm_hdeleteQuali'+iteration;
  hid.id = 'paraFrm_hdeleteQuali' + iteration;
   cellQuaChk.appendChild(hid);
   
   var hidQualiId = document.createElement('input');
  hidQualiId.type = 'hidden';
  hidQualiId.name = 'qualificationId'+iteration;
  hidQualiId.id = 'paraFrm_qualificationId'+iteration;
  cellQuaChk.appendChild(hidQualiId);
  
  var hiddenSpl= document.createElement('input');
  hiddenSpl.type = 'hidden';
  hiddenSpl.name = 'hsplId'+iteration;
  hiddenSpl.id = 'paraFrm_hsplId'+iteration;
  cellQuaChk.appendChild(hiddenSpl);
   
   
   var hiddenCode= document.createElement('input');
  hiddenCode.type = 'hidden';
  hiddenCode.name = 'quaDetCode';
   cellQuaChk.appendChild(hiddenCode);
   
   var hiddenQuaName= document.createElement('input');
  hiddenQuaName.type = 'hidden';
  hiddenQuaName.name = 'qname';
  hiddenQuaName.id='paraFrm_qname'+iteration;
   cellQuaChk.appendChild(hiddenQuaName);
   
  var hiddenSpName= document.createElement('input');
  hiddenSpName.type = 'hidden';
  hiddenSpName.name = 'spname';
  hiddenSpName.id='paraFrm_spname'+iteration;
  cellQuaChk.appendChild(hiddenSpName);
 /* var hiddenDel=row.insertCell(10);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'paraFrm_hdeleteQuali'+iteration;
  hid.id = 'paraFrm_hdeleteQuali' + iteration;
  hiddenDel.appendChild(hid);
  
  
  
  var hiddenQuali=row.insertCell(11);
  var hidQualiId = document.createElement('input');
  hidQualiId.type = 'hidden';
  hidQualiId.name = 'qualificationId'+iteration;
  hidQualiId.id = 'paraFrm_qualificationId'+iteration;
  hiddenQuali.appendChild(hidQualiId);
  
  var hiddenSplCell=row.insertCell(12);
  var hiddenSpl= document.createElement('input');
  hiddenSpl.type = 'hidden';
  hiddenSpl.name = 'hsplId'+iteration;
  hiddenSpl.id = 'paraFrm_hsplId'+iteration;
  hiddenSplCell.appendChild(hiddenSpl);
  //}
  
  
   var hiddenDetCode=row.insertCell(13);
  var hiddenCode= document.createElement('input');
  hiddenCode.type = 'hidden';
  hiddenCode.name = 'quaDetCode';
  hiddenDetCode.appendChild(hiddenCode);
  
  var hiddenQname=row.insertCell(14);
  var hiddenQuaName= document.createElement('input');
  hiddenQuaName.type = 'hidden';
  hiddenQuaName.name = 'qname';
  hiddenQuaName.id='paraFrm_qname'+iteration;
  hiddenQname.appendChild(hiddenQuaName);
  
  var hiddenSpename=row.insertCell(15);
  var hiddenSpName= document.createElement('input');
  hiddenSpName.type = 'hidden';
  hiddenSpName.name = 'spname';
  hiddenSpName.id='paraFrm_spname'+iteration;
  hiddenSpename.appendChild(hiddenSpName);*/
 
  
   }  
   


function deleteFun(){
	
		var conf=confirm("Do you really want to delete this record ?");
		if(conf){
			document.getElementById("paraFrm").action="EmployeeRequi_delete.action";
	   		document.getElementById("paraFrm").submit();
				
		}else{
		
		     return false;
		}
	
	
	}


function saveandpreviousFun(){


			if(document.getElementById("paraFrm_updateSecondFlag").value=="true"){
			
						if(!chkDup()){
						return false;;
						}else if(!chkDupSkill()){
						return false;
						}else if(!chkDupDom()){
						return false;
						}else if(!chkDupCert()){
						return false;
						}else{	
								document.getElementById("paraFrm").action="EmployeeRequi_updateSecondPage.action";
	    	    			    document.getElementById("paraFrm").submit();
						}
			
			    }else{
					
					if(!chkDup()){
						return false;;
						}else if(!chkDupSkill()){
						return false;
						}else if(!chkDupDom()){
						return false;
						}else if(!chkDupCert()){
						return false;
						}else{
						 document.getElementById("paraFrm").action="EmployeeRequi_saveAndPrevious.action";
	    	    		 document.getElementById("paraFrm").submit();
	    	    	}	
	    	    
			}
return false;
}

function cancelFun(){
	if(document.getElementById("paraFrm_cancel1").value=="true"){
		if(document.getElementById('source').value=='mymessages') {
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		} else if(document.getElementById('source').value=='myservices') {
			document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		} else {
			document.getElementById("paraFrm").action="EmployeeRequi_cancel1.action";
		}
	    document.getElementById("paraFrm").submit();
	} else if(document.getElementById("paraFrm_cancelFrth").value=="true") {
		if(document.getElementById('source').value=='mymessages') {
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		} else if(document.getElementById('source').value=='myservices') {
			document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		} else {
			document.getElementById("paraFrm").action="EmployeeRequi_cancelFrth.action";
		}	
	   	document.getElementById("paraFrm").submit();
	} else {
		if(document.getElementById('source').value=='mymessages') {
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		} else if(document.getElementById('source').value=='myservices') {
			document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		} else {
			document.getElementById("paraFrm").action="EmployeeRequi_cancelSecond.action";
		}	
	   	document.getElementById("paraFrm").submit();
	}
}
	

function saveFun(){
	
	if(document.getElementById("paraFrm_updateSecondFlag").value=="true"){
	
	
	//alert(!chkDup()+''+!chkDupSkill()+''+!chkDupDom()+''+!chkDupCert());
	
			if(!chkDup()){
				return false;;
			}else if(!chkDupSkill()){
				return false;
			}else if(!chkDupDom()){
				return false;
			
			}else if(!chkDupCert()){
			
				return false;
			}
			
			else{
				
					document.getElementById("paraFrm").action="EmployeeRequi_updateSecond.action";
				    document.getElementById("paraFrm").submit();
				   
			}	    
	       
		
		}else{
		
			if(!chkDup()){
				return false;;
			}else if(!chkDupSkill()){
				return false;
			}else if(!chkDupDom()){
				return false;
			
			}else if(!chkDupCert()){
			
				return false;
			}else{
	
				 document.getElementById("paraFrm").action="EmployeeRequi_saveSecond.action";
	   			 document.getElementById("paraFrm").submit();
	   	   }
	    }
	  return false;
	}
function editFun(){
		var status=document.getElementById('hiddenApproveStatus').value;
		if(status=="Pending"){
			alert("Application is already pending.So can't be edited.");
			return false;
		}else if(status=="Approved"){
			alert("Application is already approved.So can't be edited.");
			return false;
		}else if(status=="On Hold")	{
			alert("Application is On Hold.So can't be edited.");
			return false;
		
		}
		
		document.getElementById("paraFrm").action="EmployeeRequi_editSec.action";
	    document.getElementById("paraFrm").submit();
	    return false;
}




function chkDup(){



var tbl = document.getElementById('tblQuali');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var name= document.getElementById('paraFrm_qualificationName'+i).value;
 		 		
 		 		var specialise = document.getElementById('paraFrm_hsplName'+i).value;
 		 		var cut= document.getElementById('hcutOff'+i).value;
 		 	  
 		 		var type= document.getElementById('hqualType'+i).value;
 		 		var cutmark=document.getElementById('hcutOff'+i).value;
 		
 		 	var cnt=0;
 		 		for(var d=i+1;d<lastRow;d++){
	 		 			
	 		 			var dupQua= document.getElementById('paraFrm_qualificationName'+d).value;
 		 				var dupSpl = document.getElementById('paraFrm_hsplName'+d).value;
	 		 		
	 		 		if(dupQua == name){
	 		 		cnt++;
	 		 		}
	 	
 		 		}
 		 		
 		 	if(!(type==""))	{
 		 		if(name==""){
 		 			alert("Please select the "+ document.getElementById('qabbr').innerHTML.toLowerCase()+" in Qualification Details.");
 		 			return false;
 		 		   }	
 		  	}else if(!(cut==""))	{
 		 		if(name==""){
 		 		alert("Please select the "+ document.getElementById('qabbr').innerHTML.toLowerCase()+" in Qualification Details.");
 		 			return false;
 		 		   }	
 		  	}else if(!(specialise==""))	{
 		 		if(name==""){
 		 			alert("Please select the "+ document.getElementById('qabbr').innerHTML.toLowerCase()+" in Qualification Details.");
 		 			return false;
 		 		   }	
 		  	} 
 			
 		 	
 		 	 if(!(valCTC('hcutOff'+i,'mrks'))){
 		     	return false;
 		     } 	
 		
 		
 	 		
 	if(cutmark >100){
 		  	
					alert(document.getElementById('mrks').innerHTML.toLowerCase()+" should not be more than 100.");
					return false; 		  	
 		  	}
 		  	
 		 } 	
 		 			
 	return true;
 		
 		 
 		
 }		
 
  
 function chkDupSkill(){

var tbl = document.getElementById('tblSkill');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var skill= document.getElementById('paraFrm_skillName'+i).value;
 		 		var type=document.getElementById('skillType'+i).value;
 		 		var exp=document.getElementById('skillExp'+i).value;
 		 		
 		 		
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupSkill= document.getElementById('paraFrm_skillName'+d).value;
 		 			
	 		 		
	 		 		if(dupSkill == skill){
	 		 			cnt++;
	 		 		}
	 		 		//if(cnt > 1){
	 		 		//	alert('Duplicate records found in Skill Details.');
	 		 		//	return false;
	 		 		//}
 		 		}
 		 	if(!(type=="")){
 		 			if(skill==""){
 		 				alert("Please select the "+ document.getElementById('sname').innerHTML.toLowerCase()+" in Skill Details.");
 		 		 	return false;
 		            }
 			}else if(!(exp=="")){
 					if(skill==""){
 		 				alert("Please select the "+ document.getElementById('sname').innerHTML.toLowerCase()+" in Skill Details.");
 		 		 	return false;
 		            }
 			}
 			
 		 if(!(valCTC('skillExp'+i,'experience'))){
 		     	return false;
 		     }  	
 				
 		}
 	
 		
 	return true;	 
 		
 }			
 
 
 
 function chkDupDom(){

var tbl = document.getElementById('tblDom');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var dom= document.getElementById('paraFrm_domName'+i).value;
 		 		var domainType=document.getElementById('domType'+i).value;
 		 		var Exp=document.getElementById('domExp'+i).value;
 		 	
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupDom= document.getElementById('paraFrm_domName'+d).value;
 		 			
	 		 		
	 		 		if(dupDom == dom){
	 		 			cnt++;
	 		 		}
	 		 		//if(cnt > 1){
	 		 		//	alert('Duplicate records found in Domain details.');
	 		 		//	return false;
	 		 		//}
 		 		}
 		if(!(domainType=="")){
 		 		if(dom==""){
 					alert("Please select the "+ document.getElementById('dname').innerHTML.toLowerCase()+" in Domain/Functional Details.");
 		 			return false;
 		 		}	
 		 }else if(!(Exp=="")){
 		 		
 		 		if(dom==""){
 					alert("Please select the "+ document.getElementById('dname').innerHTML.toLowerCase()+" in Domain/Functional Details.");
 		 			return false;
 		 		}	
 		 	
 		 	}
 		 	
 		if(!(valCTC('domExp'+i,'experience'))){
 		     	return false;
 		     } 
 		}
 	
 		
 		 
 return true;		
 }		
 
 
 function chkDupCert(){

var tbl = document.getElementById('tblCert');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var cert= document.getElementById('certName'+i).value;
 		 		var certiType=document.getElementById('certType'+i).value;
 		 	
 		 		var certiIssue=document.getElementById('certIssueBy'+i).value;
 		 	
 		 		var certiVal=document.getElementById('certValid'+i).value;
 		 	
 		 		
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupCert= document.getElementById('certName'+d).value;
 		 			
	 		 		
	 		 		if(dupCert == cert){
	 		 			cnt++;
	 		 		}
	 		 		//if(cnt > 1){
	 		 		//	alert('Duplicate records found in Certification Details.');
	 		 		//	return false;
	 		 		//}
 		 		}
 		 	if(!(certiType=="")){
 		 		if(cert==""){
 					alert("Please enter the "+ document.getElementById('cname').innerHTML.toLowerCase()+" in Certification Details.");
 		 			return false;
 		 		}	
 		 	}else if(!(certiIssue=="")){
 		 		
 		 		if(cert==""){
 				alert("Please enter the "+ document.getElementById('cname').innerHTML.toLowerCase()+" in Certification Details.");
 		 			return false;
 		 		}	
 		 	
 		 	}else if(!(certiVal=="")){
 		 	
 		 			if(cert==""){
 						alert("Please enter the "+ document.getElementById('cname').innerHTML.toLowerCase()+" in Certification Details.");
 		 				return false;
 		 			}	
 			}	
 		 	
 		 	
 		}
 	
 		
 return true;		 
 		
 }				
  function exportpdfreportFun(){
   		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="EmployeeRequi_reportFun.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
   
   }
    function exporttextreportFun(){
   		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="EmployeeRequi_reportFunText.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
   
   }
   
   
    function valCTC(ctcfieldname,ctclabelname)
	{
	
	var amount=document.getElementById(ctcfieldname).value;
	var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();
	
		
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only one dot is allowed in "+amountlabel+" field.");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		 
		 }	
		 }
		  return true;
		 }
   
   </script>

