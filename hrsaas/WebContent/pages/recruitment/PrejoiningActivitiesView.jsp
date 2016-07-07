<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="PrejoiningActivities" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="bgStatus" />
	<s:hidden name="hiddencode" />
	<s:hidden name="DupcheckListType" />
	<s:hidden name="DupcheckListresponce" />
	<s:hidden name="DupbgCheckType" />
	<s:hidden name="rowId" />
	<s:hidden name="buttonpanelcode" />
	<s:hidden name="prejoinCode" />
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Pre Joining Activities</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<!-- The Following code Denotes  Include JSP Page For Button Panel -->
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
					<td width="22%">
								
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<tr> 
          <td height="5" colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
        </tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0" class="formbg"
				cellspacing="2">
				<!--Table 2-->
				<tr>
					
						
					<td width="78%" colspan="2"><strong class="formhead">Pre Joining Activity Details</strong></td>	
				</tr>	
	
				<tr>
					<td width="20%"><label  class = "set" name="cand.name" id="prej.cname" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> :</td>
					<td width="25%"><s:hidden name="candidateCode" /> <s:property
						value="candidateName"  /></td>
						
					<td  width="20%"><label  class = "set" name="position" id="prej.postin" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
					<td valign="top" width="25%"><s:property value="position" /></td>
					<td  width="25%">&nbsp;</td>
						
					
				</tr>
				<tr>
					<td width="20%"><label  class = "set" name="reqs.code" id="prej.reqco" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> :</td>
					<td width="25%"><s:hidden name="reqCode"/><s:property value="reqName" /></td>
					
					<td width="25%"><label  class = "set" name="prej.repto" id="prej.repto" ondblclick="callShowDiv(this);"><%=label.get("prej.repto")%></label> :</td>
					<td width="25%"><s:hidden name="reportingTo"/>
					<s:property value="reportingName"  />
					</td>
					
					
					
					</td>
				</tr>
				<tr>
					<td width="20%"><label  class = "set" name="division" id="prej.divso" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
					<td width="25%"><s:property value="division"  /></td>
					
					
					<td width="25%"><label  class = "set" name="offer.date" id="prej.offdt" ondblclick="callShowDiv(this);"><%=label.get("offer.date")%></label> :</td>
					<td width="25%">
					<s:property  value="offerDate"  />
				
					
					</td>
						
						
					
				</tr>
				<tr>
					<td width="20%"><label  class = "set" name="branch" id="prej.branc" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="25%"><s:property value="branch" /></td>
					
						
					<td width="25%"><label  class = "set" name="joining.date" id="prej.joidt" ondblclick="callShowDiv(this);"><%=label.get("joining.date")%></label> :</td>
					<td width="25%">
					<s:property  value="joinDate"  />
					
					
				</tr>
				<tr>
					<td width="20%"><label  class = "set" name="department" id="prej.deprt" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
					<td width="25%"><s:property value="department" /></td>
					
					<td width="25%"><label  class = "set" name="prej.appst" id="prej.appst" ondblclick="callShowDiv(this);"><%=label.get("prej.appst")%></label> :</td>
					<td width="25%"><s:property value="Appstatus"  /><s:hidden name="Appstatus"  /></td>
					
					<!--<s:select theme="simple" name="checkListType" 
				headerKey="S" headerValue="   --Select--      "
				list="#{'J':'Joining CheckList','M':'Medical CheckList','T':'Transfer CheckList','I':'Interview CheckList','B':'Background Verification CheckList','P':'Pre joining CheckList'}" />
				
					
					
					
					--></td>
				</tr>
				<tr>
					
					<td width="25%"><label  class = "set" name="prej.seact" id="prej.seact" ondblclick="callShowDiv(this);"><%=label.get("prej.seact")%></label> :</td>
					<td width="25%">
					<s:property value="DupcheckListType"/>
					
					</tr>
			</table>
			</td>
		</tr>
		
				<tr>
				<td>
				
				
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"
										>
										<!--Table 6-->
										<tr>
						<td width="25%" colspan="3"  class="formtxt"><strong
							class="formhead">
						
							 <%
							 String status ="";
							 try{ %> <%
				 	status = (String) request.getAttribute("listname");
				 	if (status.equalsIgnoreCase("J")) { 
				 		out.println("Joining");
				 	}   
				 	if (status.equalsIgnoreCase("M")) {
				 		out.println("Medical");
				 	}
				 	if (status.equalsIgnoreCase("T")) {
				 		out.println("Transfer");
				 	}
				 	if (status.equalsIgnoreCase("I")) {
				 		out.println("Interview");
				 	}
				 	if (status.equalsIgnoreCase("B")) {
				 		out.println("Background Verification");
				 	}
				 	if (status.equalsIgnoreCase("P")) {
				 		out.println("Prejoining");
				 	}%>
							 <% }
							 catch(Exception e){
								 
							 } %>
				 
							
							
							Check List Details:</strong></td>
							<s:hidden name="Chkstatus1" value='<%=status %>' />   	
					     	<s:hidden name="Chkstatus"  />   	
						
					</tr>
										
										
										
										
										
										<tr>
											<td width="8%" valign="top" class="formth" nowrap="nowrap">
										
<label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);">
						<b><%=label.get("serial.no")%></b></label>
											</td>
											<td width="20%" valign="top" class="formth" nowrap="nowrap">
											
<label  class = "set" name="Adetails" id="Adetails" ondblclick="callShowDiv(this);">
						<b><%=label.get("Adetails")%></b></label>
											</td>
											<td width="20%" valign="top" class="formth">
											
<label  class = "set" name="Tdate" id="Tdate" ondblclick="callShowDiv(this);"><b><%=label.get("Tdate")%></b></label>
</td>
											<td width="30%" valign="top" class="formth">
											
