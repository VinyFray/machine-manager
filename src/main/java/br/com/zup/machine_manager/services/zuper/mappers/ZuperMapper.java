package br.com.zup.machine_manager.services.zuper.mappers;

import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperCreateDTO;
import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperResponseDTO;
import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperUpdateDTO;
import br.com.zup.machine_manager.repository.models.Zuper;

import java.util.List;

public class ZuperMapper {
    public static Zuper toZuper(ZuperCreateDTO zuperCreateDTO){
        Zuper zuper = new Zuper();
        zuper.setZuperId(0);
        zuper.setName(zuperCreateDTO.getName());
        zuper.setPostalCode(zuperCreateDTO.getPostalCode());
        zuper.setAddress(zuperCreateDTO.getAddress());
        return zuper;
    }

    public static Zuper toZuper(ZuperUpdateDTO zuperUpdateDTO){
        Zuper zuper = new Zuper();
        zuper.setZuperId(zuperUpdateDTO.getId());
        zuper.setName(zuperUpdateDTO.getName());
        zuper.setPostalCode(zuperUpdateDTO.getPostalCode());
        zuper.setAddress(zuperUpdateDTO.getAddress());
        return zuper;
    }

    public static ZuperResponseDTO toZuperResponseDTO(Zuper zuper){
        return new ZuperResponseDTO(
                zuper.getZuperId(),
                zuper.getName(),
                zuper.getAddress(),
                zuper.getPostalCode());
    }

    public static List<ZuperResponseDTO> toZuperResponseDTO(List<Zuper> zupers){
        return zupers.stream().map(ZuperMapper::toZuperResponseDTO).toList();
    }
}
