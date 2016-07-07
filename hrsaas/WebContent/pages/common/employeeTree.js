/**************************************************************************
		
**************************************************************************/

// Arrays for nodes and icons
var nodes			= new Array();;
var openNodes	= new Array();
var icons			= new Array(6);
var empTypeGlobal ="false";
var checkLength='';
// Loads all icons that are used in the tree
function preloadIcons() {
	icons[0] = new Image();
	icons[0].src = "../pages/common/img/plus.gif";
	icons[1] = new Image();
	icons[1].src = "../pages/common/img/plusbottom.gif";
	icons[2] = new Image();
	icons[2].src = "../pages/common/img/minus.gif";
	icons[3] = new Image();
	icons[3].src = "../pages/common/img/minusbottom.gif";
	icons[4] = new Image();
	icons[4].src = "../pages/common/img/folder.gif";
	icons[5] = new Image();
	icons[5].src = "../pages/common/img/folderopen.gif";
}

// Create the tree
	function addHeadingTR(length,empType){
			empTypeGlobal =empType; 
			document.write("<td class='formth' width='18%' align='center'><INPUT TYPE='button' VALUE=' Move To Group' CLASS=' token' onClick=\"javascript:moveToGroup('"+length+"');\"  ></td>");
			if(empTypeGlobal=="false")
			document.write("<td class='formth' width='18%' align='center'><INPUT TYPE='button' VALUE=' Remove' CLASS=' delete' onClick=\"javascript:removeFromGroup('"+length+"');\"> </td> ");
			
	}
function createTree(arrName, startNode, openNode) {
	nodes = arrName;
	if (nodes.length > 0) {
		preloadIcons();
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenNodes(openNode);
	
		if (startNode !=0) {
			var nodeValues = nodes[getArrayId(startNode)].split("|");
			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img class='iconImage'    src=\"../pages/common/img/folderopen.gif\" align=\"absbottom\" alt=\"\" />" + nodeValues[2] + "</a><br />");
		}// else document.write("<img class='iconImage'    src=\"../pages/common/img/base.gif\" align=\"absbottom\" alt=\"\" />Website<br />");
	
		var recursedNodes = new Array();
		addNode(startNode, recursedNodes);
		if(checkLength==''){
				document.write(" <font color='red'>No employee is pending for configuration.</font>\ ");
		}
	}
}
// Returns the position of a node in the array
function getArrayId(node) {
	for (i=0; i<nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0]==node) return i;
	}
}
// Puts in array nodes that will be open
function setOpenNodes(openNode) {
	for (i=0; i<nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0]==openNode) {
			openNodes.push(nodeValues[0]);
			setOpenNodes(nodeValues[1]);
		}
	} 
}
// Checks if a node is open
function isNodeOpen(node) {
	for (i=0; i<openNodes.length; i++)
		if (openNodes[i]==node) return true;
	return false;
}
// Checks if a node has any children
function hasChildNode(parentNode) {
	for (i=0; i< nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode) return true;
	}
	return false;
}

