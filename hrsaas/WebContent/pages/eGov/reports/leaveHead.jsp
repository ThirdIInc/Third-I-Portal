<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="LeaveCreditConfigeGov"  validate="true" id="paraFrm" validate="true" theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Head
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
				<!--

					<s:hidden name="hLeaveCode" id="hLeaveCode" />
					-->
					
					<s:hidden name="frmId" id="frmId" />
					<s:hidden name="hiddenfrmId" id="hiddenfrmId" />
					<td width="80%" valign="top" class="formth">Head Name</td>
					<td width="20%" valign="top" class="formth">Select</td>
				</tr>
				<%
				int y = 1;
				%>
				<%!int z = 0;%>
				<s:iterator value="bean.arr">
					<tr class="sortableTD">
						<input type="hidden" name="debitCode" id="debitCode<%=y%>"
							value='<s:property value="debitCode"/>' />
						<td class="sortableTD" width="80%"><input type="hidden"
							value='<s:property value="debitName"/>' id="debitName<%=y%>" /> <s:property
							value="debitName" /></td>
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
  //alert(opener.document.getElementById('debitCode').value);
	 //    alert(opener.document.getElementById('debitName').value);	
	 opener.document.getElementById('debitCode').value="";
    opener.document.getElementById('debitName').value="";
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
   		 //alert(chkBox);
   		if(chkBox == true)
   		{
   		//	 alert("In if");
   			id+=document.getElementById('debitName'+i).value+",";
   			id1+=document.getElementById('debitCode'+i).value+",";
   			// alert(" debitName "+document.getElementById('debitName'+i).value);
   			// alert(" debitCode "+document.getElementById('debitCode'+i).value);
   		}
   			
   }
   id = id.substring(0,id.length-1);
    id1 = id1.substring(0,id1.length-1);
   
  opener.document.getElementById('paraFrm_debitName').value=id;
  opener.document.getElementById('paraFrm_debitCode').value=id1;
}
	catch(e)
   			{
   				alert(e);
   			}
window.close();
}

function setValue()
{
	
	var getValue = opener.document.getElementById('hiddenfrmId').value;
	document.getElementById('hiddenfrmId').value = getValue; 
	//alert(getValue);
	 var value='<%=z%>'; 
      var oldval = opener.document.getElementById('debitName').value;
     // alert(oldval);
       var oldval_code = getValue;
  		 fieldLis_codet=oldval_code.split(",");
      fieldList=oldval.split(",");
     
   for(k=1;k<=value;k++)
   {
    
		     for(i=0; i < fieldList.length;i++) {
		    
		      if(document.getElementById('debitName'+k).value==fieldList[i] )
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