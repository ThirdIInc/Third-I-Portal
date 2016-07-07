<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"> var records = new Array(); </script>
<script type="text/javascript"> var mapRecords = new Array(); </script>
<s:form action="Designation" validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<s:hidden name="abbrevitaionCodeCheck" />
		<s:hidden name="hiddenDesignationAbbreviation" />
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Designation
					</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>




		<s:if test="showList">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td class="formtext">
						<table width="100%" border="0">
							<tr>
								<td class="formth" align="center" width="80%"><label
									class="set" name="designationNameList" id="designationNameList"
									ondblclick="callShowDiv(this);"> <%=label.get("designationNameList")%></label>
								</td>
								<td class="formth" align="center" width="20%"><label
									class="set" name="designation.action" id="designation.action"
									ondblclick="callShowDiv(this);"> <%=label.get("designation.action")%></label>
								</td>
							</tr>

							<s:iterator value="designationTypeItterator">
								<tr>
									<td class="sortableTD">&nbsp; <s:hidden
										name="designationListTypeID" /> <s:hidden
										name="designationAbbreviation" /> Map <s:property
										value="designationListTypeName" /> with Designation</td>

									<td class="sortableTD" align="center">&nbsp; <input
										type="button" value="Process" class="token"
										onclick="processFunction('<s:property value="designationListTypeID" />','<s:property value="designationAbbreviation" />');">
									</td>
								</tr>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>


		<s:if test="showDetails">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td>
				<table width="100%" class="formbg" height="20">
					<tr>
						<td class="formtext"><b>Mapped <s:property
							value="designationListTypeName" /> Designations</b>
						<table width="100%" border="0">
							<tr>
								<td class="formth" align="center" width="60%"><label
									class="set" name="designation" id="designation"
									ondblclick="callShowDiv(this);"> <%=label.get("designation")%></label>
								</td>
								<td class="formth" align="center" width="40%"><input
									type ="button" id= ctrlShow" class ="delete" value=
									" Delete" onclick= "return chkDelete();" /> <input
									type="checkbox" id="selAll" name="selAll" style="cursor: hand;"
									title="Select all records to remove"
									onclick="selectAllMappedRecords(this);" /></td>
							</tr>

							<%!int d2 = 0;%>
							<%
							int i2 = 0;
							%>
							<s:if test="mapListLength">
								<s:iterator value="mappedDesignationlist">
									<tr <%= ++i2 %>>
										<s:hidden value="%{designationID}"
											id='<%="designationID" + i2%>' />
										<script type="text/javascript">mapRecords[<%=i2%>] = document.getElementById('designationID' + '<%=i2%>').value;</script>

										<td class="sortableTD">&nbsp; <input type="hidden"
											name="hiddenMappedDesignationTypeCode"
											id="hiddenMappedDesignationTypeCode<%=i2%>"
											value="<s:property value="hiddenMappedDesignationTypeCode"/>">
										<s:property value="designation" /></td>

										<td class="sortableTD" align="center">&nbsp; <input
											type="checkbox" class="checkbox" id="confDelChk<%=i2%>"
											name="confDelChk"
											onclick="callForDelete('<s:property value="designationID"/>','<%=i2%>')">

										</td>
									</tr>
								</s:iterator>
								<%
								d2 = i2;
								%>
							</s:if>
							<s:else>
								<tr>
									<td align="center" colspan="2"><font color="red">No
									Data To Display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>


			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td class="formtext"><b>Pending Designations </b>
						<table width="100%" border="0">
							<tr>
								<td class="formth" align="center" width="60%"><label
									class="set" name="designation" id="designation"
									ondblclick="callShowDiv(this);"> <%=label.get("designation")%></label>
								</td>
								<td class="formth" align="center" width="40%"><input
									type="button" id="ctrlShow" class="save" value=" Map"
									onclick="return chkMap();"> <input type="checkbox"
									id="selAll" style="cursor: hand;"
									title="Select all records to map"
									onclick="selectAllRecords(this);"></td>
							</tr>
							<%!int d = 0;%>
							<%
							int i = 0;
							%>
							<s:if test="listLength">
								<s:iterator value="pendingDesignationlist">

									<tr <%=++i %>>

										<s:hidden value="%{pendingDesignationID}"
											id='<%="pendingDesignationID" + i%>' />
										<script type="text/javascript">records[<%=i%>] = document.getElementById('pendingDesignationID' + '<%=i%>').value;</script>


										<td class="sortableTD">&nbsp; <input type="hidden"
											name="hiddenDesignationTypeCode"
											id="hiddenDesignationTypeCode<%=i%>"> <s:property
											value="pendingDesignation" /></td>

										<td class="sortableTD" align="center">&nbsp; <input
											type="checkbox" class="checkbox" id="confChk<%=i%>"
											name="confChk"
											onclick="callForMap('<s:property value="pendingDesignationID"/>','<%=i%>')">
										</td>
									</tr>
								</s:iterator>
								<%
								d = i;
								%>
							</s:if>

							<s:else>
								<tr>
									<td align="center" colspan="2"><font color="red">No
									Data To Display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td>
				<table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
	</table>

