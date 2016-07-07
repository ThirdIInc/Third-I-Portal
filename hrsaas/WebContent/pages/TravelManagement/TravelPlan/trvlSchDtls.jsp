
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TrvlSchl" validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">


		<s:hidden name="navStatus" />
		<s:hidden name="AppID" />
		<s:hidden name="JourneyId" />
		<s:hidden name="rowId" />
		<s:hidden name="empId" />
		


		<s:hidden name="policyId" />
		<s:hidden name="validTrMode" />
		<s:hidden name="validHotelType" />
		<s:hidden name="validRoomType" />
		<s:hidden name="gradeId" />



		<s:hidden name="travelAppId" />
		<s:hidden name="trAppId" />
		<s:hidden name="stat" />
		<s:hidden name="empGrade" />
		<s:hidden name="grade" />
		

		<tr>
			<td colspan="3">
			<table width="99%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel Schedule </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:submit cssClass="save"
						action="TrvlSchl_save" value=" Save " onclick=" return add();"
						theme="simple" /> <s:submit action="TrvlSchl_finalizeSchedule"
						value="Finalise Schedule" theme="simple" onclick=" return add();"   cssClass="token"/>
					<s:submit value="  Back" theme="simple" onclick=" callBack();"  cssClass="token"/></td>



					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>








		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">

				<tr>
					<td><strong><label class="set" id="tms.trvlEmpInfo"
						name="tms.trvlEmpInfo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlEmpInfo")%></label></strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td colspan="3"><s:label name="empToken" theme="simple"
						value="%{empToken}" /> &nbsp;&nbsp; <s:label name="employeeName"
						theme="simple" value="%{employeeName}" /></td>

				</tr>


				<tr>
					<td width="10%" class="formtext" height="22"><label
						class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="25%"><s:label name="brnchName" theme="simple"
						value="%{brnchName}" /></td>

					<td width="10%" class="formtext"><label class="set"
						id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td width="25%"><s:label name="deptName" theme="simple"
						value="%{deptName}" /></td>

				</tr>


				<tr>


					<td width="10%" class="formtext" height="22"><label
						class="set" id="tms.trvlAppDate" name="tms.trvlAppDate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlAppDate")%></label>:</td>
					<td width="25%"><s:label name="applDate" theme="simple"
						value="%{applDate}" /></td>


					<td width="10%" class="formtext"><label class="set" id="grade"
						name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>


					<td width="25%"><s:label name="grdName" theme="simple"
						value="%{grdName}" /> &nbsp;&nbsp; <input type="button"
						value="Travel Policy" class="token" onclick="callTravelPolicy();"></td>



				</tr>


				<tr>

					<td width="10%" class="formtext" height="22"><label
						class="set" id="tms.trvlAge" name="tms.trvlAge"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlAge")%></label>:</td>
					<td width="25%"><s:label name="dob" theme="simple"
						value="%{dob}" /></td>
					<td width="10%" class="formtext"><label class="set"
						id="tms.trvlConNo" name="tms.trvlConNo"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlConNo")%></label>:</td>
					<td width="25%"><s:label name="mobile" theme="simple"
						value="%{mobile}" /></td>
				</tr>




				<tr>

					<td width="10%" class="formtext" height="22"><label
						class="set" id="tms.trvlTrvlReqName" name="tms.trvlTrvlReqName"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTrvlReqName")%></label>:</td>
					<td width="25%"><s:label name="trvlRequest" theme="simple"
						value="%{trvlRequest}" /></td>
					<td width="10%" class="formtext"><label class="set"
						id="tms.trvlTourPurpose" name="tms.trvlTourPurpose"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTourPurpose")%></label>:</td>
					<td width="25%"><s:label name="tourPurpose" theme="simple"
						value="%{tourPurpose}" /></td>

				</tr>

				<tr>
					<td width="10%" class="formtext"><label class="set"
						id="tms.trvlTourStrtDate" name="tms.trvlTourStrtDate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTourStrtDate")%></label>:</td>
					<td width="25%"><s:label name="tourStrtDate" theme="simple"
						value="%{tourStrtDate}" /></td>

					<td width="10%" class="formtext"><label class="set"
						id="tms.trvlTourEndDate" name="tms.trvlTourEndDate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTourEndDate")%></label>:</td>
					<td width="25%"><s:label name="tourEndDate" theme="simple"
						value="%{tourEndDate}" /></td>
				</tr>




			</table>
			</td>
		</tr>







		<tr>
			<td width="100%">
			<table width="100%" class="formbg">

				<s:if test="jourBkFlg">
					<tr>
						<td><strong><label class="set" id="tms.trvlBookDtls"
							name="tms.trvlBookDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlBookDtls")%></label></strong></td>
					</tr>

					<!-- iterator for Booking Details  -->

					<tr>
						<td width="100%">
						<div style="overflow-x: auto; width: 765; overflow-y: auto;">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formth" width="5%" nowrap="nowrap"><label
									class="set" id="tms.trvlScheduleSrNo"
									name="tms.trvlScheduleSrNo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="tms.trvlSourDest" name="tms.trvlSourDest"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlSourDest")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="tms.trvlTravelDate" name="tms.trvlTravelDate"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTravelDate")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="tms.trvlTime" name="tms.trvlTime"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTime")%></label>(HH24:MI)</td>
								<td class="formth" width="12%"><label class="set"
									id="tms.trvlModeAndCls" name="tms.trvlModeAndCls"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlModeAndCls")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="tms.BusTrainFltNo" name="tms.BusTrainFltNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.BusTrainFltNo")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="tms.TicktNo" name="tms.TicktNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TicktNo")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="tms.cost" name="tms.cost" ondblclick="callShowDiv(this);"><%=label.get("tms.cost")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="tms.TicktDtls" name="tms.TicktDtls"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TicktDtls")%></label></td>
							</tr>

							<s:if test="noData">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
							</s:if>


							<%!int i = 0;%>
							<%
							int k = 1;
							%>

							<s:iterator value="travelSchList">


								<tr>
									<td valign="top" class="sortableTD" nowrap="nowrap"><%=k%>
									<s:hidden name="appJournDtlFrom" /> <s:hidden
										name="appJournDtlTo" /> <s:hidden name="appJournDtlId" /></td>

									<td colspan="1" valign="top" class="sortableTD" nowrap="nowrap"><s:property
										value="sourceAndDest" /></td>


									<td valign="top" class="sortableTD" nowrap="nowrap"><input
										type="text" name="travelDate" id="travelDate<%=k%>"
										value="<s:property value="travelDate"/>" size="10"
										onkeypress="return numbersWithHiphen();"
										onblur="return validateDate('travelDate<%=k%>','tms.trvlTravelDate');"
										maxlength="10"
										ondblclick="javascript:NewCal('<%="travelDate"+k%>','DDMMYYYY');">
									<td valign="top" class="sortableTD" nowrap="nowrap"><input
										type="text" name="travelTime" id="travelTime<%=k%>"
										theme="simple" size="8"
										value="<s:property value="travelTime"/>"
										onblur="return validateTime('travelTime<%=k%>','tms.trvlTime');"
										maxlength="5" onkeypress="return numbersWithColon();" /></td>





									<%
										String trModeClass = (String) request.getAttribute("trMode" + k);
										String trModeClassId = (String) request.getAttribute("trModeId" + k); 
										String uploadFileName = (String) request.getAttribute("paraFrm_uploadFileName" + k); 
									%>


									<td valign="top" class="sortableTD" nowrap="nowrap"><s:hidden
										name="<%="travelMdAndClsId"+k%>" value="<%=trModeClassId%>" />
									<input type="text" name="<%="travelMdAndCls"+k %>"
										id="<%="paraFrm_travelMdAndCls"+k %>" value="<%=trModeClass%>"
										size="10" readonly="readonly" /> <img
										src="../pages/images/search2.gif" align="absmiddle"
										theme="simple"
										onclick="callJourneyMode('<s:property value="<%=""+k%>"/>')" />
									</td>



									<td valign="top" class="sortableTD" nowrap="nowrap"><input
										type="text" name="trvlVehNo"  id="<%="trvlVehNo"+k %>" theme="simple" size="8"
										value='<s:property
										value="trvlVehNo" />'
									    maxlength="15" onkeypress="return allCharacters();" /></td>

									<td valign="top" class="sortableTD" nowrap="nowrap"><input
										type="text" name="ticktNo" id="<%="ticktNo"+k %>"
										value='<s:property
										value="ticktNo" />' size="5"
										maxlength="15" onkeypress="return allCharacters();" /></td>

									<td valign="top" class="sortableTD" nowrap="nowrap"><input
										type="text" name="ticktCost" id="<%="ticktCost"+k %>" size="8"
										value='<s:property
										value="ticktCost" />'
										onkeyup="calculate();" onkeypress="return numbersOnly();"
										align="right" maxlength="8" /></td>


									<td valign="top" class="sortableTD" nowrap="nowrap"><input
										type="text" name="uploadFileName" size="8"
										id="<%="paraFrm_uploadFileName"+k %>"
										value="<%=uploadFileName%>" readonly="readonly" /> <input name="Browse"
										type="button" class="token" value="Upload"
										onclick="uploadFile('<%="paraFrm_uploadFileName"+k%>')" /></td>


								</tr>


								<%
								k++;
								%>
							</s:iterator>
							<%
							i = k;
							%>



							<tr>
								<td colspan="7" align="right"><label class="set"
									id="tms.TotTravelCost" name="tms.TotTravelCost"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TotTravelCost")%></label>
								:</td>
								<td colspan="1">&nbsp;<input type="text" name="totTrvCost"
									value='<s:property value="totTrvCost"/>'
									id="paraFrm_totTrvCost" size="8" readonly="readonly"
									align="right"></td>

							</tr>

						</table>
						</div>
						</td>

					</tr>

					<!-- iterator for Booking Details end -->


				</s:if>
				<s:if test="localConFlg">

					<tr>
						<td><strong><label class="set"
							id="tms.trvlLocalConvDtls" name="tms.trvlLocalConvDtls"
							ondblclick="callShowDiv(this);"><%=label.get("tms.trvlLocalConvDtls")%></label></strong></td>
					</tr>

					<!-- iterator for Local Conveyance Details  -->


					<tr>
						<td>
						<div style="overflow-x: auto; width: 765;">

						<table width="100%" class="formbg">
							<tr>
								<td class="formth" nowrap="nowrap"><label class="set"
									id="tms.trvlScheduleSrNo1" name="tms.trvlScheduleSrNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
								<td class="formth"><label class="set" id="city" name="city"
									ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
								<td class="formth"><label class="set" id="tms.Source"
									name="tms.Source" ondblclick="callShowDiv(this);"><%=label.get("tms.Source")%></label></td>
								<td class="formth"><label class="set" id="tms.Date"
									name="tms.Date" ondblclick="callShowDiv(this);"><%=label.get("tms.Date")%></label>(DD-MM-YYYY)</td>
								<td class="formth"><label class="set" id="tms.TravelMode"
									name="tms.TravelMode" ondblclick="callShowDiv(this);"><%=label.get("tms.TravelMode")%></label></td>
								<td class="formth"><label class="set" id="tms.vehNum"
									name="tms.vehNum" ondblclick="callShowDiv(this);"><%=label.get("tms.vehNum")%></label></td>
								<td class="formth"><label class="set" id="tms.ConPer"
									name="tms.ConPer" ondblclick="callShowDiv(this);"><%=label.get("tms.ConPer")%></label></td>
								<td class="formth"><label class="set" id="tms.ConNum"
									name="tms.ConNum" ondblclick="callShowDiv(this);"><%=label.get("tms.ConNum")%></label></td>
								<td class="formth"><label class="set" id="tms.PickUpPer"
									name="tms.PickUpPer" ondblclick="callShowDiv(this);"><%=label.get("tms.PickUpPer")%></label>()</td>
								<td class="formth"><label class="set" id="tms.PicUpTime"
									name="tms.PicUpTime" ondblclick="callShowDiv(this);"><%=label.get("tms.PicUpTime")%></label>(HH24:MI)</td>
								<td class="formth"><label class="set" id="tms.PicUpPlace"
									name="tms.PicUpPlace" ondblclick="callShowDiv(this);"><%=label.get("tms.PicUpPlace")%></label></td>
								<td class="formth"><label class="set" id="tms.TariffCost"
									name="tms.TariffCost" ondblclick="callShowDiv(this);"><%=label.get("tms.TariffCost")%></label></td>
							</tr>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
							</s:if>






							<%!int i1 = 0;%>
							<%
							int k1 = 1;
							%>



							<s:iterator value="localConvDtls">
								<tr>
									<td valign="top" class="sortableTD"><%=k1%> <s:hidden
										name="locConCity" /> <s:hidden name="locConSource" /> <s:hidden
										name="locConCityId" /> <s:hidden name="locConDate" /> <s:hidden
										name="locConTrvlMod" /> <s:hidden name="locConId" /></td>
									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><s:property value="locConCity" /></td>

									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text"
										name="locConSource12" id="locConSource<%=k1%>"
										value="<s:property value="locConSource" />" size="8"
										maxlength="20"></td>







									<td width="10%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text" name="locConDate12"
										id="locConDate<%=k1%>"
										value="<s:property value="locConDate"/>" size="8"
										onkeypress="return numbersWithHiphen();"
										onblur="return validateDate('locConDate<%=k1%>','tms.Date');"
										maxlength="10"
										ondblclick="javascript:NewCal('<%="locConDate"+k1%>','DDMMYYYY');">

									</td>

									<%
												String locTravelMod = (String) request.getAttribute("LocTrvlMod"
												+ k1);
										String locTravelModId = (String) request
												.getAttribute("LocTrvlModId" + k1);
									%>

									<td width="6%" nowrap="nowrap" valign="top" class="sortableTD"><s:hidden
										name="<%="locConTrvlModId"+k1%>" value="<%=locTravelModId%>" />
									<input type="text" name="<%="locConTrvlMod"+k1 %>"
										id="<%="paraFrm_locConTrvlMod"+k1 %>"
										value="<%=locTravelMod%>" size="8" readonly="readonly" /> <img
										src="../pages/images/search2.gif" align="absmiddle"
										theme="simple"
										onclick="callLocJourneyMode('<s:property value="<%=""+k1%>"/>')" />
									</td>


									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text"
										name="locConTrvlModNum"  id="<%="locConTrvlModNum"+k1%>" theme="simple"
										value='<s:property
										value="locConTrvlModNum" />'
										size="8" maxlength="15" onkeypress="return allCharacters();" /></td>

									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text"
										name="locConTrvlConPer" theme="simple"
										value='<s:property	value="locConTrvlConPer"/>'
										size="8" maxlength="30"
										onkeypress="return charactersOnly();" /></td>

									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text"
										name="locConTrvlConNum" theme="simple"
										value='<s:property
										value="locConTrvlConNum" />'
										size="8" maxlength="12" onkeypress="return numbersOnly();" /></td>

									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text"
										name="locConTrvlPcPer" theme="simple"
										value='<s:property
										value="locConTrvlPcPer"/>'
										size="8" maxlength="30"
										onkeypress="return charactersOnly();" /></td>

									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text"
										name="locConTrvlPcTime" id="locConTrvlPcTime<%=k1%>"
										theme="simple"
										value='<s:property
										value="locConTrvlPcTime" />'
										size="8" onkeypress=" return numbersWithColon();"
										onblur="return validateTime('locConTrvlPcTime<%=k1%>','tms.PicUpTime');"
										maxlength="5" /></td>




									<td width="15%" nowrap="nowrap" colspan="1" valign="top"
										class="sortableTD"><input type="text"
										name="locConTrvlPcPlace" theme="simple"
										value='<s:property
										value="locConTrvlPcPlace" />'
										size="8" maxlength="25" onkeypress="return allCharacters();" /></td>

									<td valign="top" class="sortableTD"><input type="text"
										name="locConTrvlPcToriffCst"
										id="<%="locConTrvlPcToriffCst"+k1 %>" theme="simple" size="8"
										value='<s:property
										value="locConTrvlPcToriffCst" />'
										onkeyup="calculate1();" onkeypress="return numbersOnly();"
										align="right" maxlength="8" /></td>

								</tr>





								<%
								k1++;
								%>
							</s:iterator>
							<%
							i1 = k1;
							%>

							<tr>
								<td colspan="11" align="right" class="sortableTD"><label
									class="set" id="tms.TotTariffCost" name="tms.TotTariffCost"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TotTariffCost")%></label>
								:</td>
								<td colspan="1"><input type="text" name="totTariffCost"
									id="paraFrm_totTariffCost"
									value='<s:property value="totTariffCost"/>' size="8"
									align="right"></td>

							</tr>


							<tr>
								<td colspan="3" valign="bottom" class="txt"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="5" /></td>
							</tr>
							<tr>
								<td colspan="3" valign="bottom" class="txt"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="5" /></td>
							</tr>



						</table>
						</div>
						</td>

					</tr>

					<!-- iterator for Local Conveyance Details end -->


				</s:if>

				<s:if test="lodgFlg">



					<!-- iterator for  Lodging  Details -->



					<tr>
						<td><strong><label class="set" id="tms.trvlLodgDtls"
							name="tms.trvlLodgDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlLodgDtls")%></label></strong></td>
					</tr>

					<tr>
						<td width="100%">
						<div style="overflow-x: auto; width: 765;">
						<table width="100%" class="formbg">
							<tr>
								<td class="formth"><label class="set"
									id="tms.trvlScheduleSrNo2" name="tms.trvlScheduleSrNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="city1" name="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
								<td class="formth"><label class="set" id="tms.FrmDate"
									name="tms.FrmDate" ondblclick="callShowDiv(this);"><%=label.get("tms.FrmDate")%></label>(DD-MM-YYYY)</td>
								<td class="formth" width="5%"><label class="set"
									id="tms.ChkInTime" name="tms.ChkInTime"
									ondblclick="callShowDiv(this);"><%=label.get("tms.ChkInTime")%></label>(HH24:MI)</td>
								<td class="formth"><label class="set" id="tms.ToDate"
									name="tms.ToDate" ondblclick="callShowDiv(this);"><%=label.get("tms.ToDate")%></label>(DD-MM-YYYY)</td>
								<td class="formth" width="5%"><label class="set"
									id="tms.ChkOutTime" name="tms.ChkOutTime"
									ondblclick="callShowDiv(this);"><%=label.get("tms.ChkOutTime")%></label>(HH24:MI)</td>
								<td class="formth" width="10%"><label class="set"
									id="tms.HotelType" name="tms.HotelType"
									ondblclick="callShowDiv(this);"><%=label.get("tms.HotelType")%></label></td>
								<td class="formth" width="10%"><label class="set"
									id="tms.RoomType" name="tms.RoomType"
									ondblclick="callShowDiv(this);"><%=label.get("tms.RoomType")%></label></td>
								<td class="formth" width="8%"><label class="set"
									id="tms.Address" name="tms.Address"
									ondblclick="callShowDiv(this);"><%=label.get("tms.Address")%></label></td>
								<td class="formth"><label class="set" id="tms.BookingAmt"
									name="tms.BookingAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.BookingAmt")%></label></td>
								<td class="formth" align="left"><label class="set"
									id="tms.TicktDtls2" name="tms.TicktDtls"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TicktDtls")%></label></td>
							</tr>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
							</s:if>






							<%!int i2 = 0;%>
							<%
							int k2 = 1;
							%>



							<s:iterator value="lodgingDtls">
								<tr>
									<s:hidden name="lodgeDtlId" />

									<%
										String cityId = (String) request.getAttribute("lodgeCityId" + k2);
										String cityName = (String) request.getAttribute("lodgeCity" + k2);
									%>
									<td width="5%" nowrap="nowrap" valign="top" class="sortableTD"><%=k2%></td>

									<td width="10%" nowrap="nowrap" valign="top" class="sortableTD"><s:hidden
										name="<%="lodgCityId"+k2%>" value="<%=cityId%>" /> <input
										type="text" name="<%="lodgCity"+k2 %>"
										id="<%="paraFrm_lodgCity"+k2 %>" value="<%=cityName%>"
										size="6" readonly="readonly" /> <img
										src="../pages/images/search2.gif" align="absmiddle"
										theme="simple"
										onclick="callCity('<s:property value="<%=""+k2%>"/>')" /></td>


									<td width="5%" nowrap="nowrap" valign="top" class="sortableTD"><input
										type="text" name="lodgFrmDate" id="lodgFrmDate<%=k2%>"
										value="<s:property value="lodgFrmDate"/>" size="8"
										onkeypress="return numbersWithHiphen();"
										onblur="return validateDate('lodgFrmDate<%=k2%>','tms.FrmDate');"
										maxlength="10"
										ondblclick="javascript:NewCal('<%="lodgFrmDate"+k2%>','DDMMYYYY');">

									</td>


									<td valign="top" class="sortableTD" nowrap="nowrap"><input
										type="text" name="lodgChkInTm" id="lodgChkInTm<%=k2%>"
										value='<s:property value="lodgChkInTm"/>' theme="simple"
										size="4"
										onblur="return validateTime('lodgChkInTm<%=k2%>','tms.ChkInTime');"
										maxlength="5" onkeypress="return numbersWithColon();" /></td>


									<td width="5%" nowrap="nowrap" size="8" valign="top"
										class="sortableTD"><input type="text" name="lodgToDate"
										id="lodgToDate<%=k2%>"
										value="<s:property value="lodgToDate"/>" size="8"
										onkeypress="return numbersWithHiphen();"
										onblur="return validateDate('lodgToDate<%=k2%>','tms.ToDate');"
										maxlength="10"
										ondblclick="javascript:NewCal('<%="lodgToDate"+k2%>','DDMMYYYY');">

									</td>

									<td valign="top" class="sortableTD"><input type="text"
										name="lodgChkOutTm" id="lodgChkOutTm<%=k2%>" theme="simple"
										size="4"
										value='<s:property
										value="lodgChkOutTm"  />'
										onblur="return validateTime('lodgChkOutTm<%=k2%>','tms.ChkOutTime');"
										maxlength="5" onkeypress="return numbersWithColon();" /></td>


									<%
										String hotelId = (String) request.getAttribute("lodgeHotelId" + k2);
										String hotelName = (String) request.getAttribute("lodgeHotel" + k2);
									%>


									<td width="10%" nowrap="nowrap" valign="top" class="sortableTD"><s:hidden
										name="<%="lodgHotelType"+k2%>" value="<%=hotelId%>" /> <input
										type="text" name="<%="lodgHotelName"+k2 %>"
										id="<%="paraFrm_lodgHotelName"+k2 %>" value="<%=hotelName%>"
										size="6" readonly="readonly" /> <img
										src="../pages/images/search2.gif" align="absmiddle"
										theme="simple"
										onclick="callHotel('<s:property value="<%=""+k2%>"/>')" /></td>


									<%
										String roomId = (String) request.getAttribute("lodgeRoomId" + k2);
										String roomName = (String) request.getAttribute("lodgeRoom" + k2);
										String lodgUploadFileName = (String) request.getAttribute("paraFrm_lodgUploadFileName" + k2);
									%>


									<td width="10%" nowrap="nowrap" valign="top" class="sortableTD"><s:hidden
										name="<%="lodgRoomType"+k2%>" value="<%=roomId%>" /> <input
										type="text" name="<%="lodgRoomName"+k2 %>"
										id="<%="paraFrm_lodgRoomName"+k2 %>" value="<%=roomName%>"
										size="6" readonly="readonly" /> <img
										src="../pages/images/search2.gif" align="absmiddle"
										theme="simple"
										onclick="callRoom('<s:property value="<%=""+k2%>"/>')" /></td>







									<td width="8%" nowrap="nowrap" valign="top" class="sortableTD"><s:textarea
										name="lodgAddrs" theme="simple" rows="2" cols="16"
										value="%{lodgAddrs}" /></td>

									<td width="4%" colspan="1" valign="top" class="sortableTD"><input
										type="text" name="lodgBokAmt" theme="simple" size="8"
										value='<s:property
										value="lodgBokAmt" />'
										id="<%="lodgBokAmt"+k2 %>" onkeyup="calculate2();"
										onkeypress="return numbersOnly();" /></td>

									<td valign="top" class="sortableTD" nowrap="nowrap"><input type="text"
										name="lodgUploadFileName" 
										id="<%="paraFrm_lodgUploadFileName"+k2 %>"
										value="<%=lodgUploadFileName%>"  size="8" readonly="readonly"/> <input name="Browse"
										type="button" class="token" value="Upload"
										onclick="uploadFile('<%="paraFrm_lodgUploadFileName"+k2%>')"
										size="6" /></td>


								</tr>


								<%
								k2++;
								%>
							</s:iterator>
							<%
							i2 = k2;
							%>

							<tr>
								<td colspan="9" align="right" class="sortableTD"><label
									class="set" id="tms.TotLodgCost" name="tms.TotLodgCost"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TotLodgCost")%></label>
								:</td>
								<td colspan="1">&nbsp;<input type="text" name="totLodgCost"
									value='<s:property value="totLodgCost"/>'
									id="paraFrm_totLodgCost" size="8" align="right"
									readonly="readonly"></td>



							</tr>
							<tr>
								<td colspan="3" valign="bottom" class="txt"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="5" /></td>
							</tr>

						</table>
						</div>
						</td>

					</tr>

					<!-- iterator for  Lodging  Details end -->
				</s:if>

				<tr>
					<td><strong><label class="set" id="tms.TravelAdv"
						name="tms.TravelAdv" ondblclick="callShowDiv(this);"><%=label.get("tms.TravelAdv")%></label>
					</strong></td>
				</tr>


				<tr>
					<td width="100%" colspan="4">

					<table width="100%" cellpadding="2" cellspacing="2" class="formbg">


						<tr>
							<td width="25%" colspan="1"><label class="set"
								id="tms.AdvAmtIssued" name="tms.AdvAmtIssued"
								ondblclick="callShowDiv(this);"><%=label.get("tms.AdvAmtIssued")%></label>:</td>
							<td width="25%" colspan="1"><input type="text"
								name="schAdvAmt" value='<s:property value="schAdvAmt"/>'
								readonly="readonly" align="right" /></td>
							<td width="25%" colspan="1"><label class="set"
								id="tms.PrefPayMode" name="tms.PrefPayMode"
								ondblclick="callShowDiv(this);"><%=label.get("tms.PrefPayMode")%></label>:</td>

							<td width="25%" colspan="1"><s:label name="schPayMode"
								theme="simple" value="%{schPayMode}" /></td>
						</tr>

						<tr>
							<td width="25%" colspan="1"><label class="set"
								id="tms.InstructionsAndCmts" name="tms.InstructionsAndCmts"
								ondblclick="callShowDiv(this);"><%=label.get("tms.InstructionsAndCmts")%></label>:</td>
							<td colspan="3"><s:textarea rows="4" cols="100"
								name="schComment" /></td>

						</tr>


					</table>
					</td>
				</tr>


			</table>
			</td>

		</tr>



		<tr>
			<td align="left" colspan="4"><s:submit cssClass="save"
				action="TrvlSchl_save" value=" Save " onclick=" return add();"
				theme="simple" /> <s:submit action="TrvlSchl_finalizeSchedule"
				value="Finalise Schedule" theme="simple" onclick=" return add();"  cssClass="token" />
			<s:submit value="  Back" theme="simple" onclick=" callBack();"  cssClass="token"/></td>
		</tr>

		
	</table>
	
	
