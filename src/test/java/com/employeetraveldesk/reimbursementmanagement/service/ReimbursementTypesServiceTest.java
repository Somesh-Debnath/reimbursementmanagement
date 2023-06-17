package com.employeetraveldesk.reimbursementmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementTypesRepository;

public class ReimbursementTypesServiceTest {
    @Mock
    private ReimbursementTypesRepository reimbursementTypesRepository;

    @InjectMocks
    private ReimbursementTypesService reimbursementTypesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReimbursementTypes() {
        ReimbursementTypes reimbursementType1 = new ReimbursementTypes();
        reimbursementType1.setId(1);
        reimbursementType1.setType("Food");

        ReimbursementTypes reimbursementType2 = new ReimbursementTypes();
        reimbursementType2.setId(2);
        reimbursementType2.setType("Travel");

        when(reimbursementTypesRepository.findAll()).thenReturn(Arrays.asList(reimbursementType1, reimbursementType2));

        List<ReimbursementTypes> types = reimbursementTypesService.getReimbursementTypes();

        assertNotNull(types);
        assertEquals(2, types.size());

        ReimbursementTypes type1 = types.get(0);
        assertEquals(1, type1.getId());
        assertEquals("Food", type1.getType());

        ReimbursementTypes type2 = types.get(1);
        assertEquals(2, type2.getId());
        assertEquals("Travel", type2.getType());

        verify(reimbursementTypesRepository, times(1)).findAll();
    }
}
