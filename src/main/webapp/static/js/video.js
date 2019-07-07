// jQuery对象转Dom对象
var video = $('.videoID')[0];
var video = $('.videoID')[1];
var video = $('.videoID');

$('.videoID').click(function(){
  if (video[0].paused){
    video[0].play();
    video[1].play();
    $("#xiaoTV").hide();
    $('#btnPlay').hide();
    $("#btnPlay2").show();
    init_screen();
  }else{
    video[0].pause();
    video[1].pause();
    $("#xiaoTV").show();
    $("#btnPlay2").hide();
    $("#btnPlay").show();
    // $(".d_show div").stop();
  }
});

$('#btnPlay').click(function(){
    video[0].play();
    video[1].play();
    $("#xiaoTV").hide();
    $('#btnPlay').hide();
    $("#btnPlay2").show();
});
$('#btnPlay2').click(function(){
    video[0].pause();
    video[1].pause();
    $("#xiaoTV").show();
    $("#btnPlay2").hide();
    $("#btnPlay").show();
});

//进度条

// 进度条走动
video.on('timeupdate', function() {
    var currentPos = video[0].currentTime; //获取当前时间
    var maxduration = video[0].duration; //得到视频的总时间
    var percentage = 100 * currentPos / maxduration; //走的%
    $('.timeBar').css('width', percentage+'%');
});

var timeDrag = false; //拖动状态
$('.progressBar').mousedown(function(e) {
    timeDrag = true;
    updatebar(e.pageX);
});
$(document).mouseup(function(e) {
   if(timeDrag) {
      timeDrag = false;
      updatebar(e.pageX);
   }
});
$(document).mousemove(function(e) {
   if(timeDrag) {
      updatebar(e.pageX);
   }
});
 
//更新进度条控件
var updatebar = function(x) {
   var progress = $('.progressBar');
   var maxduration = video[0].duration; //视频时间
   var position = x - progress.offset().left; //点击改变位置
   var percentage = 100 * position / progress.width();
 
   //检查是否在范围内
   if(percentage > 100) {
      percentage = 100;
   }
   if(percentage < 0) {
      percentage = 0;
   }
 
   //更新进度条和视频时间
   $('.timeBar').css('width', percentage+'%');
   video[0].currentTime = maxduration * percentage / 100;
   video[1].currentTime = maxduration * percentage / 100;
};


// 视频总时间和播放时间
// 总时间
video.on('loadedmetadata', function() {
	var duration = $('.duration');
	var total = parseInt(video[0].duration/1);
	var second = total%60;
	var minute = parseInt((total/60)/1);
	if (minute<10) {
		minute = '0'+minute
	}
	duration.text(minute+':'+second);
});

// 播放时间
video.on('timeupdate', function() {
  var current = $('.current');
  var go = parseInt(video[0].currentTime/1);
	var minute = Math.floor (go / 60);
	var second = (go % 60);
	if (minute<10) {
		minute = '0'+minute;
	}
	if (second<10) {
		current.text(minute+':'+'0'+second);
	}else{
		current.text(minute+':'+second);
	}
});


// 音量控制

//静音和开启
$('.muted').click(function() {
  video[0].muted=true;
  video[1].muted=true;
  $('.muted').hide(); //隐藏
  $('.muted2').css("display", "block");
  $('.volume-span').text(0);
  video[0].volume = 0;
  video[1].volume = 0;
  return false;
});

$('.muted2').click(function() {
  video[0].muted=false;
  video[1].muted=false;
  $('.muted').css("display", "block");
  $('.muted2').hide();
  $('.volume-span').text(100);
  video[0].volume = 1.0;
  video[1].volume = 1.0;
  return false;
});

$('.muted3').click(function() {
  video[0].muted=false;
  video[1].muted=false;
  $('.muted').hide(); //隐藏
  $('.muted3').hide(); //隐藏
  $('.muted2').css("display", "block");
  $('.volume-span').text(0);
  video[0].volume = 0;
  video[1].volume = 0;
  return false;
});

// 音量条
video.on('timeupdate', function() {
   var volume = video[0].volume; //获取当前音量
   var percentage = 100 * volume; //走的%
   $('.volume').css('height', percentage+'%');
});

var volumeDrag = false; //拖动状态
$('.volumeBar').mousedown(function(e) {
   volumeDrag = true;
   updatevolume(e.pageY);
});
$(document).mouseup(function(e) {
   if(volumeDrag) {
      volumeDrag = false;
      updatevolume(e.pageY);
   }
});
$(document).mousemove(function(e) {
   if(volumeDrag) {
      updatevolume(e.pageY);
   }
});
 
//更新进度条控件
var updatevolume = function(Y) {
  var progress = $('.volumeBar')
  var position = (progress.offset().top+64.5) - Y;
  var percentage = 100 * position / progress.height();
  //检查是否在范围内
   if(percentage > 100) {
      percentage = 100;
   }
   if(percentage < 0) {
      percentage = 0;
   }
  $('.volume').css('height', percentage+'%');
  video[0].volume = percentage / 100;
  video[1].volume = percentage / 100;

  if(position > 64.5) {
      position = 64.5;
   }
   if(position < 0) {
      position = 0;
   }
   $('.volume-span').text(parseInt((position/0.645)/1));

   if (video[0].volume == 0) {
      $('.muted').hide(); //隐藏
      $('.muted3').hide(); //隐藏
      $('.muted2').css("display", "block");
   }else if (video[0].volume < 0.5) {
      $('.muted').hide(); //隐藏
      $('.muted2').hide(); //隐藏
      $('.muted3').css("display", "block");
   }else{
      $('.muted3').hide(); //隐藏
      $('.muted2').hide(); //隐藏
      $('.muted').css("display", "block");
   }
};


