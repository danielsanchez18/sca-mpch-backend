package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Certificated;
import com.mpch.controlDeAsistencias_backend.model.Intern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificatedService {

    Certificated generateCertificated(String dni);

    Certificated findCertificateByIntern(String dni);

    Page<Certificated> findAllCertificates(Pageable pageable);

    Page<Certificated> searchCertificatesByInternName(String name, Pageable pageable);

    Page<Certificated> findCertifiedInterns(Pageable pageable);

    Page<Intern> findEligibleInternsForCertification(Pageable pageable);
}
