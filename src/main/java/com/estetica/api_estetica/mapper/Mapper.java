package com.estetica.api_estetica.mapper;

import com.estetica.api_estetica.dto.appointment.AppointmentDTO;
import com.estetica.api_estetica.dto.treatment.TreatmentDTO;
import com.estetica.api_estetica.model.entity.Appointment;
import com.estetica.api_estetica.model.entity.Treatment;


public class Mapper {


    public static TreatmentDTO TreatToDTO(Treatment t){
        if(t == null) return null;

        return TreatmentDTO.builder()
                .id(t.getId())
                .name(t.getName())
                .duration(t.getDuration())
                .description(t.getDescription())
                .price(t.getPrice())
                .build();
    }

    public static AppointmentDTO AppointToDTO(Appointment a){
        if(a == null) return null;
        return AppointmentDTO.builder()
                .id(a.getId())
                .clientId(a.getClient().getId())
                .employeeId(a.getEmployee().getId())
                .treatmentId(a.getTreatment().getId())
                .startTime(a.getStartTime())
                .endTime(a.getEndTime())
                .appointmentStatus(a.getAppointmentStatus().toString())
                .build();

    }
}
