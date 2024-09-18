package br.com.zup.machine_manager.controllers.zupers;

import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperCreateDTO;
import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperResponseDTO;
import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperUpdateDTO;
import br.com.zup.machine_manager.repository.models.Zuper;
import br.com.zup.machine_manager.services.zuper.ZuperService;
import br.com.zup.machine_manager.services.zuper.mappers.ZuperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zupers")
public class ZuperController {
    @Autowired
    private ZuperService zuperService;

    @GetMapping
    public List<ZuperResponseDTO> getAllZupers(){
        return ZuperMapper.toZuperResponseDTO(zuperService.getAllZupers());
    }

    @GetMapping("/{zuperId}")
    private ResponseEntity<?> getZuper(@PathVariable Integer zuperId){
        try{
            return ResponseEntity.ok(ZuperMapper.toZuperResponseDTO(zuperService.getZuperById(zuperId)));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZuperResponseDTO create(@RequestBody ZuperCreateDTO zuper){
        return ZuperMapper.toZuperResponseDTO(zuperService.save(zuper));
    }

    @PutMapping
    public ZuperResponseDTO update(@RequestBody ZuperUpdateDTO zuper){
        return ZuperMapper.toZuperResponseDTO(zuperService.update(zuper));
    }
}
