jQuery(document).ready(function(){"use strict";$(window).load(function(){$(".body-wrapper").each(function(){var e=$(".header"),a=e.children(".nav-wrapper"),o=(a.find(".navbar-header"),a.find(".navbar-collapse")),t={nav_top:o.css("margin-top")};$(window).scroll(function(){a.hasClass("bb-fixed-header")&&(0===$(this).scrollTop()||$(this).width()<750)?(a.removeClass("bb-fixed-header").appendTo(e),o.animate({"margin-top":t.nav_top},{duration:100,queue:!1,complete:function(){e.css("height","auto")}})):!a.hasClass("bb-fixed-header")&&$(this).width()>750&&$(this).scrollTop()>e.offset().top+e.height()-parseInt($("html").css("margin-top"),10)&&(e.css("height",e.height()),a.css({opacity:"0"}).addClass("bb-fixed-header"),a.appendTo($("body")).animate({opacity:1}),o.css({"margin-top":"0px"}))})}),$(window).trigger("resize"),$(window).trigger("scroll")}),$(".navbar a.dropdown-toggle").on("click",function(e){var a=$(this).parent().parent();if(!a.hasClass("nav")){var o=$(this).parent(),t=parseInt(a.css("height").replace("px",""),10)/2,s=parseInt(a.css("width").replace("px",""),10)-10;return o.hasClass("open")?o.removeClass("open"):o.addClass("open"),$(this).next().css("top",t+"px"),$(this).next().css("left",s+"px"),!1}}),$(".navbar").width()>1007&&($(".nav .dropdown").on("mouseover",function(){$(this).addClass("open")}),$(".nav .dropdown").on("mouseleave",function(){$(this).removeClass("open")})),$(".searchBox a").on("click",function(){$(".searchBox .dropdown-menu").slideToggle(300),$(".searchBox a i").toggleClass("fa-close").toggleClass("fa-search")});var e=$(".main-slider .inner");e.length>0&&e.each(function(){var e=$(this).parent().data("loop"),a=$(this).parent().data("autoplay"),o=$(this).parent().data("interval")||3e3;$(this).owlCarousel({items:1,loop:e,margin:0,nav:!0,dots:!0,navText:[],autoplay:a,autoplayTimeout:o,autoplayHoverPause:!0,smartSpeed:700})}),$(".rtl .main-slider .inner").owlCarousel({rtl:!0}),jQuery(".fullscreenbanner").revolution({delay:5e3,startwidth:1170,startheight:560,fullWidth:"on",fullScreen:"off",hideCaptionAtLimit:"",dottedOverlay:"twoxtwo",navigationStyle:"preview4",fullScreenOffsetContainer:"",hideTimerBar:"on"}),$(".owl-carousel.commentSlider").owlCarousel({loop:!0,margin:0,autoplay:!1,autoplayTimeout:3e3,autoplayHoverPause:!0,nav:!1,moveSlides:1,smartSpeed:1e3,responsive:{320:{items:1},768:{items:1},992:{items:1}}}),$(".rtl .owl-carousel.commentSlider").owlCarousel({rtl:!0}),$(".owl-carousel.partnersLogoSlider").owlCarousel({loop:!0,margin:28,autoplay:!0,autoplayTimeout:6e3,autoplayHoverPause:!0,nav:!0,dots:!1,smartSpeed:500,responsive:{320:{slideBy:1,items:1},768:{slideBy:1,items:3},992:{slideBy:1,items:4}}}),$(".rtl .owl-carousel.partnersLogoSlider").owlCarousel({rtl:!0}),$(".owl-carousel.testimonialSlider").owlCarousel({loop:!0,margin:0,autoplay:!0,autoplayTimeout:3e3,autoplayHoverPause:!0,nav:!0,dots:!1,moveSlides:1,smartSpeed:1e3,responsive:{320:{items:1},768:{items:1},992:{items:1}}}),$(".rtl .owl-carousel.testimonialSlider").owlCarousel({rtl:!0}),$(".owl-carousel.postSlider").owlCarousel({loop:!0,margin:0,autoplay:!0,autoplayTimeout:3e3,autoplayHoverPause:!0,nav:!0,dots:!1,moveSlides:1,smartSpeed:1e3,responsive:{320:{items:1},768:{items:1},992:{items:1}}}),$(".rtl .owl-carousel.postSlider").owlCarousel({rtl:!0}),$(".select-drop").selectbox(),(new WOW).init(),$(".counter").counterUp({delay:10,time:2e3}),$(window).scroll(function(){$(this).scrollTop()>100?$("#backToTop").css("opacity",1):$("#backToTop").css("opacity",0)}),$('.scrolling  a[href*="#"]').on("click",function(e){e.preventDefault(),e.stopPropagation();var a=$(this).attr("href");$(a).velocity("scroll",{duration:1500,offset:-150,easing:"easeOutExpo",mobileHA:!1})}),$(".video1 img").click(function(){var e='<iframe width="100%" height="300px" src="'+$(this).attr("data-video")+'"></iframe>';$(this).replaceWith(e)}),$(".collapse-nav li a").on("click",function(){$(this).find("i").toggleClass("fa-minus fa-plus")}),$(".progress-bar").each(function(e,a){var o=$(this),t=o.attr("data-percent")||"100";o.attr("data-delay"),o.attr("data-type"),o.hasClass("progress-animated")||o.css({width:"0%"}),o.animate({width:t+"%"},"easeInOutCirc").addClass("progress-animated")})});