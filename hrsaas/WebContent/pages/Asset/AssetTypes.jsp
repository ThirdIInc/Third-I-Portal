<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="AssetTypes" validate="true" id="paraFrm" name="paraFrm"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="listLength" />
	<s:hidden name="assetCode" />
	<s:hidden name="assetname" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset
					Category</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" align="left" colspan="3">
			<table width="100%" align="left" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
					<s:if test="listLength">
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'AssetTypes_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'AssetTypes_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'AssetTypes_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'AssetTypes_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AssetTypes_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td class="txt" colspan="2"><strong class="text_head">Asset
					Category </strong></td>
					<td colspan="1" align="right"><s:submit cssClass="delete"
						theme="simple" value=" Delete" onclick=" return chkDelete()" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg">
						<tr class="sortableTD">
							<s:hidden name="myPage" id="myPage" />
							<td class="formth"><label class="set" name="assettype.srno"
								id="assettype.srno" ondblclick="callShowDiv(this);"><%=label.get("assettype.srno")%></label>
							</td>

							<td class="formth"><label class="set" name="assettype.name"
								id="assettype.name1" ondblclick="callShowDiv(this);"><%=label.get("assettype.name")%></label>
							</td>
							<td class="formth" align="center" width="50%" ><label 
							 class = "set" name="is.active" id="is.active"
							 ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
							 </td>
							
							
							
							<td align="center" class="formth" id="ctrlShow"><input
								type="checkbox" id="selAll" style="cursor: hand;"
								title="Select all records to remove"
								onclick="selectAllRecords(this);" />
								</td>
							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
									int i = 0;
									int cnt = pageNo * 20 - 20;
							%>



						</tr>
						<s:iterator value="iteratorlist">
							<tr <%if(count%2==0){
												%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								title="Double click for edit"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								ondblclick="javascript:callForEdit('<s:property value="assetCode"/>');">
								<script type="text/javascript">
													records[<%=i%>] = document.getElementById('assetCode' + '<%=i%>').value;									
												</script>
								<td width="10%" align="left" class="sortableTD"><%=++cnt%>
								<%
								++i;
								%>
								</td>
								<s:hidden value="%{assetCode}" id='<%="assetCode" + i%>'></s:hidden>

								<td width="90%" align="left" class="sortableTD"><s:property
									value="assetname" /> <input type="hidden" name="hdeleteCode"
									id="hdeleteCode<%=i%>" /></td>
								<td width="40%" align="left" class="sortableTD"><s:property
															value="isActive" /></td>	
								
								<td align="center" nowrap="nowrap" id="ctrlShow"
									class="sortableTD"><input type="checkbox" class="checkbox"
									id="confChk<%=i%>" name="confChk"
									onclick="callForDelete('<s:property value="assetCode"/>','<%=i%>')"
									di /></td>
							</tr>
						</s:iterator>
						<input type="hidden" name="noofRecords" width="25" value="<%=i%>" />
						<%
						d = i;
						%>
					</table>
					<s:if test="listLength"></s:if> <s:else>
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
		<tr>
			<td width="100%">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<s:if test="listLength">
						<td align="right"><b>Total No. of Records: <s:property
							value="totalRecords" /></b></b></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>
<script><!--
	function searchFun() {
			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action = 'AssetTypes_f9action.action';
			document.getElementById("paraFrm").submit();
	}

	function addnewFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='AssetTypes_addNew.action';
		document.getElementById("paraFrm").submit();
	}
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'AssetTypes_report.action';
		document.getElementById('paraFrm').submit();
	}
	function callChk(id){
	//alert("document.getElementById(checkList+id).value=="+document.getElementById("checkList"+id).value);
	
		 if(document.getElementById("checkList"+id).value=='Y'){
		   	document.getElementById("checkList"+id).value='N';
		  	  }else  if(document.getElementById("checkList"+id).value=='N'){
		  	document.getElementById("checkList"+id).value='Y';
		  	 }
		  	// alert("document.getElementById(checkList+id).value=="+document.getElementById("checkList"+id).value);
		  	 }


	/*
   function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="AssetTypes_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
   */
   
   function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="AssetTypes_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="AssetTypes_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="AssetTypes_callPage.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}



	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="AssetTypes_callForEdit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    
   function callForDelete(id,i)
	   {
	   
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
   
   	function newRowColor(cell)
   	 {
			//			cell.className='onOverCell';
		 cell.className='Cell_bg_first'; 
	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
	function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you really want to  delete the record ?');
	 if(con){
	   document.getElementById('paraFrm').action="AssetTypes_deleteCheckedRecords.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {    
	    document.getElementById('selAll').checked=false;	     
	    var flag='<%=d %>';
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('confChk'+a).checked=false;
	    document.getElementById('hdeleteCode'+a).value="";
	 	}
	     return false;
	     }
	    
	 }
	 else {
	 alert('Please select atleast one record to delete.');
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
--></script>

