<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
html {
	height: 100%;
}

form {
	display: inline;
}

noScroll {
	
}
</style>
<script>
	var intcount = 0;
</script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<div style="verticalAlign: top" id="entireDiv"><s:form
	theme="simple" id="paraFrm" name="paraFrm">
	<s:hidden name="recordCount" value="%{recordCount}" />
	<s:hidden name="previousFlag" value="%{previousFlag}" />
	<s:hidden name="hdF9SearchIndex" value="%{hdF9SearchIndex}" />
	<s:hidden name="hdPage" value="%{hdPage}" />
	<s:hidden name="hdadvanced" value="%{hdadvanced}" />
	<s:hidden name="rowId" value="%{rowId}" />
	<s:hidden name="f9query" />

	<%	String integratedLink = (String)request.getAttribute("integratedLink");
					String contextPath = request.getContextPath();
					String masterAction = request.getContextPath() + integratedLink;
					String f9Action = request.getRequestURI();
				%>
	<div id='ShowSearch'>
	<%	
			String[] width = (String[]) request.getAttribute("headerWidth");
			String[] heads = (String[]) request.getAttribute("headerNames");
			int pages = (Integer) request.getAttribute("pages");
			int PageNo = (Integer) request.getAttribute("PageNo");
			int totalPage = (Integer) request.getAttribute("TOTAL_RECORDS");
		%>


	<table width="100%" align="center" cellspacing="0" class="formbg">
		<tr>
			<td width="100%" colspan="4">


			<table width="100%" class="formbg">
				<tr>
					<td>Show results where:</td>
					<td>
					<% int count1 = 0; %> <select name="f9SearchIndex"
						id="paraFrm_f9SearchIndex">
						<% for (int i = 0; i < heads.length; i++) { %>
						<option value="<%=i%>"><%=heads[i]%></option>
						<% } %>
					</select> <s:select name="f9SearchSelect" onchange="searchselect();"
						list="#{'BW':'Begins With','E':'Exacts With','BT':'Between(a and b)','CAV':'Contains all Values',
							'CALT':'Contains at least one value','CNV':'Contains none of the values','DNBW':'Does not begin with',
							'ET':'Equal to','GT':'Greater than','IL':'Is Like','IN':'Is Null','INL':'Is Not Like','INN':'Is Not Null',
							'LT':'Less than','NBT':'Not Between(a and b)','NET':'Not Equal To'}"></s:select>
					<s:textfield name="f9SearchText" size="10"
						onfocus="if(document.getElementById('paraFrm_f9SearchSelect').value =='IN' || document.getElementById('paraFrm_f9SearchSelect').value =='INN' ){document.getElementById('paraFrm_f9SearchText').value='null';this.blur();}" />
					<s:submit cssClass="pagebutton" theme="simple" value="  Go  "
						onclick="searchRecord()" /> <input type="button"
						class="pagebutton" theme="simple" value="  Clear  "
						onclick="clearData('<%=heads.length + 1%>');" /> <a href="#"
						onclick="advance('<%=heads.length + 1%>')">Advanced</a></td>
					<td align="right"><input type="button" value="Close" class="cancel"
						onclick="javascript:closethis()" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<div id="divadv" style="display: none;"><s:hidden
						name="hdClick" value="N" />
					<table cellspacing="0" width="100%">
						<% for (int k = 0; k < heads.length + 1; k++) { %>
						<tr>
							<td width="20%" align="right"><select
								name="f9SearchCodition" id="f9SearchCodition<%=k%>">
								<option selected value="A">AND</option>
								<option value="O">OR</option>
							</select></td>
							<td width="80%"><select name="f9SearchIndex1"
								id="f9SearchIndex1<%=k%>">
								<% for (int i = 0; i < heads.length; i++) { %>
								<option value="<%=i%>"><%=heads[i]%></option>
								<% } %>
							</select> <select name="f9SearchSelect1" size="1"
								id="f9SearchSelect1<%=k%>">
								<option selected value="BW">Begins With</option>
								<option value="E">Exacts With</option>
								<option value="BT">Between(a and b)</option>
								<option value="CAV">Contains all Values</option>
								<option value="CALT">Contains at least one value</option>
								<option value="CNV">Contains none of the values</option>
								<option value="DNBW">Does not begin with</option>
								<option value="ET">Equal to</option>
								<option value="GT">Greater than</option>
								<option value="IL">Is Like</option>
								<option value="IN">Is Null(null)</option>
								<option value="INL">Is Not Like</option>
								<option value="INN">Is Not Null</option>
								<option value="LT">Less than</option>
								<option value="NBT">Not Between(a and b)</option>
								<option value="NET">Not Equal To</option>
							</select> <input type="text" name="f9SearchText1" id="f9SearchText1<%=k%>"
								size="10" /> <s:hidden name="f9SearchText1_2"
								value="%{f9SearchText1}" /> <s:hidden name="f9SearchSelect1_2"
								value="%{f9SearchSelect1}" /> <s:hidden name="f9SearchIndex1_2"
								value="%{f9SearchIndex1}" /> <s:hidden
								name="f9SearchCodition1_2" value="%{f9SearchCodition}" /></td>
						</tr>
						<% } %>
					</table>
					</div>
					</td>
				</tr>
			</table>

			</td>
		</tr>

		<tr>
			<td width="100%" colspan="4" align="right">
			<table width="100%" class="formbg" height="20px">
				<tr>
					<td colspan="1" align="left" class="iconImage">Records :<%=totalPage%></td>
					<td colspan="2" align="right">
					<% if (!(PageNo == 1)) { %> <a href="#" onclick="callPage('1');">
					<img src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()">
					<img src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /> </a> <% } 
							if (pages <= 5) {
	 							for (int z = 1; z <= pages; z++) { %> &nbsp;<a href="#"
						onclick="callPage('<%=z %>');"> <% if (PageNo == z) { %> <b><u><%=z%></u></b>
					<% } else { %> <%=z%> </a> <% }
	 							}
	 						} else {
	 							if (PageNo == pages - 1 || PageNo == pages) {
	 								for (int z = PageNo - 2; z <= pages; z++) { %> &nbsp;<a
						href="#" onclick="callPage('<%=z %>');"> <% if (PageNo == z) { %>
					<b><u><%=z%></u></b> <% } else { %> <%=z%> </a> <% }
	 								}
	 							} else if (PageNo <= 3) {
	 								for (int z = 1; z <= 5; z++) { %> &nbsp;<a href="#"
						onclick="callPage('<%=z %>');"> <% if (PageNo == z) { %> <b><u><%=z%></u></b>
					<% } else { %> <%=z%></a> <% }
	 								}
	 							} else if (PageNo > 3) {
	 								for (int z = PageNo - 2; z <= PageNo + 2; z++) { %> &nbsp;<a
						href="#" onclick="callPage('<%=z %>');"> <% if (PageNo == z) { %>
					<b><u><%=z%></u></b> <% } else { %> <%=z%></a> <% }
	 								}
	 							}
	 						}
	 						if (!(PageNo == pages)) {
	 							if (pages == 0) {
								} else { %> ...<%=pages%> <a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
						href="#" onclick="callPage('<%=pages%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /> </a> <% }
	 						} %>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
	<table width="100%" align="center" cellspacing="0" class="formbg">
		<tr>
			<td colspan="4" width="100%"><s:hidden name="pageNo" />
			<div id="scrollDiv"
				style="width: 100%; height: 285px; overflow: auto;border: 0.5px solid #CCC;">
			<table cellspacing="1" width="100%" class="sortable" >
				<% 	String[] displayData = (String[]) request.getAttribute("displayData");
							int colNum = heads.length;
							String fields = (String) request.getAttribute("fieldNames");
							String[][] obj = (String[][]) request.getAttribute("f9Data");
							int[] colIndex = (int[]) request.getAttribute("columnIndex");
		
							String submitToMethod = (String) request.getAttribute("submitToMethod");
							String submitFlag = (String) request.getAttribute("submitFlag");
						%>
				<tr id="f9_header">
					<%	int i = 0;
								while (i < colNum) {									
							%>
					<td class="formth" width="<%=width[i]%>%"><%=heads[i]%></td>
					<%		i++;									
								}
							%>
				</tr>
				<% 	for (int k = 0; k < obj.length; k++) { %>
				<tr style="cursor: hand" onmouseover="javascript:newRowColor(this);"
					onmouseout="javascript:oldRowColor(this);"
					onClick="javascript:getRecord('<%=k%>','<%=fields%>','<%=displayData[k]%>','<%=submitFlag%>','<%=submitToMethod%>')">
					<%	int j = 0;
									while (j < colNum) {
								%>
					<td class="sortableTD"><%=obj[k][j]%>&nbsp;</td>
					<%		j++;
									}
								%>
				</tr>
				<% } %>
			</table>
			</div>
			</td>
		</tr>
	</table>
	<div id='f9BottomInfo'>
	<table width="100%" align="center" cellspacing="0" class="formbg">
		<tr>
			<td width="100%" colspan="4">
			<table cellspacing="0" width="100%" class="formbg" height="20px">
				<tr>
					<td colspan="1" align="left" class="iconImage" nowrap="nowrap">Records
					:<%=totalPage%></td>
					<td colspan="2" width="100%" align="right">
					<% 	if (!(PageNo == 1)) { %> <a href="#" onclick="callPage('1');">
					<img src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()">
					<img src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /> </a> <% 	}
	 						if (pages <= 5) {
		 						for (int z = 1; z <= pages; z++) { %> &nbsp; <a href="#"
						onclick="callPage('<%=z %>');"> <%	if (PageNo == z) { %> <b><u><%=z%></u></b>
					<% 	} else { %> <%=z%> </a> <% 	}
		 						}
		 					} else {
		 						if (PageNo == pages - 1 || PageNo == pages) {
		 							for (int z = PageNo - 2; z <= pages; z++) { %> &nbsp;<a
						href="#" onclick="callPage('<%=z %>');"> <%	if (PageNo == z) { %>
					<b><u><%=z%></u></b> <% 	} else { %> <%=z%></a> <% 	}
		 							}
		 						} else if (PageNo <= 3) {
		 							for (int z = 1; z <= 5; z++) { %> &nbsp;<a href="#"
						onclick="callPage('<%=z %>');"> <% 	if (PageNo == z) { %> <b><u><%=z%></u></b>
					<%	} else { %> <%=z%></a> <%	}
		 							}
		 						} else if (PageNo > 3) {
		 							for (int z = PageNo - 2; z <= PageNo + 2; z++) { %> &nbsp;<a
						href="#" onclick="callPage('<%=z %>');"> <%	if (PageNo == z) { %>
					<b><u><%=z%></u></b> <% 	} else { %> <%=z%></a> <% 	}
		 							}
		 						}
		 					}
		 					if (!(PageNo == pages)) {
		 						if (pages == 0) {
				 				} else { %> ...<%=pages%> <a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
						href="#" onclick="callPage('<%=pages%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /> </a> <% }
		 					} %>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	</div>
</s:form></div>
<script>
	function closethis() {
		try{
			parent.document.all.f9_iframe.style.display='none';
		}catch(e) {
			window.close();	
		}
	}
	
	function addNew(contextPath, masterAction, f9Action) {
		document.getElementById('paraFrm').action = contextPath + '/AppStudio/ShowMaster.action?masterAction=' + masterAction + '&f9Action=' + f9Action;
		document.getElementById('paraFrm').submit();
	}

	function setSearch1(){
		var sear = document.getElementById('paraFrm_hdF9SearchIndex').value;
		document.getElementById('paraFrm_f9SearchIndex').value=sear;
	}

	firstCall();

	function searchselect() {
		var check = document.getElementById('paraFrm_f9SearchSelect').value;
		if(document.getElementById('paraFrm_f9SearchSelect').value =='IN' || document.getElementById('paraFrm_f9SearchSelect').value == 'INN') {
			document.getElementById('paraFrm_f9SearchText').value = "null";
		}
	}
	
	function newRowColor(cell) {
		cell.className = "Cell_bg_first";
	}
	
	function oldRowColor(cell) {
		cell.className = "Cell_bg_second";
	}

	function getRecord(ids,fields,values,submitFlag,methodName) {
		var dropFlag=true;
		if(fields.length > 0) {
		try{			
			fieldList = fields.split(",");
			valuesList = values.split("***");
			for(i = 0; i < fieldList.length; i++) {
			try{
				fieldList[i] = fieldList[i].replace(".","_");
				try { 
					fld = eval("parent.document.getElementById('paraFrm_" + fieldList[i]+"')");
					if(fld==null) {
						dropFlag=false;
						fld = eval("opener.document.getElementById('paraFrm_" + fieldList[i]+"')");
					}	
				} catch(e) {
					alert(e);
				}
				if(valuesList[i] == "null") {
					fld.value = "";
				} else {	
             try{

					fld.value = valuesList[i];	
					}catch(e){};									
				}
				
				}catch(e){};
			}
			}catch(e){};
		}
		if(submitFlag == 'true') {
			if(dropFlag){
				parent.document.getElementById('paraFrm').target = "main";
				parent.document.getElementById('paraFrm').action = methodName;
				parent.document.getElementById('paraFrm').submit();
			}else {
				opener.document.getElementById('paraFrm').target = "main";
				opener.document.getElementById('paraFrm').action = methodName;
				opener.document.getElementById('paraFrm').submit();
			}
		}
		if(dropFlag){
			parent.document.all.f9_iframe.style.display='none';
		} else {
			window.close();
		}		
	}

	function handleError() {
		alert('Error occured');
		return true;
    }
    
    function clearData(id) {  
		for(i = 0; i < id; i++) {				
			document.getElementById('f9SearchText1' + i).value = "";	
			document.getElementById('f9SearchSelect1' + i).value = "BW";	
			document.getElementById('f9SearchIndex1' + i).value = "0";		
			document.getElementById('f9SearchCodition' + i).value = "A";	
		}
		document.getElementById('paraFrm_f9SearchText').value = "";
		document.getElementById('paraFrm_f9SearchSelect').value = "BW";	
		document.getElementById('paraFrm_f9SearchIndex').value = "0";
		searchRecord();
	}
    
    function next() {
    	var idPage = document.getElementById('paraFrm_pageNo').value;
    	if(idPage == "") {
    		idPage = 1
    	}
    	document.getElementById('paraFrm_recordCount').value = (idPage) * 25 + 25;
	    document.getElementById('paraFrm_pageNo').value = eval(idPage) + 1;
    	var idPage11 = document.getElementById('paraFrm_pageNo').value;
    	document.getElementById('paraFrm').submit();   	    	
    }
    
    function advance(count) {
    	if(document.getElementById('divadv').style.display == 'none') {
    		document.getElementById('divadv').style.display = 'block';
      		document.getElementById('paraFrm_hdadvanced').value = 'sha'; 
       		document.getElementById('paraFrm_hdClick').value = 'Y';   
       		
      		var newHeight = eval(270 - count * 15);
       		document.getElementById('scrollDiv').style.height = newHeight;
		} else {
			document.getElementById('divadv').style.display = 'none'; 
      		document.getElementById('paraFrm_hdadvanced').value = '';
       		document.getElementById('paraFrm_hdClick').value = 'N';
        	document.getElementById('scrollDiv').style.height = 270;
		}
	}

    function callOnLoad() {
    	document.getElementById('divadv').style.display = 'none';
    }
    
    function previous() {
    	var idPage = document.getElementById('paraFrm_pageNo').value;
    	document.getElementById('paraFrm_recordCount').value = (idPage - 2) * 25 + 25;
	    document.getElementById('paraFrm_pageNo').value = eval(idPage) - 1;
    	document.getElementById('paraFrm').submit();
	}
    
    function searchRecord() {
    	var str = document.getElementById('paraFrm_f9SearchText1_2').value;
    	var id = document.getElementById('paraFrm_pageNo').value = 1;
  		document.getElementById('paraFrm_recordCount').value = (id - 1) * 25 + 25;
		var recCount = document.getElementById('paraFrm_f9SearchIndex').value;
	    document.getElementById('paraFrm_hdF9SearchIndex').value = recCount;
    	document.getElementById('paraFrm').submit();
    }
    
    function firstCall() { 
    	setSearch1();
      	var hadv = document.getElementById('paraFrm_hdadvanced').value;
       	if(hadv == 'sha') {
     		document.getElementById('divadv').style.display = 'block';
     		var hdclick = document.getElementById('paraFrm_hdClick').value = 'Y'; 
        }
        var str = document.getElementById('paraFrm_f9SearchText1_2').value;
        var strSelect = document.getElementById('paraFrm_f9SearchSelect1_2').value;
        var strSelectIndex = document.getElementById('paraFrm_f9SearchIndex1_2').value;
        var strSelectCond = document.getElementById('paraFrm_f9SearchCodition1_2').value;
        
        if(str.length > 0) {		
			fieldList = str.split(", ");
			for(i = 0; i < fieldList.length; i++) {	
				if(fieldList[i] == " ") {
					document.getElementById('f9SearchText1' + i).value = "";
				} else {
					document.getElementById('f9SearchText1' + i).value = fieldList[i];	
				}
			}
		}
		
       	if(str.length > 0) {
			fieldList = strSelect.split(", ");
			for(j = 0; j < fieldList.length; j++) {	
				if(fieldList[j] == " ") {
					document.getElementById('f9SearchSelect1' + j).value = "";
				} else {
					document.getElementById('f9SearchSelect1' + j).value = fieldList[j];
				}
			}
		}

		if(str.length > 0) {  		     
		   	fieldList = strSelectIndex.split(", ");
			for(k = 0; k < fieldList.length; k++) {	
				if(fieldList[k] == " ") {
					document.getElementById('f9SearchIndex1' + k).value = "";
				} else {
					document.getElementById('f9SearchIndex1' + k).value = fieldList[k];
				}
			}
		}
		
		if(str.length > 0) {  		     
			fieldList = strSelectCond.split(", ");
			for(k = 0; k < fieldList.length; k++) {	
				if(fieldList[k] == " ") {
					document.getElementById('f9SearchCodition' + k).value = "";
				} else {
					document.getElementById('f9SearchCodition' + k).value = fieldList[k];
				}
			}
		}	
    }
    
    function callPage(id) {
    	document.getElementById('paraFrm_pageNo').value = id;
	   	document.getElementById('paraFrm_recordCount').value = (id - 1) * 25 + 25;
	   	document.getElementById('paraFrm').submit();
   	}
    
    self.focus();
	window.onerror = handleError;	
</script>