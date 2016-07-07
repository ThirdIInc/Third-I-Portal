	/************************************************************************************************************
	(C) www.dhtmlgoodies.com, January 2006
	
	This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.	
	
	Version:	1.0	: January 16th - 2006
				1.1 : January 31th - 2006 - Added cookie support - remember rss sources
				1.2 : July 13th - 2006 - Fixed a problem in the createRSSBoxesFromCookie function
				
	Terms of use:
	You are free to use this script as long as the copyright message is kept intact. However, you may not
	redistribute, sell or repost it without our permission.
	
	Thank you!
	
	www.dhtmlgoodies.com
	Alf Magne Kalleland
	
	************************************************************************************************************/		
	
	/* USER VARIABLES */

	var columnParentBoxId = 'floatingBoxParentContainer';	// Id of box that is parent of all your dragable boxes
	var src_rightImage ='../pages/common/css/default/images/trans.gif';
	var src_downImage = '../pages/common/css/default/images/trans.gif';
	var src_refreshSource = '../pages/common/css/default/images/trans.gif';
	var src_smallRightArrow = '../pages/common/images/small_arrow.gif';
	var src_closeSource = '../pages/common/css/default/images/trans.gif';
	var transparencyWhenDragging = false;
	var txt_editLink = 'Edit';
	var txt_editLink_stop = 'End edit';
	var autoScrollSpeed = 4;	// Autoscroll speed	- Higher = faster	
	var dragObjectBorderWidth = 1;	// Border size of your RSS boxes - used to determine width of dotted rectangle
	
	var useCookiesToRememberRSSSources = true;
	
	var nameOfCookie = 'dragable_rss_boxes';	// Name of cookie
	
	/* END USER VARIABLES */
	
	
	
	var columnParentBox;
	var dragableBoxesObj;
	
	var ajaxObjects = new Array();
	
	var boxIndex = 0;	
	var autoScrollActive = false;
	var dragableBoxesArray = new Array();
	
	var dragDropCounter = -1;
	var dragObject = false;
	var dragObjectNextSibling = false;
	var dragObjectParent = false;
	var destinationObj = false;
	
	var mouse_x;
	var mouse_y;
	
	var el_x;
	var el_y;	
	
	var rectangleDiv;
	var okToMove = true;

	var documentHeight = false;
	var documentScrollHeight = false;
	var dragableAreaWidth = false;
		
	var opera = navigator.userAgent.toLowerCase().indexOf('opera')>=0?true:false;
	var cookieCounter=0;
	var cookieRSSSources = new Array();
	
	var staticObjectArray = new Array();

	/*
	These cookie functions are downloaded from 
	http://www.mach5.com/support/analyzer/manual/html/General/CookiesJavaScript.htm
	*/	
	
	function Get_Cookie(name) { 
	   var start = document.cookie.indexOf(name+"="); 
	   var len = start+name.length+1; 
	   if ((!start) && (name != document.cookie.substring(0,name.length))) return null; 
	   if (start == -1) return null; 
	   var end = document.cookie.indexOf(";",len); 
	   if (end == -1) end = document.cookie.length; 
	   return unescape(document.cookie.substring(len,end)); 
	} 
	// This function has been slightly modified
	function Set_Cookie(name,value,expires,path,domain,secure) { 
		expires = expires * 60*60*24*1000;
		var today = new Date();
		var expires_date = new Date( today.getTime() + (expires) );
	    var cookieString = name + "=" +escape(value) + 
	       ( (expires) ? ";expires=" + expires_date.toGMTString() : "") + 
	       ( (path) ? ";path=" + path : "") + 
	       ( (domain) ? ";domain=" + domain : "") + 
	       ( (secure) ? ";secure" : ""); 
	    document.cookie = cookieString; 
	} 

	function autoScroll(direction,yPos)
	{
		if(document.documentElement.scrollHeight>documentScrollHeight && direction>0)return;
		if(opera)return;
		window.scrollBy(0,direction);
		if(!dragObject)return;
		
		if(direction<0){
			if(document.documentElement.scrollTop>0){
				dragObject.style.top = (el_y - mouse_y + yPos + document.documentElement.scrollTop) + 'px';		
			}else{
				autoScrollActive = false;
			}
		}else{
			if(yPos>(documentHeight-50)){	
				dragObject.style.top = (el_y - mouse_y + yPos + document.documentElement.scrollTop) + 'px';			
			}else{
				autoScrollActive = false;
			}
		}
		if(autoScrollActive)setTimeout('autoScroll('+direction+',' + yPos + ')',5);
	}
		
	function initDragDropBox(e)
	{
	
		
		
		dragDropCounter = 1;
		if(document.all)e = event;
		
		if (e.target) source = e.target;
			else if (e.srcElement) source = e.srcElement;
			if (source.nodeType == 3) // defeat Safari bug
				source = source.parentNode;
		
		if(source.tagName.toLowerCase()=='img' || source.tagName.toLowerCase()=='a' || source.tagName.toLowerCase()=='input' || source.tagName.toLowerCase()=='td' || source.tagName.toLowerCase()=='tr' || source.tagName.toLowerCase()=='table')return;
		
	
		mouse_x = e.clientX;
		mouse_y = e.clientY;	
		var numericId = this.id.replace(/[^0-9]/g,'');
		el_x = getLeftPos(this.parentNode.parentNode)/1;
		el_y = getTopPos(this.parentNode.parentNode)/1 - document.documentElement.scrollTop;
			
		dragObject = this.parentNode.parentNode;
		
		documentScrollHeight = document.documentElement.scrollHeight + 100 + dragObject.offsetHeight;
		
		
		if(dragObject.nextSibling){
			dragObjectNextSibling = dragObject.nextSibling;
			if(dragObjectNextSibling.tagName!='DIV')dragObjectNextSibling = dragObjectNextSibling.nextSibling;
		}
		dragObjectParent = dragableBoxesArray[numericId]['parentObj'];
			
		dragDropCounter = 0;
		initDragDropBoxTimer();	
		
		return false;
	}
	
	
	function initDragDropBoxTimer()
	{
		if(dragDropCounter>=0 && dragDropCounter<10){
			dragDropCounter++;
			setTimeout('initDragDropBoxTimer()',10);
			return;
		}
		if(dragDropCounter==10){
			mouseoutBoxHeader(false,dragObject);
		}
		
	}

