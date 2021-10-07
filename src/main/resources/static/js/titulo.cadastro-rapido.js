var Debitum = Debitum || {};

Debitum.TituloCadastroRapido = (function() {
	
	function TituloCadastroRapido() {
		this.modal = $('#modalCadastroRapidoTitulo');
		this.botaoSalvar = $('#btn-salvar');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeTitulo = $('#nomeTitulo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-titulo');
	}
	
	TituloCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeTitulo.focus();
	}
	
	function onModalClose() {
		this.inputNomeTitulo.val('');
		this.containerMensagemErro.addClass('d-none');
		this.form.find('#nomeTitulo').removeClass('is-invalid');
	}
	
	function onBotaoSalvarClick() {
		console.log("url: " + this.url);
		var nomeTitulo = this.inputNomeTitulo.val().trim();
		console.log("Nome: " + nomeTitulo );
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nomeTitulo: nomeTitulo }),
			error: onErroSalvandoTitulo.bind(this),
			success: onTituloSalvo.bind(this)
		});
	}
	
	function onErroSalvandoTitulo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('d-none');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('#nomeTitulo').addClass('is-invalid');
	}
	
	function onTituloSalvo(titulo) {
		var comboTitulo = $('#titulo');
		comboTitulo.append('<option value=' + titulo.id + '>' + titulo.nomeTitulo + '</option>');
		comboTitulo.val(titulo.id);
		this.modal.modal('hide');
	}
	
	return TituloCadastroRapido;
	
}());

$(function() {
	var tituloCadastroRapido = new Debitum.TituloCadastroRapido();
	tituloCadastroRapido.iniciar();
});
