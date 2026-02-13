package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.appointment.AppointmentCreateDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentResponseDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentUpdateDTO;

import java.util.List;

public interface IAppointmentService {

    List<AppointmentResponseDTO> getAppointments();
    AppointmentResponseDTO createAppointment(AppointmentCreateDTO dto);
    AppointmentResponseDTO updateAppointment(Long id, AppointmentUpdateDTO dto);
    void deleteAppointment(Long id);

}
