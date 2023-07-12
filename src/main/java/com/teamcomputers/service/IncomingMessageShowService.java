package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.IncomingMessageShow;
import com.teamcomputers.repository.IncomingMessageShowRepository;

@Service
public class IncomingMessageShowService {

	@Autowired
	private IncomingMessageShowRepository incomingMessageShowRepository;

	public void updatename(String mobileno, String fullname) {

		List<IncomingMessageShow> incomingMessageShows = incomingMessageShowRepository.findByMobileNo(mobileno);
//		IncomingListShow upls=incomingListShowRepository.findByMobilenumber(mobilenumber);
		for (IncomingMessageShow incomingMessageShow2 : incomingMessageShows) {
			IncomingMessageShow incomingMessageShow = new IncomingMessageShow();
			incomingMessageShow.setId(incomingMessageShow2.getId());
			incomingMessageShow.setMessage(incomingMessageShow2.getMessage());

			incomingMessageShow.setTime(incomingMessageShow2.getTime());
			incomingMessageShow.setMobileNo(incomingMessageShow2.getMobileNo());
			incomingMessageShow.setType("Receiver");
			incomingMessageShow.setMessagetype("Text");
			incomingMessageShow.setGroup("Reception");
			incomingMessageShow.setName(fullname);
			incomingMessageShow.setIsopen(incomingMessageShow2.getIsopen());
			incomingMessageShowRepository.save(incomingMessageShow);
		}

	}

}
