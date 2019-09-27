package com.idexcel.sohamadminservice.service;

import java.util.List;

import com.idexcel.sohamadminservice.entity.Lender;

public interface LenderService {
	public void saveLender(Lender lender);
	public List<Lender> getAllLenders();
	public Lender findLenderById(String id);

	public Lender getLenderById(String id);
	public void removeLender(String id);
	public void updateLender(Lender lender);

}