<label  class = "set" name="Respper" id="Respper" ondblclick="callShowDiv(this);"><b><%=label.get("Respper")%></b></label>
											</td>
											<td width="10%" valign="top" class="formth">
											
<label  class = "set" name="Areq" id="Areq" ondblclick="callShowDiv(this);">
						<b><%=label.get("Areq")%></b></label>
</td>
											<td width="15%" valign="top" class="formth">
<label  class = "set" name="Astatus" id="Astatus" ondblclick="callShowDiv(this);">
						<b><%=label.get("Astatus")%></b></label>
											</td>
									   </tr>
										<s:if test="noData">
											<tr>
												<td width="100%" colspan="7" align="center"><font
													color="red">No Data To Display</font></td>
											</tr>
										</s:if>
										<%!int p = 1,zz=0;%>
										<%
										int q = 1;
										%>
					
		<tr>						  <% Object[][] funcObj = (Object[][])request.getAttribute("funcObj");  
		      	   		                       %>
									
		                        <s:iterator value="prejoiningList">
											<tr><s:hidden name="RespCode" />
					                           <s:hidden name="RespName" />	
												<td class="sortabletd" width="8%" nowrap="nowrap"><%=p%>
												<s:hidden
													name="prejoinListCode" />
													<s:hidden
													name="checkListCode" /></td>
												
												<td class="sortabletd" width="20%" align="left"><s:property
													value="prejoinListName" />
													
													 </td>
													<td class="sortabletd" width="20%" align="center">
													<s:property
													value="prejoinTargetDate" />
													<!--
                                  <input type="text"   name="prejoinTargetDate" id="prejoinTargetDate<%=zz%>" value='<s:property value="prejoinTargetDate"/>'
                                   size="10"  onblur="return validateDate('prejoinTargetDate<%=zz%>','Target Date');"  onkeypress="return numbersWithHiphen();"  maxlength="10" > 													
							 
							 
							   <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18" 
							   onclick="javascript:NewCal('<%="prejoinTargetDate"+zz%>','DDMMYYYY');" />
																				
				
							
													
													 -->
													 &nbsp;</td>
						<td class="sortabletd" width="30%">
						
					 
					 <s:property value="<%=String.valueOf(funcObj[zz][0]).equals("null") ? "":String.valueOf(funcObj[zz][0])%>" />
					 <s:hidden name="RespCode"  />
									<s:property value="RespName"  /> 
									
						
						&nbsp;</td>	 
													 
													<td class="sortabletd" width="10%">
													<s:property value="DupactivityRequired" /> &nbsp;</td>
													<td class="sortabletd" width="15%">
													<s:property value="DupactivityStatus" /> &nbsp;</td>

											</tr>
											<%
											p++;
											zz++;
											%>
										</s:iterator>
										<%
											q = p;
											p = 1;
											zz=0;
										%>
									</table>
									
									<s:hidden name="prejoiningListLength" value="%{prejoiningListLength}" />
									</td>
								</tr>
		
			<tr> 
          <td height="5" colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
        </tr>
				
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
					
				</tr>
			</table>
			</td>
		</tr>
				
		
	</table>



</td></tr></table>


</s:form>
<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>

<script>

var fieldName = ["paraFrm_candidateCode","paraFrm_reqName"];
var lableName = ['prej.cname','prej.reqco'];
var badflag = ["select","select"];


	
   
function addnewFun()
{
	document.getElementById('paraFrm').action="PrejoiningActivities_addNew.action";
	document.getElementById('paraFrm').submit();
}



function saveFun()
{
	 if(!validateBlank(fieldName,lableName,badflag))
        return false;  
        
      /*
         var listtype=document.getElementById('paraFrm_checkListType').value;
       if(document.getElementById('paraFrm_checkListType').value!="S")
		{
		}
		else
		{ 
		alert("Please select check list type");
		  return false;
		}
        */
        
        if(document.getElementById('paraFrm_prejoiningListLength').value=="" || document.getElementById('paraFrm_prejoiningListLength').value=="0"){
		alert("Please click on Show List.");
		return false;
		}
		
		if(document.getElementById('paraFrm_Appstatus').value=="Close")
		{
	  	 alert("You can not modify the closed application.");
	 	 return false;
		}
		
	document.getElementById('paraFrm').action="PrejoiningActivities_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Edit Button

function editFun()
{
 
   	if(document.getElementById('paraFrm_Appstatus').value=="Close")
		{
	  	 alert("You can not edit the closed application.");
	 	 return false;
		}
       
     
	document.getElementById('paraFrm').action="PrejoiningActivities_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{

    if(document.getElementById('paraFrm_Appstatus').value=="Close")
		{
	  	 alert("You can not delete the closed application.");
	 	 return false;
		}
   
	var con=confirm('Do you really want to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="PrejoiningActivities_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}

}

//For F9Window

function searchFun()
{
	callsF9(500,300,"PrejoiningActivities_f9search.action");
	
}

//For Cancel Button

function cancelFun()
{
     if(document.getElementById('paraFrm_buttonpanelcode').value=="2")
	  {
	   document.getElementById('paraFrm').action="PrejoiningActivities_cancelSec.action";
	  }else if(document.getElementById('paraFrm_buttonpanelcode').value=="3")
	  {
	   document.getElementById('paraFrm').action="PrejoiningActivities_cancelThrd.action";
	  }
	  else
	  {
	  document.getElementById('paraFrm').action="PrejoiningActivities_cancelFirst.action";
	  }
	  document.getElementById('paraFrm').submit();
	  return true;
	
} 	
    	
   
	
    	
    	
    	</script>