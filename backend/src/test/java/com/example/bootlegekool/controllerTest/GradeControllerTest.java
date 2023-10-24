package com.example.bootlegekool.controllerTest;

import com.example.bootlegekool.controller.GradeController;
import com.example.bootlegekool.dto.GradeDTO;
import com.example.bootlegekool.dto.UpdateGradeDTO;
import com.example.bootlegekool.models.Grade;
import com.example.bootlegekool.service.GradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class GradeControllerTest {

    @InjectMocks
    private GradeController gradeController;

    @Mock
    private GradeService gradeService;

    @Test
    public void testGetAllGrades() throws Exception {

        List<Grade> grades = new ArrayList<>();
        when(gradeService.getAllGrades()).thenReturn(grades);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();


        mockMvc.perform(get("/api/v1/ekool/grade/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(gradeService, times(1)).getAllGrades();
    }

    @Test
    public void testAddGrade() throws Exception {
        // Arrange
        GradeDTO gradeDTO = new GradeDTO();
        when(gradeService.addGrade(gradeDTO)).thenReturn(new Grade());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();


        mockMvc.perform(post("/api/v1/ekool/grade/addGrade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(gradeService, times(1)).addGrade(gradeDTO);
    }

    @Test
    public void testUpdateGrade() throws Exception {
        // Arrange
        UpdateGradeDTO updateGradeDTO = new UpdateGradeDTO();

        when(gradeService.updateGrade(updateGradeDTO)).thenReturn("Grade updated successfully.");
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();

        mockMvc.perform(put("/api/v1/ekool/grade/updateGrade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Grade updated successfully."));

        verify(gradeService, times(1)).updateGrade(updateGradeDTO);
    }

    @Test
    public void testDeleteGrade() throws Exception {
        Long gradeId = 1L;

        when(gradeService.deleteGrade(gradeId)).thenReturn("Grade deleted");
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();

        mockMvc.perform(delete("/api/v1/ekool/grade/deleteGrade/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Grade deleted"));

        verify(gradeService, times(1)).deleteGrade(gradeId);
    }
}
