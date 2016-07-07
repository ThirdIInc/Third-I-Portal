<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<style type="text/css">
  #notDisplay
  { 
  display:none; 
  }
</style>
<s:form action="DataReconcile" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="show" />
	<s:hidden name="queryParameter" />
	<s:hidden name="tableFlagCheckedCount" />
	<s:hidden name="filters" />
	<s:hidden name="filtersDefault" id="filtersDefault"/>
	<s:hidden name="dataReconcileFlag" />
	<s:hidden name="sourceId" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="noOfColumns"></s:hidden>
	<s:hidden name="primaryQuery"></s:hidden>
	<s:hidden name="filterQuery"></s:hidden>
	<s:hidden name="paginationQuery"></s:hidden>
	<s:hidden name="primaryFlagColumnNo"></s:hidden>
	<table width="100%" align="left" class="formbg"  border="0">
	<!-- Table 1 started  -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
			<!-- Table 2 started  -->
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Data
					Reconciliation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			<!-- Table 2 ended  -->
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left"><input type="button" class="reset"
				value=" Reset " onclick="return backFun();" /></td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
			<!-- Table 3 started  -->
				<tr>
					<td colspan="4">
					<table width="100%" border="0">
						<!-- Table 4 started  -->
						<tr>
							<td width="15%" colspan="1"><label id="dataSource"
								ondblclick="callShowDiv(this);"><%=label.get("dataSource")%></label>
							:</td>
							<td nowrap="nowrap"><s:textfield name="sourceName" size="25"
								maxlength="100" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage" id="ctrlShow"
								onclick="titleAction();" /> <input type="button" value="GO"
								Class="add" onclick="return callColumnsInfo();"></td>

						</tr>
					</table>
					<!-- Table 4 ended  -->
					</td>
				</tr>
			
				<tr>
					<td colspan="4">
					<% int columnList=0; %>
					<table width="100%" border="0	" class="formbg">
						<!-- Table 5 started  -->
							
					
					
					
					<s:if test="%{bean.filters}">
					  <tr>
						<td height="22" align="left" width="2%" valign="top"><strong class="forminnerhead">Filters</strong></td>
					</tr>
					<tr valign="top">
							<td colspan="1" width="85%" valign="top"><input name="Add Filter"
							id="Add" value="Add Filter" type="button" onclick="addRowsToTable();"></td>
					</tr>
					
					<tr>
						<td>				
							
					<table id="filterId" width="5%" border="0">
							<!-- Table 6 started  -->
							<tr  id="notDisplay">
							<td> <s:select list="filterList"
									listKey="columnName" listValue="columnName" name="filterTab" id="paraFrm_filterTables">
									</s:select> </td> 
							<td></td>  
							<td></td>
							<td></td></tr>
					<s:iterator value="filterPageList" status="stat" >
					
							<tr id="rowAdd" valign="top">

						 <td valign="top"><s:select list="filterList"
									listKey="columnName" listValue="columnName" name="filterTables" id="paraFrm_filterTables">
									</s:select> 
								</td> 	
								<td valign="top"><s:select
									list="#{'=':'Equal to', '!=':'Not Equal to', '>':'Greater Than', '<':'Less Than', 'LIKE ':'Is Like', 'NOT LIKE ':'Is Not Like', 'IS NULL':'Is Null', 'IS NOT NULL':'Is Not Null'}"
									name="conditions"></s:select></td>
								<td valign="top"><s:textfield name="inputCondition"
									size="25" maxlength="50" id="paraFrm_inputCondition" /></td>
								<%//filterIds++; 
									%>
								<td align="center" class="sortableTD"><img
								src="../pages/common/css/icons/delete.gif"   
								onclick="deleteCurrentRow(this);" /></td>
							</tr>
					</s:iterator>
							</table>
							<!-- Table 6 ended  -->
							</td>
							
							</tr>
						</s:if>

						<s:else>
							<tr>
								<td height="22" align="center" width="2%" style="display: none;"></td>
							</tr>
						</s:else>
						<!-- Table 5 ended  -->
						</table>
						</td>
						</tr>

							<s:if test="%{bean.tableFlagCheckedCount}">
							<tr class="formbg"> 
								<td height="22" align="left" width="100%" colspan="4"><strong class="forminnerhead">Columns</strong></td>
								<% columnList= (Integer)request.getAttribute("ColumnList"); 
								%>
								
							</tr>
							</s:if>
							<s:else>
							<tr>
								<td height="22" align="center" style="display: none;"></td>
							</tr>
							</s:else>
							
							<tr>
								<td colspan="4">
								<div style="width:100%; height:120px; overflow:auto;" >
								<table >
								<!-- Table 7 started  -->
								
							<%
								int j = 1;
								int check =1;
							int countField = 0;
							 
								%>

						<s:iterator value="columnList" status="stat">
