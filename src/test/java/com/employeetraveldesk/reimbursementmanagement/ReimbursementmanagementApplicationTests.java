package com.employeetraveldesk.reimbursementmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementRequestsRepository;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementTypesRepository;
import com.employeetraveldesk.reimbursementmanagement.service.ReimbursementRequestsService;
import com.employeetraveldesk.reimbursementmanagement.service.ReimbursementTypesService;


@SpringBootTest
class ReimbursementmanagementApplicationTests {

	@Autowired
	private ReimbursementRequestsService reimbursementRequestsService;

	@Autowired
	private ReimbursementTypesService reimbursementTypesService;

	@MockBean
	private ReimbursementRequestsRepository reimbursementRequestsRepository;

	@MockBean
	private ReimbursementTypesRepository reimbursementTypesRepository;

	@Test
	public void insertReimbursementRequestTest(){
		when(reimbursementRequestsRepository.save(new ReimbursementRequests()))
		.thenReturn(new ReimbursementRequests());
		//assert(reimbursementRequestsService.insertReimbursementRequest
		//(new ReimbursementRequests())!=null);
	}

	@Test
	public void getReimbursementRequestsByTravelRequestIdTest(){
		when(reimbursementRequestsRepository.findByTravelRequestId(1))
		.thenReturn(java.util.Optional.of(new ReimbursementRequests()));
		assert(reimbursementRequestsService.getReimbursementRequestsByTravelRequestId(1)
		.isPresent());
	}

	@Test
	public void processReimbursementRequestTest(){
		when(reimbursementRequestsRepository.save(new ReimbursementRequests()))
		.thenReturn(new ReimbursementRequests());
		assertEquals(1,reimbursementRequestsService.processReimbursementRequest
				(new ReimbursementRequests(),1));
	}

	@Test
	public void getReimbursementRequestByIdTest(){
		when(reimbursementRequestsRepository.findById(1))
		.thenReturn(java.util.Optional.of(new ReimbursementRequests()));
		assertEquals(1,reimbursementRequestsService.getReimbursementRequestById(1));	
	}

	@Test
	public void getReimbursementTypesTest(){
		when(reimbursementTypesRepository.findAll())
		.thenReturn(Stream.of(new ReimbursementTypes()).collect(Collectors.toList()));
		assertEquals(1,reimbursementTypesService.getReimbursementTypes().size());
	}


}
