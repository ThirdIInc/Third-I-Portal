<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="QualificationMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<!-- Flagas used For Cancel Button -->
	<s:hidden name="editFlag" />
	
	<s:hidden name="quaName" />
	<s:hidden name="qualevel" />
	<s:hidden name="hidLevel" />
	<s:hidden name="isactive" />
	<s:hidden name="quaAbbr"/> <s:hidden name="status"/> <s:hidden name="qualificationID" />
	<s:hidden name="reportAction" value='QualificationMaster_report2.action'/>
	<s:hidden name="report" />
	

	<table class="formbg" width="100%" align="right">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Qualification</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				
	
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<!-- The Following code Denotes  Include JSP Page For Button Panel -->

						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="20%"><jsp:include
										page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
									<% int totalPage = 0; int pageNo = 0; %>
									<s:if test="modeLength">
										<td width="80%" align="left"><%@ include
											file="/pages/common/reportButtonPanel.jsp"%></td>


									</s:if>

								</tr>
								

							</table>
							</td>
						</tr>
					
						<tr>
							<td>
							<div name="htmlReport" id='reportDiv'
								style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
							<iframe id="reportFrame" frameborder="0" onload=alertsize();
								style="vertical-align: top; float: left; border: 0px solid;"
								allowtransparency="yes" src="../pages/common/loading.jsp"
								scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
								name="htmlReport" width="100%" height="200"></iframe></div>
							</td>
		
						</tr>
					</table>
				</td>
				</tr>
				
		<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0" id="reportBodyTable">				
				<tr>					
					<td colspan="3">
						<table width="100%" >
							<tr>
					<td id="ctrlShow" width="100%" align="right" class="" colspan="2" >
										<b>Page:</b>
										<%	totalPage = (Integer) request.getAttribute("totalPage");
											 pageNo = (Integer) request.getAttribute("pageNo");
										%>
										<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'QualificationMaster_callPage.action');">
											<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
										</a>&nbsp;
										<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'QualificationMaster_callPage.action');" >
											<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
										</a> 
										<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'QualificationMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
										<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'QualificationMaster_callPage.action')" >
											<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'QualificationMaster_callPage.action');" >
											<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
									</td>
								<td align="right">
									<input type="button" id="ctrlShow"	class="delete" theme="simple"	value=" Delete"    onclick=" return chkDelete();"/>																				
								</td>
							</tr>
						</table>					
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
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center"><b><label class="set" name="serial.no"
										id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="qual.name"
										id="qual.name1" ondblclick="callShowDiv(this);"><%=label.get("qual.name")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="qual.abbr"
										id="qual.abbr1" ondblclick="callShowDiv(this);"><%=label.get("qual.abbr")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="qual.isactive"
										id="qual.isactive" ondblclick="callShowDiv(this);"><%=label.get("qual.isactive")%></label></b>
									</td>

  								<s:if test="modeLength">
									<td align="right" class="formth" id="ctrlShow" nowrap="nowrap">
								<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />	
										</td>
										</s:if>
								 
								 
								  <s:if test="modeLength">
									<%
					int i = 0;
					%>
					<%!int total = 0;%>
										
									
									
									
						<s:iterator value="qualificationList">
						<tr ondblclick="javascript:callForEdit('<s:property value="quaID"/>');">
					
							
							<td width="10%" align="center" class="sortableTD"><s:property
								value="ittSrN0" /></td>
							<td class="sortableTD" width="40%"><s:property value="quaName" /></td>
							<td class="sortableTD" width="20%">&nbsp;<s:property value="quaAbbr" /></td>
							<td class="sortableTD" width="20%">&nbsp;<s:property value="isactive" /></td>
							<td align="center" nowrap="nowrap" class="sortableTD"
								id="ctrlShow"><input type="checkbox" class="checkbox"
								id="confChk<%=i%>" name="confChk"
								onclick="selectRecord('<%=i%>');" /> <input type="hidden" name="hdeleteCode"
								 id="hdeleteCode<%=i%>"/ >
								
								<s:hidden  name="quaID"></s:hidden> 
								 
								</td>

						</tr>
<%
						i++;
						%>
						<%
						total = i;
						%>
					</s:iterator>	
				
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
				</table>
			</td>
		</tr>		
		 <tr><td width="100%"><table width="100%">
			<td width="79%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td align="right" ><s:if test="modeLength"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
		</table></td>
		</tr>
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>

	</table>
	
</s:form>

<script>
function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'QualificationMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='QualificationMaster_f9Action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'QualificationMaster_report.action';
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
		 cell.className='Cell_bg_first'; 
	//cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
	function callForEdit(id){
	callButton('NA', 'Y', 2);
	  	document.getElementById('paraFrm_qualificationID').value=id;
	   	document.getElementById("paraFrm").action="QualificationMaster_calforedit.action";
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
   
    function callForDelete1(id,i)
	   {
	 // alert('aa');
	   var flag='<%=total%>';
	 //alert('id----1-----'+id);
	//alert('i----1-----'+i);
	
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
	   document.getElementById('paraFrm').action="QualificationMaster_delete1.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    document.getElementById('selAll').checked=false;
	    var flag='<%=total%>';
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
   
	 var flag='<%=total%>';
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
			var count='<%=total%>';
		if(object.checked ) {
			for(var i = 0; i <= count; i++) {
				document.getElementById('confChk' + i).checked = true;
				 document.getElementById('hdeleteCode' + i).value="Y";
			}
		} else {
			for(var i = 0; i <= count; i++) {
				document.getElementById('confChk' + i).checked = false;
				 document.getElementById('hdeleteCode' + i).value="";
			}
		}
	}
	

	
	function selectRecord(i){
	
if(document.getElementById('confChk' + i).checked == true ) {
	document.getElementById('hdeleteCode' + i).value="Y";
	}
	else{
	
   document.getElementById('confChk' + i).checked =false;
	document.getElementById('hdeleteCode' + i).value=" ";
	}		 


}


function callReport(type)
	{
		document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
			
	}
function mailReportFun(type)
{
		
			document.getElementById('paraFrm_report').value=type;
			//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='QualificationMaster_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		
	}
		var obj = document.getElementById("topButtonTable").cloneNode(true);
		document.getElementById("bottomButtonTable").appendChild(obj);

</script>



