<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
	<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="OtCalculations" id="paraFrm" validate="true" target="main"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	
<s:hidden name="processDate"/>
<s:hidden name="hiddenOtCalId" />
<s:hidden name="divisionNameItr"/>
<s:hidden name="otCalculationId"/>	
<s:hidden name="divisionName"/>	

<table width="100%" border="0"  class="formbg" align="right">
		
		 <tr>
						<td valign="bottom" class="txt">
							<table  width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg" >
						
							    <tr>
									  <td>
									     <strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /></strong></td>
								    	 <td width="93%" class="txt"><strong class="text_head">OT Calculations </strong></td>
									   <td width="3%" valign="top" class="txt">
											<div align="right"><img
												src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
										</td>
							  </tr>
						  </table>
					  </td>
 		 </tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<% int totalPage = 0; int pageNo = 0; %>
						<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'OtCalculations_input.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'OtCalculations_input.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'OtCalculations_input.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'OtCalculations_input.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'OtCalculations_input.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>
						</td>
						</s:if>
					</tr>
				</table>
			</td>			
		</tr>
		
		<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">
					<tr>
						<td colspan="2" class="txt"><strong class="text_head">OT Calculation List</strong></td>		
						<td  align="right">
							
						</td>
					</tr>								
					<tr>
						<td colspan="3">
						<%
						try {
						%>
						<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
								<tr>
								<td class="formtext">
								<table width="100%" border="0" cellpadding="1" cellspacing="1"
									class="sortableTD">
									<tr class="td_bottom_border">
									<s:hidden name="myPage" id="myPage" />
									<td width="3%" class="formth" align="center"><label
										class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									
									<td width="10%" class="formth" align="center"><label
										class="set" name="ot.period" id="ot.period"
										ondblclick="callShowDiv(this);"><%=label.get("ot.period")%></label></td>
										
									<td width="10%" class="formth" align="center"><label
										class="set" name="process.date" id="process.date"
										ondblclick="callShowDiv(this);"><%=label.get("process.date")%></label></td>
									 
									<td width="10%" class="formth" align="center"><label
										class="set" name="division" id="division"
										ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
									
									<td width="10%" class="formth" align="center"><label
										class="set" name="paybill" id="paybill"
										ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label></td>
										
									<td width="10%" class="formth" align="center"><label
										class="set" name="headcount" id="headcount"
										ondblclick="callShowDiv(this);"><%=label.get("headcount")%></label></td>
									
									<td width="10%" class="formth" align="center"><label
										class="set" name="total.ot.amt" id="total.ot.amt"
										ondblclick="callShowDiv(this);"><%=label.get("total.ot.amt")%></label></td>
									
									<td width="10%" class="formth" align="center"><label
										class="set" name="paid.in.sal.month" id="paid.in.sal.month"
										ondblclick="callShowDiv(this);"><%=label.get("paid.in.sal.month")%></label></td>
										
									<td width="10%" class="formth" align="center"> Status</td>
								
									<td width="5%" class="formth" align="center"> Edit</td> 
									
									
									<s:if test="modeLength">	
									<%int count=0; %>
										<%!int d=0; %>
										<%
											int i = 0;
										int cn= pageNo*20-20;
											%>
									
										<s:iterator value="iteratorlist">

											<tr <%if(count%2==0){
													%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">

												<td width="10%" align="center" class="sortableTD"><%=++cn%>
												<%
													++i;
												%> 
												</td>
												<s:hidden value="%{hiddenOtCalId}"
													id='<%="hiddenOtCalId"+i %>' />
												<script type="text/javascript">
														records[<%=i%>] = document.getElementById('hiddenOtCalId' + '<%=i%>').value;
													</script>
												
												<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
												<td width="10%" > <s:property
													value="otPeriod" /></td>
												<td width="10%" > <s:property
													value="processDate" /></td>

												<td width="10%" align="left" class="sortableTD"><s:property
													value="divisionName" />&nbsp;</td>
												
												
												<td width="10%" ><s:property
													value="paybillName" />&nbsp; </td>
												<td width="10%" align="right" > <s:property
													value="totalEmpCount" />&nbsp; </td>
												<td width="10%"  align="right" ><s:property
													value="totalOtAmount" />&nbsp;  </td>
												<td width="10%" > <s:property
													value="paidInSalMonth" />&nbsp;  </td>
												
												<td width="10%" > <s:property
													value="status" />&nbsp;  </td>
												<td class="sortableTD" width="5%" id="ctrlShow"
													align="center"><input type="button" class="rowEdit"
													title="Click for edit"
													onclick="callForEdit('<s:property value="hiddenOtCalId"/>')" />

												</td> 
												
											</tr>

										</s:iterator>
										<%d=i; %>
										</s:if>
								</tr>
									
								</table>
								<s:if test="modeLength"></s:if>
								<s:else>
									<table width="100%">
										<tr>
											<td align="center"><font color="red">No Data To Display</font></td>
										</tr>
									</table>
								</s:else>
								</td>
							</tr>
						</table>
						<%
							} catch (Exception e) {
							}
						%>
						</td>
					</tr>
				</table>
			</td>
		</tr>		
		<tr><td width="100%"><table width="100%">
			<td width="79%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td width="21%" ><s:if test="modeLength"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
		</table></td>
		
	</table>
	
</s:form>

<script>
// new function added for add New Button
	function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'OtCalculations_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='OtCalculations_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'OtCalculations_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	
		
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
		function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
		
  	function callForEdit(otConfigId){
	
	  	//document.getElementById('paraFrm_hiddenOtCalId').value=id;
	   	document.getElementById("paraFrm").action="OtCalculations_calforedit.action?otConfigId="+otConfigId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
   
    function callForDelete1(id,i)
	   {

	   var flag='<%=d %>';
	
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }
	function chkDelete()
	{
	 
	 if(chk()){
	
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	    document.getElementById('paraFrm').action="OtCalculations_delete1.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    
	    var flag='<%=d %>';
	    document.getElementById('selAll').checked=false;
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	
	}
	     return false;
	 }
	 }
	 else {
	 alert('please select atleast one record');
	 return false;
	 }
	
}
function chk(){
   
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}
	
	
	function selectAllRecords(object) {
		if(object.checked) {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hdeleteCode' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
	}
	
	function callForDelete1(id,i)
	   {
	   
	   var flag='<%=d %>';
	 // alert('id----1-----'+id);
	  // alert('i----1-----'+i);
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }
   

	
	function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}
   
   	function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		 cell.className='Cell_bg_first'; 
		//cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
  	
  	function callDelete(id) {
		if(document.getElementById(id).value == "") {
			alert("Please select a record to delete");
			return false;
		}
      	var conf = confirm("Do you really want to delete this record ?");
		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			return true;
  		}
	  	return false;
	}
	
   function callSearch(action) {
  // alert ('x');
		myWin = window.open('','myWin','top=260,left=250,width=700,height=400,scrollbars=no,toolbars=no,status=yes,resizable=yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'OtCalculations_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
   	
   	
  
	
  	</script>



