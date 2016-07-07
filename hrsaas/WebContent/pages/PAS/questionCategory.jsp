<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="QuestionCategory" validate="true" id="paraFrm"
	theme="simple">
	
   <s:hidden name="sMode"></s:hidden>
   <s:hidden name="sQstCategoryCode"></s:hidden>
  	
  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    <tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<tr>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Question Category</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/help.gif"
							width="16" height="16" /></div>
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
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		   </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
         </s:else>
        </table>
        
        </td>
    </tr>
    
    <tr>
      <td colspan="3">
	      <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
	         <tr>
	         		<td width="10%" height="22" class="formtext">
						<label  name="questioncategory.category" class = "set" id="questioncategory.category" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.category")%></label>
						<font color="#FF0000">*</font>
					</td>
                    <td width="73%" height="22" colspan="3">
						<%-- <input name="" type="text" size="60" /> --%>
						<s:textfield name="sQstCategoryName" maxlength="100" size="50" />
					</td>
                    <td width="22%" height="22">&nbsp;</td>
             </tr>
                 
             <tr height="30">
                    <td height="25" class="formtext" width="10%">
                    	<label name="questioncategory.status" class = "set" id="questioncategory.status" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.status")%></label>
                    </td>
                    <td height="25" width="30%">
						<!-- 
						<s:radio name="sCategoryStatus" list="#{'A':'Active','D':'De-Active'}" theme="simple" ></s:radio>
						--> 
						<s:if test="flgStatus">
							<input type="radio" name="sCategoryStatus" value = "A" checked="checked" > Active 
							<input type="radio" name="sCategoryStatus" value = "D" > De-Active
						</s:if>
						<s:else>	
						 	<input type="radio" name="sCategoryStatus" value = "A"> Active 
							<input type="radio" name="sCategoryStatus" value = "D" checked="checked"> De-Active
						</s:else>
					</td>
					<td>&nbsp;</td>
             </tr>
				   
			 <tr>
                    <td width="18%" height="22" class="formtext">
                    	<label name="questioncategory.description" class = "set" id="questioncategory.description" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.description")%></label>
						<font color="#FF0000">*</font>
					</td>
                    <td width="73%" height="22" colspan="3">
						<s:textarea rows="3" cols="85" name="sCategoryDescription" onkeyup="callLength('sCategoryDescription','descCnt','200');"></s:textarea>
						<img src="../pages/images/zoomin.gif" height="12" width="12" 
						onclick = "javascript:callWindow('paraFrm_sCategoryDescription','questioncategory.description','','paraFrm_descCnt','200');" >
						
						Remaining characters &nbsp;
                    	<s:textfield name="descCnt" readonly ="true" size="5" cssStyle="background-color: #F2F2F2"></s:textfield> 
                    </td>
            		<td width="22%" height="22">
            			&nbsp;
					</td>
			 </tr>
	      </table>
      </td>
    </tr>
    

	<tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		   </td>
            <td width="22%">&nbsp;</td>
          </tr>
         </s:else>
        </table>
        </td>
    </tr>
    
  </table>
	
</s:form>

<script type="text/javascript">
	function addnewFun() 
	{
		document.getElementById("paraFrm").action="QuestionCategory_addNew.action";
		document.getElementById("paraFrm").submit();	
	}
	
	function searchFun() 
	{
		callsF9(500,325,'QuestionCategory_search.action');
	}
	
	function saveFun() 
	{
		var fields=["paraFrm_sQstCategoryName","paraFrm_sCategoryDescription"];
		var labels=["questioncategory.category","questioncategory.description"];
		var types=["enter","enter"];
		var f9Fields = ["paraFrm_sQstCategoryName","paraFrm_sCategoryDescription"];
		
		if(!(validateBlank(fields,labels,types)))
		{
			return false;
		}
		
		if(!f9specialchars(f9Fields))
		return false;
		
		var descriptionValue = document.getElementById("paraFrm_sCategoryDescription").value
		if (eval(descriptionValue.length) > 200)
		{
			alert ("'Description' is not allow more than 200 characters.");
			return false;
		}
		
		document.getElementById("paraFrm").action="QuestionCategory_save.action";
		document.getElementById("paraFrm").submit();
	}
	
	function resetFun() 
	{
		document.getElementById("paraFrm").action="QuestionCategory_reset.action";
		document.getElementById("paraFrm").submit();
	}
	
	function cancelFun() 
	{
		document.getElementById("paraFrm").action="QuestionCategory_input.action";
		document.getElementById("paraFrm").submit();	
			
	}
	
	function textCounter(field,  maxlimit) {
		
		if (field.value.length > maxlimit){			
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false;			
		}		
		return true;		
	}
	

</script>