// Checks if a node is the last sibling
function lastSibling (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode)
			lastChild = nodeValues[0];
	}
	if (lastChild==node) return true;
	return false;
}
// Adds a new node to the tree
function addNode(parentNode, recursedNodes) {
	
	if(nodes.length==0){
		alert("No employee is pending for configuration");
		}
	for (var i = 0; i < nodes.length; i++) {
		
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode) {
			
			var ls	= lastSibling(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

			document.write("<div style ='position:absolute;left:30px;'>");

			// Write out line & empty icons
			for (g=0; g<recursedNodes.length; g++) {
				if (recursedNodes[g] == 1) document.write("<img class='iconImage'    src=\"../pages/common/img/line.gif\" align=\"absbottom\" alt=\"\" />");
				else  document.write("<img class='iconImage'    src=\"../pages/common/img/empty.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// put in array line & empty icons
			if (ls) recursedNodes.push(0);
			else recursedNodes.push(1);

			// Write out join icons
			if (hcn) {	
				
				if (ls) {					
					document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 1);\"><img class='iconImage'    id=\"join" + nodeValues[0] + "\" src=\"../pages/common/img/");
					 	if (ino) document.write("minus");
						else document.write("plus");
					document.write("bottom.gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 0);\"><img class='iconImage'    id=\"join" + nodeValues[0] + "\" src=\"../pages/common/img/");
						if (ino) document.write("minus");
						else document.write("plus");
					document.write(".gif\" align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				
				if (ls) document.write("<img class='iconImage'    src=\"../pages/common/img/joinbottom.gif\" align=\"absbottom\" alt=\"\" />");
				else document.write("<img class='iconImage'    src=\"../pages/common/img/join.gif\" align=\"absbottom\" alt=\"\" />");
			}

			// Start link
			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");
			
			// Write out folder & page icons
			if (hcn) {
				document.write("<img class='iconImage'    id=\"icon" + nodeValues[0] + "\" src=\"../pages/common/img/folder")
					if (ino) document.write("open");
				document.write(".gif\" align=\"absbottom\" alt=\"Folder\" />");
			} else document.write("<img class='iconImage'    id=\"icon" + nodeValues[0] + "\" src=\"../pages/common/img/page.gif\" align=\"absbottom\" alt=\"Page\" />");
			
			// Write out node name
			document.write(nodeValues[2]);

			// End link
			
			document.write("</a></div><div style ='position:relative;left:100px;width:69%;'>");
			
			if(empTypeGlobal =="true"){
			document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE='checkbox' NAME='insertChk' onChange=\"javascript:hasParentNode('"+nodeValues[0]+"','insert');\" onClick=\"javascript:checkNode('"+nodeValues[0]+"','insert');\" id=\"insert"+nodeValues[0]+"\" value ='"+nodeValues[0]+"' >");
			document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE='checkbox' NAME='moveChk' onChange=\"javascript:hasParentNode('"+nodeValues[0]+"','move');\" onClick=\"javascript:checkNode('"+nodeValues[0]+"','move');\" id=\"move"+nodeValues[0]+"\" value ='"+nodeValues[0]+"' ></div>");
			}else{
			document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE='checkbox' NAME='insertChk' onChange=\"javascript:hasParentNode('"+nodeValues[0]+"','insert');\" onClick=\"javascript:checkNode('"+nodeValues[0]+"','insert');\" id=\"insert"+nodeValues[0]+"\" value ='"+nodeValues[0]+"' >");
			document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE='checkbox' NAME='moveChk' onChange=\"javascript:hasParentNode('"+nodeValues[0]+"','move');\" onClick=\"javascript:checkNode('"+nodeValues[0]+"','move');\" id=\"move"+nodeValues[0]+"\" value ='"+nodeValues[0]+"' >");
			document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE='checkbox' NAME='removeChk' onChange=\"javascript:hasParentNode('"+nodeValues[0]+"','remove');\" onClick=\"javascript:checkNode('"+nodeValues[0]+"','remove');\" id=\"remove"+nodeValues[0]+"\" value ='"+nodeValues[0]+"' ></div>");
			}
			checkLength = '1';
			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div id=\"div" + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");
				addNode(nodeValues[0], recursedNodes);

				document.write("</div>");				
			}			
			// remove last line or empty icon 
			recursedNodes.pop();
		}
	}
}


// Opens or closes a node
function oc(node, bottom) {
//alert('open/close'+node+bottom);
	var theDiv = document.getElementById("div" + node);
	var theJoin	= document.getElementById("join" + node);
	var theIcon = document.getElementById("icon" + node);
	
	if (theDiv.style.display == 'none') {
		if (bottom==1) theJoin.src = icons[3].src;
		else theJoin.src = icons[2].src;
		theIcon.src = icons[5].src;
		theDiv.style.display = '';
	} else {
		if (bottom==1) theJoin.src = icons[1].src;
		else theJoin.src = icons[0].src;
		theIcon.src = icons[4].src;
		theDiv.style.display = 'none';
	}
}function isEmpChecked(type){

	var checkedFlag = "false";
	for (var i = 0; i < nodes.length; i++) {
	var nodeVal= nodes[i].split("|");
		try{
		if(document.getElementById(type+nodeVal[0]).checked && nodeVal[0].length <8){
		checkedFlag ="true";
		
		}
		}catch(e){
		}
	}
	if(checkedFlag=="false"){
		alert("Please select atleast one employee to "+type+".");
		return false;
	}else return true;	
}
function moveToGroup(length){
		if(!isEmpChecked("move")){
			return false;
		}
		win=window.open('','win','top=240,left=130,width=400,height=200,scrollbars=no,status=no,resizable=no');
		document.getElementById("paraFrm").target="win";
		document.getElementById("paraFrm").action="AppraiserConfig_openMoveToGroupForm.action";
		document.getElementById("paraFrm").submit();	
		document.getElementById("paraFrm").target="main";
}

function removeFromGroup(length){
		if(!isEmpChecked("remove")){
			return false;
		}
		var conf=confirm("Do you really want to remove these employees ?");
  			if(conf) {
		document.getElementById("paraFrm").action="AppraiserConfig_removeFromGroup.action";
		document.getElementById("paraFrm").submit();
		}
	}	
// Push and pop not implemented in IE
if(!Array.prototype.push) {
	function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = array_push;
}
if(!Array.prototype.pop) {
	function array_pop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = array_pop;
}


Array.prototype.oAll = function(status) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {
			this.nodeStatus(status, n, this.aNodes[n]._ls)
			this.aNodes[n]._io = status;
		}
	}
	if (this.config.useCookies) this.updateCookie();
};