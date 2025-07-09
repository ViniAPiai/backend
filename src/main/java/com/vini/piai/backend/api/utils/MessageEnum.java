package com.vini.piai.backend.api.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageEnum {

    PATIENT_CREATED_SUCCESSFULLY("patientCreatedSuccessfully"),
    PATIENT_CREATED_BY_CLINIC_SUCCESSFULLY("patientCreatedByClinicSuccessfully"),
    PATIENT_UPDATED_SUCCESSFULLY("patientUpdatedSuccessfully"),
    PATIENT_UPDATED_BY_CLINIC_SUCCESSFULLY("patientUpdatedByClinicSuccessfully"),
    APPOINTMENT_SCHEDULED_SUCCESSFULLY("appointmentScheduledSuccessfully"),
    APPOINTMENT_UPDATED_SUCCESSFULLY("appointmentUpdatedSuccessfully"),
    APPOINTMENT_DELETED_SUCCESSFULLY("appointmentDeletedSuccessfully"),
    APPOINTMENT_CONFIRMED_SUCCESSFULLY("appointmentConfirmedSuccessfully"),
    CLINICAL_EXAM_CREATED_SUCCESSFULLY("clinicalExamCreatedSuccessfully"),
    CLINICAL_EXAM_UPDATED_SUCCESSFULLY("clinicalExamUpdatedSuccessfully"),
    PATIENT_ARRIVED_SUCCESSFULLY("patientArrivedSuccessfully"),
    APPOINTMENT_IN_PROGRESS("appointmentInProgress"),
    APPOINTMENT_FINISHED("appointmentFinished"),
    APPOINTMENT_CANCELLED("appointmentCancelled"),
    CLINIC_CONFIRMED_APPOINTMENT_SUCCESSFULLY("clinicConfirmedAppointmentSuccessfully"),
    PATIENT_CONFIRMED_APPOINTMENT_SUCCESSFULLY("patientConfirmedAppointmentSuccessfully"),
    CLINIC_CANCELLED_APPOINTMENT_SUCCESSFULLY("clinicCancelledAppointmentSuccessfully"),
    PATIENT_CANCELLED_APPOINTMENT_SUCCESSFULLY("patientCancelledAppointmentSuccessfully"),
    ;

    private final String topic;

    MessageEnum(String topic) {
        this.topic = topic;
    }

    @JsonValue
    public String getTopic() {
        return topic;
    }

}
