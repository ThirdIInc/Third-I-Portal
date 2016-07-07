<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LeavePolicy" id="paraFrm" validate="true" theme="simple"
	target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					List </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="sortable">
				<tr  class="td_bottom_border">

					<s:hidden name="hLeaveCode" id="hLeaveCode" />
					<s:hidden name="frmId" id="frmId" />
					<s:hidden name="hiddenfrmId" id="hiddenfrmId" />
					<td width="80%" valign="top" class="formth">Leave Name</td>
					<td width="20%" valign="top" class="formth">Select</td>
				</tr>
				<%
				int y = 1;
				%>
				<%!int z = 0;%>
				<s:iterator value="leavPolicy.arr">
					<tr class="sortableTD">
						<input type="hidden" name="levid" id="levid<%=y%>"
							value='<s:property value="levid"/>' />
						<td class="sortableTD" width="80%"><input type="hidden"
							value='<s:property value="levname"/>' id="levname<%=y%>" /> <s:property
							value="levname" /></td>
						<td class="sortableTD" width="10%" align="center"><input type="checkbox"
							name="check" id="check<%=y%>" value='<s:property value="check"/>' />
						</td>
					</tr>
					<%
					y++;
					%>
				</s:iterator>
				<%
				z = y;
				%>
			</table>
			</td>
		</tr>

		<tr>
			<td align="left" width="25%" colspan="1"><input type="button"
				class="token" name="Ok" value=" OK " onclick="calculate();" />
				<input type="button"
				class="token" name="Ok" value=" Clear " onclick="callClear();" />
				</td>
		</tr>
	</table>

</s:form>
<script>
var textId=document.getElementById('frmId').value;
var textId1=document.getElementById('hiddenfrmId').value;


		function callClear(){
	  opener.document.getElementById(textId).value="";
  opener.document.getElementById(textId1).value="";
			    
   			
	window.close();	
		}



function calculate()
{
//alert('in calculate');
 var value='<%=z%>'; 
 //alert('in calculate'+value);
 var id = '';
 var id1='';
 var chkBox;
 	try{
 
   for(var i=1;i<value;i++)
   {
   		
   	
   		chkBox = document.getElementById('check'+i).checked;
   		// alert(chkBox);
   		if(chkBox == true)
   		{
   		//	 alert("In if");
   			id+=document.getElementById('levname'+i).value+",";
   			id1+=document.getElementById('levid'+i).value+",";
   		//	 alert(" leave id "+document.getElementById('levname'+i).value);
   		//	 alert(" leave id1 "+document.getElementById('levid'+i).value);
   		}
   			
   }
   id = id.substring(0,id.length-1);
    id1 = id1.substring(0,id1.length-1);
   
  opener.document.getElementById(textId).value=id;
  opener.document.getElementById(textId1).value=id1;
}
	catch(e)
   			{
   				//alert(e);
   			}
window.close();
}

function setValue()
{
	 var value='<%=z%>'; 
      var oldval = opener.document.getElementById(textId).value;
       var oldval_code = opener.document.getElementById(textId1).value;
  		 fieldLis_codet=oldval_code.split(",");
  
  
      fieldList=oldval.split(",");
     
     var lCode=document.getElementById('hLeaveCode').value;
     
   for(k=1;k<=value;k++)
   {
    
		     for(i=0; i < fieldList.length;i++) {
		    
		      if(document.getElementById('levname'+k).value==fieldList[i] )
		      {
		      document.getElementById('check'+k).checked=true;
		      }  
		   }
   
  	// if(lCode==document.getElementById('levid'+k).value )
		     // {
		     // document.getElementById('check'+k).checked=true;
		     // document.getElementById('check'+k).disabled=true;
		   //  } 
   
   
    }

}
setValue();
</script>
