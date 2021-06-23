//$(function(){
//    $(".g_langbotb a").click(function(){
//        var url = $(this).attr("data-url");
//        if(url){
//            $.ajax({
//                url:'/Cn/Index/setCk',
//                type:'POST',
//                dataType:'json',
//                success:function(res){
//                    window.open(url);
//                }
//            })
//        }
//    })
//})
function setCookieFn(){
    $.ajax({
        url:'/Cn/Index/setCk',
        type:'POST',
        dataType:'json',
        success:function(res){
        }
    })
}
/*---------------------------------------------start john-----------------------------------------------------*/
//鍐檆ookies锛屼竴涓皬鏃惰繃鏈� 
function setCookie(name, value) {
    var exp = new Date();
    exp.setTime(exp.getTime() + 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=/";
}
//璇诲彇cookies 
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
//鍒犻櫎cookies 
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 60 * 60 * 1000);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString() + ";path=/";
}

function isAndroidAndIos() {
    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;
    if (isAndroid) {
        return 1; // Android
    } else {
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
        if (isiOS) {
            return 2; // isiOS
        } else {
            return 1;
        }
    }
}
// 涓嬭浇楠岃瘉
function viDownload(login, filepath, path) {
    if (filepath == 1) {
        return false;
    }else {
        // 杞崲oss杩滅▼鏂囦欢閾炬帴
        var domain = window.location.host;
        var newFilepath = filepath.split('/Public/');
        if (!newFilepath[1]) {
            var newFile = filepath.split(domain);
            filepath = newFile[1];
            // 2020-05-07  - new oss filepath
            var ossFilePath = filepath.split('/www-global.oss.rigol.com/');
            if (ossFilePath[1]) {
                filepath = 'https://oss-int.rigol.com/' + ossFilePath[1];
            }
        }
    }
    var url = location.href;
    if (path && path != 'undefined') {
        // 淇濆瓨浜у搧椤甸渶瑕侀€変腑鐨勫弬鏁�
        setCookie("path", path);
    }
    if (login == 1) {
        var terminal = isAndroidAndIos();
        $.ajax({
            url: '/Cn/Separate/viDownload.html',
            data: {url: '/Cn/Login/login', 'loginurl': url},
            type: 'POST',
            dataType: 'json',
            success: function (res) {
                if (res.code == 1) {
                    if (terminal == 2) {
                        location = filepath;
                    } else {
                        window.open(filepath);
                    }
                } else {
                    if (terminal == 2) {
                        location = res.url;
                    } else {
                        window.open(res.url);
                    }
                }
            }
        })
    } else {
        window.open(filepath);
    }
}

// 鑾峰彇鏄惁鐧诲綍
function determineWhetherLogin() {
    $.ajax({
        url: '/Cn/Separate/determineWhetherLogin.html',
        data: {key: 1},
        type: 'POST',
        dataType: 'json',
        success: function (res) {
            //console.log(res);
            if (res.code == 1) {
                // pc 
                $(".mc_unlogin").removeClass('act');
                $(".mc_alreadylogin").addClass('act');
                $(".mc_usel_name").text(res.name);
                // ph
                $(".g_deng").addClass('act');
                $(".mc_usel_name").text(res.name);
            } else {
                // pc 
                $(".mc_unlogin").addClass('act');
                $(".mc_alreadylogin").removeClass('act');
                // ph
                $(".g_deng").removeClass('act');
            }
            $(".g_deng").css({"display": "block"});
        }
    })
}

// 鑾峰彇閫夋嫨鐨勫姣斾骇鍝佷釜鏁�
function getSelProductLength() {
    var product_list_catid = $("#product_list_box").attr("data-catid");
    // console.log(product_list_catid);
    $.ajax({
        url: '/Cn/Separate/getSelProductLength.html',
        data: {catid: product_list_catid},
        type: 'POST',
        dataType: 'json',
        success: function (res) {
            // console.log(res);
            if (res.code == 1) {
                $(".product_length").text(res.count);
                $(".product_list_length").text(res.type_count);
            } else {
                $(".product_length").text(0);
            }
        }
    })
}
getSelProductLength();
determineWhetherLogin();
queryData = {
    url: "/Cn/Index/search",
    query: function (wd) {
        var base = new this.Base64();
        window.open(this.url + "/wd/" + base.encode(wd));
    },
    Base64: function Base64() {

        // private property
        _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

        // public method for encoding
        this.encode = function (input) {
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;
            input = _utf8_encode(input);
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output = output +
                        _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                        _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
            }
            return output;
        }

        // public method for decoding
        this.decode = function (input) {
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while (i < input.length) {
                enc1 = _keyStr.indexOf(input.charAt(i++));
                enc2 = _keyStr.indexOf(input.charAt(i++));
                enc3 = _keyStr.indexOf(input.charAt(i++));
                enc4 = _keyStr.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = _utf8_decode(output);
            return output;
        }

        // private method for UTF-8 encoding
        _utf8_encode = function (string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }

            }
            return utftext;
        }

        // private method for UTF-8 decoding
        _utf8_decode = function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while (i < utftext.length) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    }
}

