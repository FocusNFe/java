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

		String url = server.concat("nfse?token="+ token +"&amp;ref="+ ref);
		
		// Criamos aqui as hash que receberão os dados da nota.
		HashMap<String, String> nfse = new HashMap<String, String>();
		HashMap<String, String> prestador = new HashMap<String, String>();
		HashMap<String, String> tomador = new HashMap<String, String>();
		HashMap<String, String> TomadorEndereco = new HashMap<String, String>();
		HashMap<String, String> servico = new HashMap<String, String>();

		nfse.put("data_emissao", "2017-08-30T10:50:00-03:00");
		nfse.put("natureza_operacao", "1");
		prestador.put("cnpj", "51916585000125");
		prestador.put("inscricao_municipal", "123456");
		prestador.put("codigo_municipio", "4128104");
		tomador.put("cpf", "51966818092");
		tomador.put("razao_social", "ACME LTDA");
		tomador.put("email", "email-do-tomador@google.com.br");
		TomadorEndereco.put("bairro", "Jardim America");
		TomadorEndereco.put("cep", "80000000");
		TomadorEndereco.put("codigo_municipio", "4128104");
		TomadorEndereco.put("logradouro", "Rua Paulo Centrone");
		TomadorEndereco.put("numero", "168");
		TomadorEndereco.put("uf", "PR");
		servico.put("discriminacao", "Teste de servico");
		servico.put("aliquota", "3.00");
		servico.put("base_calculo", "1.0");
		servico.put("valor_iss", "0.03");
		servico.put("iss_retido", "false");
		servico.put("codigo_tributario_municipio", "8640202");
		servico.put("item_lista_servico", "4.03");
		servico.put("valor_servicos", "1.0");
		servico.put("valor_liquido", "1.0");
		
		// Depois de fazer o input dos dados, criamos os objetos JSON com os valores das Hash's.
		JSONObject json = new JSONObject (nfse);
		JSONObject JsonPrestador = new JSONObject (prestador);
		JSONObject JsonTomador = new JSONObject (tomador);
		JSONObject JsonTomadorEndereco = new JSONObject (TomadorEndereco);
		JSONObject JsonServico = new JSONObject (servico);
		
		// Diferente das outras notas, aqui não criamos arrays JSON, mas sim Objetos JSON.
		json.accumulate("prestador", JsonPrestador);
		json.accumulate("tomador", JsonTomador);
		JsonTomador.accumulate("endereco", JsonTomadorEndereco);
		json.accumulate("servico", JsonServico);

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
