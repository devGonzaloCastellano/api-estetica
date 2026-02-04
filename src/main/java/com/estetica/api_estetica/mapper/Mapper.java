package com.estetica.api_estetica.mapper;

import com.estetica.api_estetica.dto.AppointmentDTO;
import com.estetica.api_estetica.dto.TreatmentDTO;
import com.estetica.api_estetica.dto.UserDTO;
import com.estetica.api_estetica.model.entity.Appointment;
import com.estetica.api_estetica.model.entity.Treatment;
import com.estetica.api_estetica.model.entity.User;

public class Mapper {

    //Mapeos de Entity a DTO
    public static UserDTO toDTO(User u){
        if (u == null) return null;

        return UserDTO.builder()
                .id(u.getId())
                .firstName(u.getFirstname())
                .lastName(u.getLastname())
                .email(u.getEmail())
                .username(u.getUsername())
                .password(u.getPassword())
                .role(u.getUserRole().toString())
                .build();
    }

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
