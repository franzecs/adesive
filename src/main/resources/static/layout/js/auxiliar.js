(function (factory) {

    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        module.exports = factory(require('jquery'));
    } else {
        factory(jQuery || Zepto);
    }

}(function($) {

    $.fn.maskNumber = function(options) {

        var settings = $.extend({}, $.fn.maskNumber.defaults, options);
        settings = $.extend(settings, options);
        settings = $.extend(settings, this.data());

        this.keyup(function() {
            format(this, settings);
        });
        
        return this;
    };

    $.fn.maskNumber.defaults = {
        thousands: ",",
        decimal: ".",
        integer: false,
    };

    function format(input, settings) {
        var inputValue = input.value;
        inputValue = removeNonDigits(inputValue);
        if (!settings.integer) {
            inputValue = addDecimalSeparator(inputValue, settings);
        }
        inputValue = addThousandSeparator(inputValue, settings);
        inputValue = removeLeftZeros(inputValue);
        applyNewValue(input, inputValue);
    }
    
    function removeNonDigits(value) {
        return value.replace(/\D/g, "");
    }
    
    function addDecimalSeparator(value, settings) {
        value = value.replace(/(\d{2})$/, settings.decimal.concat("$1"));
        value = value.replace(/(\d+)(\d{3}, \d{2})$/g, "$1".concat(settings.thousands).concat("$2"));
        return value;
    }
    
    function addThousandSeparator(value, settings) {
        var totalThousandsPoints = (value.length - 3) / 3;
        var thousandsPointsAdded = 0;
        while (totalThousandsPoints > thousandsPointsAdded) {
            thousandsPointsAdded++;
            value = value.replace(/(\d+)(\d{3}.*)/, "$1".concat(settings.thousands).concat("$2"));
        }
        
        return value;
    }
    
    function removeLeftZeros(value) {
        return value.replace(/^(0)(\d)/g,"$2");
    }
    
    function applyNewValue(input, newValue) {
        if (input.value != newValue) {
            input.value = newValue;
        }
        $(input).trigger('change', input.value);
    }

}));

$('.js-moeda').maskNumber({
			decimal : ',',
			thousands : '.'
})

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

var AW=AW||{};
AW.onSidebarToggleRequest=function(a){
	a.preventDefault(),$(this).blur(),$(".js-sidebar, .js-content").toggleClass("is-toggled")
},
AW.onSearchModalShowRequest=function(a){a.preventDefault(),
	$(".js-search-modal").fadeIn("slow"),
	$("body").addClass("aw-no-scroll"),
	$(".js-search-modal-input").val("").select()},
	
AW.onSearchModalCloseRequest=function(a){a.preventDefault(),
		$(".js-search-modal").hide(),
		$("body").removeClass("aw-no-scroll")},

AW.showLoadingComponent=function(){
			$(".js-loading-overlay").css("display","table").hide().fadeIn("slow")},AW.hideLoadingComponent=function(){$(".js-loading-component").fadeOut("fast")},
AW.initMenu=function(){$(".js-menu > ul > li > a").bind("click",AW.onMenuGroupClick),$(".aw-menu__item .is-active").parents(".aw-menu__item").addClass("is-expanded is-active")},$(function(){AW.init&&AW.init(),AW.initMenu(),$(".js-tooltip").tooltip(),$(".js-sidebar-toggle").bind("click",AW.onSidebarToggleRequest),$(".js-search-modal-trigger-show").bind("click",AW.onSearchModalShowRequest),$(".js-search-modal-close").bind("click",AW.onSearchModalCloseRequest)});
