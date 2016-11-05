<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="id" required="true"%>
<%@ attribute name="value" required="false"%>
<%@ attribute name="image" required="false"%>


<input id="${id}" name="${id}" value="${value}" readonly="readonly" size="10"/>
<script>
	$("#${id}").datepicker({
		dateFormat : 'dd/mm/yy',
		showOtherMonths: true,
		selectOtherMonths: true,
	      showOn: "button",
	      buttonImage: "${image}",
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
</script>