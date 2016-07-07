<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	var intcount = 0;
</script>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<div style="verticalAlign:top" id="entireDiv">
<s:form theme="simple" id="paraFrm" name="paraFrm">
	<s:hidden name="recordCount" value="%{recordCount}" />
	<s:hidden name="previousFlag" value="%{previousFlag}" />
	<s:hidden name="hdF9SearchIndex" value="%{hdF9SearchIndex}" />
	<s:hidden name="hdPage" value="%{hdPage}" />
	<s:hidden name="hdadvanced" value="%{hdadvanced}" />
	<s:hidden name="rowId" value="%{rowId}" />
	<s:hidden name="f9query" />	

		<%	
			String[] width = (String[]) request.getAttribute("headerWidth");
			String[] heads = (String[]) request.getAttribute("headerNames");			
		%>

		
<table class="formbg" cellpadding="0" cellspacing="0" width="100%" style="margin: 0;padding: 0;">
		
		<% 	String[] displayData = (String[]) request.getAttribute("displayData");
							int colNum = heads.length;
							String fields = (String) request.getAttribute("fieldNames");
							String[][] obj = (String[][]) request.getAttribute("f9Data");
							int[] colIndex = (int[]) request.getAttribute("columnIndex");
		
							String submitToMethod = (String) request.getAttribute("submitToMethod");
							String submitFlag = (String) request.getAttribute("submitFlag");
						%>
		
	
		
		
		
		<tr><td colspan="<%=colNum%>%" width="100%" >
						<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" style="border: 0.5pt solid #ccc;"><!--FINAL TABLE -->	
								<tr>
									
									<td align="left"   valign="middle" nowrap="nowrap">										
										<a href="#" onclick="try{document.getElementById('f9_div').style.display='none';}catch(e){window.close();}"><img
										src='../pages/images/buttonIcons/cancel.png' /></a>
										</td>
										<td align="left"   valign="middle" nowrap="nowrap">
										<input type="hidden"   name="searchText1" id="searchText1" value="<%=colNum%>">
										<input type="text" size="10" name="searchText" id="searchText" onkeyup="callSearchText()">
										</td>			
									<td align="right" nowrap="nowrap"  ><b> Total :</b>
									<%=obj.length%></td>

								</tr>
							</table></td>
						</tr>
			<tr>
		
		
		<tr>
			<td colspan="4" width="100%">
				<s:hidden name="pageNo" />
				
				<div id="scrollDiv" class="scrollf9" style="width: 100%;overflow-y: yes;height: 140px; margin: 0;padding: 0;overflow: auto;">
					<table cellspacing="1" width="100%" style="border: 0.5pt solid #ccc;" border="0" id="tableID">
						
						
						
						
						<tr id="f9_header">
							<%	int i = 0;
								while (i < colNum) {									
							%>		
							<td nowrap="nowrap" align="center" class="formbg" width="<%=width[i]%>%"><strong><%=heads[i]%></strong></td>							
							<%		i++;									
								}
								
							%>
						</tr>
						<% 	for (int k = 0; k < obj.length; k++) { %>
							<tr id="TR<%=k%>"   style="cursor: hand" onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this);"
							onClick="javascript:getRecord('<%=k%>','<%=fields%>','<%=displayData[k]%>','<%=submitFlag%>','<%=submitToMethod%>')">
								<%	int j = 0;
									while (j < colNum) {
								%>		<td style='border-top: 0.5pt solid #c2c2c2;'  id="<%=k%><%=j%>" ><%=obj[k][j]%>&nbsp;
								<input type="hidden" size="2" name="nameText" id="nameText<%=k%><%=j%>" value='<%=obj[k][j]%>'>
								
								</td>
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
		
</s:form>
</div>
<script>

function callSearchText(){
	var sText=document.getElementById('searchText').value;
	var searchText1=document.getElementById('searchText1').value;
	var tbl = document.getElementById('tableID');
	var lastRow = tbl.rows.length;
	var aaa=(parseInt(searchText1)-1);
	//alert(sText);
		for(k = 0; k < lastRow; k++) {
			if(sText==''){
			document.getElementById('TR'+k).style.display = "";
			//document.getElementById(''+k+aaa).style.backgroundColor = "white";
			}
			else{
		//	for(aaa = 0; aaa < parseInt(searchText1); aaa++) {
				var value1=document.getElementById('nameText'+k+aaa).value;
				//document.getElementById(''+k+aaa).style.backgroundColor = "white";
				document.getElementById('TR'+k).style.display = "none";
				fieldList = value1.split(""+sText+"");
				if((value1.toUpperCase()).indexOf((sText.toUpperCase()))!='-1'){
				//alert(fieldList.length);
				//var is_dot_ok=value1.toUpperCase().indexOf(sText.toUpperCase());
				//if((fieldList.length>0) ){
				//if((value1.toUpperCase())==(sText.toUpperCase())){			
				//document.getElementById(''+k+aaa).style.backgroundColor = "white";
				document.getElementById('TR'+k).style.display = "";
				//}
				}			
			//}
		}	
}
}

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
			fieldList = fields.split(",");
			valuesList = values.split("***");
			for(i = 0; i < fieldList.length; i++) {
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

					fld.value = valuesList[i];										
				}
			}
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