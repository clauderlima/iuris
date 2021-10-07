var Debitum = Debitum || {};

Debitum.TituloCadastroRapido = (function() {
	
	var idHistorico;
	
	function TituloCadastroRapido() {
		this.tipoContato = $('#tipoContato');
		this.responsavelContato = $('#responsavelContato');
		this.status = $('#status');
		this.modal = $('#modalLancarInformacao');
		this.mensagemContato = $('#mensagemContato');
		this.botaoSalvar = $('#btn-salvar');
		this.botaoInformacao = $('.informacao');
		//this.informacaoValor = this.botaoInformacao.attr('xx');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		//this.inputNomeTitulo = $('#nomeTitulo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-titulo');
	}
	
	TituloCadastroRapido.prototype.iniciar = function() {
		//this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		//this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
		this.botaoInformacao.on('click', function () {
			 botaoInformacaoClick($(this).attr('xx'));
		});
	}
	
	function botaoInformacaoClick(id) {
		idHistorico = id;
		console.log("id: " + idHistorico);
	}
	
	function onModalShow() {
		console.log("id modal: " + idHistorico);
		this.mensagemContato.focus();
	}
	
	function onModalClose() {
		this.inputNomeTitulo.val('');
		this.containerMensagemErro.addClass('d-none');
		this.form.find('#nomeTitulo').removeClass('is-invalid');
	}
	
/*	function validaForm() {
		console.log("Validando Formulário");
		var tipoContato = $('#tipoContato').val();
		var status = $('#status').val();
		var mensagemContato = $('#mensagemContato').val();
		
		console.log("Tipo contato: " + tipoContato);
		console.log("Prioridade: " + status);
		console.log("Mensagem contato: " + mensagemContato);
	}*/
	
	function onBotaoSalvarClick() {
		//validaForm();
		console.log("URL: " + this.url);
		console.log("Id: " + idHistorico);
		console.log("Responsavel contato: " + this.responsavelContato.val());
		console.log("Tipo contato: " + this.tipoContato.val());
		console.log("Prioridade: " + this.status.val());
		console.log("Mensagem contato: " + this.mensagemContato.val());
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ id: idHistorico, responsavelContato: { "id" : this.responsavelContato.val() }, tipoContato: this.tipoContato.val(), status: this.status.val(), mensagemContato: this.mensagemContato.val() }),
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
		location.reload();
	}
	
	return TituloCadastroRapido;
	
}());

$(function() {
	var tituloCadastroRapido = new Debitum.TituloCadastroRapido();
	tituloCadastroRapido.iniciar();
});
