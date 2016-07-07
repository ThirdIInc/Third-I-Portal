 
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelAppr" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp; <s:hidden
				name="traApp.isApprove" /> <s:hidden name="checkEdit"
				value="%{checkEdit}" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Travel
			Application </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td width="78%"></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red"></font></span></div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Travel Application </strong> <s:hidden
								name="travelCode" /></td>
							<s:hidden name="level" value="%{level}" />
						</tr>


						<tr>
							<td width="10%" colspan="1" class="formtext" height="22">Employee Name :<font
								color="red">*</font></td>
							<td width="75%" colspan="3"><s:textfield name="empToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="eName" size="75" theme="simple" readonly="true" /></td>

						</tr>
						
						
					<tr>
							<td width="10%" class="formtext" height="22" >Branch:</td>
							<td width="25%"><s:textfield name="brName" theme="simple" 
								size="35" readonly="true" /></td> 
							<td width="10%" class="formtext">Department:</td>
							<td width="25%"><s:textfield name="dept" theme="simple" 
								size="35" readonly="true" /></td> 
						</tr>


						<tr>
							 
							<td width="10%" class="formtext" height="22" >Designation:</td>
							<td width="25%"><s:textfield name="desg" theme="simple"
								size="25" readonly="true" /></td> 
								<td width="10%" class="formtext">Application Date:</td>
							<td width="25%"><s:textfield name="aDate" size="10"
								readonly="true" theme="simple" maxlength="10" /></td>
						</tr> 

						<tr> 
							<td width="10%" class="formtext" height="22" >Status:</td> 
							<td width="25%"><s:select theme="simple" name="status" 
								list="#{'P':'Pending','A':'Approved','R':'Rejected'}"
								disabled="true"></s:select></td>
							 						</tr>
						
						<tr> 
							<td width="10%" class="formtext" height="22" >Age :</td>
							<td width="25%"><s:textfield name="empAge" theme="simple"
								size="25" readonly="true" /></td> 
								<td width="10%" class="formtext">Contact Number:</td>
							<td width="25%"><s:textfield name="contactNo" size="10"
								readonly="true" theme="simple" maxlength="10" /></td>
						</tr> 
						
						<tr> 
							<td width="10%" class="formtext" height="22" >ID Proof :</td>
							<td width="25%"><s:textfield name="idProof" theme="simple"
								size="25" readonly="true" /></td> 
								<td width="10%" class="formtext">ID Number:</td>
							<td width="25%"><s:textfield name="idNumber" size="10"
								readonly="true" theme="simple" maxlength="10" />  
								</td>
						</tr> 
						 
						
						<tr>
							<td width="10%" class="formtext" height="22" >Comments:</td>
							<td colspan="3"><s:textarea name="appComment" theme="simple"
								cols="100" rows="2" readonly="true" /></td> 
						</tr> 
					 
					</table>
					<table width="99%"  class="formbg" border="0"> 
							 
								  <tr>
									<td width="17%" colspan="1">Grade :</td>
									<td width="85%" colspan="3">
										 <s:textfield name="gradeName" size="10"
								readonly="true" theme="simple" />
								<s:hidden name="gradeCode"/> <s:hidden name="cssClassTYpe"/>  
									</td>  
								</tr> 
								
								<tr>
									<td width="17%" colspan="1">Travel Entitlement</td>
									<td  colspan="3">   
									              <s:property value="appModeName"/> 
			 				 	 </td> 
								</tr>  
											<tr>
									<td width="17%" colspan="1"> &nbsp; </td>
									<td  colspan="1" width="40%"  >  <s:property value="trOtherExp"/>   </td>  
			 				 	    <td  colspan="2" >   <s:property value="lodgOtherAllow"/>  	 	 </td> 
								</tr>    
								<tr>
									<td width="17%" colspan="1"> &nbsp; </td>
									<td  colspan="1" width="40%"  >   <s:property value="lodgAllowPerDay"/>      	 </td>  
			 				 	    <td  colspan="2" >  <s:property value="outPocketAllow"/>  	 </td>  
								</tr>    
								<tr>
									<td width="17%" colspan="1"> &nbsp; </td>
									<td  colspan="3" >  <s:property value="foodAllow"/>  	 </td> 
								</tr>    
			  
		      </table>
					</td>
				</tr> 
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Travel Application Details </strong></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">
					 <table class="sortable" width="100%">
  				
  					<tr>
					<td class="formth" width="05%">Sr No</td>
					<td class="formth" width="15%">From Place</td>
					<td class="formth" width="15%">To Place</td>
					<td class="formth" width="10%">Journey Mode</td>
					<td class="formth" width="10%">Class</td>
					<td class="formth" width="10%">Journey Date</td>
					<td class="formth" width="10%">Journey Time</td>
				</tr>
  				
  				
  			 <%
				int srNo = 0;
				%>
				<s:iterator value="travelDtlList">
					<tr>
						<td class="border2"><%=++srNo%></td>
						<td class="border2"><s:property value="itFromPlaceName" /></td>
						<td class="border2"><s:property value="itToPlaceName" /></td>
						<td class="border2"><s:property value="itJourneyName" /></td>
						<td class="border2"><s:property value="itJourneyClassName" /></td>
						<td class="border2"><s:property value="itJourneyDate" /></td>
						<td class="border2"><s:property value="itJourneyTime" /> <s:hidden
							name="itFromPlaceName" /><s:hidden name="itToPlaceName" /><s:hidden
							name="itJourneyName" /> <s:hidden name="itJourneyClassName" />
						<s:hidden name="itFromPlaceId" /><s:hidden name="itToPlaceId" /><s:hidden
							name="itJourneyId" /> <s:hidden name="itJourneyClassId" /> <s:hidden
							name="itJourneyDate" /><s:hidden name="itJourneyTime" /></td>

					</tr>
				</s:iterator> 
  				
  			</table> 
					</td>
				
			</table>
			</td>
		</tr>
		
	<tr>
		<td colspan="3"><img src="../pages/images/recruitment/space.gif"
			width="5" height="4" /></td>
	</tr> 
	<tr>
		<td colspan="3"><s:if test="commentFlag"></s:if><s:else>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">

						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Approver Details </strong></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
		</s:else></td>
	</tr>
	<s:if test="commentFlag"></s:if>
	<s:else>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td width="5%" class="formth">Sr.No.</td>
							<td width="35%" class="formth">Approver Name</td>
							<td width="20%" class="formth">Approved Date</td>
							<td width="40%" class="formth">Comments</td>


						</tr>
						<%
						int j = 0;
						%>

						<s:iterator value="approveList">
							<tr>
								<td class="border2" width="5%" align="center"><%=++j%></td>
								<td class="border2" width="35%"><s:property
									value="apprName" /></td>
								<td class="border2" width="20%" align="left"><s:property
									value="apprDate" /></td>
								<td class="border2" width="40%" align="left"><s:property
									value="apprComments" /></td>
							</tr>


						</s:iterator>


					</table>

					</td>
				</tr>
			</table>

			</td>
		</tr>
	</s:else>
	</table>
	<br />


	<label></label>
</s:form>
<script type="text/javascript">
</script>
 