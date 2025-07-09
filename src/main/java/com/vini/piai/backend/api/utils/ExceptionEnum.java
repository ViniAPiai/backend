package com.vini.piai.backend.api.utils;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    PATIENT_NOT_FOUND("patientNotFoundException"),
    PATIENT_UNIQUE_EMAIL("patientUniqueEmailException"),
    PATIENT_UNIQUE_DOCUMENT_NUMBER("patientUniqueDocumentNumberException"),
    PATIENT_NOT_BELONG_TO_CLINIC("patientNotBelongToClinicException"),
    YOU_ALREADY_HAVE_A_SCHEDULED_CONSULTATION("youAlreadyHaveAScheduledConsultationException"),
    PATIENT_ALREADY_IN_CLINIC("patientAlreadyInClinicException"),
    DOCTOR_NOT_BELONG_TO_CLINIC("doctorNotBelongToClinicException"),
    CONSULTATION_ALREADY_IS_FINISHED("consultationAlreadyIsFinishedException"),
    CONSULTATION_IS_CANCELLED("consultationIsCancelledException"),

    ;

    private final String topic;

    ExceptionEnum(String topic) {
        this.topic = topic;
    }
}
