<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="CompetencyMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="quesCode"/><s:hidden name="quesName"/><s:hidden name="quesType"/>
	<s:hidden name="srNo" />
	<s:hidden name="quesAttr" />   <s:hidden name="quesValue"/><s:hidden name="hiddenEdit" />
	<s:hidden  name="competencyCode" />
	<s:hidden 	name="competencyTitle" />
	<s:hidden 	name="competencyDesc" />
	<s:hidden 	name="competencyIndicator" />
	
<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Competency Master
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr><td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<% int totalPage = 0; int pageNo = 0; %>
				<s:if test="modeLength">
						<td id="ctrlShow" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'CompetencyMaster_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'CompetencyMaster_callPage.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'CompetencyMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'CompetencyMaster_callPage.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CompetencyMaster_callPage.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>
						</td></s:if>
					</tr>	
			</table>
		</td>
	</tr>		

	<tr>
		<td colspan="3" width="100%">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
					<tr>
						<td class="txt" colspan="2"><strong class="text_head">Competency Master
						  </strong></td>
						<td align="right">
								<input type="button" id="ctrlShow" class="token" value="Delete" onclick="return chkDelete();" />
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<%
						try {
						%>
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortableTD">
									<tr>
										<s:hidden name="myPage" id="myPage" />
										<td class="formth" align="center"><label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
										<td class="formth" align="center"><label  class = "set" name="competencyTitle" id="competencyTitle" ondblclick="callShowDiv(this);"><%=label.get("competencyTitle")%></label></td>
										<td class="formth" align="center"><label  class = "set" name="competencyDesc" id="competencyDesc" ondblclick="callShowDiv(this);"><%=label.get("competencyDesc")%></label></td>
										<td class="formth" align="center"><label  class = "set" name="competencyIndicator" id="competencyIndicator" ondblclick="callShowDiv(this);"><%=label.get("competencyIndicator")%></label></td>
				                <s:if test="modeLength">	
											<td  align="right" id="ctrlShow" class="formth" nowrap="nowrap">
								<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />
			
									
									</td>
									</s:if>
									<s:if test="modeLength">	
										<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
										int i = 0;
										int cn= pageNo*20-20;
										%>
										<s:iterator value="competencylist">
			
											<tr class="sortableTD" title="Double click for edit" <%if(count%2==0){
												%> class="tableCell1" <%}else{%>
												class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												
									    	title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="competencyCode"/>');">
												
												<td width="10%" align="center" class="sortableTD"><%=++cn%><%++i;%>
												
													<s:hidden name="competencyCodeIteratorVal" id='<%="competencyCodeIteratorVal" + i%>'/> 
												
												</td>
												
												<script type="text/javascript">
												 
													records[<%=i%>] = document.getElementById('competencyCodeIteratorVal' + '<%=i%>').value;
												</script>
												<td width="50%" align="left" class="sortableTD">&nbsp;
												    <s:property 	value="competencyTitle" />
												    
												</td>
												<td width="40%" align="left" class="sortableTD">
													<s:property value="competencyDesc" />
													<input type="hidden"	name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
												<td width="50%" align="left" class="sortableTD">&nbsp;
													<s:property value="competencyIndicator" />
												</td>	
												<td id="ctrlShow" align="center" nowrap="nowrap" class="sortableTD">
													<input type="checkbox" class="checkbox" id="confChk<%=i%>" name="confChk"
														   onclick="callForDelete1('<s:property value="competencyCode"/>','<%=i%>');" /></td>
											</tr>
										</s:iterator>
										<%
										d = i;
										%>
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
			<td ><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td align="right" ><s:if test="modeLength"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
		</table></td>
		</tr>


	</table>



</s:form>


<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>

<script>
	// new function added for add New Button
	function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'CompetencyMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='CompetencyMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'CompetencyMaster_report.action';
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
	
	  	document.getElementById('paraFrm_hiddenCode').value=id;
	  		document.getElementById('paraFrm').target = "_self";
	   	document.getElementById("paraFrm").action="CompetencyMaster_calforedit.action";
	   //	document.getElementById("paraFrm").target="main";
	   
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
		   document.getElementById('paraFrm').action="CompetencyMaster_deletequestion.action";
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
	</script>

