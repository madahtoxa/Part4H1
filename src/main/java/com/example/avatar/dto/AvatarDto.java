package com.example.avatar.dto;

import com.example.avatar.model.Avatar;

import java.util.Objects;

public class AvatarDto {
    private Long Id;
    private Long studentId;
    private String studentName;

    public AvatarDto() {
    }

    public AvatarDto(Long id, Long studentId, String studentName) {
        Id = id;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public static AvatarDto fromEntity (Avatar entity) {
        AvatarDto dto = new AvatarDto();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setStudentName(entity.getStudent().getName());
        return dto;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarDto avatarDto = (AvatarDto) o;
        return Objects.equals(Id, avatarDto.Id) && Objects.equals(studentId, avatarDto.studentId) && Objects.equals(studentName, avatarDto.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, studentId, studentName);
    }
}
