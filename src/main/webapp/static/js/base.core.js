//轮换图
var index = 1;
function rotation(obj) {
	// 图片
	var img = document.getElementById('topic-img'+obj);
	img.style.display = 'block';
	for(var i=1;i<=2;i++){
		if (i!=obj) {
			var img1 = document.getElementById('topic-img'+i);
			img1.style.display = 'none';
		}
	}
	// 文字
	var span = document.getElementById('category-span'+obj);
	span.style.display = 'block';
	for(var i=1;i<=2;i++){
		if (i!=obj) {
			var span1 = document.getElementById('category-span'+i);
			span1.style.display = 'none';
		}
	}
	// 图标
	$('#topic_slider'+obj).addClass('on1')
	for(var i=1;i<=2;i++){
		if (i!=obj) {
			$('#topic_slider'+i).removeClass('on1')
		}
	}
	index = obj;
}

function autorun(){
	if (index==2) {
		index=1;
	}
	rotation(index);
	index++;
	setTimeout('autorun()',3000);
}
// window.onload = autorun;

var index2 = 1;
function bgmimg(obj) {


  obj = index2;
}
function bgmautorun(){
  if (index2 == 4) {
    index2 = 1;
  }
  bgmimg(index2);
  index2++;
  setTimeout('bgmautorun()',3000);
}

function zidong(){
  autorun();
  bgmautorun();
}
window.onload = zidong;
