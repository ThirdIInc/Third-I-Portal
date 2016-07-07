<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="InductionFeedback" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!-- Final Table -->
		 <tr><!--start Induction Feedback-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 1-->
					<tr>
						<td colspan="5" valign="bottom" class="txt">&nbsp;</td>
					</tr>
					
					<tr>
	      				<td colspan="3" width="100%">
	        				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
								<tr>
									<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
									<td width="93%" class="txt"><strong class="text_head">Induction Feedback</strong></td>
									<td width="3%" valign="top" class="txt">  <div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>  </td>
								</tr>
							</table>
		  				</td>
					</tr>
				</table><!-- End of Table 1-->
			</td>
		</tr><!--End Induction Feedback-->
		<tr>
			<td>
				<table width="100%" align="center" cellpadding="0" cellspacing="2"><!--button panel-->
					<tr>
         				 <td>
         					 <input	type="submit" name="close" class="token" value="Close" onclick="closeWindow()"/>              				
             	 		</td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr>
		<tr><!--Schedule interview-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"><strong>Induction Feedback</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
								<tr>
									<td width="20%" bgcolor="#F2F2F2"><label  class = "set" name="indname" id="indname" 
			            				ondblclick="callShowDiv(this);"><%=label.get("indname")%></label> :</td>
									<td width="25%"><s:property value="inductionName"/><s:hidden name="inductionCode"/>
									<s:hidden name="inductionDtlCode"/><s:hidden name="inductionName"/>
									</td>
									<td width="20%" bgcolor="#F2F2F2"><label  class = "set" name="cntperson" id="cntperson" 
			            				ondblclick="callShowDiv(this);"><%=label.get("cntperson")%></label> :</td>
									<td width="25%"><s:hidden name="cntPersonId" /><s:hidden name="cntPersonToken"/><s:property value="contactPerson"/>
									<s:hidden name="contactPerson" /></td>
								</tr>
								<tr>
									<td width="20%" bgcolor="#F2F2F2"><label  class = "set" name="indfrom" id="indfrom" 
			            				ondblclick="callShowDiv(this);"><%=label.get("indfrom")%></label> :</td>
									<td width="25%"><s:property value="inductionFrom"/><s:hidden name="inductionFrom" /></td>
									<td width="20%" bgcolor="#F2F2F2"><label  class = "set" name="indto" id="indto" 
			            				ondblclick="callShowDiv(this);"><%=label.get("indto")%></label> :</td>
									<td width="25%"><s:property value="inductionTo"/><s:hidden name="inductionTo" /></td>	
								</tr>
								<tr>
									<td width="19%" colspan="1" bgcolor="#F2F2F2"><label  class = "set" name="inddesc" id="inddesc" 
			            				ondblclick="callShowDiv(this);"><%=label.get("inddesc")%></label> :</td>
									<td width="81%" colspan="3"><s:property value="inducDesc" /><s:hidden name="inducDesc" /></td>
									<td width="40%"></td>
								</tr>
								<tr>
									<td width="19%" colspan="1" bgcolor="#F2F2F2"><label  class = "set" name="indvenue" id="indvenue" 
			            				ondblclick="callShowDiv(this);"><%=label.get("indvenue")%></label> :</td>
									<td width="81%" colspan="3"><s:property value="inducVenue" /><s:hidden name="inducVenue" /></td>
									<td width="40%"></td>
								</tr>
							</table><!--Table header-->
						</td>
					</tr>		
				</table><!--Table 2-->
			</td>
		</tr>
		<tr>
			<td colspan="5" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr><!--Activity Details-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"><strong>Please give your comments about the Induction Program</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td colspan="5" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>					
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Table 6-->
								<tr>
									<td width="5%" valign="top" class="formth" ><label  class = "set" name="srno" id="srno" 
			            					ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
									<td width="50%" valign="top" class="formth"><label  class = "set" name="parameter" id="parameter" 
			            				ondblclick="callShowDiv(this);"><%=label.get("parameter")%></label></td>
									<td width="20%" valign="top" class="formth"><label  class = "set" name="rating" id="rating" 
			            				ondblclick="callShowDiv(this);"><%=label.get("rating")%></label></td>
									<td width="30%" valign="top" class="formth"><label  class = "set" name="comments" id="comments" 
			            				ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
								</tr>
						<%!int p = 1, s = 0;%>
						<s:iterator value="questionList">
							<tr>
								<td width="5%" class="border2"><%=p %></td>
								<td width="50%" class="border2"><s:hidden
									name="quesCode" /> <s:property value="quesName" /></td>

								<td class="border2" width="20%" nowrap="nowrap">
									<s:property value="rating"/>
								</td>
								<td class="border2" width="30%" nowrap="nowrap"><s:property
									value="comments"/></td>
							</tr>
							<%
							p++;
							%>

						</s:iterator>
						<%
							s = p;
							p = 1;
						%>
							</table><!--Table 6-->
						</td>
					</tr>	
					<tr>
          			  <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          			</tr>
          			<tr>
          			  <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          			</tr>	
					<tr><!-- Start of consultant list -->
			     	   <td colspan="5">
			       			<table width="100%" border="0" cellpadding="0" cellspacing="0" ><!-- table 7 -->
			       				<tr>
			       					<td width="15%">
			       						<label  class = "set" name="suggestion" id="suggestion"	ondblclick="callShowDiv(this);"><%=label.get("suggestion")%></label> :
			       					</td>
			       					<td width="60%">
			       						<s:property value="suggestion" />
									</td>
					
								</tr>
								<tr>
								    <td width="15%">
								    	<s:hidden name="dataPath" />
			       						<label  class = "set" name="attach" id="attach"	ondblclick="callShowDiv(this);"><%=label.get("attach")%></label> :
			       					</td>
			       				<s:if test="showAttachedFileLink">
			       					<td width="60%">
			       						<a title="Click on this link to view attached file" href="#"	onclick=" viewAttachedFile('<s:property value="attachFile" />');">
											<s:hidden name="attachFile" /> 
											<s:property value="attachFile" /><br>
										</a>
									</td>
			       				</s:if>
			       				<s:else>
			       					
			       				</s:else>	
								</tr>					
							</table><!-- table 7 -->
						</td>
					</tr>					
				</table><!--Table 3-->
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" align="center" cellpadding="0" cellspacing="2"><!--button panel-->
					<tr>
         				  <td>
         					 <input	type="submit" name="close" class="token" value="Close" onclick="closeWindow()"/>              				
             	 		</td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr>								
	</table><!-- Final Table -->	
</s:form>

<script>

	function closeWindow(){
		window.close();
	}
	
	function viewAttachedFile(attachFile)
	{
		//var attachFile = document.getElementById('paraFrm_attachFile').value;
		if(attachFile == '') {
			alert('File is not attached.');
			return false;
		 }
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "InductionFeedback_viewAttachedFile.action?attachFile="+attachFile;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
</script>