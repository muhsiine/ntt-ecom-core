package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.StatusDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.StatusMapper;
import ma.nttsquad.nttecomcore.model.repository.StatusRepository;
import ma.nttsquad.nttecomcore.service.StatusSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatusSrvImpl implements StatusSrv {

    final StatusRepository statusRepository;


    @Override
    public List<StatusDto> getAllStatus() {
        List<StatusDto> allStatusDTO = statusRepository.findAll()
                .stream()
                .map(StatusMapper.INSTANCE::entityToDto)
                .toList();
        if(allStatusDTO == null || allStatusDTO.isEmpty()){
            throw new NttNotFoundException("There's no status in database");
        }
        return allStatusDTO;
    }

    @Override
    public StatusDto getStatusById(Long status_id) {
        log.trace("{}", status_id);
        return statusRepository.findById(status_id)
                .map(StatusMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no status with the id '%d'".formatted(status_id)));
    }

    @Override
    public StatusDto saveStatus(StatusDto statusDto) {
        log.trace("save status: {}",statusDto);
        return StatusMapper.INSTANCE.entityToDto(statusRepository.save(StatusMapper.INSTANCE.dtoToEntity(statusDto)));
    }

    @Override
    public StatusDto updateStatus(Long status_id, StatusDto statusDto) {
        log.trace("update status: {} , {}",status_id, statusDto);
        StatusDto status = getStatusById(status_id);
        statusDto.setId(status.getId());
        return StatusMapper.INSTANCE.entityToDto(statusRepository.save(StatusMapper.INSTANCE.dtoToEntity(statusDto)));
    }

    @Override
    public void deleteStatus(Long status_id) {
        log.trace("delete status: {}",status_id);
        statusRepository.deleteById(status_id);
    }

}
