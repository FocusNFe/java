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

		/* Substituir pela sua identifica��o interna da nota. */
		String ref = "12345";
		
		/* Para ambiente de produ��o use a vari�vel abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";
 		
 		String url = server.concat("v2/nfse?ref="+ref);
 		
 		/* Configura��o para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		/* Aqui s�o criados as hash's que receber�o os dados da nota. */
		HashMap<String, String> nfse = new HashMap<String, String>();
		HashMap<String, String> prestador = new HashMap<String, String>();
		HashMap<String, String> tomador = new HashMap<String, String>();
		HashMap<String, String> TomadorEndereco = new HashMap<String, String>();
		HashMap<String, String> servico = new HashMap<String, String>();

		nfse.put("data_emissao", "2018-01-15T17:40:00");
		nfse.put("natureza_operacao", "1");
		prestador.put("cnpj", "51916585000125");
		prestador.put("inscricao_municipal", "123456");
		prestador.put("codigo_municipio", "4128104");
		tomador.put("cpf", "51966818092");
		tomador.put("razao_social", "ACME LTDA");
		tomador.put("email", "email-do-tomador@google.com.br");
		TomadorEndereco.put("bairro", "Jardim America");
		TomadorEndereco.put("cep", "82620150");
		TomadorEndereco.put("codigo_municipio", "4106902");
		TomadorEndereco.put("logradouro", "Rua Paulo Centrone");
		TomadorEndereco.put("numero", "168");
		TomadorEndereco.put("uf", "PR");
		servico.put("discriminacao", "Teste de servico");
		servico.put("aliquota", "3.00");
		servico.put("base_calculo", "1.0");
		servico.put("valor_iss", "0");
		servico.put("iss_retido", "false");
		servico.put("codigo_tributario_municipio", "080101");
		servico.put("item_lista_servico", "0801");
		servico.put("valor_servicos", "1.0");
		servico.put("valor_liquido", "1.0");
		
		/* Depois de fazer o input dos dados, s�o criados os objetos JSON j� com os valores das hash's. */
		JSONObject json = new JSONObject (nfse);
		JSONObject JsonPrestador = new JSONObject (prestador);
		JSONObject JsonTomador = new JSONObject (tomador);
		JSONObject JsonTomadorEndereco = new JSONObject (TomadorEndereco);
		JSONObject JsonServico = new JSONObject (servico);
		
		/* Aqui adicionamos os objetos JSON nos campos da API como array no JSON principal. */
		json.accumulate("prestador", JsonPrestador);
		json.accumulate("tomador", JsonTomador);
		JsonTomador.accumulate("endereco", JsonTomadorEndereco);
		json.accumulate("servico", JsonServico);

		/* � recomendado verificar como os dados foram gerados em JSON e se ele est� seguindo a estrutura especificada em nossa documenta��o.
		System.out.print(json); */
		
		WebResource request = client.resource(url);

		ClientResponse resposta = request.post(ClientResponse.class, json);

		int HttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
		/* As tr�s linhas a seguir exibem as informa��es retornadas pela nossa API. 
		 * Aqui o seu sistema dever� interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(HttpCode);
		System.out.printf(body);
	}
}