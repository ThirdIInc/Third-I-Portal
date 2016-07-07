
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelSchAppr" validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<s:hidden name="AppID" />
		<!-- for call back -->
		<s:hidden name="stat" />
		<!-- for showing Travel Policy -->
		<s:hidden name="travelAppId" />
		<s:hidden name="trAppId" />
		<s:hidden name="empGrade" />
		<s:hidden name="grade" />


		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Travel
					Schedule</strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:submit value="  Back"
				theme="simple" onclick=" callBack();"  cssClass="token"/></td>
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
			<table width="100%" align="center" cellpadding="2" cellspacing="2"
				class="formbg" theme="simple">

				<tr>
					<td><strong><label class="set" id="tms.trvlEmpInfo"
						name="tms.trvlEmpInfo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlEmpInfo")%></label></strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td colspan="3"><s:label name="empToken" theme="simple"
						value="%{empToken}" />&nbsp;&nbsp; <s:label name="employeeName"
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
						value="%{grdName}" />
						&nbsp;&nbsp; <input type="button"
						value="Travel Policy" class="token" onclick="callTravelPolicy();">
						</td>



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

					<td width="10%" class="formtext" height="22"><label class="set" id="tms.trvlTrvlReqName"	name="tms.trvlTrvlReqName" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTrvlReqName")%></label>:</td>
					<td width="25%"><s:label name="trvlRequest" theme="simple"
						value="%{trvlRequest}" /></td>
					<td width="10%" class="formtext"><label class="set" id="tms.trvlTourPurpose"	name="tms.trvlTourPurpose" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTourPurpose")%></label>:</td>
					<td width="25%"><s:label name="tourPurpose" theme="simple"
						value="%{tourPurpose}" /></td>
					
				</tr>
				
				<tr>
                    <td width="10%" class="formtext"><label class="set" id="tms.trvlTourStrtDate"	name="tms.trvlTourStrtDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTourStrtDate")%></label>:</td>
					<td width="25%"><s:label name="tourStrtDate" theme="simple"
						value="%{tourStrtDate}" /></td>
					
					<td width="10%" class="formtext"><label class="set" id="tms.trvlTourEndDate"	name="tms.trvlTourEndDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTourEndDate")%></label>:</td>
					<td width="25%"><s:label name="tourEndDate" theme="simple"
						value="%{tourEndDate}" /></td>
				</tr>
				
				
				
			</table>
		</td>
		</tr>




		<tr>
			<td width="100%">
			<table width="100%" cellpadding="2" cellspacing="2" class="formbg">

				<s:if test="jourBkFlg">
				<tr>
						<td><strong><label class="set" id="tms.trvlBookDtls"
							name="tms.trvlBookDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlBookDtls")%></label></strong></td>
					</tr>

				<!-- iterator for Booking Details  -->


				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td class="formth"><label
									class="set" id="tms.trvlScheduleSrNo"
									name="tms.trvlScheduleSrNo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
							<td class="formth"><label class="set"
									id="tms.trvlSourDest" name="tms.trvlSourDest"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlSourDest")%></label></td>
							<td class="formth"><label class="set"
									id="tms.trvlTravelDate" name="tms.trvlTravelDate"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTravelDate")%></label></td>
							<td class="formth"><label class="set"
									id="tms.trvlTime" name="tms.trvlTime"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTime")%></label></td>
							<td class="formth"><label class="set"
									id="tms.trvlModeAndCls" name="tms.trvlModeAndCls"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlModeAndCls")%></label></td>
							<td class="formth"><label class="set"
									id="tms.BusTrainFltNo" name="tms.BusTrainFltNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.BusTrainFltNo")%></label></td>
							<td class="formth"><label class="set"
									id="tms.TicktNo" name="tms.TicktNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TicktNo")%></label></td>
							<td class="formth"><label class="set"
									id="tms.cost" name="tms.cost" ondblclick="callShowDiv(this);"><%=label.get("tms.cost")%></label></td>
							<td class="formth"><label class="set"
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
								<s:hidden name="appJournDtlId" />
								
								<td class="sortableTD" ><%=k%></td>

								<td class="sortableTD" colspan="1"><s:property
									value="sourceAndDest" />&nbsp;</td>
								<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
									value="travelDate" />&nbsp;</td>
								<td class="sortableTD"><s:property value="travelTime" />&nbsp;</td>
								<td class="sortableTD" width="12%" nowrap="nowrap"><s:property
									value="travelMdAndCls" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="trvlVehNo" />&nbsp;</td>
								

								<td class="sortableTD"><s:property value="ticktNo" />&nbsp;</td>

								<td class="sortableTD"><s:property value="ticktCost" />&nbsp;</td>
								
								
								
								
								<!-- <td class="sortableTD" nowrap="nowrap"><s:property
									value="uploadFileName"/></td> -->
								
								<td  class="sortableTD" nowrap="nowrap" ><input
										type="button" name="Download" class="token" value="Download" 
										onclick="callDownLoad('<s:property value="uploadFileName"/>')" />

									</td>
								
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
									ondblclick="callShowDiv(this);"><%=label.get("tms.TotTravelCost")%></label>:</td>
							<td colspan="1"><s:label name="totTrvCost" theme="simple"
								value="%{totTrvCost}" /></td>

						</tr>

					</table>
					</td>

				</tr>

				<!-- iterator for Booking Details end -->

