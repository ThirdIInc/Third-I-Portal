<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TravelProcessManager" validate="true" id="paraFrm" theme="simple">
	
	<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
		
		<tr>
			<td  width="4%" valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Travel Process Manager </strong></td>

		
			<td class="txt"><img src="../pages/images/recruitment/space.gif"
				width="5" height="5" /></td>
			<td class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td colspan="4"><s:submit cssClass="save" value="  Save"
						action="TravelProcessManager_save" onclick="return callReq();" /></td>
						<!--  <s:submit cssClass="reset" value="    Reset" action="TravelProcessManager_reset" />-->
			  

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

				
			</table>

			</td>
		</tr>

	
		<tr>
			<td width="100%" colspan="5">
			<table class="formbg" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td width="65%" class="txt" colspan="3"><label  class = "set"  id="trp.manager"   name="trp.manager" ondblclick="callShowDiv(this);"><%=label.get("trp.manager")%></label>
				</td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
				
				
				<tr>
					<td colspan="1" width="35%"><label  class = "set"  id="trv.required" name="trv.required" ondblclick="callShowDiv(this);"><%=label.get("trv.required")%></label> :</td>
					<td colspan="3" width="75%"><input type="checkbox" name="reqFlag" <s:property value="reqFlag" />
						id="reqFlag"  onclick="callReq();" /></td><s:hidden name="hidreqFlag" id="hidreqFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="app.required" name="app.required"  ondblclick="callShowDiv(this);"><%=label.get("app.required")%></label> :</td>
					<td colspan="3" width="75%"><input type="checkbox" name="reqAppFlag" <s:property value="reqAppFlag" />
						id="reqAppFlag"  onclick="callReq();"/></td><s:hidden name="hidreqAppFlag"
						id="hidreqAppFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="sc.required"  name="sc.required" ondblclick="callShowDiv(this);"><%=label.get("sc.required")%></label>  :</td>
					<td width="75%" colspan="3"><input  type="checkbox"  name="schdFlag" <s:property value="schdFlag"/>
						id="schdFlag"  onclick="callReq();" /></td><s:hidden name="hidschdFlag"
						id="hidschdFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="tsc.required"  name="tsc.required" ondblclick="callShowDiv(this);"><%=label.get("tsc.required")%></label> :</td>
					<td colspan="3" width="75%"><input type="checkbox" name="schdAppFlag" <s:property value="schdAppFlag"/>
						id="schdAppFlag"  onclick="callReq();"/></td><s:hidden name="hidschdAppFlag"
						id="hidschdAppFlag" ></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="trc.required"  name="trc.required" ondblclick="callShowDiv(this);"><%=label.get("trc.required")%></label> :</td>
					<td colspan="3" width="75%"><input type="checkbox" name="confFlag" <s:property value="confFlag" />
						id="confFlag" onclick="callReq();" /></td><s:hidden name="hidconfFlag"
						id="hidconfFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="tca.required"  name="tca.required" ondblclick="callShowDiv(this);"><%=label.get("tca.required")%></label> :</td>
					<td colspan="3" width="75%"><input type="checkbox" name="claimFlag" <s:property value="claimFlag"/>
						id="claimFlag"  onclick="callReq();"/></td><s:hidden name="hidclaimFlag"
						id="hidclaimFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="tc.required"  name="tc.required" ondblclick="callShowDiv(this);"><%=label.get("tc.required")%></label> :</td>
					<td colspan="3" width="75%"><input type="checkbox" name="appFlag" <s:property value="appFlag"/>
						id="appFlag" onclick="callReq();"/></td><s:hidden name="hidappFlag"
						id="hidappFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="sal.required"  name="sal.required" ondblclick="callShowDiv(this);"><%=label.get("sal.required")%></label> :</td>
					<td colspan="3" width="75%"><input type="checkbox" name="levelFlag" <s:property value="levelFlag" />
						id="levelFlag"  onclick="callReq();"/></td><s:hidden name="hidlevelFlag"
						id="hidlevelFlag"></s:hidden>
				</tr>
				

				<tr>
					<td colspan="1" width="25%"><label  class = "set"  id="tp.applicatioform"  name="tp.applicatioform" ondblclick="callShowDiv(this);"><%=label.get("tp.applicatioform")%></label>  :</td>
					
					<!-- ><td colspan="3" width="90%"><s:radio name="effectiveDate"
								list="#{'F':'Form Date','T':'To Date', 'T':'Travel Application Date'}"
								onclick="chkRadio(this);" theme="simple" /> <s:hidden name="hidfromDate" /></td>-->
					
					
					
					<s:hidden name="effectDate" id="effectDate"></s:hidden>
					 <td width="75%" colspan="3">
					 <input type="radio"  name="effectiveDate"  id="one" onclick="setPollValue(this);"  value="F"/><label  class = "set"  id="tf.date" name="tf.date" ondblclick="callShowDiv(this);"><%=label.get("tf.date")%></label> 
					<input type="radio" name="effectiveDate"     id="two" onclick="setPollValue(this);" value="T"/><label  class = "set"  id="tt.date" name="tt.date" ondblclick="callShowDiv(this);"><%=label.get("tt.date")%></label> 
					 <input type="radio" name="effectiveDate"  id="three" onclick="setPollValue(this);" value="A"/><label  class = "set"  id="ta.date" name="ta.date" ondblclick="callShowDiv(this);"><%=label.get("ta.date")%></label> </td>
				</tr>
				
				
