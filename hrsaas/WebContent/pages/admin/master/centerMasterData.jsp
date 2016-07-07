<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="CenterMaster" id="paraFrm" validate="true" target="main"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	<s:hidden name="cntrMaster.centerID" />


<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Branch
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
		
		<tr>
		<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
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
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
			
		
		<tr>
			
			<td width="15%" colspan="1" height="22"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red">*</font></td>
			<td width="85%" colspan="2"   height="22"><s:textfield
				size="25" theme="simple" maxlength="60"
				name="cntrMaster.centerName" onkeypress="return allCharacters();"/></td>
		</tr>
		
		<tr>
			
			<td width="15%" colspan="1" height="22"><label  class = "set" name="branch.abbr" id="branch.abbr" ondblclick="callShowDiv(this);"><%=label.get("branch.abbr")%></label>:</td>
			<td width="85%" colspan="2"   height="22"><s:textfield
				size="25" theme="simple" maxlength="20"
				name="cntrMaster.centerAbbr" onkeypress="return allCharacters();"/></td>
		</tr>
		
		<tr>
		
		
		
			
			<td width="15%" colspan="1"></td>
			<td width="85%" colspan="2">
			<s:hidden  theme="simple"
				name="cntrMaster.locName" onkeypress="return allCharacters();"/>
			
				
			</td>
			</tr>
			
		<tr>
			
			<td width="15%" colspan="1" ><label  class = "set" name="address" id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label> :<font color="red">*</font></td>
			<td width="85%" colspan="2" height="22"><s:textfield
				size="25" theme="simple" maxlength="90" 
				name="cntrMaster.add1" /></td>

		</tr>
			<tr> <td width="15%" colspan="1"></td>
			<td  width="85%" colspan="2" height="22"><s:textfield
				 size="25" theme="simple"  maxlength="90" 
				name="cntrMaster.add2" /></td>	
		   </tr>
		<tr> <td width="15%" colspan="1"></td>
		    <td width="55%" colspan="2" height="22"><s:textfield
				size="25" theme="simple"  maxlength="90" 
				name="cntrMaster.add3" /></td>	
		</tr>
		<tr><s:hidden
				theme="simple"
				name="cntrMaster.locId" />			
			<td width="15%" colspan="1"><label  class = "set" name="city" id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font color="red">*</font></td>
			<td width="85%" colspan="2" height="22"><s:textfield
				size="25" theme="simple"
				name="cntrMaster.city"  readonly="true"/>
				<s:if test="cntrMaster.viewFlag"> <img id='ctrlHide'  align="absmiddle"
										src="../pages/common/css/default/images/search2.gif"
										width="16" height="15"
				onclick="callSearch('f9city');"></s:if>
				</td>

		</tr>		
		<tr>			
			<td width="15%" colspan="1"><label  class = "set" name="pin" id="pin" ondblclick="callShowDiv(this);"><%=label.get("pin")%></label> :</td>
			<td width="85%" colspan="2" height="22"><s:textfield
				size="25" theme="simple"   maxlength="12" onkeypress="return numbersOnly();"
				name="cntrMaster.pin" /></td>
		</tr>
		<tr>	
		<td height="22" class="formtext"><label  name="zone" id="zone" ondblclick="callShowDiv(this);"><%=label.get("zone")%></label> :<font color="red">*</font></td>
							<td height="22">
                     
                 <s:select name="cntrMaster.zone" list="map" 
				 size="1" /></td>
				 </tr>
		<tr>
			<td width="15%" colspan="1"><label  class = "set" name="telephone" id="telephone" ondblclick="callShowDiv(this);"><%=label.get("telephone")%></label> :</td>
			<td width="85%" colspan="2"  height="22"><s:textfield  maxlength="15" onkeypress="return numbersOnly();"
				size="25" theme="simple"
				name="cntrMaster.tel" /></td>
		</tr>
		<tr>
			
			<td width="15%" colspan="1"><label  class = "set" name="fax" id="fax" ondblclick="callShowDiv(this);"><%=label.get("fax")%></label> :</td>
			<td width="85%" colspan="2" height="22"><s:textfield
				size="25" theme="simple"  maxlength="15" onkeypress="return numbersOnly();"
				name="cntrMaster.fax" /></td>
		
		</tr>
		<tr>
			<td width="15%" colspan="1"><label class="set" name="centerType"
						id="centerType" ondblclick="callShowDiv(this);"><%=label.get("centerType")%></label>:</td>
			<td colspan="2"><s:select list= " #{'B':'Branch','C':'Client_Site'}" name="cntrMaster.centerType" /></td>
		</tr>
		<tr>
					<td colspan="1"><label class="set" name="is.active"
						id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					:</td>
					<td colspan="2"><s:select cssStyle="width:152"
						list=" #{'Y':'Yes','N':'No'}" name="cntrMaster.isActive" /></td>
		
		
		
		</tr>	
		<s:hidden name="cntrMaster.esiZone" value="Y" /> 
		<s:hidden name="cntrMaster.ptZone" value="Y" />
		<s:hidden name="cntrMaster.pfZone" value="Y" />
		
		
		
		
		</table>
		</td></tr>
		</table>
		
		<tr>
		<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
		</table>
		</s:form>
		
		
		
<script type="text/javascript">
function saveFun(type)
 {

var fieldName = ["paraFrm_cntrMaster_centerName","paraFrm_cntrMaster_add1"];
var lableName = ["branch","address","city"];
var flag = ["enter","enter","select"];
var fieldName1 = ["paraFrm_cntrMaster_centerName"];
var centerCity =document.getElementById('paraFrm_cntrMaster_city').value;
/*if(type == 'update'){
		if(document.getElementById('paraFrm_cntrMaster_centerID').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_cntrMaster_centerID').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}*/

     if(!validateBlank(fieldName, lableName,flag)){
     
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
       
        if(centerCity == ""){
  			      alert(" Please select "+document.getElementById('city').innerHTML.toLowerCase());
  			      return false;
  		          }
    
      document.getElementById('paraFrm').target = "_self";
     	
  		document.getElementById('paraFrm').action = 'CenterMaster_save.action';
     	document.getElementById('paraFrm').submit(); 
     	    return true;
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CenterMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CenterMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CenterMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'CenterMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
		
		
		function editFun() {
		return true;
	}	
		</script>
		
		
		
		