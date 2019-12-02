package com.caiobraz.opcaocompraoferta.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.service.BuyOptionService;

@RestController
@RequestMapping("/buyOptions")
public class BuyOptionResource {

    private BuyOptionService optionService;

    @Autowired
    public BuyOptionResource(BuyOptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping(value = "/{idOption}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyOption> get(@PathVariable Long idOption) {
        BuyOption buyOption = optionService.findActivesById(idOption);

        return ResponseEntity.ok().body(buyOption);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody BuyOption option) {
        option = this.optionService.insert(option);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(option.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyOption> update(@Valid @RequestBody BuyOption option) {
        option = this.optionService.update(option);

        return ResponseEntity.ok().body(option);
    }

    @DeleteMapping(value = "/{idOption}")
    public ResponseEntity<Void> delete(@PathVariable Long idOption) {
        this.optionService.delete(idOption);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/sellUnit/{idOption}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuyOption> sellUnit(@PathVariable Long idOption) {
        BuyOption option = this.optionService.sellUnit(idOption);

        return ResponseEntity.ok().body(option);
    }


}
