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

		String url = server.concat("nfe2/autorizar.json?token="+ token +"&ref="+ ref);
		
		// Criamos aqui as hash que receberão os dados da nota.
		HashMap<String, String> nfe = new HashMap<String, String>();
		HashMap<String, String> itens = new HashMap<String, String>();
   
		nfe.put("data_emissao", "2017-08-28T15:00:00-03:00");
		nfe.put("natureza_operacao", "Remessa de Produtos");
		nfe.put("forma_pagamento", "0");
		nfe.put("tipo_documento", "1");
		nfe.put("finalidade_emissao", "1");
		nfe.put("cnpj_emitente", "51916585000125");
		nfe.put("nome_emitente", "ACME LTDA");
		nfe.put("nome_fantasia_emitente", "ACME TESTES");
		nfe.put("logradouro_emitente", "Rua Interventor Manoel Ribas");
		nfe.put("numero_emitente", "1355 ");
		nfe.put("bairro_emitente", "Jardim Gutierres ");
		nfe.put("municipio_emitente", "Campo Mourao");
		nfe.put("uf_emitente", "PR");
		nfe.put("cep_emitente", "87300410");
		nfe.put("telefone_emitente", "44912345678");
		nfe.put("inscricao_estadual_emitente", "101942171617");
		nfe.put("nome_destinatario", "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		nfe.put("cpf_destinatario", "51966818092");
		nfe.put("inscricao_estadual_destinatario", "ISENTO");
		nfe.put("telefone_destinatario", "19912345678");
		nfe.put("logradouro_destinatario", "Rua Leonor Campos");
		nfe.put("numero_destinatario", "29");
		nfe.put("bairro_destinatario", "Swiss Park");
		nfe.put("municipio_destinatario", "Campinas");
		nfe.put("uf_destinatario", "SP");
		nfe.put("pais_destinatario", "Brasil");
		nfe.put("cep_destinatario", "13049555");
		nfe.put("icms_base_calculo", "0");
		nfe.put("icms_valor_total", "0");
		nfe.put("icms_base_calculo_st", "0");
		nfe.put("icms_valor_total_st", "0");
		nfe.put("icms_modalidade_base_calculo", "0");
		nfe.put("icms_valor", "0");
		nfe.put("valor_frete", "19.90");
		nfe.put("valor_seguro", "0");
		nfe.put("valor_total", "79.90");
		nfe.put("valor_produtos", "60.00");
		nfe.put("valor_desconto", "0.00");
		nfe.put("valor_ipi", "0");
		nfe.put("modalidade_frete", "1");
		itens.put("numero_item","128");
		itens.put("codigo_produto","1007");
		itens.put("descricao","Multi Mist 500g");
		itens.put("cfop","6102");
		itens.put("unidade_comercial","un");
		itens.put("quantidade_comercial","2");
		itens.put("valor_unitario_comercial","30");
		itens.put("valor_unitario_tributavel","30");
		itens.put("unidade_tributavel","un");
		itens.put("codigo_ncm","11041900");
		itens.put("valor_frete","19.90");
		itens.put("valor_desconto","0.00");
		itens.put("quantidade_tributavel","2");
		itens.put("valor_bruto","60.00");
		itens.put("icms_situacao_tributaria","103");
		itens.put("icms_origem","0");
		itens.put("pis_situacao_tributaria","07");
		itens.put("cofins_situacao_tributaria","07");
		itens.put("ipi_situacao_tributaria","53");
		itens.put("ipi_codigo_enquadramento_legal","999");
		
		// Depois de fazer o input dos dados, criamos os objetos JSON com os valores das Hash's.
		JSONObject json = new JSONObject (nfe);
		JSONObject JsonItens = new JSONObject (itens);
		
		// Aqui adicionamos o campo "items" como array JSON no objeto JSON principal.
		json.append("items", JsonItens);

		/* Recomendamos que verifique como os dados estão sendo codificados, antes de realizar o envio.
		System.out.print(json); */
		
		WebResource request = client.resource(url);

		ClientResponse resposta = request.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);

		int HttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
		/* As três linhas abaixo imprimem as informações retornadas pela API, aqui o seu sistema deverá 
		   interpretar e lidar com o retorno*/
		System.out.print("HTTP Code: ");
		System.out.print(HttpCode);
		System.out.printf(body);

	}
}
