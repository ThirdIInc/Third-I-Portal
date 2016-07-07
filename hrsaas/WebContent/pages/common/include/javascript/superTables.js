///////////////////////////////////////////////////////////////////////////////////////// 
// SUPER TABLES - VERSION 0.25 - Glodyne License
// Copyright (c) 2008 Venkatesh --- venkatesh.gsn@gmail.com
///////////////////////////////////////////////////////////////////////////////////////// 


var superTables = [];



var superTable = function (tableId) {
    // add this instance to the supertables array
    var x = superTables.length; 
    this.index = x;
    superTables[x] = this;

    // initialize the parameters
    this.sourceTable = document.getElementById(tableId);
    this.hasOptions = (arguments.length > 1) ? true : false;
    this.skin = (this.hasOptions && arguments[1].cssSkin) ? "_" + arguments[1].cssSkin : "_Default";
    this.headerRows = (this.hasOptions && arguments[1].headerRows) ? parseInt(arguments[1].headerRows) : 1;
    this.fixedCols = (this.hasOptions && arguments[1].fixedCols) ? parseInt(arguments[1].fixedCols) : 0;
    this.columnWidths = (this.hasOptions && arguments[1].colWidths) ? arguments[1].colWidths : [];
    this.callbackFunc = (this.hasOptions && arguments[1].onFinish) ? arguments[1].onFinish : function () { return this; };
	this.rightCols = (this.hasOptions && arguments[1].rightCols) ? parseInt(arguments[1].rightCols) : 0;
	this.viewCols = (this.hasOptions && arguments[1].viewCols) ? parseInt(arguments[1].viewCols) : 0;
   
    var i, j, k,dataCount=0;

    // get the column widths
    this.columnCount = (this.sourceTable.tHead) ? this.sourceTable.tBodies[0].rows[0].cells.length : this.sourceTable.tBodies[0].rows[this.headerRows].cells.length;
    
    this.rowsCount = document.getElementById(tableId).rows.length;
    //alert("this.sourceTable.rows.lenght"+this.rowsCount);
    //alert("this.columnCount "+this.columnCount );
    this.columnWidthSum = 0;
    this.sourceTable.className = "sTempTable";
    for (i=0; i<this.columnCount; i++) {
        if (!this.columnWidths[i] || this.columnWidths[i] === -1) {
            this.columnWidths[i] = (this.sourceTable.tHead) ? this.sourceTable.tBodies[0].rows[0].cells[i].offsetWidth : this.sourceTable.tBodies[0].rows[this.headerRows].cells[i].offsetWidth;
        }
        this.columnWidthSum += this.columnWidths[i];
    }

    // create the framework dom
    this.sParent = this.sourceTable.parentNode;
    this.sParentHeight = this.sParent.clientHeight - (this.sParent.offsetHeight - this.sParent.clientHeight);	
    this.sParentWidth = this.sParent.clientWidth - (this.sParent.offsetWidth - this.sParent.clientWidth);
    
	this.sParent.removeChild(this.sourceTable);
	
	
	
	this.sBase = document.createElement("DIV");

	this.sFHeader = this.sBase.cloneNode(false);
	this.sHeader = this.sBase.cloneNode(false);
	this.sLHeader = this.sBase.cloneNode(false);

	
	this.sFData = this.sBase.cloneNode(false);
	this.sData = this.sBase.cloneNode(false);
	this.sLData = this.sBase.cloneNode(false);

	


	this.leftColWidth = 0;
	this.middleColWidth = 0;
	this.rightColWidth = 0;

    // create the row dom molds
    if (this.fixedCols > 0) { 
        var fRowMold = ["<TR>\n"]; 
        for (i=0 ; i<this.fixedCols; i++) {
            fRowMold.push("<TD><DIV>&nbsp;</DIV></TD>");
        }
        fRowMold.push("\n</TR>\n");
        fRowMold = fRowMold.join("");
    }
     var rowMold = ["<TR>\n"];
    for (i=0, j=(this.columnCount - (this.fixedCols + this.rightCols)); i<j; i++) {
        rowMold.push("<TD><DIV>&nbsp;</DIV></TD>");
    }
    rowMold.push("\n</TR>\n");
    rowMold = rowMold.join("");
    
    if (this.rightCols > 0) { 
        var lRowMold = ["<TR>\n"]; 
        for (i=0; i<this.rightCols; i++) {
            lRowMold.push("<TD><DIV>&nbsp;</DIV></TD>");
        }
        lRowMold.push("\n</TR>\n");
        lRowMold = lRowMold.join("");
    }


    // create the header dom framework
    if (this.fixedCols > 0) {
        var fHeaderMold = ["\n<TABLE><TBODY>\n" + fRowMold];
        for (i=1; i<this.headerRows; i++) {
            fHeaderMold.push(fRowMold);
        }
        fHeaderMold.push("</TBODY></TABLE>\n");
        this.sFHeader.innerHTML = fHeaderMold.join("");
		this.sFHeaderTable = this.sFHeader.getElementsByTagName("TABLE")[0];
    }
    var headerMold = ["\n<TABLE><TBODY>\n" + rowMold];
    for (i=1; i<this.headerRows; i++) {
        headerMold.push(rowMold);
    }
    headerMold.push("</TBODY></TABLE>\n");
    this.sHeader.innerHTML = headerMold.join("");
    this.sHeaderTable = this.sHeader.getElementsByTagName("TABLE")[0];

	 if (this.rightCols > 0) {
        var lHeaderMold = ["\n<TABLE><TBODY>\n" + lRowMold];
        for (i=1; i<this.headerRows; i++) {
            lHeaderMold.push(lRowMold);
        }
        lHeaderMold.push("</TBODY></TABLE>\n");
        this.sLHeader.innerHTML = lHeaderMold.join("");
		//alert(this.sLHeader.innerHTML);
        this.sLHeaderTable = this.sLHeader.getElementsByTagName("TABLE")[0];
    }
    
    // fill the header dom framework
    var rootDest, rootSource, dest, source;
    if (this.fixedCols > 0) {
        rootDest = this.sFHeaderTable.tBodies[0];
		//alert(rootDest.innerHTML);
        rootSource = (this.sourceTable.tHead) ? this.sourceTable.tHead : this.sourceTable.tBodies[0];
        for (i=0; i<this.headerRows; i++) {
            dest = rootDest.rows[i];
            source = rootSource.rows[i];
            for (j=0; j<this.fixedCols; j++) {
                try {
					//alert(source.cells[j].innerHTML);
                    dest.cells[j].firstChild.innerHTML = source.cells[j].innerHTML;
                    dest.cells[j].style.width = this.columnWidths[j] + "px";
                    
					leftColWidth += this.columnWidths[j];
                } catch (e) {alert(e); }
            }
        }
    }
	//alert("break");
    rootDest = this.sHeaderTable.tBodies[0];
	//alert(rootDest.innerHTML);
    rootSource = (this.sourceTable.tHead) ? this.sourceTable.tHead : this.sourceTable.tBodies[0];
    for (i=0; i<this.headerRows; i++) {
        dest = rootDest.rows[i];
        source = rootSource.rows[i];
        for (j=0, k=(this.columnCount - (this.fixedCols+this.rightCols)); j<k; j++) {
            try {
				//alert(source.cells[j + this.fixedCols].innerHTML);
                dest.cells[j].firstChild.innerHTML = source.cells[j + this.fixedCols].innerHTML;
                dest.cells[j].style.width = this.columnWidths[j + this.fixedCols] + "px";
				if(j < viewCols) {
					middleColWidth += this.columnWidths[j+ this.fixedCols];
				}
            } catch (e) { }
            dataCount = dataCount+1;
        }
    }
	
	
	 if (this.rightCols > 0) {
        rootDest = this.sLHeaderTable.tBodies[0];
		//rootDest1 = this.totHeaderTable.tBodies[0]
		//alert("dest header : "+rootDest.innerHTML);
        rootSource = (this.sourceTable.tHead) ? this.sourceTable.tHead : this.sourceTable.tBodies[0];
		
		//alert("rootSource hdr :"+rootSource.innerHTML);
        for (i=0; i<this.headerRows; i++) {
            dest = rootDest.rows[i];
			//alert( "dest--------"+dest.innerHTML);
            source = rootSource.rows[i];
			//alert( "source--------"+source.innerHTML);
            for (j=0; j<this.rightCols; j++) {
                try {
					//alert(source.cells[6+j].innerHTML);
                    dest.cells[j].firstChild.innerHTML = source.cells[j+(dataCount +this.fixedCols)].innerHTML;
					//alert( "d-----------------"+dest.cells[j].firstChild.innerHTML);
                    dest.cells[j].style.width = this.columnWidths[j+(dataCount +this.fixedCols)] + "px";
					rightColWidth += this.columnWidths[j+(dataCount +this.fixedCols)];
                } catch (e) {
				
				alert(e);
				}
            }
        }
    }

    // create the data dom framework
    this.tBodyRowCount = this.sourceTable.tBodies[0].rows.length;
    if (this.fixedCols > 0) {
        var fDataMold = ["\n<TABLE><TBODY>\n"];
        for (i=(this.sourceTable.tHead) ? 0 : this.headerRows; i<this.tBodyRowCount; i++) {
            fDataMold.push(fRowMold);
        }
        fDataMold.push("</TBODY></TABLE>\n");
        this.sFData.innerHTML = fDataMold.join("");
		//alert("this.sFData.innerHTML "+this.sFData.innerHTML );
        this.sFDataTable = this.sFData.getElementsByTagName("TABLE")[0];
		//alert("this.sFDataTable.innerHTML ="+this.sFDataTable.innerHTML);
    }
    var dataMold = ["\n<TABLE><TBODY>\n"];
    for (i=(this.sourceTable.tHead) ? 0 : this.headerRows; i<this.tBodyRowCount; i++) {
        dataMold.push(rowMold);
    }
    dataMold.push("</TBODY></TABLE>\n");
    this.sData.innerHTML = dataMold.join("");
    this.sDataTable = this.sData.getElementsByTagName("TABLE")[0];
	//alert("this.sDataTable.innerHTML ="+this.sDataTable.innerHTML);

	if (this.rightCols > 0) {
        var lDataMold = ["\n<TABLE><TBODY>\n"];
        for (i=(this.sourceTable.tHead) ? 0 : this.headerRows; i<this.tBodyRowCount; i++) {
            lDataMold.push(lRowMold);
        }
        lDataMold.push("</TBODY></TABLE>\n");
        this.sLData.innerHTML = lDataMold.join("");
		//alert("this.sLData.innerHTML "+this.sLData.innerHTML );
        this.sLDataTable = this.sLData.getElementsByTagName("TABLE")[0];
		//alert("this.sLDataTable.innerHTML ="+this.sLDataTable.innerHTML);
    }

	


	//alert("fill the data dom framework - break");

    // fill the data dom framework
    if (this.fixedCols > 0) {
        rootDest = this.sFDataTable.tBodies[0];
		//alert(rootDest.innerHTML);
        rootSource = this.sourceTable.tBodies[0];
        for (i=(this.sourceTable.tHead) ? 0 : this.headerRows; i<this.tBodyRowCount; i++) {

            dest = rootDest.rows[(this.sourceTable.tHead) ? i : i - this.headerRows];
            source = rootSource.rows[i];
            for (j=0; j<this.fixedCols; j++) {
                try {
					//alert(source.cells[j].innerHTML);
                    dest.cells[j].firstChild.innerHTML = source.cells[j].innerHTML;
					//alert(dest.cells[j].firstChild.innerHTML);
                } catch (e) { }
            }
        }
        for (i=0; i<this.fixedCols; i++) {
            rootDest.rows[0].cells[i].style.width = this.columnWidths[i] + "px";
        }
    }



	//alert("fill the data dom framework - break---1");
    rootDest = this.sDataTable.tBodies[0];
	//alert(rootDest.innerHTML);
    rootSource = this.sourceTable.tBodies[0];
    for (i=(this.sourceTable.tHead) ? 0 : this.headerRows; i<this.tBodyRowCount; i++) {
        dest = rootDest.rows[(this.sourceTable.tHead) ? i : i - this.headerRows];
        source = rootSource.rows[i];
        for (j=0, k=(this.columnCount - (this.fixedCols +this.rightCols)); j<k; j++) {
            try {
				//alert(source.cells[j + this.fixedCols].innerHTML);
                dest.cells[j].firstChild.innerHTML = source.cells[j + this.fixedCols].innerHTML;
            } catch (e) { }
        }
    }
    for (i=0, j=(this.columnCount - (this.fixedCols +this.rightCols)); i<j; i++) {
        rootDest.rows[0].cells[i].style.width = this.columnWidths[i + this.fixedCols] + "px";
    }

if (this.rightCols > 0) {
	
        rootDest = this.sLDataTable.tBodies[0];
	   
        rootSource = this.sourceTable.tBodies[0];
		//alert("this.headerRows="+this.headerRows);
        for (i=(this.sourceTable.tHead) ? 0 : this.headerRows; i<this.tBodyRowCount; i++) {
			//alert("this.sourceTable.tHead="+this.sourceTable.tHead);
			//alert((this.sourceTable.tHead) ? i : i - this.headerRows);
            dest = rootDest.rows[(this.sourceTable.tHead) ? i : i - this.headerRows];
			
            source = rootSource.rows[i];
            for (j=0; j<this.rightCols; j++) {
                try {
					//alert(source.cells[j].innerHTML);
					//alert(j);
					//alert(dest.cells[j].firstChild.innerHTML);
                    dest.cells[j].firstChild.innerHTML = source.cells[j+(dataCount +this.fixedCols)].innerHTML;
					
                } catch (e) {
				alert(e);
				}
            }
        }
        for (i=0; i<this.rightCols; i++) {
            rootDest.rows[0].cells[i].style.width = this.columnWidths[i+(dataCount +this.fixedCols)] + "px";  // comment by sachin
        }
    }


	this.sBase.style.width = leftColWidth+middleColWidth+rightColWidth+26;
	//alert(this.rowsCount*45);
	//this.sBase.style.height = ((this.rowsCount+this.headerRows)*18 < (220+this.headerRows*18)) ? (this.rowsCount+this.headerRows)*18+"px" : (220+this.headerRows*18) + "px";// ((this.rowsCount+this.headerRows)*18) < 300 ? ((this.rowsCount+this.headerRows)*18+30)+"px" : "300px";
	this.sBase.id="mainDiv";
	
	this.heightVal = (this.rowsCount*18 < 220) ? (this.rowsCount*18) : 220;
		
	this.sBase.appendChild(this.sFHeader);	
	this.sBase.appendChild(this.sHeader);	
	this.sBase.appendChild(this.sLHeader);	

	this.sBase.appendChild(this.sFData);	
	this.sBase.appendChild(this.sData);	
	this.sBase.appendChild(this.sLData);
	
	this.sFHeader.style.width = leftColWidth;
	this.sFHeader.style.height = "30";
	this.sFHeader.id= "leftHeader";
	this.sFHeader.style.top = "0";
	this.sFHeader.style.left = "0";

	this.sHeader.style.width = middleColWidth;
	this.sHeader.id= "middleHeader";
	this.sHeader.style.height = this.sFHeader.style.height;	
	this.sHeader.style.left=leftColWidth;
	this.sHeader.style.top = "0";	

	this.sLHeader.style.width = rightColWidth+10;
	this.sLHeader.id= "rightHeader";
	this.sLHeader.style.top = "0";
	this.sLHeader.style.height = this.sFHeader.style.height;
	this.sLHeader.style.left=leftColWidth+middleColWidth;
	

	this.sFData.style.width = leftColWidth;
	this.sFData.style.height = this.heightVal +"px"; //(this.rowsCount*18 < 220) ? (this.rowsCount*18)+"px" : "220px";
	this.sFData.id= "leftData";
	this.sFData.style.top = this.sLHeader.style.height;
	this.sFData.style.left = "0";

	this.sData.style.width = middleColWidth;
	this.sData.style.height = (this.heightVal+16) +"px";//(this.rowsCount*18 < 220) ? (this.rowsCount*18+18)+"px" : "220px";
	this.sData.id= "middleData";
	this.sData.style.top = this.sLHeader.style.height;
	this.sData.style.left = leftColWidth;

	this.sLData.style.width = rightColWidth+26;
	this.sLData.style.height = this.sFData.style.height;
	this.sLData.id= "rightData";
	this.sLData.style.top = this.sLHeader.style.height;
	this.sLData.style.left = leftColWidth+middleColWidth;
	
	this.sBase.style.height =(this.heightVal+50)+"px";

	this.sParent.appendChild(this.sBase);

	myscroll();
 
}

function myscroll() {
try{
	addScrollSynchronization(document.getElementById("leftData"), document.getElementById("rightData"), "vertical");
	addScrollSynchronization(document.getElementById("middleData"), document.getElementById("rightData"), "vertical");	
	addScrollSynchronization(document.getElementById("middleHeader"), document.getElementById("middleData"), "horizontal");
	}
	catch(e){
	alert(e);
	}
};


