<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="AssetMaster" validate="true" id="paraFrm" theme="simple">
<s:hidden name="code"/>
<s:hidden name="assetname"/>
<s:hidden name="subTypeName"/>
<s:hidden name="invType"/>
<s:hidden name="hiddencode"/>
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt"><strong class="text_head">Asset
					Master </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%" align="left">
				<table width="100%" align="left" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					  	<% int totalPage = 0; int pageNo = 0; %>
					  	<s:if test="makeList">
						<td id="ctrlShow" width="30%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'AssetMaster_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'AssetMaster_callPage.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a>
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'AssetMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'AssetMaster_callPage.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AssetMaster_callPage.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>						
						</td>						
						</s:if>						
					</tr>
				</table>
			</td>
		</tr>
		<!-- list -->
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
					<tr>
						<td class="txt"><strong class="text_head">Asset Master
						  </strong></td>
						<td colspan="1" align="right">
							<s:submit cssClass="delete"  theme="simple" value=" Delete"  onclick=" return chkDelete()"/>
						</td>
					</tr>	
					<tr>
						<td colspan="2">
							<% try { %>
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<s:hidden name="myPage" id="myPage" />
								<tr>
									<td class="formth" >
										<label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
									</td>
									<td class="formth">
										<label  class = "set" name="asset.masterCode" id="asset.masterCode" ondblclick="callShowDiv(this);"><%=label.get("asset.masterCode")%></label>
									</td>										
									<td class="formth">
										<label  class = "set" name="asset.type" id="asset.type" ondblclick="callShowDiv(this);"><%=label.get("asset.type")%></label>
									</td>
									<td class="formth">
										<label  class = "set" name="asset.subType" id="asset.subType" ondblclick="callShowDiv(this);"><%=label.get("asset.subType")%></label>
									</td>										
									<td  align="center" class="formth" id="ctrlShow">
										<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" 
										onclick="selectAllRecords(this);" />
									</td>
								</tr>
								<% int count=0; %>
								<%! int d=0; %>
								<%
									int i = 0;
								int cnt= pageNo*20-20;
									%>
								<s:iterator value="iteratorList">
									<tr 
									<%if(count%2==0){
										%>
										 class="tableCell1" <%}else{%>
										class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="code"/>');">
										<s:hidden value="%{code}" id='<%="code" + i%>'></s:hidden>
										<script type="text/javascript">
											records[<%=i%>] = document.getElementById('<%="code"+i%>').value;
										</script>	
										<td width="10%" align="left" class="sortableTD"><%=++cnt%><%++i;%></td>
										<td align="left" class="sortableTD"><s:property value="code" /></td>	
										<td align="left" class="sortableTD">
	                                                                        <s:property value="assetname" /> 
										<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
										</td>
										<td align="left" class="sortableTD"><s:property value="subTypeName" /></td>
										<td  align="center" nowrap="nowrap"  id="ctrlShow" class="sortableTD"><input type="checkbox"
											class="checkbox" id="confChk<%=i%>" name="confChk"
											onclick="callForDelete('<s:property value="code"/>','<%=i%>')"  di/></td>
									</tr>
								</s:iterator>
								<%d=i; %>
								<tr>
									<s:if test="makeList"></s:if>
											<s:else>
											<td colspan="5" align="center"><font color="red">No Data To Display</font></td>
										 	</s:else>
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
		<tr>
			<td width="100%" align="left">
				<table width="100%" align="left" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<s:if test="makeList">
						<td align="right"><b>Total No. of Records : <s:property value="totalRecords"/></b></td>
						</s:if>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

	function addnewFun()
	{
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "AssetMaster_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	function searchFun()
	{
			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
		document.getElementById('paraFrm').action = "AssetMaster_f9action.action";
		document.getElementById('paraFrm').submit();
	}
	function callForEdit(val){
		document.getElementById('paraFrm_hiddencode').value=val;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "AssetMaster_callForEdit.action";
		document.getElementById('paraFrm').submit();
	}
	function selectAllRecords(object) {
		if(object.checked) {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hdeleteCode' + i).value = records[i-1];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
	}
	function newRowColor(cell) {
		 cell.className='Cell_bg_first'; 
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
		 cell.className='tableCell2';
		}
		else 
		cell.className='tableCell1';
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

	function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you really want to  delete the record ?');
	 if(con){
	   document.getElementById('paraFrm').action="AssetMaster_deleteCheckedRecords.action";
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
</script>