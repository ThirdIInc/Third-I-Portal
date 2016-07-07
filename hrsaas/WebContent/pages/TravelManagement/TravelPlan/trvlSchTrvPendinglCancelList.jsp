
<%@ taglib prefix="s" uri="/struts-tags"%>
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
					Schedule Pend</strong></td>
				</tr>
			</table>
			</td>
		</tr>

		
	
		
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left">
					<s:submit cssClass="save"
				action="TrvlSchl_saveCanceled" value=" Save "
				onclick="callAdd();" theme="simple" /><s:submit value="  Back"
				theme="simple" onclick=" callBack();" cssClass="token" />
					</td>
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
					<td><strong>Employee Information</strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22">Employee
					Name :</td>
					<td colspan="3"><s:label name="empToken" theme="simple"
						value="%{empToken}" />&nbsp;&nbsp; <s:label name="employeeName"
						theme="simple" value="%{employeeName}" /></td>

				</tr>


				<tr>
					<td width="10%" class="formtext" height="22">Branch:</td>
					<td width="25%"><s:label name="brnchName" theme="simple"
						value="%{brnchName}" /></td>

					<td width="10%" class="formtext">Department:</td>
					<td width="25%"><s:label name="deptName" theme="simple"
						value="%{deptName}" /></td>

				</tr>


				<tr>

					<td width="10%" class="formtext" height="22">Application Date:</td>
					<td width="25%"><s:label name="applDate" theme="simple"
						value="%{applDate}" /></td>
					<td width="10%" class="formtext">Grade:</td>
					<td width="25%"><s:label name="grdName" theme="simple"
						value="%{grdName}" /> &nbsp;&nbsp; <input type="button"
						value="Travel Policy" class="token" onclick="callTravelPolicy();">
					</td>
				</tr>


				<tr>

					<td width="10%" class="formtext" height="22">Age:</td>
					<td width="25%"><s:label name="dob" theme="simple"
						value="%{dob}" /></td>
					<td width="10%" class="formtext">Contact Number:</td>
					<td width="25%"><s:label name="mobile" theme="simple"
						value="%{mobile}" /></td>

				</tr>
				
				
				
				
				<tr>

					<td width="10%" class="formtext" height="22">Travel Request Name:</td>
					<td width="25%"><s:label name="trvlRequest" theme="simple"
						value="%{trvlRequest}" /></td>
					<td width="10%" class="formtext">Tour Purpose:</td>
					<td width="25%"><s:label name="tourPurpose" theme="simple"
						value="%{tourPurpose}" /></td>
					
				</tr>
				
				<tr>
                    <td width="10%" class="formtext">Tour Start Date:</td>
					<td width="25%"><s:label name="tourStrtDate" theme="simple"
						value="%{tourStrtDate}" /></td>
					
					<td width="10%" class="formtext">Tour End Date:</td>
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
					<td><strong>Booking Details</strong></td>
				</tr>

				<!-- iterator for Booking Details  -->

				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
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

								<td class="sortableTD" colspan="1" nowrap="nowrap"><s:property
									value="sourceAndDest" />&nbsp;</td>
								<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
									value="travelDate" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="travelTime" />&nbsp;</td>
								<td class="sortableTD" width="12%" nowrap="nowrap"><s:property
									value="travelMdAndCls" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="trvlVehNo" />&nbsp;</td>


								<td class="sortableTD" nowrap="nowrap"><s:property
									value="ticktNo" />&nbsp;</td>

								<td class="sortableTD" nowrap="nowrap"><s:property
									value="ticktCost" />&nbsp;</td>

								<td class="sortableTD" nowrap="nowrap"><input type="text"
									name="trvCancAmt" id="<%="trvCancAmt"+k %>" theme="simple"
									size="8" onkeyup="calculate();"
									onkeypress="return numbersOnly(); align=" right" maxlength="8"/>&nbsp;</td>




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
							<td colspan="1" class="sortableTD"><input type="text"
								name="totTrvCost" value='<s:property value="totTrvCost"/>'
								id="paraFrm_totTrvCost" size="12" readonly="readonly"
								align="right"></td>
							<td colspan="1" class="sortableTD"><input type="text"
								name="totTrvCanCost" value='<s:property value="totTrvCanCost"/>'
								id="paraFrm_totTrvCanCost" size="8" readonly="readonly"
								align="right"></td>

						</tr>

					</table>
					</td>

				</tr>

				<!-- iterator for Booking Details end -->

