<%@ page import="java.util.*" %>

<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../pages/common/include/javascript/labelManageAjax.js"></script>



<%	java.util.HashMap commonLabel = (java.util.HashMap)getServletContext().getAttribute("common"+session.getAttribute("session_pool"));
	java.util.HashMap label = (java.util.HashMap)request.getAttribute("formLabels");
	if(label == null) {
		label = new HashMap();
	}
	if(commonLabel!= null) {
		label.putAll(commonLabel);
	}
%>
<input type="hidden" id="changeLabelId" name="changeLabelId" />
<input type="hidden" id="restoreValue" name="restoreValue"/>
<s:hidden id="userProfileId" name="userProfileId"/>
<input type="hidden" name="divMovementHidden" id="divMovementHidden"/>

<div id="labelDiv" style='position:absolute; z-index:3; width:470px; height:120px; display:none;border:2px solid; top: 70px; 
left: 200px;padding: 10px' class="formbg" >
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
		<tr>
			<td width="35%" colspan="3" align="center" class="formth" style="cursor:move" onmouseout="Drag.end();" 
			onmouseover="Drag.init(document.getElementById('labelDiv'), null, 0, 350, 0, 700);" >
				<b>Change Label</b>
			</td>
			<td width="3%" align="right" border="1" class="formth" style="font-family:Arial;cursor: pointer" onclick="hideDiv();">
				<b>X</b>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
		<tr>
			<td width="30%" >Current Label :</td>
			<td><input type="text" name="label" id="myLabel" readonly="readonly" size="50"/></td>
		</tr>		
		<tr>
			<td width="30%">New Label :</td>
			<td><input type="text" name="newLabel" id="myNewLabel" maxlength="50" size="50"/></td>
		</tr>
		<tr>
			<td></td>
			<td width="100%">
				<input type="button" name="changeLabelButtons" value=" Apply" class="add" style="cursor: pointer;" 
				onclick="callLabelChange();" />&nbsp;
				<input type="button" name="changeLabelButtons" value=" Restore Default" class="reset" style="cursor: pointer;" 
				onclick="restore()" />&nbsp;
				<input type="button" name="changeLabelButtons" value=" Cancel" class="cancel" style="cursor: pointer;" 
				onclick="hideDiv();" />
			</td>
		</tr>
	</table>
</div>

<script>
	function callShowDiv(obj) {
		try {
			if(document.getElementById('userProfileId').value != 1) {
				return false;
			}
			document.getElementById('labelDiv').style.display = "";
			document.getElementById('labelDiv').style.top=getTopPos(obj) + 'px';
			document.getElementById('labelDiv').style.left=getleftPos(obj) + 'px';
			
			var val = trim(obj.innerHTML);
			
			while(val.indexOf("  ", " ") > -1) {
				val = val.replace("  ", " ");
			}
			if(val.length > 35) {
				if(val.indexOf("- ") > 0) {
					val = val.replace("- ", "");
				}
			}
			
			document.getElementById('myLabel').value = val;
			document.getElementById('myNewLabel').focus();
			document.getElementById('changeLabelId').value = obj.getAttribute('name');
		} catch(e) {}
	}
	
	function getTopPos(inputObj) {
		var returnValue = inputObj.offsetTop + inputObj.offsetHeight;
	  
		while((inputObj = inputObj.offsetParent) != null) {
	  		returnValue += inputObj.offsetTop;
		}
		if(returnValue < 70) {
			return returnValue;
	  	}
		return returnValue - 100;
	}

	function getleftPos(inputObj) {
		var returnValue = inputObj.offsetLeft;
		
	  	while((inputObj = inputObj.offsetParent) != null) {
	  		returnValue += inputObj.offsetLeft;
	  	}
	  	if(returnValue > 350) {
	  		returnValue = returnValue - 400;
	  			  	
		  	while(returnValue < 0) {
		  	 	returnValue += 10;
		  	}
		  	return returnValue;
	  	} else {
	  		return returnValue + 40;
		}
	}
	
	function callLabelChange() {
		try {
			var labelVal = trim(document.getElementById('myNewLabel').value);
			if(labelVal.length > 35) {
				if(labelVal.indexOf(" ") < 0) {
					labelVal = labelVal.substr(0,30) + "- " + labelVal.substr(30, labelVal.length);
				}
			}
			while(labelVal.indexOf("  ") > -1) {
				labelVal = labelVal.replace("  "," ");
			}
			
			var changeLabelId = document.getElementById('changeLabelId').value;
			
			if(labelVal == "") {
				alert("Please enter new label name");
				document.getElementById('myNewLabel').value= "";
				document.getElementById('myNewLabel').focus();
				return false;
			}
			if(labelVal == document.getElementById('myLabel').value) {
				alert("Current label and new label cannot be same. Please select different name");
				document.getElementById('myNewLabel').focus();
				return false;
			}
			var menuCode = document.getElementById('paraFrm_menuCode').value;
			changeLabel('<%=request.getContextPath()%>/common/LabelManage_changeLabel.action?labelId=' + changeLabelId + '&labelVal=' + labelVal + '&menuCode=' + menuCode + '&abc=' + Math.random());
		} catch(e) {}
	}
	
	function restore() {
		try {
			var changeLabelId = document.getElementById('changeLabelId').value;
			var menuCode = document.getElementById('paraFrm_menuCode').value;
			restoreLabel('<%=request.getContextPath()%>/common/LabelManage_restoreLabel.action?menuCode=' + menuCode + '&labelId=' + changeLabelId + '&abc=' + Math.random());
		} catch(e){}
	}
	
	function hideDiv() {
		document.getElementById('myNewLabel').value = "";
		document.getElementById('labelDiv').style.display = "none";
	}
