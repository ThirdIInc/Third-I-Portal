<script>
var intcount=0;
</script>


<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form theme="simple" id="paraFrm" >
<s:hidden name="propInitBy" value="%{propInitBy}" />
<s:hidden name="forwardId" value="%{forwardId}" />
<s:hidden name="recordCount" value="%{recordCount}" />
<s:hidden name="previousFlag" value="%{previousFlag}" />
<s:hidden name="hdF9SearchIndex" value="%{hdF9SearchIndex}" />
<s:hidden name="hdPage" value="%{hdPage}" />
<s:hidden name="hdadvanced" value="%{hdadvanced}" />
<s:hidden name="f9query"/>

	<table cellspacing="0" width="100%">
		    <!--<tr>
			<td>Search:</td>
			<td colspan="2"><s:textfield name="f9SearchText" tabindex="1"
				size="55" />Search With<s:select name="f9SearchSelect" list="#{'':'--Select--','S':'Start With','E':'End With','EX':'Exact With'}"></s:select></td>
			<tr>		
			-->
			
			<%	String[] width = (String[]) request.getAttribute("headerWidth");
				String[] heads = (String[]) request.getAttribute("headerNames");
				int pages = (Integer) request.getAttribute("pages");
				int PageNo = (Integer) request.getAttribute("PageNo");
		
			%>	
			<tr >	
			<td>Show results where:</td>			
			<td>
			<% int count1=0; %>
			<select name="f9SearchIndex">
				<%
				for (int i = 0; i < heads.length; i++) {
				%>
				<option value="<%=i%>"><%=heads[i]%></option>
				<%
				}
				%>
			</select>
			<s:select name="f9SearchSelect" list="#{'BW':'Begins With','E':'Exats With','BT':'Between(a and b)','CAV':'Contains all Values','CALT':'Contains at least one value','CNV':'Contains none of the values','DNBW':'Does not begin with','ET':'Equal to','GT':'Greater than','IL':'Is Like','IN':'Is Null','INL':'Is Not Like','INN':'Is Not Null','LT':'Less than','NBT':'Not Between(a and b)','NET':'Not Equal To'}"></s:select>
			<!--<s:select name="f9SearchCompare" list="#{'':'--Select--','1':'=','2':'<>','3':'>','4':'>=','5':'<','6':'<=','7':'LIKE','8':'BETWEEN'}"></s:select>-->
			<s:textfield name="f9SearchText" size="10" />
			<input type="button"   value="  Go  " tabindex="2" onclick="searchRecord()"/>	
		    <input type="button"  value="  Clear  " tabindex="2" onclick="clearData('<%=heads.length+1%>');"/>
			<a href="#" onclick="advance('<%=heads.length+1%>')" > Advanced</a> 
				</td>
		</tr>
		<tr>
		<td colspan="2">
		<div id="divadv" style="display: none;">
		<s:hidden name="hdClick" value="N"></s:hidden>
		<table cellspacing="0" width="100%">
		
			
			<% 
				for (int k = 0; k < heads.length+1; k++) {
					
				%>
			<tr>
			<td width="20%" align="right">  <select name="f9SearchCodition" id="f9SearchCodition<%=k%>"> 
			<!--<option  value="A">AND</option>
			<option value="O">OR</option>-->
		       <option selected value="A">AND</option>
				<option value="O">OR</option>
			 	</select>
			 </td><!-- <s:select name="f9SearchCodition" list="#{'A':'AND','O':'OR'}"></s:select> -->
			<td width="80%"><select name="f9SearchIndex1" id="f9SearchIndex1<%=k%>"> 
				<%
				for (int i = 0; i < heads.length; i++) {
				%>
				
				<option value="<%=i%>"><%=heads[i]%></option>
				<%
				}
				%>
			</select>
			<select name="f9SearchSelect1" size="1" id="f9SearchSelect1<%=k%>" >
				<option selected value="BW">Begins With</option>
				<option value="E">Exats With</option>
				<option value="BT" >Between(a and b)</option>
				<option value="CAV">Contains all Values</option>
				<option value="CALT">Contains at least one value </option>
				<option value="CNV">Contains none of the values</option>
				<option value="DNBW">Does not begin with</option>
				<option value="ET">Equal to </option>
				<option value="GT">Greater than </option>
				<option value="IL">Is Like </option>
				<option value="IN">Is Null </option>
				<option value="INL">Is Not Like </option>
				<option value="INN">Is Not Null </option>
				<option value="LT">Less than </option>
				<option value="NBT">Not Between(a and b) </option>
				<option value="NET">Not Equal To </option>
				</select>
				
			<!--<s:select name="f9SearchSelect1"  list="#{'BW':'Begins With','E':'Exats With','BT':'Between','CAV':'Contains all Values','CALT':'Contains at least one value','CNV':'Contains none of the values','DNBW':'Does not begin with','ET':'Equal to','GT':'Greater than','IL':'Is Like','IN':'Is Null','INL':'Is Not Like','INN':'Is Not Null','LT':'Less than','NBT':'Not Between','NET':'Not Equal To'}"></s:select>
			<s:select name="f9SearchCompare" list="#{'':'--Select--','1':'=','2':'<>','3':'>','4':'>=','5':'<','6':'<=','7':'LIKE','8':'BETWEEN'}"></s:select>-->
			
			<input type="text" name="f9SearchText1" id="f9SearchText1<%=k%>"  size="10"   />
			<s:hidden name="f9SearchText1_2"  value="%{f9SearchText1}"/>
			<s:hidden name="f9SearchSelect1_2"  value="%{f9SearchSelect1}"/>
			<s:hidden name="f9SearchIndex1_2"  value="%{f9SearchIndex1}"/>
			<s:hidden name="f9SearchCodition1_2"  value="%{f9SearchCodition}"/>
				</td>
		
		</tr>
		<%
				}
				%>
		</table>
		</div>
		</td>
		</tr>
				<tr>
			<td colspan="3" width="25%" align="right" >&nbsp;  </td>
		</tr>
		<tr>
			<td colspan="3" width="25%" align="right" >
			<%  if(!(PageNo==1))
			{
				%><a href="#" onclick="callPage('1');" > <img src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/> </a>&nbsp; <a href="#" onclick="previous()" ><img src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/></a><%	
			}	
			if(pages <=5) {
			for(int z=1;z<=pages;z++) {%>&nbsp;<a href="#" onclick="callPage('<%=z %>');"><%if(PageNo==z){ %><b><u><%=z %></u></b><% } else { %><%=z %></a><%}	}	
			}
			else{				
				if(PageNo ==pages-1 ||PageNo ==pages){									
					for(int z=PageNo-2;z<=pages;z++) {%>&nbsp;<a href="#" onclick="callPage('<%=z %>');"><%if(PageNo==z){ %><b><u><%=z %></u></b><% } else { %><%=z %></a><% } }							
				}
				else if(PageNo <=3){
				for(int z=1;z<=5;z++) {%>&nbsp;<a href="#" onclick="callPage('<%=z %>');"><%if(PageNo==z){ %><b><u><%=z %></u></b><% } else { %><%=z %></a><% } }						
				}
				else if(PageNo >3){
				for(int z=PageNo-2;z<=PageNo+2;z++) {%>&nbsp;<a href="#" onclick="callPage('<%=z %>');"><%if(PageNo==z){ %><b><u><%=z %></u></b><% } else { %><%=z %></a><%}}					
				}				 
				}
			if(!(PageNo==pages))
			{
				if(pages==0)
				{
					
				}
				else{
				%>...<%=pages %><a href="#" onclick="next()" > <img src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;<a href="#" onclick="callPage('<%=pages%>');" > <img src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/></a> <%	
				}
				}
			%>
			 </td>
	
		</tr>
	<tr><td colspan="3">
	<s:hidden name="pageNo" />
	<div class="scrollF9" id="scrollDiv">	
	
	<table cellspacing="1" width="100%">
		<%
				String[] displayData = (String[]) request
				.getAttribute("displayData");
				int colNum = heads.length;
				String fields = (String) request.getAttribute("fieldNames");
				String[][] obj = (String[][]) request.getAttribute("f9Data");				
				int[] colIndex = (int[]) request.getAttribute("columnIndex");

				String submitToMethod = (String) request
				.getAttribute("submitToMethod");
				String submitFlag = (String) request.getAttribute("submitFlag");

				System.out.println("heads " + heads.length);
		%>
		<tr>
			<%
					int i = 0;
					while (i < colNum) {
			%>
			<td class="headerCell" width="<%=width[i]%>%"><%=heads[i]%></td>
			<%
					i++;
					}
			%>
		</tr>
		<%
				
				for (int k = 0; k < obj.length; k++) {
					
		%>
		<tr  style="cursor:hand" onmouseover="javascript:newRowColor(this);"
			onmouseout="javascript:oldRowColor(this);"
			onClick="javascript:getRecord('<%=k%>','<%=fields%>','<%=displayData[k]%>','<%=submitFlag%>','<%=submitToMethod%>')">
			<%
						int j = 0;
						while (j < colNum) {
			%>
			<td class="F9TD"><%=obj[k][j]%>&nbsp;</td>
			<%	j++;
				}
			%>
		</tr>
		<%
		}
		%>		
	</table>
	</div></td></tr>
	<tr>
			<!--<td colspan="3" width="25%" align="right" ><a href="#" onclick="previous()" ><< Previous</a> ||<a href="#" onclick="next()" > Next >> </a>  </td>
		--></tr>
	</table>
	
	