// 鐢熸垚闅忔満鍞竴鏁�
function randomWord(randomFlag, min, max) {
    var str = "",
            range = min,
            arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

    // 闅忔満浜х敓
    if (randomFlag) {
        range = Math.round(Math.random() * (max - min)) + min;
    }
    for (var i = 0; i < range; i++) {
        pos = Math.round(Math.random() * (arr.length - 1));
        str += arr[pos];
    }
    return str;
}
$(function () {
    // 浜у搧娣诲姞瀵规瘮
    $(".l_protop8p.mc_jrdb").click(function () {
        // 鐢熸垚cookie
        var ip = getCookie('ip');
        if (!ip) {
            ip = randomWord(true, 3, 32);
            var exp = new Date();
            exp.setTime(exp.getTime() + 360 * 60 * 60 * 1000);
            document.cookie = 'ip' + "=" + escape(ip) + "; domain=rigol.com; expires=" + exp.toGMTString() + ";path=/";
        }

        var _self = $(this);
        var count = $("#product_length").text();
        var listCount = $("#product_list_length").text();
        var catid = $(this).attr("data-catid");
        var product_id = $(this).attr("data-id");
        var key = $(this).attr("data-key");
        // var layerIndex = layer.load();


        $.ajax({
            url: '/Cn/Separate/addComparisonProduct.html',
            data: {
                catid: catid,
                product_id: product_id,
                key: key
            },
            type: 'POST',
            dataType: 'json',
            success: function (res) {

                // layer.close(layerIndex);
                if (res.code == 1) {
                    _self.css({"color": "#c6c4c4"});
                    _self.prev(0).find("img").attr("src", '/Public/Cn/images/4_duibi_act.png');
                    var len = parseInt(count) + 1;
                    var listLen = parseInt(listCount) + 1;
                    $(".product_length").html(len);
                    $(".product_list_length").html(listLen);
//                    layer.msg(res.msg, {
//                        icon: 6
//                    });
                } else {
//                    layer.msg(res.msg, {
//                        icon: 5
//                    });
                }
            }
        })

        $(".mc_gwcbox").show();
    })




    // 鎵嬫満绔悳绱�
    $("#phHeadSearch").click(function () {
        var keyword = $("#ph_txt").val();
        if (/\S/.test(keyword)) {
            queryData.query(keyword);
        }
    })
    // pc 绔悳绱�
    $("#buts").click(function () {
        var keyword = $("input[name='pc_txt']").val();
        if (/\S/.test(keyword)) {
            queryData.query(keyword);
        }
    })
    $("input[name='pc_txt']").keydown(function (e) {
        if (e.keyCode == 13) {
            var keyword = $(this).val();
            if (/\S/.test(keyword)) {
                queryData.query(keyword);
            }
        }
    })
})
// $("html,body").animate({scrollTop: 10000}, 700);
/*----------------------------------------------end john------------------------------------------------------*/
// Start Grace

// // ph瀵艰埅
// $("#menuph").click(function(){
//     $(this).find(".point").toggleClass("active");
//     $(".xialaph").slideToggle();
//     $(".phonemeng").toggleClass('active');
//     $("body,html").animate({
//         scrollTop:0
//     },500);
// });
// // ph瀵艰埅浜岀骇
// $(".xialaph  h4").click(function(){
//     $(this).siblings(".ul2").slideToggle();
//     $(this).parent().parent().siblings().find(".ul2").slideUp();
//     $(this).toggleClass("active");
//     $(this).parent().parent().siblings().find("h4,h5").removeClass('active');
// });
// /*ph瀵艰埅涓夌骇*/
// $(".xialaph h5").click(function(){
//     $(this).siblings(".ul3").slideToggle();
//     $(this).parent().siblings().find(".ul3").slideUp();
//     $(this).toggleClass("active");
//     $(this).parent().siblings().find("h4,h5").removeClass('active');
// });
// 杩斿洖椤堕儴
var sTop = document.body.scrollTop || document.documentElement.scrollTop;
;
$('.g_piaopt6').click(function () {
    var termId = setInterval(function () {
        sTop -= 50;
        if (sTop <= 0) {
            clearInterval(termId);
        }
        window.scrollTo(0, sTop);
    }, 1);
});
$('.g_piaopt6').click(function () {
    $("html,body").animate({
        scrollTop: 0
    }, 500);
});
// pc涓嬫媺
$('.g_navli').hover(function () {
    $(this).find('.g_naver').stop().slideDown(280);
}, function () {
    $(this).find('.g_naver').stop().slideUp(280);
});
$(".g_header").stop().addClass('act');
// pc瀵艰埅
// $(function () {
//     if ($(window).scrollTop() > 0) {
//         $(".g_header").stop().addClass('act');
//     } else {
//         $(".g_header").stop().removeClass('act');
//     }
// })

