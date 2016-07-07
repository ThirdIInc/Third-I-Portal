<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form theme="simple" id="paraFrm" name="paraFrm">
<table class="formbg" cellpadding="0" cellspacing="0" width="100%" style="margin: 0;padding: 0;">
	        <% String  header = (String)request.getAttribute("header");
        		String  textAreaField = (String)request.getAttribute("textAreaField");
        		String  hiddenId = (String)request.getAttribute("hiddenId");
        		String  submitFlag = (String)request.getAttribute("submitFlag");
        		String  submitToMethod = (String)request.getAttribute("submitToMethod");
        		System.out.println("textAreaField==="+textAreaField);
        		System.out.println("hiddenCodeId==="+hiddenId);
        		String[][] obj = (String[][]) request.getAttribute("multiSelectData");
        		System.out.println("obj length==="+obj.length);
        	%>
	<tr>
	<td colspan="2" width="100%">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" style="border: 0.5pt solid #ccc;"><!--FINAL TABLE -->	
			<tr><td colspan="2">
							<table width="100%" style='border-top: 1px;'>
								<tr>
									<td align="left" width="60" valign="middle" nowrap="nowrap"><a href="#"
										onclick="callSelect('<s:property value='<%=submitFlag%>'/>','<s:property value='<%=submitToMethod%>'/>','<%=obj.length%>');"><img
										align="absmiddle"  src='../pages/images/buttonIcons/ok.png' style="padding-right: 5px;border: 0;"/>Ok</a></td>
									<td align="left" width="100" valign="middle" nowrap="nowrap">										
										<a href="#" onclick="try{document.getElementById('f9_div').style.display='none';}catch(e){window.close();}"><img
										align="absmiddle"  src='../pages/images/buttonIcons/cancel.png' style="padding-right: 5px;border: 0;"/>Cancel</a>
										</td>		
									<td align="right" nowrap="nowrap" width="80%"><b> Total Records :</b>
									<%=obj.length%></td>

								</tr>
							</table></td>
						</tr>
			<tr>
			<td width="100%" colspan="2">
				<div id="scrollDiv" style="width: 100%;overflow-y: yes;height: 175px; margin: 0;padding: 0;overflow: auto;">
					<table cellpadding="0" cellspacing="0" width="100%" class="sortable" >
						
						<tr class="sortable">	
						
						<td width="20%" valign="top" class="sortableTD" align="center" style='border-top: 0.5pt solid #c2c2c2;'><input type="checkbox" name="check" id="checkAll" onclick="return selectAll(<%=obj.length%>);"/></td>
					<td width="80%" valign="top" class="sortableTD" style='border-top: 0.5pt solid #c2c2c2;'>(Select All)</td>
					</tr>
						
						<%!int j = 0;
	int m = 0;%> 
							<%
 							for (int k = 0; k < obj.length; k++) {
 							%>
						<tr class="sortable"> 
						<td class="sortableTD" style='border-top: 0.5pt solid #c2c2c2;' width="20%" align="center"><input type="checkbox"   name="check" id="check<%=k%>" onclick="callAllCheck(<%=k%>);" value='<s:property value="check"/>'/>	
								</td>
							<td class="sortableTD" style='border-top: 0.5pt solid #c2c2c2;' width="80%"><%=obj[k][1]%><input type="hidden" name="multiListCode" id="multiListCode<%=k%>"  value='<%=obj[k][0]%>'/>
							<input type="hidden" name="multiListName" id="multiListName<%=k%>"  value='<%=obj[k][1]%>'/></td>
							
								<%  
																j++;
																}
																m = j;
																j = 0;
															%>
							</tr>
						
					</table>
				</div>
			</td>
		</tr>
					
					
	</table></td></tr>
	


	<!--FINAL TABLE -->	

	<input type='hidden' name='hiddenId' id='hiddenId' value='<%=hiddenId%>'/>
	<input type='hidden' name='textAreaField' id='textAreaField' value='<%=textAreaField%>'/>
	<input type='hidden' name='totalCount' id='totalCount' value='<%=m%>'/>
	</table>
	
