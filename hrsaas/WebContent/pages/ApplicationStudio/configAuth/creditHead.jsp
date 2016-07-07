<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="ConfigAuthorization" validate="true" id="paraFrm"
	validate="true" theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Credit Head
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
				<tr class="td_bottom_border">

					<s:hidden name="frmId" id="frmId" />
					<s:hidden name="hiddenCreditCodefrmId" id="hiddenCreditCodefrmId" />
					<td width="80%" valign="top" class="formth">Credit Heads</td>
					<td width="20%" valign="top" class="formth">Select</td>
				</tr>
				<%
					int y = 1;
				%>
				<%!int z = 0;%>
				<s:iterator value="bean.arr">
					<tr class="sortableTD">
						<input type="hidden" name="creditCode" id="creditCode<%=y%>"
							value='<s:property value="creditCode"/>' />
						<td class="sortableTD" width="80%"><input type="hidden"
							value='<s:property value="creditName"/>' id="creditName<%=y%>" />
						<s:property value="creditName" /></td>
						<td class="sortableTD" width="10%" align="center"><input
							type="checkbox" name="check" id="check<%=y%>"
							value='<s:property value="check"/>' /></td>
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
				class="token" name="Ok" value=" OK " onclick="calculate();" /> <input
				type="button" class="token" name="Ok" value=" Clear "
				onclick="callClear();" /></td>
		</tr>
	</table>

</s:form>
<script>
var textId=document.getElementById('frmId').value;
var textId1=document.getElementById('hiddenCreditCodefrmId').value;


		function callClear(){
  //alert(opener.document.getElementById('creditCode').value);
	 //    alert(opener.document.getElementById('creditName').value);	
	 opener.document.getElementById('paraFrm_creditCode').value="";
    opener.document.getElementById('paraFrm_creditName').value="";
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
   			// alert("In if");
   			id+=document.getElementById('creditName'+i).value+",";
   			id1+=document.getElementById('creditCode'+i).value+",";
   			// alert(" creditName "+document.getElementById('creditName'+i).value);
   			// alert(" creditCode "+document.getElementById('creditCode'+i).value);
   		}
   			
   }
   id = id.substring(0,id.length-1);
    id1 = id1.substring(0,id1.length-1);
   
  opener.document.getElementById('paraFrm_creditName').value=id;
  opener.document.getElementById('paraFrm_creditCode').value=id1;

}
	catch(e)
   			{
   				alert(e);
   			}
window.close();
}

function setValue()
{
	try{
	var getValue = opener.document.getElementById('hiddenCreditCodefrmId').value;
	document.getElementById('hiddenCreditCodefrmId').value = getValue; 
	//alert(getValue);
	 var value='<%=z%>'; 
      var oldval = opener.document.getElementById('paraFrm_creditName').value;
     // alert(oldval);
       var oldval_code = getValue;
  		 fieldLis_codet=oldval_code.split(",");
      fieldList=oldval.split(",");
     
   for(k=1;k<=value;k++)
   {
    
		     for(i=1-1; i < fieldList.length;i++) {
		    
		      if(document.getElementById('creditName'+k).value==fieldList[i] )
		      {
		      document.getElementById('check'+k).checked=true;
		      }  
		   }
   
    }
    }catch(e)
    {
   // alert(e);
    }

}
setValue();
</script>