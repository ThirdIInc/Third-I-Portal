
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TrvlSchl" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="totalSchedCost" />
	<s:hidden name="totalRefundCost" />

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<s:hidden name="AppID" />
		<s:hidden name="stat" />
		<!-- for  showing Travel Policy -->
		<s:hidden name="travelAppId" />
		<s:hidden name="trAppId" />
		<s:hidden name="trAppId" />
		<s:hidden name="empGrade" />
		<s:hidden name="grade" />


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Travel
					Schedule1 p</strong></td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"> <s:submit value="  Back"
				theme="simple" onclick=" callBack();" cssClass="token" /></td>
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
							<td class="formth">Sr.No.</td>
							<td class="formth">Source-Destination</td>
							<td class="formth">Travel Date (dd-mm-yyyy)</td>
							<td class="formth">Time (HH24:MI)</td>
							<td class="formth">Travel Mode & Class</td>
							<td class="formth">Bus / Train /Flight no.</td>
							<td class="formth">Ticket Number</td>
							<td class="formth">Cost. (Rs.)</td>
							<td class="formth">Cancellation Amount</td>

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
								<td class="sortableTD"><%=k%> <s:hidden
									name="appJournDtlId" /></td>

								<td class="sortableTD" colspan="1"><s:property
									value="sourceAndDest" /></td>
								<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
									value="travelDate" />&nbsp;</td>
								<td class="sortableTD"><s:property value="travelTime" />&nbsp;</td>
								<td class="sortableTD" width="12%" nowrap="nowrap"><s:property
									value="travelMdAndCls" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="trvlVehNo" />&nbsp;</td>
								<td class="sortableTD"><s:property value="ticktNo" />&nbsp;</td>

								<td class="sortableTD"><s:property value="ticktCost" />&nbsp;</td>

								<td class="sortableTD"><s:property value="trvCancAmt"/>&nbsp;</td>




							</tr>
							<%
							k++;
							%>
						</s:iterator>
						<%
						i = k;
						%>



						<tr>
							<td colspan="7" align="right">Total Travel Cost :</td>
							<td colspan="1" class="sortableTD"><s:label
								name="totTrvCost" theme="simple" value="%{totTrvCost}" /></td>
							<td colspan="1" class="sortableTD"><s:label
								name="totTrvCanCost" theme="simple" value="%{totTrvCanCost}" /></td>

						</tr>

					</table>
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
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
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
							<td class="formth">Cancellation Amt</td>
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
								<td class="sortableTD"><%=k1%> <s:hidden name="locConId" /></td>
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
								<td class="sortableTD"><s:property value="locCancAmt" />&nbsp;</td>


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
								value="%{totTariffCost}" /></td>

							<td colspan="1"><s:label name="totTariffCanCost"
								theme="simple" value="%{totTariffCanCost}" /></td>


						</tr>


					</table>
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
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
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
								<td class="formth">Cancellation Amt</td>
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
								<td class="sortableTD"><%=k2%> <s:hidden name="lodgeDtlId" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgCity" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgFrmDate" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgChkInTm" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgToDate" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgChkOutTm" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgHotel" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgRoom" />&nbsp;</td>
								<td class="sortableTD"><s:property value="lodgAddrs" />&nbsp;</td>
								<td class="sortableTD" colspan="1"><s:property
									value="lodgBokAmt" />&nbsp;</td>

								<td class="sortableTD"><s:property value="lodgCancAmt" /></td>
							</tr>





							<%
							k2++;
							%>
						</s:iterator>
						<%
						i2 = k2;
						%>

						<tr>
							<td colspan="9" align="right">Total Lodging Cost :</td>
							<td colspan="1" class="sortableTD"><s:label
								name="totLodgCost" theme="simple" value="%{totLodgCost}" /></td>

							<td colspan="1" class="sortableTD"><s:label
								name="totLodgCanCost" theme="simple" value="%{totLodgCanCost}" /></td>


						</tr>







					</table>
					</td>

				</tr>

				<!-- iterator for  Lodging  Details end -->


