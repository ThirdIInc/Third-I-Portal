<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/pages/common/Ajax.js"></script>
<s:form action="ReportingStrNew" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reporting
					Structure </strong></td>
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
					<td width="78%" colspan="2">&nbsp;</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
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
							<td width="20%" colspan="1" height="27"><label class="set"
								name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
								color="red">*</font> :</td>
							<td height="27"><s:textfield theme="simple" name="divName"
								readonly="true" size="40" maxlength="80" /> <s:hidden
								name="divCode" /> <img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'ReportingStrNew_f9division.action');" />

							</td>
						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="showFlag">

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="78%" colspan="2"><s:submit name="" value="Save"
							cssClass="save" action="ReportingStrNew_save"></s:submit></td>
						<td width="22%"></td>
					</tr>
				</table>
				<label></label></td>
			</tr>


			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">

							<tr>
								<td>
								<table width="100%" border="0" cellpadding="1" cellspacing="1"
									id="tableOrderId" class="sortable">
									<tr>
										<td valign="top" class="formth" width="10%"><label
											name="srnum" id="srnum" ondblclick="callShowDiv(this);"><%=label.get("srnum")%></label></td>
										<td width="30%" valign="top" class="formth"><label
											name="mdoule.name" id="mdoule.name"
											ondblclick="callShowDiv(this);"><%=label.get("mdoule.name")%></label></td>
										<td width="20%" valign="top" class="formth"><label
											name="reporting.structure" id="reporting.structure"
											ondblclick="callShowDiv(this);"><%=label.get("reporting.structure")%></label></td>

										<td width="20%" valign="top" class="formth"><label
											name="new.group" id="new.group"
											ondblclick="callShowDiv(this);"><%=label.get("new.group")%></label></td>

										<td width="20%" valign="top" class="formth"><label
											name="level" id="level" ondblclick="callShowDiv(this);"><%=label.get("level")%></label></td>

 
									</tr>

									<%
								int count = 0, p = 0;
									int fieldVal=1;
									 
								%>
									<s:iterator value="moduleList" status="stat">
										<tr>
											<td><%=++p%></td>
											<td><s:hidden name="moduleAbbrItt" /> <s:property
												value="moduleNameItt" /></td>
											<td align="center"><s:select
												onchange="callSelectLink(this);" name="reportingType"
												id='<%="reportingType#"+p%>'
												list="#{'O':'Official','M':'As per the module','N':'New'}"></s:select>
											</td>

 
											<td align="center">

											<div id="showDefineLink<%=p%>" style="display: inline;">
											<a href="javascript:void(0);" title="Define new group" style="cursor: pointer;"
												onclick="defineReportingStrNewucture('<s:property value="moduleAbbrItt"/>','<s:property value="moduleNameItt"/>','Define');"><u>Define</u></a>
											</div>
										 
											<div id="showSelectLink<%=p%>" style="display: none;">
											
											<input type="text" name="selectExistModule"
											value="<s:property value="selectExistModule"/>"
											id="paraFrm_selectExistModule<%=fieldVal%>"
											 readonly="true"/>
											 
											 <input type="hidden" name="selectExistModuleAbbr"
											value="<s:property value="selectExistModuleAbbr"/>"
											id="paraFrm_selectExistModuleAbbr<%=fieldVal%>"
											 readonly="true"/>
								 
						 	<img src="../pages/images/recruitment/search2.gif" width="16" align="absmiddle"
								height="15" class="iconImage" title="Select Module" style="cursor: pointer;"
								onclick="javascript:callsF9(500,325,'ReportingStrNew_f9module.action?fieldValueNo=<%=fieldVal%>');" />
								
										 

											</div>
											</td>

											<td align="center" nowrap="nowrap">
											<div id="levelId<%=p%>" style="display: none;"><s:select 
											 
												name="reportingLevel" list="#{'1':'1','2':'2','3':'3'}"></s:select>
											</div>
											</td>

									 
										</tr>
										
											<%
							fieldVal++;
							%>

									</s:iterator>
									<%
								p = 0;
								%>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>
	</table>
</s:form>

<script>
callOnload();

 


function callOnload()
{
try{
//alert('vish');
var table = document.getElementById('tableOrderId'); 
				var rowCount = table.rows.length; 
				 
				for(var i=1;i<rowCount;i++){		
				var repType =document.getElementById('reportingType#'+i).value;
//alert('repType'+repType);
if(repType=='N')
{
document.getElementById('showSelectLink'+i).style.display='none';
document.getElementById('showDefineLink'+i).style.display='';
document.getElementById('levelId'+i).style.display='none';
}
else if(repType=='M')
{
document.getElementById('showSelectLink'+i).style.display='';
document.getElementById('showDefineLink'+i).style.display='none';
document.getElementById('levelId'+i).style.display='none';

}
else{
document.getElementById('showSelectLink'+i).style.display='none';
document.getElementById('showDefineLink'+i).style.display='none';
document.getElementById('levelId'+i).style.display='';

}
}
}catch(e){//alert("Onload Error "+e);
}
}

function callSelectLink(obj)
{

var ss =obj.id;
//alert(ss);
var splitId =ss.split("#");


//alert(splitId[1]);
//alert(splitId[1]);
var table = document.getElementById('tableOrderId'); 
				var rowCount = table.rows.length; 	
	
	
	//alert("rowCount "+rowCount);			 
	//alert("id "+id);		 
 
try{
 
for(var i=0;i<rowCount-1;i++){		
				var repType =document.getElementById(ss).value;
//alert('repType'+repType);
if(repType=='N')
{
document.getElementById('showSelectLink'+splitId[1]).style.display='none';
document.getElementById('showDefineLink'+splitId[1]).style.display='';
document.getElementById('levelId'+splitId[1]).style.display='none';
}
else if(repType=='M')
{
document.getElementById('showSelectLink'+splitId[1]).style.display='';
document.getElementById('showDefineLink'+splitId[1]).style.display='none';
document.getElementById('levelId'+splitId[1]).style.display='none';

}
else{
document.getElementById('showSelectLink'+splitId[1]).style.display='none';
document.getElementById('showDefineLink'+splitId[1]).style.display='none';
document.getElementById('levelId'+splitId[1]).style.display='';
}

}
 
	


}catch(e){alert("e "+e);}
}

function removeModule(moduleAbbr)
{
  var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  	 
					  	 document.getElementById("paraFrm").action='ReportingStrNew_removeModule.action?moduleAbbr='+moduleAbbr;
		  					document.getElementById("paraFrm").submit();
		  				}	

}


function defineReportingStrNewucture(moduleAbbr,moduleName,source)
{
	//alert('in moduleAbbr  '+moduleAbbr);
	
	//alert('in moduleName  '+moduleName);
	
	 
	document.getElementById('paraFrm').action = "ReportingStrNew_save.action";
				document.getElementById('paraFrm').submit();
				
	var division =document.getElementById('paraFrm_divCode').value;
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/ReportingStrNew_defineReportingStructure.action?moduleAbbrStr='+moduleAbbr+'&moduleNameStr='+moduleName+'&divisionStr='+division+'&sourceStr='+source;
		
		//alert(document.getElementById('paraFrm').action);
		document.getElementById('paraFrm').submit();

}

</script>