// $(window).on('scroll', function () {
//     if ($(window).scrollTop() > 0) {
//         $(".g_header").stop().addClass('act');
//     } else {
//         $(".g_header").stop().removeClass('act');
//     }
// });
// 搴曢儴鍒嗕韩浜岀淮鐮�
$('.g_ftlook2top').hover(function () {
    $(this).parents('.g_ftlook2').find('.ace_ewm').fadeIn();
}, function () {
    $(this).parents('.g_ftlook2').find('.ace_ewm').fadeOut();
});
$('.g_ftlook3').hover(function () {
    $(this).find('.ace_ewm').fadeIn();
}, function () {
    $(this).find('.ace_ewm').fadeOut();
});
$('.g_ftlook11').hover(function () {
    $(this).find('.ace_ewm').fadeIn();
}, function () {
    $(this).find('.ace_ewm').fadeOut();
});
// 渚ц竟
// $(window).on('scroll', function () {
//     if ($(window).scrollTop() > 0) {
//         $(".g_piao").stop().addClass('act');
//     } else {
//         $(".g_piao").stop().removeClass('act');
//     }
// });
// 璐拱bug
$('.g_otherfr2 .g_otherfr21, .g_deng2').mouseenter(function () {
    $(this).parents('body').find('.g_bug').stop().css('right', '0');
});
$('.g_bugtop').mouseleave(function () {
    $(this).parents('.g_bug').stop().css('right', '-100%');
});

$('.g_otherfr2 .g_otherfr21, .g_deng2').click(function () {
    $(this).parents('body').find('.g_bug').stop().css('right', '0');
});
$('.g_bugclose').click(function () {
    $(this).parents('.g_bug').stop().css('right', '-100%');
});
$(window).on('scroll', function () {
    if ($(window).scrollTop() > 0) {
        $("body").stop().addClass('act');
    } else {
        $("body").stop().removeClass('act');
    }
});
//鎼滅储
$(".g_other1").click(function () {
    $(".mengcen").fadeIn(300);
});
$(".guanbi").click(function () {
    $(".mengcen").fadeOut(300);
});
// 浜у搧
// $(window).on('scroll',function(){
//     if ($(window).scrollTop() > $('.g_proding').offset().top){
//         $(".g_pro").stop().addClass('act');
//     } else {
//         $(".g_pro").stop().removeClass('act');
//     }
// });
// 浜у搧鍚搁《
$(window).on('scroll', function () {
    if ($(".g_proding").length > 0) {
        if ($(window).scrollTop() > $('.g_proding').offset().top - 66) {
            $(".g_pro").stop().addClass('act');
        } else {
            $(".g_pro").stop().removeClass('act');
        }
    }
});
// 浜у搧杩涘叆涓嬩竴灞�
$('.g_pronext').click(function () {
    $("html,body").animate({
        scrollTop: $('.g_proding').offset().top - 66
    }, 700);
})

// 浜у搧鎵嬫満绔�
$('.g_pronavphtop').click(function () {
    $(this).parents('.g_pronavph').find('.g_pronavphbot').stop().slideToggle();
});
// 閿氱偣
// function yxtop(){
//var test = (window.location.href).split('/tp/');
// if(!isNaN(test[1])){
//    $("html,body").animate({scrollTop: $('[yxdatop-pag="'+test[1]+'"]').offset().top - 72}, 700);
// }
// };
// 渚ц竟-鑱旂郴
$('.g_piaopt2').hover(function () {
    $(this).stop().addClass('act');
}, function () {
    $(this).stop().removeClass('act');
});
$('.g_piaopt2close').click(function () {
    $(this).parents('.g_piaopt2').stop().removeClass('act');
});
// 渚ц竟-鍏虫敞
$('.g_piaopt2top').hover(function () {
    $(this).parents('.g_piaopt1').find('.g_piaopt1bot').stop().fadeIn();
}, function () {
    $(this).parents('.g_piaopt1').find('.g_piaopt1bot').stop().fadeOut();
});
// 渚ц竟-鍒嗕韩
$('.g_piaopt3').hover(function () {
    $(this).stop().toggleClass('act');
});
// 璇█鍒囨崲
$(function () {
    if($(window).width()>1200){
        $(".g_otherfl>.g_other2").mouseenter(function () {
            $(".g_langbot").stop().slideDown();
        });
        $(".g_otherfl>.g_other2").mouseleave(function(e){
            $(".g_langbot").stop().slideUp();
        });
        $("body").click(function(){
            $(".g_langbot").stop().slideUp();
        })
    }else{
        $(".g_langtop").click(function (e) {
            $(".g_langbot").stop().slideToggle();
            e.stopPropagation();
        });
        $(".g_langbot").click(function(e){
            e.stopPropagation();
        })
        $("body").click(function(){
            $(".g_langbot").stop().slideUp();
        })
    }
})