</s:form>




<script>

function add()
{
//check for the minimum length for vehicle no,Ticket no 
if(!checkForMinLen())
{
return false;
}

if(!callValidMode()) {  return false; } 
if(!callValidMode1()) {  return false; } 
if(!callValidHotelType()) {  return false; } 

return true;

}
</script>

<script>
 function callJourneyMode(id)
    {
    
     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9SchJourney.action');
      
    }
 
 
  function callLocJourneyMode(id)
    {

     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9LocJourney.action');
      
    }
 		
	

function callCity(id)
    {

     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9LodgCity.action');
      
    }
 

function callHotel(id)
    {
   
     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9HotelType.action');
      
}
 	
 	
 
function callRoom(id)
    {

     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9RoomType.action');
      
}
 				


 		
function uploadFile(fieldName) {
	//var path="Logo"; 
	var path="images/<%=session.getAttribute("session_pool")%>/tickets"; 	
	 //var path="images"; 
	window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	document.getElementById('paraFrm').target ="main";	
		
		}
		
 
 function calculate()
 {
 var rowCount = <%=i%>; 
  var temp=eval(0);
  var value;
  for(var x=1;x<rowCount;x++)
  {
  value=Math.round(document.getElementById('ticktCost'+x).value);
  
  if(value=='')
  {
  value=0;
  }
  temp=eval(temp)+eval(value);
  
  } 
  
   document.getElementById('paraFrm_totTrvCost').value=eval(temp);
 }






