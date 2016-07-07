<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="DomainMaster" validate="true" id="paraFrm"  theme="simple">
<s:hidden name="show" /><s:hidden name="hiddencode" /><s:hidden name="cancelFlg" /><s:hidden name="flagShow"/>
<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Functional/
					Domain</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" width="100%">
			    <table width="100%" border="0" align="right">
			        <tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>



							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							      </td>
						       </tr>
					        </table>
					     </td>
				       </tr>
			      </table>
			  </td>
	  </tr>
	  <tr>
	      <td colspan="3">
					<table  width="100%" border="0" 	class="formbg">
						<tr>
							<td>
								<table width="98%" border="0" align="center" >
								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="dom.name" id="dom.name"
										ondblclick="callShowDiv(this);"><%=label.get("dom.name")%></label>
									:<font color="red">*</font></td>
									<td width="27%" height="22"><s:hidden name="domainCode" />

									<s:textfield name="domainName" theme="simple" size="30"
										maxlength="50" readonly="false" /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>
								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="dom.abbr" id="dom.abbr"
										ondblclick="callShowDiv(this);"><%=label.get("dom.abbr")%></label>
									:<font color="red">*</font></td>
									<td width="27%" height="22"><s:textfield name="domAbbr"
										theme="simple" size="30" maxlength="15" readonly="false" /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>

								</tr>
								<tr>
									<td width="22%" height="22" valign="top" class="formtext"
										nowrap="nowrap"><label class="set" name="dom.desc"
										id="dom.desc" ondblclick="callShowDiv(this);"><%=label.get("dom.desc")%></label>
									:</td>
									<td width="27%" height="22" nowrap="nowrap"><s:textarea name="domDesc"
										cols="27" rows="4"
										onkeyup="callLength('domDesc','descCnt','2000');"></s:textarea>

									<img src="../pages/images/zoomin.gif"
										height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_domDesc','dom.desc','','paraFrm_descCnt','2000');">
									Remaining chars <s:textfield name="descCnt" readonly="true"
										size="5"></s:textfield></td>

								</tr>
								<tr>
										<td width="20%" height="22" class="formtext"><label
											class="set" name="dom.stat" id="dom.stat"
											ondblclick="callShowDiv(this);"><%=label.get("dom.stat")%></label>
										:</td>
										<td><s:select name="domainStatus" disabled="false"
											list="#{'A':'Active','D':'Deactive'}"
											cssStyle="width:180;z-index:5;" /></td>
										<td height="22" class="formtext">&nbsp;</td>
										<td height="22">&nbsp;</td>
								   </tr>
								
								</table>
							</td>
					   </tr>
				</table>	
	     	</td>  
	  </tr>		
	  
	  
	  <tr>
			<td width="100%">
			<jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" />
			
			
			</td>	  
	  </tr>
	  
</table>


	
</s:form>
<script>
	
	function maxlength()
	{
	var length=document.getElementById('paraFrm_domDesc').value;
	var l=length.length;
	if(l=='2000')
	{
		return false;
	}
	else
	{
		return true;
	}
	}	
	
	
	
	function callingStage(stage){
		document.getElementById('paraFrm').action = "DomainMaster_cancel.action?callingStage="+stage;
		document.getElementById('paraFrm').submit();
	}
	
	 function saveFun(){
		 var fieldName = ['paraFrm_domainName', 'paraFrm_domAbbr'];
		 var lableName = ["dom.name","dom.abbr"];
		 var flag = ["enter", "enter"];
	  	var desc = document.getElementById('paraFrm_domDesc').value;
	  	 if(!validateBlank(fieldName,lableName,flag)){
         	 return false;
          }else	if(desc != "" && desc.length > 2000){
			alert("Maximum length of "+document.getElementById('dom.desc').innerHTML.toLowerCase()+" is 2000 characters.");
			return false;
		}else if(!f9specialchars(fieldName)){
			return false;
		
		}else{
			document.getElementById('paraFrm').action = "DomainMaster_save.action";
		    document.getElementById('paraFrm').submit();
		
		}
		
		
		
  	}
	
	function deleteRecord(){	//callDelete
		if(!callDelete('paraFrm_domainName'))return false;
		
		else{
			document.getElementById('paraFrm').action = "DomainMaster_delete.action";
		    document.getElementById('paraFrm').submit();
		}
	}
	
	
  	
  	function deleteRow(id,i){	//callForDelete1
		if(document.getElementById('confChk'+i).checked == true){
	   		document.getElementById('hdeleteCode'+i).value=id;
	    }
	    else
	   		document.getElementById('hdeleteCode'+i).value="";
   }
	
   
 
	

		//-----function for previous
  
     function cancelFun(){
    	document.getElementById('paraFrm').action="DomainMaster_cancelFirst.action";
        document.getElementById("paraFrm").submit();
    
    }
    
   




	
	</script>

