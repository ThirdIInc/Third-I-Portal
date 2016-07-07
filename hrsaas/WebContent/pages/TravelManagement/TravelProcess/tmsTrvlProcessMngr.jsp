<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelProMngr" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" border="0" cellpadding="2" cellspacing="2"
		class="formbg">

		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Travel
			Process Manager </strong></td>


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
						action="TravelProMngr_save" onclick="return callReq();" /></td>



					<td width="22%"></td>
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
					<td width="65%" class="txt" colspan="3"><label class="set"
						name="trvl.proMngr" id="trvl.proMngr"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.proMngr")%></label>
					</td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>


				<tr>
					<td colspan="1" width="35%"><label class="set"
						name="trvl.requiReq" id="trvl.requiReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.requiReq")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="reqFlag" <s:property value="reqFlag" /> id="reqFlag"
						onclick="callReq();" /></td>
					<s:hidden name="hidreqFlag" id="hidreqFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.trvlRequiApprReq" id="trvl.trvlRequiApprReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlRequiApprReq")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="reqAppFlag" <s:property value="reqAppFlag" />
						id="reqAppFlag" onclick="callReq();" /></td>
					<s:hidden name="hidreqAppFlag" id="hidreqAppFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.trvlSchReq" id="trvl.trvlSchReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlSchReq")%></label>
					:</td>
					<td width="75%" colspan="3"><input type="checkbox"
						name="schdFlag" <s:property value="schdFlag"/> id="schdFlag"
						onclick="callReq();" /></td>
					<s:hidden name="hidschdFlag" id="hidschdFlag"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.trvlSchApprReq" id="trvl.trvlSchApprReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlSchApprReq")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="schdAppFlag" <s:property value="schdAppFlag"/>
						id="schdAppFlag" onclick="callReq();" /></td>
					<s:hidden name="hidschdAppFlag" id="hidschdAppFlag"></s:hidden>
				</tr>


				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.apprFrMainSch" id="trvl.apprFrMainSch"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.apprFrMainSch")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="apprMainSch" <s:property value="apprMainSch" />
						id="apprMainSch" onclick="callReq();" /></td>
					<s:hidden name="hidapprMainSch" id="hidapprMainSch"></s:hidden>
				</tr>






				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.trvlConfrmReq" id="trvl.trvlConfrmReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlConfrmReq")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="confFlag" <s:property value="confFlag" /> id="confFlag"
						onclick="callReq();" /></td>
					<s:hidden name="hidconfFlag" id="hidconfFlag"></s:hidden>
				</tr>




				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.trvlClmAppReq" id="trvl.trvlClmAppReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlClmAppReq")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="claimFlag" <s:property value="claimFlag"/> id="claimFlag"
						onclick="callReq();" /></td>
					<s:hidden name="hidclaimFlag" id="hidclaimFlag"></s:hidden>
				</tr>


				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.trvlClmAppdReq" id="trvl.trvlClmAppdReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlClmAppdReq")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="claimAppFlag" <s:property value="claimAppFlag"/>
						id="claimAppFlag" onclick="callReq();" /></td>
					<s:hidden name="hidclaimAppFlag" id="hidclaimAppFlag"></s:hidden>
				</tr>


				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.secLevApprReq" id="trvl.secLevApprReq"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.secLevApprReq")%></label>:</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="levelFlag" <s:property value="levelFlag" /> id="levelFlag"
						onclick="callReq();" /></td>
					<s:hidden name="hidlevelFlag" id="hidlevelFlag"></s:hidden>
				</tr>



				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="trvl.trvlPolEffctDate" id="trvl.trvlPolEffctDate"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlPolEffctDate")%></label>:</td>

					<s:hidden name="effectDate" id="effectDate"></s:hidden>
					<td width="75%" colspan="3"><input type="radio"
						name="effectiveDate" id="one" onclick="setPollValue(this);"
						value="0" /><label class="set" name="trvl.FmDate" id="trvl.FmDate"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.FmDate")%></label>
					<input type="radio" name="effectiveDate" id="two"
						onclick="setPollValue(this);" value="1" /><label class="set"
						name="trvl.toDate" id="trvl.toDate"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.toDate")%></label>
					<input type="radio" name="effectiveDate" id="three"
						onclick="setPollValue(this);" value="2" /><label class="set"
						name="trvl.trvlAppDate" id="trvl.trvlAppDate"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlAppDate")%></label>
				</tr>


			</table>
		<tr>

			<td colspan="4" align="left"><s:submit cssClass="save"
				value="  Save" action="TravelProMngr_save"
				onclick="return callReq();" /></td>
		</tr>
	</table>


	<input type="hidden" name="optionValue" id="optionValue" />
</s:form>
<script>
	
	callFun();
	
	function callFun()
	{
	
	var setRad=document.getElementById('effectDate').value;
	//alert(document.getElementById('effectDate').value);
	
	if(setRad=="0")
	{
	document.getElementById('one').checked="true";
	}
	else if(setRad=="1")
	{
	document.getElementById('two').checked="true";
	}
	
	else if(setRad=="2")
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
		
		
		if(document.getElementById('apprMainSch').checked )
		{
		document.getElementById('hidapprMainSch').value='Y'
		}else
		{
		  document.getElementById('hidapprMainSch').value='N';
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
		
		
		
		
		if(document.getElementById('claimAppFlag').checked )
		{
		document.getElementById('hidclaimAppFlag').value='Y'
		}else
		{
		  document.getElementById('hidclaimAppFlag').value='N';
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

	document.getElementById('effectDate').value =id.value;
	
}
	
</script>