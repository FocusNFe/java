import java.util.HashMap;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Main {

	public static void main(String[] args) throws JSONException{
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */

		String server = "http://homologacao.acrasnfe.acras.com.br/";

		String token =  "Token_enviado_pelo_suporte";

		// Substituir pela sua identificação interna da nota.
		String ref = "12345";

		Client client = Client.create();

		String url = server.concat("nfce.json?token="+ token +"&ref="+ ref);
		
		// Criamos aqui as hash que receberão os dados da nota.
		HashMap<String, String> nfce = new HashMap<String, String>();
		HashMap<String, String> itens = new HashMap<String, String>();
		HashMap<String, String> formasPagamento = new HashMap<String, String>();

		nfce.put("data_emissao", "2017-08-28T17:20:00-03:00");
		nfce.put("consumidor_final", "1");
		nfce.put("modalidade_frete", "9");
		nfce.put("natureza_operacao", "Venda ao Consumidor");
		nfce.put("tipo_documento", "1");
		nfce.put("finalidade_emissao", "1");
		nfce.put("presenca_comprador", "1");
		nfce.put("indicador_inscricao_estadual_destinatario", "9");
		nfce.put("cnpj_emitente", "51916585000125");
		nfce.put("cpf_destinatario", "");
		nfce.put("id_estrangeiro_destinatario", "1234567");
		nfce.put("nome_destinatario", "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		nfce.put("informacoes_adicionais_contribuinte", "Documento emitido por ME ou EPP optante pelo Simples Nacional nao gera direito a credito fiscal de ICMS lei 123/2006.");
		nfce.put("valor_produtos", "1.0000");
		nfce.put("valor_desconto", "0.0000");
		nfce.put("valor_total", "1.0000");
		nfce.put("forma_pagamento", "0");
		nfce.put("icms_base_calculo", "0.0000");
		nfce.put("icms_valor_total", "0.0000");
		nfce.put("icms_base_calculo_st", "0.0000");
		nfce.put("icms_valor_total_st", "0.0");
		nfce.put("icms_modalidade_base_calculo", "3");
		nfce.put("valor_frete", "0.0");
		itens.put("numero_item", "1");
		itens.put("unidade_comercial", "PC");
		itens.put("unidade_tributavel", "PC");
		itens.put("codigo_ncm", "94019090");
		itens.put("codigo_produto", "Div.13350000");
		itens.put("descricao", "NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		itens.put("cfop", "5102");
		itens.put("valor_unitario_comercial", "1.0000000000");
		itens.put("valor_unitario_tributavel", "1.0000000000");
		itens.put("valor_bruto", "1.0000");
		itens.put("quantidade_comercial", "1.0000");
		itens.put("quantidade_tributavel", "1.0000");
		itens.put("quantidade", "1.0000");
		itens.put("icms_origem", "0");
		itens.put("icms_base_calculo", "1.00");
		itens.put("icms_modalidade_base_calculo", "3");
		itens.put("valor_frete", "0.0");
		itens.put("valor_outras_despesas", "0.0");
		itens.put("icms_situacao_tributaria", "102");
		formasPagamento.put("forma_pagamento", "99");
		formasPagamento.put("valor_pagamento", "1.0000");
		
		// Depois de fazer o input dos dados, criamos os objetos JSON com os valores das Hash's.
		JSONObject json = new JSONObject (nfce);
		JSONObject JsonItens = new JSONObject (itens);
		JSONObject JsonPagamento = new JSONObject (formasPagamento);
		
		// Aqui adicionamos estes campos como array JSON no objeto JSON principal.
		json.append("items", JsonItens);
		json.append("formas_pagamento", JsonPagamento);

		/* Recomendamos que verifique como os dados estão sendo codificados, antes de realizar o envio.
		System.out.print(json); */

		WebResource request = client.resource(url);

		ClientResponse resposta = request.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);

		int HttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);

		/* As três linhas abaixo imprimem as informações retornadas pela API, aqui o seu sistema deverá interpretar e lidar com o retorno*/
		System.out.print("HTTP Code: ");
		System.out.print(HttpCode);
		System.out.printf(body);
	}
}