</s:form>

<script>
//var textId=document.getElementById('testRequirements').value;
//onloadSelect(document.getElementById('totalCount').value);
function onloadSelect(){
	//alert(2);
	try{
	//alert("value1=="+opener.document.getElementById(document.getElementById('hiddenId').value).value);
	 var listLength='<%=m%>'; 
	 var count=0;
	 var textId1=opener.document.getElementById(document.getElementById('hiddenId').value).value;
  	 var fieldList_code=textId1.split(",");
  
   for(var k=0;k<value;k++)
   {
        for(var i=0; i < fieldList_code.length;i++) {
		    
		if(document.getElementById('multiListCode'+k).value==fieldList_code[i])
	      		{
		      document.getElementById('check'+k).checked=true;
		      count++;
		      }  
		   }
    }
    if(count==listLength){
    	document.getElementById('checkAll').checked = true;
    }
    }catch(e){
    	alert(e);
    }
    //alert(3);
}
function  selectAll(){
	checkValue=document.getElementById('checkAll').checked;
	var value='<%=m%>';
	for (var c=0;c<value;c++){
		document.getElementById('check'+c).checked =checkValue; 
	}
}
function callSelect(submitFlag, actionName)
{
//alert('in callSelect');
 var value='<%=m%>'; 
//alert('in callSelect'+value);
 var count=1;
 var displayValue = '';
 var hiddenValue='';
 var chkBox;
 	try{
 	for(var z=0;z<value;z++)
   {
   chkBox = document.getElementById('check'+z).checked;
   	//alert('z=='+z+' and chkBox=='+chkBox);
   	if(chkBox)
   		{
   			//displayValue+=count+". ";
   			//displayValue+=document.getElementById('multiListName'+z).value+"  \n";
   			displayValue+=document.getElementById('multiListName'+z).value+"; ";
   			hiddenValue+=document.getElementById('multiListCode'+z).value+",";
   		count++;
   		}
   		
   }
  	displayValue = displayValue.substring(0,displayValue.length-2);
    hiddenValue = hiddenValue.substring(0,hiddenValue.length-1);
    
    opener.document.getElementById(document.getElementById('textAreaField').value).value=displayValue;
   	opener.document.getElementById(document.getElementById('textAreaField').value).style.height='15px';
   	opener.document.getElementById(document.getElementById('textAreaField').value).style.height = (opener.document.getElementById(document.getElementById('textAreaField').value).scrollHeight+5)+"px"
   	
    
   	opener.document.getElementById(document.getElementById('textAreaField').value).value=displayValue;
 	opener.document.getElementById(document.getElementById('hiddenId').value).value=hiddenValue;
 	
   if(submitFlag == 'true') {
			opener.document.getElementById('paraFrm').target = "";
			opener.document.getElementById('paraFrm').action = actionName;
			opener.document.getElementById('paraFrm').submit();
		}
}catch(e)
   			{
   				alert(e);
   			}
window.close();
}
function callAllCheck(id){
	var checkValue=true;
	if(!document.getElementById('check'+id).checked){
		checkValue=false;
		
	}else{
	
	var value='<%=m%>';
	for (var c=0;c<value;c++){
		if(!document.getElementById('check'+c).checked){
			checkValue = false;
			break;
			}
		}
	}
	document.getElementById('checkAll').checked = checkValue;
}
function newRowColor(cell)
   	 {
   	 
			cell.className='onOverCell';

	}
	function oldRowColor(cell,val) {
	
		if(val=='1'){
			 cell.className='tableCell2';
			}
		else 
			cell.className='tableCell1';
		
	}
	

function checkTr(rowId){
	if(document.getElementById('check'+rowId).checked){
		document.getElementById('check'+rowId).checked=false;
	}else{
		document.getElementById('check'+rowId).checked=true;
	}
	callAllCheck(rowId);
}
resizeTo(document.body.scrollWidth,270);
</script>