// 涓嬫媺婊氬姩鏉�
$(function () {
    var scrollInertiaNum;
    if (/firefox/.test(navigator.userAgent.toLowerCase())) {
        scrollInertiaNum = 200;
    } else {
        scrollInertiaNum = 200;
    }
    $(".ace_gun").mCustomScrollbar({
        theme: 'dark',
        scrollInertia: scrollInertiaNum,
        horizontalScroll: false,
        axis: "y",
    });
});
// ph涓嬫媺
$('.point').click(function () {
    $('.ace_first').stop().css('right', '0');
    $('body').css('height', '100vh');
    // $('html').css('overflow', 'hidden');

});
$('.ace_first .ace_firsttu').click(function () {
    $('.ace_first').stop().css('right', '-100%');
    $('body').css('height', 'auto');
    // $('html').css('overflow', 'auto');


});
// 9.23 ace_firstlifr img
$('.ace_first .ace_firstli').click(function () {
    $(this).parents('.ace_xial').find('.ace_sec').css('right', '0');
    $(this).parents('.ace_xial').find('.ace_first').css('display', 'none');
});



$('.ace_firstwz').click(function () {
    $(this).parents('.ace_xial').find('.ace_sec').css('right', '-100%');
    $(this).parents('.ace_xial').find('.ace_first').css('display', 'block');
});
$('.ace_firsttu').click(function () {
    $('.ace_sec').stop().css('right', '-100%');
});
$('.ace_sec .ace_firsttu').click(function () {
    $('.ace_first').stop().css('right', '-100%');
    $('.ace_sec').stop().css('right', '-100%');
    $('.ace_first').css('display', 'block');
});
$('.ace_firstli').click(function () {
    var pag = $(this).attr('data-num');
    $('.ace_sec').siblings('.ace_sec').stop().css('right', '-100%');
    $('.ace_sec[data-num="' + pag + '"]').stop().css('right', '0');
});
$(function () {
    $(".ace_sec a").click(function (e) {
        e.stopPropagation();
    })
})

$(".ace_firstli.hasnosec a").click(function(e){
    e.stopPropagation();
})

// ph璇█涓嬫媺
$('.ace_langphtop').click(function () {
    $(this).parents('.ace_langph').find('.ace_langphbot').stop().slideToggle();
});
// 鍐呴〉瀵艰埅ph
$(".btner").click(function () {
    $(this).toggleClass('act');
    $(this).parent().siblings(".btn_group1").find(".btner").removeClass('act');
    $(this).siblings('.uldown').slideToggle(300);
    $(this).parent().siblings(".btn_group1").find(".uldown").slideUp(200);
});
$(document).click(function () {
    $(".btner").siblings(".uldown").slideUp(200);
});
$(".btner").click(function (event) {
    event.stopPropagation();
});
$(window).scroll(function () {
    if ($(window).scrollTop() > 0) {
        $('.header').addClass('act');
    } else {
        $('.header').removeClass('act');
    }
})
// pc涓嬫媺浜у搧
$('.g_naver2').hover(function () {
    var pag = $(this).attr('data-num');
    $('.g_naverflbottuimg').siblings('.g_naverflbottuimg').stop().removeClass('g_naverflbottuimgact');
    $('.g_naverflbottuimg[data-num="' + pag + '"]').stop().addClass('g_naverflbottuimgact');
    $(this).stop().addClass('act');
    $(this).siblings().stop().removeClass('act');
});


