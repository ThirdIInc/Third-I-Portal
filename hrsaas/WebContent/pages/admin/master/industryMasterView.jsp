<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var specArray = new Array();
</script>
<s:form action="IndustryMaster" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag" />
	<s:hidden name="industryCode" />
	<s:hidden name="industryName" />
	<s:hidden name="industryAbbr" />
	<s:hidden name="industryDesc" />
	<s:hidden name="industryStatus" />
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Industry
					Type</strong></td>
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

				<!-- The Following code Denotes  Include JSP Page For Button Panel -->

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
								<%
							int totalPage = (Integer) request.getAttribute("abc");
							int pageNo = (Integer) request.getAttribute("xyz");
						%> 
							<s:if test="flagShow">	
								<td align="right" width="22%" nowrap="nowrap" id="ctrlShow"><b>Page:</b> <input type="hidden" name="totalPage" id="totalPage"
					value="<%=totalPage%>"> <a href="#"
					onclick="callPage('1','F','<%=totalPage %>','IndustryMaster_callPage.action');">
				<img title="First Page" src="../pages/common/img/first.gif"
					width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
					onclick="callPage('P','P','<%=totalPage %>','IndustryMaster_callPage.action');">
				<img title="Previous Page" src="../pages/common/img/previous.gif"
					width="10" height="10" class="iconImage" /> </a> <input type="text"
					name="pageNoField" id="pageNoField" theme="simple" size="3"
					value="<%= pageNo%>"
					onkeypress="callPageText(event,'<%=totalPage %>','IndustryMaster_callPage.action');return numbersOnly()"
					maxlength="4" /> of <%=totalPage%> <a href="#"
					onclick="callPage('N','N','<%=totalPage %>','IndustryMaster_callPage.action');">
				<img title="Next Page" src="../pages/common/img/next.gif"
					class="iconImage" /> </a>&nbsp; <a href="#"
					onclick="callPage('<%=totalPage%>','L','<%=totalPage %>','IndustryMaster_callPage.action');">
				<img title="Last Page" src="../pages/common/img/last.gif" width="10"
					height="10" class="iconImage" /> </a></td></s:if>
						</tr>
					</table>
					</td>
				</tr>

				<s:if test="industryView">
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="formbg">
							<tr>
								<td>
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="2">

									<tr>
										<td height="7" colspan="5" class="formtext"><img
											src="../pages/common/css/default/images/space.gif" width="5"
											height="7" /></td>
									</tr>

									<tr>
										<td width="22%" height="22" class="formtext"><label
											class="set" name="indu.name" id="indu.name"
											ondblclick="callShowDiv(this);"><%=label.get("indu.name")%></label>
										:</td>
										<td width="34%" height="22"><s:property
											value="industryName" /></td>
										<td width="27%" height="22" class="formtext">&nbsp;</td>
										<td width="18%" height="22">&nbsp;</td>
									</tr>

									<tr>
										<td width="22%" height="22" class="formtext" nowrap="nowrap"><label
											class="set" name="indu.abbr" id="indu.abbr"
											ondblclick="callShowDiv(this);"><%=label.get("indu.abbr")%></label>
										:</td>
										<td height="22" width="22%"><s:property
											value="industryAbbr" /></td>
										<td height="22" class="formtext">&nbsp;</td>
										<td height="22">&nbsp;</td>
									</tr>

									<tr>
										<td width="22%" height="22" valign="top" class="formtext"
											nowrap="nowrap"><label class="set" name="indu.desc"
											id="indu.desc" ondblclick="callShowDiv(this);"><%=label.get("indu.desc")%></label>:
										</td>
										<td height="22" colspan="3"><s:property
											value="industryDesc" /></td>
									</tr>

									<tr>
										<td width="20%" height="22" class="formtext"><label
											class="set" name="indu.stat" id="indu.stat"
											ondblclick="callShowDiv(this);"><%=label.get("indu.stat")%></label>:
										</td>
										<td height="22"><s:property value="pageStatus" /></td>
										<td height="22" class="formtext">&nbsp;</td>
										<td height="22">&nbsp;</td>
									</tr>

									<tr>
										<td colspan="5"><img
											src="../pages/common/css/default/images/space.gif" width="5"
											height="7" /></td>
									</tr>

								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
		
			<tr>
			
			</tr>		
			</table>
			</td>
		</tr>
	<s:if test="flagShow">	
		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>

					<td class="formtext" colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="15" class="formhead" width="85%" colspan="4" align="left"><strong
								class="forminnerhead">Industry Type List</strong></td>
							<td align="center" colspan="1" id="ctrlShow" width="15%"><input type="button" class="delete"
								theme="simple" value="Delete"
								onclick="return chkDelete();" /></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" width="8%" colspan="1"><b><label class="set"
								name="indu.srno" id="indu.srno" ondblclick="callShowDiv(this);"><%=label.get("indu.srno")%></label></b>
							</td>
							<td class="formth" align="center" width="35%" colspan="1"><b><label class="set"
								name="indu.name" id="indu.name1" ondblclick="callShowDiv(this);"><%=label.get("indu.name")%></label></b>
							</td>
							<td class="formth" align="center" width="27%" colspan="1"><b><label class="set"
								name="indu.abbr" id="indu.abbr1" ondblclick="callShowDiv(this);"><%=label.get("indu.abbr")%></label></b>
							</td>
							<td class="formth" align="center" width="15%" colspan="1"><b><label class="set"
								name="indu.stat" id="indu.stat1" ondblclick="callShowDiv(this);"><%=label.get("indu.stat")%></label></b>
							</td>
							<td align="center" class="formth" id="ctrlShow" width="15%" colspan="1"><input
								type="checkbox" name="checkAll" id="checkAll"
								onclick=" checkAllBox()" /> </td>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
									int cnt = pageNo * 20 - 20;
									int i = 0;
							%>
							<%int counter = 0;%>
							<s:iterator value="industryList">

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


									<td width="8%" align="center" class="sortableTD"
										title="Double click for edit"
										ondblclick="javascript:callForEdit('<s:property  value="industryCode"/>');"><%=++cnt%>
									<% ++i; %>
									</td>


									<td width="35%" align="left" class="sortableTD"
										title="Double click for edit"
										ondblclick="javascript:callForEdit('<s:property  value="industryCode"/>');"><s:hidden
										value="%{industryCode}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" value="<%=i%>" /><s:property
										value="industryName" /></td>
									<td width="27%" align="left" class="sortableTD"
										title="Double click for edit"
										ondblclick="javascript:callForEdit('<s:property  value="industryCode"/>');"><s:property
										value="industryAbbr" /></td>
									<script>
													specArray[<%=counter%>] = '<s:property  value="industryCode"/>';
										</script>
									<td width="15%" align="center" class="sortableTD"
										title="Double click for edit"
										ondblclick="javascript:callForEdit('<s:property  value="industryCode"/>');"><s:property
										value="industryStatus" /></td>
									</td>
									<td align="center" class="sortableTD" id="ctrlShow" width="15%" colspan="1"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick="callForDelete1('<s:property  value="industryCode"/>','<%=i%>')" /></td>
								</tr>
								<%counter++; %>
							</s:iterator>
							<%
							d = i;
							%>
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
</s:if>
		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td colspan="3" width="25%" align="Right">
			<s:if test="flagShow"><b>Total no. of records
			:&nbsp;<s:property value="totalRecords" /></b></s:if></td>
		</tr>
	</table>
