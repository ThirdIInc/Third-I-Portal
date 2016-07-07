<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="TaxSlab" validate="true" id="paraFrm" theme="simple">
	<table class="formbg" width="100%">
		<s:hidden name="show" value="%{show}" />
		
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">TDS
					Slab </strong></td>
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
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="25%" align="right"><span class="style2"><font
						color="red">*</font></span>Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>

							<s:if test="isApprove">

							</s:if>
							<s:else>
								<!--<tr>
									<td colspan="4"><s:submit cssClass="token" action="TaxSlab_save"
											theme="simple" value="  Save   " onclick="return formValidate()" />
								
								
											 <input type="button" class="save" onclick="save();"
										theme="simple" value="Save" /> <s:submit cssClass="reset"
										action="TaxSlab_reset" theme="simple" value="    Reset  " />
									 <s:if test="reportFlag">
										<input type="button" class="token" value="  Report  "
											onclick=" return call();" theme="simple" />
									</s:if> <s:submit cssClass="back" action="TaxSlab_back" theme="simple"
										value="    Back  " /></td>


								</tr>
								-->
		
							</s:else>

							
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
								cellspacing="2">
								<tr>
									<td><label class="set" id="taxation.FinYrFrm"
										name="taxation.FinYrFrm" ondblclick="callShowDiv(this);"><%=label.get("taxation.FinYrFrm")%></label> :<font
										color="red">*</font>&nbsp;<s:textfield size="25" name="taxSlab.fromYear"
										maxlength="4" onkeypress="return numbersOnly();"
										onblur="add()" /></td>

									<td><label class="set" id="taxation.FinYrTo"
										name="taxation.FinYrTo" ondblclick="callShowDiv(this);"><%=label.get("taxation.FinYrTo")%></label> :<font
										color="red">*</font>&nbsp;<s:textfield size="25" name="taxSlab.toYear"
										maxlength="4" readonly="true" />
										<!--<input type="text" onkeyup="format(this)" onchange="format(this)"
