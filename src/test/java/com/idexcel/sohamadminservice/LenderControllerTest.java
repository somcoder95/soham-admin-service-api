package com.idexcel.sohamadminservice;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexcel.sohamadminservice.dao.LenderRepository;
import com.idexcel.sohamadminservice.dto.LenderPostDto;
import com.idexcel.sohamadminservice.dto.addressDto;
import com.idexcel.sohamadminservice.dto.primaryContactDto;
import com.idexcel.sohamadminservice.entity.Lender;
import com.idexcel.sohamadminservice.exception.LenderNotFoundException;
import com.idexcel.sohamadminservice.service.LenderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SohamAdminServiceApplication.class)
@WebAppConfiguration
public class LenderControllerTest {
	
	
	protected MockMvc mockMvc;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@MockBean 
	LenderService theLenderService;
	
	
	
	@Test
	public void postLenderTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();

        LenderPostDto lender = new LenderPostDto();
        lender.setName("Comerica Bank");
        lender.setAddress(new addressDto("ABM", "Alexandria", "VA", "USA", "2175"));
        lender.setPrimaryContact(new primaryContactDto("Alex Singh", "as@idexcel.net", "762-245-2017"));
        Lender full_lender=convertToLender(lender);

		when(theLenderService.saveLender(full_lender)).thenReturn("123");
		RequestBuilder request = MockMvcRequestBuilders.post("/soham-api/lenders").content(mapper.writeValueAsString(lender)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
		
	
	}
	
	
	@Test
    public void HealthStatus() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/soham-api/healthCheck"))
                .andExpect(status().isOk());
    }
	
	@Test
	public void getLenderById() throws Exception {
		when(theLenderService.findLenderById((String) any())).thenReturn(new Lender());
		mockMvc.perform( MockMvcRequestBuilders
				.get("/soham-api/lenders/1234")).andExpect(status().isOk());
	}
	
	@Test
	public void getLenderByIdFail() throws Exception {
		when(theLenderService.findLenderById((String) any())).thenThrow(LenderNotFoundException.class);
		mockMvc.perform( MockMvcRequestBuilders
				.get("/soham-api/lenders/4567")).andExpect(status().isOk());
	}
	
	
	
	
	
	
	
	private Lender convertToLender(LenderPostDto postDto)
	{
		Lender lender = modelMapper.map(postDto, Lender.class);
		lender.setStatus("ACTIVE"); 
		lender.setCreatedBy("Soham G");
		lender.setCreatedDate(Calendar.getInstance().getTime());
		lender.setUpdatedBy("Soham G");
		  lender.setUpdatedDate(Calendar.getInstance().getTime());
		 return lender;
		
	}

	
	
}
