<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TravelQuickBooking" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Sub-Scheduler
					List</strong></td>
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


			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg" class="sortable">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr class="td_bottom_border">
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead">Sub-Scheduler List</strong></td>
						</tr>
						<tr>

							<td class="formth" align="center" width="5%"><strong><label
								class="set" id="sr.no3" name="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>

							<td class="formth" align="center" width="15%">Employee Token</td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="employee" name="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></strong></td>

							<td class="formth" align="center" width="15%">Assign To</td>

						</tr>
						<%!int d=0; %>
						<%
							int i = 0;
						 
								%>
						<s:iterator value="subSchdlrList">
							<tr>


								<td align="left" class="sortableTD" nowrap="nowrap"><%=++i%>
								</td>

								<td align="left" class="sortableTD"><input type="hidden"
									name="ittEmployeeToken"
									value="<s:property
										value="ittEmployeeToken" />" />
								<s:property value="ittEmployeeToken" /></td>
								<td align="left" class="sortableTD"><input type="hidden"  id="ittEmployee<%=i%>"
									name="ittEmployee" value="<s:property value="ittEmployee" />" />

								<s:property value="ittEmployee" /> <input type="hidden"
									name="ittEmployeeCode"
									value="<s:property value="ittEmployeeCode" />" /></td>



								<td align="center" nowrap="nowrap" class="sortableTD"><input
									type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" /> <input
									type="checkbox" class="checkbox" id="confChk<%=i%>"
									name="confChk"
									onclick="call('<s:property value="ittEmployeeCode"/>','<%=i%>')" />


								</td>

							</tr>

						</s:iterator>
						<%
							  
								d = i;
							%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="10">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td align="center" colspan="1"><input type="button"
						value="Assign To  " class="token" onclick="return callSave();" />
					<input type="button" value="Close" class="token"
						onclick="callClose();"></td>
				</tr>

			</table>
			</td>
	</table>

	<s:hidden name="hiddenApplicationCode" />

	<s:hidden name="existingAssignedEmployee"
		id="existingAssignedEmployee" />

</s:form>

<script>



function setValue()
{
	
	try{
	 var value='<%=d%>';
	 var oldval = document.getElementById('existingAssignedEmployee').value;
 
       for(k=1;k<=value;k++)
   {
   
     
		      if(document.getElementById('ittEmployee'+k).value==oldval )
		      {
		      document.getElementById('confChk'+k).checked=true;
		      }  
		  
   
    }
	}catch(e){alert("Value oif e----------------"+e);}
	
	 
     

}
setValue();
 

function call(id,i)
	   {
	 
	  var flag='<%=d %>';
	 //  alert('id----1-----'+id);
	 // alert('i----1-----'+i);
	
		   if(document.getElementById('confChk'+i).checked == true)
		   {	  
		    document.getElementById('hdeleteCode'+i).value=id;
		   }
		   else
		   {
		   	document.getElementById('hdeleteCode'+i).value="";
		   	
		   	}
   		}
   		
   		
   		  function callSave()
	{
	 
			 if(chk()) 
			 {
				 var con=confirm('Do you really want to assign');
			 		if(con)
			 		{
			 			document.getElementById("paraFrm").target="main"; 	
						document.getElementById("paraFrm").action='TravelQuickBooking_assignEmployee.action';
						document.getElementById("paraFrm").submit();	
						 window.close(); 
						
								 		
			 		  return true ;
			  		 }
				    else
				    { 
				   		    var flag='<%=d %>';
				  				for(var a=1;a<=flag;a++)
				  				{	
								document.getElementById('confChk'+a).checked = false;
								document.getElementById('hdeleteCode'+a).value="";
				
								}
				     			return false;
				 	}
			 }
		 else 
		 {
						 return false;
		 }
		 
		 
	}
   		
   		
   	function chk()
		{
			 var flag='<%=d %>';
			 
			 var count =0 ;
			 
			// alert("v=======  "+count);
			  for(var a=1; a<=flag  ;a++)
			  {	
			  
			  	 if(document.getElementById('confChk'+a).checked == true)
			 	   {	
			  			 document.getElementById('confChk'+a).value = true;
			  			  count++; 
			  			// return true;
			  			 
			  	   }
			  	
			  }
			  
			  if(count!=1)
			  {
			   alert('Please select only one record');
			   return false;
			  }
			   return true;
		}	
   		
   		
   		
   		
   		

	function callClose(){
    
	window.close(); 
   	}

</script>
