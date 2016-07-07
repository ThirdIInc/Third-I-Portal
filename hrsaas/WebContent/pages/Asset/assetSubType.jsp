<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="AssetSubTypes" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
	
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="assetsubTypeCode" />
	<s:hidden name="assetCategoryName" />
	<s:hidden name="assetSubTypeName" />
	<s:hidden name="returnType" />
	<s:hidden name="unit" />
	<s:hidden name="invType" />
	<s:hidden name="assetCategoryCode" />

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
					<td width="93%" class="txt"><strong class="text_head">Asset Sub Type</strong></td>
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
				<table width="100%" align="left" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					  	<% int totalPage = 0; int pageNo = 0; %>
					  	<s:if test="listLength">
						<td id="ctrlShow" width="30%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'AssetSubTypes_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'AssetSubTypes_callPage.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a>
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'AssetSubTypes_callPage.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'AssetSubTypes_callPage.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AssetSubTypes_callPage.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>						
						</td>						
						</s:if>						
					</tr>
				</table>
			</td>
		</tr>
			<tr>
				<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="formbg">
						<tr>
							<td class="txt" colspan="2"><strong class="text_head">Asset
							Sub Type  </strong></td>						
							<td colspan="1" align="right">
								<s:submit
										cssClass="delete" theme="simple" value="     Delete"
										onclick=" return chkDelete()" />
							</td>
						</tr>
						<tr>
							<td colspan="3">
							<%
							try {
							%>
							<table width="100%" border="0" cellpadding="0" cellspacing="1"
								class="formbg">
								<tr class="sortable">
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center">
										<label  class = "set" name="assetsubtype.srno" id="assetsubtype.srno" ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.srno")%></label>
									</td>
									<td class="formth" align="center">
										<label  class = "set" name="assetsubtype.cat" id="assetsubtype.cat1" ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.cat")%></label>
									</td>
									<td class="formth" align="center">
										<label  class = "set" name="assetsubtype.name" id="assetsubtype.name1" ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.name")%></label>
									</td>
									<td class="formth" align="center">
										<label  class = "set" name="assetsubtype.rettype" id="assetsubtype.rettype1" ondblclick="callShowDiv(this);"><%=label.get("assetsubtype.rettype")%></label>
									</td>
									
									<td class="formth" align="center" width="05%"><label
										class="set" name="is.active" id="is.active1"
										ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									</td>
									
									<td align="right" class="formth" id="ctrlShow">
										<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" 
												onclick="selectAllRecords(this);" />					
									</td>
				
				
									<%
											int count = 0;
											%>
									<%!int d = 0;%>
									<%
											int i = 0;
											int cnt= pageNo*20-20;
											%>
									<s:iterator value="iteratorlist">
				
										<tr <%if(count%2==0){
													%> class="tableCell1" <%}else{%>
											class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											ondblclick="javascript:callForEdit('<s:property value="hdeleteCode"/>');">
											<script type="text/javascript">
												records[<%=i%>] = document.getElementById('hdeleteCode' + '<%=i%>').value;									
											</script>	
				
											<td width="10%" align="center" class="sortableTD"><%=++cnt %><%++i;%></td>
											<s:hidden value="%{assetSubTypeCode}"></s:hidden>
											<td width="30%" align="left" class="sortableTD"><s:property
												value="categoryIterator" /> <input type="hidden"
												name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
											<td width="30%" align="left" class="sortableTD"><s:property
												value="subTypeNameIterator" /> <input type="hidden"
												name="subTypeCodeIterator" /></td>
											<td width="20%" align="left" class="sortableTD"><s:property
												value="returnTypeIterator" /></td>
											
											<td width="40%" align="left" class="sortableTD"><s:property
															value="isActive" /></td>
											
											
											<td width="10%" align="center" nowrap="nowrap" id="ctrlShow" class="sortableTD"><input
												type="checkbox" class="checkbox" id="confChk<%=i%>"
												name="confChk"
												onclick="callForDelete1('<s:property value="subTypeCodeIterator"/>','<%=i%>')" /></td>
										</tr>
				
									</s:iterator>
									<%
											d = i;
											%>
								</tr>
							</table>
													<s:if test="listLength"></s:if>
												<s:else>
													<table width="100%">
														<tr>
												<td align="center"><font color="red">No Data To Display</font></td>
											</tr>
											</table></s:else>
							<%
										} catch (Exception e) {
										}
									%>
							</td>
						</tr>
					</table>
				</td>
			</tr>						
		<tr>
			<td width="100%" align="left" colspan="3">
				<table width="100%" align="left" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<s:if test="listLength">
							<td align="right"><b>Total No. of Records: <s:property value="totalRecords" /></b></b></td>
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
			document.getElementById("paraFrm").action = 'AssetSubTypes_f9action.action';
			document.getElementById("paraFrm").submit();
	}

	function addnewFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='AssetSubTypes_addNew.action';
		document.getElementById("paraFrm").submit();
	}
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'AssetSubTypes_report.action';
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



function callsave(type)
 {
 

/*var fieldName = ["paraFrm_assetname","paraFrm_type","paraFrm_unit"];
var lableName = ["Asset category name","Asset category type","Asset category unit"];*/

var fieldName = ["paraFrm_assetCategoryName","paraFrm_assetSubTypeName","paraFrm_returnType","paraFrm_invType","paraFrm_unit"];
var fieldNameSpecial = ["paraFrm_assetSubTypeName"];
var lableName = ["assetsubtype.cat","assetsubtype.name","assetsubtype.rettype","assetsubtype.invtype","assetsubtype.unit"];
var types = ["select","enter","select","select","select"];
if(type == 'update'){
		if(document.getElementById('paraFrm_assetsubTypeCode').value==""){
			alert("Please select a record to update.");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_assetsubTypeCode').value==""){
			alert("Please click on Update button to update the record.");
  			return false;
			}
		
	}


      if(!validateBlank(fieldName,lableName,types))
          return false;
        
      if(!f9specialchars(fieldNameSpecial))
      {
              return false;
       }
      
     	    return true;
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
	   	document.getElementById("paraFrm").action="AssetSubTypes_callForEdit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete( ){
	  	//document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="AssetSubTypes_calfordelete1.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   function callForDelete1(id,i)
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
			//cell.className='onOverCell';
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
	   document.getElementById('paraFrm').action="AssetSubTypes_deleteMultiple.action";
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
	function callReturnable()
	{
		if(document.getElementById("paraFrm_returnType").value=="R"){
		document.getElementById("paraFrm_invType").value="I";
		document.getElementById("paraFrm_hiddenInvType").value="I";
		}
		else if(document.getElementById("paraFrm_returnType").value=="N"){
		document.getElementById("paraFrm_invType").value="S";
		document.getElementById("paraFrm_hiddenInvType").value="S";
		}
		else 
		document.getElementById("paraFrm_invType").value="";
		document.getElementById("paraFrm_hiddenInvType").value="";
	}
	function callReturnable1()
	{
	
		if(document.getElementById("paraFrm_invType").value=="I"){
		document.getElementById("paraFrm_returnType").value="R";
		}
		else if(document.getElementById("paraFrm_invType").value=="S"){
		document.getElementById("paraFrm_returnType").value="N";
		}
		else 
		document.getElementById("paraFrm_returnType").value="";
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
		//alert("length  " +records.length);
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

