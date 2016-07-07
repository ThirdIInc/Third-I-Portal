    
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
			<td valign="bottom" class="txt" colspan="2"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong> 
				
			 <strong class="formhead">Travel Changes
			    </strong></td>
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
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
	    </tr>
	    <s:if test="trackTrModeFlag">
	    <tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Journey.Details" ondblclick="callShowDiv(this);"><%=label.get("Journey.Details")%></label> </strong></td>
								<td colspan="1">  	</td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="2">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="From.Place" ondblclick="callShowDiv(this);"><%=label.get("From.Place")%></label></td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="To.Place" ondblclick="callShowDiv(this);"><%=label.get("To.Place")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="Journey.Mode-Class" ondblclick="callShowDiv(this);"><%=label.get("Journey.Mode-Class")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="Journey.Date" ondblclick="callShowDiv(this);"><%=label.get("Journey.Date")%></label>
										</td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="jourDtlTime" ondblclick="callShowDiv(this);"><%=label.get("jourDtlTime")%></label>
										</td>
										<td width="5%" colspan="1" class="formth"> &nbsp;</td>
									</tr>
									<%
									int i2 = 1;
									%>
									<%
									int j2 = 0;
									%>
									<s:iterator value="trModeClassList">
										<tr>
											<td width="5%" colspan="1" class="sortableTD"><%=i2%></td>
											<td width="20%" colspan="1" class="sortableTD"><input  	type="hidden" name="jourFromPlace"
												value='<s:property  value="jourFromPlace"/>'
												id="jourFromPlace<%=i2%>"  /> <s:property  value="jourFromPlace"/> </td>
											<td width="20%" colspan="1" class="sortableTD"><input
												type="hidden" name="jourToPlace"
												value='<s:property  value="jourToPlace"/>'
												id="jourToPlace<%=i2%>" size="25"
												onkeydown="callCity('<%=i2%>');"
												ondblclick="callCity('<%=i2%>');"
												onclick="callCity('<%=i2%>');" /> <s:property  value="jourToPlace"/> </td>
											<td width="25%" colspan="1" class="sortableTD"> <s:property  value="trModeClass"/> </td>
											<td nowrap="nowrap" colspan="1" class="sortableTD"><s:hidden name="jourDate" /> <s:property value="jourDate" /> </td>
											<td nowrap="nowrap" colspan="1" class="sortableTD"><s:hidden name="jourTime" /> <s:property value="jourTime" />  </td>
											<td width="5%" colspan="1" class="sortableTD"> 
											</td>
										</tr>
										<script>	
		                                  travelArr[<%=j2%>] = document.getElementById('jourFromPlace'+<%=i2%>).value;   
		                            </script>
										<%
										i2++;
										%>
										<%
										j2++;
										%>
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
	 </s:if>
	 
	 <s:if test="trackLocalFlag">
	      <tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Local.Conveyance.Details" ondblclick="callShowDiv(this);"><%=label.get("Local.Conveyance.Details")%></label> </strong></td>
								<td colspan="1"> &nbsp;
								</td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="2">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="City" ondblclick="callShowDiv(this);"><%=label.get("City")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="Source" ondblclick="callShowDiv(this);"><%=label.get("Source")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Date" ondblclick="callShowDiv(this);"><%=label.get("From.Date")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Time" ondblclick="callShowDiv(this);"><%=label.get("From.Time")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Date" ondblclick="callShowDiv(this);"><%=label.get("To.Date")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Time" ondblclick="callShowDiv(this);"><%=label.get("To.Time")%></label></td>
										<td width="5%" colspan="1" class="formth"> &nbsp;</td>
									</tr>
									<%
									int p2 = 1;
									%>
									<%
									int q2 = 0;
									%>

									<s:iterator value="localConList">
										<tr>
											<td width="5%" colspan="1" class="sortableTD"><%=p2%></td>
											<td width="25%" colspan="1" class="sortableTD"><input
												type="hidden" name="localCity" 	value='<s:property  value="localCity"/>'
												id="localCity<%=p2%>" size="25"  /> <s:property  value="localCity"/> </td>
											<td width="25%" colspan="1" class="sortableTD"><s:hidden name="localSource" /> <s:property  value="localSource"/>  </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden 	name="localFromDate" /> <s:property  value="localFromDate"/>  </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  name="localFromTime" /> <s:property  value="localFromTime"/>  </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden name="localToDate" />  <s:property  value="localToDate"/></td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  name="localToTime" /> <s:property  value="localToTime"/> </td>
											<td width="5%" colspan="1" class="sortableTD"> <input	type="hidden" name="hidLocalChkBox" id="hidLocalChkBox<%=p2%>"></td>
										</tr>
										<script>	
		                                  localArr[<%=q2%>] = document.getElementById('localCity'+<%=p2%>).value;   
		                            </script>
										<% q2++;%>
										<% 	p2++;	%>
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
			</s:if>
			<s:if test="trackLodgFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Lodging.Details" ondblclick="callShowDiv(this);"><%=label.get("Lodging.Details")%></label> </strong></td>
								<td colspan="1"> </td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="2">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="12%" colspan="1" class="formth"><label  class = "set"  id="Hotel.Type" ondblclick="callShowDiv(this);"><%=label.get("Hotel.Type")%></label></td>
										<td width="12%" colspan="1" class="formth"><label  class = "set"  id="Room.Type" ondblclick="callShowDiv(this);"><%=label.get("Room.Type")%></label></td>
										<td width="15%" colspan="1" class="formth"><label  class = "set"  id="City" ondblclick="callShowDiv(this);"><%=label.get("City")%></label></td>
										<td width="15%" colspan="1" class="formth"><label  class = "set"  id="Preferred.location" ondblclick="callShowDiv(this);"><%=label.get("Preferred.location")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Date" ondblclick="callShowDiv(this);"><%=label.get("From.Date")%></label></td>
										<td width="8%" colspan="1" class="formth"><label  class = "set"  id="From.Time" ondblclick="callShowDiv(this);"><%=label.get("From.Time")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Date" ondblclick="callShowDiv(this);"><%=label.get("To.Date")%></label></td>
										<td width="8%" colspan="1" class="formth"><label  class = "set"  id="To.Time" ondblclick="callShowDiv(this);"><%=label.get("To.Time")%></label></td>
										<td width="5%" colspan="1" class="formth"> </td>
									</tr>
									<%
									int s2 = 1;
									%>
									<%
									int t2 = 0;
									%>
									<s:iterator value="lodgList">
										<tr>
											<td width="5%" colspan="1" class="sortableTD"><%=s2%></td>
											<td width="12%" colspan="1" class="sortableTD">  <s:hidden name="hotelType"/> <s:property  value="hotelType"/> </td> 
											<td width="12%" colspan="1" class="sortableTD"><s:hidden name="roomType"/> <s:property  value="roomType"/>  </td>
											<td width="15%" colspan="1" class="sortableTD"><input
												type="hidden" name="lodgCity" value='<s:property  value="lodgCity"/>' id="lodgCity<%=s2%>" />
												<s:property  value="lodgCity"/> </td>
											<td width="15%" colspan="1" class="sortableTD"><s:hidden name="lodgLocation"  /> <s:property  value="lodgLocation"/> </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  	name="lodgFromdate"/>  <s:property  value="lodgFromdate"/>  </td>
											<td width="8%" colspan="1" class="sortableTD"><s:hidden 	name="lodgFromtime" />  <s:property  value="lodgFromtime"/> </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  name="lodgTodate" /> <s:property  value="lodgTodate"/>  </td>
											<td width="8%" colspan="1" class="sortableTD"><s:hidden name="lodgTotime"  /> <s:property  value="lodgTotime"/> </td>
											<td width="5%" colspan="1" class="sortableTD">  <input
												type="hidden" name="hidLodgChkBox" id="hidLodgChkBox<%=s2%>">
											</td>
											<script>	
		                                  lodgArr[<%=t2%>] = document.getElementById('lodgCity'+<%=s2%>).value;   
		                            </script>
											<%
											s2++;
											%>
											<%
											t2++;
											%>
										</tr>

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
			</s:if>
			<tr>
				     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
		    </tr>  
	    
		  
	</table>

</s:form> 
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
 
 