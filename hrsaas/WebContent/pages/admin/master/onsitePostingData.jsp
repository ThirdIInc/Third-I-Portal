<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="OnsitePosting" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="onSiteID" />
	<s:hidden name="loccationId" />
	<s:hidden name="hidSiteId" />
	<s:hidden name="hiddenSiteType" />

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
					<td width="93%" class="txt"><strong class="text_head">Onsite
					Posting </strong></td>
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

							<td width="20%"><label class="set" name="site.type"
								id="site.type1" ondblclick="callShowDiv(this);"><%=label.get("site.type")%></label>
							:<font color="red">*</font></td>
							<td width="30" colspan="2"><s:select tabindex="1"
								name="siteType" headerKey="" headerValue="  --Select--   "
								cssStyle="width:153;z-index:5;"
								list="#{'C':'Client','V':'Vender','P':'Partner'}" /></td>

							<td width="20%"><label class="set" name="site.address"
								id="site.address" ondblclick="callShowDiv(this);"><%=label.get("site.address")%></label>
							:</td>
							<td><s:textfield size="25" tabindex="6" theme="simple"
								maxlength="50" name="siteAddress1"
								onkeypress="return allCharacters();" /></td>
						</tr>

						<tr>

							<td width="20%"><label class="set" name="site.code"
								id="site.code1" ondblclick="callShowDiv(this);"><%=label.get("site.code")%></label>
							:<font color="red">*</font></td>
							<td width="30%" colspan="3"><s:textfield tabindex="2"
								size="25" theme="simple" maxlength="10" name="siteCode"
								onkeypress="return allCharacters();" /></td>

							<td width="25%"><s:textfield size="25" tabindex="7"
								theme="simple" maxlength="50" name="siteAddress2" /></td>
						</tr>

						<tr>
							<td width="20%"><label class="set" name="site.abbriviation"
								id="site.abbriviation1" ondblclick="callShowDiv(this);"><%=label.get("site.abbriviation")%></label>
							:<font color="red">*</font></td>
							<td width="25%" colspan="3"><s:textfield tabindex="3"
								size="25" theme="simple" maxlength="10" name="siteAbbreviation"
								onkeypress="return allCharacters();" /></td>

							<td><s:textfield size="25" tabindex="8" theme="simple"
								maxlength="50" name="siteAddress3" /></td>

						</tr>
						<tr>
							<td width="20%"><label class="set" name="site.name"
								id="site.name1" ondblclick="callShowDiv(this);"><%=label.get("site.name")%></label>
							:<font color="red">*</font></td>
							<td width="25%" colspan="2"><s:textfield tabindex="4"
								size="25" theme="simple" maxlength="45" name="siteName"
								onkeypress="return allCharacters();" /></td>

							<td width="20%"><label class="set" name="phone" id="phone"
								ondblclick="callShowDiv(this);"><%=label.get("phone")%></label>
							:</td>
							<td width="25%"><s:textfield size="25" tabindex="9"
								theme="simple" maxlength="15" name="phone"
								onkeypress="return numbersOnly();" /></td>
						<tr>
						<tr>
							<td width="20%"><label class="set" name="site.location"
								id="site.location" ondblclick="callShowDiv(this);"><%=label.get("site.location")%></label>
							:<font color="red">*</font></td>
							<td width="25"><s:textfield tabindex="5" size="25"
								theme="simple" maxlength="50" name="siteLocation"
								onkeypress="return allCharacters();" readonly="true" /></td>
							<td width="10%"><img id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15" onclick="callSearch('f9location');"></td>
							<td width="20%"><label class="set" name="email" id="email"
								ondblclick="callShowDiv(this);"><%=label.get("email")%></label>
							:</td>
							<td width="25%"><s:textfield size="25" tabindex="10"
								theme="simple" maxlength="30" name="emailId" /></td>
						<tr>
					</table>
					</td>
				</tr>
			</table>
		<tr>
			<td width="80%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		</td>
		</tr>
	</table>
</s:form>




<script type="text/javascript">
function saveFun()
 {
 

var site=document.getElementById('paraFrm_siteType').value;
var code=trim(document.getElementById('paraFrm_siteCode').value);
var abbreviation=trim(document.getElementById('paraFrm_siteAbbreviation').value);
var name=trim(document.getElementById('paraFrm_siteName').value);
var location=trim(document.getElementById('paraFrm_siteLocation').value);
var emailid=document.getElementById('paraFrm_emailId').value;

            if(site==""){    
            
                 alert("Please select "+document.getElementById('site.type1').innerHTML.toLowerCase());
                 document.getElementById('paraFrm_siteType').focus();
  			      return false;
  			     
  		        }  
  		        if(code==""){              
                 alert("Please enter "+document.getElementById('site.code1').innerHTML.toLowerCase());
  			     document.getElementById('paraFrm_siteCode').focus();
  			      return false;
  			     
  		        }  
  		            
  		        if(abbreviation==""){              
                 alert("Please enter "+document.getElementById('site.abbriviation1').innerHTML.toLowerCase());
  			      document.getElementById('paraFrm_siteAbbreviation').focus();
  			      return false;
  			     
  		        } 
            if(name==""){              
                 alert("Please enter "+document.getElementById('site.name1').innerHTML.toLowerCase());
                 document.getElementById('paraFrm_siteName').focus();
  			      return false;
  			     
  		        } 
  		        if(location==""){              
                 alert("Please select "+document.getElementById('site.location').innerHTML.toLowerCase());
                 document.getElementById('paraFrm_siteLocation').focus();
  			      return false;
  			     
  		        } 
  		         
  		         if(!emailid =="")
  			{
  			var abc=validateEmail('paraFrm_emailId');
  			if(!abc){
  			return false;
  			}
  			}
  	    document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='OnsitePosting_save.action';
     	document.getElementById('paraFrm').submit(); 
       
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'OnsitePosting_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'OnsitePosting_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	 
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'OnsitePosting_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'OnsitePosting_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}	
</script>