</s:if>
	<s:if test="localConFlg">


					
					<tr>
						<td><strong><label class="set" id="tms.trvlLocalConvDtls"	name="tms.trvlLocalConvDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlLocalConvDtls")%></label></strong></td>
					</tr>

				<!-- iterator for Local Conveyance Details  -->


				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
								<td class="formth" nowrap="nowrap"><label class="set"
									id="tms.trvlScheduleSrNo1" name="tms.trvlScheduleSrNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
								<td class="formth"><label class="set" id="city" name="city"
									ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
								<td class="formth"><label class="set" id="tms.Source"
									name="tms.Source" ondblclick="callShowDiv(this);"><%=label.get("tms.Source")%></label></td>
								<td class="formth"><label class="set" id="tms.Date"
									name="tms.Date" ondblclick="callShowDiv(this);"><%=label.get("tms.Date")%></label></td>
								<td class="formth"><label class="set" id="tms.TravelMode"
									name="tms.TravelMode" ondblclick="callShowDiv(this);"><%=label.get("tms.TravelMode")%></label></td>
								<td class="formth"><label class="set" id="tms.vehNum"
									name="tms.vehNum" ondblclick="callShowDiv(this);"><%=label.get("tms.vehNum")%></label></td>
								<td class="formth"><label class="set" id="tms.ConPer"
									name="tms.ConPer" ondblclick="callShowDiv(this);"><%=label.get("tms.ConPer")%></label></td>
								<td class="formth"><label class="set" id="tms.ConNum"
									name="tms.ConNum" ondblclick="callShowDiv(this);"><%=label.get("tms.ConNum")%></label></td>
								<td class="formth"><label class="set" id="tms.PickUpPer"
									name="tms.PickUpPer" ondblclick="callShowDiv(this);"><%=label.get("tms.PickUpPer")%></label></td>
								<td class="formth"><label class="set" id="tms.PicUpTime"
									name="tms.PicUpTime" ondblclick="callShowDiv(this);"><%=label.get("tms.PicUpTime")%></label></td>
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
								<td class="sortableTD"><%=k1%></td>
								<s:hidden name="locConId" />
								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1"><s:property
									value="locConCity" />&nbsp;</td>

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1"><s:property
									value="locConSource" />&nbsp;</td>

								<td class="sortableTD" width="10%" nowrap="nowrap" colspan="1">
								<s:property value="locConDate" />&nbsp;</td>

								<td class="sortableTD" width="6%" nowrap="nowrap"><s:property
									value="locConTrvlMod" />&nbsp;</td>


								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">
								<s:property value="locConTrvlModNum" />&nbsp;</td>

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">
								<s:property value="locConTrvlConPer" />&nbsp;</td>

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">
								<s:property value="locConTrvlConNum" />&nbsp;</td>

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">
								<s:property value="locConTrvlPcPer" />&nbsp;</td>

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">
								<s:property value="locConTrvlPcTime" />&nbsp;</td>

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">
								<s:property value="locConTrvlPcPlace" />&nbsp;</td>

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">

								<s:property value="locConTrvlPcToriffCst" />&nbsp;</td>

							</tr>


							<%
							k1++;
							%>
						</s:iterator>
						<%
						i1 = k1;
						%>

						<tr>
							<td colspan="11" align="right">Total Tariff Cost :</td>
							<td colspan="1"><s:label name="totTariffCost" theme="simple"
								value="%{totTariffCost}" />&nbsp;</td>

						</tr>



					</table>
				</td>

				</tr>

				<!-- iterator for Local Conveyance Details end -->