// 弹幕部分

// 点击发送
$(".dm_sub").click(function(){
  var text=$(".dm_txt").val();
  var div="<div>"+text+"</div>";
  $(".d_show").append(div);
  var li = '<li>'+
    '<span class="vrd-cenc1">01:33</span>'+
    '<span class="vrd-cenc2">'+text+'</span>'+
    '<span class="vrd-cenc3">03-03 12:35</span>'
    +'</li>';
  $(".vrd-cenc ul").append(li);
  $(".dm_txt").val("");
  init_screen();
})

// 弹幕浮动
function init_screen(){
  $('.dm').css("display", "block");
  var _top=0;
  $(".d_show").find("div").show().each(function(){
    // 弹幕范围
    var _left=682;
    var _height=483;

    // 距离设置
    _top=_top+40;
    if(_top>=_height-80){  
      _top=0;
    }

    $(this).css({left:_left,top:_top,color:getReandomColor()}); // 赋给弹幕随机颜色
    // 时间错开
    var time=10000;
    if($(this).index()%2==0){
      time=15000;
    }
    $(this).animate({left:"-"+_left+"px"},time);// 开始浮动
  });
}

// 随机颜色
function getReandomColor(){
  return '#'+(function(h){
  return new Array(7-h.length).join("0")+h
  })((Math.random()*0x1000000<<0).toString(16))
}

// 弹幕的暂停和视频的暂停
// 弹幕暂停问题 只能暂停 但无法暂停前的位置
// 视频暂停 因为视频上多个dm的div 只能用dm控制视频的播放暂停
$('.dm').click(function(){
  if (video[0].paused){
    video[0].play();
    video[1].play();
    $("#xiaoTV").hide();
    $('#btnPlay').hide();
    $("#btnPlay2").show();
    // init_screen();
  }else{
    video[0].pause();
    video[1].pause();
    $("#xiaoTV").show();
    $("#btnPlay2").hide();
    $("#btnPlay").show();
    // $(".d_show div").stop();

  }
});

// 宽屏
$(".kuang").click(function(){
  $(".kuang").hide(); //隐藏
  $(".kuang1").css("display", "block");
  $(".video-right").hide(); //隐藏
  $(".notice").hide(); //隐藏
  $(".videoID3").css("width","978px");
  $(".videoID3").css("height","488px");
  $("#xiaoTV").css("left","860px");
  $(".control").css("width","978px");
  $(".progressBar").css("width","656px");
  $(".tetx-danmu").css("width","978px");
  $(".dm_txt").css("width","880px");
  return false;
});

$(".kuang1").click(function(){
  $(".kuang").css("display", "block");
  $(".kuang1").hide();
  $(".video-right").css("display", "block");
  $(".notice").css("display", "block");
  $(".videoID3").css("width","682px");
  $(".videoID3").css("height","430px");
  $("#xiaoTV").css("left","580px");
  $(".control").css("width","682px");
  $(".progressBar").css("width","360px");
  $(".tetx-danmu").css("width","682px");
  $(".dm_txt").css("width","549px");
  return false;
});

$('.quan').click(function(){
  element.requestFullscreen();
  return false;
});
// 评论回复
$(".submit-comment").click(function(){
  var height = $(".main-inner-p").outerHeight();
  var height1 = $(".main-inner-c").outerHeight();
  $(".main-inner-p").css("height",height+108+"px");
  $(".main-inner-c").css("height",height1+109+"px");
  var text=$(".bdc-input").val();
  var li = '<li>'+
             '<img src="imgs/top/avatar.jpg">'+
             '<span class="comm_list-span">ExistSb</span>'+
             '<i></i>'+
             '<p class="comm_list-p">'+text+'</p>'+
             '<div class="elinfo">'+
              '<span>#5</span>'+
              '<span>2016-06-06 07:17</span>'+
              '<span class="elinfo1">赞(0)</span>'+
              '<span class="elinfo1">回复</span>'+
              '<span class="elinfo1 elinfo2">举报</span>'+
             '</div>'
            +'</li>';

  $(".comm_list").append(li);
})
$('.bdc-input').mousemove(function(){
  $('.b-icon-triangle-gray-left').css("background","url(imgs/icons.png) -841px -1046px no-repeat");
});

$('.bdc-input').mouseout(function(){
  $('.b-icon-triangle-gray-left').css("background","url(imgs/icons.png) -841px -982px no-repeat");
});

// 滚动到下面 出现小视频窗口

$(function(){
    // 监听滚动事件
  $(window).scroll(function(){
    // 获得div的高度

    var h = $(".s_tag-intro").offset().top;
    if($(this).scrollTop()>h && $(this).scrollTop() < h+100){
    // 滚动到指定位置
      $(".fvideo").css("display","block");
      $(".fvideo").draggable();
    }
  });
})

$(function(){
    // 监听滚动事件
  $(window).scroll(function(){
    // 获得div的高度

    var h = $(".tetx-danmu").offset().top;
    if($(this).scrollTop()>h && $(this).scrollTop() < h+100){
    // 滚动到指定位置
      $(".fvideo").css("display","none");
    }
  });
})