<% if(countField%4==0)
{
	
	%>
	<tr class="sortableTD">
	<%} %>																
									<td><input type="checkbox" class="checkbox"
									name="tableFlag" id="<%="tableFlag"+j%>"
									onclick="callChecked('tableFlag<%=j%>', 'tableFlag<%=j%>');"  />

								<input type="hidden" class="text" name="tableFlagHidden"
									id="<%="tableFlagHidden"+j%>"
									value='<s:property value="tableFlagHidden" />' /></td>
									
								<td width="25%"><s:hidden name="columnId" /><s:hidden
									name="columnName" /><s:property value="columnName" /><s:hidden name="primaryFlag" id="<%="primaryFlag"+j%>"></s:hidden>
								
								</td>
							




								<%
										j++;
								countField++;
								if(countField%4==0){%>
								</tr>
								<%
								}
								else if(countField==columnList){
										%>
										</tr>
										<% 
										}
										%>
							
						</s:iterator>
						 
						</table>
							</div>
						 
						
						<!-- Table 7 ended  -->
						<s:if test="%{bean.filters}">
					<tr align="center">
					<td colspan="4">
					<table align="center" width="100%">
					<tr>
					<td   valign="top" align="center" ><input name="Select"
							id="Select" value="Select All" type="button" onclick="selectAllCheckBox();">
							<input name="Reset"
							id="Reset" value="Reset All" type="button" onclick="resetAllCheckBox();">
							
						<input name="Search"
							id="Search" value="Search" type="button" onclick="displayData();"></td>
					</tr>
					</table>
					</td>
						
					</tr>
					</s:if>
					</td>
					</tr>
					
						
						
						<s:iterator value="checkBoxList" status="stat">
						<s:hidden name="checkBox" id="<%="checkBox"+check%>" />
						<%check++; %>
						</s:iterator>
						

						<input type="hidden" name="otherLengthVar" id="otherLengthVar"
							value="<%=j%>" />
					
					
							
					
				
				

				

	
			
		


		<s:if test="%{bean.dataReconcileFlag}">
			<tr>
				<td colspan="4">
				<table width="100%" border="0" align="left" cellpadding="0"
					cellspacing="0" class="formbg">
					<!-- Table 8 started  -->
					
						<%
												int totalPage = 0;
													int pageNumber = 0;
											%>
											<tr>
											<td>
											<table  width="100%"> 
											<!-- Table 9 started  -->
						<s:if test="modeLength">
						<tr>
							<td width="20%" class="text_head"><strong
								class="forminnerhead">Data Reconcile  </strong></td>

							<td id="ctrlShow" width="80%" align="right" class=""><b>Page:</b>
							<%
													totalPage = (Integer) request.getAttribute("totalPage");
														pageNumber = (Integer) request.getAttribute("pageNumber");
												%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage%>', 'DataReconcile_hasData.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage%>', 'DataReconcile_hasData.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNumber" id="pageNumber" size="3" value="<%=pageNumber%>"
								maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'DataReconcile_hasData.action');return numbersOnly();" />
							of <%=totalPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage%>', 'DataReconcile_hasData.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'DataReconcile_hasData.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
						</s:if>
						</table>
						<!-- Table 9 ended  -->
						</td>
						</tr>
					
				


			<%
										int count = 0;
										%>
			<%!int d = 0; %>
			<%
												int i = 0;
												int cn = pageNumber * 20 - 20;
										%>







		<tr>
						<td  width="85%" valign="top"><input name="Save"
							id="Save" value="Save" type="button" onclick="updateData();"></td>
		</tr>
		<tr>
			<td></td>
			</tr>
		<tr>
			<td width="100%" align="left">
			<div style="width:780px; overflow:auto;" >
				<table width="100%" border="0" cellpadding="0" cellspacing="2" align="left">
				<!-- Table 10 started  -->
			<%
		Object[][] data =(Object[][]) request.getAttribute("data"); 
 		String noOfColumns = (String)request.getAttribute("noOfColumns");
 		int primaryFlagColumnNo = (Integer) request.getAttribute("primaryFlagColumnNo");
		int noOfCol = Integer.parseInt(noOfColumns);
		
			%>
			
			
			<tr align="left" >
				<%
			for(int b=0; b<noOfCol;b++)
			{
				
			%>
				<td class="formth" align="left"><%=String.valueOf(data[0][b])%></td>
				<%
			}
				%>
			</tr>
				<%
		String pageIndex0 = (String)request.getAttribute("pageIndex[0]");
		Integer start = Integer.parseInt(pageIndex0);
		System.out.println("pageIndex[0] " +start);
		
		String pageIndex1 = (String)request.getAttribute("pageIndex[1]");
		Integer end = Integer.parseInt(pageIndex1);
		System.out.println("pageIndex[1] " +end);
		
		if(pageIndex0.equals("0") && pageIndex1.equals("1"))
		{
			%>
			<table width="100%">
			<!-- Table 11 started  -->
			
				<tr>
					<td align="center"><font color="red">No Data To
					Display</font></td>
				</tr>
			</table>
			<!-- Table 11 ended  --><%
		}
		
	//	if (pageIndex[4].equals("1"))
	//		bean.setMyPageInProcess("1");
		
		if(pageNumber == totalPage)
		{
			end=end-1;
		}
		
		for( int z=start+1; z < end+1; z++)
			{
				%>
			<tr>
				<%
				for(int b=0; b<noOfCol;b++)
				{
				
					if(b<primaryFlagColumnNo)
					{
				%>

				<td><input type="text" name="<%=String.valueOf(data[0][b])%>"
					value="<%=String.valueOf(data[z][b])%>"  readonly="readonly"></td>

			<%
					}
					else{
						%>
						<td><input type="text" name="<%=String.valueOf(data[0][b])%>"
					value="<%=String.valueOf(data[z][b])%>"></td>
						<%
						
					}
			}//end of for loop
			%>
			
				</tr>
				
				<% 
			}//end of outer for loop
		d = i; 
		%>		
		
		</table>
		<!-- Table 10 ended  -->
		</div>
		</td>
			
		</tr>
		<tr>
			<td></td>
			</tr>
		<tr>
						<td  width="85%" valign="top"><input name="Save"
							id="Save" value="Save" type="button" onclick="updateData();"></td>
		</tr>
		</s:if>
		
		<s:else>
		<tr>
			<td height="22" align="center" style="display: none;"></td>
		</tr>
		</s:else>
		</table>
		<!-- Table 8 ended  -->
				</td>
			</tr>

	</table>
	<!-- Table 3 ended  -->
	</td>
	</tr>
	</table>
	<!-- Table 1 ended  -->

