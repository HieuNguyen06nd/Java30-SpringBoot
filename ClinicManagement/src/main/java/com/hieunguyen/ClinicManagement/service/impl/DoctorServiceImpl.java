package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.dto.request.CreateDoctorRequest;
import com.hieunguyen.ClinicManagement.entity.Doctor;
import com.hieunguyen.ClinicManagement.entity.Role;
import com.hieunguyen.ClinicManagement.entity.User;
import com.hieunguyen.ClinicManagement.exception.BusinessException;
import com.hieunguyen.ClinicManagement.exception.ErrorCode;
import com.hieunguyen.ClinicManagement.repository.DoctorRepository;
import com.hieunguyen.ClinicManagement.repository.RoleRepository;
import com.hieunguyen.ClinicManagement.repository.UserRepository;
import com.hieunguyen.ClinicManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Doctor createDoctor(CreateDoctorRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        Role doctorRole = roleRepository.findByName("DOCTOR")
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND, "Role DOCTOR không tồn tại"));

        user.setRoles(List.of(doctorRole));
        userRepository.save(user);

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setDepartment(request.getDepartment());
        doctor.setAvailable(request.getAvailable());
        doctor.setUser(user);

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DOCTOR_NOT_FOUND, "Không tìm thấy bác sĩ với ID: " + id));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existing = getDoctorById(id);
        existing.setName(updatedDoctor.getName());
        existing.setDepartment(updatedDoctor.getDepartment());
        existing.setAvailable(updatedDoctor.getAvailable());
        return doctorRepository.save(existing);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new BusinessException(ErrorCode.DOCTOR_NOT_FOUND, "Không tìm thấy bác sĩ để xóa");
        }
        doctorRepository.deleteById(id);
    }
}
