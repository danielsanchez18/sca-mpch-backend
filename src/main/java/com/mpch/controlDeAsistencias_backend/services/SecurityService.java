package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Security;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SecurityService {

    Security saveSecurity(Security security);

    Security getSecurityById(String idSecurity);

    Page<Security> getAllSecurities(Pageable pageable);

    Page<Security> searchSecuritiesByName(String name, Pageable pageable);

    Page<Security> searchSecuritiessByDni(String dni, Pageable pageable);

    Long getTotalSecurities();

    Security updateSecurity(String idSecurity, Security security);

    void deleteSecurity(String idSecurity);

}
