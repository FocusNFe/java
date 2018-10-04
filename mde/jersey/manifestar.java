import java.util.HashMap;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Manifestar {

	public static void main(String[] args) {
		
		String login = "Token_enviado_pelo_Suporte";
		String chave = "Chave_de_identifica��o_da_NFe";
		
		/* Para ambiente de produ��o use a vari�vel abaixo:
		String server = "https://api.focusnfe.com.br/"; */
		String server = "http://homologacao.acrasnfe.acras.com.br/";
		String url = server.concat("v2/nfes_recebidas/"+chave+"/manifesto");
			
		/* Aqui criamos um hashmap para receber a chave "tipo" e o valor que pode ser: ciencia, confirmacao, desconhecimento ou nao_realizada. */		
		HashMap<String, String> tipoManifestacao = new HashMap<String, String>();
		tipoManifestacao.put("tipo", "nao_realizada");
		
		/* Caso escolha o tipo "nao_realizada", � preciso informar o campo/chave "justificativa".
		 * TipoManifestacao.put("justificativa", "Informe aqui a sua justificativa do motivo da n�o realiza��o da opera��o."); */
			
		/* Criamos um objeto JSON para receber a hash com os dados esperado pela API. */
		JSONObject json = new JSONObject(TipoManifestacao);
		
		/* Configura��o para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request =  client.resource(url);
		ClientResponse resposta = request.post(ClientResponse.class, json);
		int httpCode = resposta.getStatus(); 
		String body = resposta.getEntity(String.class);
		
		/* As tr�s linhas abaixo imprimem as informa��es retornadas pela API. 
		 * Aqui o seu sistema dever� interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(httpCode);
		System.out.printf(body);
	}
}