</s:form>
<script>

var f9Fields= ["paraFrm_industryName", "paraFrm_industryAbbr"];
var fieldName = ["paraFrm_industryName", "paraFrm_industryAbbr"];
var lableName = ["indu.name", "indu.abbr"];
var type = ['enter','enter'];

window.onload = document.getElementById('pageNoField').focus();

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="IndustryMaster_addNew.action";
	document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{

	document.getElementById('paraFrm').action="IndustryMaster_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
			var del="IndustryMaster_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
		   return true;
	} else{
	     return false;
	}
}
//For F9Window

function searchFun()
{
	callsF9(500,300,"IndustryMaster_f9Action.action");
}

//For Cancel Button
function cancelFun(){
    	document.getElementById('paraFrm').action="IndustryMaster_cancelSecond.action";
        document.getElementById("paraFrm").submit();
}
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="IndustryMaster_report.action";
    document.getElementById("paraFrm").submit();
}
	
function callDelete(){
	if(document.getElementById('paraFrm_industryName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record?');
	if(con){
		   document.getElementById('paraFrm').action="IndustryMaster_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function newRowColor(cell){
			cell.className='Cell_bg_first';

}
	
function oldRowColor(cell,val) {
	
	//if(val=='1'){
	 cell.className='Cell_bg_second';
	//}else {
	//cell.className='tableCell1';
	//	}
}
	
	
	
	
function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	
	   	document.getElementById("paraFrm").action="IndustryMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   
   

  	
function callForDelete1(id,i){
	  if(document.getElementById('confChk'+i).checked == true){	  
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
	 	    return true;
	   }	   
	  }
	  return false;
}	

function chkDelete(){
	 var flag='<%=d %>';
	
	 if(chk()){
	 var con=confirm('Do you really want to delete the records?');
	 if(con){
	   document.getElementById('paraFrm').action="IndustryMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	     var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('checkAll').checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	    }
	     return false;
	     }
	 } else {
	 	alert('Please select atleast one record to delete');
	 	 return false;
	 }
} 

function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
}	
function checkAllBox(){
	 var rowLen ='<%=d %>';
	 if (document.getElementById("checkAll").checked){
	   for(var idx=1;idx<=rowLen;idx++){
	    document.getElementById("confChk"+idx).checked =true;
	    document.getElementById('hdeleteCode'+idx).value=specArray[idx-1];
	   
      }
	}else{
	  for (var idx = 1; idx <= rowLen; idx++) {
	   document.getElementById("confChk"+idx).checked = false;
	   document.getElementById('hdeleteCode'+idx).value="";
      }
    }	 
}

</script>

