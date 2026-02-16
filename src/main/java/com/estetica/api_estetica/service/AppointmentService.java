package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.appointment.AppointmentCreateDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentResponseDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentUpdateDTO;
import com.estetica.api_estetica.exception.NotFoundException;
import com.estetica.api_estetica.mapper.AppointmentMapper;
import com.estetica.api_estetica.model.entity.Appointment;
import com.estetica.api_estetica.model.entity.Treatment;
import com.estetica.api_estetica.model.entity.User;
import com.estetica.api_estetica.model.enums.AppointmentStatus;
import com.estetica.api_estetica.repository.AppointmentRepository;
import com.estetica.api_estetica.repository.TreatmentRepository;
import com.estetica.api_estetica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppointmentResponseDTO> getAppointments() {
        return appointmentRepository.findAll().stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentCreateDTO dto) {
        User uClient = userRepository.findById(dto.getClientId()).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        User uEmployee = userRepository.findById(dto.getEmployeeId()).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
        Treatment treatment = treatmentRepository.findById(dto.getTreatmentId()).orElseThrow(() -> new NotFoundException("Tratamiento no encontrado"));
        Appointment appointment = AppointmentMapper.fromCreateDTO(dto,uClient,uEmployee,treatment);

        //Calcula el tiempo final del turno según la duración del tratamiento.
        appointment.recalculateEndTime();
        //Asigna el estado del turno de forma predeterminada
        appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);

        return AppointmentMapper.toResponse(appointmentRepository.save(appointment));

    }

    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentUpdateDTO dto) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turno no encontrado"));

        // Valída si se modifica el tratamiento
        if (dto.getTreatmentId() != null){
            Treatment treatment = treatmentRepository.findById(dto.getTreatmentId())
                    .orElseThrow(() -> new NotFoundException("Tratamiento no encontrado"));
            appointment.setTreatment(treatment);
        }

        AppointmentMapper.updateEntity(appointment,dto);
        appointment.recalculateEndTime();

        return AppointmentMapper.toResponse(appointmentRepository.save(appointment));

    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id))
            throw new NotFoundException("Turno no encontrado para eliminar");

        appointmentRepository.deleteById(id);
    }
}
