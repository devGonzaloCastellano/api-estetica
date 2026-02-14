package com.estetica.api_estetica.mapper;

import com.estetica.api_estetica.dto.appointment.AppointmentCreateDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentResponseDTO;
import com.estetica.api_estetica.dto.appointment.AppointmentUpdateDTO;
import com.estetica.api_estetica.model.entity.Appointment;
import com.estetica.api_estetica.model.entity.Treatment;
import com.estetica.api_estetica.model.entity.User;

public class AppointmentMapper {


    /**
     * Transforma una entidad Appointment en un AppointmentResponseDTO.
     * Solo expone información pública del turno, ocultando detalles internos del dominio.
     *
     * @param appointment entidad Appointment a convertir
     * @return DTO de respuesta con los datos públicos del turno
     */
    public static AppointmentResponseDTO toResponse(Appointment appointment){

        if(appointment == null) return null;

        User userClient = appointment.getClient();
        User userEmployee = appointment.getEmployee();
        Treatment treatment = appointment.getTreatment();

        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .clientName(userClient.getFirstname() + " " + userClient.getLastname())
                .employeeName(userEmployee.getFirstname())
                .treatmentName(treatment.getName())
                .treatmentDescription(treatment.getDescription())
                .startTime(appointment.getStartTime())
                .endTime(appointment.getEndTime())
                .status(appointment.getAppointmentStatus().name())
                .build();
    }

    /**
     * Crea una entidad Appointment a partir de un AppointmentCreateDTO
     * y de las entidades del dominio previamente recuperadas.
     *
     * El método no realiza búsquedas ni validaciones; asume que las
     * entidades proporcionadas ya fueron verificadas en la capa de servicio.
     *
     * @param dto datos recibidos para la creación del turno
     * @param client entidad User correspondiente al cliente
     * @param employee entidad User correspondiente al profesional asignado
     * @param treatment entidad Treatment asociada al turno
     * @return nueva instancia de Appointment con los datos básicos inicializados
     */
    public static Appointment fromCreateDTO(AppointmentCreateDTO dto,
                                            User client,
                                            User employee,
                                            Treatment treatment){
        if (dto == null) return null;

        return Appointment.builder()
                .client(client)
                .employee(employee)
                .treatment(treatment)
                .startTime(dto.getStartTime())
                .build();
    }


    /**
     * Aplica la actualización de datos de un turno ya existente
     * La modificación es parcial: solo se actualizan los campos presentes en el DTO.
     * No devuelve datos, únicamente modifica el estado de la entidad.
     *
     * @param appointment entidad Appointment a modificar
     * @param dto datos provenientes del request
     */
    public static void updateEntity(Appointment appointment,
                                    AppointmentUpdateDTO dto){

        if (dto.getStartTime() != null){
            appointment.setStartTime(dto.getStartTime());
        }
    }

}