</s:form>


<script>

onLoad();

	function onLoad() {
			try{
			
			var count=0;
			var countClient=0;
			
			var listCount = document.getElementById('otherLengthVar').value;
			//	alert(listCount);
			
				for(var i = 1; i < listCount; i++)
				{ 
					if(document.getElementById('primaryFlag'+i).value== 'Y')
					{
					document.getElementById('tableFlag'+i).checked = true;
					document.getElementById('tableFlag'+i).disabled = true;
					}
					else if(document.getElementById('primaryFlag'+i).value== 'N'){
						document.getElementById('tableFlag'+i).checked = false;
						
					}
					else if(document.getElementById('checkBox'+i).value== 'Y'){
					document.getElementById('tableFlag'+i).checked = true;
					document.getElementById('tableFlagHidden'+i).value="Y";
					}
					
				}
			//	alert("123");
			//	alert(document.getElementById('filtersDefault').value);
				
			
				
			}
			catch(e){
			////alert(e);
			}
}




function titleAction(){
	javascript:callsF9(500,325,'DataReconcile_dataSource.action');
}
function callColumnsInfo(){
	try{
		
		var sourceId = document.getElementById('paraFrm_sourceId').value;
		//alert(sourceId);
		if(sourceId==''){  
		alert("Please select data source.");
		      document.getElementById('paraFrm_sourceId').focus();
		      return false;
	        }
	       
	       }catch(e){alert(e);}
	       
	       
	       
	         document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'DataReconcile_displayColumns.action?sourceId=' +sourceId;	
	    document.getElementById('paraFrm').submit();
	  

}
	

