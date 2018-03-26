package spring.jpa.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.jpa.demo.model.SalesPerson;
import spring.jpa.demo.service.SalesPersonService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ngoc on 24/03/2018
 * @project spring-data-jpa
 */
@Controller
@RequestMapping("/salespeople")
public class SalesPersonController
{
    @Autowired
    private SalesPersonService salesPersonService;

    /**
     * @param salesPerson
     * @param _pageable
     * @return
     */
    @GetMapping
    public ModelAndView index(@ModelAttribute("SalesPerson") SalesPerson salesPerson, Pageable _pageable)
    {
        ModelAndView modelAndView = new ModelAndView("salesPerson");
//        Collections.sort(listSalesPeople, Comparator.comparing(SalesPerson::getSalesPersonId));
        Page<SalesPerson> listSalesPeople = salesPersonService.getAll(_pageable);
        modelAndView.addObject("listSalesPeople", listSalesPeople.getContent());
        modelAndView.addObject("lastPage", listSalesPeople.getTotalPages() - 1);
        modelAndView.addObject("currentPage", _pageable.getPageNumber());
        return modelAndView;
    }

    /**
     * @param salesPerson
     * @return
     */
    @GetMapping("/{salesPersonId}")
    public ModelAndView show(@PathVariable("salesPersonId") SalesPerson salesPerson)
    {
        ModelAndView modelAndView = new ModelAndView("viewSalesPerson");
        modelAndView.addObject("SalesPerson", salesPerson);
        return modelAndView;
    }

    /**
     * @param salesPerson
     * @param result
     * @return
     */
    @PostMapping
    public String create(@Valid @ModelAttribute("SalesPerson") SalesPerson salesPerson, BindingResult result)
    {
        if (result.hasErrors()) {
            return "salesPerson";
        }
        salesPersonService.save(salesPerson);
        return "redirect:/salespeople";
    }

    /**
     * @param _salesPersonId
     * @return
     */
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("salesPersonId") String _salesPersonId)
    {
        ModelAndView modelAndView = new ModelAndView("salesPerson");
        salesPersonService.getById(_salesPersonId)
                .ifPresent(salesPerson -> modelAndView.addObject("SalesPerson", salesPerson));
        return modelAndView;
    }

    /**
     * @param _salesPersonId
     * @return
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("salesPersonId") String _salesPersonId)
    {
        salesPersonService.delete(_salesPersonId);
        return "redirect:/salespeople";
    }

    /**
     * @param salesPerson
     * @return
     */
    @GetMapping("/search")
    public ModelAndView search(@ModelAttribute("SalesPerson") SalesPerson salesPerson)
    {
        ModelAndView modelAndView = new ModelAndView("salesPerson");
        modelAndView.addObject("listSalesPeople", salesPersonService.search(salesPerson));
        return modelAndView;
    }

    /**
     * @return
     */
    @ModelAttribute("listStatus")
    public Map<String, String> listStatus()
    {
        Map<String, String> listStutus = new HashMap<>();
        listStutus.put("DE", "Delete");
        listStutus.put("AV", "Active");
        listStutus.put("UA", "UnActive");
        return listStutus;
    }
}
