function getUid(){
	var uid = localStorage.getItem("uid");
	if(uid==null)
		uid = getQueryString("uid");
	if(uid!=null)
		localStorage.setItem("uid",uid);
	
	return uid;
}