//时间1552037936000，格式yyyy-MM-dd
function yyyyMMdd(now){
	if(now==null||now==""||now.length<=0){
		return "";
	}
	return strDateFormat(now,"yyyy-MM-dd ");
}
function yyyyMMddHHmmss(now){
	if(now==null||now==""||now.length<=0){
		return "";
	}
	return strDateFormat(now,"yyyy-MM-dd HH:mm:ss");
}
//时间1552037936000，格式yyyy-MM-dd HH:mm
function strDateFormat(now,mask)
{
	var datetime = new Date();
    datetime.setTime(now);
    var d = datetime;
    var zeroize = function (value, length)
    {
        if (!length) length = 2;
        value = String(value);
        for (var i = 0, zeros = ''; i < (length - value.length); i++)
        {
            zeros += '0';
        }
        return zeros + value;
    };
 
    return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
    {
        switch ($0)
        {
            case 'd': return d.getDate();
            case 'dd': return zeroize(d.getDate());
            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
            case 'M': return d.getMonth() + 1;
            case 'MM': return zeroize(d.getMonth() + 1);
            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
            case 'yy': return String(d.getFullYear()).substr(2);
            case 'yyyy': return d.getFullYear();
            case 'h': return d.getHours() % 12 || 12;
            case 'hh': return zeroize(d.getHours() % 12 || 12);
            case 'H': return d.getHours();
            case 'HH': return zeroize(d.getHours());
            case 'm': return d.getMinutes();
            case 'mm': return zeroize(d.getMinutes());
            case 's': return d.getSeconds();
            case 'ss': return zeroize(d.getSeconds());
            case 'l': return zeroize(d.getMilliseconds(), 3);
            case 'L': var m = d.getMilliseconds();
                if (m > 99) m = Math.round(m / 10);
                return zeroize(m);
            case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
            case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
            case 'Z': return d.toUTCString().match(/[A-Z]+$/);
            // Return quoted strings with the surrounding quotes removed
            default: return $0.substr(1, $0.length - 2);
        }
    });
};
//父页面显示返回信息
function showRetInfoParent(code, msg) {
	if (code == '0000') {
		parent.$.ligerDialog.hide();
		parent.$.ligerDialog.success(msg);
		parent.window.load();
	} else if (code == '9999') {
		parent.$.ligerDialog.hide();
		parent.$.ligerDialog.error(msg);
		parent.window.load();
	}
}
//新增，或者修改父页面显示返回信息
function addOrUpdateRetInfoParent(data) {
	var code=data.code;
	var msg=data.msg;
	if (code == '0000') {

		parent.$.ligerDialog.hide();
		parent.$.ligerDialog.success(msg);
        //parent.window.load();
        parent.location.reload()
	} else if (code == '9999') {
		$.ligerDialog.hide();
		$.ligerDialog.error(msg);
	}
}

function showResult(data) {
	var code=data.code;
	var msg=data.msg;
	if (code == '0000') {
      		
		$.ligerDialog.success(msg);

	} else if (code == '9999') {
		$.ligerDialog.error(msg);
	}
}
//格式化表格日期；若更新日期为null,使用创建日期
function renderDate(record, rowindex, value, column){
    
    if(value){

         return yyyyMMddHHmmss(value)
    }else{
        return yyyyMMddHHmmss(record.createTime)
    }
   
}