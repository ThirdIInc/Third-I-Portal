  <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TestInterviewReport" validate="true" id="paraFrm" theme="simple">
	<table  width="100%" border="0"  align="right" cellpadding="0" cellspacing="0" class="formbg">
	
	<s:hidden name="itreqsiName"/>
 	<s:hidden name="itconducted"/>
 	<s:hidden name="itnonconducted"/>
 	<s:hidden name="itcanceled"/> 
	
	<s:hidden name="intStatusReport" />
	<s:hidden name="reqsId" /> 
	<s:hidden name="reqCode" />
	<s:hidden name="dataLength" />
	<s:hidden name="testStatus" />
	<s:hidden name="intStatus"/>
		
		
				
			<!-- 	<tr>
					<s:if test="summaryflag"><td height="27" class="formtxt"><strong class="text_head">Test
					List :</strong></td></s:if>
					<s:else>
					<td width="100%"><strong class="text_head">Interview
					List :</strong></td></s:else>
				</tr> -->
				
				<tr>
					<td>	
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>

							<td width="10%" class="formth"><b> <label class="set"
								id="requiName" name="requiName" ondblclick="callShowDiv(this);"><%=label.get("requiName")%></label></b></td>
							<td width="10%" class="formth"><b> <label class="set"
								id="conducted" name="conducted" ondblclick="callShowDiv(this);"><%=label.get("conducted")%></label></b>
							</td>
							<td width="15%" class="formth"><b> <label class="set"
								id="nonconducted" name="nonconducted"
								ondblclick="callShowDiv(this);"><%=label.get("nonconducted")%></label>
							</b></td>
							<td width="10%" class="formth"><b> <label class="set"
								id="canceled" name="canceled" ondblclick="callShowDiv(this);"><%=label.get("canceled")%></label>
							</b></td>
						</tr>
						<%
						int j = 1;
						%>
						<s:iterator value="summaryList">
							<tr>
								<td width="10%" class="sortableTD" align="left"><s:property
									value="itreqsiName" />&nbsp;</td>
								<td width="10%" class="sortableTD" align="center"><s:property
									value="itconducted" />&nbsp;</td>
								<td width="15%" class="sortableTD" align="center"><s:property
									value="itnonconducted" />&nbsp;</td>
								<td width="10%" class="sortableTD" align="center">&nbsp;<s:property
									value="itcanceled" /></td>

							</tr>
							<%
							j++;
							%>
						</s:iterator>
					</table>
				</td>
			</tr>
				 
				 <tr>
		 
		   <td width="95%" > <input type="button" value=" Cancel " class="token" onclick="callCancell();">
		  </td>
		</tr>
				</table>
		
</s:form> 
<script>
function callCancell(){
 
		
 window.close();
 }
 
 </script>