</s:if>

				<tr>
					<td><strong>Cancellation Details</strong></td>
				</tr>


				<tr>
					<td width="100%" colspan="6">

					<table width="100%" cellpadding="2" cellspacing="2" class="formbg">




						<tr>
							<td width="15%" colspan="1">Travel Cost :</td>
							<td width="15%" colspan="1" align="left"><s:textfield
								name="travelCost" theme="simple" value="%{travelCost}" size="12"/></td>
							<td width="20%" colspan="1">Travel Cancellation Cost :</td>
							<td width="15%" colspan="1" align="left"><s:textfield
								name="travelCancelAmt" theme="simple" value="%{travelCancelAmt}" size="12"/>

							</td>
							<td width="15%" colspan="1">Refund cost :</td>

							<td width="20%" colspan="1" align="left"><s:textfield
								name="trvlRefund" theme="simple" size="12" readonly="true"/></td>
						</tr>

						<tr>
							<td width="15%" colspan="1">Local Cost :</td>
							<td width="15%" colspan="1" align="left"><s:textfield
								name="localCost" theme="simple" value="%{localCost}" size="12"  readonly="true"/></td>
							<td width="20%" colspan="1">Locale Cancellation Cost :</td>

							<td width="15%" colspan="1" align="left"><s:textfield
								name="localCancelAmt" theme="simple" value="%{localCancelAmt}" size="12"  readonly="true"/>
							</td>
							<td width="15%" colspan="1">Refund cost :</td>

							<td width="20%" colspan="1" align="left"><s:textfield
								name="localRefund" theme="simple"  size="12"  readonly="true"/></td>
						</tr>
						<tr>
							<td width="15%" colspan="1">Lodging Cost :</td>
							<td width="15%" colspan="1" align="left"><s:textfield
								name="lodgingCost" theme="simple" value="%{lodgingCost}" size="12"  readonly="true"/></td>
							<td width="20%" colspan="1">Travel Cancellation Cost :</td>

							<td width="15%" colspan="1" align="left"><s:textfield
								name="lodgeCancelAmt" theme="simple" value="%{lodgeCancelAmt}" size="12"  readonly="true"/>
							</td>
							<td width="15%" colspan="1">Refund cost :</td>

							<td width="20%" colspan="1" align="left"><s:textfield
								name="lodgeRefund" theme="simple"  size="12"  readonly="true"/></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><strong>Travel Advance </strong></td>
				</tr>


				<tr>
					<td width="100%" colspan="4">

					<table width="100%" cellpadding="2" cellspacing="2" class="formbg">


						<tr>
							<td width="25%" colspan="1">Advance Amount issued :</td>
							<td width="25%" colspan="1"><s:label name="schAdvAmt"
								theme="simple" value="%{schAdvAmt}" /></td>
							<td width="25%" colspan="1">Preferred Payment Mode :</td>

							<td width="25%" colspan="1"><s:label name="schPayMode"
								theme="simple" value="%{schPayMode}" /></td>
						</tr>

						<tr>
							<td width="25%" colspan="1">Travel Approver Comments :</td>
							<td colspan="3"><s:textarea rows="3" cols="60"
								name="trvApprCmts" readonly="true" /></td>

						</tr>
						<tr>
							<td width="25%" colspan="1">Comments : :</td>
							<td colspan="3"><s:textarea rows="3" cols="60"
								name="canCmts"  readonly="true"/></td>
						</tr>





					</table>
					</td>
				</tr>


			</table>
			</td>

		</tr>
		<tr>
			<td align="left" colspan="4"><s:submit value="  Back"
				theme="simple" onclick=" callBack();"  cssClass="token"/></td>

		</tr>

	</table>
</s:form>











<script>
  callOnLoad();

function callOnLoad()
{

var bookRowCount = <%=i%>; 
 var localRowCount = <%=i1%>; 
 var lodgRowCount = <%=i2%>;
 
 if(bookRowCount>0)
 {
 var trvCost=eval(Math.round(document.getElementById('paraFrm_travelCost').value));
 var trvCan=eval(Math.round(document.getElementById('paraFrm_travelCancelAmt').value));
 document.getElementById('paraFrm_trvlRefund').value=eval(trvCost)-eval(trvCan);
 }
 if(localRowCount>0)
 {
 var locCost=eval(Math.round(document.getElementById('paraFrm_localCost').value));
 var locCan=eval(Math.round(document.getElementById('paraFrm_localCancelAmt').value));
 document.getElementById('paraFrm_localRefund').value=eval(locCost)-eval(locCan);
 }
if(lodgRowCount>0)
{
var lodgCost=eval(Math.round(document.getElementById('paraFrm_lodgingCost').value));
var lodgCan=eval(Math.round(document.getElementById('paraFrm_lodgeCancelAmt').value));
document.getElementById('paraFrm_lodgeRefund').value=eval(lodgCost)-eval(lodgCan); 
}


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
    
    
</script>

