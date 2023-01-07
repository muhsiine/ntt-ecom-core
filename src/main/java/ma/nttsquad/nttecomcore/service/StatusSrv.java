package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.StatusDto;
import ma.nttsquad.nttecomcore.dto.UserDto;

import java.util.List;

public interface StatusSrv {

    List<StatusDto> getAllStatus();

    StatusDto getStatusById(Long status_id);

    StatusDto saveStatus(StatusDto statusDto);

    StatusDto updateStatus(Long status_id, StatusDto statusDto);

    void deleteStatus(Long status_id);
}
