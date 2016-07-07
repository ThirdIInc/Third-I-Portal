/**************************************************************************
		
**************************************************************************/

// Arrays for nodes and icons
var nodes1			= new Array();;
var openNodes	= new Array();
var icons			= new Array(6);

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
function createTree1(arrName, startNode, openNode) {
	nodes1 = arrName;
	if (nodes1.length > 0) {
		preloadIcons();
		
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenNodes(openNode);
	
		if (startNode !=0) {
			var nodeValues = nodes1[getArrayId(startNode)].split("|");
			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img class='iconImage'    src=\"../pages/common/img/folderopen.gif\" align=\"absbottom\" alt=\"\" />" + nodeValues[2] + "</a><br />");
		}// else document.write("<img class='iconImage'    src=\"../pages/common/img/base.gif\" align=\"absbottom\" alt=\"\" />Website<br />");
	
		var recursedNodes = new Array();
		addNode1(startNode, recursedNodes);
	}
}
// Returns the position of a node in the array
function getArrayId1(node) {
	for (i=0; i<nodes1.length; i++) {
		var nodeValues = nodes1[i].split("|");
		if (nodeValues[0]==node) return i;
	}
}
// Puts in array nodes that will be open
function setOpenNodes1(openNode) {
	for (i=0; i<nodes1.length; i++) {
		var nodeValues = nodes1[i].split("|");
		if (nodeValues[0]==openNode) {
			openNodes.push(nodeValues[0]);
			setOpenNodes1(nodeValues[1]);
		}
	} 
}
// Checks if a node is open
function isNodeOpen1(node) {
	for (i=0; i<openNodes.length; i++)
		if (openNodes[i]==node) return true;
	return false;
}
// Checks if a node has any children
function hasChildNode1(parentNode) {
	for (i=0; i< nodes1.length; i++) {
		var nodeValues = nodes1[i].split("|");
		if (nodeValues[1] == parentNode) return true;
	}
	return false;
}
// Checks if a node is the last sibling
function lastSibling1 (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< nodes1.length; i++) {
		var nodeValues = nodes1[i].split("|");
		if (nodeValues[1] == parentNode)
			lastChild = nodeValues[0];
	}
	if (lastChild==node) return true;
	return false;
}
// Adds a new node to the tree
function addNode1(parentNode, recursedNodes) {
	for (var i = 0; i < nodes1.length; i++) {
		
		var nodeValues = nodes1[i].split("|");
		if (nodeValues[1] == parentNode) {
			
			var ls	= lastSibling1(nodeValues[0], nodeValues[1]);
			var hcn	= hasChildNode1(nodeValues[0]);
			var ino = isNodeOpen1(nodeValues[0]);

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
			document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT TYPE='checkbox' NAME='insertChk1' onChange=\"javascript:hasParentNode1('"+nodeValues[0]+"','insertlodge');\" onClick=\"javascript:checkNode1('"+nodeValues[0]+"','insertlodge');\" id=\"insertlodge"+nodeValues[0]+"\" value ='"+nodeValues[0]+"' ></div>");
			
			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div id=\"div" + nodeValues[0] + "\"");
					if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");
				addNode1(nodeValues[0], recursedNodes);

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