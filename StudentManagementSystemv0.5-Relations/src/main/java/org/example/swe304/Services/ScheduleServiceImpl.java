package org.example.swe304.Services;

import org.example.swe304.Model.Schedule;
import org.example.swe304.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        if (scheduleRepository.existsById(id)) {
            schedule.setId(id);
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    @Override
    public List<Schedule> getSchedulesByStudentId(Long studentId) {
        return scheduleRepository.findByStudentId(studentId);
    }
}