</s:form>

<script>

//callOnload();
 function callOnload()
	 {
	 	
	 	var lengthVar ='<%=d2%>';	 
	 		 try{	 		 
	 		 for(var a=1;a<=lengthVar;a++)
	  		{	
				document.getElementById('confDelChk'+a).checked = true;
				 
			}
	 		 }catch(e){
	 		 
	 		 }
	 }

	function saveFun()
	{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='Designation_save.action';
	  	document.getElementById('paraFrm').submit();
	}
	
	function resetFun() 
	{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'Designation_reset.action';
     	document.getElementById('paraFrm').submit();      	
	}
	
	function returntolistFun() 
	{
		try{
			document.getElementById('paraFrm').target = "_self";     	
  			document.getElementById('paraFrm').action = 'Designation_returntoListFun.action';
     		document.getElementById('paraFrm').submit();      			
		}catch(e)
		{
			alert("Exception occured in returntolistFun() : "+e);
		}
	}
	
	function processFunction(designationListTypeID,designationAbbreviation)
	{
		try
		{			
			document.getElementById('paraFrm').target = "_self";  
  			document.getElementById('paraFrm').action = 'Designation_processFunction.action?designationListTypeID='+designationListTypeID+'&designationAbbreviation='+designationAbbreviation;
  			document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert("Exception ocuured in processFunction() : "+e);
		}
     	 
	}
	
	function chkMap()
{	 
	 if(chk())
	 {	
	 	var con=confirm('Do you want to map the record(s) ?');
	 	if(con)
	 	{
	   		document.getElementById('paraFrm').action="Designation_save.action";
	   		document.getElementById('paraFrm').target = "_self";
	    	document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
			document.getElementById('selAll').checked = false;
	    	var flag='<%=d %>';
	  		for(var a=1;a<=flag;a++)
	  		{	
				document.getElementById('confChk'+a).checked = false;
				document.getElementById('hiddenDesignationTypeCode'+a).value="";	
			}
	     return false;
	 	}
	 }
	 else 
	 {
	 	alert('please select atleast one record to map.');
	 	return true ;
	 }	
}


function chk()
{   
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++)
	  {	
	   	if(document.getElementById('confChk'+a).checked == true)
	   	{	
	  		// alert('11');  
	    	return true;
	   	}	   
	  }
	  return false;
}
	
	function callForMap(id,i)
{	
	 if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    	document.getElementById('hiddenDesignationTypeCode'+i).value=id;
	    	document.getElementById('confChk'+i).value=true;
	    	
	   }
	   else
	   {
	   		document.getElementById('hiddenDesignationTypeCode'+i).value="";
   		}
}


function selectAllRecords(object) 
{		
	if(object.checked) 
	{		
		for(var i = 1; i <= records.length; i++) 
		{
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hiddenDesignationTypeCode' + i).value = records[i];
		}
	}
	else 
	{		
		for(var i = 1; i <= records.length; i++) 
		{
			document.getElementById('confChk' + i).checked = false;
			document.getElementById('hiddenDesignationTypeCode' + i).value = "";
		}
	}
}	
	



// Mapped records

	function chkDelete()
{	 
	 if(chkmapDel())
	 {	
	 	var con=confirm('Do you want to remove the record(s) ?');
	 	if(con)
	 	{
	   		document.getElementById('paraFrm').action="Designation_delete.action";
	   		document.getElementById('paraFrm').target = "_self";
	    	document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
			document.getElementById('selAll').checked = false;
	    	var flag='<%=d2 %>';
	  		for(var a=1;a<=flag;a++)
	  		{	
				document.getElementById('confDelChk'+a).checked = false;
				document.getElementById('hiddenDesignationTypeCode'+a).value="";	
			}
	     return false;
	 	}
	 }
	 else 
	 {
	 	alert('please select atleast one record to remove.');
	 	return true ;
	 }	
}


function chkmapDel()
{   
	 var flag='<%=d2 %>';
	  for(var a=1;a<=flag;a++)
	  {	
	   	if(document.getElementById('confDelChk'+a).checked == true)
	   	{	
	  		// alert('11');  
	    	return true;
	   	}	   
	  }
	  return false;
}
	
	function callForDelete(id,i)
{	
	 if(document.getElementById('confDelChk'+i).checked == true)
	   {	  
	    	document.getElementById('hiddenMappedDesignationTypeCode'+i).value=id;
	    	
	   }
	   else
	   {
	   		document.getElementById('hiddenMappedDesignationTypeCode'+i).value="";
   		}
}


function selectAllMappedRecords(object) 
{		
	if(object.checked) 
	{		
		for(var i = 1; i <= mapRecords.length; i++) 
		{
				document.getElementById('confDelChk' + i).checked = true;
				document.getElementById('hiddenMappedDesignationTypeCode' + i).value = mapRecords[i];
		}
	}
	else 
	{		
		for(var i = 1; i <= mapRecords.length; i++) 
		{
			document.getElementById('confDelChk' + i).checked = false;
			document.getElementById('hiddenMappedDesignationTypeCode' + i).value = "";
		}
	}
}	
	
</script>