onblur="if(this.value.indexOf('.')==-1)this.value=this.value+'.00'">
										
									--></td>

									<!--<td colspan="4">
										<s:submit cssClass="token" action="TaxSlab_view"
											theme="simple" value="   View   " onclick="return callView()" />
				&nbsp;
				
							</td>	
										
								-->
								</tr>

								<!--<tr>
									<td width="25%"><label class="set" id="taxation.SlabType"
										name="taxation.SlabType" ondblclick="callShowDiv(this);"><%=label.get("taxation.SlabType")%></label>:</td>
									<td colspan="2" width="40%"><s:select name="taxSlab.type"
										theme="simple" headerKey="-1"
										list="#{'M':'Men','F':'Women','S':'Senior'}" /></td>
								</tr>

								<tr>
									<td colspan="5"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>

								<tr align="center">
									<td colspan="4">
										<s:submit cssClass="token" action="TaxSlab_view"
											theme="simple" value="   View   " onclick="return callView()" />
				&nbsp;
				
							</td>

								</tr>-->
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>


				<!-- Added By Ganesh Start 16/09/2011 -->
				<%
					int i = 0;
						int j = 0;
						int k = 0;
				%>
				<tr id="tdsSlabTable">
					<td colspan="3" class="formtext">
					<table width="100%" border="0" class="formbg">
						<!-- Slab for Men Start-->
						<tr>
							<td colspan="3" class="formtext">
							<table width="100%" border="0" class="formbg">
								<tr>
									<td colspan="10">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										</td>
										</tr>
										<tr>
											<td colspan="5" class="text_head"><strong
												class="forminnerhead">TDS Slabs</strong></td>
										</tr>
										<tr>
											<td><strong class="text_head"><label
												class="set" id="taxation.slab.for.men"
												name="taxation.slab.for.men" ondblclick="callShowDiv(this);"><%=label.get("taxation.slab.for.men")%></label></strong></td>


											<td align="right"><input type="button" class="add"
												value="Add Row" onclick="addRowToSlabForMen();" /></td>
										</tr>

										<tr>
											<td colspan="4">
											<table width="100%" id="tblSlabForMen" class="sortable">
												<tr>

													<td width="22%" valign="top" class="formth" align="left"
														nowrap="nowrap"><b><label class="set"
														id="from.amount.men" name="from.amount.men"
														ondblclick="callShowDiv(this);"><%=label.get("from.amount.men")%></label></b><font
														color="red">*</font></td>
													<td width="22%" valign="top" class="formth" align="left"><b><label
														class="set" id="to.amount.men" name="to.amount.men"
														ondblclick="callShowDiv(this);"><%=label.get("to.amount.men")%></label></b><font
														color="red">*</font></td>
													<td width="22%" valign="top" class="formth" align="left"><b><label
														class="set" id="percent.men" name="percent.men"
														ondblclick="callShowDiv(this);"><%=label.get("percent.men")%></label></b><font
														color="red">*</font></td>
													<!--<td width="4%" valign="top" class="formth" align="center"></td>
											-->
													<td width="4%" valign="top" class="formth" align="center"></td>

												</tr>
												<s:iterator value="taxSlabList">
													<tr><s:hidden name="taxId" />
														<s:hidden name="taxSlabManListId" />

														<td class="sortableTD" align="center"><input
															type="text" name='frmAmount' id="<%="frmAmount"+i%>"
															value='<s:property value="frmAmount" />' width="100" maxlength="10"
															size="30" onkeypress="return numbersOnly()"  /></td>
														<td class="sortableTD" align="center"><input
															type="text" name='toAmount' id="<%="toAmount"+i%>"
															value='<s:property value="toAmount"/>' width="100" maxlength="10"
															size="30" onkeypress="return numbersOnly()"  onblur="return checkDuplicateValue(this);"/></td>
														<td class="sortableTD" align="center"><input
															type="text" name='taxPercentage'
															id="<%="taxPercentage"+i%>"
															value='<s:property value="taxPercentage"/>' width="100" maxlength="4"
															size="30" onkeypress="return numbersWithDot()"  /></td>
														<!--<td align="center" class="sortableTD">
													<img
													src="../pages/common/css/icons/edit.png"
													onclick="editCurrentRow(this);" />
												-->
														</td>
														<td align="center" class="sortableTD"><img id="ctrlHide"
															src="../pages/common/css/icons/delete.gif"
															onclick="deleteCurrentRow(this);" /></td>

													</tr>
													<%
														i++;
													%>
												</s:iterator>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>

							</table>
							</td>
						</tr>
						<!-- Slab for Men End-->

						<!-- Slab for Women Start-->
						<tr>
							<td colspan="3" class="formtext">
							<table width="100%" border="0" class="formbg">
								<tr>
									<td colspan="10">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										</td>
										</tr>

										<tr>
											<td><strong class="text_head"><label
												class="set" id="taxation.slab.for.women"
												name="taxation.slab.for.women"
												ondblclick="callShowDiv(this);"><%=label.get("taxation.slab.for.women")%></label></strong></td>


											<td align="right"><input type="button" class="add"
												value="Add Row" onclick="addRowToSlabForWomMen();" /></td>
										</tr>

										<tr>
											<td colspan="4">
											<table width="100%" id="tblSlabForWomen" class="sortable">
												<tr>

													<td width="22%" valign="top" class="formth" align="left"
														nowrap="nowrap"><b><label class="set"
														id="from.amount.men1" name="from.amount.men"
														ondblclick="callShowDiv(this);"><%=label.get("from.amount.men")%></label></b><font
														color="red">*</font></td>
													<td width="22%" valign="top" class="formth" align="left"><b><label
														class="set" id="to.amount.men1" name="to.amount.men"
														ondblclick="callShowDiv(this);"><%=label.get("to.amount.men")%></label></b><font
														color="red">*</font></td>
													<td width="22%" valign="top" class="formth" align="left"><b><label
														class="set" id="percen.men1" name="percent.men"
														ondblclick="callShowDiv(this);"><%=label.get("percent.men")%></label></b><font
														color="red">*</font></td>
													<!--<td width="4%" valign="top" class="formth" align="center"></td>
											-->
													<td width="4%" valign="top" class="formth" align="center"></td>

												</tr>
												<s:iterator value="taxSlabWomenList">
													<tr>
														<s:hidden name="taxSlabWomenListId"></s:hidden>

														<td class="sortableTD" align="center"><input
															type="text" name='frmAmountWomen'
															id="frmAmountWomen<%=j%>" maxlength="10"
															value='<s:property value="frmAmountWomen"/>' width="100"
															size="30" onkeypress="return numbersOnly();" /></td>
														<td class="sortableTD" align="center"><input maxlength="10"
															type="text" name='toAmountWomen' id="toAmountWomen<%=j%>"
															value='<s:property value="toAmountWomen"/>' width="100"
															size="30" onkeypress="return numbersOnly();"/></td>
														<td class="sortableTD" align="center"><input
															type="text" name='taxPercentageWomen'
															id="taxPercentageWomen<%=j%>" maxlength="4"
															value='<s:property value="taxPercentageWomen"/>'
															width="100" size="30" onkeypress="return numbersWithDot();" /></td>
														<td align="center" class="sortableTD"><img
															src="../pages/common/css/icons/delete.gif" id="ctrlHide"
															onclick="deleteCurrentRow(this);" /></td>

													</tr>
													<%
														j++;
													%>
												</s:iterator>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>

							</table>
							</td>
						</tr>
						<!-- Slab for W0men End-->

						<!-- Slab for Senior Start-->
						<tr>
							<td colspan="3" class="formtext">
							<table width="100%" border="0" class="formbg">
								<tr>
									<td colspan="10">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										</td>
										</tr>

										<tr>
											<td><strong class="text_head"><label
												class="set" id="taxation.slab.for.senior"
												name="taxation.slab.for.senior"
												ondblclick="callShowDiv(this);"><%=label.get("taxation.slab.for.senior")%></label></strong></td>


											<td align="right"><input type="button" class="add"
												value="Add Row" onclick="addRowToSlabForSenior();" /></td>
										</tr>

										<tr>
											<td colspan="4">
											<table width="100%" id="tblSlabForSenior" class="sortable">
												<tr>

													<td width="22%" valign="top" class="formth" align="left"
														nowrap="nowrap"><b><label class="set"
														id="from.amount.men2" name="from.amount.men"
														ondblclick="callShowDiv(this);"><%=label.get("from.amount.men")%></label></b><font
														color="red">*</font></td>
													<td width="22%" valign="top" class="formth" align="left"><b><label
														class="set" id="to.amount.men2" name="to.amount.men"
														ondblclick="callShowDiv(this);"><%=label.get("to.amount.men")%></label></b><font
														color="red">*</font></td>
													<td width="22%" valign="top" class="formth" align="left"><b><label
														class="set" id="percent.men2" name="percent.men"
														ondblclick="callShowDiv(this);"><%=label.get("percent.men")%></label></b><font
														color="red">*</font></td>
													<!--<td width="4%" valign="top" class="formth" align="center"></td>
											-->
													<td width="4%" valign="top" class="formth" align="center"></td>

												</tr>
												<s:iterator value="taxSlabSeniorList">
													<tr>
														<s:hidden name="taxSlabSeniorListId"></s:hidden>

														<td class="sortableTD" align="center"><input
															type="text" name='frmAmountSenior'
															id="frmAmountSenior<%=k%>" maxlength="10"
															value='<s:property value="frmAmountSenior"/>' width="100"
															size="30" onkeypress="return numbersOnly();" /></td>
														<td class="sortableTD" align="center"><input
															type="text" name='toAmountSenior'
															id="toAmountSenior<%=k%>" maxlength="10"
															value='<s:property value="toAmountSenior"/>' width="100"
															size="30" onkeypress="return numbersOnly();" /></td>
														<td class="sortableTD" align="center"><input
															type="text" name='taxPercentageSenior'
															id="taxPercentageSenior<%=k%>" maxlength="4"
															value='<s:property value="taxPercentageSenior"/>'
															width="100" size="30" onkeypress="return numbersWithDot();" /></td>
														<td align="center" class="sortableTD"><img id="ctrlHide"
															src="../pages/common/css/icons/delete.gif"
															onclick="deleteCurrentRow(this);" /></td>

													</tr>
													<%
														k++;
													%>
												</s:iterator>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>

							</table>
							</td>
						</tr>
						<!-- Slab for Senior End-->
						<!-- Added By Ganesh End -->

