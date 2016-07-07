<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ include file="/pages/common/labelManagement.jsp" %>
<s:form action="MapDebits" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >    
      
     
    <tr>
	     <td width="100%">
	     	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	      	  <tr>
				 <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				 	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				 <td width="93%" class="txt"><strong class="text_head">Map Debits </strong></td>
				 <td width="3%" valign="top" class="txt"><div align="right">
				   	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
			  </tr>
	        </table>
	     </td>
    </tr>
    
    <tr>
      <td>
      	<table width="100%" border="0" cellpadding="2" cellspacing="2">         
          <tr>
            <td width="78%">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />  
  			</td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required  </div></td>
          </tr>
        </table>
      </td>
    </tr>
    
    
    
    <tr>
    	<td>
    		<table width="100%" class="formbg">
    			<tr>
    				<td class="formtext">
						<table width="100%" border="0">
							<tr>
								<td class="formth" align="center" width="60%">
									<label class="set" name="debitHead" id="debitHead" ondblclick="callShowDiv(this);"> <%=label.get("debitHead")%></label>
								</td>
								<td class="formth" align="center" width="40%">
									<label class="set" name="debitType" id="debitType" ondblclick="callShowDiv(this);"> <%=label.get("debitType")%></label>
								</td>
							</tr>
							
							<s:iterator value="mapDebitList" >
							<tr>
								<td class="sortableTD">&nbsp;
									<s:hidden name="debitID" />
									<s:property	value="debitName" />																			
								</td>
								
								<td class="sortableTD" align="center">&nbsp;
								<s:select  headerKey="" headerValue="------- Select Type -------" cssStyle="width:50%" name="debitType" list="#{'ADV':'Advances', 'PF':'PF', 'ESI':'ESI', 'PT':'PT', 'IT':'IT', 'LWB':'LWB', 'OTH':'Others Deductions'}" />
								</td>
							</tr>
							</s:iterator>
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
           <td width="78%">
            	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />            			
			</td>
         </tr>
        </table>
      </td>
    </tr>
    
    </table>
    
</s:form>

<script>
	function saveFun()
	{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='MapDebits_save.action';
	  	document.getElementById('paraFrm').submit();
	}
	
	function resetFun()
	{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='MapDebits_reset.action';
	  	document.getElementById('paraFrm').submit();
	}
</script>