<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="TestSchedule" id="paraFrm" validate="true" theme="simple" target="main">
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Check List</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	<tr>
				<td align="left" width="25%" colspan="1"><s:hidden name="srNo"/>
					<input type="button" class="token" name="Ok" value=" OK " onclick="calculate();"/></td>
			</tr>
	<tr>
	<td colspan="3" width="100%">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0"><!--FINAL TABLE -->	
		
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--TABLE 1 -->
					<tr>
						<td width="10%" valign="top" class="sortableTD"><b>Sr.No.</b></td>	
						<td width="70%" valign="top" class="sortableTD"><b>Check List Name</b></td>
						<td width="20%" valign="top" class="sortableTD"><input type="checkbox"  name="chkAll" id="chkAll" onclick="chkAllChkBox();"/>	</td>
					</tr>
					<%int i = 1;%>
					<%!int j = 0;%>
					<s:iterator status="stat" value="testDataList" >
							<tr>	
								<td class="sortableTD" width="10%"><%=i%>
								</td>	
								<input type="hidden" name="checklistCode" id="checklistCode<%=i%>"  value='<s:property value="checklistCode"/>'/>
								<td class="sortableTD" width="70%">
									<input type="hidden" name="checklistName" value='<s:property value="checklistName"/>' id="checklistName<%=i%>"/>
										<s:property value="checklistName"/>
								</td>
								<td class="sortableTD" width="20%">
									<input type="checkbox"  name="check" id="check<%=i%>" value='<s:property value="check"/>'/>	
								</td>
							</tr>
							<%i++;%>
						</s:iterator>
						
						<%j = i;%>
				</table><!--TABLE 1 -->
			</td>
		</tr>
		<tr>
	          <td colspan="4" valign="bottom" class="txt"><img src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
	        </tr>
						
			<tr>
				<td align="left" width="25%" colspan="1"><s:hidden name="srNo"/>
					<input type="button" class="token" name="Ok" value=" OK " onclick="calculate();"/></td>
			</tr>			
	</table><!--FINAL TABLE -->	
	</td></tr></table>
	<s:hidden name="testRequirements" id="testRequirements" />
	<s:hidden name="testReqCode" id="testReqCode" /><s:hidden name="checkBoxFlag" id="checkBoxFlag"/>
</s:form>

<script>
//var textId=document.getElementById('testRequirements').value;

function chkAllChkBox(){

 var value='<%=j%>'; 

 if(document.getElementById('chkAll').checked==true){
    document.getElementById('checkBoxFlag').value="true";
 var textId1=document.getElementById('testReqCode').value;

 fieldLis_codet=textId1.split(" ");
 		for(k=1;k<=value;k++)
   {
    document.getElementById('check'+k).checked=true;
   
		  
    }
 }else{
 document.getElementById('chkAll').checked=false;
  document.getElementById('checkBoxFlag').value="false";
 	for(k=1;k<=value;k++)
   {
    document.getElementById('check'+k).checked=false;
   
		  
    }
 }
 
}

function setValue()
{

if(document.getElementById('checkBoxFlag').value=="true"){
document.getElementById('chkAll').checked=true;
}
try{
	 var value='<%=j%>'; 
	 
	 var textId1=document.getElementById('testReqCode').value;
  		 fieldLis_codet=textId1.split(" ");
  
  
     
     
   for(k=1;k<=value;k++)
   {
    
		     for(i=0; i < fieldLis_codet.length;i++) {
		    
		      if(document.getElementById('checklistCode'+k).value==fieldLis_codet[i] )
		      {
		      document.getElementById('check'+k).checked=true;
		      }  
		   }
    }
    }catch(e){
    	//alert(e);
    }
}
setValue();

function calculate()
{
//alert('in calculate');
 var value='<%=j%>'; 
 //alert('in calculate'+value);
 var count=1;
 var id = '';
 var id1='';
 var chkBox;
 	try{
 
   for(var i=1;i<value;i++)
   {
   		
   	
   		chkBox = document.getElementById('check'+i).checked;
   		if(chkBox == true)
   		{
   			id+=count+". ";
   			id+=document.getElementById('checklistName'+i).value+"  \n";
   			id1+=document.getElementById('checklistCode'+i).value+"  ";
   		count++;
   		}
   			
   }
   id = id.substring(0,id.length-2);
    id1 = id1.substring(0,id1.length-1);
   //alert('id    '+id);
   //alert('id1    '+id1);
  opener.document.getElementById('paraFrm_testRequirements').value=id;
 opener.document.getElementById('paraFrm_testReqCode').value=id1;
}
	catch(e)
   			{
   				alert(e);
   			}
window.close();
}
</script>