 <%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SchTestAnalysis" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	  <tr height="25">
		  <td colspan="1" width="25%">  
		    <input type="button" value=" Close " class="token" onclick="callCancel();">
		  </td>
		   <td colspan="1"  width="50%" align="center">  
		     <strong>Schedule Test Summary </strong>
		  </td>
		  <td> &nbsp;</td>
		</tr> 
	   <tr>
		  <td colspan="3" width="100%">   <s:hidden name="noDataSummFlag"/> <s:hidden name="dataSummLength"/> 
		  <s:if test="%{dataSummLength<19}">
		   <table width="100%" border="0" cellpadding="0" cellspacing="0">
			 <tr>
			    <td width="10%" class="formth" ><b> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b></td>
			   <td width="30%" class="formth" ><b> <label  class = "set"  id="reqs.code"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </b></td>
			    <td width="15%" class="formth" ><b> <label  class = "set"  id="schedule"  name="schedule" ondblclick="callShowDiv(this);"><%=label.get("schedule")%></label> </b></td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="reschedule"  name="reschedule" ondblclick="callShowDiv(this);"><%=label.get("reschedule")%></label></b></td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="conducted"  name="conducted" ondblclick="callShowDiv(this);"><%=label.get("conducted")%></label></b> </td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="cancel"  name="cancel" ondblclick="callShowDiv(this);"><%=label.get("cancel")%></label> </b> </td>
			 </tr> 
			</table>
			</s:if>
			<s:else>
			 <table width="100%" border="0" cellpadding="0" cellspacing="0">
			 <tr>
			    <td width="10%" class="formth" ><b> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b></td>
			   <td width="30%" class="formth" ><b> <label  class = "set"  id="reqs.code"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </b></td>
			    <td width="15%" class="formth" ><b> <label  class = "set"  id="schedule"  name="schedule" ondblclick="callShowDiv(this);"><%=label.get("schedule")%></label> </b></td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="reschedule"  name="reschedule" ondblclick="callShowDiv(this);"><%=label.get("reschedule")%></label></b></td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="conducted"  name="conducted" ondblclick="callShowDiv(this);"><%=label.get("conducted")%></label></b> </td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="cancel"  name="cancel" ondblclick="callShowDiv(this);"><%=label.get("cancel")%></label> </b> </td>
			  	<td width="8%" class="formth">&nbsp;&nbsp; </td>
			 </tr> 
			</table>
			</s:else>
			<div style="height: 300;overflow: auto;" >
			 <table width="100%" border="0" cellpadding="0" cellspacing="0"> 
				 <%int i=1; %>  
				  <s:iterator value="summaryList"> 
					 <tr> 
					   <td width="10%" class="sortableTD" align="center" > <%=i%> 
					    <s:hidden name="sReqName"/> <s:hidden name="sSchedule"/>  
					    <s:hidden name="sReschedule" />  <s:hidden name="sConducted"/> <s:hidden name="sCancel"/>
					   </td>
					   <td width="30%" class="sortableTD"  > <s:property value="sReqName"/> </td>
					    <td width="15%" class="sortableTD" align="right" > <s:property value="sSchedule"/> </td>
					   <td width="15%" class="sortableTD" align="right" ><s:property value="sReschedule"/> </td>
					   <td width="15%" class="sortableTD" align="right" ><s:property value="sConducted"/> </td>
					   <td width="15%" class="sortableTD" align="right" > <s:property value="sCancel"/> </td>  
					</tr>  
					<%i++;%>
				 </s:iterator>  
				 <s:if test="noDataSummFlag">
					<tr>
						<td width="100%" align="center"><font
							color="red">There is no data to display.</font></td>
					</tr>
				 </s:if>  	 
	         </table> 
	        </div>
	     </td>
	  </tr>  
	    <tr height="35">
		  <td colspan="1" align="left">  
		    <input type="button" value=" Close " class="token" onclick="callCancel();">
		  </td>
		  <td colspan="2" align="right"><s:if test="noDataSummFlag"> </s:if><s:else><b>Total records : <s:property value="dataSummLength"/></b> </s:else>&nbsp; </td> 
		</tr>
 </table>
</s:form> 
<script> 

 
function callCancel()
{
  window.close();
}  
</script>