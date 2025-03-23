package org.example.swe304.Services;

import org.example.swe304.Model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    List<Schedule> getAllSchedules();
    Schedule getScheduleById(Long id);
    void deleteSchedule(Long id);
    Schedule updateSchedule(Long id, Schedule schedule);
    List<Schedule> getSchedulesByStudentId(Long studentId);
}