</s:if>
	<s:if test="lodgFlg">


				<!-- iterator for  Lodging  Details -->



<tr>
						<td><strong><label class="set" id="tms.trvlLodgDtls"	name="tms.trvlLodgDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlLodgDtls")%></label></strong></td>
					</tr>



				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
								<td class="formth"><label class="set"
									id="tms.trvlScheduleSrNo2" name="tms.trvlScheduleSrNo"
									ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
								<td class="formth" width="12%"><label class="set"
									id="city1" name="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
								<td class="formth"><label class="set" id="tms.FrmDate"
									name="tms.FrmDate" ondblclick="callShowDiv(this);"><%=label.get("tms.FrmDate")%></label></td>
								<td class="formth" width="5%"><label class="set"
									id="tms.ChkInTime" name="tms.ChkInTime"
									ondblclick="callShowDiv(this);"><%=label.get("tms.ChkInTime")%></label></td>
								<td class="formth"><label class="set" id="tms.ToDate"
									name="tms.ToDate" ondblclick="callShowDiv(this);"><%=label.get("tms.ToDate")%></label></td>
								<td class="formth" width="5%"><label class="set"
									id="tms.ChkOutTime" name="tms.ChkOutTime"
									ondblclick="callShowDiv(this);"><%=label.get("tms.ChkOutTime")%></label></td>
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
								<td class="sortableTD" ><%=k2%> <s:hidden
									name="lodgeDtlId" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgCity" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgFrmDate" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgChkInTm" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgToDate" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgChkOutTm" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgHotel" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgRoom" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="lodgAddrs" />&nbsp;</td>
								<td class="sortableTD"  colspan="1">
								<s:property value="lodgBokAmt" />&nbsp;</td>
								<!-- <td class="border2" ><s:property
									value="lodgUploadFileName" /></td>
							 -->
								
							
							<td  class="sortableTD" nowrap="nowrap" ><input
										type="button" name="Download" class="token" value="Download" 
										onclick="callDownLoad('<s:property value="lodgUploadFileName"/>')" />

									</td>
							
							</tr>





							<%
							k2++;
							%>
						</s:iterator>
						<%
						i2 = k2;
						%>

						<tr>
							<td colspan="9" align="right"><label
									class="set" id="tms.TotLodgCost" name="tms.TotLodgCost"
									ondblclick="callShowDiv(this);"><%=label.get("tms.TotLodgCost")%></label>:</td>
							<td colspan="1"><s:label name="totLodgCost" theme="simple"
								value="%{totLodgCost}" /></td>

						</tr>




					</table>
				</td>

				</tr>
				</s:if>

				<!-- iterator for  Lodging  Details end -->


				<tr>
					<td><strong><label class="set" id="tms.TravelAdv"
						name="tms.TravelAdv" ondblclick="callShowDiv(this);"><%=label.get("tms.TravelAdv")%></label> </strong></td>
				</tr>


				<tr>
					<td width="100%" colspan="4">

					<table width="100%" cellpadding="2" cellspacing="2" class="formbg">


						<tr>
							<td width="25%" colspan="1"><label class="set"
								id="tms.AdvAmtIssued" name="tms.AdvAmtIssued"
								ondblclick="callShowDiv(this);"><%=label.get("tms.AdvAmtIssued")%></label>:</td>
							<td width="25%" colspan="1"><s:label name="schAdvAmt"
								theme="simple" value="%{schAdvAmt}" /></td>
							<td width="25%" colspan="1"><label class="set"
								id="tms.PrefPayMode" name="tms.PrefPayMode"
								ondblclick="callShowDiv(this);"><%=label.get("tms.PrefPayMode")%></label>:</td>

							<td width="25%" colspan="1"><s:label name="schPayMode"
								theme="simple" value="%{schPayMode}" /></td>
						</tr>

					
											
