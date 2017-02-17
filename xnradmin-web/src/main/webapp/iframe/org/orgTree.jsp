<script>
	function SetWinHeight(obj) {
		var win = obj;	
		if (document.getElementById) {
			if (win && !window.opera) {
				if (win.contentDocument
						&& win.contentDocument.body.offsetHeight){
					win.height = win.contentDocument.body.offsetHeight;
					//console.log("-----win.height: "+win.height);
				}else if (win.Document && win.Document.body.scrollHeight){
					win.height = win.Document.body.scrollHeight;
					//console.log("-----win.height: "+win.height);
				}
			
			}
		}
	}
</script>

<iframe id="win" height="482" width="100%"  class="share_self" frameborder="0"
	scrolling="yes" src="page/org/orgTree.action"></iframe>
	
