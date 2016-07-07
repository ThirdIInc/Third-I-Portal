<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="RankMaster" validate="true" id="paraFrm" 
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="designationName" />
	<s:hidden name="designationAbbr" />
	<s:hidden name="designationStatus" />
	<s:hidden name="designationCode" />
	<s:hidden name="salaryRange" />
	<s:hidden name="isActive" />

	<s:hidden name="report" />
	<s:hidden name="reportAction" value='RankMaster_getReport.action' />

	<table class="formbg" width="100%" >
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Designation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="18%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="82%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>

				</tr>
				<tr>

					<%
						int totalPage = 0;
						int pageNo = 0;
					%>

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

		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
				border="0" id="reportBodyTable">
				<tr>
					<s:if test="modeLength">
						<td id="ctrlShow" width="100%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'RankMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'RankMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'RankMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'RankMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'RankMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
					<td align="right"><input type="button" class="delete"
						id="ctrlShow" value=" Delete" onclick="return chkDelete();" /></td>
				</tr>

				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" class="formbg" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="formtext">
							<table width="100%" border="0">
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" width="10%"><label
										class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
									</td>
									<td class="formth" align="center" width="25%"><label
										class="set" name="desg.name" id="desg.name1"
										ondblclick="callShowDiv(this);"><%=label.get("desg.name")%></label>
									</td>
									<td class="formth" align="center" width="20%"><label
										class="set" name="desg.abbr" id="desg.abbr1"
										ondblclick="callShowDiv(this);"><%=label.get("desg.abbr")%></label>
									</td>
									<td class="formth" align="center" width="20%"><label
										class="set" name="range" id="range1"
										ondblclick="callShowDiv(this);"><%=label.get("range")%></label>
									</td>
									<td class="formth" align="center" width="15%"><label
										class="set" name="is.active" id="is.active1"
										ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									</td>
									<s:if test="modeLength">
										<td align="right" class="formth" nowrap="nowrap" id="ctrlShow"
											width="10%"><input type="checkbox" id="selAll"
											style="cursor: hand;"
											title="Select all records Name  to remove"
											onclick="selectAllRecords(this);" /></td>
									</s:if>
									<s:if test="modeLength">
										<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
												int cnt = pageNo * 20 - 20;
												int i = 0;
										%>
										<s:iterator value="designationList">

											<tr id="tr1" <%if(count%2==0){%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												title="Double click for edit"
												ondblclick="javascript:callForEdit('<s:property  value="designationCodeItt"/>');">
												<td align="center" class="sortableTD"><%=++cnt%> <%
 ++i;
 %>
												</td>
												<td align="left" class="sortableTD"><s:hidden
													value="%{designationCodeItt}"
													id='<%="designationCodeItt" + i%>'>
												</s:hidden> <script type="text/javascript">
												records[<%=i%>] = document.getElementById('designationCodeItt' + '<%=i%>').value;
											</script> <input type="hidden" name="hdeleteCode" id="hdeleteCode" />
												<s:property value="designationName" /></td>
												<td align="left" class="sortableTD"
													title="Double click for edit"
													ondblclick="javascript:callForEdit('<s:property  value="designationCodeItt"/>');">
												<s:property value="designationAbbr" /></td>
												<td class="sortableTD" align="center">&nbsp; <s:property
													value="salaryRangeItt" /></td>
												<td class="sortableTD" align="center">&nbsp; <s:property
													value="isActiveItt" /></td>
												<td id="ctrlShow" align="center" nowrap="nowrap"
													class="sortableTD"><input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" /> <input
													type="checkbox" class="checkbox" id="confChk<%=i%>"
													name="confChk"
													onclick="callForDelete1('<s:property  value="designationCodeItt"/>','<%=i%>')" /></td>
											</tr>
										</s:iterator>
										<%
										d = i;
										%>
									</s:if>
								</tr>
							</table>
							<s:if test="modeLength"></s:if> <s:else>
								<table width="100%">
									<tr>
										<td align="center"><font color="red">No Data To
										Display</font></td>
									</tr>
								</table>
							</s:else> <%
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
	</table>
			</td>
		</tr>
	</table>
			</td>
		</tr>

		
		<!--<tr>
			<td width="100%">
			<table width="100%">
				<td width="40%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="60%" colspan="6"><</td>
			</table>
			</td>
		</tr>
		
		<tr width="100%">
		<td width="40%"></td>
		<td width="60%" align="right"><s:if test="modeLength">
					<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
				</s:if></td>
		</tr>
		-->
		<tr>
		<td width="100%">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="18%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="100%" colspan="6"><%@ include	file="/pages/common/reportButtonPanel.jsp"%></td>
					
				</tr>
				<tr>
				<td></td>
				<s:if test="modeLength">
						<td align="right"><b>Total No. of Records:<s:property value="totalRecords" /></b></b></td>
				</s:if>
				</tr>
			</table>
		</td>			
	</tr>
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
	</table>
	<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>

</s:form>


<script>
	// new function added for add New Button
	function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'RankMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='RankMaster_f9Action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'RankMaster_report.action';
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
	   	document.getElementById("paraFrm").action='RankMaster_calforedit.action?code='+id;
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
   
    function callForDelete1(id,i)
	   {
	 // alert('aa');
	   var flag='<%=d %>';
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
	   document.getElementById('paraFrm').action="RankMaster_delete1.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	document.getElementById('selAll').checked = false;
	    var flag='<%=d %>';
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
	
	
	//Added by Tinshuk Banafar
	function callReport(type){	
	  try{
		 document.getElementById('paraFrm_report').value=type;
		 callReportCommon(type);
	     }
		 catch (e){
	 	 alert(e);
	 	 }	
	  }
function mailReportFun(type){
				
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='RankMaster_mailReport.action';
		document.getElementById('paraFrm').submit();
			//return true;
		}
	</script>