<tr>
							<td width="25%" colspan="1"><label class="set" id="tms.SchComments"	name="tms.SchComments" ondblclick="callShowDiv(this);"><%=label.get("tms.SchComments")%></label>:</td>
							<td colspan="3"><s:textarea rows="4" cols="100"
								name="schComment" readonly="true"/></td>

						</tr>
						
						
						
						<tr>
							<td width="25%" colspan="1"><label class="set" id="tms.SchApprComments"	name="tms.SchApprComments" ondblclick="callShowDiv(this);"><%=label.get("tms.SchApprComments")%></label>:</td>
							<td colspan="3"><s:textarea rows="4" cols="100"
								name="schApprComment" readonly="true"/></td>

						</tr>
					
					
					
					
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="5" /></td>
		</tr>


					</table>
					</td>
				</tr>


			</table>
		</td>

		</tr>



		<tr>
			<td align="laft" colspan="4"><s:submit value="  Back"
				theme="simple" onclick=" callBack();"  cssClass="token"/></td>

		</tr>


	</table>
</s:form>











<script>
 function callJourneyMode(id)
    {
    alert(id);
     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9SchJourney.action');
      
    }
 
 
  function callLocJourneyMode(id)
    {
    alert(id);
     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9LocJourney.action');
      
    }
 		
	

function callCity(id)
    {
    alert(id);
     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9LodgCity.action');
      
    }
 

function callHotel(id)
    {
    alert(id);
     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9HotelType.action');
      
}
 	
 	
 
function callRoom(id)
    {
    alert(id);
     document.getElementById('paraFrm_rowId').value=id;       
      callsF9(500,325,'TrvlSchl_f9RoomType.action');
      
}
 				


 		
function uploadFile(fieldName) {
	//var path="Logo";
	
	var path="images/<%=session.getAttribute("session_pool")%>"; 	
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
  value=document.getElementById('ticktCost'+x).value;
  
  if(value=='')
  {
  value=0;
  }
  temp=eval(temp)+eval(value);
  
  } 
  alert(temp);
   document.getElementById('paraFrm_totTrvCost').value=eval(temp);
 }






</script>

<script>



 function calculate1()
 {
 var rowCount1 = <%=i1%>; 
alert(rowCount1);
 var temp=eval(0);
  var value;
  for(var x1=1;x1<rowCount1;x1++)
  {
  value=document.getElementById('locConTrvlPcToriffCst'+x1).value;

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
alert(rowCount2);
 var temp=eval(0);
  var value;
  for(var x2=1;x2<rowCount2;x2++)
  {
  value=document.getElementById('lodgBokAmt'+x2).value;

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
function callDownLoad(fileName){ 		
	if(fileName=="")
		{
		alert('Ticket details are not  available');
		return false;
		}
var path="images/<%=session.getAttribute("session_pool")%>/tickets/"+fileName; 	
window.open('../pages/'+path);	
document.getElementById('paraFrm').target ="main";	
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
	document.getElementById('paraFrm').action = "TravelSchAppr_callStatus.action?status="+status;  
	document.getElementById('paraFrm').submit();  
	 }
    
</script>

