<!--Author by Ankit-->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
	<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="TrainingCalendar" id="paraFrm" validate="true" target="main"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	<s:hidden name="trainingCalendar.trainingID" />
	<s:hidden name="modeLength" />
	
	
<table width="100%" border="0"  class="formbg" align="right">
		
		 <tr>
						<td valign="bottom" class="txt">
							<table  width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg" >
						
							    <tr>
									  <td>
									     <strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /></strong></td>
								    	 <td width="93%" class="txt"><strong class="text_head">Training</strong></td>
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
						<td width="18%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<% int totalPage = 0; int pageNo = 0; %>
						
				</table>
			</td>			
		</tr>
		
		<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">
					<tr><td></td>
					
						<s:if test="modeLength">
						<td id="ctrlShow" width="90%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'TrainingCalendar_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'TrainingCalendar_callPage.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TrainingCalendar_callPage.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'TrainingCalendar_callPage.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TrainingCalendar_callPage.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>
						</td>
						</s:if>
						<td  align="right">
							<input type="button" id="ctrlShow" class="delete" value=" Delete" onclick="return chkDelete();" />
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
										<td class="formth" align="center"><label name="serialno"
											id="serialno" ondblclick="callShowDiv(this);"><%=label.get("serialno")%></label></td>
											
											<td class="formth" align="center"><label name="coursename"
											id="coursename" ondblclick="callShowDiv(this);"><%=label.get("coursename")%></label></td>
											
										<td class="formth" align="center"><label name="enrolcutoff"
											id="enrolcutoff" ondblclick="callShowDiv(this);"><%=label.get("enrolcutoff")%></label></td>
											
											
										<td class="formth" align="center"><label name="scheduledstartdate"
											id="scheduledstartdate" ondblclick="callShowDiv(this);"><%=label.get("scheduledstartdate")%></label></td>
											
											<td class="formth" align="center"><label name="scheduledenddate"
											id="scheduledenddate" ondblclick="callShowDiv(this);"><%=label.get("scheduledenddate")%></label></td>
											
											<td class="formth" align="center"><label name="managesession"
											id="managesession" ondblclick="callShowDiv(this);"><%=label.get("managesession")%></label></td>
											
											<td class="formth" align="center"><label name="configurefeedback"
											id="configurefeedback" ondblclick="callShowDiv(this);"><%=label.get("configurefeedback")%></label></td>
											
											<td class="formth" align="center"><label name="trainingstatus"
											id="trainingstatus" ondblclick="callShowDiv(this);"><%=label.get("trainingstatus")%></label></td>
											
											<td class="formth" align="center"><label name="schedule.action"
											id="schedule.action" ondblclick="callShowDiv(this);"><%=label.get("schedule.action")%></label></td>
											
											<td class="formth" align="center"><label name="confiattendence"
											id="confiattendence" ondblclick="callShowDiv(this);"><%=label.get("confiattendence")%></label></td>
											
											<td class="formth" align="center"><label name="markattendence"
											id="markattendence" ondblclick="callShowDiv(this);"><%=label.get("markattendence")%></label></td>
											
											<td class="formth" align="center"><label name="action"
											id="action" ondblclick="callShowDiv(this);"><%=label.get("action")%></label></td>
										
									
									
									<s:if test="modeLength">	
									<%int count=0; %>
										<%!int d=0; %>
										<%
											int i = 0;
										int cn= pageNo*20-20;
											%>
									
										<s:iterator value=" courseList">
										<tr  class="sortableTD"
											<%if(count%2==0){
												%>
												 class="tableCell1" <%}else{%>
												class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="trainingID"/>');">
			
			
												<td width="10%" align="center" class="sortableTD"><%=++cn%><%++i;%><s:hidden name="srNo" /></td>
												<s:hidden value="%{trainingID}" id='<%="trainingID" + i%>'></s:hidden>
			                                      <script type="text/javascript">
													records[<%=i%>] = document.getElementById('trainingID' + '<%=i%>').value;
												</script>
												<td width="40%" align="left" class="sortableTD"><s:property
													value="courseName" /> <input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
											    <td width="50%" align="left" class="sortableTD"><s:property
													value="cutOffDate" /></td>
												<td width="40%" align="left" class="sortableTD"><s:property
															value="scheduleStartDate" /></td>
										        <td width="40%" align="left" class="sortableTD"><s:property
															value="scheduleEndDate" /></td>	
															
												<td width="40%" align="left" class="sortableTD">Manage</td>
												<td width="40%" align="left" class="sortableTD">Map</td>
											    <td width="40%" align="left" class="sortableTD">Initiated</td>
											    <td width="40%" align="left" class="sortableTD">Publish</td>			
												<td width="40%" align="left" class="sortableTD">Veiw</td>
												<td width="40%" align="left" class="sortableTD">Mark</td>
												<td class="sortableTD" width="5%" id="ctrlShow"
													align="center"><input type="button" class="rowEdit"
													title="Click for edit"
													onclick="callForEdit('<s:property value="trainingID"/>')" />
													|
													<input type="button" class="rowDelete"
													title="Click for edit"
													onclick="callForDelete('<s:property value="trainingID"/>')" />

												</td>
																								
														
												
													
											</tr>
			
										</s:iterator>
										<%d=i; %>
										</s:if>
								
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
		</table></td></tr>
		
	</table>
	
</s:form>

<script>
function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'TrainingCalendar_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='TrainingCalendar_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'TrainingCalendar_report.action';
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
		
  	function callForEdit(id){
  
  	callButton('NA', 'Y', 2);
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="TrainingCalendar_calforedit.action";
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
	   document.getElementById('paraFrm').action="TrainingCalendar_delete1.action";
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
		document.getElementById("paraFrm").action = 'TrainingCalendar_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
   	
   	

   	function callReport(type){	
	try{
	
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
	
		 }
		 catch (e)
	    {
	 	alert(e);
	 	}	
	 }

	 function mailReportFun(type){
				
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='TrainingCalendar_mailReport.action';
		document.getElementById('paraFrm').submit();
			//return true;
		}
  	</script>



