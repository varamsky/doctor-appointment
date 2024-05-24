package com.example.doctor_appointment_be.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AuditLogService {

    @AfterReturning(value = "com.example.doctor_appointment_be.audit.CommonPointCutConfig.auditable()", returning = "dto")
    public void auditableMethodCall(JoinPoint joinPoint, Object dto) {
        try {
            final AuditLog auditLog = new AuditLog();

            // One can also use the returned value "dto" to get what the method has returned
            auditLog.setRequest(joinPoint.getSignature());
            auditLog.setDate(LocalDateTime.now());
            auditLog.setModifiedBy(getModifiedBy());

            log.info(auditLog.toString());

        } catch (Exception exception) {
            log.warn("[AuditLog] error occurred while auditableMethodCall logging ! {}", exception.getMessage());
        }
    }

    private String getModifiedBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName.isEmpty() ? "DEMO user" : currentPrincipalName;
    }
}