function moveDragableElement(e){
		if(document.all)e = event;
		if(dragDropCounter<10)return;
		
		if(document.all && e.button!=1 && !opera){
			stop_dragDropElement();
			return;
		}
		
		if(document.body!=dragObject.parentNode){
			dragObject.style.width = (dragObject.offsetWidth - (dragObjectBorderWidth*2)) + 'px';
			dragObject.style.position = 'absolute';	
			dragObject.style.textAlign = 'left';
			if(transparencyWhenDragging){	
				dragObject.style.filter = 'alpha(opacity=70)';
				dragObject.style.opacity = '0.7';
			}	
			dragObject.parentNode.insertBefore(rectangleDiv,dragObject);
			rectangleDiv.style.display='block';
			document.body.appendChild(dragObject);

			rectangleDiv.style.width = (dragObject.offsetWidth - (dragObjectBorderWidth*2)) + 'px'; 
			rectangleDiv.style.height = (dragObject.offsetHeight - (dragObjectBorderWidth*2)) + 'px'; 
			
		}
		
		if(e.clientY<50 || e.clientY>(documentHeight-50)){
			if(e.clientY<50 && !autoScrollActive){
				autoScrollActive = true;
				autoScroll((autoScrollSpeed*-1),e.clientY);
			}
			
			if(e.clientY>(documentHeight-50) && document.documentElement.scrollHeight<=documentScrollHeight && !autoScrollActive){
				autoScrollActive = true;
				autoScroll(autoScrollSpeed,e.clientY);
			}
		}else{
			autoScrollActive = false;
		}		

		
		var leftPos = e.clientX;
		var topPos = e.clientY + document.documentElement.scrollTop;
		
		dragObject.style.left = (e.clientX - mouse_x + el_x) + 'px';
		dragObject.style.top = (el_y - mouse_y + e.clientY + document.documentElement.scrollTop) + 'px';
								

		
		if(!okToMove)return;
		okToMove = false;

		destinationObj = false;	
		rectangleDiv.style.display = 'none'; 
		
		var objFound = false;
		var tmpParentArray = new Array();
		
		if(!objFound){
			for(var no=1;no<dragableBoxesArray.length;no++){
		
			////alert("boxIndex"+boxIndex);
			
				
				if(dragableBoxesArray[no]['obj']==dragObject)continue;
				tmpParentArray[dragableBoxesArray[no]['obj'].parentNode.id] = true;
				if(!objFound){
					var tmpX = getLeftPos(dragableBoxesArray[no]['obj']);
					var tmpY = getTopPos(dragableBoxesArray[no]['obj']);

					if(leftPos>tmpX && leftPos<(tmpX + dragableBoxesArray[no]['obj'].offsetWidth) && topPos>(tmpY-20) && topPos<(tmpY + (dragableBoxesArray[no]['obj'].offsetHeight/2))){
						destinationObj = dragableBoxesArray[no]['obj'];
						destinationObj.parentNode.insertBefore(rectangleDiv,dragableBoxesArray[no]['obj']);
						rectangleDiv.style.display = 'block';
						objFound = true;
						break;
						
					}
					
					if(leftPos>tmpX && leftPos<(tmpX + dragableBoxesArray[no]['obj'].offsetWidth) && topPos>=(tmpY + (dragableBoxesArray[no]['obj'].offsetHeight/2)) && topPos<(tmpY + dragableBoxesArray[no]['obj'].offsetHeight)){
						objFound = true;
						if(dragableBoxesArray[no]['obj'].nextSibling){
							
							destinationObj = dragableBoxesArray[no]['obj'].nextSibling;
							if(!destinationObj.tagName)destinationObj = destinationObj.nextSibling;
							if(destinationObj!=rectangleDiv)destinationObj.parentNode.insertBefore(rectangleDiv,destinationObj);
						}else{
							destinationObj = dragableBoxesArray[no]['obj'].parentNode;
							dragableBoxesArray[no]['obj'].parentNode.appendChild(rectangleDiv);
						}
						rectangleDiv.style.display = 'block';
						break;					
					}
					
					
					if(!dragableBoxesArray[no]['obj'].nextSibling && leftPos>tmpX && leftPos<(tmpX + dragableBoxesArray[no]['obj'].offsetWidth)
					&& topPos>topPos>(tmpY + (dragableBoxesArray[no]['obj'].offsetHeight))){
						destinationObj = dragableBoxesArray[no]['obj'].parentNode;
						dragableBoxesArray[no]['obj'].parentNode.appendChild(rectangleDiv);	
						rectangleDiv.style.display = 'block';	
						objFound = true;				
						
					}
				}
				
			}
		
		}
		
		if(!objFound){
			////alert(numberOfDash);
			for(var no=0;no<numberOfColumns;no++){
				if(!objFound){
					var obj = document.getElementById('dragableBoxesColumn' + no);			
					
						var left = getLeftPos(obj)/1;						
					
						var width = obj.offsetWidth;
						if(leftPos>left && leftPos<(left+width)){
							destinationObj = obj;
							obj.appendChild(rectangleDiv);
							rectangleDiv.style.display='block';
							objFound=true;		
							
						}				
					
				}
		}
		}
	

		setTimeout('okToMove=true',5);
		
	}
	
	function stop_dragDropElement()
	{
		
		if(dragDropCounter<10){
			dragDropCounter = -1
			return;
		}
		dragDropCounter = -1;
		if(transparencyWhenDragging){
			dragObject.style.filter = null;
			dragObject.style.opacity = null;
		}		
		dragObject.style.position = 'static';
		dragObject.style.width = null;
		var numericId = dragObject.id.replace(/[^0-9]/g,'');
		if(destinationObj && destinationObj.id!=dragObject.id){
			
			if(destinationObj.id.indexOf('dragableBoxesColumn')>=0){
				destinationObj.appendChild(dragObject);
				dragableBoxesArray[numericId]['parentObj'] = destinationObj;
			}else{
				destinationObj.parentNode.insertBefore(dragObject,destinationObj);
				dragableBoxesArray[numericId]['parentObj'] = destinationObj.parentNode;
			}


							
		}else{
			if(dragObjectNextSibling){
				dragObjectParent.insertBefore(dragObject,dragObjectNextSibling);	
			}else{
				dragObjectParent.appendChild(dragObject);
			}				
			
			
		}
	

		
		autoScrollActive = false;
		rectangleDiv.style.display = 'none'; 
		dragObject = false;
		dragObjectNextSibling = false;
		destinationObj = false;
		
		//if(useCookiesToRememberRSSSources)setTimeout('saveCookies()',100);

		documentHeight = document.documentElement.clientHeight;	
	}

	
	function getTopPos(inputObj)
	{		
	  var returnValue = inputObj.offsetTop;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetTop;
	  }
	  return returnValue;
	}
	
	function getLeftPos(inputObj)
	{
	  var returnValue = inputObj.offsetLeft;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetLeft;
	  }
	  return returnValue;
	}
		
	
	
	function createColumns(dashletURLs,dashletWidths,dashletHeights,dashletFloats)
	{
		//alert(1);
		if(!columnParentBoxId){
			//alert('No parent box defined for your columns');
			return;
		}
		columnParentBox = document.getElementById(columnParentBoxId);
		var columnWidth = Math.floor(100/numberOfColumns);
		var sumWidth = 0;
		for(var no=0;no<numberOfColumns;no++){
		//alert(no);
			var div = document.createElement('DIV');
			if(no==(numberOfColumns-1))columnWidth = 99 - sumWidth;
			sumWidth = sumWidth + columnWidth;
			div.style.cssText = 'float:left;width:'+dashletWidths[no]+'%;padding:0px;margin:0px;';
			div.style.height='100%';
			div.style.styleFloat='left';
			
			div.style.width = dashletWidths[no] + '%';
			div.style.padding = '0px';
			div.style.margin = '0px';

			div.id = 'dragableBoxesColumn' + (no+1);
			columnParentBox.appendChild(div);
			
			var clearObj = document.createElement('HR');	
			clearObj.style.clear = 'both';
			clearObj.style.visibility = 'hidden';
			//div.appendChild(clearObj);
		}
		
		
		
		var clearingDiv = document.createElement('DIV');
		columnParentBox.appendChild(clearingDiv);
		clearingDiv.style.clear='both';
		
	}
	
	function mouseoverBoxHeader()
	{
		if(dragDropCounter==10)return;
		var id = this.id.replace(/[^0-9]/g,'');
		document.getElementById('dragableBoxExpand' + id).style.visibility = 'visible';		
		document.getElementById('dragableBoxRefreshSource' + id).style.visibility = 'visible';		
		document.getElementById('dragableBoxCloseLink' + id).style.visibility = 'visible';
		//if(document.getElementById('dragableBoxEditLink' + id))document.getElementById('dragableBoxEditLink' + id).style.visibility = 'visible';
		
	}
	function mouseoutBoxHeader(e,obj)
	{
		if(!obj)obj=this;
		
		var id = obj.id.replace(/[^0-9]/g,'');
		document.getElementById('dragableBoxExpand' + id).style.visibility = 'visible';		
		document.getElementById('dragableBoxRefreshSource' + id).style.visibility = 'visible';		
		document.getElementById('dragableBoxCloseLink' + id).style.visibility = 'visible';		
		//if(document.getElementById('dragableBoxEditLink' + id))document.getElementById('dragableBoxEditLink' + id).style.visibility = 'visible';		
		
	}
	
	function refreshRSS()
	{
	//alert(this.id.replace(/[^0-9]/g);
		reloadRSSData(this.id.replace(/[^0-9]/g,''));
		setTimeout('dragDropCounter=-5',5);
	}
	
	function showHideBoxContent(e,inputObj)
	{
	////alert('hide');
		if(document.all)e = event;
		if(!inputObj)inputObj=this;
		
		var numericId = inputObj.id.replace(/[^0-9]/g,'');
		var obj = document.getElementById('dragableBoxContent' + numericId);
		////alert(document.getElementById('dragableBoxContent' + numericId).innerHTML)
		////alert(document.getElementById('dragableBoxContent' + numericId).style.display);
		var status=document.getElementById('dragableBoxContent' + numericId).style.display;
		if(status=="" || status=="block"){
		document.getElementById('dragableBoxContent' + numericId).style.display ='none';
		inputObj.className="max";
		}
		else {
		document.getElementById('dragableBoxContent' + numericId).style.display ='block';
		inputObj.className="min";
		}
		
		
		
		//obj.style.display = inputObj.src.indexOf(src_rightImage)>=0?'none':'block';
	    //inputObj.src.indexOf(src_rightImage)>=0?src_downImage:src_rightImage
		
		//dragableBoxesArray[numericId]['boxState'] = obj.style.display=='block'?1:0;
		//saveCookies();
		setTimeout('dragDropCounter=-5',5);
	}
	
	function mouseover_CloseButton()
	{
		this.className = 'closeButton_over';	
		setTimeout('dragDropCounter=-5',5);
	}
	
	function highlightCloseButton()
	{
		this.className = 'closeButton_over';	
	}
	
	function mouseout_CloseButton()
	{
		this.className = 'closeButton';	
	}
	
	function closeDragableBox(e,inputObj)
	{
		if(!inputObj)inputObj = this;
		var numericId = inputObj.id.replace(/[^0-9]/g,'');
		document.getElementById('dragableBox' + numericId).style.display='none';	
		
		Set_Cookie(nameOfCookie + cookieRSSSources[dragableBoxesArray[numericId]['rssUrl']],'none' ,60000);

		setTimeout('dragDropCounter=-5',5);
		
	}
	
	function editRSSContent()
	{
		var numericId = this.id.replace(/[^0-9]/g,'');
		var obj = document.getElementById('dragableBoxEdit' + numericId);
		if(obj.style.display=='none'){
			obj.style.display='block';
			this.innerHTML = txt_editLink_stop;
			document.getElementById('dragableBoxHeader' + numericId).style.height = '135px';
		}else{
			obj.style.display='none';
			this.innerHTML = txt_editLink;
			document.getElementById('dragableBoxHeader' + numericId).style.height = '20px';
		}
		
		setTimeout('dragDropCounter=-5',5);
	}
	
	
	function showStatusBarMessage(numericId,message)
	{
		//document.getElementById('dragableBoxStatusBar' + numericId).innerHTML = message;
		
	}
	
	function addBoxHeader(parentObj,externalUrl,notDrabable)
	{
		var div = document.createElement('DIV');
		div.className = 'dragableBoxHeader';
		
		div.id = 'dragableBoxHeader' + boxIndex;
		div.onmouseover = mouseoverBoxHeader;
		div.onmouseout = mouseoutBoxHeader;
		if(!notDrabable){
			div.onmousedown = initDragDropBox;
			div.style.cursor = 'move';
		}
		var textSpan = document.createElement('SPAN');
		textSpan.id = 'dragableBoxHeader_txt' + boxIndex;
		textSpan.style.padding='3px';
		textSpan.style.paddingLeft='1px';
		div.appendChild(textSpan);

		parentObj.appendChild(div);	

		/*var image = document.createElement('IMG');
		image.style.cssText = 'float:right';
		image.style.styleFloat = 'right';
		image.style.verticalAlign='bottom';
		image.id = 'dragableBoxCloseLink' + boxIndex;
		image.src = src_closeSource;
		image.style.cursor = 'pointer';
		image.style.visibility = 'visible';
		image.onmousedown = closeDragableBox;
		image.className="close";
		image.style.height='14px';
		image.style.width='14px';
		image.style.marginTop='4px';
		image.style.marginBottom='5px';
		image.style.marginRight='2px';
		div.appendChild(closeLink);
		
		var image = document.createElement('IMG');
		image.src = src_refreshSource;
		image.id = 'dragableBoxRefreshSource' + boxIndex;
		image.style.cssText = 'float:right';
		image.style.styleFloat = 'right';
		image.style.visibility = 'visible';
		image.onclick = refreshRSS;
		image.className="refresh";
		image.style.cursor = 'pointer';
		
		image.style.height='14px';
		image.style.width='14px';
		image.style.marginTop='4px';
		image.style.marginBottom='5px';
		image.style.marginRight='2px';
		if(!externalUrl)image.style.display='none';
		
		div.appendChild(image);

		var image = document.createElement('IMG');
		image.id = 'dragableBoxExpand' + boxIndex;
		image.style.cssText = 'float:right';
		image.style.styleFloat = 'right';
		image.src = src_rightImage;
		image.style.visibility = 'visible';	
		image.style.cursor = 'pointer';
		image.className="min";
		image.style.height='14px';
		image.style.width='14px';
		image.style.marginTop='4px';
		image.style.marginBottom='5px';
		image.style.marginRight='2px';
		image.onmousedown = showHideBoxContent;	
		if(!externalUrl)image.style.display='none';
		div.appendChild(image);*/

		var span = document.createElement('DIV');
		span.style.styleFloat = 'right';
		span.style.verticalAlign='middle';
		span.id = 'dragableBoxCloseLink' + boxIndex;
		span.style.cursor = 'pointer';
		span.style.visibility = 'visible';
		span.onmousedown = closeDragableBox;
		span.className="close";
		span.style.height='14px';
		span.style.width='14px';
		span.style.marginTop='4px';
		span.style.marginBottom='0px';
		span.style.marginRight='2px';

		div.appendChild(span);

		var span = document.createElement('DIV');
		span.style.styleFloat = 'right';
		span.style.verticalAlign='middle';
		span.id = 'dragableBoxRefreshSource' + boxIndex;
		span.style.cursor = 'pointer';
		span.style.visibility = 'visible';
		span.onclick = refreshRSS;
		span.className="refresh";
		span.style.height='14px';
		span.style.width='14px';
		span.style.marginTop='4px';
		span.style.marginBottom='0px';
		span.style.marginRight='2px';
		div.appendChild(span);

		var span = document.createElement('DIV');
		span.style.styleFloat = 'right';
		span.style.verticalAlign='middle';
		span.id =  'dragableBoxExpand' + boxIndex;
		span.style.cursor = 'pointer';
		span.style.visibility = 'visible';
		span.onmousedown = showHideBoxContent;	
		span.className="min";
		span.style.height='14px';
		span.style.width='14px';
		span.style.marginTop='4px';
		span.style.marginBottom='0px';
		span.style.marginRight='2px';
		div.appendChild(span);

	}
	
	function saveFeed(boxIndex)
	{
		var heightOfBox = dragableBoxesArray[boxIndex]['heightOfBox'] = document.getElementById('heightOfBox[' + boxIndex + ']').value;
		var intervalObj = dragableBoxesArray[boxIndex]['intervalObj'];
		if(intervalObj)clearInterval(intervalObj);
		
		if(heightOfBox && heightOfBox>40){
			var contentObj = document.getElementById('dragableBoxContent' + boxIndex);
			contentObj.style.height = heightOfBox + 'px';
			contentObj.setAttribute('heightOfBox',heightOfBox);
			contentObj.heightOfBox = heightOfBox;	
			if(document.all)contentObj.style.overflowY = 'auto';else contentObj.style.overflow='-moz-scrollbars-vertical;';
			if(opera)contentObj.style.overflow='auto';			
			
		}
		
		dragableBoxesArray[boxIndex]['rssUrl'] = document.getElementById('rssUrl[' + boxIndex + ']').value;
		dragableBoxesArray[boxIndex]['heightOfBox'] = heightOfBox;
		dragableBoxesArray[boxIndex]['maxRssItems'] = document.getElementById('maxRssItems[' + boxIndex + ']').value;
		dragableBoxesArray[boxIndex]['heightOfBox'] = document.getElementById('heightOfBox[' + boxIndex + ']').value;
		dragableBoxesArray[boxIndex]['minutesBeforeReload'] = document.getElementById('minutesBeforeReload[' + boxIndex + ']').value;
		
	/*	if(dragableBoxesArray[boxIndex]['minutesBeforeReload'] && dragableBoxesArray[boxIndex]['minutesBeforeReload']>5){
			var tmpInterval = setInterval("reloadRSSData(" + boxIndex + ")",(dragableBoxesArray[boxIndex]['minutesBeforeReload']*1000*60));	
			dragableBoxesArray[boxIndex]['intervalObj'] = tmpInterval;
		}
		reloadRSSData(boxIndex);
		
		if(useCookiesToRememberRSSSources)setTimeout('saveCookies()',100);*/
		
	}
	
	function addBoxContentContainer(parentObj,heightOfBox)
	{
		var div = document.createElement('DIV');
		
		div.className = 'dragableBoxContent';
		if(opera)div.style.clear='none';
		div.id = 'dragableBoxContent' + boxIndex;
		
		var newAdddiv = document.createElement('DIV');
		newAdddiv.className = 'new_dragableBoxContent';
		parentObj.appendChild(newAdddiv);
		newAdddiv.appendChild(div);	
		div.style.float='left';		
		//div.style.width="98%";
		if(heightOfBox && heightOfBox/1>40){
			div.style.height = heightOfBox + 'px';
			//div.setAttribute('heightOfBox',heightOfBox);
			//div.heightOfBox = heightOfBox;	
			//if(document.all)div.style.overflowY = 'auto';else div.style.overflow='-moz-scrollbars-vertical;';
			//if(opera)div.style.overflow='auto';
		}		
	}
	
	function addBoxStatusBar(parentObj)
	{
		var div = document.createElement('DIV');
		div.className = 'dragableBoxStatus';
		div.style.height='2px';
		parentObj.appendChild(div);	
		
		
	}
	
	function createABox(columnIndex,heightOfBox,externalUrl,uniqueIdentifier,notDragable)
	{
		boxIndex++;
		
		
		
		var maindiv = document.createElement('DIV');
		maindiv.className = 'dragableBox';
		maindiv.id = 'dragableBox' + boxIndex;
		
		
		var div = document.createElement('DIV');
		div.className='dragableBoxInner';
		
		maindiv.appendChild(div);
		
		addBoxHeader(div,externalUrl,notDragable);
		//alert('hey..'+heightOfBox);
		addBoxContentContainer(div,heightOfBox);
		//addBoxStatusBar(div);
		var div1 = document.createElement('DIV');
		div1.className = 'dragableBoxStatus';
		div1.style.height='2px';
		div.appendChild(div1);	
		
		
		var mainSpan=document.createElement('SPAN');
		
		var span1=document.createElement('SPAN');
		span1.className='r1';
		
		var span2=document.createElement('SPAN');
		span2.className='r2';
		
		var span3=document.createElement('SPAN');
		span3.className='r3';
		
		var span4=document.createElement('SPAN');
		span4.className='r4';		
		//alert('boxIndex=='+boxIndex);
		mainSpan.height='5px';
		mainSpan.appendChild(span4);
		mainSpan.appendChild(span3);
		mainSpan.appendChild(span2);
		mainSpan.appendChild(span1);
		maindiv.appendChild(mainSpan);
		var obj = document.getElementById('dragableBoxesColumn' + columnIndex);		
		var obj = document.getElementById('dragableBoxesColumn' + columnIndex);	
		//alert('3'+obj);
		var subs = obj.getElementsByTagName('DIV');
		//alert(obj.getElementsByTagName('DIV'));
		////alert(subs.length);
		if(subs.length>0){
			obj.insertBefore(maindiv,subs[0]);
		}else{
		
			obj.appendChild(maindiv);
		}
		//alert('3.1');
		dragableBoxesArray[boxIndex] = new Array();
		dragableBoxesArray[boxIndex]['obj'] = maindiv;
		dragableBoxesArray[boxIndex]['parentObj'] = maindiv.parentNode;
		dragableBoxesArray[boxIndex]['uniqueIdentifier'] = uniqueIdentifier;
		dragableBoxesArray[boxIndex]['heightOfBox'] = heightOfBox;
		dragableBoxesArray[boxIndex]['boxState'] = 1;	// Expanded
		
		staticObjectArray[uniqueIdentifier] = boxIndex;
		
		return boxIndex;
		
	}
	
	function showRSSData(ajaxIndex,boxIndex)
	{
	try{
		var rssContent = ajaxObjects[ajaxIndex].response;
		////alert('rssContent' +rssContent);
		tokens = rssContent.split(/\n\n/g);
		
		
		var headerTokens = tokens[0].split(/\n/g);
		if(headerTokens[0]=='0'){
			headerTokens[1] = '';
			headerTokens[0] = 'Invalid source';
			////alert(2);
		////alert(document.getElementById('dragableBoxHeader_txt' + boxIndex));
			closeDragableBox(false,document.getElementById('dragableBoxHeader_txt' + boxIndex));
			return;			
		}
		document.getElementById('dragableBoxHeader_txt' + boxIndex).innerHTML = '<span class="textHeader">' + dragableBoxesArray[boxIndex]['dashHeader'] + '<\/span>';	// title  <span class="rssNumberOfItems">(' + headerTokens[1] + ')<\/span>
		
		var string = '<table cellpadding="0" cellspacing="0" >';
		for(var no=1;no<tokens.length;no++){	// Looping through RSS items
			var itemTokens = tokens[no].split(/##/g);
			
			string = string + '<tr bgcolor="black"><td bgcolor="black"><img src="' + src_smallRightArrow + '"><td><p class=\"boxItemHeader\"><a class=\"boxItemHeader\" href="' + itemTokens[3] + '" onclick="var w = window.open(this.href);return false">' + itemTokens[0] + '<\/a><\/p><\/td><\/tr>';		
		}
		string = string +rssContent+ '<\/table>';
		//alert('string'+string);
		
		//document.getElementById('dragableBoxContent' + boxIndex).innerHTML =string ;
		includeScript('dragableBoxContent' + boxIndex,string);
		//showStatusBarMessage(boxIndex,'');
		//alert(document.getElementById('dragableBoxContent' + boxIndex).innerHTML);
		ajaxObjects[ajaxIndex] = false;
		}catch(e){}
	}
	function includeScript(element, html) {
			
			//alert(document.getElementById(element));
			//alert(html);
			
			//alert(html);
			try{
			document.getElementById(element).innerHTML=html;
			//'<table cellpadding="0" cellspacing="0"></table><table width="100%" align="center" cellpadding="0" cellspacing="0" height="25%"><tbody><tr><td width="100%" align="center"><div id="chartPerfDiv"></div></td></tr><tr></tr><tr><td align="center"></td>	</tr></tbody></table><script defer="defer">showPerformanceGraph();var width='';var xmlData='';function showPerformanceGraph(){	try{		width=700;		xmlData='<chart adjustTM="1"  defaultAnimation="1" bgAlpha="0" autoScale="0"  bgAlpha="0" bgColor="FFFFFF" lowerLimit="0" upperLimit="100" numberSuffix="%25" showBorder="0" chartTopMargin="10" toolTipBgColor="80A905" gaugeFillMix="{dark-10},FFFFFF,{dark-10}" gaugeFillRatio="3"> <colorRange><color minValue="0"  maxValue="65" code="FF654F"/><color minValue="65"  maxValue="95" code="F6BD0F"/><color minValue="95"  maxValue="100" code="8BBA00"/> </colorRange><dials><dial value="0.0" rearExtension="10"/></dials> <trendpoints><point value="65" displayValue="Minimum_2" fontcolor="F6BD0F" useMarker="1" dashed="1" dashGap="2" valueInside="1" /><point value="75" displayValue="Average_2" fontcolor="F6BD0F" useMarker="1" dashed="1" dashGap="2" valueInside="1" /><point value="85" displayValue="Average_1" fontcolor="F6BD0F" useMarker="1" dashed="1" dashGap="2" valueInside="1" /><point value="95" displayValue="Maximum_2" fontcolor="F6BD0F" useMarker="1" dashed="1" dashGap="2" valueInside="1" /><point value="100" displayValue="Maximum_1" fontcolor="F6BD0F" useMarker="1" dashed="1" dashGap="2" valueInside="1" /> </trendpoints></chart>';	     var myChart = new FusionCharts('../pages/charts/fusionWidgets/AngularGauge.swf', 'myChartId',width, '170', '0','0', 'F8BB4B' );	     xmlData = myChart.encodeDataXML(xmlData);myChart.setDataXML(xmlData);myChart.setTransparent(true); myChart.render("chartPerfDiv");	}catch(e){	}}</script>';
			
			var re = /<script\b[\s\S]*?>([\s\S]*?)<\//ig;
			var match;
			while (match = re.exec(html)) {
			//alert('match  '+ match[1]);
			eval(match[1]);
			var newScript = document.createElement('script');
			newScript.type = "text/javascript";
			newScript.text = match[1];
			//alert('d[x].text  '+  match[1]);
			document.getElementById(element).appendChild(newScript);
			
			}
			}catch(e){
			
			}
			
		}
	
	function reloadRSSData(numericId)
	{
	//alert(numericId);
		var ajaxIndex = ajaxObjects.length;
		ajaxObjects[ajaxIndex] = new sack();
		try{
		document.getElementById('dragableBoxContent' + numericId).innerHTML ="<table height='100%' width='100%'><tr><td class='load' height='100%' width='100%'>Loading .... Please wait<br><img src='../pages/images/ajax-loader.gif' width='20' height='20'/></td></tr></table>";
		//showStatusBarMessage(numericId,"<div><font color='#FFFFFF' >Loading ....Please wait </font></div>");
		ajaxObjects[ajaxIndex].requestFile = dragableBoxesArray[numericId]['dashletURL'];	// Specifying which file to get
		ajaxObjects[ajaxIndex].onCompletion = function(){ showRSSData(ajaxIndex,numericId); };	// Specify function that will be executed after file has been found
		ajaxObjects[ajaxIndex].runAJAX();		// Execute AJAX function			
		}catch(e){
		alert(e);
		}
	}
		
	function createARSSBox(url,columnIndex,heightOfBox,maxRssItems,minutesBeforeReload,uniqueIdentifier,state,dashletURL,dashHeader,reportFlag)
	{
		//alert('dashletURL'+dashletURL);

		if(!heightOfBox)heightOfBox = '0';
		if(!minutesBeforeReload)minutesBeforeReload = '0';

		var tmpIndex = createABox(columnIndex,heightOfBox,true);

		dragableBoxesArray[tmpIndex]['rssUrl'] = url;
		dragableBoxesArray[tmpIndex]['dashletURL']=dashletURL;
		dragableBoxesArray[tmpIndex]['dashHeader']=dashHeader;
		dragableBoxesArray[tmpIndex]['maxRssItems'] = maxRssItems?maxRssItems:100;
		dragableBoxesArray[tmpIndex]['minutesBeforeReload'] = minutesBeforeReload;
		dragableBoxesArray[tmpIndex]['heightOfBox'] = heightOfBox;
		dragableBoxesArray[tmpIndex]['uniqueIdentifier'] = uniqueIdentifier;
		dragableBoxesArray[tmpIndex]['state'] = state;
		
		if(state==0){
			showHideBoxContent(false,document.getElementById('dragableBoxExpand' + tmpIndex));
		}
		
		staticObjectArray[uniqueIdentifier] = tmpIndex;
		
		var tmpInterval = false;
		/*if(minutesBeforeReload && minutesBeforeReload>0){
			var tmpInterval = setInterval("reloadRSSData(" + tmpIndex + ")",(minutesBeforeReload*1000*60));
		}*/
try{
		dragableBoxesArray[tmpIndex]['intervalObj'] = tmpInterval;
		//if(!document.getElementById('dragableBoxContent' + tmpIndex).innerHTML)
		//document.getElementById('dragableBoxContent' + tmpIndex).innerHTML ="<table height='100%' width='100%'><tr><td class='load' height='100%' width='100%'>Loading .... Please wait<br><img src='../pages/images/ajax-loader.gif' width='20' height='20'/></td></tr></table>";

		if(url.length>0 && url!='undefined'){
			
			var ajaxIndex = ajaxObjects.length;
			ajaxObjects[ajaxIndex] = new sack();
			//alert('maxRssItems  '+maxRssItems);
			if(!maxRssItems)maxRssItems = 100;
			//alert(dashletURL);
			
			ajaxObjects[ajaxIndex].requestFile = dashletURL;	// Specifying which file to get
			ajaxObjects[ajaxIndex].onCompletion = function(){ showRSSData(ajaxIndex,tmpIndex); };	// Specify function that will be executed after file has been found
			//alert('completion');
			ajaxObjects[ajaxIndex].runAJAX();	// Execute AJAX function
			//alert('runAJAX()');
		}else{
			hideHeaderOptionsForStaticBoxes(tmpIndex);
		}	
		}catch(e){
			
		}
				
	}
	
	function createHelpObjects()
	{
		/* Creating rectangle div */
		rectangleDiv = document.createElement('DIV');
		rectangleDiv.id='rectangleDiv';
		rectangleDiv.style.display='none';
		document.body.appendChild(rectangleDiv);
		
		 
	}
	
	function cancelSelectionEvent(e)
	{
		if(document.all)e = event;
		
		if (e.target) source = e.target;
			else if (e.srcElement) source = e.srcElement;
			if (source.nodeType == 3) // defeat Safari bug
				source = source.parentNode;
		if(source.tagName.toLowerCase()=='input')return true;
						
		if(dragDropCounter>=0)return false; else return true;	
		
	}
	
	function cancelEvent()
	{
		return false;
	}
	
	function initEvents()
	{
		document.body.onmousemove = moveDragableElement;
		document.body.onmouseup = stop_dragDropElement;
		document.body.onselectstart = cancelSelectionEvent;

		document.body.ondragstart = cancelEvent;	
		
		documentHeight = document.documentElement.clientHeight;	
		
	}

	function hideHeaderOptionsForStaticBoxes(boxIndex)
	{
		if(document.getElementById('dragableBoxRefreshSource' + boxIndex))document.getElementById('dragableBoxRefreshSource' + boxIndex).style.display='none';
		if(document.getElementById('dragableBoxCloseLink' + boxIndex))document.getElementById('dragableBoxCloseLink' + boxIndex).style.display='';		
		if(document.getElementById('dragableBoxEditLink' + boxIndex))document.getElementById('dragableBoxEditLink' + boxIndex).style.display='none';		
	}
	
	
	
	/* You customize this function */
	function createDefaultBoxes(dashletURLs,columnNos,dashHeaders,numberOfColumns,heightOfBoxes)
	{		
	try{
		numberOfColumns=numberOfColumns;	
		if(cookieCounter==0){
		
			for(var no=dashletURLs.length-1;no>=0;no--){
					createARSSBox('http://rss.cnn.com/rss/cnn_topstories.rss',columnNos[no],heightOfBoxes[no],5,100,'',1,dashletURLs[no],dashHeaders[no]);
			}
		
		}
	}catch(e){}
	}
	
	function reportViewer(url){
		document.getElementById('reportDash').style.display='block';
		document.getElementById("urlFrame").src=url;	
	}
	
	/* Disable drag for a box */
	function disableBoxDrag(boxIndex)
	{
			document.getElementById('dragableBoxHeader' + boxIndex).onmousedown = '';
			document.getElementById('dragableBoxHeader' + boxIndex).style.cursor = 'default';		
	}
	