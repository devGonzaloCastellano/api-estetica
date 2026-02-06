package com.estetica.api_estetica.service;

import com.estetica.api_estetica.dto.appointment.AppointmentDTO;
import com.estetica.api_estetica.exception.NotFoundException;
import com.estetica.api_estetica.mapper.Mapper;
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
    public List<AppointmentDTO> getAppointment() {
        return appointmentRepository.findAll().stream().map(Mapper::AppointToDTO).toList();
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDto) {
        User uClient = userRepository.findById(appointmentDto.getClientId()).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        User uEmployee = userRepository.findById(appointmentDto.getEmployeeId()).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
        Treatment treatment = treatmentRepository.findById(appointmentDto.getTreatmentId()).orElseThrow(() -> new NotFoundException("Tratamiento no encontrado"));


        Appointment appointment = Appointment.builder()
                .client(uClient)
                .employee(uEmployee)
                .treatment(treatment)
                .startTime(appointmentDto.getStartTime())
                .endTime(appointmentDto.getEndTime())
                .appointmentStatus(AppointmentStatus.valueOf(appointmentDto.getAppointmentStatus()))
                .build();

        return Mapper.AppointToDTO(appointmentRepository.save(appointment));

    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turno no encontrado"));

        if (appointmentDto.getClientId() != null){
            User uClient = userRepository.findById(appointmentDto.getClientId()).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
            appointment.setClient(uClient);
        }

        if (appointmentDto.getEmployeeId()!= null){
            User uEmployee = userRepository.findById(appointmentDto.getEmployeeId()).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
            appointment.setEmployee(uEmployee);
        }

        if (appointmentDto.getTreatmentId() != null){
            Treatment treatment = treatmentRepository.findById(appointmentDto.getTreatmentId()).orElseThrow(() -> new NotFoundException("Tratamiento no encontrado"));
            appointment.setTreatment(treatment);
        }

        if (appointmentDto.getStartTime() != null){
            appointment.setStartTime(appointmentDto.getStartTime());
        }

        if (appointmentDto.getEndTime() != null){
            appointment.setEndTime(appointmentDto.getEndTime());
        }

        if(appointmentDto.getAppointmentStatus() != null){
            appointment.setAppointmentStatus(AppointmentStatus.valueOf(appointmentDto.getAppointmentStatus()));
        }


        return Mapper.AppointToDTO(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id))
            throw new NotFoundException("Turno no encontrado para eliminar");

        appointmentRepository.deleteById(id);
    }
}
