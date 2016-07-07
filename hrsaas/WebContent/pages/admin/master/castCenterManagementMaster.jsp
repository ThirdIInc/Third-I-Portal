<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="CastCenterManagementMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="castCode" />
	<s:hidden name="subcode" />
	<s:hidden name="tableLength" />
	<s:hidden name="castAbbr"/>
	<s:hidden name="castName"/>
	
<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Cost Center Management
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>

	<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
	<% int totalPage = 0; int pageNo = 0; %>
	<s:if test="modeLength">
	<tr>
			<td id="ctrlShow" width="100%" align="right" class="formbg">
				<b>Page:</b>
				<%	 totalPage = (Integer) request.getAttribute("totalPage");
					 pageNo = (Integer) request.getAttribute("pageNo");
				%>
				<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'CastCenterManagementMaster_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
				</a>&nbsp;
				<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'CastCenterManagementMaster_callPage.action');" >
					<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
				</a> 
				<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
				onkeypress="callPageText(event, '<%=totalPage%>', 'CastCenterManagementMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
				<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'CastCenterManagementMaster_callPage.action')" >
					<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
				</a>&nbsp;
				<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CastCenterManagementMaster_callPage.action');" >
					<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
				</a>
			</td>
		</tr>	</s:if>
		


<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							<td class="formth" align="center"><label  class = "set" name="cast.abbr" id="cast.abbr" ondblclick="callShowDiv(this);"><%=label.get("cast.abbr")%></label></td>
							<td class="formth" align="center"><label  class = "set" name="cast.name" id="cast.name" ondblclick="callShowDiv(this);"><%=label.get("cast.name")%></label></td>
							<td class="formth" align="center"><label  class = "set" name="subcast.abbr" id="subcast.abbr" ondblclick="callShowDiv(this);"><%=label.get("subcast.abbr")%></label></td>
							<td class="formth" align="center"><label  class = "set" name="subcast.center" id="subcast.center" ondblclick="callShowDiv(this);"><%=label.get("subcast.center")%></label></td>
	                <s:if test="modeLength">	
	                
	                
								<td  align="right" class="formth" nowrap="nowrap">
					<input type="button" id="ctrlShow" class="token" value="Remove" onclick="return chkDelete();" />
					
						<br>
								<input type="checkbox" id="ctrlShow" style="cursor: hand;" title="Select all records to remove" 
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
							<s:iterator value="castlist">

								<tr class="sortableTD" title="Double click for edit" <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									
						    	title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="castCode"/>');">
									
									<td width="10%" align="center" class="sortableTD"><%=++cn%><%++i;%></td>
									<s:hidden value="%{castCode}"  id='<%="castCode"+i %>' ></s:hidden>
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('castCode' + '<%=i%>').value;
									</script>
									
									<td width="20%" align="left" class="sortableTD"><s:property
										value="castAbbr" />&nbsp;</td>
									<td width="20%" align="left" class="sortableTD"><s:property
										value="castName" />&nbsp;</td>
										<td width="25%" align="left" class="sortableTD"><s:property
										value="subcastAbbr" />&nbsp;</td>
										<td width="20%" align="left" class="sortableTD"><s:property
										value="subcastName" /> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" />&nbsp;</td>
									<td id="ctrlShow" align="center" nowrap="nowrap" class="sortableTD"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="castCode"/>','<%=i%>');" /></td>
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
		document.getElementById('paraFrm').action = 'CastCenterManagementMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='CastCenterManagementMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'CastCenterManagementMaster_report.action';
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
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  		document.getElementById('paraFrm').target = "_self";
	   	document.getElementById("paraFrm").action="CastCenterManagementMaster_calforedit.action";
	   //	document.getElementById("paraFrm").target="main";
	   
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
	   document.getElementById('paraFrm').action="CastCenterManagementMaster_delete1.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    
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

