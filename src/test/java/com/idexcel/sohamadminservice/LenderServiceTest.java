package com.idexcel.sohamadminservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.idexcel.sohamadminservice.dao.LenderRepository;
import com.idexcel.sohamadminservice.dto.LenderPostDto;
import com.idexcel.sohamadminservice.dto.addressDto;
import com.idexcel.sohamadminservice.dto.primaryContactDto;
import com.idexcel.sohamadminservice.entity.Lender;
import com.idexcel.sohamadminservice.service.LenderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SohamAdminServiceApplication.class)
@WebAppConfiguration
public class LenderServiceTest {
	@MockBean 
	LenderRepository LenderDAO;
	
	@Autowired
	LenderService  service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Test
	public void saveLenderTest( ) throws Exception
	{
		LenderPostDto lender = new LenderPostDto();
        lender.setName("Comerica Bank");
        lender.setAddress(new addressDto("ABM", "Alexandria", "VA", "USA", "2175"));
        lender.setPrimaryContact(new primaryContactDto("Alex Singh", "as@idexcel.net", "762-245-2017"));
        Lender full_lender=convertToLender(lender);
        when(LenderDAO.findByName(full_lender.getName())).thenReturn(full_lender);
		when(LenderDAO.save(full_lender)).thenReturn(full_lender);
	}
	
	@Test
	public void findValidLenderById() throws Exception
	{
		when(LenderDAO.findByid((String) any())).thenReturn(new Lender());
		
	}
	
	@Test
	public void findInvalidLenderById() throws Exception
	{
		when(LenderDAO.findByid((String) any())).thenReturn(null);
		
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
