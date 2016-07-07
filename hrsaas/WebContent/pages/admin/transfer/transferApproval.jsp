<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<s:form  action="TransferApproval" validate="true" id="paraFrm"
	 theme="simple">
	
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
       
       
        <tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Transfer Approval
					</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
        <!-- <tr>
          <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead"> Transfer Approval</strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
        </tr>-->
       
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="27" class="formtxt"> <a href="TransferApproval_chkeckdata.action?status=P">Pending List</a> |  <a href="TransferApproval_chkeckdata.action?status=A">Approved List</a> |  <a href="TransferApproval_chkeckdata.action?status=R">Rejected List</a> </td>
                      </tr><s:hidden name="apprflag"/><s:hidden name="listLength"></s:hidden>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td width="78%"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /><br />
              <s:if test="%{apprflag}"></s:if><s:else> <input   type="button" class="save" value="    Save " onclick="saveValidate();" /></s:else>
              <input type="submit" class="reset" value="    Reset" onclick="return callReset();" /></td>
              <td width="22%"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
            <s:hidden name="status" /></tr>
          </table>            
          <label></label></td>
        </tr>
       
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <s:if test="%{pen}"><td height="27" class="formtxt"><strong>Pending List</strong></td></s:if>
                    <s:elseif test="%{app}"><td height="27" class="formtxt"><strong>Approved List</strong></td></s:elseif>
                    <s:elseif test="%{rej}"><td height="27" class="formtxt"><strong>Rejected List</strong></td></s:elseif>
                    <s:elseif test="%{hol}"><td height="27" class="formtxt"><strong>On-Hold List</strong></td></s:elseif>
                    
                    <s:else><td height="27" class="formtxt"><strong>Pending List</strong></td></s:else>
                  </tr>
                  
                  
	 
	  
                  <tr>
                    <td>
                    <table width="100%" border="0" cellpadding="2" cellspacing="2"   class="sortable">
                        <tr >
                        <td width="8%" valign="top" class="formth"><label  class = "set" name="sr.no." id="sr.no." ondblclick="callShowDiv(this);"><%=label.get("sr.no.")%></label></td>
                         <td width="12%" valign="top" class="formth"><label  class = "set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
                         <td width="30%" valign="top" class="formth"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
                         <td width="14%" valign="top" class="formth"><label  class = "set" name="application.date" id="application.date" ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label> </td>
                         <td width="10%" valign="top" class="formth"><label  class = "set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
                         <td width="5%" valign="top" class="formth"><label  class = "set" name="view" id="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label></label></td>
                         <td width="20%" valign="top" class="formth"><label  class = "set" name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></td>
                   
                   
                        
                        </tr>
                        <s:if test="noData">
	                        <tr>
	                        	<td width="100%" colspan="7" align="center"><font color="red">No Data To Display</font></td>
	                        </tr></s:if>
                        <%! int i = 0 ; %>
                        <% int k = 1; %>
                  <%
						
						int s = 1;
				%>      
            <s:iterator value="trfList">
		           	
	 <tr>	 
		   <td class="td_bottom_border"  align="center" width="8%"><%=s%></td>
	      <td class="td_bottom_border" width="12%"><s:property value="empToken"/><s:hidden name="trfId"/><s:hidden name="level"/><s:hidden name="empId"/><s:hidden name="newDeptId"/><s:hidden name="newDivId"/><s:hidden name="newCentId"/></td>
	       <td class="td_bottom_border" width="30%"><s:property value="empName"/></td>  	  	
          <td class="td_bottom_border" width="14%"><s:property value="appDt"/></td>
          <td align="center" class="td_bottom_border" width="10%">
          	<s:if test="%{statusFlag}"><s:property value="statusNew"/></s:if><s:else>
          <s:select name="statusList" id='<%="check"+k %>'
		   cssStyle="width:75" theme="simple" list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
		   </s:else>
		   </td>
					 
		<td class="td_bottom_border" width="5%" align="center">
	       <input type="button" name="view_Details" class="token" value="View Details" 
	      	   								onclick = "viewDetails('<s:property value="trfId"/>')"/>
	      	   								
	      	<s:if test="%{statusFlag}"><td class="border2" width="21%"><s:property value="remark"  /></td></s:if>  
	      	<s:else> 								
	      	<td class="td_bottom_border" width="20%"><s:textarea theme="simple" cols="30" rows="1.5" name="remark"  /></td>
	    </s:else>
	      
	    
	          
	      </td>
	      
	  </tr>   
	 <%k++; %>
	  <%s++; %>
	</s:iterator>
               <% i=k ; %>      
                    </table></td>
                  </tr>
                  <tr>
                    <td  class="cellbg"><table width="130" border="0" align="right" cellpadding="2" cellspacing="2">
                        <tr>
                          <td><strong><label  class = "set" name="page" id="page" ondblclick="callShowDiv(this);"><%=label.get("page")%> </strong></td>
                          <td><img src="../pages/images/recruitment/first.gif" width="10" height="10" /></td>
                          <td><img src="../pages/images/recruitment/previous.gif" width="10" height="10" /></td>
                          <td><div align="center">0-1</div></td>
                          <td><img src="../pages/images/recruitment/next.gif" width="10" height="10" /></td>
                          <td><img src="../pages/images/recruitment/last.gif" width="10" height="10" /></td>
                       <s:hidden name="hiddenTrfCode" /> </tr>
                  
                 
                  
                </table>
                </td>
              </tr>
        <tr>
          <td colspan="3">
          <s:if test="%{apprflag}"></s:if><s:else>
          
      
          <input type="button" class="save" value="    Save " onclick="saveValidate();" />
         
          
          </s:else>
            <input  type="submit" class="reset" value="    Reset"  onclick="return callReset();" /></td>
        </tr>
  </table>
</td>
</tr>

</table>
</td>
</tr>
</table>
</s:form>		

	<script>
	 callOnload();
	
	function saveValidate(){
	if(document.getElementById("paraFrm_listLength").value==0){
	alert("No Record to Save");
	return false;
	}else{
	document.getElementById("paraFrm").action="TransferApproval_save.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
		function callReset(){
			document.getElementById("paraFrm").action="TransferApproval_reset.action";
 	document.getElementById("paraFrm").submit();
	 }
	 function callOnload(){
	 	var check =<%=i%>;
	 	
	 if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
			
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;
			
			
			
			}
		}
	}
	
	
	function viewDetails(transferCode){
		document.getElementById('paraFrm_trfId').value = transferCode;
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "TransferApplication_callByTransferApprove.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
</script>