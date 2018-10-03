import java.util.HashMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Autorizar {

	public static void main(String[] args) throws JSONException{
		
		String login = "Token_enviado_pelo_suporte";

		/* Substituir pela sua identificação interno do CTe. */
		String ref = "12345";
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";
 		
 		String url = server.concat("v2/cte_os?ref="+ref);
	
		/* Configuração para realizar o HTTP BasicAuth. */
 		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));
		
		/* Aqui são criados as hash's que receberão os dados do CTe. */
		HashMap<String, String> cte = new HashMap<String, String>();
		HashMap<String, String> seguroCarga = new HashMap<String, String>();
		HashMap<String, String> documentosReferenciados = new HashMap<String, String>();
   
		cte.put("bairro_emitente","Sao Cristova");
		cte.put("bairro_tomador","Bacacheri");
		cte.put("cep_emitente","99880077");
		cte.put("cep_tomador","88991188");
		cte.put("cfop","5353");
		cte.put("cnpj_emitente","51916585000125");
		cte.put("cnpj_tomador","51966818092777");
		cte.put("codigo_municipio_emitente","2927408");
		cte.put("codigo_municipio_envio","5200050");
		cte.put("codigo_municipio_fim","3100104");
		cte.put("codigo_municipio_inicio","5200050");
		cte.put("codigo_municipio_tomador","4106902");
		cte.put("codigo_pais_tomador","1058");
		cte.put("complemento_emitente","Andar 19 - sala 23");
		cte.put("data_emissao","2018-06-18T09:17:00");
		cte.put("descricao_servico","Descricao do seu servico aqui");
		cte.put("funcionario_emissor","Nome do funcionario que fez a emissao");
		cte.put("icms_aliquota","17.00");
		cte.put("icms_base_calculo","1.00");
		cte.put("icms_situacao_tributaria","00");
		cte.put("icms_valor","0.17");
		cte.put("indicador_inscricao_estadual_tomador","9");
		cte.put("inscricao_estadual_emitente","12345678");
		cte.put("logradouro_emitente","Aeroporto Internacional de Salvador");
		cte.put("logradouro_tomador","Rua Joao Dalegrave");
		cte.put("modal","02");
		cte.put("municipio_emitente","Salvador");
		cte.put("municipio_envio","Abadia de Goias");
		cte.put("municipio_fim","Abadia dos Dourados");
		cte.put("municipio_inicio","Abadia de Goias");
		cte.put("municipio_tomador","Curitiba");
		cte.put("natureza_operacao","PREST. DE SERV. TRANSPORTE A ESTAB. COMERCIAL");
		cte.put("nome_emitente","ACME LTDA");
		cte.put("nome_fantasia_emitente","ACME");
		cte.put("nome_fantasia_tomador","Nome do tomador do servico aqui");
		cte.put("nome_tomador","NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		cte.put("numero_emitente","S/N");
		cte.put("numero_fatura","1");
		cte.put("numero_tomador","1");
		cte.put("pais_tomador","BRASIL");
		cte.put("quantidade","1.0000");
		cte.put("telefone_emitente","4133336666");
		cte.put("tipo_documento","0");
		cte.put("tipo_servico","6");
		cte.put("uf_emitente","BA");
		cte.put("uf_envio","GO");
		cte.put("uf_fim","MG");
		cte.put("uf_inicio","GO");
		cte.put("uf_tomador","PR");
		cte.put("valor_desconto_fatura","0.00");
		cte.put("valor_inss","0.10");
		cte.put("valor_liquido_fatura","1.00");
		cte.put("valor_original_fatura","1.00");
		cte.put("valor_receber","1.00");
		cte.put("valor_total","1.00");
		cte.put("valor_total_tributos","0.00");
		segurosCarga.put("nome_seguradora","Nome da seguradora aqui");
		segurosCarga.put("numero_apolice","12345");
		segurosCarga.put("responsavel_seguro","4");
		documentosReferenciados.put("data_emissao","2018-06-18");
		documentosReferenciados.put("numero","1");
		documentosReferenciados.put("serie","1");
		documentosReferenciados.put("subserie","1");
		documentosReferenciados.put("valor","1.00");
		
		/* Depois de fazer o input dos dados, são criados os objetos JSON já com os valores das hash's. */
		JSONObject json = new JSONObject (cte);
		JSONObject jsonSegurosCarga = new JSONObject (segurosCarga);
		JSONObject jsonDocumentosReferenciados = new JSONObject (documentosReferenciados);
		
		/* Aqui adicionamos os objetos JSON nos campos da API como array no JSON principal. */
		json.append("segurosCarga", jsonSegurosCarga);
		json.append("documentosReferenciados", jsonDocumentosReferenciados);

		/* É recomendado verificar como os dados foram gerados em JSON e se ele está seguindo a estrutura especificada em nossa documentação.*/
		//System.out.print(json); 
		
		WebResource request = client.resource(url);

		ClientResponse resposta = request.post(ClientResponse.class, json);

		int httpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
		/* As três linhas a seguir exibem as informações retornadas pela nossa API. 
		 * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(httpCode);
		System.out.printf(body);
	}
}