function displayData(){
		document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'DataReconcile_getDataReconciliation.action';	
	    document.getElementById('paraFrm').submit();
}

function selectAllCheckBox(){

var listCount = document.getElementById('otherLengthVar').value;

	for(var i = 1; i < listCount; i++) {
		document.getElementById('tableFlag'+i).checked = true;
		document.getElementById('tableFlagHidden'+i).value="Y";
	}
}

function resetAllCheckBox(){

var listCount = document.getElementById('otherLengthVar').value;

	for(var i = 1; i < listCount; i++) {
		document.getElementById('tableFlag'+i).checked = false;
		document.getElementById('tableFlagHidden'+i).value="N";
	}
}
	
function callChecked(checkBox, field){
		try{
			var count=1;
			var crmFlag=1;
			var clientFlag=1;
		
		var listCount = document.getElementById('otherLengthVar').value;
			if(document.getElementById(checkBox).checked == true){	  
				document.getElementById(field).value="Y";
				for(var i = 1; i < listCount; i++) {
					if(document.getElementById('tableFlag'+i).checked == true){
						crmFlag=crmFlag+1;
						document.getElementById('tableFlagHidden'+i).value="Y";
					}
				}
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  			if(!document.getElementById('tableFlag'+i).checked == true){
					 	document.getElementById('tableFlagHidden'+i).value="N";
					}
				
	  		}
	  	} 
	  	} catch(e){  
	  		////alert(e); 
	  	}
	}	
	
	function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
		return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DataReconcile_input.action';
		document.getElementById('paraFrm').submit();
	}

	
	// Application In-Process List Begins
	function callPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNumber = document.getElementById('pageNumber').value;	
		actPage = document.getElementById('myPageInProcess').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNumber == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNumber').focus();
				return false;
			}
		  	if(Number(pageNumber) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNumber').value = actPage;
			    document.getElementById('pageNumber').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNumber)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNumber').value=actPage;
			    document.getElementById('pageNumber').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNumber == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNumber').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNumber) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNumber').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNumber == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNumber').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNumber) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNumber').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNumber').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNumber').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageInProcess').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNumber =document.getElementById('pageNumber').value;
		 	var actPage = document.getElementById('myPageInProcess').value;   
	     
		 	 
	        if(pageNumber==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNumber').focus();
			 return false;
	        }
		   if(Number(pageNumber)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNumber').focus();
		     document.getElementById('pageNumber').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNumber))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNumber').focus();
		     document.getElementById('pageNumber').value=actPage;  
			 return false;
		    }
		    
		    if(pageNumber==actPage){  
		      document.getElementById('pageNumber').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageInProcess').value=pageNumber;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Application In-Process List Ends
	
function addRowsToTable(){

  var tbl = document.getElementById('filterId');
  //alert(tbl);
   var lastRow = tbl.rows.length;
  // if there's no header row in the table, then iteration = lastRow + 1
  //alert(lastRow);
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
   //
   var cell1 = row.insertCell(0);
   
   var a = document.getElementById('paraFrm_filterTables');
  // alert(a.length);
   var b= document.getElementsByName('filterTables');
   var ed1 = document.createElement('select');
    ed1.id = 'paraFrm_filterTables'+iteration ;
  	ed1.name = 'filterTables';
   for(var i=0; i<a.length;i++)
   {
  
   
   var option = document.createElement('option');
   
   var x=document.getElementById('paraFrm_filterTables').selectedIndex;
			var selected = document.getElementById('paraFrm_filterTables').options;
		
	option.value = document.getElementById('paraFrm_filterTables').options[i].text;
	option.text=document.getElementById('paraFrm_filterTables').options[i].text;
	ed1.options.add(option);
	}
	cell1.appendChild(ed1);
  
   //
   var cell2 = row.insertCell(1);
  
    var selector = document.createElement('select');
	selector.id = 'paraFrm_conditions'+iteration;
	selector.name = 'conditions';
	var option = document.createElement('option');
	option.value = '=';
	option.text="Equal to";
	selector.options.add(option);
	option = document.createElement('option');
	option.value = '!=';
	option.text="Not Equal to";
	selector.options.add(option);
	option = document.createElement('option');
	option.value = '> ';
	option.text="Greater Than";
	selector.options.add(option);
	option = document.createElement('option');
	option.value = '<';
	option.text="Less Than";
	selector.options.add(option);
	option = document.createElement('option');
	option.value = 'LIKE ';
	option.text="Is Like";
	selector.options.add(option);
	option = document.createElement('option');
	option.value = 'NOT LIKE ';
	option.text="Is Not Like";
	selector.options.add(option);
	option = document.createElement('option');
	option.value = 'IS NULL';
	option.text="Is Null";
	selector.options.add(option);
	option = document.createElement('option');
	option.value = 'IS NOT NULL';
	option.text="Is Not Null";
	selector.options.add(option);
	
	cell2.appendChild(selector);
  
  
  
   //
   var cell4 = row.insertCell(2);
   var ed4 = document.createElement('input');
   ed4.type = 'text';
   ed4.name = 'inputCondition' ;
   ed4.id = 'paraFrm_inputCondition'+iteration;
   ed4.size =25;
   ed4.maxlength=50;
   cell4.appendChild(ed4);
   //
   
   var cell5= row.insertCell(3);
		 var column8 = document.createElement('img');
		  cell5.className='sortableTD';
		  column8.type='image';
		  column8.src="../pages/common/css/icons/delete.gif";
		  column8.align='absmiddle';
	  	  column8.id='img'+ iteration;
		  column8.theme='simple';
		  //cell8.align='center';

		  column8.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell5.appendChild(column8);
		 
   
 }
	
	function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			//if(rIndex!= 0)
			//{
			rowObjArray[i].parentNode.deleteRow(rIndex);
			//}
			//else if(rIndex== 0)
			//{
			//var link = document.getElementById('filterId');
			//link.style.display = 'none';
			//}
		} 
	}
	
function deleteCurrentRow(obj){
		var conf=confirm("Are you sure to delete this record?");   
   		if(conf) {
			var delRow = obj.parentNode.parentNode;
			var tbl = delRow.parentNode.parentNode;
			var rIndex = delRow.sectionRowIndex;
			var rowArray = new Array(delRow);
			deleteRows(rowArray);
		}
	}
	
function updateData()
{
		document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'DataReconcile_updateData.action';	
	    document.getElementById('paraFrm').submit();

}		
		</script>