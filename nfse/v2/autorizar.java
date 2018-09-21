package v2.NFSe;

import java.util.HashMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class NFSe_autorizar {

	public static void main(String[] args) throws JSONException{
		
		String login = "Token_enviado_pelo_suporte";

		/* Substituir pela sua identificação interna da nota. */
		String ref = "12345";
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";
 		
 		String url = server.concat("v2/nfse?ref="+ref);
 		
 		/* Configuração para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		/* Aqui são criados as hash's que receberão os dados da nota. */
		HashMap<String, String> nfse = new HashMap<String, String>();
		HashMap<String, String> prestador = new HashMap<String, String>();
		HashMap<String, String> tomador = new HashMap<String, String>();
		HashMap<String, String> tomadorEndereco = new HashMap<String, String>();
		HashMap<String, String> servico = new HashMap<String, String>();

		nfse.put("data_emissao", "2018-01-15T17:40:00");
		nfse.put("natureza_operacao", "1");
		prestador.put("cnpj", "51916585000125");
		prestador.put("inscricao_municipal", "123456");
		prestador.put("codigo_municipio", "4128104");
		tomador.put("cpf", "51966818092");
		tomador.put("razao_social", "ACME LTDA");
		tomador.put("email", "email-do-tomador@google.com.br");
		tomadorEndereco.put("bairro", "Jardim America");
		tomadorEndereco.put("cep", "82620150");
		tomadorEndereco.put("codigo_municipio", "4106902");
		tomadorEndereco.put("logradouro", "Rua Paulo Centrone");
		tomadorEndereco.put("numero", "168");
		tomadorEndereco.put("uf", "PR");
		servico.put("discriminacao", "Teste de servico");
		servico.put("aliquota", "3.00");
		servico.put("base_calculo", "1.0");
		servico.put("valor_iss", "0");
		servico.put("iss_retido", "false");
		servico.put("codigo_tributario_municipio", "080101");
		servico.put("item_lista_servico", "0801");
		servico.put("valor_servicos", "1.0");
		servico.put("valor_liquido", "1.0");
		
		/* Depois de fazer o input dos dados, são criados os objetos JSON já com os valores das hash's. */
		JSONObject json = new JSONObject (nfse);
		JSONObject jsonPrestador = new JSONObject (prestador);
		JSONObject jsonTomador = new JSONObject (tomador);
		JSONObject jsonTomadorEndereco = new JSONObject (tomadorEndereco);
		JSONObject jsonServico = new JSONObject (servico);
		
		/* Aqui adicionamos os objetos JSON nos campos da API como array no JSON principal. */
		json.accumulate("prestador", jsonPrestador);
		json.accumulate("tomador", jsonTomador);
		jsonTomador.accumulate("endereco", jsonTomadorEndereco);
		json.accumulate("servico", jsonServico);

		/* É recomendado verificar como os dados foram gerados em JSON e se ele está seguindo a estrutura especificada em nossa documentação.
		System.out.print(json); */
		
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