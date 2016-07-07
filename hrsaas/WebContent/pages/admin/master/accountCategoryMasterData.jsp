<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="AccountCategoryMaster" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="acctNameId"/>
	<s:hidden name="accountID"/>



	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Account Category </strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="80%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="20%">
						<div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%">
							<table width="100%" border="0" align="center" cellpadding="2"
								cellspacing="2">
								<tr>
									<td width="15%"><label class="set" name="account.name"
										id="account.name1" ondblclick="callShowDiv(this);"><%=label.get("account.name")%></label>
									:<font color="red">*</font></td>
									
									<td width="45%" colspan="2"><s:textfield 
										size="25" theme="simple" maxlength="50" name="accountName"
										 /></td>
									</tr>
									<tr>
									<td width="15%"><label class="set" name="account.abbriviation"
										id="account.abbriviation1" ondblclick="callShowDiv(this);"><%=label.get("account.abbriviation")%></label>
									:<font color="red">*</font></td>
									<td width="45" colspan="1"><s:textfield 
										size="25" theme="simple" maxlength="10" name="accountAbbreviation"
										 /></td>
								</tr>
								
								<tr>
	<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" 			ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
	</td>
	<td>
		<s:select name="isActive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
	</td>
</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="80%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>




<script type="text/javascript">
function saveFun()
 {
 var f9Fields=["paraFrm_accountName","paraFrm_accountAbbreviation"];
var name=trim(document.getElementById('paraFrm_accountName').value);
var abbr=trim(document.getElementById('paraFrm_accountAbbreviation').value);


            if(name==""){    
            
                 alert("Please enter "+document.getElementById('account.name1').innerHTML.toLowerCase());
                 document.getElementById('paraFrm_accountName').focus();
  			      return false;
  			     
  		        }  
  		        if(abbr==""){              
                 alert(" Please enter "+document.getElementById('account.abbriviation1').innerHTML.toLowerCase());
  			     document.getElementById('paraFrm_accountAbbreviation').focus();
  			      return false;
  			     
  		        }  
  		          if(!f9specialchars(f9Fields))
     		return false;
     	
  		         
  	    document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='AccountCategoryMaster_save.action';
     	document.getElementById('paraFrm').submit(); 
       
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AccountCategoryMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccountCategoryMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccountCategoryMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'AccountCategoryMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}	
		
		
		</script>
