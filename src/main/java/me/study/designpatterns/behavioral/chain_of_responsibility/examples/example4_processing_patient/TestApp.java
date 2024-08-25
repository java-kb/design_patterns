package me.study.designpatterns.behavioral.chain_of_responsibility.examples.example4_processing_patient;

import java.util.Objects;

public class TestApp {
    public static void main(String[] args) {
        Cashier cashier = new Cashier();

        // Set next for medical department
        Medical medical = new Medical();
        medical.setNext(cashier);

        // Set next for doctor department
        Doctor doctor = new Doctor();
        doctor.setNext(medical);

        // Set next for reception department
        Reception reception = new Reception();
        reception.setNext(doctor);

        Patient patient = new Patient();
        patient.setName("abc");
        // Patient visiting
        reception.execute(patient);
    }
}

interface Department {
    void execute(Patient patient);

    void setNext(Department next);
}

class Reception implements Department {
    private Department next;

    @Override
    public void execute(Patient patient) {
        if (patient.isRegistrationDone()) {
            System.out.println("Patient registration already done");
            Objects.requireNonNull(next).execute(patient);
            return;
        }
        System.out.println("Reception registering patient");
        patient.setRegistrationDone(true);
        Objects.requireNonNull(next).execute(patient);
    }

    @Override
    public void setNext(Department next) {
        this.next = next;
    }
}

class Doctor implements Department {
    private Department next;

    @Override
    public void execute(Patient patient) {
        if (patient.isDoctorCheckUpDone()) {
            System.out.println("Doctor checkup already done");
            Objects.requireNonNull(next).execute(patient);
            return;
        }
        System.out.println("Doctor checking patient");
        patient.setDoctorCheckUpDone(true);
        Objects.requireNonNull(next).execute(patient);
    }

    @Override
    public void setNext(Department next) {
        this.next = next;
    }
}

class Medical implements Department {
    private Department next;

    @Override
    public void execute(Patient patient) {
        if (patient.isMedicineDone()) {
            System.out.println("Medicine already given to patient");
            Objects.requireNonNull(next).execute(patient);
            return;
        }
        System.out.println("Medical giving medicine to patient");
        patient.setMedicineDone(true);
        Objects.requireNonNull(next).execute(patient);
    }

    @Override
    public void setNext(Department next) {
        this.next = next;
    }
}

class Cashier implements Department {
    private Department next;

    @Override
    public void execute(Patient patient) {
        if (patient.isPaymentDone()) {
            System.out.println("Payment Done");
        }
        System.out.println("Cashier getting money from patient patient");
        patient.setPaymentDone(true);
    }

    @Override
    public void setNext(Department next) {
        this.next = next;
    }
}

class Patient {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    private boolean registrationDone;
    private boolean doctorCheckUpDone;
    private boolean medicineDone;
    private boolean paymentDone;

    public String getName() {
        return name;
    }

    public boolean isRegistrationDone() {
        return registrationDone;
    }

    public void setRegistrationDone(boolean registrationDone) {
        this.registrationDone = registrationDone;
    }

    public boolean isDoctorCheckUpDone() {
        return doctorCheckUpDone;
    }

    public void setDoctorCheckUpDone(boolean doctorCheckUpDone) {
        this.doctorCheckUpDone = doctorCheckUpDone;
    }

    public boolean isMedicineDone() {
        return medicineDone;
    }

    public void setMedicineDone(boolean medicineDone) {
        this.medicineDone = medicineDone;
    }

    public boolean isPaymentDone() {
        return paymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        this.paymentDone = paymentDone;
    }
}