</script>

<script>
/* ----------------------------------------
Following java script is used to drag the 
label div.
------------------------------------------*/

var Drag = {
	obj : null,

	init : function(o, oRoot, minX, maxX, minY, maxY, bSwapHorzRef, bSwapVertRef, fXMapper, fYMapper)
	{
		document.getElementById('divMovementHidden').value="";
		o.onmousedown	= Drag.start;
		o.hmode			= bSwapHorzRef ? false : true ;
		o.vmode			= bSwapVertRef ? false : true ;

		o.root = oRoot && oRoot != null ? oRoot : o ;

		if (o.hmode  && isNaN(parseInt(o.root.style.left  ))) o.root.style.left   = "0px";
		if (o.vmode  && isNaN(parseInt(o.root.style.top   ))) o.root.style.top    = "0px";
		if (!o.hmode && isNaN(parseInt(o.root.style.right ))) o.root.style.right  = "0px";
		if (!o.vmode && isNaN(parseInt(o.root.style.bottom))) o.root.style.bottom = "0px";

		o.minX	= typeof minX != 'undefined' ? minX : null;
		o.minY	= typeof minY != 'undefined' ? minY : null;
		o.maxX	= typeof maxX != 'undefined' ? maxX : null;
		o.maxY	= typeof maxY != 'undefined' ? maxY : null;

		o.xMapper = fXMapper ? fXMapper : null;
		o.yMapper = fYMapper ? fYMapper : null;

		o.root.onDragStart	= new Function();
		o.root.onDragEnd	= new Function();
		o.root.onDrag		= new Function();
	},

	start : function(e)
	{
		if(document.getElementById('divMovementHidden').value!="")
			return false;
		var o = Drag.obj = this;
		e = Drag.fixE(e);
		var y = parseInt(o.vmode ? o.root.style.top  : o.root.style.bottom);
		var x = parseInt(o.hmode ? o.root.style.left : o.root.style.right );
		o.root.onDragStart(x, y);

		o.lastMouseX	= e.clientX;
		o.lastMouseY	= e.clientY;

		if (o.hmode) {
			if (o.minX != null)	o.minMouseX	= e.clientX - x + o.minX;
			if (o.maxX != null)	o.maxMouseX	= o.minMouseX + o.maxX - o.minX;
		} else {
			if (o.minX != null) o.maxMouseX = -o.minX + e.clientX + x;
			if (o.maxX != null) o.minMouseX = -o.maxX + e.clientX + x;
		}

		if (o.vmode) {
			if (o.minY != null)	o.minMouseY	= e.clientY - y + o.minY;
			if (o.maxY != null)	o.maxMouseY	= o.minMouseY + o.maxY - o.minY;
		} else {
			if (o.minY != null) o.maxMouseY = -o.minY + e.clientY + y;
			if (o.maxY != null) o.minMouseY = -o.maxY + e.clientY + y;
		}

		document.onmousemove	= Drag.drag;
		document.onmouseup		= Drag.end;

		return false;
	},

	drag : function(e)
	{
		e = Drag.fixE(e);
		var o = Drag.obj;

		var ey	= e.clientY;
		var ex	= e.clientX;
		var y = parseInt(o.vmode ? o.root.style.top  : o.root.style.bottom);
		var x = parseInt(o.hmode ? o.root.style.left : o.root.style.right );
		var nx, ny;

		if (o.minX != null) ex = o.hmode ? Math.max(ex, o.minMouseX) : Math.min(ex, o.maxMouseX);
		if (o.maxX != null) ex = o.hmode ? Math.min(ex, o.maxMouseX) : Math.max(ex, o.minMouseX);
		if (o.minY != null) ey = o.vmode ? Math.max(ey, o.minMouseY) : Math.min(ey, o.maxMouseY);
		if (o.maxY != null) ey = o.vmode ? Math.min(ey, o.maxMouseY) : Math.max(ey, o.minMouseY);

		nx = x + ((ex - o.lastMouseX) * (o.hmode ? 1 : -1));
		ny = y + ((ey - o.lastMouseY) * (o.vmode ? 1 : -1));

		if (o.xMapper)		nx = o.xMapper(y)
		else if (o.yMapper)	ny = o.yMapper(x)

		Drag.obj.root.style[o.hmode ? "left" : "right"] = nx + "px";
		Drag.obj.root.style[o.vmode ? "top" : "bottom"] = ny + "px";
		Drag.obj.lastMouseX	= ex;
		Drag.obj.lastMouseY	= ey;

		Drag.obj.root.onDrag(nx, ny);
		return false;
	},

	end : function()
	{
		document.getElementById('divMovementHidden').value="END";
		document.onmousemove = null;
		document.onmouseup   = null;
		document.onmouseover   = null;
		try {
		Drag.obj.root.onDragEnd(	parseInt(Drag.obj.root.style[Drag.obj.hmode ? "left" : "right"]), 
									parseInt(Drag.obj.root.style[Drag.obj.vmode ? "top" : "bottom"]));
			}
			catch(e)
			{
			}
		Drag.obj = null;
	},

	fixE : function(e)
	{
		if (typeof e == 'undefined') e = window.event;
		if (typeof e.layerX == 'undefined') e.layerX = e.offsetX;
		if (typeof e.layerY == 'undefined') e.layerY = e.offsetY;
		return e;
	}
};
</script>

