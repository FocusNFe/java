import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class ConsultarTodosManifestos {

	public static void main(String[] args) {

		String login = "Token_enviado_pelo_Suporte";
		String cnpj = "CNPJ_da_sua_empresa";
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
		String server = "https://homologacao.focusnfe.com.br/";
		String url = server.concat("v2/nfes_recebidas?cnpj="+cnpj);
		
		/* Configuração para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request =  client.resource(url);
		ClientResponse resposta = request.get(ClientResponse.class);
		int httpCode = resposta.getStatus(); 
		String body = resposta.getEntity(String.class);
		
		/* As três linhas abaixo imprimem as informações retornadas pela API. 
		 * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(httpCode);
		System.out.printf(body);
	}
}
