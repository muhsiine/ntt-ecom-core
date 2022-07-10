package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.StatusDto;
import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.StatusMapper;
import ma.nttsquad.nttecomcore.mapper.UserMapper;
import ma.nttsquad.nttecomcore.model.repository.StatusRepository;
import ma.nttsquad.nttecomcore.service.StatusSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatusSrvImpl implements StatusSrv {

    @Autowired
    StatusRepository statusRepository;


    @Override
    public List<StatusDto> getAllStatus() {
        return statusRepository.findAll()
                .stream()
                .map(StatusMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StatusDto getStatusById(Long status_id) {
        log.trace("{}", status_id);
        return statusRepository.findById(status_id)
                .map(StatusMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no status with the id '%d'".formatted(status_id)));
    }

    @Override
    public void saveStatus(StatusDto statusDto) {
        log.trace("start save status: {}",statusDto);
        statusRepository.save(StatusMapper.INSTANCE.dtoToEntity(statusDto));
        log.trace("end save status");
    }

    @Override
    public void updateStatus(Long status_id, StatusDto statusDto) {
        log.trace("start update status: {} {}",status_id, statusDto);
        StatusDto status = getStatusById(status_id);
        statusDto.setId(status.getId());
        statusRepository.save(StatusMapper.INSTANCE.dtoToEntity(statusDto));
        log.trace("end update status: {}",status_id);
    }

    @Override
    public void deleteStatus(Long status_id) {
        log.trace("start delete status: {}",status_id);
        statusRepository.deleteById(status_id);
        log.trace("end delete status: {}",status_id);
    }
}