</s:if>
	<s:if test="localConFlg">


				<tr>
					<td><strong>Local Conveyance Details</strong></td>
				</tr>

				<!-- iterator for Local Conveyance Details  -->


				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
						<tr>
							<td class="formth">Sr.No.</td>
							<td class="formth">City</td>
							<td class="formth">Source</td>
							<td class="formth">Date</td>
							<td class="formth">Travel Mode</td>
							<td class="formth">Number</td>
							<td class="formth">Contact Person</td>
							<td class="formth">Contact Number</td>
							<td class="formth">Pick Up Person</td>
							<td class="formth">Pick up time</td>
							<td class="formth">Pick up Place</td>
							<td class="formth">tariff Cost</td>
							<td class="formth">Cancellation Amount</td>
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

								<td class="sortableTD" width="15%" nowrap="nowrap" colspan="1">&nbsp;<s:property
									value="locConSource" />&nbsp;</td>

								<td class="sortableTD" width="10%" nowrap="nowrap" colspan="1">&nbsp;
								<s:property value="locConDate" /></td>

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
								<td class="sortableTD"><input type="text" name="locCancAmt"
									id="<%="locCancAmt"+k1 %>" theme="simple" size="8"
									onkeyup="calculate1();" onkeypress="return numbersOnly();"
									align="right" maxlength="8"/>&nbsp;</td>


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
							<td colspan="1" class="sortableTD"><input type="text"
								name="totTariffCost" id="paraFrm_totTariffCost"
								value='<s:property value="totTariffCost"/>' size="12"
								readonly="readonly" align="right">&nbsp;</td>

							<td colspan="1" class="sortableTD"><input type="text"
								name="totTariffCanCost" id="paraFrm_totTariffCanCost"
								value='<s:property value="totTariffCanCost"/>' size="8"
								readonly="readonly" align="right">&nbsp;</td>


						</tr>


					</table>
					</td>

				</tr>

				<!-- iterator for Local Conveyance Details end -->