</s:form>

<script>
	   function setSearch1(){
			var sear = document.getElementById('paraFrm_hdF9SearchIndex').value;
		 	document.getElementById('f9SearchIndex').value=sear;
	}
	</script>
<script>
firstCall();

	function newRowColor(cell) {
		cell.style.backgroundColor="#C0EDFE";
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
	
	function oldRowColor(cell) {
		cell.style.backgroundColor="#ffffff";
	}

	function getRecord(ids,fields,values,submitFlag,methodName) {
		if(fields.length>0) {			
			fieldList=fields.split(",");
			valuesList=values.split("***");
			for(i=0; i < fieldList.length;i++) {	
				fieldList[i]=fieldList[i].replace(".","_");		
				fld = eval("opener.document.getElementById('paraFrm_"+fieldList[i]+"')");			
				fld.value=valuesList[i];				
			}
		}		
		if(submitFlag == 'true') {
				opener.document.getElementById('paraFrm').target="main";
				opener.document.getElementById('paraFrm').action=methodName;
				opener.document.getElementById('paraFrm').submit();
		}
		window.close();
	}

	function handleError() {
		alert('Error occured');
		return true;
    }    
    function clearData(id) {  
       		  		for(i=0; i < id;i++) {					
				document.getElementById('f9SearchText1'+i).value="";	
				document.getElementById('f9SearchSelect1'+i).value="BW";	
				document.getElementById('f9SearchIndex1'+i).value="0";		
				document.getElementById('f9SearchCodition'+i).value="A";	
			}
			document.getElementById('f9SearchText').value="";
			document.getElementById('f9SearchSelect').value="BW";	
				document.getElementById('f9SearchIndex').value="0";
       	}
    function next() {
    	//document.getElementById('paraFrm_previousFlag').value=false;
    	 var idPage=document.getElementById('pageNo').value;
    	 if(idPage==""){idPage=1}
    	document.getElementById('paraFrm_recordCount').value=(idPage)*25+25;
	    document.getElementById('pageNo').value=eval(idPage)+1;
    	var idPage11=document.getElementById('pageNo').value;
    	document.getElementById('paraFrm').submit();   	    	
    }
     function advance(count) {
    
     if(document.getElementById('divadv').style.display=='none')
     { document.getElementById('divadv').style.display='block';
      document.getElementById('hdadvanced').value='sha'; 
       document.getElementById('hdClick').value='Y';   
       // var divheight=document.getElementById('scrollDiv').style.height;
      var newHeight=eval(270-count*15);
       document.getElementById('scrollDiv').style.height=newHeight;
	
      }
       else
     {document.getElementById('divadv').style.display='none'; 
      document.getElementById('hdadvanced').value='';
       document.getElementById('hdClick').value='N';
        document.getElementById('scrollDiv').style.height=270;
        }        
     
        }
        
    function callOnLoad() {
    	document.getElementById('divadv').style.display = 'none';
        
     	    	    	}
    function previous() {
    	var idPage=document.getElementById('pageNo').value;
    	document.getElementById('paraFrm_recordCount').value=(idPage-2)*25+25;
	    document.getElementById('pageNo').value=eval(idPage)-1;
    	document.getElementById('paraFrm').submit();
    	
    }
    
    function searchRecord(){    
     var str=document.getElementById('f9SearchText12').value;
      
      
              	var id= document.getElementById('pageNo').value=1;
	   document.getElementById('paraFrm_recordCount').value=(id-1)*25+25;
    	 var recCount = document.getElementById('f9SearchIndex').value;
    	 document.getElementById('paraFrm_hdF9SearchIndex').value=recCount;
    	 document.getElementById('paraFrm').submit();
    	
    }  
    
    
    function firstCall()
    { setSearch1();
     //callOnLoad();
      var hadv = document.getElementById('hdadvanced').value;
       if(hadv=='sha')
     {
     document.getElementById('divadv').style.display='block';
     var hdclick=document.getElementById('hdClick').value='Y'; 
        }
        var str=document.getElementById('f9SearchText1_2').value;
        var strSelect=document.getElementById('f9SearchSelect1_2').value;
         var strSelectIndex=document.getElementById('f9SearchIndex1_2').value;
         var strSelectCond=document.getElementById('f9SearchCodition1_2').value;
         // alert('----condition--123-strSelectIndex---'+strSelectIndex); 
         //alert('----condition--123-strSelectCond---'+strSelectCond); 
             if(str.length>0) {			
			 fieldList=str.split(", ");
			for(i=0; i < fieldList.length;i++) {	
				if(fieldList[i]==" ")
				{document.getElementById('f9SearchText1'+i).value="";
				}
				else{
				document.getElementById('f9SearchText1'+i).value=fieldList[i];	
				}		
			}			
		}	
       if(str.length>0) {       		
			 fieldList=strSelect.split(", ");
			for(j=0; j < fieldList.length;j++) {	
			
				if(fieldList[j]==" ")
				{document.getElementById('f9SearchSelect1'+j).value="";
				}
				else{document.getElementById('f9SearchSelect1'+j).value=fieldList[j];}		
			}						
		}	
		 if(str.length>0) {  		     
		    fieldList=strSelectIndex.split(", ");
			for(k=0; k < fieldList.length;k++) {	
				if(fieldList[k]==" ")
				{document.getElementById('f9SearchIndex1'+k).value="";
				}
				else{document.getElementById('f9SearchIndex1'+k).value=fieldList[k];}		
			}
			}
			
			if(str.length>0) {  		     
		    fieldList=strSelectCond.split(", ");
			for(k=0; k < fieldList.length;k++) {	
				if(fieldList[k]==" ")
				{document.getElementById('f9SearchCodition'+k).value="";
				}
				else{document.getElementById('f9SearchCodition'+k).value=fieldList[k];}		
			}
			}
			
    }
     function callPage(id){
	   	document.getElementById('pageNo').value=id;
	   document.getElementById('paraFrm_recordCount').value=(id-1)*25+25;
	   document.getElementById('paraFrm').submit();
   }
    
    self.focus();    
	window.onerror = handleError;
	
</script>