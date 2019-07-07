function add0(m){return m<10?'0'+m:m }

function timestampToTime(timestamp) {
    // var timestamp = parseInt(new Date().getTime()/1000);//当前时间戳
    let time = new Date(timestamp*1);
    let y = time.getFullYear();
    let m = time.getMonth()+1;
    let d = time.getDate();
    let h = time.getHours();
    let mm = time.getMinutes();
    let s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function getUrlPara() {
    let url = document.location.toString();
    let arrUrl = url.split("?");
    return decodeURI(arrUrl[1]);
}