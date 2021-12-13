/**
 * 
 */
package br.edu.ifrn.sga.contactservice.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Lucas Mariano
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ContatoControllerTest {
	
	private static final String ENDPOINT_API_CONTATO = "/api/contato";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("deve invalidar a tentativa de contato caso a request não tenha body")
	void testRequestSemBody() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
						.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isBadRequest())
			.andDo(handler -> {
				System.out.println(handler.getResponse().getContentAsString());
			});
	}
	
	@Test
	@DisplayName("deve invalidar a tentativa de contato caso o content type da request não seja JSON")
	void testRequestContentTypeDiferenteDeJson() throws Exception {
		Map<String, String> mapa = criarMapaRequisicaoValida();
		this.mockMvc
				.perform(MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
						.content(montarPayloadJson(mapa)))
				.andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType()).andDo(handler -> {
					System.out.println(handler.getResponse().getContentAsString());
				});
	}

	@DisplayName("deve invalidar a tentativa de contato com o body do request vazio.")
	@ParameterizedTest(name = "[{index}] body inválido: {0}")
	@EmptySource
	@ValueSource(strings = {"   ", "\t", "\n"})
	void testRequestComBodyVazio(String body) throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(handler -> {
			System.out.println(handler.getResponse().getContentAsString());
		});
	}
	
	@DisplayName("deve invalidar a tentativa de contato caso seja informado um nome de remetente inválido")
	@ParameterizedTest(name = "[{index}] nome inválido: {0}")
	@NullAndEmptySource
	@ValueSource(strings = {"   ", "\t", "\n", "ab", "Dino da Silva Santos Oliveira Almeida Bruno Francisco Bernardo Constantino Ezequiel Moreira Sauro Filho"})
	void testTentativaContatoComNomeVazio(String nome) throws Exception {
		Map<String, String> mapa = criarMapaRequisicaoValida();
		mapa.put("nome", nome);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(montarPayloadJson(mapa)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(handler -> {
			System.out.println(handler.getResponse().getContentAsString());
		});
	}
	
	@DisplayName("deve invalidar a tentativa de contato caso seja informado um email de remetente inválido")
	@ParameterizedTest(name = "[{index}] email invalido: {0}")
	@NullAndEmptySource
	@ValueSource(strings = {"\t", "\n", "dino.sauro", "dino.sauro@", "@dino.sauro", "dino.sauro.dino.sauro.dino.sauro.dino.sauro.dino.sauro.dino.sauro.dino.sauro.dino.sauro.dino@mail.it"})
	void testTentativaContatoComEmailInvalido(String email) throws Exception {
		Map<String, String> mapa = criarMapaRequisicaoValida();
		mapa.put("email", email);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(montarPayloadJson(mapa)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(handler -> {
			System.out.println(handler.getResponse().getContentAsString());
		});
	}
	
	@DisplayName("deve invalidar a tentativa de contato caso não seja informado um assunto válido")
	@ParameterizedTest(name = "[{index}] assunto inválido: {0}")
	@NullAndEmptySource
	@ValueSource(strings = {"   ", "\t", "\n", "ab", "O assunto do contato é um texto, porém não pode ser muito extenso. Não deve passar de 100 caracteres."})
	void testTentativaContatoComAssuntoInvalido(String assunto) throws Exception {
		Map<String, String> mapa = criarMapaRequisicaoValida();
		mapa.put("assunto", assunto);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(montarPayloadJson(mapa)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(handler -> {
			System.out.println(handler.getResponse().getContentAsString());
		});
	}
	
	@DisplayName("deve invalidar a tentativa de contato caso não seja informada uma mensagem")
	@ParameterizedTest(name = "[{index}] mensagem inválida: {0}")
	@NullAndEmptySource
	@ValueSource(strings = {"   ", "\t", "\n", "Mensagem "})
	void testTentativaContatoComMensagemInvalida(String mensagem) throws Exception {
		Map<String, String> mapa = criarMapaRequisicaoValida();
		mapa.put("mensagem", mensagem);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(montarPayloadJson(mapa)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(handler -> {
			System.out.println(handler.getResponse().getContentAsString());
		});
	}
	
	@DisplayName("deve invalidar a tentativa de contato caso o conteúdo da mensagem seja muito longo")
	@Test
	void testTentativaContatoComMensagemMuitoLonga() throws Exception {
		final String mensagemLonga = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse at accumsan odio. Vivamus ligula dolor, "
				+ "commodo id enim id, vulputate porttitor metus. Etiam lorem massa, eleifend et convallis ac, dapibus vitae ipsum. In hac habitasse "
				+ "platea dictumst. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed id metus fringilla, "
				+ "ornare tortor a, lobortis justo. Duis tincidunt risus id feugiat semper. Fusce sapien magna, maximus a metus et, viverra eleifend "
				+ "leo. Quisque eget ornare mi, nec luctus massa. In maximus augue quis gravida placerat.Curabitur dictum malesuada est, a posuere "
				+ "orci sollicitudin sed. Morbi consequat, nibhy ut hendrerit pretium, turpis sem mollis diam, in cursus est mauris in nibh. Sed "
				+ "cursus ligula non tempor viverra. Morbi aliquet ac tellus at commodo. Nulla facilisi. Quisque laoreet lectus eget pulvinari "
				+ "sollicitudin. Cras viverra id libero vitae tempore. Integer sit amet lorem dapibus, egestas metus ut, imperdiet justo quisque.";
		Map<String, String> mapa = criarMapaRequisicaoValida();
		mapa.put("mensagem", mensagemLonga);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(montarPayloadJson(mapa)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(handler -> {
			System.out.println(handler.getResponse().getContentAsString());
		});
	}
	
	@DisplayName("deve validar a tentativa de contato")
	@Test
	void testTentativaContatoComTodasInformacoesValidas() throws Exception {
		Map<String, String> mapa = criarMapaRequisicaoValida();

		this.mockMvc.perform(
				MockMvcRequestBuilders.post(ContatoControllerTest.ENDPOINT_API_CONTATO)
				.contentType(MediaType.APPLICATION_JSON)
				.content(montarPayloadJson(mapa)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(handler -> {
			System.out.println(handler.getResponse().getContentAsString());
		});
	}
	
	/**
	 * Método responsável por transformar um mapa em um JSON.
	 * 
	 * @param mapa um {@link Map} contendo todas as informações necessárias para
	 *             realizar um contato, ou seja, contendo: nome e email do
	 *             contatante, assunto e conteúdo do contato.
	 * @return um {@link String} no formado JSON
	 * @throws NullPointerException caso alguma chave contida no mapa for
	 *                              <code>null</code>
	 * @throws JSONException        caso não consiga transformar o JSON resultante
	 *                              do mapa em um {@link String}.
	 */
	private String montarPayloadJson(Map<String, String> mapa) throws JSONException {
		String payload = new JSONObject(mapa).toString(2);
		System.out.println(payload);
		return payload;
	}
	
	/**
	 * Método responsável por instanciar e popular um {@link Map} contendo todas as
	 * informações necessárias e válidas para realizar um contato, ou seja,
	 * contendo: nome e email do contatante, assunto e conteúdo do contato.
	 * 
	 * @return um {@link Map}
	 */
	private Map<String, String> criarMapaRequisicaoValida() {
		final String mensagem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse at accumsan odio. Vivamus ligula dolor, "
				+ "commodo id enim id, vulputate porttitor metus. Etiam lorem massa, eleifend et convallis ac, dapibus vitae ipsum. In hac habitasse "
				+ "platea dictumst. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed id metus fringilla, "
				+ "ornare tortor a, lobortis justo. Duis tincidunt risus id feugiat semper. Fusce sapien magna, maximus a metus et, viverra eleifend "
				+ "leo. Quisque eget ornare mi, nec luctus massa. In maximus augue quis gravida placerat.Curabitur dictum malesuada est, a posuere "
				+ "orci sollicitudin sed. Morbi consequat, nibhy ut hendrerit pretium, turpis sem mollis diam, in cursus est mauris in nibh. Sed "
				+ "cursus ligula non tempor viverra. Morbi aliquet ac tellus at commodo. Nulla facilisi. Quisque laoreet lectus eget pulvinar "
				+ "sollicitudin. Cras viverra id libero vitae tempore. Integer sit amet lorem dapibus, egestas metus ut, imperdiet justo quisque.";
		Map<String, String> mapa = new LinkedHashMap<>();
		mapa.put("nome", "Dino da Silva Sauro");
		mapa.put("email", "dino.sauro@email.com");
		mapa.put("assunto", "Teste de Email");
		mapa.put("mensagem", mensagem);
		return mapa;
	}
	
}
