package com.caiobraz.opcaocompraoferta.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.entity.Deal;
import com.caiobraz.opcaocompraoferta.model.service.DealService;

@RestController
@RequestMapping("/deals")
public class DealResource {

    private DealService dealService;

    @Autowired
    public DealResource(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping(value = "/{idDeal}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deal> get(@PathVariable Long idDeal) {
        Deal deal = dealService.findByIdWithOptions(idDeal);

        return ResponseEntity.ok().body(deal);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Deal>> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "quantity", defaultValue = "10") Integer quantity,
                                           @RequestParam(value = "sortOrder", defaultValue = "ASC") String sortOrder,
                                           @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Page<Deal> deals = this.dealService.listPerPage(page, quantity, sortOrder, orderBy);

        return ResponseEntity.ok().body(deals);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody Deal deal) {
        deal = this.dealService.insert(deal);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(deal.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deal> update(@Valid @RequestBody Deal deal) {
        deal = this.dealService.update(deal);

        return ResponseEntity.ok().body(deal);
    }

    @DeleteMapping(value = "/{idDeal}")
    public ResponseEntity<Void> delete(@PathVariable Long idDeal) {
        this.dealService.delete(idDeal);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/addOption/{idDeal}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deal> addOption(@PathVariable Long idDeal, @Valid @RequestBody BuyOption option) throws URISyntaxException {
        Deal deal = this.dealService.addOption(idDeal, option);

        return ResponseEntity.ok().location(new URI(deal.getUrl())).body(deal);
    }

    @PostMapping(value = "/updateTotalSold/{idDeal}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deal> updateTotalSold(@PathVariable Long idDeal) throws URISyntaxException {
        Deal deal = this.dealService.updateTotalSold(idDeal);

        return ResponseEntity.ok().location(new URI(deal.getUrl())).body(deal);
    }

}
