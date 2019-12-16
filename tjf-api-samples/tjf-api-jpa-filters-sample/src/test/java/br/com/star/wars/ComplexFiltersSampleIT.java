package br.com.star.wars;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.totvs.tjf.api.jpa.CashAccountApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CashAccountApplication.class)
@AutoConfigureMockMvc
public class ComplexFiltersSampleIT {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void WithComplexFilterEqTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"employeeId\":1,\"name\":\"John\"}]}";

		mockMvc.perform(get("/api/v1/empregados?$filter=name eq 'John'").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterNeTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"employeeId\":2,\"name\":\"Mary\"}]}";

		mockMvc.perform(get("/api/v1/empregados?$filter=name ne 'John'").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterGtTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"accountId\":8,\"balance\":\"9000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":1,\"limit\":\"18000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}},{\"accountId\":9,\"balance\":\"9000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":2,\"limit\":\"19000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}}]}";

		mockMvc.perform(get("/api/v1/contas?$filter=balance gt 8000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterGeTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"accountId\":8,\"balance\":\"9000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":1,\"limit\":\"18000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}},{\"accountId\":9,\"balance\":\"9000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":2,\"limit\":\"19000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}}]}";

		mockMvc.perform(get("/api/v1/contas?$filter=limit ge 18000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterLtTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[]}";

		mockMvc.perform(get("/api/v1/contas?$filter=limit lt 10000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterLeTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"accountId\":0,\"balance\":\"0.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":1,\"limit\":\"10000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}},{\"accountId\":1,\"balance\":\"1000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":2,\"limit\":\"11000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}}]}";

		mockMvc.perform(get("/api/v1/contas?$filter=balance le 1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterLogicalAndGroupTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"accountId\":8,\"balance\":\"9000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":1,\"limit\":\"18000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}}]}";

		mockMvc.perform(get("/api/v1/contas?$filter=(balance gt 8000) and (limit eq 18000)").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterNotTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"accountId\":0,\"balance\":\"0.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":1,\"limit\":\"10000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}},{\"accountId\":1,\"balance\":\"1000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":2,\"limit\":\"11000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}}]}";

		mockMvc.perform(get("/api/v1/contas?$filter=not (limit gt 11000)").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
	@Test
	public void WithComplexFilterOrTest() throws Exception {
		String expectedResult = "{\"hasNext\":false,\"items\":[{\"accountId\":0,\"balance\":\"0.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":1,\"limit\":\"10000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}},{\"accountId\":9,\"balance\":\"9000.00\",\"balanceCurrencyCode\":\"BRL\",\"FK_id\":2,\"limit\":\"19000.00\",\"limitCurrencyCode\":\"BRL\",\"employee\":{}}]}";

		mockMvc.perform(get("/api/v1/contas?$filter=(limit lt 11000) or (limit gt 18000)").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(expectedResult));
	}
	
}
