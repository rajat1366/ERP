(function($){
    "use strict"; // Start of use strict
    $(document).ready(function() {

      //SELECT
      

      //Shortcode Banner
      $('.kt-banner').each(function(){
        var minHeight = $(this).data('height'),
          bgImg = $(this).data('background'),
          linkColor = $(this).data('colorlink'),
          colorBdAfter = $(this).data('colorafter'),
          colorBorder = $(this).data('colorborder');
        $(this).find('.bg-image').css({
          'background-image':'url('+bgImg+')',
          'min-height':minHeight+'px'
        });
        if(linkColor!=''){
          $(this).find('.link-banner').css("color",linkColor);
        }
        if(colorBdAfter!=''){
          $(this).find('.border-after').css("border-color",colorBdAfter);
        }
        if(colorBorder!=''){
          $(this).css("border-color",colorBorder);
        }
      });

      // OWL CAROUSEL
      init_carousel();

      //ACORDION SINGLE PRODUCT
      if($('.hm-accordion').length > 0){
          $('.hm-accordion').accordion({ 
              icons: { "header": "fa fa-caret-down", "activeHeader": "fa fa-caret-down" },
              collapsible: true,
              heightStyle:"content"
          })
      }

      //PROGRESS BAR
      $('.item-progressbar').each(function(){
          var $percentSkill = $(this).attr('data-percent');
          $(this).find('.progress-percent').html($percentSkill+'%')
          $(this).find('.progressbar-width').animate({
            'width':$percentSkill+'%'
          },6000);
      });

      //SLIDE PRICE WIDGET
      $('.price_slider_wrapper').each(function(){
          var _min = $(this).find('.price_slider_amount input#min_price').data('min');
          var _max = $(this).find('.price_slider_amount input#max_price').data('max');
          $(this).find( ".price_slider" ).slider({
            range: true,
            min: _min,
            max: _max,
            values: [ 10, 100 ],
            slide: function( event, ui ) {
              $( ".price_label .from" ).text( "$" + ui.values[ 0 ]);
              $( ".price_label .to" ).text( "$" + ui.values[ 1 ] );
            }
          });
          $(this).find( ".price_label .from" ).text( "$" + $( ".price_slider" ).slider( "values", 0 ));
          $(this).find( ".price_label .to" ).text( "$" + $( ".price_slider" ).slider( "values", 1 ));
      });

      //SLIDE PRODUCT DETAIL
      $(window).load(function() {
        $('.kt-single-product .product-image').each(function(){
            $(this).find('#carousel-thumb').flexslider({
              animation: "slide",
              controlNav: false,
              animationLoop: false,
              slideshow: false,
              itemWidth: 120,
              itemMargin: 5,
              asNavFor: '#main-slide'
            });
            $(this).find('#main-slide').flexslider({
              animation: "slide",
              controlNav: false,
              animationLoop: false,
              slideshow: false,
              sync: "#carousel-thumb"
            });
        })
      });

      //PAGE CHECKOUT
      $('.woo-info .showlogin').on('click',function(){
          $('.custommers-checkout .login').slideToggle(500);
      });
      $('.woo-info .showcoupon').on('click',function(){
          $('.custommers-checkout .coupon').slideToggle(500);
      });
      $('.checkout-payment input[type="radio"]').each(function(){
        var $this = $(this);
        if($this.is(':checked')){
          $(this).closest('.payment-method').find('.payment-desc').css('display','block')
        }
        $this.on( 'click', function() {
          $('.payment-method').removeClass('active');
          $('.payment-method .payment-desc').slideUp(300);
          $(this).closest('.payment-method').addClass('active');
          $(this).closest('.payment-method').find('.payment-desc').slideDown(500);
        });
      });

      //POST AUDIO
      $('.post-audio-html5').each(function(){
        var $linkAudio = $(this).find('.audio-wraper').attr('data-audio');
        $("#jquery_jplayer_1").jPlayer({
          ready: function (event) {
            $(this).jPlayer("setMedia", {
              free:true,
              mp3: $linkAudio
            });
          },
          swfPath: "js",
          supplied: "mp3, wav, ogg, all",
          useStateClassSkin: true,
          smoothPlayBar: true,
          globalVolume: true,
          keyEnabled: true
        });
      })

      //HOME SLIDE
      $('.item-homeslide').each(function(){
        var bgImg = $(this).data('background');
        $(this).css({
          'background-image':'url('+bgImg+')'
        });
      });

      //MENU CATEGORY
      $('.block-cats').each(function(){
        $(this).find('.togole-menu').on('click',function(){
          $(this).parent().toggleClass('hide-menu');
          $(this).parent().find('.menu-vertical').slideToggle(500);
        });
      });

      //FUNFACT
      $('.kt-funfact').each(function(){
        var count_element = $(this).find('.number').attr('data-number');
        if (count_element != '') {
          $(this).find('.number').countTo({
            from: 0,
            to: count_element,
            speed: 2500,
                refreshInterval: 50,
          })
        };
      });

      //full height
      js_height_full();

      //Cart Header
      $('.kt-shopping-cart .icon-cart').on('click',function(){
          $(this).parent().find('#shopping-cart-head').slideToggle(500)
      });

      //Langue & Currency
      $('.kt-language,.kt-currency').each(function(){
        $(this).find('.toggle-dropdown').on('click',function(){
          $(this).parent().find('.dropdown-menu').slideToggle(500);
        });
      });

       //MENU DROPDOWN
      var ts_is_mobile = (Modernizr.touch) ? true : false;
      if (ts_is_mobile === true){
        $('.kt-mainmenu .navigation .menu-parent > a .caret').on('click',function(e){
            var $this = $(this);
            var thisLi = $this.closest('li');
            var thisUl = thisLi.closest('ul');
            var thisA = $this.closest('a');
            if ( thisLi.is('.sub-menu-open') ) {
                thisLi.find('> .sub-menu').stop().slideUp('fast');
                thisLi.removeClass('sub-menu-open').find('> a').removeClass('active');
            }
            else{
                thisUl.find('> li.sub-menu-open > .sub-menu').stop().slideUp('fast');
                thisUl.find('> li.sub-menu-open').removeClass('sub-menu-open');
                thisUl.find('> li > a.active').removeClass('active');
                thisLi.find('> .sub-menu').stop().slideDown('fast');
                thisLi.addClass('sub-menu-open').find('> a').addClass('active');
            }
            thisUl.closest('.kt-mainmenu').trigger('focus');
            e.preventDefault();
            e.stopPropagation();
        });
      }else{
        $('.kt-mainmenu .navigation .menu-parent').hover(function(){
              $(this).addClass('sub-menu-open');
            }, function(){
              $(this).removeClass('sub-menu-open'); 
            });
      }
      //MENU MOBILE
      $('.togole-menu-mobile').on('click',function(){
          $(this).toggleClass('active');
          $('.kt-mainmenu .navigation').slideToggle(500);
      });

      //Tab
      $( ".kt-tab" ).each(function(){
          $(this).tabs();
      });

      //Tab over
      var _event;
      if (ts_is_mobile === true){
          _event="click";
      }else{
        _event="mouseover";
      }
      $('.kt-tab-over').each(function(){
          $(this).tabs({
             event: _event
          });
      })

      //FORM SEARCH HEADER 2
      $('.header-style2 .hm-form-search .form-search .button-search').on('click',function(){
          var _valueSearch = $(this).closest('.form-search').find('input[type="text"]').val();
          $(this).closest('.form-search').toggleClass('show-input');
          if(_valueSearch == '') {
              return false;
          };
      })

      //SEARCH POPUP
      $('.kt-search-head .togole-search').on('click',function(){
        $(this).parent().find('.kt-search-box').fadeIn(500);
      });
      $('.kt-search-box .close-search, .kt-search-box .overlay').on('click',function(){
        $('.kt-search-head .kt-search-box').fadeOut(500);
      });

      //SCROLL UP
      $('.scroll-up .scroll-bt').on('click',function(){
          $('html, body').animate({scrollTop : 0},800);
          return false;
      });

      //FITVITS
      // $('.main-wrapper').fitVids();

    });
    $(window).on("resize", function() {
        //full height
        js_height_full();
    });
    /* ---------------------------------------------
     Owl carousel
     --------------------------------------------- */
    function init_carousel(){
        $('.owl-carousel').each(function(){
          var config = $(this).data();
          config.navText = ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'];
          var animateOut = $(this).data('animateout');
          var animateIn = $(this).data('animatein');
          if(typeof animateOut != 'undefined' ){
            config.animateOut = animateOut;
          }
          if(typeof animateIn != 'undefined' ){
            config.animateIn = animateIn;
          }
          var owl = $(this);
          owl.owlCarousel(config);
        });
    }
    /* ---------------------------------------------
     Height Full
    --------------------------------------------- */
    function js_height_full(){
        (function($){
            var heightoff = 0;
            // if($('#wpadminbar').length){
            //     heightoff = $('#wpadminbar').outerHeight();
            // }
            var heightSlide = $(window).outerHeight() - heightoff;
            $(".full-height").css("height",heightSlide);
            $(".min-fullheight").css("min-height",heightSlide);
        })(jQuery);
    }
})(jQuery); // End of use strict