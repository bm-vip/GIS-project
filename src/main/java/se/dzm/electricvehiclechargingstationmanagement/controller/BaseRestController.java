package se.dzm.electricvehiclechargingstationmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dzm.electricvehiclechargingstationmanagement.model.PageModel;
import se.dzm.electricvehiclechargingstationmanagement.model.Select2Model;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author Behrooz Mohamadi
 * @email behroooz.mohamadi@gmail.com
 * @date 3/27/2018 11:42 AM
 */
public interface BaseRestController<M, ID extends Serializable> {

    @GetMapping(value = {"/findById/{id}"})
    ResponseEntity<M> findById(@PathVariable("id") ID id);

    @GetMapping(value = {"/findAll"})
    ResponseEntity<Page<M>> findAll(@RequestParam(value = "model", required = false) Optional<String> json
            , @PageableDefault Pageable pageable);

    @GetMapping(value = {"/findAllTable"})
    @ResponseBody
    PageModel findAllTable(@RequestParam(value = "model",required = false) Optional<String> json
                         , @RequestParam int start
                         , @RequestParam int length
                         , @RequestParam("order[0][dir]") String dir
                         , @RequestParam("order[0][column]") int columnIndex
                         , HttpServletRequest request);

    @GetMapping(value = {"/findAllSelect"})
    @ResponseBody
    Page<Select2Model> findAllSelect(@RequestParam(value = "model",required = false) Optional<String> json,
                          @RequestParam int page);

    @GetMapping(value = {"/countAll"})
    ResponseEntity<Long> countAll(@RequestParam(value = "model", required = false) Optional<String> json);

    @DeleteMapping(value = {"/deleteById/{id}"})
    ResponseEntity<Void> deleteById(@PathVariable("id") ID id);

    @PostMapping(value = {"/save"})
    @ResponseBody
    ResponseEntity<M> save(@Valid @RequestBody M model);
}