</s:if>
<s:if test="lodgFlg">



				<!-- iterator for  Lodging  Details -->




				<tr>
					<td><strong>Lodging Detail</strong></td>
				</tr>




				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td class="formth">Sr.No.</td>
							<td class="formth">City</td>
							<td class="formth">From Date(dd-mm-yyyy)</td>
							<td class="formth">Check In(HH24:MI)</td>
							<td class="formth">TO Date(dd-mm-yyyy)</td>
							<td class="formth">Check Out(HH24:MI)</td>
							<td class="formth">Hotel Type</td>
							<td class="formth">Room Type</td>
							<td class="formth">Address</td>
							<td class="formth">Booking Amt (Rs.)</td>
							<td class="formth">Cancellation Amount</td>


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
								<td class="sortableTD"><%=k2%> <s:hidden name="lodgeDtlId" /></td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="lodgCity" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="lodgFrmDate" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="lodgChkInTm" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap" width="10%"><s:property
									value="lodgToDate" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="lodgChkOutTm" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="lodgHotel" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="lodgRoom" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="lodgAddrs" />&nbsp;</td>
								<td class="sortableTD" colspan="1" align="right"><s:property
									value="lodgBokAmt" />&nbsp;</td>

								<td class="sortableTD"><input type="text"
									name="lodgCancAmt" id="<%="lodgCancAmt"+k2 %>" theme="simple"
									size="8" onkeyup="calculate2();"
									onkeypress="return numbersOnly();" align="right" maxlength="8"/></td>
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
							<td colspan="1" class="sortableTD"><input type="text"
								name="totLodgCost" value='<s:property value="totLodgCost"/>'
								id="paraFrm_totLodgCost" size="12" readonly="readonly"
								align="right"></td>

							<td colspan="1" class="sortableTD"><input type="text"
								name="totLodgCanCost"
								value='<s:property value="totLodgCanCost"/>'
								id="paraFrm_totLodgCanCost" size="8" readonly="readonly"
								align="right"></td>


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
					<td width="100%">

					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">


						<s:if test="jourBkFlg">
						<tr>
							<td width="15%">Travel Cost :</td>
							<td width="15%"><input type="text" name="travelCost"
								id="paraFrm_travelCost" readonly="readonly" size="10"
								   value='<s:property value="totTrvCost"/>' align="right" /></td>
							<td width="20%">Travel Cancellation Cost :</td>
							<td width="15%" align="left"><input type="text"
								name="travelCancelAmt" id="paraFrm_travelCancelAmt"
								value='<s:property value="travelCancelAmt"/>'
								readonly="readonly" size="10" align="right" /></td>
							<td width="15%">Refund cost :</td>

							<td width="20%" align="left"><input type="text"
								name="trvlRefund" value='<s:property value="trvlRefund"/>'
								readonly="readonly" id="paraFrm_trvlRefund" size="10"
								align="right" /></td>

						</tr>
						</s:if>

						<s:if test="localConFlg">
						<tr>
							<td width="15%">Local Cost :</td>
							<td width="15%" align="left"><input type="text"
								name="localCost" id="paraFrm_localCost" readonly="readonly"
								size="10" align="right"  value='<s:property value="totTariffCost"/>'/></td>
							<td width="20%">Locale Cancellation Cost :</td>

							<td width="15%" align="left"><input type="text"
								name="localCancelAmt" id="paraFrm_localCancelAmt"
								value='<s:property value="localCancelAmt"/>' readonly="readonly"
								size="10" align="right" /></td>
							<td width="15%">Refund cost :</td>

							<td width="20%" align="left"><input type="text"
								name="localRefund" id="paraFrm_localRefund"
								value='<s:property value="localRefund"/>' readonly="readonly"
								size="10" align="right" /></td>

						</tr>
						</s:if>
					<s:if test="lodgFlg">
						<tr>
							<td width="15%">Lodging Cost :</td>
							<td width="15%" align="left"><input type="text"
								name="lodgingCost" id="paraFrm_lodgingCost" readonly="readonly"
								size="10" align="right"  value='<s:property value="totLodgCost"/>' /></td>
							<td width="20%">Travel Cancellation Cost :</td>

							<td width="15%" align="left"><input type="text"
								name="lodgeCancelAmt" id="paraFrm_lodgeCancelAmt"
								value='<s:property value="lodgeCancelAmt"/>' readonly="readonly"
								size="10" align="right" /></td>
							<td width="15%">Refund cost :</td>

							<td width="20%" align="left"><input type="text"
								name="lodgeRefund" id="paraFrm_lodgeRefund"
								value='<s:property value="lodgeRefund"/>' readonly="readonly"
								size="10" align="right" /></td>

						</tr>
						</s:if>
					</table>
					</td>
				</tr>
				<tr>
					<td><strong>Travel Advance </strong></td>
				</tr>


				<tr>
					<td width="100%">

					<table width="100%" cellpadding="2" cellspacing="2" class="formbg">


						<tr>
							<td width="25%">Advance Amount issued :</td>
							<td width="25%"><input type="text" name="schAdvAmt"
								value='<s:property value="schAdvAmt"/>' readonly="readonly"
								align="right" / size="6"></td>
							<td width="25%" colspan="1">Preferred Payment Mode :</td>

							<td width="25%"><s:label name="schPayMode" theme="simple"
								value="%{schPayMode}" /></td>
						</tr>

						<tr>
							<td width="25%">Travel Approver Comments :</td>
							<td colspan="3"><s:textarea rows="4" cols="100"
								name="trvApprCmts" readonly="true" /></td>

						</tr>
						<tr>
							<td width="25%">Comments : :</td>
							<td colspan="3"><s:textarea rows="4" cols="100"
								name="canCmts" /></td>
						</tr>





					</table>
					</td>
				</tr>


			</table>
			</td>

		</tr>



		<tr>
			<td align="left" colspan="4"><s:submit cssClass="save"
				action="TrvlSchl_saveCanceled" value=" Save "
				onclick="callAdd();" theme="simple" /><s:submit value="  Back"
				theme="simple" onclick=" callBack();" cssClass="token" /></td>

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
 var trvlCost=document.getElementById('paraFrm_totTrvCost').value;
 document.getElementById('paraFrm_travelCost').value=trvlCost;
 }
  
  if(localRowCount>0)
  {
  var localCost=document.getElementById('paraFrm_totTariffCost').value;
  document.getElementById('paraFrm_localCost').value=localCost;
  }
  if(lodgRowCount>0)
  {
  var lodgeCost=document.getElementById('paraFrm_totLodgCost').value;
  document.getElementById('paraFrm_lodgingCost').value=lodgeCost;
  }
}
</script>

