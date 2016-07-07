<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%try{ %>
<s:form action="DepartmentNumberMaster" id="paraFrm" validate="true" target="main"
	theme="simple">
	
	
	<s:hidden name="hiddencode" />
	<s:hidden name="bean.deptID" />
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
					<td width="93%" class="txt"><strong class="text_head">Department Number
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						
						<tr>
							<td width="10%"><label class="set" name="deptNo"
								id="deptNo" ondblclick="callShowDiv(this);"><%=label.get("deptNo")%></label>
							:<font color="red">*</font></td>
							<td colspan="2" width="90%"><s:textfield size="25" maxlength="40"
								label="%{getText('deptNo')}" theme="simple"
								name="bean.deptNumber" onkeypress="return allCharacters();" /></td>
						</tr><!--
						
						<tr>
							<td width="10%"><label class="set" name="dept"
								id="department" ondblclick="callShowDiv(this);"><%=label.get("dept")%></label>
							:<font color="red">*</font></td>
							<td colspan="2" width="90%"><s:textfield size="25"
								label="%{getText('dept')}" theme="simple"
								name="bean.deptName" onkeypress="return allCharacters();" /></td>
						</tr>
						
						
						--><tr>
							<td width="10%"><label class="set" name="deptdesc"
								id="deptdesc" ondblclick="callShowDiv(this);"><%=label.get("deptdesc")%></label>
							:</td>
							<td colspan="2" width="90%">
								
									<s:textarea name="bean.deptDesc" cols="40" rows="3" onkeyup="callLength('descriptionLength');"/> 
									
									<img src="../pages/images/zoomin.gif" height="12" align="absmiddle"	id='ctrlHide' width="12" theme="simple"	onclick="javascript:callWindow('paraFrm_bean_deptDesc','deptdesc','', 'praFrm_descriptionLength', '500','500');">&nbsp;&nbsp;
								Remaining chars 
							<s:textfield name="descriptionLength" readonly="true" size="5"></s:textfield>
						</td>	
						</tr>
						<tr>
							<td width="10%"><label class="set" name="deptabbr"
								id="deptabbr" ondblclick="callShowDiv(this);"><%=label.get("deptabbr")%></label>
							:</td>
							<td colspan="2" width="90%" height="22"><s:textfield
								maxlength="6" label="%{getText('dptAbbr')}" theme="simple"
								size="25" name="bean.deptAbbr" /></td>
						</tr>
						<tr>

							<td width="10%"><label class="set" name="division"
								id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:<font color="red">*</font></td>
							<td colspan="2" width="90%" height="22"><s:hidden
								theme="simple" name="bean.deptDivCode" /> <s:textfield
								label="%{getText('divName')}" readonly="true" theme="simple"
								name="bean.divName" size="25" /> <s:if
								test="bean.viewFlag">
								<img id='ctrlHide'
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" onclick="callSearch('f9Divaction');"
									align="absmiddle" />
							</s:if></td>
						</tr>

						<tr>
							<td colspan="1"><label class="set" name="is.active"
								id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
							:</td>
							<td colspan="2"><s:select cssStyle="width:152"
								list=" #{'Y':'Yes','N':'No'}" name="bean.isActive" /></td>



						</tr>
					</table>
					</td>
				</tr>
		

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>


<script type="text/javascript">	
		
	
	function saveFun(){
  		var deptNumber =document.getElementById('paraFrm_bean_deptNumber').value;
  		var div=document.getElementById('paraFrm_bean_deptDivCode').value;
	 	var fieldName = ["paraFrm_bean_deptName"];
		var lableName = ["department"];
		var flag = ["enter"];
		var fieldName1 = ["paraFrm_bean_deptName"];
		
		if(deptNumber==""){
			alert ("Please enter Department number ");
			return false;
		}
		
			if(!(deptNumber==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < deptNumber.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(deptNumber.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==deptNumber.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  	
  		}
  	
     	
        if(div==""){
        	alert ("Please select division ");
        	return false;
        }
        
     
  		  
	   document.getElementById('paraFrm').target = "_self";	
	   document.getElementById('paraFrm').action = 'DepartmentNumberMaster_Save.action';
   	   document.getElementById('paraFrm').submit(); 	 
   		
  	}		
		
		function resetFun() {
		//document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'DepartmentNumberMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DepartmentNumberMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function cancelFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DepartmentNumberMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DepartmentNumberMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'DepartmentNumberMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
		
		function editFun() {
		//document.getElementById('paraFrm').target = "_self";
      	//document.getElementById('paraFrm').action = 'DepartmentNumberMaster_edit.action';
		//document.getElementById('paraFrm').submit();
		return true;
	}	
	function callLength(type){ 
	
		 if(type=='descriptionLength'){
			
					var cmt =document.getElementById('paraFrm_bean_deptDesc').value;
					var remain = 500 - eval(cmt.length);
					document.getElementById('paraFrm_descriptionLength').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_bean_deptDesc').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_bean_deptDesc').style.background = '#FFFFFF';
				
				}
				}  

		</script>
<%}catch(Exception e){ %>
	e.printStackStrace();
<%} %>