// 9.19
$(function () {
    $(".mc_d1_inputbox_dd").click(function (e) {
        e.stopPropagation();
    });
    $("body").click(function () {
        $(".ace_fuwubot").stop().slideUp();
    });

    // $(".mc_main,mc_d1_inputbox").click(function(){
    //     $(".ace_fuwubot").stop().slideUp();
    //     $(".uldown").stop().slideUp();
    // })
    // $(".mc_d1_inputbox_dd input,.mc_d3_inputbox_tarea textarea").focus(function(){
    //     $(".ace_fuwubot").stop().slideUp();
    // })
})
// 鎵嬫満绔笁涓渾
$(function () {
    $(".mc_phpiaoli").not(":last-child").click(function () {
        $(".mc_phpiao_modal").stop().fadeIn();
        $(this).find(".mc_phpiao_xl").stop().fadeIn();
        $(this).siblings().find(".mc_phpiao_xl").stop().fadeOut();
    });
    $(".mc_phpiao_modal").click(function () {
        $(this).stop().fadeOut();
        $(".mc_phpiao_xl").stop().fadeOut();
    });
    // input鎻愮ず
    $(".mc_d1_inputbox_dd input,.mc_d1_inputbox_dd textarea").each(function () {
        var valu = $(this).parents(".mc_d1_inputbox_dd").siblings(".mc_d1_inputbox_dt").text();
        val = valu.replace("*", "");
        $(this).attr("placeholder", "璇疯緭鍏�" + val)

        $("#type").attr("placeholder", "璇烽€夋嫨鏈嶅姟绫诲瀷")
        $("#accessories").attr("placeholder", "璇疯緭鍏ラ厤浠跺悕绉�")
        $("#info").attr("placeholder", "璇疯緭鍏ユ晠闅滄弿杩板唴瀹�")

    })

    // 9.30
    // 澶撮儴鐢ㄦ埛淇℃伅涓嬫媺
    if ($(window).width() > 1200) {
        $(".mc_user_select").hover(
                function () {
                    $(this).addClass("on");
                    $(".mc_usel_bd").stop().slideDown();
                },
                function () {
                    $(this).removeClass("on");
                    $(".mc_usel_bd").stop().slideUp();
                }
        );
        $(".mc_unlogin").hover(
                function () {
                    $(".mc_uxl_bd").stop().slideDown();
                },
                function () {
                    $(".mc_uxl_bd").stop().slideUp();
                }
        );
    } else {
        $(".g_deng2").click(function (e) {
            e.stopPropagation()
            $(this).find(".mc_user_select").toggleClass("on");
            $(this).find(".mc_usel_bd").stop().slideToggle();
        });

        $(".g_deng1").click(function (e) {
            e.stopPropagation()
            $(this).find(".mc_uxl_bd").stop().slideToggle();
        });

        $("body").click(function () {
            $(".g_deng1").find(".mc_uxl_bd").stop().slideUp();
            $(".g_deng2").find(".mc_usel_bd").stop().slideUp();
            $(this).find(".mc_user_select").removeClass("on");

        })
    }


})


// 10.9
$(function () {
    // if ($(window).width() < 768) {

    //     gwcShow();

    //     $(".selected_ss").click(function () {
    //         gwcShow();
    //     })

    // }

    // 璐墿杞over娴忚
    // if($(window).width()>1200){
    //     $(".mc_gwcbox").hover(
    //         function(){
    //             $(this).find(".mc_gwc_xl").stop().slideDown();
    //         },
    //         function(){
    //             $(this).find(".mc_gwc_xl").stop().slideUp();
    //         }
    //     )
    // }
})

// function gwcShow() {
//     var num = $(".selected_ss.act").attr("data-num");
//     if (num == 0) {
//         $(".mc_gwcbox").stop().show();
//     } else {
//         $(".mc_gwcbox").stop().hide();
//     }
// }

//璐墿杞﹂绐楃Щ鍔ㄧ鏄剧ず
$(function () {
    if ($(window).width() < 768 && $(".g_proding").length > 0) {
        qrcode();
        $(window).scroll(function () {
            qrcode();
        })

        $(".g_pronavphtop").click(function (e) {
            e.stopPropagation();
        })

        $("body").click(function () {
            $(".g_pronavphbot").stop().slideUp();
        })
    }

    function qrcode() {
        if ($(window).scrollTop() < $(".g_proding").offset().top - 70) {
            $(".mc_gwcbox").css({
                position: "absolute",
                top: "120px",
                bottom: "unset",
            })
        } else if ($(window).scrollTop() < $(".g_foot").offset().top - $(".mc_gwcbox").height() -
                370) {
            $(".mc_gwcbox").css({
                position: "fixed",
                top: "190px",
                bottom: "unset",
            })
        } else {

            $(".mc_gwcbox").css({
                position: "absolute",
                top: "unset",
                bottom: 30,
            })
        }
    }
})