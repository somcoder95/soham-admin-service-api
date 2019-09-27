package com.idexcel.sohamadminservice.controller;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idexcel.sohamadminservice.dto.LenderPatchDto;
import com.idexcel.sohamadminservice.dto.LenderPostDto;
import com.idexcel.sohamadminservice.dto.LenderPutDto;
import com.idexcel.sohamadminservice.dto.LendersGetDto;
import com.idexcel.sohamadminservice.entity.Lender;
import com.idexcel.sohamadminservice.service.LenderService;

@RestController
@RequestMapping("soham-api")
public class LenderController {
	@Autowired
	private LenderService service;
	@Autowired
    private ModelMapper modelMapper;
	
	@PostMapping("/lenders")
	public  ResponseEntity<LenderErrorResponse> createLender(@RequestBody LenderPostDto theLender)
	{   if(theLender.getName()==null||theLender.getName().trim().isEmpty()||theLender.getAddress()==null||theLender.getPrimaryContact()==null)
	{
		LenderErrorResponse error=new LenderErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Bad Request");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.BAD_REQUEST);
	           
	}
		Lender lender=convertToLender(theLender);
		service.saveLender(lender);
		LenderErrorResponse error=new LenderErrorResponse();
		error.setStatus(HttpStatus.CREATED.value());
		error.setMessage("Lender Created");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/lenders")
     public List<LendersGetDto> getAllLenders() {
        final List<Lender> lenders = service.getAllLenders();
        Type targetListType = new TypeToken<List<LendersGetDto>>() {
            private static final long serialVersionUID = 1L;}.getType();
        return modelMapper.map(lenders, targetListType);
	}
	
	@PutMapping("/lenders/{lenderId}")
	public ResponseEntity<LenderErrorResponse> updateLender(@PathVariable(value="lenderId")  String Id, @RequestBody LenderPutDto theLender)
	{   LenderErrorResponse error=new LenderErrorResponse();
		if(!(theLender.getStatus()=="ACTIVE"||theLender.getStatus()=="SUSPENDED"))
	{
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Bad Request");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
		Lender lender = service.findLenderById(Id);
		modelMapper.map(theLender, lender);
		
		lender.setUpdatedDate(Calendar.getInstance().getTime());
		
		service.updateLender(lender);
		error.setStatus(HttpStatus.NO_CONTENT.value());
		error.setMessage("Lender Updated");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping("/lenders/{lenderId}")
	public ResponseEntity<LenderErrorResponse> deleteLender(@PathVariable(value="lenderId")  String Id)
	{
		LenderErrorResponse error=new LenderErrorResponse();
		
		service.removeLender(Id);
		error.setStatus(HttpStatus.NO_CONTENT.value());
		error.setMessage("Lender Deleted");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/lenders/{lenderId}")
	public  Lender getLenderById(@PathVariable(value="lenderId")  String Id)
			{
		      return service.getLenderById(Id);
			}
	
	@PatchMapping("/lenders/{lenderId}")
	public  ResponseEntity<LenderErrorResponse> updateLenderStatus(@PathVariable(value="lenderId")  String Id, @RequestBody LenderPatchDto theLender)
	{   LenderErrorResponse error=new LenderErrorResponse();
		if(!(theLender.getStatus().equals("ACTIVE")||theLender.getStatus().equals("SUSPENDED")))
		{
			
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage("Bad Request");
			error.setTimeStamp(System.currentTimeMillis());
			return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.BAD_REQUEST);
		}
		Lender lender = service.findLenderById(Id);
		modelMapper.map(theLender, lender);
		lender.setUpdatedDate(Calendar.getInstance().getTime());
		
		service.updateLender(lender);
		error.setStatus(HttpStatus.NO_CONTENT.value());
		error.setMessage("Lender Updated");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(path="/lenders/{lenderId}", method = RequestMethod.HEAD)
	public ResponseEntity<LenderErrorResponse> LenderExists(@PathVariable(value="lenderId")  String Id)
	{   LenderErrorResponse error=new LenderErrorResponse();
		service.findLenderById(Id);
		error.setStatus(HttpStatus.OK.value());
		error.setMessage("Lender Exists");
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<LenderErrorResponse>(error,HttpStatus.OK);
	
	}
	
	@GetMapping("/healthCheck")
	public String healthStatus() {
		return "soham admin service is running.";
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
