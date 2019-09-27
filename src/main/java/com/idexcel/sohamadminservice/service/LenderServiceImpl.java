package com.idexcel.sohamadminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idexcel.sohamadminservice.dao.LenderRepository;
import com.idexcel.sohamadminservice.entity.Lender;
import com.idexcel.sohamadminservice.exception.LenderAlreadyExistsException;
import com.idexcel.sohamadminservice.exception.LenderNotFoundException;

@Service
@Transactional
public class LenderServiceImpl implements LenderService{
	@Autowired
	private LenderRepository lenderDAO; 
	
	public void saveLender(Lender lender)
	{   Lender  existingLender = lenderDAO.findByName(lender.getName());
	    if(existingLender!=null) throw new LenderAlreadyExistsException("Lender " + lender.getName() + " Already Exists!");
		this.lenderDAO.save(lender);
	}
	public void updateLender(Lender lender)
	{   Lender existing_lender=lenderDAO.findByid(lender.getId());
	if(existing_lender == null) {
	throw new LenderNotFoundException("Lender " + lender.getId() + " Not Found");
}
	    //if(existingLender!=null) throw new LenderAlreadyExistsException("Lender " + lender.getName() + " Already Exists!");
		this.lenderDAO.save(lender);
	}
	
	public List<Lender> getAllLenders()
	{
		return lenderDAO.findAll();
	}
	
	public Lender findLenderById(String id)
	{   Lender lender=lenderDAO.findByid(id);
		if(lender == null) {
		throw new LenderNotFoundException("Lender " + id + " Not Found");
	}
		 return lender;
	}
	
	public Lender getLenderById(String id)
	{   Lender lender=lenderDAO.findByid(id);
		if(lender == null||lender.getStatus()=="SUSPENDED") {
		throw new LenderNotFoundException("Lender " + id + " Not Found");
	}
		 return lender;
	}
	
	public void removeLender(String id)
	{   Lender lender=lenderDAO.findByid(id);
	  if(lender == null) 
	{
	throw new LenderNotFoundException("Lender " + id + " Not Found");
    }
		lenderDAO.deleteById(id);
	}

	

}
