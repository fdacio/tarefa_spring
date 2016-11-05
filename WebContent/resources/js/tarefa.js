/**
 *  Javacript/JQuery 1.2
 */
$(function() {
	
	$( "#menu" ).menu({items: "> :not(.ui-widget-header)"});
	$(".ui-menu").css("width","200px");
	$(".ui-widget-header").css(".ui-widget-header","0.2em");
	$( "#menu" ).find("a:link").css("text-decoration","none");
	$( "#menu" ).find("span").css("display","block").css("width", "100%");
	$( "#menu" ).find(".user_name").css("color","blue");
	
	$("a.finaliza").click(function(e){
		//alert("Finalizando....");
	});
	
	$(".date-picker").datepicker({
		dateFormat : 'dd/mm/yy',
		showOtherMonths: true,
		selectOtherMonths: true,
	      showOn: "button",
	      buttonImage: "resources/images/calendar.gif",
	      buttonImageOnly: true,
	      buttonText: "Selecione uma data",
	      dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
	      dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
	      dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
	      monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
	      monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
	      nextText: 'Próximo',
	      prevText: 'Anterior'	      
	});	
	$(".date-picker").css("width","100px").css("height","20px").prop("readonly", true);
});

function finalizaTarefa(id){
	$.post("finalizaTarefa",{"id":id},function(resposta){
		$("#tarefa_"+id).html("Finalizada em "+resposta);
	});
}
