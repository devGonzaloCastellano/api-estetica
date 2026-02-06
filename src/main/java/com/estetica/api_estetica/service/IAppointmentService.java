package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.appointment.AppointmentDTO;

import java.util.List;

public interface IAppointmentService {

    List<AppointmentDTO> getAppointment();
    AppointmentDTO createAppointment(AppointmentDTO appointmentDto);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDto);
    void deleteAppointment(Long id);

}