<s:hidden name="taxSlab.paraID" />
						<s:hidden name="taxSlab.isFromEdit" />
						<s:hidden name="taxSlab.chkEdit" />
						

					</table>
					</td>
				</tr>



			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="25%" align="right"><span class="style2"><font
						color="red">*</font></span>Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>



	function saveFun(){

	try{	
	var fieldName = ["paraFrm_taxSlab_fromYear","paraFrm_taxSlab_toYear"];
	var lableName = ["taxation.FinYrFrm","taxation.FinYrTo"];
		var flag = ["enter","enter"];
		
	if(!validateBlank(fieldName,lableName,flag)){
	
             return false;
     	 }else if(!chkDupVac()){
     	
			return false;	
	}
	
	var from = document.getElementById('paraFrm_taxSlab_fromYear').value;
	 
	   var finYrFrm=document.getElementById('taxation.FinYrFrm').innerHTML.toLowerCase();
	  
	    	   
	    if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm);
	    	return false;
	    }
	    
	    if(eval(from)<2000){
	    	alert(finYrFrm+" cannot be less than 2000");
	    	return false;
	    }
	
	
	
	}
	catch(e){
	alert(e);
	}
		document.getElementById('paraFrm').action = "TaxSlab_save.action";
		document.getElementById('paraFrm').submit();
	
	}
	
	
	function chkDupVac(){
	try{

		var tbl = document.getElementById('tblSlabForMen');
		var lastRow = tbl.rows.length;
		if(lastRow<2){
		alert("Please add at least one row value in Slab For Men.");
		return false;
		}
	
	var tblWomen = document.getElementById('tblSlabForWomen');
	var lastRowWomen = tblWomen.rows.length;
		if(lastRowWomen<2){
		alert("Please add at least one row value in Slab For Women.");
		return false;
		}
		
	var tblSenior = document.getElementById('tblSlabForSenior');
	var lastRowSenior = tblSenior.rows.length;
		if(lastRowSenior<2){
		alert("Please add at least one row value in Slab For Senior.");
		return false;
		}
	
	
	var flag=false;
	for(var i=0;i<(lastRow-1);i++){

 		 				var fromAmtForMen= document.getElementById('frmAmount'+i).value;
 		 		
 		 	//alert(parseFloat(fromAmtForMen)+'0.0');
 		 		
 		 				var toAmtForMen= document.getElementById('toAmount'+i).value;
 		 				var taxPercentageForMen= document.getElementById('taxPercentage'+i).value;
 		 				
 		 				if(fromAmtForMen!=""){
 		 					flag=true;
 		 				}
 		 				if(toAmtForMen!=""){
 		 					flag=true;
 		 				}
 		 				if(taxPercentageForMen!=""){
 		 					flag=true;
 		 				}
 		 	var cnt=0;
 		 		
 		 	
 	//	 	if(fromAmtForMen!=""){
 	//	 	if(Math.abs(fromAmtForMen)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('taxation.slab.for.men').innerHTML.toLowerCase());
 	//	 	document.getElementById('frmAmount'+i).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 	//	 	if(toAmtForMen!=""){
 	///	 	if(Math.abs(toAmtForMen)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('to.amount').innerHTML.toLowerCase());
 	///	 	document.getElementById('toAmount'+i).focus();
 	///	 	return false;
 	//	 	}
 	//	 	}
 	//	 	if(taxPercentageForMen!=""){
 	//	 	if(Math.abs(taxPercentageForMen)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('to.amount').innerHTML.toLowerCase());
 	//	 	document.getElementById('toAmount'+i).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 		 		 
  		
 		 	  if(fromAmtForMen==""){
 		 		alert("Please enter "+document.getElementById('from.amount.men').innerHTML.toLowerCase()+" Slab For Men");
 		 		document.getElementById('frmAmount'+i).focus();
 		 		return false;
 		 	      }
 		 if(toAmtForMen==""){
 		 		alert("Please enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Slab For Men");
 		 		document.getElementById('toAmount'+i).focus();
 		 		return false;
 		 	      }
 		  	 if(taxPercentageForMen==""){
 		 		alert("Please enter "+document.getElementById('percent.men').innerHTML.toLowerCase()+" Slab For Men");
 		 		document.getElementById('taxPercentage'+i).focus();
 		 		return false;
 		 	      }
 		 	      
 		  if(eval(fromAmtForMen) > eval(toAmtForMen)){
  			alert("Please  Enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Greater Than "+document.getElementById('from.amount.men').innerHTML.toLowerCase());
  			document.getElementById('toAmount'+i).focus();
  			return false;
  		}
  		 if(eval(fromAmtForMen) == eval(toAmtForMen)){
  		alert("Please  Enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Greater Than "+document.getElementById('from.amount.men').innerHTML.toLowerCase());
  			document.getElementById('toAmount'+i).focus();
  			return false;
  		}
 		 	
 		}
 		
 	// for women
 	
 	
 	for(var w=0;w<(lastRowWomen-1);w++){

 		 				var fromAmtForWoMen= document.getElementById('frmAmountWomen'+w).value;
 		 		
 		 	//alert(parseFloat(fromAmtForMen)+'0.0');
 		 		
 		 				var toAmtForWoMen= document.getElementById('toAmountWomen'+w).value;
 		 				var taxPercentageForWoMen= document.getElementById('taxPercentageWomen'+w).value;
 		 				
 		 				if(fromAmtForWoMen!=""){
 		 					flag=true;
 		 				}
 		 				if(toAmtForWoMen!=""){
 		 					flag=true;
 		 				}
 		 				if(taxPercentageForWoMen!=""){
 		 					flag=true;
 		 				}
 		 	var cnt=0;
 		 		
 		 	
 	//	 	if(fromAmtForWoMen!=""){
 	//	 	if(Math.abs(fromAmtForWoMen)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('taxation.slab.for.women').innerHTML.toLowerCase());
 	//	 	document.getElementById('frmAmountWomen'+w).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 	//	 	if(toAmtForWoMen!=""){
 	//	 	if(Math.abs(toAmtForWoMen)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('to.amount').innerHTML.toLowerCase());
 	//	 	document.getElementById('toAmountWomen'+w).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 	//	 	if(taxPercentageForWoMen!=""){
 	////	 	if(Math.abs(taxPercentageForWoMen)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('to.amount').innerHTML.toLowerCase());
 	//	 	document.getElementById('taxPercentageWomen'+w).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 		 		 
  		
 		 	  if(fromAmtForWoMen==""){
 		 		alert("Please enter "+document.getElementById('from.amount.men').innerHTML.toLowerCase()+" Slab For Women");
 		 		document.getElementById('frmAmountWomen'+w).focus();
 		 		return false;
 		 	      }
 		 if(toAmtForWoMen==""){
 		 		alert("Please enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Slab For Women");
 		 		document.getElementById('toAmountWomen'+w).focus();
 		 		return false;
 		 	      }
 		  	 if(taxPercentageForWoMen==""){
 		 		alert("Please enter "+document.getElementById('percent.men').innerHTML.toLowerCase()+" Slab For Women");
 		 		document.getElementById('taxPercentageWomen'+w).focus();
 		 		return false;
 		 	      }
 		 	      
 		  if(eval(fromAmtForWoMen) > eval(toAmtForWoMen)){
  			alert("Please  Enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Greater Than "+document.getElementById('from.amount.men').innerHTML.toLowerCase());
  			document.getElementById('toAmountWomen'+w).focus();
  			return false;
  		}
  		 if(eval(fromAmtForWoMen) == eval(toAmtForWoMen)){
  		alert("Please  Enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Greater Than "+document.getElementById('from.amount.men').innerHTML.toLowerCase());
  			document.getElementById('toAmountWomen'+w).focus();
  			return false;
  		}
 		 	
 		}	
 		
 		
 	// for Senior
 	
 	
 	for(var s=0;s<(lastRowSenior-1);s++){

 		 				var fromAmtForSenior= document.getElementById('frmAmountSenior'+s).value;
 		 		
 		 	//alert(parseFloat(fromAmtForMen)+'0.0');
 		 		
 		 				var toAmtForSenior= document.getElementById('toAmountSenior'+s).value;
 		 				var taxPercentageForSenior= document.getElementById('taxPercentageSenior'+s).value;
 		 				
 		 				if(fromAmtForSenior!=""){
 		 					flag=true;
 		 				}
 		 				if(toAmtForSenior!=""){
 		 					flag=true;
 		 				}
 		 				if(taxPercentageForSenior!=""){
 		 					flag=true;
 		 				}
 		 	var cnt=0;
 		 		
 		 	
 	//	 	if(fromAmtForSenior!=""){
 	//	 	if(Math.abs(fromAmtForSenior)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('taxation.slab.for.senior').innerHTML.toLowerCase());
 	//	 	document.getElementById('frmAmountSenior'+s).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 	//	 	if(toAmtForSenior!=""){
 	//	 	if(Math.abs(toAmtForSenior)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('to.amount').innerHTML.toLowerCase());
 	//	 	document.getElementById('toAmountSenior'+s).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 	//	 	if(taxPercentageForSenior!=""){
 	//	 	if(Math.abs(taxPercentageForSenior)==0){
 	//	 	alert("Zero not allowed in "+ document.getElementById('to.amount').innerHTML.toLowerCase());
 	//	 	document.getElementById('taxPercentageSenior'+s).focus();
 	//	 	return false;
 	//	 	}
 	//	 	}
 		 		 
  		
 		 	  if(fromAmtForSenior==""){
 		 		alert("Please enter "+document.getElementById('from.amount.men').innerHTML.toLowerCase()+" Slab For Sr.Citizen");
 		 		document.getElementById('frmAmountSenior'+s).focus();
 		 		return false;
 		 	      }
 		 if(toAmtForSenior==""){
 		 		alert("Please enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Slab For Sr.Citizen");
 		 		document.getElementById('toAmountSenior'+s).focus();
 		 		return false;
 		 	      }
 		  	 if(taxPercentageForSenior==""){
 		 		alert("Please enter "+document.getElementById('percent.men').innerHTML.toLowerCase()+" Slab For Sr.Citizen");
 		 		document.getElementById('taxPercentageSenior'+s).focus();
 		 		return false;
 		 	      }
 		 	      
 		  if(eval(fromAmtForSenior) > eval(toAmtForSenior)){
  			alert("Please  Enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Greater Than "+document.getElementById('from.amount.men').innerHTML.toLowerCase());
  			document.getElementById('toAmountSenior'+s).focus();
  			return false;
  		}
  		 if(eval(fromAmtForSenior) == eval(toAmtForSenior)){
  		alert("Please  Enter "+document.getElementById('to.amount.men').innerHTML.toLowerCase()+" Greater Than "+document.getElementById('from.amount.men').innerHTML.toLowerCase());
  			document.getElementById('toAmountSenior'+s).focus();
  			return false;
  		}
 		 	
 		}		
 		
 		
 		
 		
 		
 	}catch(e) {
 		//alert(e);
 	}
 		
 return true;		 
 		
 }		
 
	function backFun() {
	
  	 	document.getElementById('paraFrm').action = "TaxSlab_back.action";
		document.getElementById('paraFrm').submit();
  	}
  	
  	function resetFun() {
	
  	 	document.getElementById('paraFrm').action = "TaxSlab_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	
  function reportFun(){

 var frmYr = document.getElementById('paraFrm_taxSlab_fromYear').value;
   var finYrFrm=document.getElementById('taxation.FinYrFrm').innerHTML.toLowerCase();
 if (frmYr==""){
	    	alert("Please Enter "+finYrFrm);
			return false;
	 	 }
	 
 
 document.getElementById('paraFrm').target="_blank";
 document.getElementById('paraFrm').action="TaxSlab_report.action";
 document.getElementById('paraFrm').submit();
 document.getElementById('paraFrm').target="main";
 }


 function callForEdit(id){
	   	document.getElementById("paraFrm").action="TaxSlab_edit.action";
	  	document.getElementById('paraFrm_taxSlab_paraID').value=id;
	    document.getElementById("paraFrm").submit();
   
   }
	
	 function callView(){
	 
	   var from = document.getElementById('paraFrm_taxSlab_fromYear').value;
	   var slabType = document.getElementById('paraFrm_taxSlab_type').value;
	   var finYrFrm=document.getElementById('taxation.FinYrFrm').innerHTML.toLowerCase();
	    var slabType=document.getElementById('taxation.SlabType').innerHTML.toLowerCase();
	    	   
	    if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm);
	    	return false;
	    }
	    
	    if(eval(from)<2000){
	    	alert(finYrFrm+" cannot be less than 2000");
	    	return false;
	    }
	    
	    
	     if(slabType=="")
	    {
	    	alert("Please Select "+slabType);
	    	return false;
	    }
	    
	    
  		 if(!(from==""))
					{ 
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < from.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(from.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==from.length){
  					alert ("Blank Spaces Not Allowed in "+finYrFrm+" Field ");
  					return false;	
  					}
				}
	
	  
   }
   
   function callDelete(id)
   {
    var flag=formValidate1();
   if(flag)
   {
    	document.getElementById("paraFrm").action="TaxSlab_delete.action";
  	   	document.getElementById('paraFrm_taxSlab_paraID').value=id;
	  	document.getElementById("paraFrm").submit();
	   
   	}
   	}
   
   
   
   function add()
   {
    var from = document.getElementById('paraFrm_taxSlab_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_taxSlab_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_taxSlab_toYear').value=x;
	  }
   }
  
   
   	function formValidate1(){
  		 		
  		var conf=confirm("Are you sure to delete this record? ");
  			if(conf) {
  			return true;
  			
  			}else
  			{
  			return false;
  			}
  		}
  		
	 function formValidate(){
 		
 		var frmAmount = document.getElementById('paraFrm_taxSlab_frmAmount').value;
 		var toAmount = document.getElementById('paraFrm_taxSlab_toAmount').value;
 		var taxPercent = document.getElementById('paraFrm_taxSlab_taxPercentage').value;
	  
	   var frmAmt=document.getElementById('taxation.FromAmt').innerHTML.toLowerCase();
	   var toAmt=document.getElementById('taxation.ToAmt').innerHTML.toLowerCase();
	    var taxPer=document.getElementById('taxation.TaxAmt').innerHTML.toLowerCase();
	  
	  
	    if (frmAmount==""){
	    	alert("Please Enter "+frmAmt);
			return false;
	 	 }
	 	 
	 	 if (toAmount==""){
	    	alert("Please Enter "+toAmt);
			return false;
	 	 }
	 	 
	 
	 
	 
	 	 if(eval(frmAmount) > eval(toAmount)){
  			alert("Please  Enter "+toAmt+" Greater Than "+frmAmt);
  			return false;
  		}
  		 if(eval(frmAmount) == eval(toAmount)){
  		alert("Please  Enter "+toAmt+" Greater Than "+frmAmt);
  			return false;
  		}
 
  		 	 
	    if (taxPercent==""){
	    	alert("Please Enter "+taxPer);
			return false;
	 	 }
	 	 
 	    if (eval(taxPercent)>100){
  			alert(taxPer+" should not exceed 100");
  			return false;
  		}	
 		
  }

 function addRowToSlabForMen()
	{
		  var tbl = document.getElementById('tblSlabForMen');
		  var lastRow = tbl.rows.length;
		 
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow -1;
		  var row = tbl.insertRow(lastRow);
		  
		  var cellNoOfVac = row.insertCell(0);
   		  var vac = document.createElement('input');
		  vac.type = 'text';
		  vac.name = 'frmAmount';
		  vac.id = 'frmAmount'+iteration;
		  cellNoOfVac.className='sortableTD';
		  vac.maxLength='10';
		  vac.size='30';
		  
		//  vac.onkeyup=function(){
		//  try {alert(11);
		//  alert(vac.value);
		//  	   format(vac.id);
		//  }catch(e){alert(e);}
		//  };
		//  cellNoOfVac.appendChild(vac);
		  
	///	  vac.onkeypress = function(){
  	///	document.onkeypress = checkNumbers(this,event) ;
	///var count = 0;
	///	var countWithDot = 0;
	///	var txtNo = this.value;
	///	for(var i = 0; i < txtNo.length; i++) {
	////		if(txtNo.charAt(i) == '.') {
	///			count = count + 1;
	///		} else {
	///		    countWithDot = countWithDot + 1;
	///		}
	///	}
		
	///	if(count > 0) {
	///		if(!numbersOnly(event)) {
	///			return false;
	///		}	
	///		if(!dotAfterLen(txtNo,event)){
	///			return false;
	///		}			 	
			
	///	} else if(!numbersWithDot(event)) {
	///		return false;
	///	} else if(!numbersBeforeDotOnly(countWithDot,event)) {
	///		return false;
	///	}
	///	return true;
	
  //}
 	   vac.onkeypress = numbersOnly;				//Called Validation 'NumberOnly'
		  cellNoOfVac.align='center';
		
		  cellNoOfVac.appendChild(vac);
  
		  
		 var cellNo1 = row.insertCell(1);
   		  var vac1 = document.createElement('input');
		  vac1.type = 'text';
		  vac1.name = 'toAmount';
		  vac1.id = 'toAmount'+iteration;
		  cellNo1.className='sortableTD';
		  vac1.maxLength='10';
		  vac1.size='30';
		 
		 
		///  vac1.onkeypress = function(){
  		///document.onkeypress = checkNumbersWithDot(this) ;
	////var count = 0;
	///var txtNo = this.value;
	
	///for(var i = 0; i < txtNo.length; i++) {
	//	if(txtNo.charAt(i) == '.') {
	//		count = count + 1;
	///	}
	//}
	
	///if(count > 0) {
	///	if(!numbersOnly()) {
	///		return false;
	///	}
	///} else if(!numbersWithDot()) {
	///	return false;
	///}
	///return true;
  //}
  
  vac1.onkeypress = numbersOnly;				//Called Validation 'NumberOnly'
  
		  cellNo1.align='center';
		
		  cellNo1.appendChild(vac1);
		 
		var cellNo2 = row.insertCell(2);
   		  var vac2 = document.createElement('input');
   		  vac2.className='sortableTD';
		  vac2.type = 'text';
		  vac2.name = 'taxPercentage';
		  vac2.id = 'taxPercentage'+iteration;
		  cellNo2.className='sortableTD';
		  vac2.maxLength='5';
		  vac2.size='30';
		 
		/// vac2.onkeypress = function(){
  	///		document.onkeypress = checkNumbersWithDot(this) ;
	///var count = 0;
	///var txtNo = this.value;
	
	///for(var i = 0; i < txtNo.length; i++) {
	///	if(txtNo.charAt(i) == '.') {
	///		count = count + 1;
	///	}
	///}
	
	///if(count > 0) {
	///	if(!numbersOnly()) {
	///		return false;
	///	}
	///} else if(!numbersWithDot()) {
	///	return false;
	///}
	///return true;
  //}
		  vac2.onkeypress = numbersWithDot;				//Called Validation 'NumberOnly'
		  cellNo2.align='center';
		
		  cellNo2.appendChild(vac2);
 
		  var cell3 = row.insertCell(3);
		  var column3 = document.createElement('img');
		  cell3.className='sortableTD';
		  
		  column3.type='image';
		  column3.src="../pages/common/css/icons/delete.gif";
		  column3.align='absmiddle';
	  	  column3.id='img'+ iteration;
		  column3.theme='simple';
		  cell3.align='center';

		  column3.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
	  
		//  var cell4= row.insertCell(3);
		 // var column4 = document.createElement('img');
		 // cell4.className='sortableTD';
		//  column4.type='image';
		//  column4.src="../pages/common/css/icons/edit.png";
		//  column3.align='center';
		//  column4.id='img'+ iteration;
		//  column4.theme='simple';
		//  column4.onclick=function(){
		//  try {
		//		 editCurrentRow(this);
		//  }catch(e){alert(e);}
		//  };
		//  cell4.appendChild(column4);
		  
	} 
	
 function addRowToSlabForWomMen()
	{
		  var tbl = document.getElementById('tblSlabForWomen');
		  var lastRow = tbl.rows.length;
	
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow -1;
		  var row = tbl.insertRow(lastRow);
		  
		  var cellNoOfVac = row.insertCell(0);
   		  var vac = document.createElement('input');
		  vac.type = 'text';
		  vac.name = 'frmAmountWomen';
		  vac.id = 'frmAmountWomen'+iteration;
		  cellNoOfVac.className='sortableTD';
		  vac.maxLength='10';
		  vac.size='30';
		  
		   vac.onkeypress = numbersOnly;				//Called Validation 'NumberOnly'
		  
		  cellNoOfVac.align='center';
		
		  cellNoOfVac.appendChild(vac);
  
		  
		 var cellNo1 = row.insertCell(1);
   		  var vac1 = document.createElement('input');
		  vac1.type = 'text';
		  vac1.name = 'toAmountWomen';
		  vac1.id = 'toAmountWomen'+iteration;
		  cellNo1.className='sortableTD';
		  vac1.maxLength='10';
		  vac1.size='30';
		   vac1.onkeypress = numbersOnly;				//Called Validation 'NumberOnly'
		  cellNo1.align='center';
		
		  cellNo1.appendChild(vac1);
		 
		var cellNo2 = row.insertCell(2);
   		  var vac2 = document.createElement('input');
   		  vac2.className='sortableTD';
		  vac2.type = 'text';
		  vac2.name = 'taxPercentageWomen';
		  vac2.id = 'taxPercentageWomen'+iteration;
		  cellNo2.className='sortableTD';
		  vac2.maxLength='4';
		  vac2.size='30';
		 
		  vac2.onkeypress = numbersWithDot;				//Called Validation 'NumberOnly'
		 
		  cellNo2.align='center';
		
		  cellNo2.appendChild(vac2);
		 
	  
		   var cell3 = row.insertCell(3);
		  var column3 = document.createElement('img');
		  cell3.className='sortableTD';
		  
		  column3.type='image';
		  column3.src="../pages/common/css/icons/delete.gif";
		  column3.align='absmiddle';
	  	  column3.id='img'+ iteration;
		  column3.theme='simple';
		  cell3.align='center';

		  column3.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
	  
		//  var cell4= row.insertCell(3);
		//  var column4 = document.createElement('img');
		//  cell4.className='sortableTD';
		//  column4.type='image';
		//  column4.src="../pages/common/css/icons/edit.png";
		//  column3.align='center';
		//  column4.id='img'+ iteration;
		//  column4.theme='simple';
		//  column4.onclick=function(){
		//  try {
		//		 editCurrentRow(this);
		//  }catch(e){alert(e);}
		//  };
		//  cell4.appendChild(column4);
		  
	} 
	
	function addRowToSlabForSenior()
	{
		  var tbl = document.getElementById('tblSlabForSenior');
		  var lastRow = tbl.rows.length;
		 
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow -1;
		  var row = tbl.insertRow(lastRow);
		  
		  var cellNoOfVac = row.insertCell(0);
   		  var vac = document.createElement('input');
		  vac.type = 'text';
		  vac.name = 'frmAmountSenior';
		  vac.id = 'frmAmountSenior'+iteration;
		  cellNoOfVac.className='sortableTD';
		  vac.maxLength='10';
		  vac.size='30';
		
		  vac.onkeypress = numbersOnly;				//Called Validation 'NumberOnly'
		  
		  cellNoOfVac.align='center';
		
		  cellNoOfVac.appendChild(vac);
  
		  
		 var cellNo1 = row.insertCell(1);
   		  var vac1 = document.createElement('input');
		  vac1.type = 'text';
		  vac1.name = 'toAmountSenior';
		  vac1.id = 'toAmountSenior'+iteration;
		  cellNo1.className='sortableTD';
		  vac1.maxLength='10';
		  vac1.size='30';
		   vac1.onkeypress = numbersOnly;				//Called Validation 'NumberOnly'
		  cellNo1.align='center';
		
		  cellNo1.appendChild(vac1);
		 
		var cellNo2 = row.insertCell(2);
   		  var vac2 = document.createElement('input');
   		  vac2.className='sortableTD';
		  vac2.type = 'text';
		  vac2.name = 'taxPercentageSenior';
		  vac2.id = 'taxPercentageSenior'+iteration;
		  cellNo2.className='sortableTD';
		  vac2.maxLength='4';
		  vac2.size='30';
		 
		  vac2.onkeypress = numbersWithDot;				//Called Validation 'NumberOnly'
		 
		  cellNo2.align='center';
		
		  cellNo2.appendChild(vac2);
 
		 
		 var cell3 = row.insertCell(3);
		  var column3 = document.createElement('img');
		  cell3.className='sortableTD';
		  
		  column3.type='image';
		  column3.src="../pages/common/css/icons/delete.gif";
		  column3.align='absmiddle';
	  	  column3.id='img'+ iteration;
		  column3.theme='simple';
		  cell3.align='center';

		  column3.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
	  
		//  var cell4= row.insertCell(3);
		//  var column4 = document.createElement('img');
		//  cell4.className='sortableTD';
		//  column4.type='image';
		//  column4.src="../pages/common/css/icons/edit.png";
		//  column3.align='center';
		//  column4.id='img'+ iteration;
		//  column4.theme='simple';
		//  column4.onclick=function(){
		//  try {
		//		 editCurrentRow(this);
		//		}catch(e){alert(e);}
		//  };
		//  cell4.appendChild(column4);
		  
	} 
	
	//For Delete Rows
		function deleteCurrentRow(obj){
		var conf = confirm("Do you really want to delete this record ?");
		if(conf) {
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		}
	}
	function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
	
	 function call(){

 var frmYr = document.getElementById('paraFrm_taxSlab_fromYear').value;
   var finYrFrm=document.getElementById('taxation.FinYrFrm').innerHTML.toLowerCase();
 if (frmYr==""){
	    	alert("Please Enter "+finYrFrm);
			return false;
	 	 }
	 
 
 document.getElementById('paraFrm').target="_blank";
 document.getElementById('paraFrm').action="TaxSlab_report.action";
 document.getElementById('paraFrm').submit();
 document.getElementById('paraFrm').target="main";
 }
 
	function format(input){
	alert(222);
	alert("input=="+document.getElementById(input).value);

	try{
	var RR = document.getElementById(input).value;
	var num = RR.replace(/\,/g,'');
		if(!isNaN(num)){
		alert("num=-=" +num);
		if(num.indexOf('.') > -1){
		alert("num=IF-=" +num);
		num = num.split('.');
		num[0] = 
		
		num[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');

		if(num[1].length > 2){
		alert('You may only enter two decimals!');
		num[1] = num[1].substring(0,num[1].length-1);
		} input.value = num[0]+'.'+num[1];
		} else{ 
		alert("num=ELSEEEEEEE-=" +num);
		input.value = 
		
		num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split(
		
		'').reverse().join('').replace(/^[\,]/,'')
		
		 };
		}
		else{ alert('You may enter only numbers in this field!');
		input.value = input.value.substring(0,input.value.length-1);
		}
		}
		catch(e){
		alert(e);
		}
	}
	function editFun() {
	
		return true;
	}
	
	
	function checkNumbers(obj,event) {	
		var count = 0;
		var countWithDot = 0;
		var txtNo = obj.value;
		for(var i = 0; i < txtNo.length; i++) {
			if(txtNo.charAt(i) == '.') {
				count = count + 1;
			} else {
			    countWithDot = countWithDot + 1;
			}
		}
		
		if(count > 0) {
			if(!numbersOnly(event)) {
				return false;
			}	
			if(!dotAfterLen(txtNo,event)){
				return false;
			}			 	
			
		} else if(!numbersWithDot(event)) {
			return false;
		} else if(!numbersBeforeDotOnly(countWithDot,event)) {
			return false;
		}
		return true;
	}
	
	
	
	function dotAfterLen(txtNo,event) {		
			document.onkeypress = dotAfterLen;
			var key;
			if (window.event)
		    	key = event.keyCode
			else				
		   		key = event.which
		   		clear();			
			var lenArray = txtNo.split('.');				
			var len=lenArray[1];			
			if(!(len.length > 2) &&  key > 47 && key < 58 || key == 8 || key == 46 || key == 0){				
		   		return true; 
			} else {
				return false;	
			} 
			if (window.event) //IE
		    	window.event.returnValue = null;
		    else //Firefox
		    	event.preventDefault();
		}
	function numbersWithDot(event) {
		document.onkeypress = numbersWithDot;		
		var key;
		if (window.event)
	    	key = event.keyCode
		else
	   		key = event.which
		clear();
	
		// Was key that was pressed a numeric character (0-9) or backspace (8)?
		if (key > 47 && key < 58 || key == 8 || key == 46 || key == 0)
	 		return true; // if so, do nothing
		else // otherwise, discard character
			return false;
		
		if (window.event) //IE
	    	window.event.returnValue = null;
	    else //Firefox
	    	event.preventDefault();
	}
	
	function numbersOnly(event) {		
		document.onkeypress = numbersOnly;
		var key;
		if (window.event)
	    	key = event.keyCode
		else
	   		key = event.which
		clear();
	
		// Was key that was pressed a numeric character (0-9) or backspace (8)?
		if (key > 47 && key < 58 || key == 8 || key == 0)
	 		return true; // if so, do nothing
		else // otherwise, discard character
			return false;
	
		if (window.event) //IE
	    	window.event.returnValue = null;
	    else //Firefox
	    	event.preventDefault();
	}
	
	function numbersBeforeDotOnly(countWithDot,event) {	
	
		document.onkeypress = numbersOnly;
		var key;
		if (window.event)
	    	key = event.keyCode
		else
	   		key = event.which
		clear();
	
		// Was key that was pressed a numeric character (0-9) or backspace (8)?
		if (countWithDot < 5 && key > 47 && key < 58 || key == 8 || key == 0 || key == 46)
	 		return true; // if so, do nothing
		else // otherwise, discard character
			return false;
	
		if (window.event) //IE
	    	window.event.returnValue = null;
	    else //Firefox
	    	event.preventDefault();
	}

	function clear() {
		document.onkeypress = "";
	}
function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TaxSlab_deleteSelectedRecordsFromTaxSlabList.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
   </script>