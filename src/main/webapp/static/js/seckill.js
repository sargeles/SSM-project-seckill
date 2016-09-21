
//存放主要交互逻辑JS代码
//JavaScript模块化
var seckill={
    //封装秒杀相关ajax的地址
    URL:{
        now:function(){
            return '/seckill/seckill/time/now';
        },
        exposer:function(seckillId){
            return '/seckill/seckill/'+seckillId+'/exposer';
        },
        execution: function (seckillId,md5) {
            return '/seckill/seckill/'+seckillId+'/'+md5+'/execution';
        }
    },
    validatePhone:function(phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        }
        else return false;
    },
    handlerSeckill:function(seckillId,node){
        //处理秒杀逻辑
        node.hide();
        node.html("<button class='btn btn-danger' id='killbtn'>开始秒杀</button>");
        $.post(seckill.URL.exposer(seckillId),{},function(result){
            if(result&&result.success==true){
                var exposer=result['data'];
                if(exposer.exposed==true){
                    //开启秒杀
                    var md5=exposer['md5'];
                    var killURL=seckill.URL.execution(seckillId,md5);
                    //只绑定一次点击事件,防止服务器重复提交请求
                    $("#killbtn").one('click',function(){
                       //执行秒杀请求
                        //1.禁用按钮
                        $(this).addClass('disabled');
                        //2.发送秒杀请求
                        $.post(killURL,{},function(result){
                            if(result&&result.success==true){
                                var killResutl=result['data'];
                                var state=killResutl['state'];
                                var stateInfo=killResutl['stateInfo'];
                                //显示秒杀结果
                                node.html("<span class='label label-success'>"+stateInfo+"</span>");
                            }else{
                                //打印错误
                                console.error(result);
                            }
                        });
                    });
                    //显示节点
                    node.show(300);
                }else{
                    //没有开启秒杀
                    var now=exposer.now;
                    var end=exposer.end;
                    var start=exposer.start;
                    //重新进行计时逻辑，纠正计时
                    seckill.countdown(seckillId,now,start,end);
                }
            }else{
                //ajax请求失败
                console.error(result);
            }
        });
    },
    countdown:function(seckillId,nowTime,startTime,endTime){
        var seckillbox=$("#seckill-box");
        if(nowTime>=endTime){
            //秒杀结束
            seckillbox.html("秒杀结束")
        }else if(nowTime<startTime){

            var killTime=new Date(startTime+1000);
            seckillbox.countdown(killTime,function(event){
                var format=event.strftime("秒杀倒计时：%D天 %H时 %M分 %S秒");
                seckillbox.html(format);
            }).on("finsih.countdown",function(){
                //时间完成后的回掉函数
                //获取秒杀地址控制显示逻辑，执行秒杀操作
                seckill.handlerSeckill(seckillId,seckillbox);
            });
        }else{
            //秒杀开始
            seckill.handlerSeckill(seckillId,seckillbox);
        }
    },
    //详情秒杀逻辑
    deatil:{
        //详情页初始化
        init: function (params) {
            //用户手机验证
            //计时交互
            //在Coookie中查找手机号

            var killPhone= $.cookie("userPhone");
            var startTime=params['startTime'];
            var endTime=params['endTime'];
            var seckillId=params['seckillId'];

            //验证手机号
            if(seckill.validatePhone(killPhone)){

            }else{
                //绑定手机号码
                var killPhoneModal=$("#killPhoneModal");
                killPhoneModal.modal({
                    show:true,  //显示弹出层
                    backdrop:"static",//禁止位置关闭
                    keyboard:false   //键盘事件关闭
                });
            }
            $("#killPhoneBtn").click(function(){
                var inputPhone=$("#killPhoneKey").val();
                if(seckill.validatePhone(inputPhone)){
                    $.cookie("userPhone",inputPhone,{expires:7,path:'/seckill/seckill'});//expires 7天 path 路径
                    //刷新页面
                    window.location.reload();
                }else {
                    $("#killPhoneMessage").hide().html("<lable class='label label-danger'>手机号码错误</lable>").show(300);
                }
            });
            //已经登陆成功
            //计时交互
            $.get(seckill.URL.now(),{},function(result){
                if(result&&result.success==true){
                    var nowTime=result['data'];
                    var nowDate=new Date(nowTime).getTime();
                    //时间判断,计时交互
                    seckill.countdown(seckillId,nowDate,startTime,endTime);
                }else{
                    console.error(result);
                }
            });
        }
    },

};
