package com.example.doctor_appointment_be.audit;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCutConfig {
    // we are using this so that if we change the name of location of class or annotation that we provide here
    // for example, Auditable name is changed to Loggable or the package is changed, etc.
    // Instead of changing it everywhere we use this pointcut, we will only need to change it here
    @Pointcut("@annotation(com.example.doctor_appointment_be.audit.Auditable)")
    public void auditable() {
    }
}
