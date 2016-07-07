    
<%--Bhushan--%>
<%--Dec 18, 2007--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 

<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm" 	theme="simple"> 	 
	<table width="100%" border="0" align="right" cellpadding="0"  		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
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
	      <td colspan="3">
				<table width="100%" border="0" align="center" class="formbg">
					<tr>
						<td valign="bottom" class="txt" width="3%" colspan="1"><strong
							class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="97%" class="txt" colspan="2"><strong
							class="text_head"> Travel
			Policy For Grade <s:property value="empGrade"/>  </strong></td>
					</tr>
				</table>
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
					<td width="78%"> </td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr> 
        <tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /> <s:hidden name="travelPolicyViewFlag" />    </td>
	    </tr>
	    <s:if test="travelPolicyViewFlag">
	    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						
						<tr height="20">
							<td width="25%" colspan="0"><strong><label  class = "set"  id="exp.tr.list" ondblclick="callShowDiv(this);"><%=label.get("exp.tr.list")%></label> </strong> </td>
						 
						</tr>  
						 <tr height="20">
							<td width="100%" colspan="2">   
							  <table  width="100%"  class="formbg"> 
							    <tr>   
							          <td width="10%" colspan="1" class="formth" >Sr.No.</td>
						 	          <td width="60%" colspan="1" class="formth" >Expenses Category Name  </td>
						 	          <td width="20%" colspan="1" class="formth" >Category Unit  </td>
						 	          <td width="10%" colspan="1" class="formth" >Value </td> 
								</tr> 
								<% int x=1; %>
								 <s:iterator value="expList">						 
					 			   <tr>
							 			 <s:hidden name ="exCategory" /> <s:hidden name ="exCategoryId" />  <s:hidden name ="expValue" />   <s:hidden name ="expCatUnit" />   <s:hidden name ="expCatUnitCode" />    
										  <td width="10%" colspan="1" class="sortableTD" ><%=x%></td>
							 	          <td width="60%" colspan="1" class="sortableTD" ><s:property value="exCategory"/>  </td>
							 	          <td width="20%" colspan="1" class="sortableTD" ><s:property value="expCatUnit"/> </td>
							 	          <td width="10%" colspan="1" class="sortableTD" ><s:property value="expValue" />  </td> 
								    </tr>  	 	
								 <%x++; %>
							      </s:iterator>	
							       				 
								  </table>   
							</td>
						</tr> 
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
	    </tr>  
	    <tr>
			<td width="50%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
						 <tr height="20">
							<td width="35%" colspan="1"><strong>  <label  class = "set"  id="tr.mode.list" ondblclick="callShowDiv(this);"><%=label.get("tr.mode.list")%></label> </strong> </td>
							<td  colspan="1"> &nbsp;
							 </td>
						 
						</tr>  
						 <tr height="20">
							<td width="100%" colspan="2">   
							  <table   class="formbg" width="100%"> 
							    <tr>   
							          <td width="10%" colspan="1" class="formth" >Sr.No.</td>
						 	          <td width="45%" colspan="1" class="formth" >Travel Mode   </td>
						 	          <td width="45%" colspan="1" class="formth" >Travel Class  </td> 
								</tr> 
								<% int y=1; %>
								 <s:iterator value="travelModeList">						 
					 			   <tr>
							 			 <s:hidden name ="travelMode" /> <s:hidden name ="travelModeId" /> 
							 			  <s:hidden name ="travelClass" /> <s:hidden name ="travelClassId" /> 
										  <td width="10%" colspan="1" class="sortableTD" ><%=y%></td>
							 	          <td width="45%" colspan="1" class="sortableTD" ><s:property value="travelMode"/> &nbsp; </td>
							 	          <td width="45%" colspan="1" class="sortableTD" ><s:property value="travelClass"/> </td>
							       </tr>  	 	
								   <%y++; %>
							      </s:iterator>		
							      	<% int a=1; %>
							       <s:iterator value="hotelTypeList">	
							       <%a++; %> 
							       	<%if(a>y) {%>
							       		 <tr> 
											  <td width="10%" colspan="1" class="sortableTD" >  </td>
								 	          <td width="45%" colspan="1" class="sortableTD" >   </td>
								 	          <td width="45%" colspan="1" class="sortableTD" >  </td>
							       		</tr>  
							       	<%} %>
							       	 
							       </s:iterator>	
							      					 
								  </table>   
							</td>
						</tr> 
					</table>
					</td>
				</tr>
			</table>
			</td> 
			<td>&nbsp;
			</td>
			<td  width="50%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						
						<tr height="20">
							<td width="35%" colspan="1"><strong> Hotel Type List </strong> </td>
							<td  colspan="1"> &nbsp;</td>
						 
						</tr>  
						 <tr height="20">
							<td width="100%" colspan="2">   
							  <table   class="formbg" width="100%"> 
							    <tr>   
							          <td width="10%" colspan="1" class="formth" >Sr.No.</td>
						 	          <td width="45%" colspan="1" class="formth" >Hotel Type  </td>
						 	          <td width="45%" colspan="1" class="formth" >Room Type </td> 
								</tr> 
								<% int z=1; %>
								 <s:iterator value="hotelTypeList">						 
					 			   <tr>
							 			 <s:hidden name ="hotelTypePol" />  
							 			 <s:hidden name ="roomTypePol" />  
										  <td width="10%" colspan="1" class="sortableTD" ><%=z%></td>
							 	          <td width="45%" colspan="1" class="sortableTD" ><s:property value="hotelTypePol"/> &nbsp;</td>
							 	          <td width="45%" colspan="1" class="sortableTD" ><s:property value="roomTypePol"/> </td> 
							 	          
								    </tr>  	 	
								 <%z++; %>
							      </s:iterator>								 
								  </table>   
							</td>
						</tr> 
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>  
		<tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
	    </tr>
	    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						
						<tr height="20">
							<td width="100%" colspan="2"><Strong>Policy Guidelines</Strong></td>
							 
						</tr>	
						 <tr height="20">
							<td width="100%" colspan="2"> <s:hidden name="textAreaLength" id="textAreaLength" value="0"/> 
							 <s:property value="guidLines"  />
							 <s:hidden name="guidLines"  id="guidLines"  /> 
                             </td> 
						</tr>						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:if>
		<s:else>
		<tr>
			     <td ><font color="red"> <b>  Travel Policy is not defined for Grade <s:property value="empGrade"/>. </b>  </font></td>
	    </tr>
		 </s:else>
	</table>

</s:form> 
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
 
 