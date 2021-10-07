var SPMaskBehavior = function (val) {
	  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
	},
	spOptions = {
	  onKeyPress: function(val, e, field, options) {
	      field.mask(SPMaskBehavior.apply({}, arguments), options);
	    }
	};

$('.js-phone-number').mask(SPMaskBehavior, spOptions);

$(document).ready(function(){
	  $('.js-date').mask('00/00/0000');
	  $('.js-time').mask('00:00:00');
	  $('.js-date_time').mask('00/00/0000 00:00:00');
	  $('.js-cep').mask('00.000-000');
	  $('.js-cpf').mask('000.000.000-00', {reverse: true});
	  $('.js-cnpj').mask('00.000.000/0000-00', {reverse: true});
	  $('.js-rg').mask('000.000.000.000.000', {reverse: true});
	  $('.js-money').mask('000.000.000.000.000,00', {reverse: true});
	  $('.js-money2').mask("#.##0,00", {reverse: true});
	});