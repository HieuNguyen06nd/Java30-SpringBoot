package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.dto.request.CreatePatientRequest;
import com.hieunguyen.ClinicManagement.entity.Patient;
import com.hieunguyen.ClinicManagement.entity.User;
import com.hieunguyen.ClinicManagement.exception.BusinessException;
import com.hieunguyen.ClinicManagement.exception.ErrorCode;
import com.hieunguyen.ClinicManagement.repository.PatientRepository;
import com.hieunguyen.ClinicManagement.repository.UserRepository;
import com.hieunguyen.ClinicManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    public Patient createPatient(CreatePatientRequest request) {
        // Nếu số điện thoại đã tồn tại → báo lỗi
        if (patientRepository.existsByPhone(request.getPhone())) {
            Patient existing = patientRepository.findByPhone(request.getPhone())
                    .orElseThrow(() -> new BusinessException(ErrorCode.INTERNAL_ERROR));

            // Nếu có tài khoản → trả thông tin
            if (existing.getUser() != null) {
                throw new BusinessException(ErrorCode.DUPLICATE_PHONE, "Số điện thoại đã có tài khoản. Vui lòng đăng nhập.");
            }

            // Nếu chưa có tài khoản → trả về patient đó (không tạo mới)
            return existing;
        }


        Patient patient = Patient.builder()
                .fullName(request.getFullName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
            patient.setUser(user);
        }

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.PATIENT_NOT_FOUND, "Không tìm thấy bệnh nhân với ID: " + id));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Long id, CreatePatientRequest request) {
        Patient existing = getPatientById(id);
        existing.setFullName(request.getFullName());
        existing.setGender(request.getGender());
        existing.setDateOfBirth(request.getDateOfBirth());
        existing.setPhone(request.getPhone());
        existing.setEmail(request.getEmail());
        existing.setAddress(request.getAddress());

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
            existing.setUser(user);
        } else {
            existing.setUser(null);
        }

        return patientRepository.save(existing);
    }

    @Override
    public Patient getByPhone(String phone) {
        return patientRepository.findByPhone(phone)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, "Không tìm thấy bệnh nhân theo số điện thoại"));
    }


    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new BusinessException(ErrorCode.PATIENT_NOT_FOUND, "Không tìm thấy bệnh nhân để xóa");
        }
        patientRepository.deleteById(id);
    }
}
