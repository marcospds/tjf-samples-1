package br.com.star.wars.habitants;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarWarsHabitantsApplication.class)
@AutoConfigureMockMvc
public class SchemaHabitantsIT {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void tatooineHabitantsTest() throws Exception {
		String expectedResult = "[{\"id\":\"anakin\",\"name\":\"Anakin Skywalker\",\"gender\":\"male\"},{\"id\":\"luke\",\"name\":\"Luke Skywalker\",\"gender\":\"male\"},{\"id\":\"han\",\"name\":\"Han Solo\",\"gender\":\"male\"}]";

		mockMvc.perform(post("/api/v1/habitants").header("X-Planet", "tatooine").contentType(MediaType.APPLICATION_JSON)
				.content("[\n" + "    {\n" + "        \"id\": \"anakin\",\n"
						+ "        \"name\": \"Anakin Skywalker\",\n" + "        \"gender\": \"male\"\n" + "    },\n"
						+ "    {\n" + "        \"id\": \"luke\",\n" + "        \"name\": \"Luke Skywalker\",\n"
						+ "        \"gender\": \"male\"\n" + "    },\n" + "    {\n" + "        \"id\": \"han\",\n"
						+ "        \"name\": \"Han Solo\",\n" + "        \"gender\": \"male\"\n" + "    }\n" + "]"))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));

		mockMvc.perform(get("/api/v1/habitants").header("X-Planet", "tatooine").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}

	@Test
	public void alderaanHabitantsTest() throws Exception {
		String expectedResult = "[{\"id\":\"leia\",\"name\":\"Leia Organa\",\"gender\":\"female\"}]";

		mockMvc.perform(post("/api/v1/habitants").header("X-Planet", "alderaan").contentType(MediaType.APPLICATION_JSON)
				.content("[\n" + "    {\n" + "        \"id\": \"leia\",\n" + "        \"name\": \"Leia Organa\",\n"
						+ "        \"gender\": \"female\"\n" + "    }\n" + "]"))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));

		mockMvc.perform(get("/api/v1/habitants").header("X-Planet", "alderaan").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}

	@Test
	public void bespinHabitantsTest() throws Exception {
		String expectedResult = "[{\"id\":\"lando\",\"name\":\"Lando Calrissian\",\"gender\":\"male\"},{\"id\":\"dengar\",\"name\":\"Dengar, The Bounty Hunter\",\"gender\":\"male\"}]";

		mockMvc.perform(
				post("/api/v1/habitants").header("X-Planet", "bespin").contentType(MediaType.APPLICATION_JSON)
						.content("[\n" + "    {\n" + "        \"id\": \"lando\",\n"
								+ "        \"name\": \"Lando Calrissian\",\n" + "        \"gender\": \"male\"\n"
								+ "    },\n" + "    {\n" + "        \"id\": \"dengar\",\n"
								+ "        \"name\": \"Dengar, The Bounty Hunter\",\n"
								+ "        \"gender\": \"male\"\n" + "    }\n" + "]"))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));

		mockMvc.perform(get("/api/v1/habitants").header("X-Planet", "bespin").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}

}
