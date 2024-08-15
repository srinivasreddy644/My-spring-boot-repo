package net.javaguides.springboot.web;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.service.CoinDataService;
import net.javaguides.springboot.service.ApiRequestService;

@Controller
public class CoinDataController {

    private CoinDataService coinDataService;
    private ApiRequestService apiRequestService;

    public CoinDataController(CoinDataService coinDataService, ApiRequestService apiRequestService) {
        this.coinDataService = coinDataService;
        this.apiRequestService = apiRequestService;
    }

    @GetMapping("/coins")
    public String getCoinData(@RequestParam String symbols, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String coinData = coinDataService.getCoinData(symbols);
        apiRequestService.saveRequest(userDetails.getUsername(), symbols, coinData);
        model.addAttribute("coinData", coinData);
        return "coins";
    }
}