<script>


 
 function calculate()
 {

 var rowCount = <%=i%>; 
  var temp=eval(0);
  var value;
  for(var x=1;x<rowCount;x++)
  {
  value=document.getElementById('trvCancAmt'+x).value;
  
  if(value=='')
  {
  value=0;
  }
  temp=eval(temp)+eval(value);
  
  
  } 
  document.getElementById('paraFrm_totTrvCanCost').value=eval(temp);
 
 jsp:callMe();
 
 //call function to cal 

 }




 function calculate1()
 {
 var rowCount1 = <%=i1%>; 
 var temp=eval(0);
 var value;
 for(var x1=1;x1<rowCount1;x1++)
 {
 value=document.getElementById('locCancAmt'+x1).value;
 if(value=='')
 {
 value=0;
 }
 temp=eval(temp)+eval(value);
 } 
 document.getElementById('paraFrm_totTariffCanCost').value=eval(temp);
 jsp:callMe();
 }

 function calculate2()
 {
 var rowCount2 = <%=i2%>; 
 var temp=eval(0);
  var value;
  for(var x2=1;x2<rowCount2;x2++)
  {
  value=document.getElementById('lodgCancAmt'+x2).value;

  if(value=='')
  {
  value=0;
  }
  temp=eval(temp)+eval(value);
  } 
   document.getElementById('paraFrm_totLodgCanCost').value=eval(temp);
   jsp:callMe();
 }
 
 
 
 function callMe()
 {
 
//  travelCancelAmt   localCancelAmt lodgeCancelAmt trvlRefund localRefund lodgeRefund


var bookRowCount = <%=i%>; 
var localRowCount = <%=i1%>; 
var lodgRowCount = <%=i2%>;

if(bookRowCount>0)
{
var trvCost=eval(document.getElementById('paraFrm_totTrvCost').value);
var trvCan=eval(document.getElementById('paraFrm_totTrvCanCost').value);
document.getElementById('paraFrm_travelCancelAmt').value=trvCan;
document.getElementById('paraFrm_trvlRefund').value=eval(trvCost)-eval(trvCan);
}
if(localRowCount>0)
{
var locCost=eval(document.getElementById('paraFrm_totTariffCost').value);
var locCan=eval(document.getElementById('paraFrm_totTariffCanCost').value);
document.getElementById('paraFrm_localCancelAmt').value=locCan;
document.getElementById('paraFrm_localRefund').value=eval(locCost)-eval(locCan);
}  
if(lodgRowCount>0)
{
var lodgCost=eval(document.getElementById('paraFrm_totLodgCost').value);
var lodgCan=eval(document.getElementById('paraFrm_totLodgCanCost').value);
document.getElementById('paraFrm_lodgeCancelAmt').value=lodgCan;
document.getElementById('paraFrm_lodgeRefund').value=eval(lodgCost)-eval(lodgCan);
}
}



function callAdd()
{
var bookRowCount = <%=i%>; 
var localRowCount = <%=i1%>; 
var lodgRowCount = <%=i2%>;
var totalCost=0,totalRefundcost=0;
if(bookRowCount>0)
{
var trvCost=eval(document.getElementById('paraFrm_totTrvCost').value);
var trvRefund=eval(document.getElementById('paraFrm_trvlRefund').value);
totalCost=eval(trvCost);
totalRefundcost=eval(trvRefund)
}

if(localRowCount>0)
{
var locCost=eval(document.getElementById('paraFrm_totTariffCost').value);
var locRefund=eval(document.getElementById('paraFrm_localRefund').value);
totalCost+=eval(locCost);
totalRefundcost+=eval(locRefund)
}
if(lodgRowCount>0)
{
var lodgCost=eval(document.getElementById('paraFrm_totLodgCost').value);
var lodgRefund=eval(document.getElementById('paraFrm_lodgeRefund').value);
totalCost+=eval(lodgCost);
totalRefundcost+=eval(lodgRefund)
}
document.getElementById('paraFrm_totalSchedCost').value=totalCost;
document.getElementById('paraFrm_totalRefundCost').value=totalRefundcost;
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


