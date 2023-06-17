package com.employeetraveldesk.reimbursementmanagement.controller;

import com.employeetraveldesk.reimbursementmanagement.dto.ReimbursementTypeDTO;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;
import com.employeetraveldesk.reimbursementmanagement.service.IReimbursementTypesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReimbursementTypesControllerTest {
    @Mock
    private IReimbursementTypesService reimbursementTypesService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReimbursementTypesController reimbursementTypesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReimbursementTypes() {
        // Mock data
        ReimbursementTypes reimbursementType1 = new ReimbursementTypes();
        reimbursementType1.setId(1);
        reimbursementType1.setType("Food");

        ReimbursementTypes reimbursementType2 = new ReimbursementTypes();
        reimbursementType2.setId(2);
        reimbursementType2.setType("Travel");

        List<ReimbursementTypes> expectedReimbursementTypes = Arrays.asList(reimbursementType1, reimbursementType2);

        ReimbursementTypeDTO reimbursementTypeDTO1 = new ReimbursementTypeDTO();
        reimbursementTypeDTO1.setId(1);
        reimbursementTypeDTO1.setType("Food");

        ReimbursementTypeDTO reimbursementTypeDTO2 = new ReimbursementTypeDTO();
        reimbursementTypeDTO2.setId(2);
        reimbursementTypeDTO2.setType("Travel");

        List<ReimbursementTypeDTO> expectedReimbursementTypeDTOs = Arrays.asList(reimbursementTypeDTO1, reimbursementTypeDTO2);

        when(reimbursementTypesService.getReimbursementTypes()).thenReturn(expectedReimbursementTypes);
        when(modelMapper.map(reimbursementType1, ReimbursementTypeDTO.class)).thenReturn(reimbursementTypeDTO1);
        when(modelMapper.map(reimbursementType2, ReimbursementTypeDTO.class)).thenReturn(reimbursementTypeDTO2);

        // Perform the GET request
        List<ReimbursementTypeDTO> actualReimbursementTypes = reimbursementTypesController.getAllReimbursementTypes();

        // Verify the results
        assertEquals(expectedReimbursementTypeDTOs.size(), actualReimbursementTypes.size());
        assertEquals(expectedReimbursementTypeDTOs.get(0).getId(), actualReimbursementTypes.get(0).getId());
        assertEquals(expectedReimbursementTypeDTOs.get(0).getType(), actualReimbursementTypes.get(0).getType());
        assertEquals(expectedReimbursementTypeDTOs.get(1).getId(), actualReimbursementTypes.get(1).getId());
        assertEquals(expectedReimbursementTypeDTOs.get(1).getType(), actualReimbursementTypes.get(1).getType());

        // Verify the service method was called
        verify(reimbursementTypesService, times(1)).getReimbursementTypes();

        // Verify the model mapper was called
        verify(modelMapper, times(1)).map(reimbursementType1, ReimbursementTypeDTO.class);
        verify(modelMapper, times(1)).map(reimbursementType2, ReimbursementTypeDTO.class);
    }
}