</table>
				<tr><td width="100%" colspan="5">
				<table width="100%" class="formbg" border="0" >
		
					<td colspan="4" align="left"><s:submit cssClass="save" value="  Save"
						action="TravelProcessManager_save" onclick="return callReq();" />
						
			        </td>
			        	
		     </table></td></tr>
		</table>
		
		
	
	
	<input type="hidden" name="optionValue" id="optionValue" />
</s:form>
<script>
	
	callFun();
	
	function callFun()
	{
	
	var setRad=document.getElementById('effectDate').value;
	//alert(document.getElementById('effectDate').value);
	
	if(setRad=="F")
	{
	document.getElementById('one').checked="true";
	}
	else if(setRad=="T")
	{
	document.getElementById('two').checked="true";
	}
	
	else if(setRad=="A")
	{
	document.getElementById('three').checked="true";
	}
	
	}

	function callReq()
	{ 
	
	var opt = document.getElementById('optionValue').value;
	
	    
        
		if(document.getElementById('reqFlag').checked )
		{
		
		document.getElementById('hidreqFlag').value='Y'
		 
		}else
		{
		
		  document.getElementById('hidreqFlag').value='N';
		}
		if(document.getElementById('reqAppFlag').checked )
		{
		document.getElementById('hidreqAppFlag').value='Y'
		}else
		{
		  document.getElementById('hidreqAppFlag').value='N';
		}
		if(document.getElementById('schdFlag').checked )
		{
		document.getElementById('hidschdFlag').value='Y'
		}else
		{
		  document.getElementById('hidschdFlag').value='N';
		}
		if(document.getElementById('schdAppFlag').checked )
		{
		document.getElementById('hidschdAppFlag').value='Y'
		}else
		{
		  document.getElementById('hidschdAppFlag').value='N';
		}
		if(document.getElementById('confFlag').checked )
		{
		document.getElementById('hidconfFlag').value='Y'
		}else
		{
		  document.getElementById('hidconfFlag').value='N';
		}
		if(document.getElementById('claimFlag').checked )
		{
		document.getElementById('hidclaimFlag').value='Y'
		}else
		{
		  document.getElementById('hidclaimFlag').value='N';
		}
		if(document.getElementById('appFlag').checked )
		{
		document.getElementById('hidappFlag').value='Y'
		}else
		{
		  document.getElementById('hidappFlag').value='N';
		}
		if(document.getElementById('levelFlag').checked )
		{
		document.getElementById('hidlevelFlag').value='Y'
		}else
		{
		  document.getElementById('hidlevelFlag').value='N';
		}
			
	}
	
	function setPollValue(id){

	var opt=document.getElementById('optionValue').value =id.value;
	
}
	
</script>