</script>

<script>



 function calculate1()
 {
 var rowCount1 = <%=i1%>; 

 var temp=eval(0);
  var value;
  for(var x1=1;x1<rowCount1;x1++)
  {
  value=Math.round(document.getElementById('locConTrvlPcToriffCst'+x1).value);

  if(value=='')
  {
  value=0;
  }
  temp=eval(temp)+eval(value);
  
  } 

   document.getElementById('paraFrm_totTariffCost').value=eval(temp);
  
 }


 function calculate2()
 {
 var rowCount2 = <%=i2%>; 
 var temp=eval(0);
  var value;
  for(var x2=1;x2<rowCount2;x2++)
  {
  value=Math.round(document.getElementById('lodgBokAmt'+x2).value);

  if(value=='')
  {
  value=0;
  }
  temp=eval(temp)+eval(value);
  
  } 

   document.getElementById('paraFrm_totLodgCost').value=eval(temp);
  
 }

</script>


<script>
function callTravelPolicy()
    {   
     document.getElementById('paraFrm_empGrade').value=document.getElementById('paraFrm_grade').value;   
 	document.getElementById('paraFrm_trAppId').value=document.getElementById('paraFrm_travelAppId').value;
 	var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
	document.getElementById('paraFrm').target = "wind";
	document.getElementById('paraFrm').action = "TravelApplication_TravelPolicy.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";   
    }
    
    function callBack()
	{    	
	var status=document.getElementById('paraFrm_stat').value;	
	document.getElementById('paraFrm').action = "TrvlSchl_callStatus.action?status="+status;  
	document.getElementById('paraFrm').submit();  
	 }
    
    
    
    	 
	 
  function callValidMode()
   {    
 
    validTrMode = document.getElementById('paraFrm_validTrMode').value; 
     
     var rowCount = <%=i%>;     
    var flag ="false";
    var count =0;
    var invalidTrMode = "";
    var j =1;
     var k =1;
    if(validTrMode!="")
    {
     for(var x=0;x<rowCount-1;x++)
     { 
       
	    trModeClassId ="*"+document.getElementById('paraFrm_travelMdAndClsId'+j).value+"*"; 
	    trModeClass =document.getElementById('paraFrm_travelMdAndCls'+j).value; 
	    if(validTrMode.indexOf(trModeClassId)==-1)
			    {   
				    if(k==1)
				    {
				     invalidTrMode=invalidTrMode+""+trModeClass;     flag ="true"; 
				     } else{
				     invalidTrMode=invalidTrMode+","+trModeClass;     flag ="true"; 
				     }
				     k++;
				} else { flag ="false"; }
				
			 j++;
			 if( flag =="true")
			 {
			  count=count+1;
			 }
		}	  
	}
	
	
	if(count>0)	 
		{
		
		   var conf = confirm("You are not entitle to travel by "+invalidTrMode+" journey(Booking Details).Do you want to continue ? ");
			   if(conf==true)
			     { 
			        return true;
			     }else
			     {   return false;
			     }
		} 
    return true;
   }
   
   
	 
  function callValidMode1()
   {    
    validTrMode = document.getElementById('paraFrm_validTrMode').value;   
     var rowCount = <%=i1%>;  
     var flag ="false";
    var count =0;
    var invalidTrMode = "";
    var j =1;
     var k =1;
  
  
  
    if(validTrMode!="")
    {
     for(var x=1;x<rowCount;x++)
     { 
    
       if(document.getElementById('paraFrm_locConTrvlModId'+x).value!="")
       {
       //---------
       
       trModeClassId ="*"+document.getElementById('paraFrm_locConTrvlModId'+j).value+"*"; 
	    trModeClass =document.getElementById('paraFrm_locConTrvlMod'+j).value; 
	    if(validTrMode.indexOf(trModeClassId)==-1)
			    {   
				    if(k==1)
				    {
				     invalidTrMode=invalidTrMode+""+trModeClass;     flag ="true"; 
				     } else{
				     invalidTrMode=invalidTrMode+","+trModeClass;     flag ="true"; 
				     }
				     k++;
				} else { flag ="false"; }
				
			 j++;
			 if( flag =="true")
			 {
			  count=count+1;
			 }
       //--------
       
       
       
       }
       
       
       
        
		
		
		
		
		
		}	  
	}
	
	
	if(count>0)	 
		{
		
		   var conf = confirm("You are not entitle to travel by "+invalidTrMode+" journey (Local Conveyance Details).Do you want to continue ? ");
			   if(conf==true)
			     { 
			        return true;
			     }else
			     {   return false;
			     }
		} 
    return true;
   }
   
   
   
   
   
   
   
   
   
    function callValidHotelType()
   {  
 
    validHotelType = document.getElementById('paraFrm_validHotelType').value; 

    hotelTypeIdFrst = document.getElementById('paraFrm_lodgHotelType1').value;  
    var flag ="false";
    var count =0;
    var invalidHotelType= "";
    var j =1; 
    var k =1; 
    var rowCount = <%=i2%>; 

    
    if(validHotelType!="" && hotelTypeIdFrst!="")
     {
	    for(var i=0;i<rowCount-1;i++)
	     {  hotelTypeId ="*"+document.getElementById('paraFrm_lodgHotelType'+j).value+"*H"; 
		    roomTypeId ="*"+document.getElementById('paraFrm_lodgRoomType'+j).value+"*R"; 
		    var appHotRoom = hotelTypeId+roomTypeId;
		    hotelType =document.getElementById('paraFrm_lodgHotelName'+j).value;  
		    roomType =document.getElementById('paraFrm_lodgRoomName'+j).value;  
		    
		    if(hotelTypeId!="0" && hotelTypeId !="" && hotelTypeId !="null")
		    { 
		       if(validHotelType.indexOf(appHotRoom)==-1)
				    { 
				    if(k==1)   {
				     invalidHotelType=invalidHotelType+""+hotelType+"-"+roomType;     flag ="true"; 
				      }else
				      {
				      invalidHotelType=invalidHotelType+","+hotelType+"-"+roomType;     flag ="true"; 
				      }
				      k++;
					} else { flag ="false"; }
					
				 j++;
				 if( flag =="true")
				 {
				  count=count+1;
				 }
			 }
			}	  
	 } 
		if(count>0)	 
		{
		   var conf = confirm("You are not entitle for "+invalidHotelType+" Hotel type.Do you want to continue ? ");
			   if(conf==true)
			     { 
			     return true;
			     }else
			     {   return false;
			     }
		} 
    return true;
   }
   
   
   
   
   
   function checkForMinLen()
    {
  	 
  	 var rowCount = <%=i%>;   	 
  	 var rowCount1 = <%=i1%>; 
  	 
  	 
  	 var vehNo,ticktNo,vehicleNo;   
  	
  	
  	for(var x=1;x<rowCount;x++)
 	 {
  	 vehNo=document.getElementById('trvlVehNo'+x).value;
  	 ticktNo=document.getElementById('ticktNo'+x).value;
  	
  	if(vehNo.length>0)
  	{
  	 if(vehNo.length<4)
  	  {
   	
   	 alert(document.getElementById('tms.BusTrainFltNo').innerHTML.toLowerCase()+" should be minimum 4 characters");
   	 return false;
 	   }
 	   }
 	    if(ticktNo.length>0)
 	    {
 	  if(ticktNo.length<4)
   	 {
	alert(document.getElementById('tms.TicktNo').innerHTML.toLowerCase()+" should be minimum 4 characters");
    return false;
   	}
    	}
    	} 
 
  for(var y=1;y<rowCount1;y++)
 	 {
  	 vehicleNo=document.getElementById('locConTrvlModNum'+y).value;
    if(vehicleNo.length>0)
    {
  	  if(vehicleNo.length<4)
  	  {
   	alert(document.getElementById('tms.vehNum').innerHTML.toLowerCase()+"should be minimum 4 characters");
   	return false;
 	   }
 	   }
  	 
  	} 
  return true;
    }
    
    
</script>